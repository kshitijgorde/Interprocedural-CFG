// 
// Decompiled by Procyon v0.5.30
// 

package javax.xml.parsers;

public class FactoryConfigurationError extends Error
{
    private Exception exception;
    
    public FactoryConfigurationError() {
        this.exception = null;
    }
    
    public FactoryConfigurationError(final String msg) {
        super(msg);
        this.exception = null;
    }
    
    public FactoryConfigurationError(final Exception e) {
        super(e.toString());
        this.exception = e;
    }
    
    public FactoryConfigurationError(final Exception e, final String msg) {
        super(msg);
        this.exception = e;
    }
    
    public String getMessage() {
        final String message = super.getMessage();
        if (message == null && this.exception != null) {
            return this.exception.getMessage();
        }
        return message;
    }
    
    public Exception getException() {
        return this.exception;
    }
}
