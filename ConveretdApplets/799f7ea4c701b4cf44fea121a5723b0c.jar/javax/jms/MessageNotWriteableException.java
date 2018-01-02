// 
// Decompiled by Procyon v0.5.30
// 

package javax.jms;

public class MessageNotWriteableException extends JMSException
{
    public MessageNotWriteableException(final String reason, final String errorCode) {
        super(reason, errorCode);
    }
    
    public MessageNotWriteableException(final String reason) {
        this(reason, (String)null);
    }
}
