// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.graphics;

import java.util.Enumeration;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.StringTokenizer;
import netcharts.util.NFDebug;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import netcharts.util.NFUtil;
import java.net.URL;
import netcharts.util.NFVersion;
import java.util.Date;
import java.net.InetAddress;
import netcharts.util.NFNetworkAccess;
import java.applet.Applet;
import java.util.Hashtable;
import java.util.Vector;

public class NFLicense2
{
    public static final int UNLICENSED = 0;
    public static final int EVALUATION = 1;
    public static final int REGISTERED = 2;
    protected static final String licenseFile = "NFLicense.dat";
    protected static final boolean ALWAYSVALID = false;
    protected static String registration;
    protected static Vector licenseKeys;
    protected static Hashtable cache;
    protected static String hostID;
    static NFLicenseEntry a;
    
    public static NFLicenseEntry getLicenseEntry() {
        return NFLicense2.a;
    }
    
    public static boolean isLicenseValid(final String s, final Applet applet) {
        return isLicenseValid(s, applet, null);
    }
    
    public static boolean isLicenseValid(final String s, final Applet applet, final String[] array) {
        int n = 0;
        b("Checking for valid license ...");
        b("target = " + s);
        boolean b;
        try {
            b = (applet.getCodeBase() == null);
        }
        catch (Exception ex2) {
            b = true;
        }
        if (NFLicense2.hostID == null) {
            try {
                final InetAddress localHost = NFNetworkAccess.getLocalHost();
                b("getLocalHost() = " + localHost);
                NFLicense2.hostID = localHost.toString();
            }
            catch (Exception ex) {
                b(ex.toString());
                NFLicense2.hostID = "ERROR";
            }
        }
        if (NFLicense2.licenseKeys != null) {
            b("oem mode.");
            n = checkVector(NFLicense2.licenseKeys, NFLicense2.hostID, s);
        }
        if (n == 0) {
            if (b) {
                b("standalone mode.");
                n = a(s, array);
            }
            else {
                b("applet mode.");
                n = a(applet, s, array);
            }
        }
        if (n != 0) {
            b("License Validation Succeeded.");
        }
        else {
            b(applet, "License Validation Failed.");
        }
        return n != 0;
    }
    
    public static boolean isLicenseEvaluation() {
        return NFLicense2.a != null && NFLicense2.a.get("EXPIRATION") != null;
    }
    
    public static boolean getShowBanner() {
        if (NFLicense2.a.get("BANNER") == null) {
            b("didn't find BANNER keyword");
        }
        else {
            b("BANNER = " + NFLicense2.a.get("BANNER"));
        }
        return NFLicense2.a != null && NFLicense2.a.get("EXPIRATION") != null && (NFLicense2.a.get("BANNER") == null || NFLicense2.a.get("BANNER").equalsIgnoreCase("YES"));
    }
    
    public static Date getExpirationDate() {
        if (NFLicense2.a == null) {
            return null;
        }
        return NFLicense2.a.getExpiration();
    }
    
    public static String getRegistrationID() {
        if (NFLicense2.a == null) {
            return null;
        }
        return NFLicense2.a.getRegistration();
    }
    
    public static boolean isNodeLocked() {
        return NFLicense2.a != null && NFLicense2.a.get("HOST") != null;
    }
    
    public static void register(final String registration) {
        NFLicense2.registration = registration;
    }
    
    public static void setKey(final String s) {
        b("LicenseKey = " + s);
        if (NFLicense2.licenseKeys == null) {
            NFLicense2.licenseKeys = new Vector();
        }
        NFLicense2.licenseKeys.addElement(new String(s));
    }
    
    public static void setKey(final Vector licenseKeys) {
        NFLicense2.licenseKeys = licenseKeys;
    }
    
    public static int check(final Applet applet, final Vector vector, final String s) {
        return check(applet, vector, s, null);
    }
    
