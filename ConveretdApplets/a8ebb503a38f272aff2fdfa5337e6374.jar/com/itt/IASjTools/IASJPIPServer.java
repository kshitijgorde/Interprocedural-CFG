// 
// Decompiled by Procyon v0.5.30
// 

package com.itt.IASjTools;

public class IASJPIPServer
{
    private String serverName;
    private String cacheDirectory;
    private String proxyName;
    public static String JPIP_CHANNEL_TYPE;
    private int serverPort;
    private int proxyPort;
    private String basicAuthentication;
    
    public IASJPIPServer() {
        this.serverName = null;
        this.cacheDirectory = null;
        this.proxyName = null;
        this.serverPort = 80;
        this.proxyPort = 8088;
        this.basicAuthentication = null;
    }
    
    public void setServerName(final String serverName) {
        this.serverName = serverName;
    }
    
    public String getServerName() {
        return this.serverName;
    }
    
    public void setServerPort(final int serverPort) {
        this.serverPort = serverPort;
    }
    
    public int getServerPort() {
        return this.serverPort;
    }
    
    public void setCacheDirectory(final String cacheDirectory) {
        this.cacheDirectory = cacheDirectory;
    }
    
    public String getCacheDirectory() {
        return this.cacheDirectory;
    }
    
    public String getJpipChannelType() {
        return IASJPIPServer.JPIP_CHANNEL_TYPE;
    }
    
    public void setBasicAuthentication(final String basicAuthentication) {
        this.basicAuthentication = basicAuthentication;
    }
    
    public String getBasicAuthentication() {
        return this.basicAuthentication;
    }
    
    public void setJpipChannelType(String s) throws Exception {
        final String lowerCase = s.toLowerCase();
        if (lowerCase != "http" && lowerCase != "http-tcp" && lowerCase != "https" && lowerCase != "none") {
            throw new Exception("IAS_JPIPServer::SetJPIPChannelType() - incorrect type: [" + s + "]\n" + "Valid values are: 'http' or 'http-tcp' or 'none'");
        }
        s = (IASJPIPServer.JPIP_CHANNEL_TYPE = lowerCase);
    }
    
    public String getProxyName() {
        return this.proxyName;
    }
    
    public void setProxyName(final String proxyName) {
        this.proxyName = proxyName;
    }
    
    public int getProxyPort() {
        return this.proxyPort;
    }
    
    public void setProxyPort(final int proxyPort) {
        this.proxyPort = proxyPort;
    }
    
    static {
        IASJPIPServer.JPIP_CHANNEL_TYPE = "http";
    }
}
