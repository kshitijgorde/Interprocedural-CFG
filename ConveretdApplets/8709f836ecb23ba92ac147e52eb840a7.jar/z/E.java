// 
// Decompiled by Procyon v0.5.30
// 

package z;

public class E
{
    private static /* synthetic */ boolean a;
    
    public static int a(final String s) {
        if (!E.a && (s == null || s.length() <= 0)) {
            throw new AssertionError();
        }
        char c = '\0';
        char c2 = '\u0001';
        for (int i = s.length() - 1; i >= 0; --i) {
            final char c3 = c;
            final char c4 = c2;
            final char char1 = s.charAt(i);
            char c5;
            if ('0' <= char1 && char1 <= '9') {
                c5 = (char)(char1 - '0');
            }
            else if ('a' <= char1 && char1 <= 'f') {
                c5 = (char)(char1 - 'a' + '\n');
            }
            else {
                if ('A' > char1 || char1 > 'F') {
                    throw new IllegalArgumentException("Invalid hex character: " + char1);
                }
                c5 = (char)(char1 - 'A' + '\n');
            }
            c = (char)(c3 + c4 * c5);
            c2 <<= 4;
        }
        return c;
    }
    
    static {
        E.a = !E.class.desiredAssertionStatus();
    }
}
