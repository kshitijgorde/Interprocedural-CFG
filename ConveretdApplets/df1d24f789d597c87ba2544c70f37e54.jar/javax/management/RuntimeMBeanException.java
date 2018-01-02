// 
// Decompiled by Procyon v0.5.30
// 

package javax.management;

public class RuntimeMBeanException extends JMRuntimeException
{
    private static final long serialVersionUID = 5274912751982730171L;
    private RuntimeException runtimeException;
    
    public RuntimeMBeanException(final RuntimeException e) {
        this.runtimeException = null;
        this.runtimeException = e;
    }
    
    public RuntimeMBeanException(final RuntimeException e, final String message) {
        super(message);
        this.runtimeException = null;
        this.runtimeException = e;
    }
    
    public RuntimeException getTargetException() {
        return this.runtimeException;
    }
    
    public Throwable getCause() {
        return this.runtimeException;
    }
    
    public String toString() {
        return "RuntimeMBeanException: " + this.getMessage() + ((this.runtimeException == null) ? "" : (" Cause: " + this.runtimeException.toString()));
    }
}
