// 
// Decompiled by Procyon v0.5.30
// 

package HTTPClient;

import java.io.EOFException;
import java.io.SequenceInputStream;
import java.util.Vector;
import java.net.ProtocolException;
import java.io.ByteArrayInputStream;
import java.io.InterruptedIOException;
import java.util.Date;
import java.io.IOException;
import java.io.InputStream;

public final class Response implements RoResponse, GlobalConstants, Cloneable
{
    private HTTPConnection connection;
    private StreamDemultiplexor stream_handler;
    HTTPResponse http_resp;
    int timeout;
    public InputStream inp_stream;
    private RespInputStream resp_inp_stream;
    private String method;
    String resource;
    private boolean used_proxy;
    private boolean sent_entity;
    int StatusCode;
    String ReasonLine;
    String Version;
    URI EffectiveURI;
    CIHashtable Headers;
    CIHashtable Trailers;
    int ContentLength;
    int cd_type;
    byte[] Data;
    boolean reading_headers;
    boolean got_headers;
    boolean got_trailers;
    private boolean interrupted;
    private IOException exception;
    boolean final_resp;
    boolean retry;
    private byte[] buf;
    private char[] hdrs;
    private int buf_pos;
    private int hdr_pos;
    private boolean reading_lines;
    char[] trailers;
    Request req;
    boolean isFirstResponse;
    
    Response(final Request request, final boolean used_proxy, final StreamDemultiplexor stream_handler) throws IOException {
        this.timeout = 0;
        this.resp_inp_stream = null;
        this.StatusCode = 0;
        this.EffectiveURI = null;
        this.Headers = new CIHashtable();
        this.Trailers = new CIHashtable();
        this.ContentLength = -1;
        this.cd_type = 0;
        this.Data = null;
        this.reading_headers = false;
        this.got_headers = false;
        this.got_trailers = false;
        this.interrupted = false;
        this.exception = null;
        this.final_resp = false;
        this.retry = false;
        this.buf = new byte[600];
        this.hdrs = new char[600];
        this.buf_pos = 0;
        this.hdr_pos = 0;
        this.reading_lines = false;
        this.req = null;
        this.isFirstResponse = false;
        this.connection = request.getConnection();
        this.method = request.getMethod();
        this.resource = request.getRequestURI();
        this.used_proxy = used_proxy;
        this.stream_handler = stream_handler;
        this.sent_entity = (request.getData() != null);
        stream_handler.register(this, request);
        this.resp_inp_stream = stream_handler.getStream(this);
        this.inp_stream = this.resp_inp_stream;
    }
    
    Response(final Request request, final InputStream inp_stream) {
        this.timeout = 0;
        this.resp_inp_stream = null;
        this.StatusCode = 0;
        this.EffectiveURI = null;
        this.Headers = new CIHashtable();
        this.Trailers = new CIHashtable();
        this.ContentLength = -1;
        this.cd_type = 0;
        this.Data = null;
        this.reading_headers = false;
        this.got_headers = false;
        this.got_trailers = false;
        this.interrupted = false;
        this.exception = null;
        this.final_resp = false;
        this.retry = false;
        this.buf = new byte[600];
        this.hdrs = new char[600];
        this.buf_pos = 0;
        this.hdr_pos = 0;
        this.reading_lines = false;
        this.req = null;
        this.isFirstResponse = false;
        this.connection = request.getConnection();
        this.method = request.getMethod();
        this.resource = request.getRequestURI();
        this.used_proxy = false;
        this.stream_handler = null;
        this.sent_entity = (request.getData() != null);
        this.inp_stream = inp_stream;
    }
    
    public Response(final String version, final int statusCode, final String reasonLine, final NVPair[] array, final byte[] data, final InputStream inp_stream, final int contentLength) {
        this.timeout = 0;
        this.resp_inp_stream = null;
        this.StatusCode = 0;
        this.EffectiveURI = null;
        this.Headers = new CIHashtable();
        this.Trailers = new CIHashtable();
        this.ContentLength = -1;
        this.cd_type = 0;
        this.Data = null;
        this.reading_headers = false;
        this.got_headers = false;
        this.got_trailers = false;
        this.interrupted = false;
        this.exception = null;
        this.final_resp = false;
        this.retry = false;
        this.buf = new byte[600];
        this.hdrs = new char[600];
        this.buf_pos = 0;
        this.hdr_pos = 0;
        this.reading_lines = false;
        this.req = null;
        this.isFirstResponse = false;
        this.Version = version;
        this.StatusCode = statusCode;
        this.ReasonLine = reasonLine;
        if (array != null) {
            for (int i = 0; i < array.length; ++i) {
                this.setHeader(array[i].getName(), array[i].getValue());
            }
        }
        if (data != null) {
            this.Data = data;
        }
        else if (inp_stream == null) {
            this.Data = new byte[0];
        }
        else {
            this.inp_stream = inp_stream;
            this.ContentLength = contentLength;
        }
        this.got_headers = true;
        this.got_trailers = true;
    }
    
