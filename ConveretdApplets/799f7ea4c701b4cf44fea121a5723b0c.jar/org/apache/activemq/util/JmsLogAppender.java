// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.util;

import java.net.URISyntaxException;
import javax.jms.JMSException;
import org.apache.activemq.ActiveMQConnection;
import javax.jms.Connection;

public class JmsLogAppender extends JmsLogAppenderSupport
{
    private String uri;
    private String userName;
    private String password;
    
    public JmsLogAppender() {
        this.uri = "tcp://localhost:61616";
    }
    
    public String getUri() {
        return this.uri;
    }
    
    public void setUri(final String uri) {
        this.uri = uri;
    }
    
    public String getUserName() {
        return this.userName;
    }
    
    public void setUserName(final String userName) {
        this.userName = userName;
    }
    
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(final String password) {
        this.password = password;
    }
    
    @Override
    protected Connection createConnection() throws JMSException {
        if (this.userName != null) {
            try {
                return ActiveMQConnection.makeConnection(this.userName, this.password, this.uri);
            }
            catch (URISyntaxException e) {
                throw new JMSException("Unable to connect to a broker using userName: '" + this.userName + "' password '" + this.password + "' uri '" + this.uri + "' :: error - " + e.getMessage());
            }
        }
        try {
            return ActiveMQConnection.makeConnection(this.uri);
        }
        catch (URISyntaxException e) {
            throw new JMSException("Unable to connect to a broker using uri '" + this.uri + "' :: error - " + e.getMessage());
        }
    }
}
