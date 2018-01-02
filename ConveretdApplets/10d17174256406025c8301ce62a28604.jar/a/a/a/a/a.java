// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

public final class a
{
    public static boolean a(final String s) {
        return s == null || s.length() == 0;
    }
    
    public static String a(final String s, final String s2, final String s3) {
        if (s == null || s2 == null || s3 == null) {
            return null;
        }
        final int index;
        final int index2;
        if ((index = s.indexOf(s2)) != -1 && (index2 = s.indexOf(s3, index + s2.length())) != -1) {
            return s.substring(index + s2.length(), index2);
        }
        return null;
    }
}
