// 
// Decompiled by Procyon v0.5.30
// 

package BsscXML;

public interface IBsscXMLElementBuilder
{
    void setAttribute(final String p0, final String p1);
    
    void setValue(final String p0);
    
    void setParent(final IBsscXMLElementBuilder p0) throws BsscXMLException;
    
    boolean checkName(final String p0);
    
    void addChild(final IBsscXMLElementBuilder p0) throws BsscXMLException;
}