    public static int check(final Applet applet, Vector licenseKeys, final String s, final String[] array) {
        String hostID = NFLicense2.registration;
        URL url = null;
        b("Checking for valid license ...");
        final String string = "NetCharts" + NFVersion.version;
        if (NFLicense2.licenseKeys != null) {
            licenseKeys = NFLicense2.licenseKeys;
        }
        if (licenseKeys != null && licenseKeys.size() == 0) {
            licenseKeys = null;
        }
        if (licenseKeys == null && s != null) {
            b("LicenseURL = " + s);
            try {
                url = new URL(applet.getDocumentBase(), s);
            }
            catch (Exception ex) {
                b(ex.toString());
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
                    final InetAddress localHost = NFNetworkAccess.getLocalHost();
                    b("getLocalHost() = " + localHost);
                    hostID = localHost.toString();
                }
                catch (Exception ex2) {
                    b(ex2.toString());
                    hostID = "ERROR";
                }
            }
            else {
                try {
                    final URL codeBase = applet.getCodeBase();
                    b("getCodeBase() = <" + codeBase + ">");
                    final String host = codeBase.getHost();
                    b("getHost() = <" + host + ">");
                    InetAddress inetAddress;
                    if (host.length() == 0 || host.equals("localhost")) {
                        inetAddress = NFNetworkAccess.getLocalHost();
                        b("getLocalHost() = " + inetAddress);
                    }
                    else {
                        inetAddress = NFNetworkAccess.getIPByName(host);
                        b("getByName() = " + inetAddress);
                    }
                    hostID = inetAddress.toString();
                }
                catch (Exception ex3) {
                    b(ex3.toString());
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
                final byte[] array2 = new byte[64];
                final DatagramPacket datagramPacket = new DatagramPacket(array2, array2.length, datagramSocket.getLocalAddress(), datagramSocket.getLocalPort());
                datagramSocket.send(datagramPacket);
                datagramSocket.receive(datagramPacket);
                final InetAddress address = datagramPacket.getAddress();
                b("socket addr = " + address);
                hostID = address.getHostAddress();
            }
            catch (Exception ex6) {}
        }
        if (NFLicense2.registration == null) {
            b("hostID = <" + hostID + ">");
        }
        int n;
        try {
            if (hostID.equals("ERROR")) {
                n = 2;
            }
            else {
                n = 0;
                if (licenseKeys != null) {
                    n = checkVector(licenseKeys, hostID, string);
                }
                if (n == 0 && url != null) {
                    n = checkURL(url, hostID, string, array);
                }
                if (n == 0) {
                    if (b) {
                        n = checkClassPath(hostID, string, array);
                    }
                    else if (hostID.equals("127.0.0.1")) {
                        n = 2;
                    }
                    else {
                        n = checkCodeBase(applet, hostID, string, array);
                    }
                }
            }
        }
        catch (Exception ex4) {
            b(ex4.toString());
            n = 2;
        }
        if (n != 0) {
            b("License Validation Succeeded.");
        }
        else {
            b(applet, "License Validation Failed.");
        }
        b("License type = " + n);
        return n;
    }
    
    private static int a(final Applet applet, final String s) {
        return a(applet, s, null);
    }
    
    private static int a(final Applet applet, final String s, final String[] array) {
        try {
            String s2 = applet.getCodeBase().toString();
            int n = s2.lastIndexOf(92);
            if ("NFLicense.dat" == null) {
                b("licenseFile is null");
            }
            else {
                b("licenseFile = NFLicense.dat");
            }
            if (n < 0) {
                n = s2.lastIndexOf(47);
            }
            if (n != -1) {
                s2 = s2.substring(0, n + 1) + "NFLicense.dat";
            }
            b("License URL = " + s2);
            return a(new URL(s2), applet, s, array);
        }
        catch (Exception ex) {
            b(ex.toString());
            return 0;
        }
    }
    
    private static int a(final String s) {
        return a(s, (String[])null);
    }
    
