// 
// Decompiled by Procyon v0.5.30
// 

package com.postx.client;

import java.io.FileInputStream;
import com.postx.sec.algorithms.ARC4;
import java.math.BigInteger;
import java.awt.Image;
import java.text.DateFormat;
import java.util.Date;
import com.postx.security.RSA;
import com.postx.ui.VerifyWindow;
import com.postx.security.Certificate;
import com.postx.util.Encodings;
import com.postx.util.logging.Handler;
import com.postx.util.logging.FileHandler;
import java.security.NoSuchAlgorithmException;
import com.postx.io.ExtendedPushbackReader;
import java.io.BufferedWriter;
import java.awt.Toolkit;
import java.net.MalformedURLException;
import com.postx.io.BadHashException;
import com.jcraft.jzlib.ZStreamException;
import java.io.FileNotFoundException;
import com.postx.payload.AppletTOCEntry;
import com.postx.io.EnvelopeDamagedException;
import com.postx.io.HashVerificationInputStream;
import com.postx.io.DecryptInputStream;
import com.postx.util.FileMap;
import com.postx.io.PayloadInputStream;
import java.awt.FileDialog;
import java.awt.Frame;
import com.postx.util.StringConversion;
import com.postx.payload.TOCEntry;
import java.io.BufferedInputStream;
import com.jcraft.jzlib.ZInputStream;
import java.util.Vector;
import java.security.MessageDigest;
import com.postx.security.SecurityUtils;
import java.io.IOException;
import com.postx.util.logging.Level;
import java.io.FileReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import sun.misc.BASE64Encoder;
import java.io.InputStream;
import java.net.URLConnection;
import java.io.ByteArrayOutputStream;
import com.ms.security.PolicyEngine;
import com.ms.security.PermissionID;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import com.postx.util.URLCode;
import com.apple.eio.FileManager;
import java.util.Hashtable;
import com.postx.web.WebServer;
import java.net.URL;
import netscape.javascript.JSObject;
import java.io.File;
import java.applet.Applet;
import com.postx.util.logging.Logger;

public class EnvelopeTools
{
    public static final String Ident = "$Id: EnvelopeTools.java,v 1.14 2011/01/10 05:13:52 blm Exp $";
    static final Logger log;
    private static final String className = "EnvelopeTools";
    protected static final String TEMP_DIR = "CSTemp";
    protected static final String PERM_DIR = "PostX";
    protected static final String LS = "Local Settings";
    protected static final String TIF = "Temporary Internet Files";
    protected static final String APPDATA16 = "\\AppData\\Local\\Microsoft\\Windows\\";
    protected static final String APPDATA15;
    protected static final String MAIN_NAME = "message";
    protected static final String DEF_PAYLOAD_NAME = "Secure Documents";
    protected static final String PARAM_DOCUMENT_URL = "document.URL";
    protected static final byte[] gif;
    protected static final int ACTION_OPEN = 0;
    protected static final int ACTION_SAVE = 1;
    protected static final int ACTION_VERIFY_SIGNATURE = 5;
    protected static final int ACTION_SAVE_ONE_FILE = 9;
    protected static final int ACTION_AUTHENTICATE_AND_OPEN = 17;
    protected static final int ACTION_SELF_TEST = 33;
    protected static final int STATUS_BADPASSWORD = 3;
    protected static final int STATUS_BADHASH = 4;
    protected static final int STATUS_SUCCESS = 0;
    protected static final int STATUS_BADSIGNATURE = 6;
    protected static final int STATUS_INCOMPATIBLE = 8;
    protected static final int STATUS_APPLETINTERNALERROR = 11;
    protected static final int STATUS_ENVELOPEMISSING = 12;
    protected static final int STATUS_ENVELOPEDAMAGED = 13;
    protected static final String fileSep;
    static final String[] callbackNames;
    protected static final short kUserDomain = -32763;
    protected static final short kLocalDomain = -32765;
    protected static final int kTemporaryFolderType = 1952804208;
    protected static final int kApplicationSupportFolderType = 1634956656;
    private static final String logFile = "C:\\PostXAppletLog.txt";
    protected Applet applet;
    protected String[] tempDirs;
    protected File tempBaseDir;
    protected File permBaseDir;
    protected File tempDir;
    protected JSObject jsObject;
    protected String appletName;
    protected URL documentBase;
    protected String documentURL;
    protected String documentCharset;
    protected String userAgent;
    protected boolean sunJava;
    protected boolean inIE;
    protected boolean OSMacOSX;
    protected boolean OSVista;
    protected boolean haveMSSecurity;
    protected int javaClassVersion;
    protected WebServer webServer;
    protected byte[] buf;
    protected Hashtable callbacks;
    protected double lastProgressCall;
    protected File storePath;
    private boolean logInfo;
    private boolean logConfig;
    private boolean logFine;
    private boolean logFiner;
    private boolean logFinest;
    private boolean logFlow;
    private static final String commentStart = "<!--";
    private static final String frameHeight = "frame-height:";
    protected static final String REG_SZ = "REG_SZ";
    protected static final String REG_TIF_KEY = "\"HKCU\\Software\\Microsoft\\Windows\\CurrentVersion\\Explorer\\Shell Folders\"";
    protected static final String REG_TIF_VALUE = "Cache";
    protected String signatureTimestamp;
    
    protected int getIntParameter(final String s) {
        return Integer.parseInt(this.getParameter(s));
    }
    
    protected Hashtable collectCallbacks(final String[] array) {
        final int length = array.length;
        final Hashtable hashtable = new Hashtable<String, String>((length * 4 + 3) / 3);
        for (int i = 0; i < length; ++i) {
            final String parameter;
            if ((parameter = this.getParameter("fns." + array[i])) != null) {
                hashtable.put(array[i], parameter);
            }
        }
        return hashtable;
    }
    
    protected int getIntParameter(final String s, final int n) {
        final String parameter = this.getParameter(s);
        if (parameter != null) {
            return Integer.parseInt(parameter);
        }
        return n;
    }
    
