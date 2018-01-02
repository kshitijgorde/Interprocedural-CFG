// 
// Decompiled by Procyon v0.5.30
// 

package com.cloudpath.loader;

import java.util.Properties;
import java.net.URL;
import java.applet.Applet;

public class XpcUnsignedApplet extends Applet
{
    public static final long serialVersionUID = 3L;
    private Thread cls_WorkerThread;
    private boolean cls_WasRedirected;
    
    public XpcUnsignedApplet() {
        this.cls_WorkerThread = null;
        this.cls_WasRedirected = false;
        this.log(this.getClass().getName() + " constructor called.");
        this.setBounds(0, 0, 1, 1);
        this.log(this.getClass().getName() + " instantiation complete.");
    }
    
    public void init() {
        this.log(this.getClass().getName() + " init.");
        super.init();
        SystemConfigUtil.printSystemProperties(this);
        this.cls_WorkerThread = new Thread(new Runnable() {
            public void run() {
                try {
                    XpcUnsignedApplet.this.log("Will load system config information.");
                    final Properties systemConfig = SystemConfigUtil.getSystemConfig(XpcUnsignedApplet.this.getDocumentBase());
                    XpcUnsignedApplet.this.log("System config captured: " + systemConfig.toString());
                    final URL nextPageForOs = SystemConfigUtil.getNextPageForOs(XpcUnsignedApplet.this.getDocumentBase(), systemConfig);
                    XpcUnsignedApplet.this.log("User will be forwarded to '" + nextPageForOs + "'.");
                    XpcUnsignedApplet.this.getAppletContext().showDocument(nextPageForOs);
                    XpcUnsignedApplet.this.cls_WasRedirected = true;
                    XpcUnsignedApplet.this.log("Applet work is complete.  User was forwarded to " + nextPageForOs + ".");
                }
                catch (Exception ex) {
                    XpcUnsignedApplet.this.log("An exception occurred while loading system config.");
                    ex.printStackTrace();
                    try {
                        XpcUnsignedApplet.this.getAppletContext().showDocument(new URL(XpcUnsignedApplet.this.getDocumentBase(), "page4_download.html"));
                        XpcUnsignedApplet.this.cls_WasRedirected = true;
                    }
                    catch (Exception ex2) {
                        XpcUnsignedApplet.this.log("Attempted to forward user to manual download page, but that also failed.");
                        ex2.printStackTrace();
                        XpcUnsignedApplet.this.cls_WasRedirected = false;
                    }
                }
                XpcUnsignedApplet.this.log("Worker thread is done. User was " + (XpcUnsignedApplet.this.cls_WasRedirected ? "" : "not ") + "redirected.");
            }
        });
        this.log("Will start worker thread...");
        this.cls_WorkerThread.start();
        this.log("Worker thread started.");
        this.log(this.getClass().getName() + " init done.");
    }
    
    public void destroy() {
        this.log(this.getClass().getName() + " is being disposed.");
        super.destroy();
    }
    
    public boolean wasRedirected() {
        this.log("IsRunning called. Will join worker thread...");
        try {
            this.cls_WorkerThread.join();
        }
        catch (Exception ex) {
            this.log("Exception while waiting for worker thread to join.");
            ex.printStackTrace();
        }
        this.log("Worker thread joined.  Was redirected = " + this.cls_WasRedirected);
        return this.cls_WasRedirected;
    }
    
    private final void log(final String s) {
        System.out.println(s);
    }
}
