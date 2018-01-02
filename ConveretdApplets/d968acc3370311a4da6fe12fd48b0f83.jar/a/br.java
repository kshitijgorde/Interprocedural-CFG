// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.applet.Applet;
import com.spilka.client.muc.AppletAbstract;
import netscape.javascript.JSObject;
import java.awt.Color;

public class br
{
    protected String q;
    protected String w;
    protected Color q;
    
    private br(final bq bq, final byte b) {
        this.q = "";
        this.w = "";
        this.q = Color.black;
    }
    
    br(final bq bq) {
        this(bq, (byte)0);
    }
    
    public br() {
    }
    
    public static int q() {
        int int1 = 0;
        try {
            final String s2;
            final String s;
            final boolean b;
            if (b = ((s = (s2 = (String)ea.q("getLS", new String[] { "bid" }, null))) != null && !"0".equals(s) && !"".equals(s) && !"false".equals(s) && !"undefined".equals(s))) {
                int1 = Integer.parseInt(s2);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return int1;
    }
    
    public static int w() {
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
            final String[] q = dV.q((String)((JSObject)JSObject.getWindow((Applet)AppletAbstract.q()).getMember("document")).getMember("cookie"), ";");
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