    public final int getStatusCode() throws IOException {
        if (!this.got_headers) {
            this.getHeaders(true);
        }
        return this.StatusCode;
    }
    
    public final String getReasonLine() throws IOException {
        if (!this.got_headers) {
            this.getHeaders(true);
        }
        return this.ReasonLine;
    }
    
    public final String getVersion() throws IOException {
        if (!this.got_headers) {
            this.getHeaders(true);
        }
        return this.Version;
    }
    
    int getContinue() throws IOException {
        this.getHeaders(false);
        return this.StatusCode;
    }
    
    public final URI getEffectiveURI() throws IOException {
        if (!this.got_headers) {
            this.getHeaders(true);
        }
        return this.EffectiveURI;
    }
    
    public void setEffectiveURI(final URI effectiveURI) {
        this.EffectiveURI = effectiveURI;
    }
    
    public String getHeader(final String s) throws IOException {
        if (!this.got_headers) {
            this.getHeaders(true);
        }
        return (String)this.Headers.get(s.trim());
    }
    
    public int getHeaderAsInt(final String s) throws IOException, NumberFormatException {
        return Integer.parseInt(this.getHeader(s));
    }
    
    public Date getHeaderAsDate(final String s) throws IOException, IllegalArgumentException {
        String s2 = this.getHeader(s);
        if (s2 == null) {
            return null;
        }
        if (s2.toUpperCase().indexOf("GMT") == -1) {
            s2 += " GMT";
        }
        Date date;
        try {
            date = Util.parseDate(s2);
        }
        catch (IllegalArgumentException ex) {
            long long1;
            try {
                long1 = Long.parseLong(s2);
            }
            catch (NumberFormatException ex2) {
                throw ex;
            }
            if (long1 < 0L) {
                long1 = 0L;
            }
            date = new Date(long1 * 1000L);
        }
        return date;
    }
    
    public void setHeader(final String s, final String s2) {
        this.Headers.put(s.trim(), s2.trim());
    }
    
    public void deleteHeader(final String s) {
        this.Headers.remove(s.trim());
    }
    
    public String getTrailer(final String s) throws IOException {
        if (!this.got_trailers) {
            this.getTrailers();
        }
        return (String)this.Trailers.get(s.trim());
    }
    
    public int getTrailerAsInt(final String s) throws IOException, NumberFormatException {
        return Integer.parseInt(this.getTrailer(s));
    }
    
    public Date getTrailerAsDate(final String s) throws IOException, IllegalArgumentException {
        String s2 = this.getTrailer(s);
        if (s2 == null) {
            return null;
        }
        if (s2.toUpperCase().indexOf("GMT") == -1) {
            s2 += " GMT";
        }
        Date date;
        try {
            date = Util.parseDate(s2);
        }
        catch (IllegalArgumentException ex) {
            long long1;
            try {
                long1 = Long.parseLong(s2);
            }
            catch (NumberFormatException ex2) {
                throw ex;
            }
            if (long1 < 0L) {
                long1 = 0L;
            }
            date = new Date(long1 * 1000L);
        }
        return date;
    }
    
    public void setTrailer(final String s, final String s2) {
        this.Trailers.put(s.trim(), s2.trim());
    }
    
    public void deleteTrailer(final String s) {
        this.Trailers.remove(s.trim());
    }
    
    public synchronized byte[] getData() throws IOException {
        if (!this.got_headers) {
            this.getHeaders(true);
        }
        if (this.Data != null) {
            if (!this.interrupted) {
                return this.Data;
            }
        }
        try {
            this.readResponseData(this.inp_stream);
        }
        catch (InterruptedIOException ex) {
            throw ex;
        }
        catch (IOException ex2) {
            if (GlobalConstants.DebugResp) {
                Util.logLine("Resp:  (" + this.inp_stream.hashCode() + ")");
                Util.logMessage("       ");
                Util.logStackTrace(ex2);
            }
            try {
                this.inp_stream.close();
            }
            catch (Exception ex3) {}
            throw ex2;
        }
        this.inp_stream.close();
        return this.Data;
    }
    
