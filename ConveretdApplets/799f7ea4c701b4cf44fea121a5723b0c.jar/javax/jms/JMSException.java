// 
// Decompiled by Procyon v0.5.30
// 

package javax.jms;

public class JMSException extends Exception
{
    private String errorCode;
    private Exception linkedException;
    
    public JMSException(final String reason, final String errorCode) {
        super(reason);
        this.errorCode = errorCode;
        this.linkedException = null;
    }
    
    public JMSException(final String reason) {
        this(reason, (String)null);
        this.linkedException = null;
    }
    
    public String getErrorCode() {
        return this.errorCode;
    }
    
    public Exception getLinkedException() {
        return this.linkedException;
    }
    
    public synchronized void setLinkedException(final Exception ex) {
        this.linkedException = ex;
    }
}
