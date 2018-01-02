// 
// Decompiled by Procyon v0.5.30
// 

package BsscXML;

public interface IBsscXMLDocumentReader
{
    IBsscXMLElementReader getRoot();
    
    void removeConsumer(final IBsscXMLConsumer p0);
    
    void addConsumer(final IBsscXMLConsumer p0);
}
