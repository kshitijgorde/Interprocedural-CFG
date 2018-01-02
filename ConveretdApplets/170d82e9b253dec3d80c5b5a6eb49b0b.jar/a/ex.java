// 
// Decompiled by Procyon v0.5.30
// 

package a;

public final class ex
{
    public static String q(String substring, final String s) {
        final StringBuffer sb = new StringBuffer();
        for (int i = substring.indexOf(s); i >= 0; i = (substring = substring.substring(i + 1)).indexOf(s)) {
            final String substring2 = substring.substring(0, i);
            sb.append(substring2).append((substring2.indexOf(" ") >= 0) ? "" : " ");
        }
        return sb.toString();
    }
}
