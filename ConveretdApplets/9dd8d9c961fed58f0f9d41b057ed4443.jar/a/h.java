// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.net.URL;
import java.awt.Image;
import java.applet.Applet;

public abstract class h extends Applet
{
    protected Image[] q;
    public Image[] w;
    public String q;
    public int q;
    public int w;
    public int e;
    private static h q;
    public cn q;
    public boolean q;
    public int r;
    public String w;
    public W q;
    public String e;
    
    public abstract void q();
    
    public h() {
        this.q = new Image[6];
        this.w = new Image[6];
        this.q = 0;
        this.w = 0;
        this.e = -1;
        this.q = null;
        this.q = false;
        this.r = 0;
        this.w = null;
    }
    
    public static Image q(final int n) {
        try {
            return h.q.q[0];
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public static h q() {
        return h.q;
    }
    
    public static void q(final h q) {
        h.q = q;
        String host;
        if ((host = q.getCodeBase().getHost()) == null || host.equals("")) {
            host = "127.0.0.1";
        }
        cu.w = host;
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
        h.q = null;
    }
}
