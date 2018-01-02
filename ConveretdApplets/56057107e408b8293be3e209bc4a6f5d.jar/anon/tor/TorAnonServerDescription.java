// 
// Decompiled by Procyon v0.5.30
// 

package anon.tor;

import anon.AnonServerDescription;

public class TorAnonServerDescription implements AnonServerDescription
{
    private int m_iTorDirServerPort;
    private String m_strTorDirServerAddr;
    private final boolean m_bUseInfoService;
    private final boolean m_bStartCircuitsAtStartup;
    private int m_iMaxRouteLen;
    private int m_iMinRouteLen;
    private int m_iMaxConnectionsPerRoute;
    
    public TorAnonServerDescription() {
        this.m_iMaxRouteLen = 5;
        this.m_iMinRouteLen = 2;
        this.m_iMaxConnectionsPerRoute = 1000;
        this.m_strTorDirServerAddr = "moria.seul.org";
        this.m_iTorDirServerPort = 9031;
        this.m_bUseInfoService = false;
        this.m_bStartCircuitsAtStartup = false;
    }
    
    public TorAnonServerDescription(final boolean b) {
        this(b, false);
    }
    
    public TorAnonServerDescription(final boolean b, final boolean bStartCircuitsAtStartup) {
        this.m_iMaxRouteLen = 5;
        this.m_iMinRouteLen = 2;
        this.m_iMaxConnectionsPerRoute = 1000;
        if (b) {
            this.m_strTorDirServerAddr = null;
            this.m_iTorDirServerPort = -1;
            this.m_bUseInfoService = true;
        }
        else {
            this.m_strTorDirServerAddr = "moria.seul.org";
            this.m_iTorDirServerPort = 9031;
            this.m_bUseInfoService = false;
        }
        this.m_bStartCircuitsAtStartup = bStartCircuitsAtStartup;
    }
    
    public TorAnonServerDescription(final String strTorDirServerAddr, final int iTorDirServerPort, final boolean bStartCircuitsAtStartup) {
        this.m_iMaxRouteLen = 5;
        this.m_iMinRouteLen = 2;
        this.m_iMaxConnectionsPerRoute = 1000;
        this.m_strTorDirServerAddr = strTorDirServerAddr;
        this.m_iTorDirServerPort = iTorDirServerPort;
        this.m_bUseInfoService = false;
        this.m_bStartCircuitsAtStartup = bStartCircuitsAtStartup;
    }
    
    public void setTorDirServer(final String strTorDirServerAddr, final int iTorDirServerPort) {
        this.m_strTorDirServerAddr = strTorDirServerAddr;
        this.m_iTorDirServerPort = iTorDirServerPort;
    }
    
    public String getTorDirServerAddr() {
        return this.m_strTorDirServerAddr;
    }
    
    public int getTorDirServerPort() {
        return this.m_iTorDirServerPort;
    }
    
    public boolean useInfoService() {
        return this.m_bUseInfoService;
    }
    
    public boolean startCircuitsAtStartup() {
        return this.m_bStartCircuitsAtStartup;
    }
    
    public void setMaxRouteLen(final int iMaxRouteLen) {
        this.m_iMaxRouteLen = iMaxRouteLen;
    }
    
    public int getMaxRouteLen() {
        return this.m_iMaxRouteLen;
    }
    
    public void setMinRouteLen(final int iMinRouteLen) {
        this.m_iMinRouteLen = iMinRouteLen;
    }
    
    public int getMinRouteLen() {
        return this.m_iMinRouteLen;
    }
    
    public void setMaxConnectionsPerRoute(final int iMaxConnectionsPerRoute) {
        this.m_iMaxConnectionsPerRoute = iMaxConnectionsPerRoute;
    }
    
    public int getMaxConnectionsPerRoute() {
        return this.m_iMaxConnectionsPerRoute;
    }
}
