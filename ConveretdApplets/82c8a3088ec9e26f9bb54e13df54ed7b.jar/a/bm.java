// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Frame;
import java.applet.Applet;
import netscape.javascript.JSObject;
import java.util.Hashtable;

public final class bm
{
    private static final String w;
    private static final String e;
    private static final String r;
    public static final String q;
    private static Hashtable q;
    
    private static String q(final String s) {
        String q;
        if ((q = bm.q.get(s)) == null) {
            q = az.q(s);
            bm.q.put(s, q);
        }
        return q;
    }
    
    private static Object q(final String s, final Object[] array, final bp bp) {
        try {
            return JSObject.getWindow((Applet)h.q()).call(s, array);
        }
        catch (Exception ex) {
            if (bp != null) {
                final String s2 = "To perform this operation ";
                String s3;
                if (ex.getMessage() != null && ex.getMessage().indexOf("MAYSCRIPT") >= 0) {
                    s3 = s2 + "MAYSCRIPT should be enabled";
                }
                else {
                    s3 = s2 + "enable popup windows in your browser";
                }
                new bR(new Frame(), ak.q("Note"), ak.q(s3), bp).setVisible(true);
            }
            System.err.println("Exc run script:" + ex.getMessage());
            return "";
        }
    }
    
    public static Object q(final Object[] array, final bp bp) {
        return q("eval", array, bp);
    }
    
    public static void q(final String s, bp bp) {
        final String[] array = { " var txt = '" + ce.w(s) + "'; " + q(bm.w + "/" + bm.e) };
        bp = bp;
        q("eval", array, bp);
    }
    
    public static void q(final String s, String s2, final String s3) {
        final StringBuffer sb;
        (sb = new StringBuffer()).append("themeColor=").append(ce.q(aS.w.q)).append("&");
        sb.append("backgroundColor=").append(ce.q(aS.w.q)).append("&");
        sb.append("backgroundGradientColors=").append(ce.q(aS.w.q.brighter())).append("&");
        sb.append("mainPanelBackgroundColor=").append(ce.q(aS.w.w)).append("&");
        sb.append(ce.q("C5CC9?>t4h")).append(s2).append("&");
        sb.append("host=").append(R.q(cs.w.getBytes())).append("&");
        sb.append("imageUrl=").append(R.q(s3.getBytes()));
        s2 = ce.q(s2 = ce.q(s2 = q(bm.w + "/" + bm.r), ce.q("+OG9>4?Gy1=5-"), s), ce.q("+O6<1C8&1BC-"), R.q(sb.toString().getBytes()));
        System.out.println("readed_js=" + s2);
        q("eval", new String[] { s2 }, null);
    }
    
    public static void q(final String s, final String s2, final bp bp) {
        final Object[] array2;
        final Object[] array = new Object[(array2 = new Object[] { s + "/" + s2 + "/index.html", s2, ce.q("G94D8hc[[W85978Dha[[WD??<21Bh>?W=5>E21Bh>?WC3B?<<21BCh>?WB5C9J12<5hI5CW<?31D9?>h>?WCD1DECh>?") }).length + 1];
        array[0] = "myRef = window.open('" + array2[0] + "','" + array2[1] + "','" + array2[2] + "'); myRef.focus()";
        q("eval", array, bp);
    }
    
    static {
        w = ce.q("C3B9@DC");
        e = ce.q("3@3\\Y:C");
        ce.q("1F/38;\\Y:C");
        r = ce.q("1F/?@>Y:C");
        ce.q("8D=<l&Y8D=<");
        q = ce.q("j>/<");
        bm.q = new Hashtable();
    }
}
