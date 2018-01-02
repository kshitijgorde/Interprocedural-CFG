// 
// Decompiled by Procyon v0.5.30
// 

package chat;

public final class aS
{
    private static br a;
    
    public static String a(String s) {
        if (aS.a == null) {
            return s;
        }
        s = new String(s);
        final String a;
        if ((a = aS.a.a(s)) == null) {
            return new String(s);
        }
        return a;
    }
    
    public static String a(final int n) {
        if (aS.a == null) {
            return "X";
        }
        final String a;
        if ((a = aS.a.a(n & 0xFFFF)) == null) {
            return new String("X");
        }
        return a;
    }
    
    public aS(final br a) {
        aS.a = a;
    }
}
