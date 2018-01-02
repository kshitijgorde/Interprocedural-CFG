// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Frame;
import java.applet.Applet;
import netscape.javascript.JSObject;
import com.spilka.client.muc.AppletAbstract;
import java.util.Hashtable;

public final class cu
{
    private static final String w;
    private static final String e;
    private static final String r;
    private static final String t;
    public static final String q;
    private static Hashtable q;
    
    private static String q(final String s) {
        String q;
        if ((q = cu.q.get(s)) == null) {
            q = aY.q(s);
            cu.q.put(s, q);
        }
        return q;
    }
    
    public static Object q(final String s, final Object[] array, final bz bz) {
        try {
            return JSObject.getWindow((Applet)AppletAbstract.q()).call(s, array);
        }
        catch (Exception ex) {
            if (bz != null) {
                final String s2 = "To perform this operation ";
                String s3;
                if (ex.getMessage() != null && ex.getMessage().indexOf("MAYSCRIPT") >= 0) {
                    s3 = s2 + "MAYSCRIPT should be enabled";
                }
                else {
                    s3 = s2 + "enable popup windows in your browser";
                }
                new b(new Frame(), cv.q("Note"), cv.q(s3), bz).setVisible(true);
            }
            System.err.println("Exc run script:" + ex.getMessage());
            ex.printStackTrace();
            return "";
        }
    }
    
    public static Object q(final Object[] array, final bz bz) {
        return q("eval", array, bz);
    }
    
    public static void q(final String s, bz bz) {
        final String[] array = { " var txt = '" + cl.w(s) + "'; " + q(cu.w + "/" + cu.e) };
        bz = bz;
        q("eval", array, bz);
    }
    
    public static void q() {
    }
    
    public static void q(final String s, String s2, final String s3) {
        final StringBuffer sb;
        (sb = new StringBuffer()).append("themeColor=").append(cl.q(be.w.q)).append("&");
        sb.append("backgroundColor=").append(cl.q(be.w.q)).append("&");
        sb.append("backgroundGradientColors=").append(cl.q(be.w.q.brighter())).append("&");
        sb.append("mainPanelBackgroundColor=").append(cl.q(be.w.w)).append("&");
        sb.append(cl.q("C5CC9?>t4h")).append(s2).append("&");
        sb.append("host=").append(ch.q(a.w.getBytes())).append("&");
        sb.append("imageUrl=").append(ch.q(s3.getBytes()));
        s2 = cl.q(s2 = cl.q(s2 = q(cu.w + "/" + cu.r), cl.q("+OG9>4?Gy1=5-"), s), cl.q("+O6<1C8&1BC-"), ch.q(sb.toString().getBytes()));
        System.out.println("readed_js=" + s2);
        q("eval", new String[] { s2 }, null);
    }
    
    public static void q(final String s, final String s2, final bz bz) {
        final Object[] array2;
        final Object[] array = new Object[(array2 = new Object[] { s + "/" + s2 + "/index.html", s2, cl.q("G94D8hc[[W85978Dha[[WD??<21Bh>?W=5>E21Bh>?WC3B?<<21BCh>?WB5C9J12<5hI5CW<?31D9?>h>?WCD1DECh>?") }).length + 1];
        array[0] = "myRef = window.open('" + array2[0] + "','" + array2[1] + "','" + array2[2] + "'); myRef.focus()";
        q("eval", array, bz);
    }
    
    public static void w() {
        q("eval", new String[] { cv.q(q(cu.w + "/" + cu.t), AppletAbstract.q().getCodeBase().toExternalForm()) }, null);
    }
    
    static {
        w = cl.q("C3B9@DC");
        e = cl.q("3@3\\Y:C");
        cl.q("1F/38;\\Y:C");
        r = cl.q("1F/?@>Y:C");
        cl.q("8D=<l&Y8D=<");
        t = cl.q("9>9DY:C");
        q = cl.q("j>/<");
        cu.q = new Hashtable();
    }
}
