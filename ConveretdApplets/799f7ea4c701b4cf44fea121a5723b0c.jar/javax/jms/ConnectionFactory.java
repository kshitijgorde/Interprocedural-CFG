// 
// Decompiled by Procyon v0.5.30
// 

package javax.jms;

public interface ConnectionFactory
{
    Connection createConnection() throws JMSException;
    
    Connection createConnection(final String p0, final String p1) throws JMSException;
}
