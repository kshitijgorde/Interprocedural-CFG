// 
// Decompiled by Procyon v0.5.30
// 

package javax.jms;

public interface ConnectionConsumer
{
    ServerSessionPool getServerSessionPool() throws JMSException;
    
    void close() throws JMSException;
}
