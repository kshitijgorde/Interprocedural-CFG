// 
// Decompiled by Procyon v0.5.30
// 

package inknet;

import java.net.MalformedURLException;
import java.net.URLConnection;
import java.io.IOException;
import java.io.DataInputStream;
import java.net.URLEncoder;
import java.applet.Applet;
import java.net.UnknownHostException;
import java.net.InetAddress;
import java.util.Vector;
import java.net.URL;

public class InkUtil
{
    public static final int INVALID = 0;
    public static final int VALID_COPYRIGHT = 2;
    public static final int VALID_PRIVATE = 4;
    public static final int VALID_COBRAND = 1;
    
    private static final int \u00c4(final URL url, final Vector vector, final String s) {
        int \u00e7 = 0;
        final long longValue = vector.elementAt(vector.size() - 1);
        try {
            final String host = url.getHost();
            InetAddress byName = null;
            try {
                byName = InetAddress.getByName(host);
            }
            catch (UnknownHostException ex3) {}
            catch (SecurityException ex4) {}
            final String lowerCase = host.toLowerCase();
            for (int i = 0; i < vector.size() - 1; ++i) {
                final String s2 = (String)vector.elementAt(i);
                if (byName != null) {
                    final int \u00e72 = \u00c7(byName, s2, s, longValue);
                    if (\u00e72 != 0) {
                        return \u00e72;
                    }
                }
                String substring = lowerCase;
                do {
                    \u00e7 = \u00c7(substring, s2, s, longValue);
                    if (\u00e7 != 0) {
                        return \u00e7;
                    }
                    substring = substring.substring(substring.indexOf(46) + 1);
                } while (substring.indexOf(46) != -1);
            }
        }
        catch (NullPointerException ex) {
            vector.addElement("Exception Occurred: " + ex.toString());
            \u00e7 = 0;
        }
        catch (SecurityException ex2) {
            vector.addElement("Exception Occurred: " + ex2.toString());
            \u00e7 = 0;
        }
        return \u00e7;
    }
    
    private static byte[] \u00c5(final String s) {
        try {
            final byte[] bytes = s.getBytes();
            if (bytes.length != s.length()) {}
            return bytes;
        }
        catch (NoSuchMethodError noSuchMethodError) {
            final byte[] array = new byte[s.length()];
            if (s.length() == 0) {
                return array;
            }
            s.getBytes(0, s.length() - 1, array, 0);
            return array;
        }
    }
    
    private static final int \u00c6(final int n, final int n2, final int n3) {
        final int[] array = { 0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334 };
        int n4 = (n - 1970) * 365 + (n - 1970 + 1) / 4;
        if (n % 4 == 0 && n2 > 2) {
            ++n4;
        }
        return n4 + array[n2 - 1] + n3 - 1;
    }
    
    public static String loadString(final String s) {
        final StringBuffer sb = new StringBuffer(s);
        for (int i = 0; i < sb.length(); ++i) {
            sb.setCharAt(i, (char)(sb.charAt(i) ^ '\u000f'));
        }
        return sb.toString();
    }
    
    public static User getUser(final Applet applet, final String s, final int n) {
        User user = null;
        String parameter = null;
        String parameter2 = null;
        String parameter3 = null;
        int int1 = 80;
        if (applet != null && applet.getAppletContext() != null) {
            String s2 = "inknet.TempUser";
            try {
                parameter2 = applet.getParameter("USER");
                parameter = applet.getParameter("SESSIONID");
                parameter3 = applet.getParameter("GAMENAME");
                final String parameter4 = applet.getParameter("PORT");
                if (parameter4 != null) {
                    try {
                        int1 = Integer.parseInt(parameter4);
                    }
                    catch (NumberFormatException ex2) {
                        int1 = 80;
                    }
                }
                final String parameter5 = applet.getParameter("USERCLASS");
                if (parameter5 != null && parameter5.length() > 0) {
                    s2 = parameter5;
                }
                user = (User)Class.forName(s2).newInstance();
            }
            catch (ClassNotFoundException ex) {
                System.out.println("Class not available " + s2 + " " + ex.toString());
            }
            catch (InstantiationException ex3) {
                System.out.println("Class cannot be created " + s2);
            }
            catch (IllegalAccessException ex4) {
                System.out.println("Class needs public constuctor " + s2);
            }
        }
        if (user == null) {
            user = new TempUser();
        }
        user.init(applet, s, parameter, parameter2, parameter3, int1, n);
        return user;
    }
    