    public synchronized InputStream getInputStream() throws IOException {
        if (!this.got_headers) {
            this.getHeaders(true);
        }
        if (this.Data == null) {
            return this.inp_stream;
        }
        return new ByteArrayInputStream(this.Data);
    }
    
    public synchronized boolean hasEntity() throws IOException {
        if (!this.got_headers) {
            this.getHeaders(true);
        }
        return this.cd_type != 2;
    }
    
    public void setRetryRequest(final boolean retry) {
        this.retry = retry;
    }
    
    public boolean retryRequest() {
        return this.retry;
    }
    
    private synchronized void getHeaders(final boolean b) throws IOException {
        if (this.got_headers) {
            return;
        }
        if (this.exception != null) {
            throw (IOException)this.exception.fillInStackTrace();
        }
        this.reading_headers = true;
        try {
            do {
                this.Headers.clear();
                this.parseResponseHeaders(this.readResponseHeaders(this.inp_stream));
            } while ((this.StatusCode == 100 && b) || (this.StatusCode > 101 && this.StatusCode < 200));
        }
        catch (IOException exception) {
            if (!(exception instanceof InterruptedIOException)) {
                this.exception = exception;
            }
            if (exception instanceof ProtocolException) {
                this.cd_type = 3;
                if (this.stream_handler != null) {
                    this.stream_handler.markForClose(this);
                }
            }
            throw exception;
        }
        finally {
            this.reading_headers = false;
        }
        if (this.StatusCode == 100) {
            return;
        }
        this.got_headers = true;
        boolean equalsIgnoreCase = false;
        boolean b2 = true;
        boolean b3 = false;
        Vector header = null;
        try {
            header = Util.parseHeader(this.getHeader("Transfer-Encoding"));
        }
        catch (ParseException ex2) {}
        if (header != null) {
            equalsIgnoreCase = header.lastElement().getName().equalsIgnoreCase("chunked");
            for (int i = 0; i < header.size(); ++i) {
                if (header.elementAt(i).getName().equalsIgnoreCase("identity")) {
                    header.removeElementAt(i--);
                }
                else {
                    b2 = false;
                }
            }
        }
        try {
            final String header2;
            if ((header2 = this.getHeader("Content-Type")) != null) {
                final Vector header3 = Util.parseHeader(header2);
                b3 = (header3.contains(new HttpHeaderElement("multipart/byteranges")) || header3.contains(new HttpHeaderElement("multipart/x-byteranges")));
            }
        }
        catch (ParseException ex3) {}
        if (this.method.equals("HEAD") || this.ContentLength == 0 || this.StatusCode < 200 || this.StatusCode == 204 || this.StatusCode == 205 || this.StatusCode == 304) {
            this.Data = new byte[0];
            this.cd_type = 2;
            this.inp_stream.close();
        }
        else if (equalsIgnoreCase) {
            this.cd_type = 5;
            header.removeElementAt(header.size() - 1);
            if (header.size() > 0) {
                this.setHeader("Transfer-Encoding", Util.assembleHeader(header));
            }
            else {
                this.deleteHeader("Transfer-Encoding");
            }
        }
        else if (this.ContentLength != -1 && b2) {
            this.cd_type = 4;
        }
        else if (b3 && b2) {
            this.cd_type = 6;
        }
        else {
            this.cd_type = 3;
            this.ContentLength = -1;
            if (this.stream_handler != null) {
                this.stream_handler.markForClose(this);
            }
            if (this.Version.equals("HTTP/0.9")) {
                this.inp_stream = new SequenceInputStream(new ByteArrayInputStream(this.Data), this.inp_stream);
                this.Data = null;
            }
        }
        if (GlobalConstants.DebugResp) {
            Util.logLine("Resp:  Response entity delimiter: " + ((this.cd_type == 2) ? "No Entity" : ((this.cd_type == 3) ? "Close" : ((this.cd_type == 4) ? "Content-Length" : ((this.cd_type == 5) ? "Chunked" : ((this.cd_type == 6) ? "Multipart" : "???"))))) + " (" + this.inp_stream.hashCode() + ")");
        }
        if (this.isFirstResponse && !this.connection.handleFirstRequest(this.req, this)) {
            Response sendRequest;
            try {
                sendRequest = this.connection.sendRequest(this.req, this.timeout);
            }
            catch (ModuleException ex) {
                throw new IOException(ex.toString());
            }
            sendRequest.getVersion();
            this.StatusCode = sendRequest.StatusCode;
            this.ReasonLine = sendRequest.ReasonLine;
            this.Version = sendRequest.Version;
            this.EffectiveURI = sendRequest.EffectiveURI;
            this.ContentLength = sendRequest.ContentLength;
            this.Headers = sendRequest.Headers;
            this.inp_stream = sendRequest.inp_stream;
            this.Data = sendRequest.Data;
            this.req = null;
        }
        if (this.connection.ServerProtocolVersion < 65537) {
            Vector header4;
            try {
                header4 = Util.parseHeader(this.getHeader("Connection"));
            }
            catch (ParseException ex4) {
                header4 = null;
            }
            if (header4 != null) {
                if (this.connection.getProxyHost() != null) {
                    header4.removeAllElements();
                }
                for (int j = 0; j < header4.size(); ++j) {
                    final String name = header4.elementAt(j).getName();
                    if (!name.equalsIgnoreCase("keep-alive")) {
                        header4.removeElementAt(j);
                        this.deleteHeader(name);
                        --j;
                    }
                }
                if (header4.size() > 0) {
                    this.setHeader("Connection", Util.assembleHeader(header4));
                }
                else {
                    this.deleteHeader("Connection");
                }
            }
            Vector header5;
            try {
                header5 = Util.parseHeader(this.getHeader("Proxy-Connection"));
            }
            catch (ParseException ex5) {
                header5 = null;
            }
            if (header5 != null) {
                if (this.connection.getProxyHost() == null) {
                    header5.removeAllElements();
                }
                for (int k = 0; k < header5.size(); ++k) {
                    final String name2 = header5.elementAt(k).getName();
                    if (!name2.equalsIgnoreCase("keep-alive")) {
                        header5.removeElementAt(k);
                        this.deleteHeader(name2);
                        --k;
                    }
                }
                if (header5.size() > 0) {
                    this.setHeader("Proxy-Connection", Util.assembleHeader(header5));
                }
                else {
                    this.deleteHeader("Proxy-Connection");
                }
            }
        }
        else {
            this.deleteHeader("Proxy-Connection");
        }
    }
    
