import GK.FK;
import java.net.URLConnection;
import java.net.Socket;
import java.io.ByteArrayOutputStream;
import java.lang.reflect.Method;
import java.util.Random;

// 
// Decompiled by Procyon v0.5.30
// 

public class JK
{
    private static Random RR;
    private static Method UR;
    private static long WR;
    private static long VR;
    static Class YR;
    
    static {
        JK.RR = new Random(System.currentTimeMillis());
        SR();
    }
    
    public static long MP() {
        final long nn = NN();
        int n = 0;
        long nn2;
        while ((nn2 = NN()) == nn) {
            ++n;
        }
        int n2 = 0;
        long nn3;
        while ((nn3 = NN()) == nn2) {
            ++n2;
        }
        final int n3 = (int)(nn2 - nn);
        final int n4 = (int)(nn3 - nn2);
        if (n3 > 0 && n4 > 0) {
            final int n5 = n3 * n2 / n4;
            if (n5 > 0) {
                return nn2 - Math.min(n3, n * n3 / n5);
            }
        }
        return nn;
    }
    
    private static byte[] TR() {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final boolean[][] array = new boolean[256][256];
        int n = 0;
        for (int i = 0; i < 196608; ++i) {
            final int n2 = JK.RR.nextInt() & 0xFF;
            for (int j = 0; j < 7; ++j) {
                final int n3 = n2 + j & 0xFF;
                if (!array[n][n3]) {
                    byteArrayOutputStream.write((char)n3);
                    array[n][n3] = true;
                    n = n3;
                }
            }
        }
        return byteArrayOutputStream.toByteArray();
    }
    
    public static int EN(final Socket socket) {
        try {
            return (int)socket.getClass().getMethod(VK(":<-O<><8+<_,;;</N8'<"), (Class<?>[])new Class[0]).invoke(socket, new Object[0]);
        }
        catch (Throwable t) {
            return 0;
        }
    }
    
    public static int FN(final Socket socket) {
        try {
            return (int)socket.getClass().getMethod(VK(":<-N<3=_,;;</N8'<"), (Class<?>[])new Class[0]).invoke(socket, new Object[0]);
        }
        catch (Throwable t) {
            return 0;
        }
    }
    
    public static int EO() {
        int min = 100;
        long fr = FR();
        for (int i = 0; i < 10; ++i) {
            final long fr2 = FR();
            min = Math.min(min, (int)(fr2 - fr));
            fr = fr2;
        }
        return Math.max(1, min);
    }
    
    public static byte[] KN(final int n) {
        final byte[] array = new byte[n];
        int min;
        for (int i = 0; i < array.length; i += min) {
            final byte[] tr = TR();
            min = Math.min(array.length - i, tr.length);
            System.arraycopy(tr, 0, array, i, min);
        }
        return array;
    }
    
    private static long XR() {
        if (JK.UR != null) {
            try {
                return (long)JK.UR.invoke(null, new Object[0]);
            }
            catch (Throwable t) {}
        }
        return 0L;
    }
    
    public static URLConnection PN(final URLConnection urlConnection) {
        urlConnection.setUseCaches(false);
        urlConnection.setRequestProperty(VK("^@>9<t^23-/25"), VK("32t>@>9<"));
        return urlConnection;
    }
    
    public static void DN(final Socket socket, final int n) {
        if (n > EN(socket)) {
            try {
                socket.getClass().getMethod(VK(".<-O<><8+<_,;;</N8'<"), Integer.TYPE).invoke(socket, new Integer(n));
            }
            catch (Throwable t) {}
        }
    }
    
    public static void MN(final Socket socket, final int n) {
        if (n > FN(socket)) {
            try {
                socket.getClass().getMethod(VK(".<-N<3=_,;;</N8'<"), Integer.TYPE).invoke(socket, new Integer(n));
            }
            catch (Throwable t) {}
        }
    }
    
    public static byte[] BN(final String s) {
        if (s != null) {
            final byte[] array = new byte[s.length()];
            s.getBytes(0, array.length, array, 0);
            return array;
        }
        return null;
    }
    
    public static String UM(String string, final String s, final String s2) {
        int index;
        for (int n = 0; n < 10 && (index = string.indexOf(s)) >= 0; string = String.valueOf(string.substring(0, index)) + s2 + string.substring(index + s.length()), ++n) {}
        return string;
    }
    
    public static long FR() {
        long nn;
        while ((nn = NN()) == NN()) {}
        return nn;
    }
    
    public static long NN() {
        return (JK.VR != 0L) ? (JK.WR + (XR() - JK.VR) / 1000000L) : System.currentTimeMillis();
    }
    
    private static void SR() {
        try {
            Class yr;
            if ((yr = JK.YR) == null) {
                try {
                    yr = (JK.YR = FK.AP("java.lang.System"));
                }
                catch (ClassNotFoundException ex) {
                    throw new NoClassDefFoundError(ex.getMessage());
                }
            }
            JK.UR = yr.getMethod(VK("3@32M84<"), (Class[])new Class[0]);
        }
        catch (Throwable t) {}
        long nn;
        while ((nn = NN()) == NN()) {}
        JK.VR = XR();
        JK.WR = nn;
    }
    
    private static String VK(final String s) {
        final char[] charArray = s.toCharArray();
        for (int i = 0; i < charArray.length; ++i) {
            final char c = charArray[i];
            if (c >= ' ' && c <= '~') {
                charArray[i] = (char)(32 + (13196009 - c) % 95);
            }
        }
        return new String(charArray);
    }
}
