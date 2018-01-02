// 
// Decompiled by Procyon v0.5.30
// 

package a;

public final class cN
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
        final cL cl = new cL();
        try {
            cl.q(string.toCharArray());
            cl.q();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return cl.q();
    }
    
    static {
        final byte[] array = { 11, 123, 41, 31, 79, 23, 117, -13 };
    }
}
