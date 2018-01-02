// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

import java.util.MissingResourceException;
import com.hw.client.util.a;
import java.util.ResourceBundle;

public final class cR
{
    private static cR a;
    private static ResourceBundle b;
    private static final String c;
    
    public static String a(int n) {
        final int n2 = n;
        final String s = "title";
        n = n2;
        final StringBuffer sb;
        (sb = new StringBuffer(cR.c)).append(Integer.toString(n)).append(".").append(s);
        return a(sb.toString());
    }
    
    private static String a(final String s) {
        String string = null;
        try {
            if (cR.b == null) {
                cR.b = ResourceBundle.getBundle("jsecuredoor.doorerror");
            }
            string = cR.b.getString(s);
        }
        catch (MissingResourceException ex) {
            com.hw.client.util.a.a("could not find value for resource, key => " + s, ex);
        }
        return string;
    }
    
    static {
        cR.a = new cR();
        c = new String("doorerror.");
    }
}
