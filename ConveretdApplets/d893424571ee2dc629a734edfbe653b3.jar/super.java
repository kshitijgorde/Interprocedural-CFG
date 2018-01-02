// 
// Decompiled by Procyon v0.5.30
// 

public class super
{
    public static String _(String s) {
        s.trim();
        if (s.indexOf(63) == -1) {
            s = String.valueOf(s) + '?';
        }
        else if (s.charAt(s.length() - 1) != '?' && s.charAt(s.length() - 1) != '&') {
            s = String.valueOf(s) + '&';
        }
        return s;
    }
}
