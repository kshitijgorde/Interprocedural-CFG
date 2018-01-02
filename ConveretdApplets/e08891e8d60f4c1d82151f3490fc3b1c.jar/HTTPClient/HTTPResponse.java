// 
// Decompiled by Procyon v0.5.30
// 

package HTTPClient;

import java.io.ByteArrayInputStream;
import java.io.InterruptedIOException;
import java.util.Enumeration;
import java.util.Date;
import java.net.URL;
import java.io.IOException;
import java.io.InputStream;

public class HTTPResponse implements HTTPClientModuleConstants
{
    private HTTPClientModule[] modules;
    private int timeout;
    private Request request;
    Response response;
    private HttpOutputStream out_stream;
    private InputStream inp_stream;
    private int StatusCode;
    private String ReasonLine;
    private String Version;
    private URI OriginalURI;
    private URI EffectiveURI;
    private CIHashtable Headers;
    private CIHashtable Trailers;
    private int ContentLength;
    private byte[] Data;
    private boolean initialized;
    private boolean got_trailers;
    private boolean aborted;
    private boolean retry;
    private String method;
    private boolean handle_trailers;
    private boolean trailers_handled;
    
    HTTPResponse(final HTTPClientModule[] modules, final int timeout, final Request orig) {
        this.ContentLength = -1;
        this.initialized = false;
        this.got_trailers = false;
        this.aborted = false;
        this.retry = false;
        this.handle_trailers = false;
        this.trailers_handled = false;
        this.modules = modules;
        this.timeout = timeout;
        try {
            final int qp = orig.getRequestURI().indexOf(63);
            this.OriginalURI = new URI(orig.getConnection().getProtocol(), null, orig.getConnection().getHost(), orig.getConnection().getPort(), (qp < 0) ? orig.getRequestURI() : orig.getRequestURI().substring(0, qp), (qp < 0) ? null : orig.getRequestURI().substring(qp + 1), null);
        }
        catch (ParseException ex) {}
        this.method = orig.getMethod();
    }
    
    void set(final Request req, final Response resp) {
        this.request = req;
        this.response = resp;
        resp.http_resp = this;
        resp.timeout = this.timeout;
        this.aborted = resp.final_resp;
    }
    
    void set(final Request req, final HttpOutputStream out_stream) {
        this.request = req;
        this.out_stream = out_stream;
    }
    
    public final int getStatusCode() throws IOException, ModuleException {
        if (!this.initialized) {
            this.handleResponse();
        }
        return this.StatusCode;
    }
    
    public final String getReasonLine() throws IOException, ModuleException {
        if (!this.initialized) {
            this.handleResponse();
        }
        return this.ReasonLine;
    }
    
    public final String getVersion() throws IOException, ModuleException {
        if (!this.initialized) {
            this.handleResponse();
        }
        return this.Version;
    }
    
    public final String getServer() throws IOException, ModuleException {
        if (!this.initialized) {
            this.handleResponse();
        }
        return this.getHeader("Server");
    }
    
    public final URI getOriginalURI() {
        return this.OriginalURI;
    }
    
    public final URL getEffectiveURL() throws IOException, ModuleException {
        if (!this.initialized) {
            this.handleResponse();
        }
        if (this.EffectiveURI != null) {
            return this.EffectiveURI.toURL();
        }
        return null;
    }
    
    public final URI getEffectiveURI() throws IOException, ModuleException {
        if (!this.initialized) {
            this.handleResponse();
        }
        if (this.EffectiveURI != null) {
            return this.EffectiveURI;
        }
        return this.OriginalURI;
    }
    
    public String getHeader(final String hdr) throws IOException, ModuleException {
        if (!this.initialized) {
            this.handleResponse();
        }
        return (String)this.Headers.get(hdr.trim());
    }
    
