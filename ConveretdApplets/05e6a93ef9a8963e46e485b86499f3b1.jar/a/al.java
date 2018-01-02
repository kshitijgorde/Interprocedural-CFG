// 
// Decompiled by Procyon v0.5.30
// 

package a;

public class al
{
    public static k q;
    
    public al() {
    }
    
    public static String q(String s) {
        if (al.q == null) {
            return s;
        }
        s = new String(s);
        final String q;
        if ((q = al.q.q(s)) == null) {
            return new String(s);
        }
        return q;
    }
    
    public al(final k q) {
        al.q = q;
    }
}
