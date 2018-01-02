// 
// Decompiled by Procyon v0.5.30
// 

package javax.xml.transform;

public interface ErrorListener
{
    void warning(final TransformerException p0) throws TransformerException;
    
    void error(final TransformerException p0) throws TransformerException;
    
    void fatalError(final TransformerException p0) throws TransformerException;
}