    public int getHeaderAsInt(final String hdr) throws IOException, ModuleException, NumberFormatException {
        final String val = this.getHeader(hdr);
        if (val == null) {
            throw new NumberFormatException("null");
        }
        return Integer.parseInt(val);
    }
    
    public Date getHeaderAsDate(final String hdr) throws IOException, IllegalArgumentException, ModuleException {
        String raw_date = this.getHeader(hdr);
        if (raw_date == null) {
            return null;
        }
        if (raw_date.toUpperCase().indexOf("GMT") == -1 && raw_date.indexOf(32) > 0) {
            raw_date = String.valueOf(raw_date) + " GMT";
        }
        Date date;
        try {
            date = Util.parseHttpDate(raw_date);
        }
        catch (IllegalArgumentException iae) {
            long time;
            try {
                time = Long.parseLong(raw_date);
            }
            catch (NumberFormatException ex) {
                throw iae;
            }
            if (time < 0L) {
                time = 0L;
            }
            date = new Date(time * 1000L);
        }
        return date;
    }
    
    public Enumeration listHeaders() throws IOException, ModuleException {
        if (!this.initialized) {
            this.handleResponse();
        }
        return this.Headers.keys();
    }
    
    public String getTrailer(final String trailer) throws IOException, ModuleException {
        if (!this.got_trailers) {
            this.getTrailers();
        }
        return (String)this.Trailers.get(trailer.trim());
    }
    
    public int getTrailerAsInt(final String trailer) throws IOException, ModuleException, NumberFormatException {
        final String val = this.getTrailer(trailer);
        if (val == null) {
            throw new NumberFormatException("null");
        }
        return Integer.parseInt(val);
    }
    
    public Date getTrailerAsDate(final String trailer) throws IOException, IllegalArgumentException, ModuleException {
        String raw_date = this.getTrailer(trailer);
        if (raw_date == null) {
            return null;
        }
        if (raw_date.toUpperCase().indexOf("GMT") == -1 && raw_date.indexOf(32) > 0) {
            raw_date = String.valueOf(raw_date) + " GMT";
        }
        Date date;
        try {
            date = Util.parseHttpDate(raw_date);
        }
        catch (IllegalArgumentException iae) {
            long time;
            try {
                time = Long.parseLong(raw_date);
            }
            catch (NumberFormatException ex) {
                throw iae;
            }
            if (time < 0L) {
                time = 0L;
            }
            date = new Date(time * 1000L);
        }
        return date;
    }
    
    public Enumeration listTrailers() throws IOException, ModuleException {
        if (!this.got_trailers) {
            this.getTrailers();
        }
        return this.Trailers.keys();
    }
    
    public synchronized byte[] getData() throws IOException, ModuleException {
        if (!this.initialized) {
            this.handleResponse();
        }
        if (this.Data == null) {
            try {
                this.readResponseData(this.inp_stream);
            }
            catch (InterruptedIOException ie) {
                throw ie;
            }
            catch (IOException ioe) {
                Log.write(2, "HResp: (\"" + this.method + " " + this.OriginalURI.getPathAndQuery() + "\")");
                Log.write(2, "       ", ioe);
                try {
                    this.inp_stream.close();
                }
                catch (Exception ex) {}
                throw ioe;
            }
            this.inp_stream.close();
        }
        return this.Data;
    }
    
    public synchronized String getText() throws IOException, ModuleException, ParseException {
        final String ct = this.getHeader("Content-Type");
        if (ct == null || !ct.toLowerCase().startsWith("text/")) {
            throw new IOException("Content-Type `" + ct + "' is not a text type");
        }
        String charset = Util.getParameter("charset", ct);
        if (charset == null) {
            charset = "ISO-8859-1";
        }
        return new String(this.getData(), charset);
    }
    
    public synchronized InputStream getInputStream() throws IOException, ModuleException {
        if (!this.initialized) {
            this.handleResponse();
        }
        if (this.Data == null) {
            return this.inp_stream;
        }
        this.getData();
        return new ByteArrayInputStream(this.Data);
    }
    
