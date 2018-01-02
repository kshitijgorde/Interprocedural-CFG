// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath;

public class XPathProcessorException extends XPathException
{
    static final long serialVersionUID = 1215509418326642603L;
    
    public XPathProcessorException(final String message) {
        super(message);
    }
    
    public XPathProcessorException(final String message, final Exception e) {
        super(message, e);
    }
}
