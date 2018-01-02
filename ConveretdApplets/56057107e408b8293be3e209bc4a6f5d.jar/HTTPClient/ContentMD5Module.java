// 
// Decompiled by Procyon v0.5.30
// 

package HTTPClient;

import java.io.IOException;

class ContentMD5Module implements HTTPClientModule, GlobalConstants
{
    public int requestHandler(final Request request, final Response[] array) {
        return 0;
    }
    
    public void responsePhase1Handler(final Response response, final RoRequest roRequest) {
    }
    
    public int responsePhase2Handler(final Response response, final Request request) {
        return 10;
    }
    
    public void responsePhase3Handler(final Response response, final RoRequest roRequest) throws IOException, ModuleException {
        if (roRequest.getMethod().equals("HEAD")) {
            return;
        }
        final String header = response.getHeader("Content-MD5");
        final String header2 = response.getHeader("Trailer");
        boolean hasToken = false;
        try {
            if (header2 != null) {
                hasToken = Util.hasToken(header2, "Content-MD5");
            }
        }
        catch (ParseException ex) {
            throw new ModuleException(ex.toString());
        }
        if ((header == null && !hasToken) || response.getHeader("Transfer-Encoding") != null) {
            return;
        }
        if (GlobalConstants.DebugMods) {
            if (header != null) {
                Util.logLine("CMD5M: Received digest: " + header + " - pushing md5-check-stream");
            }
            else {
                Util.logLine("CMD5M: Expecting digest in trailer  - pushing md5-check-stream");
            }
        }
        response.inp_stream = new MD5InputStream(response.inp_stream, new VerifyMD5(response));
    }
    
    public void trailerHandler(final Response response, final RoRequest roRequest) {
    }
}