    public boolean retryRequest() throws IOException, ModuleException {
        if (!this.initialized) {
            try {
                this.handleResponse();
            }
            catch (RetryException ex) {
                this.retry = this.response.retry;
            }
        }
        return this.retry;
    }
    
    public String toString() {
        if (!this.initialized) {
            try {
                this.handleResponse();
            }
            catch (Exception e) {
                if (!(e instanceof InterruptedIOException)) {
                    Log.write(2, "HResp: (\"" + this.method + " " + this.OriginalURI.getPathAndQuery() + "\")");
                    Log.write(2, "       ", e);
                }
                return "Failed to read headers: " + e;
            }
        }
        final String nl = System.getProperty("line.separator", "\n");
        final StringBuffer str = new StringBuffer(this.Version);
        str.append(' ');
        str.append(this.StatusCode);
        str.append(' ');
        str.append(this.ReasonLine);
        str.append(nl);
        if (this.EffectiveURI != null) {
            str.append("Effective-URI: ");
            str.append(this.EffectiveURI);
            str.append(nl);
        }
        final Enumeration hdr_list = this.Headers.keys();
        while (hdr_list.hasMoreElements()) {
            final String hdr = hdr_list.nextElement();
            str.append(hdr);
            str.append(": ");
            str.append(this.Headers.get(hdr));
            str.append(nl);
        }
        return str.toString();
    }
    
    HTTPClientModule[] getModules() {
        return this.modules;
    }
    
    synchronized boolean handleResponse() throws IOException, ModuleException {
        if (this.initialized) {
            return false;
        }
        if (this.out_stream != null) {
            this.response = this.out_stream.getResponse();
            this.response.http_resp = this;
            this.out_stream = null;
        }
        while (true) {
            Label_0040: {
                break Label_0040;
                int idx;
            Label_0083_Outer:
                do {
                Label_0099:
                    while (true) {
                        Label_0104: {
                            break Label_0104;
                            int idx2 = 0;
                            while (idx2 < this.modules.length && !this.aborted) {
                                try {
                                    this.modules[idx2].responsePhase1Handler(this.response, this.request);
                                }
                                catch (RetryException re) {
                                    if (re.restart) {
                                        break Label_0040;
                                    }
                                    throw re;
                                }
                                ++idx2;
                                continue Label_0083_Outer;
                                int sts;
                                Block_7_Outer:Block_13_Outer:Block_12_Outer:
                                while (true) {
                                    this.invokeTrailerHandlers(true);
                                    while (true) {
                                    Block_8_Outer:
                                        while (true) {
                                            while (true) {
                                                Label_0195: {
                                                    break Label_0195;
                                                    idx = -1;
                                                    break Label_0040;
                                                }
                                                return true;
                                                this.invokeTrailerHandlers(false);
                                                return false;
                                                Label_0342: {
                                                    ++idx;
                                                }
                                                sts = this.modules[idx].responsePhase2Handler(this.response, this.request);
                                                Label_0207:
                                                this.request.getConnection().handleRequest(this.request, this, this.response, true);
                                                continue Block_13_Outer;
                                            }
                                        Label_0433:
                                            while (true) {
                                                this.invokeTrailerHandlers(true);
                                                Label_0261: {
                                                    break Label_0261;
                                                    Label_0273: {
                                                        this.request.getConnection().handleRequest(this.request, this, this.response, false);
                                                    }
                                                    idx = -1;
                                                    break Label_0040;
                                                    Label_0298:
                                                    throw new Error("HTTPClient Internal Error: invalid status " + sts + " returned by module " + this.modules[idx].getClass().getName());
                                                }
                                                return true;
                                                this.init(this.response);
                                                break Label_0433;
                                                Label_0239: {
                                                    this.response.getInputStream().close();
                                                }
                                                continue Block_12_Outer;
                                            }
                                            continue Block_8_Outer;
                                        }
                                        Label_0445: {
                                            return false;
                                        }
                                        this.response.getStatusCode();
                                        continue;
                                    }
                                    Label_0168: {
                                        idx = -1;
                                    }
                                    break Label_0040;
                                    Label_0173:
                                    this.response.getInputStream().close();
                                    continue Block_7_Outer;
                                }
                            }
                            // iftrue(Label_0207:, !this.request.internal_subrequest)
                            // switch([Lcom.strobel.decompiler.ast.Label;@5f8ccda8, sts)
                            // iftrue(Label_0407:, this.initialized)
                            // iftrue(Label_0273:, !this.request.internal_subrequest)
                            // iftrue(Label_0261:, !this.handle_trailers)
                            // iftrue(Label_0445:, !this.handle_trailers)
                            // iftrue(Label_0433:, this.request.internal_subrequest)
                            // iftrue(Label_0195:, !this.handle_trailers)
                        }
                        break Label_0099;
                        int idx2 = 0;
                        continue;
                    }
                    idx = 0;
                } while (idx < this.modules.length && !this.aborted);
            }
            for (int idx3 = 0; idx3 < this.modules.length && !this.aborted; ++idx3) {
                this.modules[idx3].responsePhase3Handler(this.response, this.request);
            }
            continue;
        }
    }
    
