// 
// Decompiled by Procyon v0.5.30
// 

package com.pluraprocessing.node.applet;

import java.util.UUID;
import netscape.javascript.JSObject;
import com.pluraprocessing.node.PluraRuntime;
import java.applet.Applet;

public class JAVA extends Applet implements Runnable
{
    private static final long serialVersionUID = 1L;
    private String versionId;
    private double percentageCPU;
    private String affiliateId;
    private String clientId;
    private String pluraServerName;
    private double bandwidthPercent;
    private Thread appletThread;
    private boolean isRunning;
    private String isRestricted;
    
    public JAVA() {
        this.percentageCPU = 0.5;
        this.bandwidthPercent = 0.5;
        this.isRunning = false;
        this.isRestricted = "1";
    }
    
    @Override
    public void start() {
        try {
            if (this.appletThread == null) {
                this.appletThread = new Thread(this);
            }
            this.appletThread.start();
        }
        catch (RuntimeException e1) {
            this.appletThread = null;
        }
    }
    
    @Override
    public void stop() {
        this.stopRunning();
        super.stop();
    }
    
    @Override
    public void destroy() {
        this.stopRunning();
        super.destroy();
    }
    
    private void stopRunning() {
        this.appletThread = null;
        if (this.isRunning) {
            PluraRuntime.getInstance().stopPluraThreads(false);
        }
    }
    
    @Override
    public void run() {
        try {
            final JSObject window = JSObject.getWindow((Applet)this);
            final String[] args = { "crawlable", "yes", "365" };
            window.call("setCookie", (Object[])args);
        }
        catch (Throwable t) {}
        try {
            final String cpu = this.getParameter("cpu");
            if (cpu != null && !cpu.equals("")) {
                this.percentageCPU = Float.valueOf(cpu);
            }
            final String bandwidth = this.getParameter("bw");
            if (bandwidth != null && !bandwidth.equals("")) {
                this.bandwidthPercent = Float.valueOf(bandwidth);
            }
            this.versionId = this.insertDashesIntoVersionIdString(this.getParameter("version"));
            this.affiliateId = this.getParameter("affiliate");
            this.pluraServerName = this.getParameter("svr");
            this.isRestricted = this.getParameter("restrictedAPI");
            final String client = this.getParameter("client");
            if (client == null || client.equals("")) {
                this.clientId = String.valueOf(UUID.randomUUID().toString()) + "-pl";
            }
            else {
                this.clientId = client;
            }
            synchronized (PluraRuntime.getInstance()) {
                if (PluraRuntime.getInstance().isStopped()) {
                    this.isRunning = true;
                    PluraRuntime.getInstance().initialize(this, this.versionId, this.affiliateId, this.clientId, this.pluraServerName, this.percentageCPU, this.bandwidthPercent, false, 1, (this.isRestricted == "1" || this.isRestricted == "true") ? Boolean.TRUE : Boolean.FALSE);
                    PluraRuntime.getInstance().startPluraThreads();
                }
            }
            // monitorexit(PluraRuntime.getInstance())
        }
        catch (RuntimeException e1) {
            this.appletThread = null;
            PluraRuntime.getInstance().stopPluraThreads(false);
        }
        catch (Exception e2) {
            this.appletThread = null;
            PluraRuntime.getInstance().stopPluraThreads(false);
        }
    }
    
    public String insertDashesIntoVersionIdString(final String versionId) {
        final StringBuilder builder = new StringBuilder(versionId.substring(0, 8));
        builder.append("-").append(versionId.substring(8, 12)).append("-").append(versionId.substring(12, 16)).append("-").append(versionId.substring(16, 20)).append("-").append(versionId.substring(20, 32));
        return builder.toString();
    }
}
