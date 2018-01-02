// 
// Decompiled by Procyon v0.5.30
// 

package javax.jms;

import javax.transaction.xa.XAResource;

public interface XASession extends Session
{
    Session getSession() throws JMSException;
    
    XAResource getXAResource();
    
    boolean getTransacted() throws JMSException;
    
    void commit() throws JMSException;
    
    void rollback() throws JMSException;
}
