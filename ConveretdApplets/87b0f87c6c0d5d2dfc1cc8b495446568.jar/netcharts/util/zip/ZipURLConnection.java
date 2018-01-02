// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.util.zip;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.zip.ZipEntry;
import java.io.InputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.zip.ZipFile;
import java.net.URL;
import java.net.URLConnection;

public class ZipURLConnection extends URLConnection
{
    public static final String httpFileSep = "/";
    private URL a;
    private ZipFile b;
    private String c;
    
    public ZipURLConnection(final URL url) throws MalformedURLException {
        super(url);
        this.a = null;
        this.b = null;
        this.c = null;
        this.a(url);
    }
    
    public void connect() throws IOException {
    }
    
    public void finalize() throws IOException {
        if (this.b != null) {
            this.b.close();
        }
    }
    
    public InputStream getInputStream() throws IOException {
        this.getZipFile();
        if (this.b == null) {
            return null;
        }
        final ZipEntry zipEntry = this.getZipEntry();
        if (zipEntry == null) {
            return null;
        }
        return this.b.getInputStream(zipEntry);
    }
    
    public URL getZipFileURL() {
        return this.a;
    }
    
    public ZipFile getZipFile() {
        if (this.b == null) {
            try {
                this.b = new ZipFile(this.a.getFile());
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return this.b;
    }
    
    public String getEntryName() {
        return this.c;
    }
    
    public ZipEntry getZipEntry() {
        this.getZipFile();
        if (this.b != null) {
            return this.b.getEntry(this.c);
        }
        return null;
    }
    
    private void a(final URL url) throws MalformedURLException {
        final String file = url.getFile();
        int index = file.indexOf(33);
        if (index == -1) {
            throw new MalformedURLException("no ! found in a ZIP url spec:" + file);
        }
        this.a = new URL(file.substring(0, index++));
        this.c = null;
        if (++index != file.length()) {
            this.c = file.substring(index, file.length());
        }
        else {
            this.c = null;
        }
    }
    
    public String[] getTopLevelZipEntryNames() {
        this.getZipFile();
        if (this.b == null) {
            return null;
        }
        final Hashtable topLevelZipEntries = this.getTopLevelZipEntries();
        final String[] array = new String[topLevelZipEntries.size()];
        int n = 0;
        final Enumeration<String> keys = topLevelZipEntries.keys();
        while (keys.hasMoreElements()) {
            array[n++] = keys.nextElement();
        }
        return array;
    }
    
    public Hashtable getTopLevelZipEntries() {
        final Hashtable<String, ZipEntry> hashtable = new Hashtable<String, ZipEntry>();
        this.getZipFile();
        if (this.b == null) {
            return hashtable;
        }
        final String[] array = new String[2];
        final Enumeration<? extends ZipEntry> entries = this.b.entries();
        while (entries.hasMoreElements()) {
            final ZipEntry zipEntry = (ZipEntry)entries.nextElement();
            final String[] a = this.a(zipEntry.getName(), "/");
            if (this.c == null || this.c.equals("/")) {
                if (a[0] == null) {
                    hashtable.put(a[1], zipEntry);
                }
                else {
                    final String a2 = this.a(a[0]);
                    if (hashtable.get(a2) != null) {
                        continue;
                    }
                    hashtable.put(a2, new ZipEntry(a2));
                }
            }
            else {
                if (a[0] == null || !a[0].startsWith(this.c)) {
                    continue;
                }
                final int index = a[0].indexOf("/", this.c.length());
                if (index != -1) {
                    final String substring = a[0].substring(this.c.length(), index + 1);
                    if (hashtable.get(substring) != null) {
                        continue;
                    }
                    hashtable.put(substring, new ZipEntry(substring));
                }
                else {
                    if (a[1] == null || hashtable.get(a[1]) != null) {
                        continue;
                    }
                    hashtable.put(a[1], zipEntry);
                }
            }
        }
        return hashtable;
    }
    
    private String[] a(final String s, final String s2) {
        final String[] array = { null, null };
        if (s != null && s.length() > 0) {
            final int lastIndex = s.lastIndexOf(s2);
            if (lastIndex < 0) {
                array[1] = s;
            }
            else if (lastIndex == s.length() - 1) {
                array[0] = s;
            }
            else {
                array[0] = s.substring(0, lastIndex + 1);
                array[1] = s.substring(lastIndex + 1);
            }
        }
        return array;
    }
    
    private String a(final String s) {
        String substring = null;
        if (s != null) {
            final int index = s.indexOf("/", 0);
            if (index != -1) {
                substring = s.substring(0, index + 1);
            }
        }
        return substring;
    }
}
