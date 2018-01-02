// 
// Decompiled by Procyon v0.5.30
// 

package javax.management;

public class ReflectionException extends JMException
{
    private static final long serialVersionUID = 9170809325636915553L;
    private Exception exception;
    
    public ReflectionException(final Exception e) {
        this.exception = null;
        this.exception = e;
    }
    
    public ReflectionException(final Exception e, final String message) {
        super(message);
        this.exception = null;
        this.exception = e;
    }
    
    public Exception getTargetException() {
        return this.exception;
    }
    
    public Throwable getCause() {
        return this.exception;
    }
    
    public String toString() {
        return "ReflectionException: " + this.getMessage() + ((this.exception == null) ? "" : (" Cause: " + this.exception.toString()));
    }
}
