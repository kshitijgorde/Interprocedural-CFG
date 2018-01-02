// 
// Decompiled by Procyon v0.5.30
// 

package javax.jms;

public class MessageNotReadableException extends JMSException
{
    public MessageNotReadableException(final String reason, final String errorCode) {
        super(reason, errorCode);
    }
    
    public MessageNotReadableException(final String reason) {
        this(reason, (String)null);
    }
}
