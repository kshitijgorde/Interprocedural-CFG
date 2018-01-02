// 
// Decompiled by Procyon v0.5.30
// 

package com.screencastomatic.detect;

import netscape.javascript.JSObject;
import java.applet.Applet;

public class DetectApplet extends Applet
{
    boolean m_startCalled;
    
    public DetectApplet() {
        this.m_startCalled = false;
    }
    
    public void init() {
        System.out.println("--- Init applet: " + this);
    }
    
    public void start() {
        if (this.m_startCalled) {
            System.out.println("--- IGNORING start since we already started: " + this);
            return;
        }
        this.m_startCalled = true;
        System.out.println("--- Start applet: " + this);
        final String callBack = this.getParameter("doSetup");
        if (callBack != null) {
            new Thread(new Runnable() {
                public void run() {
                    try {
                        final JSObject window = JSObject.getWindow((Applet)DetectApplet.this);
                        if (window == null) {
                            System.out.println("ERROR:Failed to get javascript window.");
                            return;
                        }
                        window.call(callBack, new Object[] { String.valueOf(DetectApplet.this.isJavaVersionOk()) });
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).run();
        }
    }
    
    public String getJavaVersion() {
        return System.getProperty("java.version");
    }
    
    public String getJavaVendor() {
        return System.getProperty("java.vendor");
    }
    
    public boolean isJavaVersionOk() {
        return this.getJavaVersionNum() >= 5;
    }
    
    private int getJavaVersionNum() {
        return Integer.valueOf(this.getJavaVersion().substring(2, 3));
    }
}
