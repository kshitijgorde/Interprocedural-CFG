// 
// Decompiled by Procyon v0.5.30
// 

package javax.jms;

public interface Queue extends Destination
{
    String getQueueName() throws JMSException;
    
    String toString();
}
