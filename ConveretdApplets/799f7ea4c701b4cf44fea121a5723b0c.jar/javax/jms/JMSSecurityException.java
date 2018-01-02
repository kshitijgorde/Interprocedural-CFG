// 
// Decompiled by Procyon v0.5.30
// 

package javax.jms;

public class JMSSecurityException extends JMSException
{
    public JMSSecurityException(final String reason, final String errorCode) {
        super(reason, errorCode);
    }
    
    public JMSSecurityException(final String reason) {
        this(reason, (String)null);
    }
}
