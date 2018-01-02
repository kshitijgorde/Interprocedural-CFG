// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Point;

public class V implements dP
{
    public dx q;
    public final bo q;
    
    public V() {
    }
    
    public static String q(String substring, final String s) {
        final StringBuffer sb = new StringBuffer();
        for (int i = substring.indexOf(s); i >= 0; i = (substring = substring.substring(i + 1)).indexOf(s)) {
            final String substring2 = substring.substring(0, i);
            sb.append(substring2).append((substring2.indexOf(" ") >= 0) ? "" : " ");
        }
        return sb.toString();
    }
    
    public V(final bo q, final dx q2) {
        this.q = q;
        this.q = q2;
    }
    
    public Point q() {
        return this.q.q.q.q().getLocation();
    }
    
    public void q(final String s) {
        this.q.q(Long.parseLong(s));
    }
}
