// 
// Decompiled by Procyon v0.5.30
// 

package ji.xml;

import java.util.Hashtable;

public interface jiXMLDocHandler
{
    void startElement(final String p0, final Hashtable p1) throws Exception;
    
    void endElement(final String p0) throws Exception;
    
    void startDocument() throws Exception;
    
    void endDocument() throws Exception;
    
    void text(final String p0) throws Exception;
}
