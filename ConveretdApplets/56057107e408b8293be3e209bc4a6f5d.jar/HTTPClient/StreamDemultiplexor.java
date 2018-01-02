// 
// Decompiled by Procyon v0.5.30
// 

package HTTPClient;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.EOFException;
import java.io.InterruptedIOException;
import java.io.IOException;
import java.net.Socket;

class StreamDemultiplexor implements GlobalConstants
{
    private int Protocol;
    private HTTPConnection Connection;
    private DemultiplexorInputStream Stream;
    private Socket Sock;
    private ResponseHandler MarkedForClose;
    private SocketTimeout.TimeoutEntry Timer;
    private static SocketTimeout TimerThread;
    private LinkedList RespHandlerList;
    private int chunk_len;
    private static byte[] hdr_end;
    private static int[] hdr_cmp;
    private boolean hdr_term_set;
    private boolean trl_term_set;
    private int cur_timeout;
    private boolean m_httpConnectCompatibilityMode;
    private Socket m_hiddenSocket;
    
    StreamDemultiplexor(final int protocol, final Socket socket, final HTTPConnection connection, final boolean httpConnectCompatibilityMode) throws IOException {
        this.Sock = null;
        this.Timer = null;
        this.hdr_term_set = false;
        this.trl_term_set = false;
        this.cur_timeout = 0;
        this.Protocol = protocol;
        this.Connection = connection;
        this.RespHandlerList = new LinkedList();
        this.m_httpConnectCompatibilityMode = httpConnectCompatibilityMode;
        this.m_hiddenSocket = null;
        this.init(socket);
    }
    
    private void init(final Socket sock) throws IOException {
        if (GlobalConstants.DebugDemux) {
            Util.logLine("Demux: Initializing Stream Demultiplexor (" + this.hashCode() + ")");
        }
        this.Sock = sock;
        if (this.m_httpConnectCompatibilityMode) {
            this.Stream = new LazyReadInputStream(sock.getInputStream());
        }
        else {
            this.Stream = new ExtBufferedInputStream(sock.getInputStream());
        }
        this.MarkedForClose = null;
        this.chunk_len = -1;
        this.Timer = StreamDemultiplexor.TimerThread.setTimeout(this);
    }
    
    void register(final Response response, final Request request) throws RetryException {
        synchronized (this.RespHandlerList) {
            if (this.Sock == null) {
                throw new RetryException();
            }
            this.RespHandlerList.addToEnd(new ResponseHandler(response, request, this));
        }
    }
    
    RespInputStream getStream(final Response response) {
        ResponseHandler responseHandler;
        for (responseHandler = (ResponseHandler)this.RespHandlerList.enumerate(); responseHandler != null && responseHandler.resp != response; responseHandler = (ResponseHandler)this.RespHandlerList.next()) {}
        if (responseHandler != null) {
            return responseHandler.stream;
        }
        return null;
    }
    
    public Socket releaseSocket() {
        if (GlobalConstants.DebugDemux) {
            Util.logLine("Demux: releasing socket (" + this.hashCode() + ")");
        }
        Socket hiddenSocket = null;
        synchronized (this) {
            if (this.m_httpConnectCompatibilityMode) {
                try {
                    synchronized (this.RespHandlerList) {
                        ResponseHandler responseHandler2;
                        for (ResponseHandler responseHandler = (ResponseHandler)this.RespHandlerList.enumerate(); responseHandler != null; responseHandler = responseHandler2) {
                            responseHandler2 = (ResponseHandler)this.RespHandlerList.next();
                            if (responseHandler2 == null) {
                                responseHandler.resp.getStatusCode();
                            }
                        }
                        this.close(null, false);
                    }
                    hiddenSocket = this.m_hiddenSocket;
                    this.m_hiddenSocket = null;
                    this.m_httpConnectCompatibilityMode = false;
                }
                catch (IOException ex) {
                    this.m_httpConnectCompatibilityMode = false;
                    this.close(null, false);
                }
            }
        }
        return hiddenSocket;
    }
    
