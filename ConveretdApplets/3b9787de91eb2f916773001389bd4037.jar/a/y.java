// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.applet.Applet;
import com.spilka.client.muc.AppletAbstract;
import netscape.javascript.JSObject;
import java.awt.Image;
import java.awt.Color;

public class y
{
    aq q;
    Color q;
    Color w;
    boolean q;
    boolean w;
    
    y(final u u, final u u2, final aq q) {
        this.q = Color.black;
        this.w = Color.white;
        this.q = true;
        this.w = false;
        this.q = q;
    }
    
    final int q() {
        if (this.q instanceof bp) {
            return ((bp)this.q).a;
        }
        return 0;
    }
    
    final Image q() {
        if (this.q instanceof bV) {
            return ((bV)this.q).q();
        }
        return null;
    }
    
    public y() {
    }
    
    public static int w() {
        int int1 = 0;
        try {
            final String s2;
            final String s;
            final boolean b;
            if (b = ((s = (s2 = (String)cu.q("getLS", new String[] { "bid" }, null))) != null && !"0".equals(s) && !"".equals(s) && !"false".equals(s) && !"undefined".equals(s))) {
                int1 = Integer.parseInt(s2);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return int1;
    }
    
    public static int e() {
        final String q = q("bID");
        try {
            return Integer.parseInt(q);
        }
        catch (Exception ex) {
            return Integer.parseInt(q.substring(q.indexOf("=") + 1));
        }
    }
    
    public static String q(final String s) {
        try {
            final String[] q = cl.q((String)((JSObject)JSObject.getWindow((Applet)AppletAbstract.q()).getMember("document")).getMember("cookie"), ";");
            for (int i = 0; i < q.length; ++i) {
                final String trim;
                if ((trim = q[i].trim()).indexOf(s) >= 0) {
                    return trim.substring(trim.indexOf("=") + 1);
                }
            }
        }
        catch (Exception ex) {}
        return "0";
    }
}
