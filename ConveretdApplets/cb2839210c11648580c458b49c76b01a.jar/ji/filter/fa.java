// 
// Decompiled by Procyon v0.5.30
// 

package ji.filter;

import ji.io.h;
import ji.util.d;
import ji.io.fb;
import ji.res.s;
import ji.util.e;
import ji.document.ad;

public class fa
{
    public static boolean a(final String s, final ad ad, final boolean b) throws Exception {
        return a(s, "application/vnd.ms-outlook", ad, b);
    }
    
    public static boolean a(final String s, final String s2, final ad ad, final boolean b) throws Exception {
        if (!e.y() || !b(s, s2, ad, b)) {
            return false;
        }
        if (e.av()) {
            return true;
        }
        throw new fb(s.a(1322, ad.getId()));
    }
    
    public static ck a(final String s) throws Exception {
        ck ck = null;
        boolean b = false;
        try {
            ck = (ck)d.a2("daeja4.filter.msg.jiFilterMSG");
        }
        catch (NoClassDefFoundError noClassDefFoundError) {
            b = true;
            h.a(s, noClassDefFoundError);
        }
        catch (Exception ex) {
            h.a(s, ex);
        }
        finally {
            if (ck == null) {
                h.d(s, "MSG filter factory: Failed to load MSG filter!");
            }
        }
        if (b) {
            throw new fb(s.a(1323, s));
        }
        return ck;
    }
    
    public static boolean b(final String s, final String s2, final ad ad, final boolean b) {
        return "application/vnd.ms-outlook".equals(s2) || "application/octet-stream".equals(s2) || "msg".equals(s);
    }
}
