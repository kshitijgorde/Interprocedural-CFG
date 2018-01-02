// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.console.command;

import java.net.URISyntaxException;
import java.util.Iterator;
import javax.jms.JMSException;
import org.apache.activemq.ActiveMQConnectionFactory;
import java.util.ArrayList;
import javax.jms.Connection;
import java.util.List;
import javax.jms.ConnectionFactory;
import java.net.URI;

public abstract class AbstractAmqCommand extends AbstractCommand
{
    private URI brokerUrl;
    private ConnectionFactory factory;
    private final List<Connection> connections;
    
    public AbstractAmqCommand() {
        this.connections = new ArrayList<Connection>();
    }
    
    protected Connection createConnection() throws JMSException {
        if (this.getBrokerUrl() == null) {
            this.context.printException(new IllegalStateException("You must specify a broker URL to connect to using the --amqurl option."));
            return null;
        }
        if (this.factory == null) {
            this.factory = new ActiveMQConnectionFactory(this.getBrokerUrl());
        }
        final Connection conn = this.factory.createConnection();
        this.connections.add(conn);
        return conn;
    }
    
    protected Connection createConnection(final String username, final String password) throws JMSException {
        if (this.getBrokerUrl() == null) {
            this.context.printException(new IllegalStateException("You must specify a broker URL to connect to using the --amqurl option."));
            return null;
        }
        if (this.factory == null) {
            this.factory = new ActiveMQConnectionFactory(this.getBrokerUrl());
        }
        final Connection conn = this.factory.createConnection(username, password);
        this.connections.add(conn);
        conn.start();
        return conn;
    }
    
    protected void closeAllConnections() {
        final Iterator<Connection> i = this.connections.iterator();
        while (i.hasNext()) {
            try {
                i.next().close();
            }
            catch (Exception e) {}
        }
        this.connections.clear();
    }
    
    @Override
    protected void handleOption(final String token, final List tokens) throws Exception {
        if (token.equals("--amqurl")) {
            if (tokens.isEmpty() || tokens.get(0).startsWith("-")) {
                this.context.printException(new IllegalArgumentException("Broker URL not specified."));
                tokens.clear();
                return;
            }
            if (this.getBrokerUrl() != null) {
                this.context.printException(new IllegalArgumentException("Multiple broker URL cannot be specified."));
                tokens.clear();
                return;
            }
            final String strBrokerUrl = tokens.remove(0);
            try {
                this.setBrokerUrl(new URI(strBrokerUrl));
            }
            catch (URISyntaxException e) {
                this.context.printException(e);
                tokens.clear();
            }
        }
        else {
            super.handleOption(token, tokens);
        }
    }
    
    protected void setBrokerUrl(final URI brokerUrl) {
        this.brokerUrl = brokerUrl;
    }
    
    protected void setBrokerUrl(final String address) throws URISyntaxException {
        this.brokerUrl = new URI(address);
    }
    
    protected URI getBrokerUrl() {
        return this.brokerUrl;
    }
}
