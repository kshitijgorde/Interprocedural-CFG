// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.tool;

import org.apache.activemq.util.IndentPrinter;
import javax.jms.JMSException;
import org.apache.activemq.ActiveMQConnectionFactory;
import javax.jms.Session;
import javax.jms.Connection;
import org.apache.activemq.ActiveMQConnection;
import javax.jms.Destination;

public class ToolSupport
{
    protected Destination destination;
    protected String subject;
    protected boolean topic;
    protected String user;
    protected String pwd;
    protected String url;
    protected boolean transacted;
    protected boolean durable;
    protected String clientID;
    protected int ackMode;
    protected String consumerName;
    
    public ToolSupport() {
        this.subject = "TOOL.DEFAULT";
        this.topic = true;
        this.user = ActiveMQConnection.DEFAULT_USER;
        this.pwd = ActiveMQConnection.DEFAULT_PASSWORD;
        this.url = "failover://tcp://localhost:61616";
        this.clientID = this.getClass().getName();
        this.ackMode = 1;
        this.consumerName = "James";
    }
    
    protected Session createSession(final Connection connection) throws Exception {
        if (this.durable) {
            connection.setClientID(this.clientID);
        }
        final Session session = connection.createSession(this.transacted, this.ackMode);
        if (this.topic) {
            this.destination = session.createTopic(this.subject);
        }
        else {
            this.destination = session.createQueue(this.subject);
        }
        return session;
    }
    
    protected Connection createConnection() throws JMSException, Exception {
        final ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(this.user, this.pwd, this.url);
        return connectionFactory.createConnection();
    }
    
    protected void close(final Connection connection, final Session session) throws JMSException {
        this.dumpStats(connection);
        if (session != null) {
            session.close();
        }
        if (connection != null) {
            connection.close();
        }
    }
    
    protected void dumpStats(final Connection connection) {
        final ActiveMQConnection c = (ActiveMQConnection)connection;
        c.getConnectionStats().dump(new IndentPrinter());
    }
}
