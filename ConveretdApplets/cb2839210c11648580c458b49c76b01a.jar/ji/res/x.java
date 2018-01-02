// 
// Decompiled by Procyon v0.5.30
// 

package ji.res;

import java.util.Locale;

public class x
{
    private static String a;
    
    public static final String a() {
        if (x.a != null) {
            return x.a;
        }
        return Locale.getDefault().getCountry().toLowerCase();
    }
    
    public static final String b() {
        final Locale default1 = Locale.getDefault();
        String substring = "";
        try {
            if (default1 != null) {
                substring = default1.toString().toLowerCase().substring(0, 2);
            }
        }
        catch (Exception ex) {}
        return substring;
    }
    
    static {
        x.a = null;
    }
}
