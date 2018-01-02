// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq;

import javax.jms.Session;
import javax.jms.XAQueueSession;
import javax.jms.XATopicSession;
import javax.jms.JMSException;
import javax.jms.XASession;
import org.apache.activemq.management.JMSStatsImpl;
import org.apache.activemq.util.IdGenerator;
import org.apache.activemq.transport.Transport;
import javax.jms.XAConnection;
import javax.jms.XAQueueConnection;
import javax.jms.XATopicConnection;

public class ActiveMQXAConnection extends ActiveMQConnection implements XATopicConnection, XAQueueConnection, XAConnection
{
    protected ActiveMQXAConnection(final Transport transport, final IdGenerator clientIdGenerator, final JMSStatsImpl factoryStats) throws Exception {
        super(transport, clientIdGenerator, factoryStats);
    }
    
    @Override
    public XASession createXASession() throws JMSException {
        return (XASession)this.createSession(true, 0);
    }
    
    @Override
    public XATopicSession createXATopicSession() throws JMSException {
        return (XATopicSession)this.createSession(true, 0);
    }
    
    @Override
    public XAQueueSession createXAQueueSession() throws JMSException {
        return (XAQueueSession)this.createSession(true, 0);
    }
    
    @Override
    public Session createSession(final boolean transacted, final int acknowledgeMode) throws JMSException {
        this.checkClosedOrFailed();
        this.ensureConnectionInfoSent();
        return new ActiveMQXASession(this, this.getNextSessionId(), 0, this.isDispatchAsync());
    }
}
