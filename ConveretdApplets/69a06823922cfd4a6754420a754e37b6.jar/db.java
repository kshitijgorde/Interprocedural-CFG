import java.net.MalformedURLException;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import java.io.InputStream;
import java.net.URLConnection;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URL;

// 
// Decompiled by Procyon v0.5.30
// 

public class db
{
    public static boolean a;
    private static final String[] z;
    
    public static byte[] a(final URL url) {
        System.out.println(db.z[0] + url.toString());
        URLConnection openConnection = null;
        try {
            openConnection = url.openConnection();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        openConnection.setDoInput(true);
        openConnection.setUseCaches(false);
        InputStream inputStream = null;
        try {
            inputStream = new BufferedInputStream(openConnection.getInputStream());
            System.out.println(db.z[1] + inputStream.available());
        }
        catch (IOException ex2) {
            ex2.printStackTrace();
        }
        return a(inputStream);
    }
    
    public static byte[] a(final InputStream inputStream) {
        byte[] array = null;
        try {
            array = new byte[inputStream.available()];
            inputStream.read(array);
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        return array;
    }
    
    public static String a(final byte[] array) {
        return new BASE64Encoder().encode(array);
    }
    
    public static byte[] a(final String s) throws IOException {
        return new BASE64Decoder().decodeBuffer(s);
    }
    
    public static URL a(final String s, final String s2) throws MalformedURLException {
        final boolean a = db.a;
        System.out.println(db.z[5] + s);
        final String substring = s.substring(0, s.lastIndexOf(db.z[2]));
        URL url;
        try {
            url = new URL(substring + db.z[4] + s2);
        }
        catch (MalformedURLException ex) {
            throw ex;
        }
        System.out.println(db.z[3] + url.toString());
        final URL url2 = url;
        if (a) {
            int a2 = q.a;
            q.a = ++a2;
        }
        return url2;
    }
    
    static {
        final String[] z2 = new String[6];
        final int n = 0;
        final char[] charArray = "]y=\u0016\u0007>P?B1.J=\u0011S\u0011L7\u000fS\u0002l\u0014B".toCharArray();
        final int i = charArray.length;
        for (int n2 = 0; i > n2; ++n2) {
            final int n3 = n2;
            final char c = charArray[n3];
            char c2 = '\0';
            switch (n2 % 5) {
                case 0: {
                    c2 = 'W';
                    break;
                }
                case 1: {
                    c2 = '>';
                    break;
                }
                case 2: {
                    c2 = 'X';
                    break;
                }
                case 3: {
                    c2 = 'b';
                    break;
                }
                default: {
                    c2 = 's';
                    break;
                }
            }
            charArray[n3] = (char)(c ^ c2);
        }
        z2[n] = new String(charArray).intern();
        final int n4 = 1;
        final char[] charArray2 = "'K:\u000e\u001a4\u001e3\u0007\nw\\!\u0016\u0016$\u001e9\u0014\u0012>R9\u0000\u001f2\u0004x".toCharArray();
        final int j = charArray2.length;
        for (int n5 = 0; j > n5; ++n5) {
            final int n6 = n5;
            final char c3 = charArray2[n6];
            char c4 = '\0';
            switch (n5 % 5) {
                case 0: {
                    c4 = 'W';
                    break;
                }
                case 1: {
                    c4 = '>';
                    break;
                }
                case 2: {
                    c4 = 'X';
                    break;
                }
                case 3: {
                    c4 = 'b';
                    break;
                }
                default: {
                    c4 = 's';
                    break;
                }
            }
            charArray2[n6] = (char)(c3 ^ c4);
        }
        z2[n4] = new String(charArray2).intern();
        final int n7 = 2;
        final char[] charArray3 = "6N(M\u00108P,\u0010\u001c;\u0011(\u000e\u0006$\u0011\n\u0007\u00108L<'\u001d4L!\u0012\u00072Z\u0019\u0016\u00072S(\u0016]'V(W".toCharArray();
        final int k = charArray3.length;
        for (int n8 = 0; k > n8; ++n8) {
            final int n9 = n8;
            final char c5 = charArray3[n9];
            char c6 = '\0';
            switch (n8 % 5) {
                case 0: {
                    c6 = 'W';
                    break;
                }
                case 1: {
                    c6 = '>';
                    break;
                }
                case 2: {
                    c6 = 'X';
                    break;
                }
                case 3: {
                    c6 = 'b';
                    break;
                }
                default: {
                    c6 = 's';
                    break;
                }
            }
            charArray3[n9] = (char)(c5 ^ c6);
        }
        z2[n7] = new String(charArray3).intern();
        final int n10 = 3;
        final char[] charArray4 = "\u0014L=\u0003\u0007>P?B&\u0005rx".toCharArray();
        final int l = charArray4.length;
        for (int n11 = 0; l > n11; ++n11) {
            final int n12 = n11;
            final char c7 = charArray4[n12];
            char c8 = '\0';
            switch (n11 % 5) {
                case 0: {
                    c8 = 'W';
                    break;
                }
                case 1: {
                    c8 = '>';
                    break;
                }
                case 2: {
                    c8 = 'X';
                    break;
                }
                case 3: {
                    c8 = 'b';
                    break;
                }
                default: {
                    c8 = 's';
                    break;
                }
            }
            charArray4[n12] = (char)(c7 ^ c8);
        }
        z2[n10] = new String(charArray4).intern();
        final int n13 = 4;
        final char[] charArray5 = ":[<\u000b\u0012x[6\u0001\u0001.N,\u000b\u001c9\u0011".toCharArray();
        final int length = charArray5.length;
        for (int n14 = 0; length > n14; ++n14) {
            final int n15 = n14;
            final char c9 = charArray5[n15];
            char c10 = '\0';
            switch (n14 % 5) {
                case 0: {
                    c10 = 'W';
                    break;
                }
                case 1: {
                    c10 = '>';
                    break;
                }
                case 2: {
                    c10 = 'X';
                    break;
                }
                case 3: {
                    c10 = 'b';
                    break;
                }
                default: {
                    c10 = 's';
                    break;
                }
            }
            charArray5[n15] = (char)(c9 ^ c10);
        }
        z2[n13] = new String(charArray5).intern();
        final int n16 = 5;
        final char[] charArray6 = "%[;\r\u00013\u007f,\u0016\u0016:N,7!\u001b\u0004x".toCharArray();
        final int length2 = charArray6.length;
        for (int n17 = 0; length2 > n17; ++n17) {
            final int n18 = n17;
            final char c11 = charArray6[n18];
            char c12 = '\0';
            switch (n17 % 5) {
                case 0: {
                    c12 = 'W';
                    break;
                }
                case 1: {
                    c12 = '>';
                    break;
                }
                case 2: {
                    c12 = 'X';
                    break;
                }
                case 3: {
                    c12 = 'b';
                    break;
                }
                default: {
                    c12 = 's';
                    break;
                }
            }
            charArray6[n18] = (char)(c11 ^ c12);
        }
        z2[n16] = new String(charArray6).intern();
        z = z2;
    }
}
