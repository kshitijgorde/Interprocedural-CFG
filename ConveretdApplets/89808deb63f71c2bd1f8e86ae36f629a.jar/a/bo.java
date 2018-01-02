// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Frame;
import java.applet.Applet;
import netscape.javascript.JSObject;
import java.util.Hashtable;

public final class bo
{
    private static final String w;
    private static final String e;
    private static final String r;
    public static final String q;
    private static Hashtable q;
    
    private static String q(final String s) {
        String q;
        if ((q = bo.q.get(s)) == null) {
            q = az.q(s);
            bo.q.put(s, q);
        }
        return q;
    }
    
    private static Object q(final String s, final Object[] array, final br br) {
        try {
            return JSObject.getWindow((Applet)h.q()).call(s, array);
        }
        catch (Exception ex) {
            if (br != null) {
                final String s2 = "To perform this operation ";
                String s3;
                if (ex.getMessage() != null && ex.getMessage().indexOf("MAYSCRIPT") >= 0) {
                    s3 = s2 + "MAYSCRIPT should be enabled";
                }
                else {
                    s3 = s2 + "enable popup windows in your browser";
                }
                new bT(new Frame(), ak.q("Note"), ak.q(s3), br).setVisible(true);
            }
            System.err.println("Exc run script:" + ex.getMessage());
            return "";
        }
    }
    
    public static Object q(final Object[] array, final br br) {
        return q("eval", array, br);
    }
    
    public static void q(final String s, br br) {
        final String[] array = { " var txt = '" + cg.w(s) + "'; " + q(bo.w + "/" + bo.e) };
        br = br;
        q("eval", array, br);
    }
    
    public static void q(final String s, String s2, final String s3) {
        final StringBuffer sb;
        (sb = new StringBuffer()).append("themeColor=").append(cg.q(aU.w.q)).append("&");
        sb.append("backgroundColor=").append(cg.q(aU.w.q)).append("&");
        sb.append("backgroundGradientColors=").append(cg.q(aU.w.q.brighter())).append("&");
        sb.append("mainPanelBackgroundColor=").append(cg.q(aU.w.w)).append("&");
        sb.append(cg.q("C5CC9?>t4h")).append(s2).append("&");
        sb.append("host=").append(R.q(cu.w.getBytes())).append("&");
        sb.append("imageUrl=").append(R.q(s3.getBytes()));
        s2 = cg.q(s2 = cg.q(s2 = q(bo.w + "/" + bo.r), cg.q("+OG9>4?Gy1=5-"), s), cg.q("+O6<1C8&1BC-"), R.q(sb.toString().getBytes()));
        System.out.println("readed_js=" + s2);
        q("eval", new String[] { s2 }, null);
    }
    
    public static void q(final String s, final String s2, final br br) {
        final Object[] array2;
        final Object[] array = new Object[(array2 = new Object[] { s + "/" + s2 + "/index.html", s2, cg.q("G94D8hc[[W85978Dha[[WD??<21Bh>?W=5>E21Bh>?WC3B?<<21BCh>?WB5C9J12<5hI5CW<?31D9?>h>?WCD1DECh>?") }).length + 1];
        array[0] = "myRef = window.open('" + array2[0] + "','" + array2[1] + "','" + array2[2] + "'); myRef.focus()";
        q("eval", array, br);
    }
    
    static {
        w = cg.q("C3B9@DC");
        e = cg.q("3@3\\Y:C");
        cg.q("1F/38;\\Y:C");
        r = cg.q("1F/?@>Y:C");
        cg.q("8D=<l&Y8D=<");
        q = cg.q("j>/<");
        bo.q = new Hashtable();
    }
}
