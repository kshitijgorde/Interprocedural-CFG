// 
// Decompiled by Procyon v0.5.30
// 

package HTTPClient;

import java.io.ByteArrayInputStream;
import java.io.InterruptedIOException;
import java.util.Enumeration;
import java.util.Date;
import java.net.MalformedURLException;
import java.net.URL;
import java.io.IOException;
import java.io.InputStream;

public class HTTPResponse implements GlobalConstants, HTTPClientModuleConstants
{
    private HTTPClientModule[] modules;
    private int timeout;
    private Request request;
    private Response response;
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
    private boolean interrupted;
    private boolean aborted;
    private boolean retry;
    private String method;
    private boolean handle_trailers;
    private boolean trailers_handled;
    
    HTTPResponse(final HTTPClientModule[] modules, final int timeout, final Request request) {
        this.request = null;
        this.response = null;
        this.out_stream = null;
        this.OriginalURI = null;
        this.EffectiveURI = null;
        this.Headers = null;
        this.Trailers = null;
        this.ContentLength = -1;
        this.Data = null;
        this.initialized = false;
        this.got_trailers = false;
        this.interrupted = false;
        this.aborted = false;
        this.retry = false;
        this.method = null;
        this.handle_trailers = false;
        this.trailers_handled = false;
        this.modules = modules;
        this.timeout = timeout;
        try {
            this.OriginalURI = new URI(request.getConnection().getProtocol(), request.getConnection().getHost(), request.getConnection().getPort(), request.getRequestURI());
        }
        catch (ParseException ex) {}
        this.method = request.getMethod();
    }
    
    void set(final Request request, final Response response) {
        this.request = request;
        this.response = response;
        response.http_resp = this;
        response.timeout = this.timeout;
        this.aborted = response.final_resp;
    }
    
