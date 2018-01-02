// 
// Decompiled by Procyon v0.5.30
// 

package BsscXML;

import java.io.Reader;

public interface IBsscXMLParser
{
    void parse();
    
    void parse(final Reader p0);
    
    void beginElement(final String p0) throws BsscXMLException;
    
    void findValue(final String p0) throws BsscXMLException;
    
    void beginDocument();
    
    void endElement(final String p0) throws BsscXMLException;
    
    void endDocument() throws BsscXMLException;
    
    void findAttr(final String p0, final String p1) throws BsscXMLException;
    
    void setSource(final Reader p0);
}
