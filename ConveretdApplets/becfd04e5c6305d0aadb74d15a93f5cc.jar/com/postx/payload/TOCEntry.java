// 
// Decompiled by Procyon v0.5.30
// 

package com.postx.payload;

import com.postx.util.Encodings;
import java.io.DataInputStream;
import java.io.FileInputStream;
import com.postx.util.logging.Level;
import java.security.MessageDigest;
import com.postx.util.StringConversion;
import com.postx.security.SecurityUtils;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.io.FilterOutputStream;
import com.postx.io.EncryptOutputStream;
import com.postx.io.RandomAccessFileOutputStream;
import java.util.Enumeration;
import com.postx.util.FileMap;
import java.io.UnsupportedEncodingException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import com.postx.io.DecryptInputStream;
import com.postx.io.RandomAccessFileInputStream;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.io.File;
import java.util.Hashtable;
import java.security.SecureRandom;
import com.postx.util.logging.Logger;

public class TOCEntry
{
    public static final String Ident = "$Id: TOCEntry.java,v 1.5 2011/01/10 05:13:52 blm Exp $";
    private static final Logger log;
    private static final boolean logFiner;
    private static final SecureRandom rand;
    private static final Hashtable allEntries;
    private String localizedFileName;
    private int type;
    private int flags;
    private int offset;
    private int length;
    private String encoding;
    private String sanitizedFileName;
    private String localEncoding;
    private String localFileName;
    private boolean encrypted;
    private boolean saving;
    private File path;
    private RandomAccessFile file;
    private byte[] key;
    private byte[] IV;
    private boolean forcedPublic;
    private static final int MAGIC_NUMBER = -1050931585;
    private static final short VERSION = 0;
    private static final short NAME_LENGTH = 28;
    
    public InputStream getInputStream() throws IOException {
        this.pos();
        InputStream inputStream = new RandomAccessFileInputStream(this.file);
        if (this.encrypted) {
            inputStream = new DecryptInputStream(inputStream, this.key, this.IV);
        }
        return inputStream;
    }
    
    private void pos() throws IOException {
        this.file.seek(this.saving ? 0L : getHeaderLength());
    }
    
