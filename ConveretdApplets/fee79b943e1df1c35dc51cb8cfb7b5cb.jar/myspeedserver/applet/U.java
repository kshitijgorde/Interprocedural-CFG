// 
// Decompiled by Procyon v0.5.30
// 

package myspeedserver.applet;

import java.util.Hashtable;
import java.net.DatagramSocket;
import java.net.URLConnection;
import java.awt.Graphics;
import java.awt.Color;
import java.util.StringTokenizer;
import java.net.Socket;
import java.io.ByteArrayOutputStream;
import java.applet.Applet;
import java.lang.reflect.Method;
import java.util.Random;

public class U
{
    private static Random BU;
    private static Method DU;
    private static long FU;
    private static long EU;
    private static myspeed TU;
    private static int CU;
    static Class US;
    
    static {
        U.BU = new Random(System.currentTimeMillis());
        U.CU = 0;
        timeSetup();
    }
    
    public static void setMySpeed(final Applet applet) {
        U.TU = (myspeed)applet;
    }
    
    public static long endTime() {
        final long time = time();
        int n = 0;
        long time2;
        while ((time2 = time()) == time) {
            ++n;
        }
        int n2 = 0;
        long time3;
        while ((time3 = time()) == time2) {
            ++n2;
        }
        final int n3 = (int)(time2 - time);
        final int n4 = (int)(time3 - time2);
        if (n3 > 0 && n4 > 0) {
            final int n5 = n3 * n2 / n4;
            if (n5 > 0) {
                return time2 - Math.min(n3, n * n3 / n5);
            }
        }
        return time;
    }
    
