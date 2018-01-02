// 
// Decompiled by Procyon v0.5.30
// 

package BsscXML;

public interface IBsscXMLElementReader
{
    int findChild(final IBsscXMLElementReader p0);
    
    IBsscXMLElementReader getChild(final int p0);
    
    String getValue();
    
    String getAttribute(final String p0);
    
    IBsscXMLElementReader getNextSibling();
    
    IBsscXMLElementReader getParent();
    
    IBsscXMLElementReader getPrevSibling();
    
    String getName();
}