    public boolean isHttpConnectCompatibilityModeUsed() {
        return this.m_httpConnectCompatibilityMode;
    }
    
    void restartTimer() {
        if (this.Timer != null) {
            this.Timer.reset();
        }
    }
    
    int read(final byte[] array, final int n, int chunk_len, final ResponseHandler responseHandler, final int n2) throws IOException {
        if (responseHandler.exception != null) {
            throw (IOException)responseHandler.exception.fillInStackTrace();
        }
        if (responseHandler.eof) {
            return -1;
        }
        ResponseHandler responseHandler2;
        while ((responseHandler2 = (ResponseHandler)this.RespHandlerList.getFirst()) != null && responseHandler2 != responseHandler) {
            try {
                responseHandler2.stream.readAll(n2);
            }
            catch (IOException ex) {
                if (ex instanceof InterruptedIOException) {
                    throw ex;
                }
                throw (IOException)responseHandler.exception.fillInStackTrace();
            }
        }
        synchronized (this) {
            if (responseHandler.exception != null) {
                throw (IOException)responseHandler.exception.fillInStackTrace();
            }
            if (GlobalConstants.DebugDemux) {
                Util.logLine("Demux: Reading for stream " + responseHandler.stream.hashCode());
            }
            if (this.Timer != null) {
                this.Timer.hyber();
            }
            try {
                if (n2 != this.cur_timeout) {
                    if (GlobalConstants.DebugDemux) {
                        Util.logLine("Demux: Setting timeout to " + n2 + " ms");
                    }
                    try {
                        this.Sock.setSoTimeout(n2);
                    }
                    catch (Throwable t) {}
                    this.cur_timeout = n2;
                }
                int n3 = 0;
                switch (responseHandler.resp.cd_type) {
                    case 0: {
                        n3 = this.Stream.read(array, n, chunk_len);
                        if (n3 == -1) {
                            throw new EOFException("Premature EOF encountered");
                        }
                        break;
                    }
                    case 1: {
                        if (!this.hdr_term_set) {
                            this.Stream.setTerminator(StreamDemultiplexor.hdr_end, StreamDemultiplexor.hdr_cmp);
                            this.hdr_term_set = true;
                        }
                        if (this.Stream.atEnd()) {
                            this.Stream.setTerminator(null, null);
                            this.hdr_term_set = false;
                            n3 = 0;
                        }
                        else {
                            n3 = this.Stream.read(array, n, chunk_len);
                        }
                        if (n3 == -1) {
                            throw new EOFException("Premature EOF encountered");
                        }
                        break;
                    }
                    case 2: {
                        n3 = -1;
                        this.close(responseHandler);
                        break;
                    }
                    case 3: {
                        n3 = this.Stream.read(array, n, chunk_len);
                        if (n3 == -1) {
                            this.close(responseHandler);
                            break;
                        }
                        break;
                    }
                    case 4: {
                        final int contentLength = responseHandler.resp.ContentLength;
                        if (chunk_len > contentLength - responseHandler.stream.count) {
                            chunk_len = contentLength - responseHandler.stream.count;
                        }
                        n3 = this.Stream.read(array, n, chunk_len);
                        if (n3 == -1) {
                            throw new EOFException("Premature EOF encountered");
                        }
                        if (responseHandler.stream.count + n3 == contentLength) {
                            this.close(responseHandler);
                            break;
                        }
                        break;
                    }
                    case 5: {
                        if (this.chunk_len == -1) {
                            this.chunk_len = Codecs.getChunkLength(this.Stream);
                        }
                        if (this.chunk_len <= 0) {
                            if (this.trl_term_set || !this.Stream.startsWithCRLF()) {
                                if (!this.trl_term_set) {
                                    this.Stream.setTerminator(StreamDemultiplexor.hdr_end, StreamDemultiplexor.hdr_cmp);
                                    this.trl_term_set = true;
                                }
                                responseHandler.resp.readTrailers(this.Stream);
                                if (!this.Stream.atEnd()) {
                                    throw new EOFException("Premature EOF encountered");
                                }
                                this.Stream.setTerminator(null, null);
                                this.trl_term_set = false;
                            }
                            n3 = -1;
                            this.close(responseHandler);
                            this.chunk_len = -1;
                            break;
                        }
                        if (chunk_len > this.chunk_len) {
                            chunk_len = this.chunk_len;
                        }
                        n3 = this.Stream.read(array, n, chunk_len);
                        if (n3 == -1) {
                            throw new EOFException("Premature EOF encountered");
                        }
                        this.chunk_len -= n3;
                        if (this.chunk_len == 0) {
                            this.Stream.read();
                            this.Stream.read();
                            this.chunk_len = -1;
                            break;
                        }
                        break;
                    }
                    case 6: {
                        responseHandler.setupBoundary(this.Stream);
                        n3 = this.Stream.read(array, n, chunk_len);
                        if (n3 == -1) {
                            throw new EOFException("Premature EOF encountered");
                        }
                        if (this.Stream.atEnd()) {
                            this.Stream.setTerminator(null, null);
                            this.close(responseHandler);
                            break;
                        }
                        break;
                    }
                    default: {
                        throw new Error("Internal Error in StreamDemultiplexor: Invalid cd_type " + responseHandler.resp.cd_type);
                    }
                }
                this.restartTimer();
                return n3;
            }
            catch (InterruptedIOException ex2) {
                this.restartTimer();
                throw ex2;
            }
            catch (IOException ex3) {
                if (GlobalConstants.DebugDemux) {
                    Util.logLine("Demux: (" + this.hashCode() + ")");
                    Util.logMessage("       ");
                    Util.logStackTrace(ex3);
                }
                this.close(ex3, true);
                throw responseHandler.exception;
            }
            catch (ParseException ex4) {
                if (GlobalConstants.DebugDemux) {
                    Util.logLine("Demux: (" + this.hashCode() + ")");
                    Util.logMessage("       ");
                    Util.logStackTrace(ex4);
                }
                this.close(new IOException(ex4.toString()), true);
                throw responseHandler.exception;
            }
        }
    }
    
