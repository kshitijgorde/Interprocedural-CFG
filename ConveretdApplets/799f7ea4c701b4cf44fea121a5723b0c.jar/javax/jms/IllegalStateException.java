// 
// Decompiled by Procyon v0.5.30
// 

package javax.jms;

public class IllegalStateException extends JMSException
{
    public IllegalStateException(final String reason, final String errorCode) {
        super(reason, errorCode);
    }
    
    public IllegalStateException(final String reason) {
        this(reason, (String)null);
    }
}