    private static int a(final String s, final String[] array) {
        if (NFUtil.getJDKVersion() >= 1.1) {
            try {
                final int checkStream = checkStream("resource", NFNetworkAccess.getResourceAsStream(Class.forName("netcharts.graphics.NFLicense2"), "/NFLicense.dat"), a((Applet)null), s, array);
                if (checkStream == 2 || checkStream == 1) {
                    return checkStream;
                }
            }
            catch (Exception ex) {
                b(ex.getMessage());
            }
        }
        b("Checking $CLASSPATH ...");
        String property;
        String property2;
        String property3;
        try {
            property = System.getProperty("java.class.path");
            b("class path = <" + property + ">");
            property2 = System.getProperty("path.separator");
            b("pathsep = <" + property2 + ">");
            property3 = System.getProperty("file.separator");
            b("filesep = <" + property3 + ">");
            b("userdir = <" + System.getProperty("user.dir") + ">");
        }
        catch (Exception ex3) {
            NFDebug.print("License: *** Unable to access system properties ***");
            return 0;
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(property, property2);
        int countTokens = stringTokenizer.countTokens();
        while (stringTokenizer.hasMoreTokens() && countTokens-- > 0) {
            final String nextToken = stringTokenizer.nextToken();
            try {
                String s2;
                if (nextToken.indexOf(".jar") != -1 || nextToken.indexOf(".zip") != -1) {
                    s2 = nextToken + "!/" + "NFLicense.dat";
                }
                else {
                    s2 = nextToken + property3 + "NFLicense.dat";
                }
                b("Checking File " + s2);
                final int a = a(NFUtil.getFileURL(s2), null, s);
                if (a == 2 || a == 1) {
                    return a;
                }
                continue;
            }
            catch (Exception ex2) {
                b(ex2.getMessage());
            }
        }
        return 0;
    }
    
    protected static int checkClassPath(final String s, final String s2) {
        return checkClassPath(s, s2, null);
    }
    
    protected static int checkClassPath(final String s, final String s2, final String[] array) {
        if (NFUtil.getJDKVersion() >= 1.1) {
            try {
                final int checkStream = checkStream("resource", NFNetworkAccess.getResourceAsStream(Class.forName("netcharts.graphics.NFLicense2"), "/NFLicense.dat"), s, s2, array);
                if (checkStream == 2 || checkStream == 1) {
                    return checkStream;
                }
            }
            catch (Exception ex) {
                b(ex.getMessage());
            }
        }
        b("Checking $CLASSPATH ...");
        String property;
        String property2;
        String property3;
        try {
            property = System.getProperty("java.class.path");
            b("class path = <" + property + ">");
            property2 = System.getProperty("path.separator");
            b("pathsep = <" + property2 + ">");
            property3 = System.getProperty("file.separator");
            b("filesep = <" + property3 + ">");
            b("userdir = <" + System.getProperty("user.dir") + ">");
        }
        catch (Exception ex3) {
            NFDebug.print("NetCharts: *** Unable to access system properties ***");
            return 0;
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(property, property2);
        int countTokens = stringTokenizer.countTokens();
        while (stringTokenizer.hasMoreTokens() && countTokens-- > 0) {
            final String nextToken = stringTokenizer.nextToken();
            try {
                String s3;
                if (nextToken.indexOf(".jar") != -1 || nextToken.indexOf(".zip") != -1) {
                    s3 = nextToken + "!/" + "NFLicense.dat";
                }
                else {
                    s3 = nextToken + property3 + "NFLicense.dat";
                }
                b("Checking File " + s3);
                final int checkURL = checkURL(NFUtil.getFileURL(s3), s, s2);
                if (checkURL == 2 || checkURL == 1) {
                    return checkURL;
                }
                continue;
            }
            catch (Exception ex2) {
                b(ex2.getMessage());
            }
        }
        return 0;
    }
    
    protected static int checkCodeBase(final Applet applet, final String s, final String s2) {
        return checkCodeBase(applet, s, s2, null);
    }
    
    protected static int checkCodeBase(final Applet applet, final String s, final String s2, final String[] array) {
        try {
            String s3 = applet.getCodeBase().toString();
            int n = s3.lastIndexOf(92);
            if (n < 0) {
                n = s3.lastIndexOf(47);
            }
            if (n != -1) {
                s3 = s3.substring(0, n + 1) + "NFLicense.dat";
            }
            b("License URL = " + s3);
            URL url;
            try {
                url = new URL(s3);
            }
            catch (Exception ex) {
                b("EXCEPTION thrown while making URL for checkURL: " + ex.getMessage());
                url = null;
            }
            return a(url, applet, s2, array);
        }
        catch (Exception ex2) {
            b(ex2.getMessage());
            return 0;
        }
    }
    
    private static int a(final URL url, final Applet applet, final String s) {
        return a(url, applet, s, null);
    }
    
    private static int a(final URL url, final Applet applet, final String s, final String[] array) {
        return checkURL(url, a(applet), s, array);
    }
    
    protected static int checkURL(final URL url, final String s, final String s2) {
        return checkURL(url, s, s2, null);
    }
    
    protected static int checkURL(final URL url, final String s, final String s2, final String[] array) {
        return checkStream(url.toString(), NFNetworkAccess.getURLAsStream(url), s, s2, array);
    }
    
    protected static int checkStream(final String s, final InputStream inputStream, final String s2, final String s3) {
        return checkStream(s, inputStream, s2, s3, null);
    }
    
    protected static int checkStream(final String s, final InputStream inputStream, final String s2, final String s3, final String[] array) {
        synchronized (NFLicense2.cache) {
            String s4;
            if (s == null) {
                s4 = "*|" + s3;
            }
            else {
                s4 = s + "|" + s3;
            }
            b("looking for: " + s4);
            a();
            final Integer n = NFLicense2.cache.get(s4);
            if (n != null) {
                b("Using cached result");
                return n;
            }
            final Integer n2 = NFLicense2.cache.get("*|" + s3);
            if (n2 != null) {
                b("Using cached result");
                return n2;
            }
            b("hostid = " + s2);
            b("checking license file at: " + s);
            InputStream inputStream2 = null;
            boolean b = false;
            try {
                inputStream2 = new BufferedInputStream(inputStream);
                b("Opened URL Successfully");
                NFLicense2.a = NFParseStream.parseStream(inputStream2, s3, s2, null);
                b("Parsed URL Successfully");
                if (isLicenseEvaluation()) {
                    if (new Date(System.currentTimeMillis()).before(getExpirationDate())) {
                        b("Evaluation License has not Expired");
                        b = true;
                    }
                    else {
                        b("Evaluation License has Expired");
                    }
                }
                else {
                    b("Registered License");
                    b = true;
                }
            }
            catch (Exception ex) {
                b(ex.toString());
                b = false;
            }
            if (inputStream2 != null) {
                try {
                    inputStream2.close();
                }
                catch (Exception ex2) {}
            }
            if (b) {
                if (isNodeLocked()) {
                    final String field = NFLicense2.a.getField("HOST");
                    b("node locked to: " + field);
                    b("this host is: " + s2);
                    if (field.endsWith(".0.0")) {
                        if (!field.substring(0, field.lastIndexOf(".0.0")).equals(s2.substring(0, s2.indexOf(".", s2.indexOf(".") + 1)))) {
                            return 0;
                        }
                    }
                    else if (field.endsWith(".0")) {
                        if (!field.substring(0, field.lastIndexOf(".0")).equals(s2.substring(0, s2.lastIndexOf(".")))) {
                            return 0;
                        }
                    }
                    else if (!s2.equals(NFLicense2.a.getField("HOST"))) {
                        return 0;
                    }
                }
                NFLicense2.cache.put(s4, new Integer(isLicenseEvaluation() ? 1 : 2));
                final String s5 = "NetCharts";
                final String string = "" + (char)(Math.min(10, 8) * Math.max(10, 8)) + String.valueOf(s5.charAt(6)) + (char)(s5.charAt(6) - '\u0003');
                if (!s3.equalsIgnoreCase(s5 + NFVersion.version)) {
                    a(s, s5);
                    if (!s3.equalsIgnoreCase(s5 + string + NFVersion.version)) {
                        a(s, s5 + string);
                    }
                    for (int n3 = 0; array != null && n3 < array.length; ++n3) {
                        if (!s3.equalsIgnoreCase(array[n3] + NFVersion.version)) {
                            a(s, array[n3]);
                        }
                    }
                }
                a("*", s5);
                if (!s3.equalsIgnoreCase(s5 + string + NFVersion.version)) {
                    a("*", s5 + string);
                }
                for (int n4 = 0; array != null && n4 < array.length; ++n4) {
                    if (!s3.equalsIgnoreCase(array[n4] + NFVersion.version)) {
                        a("*", array[n4]);
                    }
                }
            }
            if (b) {
                return isLicenseEvaluation() ? 1 : 2;
            }
            return 0;
        }
    }
    
    private static void a(final String s, final String s2) {
        NFLicense2.cache.put(s + "|" + s2 + NFVersion.version, new Integer(2));
    }
    
    protected static int checkVector(final Vector vector, final String s, final String s2) {
        b("Checking License Vector");
        final StringBuffer sb = new StringBuffer();
        final Enumeration<String> elements = vector.elements();
        while (elements.hasMoreElements()) {
            final String s3 = elements.nextElement();
            b("Key = " + s3);
            if (s3.length() < 10 || !s3.substring(0, 9).equals("NetCharts")) {
                sb.append(s2 + " ");
            }
            sb.append(s3 + "\n");
        }
        boolean b = false;
        try {
            NFLicense2.a = NFParseStream.parseStream(sb, s2, s, NFLicense2.registration);
            b("Parsed URL Successfully");
            if (isLicenseEvaluation()) {
                if (new Date(System.currentTimeMillis()).before(getExpirationDate())) {
                    b("Evaluation License has not Expired");
                    b = true;
                }
                else {
                    b("Evaluation License has Expired");
                }
            }
            else {
                b("Registered License");
                b = true;
            }
        }
        catch (Exception ex) {
            b(ex.toString());
            b = false;
        }
        if (b && isNodeLocked()) {
            final String field = NFLicense2.a.getField("HOST");
            b("node locked to: " + field);
            b("this host is: " + s);
            if (field.endsWith(".0.0")) {
                if (!field.substring(0, field.lastIndexOf(".0.0")).equals(s.substring(0, s.indexOf(".", s.indexOf(".") + 1)))) {
                    return 0;
                }
            }
            else if (field.endsWith(".0")) {
                if (!field.substring(0, field.lastIndexOf(".0")).equals(s.substring(0, s.lastIndexOf(".")))) {
                    return 0;
                }
            }
            else if (!s.equals(NFLicense2.a.getField("HOST"))) {
                return 0;
            }
        }
        if (b) {
            return isLicenseEvaluation() ? 1 : 2;
        }
        return 0;
    }
    
    private static String a(final Applet applet) {
        String hostID = NFLicense2.registration;
        boolean b;
        try {
            b = (applet.getAppletContext() == null);
        }
        catch (Exception ex3) {
            b = true;
        }
        if (hostID == null && NFLicense.hostID != null) {
            hostID = NFLicense.hostID;
        }
        if (hostID == null) {
            if (b) {
                try {
                    final InetAddress localHost = NFNetworkAccess.getLocalHost();
                    b("getLocalHost() = " + localHost);
                    hostID = localHost.toString();
                }
                catch (Exception ex) {
                    b(ex.toString());
                    hostID = "ERROR";
                }
            }
            else {
                try {
                    final URL codeBase = applet.getCodeBase();
                    b("getCodeBase() = <" + codeBase + ">");
                    final String host = codeBase.getHost();
                    b("getHost() = <" + host + ">");
                    InetAddress inetAddress;
                    if (host.length() == 0 || host.equals("localhost")) {
                        inetAddress = NFNetworkAccess.getLocalHost();
                        b("getLocalHost() = " + inetAddress);
                    }
                    else {
                        inetAddress = InetAddress.getByName(host);
                        b("getByName() = " + inetAddress);
                    }
                    hostID = inetAddress.toString();
                }
                catch (Exception ex2) {
                    b(ex2.toString());
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
                b("socket addr = " + address);
                hostID = address.getHostAddress();
            }
            catch (Exception ex4) {}
        }
        return hostID;
    }
    
    private static void b(final String s) {
        NFDebug.print(1L, "License: " + s);
    }
    
    private static void b(final Applet applet, final String s) {
        try {
            applet.showStatus(s);
            b(s);
        }
        catch (Exception ex) {
            NFDebug.print("License: " + s);
        }
    }
    
    private static void a() {
        final Enumeration<String> keys = NFLicense2.cache.keys();
        while (keys.hasMoreElements()) {
            final String s = keys.nextElement();
            b("cache:\t key = " + s + "\t value = " + NFLicense2.cache.get(s));
        }
    }
    
    public static void main(final String[] array) throws Exception {
        NFDebug.set(1L);
        if (array.length < 1) {
            System.out.println("usage: java netcharts.graphics.NFLicense2 <product>");
        }
        try {
            isLicenseValid(array[0], null);
        }
        catch (Exception ex) {
            System.out.println("Invalid License file: " + ex);
        }
        b("isLicenseEvaluation = " + isLicenseEvaluation());
        final Date expirationDate = getExpirationDate();
        if (expirationDate == null) {
            b("***Registered Version***");
        }
        else {
            b("Expiration Date: " + expirationDate);
        }
        final String registrationID = getRegistrationID();
        if (registrationID == null) {
            b("***Evaluation Version***");
        }
        else {
            b("Registration ID: " + registrationID);
        }
        if (getShowBanner()) {
            b("Show Banner");
        }
        else {
            b("Don't show Banner");
        }
        throw new Exception();
    }
    
    static {
        NFLicense2.registration = null;
        NFLicense2.licenseKeys = null;
        NFLicense2.cache = new Hashtable();
        NFLicense2.hostID = null;
        NFLicense2.a = null;
    }
}
