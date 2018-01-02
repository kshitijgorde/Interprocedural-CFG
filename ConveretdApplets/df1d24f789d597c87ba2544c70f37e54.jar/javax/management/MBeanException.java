// 
// Decompiled by Procyon v0.5.30
// 

package javax.management;

public class MBeanException extends JMException
{
    private Exception exception;
    private static final long serialVersionUID = 4066342430588744142L;
    
    public MBeanException(final Exception exception) {
        this.exception = null;
        this.exception = exception;
    }
    
    public MBeanException(final Exception exception, final String message) {
        super(message);
        this.exception = null;
        this.exception = exception;
    }
    
    public Exception getTargetException() {
        return this.exception;
    }
    
    public Throwable getCause() {
        return this.exception;
    }
    
    public String toString() {
        return "MBeanException: " + this.getMessage() + ((this.exception == null) ? "" : (" Cause: " + this.exception.toString()));
    }
}