    private char[] readResponseHeaders(final InputStream inputStream) throws IOException {
        if (GlobalConstants.DebugResp) {
            if (this.buf_pos == 0) {
                Util.logLine("Resp:  Reading Response headers " + this.inp_stream.hashCode());
            }
            else {
                Util.logLine("Resp:  Resuming reading Response headers " + this.inp_stream.hashCode());
            }
        }
        if (!this.reading_lines) {
            try {
                this.cd_type = 0;
                Label_0187: {
                    if (this.buf_pos == 0) {
                        int read;
                        while ((read = inputStream.read()) != -1) {
                            if (!Character.isWhitespace((char)(read & 0xFF))) {
                                this.buf[0] = (byte)(read & 0xFF);
                                this.buf_pos = 1;
                                break Label_0187;
                            }
                        }
                        throw new EOFException("Encountered premature EOF while reading Version");
                    }
                }
                while (this.buf_pos < 5) {
                    final int read2 = inputStream.read(this.buf, this.buf_pos, 5 - this.buf_pos);
                    if (read2 == -1) {
                        throw new EOFException("Encountered premature EOF while reading Version");
                    }
                    this.buf_pos += read2;
                }
            }
            catch (EOFException ex) {
                if (GlobalConstants.DebugResp) {
                    Util.logLine("Resp:  (" + this.inp_stream.hashCode() + ")");
                    Util.logMessage("       ");
                    Util.logStackTrace(ex);
                }
                throw ex;
            }
            for (int i = 0; i < this.buf_pos; ++i) {
                this.hdrs[this.hdr_pos++] = (char)(this.buf[i] & 0xFF);
            }
            this.reading_lines = true;
        }
        if (this.hdrs[0] == 'H' && this.hdrs[1] == 'T' && this.hdrs[2] == 'T' && this.hdrs[3] == 'P' && (this.hdrs[4] == '/' || this.hdrs[4] == ' ')) {
            this.cd_type = 1;
            this.readHeaderBlock(inputStream);
        }
        this.buf_pos = 0;
        this.reading_lines = false;
        final char[] resizeArray = Util.resizeArray(this.hdrs, this.hdr_pos);
        this.hdr_pos = 0;
        return resizeArray;
    }
    