    synchronized long skip(final long n, final ResponseHandler responseHandler) throws IOException {
        if (responseHandler.exception != null) {
            throw (IOException)responseHandler.exception.fillInStackTrace();
        }
        if (responseHandler.eof) {
            return 0L;
        }
        final int read = this.read(new byte[(int)n], 0, (int)n, responseHandler, 0);
        if (read == -1) {
            return 0L;
        }
        return read;
    }
    
    synchronized int available(final ResponseHandler responseHandler) throws IOException {
        final int available = this.Stream.available();
        if (responseHandler == null) {
            return available;
        }
        if (responseHandler.exception != null) {
            throw (IOException)responseHandler.exception.fillInStackTrace();
        }
        if (responseHandler.eof) {
            return 0;
        }
        switch (responseHandler.resp.cd_type) {
            case 0: {
                return available;
            }
            case 1: {
                return (available > 0) ? 1 : 0;
            }
            case 2: {
                return 0;
            }
            case 3: {
                return available;
            }
            case 4: {
                final int n = responseHandler.resp.ContentLength - responseHandler.stream.count;
                return (available < n) ? available : n;
            }
            case 5: {
                return available;
            }
            case 6: {
                return available;
            }
            default: {
                throw new Error("Internal Error in StreamDemultiplexor: Invalid cd_type " + responseHandler.resp.cd_type);
            }
        }
    }
    
