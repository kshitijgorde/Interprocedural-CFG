// 
// Decompiled by Procyon v0.5.30
// 

package hypergraph.graphApi.io;

public interface ContentHandler extends org.xml.sax.ContentHandler
{
    void setReader(final SAXReader p0);
    
    SAXReader getReader();
}
