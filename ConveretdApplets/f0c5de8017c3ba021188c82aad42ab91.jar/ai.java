// 
// Decompiled by Procyon v0.5.30
// 

public class ai
{
    public static final String a(final String s, final String s2) {
        if (s == null) {
            return s2;
        }
        if (s2 == null) {
            return s;
        }
        if (s.endsWith("/")) {
            if (s2.startsWith("/")) {
                return s + s2.substring(1);
            }
            return s + s2;
        }
        else {
            if (s2.startsWith("/")) {
                return s + s2;
            }
            return s + "/" + s2;
        }
    }
}
