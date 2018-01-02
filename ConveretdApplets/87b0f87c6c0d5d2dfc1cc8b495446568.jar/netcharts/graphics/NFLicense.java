// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.graphics;

import java.io.InputStream;
import java.io.BufferedInputStream;
import java.util.Enumeration;
import java.util.StringTokenizer;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import netcharts.util.NFUtil;
import java.net.InetAddress;
import java.net.URL;
import netcharts.util.NFVersion;
import java.applet.Applet;
import netcharts.util.NFDebug;
import java.util.Hashtable;
import java.util.Vector;

public class NFLicense
{
    protected static final String licenseFile = "NFLicense.dat";
    protected static final boolean ALWAYSVALID = false;
    protected static String registration;
    protected static Vector licenseKeys;
    protected static Hashtable cache;
    protected static String hostID;
    
    private static void a(final String s) {
        NFDebug.print(1L, "NetCharts: " + s);
    }
    
    private static void a(final Applet applet, final String s) {
        try {
            applet.showStatus(s);
            a(s);
        }
        catch (Exception ex) {
            NFDebug.print("NetCharts: " + s);
        }
    }
    
    public static void register(final String registration) {
        NFLicense.registration = registration;
    }
    
    public static void setKey(final String s) {
        a("LicenseKey = " + s);
        (NFLicense.licenseKeys = new Vector()).addElement(new String(s));
    }
    
    public static void setKey(final Vector licenseKeys) {
        NFLicense.licenseKeys = licenseKeys;
    }
    
    public static boolean check(final Applet applet) {
        return check(applet, null, null);
    }
    
    public static boolean check(final Applet applet, final String s) {
        return check(applet, null, s);
    }
    
    public static boolean check(final Applet applet, final Vector vector) {
        return check(applet, vector, null);
    }
    
    public static boolean check(final Applet applet, Vector licenseKeys, final String s) {
        String hostID = NFLicense.registration;
        URL url = null;
        a("Checking for valid license ...");
        final String string = "NetCharts" + NFVersion.version;
        if (NFLicense.licenseKeys != null) {
            licenseKeys = NFLicense.licenseKeys;
        }
        if (licenseKeys != null && licenseKeys.size() == 0) {
            licenseKeys = null;
        }
        if (licenseKeys == null && s != null) {
            a("LicenseURL = " + s);
            try {
                url = new URL(applet.getDocumentBase(), s);
            }
            catch (Exception ex) {
                a(ex.toString());
            }
        }
        boolean b;
        try {
            b = (applet.getCodeBase() == null);
        }
        catch (Exception ex5) {
            b = true;
        }
        if (hostID == null && NFLicense.hostID != null) {
            hostID = NFLicense.hostID;
        }
        if (hostID == null) {
            if (b) {
                try {
                    final InetAddress localHost = InetAddress.getLocalHost();
                    a("getLocalHost() = " + localHost);
                    hostID = localHost.toString();
                }
                catch (Exception ex2) {
                    a(ex2.toString());
                    hostID = "ERROR";
                }
            }
            else {
                try {
                    final URL codeBase = applet.getCodeBase();
                    a("getCodeBase() = <" + codeBase + ">");
                    final String host = codeBase.getHost();
                    a("getHost() = <" + host + ">");
                    InetAddress inetAddress;
                    if (host.length() == 0 || host.equals("localhost")) {
                        inetAddress = InetAddress.getLocalHost();
                        a("getLocalHost() = " + inetAddress);
                    }
                    else {
                        inetAddress = InetAddress.getByName(host);
                        a("getByName() = " + inetAddress);
                    }
                    hostID = inetAddress.toString();
                }
                catch (Exception ex3) {
                    a(ex3.toString());
                    hostID = "ERROR";
                }
            }
            NFLicense.hostID = hostID;
        }
        final int index = hostID.indexOf(47);
        if (index != -1) {
            hostID = hostID.substring(index + 1);
        }
        if (hostID.equals("127.0.0.1") && NFUtil.getJDKVersion() > 1.0) {
            try {
                final DatagramSocket datagramSocket = new DatagramSocket();
                final byte[] array = new byte[64];
                final DatagramPacket datagramPacket = new DatagramPacket(array, array.length, datagramSocket.getLocalAddress(), datagramSocket.getLocalPort());
                datagramSocket.send(datagramPacket);
                datagramSocket.receive(datagramPacket);
                final InetAddress address = datagramPacket.getAddress();
                a("socket addr = " + address);
                hostID = address.getHostAddress();
            }
            catch (Exception ex6) {}
        }
        if (NFLicense.registration == null) {
            a("hostID = <" + hostID + ">");
        }
        boolean b2;
        try {
            if (hostID.equals("ERROR")) {
                b2 = true;
            }
            else if (licenseKeys != null) {
                b2 = a(licenseKeys, hostID, string);
            }
            else if (url != null) {
                b2 = a(url, hostID, string);
            }
            else if (b) {
                b2 = a(hostID, string);
            }
            else {
                b2 = (hostID.equals("127.0.0.1") || a(applet, hostID, string));
            }
        }
        catch (Exception ex4) {
            a(ex4.toString());
            b2 = true;
        }
        if (b2) {
            a("License Validation Succeeded.");
        }
        else {
            a(applet, "License Validation Failed.");
        }
        return b2;
    }
    
