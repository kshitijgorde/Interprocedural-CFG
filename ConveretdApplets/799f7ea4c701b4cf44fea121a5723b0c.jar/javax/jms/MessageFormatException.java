// 
// Decompiled by Procyon v0.5.30
// 

package javax.jms;

public class MessageFormatException extends JMSException
{
    public MessageFormatException(final String reason, final String errorCode) {
        super(reason, errorCode);
    }
    
    public MessageFormatException(final String reason) {
        this(reason, (String)null);
    }
}
