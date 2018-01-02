// 
// Decompiled by Procyon v0.5.30
// 

package javax.jms;

public interface Topic extends Destination
{
    String getTopicName() throws JMSException;
    
    String toString();
}