    private static boolean a(final Applet applet, final String s, final String s2) {
        try {
            String s3 = applet.getCodeBase().toString();
            int n = s3.lastIndexOf(92);
            if (n < 0) {
                n = s3.lastIndexOf(47);
            }
            if (n != -1) {
                s3 = s3.substring(0, n + 1) + "NFLicense.dat";
            }
            a("License URL = " + s3);
            return a(new URL(s3), s, s2);
        }
        catch (Exception ex) {
            a(ex.getMessage());
            return false;
        }
    }
    
    private static boolean a(final String s, final String s2) {
        a("Checking $CLASSPATH ...");
        String property;
        String property2;
        String property3;
        try {
            property = System.getProperty("java.class.path");
            a("class path = <" + property + ">");
            property2 = System.getProperty("path.separator");
            a("pathsep = <" + property2 + ">");
            property3 = System.getProperty("file.separator");
            a("filesep = <" + property3 + ">");
            a("userdir = <" + System.getProperty("user.dir") + ">");
        }
        catch (Exception ex2) {
            NFDebug.print("NetCharts: *** Unable to access system properties ***");
            return true;
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(property, property2);
        int countTokens = stringTokenizer.countTokens();
        while (stringTokenizer.hasMoreTokens() && countTokens-- > 0) {
            final String nextToken = stringTokenizer.nextToken();
            try {
                a("Checking File " + nextToken + property3 + "NFLicense.dat");
                if (a(NFUtil.getFileURL(nextToken + property3 + "NFLicense.dat"), s, s2)) {
                    return true;
                }
                continue;
            }
            catch (Exception ex) {
                a(ex.getMessage());
            }
        }
        return false;
    }
    
    private static boolean a(final Vector vector, final String s, final String s2) {
        a("Checking License Vector");
        final StringBuffer sb = new StringBuffer();
        final Enumeration<String> elements = vector.elements();
        while (elements.hasMoreElements()) {
            final String s3 = elements.nextElement();
            a("Key = " + s3);
            if (s3.length() < 10 || !s3.substring(0, 9).equals("NetCharts")) {
                sb.append(s2 + " ");
            }
            sb.append(s3 + "\n");
        }
        try {
            NFParseStream.parseStream(sb, s2, s, NFLicense.registration);
            return true;
        }
        catch (Exception ex) {
            a(ex.getMessage());
            return false;
        }
    }
    
    private static boolean a(final URL url, final String s, final String s2) {
        synchronized (NFLicense.cache) {
            final String string = url.toString() + "|" + s;
            final String s3 = NFLicense.cache.get(string);
            if (s3 != null) {
                a("Using cached result");
                return s3.equals("true");
            }
            InputStream inputStream = null;
            boolean b;
            try {
                inputStream = new BufferedInputStream(url.openStream());
                a("Opened URL Successfully");
                NFParseStream.parseStream(inputStream, s2, s, NFLicense.registration);
                a("Parsed URL Successfully");
                b = true;
            }
            catch (Exception ex) {
                a(ex.toString());
                b = false;
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                }
                catch (Exception ex2) {}
            }
            if (b) {
                NFLicense.cache.put(string, "true");
            }
            return b;
        }
    }
    
    static {
        NFLicense.registration = null;
        NFLicense.licenseKeys = null;
        NFLicense.cache = new Hashtable();
        NFLicense.hostID = null;
    }
}