    void init(final Response resp) {
        if (this.initialized) {
            return;
        }
        this.StatusCode = resp.StatusCode;
        this.ReasonLine = resp.ReasonLine;
        this.Version = resp.Version;
        this.EffectiveURI = resp.EffectiveURI;
        this.ContentLength = resp.ContentLength;
        this.Headers = resp.Headers;
        this.inp_stream = resp.inp_stream;
        this.Data = resp.Data;
        this.retry = resp.retry;
        this.initialized = true;
    }
    
    void invokeTrailerHandlers(final boolean force) throws IOException, ModuleException {
        if (this.trailers_handled) {
            return;
        }
        if (!force && !this.initialized) {
            this.handle_trailers = true;
            return;
        }
        for (int idx = 0; idx < this.modules.length && !this.aborted; ++idx) {
            this.modules[idx].trailerHandler(this.response, this.request);
        }
        this.trailers_handled = true;
    }
    
    void markAborted() {
        this.aborted = true;
    }
    
    private synchronized void getTrailers() throws IOException, ModuleException {
        if (this.got_trailers) {
            return;
        }
        if (!this.initialized) {
            this.handleResponse();
        }
        this.response.getTrailer("Any");
        this.Trailers = this.response.Trailers;
        this.got_trailers = true;
        this.invokeTrailerHandlers(false);
    }
    
    private void readResponseData(final InputStream inp) throws IOException, ModuleException {
        if (this.ContentLength == 0) {
            return;
        }
        if (this.Data == null) {
            this.Data = new byte[0];
        }
        int off = this.Data.length;
        try {
            if (this.getHeader("Content-Length") != null) {
                int rcvd = 0;
                this.Data = new byte[this.ContentLength];
                do {
                    off += rcvd;
                    rcvd = inp.read(this.Data, off, this.ContentLength - off);
                    if (rcvd != -1) {
                        continue;
                    }
                    break;
                } while (off + rcvd < this.ContentLength);
            }
            else {
                final int inc = 1000;
                int rcvd2 = 0;
                do {
                    off += rcvd2;
                    this.Data = Util.resizeArray(this.Data, off + inc);
                } while ((rcvd2 = inp.read(this.Data, off, inc)) != -1);
                this.Data = Util.resizeArray(this.Data, off);
            }
        }
        catch (IOException ioe) {
            this.Data = Util.resizeArray(this.Data, off);
            throw ioe;
        }
        finally {
            try {
                inp.close();
            }
            catch (IOException ex) {}
        }
    }
    
    int getTimeout() {
        return this.timeout;
    }
}
