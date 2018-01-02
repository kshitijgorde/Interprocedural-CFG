// 
// Decompiled by Procyon v0.5.30
// 

package a;

public class ak
{
    public static k q;
    
    public ak() {
    }
    
    public static String q(String s) {
        if (ak.q == null) {
            return s;
        }
        s = new String(s);
        final String q;
        if ((q = ak.q.q(s)) == null) {
            return new String(s);
        }
        return q;
    }
    
    public ak(final k q) {
        ak.q = q;
    }
}