    synchronized void close(final IOException ex, final boolean b) {
        if (this.Sock == null) {
            return;
        }
        if (GlobalConstants.DebugDemux) {
            Util.logLine("Demux: Closing all streams and socket (" + this.hashCode() + ")");
        }
        if (GlobalConstants.DebugDemux) {
            Util.logStackTrace(new Throwable());
        }
        if (this.m_httpConnectCompatibilityMode) {
            this.Stream = new ExtBufferedInputStream(new ByteArrayInputStream(new byte[0]));
        }
        try {
            this.Stream.close();
        }
        catch (IOException ex2) {}
        if (this.m_httpConnectCompatibilityMode) {
            this.m_hiddenSocket = this.Sock;
        }
        else {
            try {
                this.Sock.close();
            }
            catch (IOException ex3) {}
        }
        this.Sock = null;
        if (this.Timer != null) {
            this.Timer.kill();
            this.Timer = null;
        }
        this.Connection.DemuxList.remove(this);
        if (ex != null) {
            synchronized (this.RespHandlerList) {
                this.retry_requests(ex, b);
            }
        }
    }
    
    private void retry_requests(final IOException ex, final boolean conn_reset) {
        RetryException first = null;
        RetryException ex2 = null;
        for (ResponseHandler responseHandler = (ResponseHandler)this.RespHandlerList.enumerate(); responseHandler != null; responseHandler = (ResponseHandler)this.RespHandlerList.next()) {
            if (responseHandler.resp.got_headers) {
                responseHandler.exception = ex;
            }
            else {
                final RetryException exception = new RetryException(ex.getMessage());
                if (first == null) {
                    first = exception;
                }
                exception.request = responseHandler.request;
                exception.response = responseHandler.resp;
                exception.exception = ex;
                exception.conn_reset = conn_reset;
                exception.first = first;
                exception.addToListAfter(ex2);
                ex2 = exception;
                responseHandler.exception = exception;
            }
            this.RespHandlerList.remove(responseHandler);
        }
    }
    
    synchronized void close(final ResponseHandler responseHandler) {
        if (responseHandler != this.RespHandlerList.getFirst()) {
            return;
        }
        if (GlobalConstants.DebugDemux) {
            Util.logLine("Demux: Closing stream " + responseHandler.stream.hashCode());
        }
        responseHandler.eof = true;
        this.RespHandlerList.remove(responseHandler);
        if (responseHandler == this.MarkedForClose) {
            this.close(new IOException("Premature end of Keep-Alive"), false);
        }
        else {
            this.closeSocketIfAllStreamsClosed();
        }
    }
    
    synchronized void closeSocketIfAllStreamsClosed() {
        synchronized (this.RespHandlerList) {
            for (ResponseHandler responseHandler = (ResponseHandler)this.RespHandlerList.enumerate(); responseHandler != null && responseHandler.stream.closed; responseHandler = (ResponseHandler)this.RespHandlerList.next()) {
                if (responseHandler == this.MarkedForClose) {
                    ResponseHandler responseHandler2;
                    do {
                        responseHandler2 = (ResponseHandler)this.RespHandlerList.getFirst();
                        this.RespHandlerList.remove(responseHandler2);
                    } while (responseHandler2 != responseHandler);
                    this.close(new IOException("Premature end of Keep-Alive"), false);
                    return;
                }
            }
        }
    }
    
    synchronized Socket getSocket() {
        if (this.MarkedForClose != null) {
            return null;
        }
        if (this.Timer != null) {
            this.Timer.hyber();
        }
        return this.Sock;
    }
    
    synchronized void markForClose(final Response response) {
        synchronized (this.RespHandlerList) {
            if (this.RespHandlerList.getFirst() == null) {
                this.close(new IOException("Premature end of Keep-Alive"), false);
                return;
            }
        }
        if (this.Timer != null) {
            this.Timer.kill();
            this.Timer = null;
        }
        ResponseHandler markedForClose = null;
        for (ResponseHandler markedForClose2 = (ResponseHandler)this.RespHandlerList.enumerate(); markedForClose2 != null; markedForClose2 = (ResponseHandler)this.RespHandlerList.next()) {
            if (markedForClose2.resp == response) {
                this.MarkedForClose = markedForClose2;
                if (GlobalConstants.DebugDemux) {
                    Util.logLine("Demux: stream " + markedForClose2.stream.hashCode() + " marked for close");
                }
                this.closeSocketIfAllStreamsClosed();
                return;
            }
            if (this.MarkedForClose == markedForClose2) {
                return;
            }
            markedForClose = markedForClose2;
        }
        if (markedForClose == null) {
            return;
        }
        this.MarkedForClose = markedForClose;
        this.closeSocketIfAllStreamsClosed();
        if (GlobalConstants.DebugDemux) {
            Util.logLine("Demux: stream " + markedForClose.stream.hashCode() + " marked for close");
        }
    }
    
