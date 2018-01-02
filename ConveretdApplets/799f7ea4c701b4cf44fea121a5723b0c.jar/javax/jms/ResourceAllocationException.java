// 
// Decompiled by Procyon v0.5.30
// 

package javax.jms;

public class ResourceAllocationException extends JMSException
{
    public ResourceAllocationException(final String reason, final String errorCode) {
        super(reason, errorCode);
    }
    
    public ResourceAllocationException(final String reason) {
        this(reason, (String)null);
    }
}