    private static final int \u00c7(final String s, final String s2, final String s3, final long n) {
        if (s2.length() == 0) {
            return 0;
        }
        int n2 = 0;
        switch (s2.charAt(0)) {
            default: {
                if (\u00c8(\u00c5(s), s2, 453001, n)) {
                    n2 = 3;
                    break;
                }
                break;
            }
            case '0': {
                if (\u00c8(\u00c5(s), s2.substring(1), 453001, n)) {
                    n2 = 1;
                    break;
                }
                break;
            }
            case 'A': {
                if (\u00c8(\u00c5(s), s2.substring(1), 453007, n)) {
                    n2 = 3;
                    break;
                }
                break;
            }
            case '1': {
                if (\u00c8(\u00c5(s), s2.substring(1), 53007, n)) {
                    n2 = 7;
                    break;
                }
                break;
            }
            case '2': {
                if (\u00c8(\u00c5(s3 + s), s2.substring(1), 453001, n)) {
                    n2 = 1;
                    break;
                }
                break;
            }
            case '3': {
                if (\u00c8(\u00c5(s3 + s), s2.substring(1), 53007, n)) {
                    n2 = 7;
                    break;
                }
                break;
            }
            case 'B': {
                if (\u00c8(\u00c5(s3 + s), s2.substring(1), 453007, n)) {
                    n2 = 3;
                    break;
                }
                break;
            }
        }
        return n2;
    }
    
    private static final int \u00c7(final InetAddress inetAddress, final String s, final String s2, final long n) {
        if (s.length() == 0) {
            return 0;
        }
        int n2 = 0;
        switch (s.charAt(0)) {
            default: {
                if (\u00c8(inetAddress.getAddress(), s, 456001, n)) {
                    n2 = 3;
                    break;
                }
                break;
            }
            case '0': {
                if (\u00c8(inetAddress.getAddress(), s.substring(1), 456001, n)) {
                    n2 = 1;
                    break;
                }
                break;
            }
            case '1': {
                if (\u00c8(inetAddress.getAddress(), s.substring(1), 53007, n)) {
                    n2 = 7;
                    break;
                }
                break;
            }
            case '2': {
                final byte[] \u00e5 = \u00c5(s2);
                final byte[] address = inetAddress.getAddress();
                final byte[] array = new byte[\u00e5.length + address.length];
                System.arraycopy(\u00e5, 0, array, 0, \u00e5.length);
                System.arraycopy(address, 0, array, \u00e5.length, address.length);
                if (\u00c8(array, s.substring(1), 456001, n)) {
                    n2 = 1;
                    break;
                }
                break;
            }
            case '3': {
                final byte[] \u00e52 = \u00c5(s2);
                final byte[] address2 = inetAddress.getAddress();
                final byte[] array2 = new byte[\u00e52.length + address2.length];
                System.arraycopy(\u00e52, 0, array2, 0, \u00e52.length);
                System.arraycopy(address2, 0, array2, \u00e52.length, address2.length);
                if (\u00c8(array2, s.substring(1), 53007, n)) {
                    n2 = 7;
                    break;
                }
                break;
            }
            case 'A': {
                if (\u00c8(inetAddress.getAddress(), s.substring(1), 453007, n)) {
                    n2 = 3;
                    break;
                }
                break;
            }
            case 'B': {
                final byte[] \u00e53 = \u00c5(s2);
                final byte[] address3 = inetAddress.getAddress();
                final byte[] array3 = new byte[\u00e53.length + address3.length];
                System.arraycopy(\u00e53, 0, array3, 0, \u00e53.length);
                System.arraycopy(address3, 0, array3, \u00e53.length, address3.length);
                if (\u00c8(array3, s.substring(1), 453007, n)) {
                    n2 = 3;
                    break;
                }
                break;
            }
        }
        return n2;
    }
    