    void abort() {
        if (GlobalConstants.DebugDemux) {
            Util.logLine("Demux: Aborting socket (" + this.hashCode() + ")");
        }
        synchronized (this.RespHandlerList) {
            for (ResponseHandler responseHandler = (ResponseHandler)this.RespHandlerList.enumerate(); responseHandler != null; responseHandler = (ResponseHandler)this.RespHandlerList.next()) {
                if (responseHandler.resp.http_resp != null) {
                    responseHandler.resp.http_resp.markAborted();
                }
                if (responseHandler.exception == null) {
                    responseHandler.exception = new IOException("Request aborted by user");
                }
            }
            if (this.Sock != null) {
                try {
                    if (!this.m_httpConnectCompatibilityMode) {
                        try {
                            this.Sock.setSoLinger(false, 0);
                        }
                        catch (Throwable t) {}
                    }
                    if (this.m_httpConnectCompatibilityMode) {
                        this.Stream = new ExtBufferedInputStream(new ByteArrayInputStream(new byte[0]));
                    }
                    try {
                        this.Stream.close();
                    }
                    catch (IOException ex) {}
                    if (this.m_httpConnectCompatibilityMode) {
                        this.m_hiddenSocket = this.Sock;
                    }
                    else {
                        try {
                            this.Sock.close();
                        }
                        catch (IOException ex2) {}
                    }
                    this.Sock = null;
                    if (this.Timer != null) {
                        this.Timer.kill();
                        this.Timer = null;
                    }
                }
                catch (NullPointerException ex3) {}
                this.Connection.DemuxList.remove(this);
            }
        }
    }
    
    public void releaseHttpConnectResources() {
        synchronized (this) {
            this.m_httpConnectCompatibilityMode = false;
            if (this.m_hiddenSocket != null) {
                try {
                    this.m_hiddenSocket.getInputStream().close();
                }
                catch (IOException ex) {}
                try {
                    this.m_hiddenSocket.getOutputStream().close();
                }
                catch (IOException ex2) {}
                try {
                    this.m_hiddenSocket.close();
                }
                catch (IOException ex3) {}
                this.m_hiddenSocket = null;
            }
        }
    }
    
    protected void finalize() throws Throwable {
        if (this.m_hiddenSocket != null) {
            try {
                this.m_hiddenSocket.getInputStream().close();
            }
            catch (IOException ex) {}
            try {
                this.m_hiddenSocket.getOutputStream().close();
            }
            catch (IOException ex2) {}
            try {
                this.m_hiddenSocket.close();
            }
            catch (IOException ex3) {}
            this.m_hiddenSocket = null;
        }
        this.m_httpConnectCompatibilityMode = false;
        this.close(null, false);
        super.finalize();
    }
    
    public String toString() {
        String s = null;
        switch (this.Protocol) {
            case 0: {
                s = "HTTP";
                break;
            }
            case 1: {
                s = "HTTPS";
                break;
            }
            case 2: {
                s = "SHTTP";
                break;
            }
            case 3: {
                s = "HTTP_NG";
                break;
            }
            default: {
                throw new Error("HTTPClient Internal Error: invalid protocol " + this.Protocol);
            }
        }
        return this.getClass().getName() + "[Protocol=" + s + "]";
    }
    
    static {
        StreamDemultiplexor.TimerThread = null;
        StreamDemultiplexor.hdr_end = new byte[] { 13, 10, 13, 10 };
        StreamDemultiplexor.hdr_cmp = Util.compile_search(StreamDemultiplexor.hdr_end);
        (StreamDemultiplexor.TimerThread = new SocketTimeout(60)).start();
    }
    
