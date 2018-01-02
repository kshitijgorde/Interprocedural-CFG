// 
// Decompiled by Procyon v0.5.30
// 

package b.a.b;

import java.io.FileInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import b.a.d.c;
import b.a.d.d;
import java.io.FileNotFoundException;
import java.applet.Applet;
import java.io.InputStream;

public class a extends InputStream
{
    protected InputStream a;
    protected static String b;
    protected static boolean c;
    protected static Applet d;
    
    public a(final String s) throws FileNotFoundException {
        this.a(s, null);
    }
    
    public a(final String s, final Class clazz) throws FileNotFoundException {
        this.a(s, clazz);
    }
    
    private void a(final String s, final Class clazz) throws FileNotFoundException {
        String s2 = null;
        String s3 = s;
        if (s.indexOf("_@R") >= 0) {
            s2 = b.a.d.d.a(b.a.d.a.a());
            s3 = b.a.d.c.a(s, "_@R", s2);
        }
        while (true) {
            if (s2 != null) {
                s3 = b.a.d.c.a(s, "_@R", s2);
            }
            this.a = this.b(s3, clazz);
            if (this.a != null) {
                return;
            }
            if (s2 == null) {
                break;
            }
            if (s2.length() == 0) {
                break;
            }
            final int lastIndex = s2.lastIndexOf("_");
            if (lastIndex < 0) {
                break;
            }
            s2 = s2.substring(0, lastIndex);
        }
        throw new FileNotFoundException();
    }
    
    protected InputStream b(String s, final Class clazz) {
        if (b.a.b.a.c) {
            Class<? extends a> class1;
            String s2;
            if ((class1 = (Class<? extends a>)clazz) == null) {
                s2 = "/" + this.a(s, true);
                class1 = this.getClass();
            }
            else {
                s2 = this.a(s, true);
            }
            final InputStream resourceAsStream = class1.getResourceAsStream(s2);
            if (resourceAsStream != null) {
                return resourceAsStream;
            }
        }
        if (clazz != null) {
            final String replace = clazz.getName().replace('.', '/');
            final int lastIndex = replace.lastIndexOf(47);
            if (lastIndex >= 0) {
                if (s.startsWith("$/")) {
                    s = "$/" + replace.substring(0, lastIndex + 1) + s.substring(2);
                }
                else {
                    s = replace.substring(0, lastIndex + 1) + s;
                }
            }
        }
        if (b.a.b.a.d != null) {
            String substring = s;
            if (substring.startsWith("$/")) {
                substring = substring.substring(2);
            }
            try {
                return new URL(b.a.b.a.d.getCodeBase(), substring).openStream();
            }
            catch (IOException ex) {
                return null;
            }
        }
        s = this.a(s);
        try {
            final File file = new File(s);
            file.length();
            return new FileInputStream(file);
        }
        catch (Throwable t) {
            return null;
        }
    }
    
    public static void a(final Applet d) {
        a.d = d;
    }
    
    protected String a(final String s) {
        return this.a(s, false);
    }
    
    protected String a(final String s, final boolean b) {
        if (s == null || !s.startsWith("$/")) {
            return s;
        }
        if (b.a.b.a.b != null) {
            return b.a.b.a.b + s.substring(2);
        }
        if (!b) {
            String s2 = null;
            try {
                s2 = System.getProperty("user.dir");
            }
            catch (Throwable t) {}
            if (s2 != null) {
                if (!s2.endsWith(System.getProperty("file.separator"))) {
                    s2 += System.getProperty("file.separator");
                }
                return s2 + s.substring(2);
            }
        }
        return s.substring(2);
    }
    
    public int available() throws IOException {
        return this.a.available();
    }
    
    public void close() throws IOException {
        this.a.close();
    }
    
    public void mark(final int n) {
        this.a.mark(n);
    }
    
    public boolean markSupported() {
        return this.a.markSupported();
    }
    
    public int read() throws IOException {
        return this.a.read();
    }
    
    public int read(final byte[] array) throws IOException {
        return this.a.read(array);
    }
    
    public int read(final byte[] array, final int n, final int n2) throws IOException {
        return this.a.read(array, n, n2);
    }
    
    public void reset() throws IOException {
        this.a.reset();
    }
    
    public long skip(final long n) throws IOException {
        return this.a.skip(n);
    }
    
    static {
        a.b = null;
        a.c = true;
        a.d = null;
    }
}
