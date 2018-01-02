// 
// Decompiled by Procyon v0.5.30
// 

package javax.management;

public class RuntimeErrorException extends JMRuntimeException
{
    private static final long serialVersionUID = 704338937753949796L;
    private Error error;
    
    public RuntimeErrorException(final Error e) {
        this.error = null;
        this.error = e;
    }
    
    public RuntimeErrorException(final Error e, final String message) {
        super(message);
        this.error = null;
        this.error = e;
    }
    
    public Error getTargetError() {
        return this.error;
    }
    
    public Throwable getCause() {
        return this.error;
    }
    
    public String toString() {
        return "RuntimeErrorException: " + this.getMessage() + ((this.error == null) ? "" : (" Cause: " + this.error.toString()));
    }
}
