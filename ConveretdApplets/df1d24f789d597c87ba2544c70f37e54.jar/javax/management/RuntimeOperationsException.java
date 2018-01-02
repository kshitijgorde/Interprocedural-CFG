// 
// Decompiled by Procyon v0.5.30
// 

package javax.management;

public class RuntimeOperationsException extends JMRuntimeException
{
    private static final long serialVersionUID = -8408923047489133588L;
    private RuntimeException runtimeException;
    
    public RuntimeOperationsException(final RuntimeException e) {
        this.runtimeException = null;
        this.runtimeException = e;
    }
    
    public RuntimeOperationsException(final RuntimeException e, final String message) {
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
        return "RuntimeOperationsException: " + this.getMessage() + ((this.runtimeException == null) ? "" : (" Cause: " + this.runtimeException.toString()));
    }
}
