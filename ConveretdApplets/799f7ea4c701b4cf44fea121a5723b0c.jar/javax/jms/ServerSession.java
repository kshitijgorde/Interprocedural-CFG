// 
// Decompiled by Procyon v0.5.30
// 

package javax.jms;

public interface ServerSession
{
    Session getSession() throws JMSException;
    
    void start() throws JMSException;
}
