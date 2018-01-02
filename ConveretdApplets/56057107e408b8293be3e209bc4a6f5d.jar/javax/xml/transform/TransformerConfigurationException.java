// 
// Decompiled by Procyon v0.5.30
// 

package javax.xml.transform;

public class TransformerConfigurationException extends TransformerException
{
    public TransformerConfigurationException() {
        super("Configuration Error");
    }
    
    public TransformerConfigurationException(final String msg) {
        super(msg);
    }
    
    public TransformerConfigurationException(final Throwable e) {
        super(e);
    }
    
    public TransformerConfigurationException(final String msg, final Throwable e) {
        super(msg, e);
    }
    
    public TransformerConfigurationException(final String message, final SourceLocator locator) {
        super(message, locator);
    }
    
    public TransformerConfigurationException(final String message, final SourceLocator locator, final Throwable e) {
        super(message, locator, e);
    }
}