    void readTrailers(final InputStream inputStream) throws IOException {
        try {
            this.readHeaderBlock(inputStream);
            this.trailers = Util.resizeArray(this.hdrs, this.hdr_pos);
        }
        catch (IOException exception) {
            if (!(exception instanceof InterruptedIOException)) {
                this.exception = exception;
            }
            throw exception;
        }
    }
    
    private void readHeaderBlock(final InputStream inputStream) throws IOException {
        int read;
        while ((read = inputStream.read(this.buf, 0, this.buf.length)) > 0) {
            if (this.hdr_pos + read > this.hdrs.length) {
                this.hdrs = Util.resizeArray(this.hdrs, (this.hdr_pos + read) * 2);
            }
            for (int i = 0; i < read; ++i) {
                this.hdrs[this.hdr_pos++] = (char)(this.buf[i] & 0xFF);
            }
        }
        this.hdr_pos -= 2;
    }
    
    private void parseResponseHeaders(final char[] array) throws ProtocolException {
        if (GlobalConstants.DebugResp) {
            Util.logLine("Resp:  Parsing Response headers from Request \"" + this.method + " " + this.resource + "\":  (" + this.inp_stream.hashCode() + ")");
            final String property = System.getProperty("line.separator");
            Util.logMessage(property + new String(array) + property);
        }
        if (array[0] != 'H' || array[1] != 'T' || array[2] != 'T' || array[3] != 'P' || (array[4] != '/' && array[4] != ' ')) {
            this.Version = "HTTP/0.9";
            this.StatusCode = 200;
            this.ReasonLine = "OK";
            this.Data = new byte[array.length];
            for (int i = 0; i < this.Data.length; ++i) {
                this.Data[i] = (byte)array[i];
            }
            return;
        }
        final int n = 0;
        final int space = Util.findSpace(array, n);
        if (space - n > 4) {
            this.Version = new String(array, n, space - n);
        }
        else {
            this.Version = "HTTP/1.0";
        }
        final int skipSpace = Util.skipSpace(array, space);
        int space2 = Util.findSpace(array, skipSpace);
        if (skipSpace == space2) {
            throw new ProtocolException("Invalid HTTP status line received: no status code found in '" + new String(array) + "'");
        }
        try {
            this.StatusCode = Integer.parseInt(new String(array, skipSpace, space2 - skipSpace));
        }
        catch (NumberFormatException ex) {
            throw new ProtocolException("Invalid HTTP status line received: status code '" + new String(array, skipSpace, space2 - skipSpace) + "' not a number in '" + new String(array) + "'");
        }
        final int n2 = space2;
        while (space2 < array.length && array[space2] != '\r' && array[space2] != '\n') {
            ++space2;
        }
        this.ReasonLine = new String(array, n2, space2 - n2).trim();
        if (this.StatusCode >= 300 && this.sent_entity && this.stream_handler != null) {
            this.stream_handler.markForClose(this);
        }
        this.parseHeaderFields(array, Util.skipSpace(array, space2), this.Headers);
        if (this.Headers.get("Trailer") != null && this.resp_inp_stream != null) {
            this.resp_inp_stream.dontTruncate();
        }
        boolean b;
        if (this.Version.equals("HTTP/0.9") || this.Version.equals("HTTP/1.0")) {
            b = false;
        }
        else {
            b = true;
        }
        try {
            final String s = (String)this.Headers.get("Connection");
            final String s2 = (String)this.Headers.get("Proxy-Connection");
            if (((b && s != null && Util.hasToken(s, "close")) || (!b && (this.used_proxy || s == null || !Util.hasToken(s, "keep-alive")) && (!this.used_proxy || s2 == null || !Util.hasToken(s2, "keep-alive")))) && this.stream_handler != null) {
                this.stream_handler.markForClose(this);
            }
        }
        catch (ParseException ex2) {}
    }
    
    private synchronized void getTrailers() throws IOException {
        if (this.got_trailers) {
            return;
        }
        if (this.exception != null) {
            throw (IOException)this.exception.fillInStackTrace();
        }
        if (GlobalConstants.DebugResp) {
            Util.logLine("Resp:  Reading Response trailers " + this.inp_stream.hashCode());
        }
        try {
            if (this.trailers == null && this.resp_inp_stream != null) {
                this.resp_inp_stream.readAll(this.timeout);
            }
            if (this.trailers != null) {
                if (GlobalConstants.DebugResp) {
                    Util.logLine("Resp:  Parsing Response trailers from Request \"" + this.method + " " + this.resource + "\":  (" + this.inp_stream.hashCode() + ")");
                    final String property = System.getProperty("line.separator");
                    Util.logMessage(property + new String(this.hdrs, 0, this.hdr_pos) + property);
                }
                this.parseHeaderFields(this.trailers, 0, this.Trailers);
            }
        }
        catch (IOException exception) {
            if (!(exception instanceof InterruptedIOException)) {
                this.exception = exception;
            }
            throw exception;
        }
        this.got_trailers = true;
    }
    
