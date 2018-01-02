// 
// Decompiled by Procyon v0.5.30
// 

package soht.client.java.configuration;

import java.util.Hashtable;
import java.util.Enumeration;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.net.HttpURLConnection;
import java.util.Vector;
import java.util.Properties;

public class ConfigurationManager
{
    public static final int MODE_STATEFUL = 0;
    public static final int MODE_STATELESS = 1;
    private Properties properties;
    private String propertiesFile;
    private String serverURL;
    private boolean serverLoginRequired;
    private String serverUsername;
    private String serverPassword;
    private boolean useStatelessConnection;
    private boolean useHTTPProxy;
    private String proxyHost;
    private String proxyPort;
    private Vector hosts;
    
    public ConfigurationManager(final String propertiesFile) throws ConfigurationException {
        this.propertiesFile = propertiesFile;
        this.hosts = new Vector();
        this.loadProperties();
    }
    
    public ConfigurationManager() throws ConfigurationException {
        System.out.println("Inisde configurationmanager constructor");
        this.hosts = new Vector();
    }
    
    public String getServerURL() {
        return this.serverURL;
    }
    
    public void setServerURL(final String serverURL) {
        this.serverURL = serverURL;
    }
    
    public boolean isServerLoginRequired() {
        return this.serverLoginRequired;
    }
    
    public void setServerLoginRequired(final boolean serverLoginRequired) {
        this.serverLoginRequired = serverLoginRequired;
    }
    
    public String getServerUsername() {
        return this.serverUsername;
    }
    
    public void setServerUsername(final String serverUsername) {
        this.serverUsername = serverUsername;
    }
    
    public String getServerPassword() {
        return this.serverPassword;
    }
    
    public void setServerPassword(final String serverPassword) {
        this.serverPassword = serverPassword;
    }
    
    public boolean isUseStatelessConnection() {
        return this.useStatelessConnection;
    }
    
    public void setUseStatelessConnection(final boolean useStatelessConnection) {
        this.useStatelessConnection = useStatelessConnection;
    }
    
    public boolean isUseHTTPProxy() {
        return this.useHTTPProxy;
    }
    
    public void setUseHTTPProxy(final boolean useHTTPProxy) {
        this.useHTTPProxy = useHTTPProxy;
    }
    
    public String getProxyHost() {
        return this.proxyHost;
    }
    
    public void setProxyHost(final String proxyHost) {
        this.proxyHost = proxyHost;
    }
    
    public String getProxyPort() {
        return this.proxyPort;
    }
    
    public void setProxyPort(final String proxyPort) {
        this.proxyPort = proxyPort;
    }
    
    public Vector getHosts() {
        return this.hosts;
    }
    
    public void addHost(final Host h) {
        this.hosts.addElement(new Host(h.getLocalPort(), h.getRemoteHost(), h.getRemotePort()));
    }
    
    public void setHosts(final Vector hosts) {
        this.hosts = hosts;
    }
    
    public HttpURLConnection getURLConnection() throws IOException {
        final URL url = new URL(this.getServerURL());
        final HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
        urlConnection.setRequestMethod("POST");
        urlConnection.setDoOutput(true);
        if (this.useHTTPProxy) {
            ((Hashtable<String, String>)System.getProperties()).put("proxySet", "true");
            ((Hashtable<String, String>)System.getProperties()).put("proxyHost", this.proxyHost);
            ((Hashtable<String, String>)System.getProperties()).put("proxyPort", String.valueOf(this.proxyPort));
        }
        return urlConnection;
    }
    
    private void loadProperties() throws ConfigurationException {
        this.properties = new Properties();
        try {
            this.properties.load(new FileInputStream(this.propertiesFile));
        }
        catch (Throwable throwable) {
            throw new ConfigurationException("Unable to load configuration file: " + this.propertiesFile + " - " + throwable.toString());
        }
        this.serverURL = this.getRequiredProperty("server.url");
        final String serverLoginRequiredString = this.properties.getProperty("server.loginrequired", "false");
        this.serverLoginRequired = Boolean.valueOf(serverLoginRequiredString);
        if (this.serverLoginRequired) {
            this.serverUsername = this.getRequiredProperty("server.username");
            this.serverPassword = this.getRequiredProperty("server.password");
        }
        final String connectionMode = this.properties.getProperty("server.stateless", "false");
        this.useStatelessConnection = Boolean.valueOf(connectionMode);
        final String useProxyString = this.properties.getProperty("proxy.useproxy", "false");
        this.useHTTPProxy = Boolean.valueOf(useProxyString);
        if (this.useHTTPProxy) {
            this.proxyHost = this.getRequiredProperty("proxy.host");
            this.proxyPort = this.getRequiredProperty("proxy.port");
        }
        final Enumeration propertyKeys = this.properties.keys();
        while (propertyKeys.hasMoreElements()) {
            final String keyName = propertyKeys.nextElement();
            if (keyName.startsWith("port.")) {
                final String localPort = keyName.substring(5);
                final String keyValue = this.properties.getProperty(keyName);
                final int delimiterIndex = keyValue.indexOf(":");
                if (delimiterIndex == -1) {
                    throw new ConfigurationException("Mapping for local port: " + localPort + " invalid.  Please specify value as <host>:<port>.");
                }
                final String remoteHost = keyValue.substring(0, delimiterIndex);
                final String remotePort = keyValue.substring(delimiterIndex + 1);
                this.hosts.addElement(new Host(localPort, remoteHost, remotePort));
            }
        }
    }
    
    private String getRequiredProperty(final String propertyName) throws ConfigurationException {
        final String property = this.properties.getProperty(propertyName);
        if (property == null) {
            throw new ConfigurationException("Missing required property: " + propertyName);
        }
        return property;
    }
}
