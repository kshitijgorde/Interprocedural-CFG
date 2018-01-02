// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.applet.Applet;
import netscape.javascript.JSObject;
import java.awt.Color;

public class aI extends bn
{
    public int v;
    public int w;
    public boolean u;
    public boolean v;
    public String w;
    public String x;
    public static String[] d;
    public static int[] d;
    public static boolean w;
    public static boolean x;
    
    public aI(final int n, final String s) {
        super(n, s);
        this.v = -999;
        this.w = -999;
        this.u = false;
        this.v = false;
    }
    
    public static void b(final V v) {
        if (v.d() == 1) {
            return;
        }
        aI.d = new String[v.d() * 2];
        for (int i = 0; i < v.d(); ++i) {
            aI.d[i * 2] = v.a(i, 0);
            aI.d[i * 2 + 1] = v.a(i, 1);
        }
    }
    
    public static Color[] a(final aq aq) {
        if (aq == null) {
            return new Color[] { Color.black, Color.white };
        }
        if (aq.u) {
            return new Color[] { Color.red, Color.pink };
        }
        return new Color[] { new Color(aq.ag), Color.white };
    }
    
    public static Object a(final Object[] array) {
        try {
            return JSObject.getWindow((Applet)ar.b).call("eval", array);
        }
        catch (Exception ex) {
            return "";
        }
    }
    
    static {
        aI.d = new int[] { 3, 6, 6, 12 };
    }
}
