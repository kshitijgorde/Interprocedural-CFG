// 
// Decompiled by Procyon v0.5.30
// 

package javax.jms;

public class InvalidDestinationException extends JMSException
{
    public InvalidDestinationException(final String reason, final String errorCode) {
        super(reason, errorCode);
    }
    
    public InvalidDestinationException(final String reason) {
        this(reason, (String)null);
    }
}
