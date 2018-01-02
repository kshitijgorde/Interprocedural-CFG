// 
// Decompiled by Procyon v0.5.30
// 

package HTTPClient;

import java.net.ProtocolException;
import java.io.IOException;

class DefaultModule implements HTTPClientModule, GlobalConstants
{
    private int req_timeout_retries;
    
    DefaultModule() {
        this.req_timeout_retries = 3;
    }
    
    public int requestHandler(final Request request, final Response[] array) {
        return 0;
    }
    
    public void responsePhase1Handler(final Response response, final RoRequest roRequest) {
    }
    
    public int responsePhase2Handler(final Response response, final Request request) throws IOException {
        final int statusCode = response.getStatusCode();
        switch (statusCode) {
            case 408: {
                if (this.req_timeout_retries-- == 0 || request.getStream() != null) {
                    if (GlobalConstants.DebugMods) {
                        Util.logLine("DefM:  Status " + statusCode + " " + response.getReasonLine() + " not handled - " + "maximum number of retries exceeded");
                    }
                    return 10;
                }
                if (GlobalConstants.DebugMods) {
                    Util.logLine("DefM:  Handling " + statusCode + " " + response.getReasonLine() + " - " + "resending request");
                }
                return 13;
            }
            case 411: {
                if (request.getStream() != null && request.getStream().getLength() == -1) {
                    return 10;
                }
                try {
                    response.getInputStream().close();
                }
                catch (IOException ex) {}
                if (request.getData() != null) {
                    throw new ProtocolException("Received status code 411 even though Content-Length was sent");
                }
                if (GlobalConstants.DebugMods) {
                    Util.logLine("DefM:  Handling " + statusCode + " " + response.getReasonLine() + " - resending " + "request with 'Content-length: 0'");
                }
                request.setData(new byte[0]);
                return 13;
            }
            case 505: {
                return 10;
            }
            default: {
                return 10;
            }
        }
    }
    
    public void responsePhase3Handler(final Response response, final RoRequest roRequest) {
    }
    
    public void trailerHandler(final Response response, final RoRequest roRequest) {
    }
}
