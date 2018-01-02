// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.applet.Applet;
import netscape.javascript.JSObject;
import java.awt.Color;

public class cG extends cF
{
    public int g;
    public int h;
    public boolean b;
    public boolean h;
    public String W;
    public String f;
    public static boolean[] a;
    public static int[] g;
    public static String[] g;
    public static int[] h;
    public static boolean ax;
    
    public cG(final int n, final String s) {
        super(n, s);
        this.g = -999;
        this.h = -999;
        this.b = false;
        this.h = false;
    }
    
    public static void E(final cD cd) {
        for (int i = 0; i < cG.a.length; ++i) {
            cG.a[i] = cd.b(0, 41 + i);
        }
        for (int j = 0; j < cd.c(); ++j) {
            cG.g[j] = cd.b(0, j);
        }
        for (int k = 0; k < cd.d(); ++k) {
            cG.g[k] = cd.a(0, k);
        }
    }
    
    public static void F(final cD c) {
        bw.c = c;
        bw.w();
    }
    
    public static Color[] a(final ab ab) {
        if (ab == null) {
            return new Color[] { Color.black, Color.white };
        }
        if (ab.b) {
            return new Color[] { Color.red, Color.pink };
        }
        return new Color[] { new Color(ab.aN), Color.white };
    }
    
    public static Object a(final Object[] array) {
        try {
            return JSObject.getWindow((Applet)cI.b).call("eval", array);
        }
        catch (Exception ex) {
            return "";
        }
    }
    
    static {
        cG.a = new boolean[10];
        cG.g = new int[15];
        cG.g = new String[10];
        cG.h = new int[] { 3, 6, 6, 12 };
    }
}
