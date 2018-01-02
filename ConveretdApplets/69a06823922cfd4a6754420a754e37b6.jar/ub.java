// 
// Decompiled by Procyon v0.5.30
// 

public class ub
{
    public static boolean a;
    
    public static String a(final double n, final int n2) {
        final boolean a = ub.a;
        if (n2 == 0) {
            final int n3 = (int)Math.round(n);
        }
        String s = "" + Math.round(n * Math.pow(10.0, n2)) / Math.pow(10.0, n2);
        final int index = s.indexOf(46);
        if (index != -1 && index < s.length() - 1) {
            final int length = s.substring(index + 1).length();
            if (length < n2) {
                int i = 0;
                while (i < n2 - length) {
                    s += "0";
                    ++i;
                    if (a) {
                        int a2 = q.a;
                        q.a = ++a2;
                        break;
                    }
                }
            }
        }
        return s;
    }
    
    public static boolean a(final char c) {
        return c >= '0' && c <= '9';
    }
}
