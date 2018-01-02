// 
// Decompiled by Procyon v0.5.30
// 

package lotus.notes.util;

public interface XMLHandler
{
    void startDocument() throws Exception;
    
    void endDocument() throws Exception;
    
    String resolveEntity(final String p0, final String p1, final String p2) throws Exception;
    
    void startExternalEntity(final String p0) throws Exception;
    
    void endExternalEntity(final String p0) throws Exception;
    
    void doctypeDecl(final String p0, final String p1, final String p2) throws Exception;
    
    void attribute(final String p0, final String p1, final boolean p2) throws Exception;
    
    void startElement(final String p0) throws Exception;
    
    void endElement(final String p0) throws Exception;
    
    void charData(final char[] p0, final int p1, final int p2) throws Exception;
    
    void ignorableWhitespace(final char[] p0, final int p1, final int p2) throws Exception;
    
    void processingInstruction(final String p0, final String p1) throws Exception;
    
    void error(final String p0, final String p1, final int p2, final int p3) throws Exception;
}
