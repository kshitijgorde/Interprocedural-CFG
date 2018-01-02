// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.tool;

import javax.jms.Queue;
import javax.jms.ConnectionFactory;
import javax.naming.InitialContext;
import javax.jms.Connection;

public class JndiProducerTool extends ProducerTool
{
    public static void main(final String[] args) {
        ProducerTool.runTool(args, new JndiProducerTool());
    }
    
    @Override
    protected Connection createConnection() throws Exception {
        final InitialContext jndiContext = new InitialContext();
        final ConnectionFactory queueConnectionFactory = (ConnectionFactory)jndiContext.lookup("ConnectionFactory");
        final Connection connection = queueConnectionFactory.createConnection();
        this.destination = (Queue)jndiContext.lookup(this.subject);
        return connection;
    }
}