    private void parseHeaderFields(final char[] array, int n, final CIHashtable ciHashtable) throws ProtocolException {
        int i = n;
        final int length = array.length;
        while (i < length) {
            while (i < length && !Character.isWhitespace(array[i]) && array[i] != ':') {
                ++i;
            }
            final String s = new String(array, n, i - n);
            while (i < length && Character.isWhitespace(array[i])) {
                ++i;
            }
            if (i < length && array[i] == ':' && array[i - 1] != '\n') {
                n = i + 1;
            }
            else {
                n = i;
            }
            String s2 = "";
            if (array[i - 1] != '\n') {
                while (n < length && Character.isWhitespace(array[n])) {
                    ++n;
                }
                for (i = n; i < length && array[i] != '\n'; ++i) {}
                if (array[i - 1] == '\r') {
                    s2 = new String(array, n, i - 1 - n);
                }
                else {
                    s2 = new String(array, n, i - n);
                }
                ++i;
                while (i < length && (array[i] == ' ' || array[i] == '\t')) {
                    for (n = i + 1; n < length && (array[n] == ' ' || array[n] == '\t'); ++n) {}
                    for (i = n; i < length && array[i] != '\n'; ++i) {}
                    if (array[i - 1] == '\r') {
                        s2 = s2 + ' ' + new String(array, n, i - 1 - n);
                    }
                    else {
                        s2 = s2 + ' ' + new String(array, n, i - n);
                    }
                    ++i;
                }
                n = i;
            }
            if (s.equalsIgnoreCase("Content-length")) {
                try {
                    this.ContentLength = Integer.parseInt(s2);
                    if (this.ContentLength < 0) {
                        throw new NumberFormatException();
                    }
                }
                catch (NumberFormatException ex) {
                    throw new ProtocolException("Invalid Content-length header received: " + s2);
                }
                ciHashtable.put(s, s2);
            }
            else {
                final String s3 = (String)ciHashtable.get(s);
                if (s3 == null) {
                    ciHashtable.put(s, s2);
                }
                else {
                    ciHashtable.put(s, s3 + ", " + s2);
                }
            }
        }
    }
    
    private void readResponseData(final InputStream inputStream) throws IOException {
        if (this.Data == null) {
            this.Data = new byte[0];
        }
        int length = this.Data.length;
        try {
            this.interrupted = false;
            if (this.getHeader("Content-Length") != null && this.ContentLength != -1 && this.getHeader("Transfer-Encoding") == null) {
                int read = 0;
                this.Data = Util.resizeArray(this.Data, this.ContentLength);
                do {
                    length += read;
                    read = inputStream.read(this.Data, length, this.ContentLength - length);
                } while (read != -1 && length + read < this.ContentLength);
                if (read == -1) {
                    this.Data = Util.resizeArray(this.Data, length);
                }
            }
            else {
                final int n = 1000;
                int read2 = 0;
                do {
                    length += read2;
                    this.Data = Util.resizeArray(this.Data, length + n);
                } while ((read2 = inputStream.read(this.Data, length, n)) != -1);
                this.Data = Util.resizeArray(this.Data, length);
            }
        }
        catch (InterruptedIOException ex) {
            this.Data = Util.resizeArray(this.Data, length);
            this.interrupted = true;
            throw ex;
        }
        catch (IOException ex2) {
            this.Data = Util.resizeArray(this.Data, length);
            throw ex2;
        }
        finally {
            if (!this.interrupted) {
                try {
                    inputStream.close();
                }
                catch (IOException ex3) {}
            }
        }
    }
    
    void markAsFirstResponse(final Request req) {
        this.req = req;
        this.isFirstResponse = true;
    }
    
    public Object clone() {
        Response response;
        try {
            response = (Response)super.clone();
        }
        catch (CloneNotSupportedException ex) {
            throw new InternalError(ex.toString());
        }
        response.Headers = (CIHashtable)this.Headers.clone();
        response.Trailers = (CIHashtable)this.Trailers.clone();
        return response;
    }
}
