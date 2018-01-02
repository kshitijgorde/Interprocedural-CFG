// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.util;

import java.util.zip.ZipEntry;
import java.util.Date;
import java.net.ProtocolException;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.File;
import netcharts.util.zip.ZipURLConnection;
import java.util.StringTokenizer;
import java.util.Enumeration;
import java.net.URL;
import java.applet.Applet;
import java.util.Hashtable;

public class NFFile
{
    public static String httpFileSep;
    public static String localFileSep;
    private static Hashtable a;
    private static NFContext b;
    
    public static void debug(final String s) {
        if (!NFDebug.enabled(64L)) {
            return;
        }
        NFDebug.print("NFFile: " + s);
    }
    
    public static void setContext(final NFContext b) {
        NFFile.b = b;
        setFileBase(b.getDocumentBase(), b.getCodeBase());
    }
    
    public static NFContext getContext() {
        return NFFile.b;
    }
    
    public static void setFileBase(final Applet applet) {
        NFFile.b.setApplet(applet);
        URL documentBase = null;
        URL codeBase = null;
        try {
            documentBase = applet.getDocumentBase();
        }
        catch (Exception ex) {}
        try {
            codeBase = applet.getCodeBase();
        }
        catch (Exception ex2) {}
        setFileBase(documentBase, codeBase);
    }
    
    public static void setFileBase(final URL url, final URL url2) {
        NFFile.a.remove("$CODEBASE");
        NFFile.a.remove("$DOCBASE");
        if (url != null) {
            String s = url.toString();
            final int lastIndex = s.lastIndexOf(NFFile.httpFileSep);
            if (lastIndex != -1) {
                s = s.substring(0, lastIndex);
            }
            NFFile.a.put("$DOCBASE", s);
        }
        if (url2 != null) {
            String s2 = url2.toString();
            final int lastIndex2 = s2.lastIndexOf(NFFile.httpFileSep);
            if (lastIndex2 != -1) {
                s2 = s2.substring(0, lastIndex2);
            }
            NFFile.a.put("$CODEBASE", s2);
        }
        a();
    }
    
    public static Applet getApplet() {
        return NFFile.b.getApplet();
    }
    
    private static void a() {
        if (NFFile.a.get("$DOCBASE") == null) {
            NFFile.a.put("$DOCBASE", ".");
        }
        if (NFFile.a.get("$CODEBASE") == null) {
            String chasePath;
            try {
                chasePath = chasePath(System.getProperty("java.class.path"), "netcharts/");
                if (chasePath == null) {
                    chasePath = ".";
                }
            }
            catch (Exception ex) {
                chasePath = ".";
            }
            NFFile.a.put("$CODEBASE", chasePath);
        }
        b();
    }
    
    private static void b() {
        final String string = NFFile.a.get("$CODEBASE") + NFFile.httpFileSep + "netcharts";
        NFFile.a.put("$NETCHARTS", string);
        NFFile.a.put("$IMAGES", string + NFFile.httpFileSep + "images");
        NFFile.a.put("$SYMBOLS", string + NFFile.httpFileSep + "symbols");
        NFFile.a.put("$PATTERNS", string + NFFile.httpFileSep + "patterns");
        NFFile.a.put("$ICONS", string + NFFile.httpFileSep + "icons");
    }
    
    private static void c() {
        final Enumeration<String> keys = NFFile.a.keys();
        while (keys.hasMoreElements()) {
            final String s = keys.nextElement();
            NFDebug.print(s + " = " + (String)NFFile.a.get(s));
        }
    }
    
    public static boolean sameFile(final URL url, final URL url2) {
        return url.getProtocol().equals(url2.getProtocol()) && url.getHost().equals(url2.getHost()) && url.getPort() == url2.getPort() && url.getFile().equalsIgnoreCase(url2.getFile());
    }
    
    public static String resolvePath(String string) {
        if (string == null || string.length() == 0 || string.charAt(0) != '$') {
            return string;
        }
        if (NFFile.a.size() == 0) {
            a();
        }
        final Enumeration<String> keys = NFFile.a.keys();
        while (keys.hasMoreElements()) {
            final String s = keys.nextElement();
            if (string.startsWith(s)) {
                string = (String)NFFile.a.get(s) + string.substring(s.length());
                return string;
            }
        }
        return string;
    }
    
    public static String unresolvePath(String string) {
        if (string == null || string.length() == 0) {
            return string;
        }
        if (NFFile.a.size() == 0) {
            a();
        }
        String s = "";
        String s2 = null;
        final Enumeration<String> keys = NFFile.a.keys();
        while (keys.hasMoreElements()) {
            final String s3 = keys.nextElement();
            final String s4 = NFFile.a.get(s3);
            if (string.startsWith(s4) && s4.length() > s.length()) {
                s = s4;
                s2 = s3;
            }
        }
        if (s2 != null) {
            string = s2 + string.substring(s.length());
        }
        return string;
    }
    
