// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.util;

import java.util.Hashtable;
import javax.naming.NamingException;
import javax.naming.InitialContext;
import javax.jms.JMSException;
import javax.jms.ConnectionFactory;
import org.apache.log4j.helpers.LogLog;
import javax.jms.Connection;

public class JndiJmsLogAppender extends JmsLogAppenderSupport
{
    private String jndiName;
    private String userName;
    private String password;
    private String initialContextFactoryName;
    private String providerURL;
    private String urlPkgPrefixes;
    private String securityPrincipalName;
    private String securityCredentials;
    
    public String getJndiName() {
        return this.jndiName;
    }
    
    public void setJndiName(final String jndiName) {
        this.jndiName = jndiName;
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
    
    public String getInitialContextFactoryName() {
        return this.initialContextFactoryName;
    }
    
    public void setInitialContextFactoryName(final String initialContextFactoryName) {
        this.initialContextFactoryName = initialContextFactoryName;
    }
    
    public String getProviderURL() {
        return this.providerURL;
    }
    
    public void setProviderURL(final String providerURL) {
        this.providerURL = providerURL;
    }
    
    public String getUrlPkgPrefixes() {
        return this.urlPkgPrefixes;
    }
    
    public void setUrlPkgPrefixes(final String urlPkgPrefixes) {
        this.urlPkgPrefixes = urlPkgPrefixes;
    }
    
    public String getSecurityPrincipalName() {
        return this.securityPrincipalName;
    }
    
    public void setSecurityPrincipalName(final String securityPrincipalName) {
        this.securityPrincipalName = securityPrincipalName;
    }
    
    public String getSecurityCredentials() {
        return this.securityCredentials;
    }
    
    public void setSecurityCredentials(final String securityCredentials) {
        this.securityCredentials = securityCredentials;
    }
    
    @Override
    protected Connection createConnection() throws JMSException, NamingException {
        final InitialContext context = this.createInitialContext();
        LogLog.debug("Looking up ConnectionFactory with jndiName: " + this.jndiName);
        final ConnectionFactory factory = (ConnectionFactory)context.lookup(this.jndiName);
        if (factory == null) {
            throw new JMSException("No such ConnectionFactory for name: " + this.jndiName);
        }
        if (this.userName != null) {
            return factory.createConnection(this.userName, this.password);
        }
        return factory.createConnection();
    }
    
    protected InitialContext createInitialContext() throws NamingException {
        if (this.initialContextFactoryName == null) {
            return new InitialContext();
        }
        final Hashtable<String, String> env = new Hashtable<String, String>();
        env.put("java.naming.factory.initial", this.initialContextFactoryName);
        if (this.providerURL != null) {
            env.put("java.naming.provider.url", this.providerURL);
        }
        else {
            LogLog.warn("You have set InitialContextFactoryName option but not the ProviderURL. This is likely to cause problems.");
        }
        if (this.urlPkgPrefixes != null) {
            env.put("java.naming.factory.url.pkgs", this.urlPkgPrefixes);
        }
        if (this.securityPrincipalName != null) {
            env.put("java.naming.security.principal", this.securityPrincipalName);
            if (this.securityCredentials != null) {
                env.put("java.naming.security.credentials", this.securityCredentials);
            }
            else {
                LogLog.warn("You have set SecurityPrincipalName option but not the SecurityCredentials. This is likely to cause problems.");
            }
        }
        LogLog.debug("Looking up JNDI context with environment: " + env);
        return new InitialContext(env);
    }
}