    private static final boolean \u00c8(final byte[] array, String substring, int n, final long n2) {
        if (substring.length() < array.length * 4) {
            return false;
        }
        final char char1 = substring.charAt(0);
        if (char1 >= '0' && char1 <= '9') {
            final long n3 = \u00c6(Integer.parseInt(substring.substring(4, 8)), Integer.parseInt(substring.substring(0, 2)), Integer.parseInt(substring.substring(2, 4)));
            if (n2 > n3 * 86400000L) {
                System.out.println(loadString("Cflja|j/Jw\u007ff}jk5/") + n3);
                return false;
            }
            n -= (int)(n3 % 10000L);
            substring = substring.substring(8);
        }
        char c = (char)n;
        for (int i = 0; i < array.length; ++i) {
            char c2 = '\0';
            char c3 = '\u0001';
            int n4 = 0;
            do {
                c2 += (char)((substring.charAt(i * 4 + n4) - 'a') * c3);
                c3 *= '\u001a';
            } while (++n4 < 4);
            final char c4 = (char)(c % c2);
            char c5 = (char)array[i];
            if (c5 < '\0') {
                c5 += '\u00ff';
            }
            if (c4 != c5) {
                return false;
            }
            c -= c4;
            if (c < '\0') {
                c = (char)n;
            }
        }
        return true;
    }
    
    private static final Vector \u00c9(final Applet applet, final URL url) {
        long date = 0L;
        final Vector vector = new Vector<String>();
        String parameter = null;
        if (applet != null && applet.getAppletContext() != null) {
            int n = 0;
            String parameter2;
            do {
                parameter2 = applet.getParameter("LICENSE" + n);
                if (parameter2 != null) {
                    vector.addElement(parameter2);
                }
                ++n;
            } while (parameter2 != null);
            parameter = applet.getParameter("MONITOR");
        }
        if (parameter == null) {
            parameter = "license.txt";
        }
        if (vector.size() == 0) {
            boolean b = false;
            URLEncoder.encode(url.getHost());
            for (int n2 = 0; n2 < 3 && !b; ++n2) {
                try {
                    final URLConnection openConnection = new URL(applet.getCodeBase(), parameter).openConnection();
                    final DataInputStream dataInputStream = new DataInputStream(openConnection.getInputStream());
                    String line;
                    while ((line = dataInputStream.readLine()) != null) {
                        if (!line.startsWith("#")) {
                            vector.addElement(line);
                        }
                    }
                    b = true;
                    date = openConnection.getDate();
                    System.out.println("Date Value " + date);
                    dataInputStream.close();
                }
                catch (IOException ex) {
                    vector.addElement("Error getting license.txt, " + ex.toString());
                }
            }
        }
        vector.addElement((String)new Long(date));
        return vector;
    }
    
    public static int getGameCanvasType(final Applet applet, final String s) {
        final URL documentBase = applet.getDocumentBase();
        final Vector \u00e9 = \u00c9(applet, documentBase);
        final int n = \u00c4(documentBase, \u00e9, s) & \u00c4(applet.getCodeBase(), \u00e9, s);
        if (n == 0 && applet != null && applet.getAppletContext() != null) {
            applet.showStatus(loadString("Zacflja|jk/|`i{xn}j!/l`a{nl{/|z\u007f\u007f`}{Ohnbjg`z|j!l`b"));
            try {
                if (\u00e9 != null && \u00e9.size() > 0) {
                    final String string = \u00e9.elementAt(0).toString();
                    if (string.startsWith("http")) {
                        applet.getAppletContext().showDocument(new URL(string), "_new");
                    }
                }
            }
            catch (MalformedURLException ex) {}
        }
        return n;
    }
}
