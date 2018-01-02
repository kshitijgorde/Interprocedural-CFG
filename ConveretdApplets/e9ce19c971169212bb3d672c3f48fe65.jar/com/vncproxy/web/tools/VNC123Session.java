// 
// Decompiled by Procyon v0.5.30
// 

package com.vncproxy.web.tools;

import java.io.Serializable;

public class VNC123Session implements Serializable
{
    private static final long serialVersionUID = 12345678L;
    public int sid;
    public byte[] key;
    public long startDate;
    public long lastMove;
    public long dataSize;
    public long nbRequests;
    public boolean active;
    public String userId;
    public String serverHostname;
    public String serverIPLan;
    public String serverIPWan;
    public String serverOSArch;
    public String serverOSName;
    public String serverOSVersion;
    public String serverJavaVersion;
    public String serverJavaVendor;
    public String serverUserName;
    public String serverBrowser;
    public String serverProxy;
    public String viewerHostname;
    public String viewerIPLan;
    public String viewerIPWan;
    public String viewerOSArch;
    public String viewerOSName;
    public String viewerOSVersion;
    public String viewerJavaVersion;
    public String viewerJavaVendor;
    public String viewerUserName;
    public String viewerBrowser;
    public String viewerProxy;
    
    public VNC123Session(final int sid) {
        this.sid = sid;
        this.key = new byte[16];
        this.startDate = System.currentTimeMillis();
        this.lastMove = this.startDate;
        this.dataSize = 0L;
        this.nbRequests = 0L;
        this.active = false;
        this.userId = null;
    }
}
