// 
// Decompiled by Procyon v0.5.30
// 

package ABLjemsty;

import java.net.URLEncoder;
import ABLwidgets.utils;

public class StyleEventArg
{
    public String a;
    public int b;
    public int c;
    public Object d;
    public static final String e;
    private static final String f;
    
    public StyleEventArg(final String a) {
        this.a = a;
    }
    
    public void a(final String s, final String s2) {
        this.a = utils.a(this.a, s, s2);
        if (s == StyleEventArg.e && this.a.indexOf(StyleEventArg.f) >= 0) {
            this.a = utils.a(this.a, StyleEventArg.f, (s2 == null) ? null : URLEncoder.encode(s2));
        }
    }
    
    static {
        e = new String("\u0010F");
        f = URLEncoder.encode(StyleEventArg.e);
    }
}
