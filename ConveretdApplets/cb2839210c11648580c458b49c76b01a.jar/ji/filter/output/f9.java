// 
// Decompiled by Procyon v0.5.30
// 

package ji.filter.output;

import ji.io.h;
import ji.util.d;
import java.util.Hashtable;

public class f9
{
    private static Hashtable a;
    
    public static String a(final int n) {
        switch (n) {
            case 1: {
                return "png";
            }
            case 2: {
                return "jpg";
            }
            case 3: {
                return "tif";
            }
            default: {
                return null;
            }
        }
    }
    
    public static int a(String trim) {
        int n = 0;
        if (trim != null) {
            trim = trim.toLowerCase().trim();
            if (trim.indexOf("png") > -1) {
                n = 1;
            }
            else if (trim.indexOf("jpg") > -1) {
                n = 2;
            }
            else if (trim.indexOf("jpeg") > -1) {
                n = 2;
            }
            else if (trim.indexOf("tif") > -1) {
                n = 3;
            }
        }
        return n;
    }
    
    public static ga a(final String s, final String parentId) throws Exception {
        final int a = a(s);
        ga ga = null;
        if (f9.a != null) {
            final String s2 = f9.a.get(new Integer(a));
            if (s2 != null) {
                try {
                    final Class<?> forName = Class.forName(s2);
                    if (forName != null) {
                        final ga ga2 = (ga)forName.newInstance();
                        ga2.setParentId(parentId);
                        ga = ga2;
                    }
                    else if (d.cy()) {
                        h.d(parentId, "Unable to initialize output filter for ".concat(String.valueOf(String.valueOf(s2))));
                    }
                }
                catch (Exception ex) {
                    if (d.cy()) {
                        h.d(parentId, String.valueOf(String.valueOf(new StringBuffer("Unable to initialize output filter for ").append(s2).append(" (").append(ex.getMessage()).append(")"))));
                    }
                }
            }
            else if (d.cy()) {
                h.d(parentId, "Unknown filter for type ".concat(String.valueOf(String.valueOf(s))));
            }
        }
        else if (d.cy()) {
            h.d(parentId, "Filter lookup table is empty.");
        }
        return ga;
    }
    
    public static boolean b(final String s) {
        return a(s) != 0;
    }
    
    static {
        (f9.a = new Hashtable()).put(new Integer(1), "ji.filter.output.jiPNGWriter");
        f9.a.put(new Integer(2), "ji.filter.output.jiJPEGWriter");
        f9.a.put(new Integer(3), "ji.filter.output.jiTIFFOutputWriter");
    }
}
