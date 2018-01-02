// 
// Decompiled by Procyon v0.5.30
// 

package hypergraph.graphApi.io;

public class ContentHandlerFactory
{
    public ContentHandler createContentHandler(final String s) {
        if (s.equals("GraphXML")) {
            return new GraphXMLContentHandler();
        }
        if (s.equals("graphml")) {
            return new GraphMLContentHandler();
        }
        System.out.println("Error in ContentHandlerFactory.createContentHandler : ");
        System.out.println("  Cannot find ContentHandler for DTD " + s);
        return null;
    }
}