    private File genBaseDir(final boolean b) {
        final String s = b ? "PostX" : "CSTemp";
        File file = null;
        if (this.OSMacOSX) {
            try {
                final short[] array = new short[4];
                final int[] array2 = new int[4];
                int n = 0;
                if (b) {
                    array[n] = -32765;
                    array2[n++] = 1634956656;
                    array[n] = -32763;
                    array2[n++] = 1634956656;
                }
                array[n] = -32763;
                array2[n++] = 1952804208;
                array[n] = -32765;
                array2[n++] = 1952804208;
                for (int i = 0; i < n; ++i) {
                    final String folder;
                    if ((folder = FileManager.findFolder(array[i], array2[i], true)) != null) {
                        file = new File(folder, s);
                        if (file.isDirectory() || file.mkdirs()) {
                            if (this.logConfig) {
                                EnvelopeTools.log.config("Using " + (b ? "permanent" : "temporary") + " base directory of " + file);
                            }
                            return file;
                        }
                    }
                }
            }
            catch (Exception ex) {}
        }
        String s2 = this.documentBase.getFile();
        if (this.sunJava) {
            s2 = URLCode.decode(s2, 256);
        }
        if ((s2.charAt(0) == '/' || s2.charAt(0) == '\\') && s2.charAt(2) == ':') {
            s2 = s2.substring(1).replace('/', '\\');
        }
        final int index;
        if ((index = s2.indexOf("\\" + "Temporary Internet Files" + "\\")) != -1) {
            this.tempDirs[0] = s2.substring(0, index + "Temporary Internet Files".length() + 1);
        }
        else {
            file = null;
            File file2;
            for (String s3 = System.getProperty("user.dir"); s3 != null; s3 = file2.getParent()) {
                file2 = new File(s3);
                if ((file = new File(new File(file2, "Local Settings"), "Temporary Internet Files")).isDirectory() || (file = new File(file2, "Temporary Internet Files")).isDirectory()) {
                    break;
                }
                file = null;
            }
            if (file == null) {
                final String registryValue = this.readRegistryValue("\"HKCU\\Software\\Microsoft\\Windows\\CurrentVersion\\Explorer\\Shell Folders\"", "Cache");
                if (registryValue != null && !(file = new File(registryValue)).isDirectory()) {
                    file = null;
                }
            }
            if (file == null) {
                final String property = System.getProperty("java.io.tmpdir");
                if (property != null && !(file = new File(property)).isDirectory()) {
                    file = null;
                }
            }
            if (file != null) {
                this.tempDirs[0] = file.getPath();
            }
        }
        if (b) {
            this.tempDirs[1] = System.getProperty("user.home");
        }
        else {
            this.tempDirs[this.tempDirs.length - 1] = System.getProperty("user.home");
        }
        if (this.logFinest) {
            for (int j = 0; j < this.tempDirs.length; ++j) {
                EnvelopeTools.log.finest("tempDirs[" + j + "]: " + this.tempDirs[j]);
            }
        }
        int k;
        for (k = 0; k < this.tempDirs.length; ++k) {
            if (this.tempDirs[k] != null) {
                file = new File(this.tempDirs[k]);
                if (file.isDirectory()) {
                    file = new File(file, s);
                    if (file.isDirectory() || file.mkdirs()) {
                        break;
                    }
                }
            }
        }
        if (k == this.tempDirs.length) {
            try {
                final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec(new String[] { "cmd", "/c", "set", "TEMP" }).getInputStream()));
                String s4;
                while ((s4 = bufferedReader.readLine()) != null) {
                    if (s4.startsWith("TEMP=")) {
                        s4 = s4.substring(5);
                        break;
                    }
                }
                bufferedReader.close();
                if (s4 != null) {
                    file = new File(s4, s);
                    if (!file.isDirectory() && !file.mkdirs()) {
                        file = null;
                    }
                }
            }
            catch (Exception ex2) {
                file = null;
            }
        }
        if (file == null) {
            final String[] file3 = this.getFile("payload", b ? "Select a Directory to Store Envelope Tools Information" : "Select a Temporary Directory");
            if (file3 != null) {
                file = new File(file3[0], s);
            }
        }
        if (file != null && !file.isDirectory()) {
            file.mkdirs();
        }
        if (this.logConfig) {
            EnvelopeTools.log.config("Using " + (b ? "permanent" : "temporary") + " base directory of " + file);
        }
        return file;
    }
    
    protected byte[] readURL(final String s) {
        if (this.haveMSSecurity) {
            try {
                PolicyEngine.assertPermission(PermissionID.NETIO);
            }
            catch (Exception ex) {}
        }
        try {
            final URLConnection openConnection = new URL(s).openConnection();
            final InputStream inputStream = openConnection.getInputStream();
            final int contentLength = openConnection.getContentLength();
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream((contentLength == -1) ? this.buf.length : contentLength);
            int read;
            while ((read = inputStream.read(this.buf)) != -1) {
                byteArrayOutputStream.write(this.buf, 0, read);
            }
            this.buf = byteArrayOutputStream.toByteArray();
            if (read != -1 && this.buf.length != read) {
                throw new Exception("Couldn't retrieve entire URL");
            }
            return this.buf;
        }
        catch (Exception ex2) {
            return null;
        }
    }
    
    protected static String base64Encode(final byte[] array) {
        try {
            return new BASE64Encoder().encode(array);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    protected static String safeFilename(final String s) {
        int n = s.lastIndexOf(47) + 1;
        final int n2 = s.lastIndexOf(92) + 1;
        if (n2 > n) {
            n = n2;
        }
        return s.substring(n);
    }
    
    private String selfTest() {
        BufferedReader bufferedReader = null;
        PrintWriter printWriter = null;
        final String s = "line 1";
        String string = null;
        final String s2 = "Exception occurred, see Java console";
        File file = null;
        try {
            file = new File(this.tempBaseDir, "self test " + System.currentTimeMillis() + ".txt");
            printWriter = new PrintWriter(new FileOutputStream(file));
            printWriter.println(s);
            printWriter.close();
            printWriter = null;
            bufferedReader = new BufferedReader(new FileReader(file));
            final String line = bufferedReader.readLine();
            bufferedReader.close();
            bufferedReader = null;
            if (!s.equals(line)) {
                string = "Wrote \"" + s + "\", read " + ((line == null) ? line : ('\"' + line + '\"'));
            }
        }
        catch (IOException ex) {
            string = s2;
            EnvelopeTools.log.log(Level.SEVERE, "Exception during self test", ex);
        }
        finally {
            if (printWriter != null) {
                printWriter.close();
            }
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                }
                catch (IOException ex2) {
                    if (string == null) {
                        string = s2;
                    }
                    ex2.printStackTrace();
                }
            }
            if (file != null) {
                file.delete();
            }
        }
        if (string == null) {
            return "1";
        }
        return string;
    }
    
    private static int skipNonWhitespace(final String s, final int n) {
        int length;
        int n2;
        for (length = s.length(), n2 = n; n2 < length && !Character.isWhitespace(s.charAt(n2)); ++n2) {}
        return n2;
    }
    
    protected static byte[] checkCRC32(final byte[] array) {
        final byte[] array2 = new byte[array.length];
        System.arraycopy(array, 4, array2, 0, array2.length);
        final byte[] crc32 = crc32(array2);
        int n = 0;
        while (crc32[n] == array[n]) {
            if (++n >= 4) {
                return array2;
            }
        }
        return null;
    }
    
    protected String readRegistryValue(final String s, final String s2) {
        String trim = null;
        try {
            String line;
            while ((line = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec(new String[] { "reg", "query", s, "/v", s2 }).getInputStream())).readLine()) != null) {
                if (line.indexOf("REG_SZ") != -1) {
                    trim = line.substring(line.indexOf("REG_SZ") + "REG_SZ".length()).trim();
                }
            }
        }
        catch (Exception ex) {
            if (this.logFiner) {
                EnvelopeTools.log.log(Level.FINER, "Error while reading registry", ex);
            }
        }
        if (this.logFine) {
            EnvelopeTools.log.fine("Read value from registry: " + trim);
        }
        return trim;
    }
    
    protected void setStorePath(final File storePath) {
        this.storePath = storePath;
    }
    
    private File genTempDir(final String s) {
        File file = new File(this.tempBaseDir, this.genFileName(s));
        if (!file.isDirectory() && !file.mkdirs()) {
            file = null;
        }
        if (this.logConfig) {
            EnvelopeTools.log.config("Using temp directory of " + file);
        }
        return file;
    }
    
    protected static byte[] digest(final String s, final byte[] array) {
        final MessageDigest messageDigest = SecurityUtils.getMessageDigest(s);
        messageDigest.update(array);
        return messageDigest.digest();
    }
    
    protected String getParameter(final String s) {
        return this.applet.getParameter(s);
    }
    
    private int processTOC(InputStream inputStream, final String s, final String[] array, final Vector vector, final boolean b, final long[] array2) throws IOException {
        if (b) {
            inputStream = new ZInputStream(inputStream);
        }
        final BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        int i = -1;
        final int size = vector.size();
        int n = 0;
        for (int j = 0; j < size; ++j) {
            final TOCEntry tocEntry = vector.elementAt(j);
            int offset;
            for (offset = tocEntry.getOffset(); n < offset && (i = (int)bufferedInputStream.skip(offset - n)) != -1; n += i) {
                this.updateProgress(array2, i);
            }
            if (n < offset) {
                return 13;
            }
            final OutputStream create = tocEntry.create();
            int k;
            int length;
            for (k = 0, length = tocEntry.getLength(); k < length; k += i) {
                int length2;
                if ((length2 = length - k) > this.buf.length) {
                    length2 = this.buf.length;
                }
                if ((i = bufferedInputStream.read(this.buf, 0, length2)) == -1) {
                    break;
                }
                create.write(this.buf, 0, i);
                this.updateProgress(array2, i);
            }
            create.close();
            if (k < length) {
                return 13;
            }
            n += k;
        }
        while (i != -1) {
            i = bufferedInputStream.read(this.buf, 0, this.buf.length);
        }
        return 0;
    }
    
    private String getDocumentURL(final String s) {
        String s2 = this.getParameter(s);
        if (this.logInfo) {
            EnvelopeTools.log.info("Raw " + s + ": " + s2);
        }
        if (s2 == null) {
            return null;
        }
        if (!this.inIE) {
            try {
                s2 = URLCode.decode(s2, 128);
                EnvelopeTools.log.fine("UTF-8 URL decoded " + s + ": " + s2);
                return s2;
            }
            catch (IllegalArgumentException ex) {}
        }
        if (this.inIE) {
            if (!s2.startsWith("file:")) {
                return s2;
            }
        }
        try {
            s2 = URLCode.decode(s2);
            EnvelopeTools.log.fine("URL decoded " + s + ": " + s2);
            return s2;
        }
        catch (IllegalArgumentException ex2) {}
        return s2;
    }
    
    protected static byte[] crc32(final byte[] array) {
        if (array.length == 0) {
            return new byte[4];
        }
        return digest("CRC-32", array);
    }
    
    public boolean saveFile(final String s, final String s2) {
        final String[] file = this.getFile(s, "File Save");
        if (file != null) {
            FileOutputStream fileOutputStream = null;
            try {
                final String string = file[0] + file[1];
                if (this.haveMSSecurity) {
                    try {
                        PolicyEngine.assertPermission(PermissionID.FILEIO);
                    }
                    catch (Exception ex) {}
                }
                fileOutputStream = new FileOutputStream(string);
                fileOutputStream.write(StringConversion.stringToBytes(s2));
                return true;
            }
            catch (IOException ex2) {}
            finally {
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    }
                    catch (IOException ex3) {}
                }
            }
        }
        return false;
    }
    
    protected String[] getFile(String safeFilename, final String s) {
        if (this.logFlow) {
            EnvelopeTools.log.entering("EnvelopeTools", "getFile", new Object[] { safeFilename, s });
        }
        safeFilename = safeFilename(safeFilename);
        if (this.haveMSSecurity) {
            try {
                PolicyEngine.assertPermission(PermissionID.FILEIO);
                PolicyEngine.assertPermission(PermissionID.UI);
            }
            catch (Exception ex) {}
        }
        final String property = System.getProperty("user.home");
        final FileDialog fileDialog = new FileDialog(new Frame(), s, 1);
        fileDialog.setLocation(200, 150);
        if (property != null) {
            fileDialog.setDirectory(property);
        }
        fileDialog.setFile(safeFilename);
        fileDialog.show();
        String[] array = { fileDialog.getDirectory(), fileDialog.getFile() };
        if (array[0] != null && array[1] != null) {
            array[1] = safeFilename(array[1]);
        }
        else {
            array = null;
        }
        if (this.logFlow) {
            EnvelopeTools.log.exiting("EnvelopeTools", "getFile", array);
        }
        return array;
    }
    
    protected void updateProgress(final long[] array, final int n) {
        final int n2 = 1;
        array[n2] += n;
        final double lastProgressCall = array[1] / array[0];
        if (array[0] == array[1] || lastProgressCall - this.lastProgressCall >= 0.03) {
            this.callJavaScript("progress", new String[] { String.valueOf(lastProgressCall) }, "Decryption Progress: " + (int)(lastProgressCall * 100.0) + "%", false);
            this.lastProgressCall = lastProgressCall;
        }
    }
    
    protected void updateProgress(final String s) {
        if (s.equals("start")) {
            this.lastProgressCall = 0.0;
        }
        this.callJavaScript("progress", new String[] { s }, "Decryption Progress: " + s, false);
    }
    
    private static int skipWhitespace(final String s, final int n) {
        int length;
        int n2;
        for (length = s.length(), n2 = n; n2 < length && Character.isWhitespace(s.charAt(n2)); ++n2) {}
        return n2;
    }
    
    public void close() {
    }
    
    public String installKey(final String s, final String s2, final String s3, final String s4) {
        if (this.haveMSSecurity) {
            try {
                PolicyEngine.assertPermission(PermissionID.FILEIO);
            }
            catch (Exception ex) {}
        }
        final File permBaseDir = this.permBaseDir;
        if (permBaseDir != null) {
            this.setStorePath(permBaseDir);
            new Key(s2, s3, s4).saveKey();
        }
        return "OK";
    }
    
    protected int processPayload(final boolean b, final String s) {
        if (this.logFlow) {
            EnvelopeTools.log.entering("EnvelopeTools", "processPayload", new Object[] { b ? Boolean.TRUE : Boolean.FALSE, s });
        }
        this.updateProgress("clear");
        String parameter = this.getParameter("payloadname");
        final boolean b2 = false;
        if (parameter == null || parameter.equals("null")) {
            parameter = "Secure Documents";
        }
        String[] file;
        if (!b) {
            file = new String[] { this.tempDir.toString(), null };
        }
        else if ((file = this.getFile(parameter, "Select Save Location")) == null) {
            if (this.logFlow) {
                EnvelopeTools.log.exiting("EnvelopeTools", "processPayload", new Integer((int)(b2 ? 1 : 0)));
            }
            return b2 ? 1 : 0;
        }
        if (this.haveMSSecurity) {
            try {
                PolicyEngine.assertPermission(PermissionID.FILEIO);
            }
            catch (Throwable t) {}
        }
        try {
            this.updateProgress("start");
            final PayloadInputStream payloadInputStream = new PayloadInputStream(this.documentURL, this.documentCharset);
            final int[] segments = payloadInputStream.getSegments();
            final Object[] array = (Object[])payloadInputStream.getProperty("toc");
            final int length = array.length;
            if (b && length > 1) {
                if (!file[0].endsWith(File.separator)) {
                    final String[] array2 = file;
                    final int n = 0;
                    array2[n] += File.separator;
                }
                final String[] array3 = file;
                final int n2 = 0;
                array3[n2] += file[1];
                file[1] = null;
                final File file2 = new File(file[0]);
                if (!file2.isDirectory()) {
                    file2.mkdirs();
                }
            }
            final int n3 = 0;
            final int n4 = 1;
            final FileMap fileMap = new FileMap();
            final Vector[] array4 = { new Vector(length), new Vector(length) };
            final int[] array5 = new int[2];
            for (int i = 0; i < length; ++i) {
                final TOCEntry tocEntry = new TOCEntry((Object[])array[i], fileMap, file, b, this.inIE);
                final int n5 = tocEntry.isSecure() ? n3 : n4;
                if (tocEntry.getOffset() + tocEntry.getLength() > array5[n5]) {
                    array5[n5] = tocEntry.getOffset() + tocEntry.getLength();
                }
                Vector vector;
                int size;
                for (vector = array4[n5], size = vector.size(); size > 0 && tocEntry.getOffset() < vector.elementAt(size - 1).getOffset(); --size) {}
                vector.insertElementAt(tocEntry, size);
            }
            final long[] array6 = new long[2];
            for (int j = n3; j <= n4; ++j) {
                final String s2 = (j == n3) ? "crypt" : "plain";
                final Vector vector2 = array4[j];
                final int size2 = vector2.size();
                if (this.logFine) {
                    EnvelopeTools.log.fine("Number of " + s2 + " entries: " + size2);
                    EnvelopeTools.log.fine("Size of " + s2 + " entries: " + array5[j]);
                    if (size2 > 0) {
                        EnvelopeTools.log.fine(s2.substring(0, 1).toUpperCase() + s2.substring(1) + " entries:");
                        for (int k = 0; k < size2; ++k) {
                            EnvelopeTools.log.fine("  " + k + ": " + vector2.elementAt(k));
                        }
                    }
                }
                final long[] array7 = array6;
                final int n6 = 0;
                array7[n6] += array5[j];
            }
            final byte[] salt = payloadInputStream.getSalt();
            byte[] stringToBytes = null;
            final long flags = payloadInputStream.getFlags();
            final String parameter2;
            if ((parameter2 = this.getParameter("key")) != null) {
                stringToBytes = StringConversion.stringToBytes(URLCode.decode(parameter2));
            }
            final int keySize = payloadInputStream.getKeySize();
            String s3;
            if ((s3 = this.getParameter("sessionKey")) != null) {
                s3 = URLCode.decode(s3);
            }
            byte[] stringToBytes2;
            if (s3 == null) {
                final byte[] keyEncryptionIV = payloadInputStream.getKeyEncryptionIV();
                final int keyEncryptionPrerun = payloadInputStream.getKeyEncryptionPrerun();
                final boolean equals = payloadInputStream.getKeyEncryptionAlgorithmName().equals("AES");
                final String keyVerificationAlgorithmName = payloadInputStream.getKeyVerificationAlgorithmName();
                if ("AES".equals(payloadInputStream.getDataEncryptionAlgorithmName())) {
                    stringToBytes2 = new byte[24];
                }
                else {
                    stringToBytes2 = new byte[20];
                }
                final int length2 = stringToBytes2.length;
                final byte[] sha1 = SHA1(byteAppend(stringToBytes, salt));
                int l = 0;
            Label_1108:
                while (l < segments[0]) {
                    DecryptInputStream decryptInputStream;
                    if (equals) {
                        decryptInputStream = new DecryptInputStream(payloadInputStream, sha1, keyEncryptionIV);
                    }
                    else {
                        decryptInputStream = new DecryptInputStream(payloadInputStream, sha1, keySize, keyEncryptionPrerun);
                    }
                    final HashVerificationInputStream hashVerificationInputStream = new HashVerificationInputStream(decryptInputStream, keyVerificationAlgorithmName);
                    try {
                        while (true) {
                            int n8;
                            for (int n7 = n8 = 0; n7 < length2; n7 += n8) {
                                if ((n8 = hashVerificationInputStream.read(stringToBytes2, n7, stringToBytes2.length - n7)) == -1) {
                                    while (n8 != -1) {
                                        n8 = hashVerificationInputStream.read();
                                    }
                                    this.skipToNextSegment(payloadInputStream, segments[0] - l - 1);
                                    break Label_1108;
                                }
                            }
                            continue;
                        }
                    }
                    catch (IOException ex7) {
                        this.skipToNextSegment(payloadInputStream, 1);
                        hashVerificationInputStream.close();
                        ++l;
                    }
                }
                if (l == segments[0]) {
                    final int n9 = 3;
                    if (this.logFlow) {
                        EnvelopeTools.log.exiting("EnvelopeTools", "processPayload", new Integer(n9));
                    }
                    return n9;
                }
            }
            else {
                this.skipToNextSegment(payloadInputStream, segments[0] - 1);
                stringToBytes2 = StringConversion.stringToBytes(s3);
            }
            final byte[] sha2 = SHA1(byteAppend(SHA1(stringToBytes2), salt));
            payloadInputStream.next();
            final boolean equals2 = payloadInputStream.getDataEncryptionAlgorithmName().equals("AES");
            final String dataVerificationAlgorithmName = payloadInputStream.getDataVerificationAlgorithmName();
            DecryptInputStream decryptInputStream2;
            if (equals2) {
                decryptInputStream2 = new DecryptInputStream(payloadInputStream, sha2, payloadInputStream.getDataEncryptionIV());
            }
            else {
                decryptInputStream2 = new DecryptInputStream(payloadInputStream, sha2, keySize, payloadInputStream.getDataEncryptionPrerun());
            }
            final HashVerificationInputStream hashVerificationInputStream2 = new HashVerificationInputStream(decryptInputStream2, dataVerificationAlgorithmName);
            final int processTOC;
            if ((processTOC = this.processTOC(hashVerificationInputStream2, s, file, array4[n3], (flags & 0x1L) != 0x0L, array6)) != 0) {
                throw new EnvelopeDamagedException(this.documentURL, "Couldn't process encrypted data (" + processTOC + ")");
            }
            hashVerificationInputStream2.close();
            payloadInputStream.next();
            final HashVerificationInputStream hashVerificationInputStream3 = new HashVerificationInputStream(payloadInputStream, dataVerificationAlgorithmName);
            final int processTOC2;
            if ((processTOC2 = this.processTOC(hashVerificationInputStream3, s, file, array4[n4], (flags & 0x2L) != 0x0L, array6)) != 0) {
                throw new EnvelopeDamagedException(this.documentURL, "Couldn't process plain data (" + processTOC2 + ")");
            }
            if (this.logFiner) {
                if ((System.currentTimeMillis() & 0x1L) == 0x0L) {
                    if (payloadInputStream.next()) {
                        EnvelopeTools.log.finer("Final payload.next(): true");
                    }
                    final int read;
                    if ((read = payloadInputStream.read()) != -1) {
                        EnvelopeTools.log.finer("Final payload.read(): " + read);
                    }
                }
                else {
                    final int read2;
                    if ((read2 = payloadInputStream.read()) != -1) {
                        EnvelopeTools.log.finer("Final payload.read(): " + read2);
                    }
                    if (payloadInputStream.next()) {
                        EnvelopeTools.log.finer("Final payload.next(): true");
                    }
                }
            }
            hashVerificationInputStream3.close();
            payloadInputStream.close();
            final String parameter3;
            if ((parameter3 = this.getParameter("hardener")) != null) {
                this.setCookie(this.getParameter("hardener.cookie"), "hardener", URLCode.decode(parameter3));
            }
            if (!b && !fileMap.containsKey("favicon.ico", 6)) {
                try {
                    new AppletTOCEntry(fileMap, "/data/", "favicon.ico");
                    EnvelopeTools.log.config("Don't have a favicon, using one in applet");
                }
                catch (IOException ex8) {
                    EnvelopeTools.log.config("Don't have a favicon");
                }
            }
            TOCEntry message = (TOCEntry)fileMap.get("main", 1);
            final TOCEntry tocEntry2 = (TOCEntry)fileMap.get("mbar", 1);
            if (tocEntry2 != null) {
                if (message == null) {
                    message = tocEntry2;
                }
                else {
                    message = this.createMessage(fileMap, tocEntry2, message, file, b);
                }
            }
            this.updateProgress("finish");
            if (message != null) {
                if (b) {
                    TOCEntry.closeAll();
                    final String string = message.getPath().toString();
                    this.callJavaScript("saved", new String[] { string, String.valueOf(length) }, "File" + ((length == 1) ? "" : "s") + " saved, open " + string.substring(1) + " to view", true);
                }
                else {
                    try {
                        this.webServer = new WebServer(this.permBaseDir, fileMap);
                        final String string2 = "http://" + this.webServer.getAddr() + "/" + message.getLocalizedFileName();
                        new Thread(Thread.currentThread().getThreadGroup().getParent(), this.webServer, "EnvelopeTools web server thread").start();
                        if (this.logFine) {
                            EnvelopeTools.log.fine("Showing document " + string2);
                        }
                        this.callJavaScript("launch", new String[] { string2 });
                    }
                    catch (IOException ex) {
                        final int n10 = 11;
                        EnvelopeTools.log.log(Level.SEVERE, "Could not create web server", ex);
                        if (this.logFlow) {
                            EnvelopeTools.log.exiting("EnvelopeTools", "processPayload", new Integer(n10));
                        }
                        return n10;
                    }
                }
            }
            if (this.logFlow) {
                EnvelopeTools.log.exiting("EnvelopeTools", "processPayload", new Integer(processTOC2));
            }
            return processTOC2;
        }
        catch (FileNotFoundException ex2) {
            final int n11 = 12;
            EnvelopeTools.log.log(Level.SEVERE, "Couldn't open envelope", ex2);
            if (this.logFlow) {
                EnvelopeTools.log.exiting("EnvelopeTools", "processPayload", new Integer(n11));
            }
            return n11;
        }
        catch (EnvelopeDamagedException ex3) {
            final int n12 = 13;
            EnvelopeTools.log.log(Level.SEVERE, "Couldn't open envelope", ex3);
            if (this.logFlow) {
                EnvelopeTools.log.exiting("EnvelopeTools", "processPayload", new Integer(n12));
            }
            return n12;
        }
        catch (ZStreamException ex4) {
            final int n13 = 4;
            EnvelopeTools.log.log(Level.SEVERE, "Bad input to zlib decompressor", ex4);
            if (this.logFlow) {
                EnvelopeTools.log.exiting("EnvelopeTools", "processPayload", new Integer(n13));
            }
            return n13;
        }
        catch (BadHashException ex5) {
            final int n14 = 4;
            EnvelopeTools.log.log(Level.SEVERE, "Bad hash", ex5);
            if (this.logFlow) {
                EnvelopeTools.log.exiting("EnvelopeTools", "processPayload", new Integer(n14));
            }
            return n14;
        }
        catch (Exception ex6) {
            final int n15 = 11;
            EnvelopeTools.log.log(Level.SEVERE, "Unexpected exception", ex6);
            if (this.logFlow) {
                EnvelopeTools.log.exiting("EnvelopeTools", "processPayload", new Integer(n15));
            }
            return n15;
        }
    }
    
    protected static String quoteString(final String s, final char c) {
        final int length = s.length();
        final StringBuffer sb = new StringBuffer(length);
        sb.append(c);
        for (int i = 0; i < length; ++i) {
            final char char1 = s.charAt(i);
            if (char1 < ' ' || char1 > '~') {
                sb.append("\\x");
                if (char1 < '\u0010') {
                    sb.append('0');
                }
                sb.append(Integer.toHexString(char1));
            }
            else {
                if (char1 == '\\' || char1 == c) {
                    sb.append('\\');
                }
                sb.append(char1);
            }
        }
        sb.append(c);
        return sb.toString();
    }
    
    public EnvelopeTools(final Applet applet) {
        this.tempDirs = new String[] { null, null, "c:\\windows\\temp", "c:\\winnt\\temp", "c:\\temp", "/tmp", "", null };
        this.tempBaseDir = null;
        this.permBaseDir = null;
        this.tempDir = null;
        this.jsObject = null;
        this.appletName = null;
        this.documentCharset = "UTF-8";
        this.sunJava = false;
        this.inIE = false;
        this.OSMacOSX = false;
        this.OSVista = false;
        this.haveMSSecurity = false;
        this.javaClassVersion = 0;
        this.webServer = null;
        this.buf = new byte[16384];
        this.lastProgressCall = 0.0;
        this.storePath = null;
        this.signatureTimestamp = null;
        this.applet = applet;
    }
    
    private void skipToNextSegment(final PayloadInputStream payloadInputStream, int n) throws IOException {
        while (n-- > 0) {
            payloadInputStream.next();
        }
    }
    
    protected void reportInternalError(String string) {
        string += " - see Java console for more information.";
        this.callJavaScript("error", new String[] { String.valueOf(11), string }, string, true);
        this.callJavaScript("loaded", new String[] { "1", "1" });
    }
    
    protected void authenticateAndOpen(final String s) {
    }
    
    protected void callJavaScript(final String s, final String[] array) {
        this.callJavaScript(s, array, null, false);
    }
    
    protected void callJavaScript(final String s, final String[] array, final String s2, final boolean b) {
        final String s3 = this.callbacks.get(s);
        final Level level = s.equals("progress") ? Level.FINEST : Level.FINE;
        if (s3 == null) {
            this.displayStatus(s2, b);
            return;
        }
        final StringBuffer sb = new StringBuffer();
        final int length = array.length;
        sb.append("void(").append(s3).append("(");
        for (int i = 0; i < length; ++i) {
            final String s4 = array[i];
            if (i > 0) {
                sb.append(",");
            }
            if (s4 == null) {
                sb.append("''");
            }
            else {
                sb.append(quoteString(s4, '\''));
            }
        }
        sb.append("))");
        if (EnvelopeTools.log.isLoggable(level)) {
            EnvelopeTools.log.log(level, sb.toString());
        }
        if (this.jsObject != null) {
            this.jsObject.eval(sb.toString());
            return;
        }
        try {
            this.applet.getAppletContext().showDocument(new URL("javascript:" + URLCode.encode(sb.toString())));
        }
        catch (MalformedURLException ex) {
            EnvelopeTools.log.log(Level.WARNING, "Can't call JavaScript", ex);
        }
    }
    
    protected boolean getBooleanParameter(final String s) {
        final String parameter = this.getParameter(s);
        return parameter != null && (parameter.equals("1") || parameter.equalsIgnoreCase("true"));
    }
    
    private static void cleanBaseDir(final File file) {
        final String[] list = file.list();
        for (int length = list.length, i = 0; i < length; ++i) {
            final File file2 = new File(file, list[i]);
            final String[] list2;
            if ((list2 = file2.list()) != null) {
                for (int length2 = list2.length, j = 0; j < length2; ++j) {
                    final File file3 = new File(file2, list2[j]);
                    if (TOCEntry.isEntry(file3)) {
                        file3.delete();
                    }
                }
            }
            file2.delete();
        }
    }
    
    protected void displayStatus(final String s) {
        this.displayStatus(s, false);
    }
    
    protected void displayStatus(final String s, final boolean b) {
        if (s != null) {
            this.applet.showStatus(s);
        }
        if (b) {
            Toolkit.getDefaultToolkit().beep();
        }
    }
    
    public void destroy() {
        this.close();
    }
    
    public static void changePassword(final String s, final String s2, final String s3) {
    }
    
    protected TOCEntry createMessage(final FileMap fileMap, final TOCEntry tocEntry, final TOCEntry tocEntry2, final String[] array, final boolean b) throws IOException {
        if (this.logFlow) {
            EnvelopeTools.log.entering("EnvelopeTools", "createMessage", new Object[] { fileMap, tocEntry, tocEntry2, array, b ? Boolean.TRUE : Boolean.FALSE });
        }
        final String frameHeight = this.getFrameHeight(tocEntry, "20%");
        String s = "message";
        final boolean equals = frameHeight.equals("none");
        if (equals) {
            final String localizedFileName = tocEntry.getLocalizedFileName();
            final int lastIndex;
            if ((lastIndex = localizedFileName.lastIndexOf(46)) != -1) {
                s += localizedFileName.substring(lastIndex);
            }
        }
        else {
            s += ".html";
        }
        int n = 8;
        if (tocEntry.isSecure() || tocEntry2.isSecure()) {
            n |= 0x1;
        }
        final TOCEntry tocEntry3 = new TOCEntry(fileMap, array, b, s, tocEntry.getEncoding(), n, this.inIE);
        final BufferedWriter bufferedWriter = new BufferedWriter(tocEntry3.createWriter());
        final int n2 = b ? 0 : 128;
        if (equals) {
            tocEntry.makeInternal(fileMap);
            final ExtendedPushbackReader extendedPushbackReader = new ExtendedPushbackReader(new BufferedReader(tocEntry.getReader()), 16);
            final StringBuffer sb = new StringBuffer();
            char[] array2 = null;
            final String[] array3 = { this.getParameter("secureName"), this.getParameter("logoutURL") };
            int read;
            while ((read = extendedPushbackReader.read()) != -1) {
                if (read == 36 && extendedPushbackReader.at('[')) {
                    try {
                        sb.append("$[");
                        int read2;
                        if ((read2 = extendedPushbackReader.read()) == -1) {
                            break;
                        }
                        if (!extendedPushbackReader.at(']')) {
                            sb.append((char)read2);
                        }
                        else if (read2 == 48) {
                            if (tocEntry2 == null) {
                                continue;
                            }
                            tocEntry2.makeInternal(fileMap);
                            final Reader reader = tocEntry2.getReader();
                            if (array2 == null) {
                                array2 = new char[1024];
                            }
                            int read3;
                            while ((read3 = reader.read(array2)) != -1) {
                                bufferedWriter.write(array2, 0, read3);
                            }
                            reader.close();
                            sb.setLength(0);
                        }
                        else if (49 <= read2 && read2 <= 48 + array3.length) {
                            read2 -= 49;
                            if (array3[read2] != null) {
                                bufferedWriter.write(array3[read2]);
                            }
                            sb.setLength(0);
                        }
                        else {
                            sb.append((char)read2).append(']');
                        }
                        continue;
                    }
                    finally {
                        if (sb.length() > 0) {
                            bufferedWriter.write(sb.toString());
                            sb.setLength(0);
                        }
                    }
                }
                if (read == 60 && extendedPushbackReader.at('a')) {
                    bufferedWriter.write("<a");
                    boolean b2 = false;
                    int read4;
                    while ((read4 = extendedPushbackReader.read()) != -1 && Character.isWhitespace((char)read4)) {
                        b2 = true;
                        bufferedWriter.write(read4);
                    }
                    if (read4 == -1) {
                        break;
                    }
                    extendedPushbackReader.unread(read4);
                    if (!b2 || !extendedPushbackReader.at("href=")) {
                        continue;
                    }
                    bufferedWriter.write("href=");
                    final int read5;
                    if ((read5 = extendedPushbackReader.read()) == -1) {
                        break;
                    }
                    int n3 = -1;
                    if (read5 == 39 || read5 == 34) {
                        n3 = read5;
                        bufferedWriter.write(read5);
                    }
                    else {
                        sb.append((char)read5);
                    }
                    boolean b3 = false;
                    boolean b4 = true;
                    int read6;
                    while ((read6 = extendedPushbackReader.read()) != -1) {
                        if ((n3 == -1) ? (Character.isWhitespace((char)read6) || read6 == 62) : (read6 == n3)) {
                            extendedPushbackReader.unread(read6);
                            break;
                        }
                        if (read6 == 92 || read6 == 47) {
                            b4 = false;
                        }
                        else if (read6 == 37) {
                            b3 = true;
                        }
                        sb.append((char)read6);
                    }
                    if (b4 && sb.length() > 0) {
                        String s2 = sb.toString();
                        if (this.logFiner) {
                            EnvelopeTools.log.finer("Found an href of <" + s2 + ">" + (b3 ? " (maybe URL-encoded)" : ""));
                        }
                        if (b3) {
                            try {
                                s2 = URLCode.decode(s2, 128);
                            }
                            catch (IllegalArgumentException ex) {
                                if (this.logFine) {
                                    EnvelopeTools.log.fine(s2 + ": UTF-8 decoding " + "failed, trying regular decoding");
                                }
                                s2 = URLCode.decode(s2);
                            }
                        }
                        final TOCEntry tocEntry4;
                        if ((tocEntry4 = (TOCEntry)fileMap.get(s2, 6)) != null) {
                            if (this.logFiner) {
                                EnvelopeTools.log.finer("Found href to attachment " + tocEntry4);
                            }
                            bufferedWriter.write(URLCode.encode(tocEntry4.getLocalizedFileName(), n2));
                            sb.setLength(0);
                            tocEntry4.forcePublic(fileMap);
                        }
                        else if (this.logFiner) {
                            EnvelopeTools.log.finer("Found external href");
                        }
                    }
                    if (sb.length() <= 0) {
                        continue;
                    }
                    bufferedWriter.write(sb.toString());
                    sb.setLength(0);
                }
                else {
                    bufferedWriter.write(read);
                }
            }
            extendedPushbackReader.close();
        }
        else {
            bufferedWriter.write("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Frameset//EN\"\n \"http://www.w3.org/TR/html4/frameset.dtd\">\n<html lang=en><head></head>\n<frameset rows='" + frameHeight + ",*'>\n" + "<frame src='" + URLCode.encode(tocEntry.getLocalizedFileName(), n2) + "'>\n" + "<frame src='" + URLCode.encode(tocEntry2.getLocalizedFileName(), n2) + "'>\n" + "</frameset>\n" + "</html>\n");
        }
        if (!b) {
            bufferedWriter.write("<applet height=1 width=1 code='java.applet.Applet' style='position:absolute;left:-500px'></applet>");
        }
        bufferedWriter.close();
        if (this.logFlow) {
            EnvelopeTools.log.exiting("EnvelopeTools", "createMessage", tocEntry3);
        }
        return tocEntry3;
    }
    
    protected void setCookie(final String s, final String s2, final String s3) {
        String parameter = this.getParameter("expiries." + s2);
        if (parameter == null) {
            parameter = "0";
        }
        this.callJavaScript("setcookie", new String[] { s, s3, parameter, "/" });
    }
    
    static {
        log = Logger.global;
        APPDATA15 = "\\AppData\\Local\\Microsoft\\Windows\\" + "Temporary Internet Files" + "\\Virtualized\\C\\";
        gif = new byte[] { 71, 73, 70, 56, 55, 97, 1, 0, 1, 0, 0, 0, 0, 44, 0, 0, 0, 0, 1, 0, 1, 0, 0, 2, 2, 68, 1, 0, 59 };
        fileSep = File.separator;
        callbackNames = new String[] { "error", "launch", "loaded", "progress", "saved", "setcookie", "success" };
    }
    
    public String getSignatureTimestamp() {
        return this.signatureTimestamp;
    }
    
    protected static byte[] SHA1(final byte[] array) {
        return digest("SHA-1", array);
    }
    
    protected String getFrameHeight(final TOCEntry tocEntry, final String s) throws IOException {
        if (this.logFlow) {
            EnvelopeTools.log.entering("EnvelopeTools", "getFrameHeight", new Object[] { tocEntry, s });
        }
        InputStream inputStream = tocEntry.getInputStream();
        BufferedReader bufferedReader = null;
        try {
            final byte[] array = new byte[2];
            String s2;
            if (inputStream.read(array) == array.length && array[0] == -2 && array[1] == -1) {
                s2 = "UnicodeBig";
            }
            else {
                inputStream.close();
                inputStream = tocEntry.getInputStream();
                s2 = "ISO-8859-1";
            }
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream, s2));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                final int index;
                if ((index = line.indexOf("<!--")) >= 0) {
                    final int skipWhitespace = skipWhitespace(line, index + "<!--".length());
                    if (!line.regionMatches(skipWhitespace, "frame-height:", 0, "frame-height:".length())) {
                        continue;
                    }
                    final int skipWhitespace2 = skipWhitespace(line, skipWhitespace + "frame-height:".length());
                    final int skipNonWhitespace = skipNonWhitespace(line, skipWhitespace2);
                    if (skipNonWhitespace > skipWhitespace2) {
                        final String substring = line.substring(skipWhitespace2, skipNonWhitespace);
                        if (this.logFlow) {
                            EnvelopeTools.log.exiting("EnvelopeTools", "getFrameHeight", substring);
                        }
                        return substring;
                    }
                    continue;
                }
            }
        }
        catch (Exception ex) {
            EnvelopeTools.log.log(Level.FINEST, "Unexpected exception", ex);
        }
        finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                }
                catch (IOException ex2) {}
            }
        }
        if (this.logFlow) {
            EnvelopeTools.log.exiting("EnvelopeTools", "getFrameHeight", s);
        }
        return s;
    }
    
    protected byte[] sha1PayloadData(final PayloadInputStream payloadInputStream) throws IOException {
        try {
            final MessageDigest instance = MessageDigest.getInstance("SHA-1");
            final byte[] array = new byte[1024];
            while (true) {
                final int read;
                if ((read = payloadInputStream.read(array)) == -1) {
                    if (!payloadInputStream.next()) {
                        break;
                    }
                    continue;
                }
                else {
                    instance.update(array, 0, read);
                }
            }
            return instance.digest();
        }
        catch (NoSuchAlgorithmException ex) {
            EnvelopeTools.log.severe("Don't have SHA1");
            return null;
        }
    }
    
    public void run() {
        final String parameter = this.getParameter("debug");
        if (parameter != null) {
            try {
                final int int1 = Integer.parseInt(parameter);
                EnvelopeTools.log.setLevel((int1 < 1) ? Level.INFO : ((int1 == 1) ? Level.CONFIG : ((int1 == 2) ? Level.FINE : ((int1 == 3) ? Level.FINER : ((int1 == 4) ? Level.FINEST : Level.ALL)))));
            }
            catch (NumberFormatException ex2) {
                EnvelopeTools.log.warning("Invalid debug level `" + parameter + "'");
            }
        }
        final String parameter2;
        if ((parameter2 = this.getParameter("logLevel")) != null) {
            try {
                EnvelopeTools.log.setLevel(Level.parse(parameter2));
            }
            catch (IllegalArgumentException ex3) {
                EnvelopeTools.log.warning("Invalid log level `" + parameter2 + "'");
            }
        }
        if (this.applet.getAppletContext().toString().startsWith("com.ms.applet.")) {
            try {
                final boolean haveMSSecurity = Class.forName("com.ms.security.PolicyEngine") != null;
                this.haveMSSecurity = haveMSSecurity;
                if (haveMSSecurity) {
                    PolicyEngine.assertPermission(PermissionID.FILEIO);
                    PolicyEngine.assertPermission(PermissionID.NETIO);
                }
            }
            catch (Throwable t) {}
        }
        else {
            this.sunJava = true;
        }
        if (new File("C:\\PostXAppletLog.txt").exists()) {
            try {
                EnvelopeTools.log.addHandler(new FileHandler("C:\\PostXAppletLog.txt", true));
            }
            catch (IOException ex) {
                EnvelopeTools.log.log(Level.WARNING, "Can't add log file handler", ex);
            }
        }
        this.logInfo = EnvelopeTools.log.isLoggable(Level.INFO);
        this.logConfig = EnvelopeTools.log.isLoggable(Level.CONFIG);
        this.logFine = EnvelopeTools.log.isLoggable(Level.FINE);
        this.logFiner = EnvelopeTools.log.isLoggable(Level.FINER);
        this.logFinest = EnvelopeTools.log.isLoggable(Level.FINEST);
        this.logFlow = this.logFiner;
        this.inIE = this.getBooleanParameter("ie");
        this.appletName = this.getParameter("applet.name");
        this.documentBase = this.applet.getDocumentBase();
        this.documentURL = this.getDocumentURL("document.URL");
        final String parameter3;
        if ((parameter3 = this.getParameter("document.charset")) != null) {
            this.documentCharset = parameter3;
        }
        if ((this.userAgent = this.getParameter("ua")) != null) {
            this.OSVista = (this.userAgent.indexOf("windows nt 6") != -1);
        }
        this.OSMacOSX = System.getProperty("os.name").toLowerCase().startsWith("mac os x");
        try {
            this.jsObject = JSObject.getWindow(this.applet);
        }
        catch (Exception ex4) {}
        if (this.logInfo) {
            EnvelopeTools.log.info("documentBase: " + this.documentBase);
            EnvelopeTools.log.info("documentURL: " + this.documentURL);
            EnvelopeTools.log.info("documentCharset: " + this.documentCharset);
            EnvelopeTools.log.info("codeBase: " + this.applet.getCodeBase());
            EnvelopeTools.log.info("appletName: " + this.appletName);
            EnvelopeTools.log.info("Ident: " + "$Id: EnvelopeTools.java,v 1.14 2011/01/10 05:13:52 blm Exp $");
            EnvelopeTools.log.info("Java: " + System.getProperty("java.vendor") + " " + System.getProperty("java.version"));
            EnvelopeTools.log.info("OS: " + System.getProperty("os.name") + " " + System.getProperty("os.version"));
            EnvelopeTools.log.info("userAgent: " + this.userAgent);
            EnvelopeTools.log.info("sunJava: " + this.sunJava);
            EnvelopeTools.log.info("inIE: " + this.inIE);
            EnvelopeTools.log.info("OSMacOSX: " + this.OSMacOSX);
            EnvelopeTools.log.info("OSVista: " + this.OSVista);
            EnvelopeTools.log.info("haveMSSecurity: " + this.haveMSSecurity);
            final Runtime runtime = Runtime.getRuntime();
            EnvelopeTools.log.info("Free memory: " + runtime.freeMemory());
            EnvelopeTools.log.info("Total memory: " + runtime.totalMemory());
        }
        this.callbacks = this.collectCallbacks(EnvelopeTools.callbackNames);
        if (this.sunJava) {
            if (this.inIE && this.documentBase == null) {
                EnvelopeTools.log.severe("IE vs. Sun Java Plugin 1.4.x problem.");
                EnvelopeTools.log.severe("Upgrade Java to 1.4.2_12 or higher.");
                this.reportInternalError("Bad Java version");
                return;
            }
            try {
                this.javaClassVersion = (int)(Object)Float.valueOf(System.getProperty("java.class.version"));
            }
            catch (Exception ex5) {}
            if (this.javaClassVersion < (this.OSVista ? 49 : 47)) {
                EnvelopeTools.log.severe("Your installed Java is too old, upgrade to Java version " + (this.OSVista ? "1.5" : "1.3") + " or higher.");
                this.reportInternalError("Unsupported Java version");
                return;
            }
        }
        final String parameter4 = this.getParameter("rid");
        this.tempBaseDir = this.genBaseDir(false);
        this.permBaseDir = this.genBaseDir(true);
        cleanBaseDir(this.tempBaseDir);
        this.tempDir = this.genTempDir(parameter4);
        String selfTest = "1";
        final String encoding;
        if ((encoding = Encodings.getEncoding(this.documentCharset)) == null) {
            EnvelopeTools.log.severe("Unknown envelope character set " + this.documentCharset);
            this.callJavaScript("error", new String[] { String.valueOf(8) }, "This envelope is not compatible with your platform.", true);
            return;
        }
        this.documentCharset = encoding;
        final String installApplet;
        if (this.getBooleanParameter("applet.install") && this.appletName != null && this.callbacks.get("setcookie") != null && (installApplet = this.installApplet(this.appletName)) != null) {
            if (this.logInfo) {
                EnvelopeTools.log.info("Installed applet at " + installApplet);
            }
            this.setCookie(this.appletName, "tools", installApplet);
        }
        final long currentTimeMillis = System.currentTimeMillis();
        final int intParameter = this.getIntParameter("action", 33);
        String string = null;
        switch (intParameter) {
            case 33: {
                string = "Self test";
                selfTest = this.selfTest();
                break;
            }
            case 0:
            case 1: {
                string = "Envelope " + ((intParameter == 0) ? "open" : "save");
                if (this.documentURL == null) {
                    EnvelopeTools.log.severe("Required " + "document.URL" + " parameter missing");
                    this.reportInternalError("Required parameter missing");
                    return;
                }
                final int processPayload = this.processPayload(intParameter == 1, parameter4);
                String s = null;
                Label_1672: {
                    switch (processPayload) {
                        case 3: {
                            s = "Bad password, please reenter.";
                            break Label_1672;
                        }
                        case 4: {
                            s = "Envelope payload was found but is damaged.";
                            break Label_1672;
                        }
                        case 13: {
                            s = "Envelope is damaged, payload can't be found.";
                            break Label_1672;
                        }
                        case 11: {
                            s = "Internal error, see Java console.";
                            break Label_1672;
                        }
                        case 12: {
                            this.callJavaScript("error", new String[] { String.valueOf(processPayload), this.documentURL }, "Envelope can't be found.", true);
                            break;
                        }
                    }
                    s = null;
                }
                if (s != null) {
                    this.callJavaScript("error", new String[] { String.valueOf(processPayload) }, s, true);
                    break;
                }
                break;
            }
            case 5: {
                string = "Signature verification";
                final int checkSignature;
                if ((checkSignature = this.checkSignature(parameter4)) == 0) {
                    this.callJavaScript("success", new String[] { String.valueOf(checkSignature), this.getSignatureTimestamp() });
                    this.displayStatus("Signature verified.");
                    break;
                }
                this.callJavaScript("error", new String[] { String.valueOf(checkSignature) }, "Bad signature, DON'T OPEN DOCUMENT!", true);
                break;
            }
            case 9: {
                string = "File save";
                String s2;
                if ((s2 = this.getParameter("save.data")) == null) {
                    s2 = URLCode.decode(this.getParameter("save.data.URLEncoded"));
                }
                this.saveFile(this.getParameter("save.name"), s2);
                break;
            }
            case 17: {
                string = "Authenticated and open";
                this.authenticateAndOpen(parameter4);
                break;
            }
            default: {
                string = "Unknown command";
                break;
            }
        }
        if (this.logInfo) {
            EnvelopeTools.log.info(string + " took " + (System.currentTimeMillis() - currentTimeMillis) / 1000.0 + "s");
        }
        this.callJavaScript("loaded", new String[] { selfTest, "1" });
    }
    
    protected static byte[] byteAppend(final byte[] array, final byte[] array2) {
        final byte[] array3 = new byte[array.length + array2.length];
        System.arraycopy(array, 0, array3, 0, array.length);
        System.arraycopy(array2, 0, array3, array.length, array2.length);
        return array3;
    }
    
    protected String installApplet(String safeFilename) {
        safeFilename = safeFilename(safeFilename);
        final String string = safeFilename + (this.sunJava ? ".jar" : ".cab");
        final byte[] url = this.readURL(this.applet.getCodeBase() + "/" + string);
        if (this.haveMSSecurity) {
            try {
                PolicyEngine.assertPermission(PermissionID.FILEIO);
            }
            catch (Exception ex) {}
        }
        try {
            File permBaseDir = this.permBaseDir;
            if (permBaseDir != null) {
                if (this.logFine) {
                    EnvelopeTools.log.fine("Downloading applet to " + new File(permBaseDir, string));
                }
                final FileOutputStream fileOutputStream = new FileOutputStream(new File(permBaseDir, string));
                fileOutputStream.write(url);
                fileOutputStream.close();
                final FileOutputStream fileOutputStream2 = new FileOutputStream(new File(permBaseDir, safeFilename + ".gif"));
                fileOutputStream2.write(EnvelopeTools.gif);
                fileOutputStream2.close();
                if (this.OSVista) {
                    final File realPath = this.getRealPath(permBaseDir, string);
                    if (realPath != null) {
                        EnvelopeTools.log.fine("Mapped:\n  " + permBaseDir + "\nto\n  " + realPath);
                        permBaseDir = realPath;
                    }
                }
                return permBaseDir.getPath();
            }
        }
        catch (Exception ex2) {}
        return null;
    }
    
    private File getRealPath(final File file, final String s) {
        final String path = file.getPath();
        final int index = path.indexOf("\\" + "Local Settings" + "\\");
        if (index != -1 && path.charAt(1) == ':') {
            final File file2 = new File(file, s);
            if (this.javaClassVersion < 50) {
                final File file3 = new File(path.substring(0, index) + EnvelopeTools.APPDATA15 + path.substring(2));
                final File file4 = new File(file3, s);
                if (file4.exists() && file4.length() == file2.length() && file4.lastModified() == file2.lastModified()) {
                    return file3;
                }
            }
            else {
                final File file5 = new File(path.substring(0, index) + "\\AppData\\Local\\Microsoft\\Windows\\" + path.substring(index + "Local Settings".length() + 2));
                final File file6 = new File(file5, s);
                if (file6.exists() && file6.length() == file2.length() && file6.lastModified() == file2.lastModified()) {
                    return file5;
                }
            }
        }
        return null;
    }
    
    protected int checkSignature(final String s) {
        final long currentTimeMillis = System.currentTimeMillis();
        if (this.haveMSSecurity) {
            try {
                PolicyEngine.assertPermission(PermissionID.UI);
                PolicyEngine.assertPermission(PermissionID.FILEIO);
                PolicyEngine.assertPermission(PermissionID.SYSTEM);
            }
            catch (Exception ex5) {}
        }
        PayloadInputStream payloadInputStream;
        try {
            payloadInputStream = new PayloadInputStream(this.documentURL, this.documentCharset);
        }
        catch (Exception ex) {
            if (ex instanceof FileNotFoundException) {
                EnvelopeTools.log.log(Level.SEVERE, "Couldn't open envelope", ex);
                return 12;
            }
            EnvelopeTools.log.log(Level.SEVERE, "Unexpected exception", ex);
            return 11;
        }
        final String sigKeyName = payloadInputStream.getSigKeyName();
        Key key;
        try {
            if (sigKeyName.startsWith("JAR|")) {
                key = new Key(Certificate.getInstance(sigKeyName.substring(4)));
            }
            else {
                this.setStorePath(this.permBaseDir);
                key = new Key(sigKeyName, "");
            }
        }
        catch (Exception ex2) {
            EnvelopeTools.log.log(Level.SEVERE, "Couldn't get certificate", ex2);
            return 11;
        }
        Image image = null;
        if (key.imagedata.length != 0) {
            try {
                image = this.applet.getToolkit().createImage(key.imagedata);
            }
            catch (Exception ex6) {}
        }
        final VerifyWindow verifyWindow = new VerifyWindow(image, this.sunJava, "Signature Verification", "Verifying signature...");
        long long1 = 0L;
        final byte[] signature = payloadInputStream.getSignature();
        try {
            final RSA rsa = new RSA();
            rsa.init(false, key.getModulus(), key.getPublicExponent());
            byte[] processBlock;
            try {
                processBlock = rsa.processBlock(signature, 0, signature.length);
            }
            catch (Exception ex3) {
                EnvelopeTools.log.log(Level.SEVERE, "Can't process block", ex3);
                throw ex3;
            }
            final String bytesToString = StringConversion.bytesToString(processBlock);
            String substring;
            try {
                final int index = bytesToString.indexOf(124);
                long1 = Long.parseLong(bytesToString.substring(0, index));
                substring = bytesToString.substring(index + 1);
            }
            catch (Exception ex4) {
                EnvelopeTools.log.log(Level.SEVERE, "Badly formed signature", ex4);
                throw ex4;
            }
            final byte[] sha1PayloadData = this.sha1PayloadData(payloadInputStream);
            try {
                final long n = System.currentTimeMillis() - currentTimeMillis;
                if (n < 2000L) {
                    Thread.sleep(2000L - n);
                }
            }
            catch (Exception ex7) {}
            if (sha1PayloadData == null) {
                throw new Exception("Can't get data signature");
            }
            if (!substring.equals(StringConversion.bytesToString(sha1PayloadData))) {
                throw new Exception("Signatures don't match");
            }
        }
        catch (Exception ex8) {
            verifyWindow.sigVerified(1, this.signatureTimestamp = null);
            return 6;
        }
        finally {
            if (payloadInputStream != null) {
                try {
                    payloadInputStream.close();
                }
                catch (IOException ex9) {}
            }
        }
        final Date date = new Date(long1);
        verifyWindow.sigVerified(0, this.signatureTimestamp = DateFormat.getDateInstance(1).format(date) + " " + DateFormat.getTimeInstance(1).format(date));
        return 0;
    }
    
    protected String genFileName(String value) {
        if (value == null) {
            value = String.valueOf(System.currentTimeMillis());
        }
        return base64Encode(SHA1(StringConversion.stringToBytes(value))).replace('+', '-').replace('/', '_').replace('=', '!');
    }
    
    public class Key
    {
        public String name;
        public String passwd;
        public int keytype;
        public Date issued;
        public Date expires;
        public String owner;
        public String owneremail;
        public String description;
        public byte[] imagedata;
        private BigInteger modulus;
        private BigInteger pubexp;
        private BigInteger privexp;
        
        public BigInteger getModulus() {
            return this.modulus;
        }
        
        public void encryptPrivateKey(final String s) {
            final byte[] byteArray = this.privexp.toByteArray();
            final byte[] byteAppend = EnvelopeTools.byteAppend(EnvelopeTools.crc32(byteArray), byteArray);
            final ARC4 arc4 = new ARC4(StringConversion.stringToBytes(s), 0, 0);
            final byte[] arraycrypt = arc4.arraycrypt(byteAppend);
            arc4.wipe();
            this.privexp = new BigInteger(arraycrypt);
        }
        
        public String toString() {
            return String.valueOf(this.keytype) + "|" + this.getUTC(this.issued) + "|" + this.getUTC(this.expires) + "|" + this.modulus.toString(16) + "|" + this.pubexp.toString(16) + "|" + this.privexp.toString(16) + "|" + this.toHex(this.owner) + "|" + this.toHex(this.owneremail) + "|" + this.toHex(this.description) + "|" + this.toHex(this.imagedata) + "|";
        }
        
        public Key(final Certificate certificate) {
            EnvelopeTools.this.getClass();
            this.keytype = 0;
            this.issued = null;
            this.expires = null;
            this.owner = "";
            this.owneremail = "";
            this.description = "";
            this.imagedata = new byte[0];
            this.name = certificate.getName();
            this.passwd = "";
            this.keytype = certificate.getKeyType();
            this.issued = certificate.getIssueDate();
            this.expires = certificate.getExpiresDate();
            this.modulus = certificate.getPublicKeyModulus();
            this.pubexp = certificate.getPublicKeyExponent();
            this.privexp = certificate.getPrivateKeyExponent();
            this.owner = certificate.getOwnerName();
            this.owneremail = certificate.getOwnerEmail();
            this.description = certificate.getDescription();
            this.imagedata = certificate.getImageData();
        }
        
        public Key(final String name, final String passwd) throws Exception {
            EnvelopeTools.this.getClass();
            this.keytype = 0;
            this.issued = null;
            this.expires = null;
            this.owner = "";
            this.owneremail = "";
            this.description = "";
            this.imagedata = new byte[0];
            this.name = name;
            this.passwd = passwd;
            if (!this.loadKey()) {
                throw new Exception("Bad password");
            }
        }
        
        public Key(final String name, final String s, final String passwd) {
            EnvelopeTools.this.getClass();
            this.keytype = 0;
            this.issued = null;
            this.expires = null;
            this.owner = "";
            this.owneremail = "";
            this.description = "";
            this.imagedata = new byte[0];
            this.name = name;
            this.passwd = passwd;
            this.fromString(s);
        }
        
        public Key(final String name, final BigInteger modulus, final BigInteger pubexp, final BigInteger privexp, final String passwd) {
            EnvelopeTools.this.getClass();
            this.keytype = 0;
            this.issued = null;
            this.expires = null;
            this.owner = "";
            this.owneremail = "";
            this.description = "";
            this.imagedata = new byte[0];
            this.name = name;
            this.passwd = passwd;
            this.modulus = modulus;
            this.pubexp = pubexp;
            this.privexp = privexp;
        }
        
        public String toHex(final String s) {
            return this.toHex(StringConversion.stringToBytes(s));
        }
        
        public String toHex(final byte[] array) {
            return new BigInteger(array).toString(16);
        }
        
        public boolean loadKey() {
            if (EnvelopeTools.this.haveMSSecurity) {
                try {
                    PolicyEngine.assertPermission(PermissionID.FILEIO);
                }
                catch (Exception ex2) {}
            }
            try {
                final File file = new File(EnvelopeTools.this.storePath, this.name);
                final FileInputStream fileInputStream = new FileInputStream(file);
                int i;
                byte[] array;
                int read;
                for (i = 0, array = new byte[(int)file.length()]; i < array.length; i += read) {
                    if ((read = fileInputStream.read(array, i, array.length - i)) == -1) {
                        throw new Exception("Can't read applet store");
                    }
                }
                fileInputStream.close();
                this.fromString(new String(array, 0));
                return this.keytype != 1 || this.decryptPrivateKey(this.passwd);
            }
            catch (Exception ex) {
                EnvelopeTools.log.log(Level.SEVERE, "error in loadkey", ex);
                return false;
            }
        }
        
        public boolean decryptPrivateKey(final String s) {
            final ARC4 arc4 = new ARC4(StringConversion.stringToBytes(s), 0, 0);
            final byte[] arraycrypt = arc4.arraycrypt(this.privexp.toByteArray());
            arc4.wipe();
            final byte[] checkCRC32 = EnvelopeTools.checkCRC32(arraycrypt);
            if (checkCRC32 == null) {
                EnvelopeTools.log.severe("bad crc");
                return false;
            }
            this.privexp = new BigInteger(checkCRC32);
            return true;
        }
        
        public BigInteger getPublicExponent() {
            return this.pubexp;
        }
        
        public BigInteger getPrivateExponent() {
            return this.privexp;
        }
        
        public String getName() {
            return this.name;
        }
        
        public void saveKey() {
            if (EnvelopeTools.this.haveMSSecurity) {
                try {
                    PolicyEngine.assertPermission(PermissionID.FILEIO);
                }
                catch (Exception ex2) {}
            }
            try {
                if (this.keytype == 1) {
                    this.encryptPrivateKey(this.passwd);
                }
                else {
                    this.privexp = new BigInteger("0");
                }
                final FileOutputStream fileOutputStream = new FileOutputStream(new File(EnvelopeTools.this.storePath, this.name));
                fileOutputStream.write(StringConversion.stringToBytes(this.toString()));
                fileOutputStream.close();
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        
        public String getUTC(final Date date) {
            return Long.toString(date.getTime());
        }
        
        public void fromString(final String s) {
            final String[] split = StringConversion.split(s.trim(), "|");
            this.keytype = Integer.parseInt(split[0]);
            this.issued = new Date(Long.parseLong(split[1]));
            this.expires = new Date(Long.parseLong(split[2]));
            this.modulus = new BigInteger(split[3], 16);
            this.pubexp = new BigInteger(split[4], 16);
            if (!split[5].equals("")) {
                this.privexp = new BigInteger(split[5], 16);
            }
            if (!split[6].equals("")) {
                this.owner = new String(new BigInteger(split[6], 16).toByteArray());
            }
            if (!split[7].equals("")) {
                this.owneremail = new String(new BigInteger(split[7], 16).toByteArray());
            }
            if (!split[8].equals("")) {
                this.description = new String(new BigInteger(split[8], 16).toByteArray());
            }
            if (!split[9].equals("")) {
                this.imagedata = StringConversion.hexStringToBytes(split[9]);
            }
        }
    }
}
