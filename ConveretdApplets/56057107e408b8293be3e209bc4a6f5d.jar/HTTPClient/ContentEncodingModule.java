// 
// Decompiled by Procyon v0.5.30
// 

package HTTPClient;

import java.io.IOException;
import java.util.zip.InflaterInputStream;
import java.util.zip.GZIPInputStream;
import java.util.Vector;

class ContentEncodingModule implements HTTPClientModule, GlobalConstants
{
    public int requestHandler(final Request request, final Response[] array) throws ModuleException {
        NVPair[] headers = request.getHeaders();
        int n = Util.getIndex(headers, "Accept-Encoding");
        Vector header;
        if (n == -1) {
            n = headers.length;
            headers = Util.resizeArray(headers, n + 1);
            request.setHeaders(headers);
            header = new Vector<HttpHeaderElement>();
        }
        else {
            try {
                header = Util.parseHeader(headers[n].getValue());
            }
            catch (ParseException ex) {
                throw new ModuleException(ex.toString());
            }
        }
        final HttpHeaderElement element = Util.getElement(header, "*");
        if (element != null) {
            NVPair[] params;
            for (params = element.getParams(), n = 0; n < params.length && !params[n].getName().equalsIgnoreCase("q"); ++n) {}
            if (n == params.length) {
                return 0;
            }
            if (params[n].getValue() == null || params[n].getValue().length() == 0) {
                throw new ModuleException("Invalid q value for \"*\" in Accept-Encoding header: ");
            }
            try {
                if (Float.valueOf(params[n].getValue()) > 0.0) {
                    return 0;
                }
            }
            catch (NumberFormatException ex2) {
                throw new ModuleException("Invalid q value for \"*\" in Accept-Encoding header: " + ex2.getMessage());
            }
        }
        if (!header.contains(new HttpHeaderElement("deflate"))) {
            header.addElement(new HttpHeaderElement("deflate"));
        }
        if (!header.contains(new HttpHeaderElement("gzip"))) {
            header.addElement(new HttpHeaderElement("gzip"));
        }
        if (!header.contains(new HttpHeaderElement("x-gzip"))) {
            header.addElement(new HttpHeaderElement("x-gzip"));
        }
        if (!header.contains(new HttpHeaderElement("compress"))) {
            header.addElement(new HttpHeaderElement("compress"));
        }
        if (!header.contains(new HttpHeaderElement("x-compress"))) {
            header.addElement(new HttpHeaderElement("x-compress"));
        }
        headers[n] = new NVPair("Accept-Encoding", Util.assembleHeader(header));
        return 0;
    }
    
    public void responsePhase1Handler(final Response response, final RoRequest roRequest) {
    }
    
    public int responsePhase2Handler(final Response response, final Request request) {
        return 10;
    }
    
    public void responsePhase3Handler(final Response response, final RoRequest roRequest) throws IOException, ModuleException {
        final String header = response.getHeader("Content-Encoding");
        if (header == null || roRequest.getMethod().equals("HEAD")) {
            return;
        }
        Vector header2;
        try {
            header2 = Util.parseHeader(header);
        }
        catch (ParseException ex) {
            throw new ModuleException(ex.toString());
        }
        if (header2.size() == 0) {
            return;
        }
        final String name = header2.firstElement().getName();
        if (name.equalsIgnoreCase("gzip") || name.equalsIgnoreCase("x-gzip")) {
            if (GlobalConstants.DebugMods) {
                Util.logLine("CEM:   pushing gzip-input-stream");
            }
            response.inp_stream = new GZIPInputStream(response.inp_stream);
            header2.removeElementAt(header2.size() - 1);
            response.deleteHeader("Content-length");
        }
        else if (name.equalsIgnoreCase("deflate")) {
            if (GlobalConstants.DebugMods) {
                Util.logLine("CEM:   pushing inflater-input-stream");
            }
            response.inp_stream = new InflaterInputStream(response.inp_stream);
            header2.removeElementAt(header2.size() - 1);
            response.deleteHeader("Content-length");
        }
        else if (name.equalsIgnoreCase("compress") || name.equalsIgnoreCase("x-compress")) {
            if (GlobalConstants.DebugMods) {
                Util.logLine("CEM:   pushing uncompress-input-stream");
            }
            response.inp_stream = new UncompressInputStream(response.inp_stream);
            header2.removeElementAt(header2.size() - 1);
            response.deleteHeader("Content-length");
        }
        else if (name.equalsIgnoreCase("identity")) {
            if (GlobalConstants.DebugMods) {
                Util.logLine("CEM:   ignoring 'identity' token");
            }
            header2.removeElementAt(header2.size() - 1);
        }
        else if (GlobalConstants.DebugMods) {
            Util.logLine("CEM:   Unknown content encoding '" + name + "'");
        }
        if (header2.size() > 0) {
            response.setHeader("Content-Encoding", Util.assembleHeader(header2));
        }
        else {
            response.deleteHeader("Content-Encoding");
        }
    }
    
    public void trailerHandler(final Response response, final RoRequest roRequest) {
    }
}
