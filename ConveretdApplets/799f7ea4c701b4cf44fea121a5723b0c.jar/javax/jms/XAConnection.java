// 
// Decompiled by Procyon v0.5.30
// 

package javax.jms;

public interface XAConnection extends Connection
{
    XASession createXASession() throws JMSException;
    
    Session createSession(final boolean p0, final int p1) throws JMSException;
}
