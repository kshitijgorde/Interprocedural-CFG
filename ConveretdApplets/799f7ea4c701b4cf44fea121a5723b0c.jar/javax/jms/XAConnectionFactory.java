// 
// Decompiled by Procyon v0.5.30
// 

package javax.jms;

public interface XAConnectionFactory
{
    XAConnection createXAConnection() throws JMSException;
    
    XAConnection createXAConnection(final String p0, final String p1) throws JMSException;
}
