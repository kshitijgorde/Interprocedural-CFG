// 
// Decompiled by Procyon v0.5.30
// 

package HTTPClient;

import java.io.IOException;
import java.util.Vector;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

public class HttpOutputStream extends OutputStream implements GlobalConstants
{
    private int length;
    private int rcvd;
    private Request req;
    private Response resp;
    private OutputStream os;
    private ByteArrayOutputStream bos;
    private Vector filters;
    private int con_to;
    private boolean ignore;
    private NVPair[] trailers;
    
    public HttpOutputStream() {
        this.rcvd = 0;
        this.req = null;
        this.resp = null;
        this.os = null;
        this.bos = null;
        this.filters = new Vector();
        this.con_to = 0;
        this.ignore = false;
        this.trailers = null;
        this.length = -1;
    }
    
    public HttpOutputStream(final int length) {
        this.rcvd = 0;
        this.req = null;
        this.resp = null;
        this.os = null;
        this.bos = null;
        this.filters = new Vector();
        this.con_to = 0;
        this.ignore = false;
        this.trailers = null;
        if (length < 0) {
            throw new IllegalArgumentException("Length must be greater equal 0");
        }
        this.length = length;
    }
    
    void goAhead(final Request req, OutputStream os, final int con_to) {
        this.req = req;
        this.con_to = con_to;
        if (os == null) {
            final ByteArrayOutputStream bos = new ByteArrayOutputStream();
            this.bos = bos;
            os = bos;
        }
        this.os = os;
        for (int size = this.filters.size(), i = 0; i < size; ++i) {
            this.os = ((HttpOutputStreamFilter)this.filters.elementAt(i)).pushStream(this.os, req);
        }
        if (GlobalConstants.DebugConn) {
            Util.logLine("OutS:  Stream ready for writing");
            if (this.bos != null) {
                Util.logLine("OutS:  Buffering all data before sending request");
            }
        }
    }
    
    void ignoreData(final Request req) {
        this.req = req;
        this.ignore = true;
    }
    
    synchronized Response getResponse() {
        while (this.resp == null) {
            try {
                this.wait();
            }
            catch (InterruptedException ex) {}
        }
        return this.resp;
    }
    
    public int getLength() {
        return this.length;
    }
    
    public void write(final int n) throws IOException, IllegalAccessError {
        this.write(new byte[] { (byte)n }, 0, 1);
    }
    
    public synchronized void write(final byte[] array, final int n, final int n2) throws IOException, IllegalAccessError {
        if (this.req == null) {
            throw new IllegalAccessError("Stream not associated with a request");
        }
        if (this.ignore) {
            return;
        }
        try {
            if (this.length != -1 && this.rcvd + n2 > this.length) {
                throw new IOException("Tried to write too many bytes (" + (this.rcvd + n2) + " > " + this.length + ")");
            }
            if (this.bos != null || this.length != -1) {
                this.os.write(array, n, n2);
            }
            else {
                this.os.write(Codecs.chunkedEncode(array, n, n2, null, false));
            }
        }
        catch (IOException ex) {
            this.req.getConnection().closeDemux(ex);
            this.req.getConnection().outputFinished();
            throw ex;
        }
        this.rcvd += n2;
    }
    
    public synchronized void close() throws IOException, IllegalAccessError {
        if (this.req == null) {
            throw new IllegalAccessError("Stream not associated with a request");
        }
        if (this.ignore) {
            return;
        }
        if (this.bos != null) {
            this.os.close();
            this.req.setData(this.bos.toByteArray());
            this.req.setStream(null);
            if (this.trailers != null) {
                final NVPair[] headers = this.req.getHeaders();
                int length = headers.length;
                for (int i = 0; i < length; ++i) {
                    if (headers[i].getName().equalsIgnoreCase("Trailer")) {
                        System.arraycopy(headers, i + 1, headers, i, length - i - 1);
                        --length;
                    }
                }
                final NVPair[] resizeArray = Util.resizeArray(headers, length + this.trailers.length);
                System.arraycopy(this.trailers, 0, resizeArray, length, this.trailers.length);
                this.req.setHeaders(resizeArray);
            }
            if (GlobalConstants.DebugConn) {
                Util.logLine("OutS:  Sending request");
            }
            try {
                this.resp = this.req.getConnection().sendRequest(this.req, this.con_to);
            }
            catch (ModuleException ex) {
                throw new IOException(ex.toString());
            }
            this.notify();
        }
        else {
            try {
                if (this.length == -1) {
                    this.os.write(Codecs.chunkedEncode(null, 0, 0, this.trailers, true));
                }
                else if (this.rcvd < this.length) {
                    throw new IOException("Premature close: only " + this.rcvd + " bytes written instead of exptected " + this.length);
                }
                this.os.flush();
                if (GlobalConstants.DebugConn) {
                    Util.logLine("OutS:  All data sent");
                }
            }
            catch (IOException ex2) {
                this.req.getConnection().closeDemux(ex2);
                throw ex2;
            }
            finally {
                this.req.getConnection().outputFinished();
            }
        }
    }
    
    public void flush() throws IOException {
        this.os.flush();
    }
    
    public void setTrailers(final NVPair[] array) throws IllegalAccessError, IllegalStateException {
        if (this.req == null) {
            throw new IllegalAccessError("Stream not associated with a request");
        }
        if (this.length != -1) {
            throw new IllegalStateException("Entity being sent with a Content-length");
        }
        System.arraycopy(array, 0, this.trailers = new NVPair[array.length], 0, array.length);
    }
    
    public NVPair[] getTrailers() {
        final NVPair[] array = new NVPair[this.trailers.length];
        System.arraycopy(this.trailers, 0, array, 0, this.trailers.length);
        return array;
    }
    
    public void addFilter(final HttpOutputStreamFilter httpOutputStreamFilter) {
        if (this.req != null) {
            throw new IllegalAccessError("Stream already bound to socket");
        }
        this.filters.addElement(httpOutputStreamFilter);
    }
    
    public void reset() {
        this.rcvd = 0;
        this.req = null;
        this.resp = null;
        this.os = null;
        this.bos = null;
        this.filters = new Vector();
        this.con_to = 0;
        this.ignore = false;
        this.trailers = null;
    }
    
    public String toString() {
        return this.getClass().getName() + "[length=" + this.length + "]";
    }
}
