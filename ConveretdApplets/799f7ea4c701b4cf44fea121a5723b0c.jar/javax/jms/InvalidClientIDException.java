// 
// Decompiled by Procyon v0.5.30
// 

package javax.jms;

public class InvalidClientIDException extends JMSException
{
    public InvalidClientIDException(final String reason, final String errorCode) {
        super(reason, errorCode);
    }
    
    public InvalidClientIDException(final String reason) {
        this(reason, (String)null);
    }
}