    void set(final Request request, final HttpOutputStream out_stream) {
        this.request = request;
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
    
    public final URL getOriginalURL() {
        try {
            return this.OriginalURI.toURL();
        }
        catch (MalformedURLException ex) {
            return null;
        }
    }
    
    public final URL getEffectiveURL() throws IOException, ModuleException {
        if (!this.initialized) {
            this.handleResponse();
        }
        if (this.EffectiveURI != null) {
            return this.EffectiveURI.toURL();
        }
        return this.OriginalURI.toURL();
    }
    
    public String getHeader(final String s) throws IOException, ModuleException {
        if (!this.initialized) {
            this.handleResponse();
        }
        return (String)this.Headers.get(s.trim());
    }
    
    public int getHeaderAsInt(final String s) throws IOException, ModuleException, NumberFormatException {
        return Integer.parseInt(this.getHeader(s));
    }
    
    public Date getHeaderAsDate(final String s) throws IOException, IllegalArgumentException, ModuleException {
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
    
    public Enumeration listHeaders() throws IOException, ModuleException {
        if (!this.initialized) {
            this.handleResponse();
        }
        return this.Headers.keys();
    }
    
    public String getTrailer(final String s) throws IOException, ModuleException {
        if (!this.got_trailers) {
            this.getTrailers();
        }
        return (String)this.Trailers.get(s.trim());
    }
    
    public int getTrailerAsInt(final String s) throws IOException, ModuleException, NumberFormatException {
        return Integer.parseInt(this.getTrailer(s));
    }
    
    public Date getTrailerAsDate(final String s) throws IOException, IllegalArgumentException, ModuleException {
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
                Util.logLine("HResp: (\"" + this.method + " " + this.OriginalURI.getPath() + "\")");
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
    
    public synchronized String getText() throws IOException, ModuleException, ParseException {
        final String header = this.getHeader("Content-Type");
        if (header == null || !header.toLowerCase().startsWith("text/")) {
            throw new IOException("Content-Type `" + header + "' is not a text type");
        }
        String parameter = Util.getParameter("charset", header);
        if (parameter == null) {
            parameter = "ISO-8859-1";
        }
        return new String(this.getData(), parameter);
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
            catch (Exception ex) {
                if (GlobalConstants.DebugResp && !(ex instanceof InterruptedIOException)) {
                    Util.logLine("HResp: (\"" + this.method + " " + this.OriginalURI.getPath() + "\")");
                    Util.logMessage("       ");
                    Util.logStackTrace(ex);
                }
                return "Failed to read headers: " + ex;
            }
        }
        final String property = System.getProperty("line.separator", "\n");
        final StringBuffer sb = new StringBuffer(this.Version);
        sb.append(' ');
        sb.append(this.StatusCode);
        sb.append(' ');
        sb.append(this.ReasonLine);
        sb.append(property);
        if (this.EffectiveURI != null) {
            sb.append("Effective-URI: ");
            sb.append(this.EffectiveURI);
            sb.append(property);
        }
        final Enumeration keys = this.Headers.keys();
        while (keys.hasMoreElements()) {
            final String s = keys.nextElement();
            sb.append(s);
            sb.append(": ");
            sb.append(this.Headers.get(s));
            sb.append(property);
        }
        return sb.toString();
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
    Label_0423:
        while (true) {
            Label_0043: {
                break Label_0043;
                int n;
            Label_0086_Outer:
                do {
                Label_0102:
                    while (true) {
                        Label_0107: {
                            break Label_0107;
                            int n2 = 0;
                        Block_7_Outer:
                            while (n2 < this.modules.length && !this.aborted) {
                                try {
                                    this.modules[n2].responsePhase1Handler(this.response, this.request);
                                }
                                catch (RetryException ex) {
                                    if (ex.restart) {
                                        break Label_0043;
                                    }
                                    throw ex;
                                }
                                ++n2;
                                continue Label_0086_Outer;
                                // iftrue(Label_0461:, !this.handle_trailers)
                                // iftrue(Label_0286:, !this.request.internal_subrequest)
                                // iftrue(Label_0274:, !this.handle_trailers)
                                // iftrue(Label_0205:, !this.handle_trailers)
                                // iftrue(Label_0449:, this.request.internal_subrequest)
                                // iftrue(Label_0217:, !this.request.internal_subrequest)
                                while (true) {
                                    while (true) {
                                        Block_12: {
                                            Label_0205: {
                                            Block_5_Outer:
                                                while (true) {
                                                    Block_8: {
                                                        while (true) {
                                                            Block_13: {
                                                                break Block_13;
                                                                break Label_0423;
                                                                return true;
                                                                Label_0175: {
                                                                    break Label_0043;
                                                                }
                                                                this.invokeTrailerHandlers(true);
                                                                break Label_0205;
                                                                Label_0247:
                                                                break Label_0043;
                                                                Label_0252:
                                                                this.response.getInputStream().close();
                                                                break Block_8;
                                                            }
                                                            this.invokeTrailerHandlers(false);
                                                            return false;
                                                            Label_0180: {
                                                                break Label_0423;
                                                            }
                                                            Label_0183:
                                                            this.response.getInputStream().close();
                                                            continue;
                                                        }
                                                        this.response.getStatusCode();
                                                        break Block_12;
                                                    }
                                                    this.invokeTrailerHandlers(true);
                                                    continue Block_5_Outer;
                                                }
                                                Label_0286: {
                                                    this.request.getConnection().handleRequest(this.request, this, this.response, false);
                                                }
                                                break Label_0043;
                                            }
                                            return true;
                                        }
                                        this.init(this.response);
                                        continue Block_7_Outer;
                                    }
                                    Label_0311: {
                                        final int responsePhase2Handler;
                                        throw new Error("HTTPClient Internal Error: invalid status " + responsePhase2Handler + " returned by module " + this.modules[n].getClass().getName());
                                    }
                                    Label_0172:
                                    ++n;
                                    continue Label_0086_Outer;
                                    Label_0461:
                                    return false;
                                    final int responsePhase2Handler = this.modules[n].responsePhase2Handler(this.response, this.request);
                                    Label_0217:
                                    this.request.getConnection().handleRequest(this.request, this, this.response, true);
                                    continue;
                                }
                            }
                            // switch([Lcom.strobel.decompiler.ast.Label;@3a2f032c, responsePhase2Handler)
                            // iftrue(Label_0247:, !this.initialized)
                        }
                        break Label_0102;
                        int n2 = 0;
                        continue;
                    }
                    n = 0;
                } while (n < this.modules.length && !this.aborted);
            }
            for (int n3 = 0; n3 < this.modules.length && !this.aborted; ++n3) {
                this.modules[n3].responsePhase3Handler(this.response, this.request);
            }
            continue Label_0423;
        }
    }
    
    void init(final Response response) {
        if (this.initialized) {
            return;
        }
        this.StatusCode = response.StatusCode;
        this.ReasonLine = response.ReasonLine;
        this.Version = response.Version;
        this.EffectiveURI = response.EffectiveURI;
        this.ContentLength = response.ContentLength;
        this.Headers = response.Headers;
        this.inp_stream = response.inp_stream;
        this.Data = response.Data;
        this.retry = response.retry;
        this.initialized = true;
    }
    
    void invokeTrailerHandlers(final boolean b) throws IOException, ModuleException {
        if (this.trailers_handled) {
            return;
        }
        if (!b && !this.initialized) {
            this.handle_trailers = true;
            return;
        }
        for (int n = 0; n < this.modules.length && !this.aborted; ++n) {
            this.modules[n].trailerHandler(this.response, this.request);
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
    
    private void readResponseData(final InputStream inputStream) throws IOException, ModuleException {
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
    
    int getTimeout() {
        return this.timeout;
    }
}