    private static byte[] gen() {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final boolean[][] array = new boolean[256][256];
        int n = 0;
        for (int i = 0; i < 196608; ++i) {
            final int n2 = U.BU.nextInt() & 0xFF;
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
    
    public static int getReceiveBufferSize(final Socket socket) {
        try {
            return (int)socket.getClass().getMethod(TX("suf)uwuqdu9ettuh(q`u"), (Class<?>[])new Class[0]).invoke(socket, new Object[0]);
        }
        catch (Throwable t) {
            return 0;
        }
    }
    
    public static int getSendBufferSize(final Socket socket) {
        try {
            return (int)socket.getClass().getMethod(TX("suf(ulv9ettuh(q`u"), (Class<?>[])new Class[0]).invoke(socket, new Object[0]);
        }
        catch (Throwable t) {
            return 0;
        }
    }
    
    public static int getTickResolution() {
        if (U.CU <= 0) {
            int min = 100;
            long syncTime = syncTime();
            for (int i = 0; i < 10; ++i) {
                final long syncTime2 = syncTime();
                min = Math.min(min, (int)(syncTime2 - syncTime));
                syncTime = syncTime2;
            }
            U.CU = Math.max(1, min);
        }
        return U.CU;
    }
    
    public static byte[] makeRandom(final int n) {
        final byte[] array = new byte[n];
        int min;
        for (int i = 0; i < array.length; i += min) {
            final byte[] gen = gen();
            min = Math.min(array.length - i, gen.length);
            System.arraycopy(gen, 0, array, i, min);
        }
        return array;
    }
    
    public static double log(final double n) {
        return Math.log(n);
    }
    
    public static double log10(final double n) {
        return log(n) / log(10.0);
    }
    
    public static int max(final int n, final int n2) {
        return (n > n2) ? n : n2;
    }
    
    public static long max(final long n, final long n2) {
        return (n > n2) ? n : n2;
    }
    
    public static int min(final int n, final int n2) {
        return (n < n2) ? n : n2;
    }
    
    public static long min(final long n, final long n2) {
        return (n < n2) ? n : n2;
    }
    
    public static double pow(final double n, final double n2) {
        return Math.pow(n, n2);
    }
    
    public static int abs(final int n) {
        return (n < 0) ? (-n) : n;
    }
    
    public static long abs(final long n) {
        return (n < 0L) ? (-n) : n;
    }
    
    public static int rand(final int n) {
        return (int)(Math.random() * n) % n;
    }
    
    public static int[] resize(final int[] array, final int n) {
        final int[] array2 = new int[n];
        System.arraycopy(array, 0, array2, 0, min(array.length, n));
        return array2;
    }
    
    public static byte[] resize(final byte[] array, final int n) {
        final byte[] array2 = new byte[n];
        System.arraycopy(array, 0, array2, 0, min(array.length, n));
        return array2;
    }
    
    public static long[] resize(final long[] array, final int n) {
        final long[] array2 = new long[n];
        System.arraycopy(array, 0, array2, 0, min(array.length, n));
        return array2;
    }
    
    public static String bps2s(final long n) {
        return bps2s(n, true);
    }
    
    public static String bps2s(final long n, final boolean b) {
        final boolean g_bBytes = G.g_bBytes;
        final long n2 = g_bBytes ? (n / 8L) : n;
        final int n3 = 100;
        long n4 = n2 * n3 / 1000L;
        final boolean b2 = n4 / n3 > 1000L;
        final String s = b2 ? RC(TX("m")) : RC(TX("o"));
        if (b2) {
            n4 /= 1000L;
        }
        final int n5 = (int)(n4 / 100L);
        String s2 = String.valueOf(TX("")) + n5;
        if (n5 < 100) {
            s2 = String.valueOf(s2) + TX("M") + n4 % 100L / 10L;
            if (n5 < 10 && b) {
                s2 = String.valueOf(s2) + n4 % 10L;
            }
        }
        return String.valueOf(s2) + TX("[") + s + (g_bBytes ? RC(TX("xafugjg")) : RC(TX("xqfgjg")));
    }
    
    public static long toSF(final long n, final int n2) {
        final long n3 = (long)Math.pow(10.0, Math.floor(Math.log(n) / Math.log(10.0)) - n2);
        return (n3 > 0L) ? ((n + 5L * n3) / (n3 * 10L) * n3 * 10L) : n;
    }
    
    public static String addCommas(long abs) {
        final StringBuffer sb = new StringBuffer();
        final boolean b = abs < 0L;
        abs = abs(abs);
        for (int n = 0; n == 0 || abs != 0L; abs /= 10L, ++n) {
            if (n > 0 && n % 3 == 0) {
                sb.append(TX("O"));
            }
            sb.append((char)(48L + abs % 10L));
        }
        if (b) {
            sb.append(TX("N"));
        }
        return sb.reverse().toString();
    }
    
    public static String d2s(final double n) {
        return (n != -1.0) ? Util.oneDP(n) : "-";
    }
    
    public static String mapValueToLabel(final String s, final int n) {
        if (s != null) {
            final StringTokenizer stringTokenizer = new StringTokenizer(s, ",");
            while (stringTokenizer.hasMoreTokens()) {
                try {
                    final StringTokenizer stringTokenizer2 = new StringTokenizer(stringTokenizer.nextToken(), "<>=", true);
                    final String nextToken = stringTokenizer2.nextToken();
                    int n2 = 0;
                    boolean b = false;
                    if (!stringTokenizer2.hasMoreTokens()) {
                        return nextToken;
                    }
                    while (stringTokenizer2.hasMoreTokens()) {
                        final String nextToken2 = stringTokenizer2.nextToken();
                        if ("<".equals(nextToken2)) {
                            n2 = -1;
                        }
                        else if (">".equals(nextToken2)) {
                            n2 = 1;
                        }
                        else if ("=".equals(nextToken2)) {
                            b = true;
                        }
                        else {
                            final int int1 = Integer.parseInt(nextToken2);
                            if ((n2 < 0 && n < int1) || (n2 > 0 && n > int1) || (b && n == int1)) {
                                return nextToken;
                            }
                            continue;
                        }
                    }
                }
                catch (Exception ex) {}
            }
        }
        return "";
    }
    
    private static String RC(final String s) {
        return U.TU.RC(s);
    }
    
    public static Color valueToColour(final double n, final long[] array) {
        final int n2 = array.length / 2;
        if (n2 > 0 && n > array[array.length - 2]) {
            return new Color((int)array[array.length - 1]);
        }
        for (int i = 0; i < n2 - 1; ++i) {
            final long n3 = array[i * 2 + 0];
            final long n4 = array[i * 2 + 2];
            if (n >= n3 && n <= n4) {
                return xxx(n3, n4, n, (int)array[i * 2 + 1], (int)array[i * 2 + 3]);
            }
        }
        return Color.white;
    }
    
    private static Color xxx(final double n, final double n2, final double n3, final int n4, final int n5) {
        final double n6 = 1.0 * (n3 - n) / (n2 - n);
        final int n7 = n4 >> 16 & 0xFF;
        final int n8 = n4 >> 8 & 0xFF;
        final int n9 = n4 >> 0 & 0xFF;
        return new Color(n7 + (int)(n6 * ((n5 >> 16 & 0xFF) - n7)), n8 + (int)(n6 * ((n5 >> 8 & 0xFF) - n8)), n9 + (int)(n6 * ((n5 >> 0 & 0xFF) - n9)));
    }
    
    public static long[] parseColourTable(final String s) {
        try {
            if (s != null) {
                final StringTokenizer stringTokenizer = new StringTokenizer(s, TX("O\r\n"));
                final int countTokens = stringTokenizer.countTokens();
                final long[] array = new long[countTokens * 2];
                for (int i = 0; i < countTokens; ++i) {
                    final String nextToken = stringTokenizer.nextToken();
                    final int index = nextToken.indexOf(61);
                    final long long1 = Long.parseLong(nextToken.substring(0, index).trim());
                    final int int1 = Integer.parseInt(nextToken.substring(index + 1).trim(), 16);
                    array[i * 2 + 0] = long1;
                    array[i * 2 + 1] = int1;
                }
                return array;
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public static void drawDot(final Graphics graphics, final int n, final int n2, final Color color) {
        final Color color2 = graphics.getColor();
        graphics.setColor(Color.black);
        graphics.fillRect(n - 3, n2 - 1, 7, 3);
        graphics.fillRect(n - 2, n2 - 2, 5, 5);
        graphics.fillRect(n - 1, n2 - 3, 3, 7);
        graphics.setColor(color);
        graphics.fillRect(n - 2, n2 - 1, 5, 3);
        graphics.fillRect(n - 1, n2 - 2, 3, 5);
        graphics.setColor(color2);
    }
    
    public static boolean isNewerJavaAvailable() {
        try {
            final StringTokenizer stringTokenizer = new StringTokenizer("1.6", ".");
            final StringTokenizer stringTokenizer2 = new StringTokenizer(System.getProperty("java.version"), ".");
            while (stringTokenizer.hasMoreTokens()) {
                final char char1 = stringTokenizer.nextToken().charAt(0);
                final char c = stringTokenizer2.hasMoreTokens() ? stringTokenizer2.nextToken().charAt(0) : '0';
                if (char1 > c) {
                    return true;
                }
                if (char1 < c) {
                    break;
                }
            }
        }
        catch (Throwable t) {}
        return false;
    }
    
    public static String javaInfo(final boolean b) {
        final String systemProperty = getSystemProperty(TX("pydyMdulvkh"), "");
        final String lowerCase = systemProperty.toLowerCase();
        return String.valueOf(b ? systemProperty : ((lowerCase.indexOf(TX("mqwhkgktf")) >= 0) ? TX(".(") : ((lowerCase.indexOf(TX("gel")) >= 0) ? TX("(el") : ""))) + TX("[1ydy[") + getSystemProperty(TX("pydyMduhgqkl"), TX("<"));
    }
    
    public static String getSystemProperty(final String s, final String s2) {
        return System.getProperty(s, s2);
    }
    
    private static long nanoTime() {
        if (U.DU != null) {
            try {
                return (long)U.DU.invoke(null, new Object[0]);
            }
            catch (Throwable t) {}
        }
        return 0L;
    }
    
    public static void sleep(final long n) {
        try {
            if (n > 0L) {
                Thread.sleep(n);
            }
        }
        catch (Exception ex) {}
    }
    
    public static URLConnection setNoCaching(final URLConnection urlConnection) {
        urlConnection.setUseCaches(false);
        urlConnection.setRequestProperty(TX("8ywruN8klfhkn"), TX("lkNwywru"));
        return urlConnection;
    }
    
    public static int getReceiveBufferSize(final DatagramSocket datagramSocket) {
        try {
            return (int)datagramSocket.getClass().getMethod(TX("suf)uwuqdu9ettuh(q`u"), (Class<?>[])new Class[0]).invoke(datagramSocket, new Object[0]);
        }
        catch (Throwable t) {
            return 0;
        }
    }
    
    public static int getSendBufferSize(final DatagramSocket datagramSocket) {
        try {
            return (int)datagramSocket.getClass().getMethod(TX("suf(ulv9ettuh(q`u"), (Class<?>[])new Class[0]).invoke(datagramSocket, new Object[0]);
        }
        catch (Throwable t) {
            return 0;
        }
    }
    
    public static void setReceiveBufferSize(final DatagramSocket datagramSocket, final int n) {
        if (n > getReceiveBufferSize(datagramSocket)) {
            try {
                datagramSocket.getClass().getMethod(TX("guf)uwuqdu9ettuh(q`u"), Integer.TYPE).invoke(datagramSocket, new Integer(n));
            }
            catch (Throwable t) {}
        }
    }
    
    public static void setSendBufferSize(final DatagramSocket datagramSocket, final int n) {
        if (n > getSendBufferSize(datagramSocket)) {
            try {
                datagramSocket.getClass().getMethod(TX("guf(ulv9ettuh(q`u"), Integer.TYPE).invoke(datagramSocket, new Integer(n));
            }
            catch (Throwable t) {}
        }
    }
    
    public static void setReceiveBufferSize(final Socket socket, final int n) {
        if (n > getReceiveBufferSize(socket)) {
            try {
                socket.getClass().getMethod(TX("guf)uwuqdu9ettuh(q`u"), Integer.TYPE).invoke(socket, new Integer(n));
            }
            catch (Throwable t) {}
        }
    }
    
    public static void setSendBufferSize(final Socket socket, final int n) {
        if (n > getSendBufferSize(socket)) {
            try {
                socket.getClass().getMethod(TX("guf(ulv9ettuh(q`u"), Integer.TYPE).invoke(socket, new Integer(n));
            }
            catch (Throwable t) {}
        }
    }
    
    public static byte[] stob(final String s) {
        if (s != null) {
            final byte[] array = new byte[s.length()];
            s.getBytes(0, array.length, array, 0);
            return array;
        }
        return null;
    }
    
    public static String subst(String string, final String s, final String s2) {
        int index;
        for (int n = 0; n < 10 && (index = string.indexOf(s)) >= 0; string = String.valueOf(string.substring(0, index)) + s2 + string.substring(index + s.length()), ++n) {}
        return string;
    }
    
    public static Hashtable stringToHash(final String s, final String s2) {
        return stringToHash(s, s2, null);
    }
    
    public static Hashtable stringToHash(final String s, final String s2, Hashtable hashtable) {
        if (hashtable == null) {
            hashtable = new Hashtable<String, String>();
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(s, s2);
        while (stringTokenizer.hasMoreTokens()) {
            final String nextToken = stringTokenizer.nextToken();
            final int index = nextToken.indexOf("=");
            if (index > 0) {
                hashtable.put(nextToken.substring(0, index), nextToken.substring(index + 1));
            }
        }
        return hashtable;
    }
    
    public static long syncTime() {
        long time;
        while ((time = time()) == time()) {}
        return time;
    }
    
    public static long time() {
        return (U.EU != 0L) ? (U.FU + (nanoTime() - U.EU) / 1000000L) : System.currentTimeMillis();
    }
    
    private static void timeSetup() {
        try {
            Class us;
            if ((us = U.US) == null) {
                try {
                    us = (U.US = Class.forName("java.lang.System"));
                }
                catch (ClassNotFoundException ex) {
                    throw new NoClassDefFoundError(ex.getMessage());
                }
            }
            U.DU = us.getMethod(TX("lylk'qmu"), (Class[])new Class[0]);
        }
        catch (Throwable t) {}
        long time;
        while ((time = time()) == time()) {}
        U.EU = nanoTime();
        U.FU = time;
    }
    
    private static String TX(final String s) {
        final char[] charArray = s.toCharArray();
        for (int i = 0; i < charArray.length; ++i) {
            final char c = charArray[i];
            if (c >= ' ' && c <= '~') {
                charArray[i] = (char)(32 + (6408791 - c) % 95);
            }
        }
        return new String(charArray);
    }
}
