// 
// Decompiled by Procyon v0.5.30
// 

package doook;

public class aG
{
    private static ai a;
    
    public static String a(final String s) {
        if (aG.a == null) {
            return s;
        }
        final String s2 = new String(s);
        final String a = aG.a.a(s2);
        if (a == null) {
            return new String(s2);
        }
        return a;
    }
    
    public aG(final ai a) {
        aG.a = a;
    }
}
