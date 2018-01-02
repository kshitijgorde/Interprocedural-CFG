// 
// Decompiled by Procyon v0.5.30
// 

package javax.jms;

public interface TextMessage extends Message
{
    void setText(final String p0) throws JMSException;
    
    String getText() throws JMSException;
}
