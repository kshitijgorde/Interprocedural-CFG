// 
// Decompiled by Procyon v0.5.30
// 

package javax.jms;

public class InvalidSelectorException extends JMSException
{
    public InvalidSelectorException(final String reason, final String errorCode) {
        super(reason, errorCode);
    }
    
    public InvalidSelectorException(final String reason) {
        this(reason, (String)null);
    }
}