    private static boolean validFileName(final File file, final String s) {
        OutputStream outputStream = null;
        final File file2 = new File(file, s);
        try {
            outputStream = new FileOutputStream(file2);
            if (!file2.exists()) {
                throw new IOException("file doesn't exist after successful creation");
            }
            return true;
        }
        catch (IOException ex) {
            if (TOCEntry.logFiner) {
                TOCEntry.log.finer("File name " + s + " isn't valid: " + ex.getMessage());
            }
        }
        finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                }
                catch (IOException ex2) {}
                file2.delete();
            }
        }
        return false;
    }
    
    public Reader getReader() throws IOException, UnsupportedEncodingException {
        return new InputStreamReader(this.getInputStream(), this.localEncoding);
    }
    
    public void makeInternal(final FileMap fileMap) {
        if (!this.forcedPublic) {
            fileMap.remove(this.localizedFileName, 2);
            fileMap.remove(this.sanitizedFileName, 4);
        }
    }
    
    public File getPath() {
        return this.path;
    }
    
    public static void closeAll() throws IOException {
        synchronized (TOCEntry.allEntries) {
            final Enumeration<TOCEntry> keys = TOCEntry.allEntries.keys();
            while (keys.hasMoreElements()) {
                final TOCEntry tocEntry = keys.nextElement();
                if (tocEntry.saving) {
                    tocEntry.file.close();
                }
            }
        }
        // monitorexit(TOCEntry.allEntries)
    }
    
    private static String safeFileName(final String s) {
        int n = s.lastIndexOf(47) + 1;
        final int n2 = s.lastIndexOf(92) + 1;
        if (n2 > n) {
            n = n2;
        }
        return s.substring(n);
    }
    
    public void forcePublic(final FileMap fileMap) {
        fileMap.put(this.localizedFileName, 2, this);
        fileMap.put(this.sanitizedFileName, 4, this);
        this.forcedPublic = true;
    }
    
    public int getOffset() {
        return this.offset;
    }
    
    public int getLength() {
        return this.length;
    }
    
    public void setLength(final int length) {
        this.length = length;
    }
    
    private static long getHeaderLength() {
        return 26L;
    }
    
    private void genCryptoParams() {
        if (this.key == null) {
            this.key = new byte[32];
            this.IV = new byte[16];
        }
        TOCEntry.rand.nextBytes(this.key);
        TOCEntry.rand.nextBytes(this.IV);
    }
    
    public String getEncoding() {
        return this.encoding;
    }
    
    protected TOCEntry() {
        this.file = null;
        this.key = null;
        this.IV = null;
        this.forcedPublic = false;
    }
    
    public TOCEntry(final FileMap fileMap, final String[] array, final boolean b, final String sanitizedFileName, final String s, final int flags, final boolean b2) {
        this.file = null;
        this.key = null;
        this.IV = null;
        this.forcedPublic = false;
        this.sanitizedFileName = sanitizedFileName;
        this.type = 1;
        this.flags = flags;
        this.init(fileMap, b, b2, array, sanitizedFileName, s);
    }
    
    public TOCEntry(final Object[] array, final FileMap fileMap, final String[] array2, final boolean b, final boolean b2) {
        this.file = null;
        this.key = null;
        this.IV = null;
        this.forcedPublic = false;
        this.sanitizedFileName = (String)array[0];
        this.type = ((Number)array[1]).intValue();
        this.flags = ((Number)array[4]).intValue();
        final Object[] array3 = (Object[])array[5];
        this.offset = ((Number)array3[0]).intValue();
        this.length = ((Number)array3[1]).intValue();
        this.init(fileMap, b, b2, array2, (String)array[6], (String)array[7]);
    }
    
    public String getLocalEncoding() {
        return this.localEncoding;
    }
    
    public OutputStream create() throws IOException {
        this.file = new RandomAccessFile(this.path, "rw");
        if (this.encrypted) {
            this.path.delete();
        }
        if (!this.saving) {
            this.writeHeader();
        }
        if (TOCEntry.logFiner) {
            TOCEntry.log.finer("Created file " + this.localFileName);
        }
        return this.getOutputStream();
    }
    
    public OutputStream getOutputStream() throws IOException {
        this.pos();
        OutputStream outputStream = new RandomAccessFileOutputStream(this.file);
        if (this.encrypted) {
            outputStream = new EncryptOutputStream(outputStream, this.key, this.IV);
        }
        if (this.length == 0) {
            outputStream = new FilterOutputStream(outputStream) {
                private int count;
                
                public void close() throws IOException {
                    TOCEntry.this.setLength(this.count);
                    super.out.close();
                }
                
                {
                    TOCEntry.this.getClass();
                    this.count = 0;
                }
                
                public void write(final int n) throws IOException {
                    super.out.write(n);
                    ++this.count;
                }
                
                public void write(final byte[] array, final int n, final int n2) throws IOException {
                    super.out.write(array, n, n2);
                    this.count += n2;
                }
            };
        }
        return outputStream;
    }
    
    public String toString() {
        final StringBuffer sb = new StringBuffer("<");
        if (this.localizedFileName != null) {
            sb.append("localizedFileName:").append(this.localizedFileName);
        }
        if (sb.length() > 1) {
            sb.append(", ");
        }
        if (this.sanitizedFileName != null) {
            sb.append("sanitizedFileName:").append(this.sanitizedFileName);
        }
        if (sb.length() > 1) {
            sb.append(", ");
        }
        if (this.localFileName != null) {
            sb.append("localFileName:").append(this.localFileName);
        }
        if (sb.length() > 1) {
            sb.append(", ");
        }
        sb.append("type:").append(this.type).append(", flags:").append(this.flags).append(", loc:[").append(this.offset).append(", ").append(this.length).append("]");
        if (this.encoding != null && this.encoding.length() > 0) {
            sb.append(", encoding:").append(this.encoding);
        }
        if (this.encrypted) {
            sb.append(", encrypted");
        }
        if (this.saving) {
            sb.append(", saving");
        }
        return sb.append(">").toString();
    }
    
    public Writer createWriter() throws IOException {
        return new OutputStreamWriter(this.create(), this.localEncoding);
    }
    
    private static String localizeFileName(final FileMap fileMap, final boolean b, final File file, final String s) {
        String s2 = s;
        if ((b && s.indexOf(58) != -1) || !validFileName(file, s)) {
            final StringBuffer sb = new StringBuffer(s);
            final int length = sb.length();
            final char[] array = { 'f', 'f', '\0', 'l', 'l' };
            for (int i = 0; i < length; ++i) {
                final char char1 = sb.charAt(i);
                if (b && char1 == ':') {
                    sb.setCharAt(i, '_');
                }
                else {
                    array[2] = char1;
                    if (!validFileName(file, new String(array))) {
                        sb.setCharAt(i, (char)((char1 == '/') ? 45 : 95));
                    }
                }
            }
            s2 = sb.toString();
        }
        if (fileMap.containsKey(s2, 2)) {
            int n;
            if ((n = s2.lastIndexOf(46)) == -1) {
                n = s2.length();
            }
            final String substring = s2.substring(0, n);
            final String substring2 = s2.substring(n);
            int n2 = 1;
            while (true) {
                s2 = substring + " (" + n2 + ")" + substring2;
                if (!fileMap.containsKey(s2, 2)) {
                    break;
                }
                ++n2;
            }
        }
        if (TOCEntry.logFiner) {
            if (s.equals(s2)) {
                TOCEntry.log.finer("No need to sanitize \"" + s + "\"");
            }
            else {
                TOCEntry.log.finer("Sanitized \"" + s + "\" to \"" + s2 + "\"");
            }
        }
        return s2;
    }
    
    public String getLocalizedFileName() {
        return this.localizedFileName;
    }
    
    private static byte[] genEntryDigest(final String s) {
        final MessageDigest messageDigest = SecurityUtils.getMessageDigest("SHA-1");
        messageDigest.update((byte)(-63));
        messageDigest.update((byte)92);
        messageDigest.update((byte)14);
        messageDigest.update((byte)127);
        messageDigest.update((byte)0);
        messageDigest.update((byte)0);
        messageDigest.update(StringConversion.stringToBytes(s));
        return messageDigest.digest();
    }
    
    static {
        log = Logger.global;
        logFiner = TOCEntry.log.isLoggable(Level.FINER);
        rand = new SecureRandom();
        allEntries = new Hashtable();
    }
    
    public static boolean isEntry(final File file) {
        final String name = file.getName();
        if (name.length() != 28) {
            return false;
        }
        for (int length = name.length(), i = 0; i < length; ++i) {
            final char char1 = name.charAt(i);
            if ((char1 < '0' || '9' < char1) && (char1 < 'A' || 'F' < char1)) {
                return false;
            }
        }
        DataInputStream dataInputStream = null;
        try {
            dataInputStream = new DataInputStream(new FileInputStream(file));
            if (dataInputStream.readInt() != -1050931585 || dataInputStream.readShort() != 0) {
                return false;
            }
            final byte[] genEntryDigest = genEntryDigest(file.getName());
            final byte[] array = new byte[genEntryDigest.length];
            dataInputStream.readFully(array);
            if (!MessageDigest.isEqual(genEntryDigest, array)) {
                return false;
            }
        }
        catch (Exception ex) {
            return false;
        }
        finally {
            if (dataInputStream != null) {
                try {
                    dataInputStream.close();
                }
                catch (Exception ex2) {}
            }
        }
        return true;
    }
    
    private void writeHeader() throws IOException {
        this.file.writeInt(-1050931585);
        this.file.writeShort(0);
        this.file.write(genEntryDigest(this.localFileName));
    }
    
    private void init(final FileMap fileMap, final boolean saving, final boolean b, final String[] array, final String s, final String s2) {
        TOCEntry.allEntries.put(this, this);
        final File file = new File(array[0]);
        this.localizedFileName = (saving ? this.sanitizedFileName : localizeFileName(fileMap, b, file, s));
        this.encoding = ((s2 == null) ? "ISO-8859-1" : s2);
        this.localEncoding = Encodings.getEncoding(this.encoding);
        this.encrypted = !saving;
        this.saving = saving;
        this.localFileName = (saving ? ((array[1] == null) ? this.localizedFileName : safeFileName(array[1])) : genFileName(file));
        this.path = new File(file, this.localFileName);
        if (this.localizedFileName != null) {
            fileMap.put(this.localizedFileName, 2, this);
        }
        if (this.sanitizedFileName != null) {
            fileMap.put(this.sanitizedFileName, 4, this);
        }
        if ((this.flags & 0xA) != 0x0) {
            fileMap.put("main", 1, this);
        }
        if (this.type == 4) {
            fileMap.put("mbar", 1, this);
        }
        if (this.encrypted) {
            this.genCryptoParams();
        }
    }
    
    public void clearCryptoParams() {
        if (this.key != null) {
            this.genCryptoParams();
        }
        this.key = null;
        this.IV = null;
    }
    
    public boolean isSecure() {
        return (this.flags & 0x1) != 0x0;
    }
    
    private static String genFileName(final File file) {
        final byte[] array = new byte[14];
        String bytesToHexString;
        do {
            TOCEntry.rand.nextBytes(array);
            bytesToHexString = StringConversion.bytesToHexString(array);
        } while (new File(file, bytesToHexString).exists());
        return bytesToHexString;
    }
}
