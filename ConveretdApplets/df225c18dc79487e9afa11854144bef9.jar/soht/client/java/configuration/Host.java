// 
// Decompiled by Procyon v0.5.30
// 

package soht.client.java.configuration;

public class Host
{
    private int localPort;
    private String remoteHost;
    private int remotePort;
    private int boundLocalPort;
    
    public Host(final String localPort, final String remoteHost, final String remotePort) throws ConfigurationException {
        this.boundLocalPort = 0;
        try {
            this.localPort = Integer.parseInt(localPort);
        }
        catch (NumberFormatException e) {
            throw new ConfigurationException("Invalid local port.  Port must be a number!");
        }
        this.remoteHost = remoteHost;
        try {
            this.remotePort = Integer.parseInt(remotePort);
        }
        catch (NumberFormatException e) {
            throw new ConfigurationException("Invalid remote port.  Port must be a number!");
        }
    }
    
    public Host(final int localPort, final String remoteHost, final int remotePort) {
        this.boundLocalPort = 0;
        this.localPort = localPort;
        this.remoteHost = remoteHost;
        this.remotePort = remotePort;
    }
    
    public int getLocalPort() {
        return this.localPort;
    }
    
    public void setLocalPort(final int localPort) {
        this.localPort = localPort;
    }
    
    public String getRemoteHost() {
        return this.remoteHost;
    }
    
    public void setRemoteHost(final String remoteHost) {
        this.remoteHost = remoteHost;
    }
    
    public int getRemotePort() {
        return this.remotePort;
    }
    
    public void setRemotePort(final int remotePort) {
        this.remotePort = remotePort;
    }
    
    public int getBoundLocalPort() {
        return this.boundLocalPort;
    }
    
    public void setBoundLocalPort(final int boundLocalPort) {
        this.boundLocalPort = boundLocalPort;
    }
}
