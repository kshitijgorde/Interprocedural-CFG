// 
// Decompiled by Procyon v0.5.30
// 

package hypergraph.applications.hexplorer;

import hypergraph.graphApi.io.ContentHandler;
import java.net.URL;

public class ContentHandlerFactory extends hypergraph.graphApi.io.ContentHandlerFactory
{
    private URL baseUrl;
    
    public void setBaseUrl(final URL baseUrl) {
        this.baseUrl = baseUrl;
    }
    
    public ContentHandler createContentHandler(final String s) {
        if (s.equals("GraphXML")) {
            return new GraphXMLContentHandler(this.baseUrl);
        }
        return super.createContentHandler(s);
    }
}
