// 
// Decompiled by Procyon v0.5.30
// 

package a;

public final class ew
{
    public static byte[] q;
    
    public static byte[] q() {
        final String s = "VVVVVVVVVV+iVVVVViVVVVVVViVVVVVVVViVVVVVViViVVVggggggX-iVVVVVVVYiVVVYiVVVVVYiVYiVVVVVVVVVYiVVVVgggg+iVgX-iVVVYii+ggViiX-ggVVVYiii+gggViiiX-gggY";
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); ++i) {
            char c;
            if ((c = (char)(s.charAt(i) + '[' - '+')) > 'z') {
                c = (char)(s.charAt(i) - '+');
            }
            sb.append(c);
        }
        final String string = sb.toString();
        final eu eu = new eu();
        try {
            eu.q(string.toCharArray());
            eu.q();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return eu.q();
    }
    
    static {
        final byte[] array = { 11, 123, 41, 31, 79, 23, 117, -13 };
    }
}