    public static String chasePath(final String s, final String s2) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, System.getProperty("path.separator"));
        while (stringTokenizer.hasMoreTokens()) {
            final String httpFormat = httpFormat(stringTokenizer.nextToken());
            if ((httpFormat.endsWith(".jar") || httpFormat.endsWith(".zip")) && NFUtil.isZipDirEntry(httpFormat + "!/", s2)) {
                try {
                    return ((ZipURLConnection)NFUtil.getFileURL(httpFormat + "!/").openConnection()).getZipFileURL().getFile() + "!";
                }
                catch (Exception ex) {
                    NFDebug.print("NFFile.chasePath: exception: " + ex);
                    return null;
                }
            }
            try {
                final File file = new File(httpFormat, s2);
                if (file.exists()) {
                    String s3 = httpFormat(file.getAbsolutePath());
                    int n;
                    if (s2.endsWith("/")) {
                        n = s3.lastIndexOf(NFFile.httpFileSep, s3.length() - 2);
                    }
                    else {
                        n = s3.lastIndexOf(NFFile.httpFileSep);
                    }
                    if (n > -1) {
                        s3 = s3.substring(0, n);
                    }
                    return s3;
                }
                continue;
            }
            catch (Exception ex2) {
                NFDebug.print(ex2.getMessage());
            }
        }
        return null;
    }
    
    public static String httpFormat(String s) {
        if (s == null) {
            return s;
        }
        if (NFFile.httpFileSep.charAt(0) == '/') {
            s = s.replace('\\', '/');
        }
        else {
            s = s.replace('/', '\\');
        }
        return s;
    }
    
    public static String localFormat(String s) {
        if (s == null) {
            return s;
        }
        if (NFFile.localFileSep.charAt(0) == '/') {
            s = s.replace('\\', '/');
        }
        else {
            s = s.replace('/', '\\');
        }
        return s;
    }
    
    public static String[] pathSplit(final String s, final String s2) {
        final String[] array = new String[2];
        String s3 = s;
        final int index = s.indexOf("//");
        if (index > 0 && s.indexOf(s2, index + 2) < 0) {
            s3 = new String(s + "/");
        }
        if (s3 == null || s3.length() == 0 || s3.equalsIgnoreCase("null")) {
            array[1] = (array[0] = null);
            return array;
        }
        final int lastIndex = s3.lastIndexOf(s2);
        if (lastIndex < 0) {
            array[0] = null;
            array[1] = s3;
            return array;
        }
        if (lastIndex == s3.length() - 1) {
            array[0] = s3;
            array[1] = null;
            return array;
        }
        array[0] = s3.substring(0, lastIndex + 1);
        array[1] = s3.substring(lastIndex + 1);
        return array;
    }
    
    public static InputStream getInputStream(final URL url) throws Exception {
        return getInputStream(url, NFFile.b);
    }
    
    public static InputStream getInputStream(final URL url, final NFContext nfContext) throws Exception {
        String protocol = url.getProtocol();
        if (protocol == null) {
            protocol = "http";
        }
        if (protocol.equalsIgnoreCase("file")) {
            return new BufferedInputStream(new FileInputStream(url.getFile()));
        }
        if (protocol.equalsIgnoreCase("http") && NFContext.getUserAgentType() == 0) {
            return url.openStream();
        }
        if (protocol.equalsIgnoreCase("http")) {
            return new NFHttpClient(nfContext).getContentAsInputStream(url);
        }
        throw new ProtocolException("Unsupported protocol = " + protocol);
    }
    
    public static Date getLastModified(final URL url) {
        if (url == null) {
            return null;
        }
        try {
            String protocol = url.getProtocol();
            if (protocol == null) {
                protocol = "http";
            }
            if (protocol.equalsIgnoreCase("file")) {
                final String file = url.getFile();
                final File file2 = new File(file);
                if (!file2.exists()) {
                    NFDebug.print(32768L, "NFFile: " + file + ": DOES NOT EXIST");
                    return null;
                }
                final Date date = new Date(file2.lastModified());
                NFDebug.print(32768L, "NFFile: " + file + ": lastModified = " + date);
                return date;
            }
            else if (NFUtil.getJDKVersion() > 1.0 && protocol.equalsIgnoreCase("zip")) {
                final ZipEntry zipEntry = ((ZipURLConnection)url.openConnection()).getZipEntry();
                if (zipEntry == null) {
                    return null;
                }
                return new Date(zipEntry.getTime());
            }
            else {
                if (protocol.equalsIgnoreCase("http") && NFContext.getUserAgentType() != 0) {
                    final String timeStamp = new NFHttpClient().getTimeStamp(url);
                    Date date2;
                    if (timeStamp == null) {
                        date2 = new Date(System.currentTimeMillis());
                    }
                    else {
                        date2 = new Date(timeStamp);
                    }
                    NFDebug.print(32768L, "NFFile: " + url + ": lastModified = " + date2);
                    return date2;
                }
                if (protocol.equalsIgnoreCase("http")) {
                    try {
                        final Date date3 = new Date(url.openConnection().getLastModified());
                        NFDebug.print(32768L, "NFFile: " + url + ": lastModified = " + date3);
                        return date3;
                    }
                    catch (Exception ex) {
                        NFDebug.print(32768L, "NFFile: exception getting lastModified. message=" + ex.getMessage());
                    }
                }
                throw new Exception("NFFile: Unsupported protocol " + protocol);
            }
        }
        catch (Exception ex2) {
            ex2.printStackTrace();
            return null;
        }
    }
    
    public static boolean modifiedSince(final URL url, final Date date) {
        if (url == null) {
            return false;
        }
        if (date == null) {
            return true;
        }
        try {
            final Date lastModified = getLastModified(url);
            return lastModified == null || date.before(lastModified);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return true;
        }
    }
    
    public static void main(final String[] array) {
        for (int i = 0; i < array.length; ++i) {
            String gmtString = "NULL";
            try {
                final Date lastModified = getLastModified(NFUtil.getFileURL(array[i]));
                if (lastModified != null) {
                    gmtString = lastModified.toGMTString();
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
            NFDebug.print(NFUtil.sprintf("%-16s lastModified = %s", array[i], gmtString));
        }
    }
    
    static {
        NFFile.httpFileSep = "/";
        NFFile.localFileSep = System.getProperty("file.separator");
        NFFile.a = new Hashtable();
        NFFile.b = NFContext.getDefault();
    }
}
