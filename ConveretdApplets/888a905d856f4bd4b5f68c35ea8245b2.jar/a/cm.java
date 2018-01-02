// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Frame;
import java.applet.Applet;
import netscape.javascript.JSObject;
import java.util.Hashtable;

public final class cm
{
    private static final String w;
    private static final String e;
    private static final String r;
    public static final String q;
    private static Hashtable q;
    
    private static String q(final String s) {
        String q;
        if ((q = cm.q.get(s)) == null) {
            q = be.q(s);
            cm.q.put(s, q);
        }
        return q;
    }
    
    private static Object q(final String s, final Object[] array, final cs cs) {
        try {
            return JSObject.getWindow((Applet)m.q()).call(s, array);
        }
        catch (Exception ex) {
            if (cs != null) {
                final String s2 = "To perform this operation ";
                String s3;
                if (ex.getMessage() != null && ex.getMessage().indexOf("MAYSCRIPT") >= 0) {
                    s3 = s2 + "MAYSCRIPT should be enabled";
                }
                else {
                    s3 = s2 + "enable popup windows in your browser";
                }
                new dd(new Frame(), be.w("Note"), be.w(s3), cs).setVisible(true);
            }
            System.err.println("Exc run script:" + ex.getMessage());
            return "";
        }
    }
    
    public static Object q(final Object[] array, final cs cs) {
        return q("eval", array, cs);
    }
    
    public static void q(final String s, cs cs) {
        final String[] array = { " var txt = '" + ds.w(s) + "'; " + q(cm.w + "/" + cm.e) };
        cs = cs;
        q("eval", array, cs);
    }
    
    public static void q(final String s, String s2, final String s3) {
        final StringBuffer sb;
        (sb = new StringBuffer()).append("themeColor=").append(ds.q(bC.w.q)).append("&");
        sb.append("backgroundColor=").append(ds.q(bC.w.q)).append("&");
        sb.append("backgroundGradientColors=").append(ds.q(bC.w.q.brighter())).append("&");
        sb.append("mainPanelBackgroundColor=").append(ds.q(bC.w.w)).append("&");
        sb.append(ds.q("C5CC9?>t4h")).append(s2).append("&");
        sb.append("host=").append(ak.q(dN.w.getBytes())).append("&");
        sb.append("imageUrl=").append(ak.q(s3.getBytes()));
        s2 = ds.q(s2 = ds.q(s2 = q(cm.w + "/" + cm.r), ds.q("+OG9>4?Gy1=5-"), s), ds.q("+O6<1C8&1BC-"), ak.q(sb.toString().getBytes()));
        System.out.println("readed_js=" + s2);
        q("eval", new String[] { s2 }, null);
    }
    
    public static void q(final String s, final String s2, final cs cs) {
        final Object[] array2;
        final Object[] array = new Object[(array2 = new Object[] { s + "/" + s2 + "/index.html", s2, ds.q("G94D8hc[[W85978Dha[[WD??<21Bh>?W=5>E21Bh>?WC3B?<<21BCh>?WB5C9J12<5hI5CW<?31D9?>h>?WCD1DECh>?") }).length + 1];
        array[0] = "myRef = window.open('" + array2[0] + "','" + array2[1] + "','" + array2[2] + "'); myRef.focus()";
        q("eval", array, cs);
    }
    
    static {
        w = ds.q("C3B9@DC");
        e = ds.q("3@3\\Y:C");
        ds.q("1F/38;\\Y:C");
        r = ds.q("1F/?@>Y:C");
        ds.q("8D=<l&Y8D=<");
        q = ds.q("j>/<");
        cm.q = new Hashtable();
    }
}
