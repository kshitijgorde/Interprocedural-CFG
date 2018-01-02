// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.applet.Applet;
import netscape.javascript.JSObject;
import java.awt.Color;

public class F extends D
{
    public int c;
    public int t;
    public boolean j;
    public boolean h;
    public String l;
    public String d;
    public static String[] a;
    public static int[] b;
    public static boolean o;
    public static boolean p;
    
    public F(final int n, final String s) {
        super(n, s);
        this.c = -999;
        this.t = -999;
        this.j = false;
        this.h = false;
    }
    
    public static void a(final aJ aj) {
        if (aj.g() == 1) {
            return;
        }
        F.a = new String[aj.g() * 2];
        for (int i = 0; i < aj.g(); ++i) {
            F.a[i * 2] = aj.a(i, 0);
            F.a[i * 2 + 1] = aj.a(i, 1);
        }
    }
    
    public static Color[] a(final a a) {
        if (a == null) {
            return new Color[] { Color.black, Color.white };
        }
        if (a.j) {
            return new Color[] { Color.red, Color.pink };
        }
        return new Color[] { new Color(a.u), Color.white };
    }
    
    public static Object a(final Object[] array) {
        try {
            return JSObject.getWindow((Applet)av.a).call("eval", array);
        }
        catch (Exception ex) {
            return "";
        }
    }
    
    static {
        F.b = new int[] { 3, 6, 6, 12 };
    }
}
