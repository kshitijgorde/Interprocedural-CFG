// 
// Decompiled by Procyon v0.5.30
// 

package de.mud.jta;

import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.applet.AppletStub;
import java.applet.Applet;

public class MacWrapper extends Applet implements AppletStub
{
    String appletName;
    Applet applet;
    
    public MacWrapper() {
        this.appletName = null;
        this.applet = null;
    }
    
    public void init() {
        final String parameter = this.getParameter("applet");
        this.appletName = parameter;
        if (parameter == null) {
            this.showStatus("MacWrapper: missing applet parameter, nothing loaded");
            System.err.println("MacWrapper: missing applet parameter");
            return;
        }
        this.setLayout(new BorderLayout());
    }
    
    public void start() {
        if (this.applet == null) {
            try {
                System.err.println("MacWrapper: loading actual applet: " + this.appletName);
                (this.applet = (Applet)Class.forName(this.getParameter("applet")).newInstance()).setStub(this);
            }
            catch (Exception e) {
                System.err.println("MacWrapper: could not load " + this.appletName);
                e.printStackTrace();
                return;
            }
            this.add("Center", this.applet);
            this.applet.init();
            this.applet.start();
            return;
        }
        System.err.println("MacWrapper: applet already loaded");
    }
    
    public void stop() {
        if (this.applet != null) {
            this.applet.stop();
        }
    }
    
    public void appletResize(final int width, final int height) {
        System.err.println("MacWrapper: appletResize()");
        if (this.applet != null) {
            this.applet.resize(width, height);
        }
    }
    
    public String getAppletInfo() {
        String info = "MacWrapper: $Id$\n";
        if (this.applet != null) {
            info += this.applet.getAppletInfo();
        }
        return info;
    }
    
    public String[][] getParameterInfo() {
        final String[][] wrapper = { { "applet", "String", "MacWrapper: Applet to load" } };
        String[][] info;
        if (this.applet != null) {
            final String[][] tmp = this.applet.getParameterInfo();
            info = new String[tmp.length + 1][3];
            System.arraycopy(tmp, 0, info, 1, tmp.length);
        }
        else {
            info = new String[1][3];
        }
        System.arraycopy(wrapper, 0, info, 0, 1);
        return info;
    }
    
    public void reshape(final int x, final int y, final int w, final int h) {
        if (this.applet != null) {
            this.applet.reshape(x, y, w, h);
        }
        super.reshape(x, y, w, h);
    }
}
