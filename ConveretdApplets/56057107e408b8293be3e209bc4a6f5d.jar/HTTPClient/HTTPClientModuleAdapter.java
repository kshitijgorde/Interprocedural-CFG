// 
// Decompiled by Procyon v0.5.30
// 

package HTTPClient;

import java.io.IOException;

public class HTTPClientModuleAdapter implements HTTPClientModule
{
    public int requestHandler(final Request request, final Response[] array) throws IOException, ModuleException {
        return 0;
    }
    
    public void responsePhase1Handler(final Response response, final RoRequest roRequest) throws IOException, ModuleException {
    }
    
    public int responsePhase2Handler(final Response response, final Request request) throws IOException, ModuleException {
        return 10;
    }
    
    public void responsePhase3Handler(final Response response, final RoRequest roRequest) throws IOException, ModuleException {
    }
    
    public void trailerHandler(final Response response, final RoRequest roRequest) throws IOException, ModuleException {
    }
}