    private static class SocketTimeout extends Thread implements GlobalConstants
    {
        private TimeoutEntry[] time_list;
        private int current;
        
        SocketTimeout(final int n) {
            super("SocketTimeout");
            try {
                this.setDaemon(true);
            }
            catch (SecurityException ex) {}
            this.setPriority(10);
            this.time_list = new TimeoutEntry[n];
            for (int i = 0; i < n; ++i) {
                this.time_list[i] = new TimeoutEntry(null);
                final TimeoutEntry timeoutEntry = this.time_list[i];
                final TimeoutEntry timeoutEntry2 = this.time_list[i];
                final TimeoutEntry timeoutEntry3 = this.time_list[i];
                timeoutEntry2.prev = timeoutEntry3;
                timeoutEntry.next = timeoutEntry3;
            }
            this.current = 0;
        }
        
        public TimeoutEntry setTimeout(final StreamDemultiplexor streamDemultiplexor) {
            final TimeoutEntry timeoutEntry = new TimeoutEntry(streamDemultiplexor);
            synchronized (this.time_list) {
                timeoutEntry.next = this.time_list[this.current];
                timeoutEntry.prev = this.time_list[this.current].prev;
                timeoutEntry.prev.next = timeoutEntry;
                timeoutEntry.next.prev = timeoutEntry;
            }
            return timeoutEntry;
        }
        
        public void run() {
        Label_0121_Outer:
            while (true) {
                try {
                    Thread.sleep(1000L);
                }
                catch (InterruptedException ex) {}
                while (true) {
                    TimeoutEntry timeoutEntry = null;
                    final TimeoutEntry timeoutEntry2;
                    Label_0180: {
                        synchronized (this.time_list) {
                            for (timeoutEntry = this.time_list[this.current].next; timeoutEntry != this.time_list[this.current]; timeoutEntry = timeoutEntry.next) {
                                timeoutEntry.restart = false;
                            }
                            ++this.current;
                            if (this.current >= this.time_list.length) {
                                this.current = 0;
                            }
                            timeoutEntry = this.time_list[this.current].next;
                            timeoutEntry2 = this.time_list[this.current];
                            break Label_0180;
                        }
                        try {
                            synchronized (timeoutEntry.demux) {
                                if (timeoutEntry.alive && !timeoutEntry.hyber) {
                                    timeoutEntry.demux.markForClose(null);
                                    timeoutEntry.kill();
                                }
                            }
                        }
                        catch (NullPointerException ex2) {}
                        timeoutEntry = timeoutEntry.next;
                    }
                    if (timeoutEntry == timeoutEntry2) {
                        continue Label_0121_Outer;
                    }
                    continue;
                }
            }
        }
        
        private class TimeoutEntry
        {
            boolean restart;
            boolean hyber;
            boolean alive;
            StreamDemultiplexor demux;
            TimeoutEntry next;
            TimeoutEntry prev;
            
            TimeoutEntry(final StreamDemultiplexor demux) {
                this.restart = false;
                this.hyber = false;
                this.alive = true;
                this.next = null;
                this.prev = null;
                this.demux = demux;
            }
            
            void reset() {
                this.hyber = false;
                if (this.restart) {
                    return;
                }
                this.restart = true;
                synchronized (SocketTimeout.this.time_list) {
                    this.next.prev = this.prev;
                    this.prev.next = this.next;
                    this.next = SocketTimeout.this.time_list[SocketTimeout.this.current];
                    this.prev = SocketTimeout.this.time_list[SocketTimeout.this.current].prev;
                    this.prev.next = this;
                    this.next.prev = this;
                }
            }
            
            void hyber() {
                if (this.alive) {
                    this.hyber = true;
                }
            }
            
            void kill() {
                this.alive = false;
                this.restart = false;
                this.hyber = false;
                synchronized (SocketTimeout.this.time_list) {
                    this.next.prev = this.prev;
                    this.prev.next = this.next;
                }
            }
        }
    }
}
