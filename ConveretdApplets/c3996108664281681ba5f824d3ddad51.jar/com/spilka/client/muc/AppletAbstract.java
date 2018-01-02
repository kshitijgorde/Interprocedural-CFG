// 
// Decompiled by Procyon v0.5.30
// 

package com.spilka.client.muc;

import java.net.URL;
import a.cl;
import java.awt.Component;
import a.e;
import java.awt.MediaTracker;
import a.bI;
import a.bt;
import java.awt.Image;
import java.applet.Applet;

public abstract class AppletAbstract extends Applet
{
    protected Image[] q;
    public Image[] w;
    public String q;
    public int q;
    public int w;
    public int e;
    public static AppletAbstract q;
    public bt q;
    public boolean q;
    public int r;
    public String w;
    public bI q;
    public String e;
    private MediaTracker q;
    
    public AppletAbstract() {
        this.q = new Image[6];
        this.w = new Image[6];
        this.q = 0;
        this.w = 0;
        this.e = -1;
        this.q = null;
        this.q = false;
        this.r = 0;
        this.w = null;
        this.q = new MediaTracker(new e());
    }
    
    public abstract void q();
    
    public void init() {
        try {
            for (int i = 0; i < this.w.length; ++i) {
                this.q[i] = this.getImage(new URL(this.getCodeBase() + cl.q("\"5C?EB35CZCD1BCZCD1B/") + i + ".gif"));
                this.w[i] = this.getImage(new URL(this.getCodeBase() + cl.q("\"5C?EB35CZCD1DEC5CZCD1DEC/") + i + ".gif"));
                final int n = i;
                final Image image = this.w[i];
                final int n2 = n;
                (this.q = new MediaTracker(new e())).addImage(image, n2);
                this.q.waitForID(n2);
            }
        }
        catch (Exception ex) {}
    }
    
    public static Image q(final int n) {
        try {
            return AppletAbstract.q.q[0];
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public static AppletAbstract q() {
        return AppletAbstract.q;
    }
    
    public Image getImage(final URL url, final String s) {
        try {
            return super.getImage(url, s);
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    static {
        AppletAbstract.q = null;
    }
}
