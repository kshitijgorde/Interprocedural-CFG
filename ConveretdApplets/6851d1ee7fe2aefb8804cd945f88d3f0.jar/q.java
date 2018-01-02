import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.GZIPInputStream;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.IOException;
import java.net.URLConnection;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Vector;
import java.util.Observable;

// 
// Decompiled by Procyon v0.5.30
// 

public class q extends Observable
{
    private static boolean Csa;
    private static String Dsa;
    private static boolean Esa;
    private Vector Fsa;
    private long Gsa;
    private long Hsa;
    private long Isa;
    private long Jsa;
    private long Ksa;
    private boolean Lsa;
    private boolean uja;
    private static final byte[] Msa;
    
    public static void M(final String dsa) {
        if (dsa == null) {
            return;
        }
        q.Dsa = dsa;
        boolean esa = false;
        try {
            esa = (new String(new byte[] { 71, 72, 73 }, q.Dsa).length() > 0);
        }
        catch (UnsupportedEncodingException ex2) {
            if (q.Dsa.equals("ISO8859_1")) {
                try {
                    final String s = new String(new byte[] { 71, 72, 73 }, "8859_1");
                    q.Dsa = "8859_1";
                    esa = (s.length() > 0);
                }
                catch (UnsupportedEncodingException ex) {
                    ex.printStackTrace();
                }
            }
        }
        q.Esa = esa;
    }
    
    public q() {
        this.Fsa = new Vector();
        this.Gsa = 0L;
        this.Hsa = 0L;
        this.Isa = 0L;
        this.Jsa = 0L;
        this.Ksa = 0L;
        this.Lsa = false;
        this.uja = false;
    }
    
    public void setUseCache(final boolean uja) {
        this.uja = uja;
    }
    
    public void _(final long isa) {
        this.Isa = isa;
    }
    
    public void notifyObservers(final Object o) {
        this.setChanged();
        super.notifyObservers(o);
    }
    
    public void notifyObservers() {
        if (this.Gsa > 0L && (this.Ksa == 0L || this.Jsa > this.Ksa + this.Gsa / 50L)) {
            this.Ksa = this.Jsa;
            this.setChanged();
            super.notifyObservers(new Integer((int)(100.0 * this.Jsa / this.Gsa)));
        }
    }
    
    public void clear() {
        this.Fsa.removeAllElements();
    }
    
    public void N(final String s) {
        this.Fsa.addElement(s);
    }
    
    public void a(final String s, final int n) {
        if (n >= 0 && n < this.Fsa.size()) {
            this.Fsa.setElementAt(s, n);
        }
    }
    
    public String _(final int n) {
        if (n >= 0 && n < this.Fsa.size()) {
            return this.Fsa.elementAt(n);
        }
        return null;
    }
    
    public void b(final String s, final int n) {
        if (n >= 0 && n <= this.Fsa.size()) {
            this.Fsa.insertElementAt(s, n);
        }
    }
    
    public int l() {
        return this.Fsa.size();
    }
    
    public boolean b(final q q) {
        this.clear();
        this.Jsa = 0L;
        if (!(this.Lsa = q.Lsa)) {
            return false;
        }
        this.Gsa = q.Hsa;
        for (int i = 0; i < q.l(); ++i) {
            final String _ = q._(i);
            this.Fsa.addElement(_);
            this.Jsa += _.length();
        }
        this.Hsa = this.Jsa;
        return this.Lsa;
    }
    
    private byte[] a(final String s) {
        if (this.l() == 0) {
            return null;
        }
        final String text = this.getText();
        final StringBuffer sb = new StringBuffer(text.length() * 2 + 100);
        sb.append(catch.h(s));
        sb.append("=");
        sb.append(catch.h(text));
        return sb.toString().getBytes("UTF8");
    }
    
    public boolean _(final String s, final String s2) {
        if (this.l() == 0 || s2 == null || s2.length() == 0) {
            return false;
        }
        boolean equals;
        try {
            final byte[] a = this.a(s2);
            if (a == null) {
                return false;
            }
            if (q.Csa) {
                System.out.println("Data POST: " + s);
            }
            final URLConnection b = fj.b(s, a.length);
            if (!s.startsWith("file:")) {
                final BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(b.getOutputStream());
                bufferedOutputStream.write(a);
                bufferedOutputStream.flush();
                bufferedOutputStream.close();
            }
            final BufferedInputStream bufferedInputStream = new BufferedInputStream(b.getInputStream());
            final StringBuffer sb = new StringBuffer();
            int read;
            while ((read = bufferedInputStream.read()) != -1) {
                sb.append((char)read);
            }
            bufferedInputStream.close();
            equals = "OK".equals(sb.toString().trim());
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return equals;
    }
    
    public boolean h(final String s) {
        this.clear();
        this.Jsa = 0L;
        if (q.Csa) {
            System.out.println("Data request: " + s);
        }
        this.Lsa = false;
        URLConnection a = null;
        try {
            a = fj.a(s, false, false);
            this.Gsa = a.getContentLength();
            if (this.Gsa == -1L) {
                if (this.Isa > 0L) {
                    this.Gsa = this.Isa;
                }
                else {
                    this.Gsa = this.Hsa;
                }
            }
            this.Ksa = 0L;
            this.Lsa = this._(a.getInputStream(), "UTF8");
            this.Lsa = true;
        }
        catch (IOException ex) {
            ex.printStackTrace();
            this.clear();
            this.Jsa = 0L;
            this.Lsa = false;
        }
        finally {
            if (a != null) {
                try {
                    a.getInputStream().close();
                }
                catch (IOException ex2) {
                    ex2.printStackTrace();
                }
            }
        }
        return this.Lsa;
    }
    
    private boolean a(final InputStream inputStream) {
        return this._(inputStream, q.Dsa);
    }
    
    private boolean _(final InputStream inputStream, final String s) {
        this.Jsa = 0L;
        if (inputStream == null) {
            return false;
        }
        BufferedReader bufferedReader;
        if (s != null) {
            if (s.equals(q.Dsa)) {
                if (q.Esa) {
                    bufferedReader = new BufferedReader(new InputStreamReader(inputStream, s));
                }
                else {
                    bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                }
            }
            else {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream, s));
            }
        }
        else {
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        }
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            this.Fsa.addElement(line);
            this.Jsa += line.length();
            this.notifyObservers();
        }
        if (this.Jsa > 0L) {
            this.Hsa = this.Jsa;
        }
        this.notifyObservers();
        return true;
    }
    
    private long b(final long n) {
        if (n < 50L) {
            return (long)(n / 2.5);
        }
        if (n < 120L) {
            return n;
        }
        if (n < 160L) {
            return (long)(n * 1.6);
        }
        if (n < 200L) {
            return (long)(n * 1.75);
        }
        if (n < 300L) {
            return n * 2L;
        }
        if (n < 400L) {
            return (long)(n * 2.35);
        }
        if (n < 500L) {
            return (long)(n * 2.6);
        }
        if (n < 700L) {
            return (long)(n * 2.9);
        }
        if (n < 900L) {
            return (long)(n * 3.3);
        }
        if (n < 1500L) {
            return (long)(n * 3.5);
        }
        if (n < 4000L) {
            return (long)(n * 3.8);
        }
        if (n < 6000L) {
            return (long)(n * 4.1);
        }
        if (n < 15000L) {
            return (long)(n * 4.6);
        }
        if (n < 20000L) {
            return (long)(n * 5.3);
        }
        if (n < 30000L) {
            return (long)(n * 5.9);
        }
        if (n < 50000L) {
            return (long)(n * 6.7);
        }
        if (n < 80000L) {
            return (long)(n * 7.9);
        }
        if (n < 100000L) {
            return n * 10L;
        }
        return n * 12L;
    }
    
    private boolean a(final URLConnection urlConnection, final BufferedInputStream bufferedInputStream) {
        if (bufferedInputStream.markSupported()) {
            bufferedInputStream.mark(2);
            final int read = bufferedInputStream.read();
            if (read != -1) {
                final int n = bufferedInputStream.read() << 8 | read;
                bufferedInputStream.reset();
                if (n == 35615) {
                    return true;
                }
            }
            else {
                bufferedInputStream.reset();
            }
        }
        final String headerField = urlConnection.getHeaderField("Content-Encoding");
        if (headerField != null && (headerField.indexOf("gzip") != -1 || headerField.indexOf("GZIP") != -1)) {
            return true;
        }
        String s = urlConnection.getURL().getFile();
        final int index = s.indexOf(63);
        if (index != -1) {
            s = s.substring(0, index);
        }
        return s.endsWith(".gz") || s.endsWith(".GZ");
    }
    
    private boolean b(final URLConnection urlConnection, final BufferedInputStream bufferedInputStream) {
        if (bufferedInputStream.markSupported()) {
            bufferedInputStream.mark(q.Msa.length);
            boolean b = true;
            for (int i = 0; i < q.Msa.length; ++i) {
                final int read = bufferedInputStream.read();
                if (read == -1) {
                    b = false;
                    break;
                }
                if (q.Msa[i] != read) {
                    b = false;
                    break;
                }
            }
            bufferedInputStream.reset();
            if (b) {
                return true;
            }
        }
        final String headerField = urlConnection.getHeaderField("Content-Type");
        return headerField != null && headerField.indexOf("application/zip") != -1;
    }
    
    public boolean f(final String s) {
        if (q.Csa) {
            System.out.println("Data request: " + s);
        }
        this.Lsa = false;
        this.clear();
        this.Jsa = 0L;
        BufferedInputStream bufferedInputStream = null;
        InputStream inputStream = null;
        try {
            final URLConnection a = fj.a(s, this.uja, true);
            bufferedInputStream = new BufferedInputStream(a.getInputStream());
            final boolean a2 = this.a(a, bufferedInputStream);
            this.Gsa = a.getContentLength();
            if (this.Gsa == -1L) {
                if (this.Isa > 0L) {
                    this.Gsa = this.Isa;
                }
                else {
                    this.Gsa = this.Hsa;
                }
            }
            else if (a2) {
                this.Gsa = Math.max(0L, this.b(this.Gsa));
            }
            this.Ksa = 0L;
            if (a2) {
                inputStream = new GZIPInputStream(bufferedInputStream);
                this.Lsa = this.b(inputStream);
            }
            else if (this.b(a, bufferedInputStream)) {
                this.Lsa = this.b(bufferedInputStream);
            }
            else {
                this.Lsa = this.a(bufferedInputStream);
            }
        }
        catch (IOException ex) {
            ex.printStackTrace();
            this.clear();
            this.Jsa = 0L;
            this.Lsa = false;
        }
        finally {
            if (inputStream != null) {
                try {
                    ((GZIPInputStream)inputStream).close();
                }
                catch (IOException ex2) {
                    ex2.printStackTrace();
                }
            }
            if (bufferedInputStream != null) {
                try {
                    bufferedInputStream.close();
                }
                catch (IOException ex3) {
                    ex3.printStackTrace();
                }
            }
        }
        return this.Lsa;
    }
    
    public boolean b(final BufferedInputStream bufferedInputStream) {
        ZipInputStream zipInputStream = null;
        try {
            zipInputStream = new ZipInputStream(bufferedInputStream);
            boolean b = false;
            ZipEntry nextEntry;
            while ((nextEntry = zipInputStream.getNextEntry()) != null) {
                if ("data.txt".equals(nextEntry.getName())) {
                    b = true;
                    break;
                }
                zipInputStream.closeEntry();
            }
            if (b) {
                final int n = (int)nextEntry.getSize();
                if (n != -1) {
                    this.Gsa = n;
                }
                this.Lsa = this.b(zipInputStream);
                zipInputStream.closeEntry();
            }
            else {
                this.Lsa = false;
            }
            zipInputStream.close();
        }
        catch (IOException ex) {
            ex.printStackTrace();
            if (zipInputStream != null) {
                try {
                    zipInputStream.close();
                }
                catch (IOException ex2) {}
            }
        }
        return this.Lsa;
    }
    
    private boolean b(final InputStream inputStream) {
        this.Jsa = 0L;
        if (inputStream == null) {
            return false;
        }
        final StringBuffer sb = new StringBuffer();
        sb.setLength(0);
        byte b = 0;
        final byte[] array = new byte[80];
        int n = 0;
        int n2 = 0;
        boolean b2 = false;
        boolean b3 = true;
        byte b4;
        while ((b4 = (byte)inputStream.read()) != -1) {
            ++this.Jsa;
            b3 = false;
            if (n2 != 0 && ((b4 == 10 && b == 13) || (b4 == 13 && b == 10))) {
                b = b4;
            }
            else {
                b = b4;
                b2 = false;
                if (b4 == 10 || b4 == 13) {
                    n2 = 1;
                }
                else {
                    n2 = 0;
                }
                if (n >= array.length) {
                    if (q.Esa) {
                        sb.append(new String(array, 0, n, q.Dsa));
                    }
                    else {
                        sb.append(new String(array, 0, n));
                    }
                    n = 0;
                }
                if (n2 == 0 && n < array.length) {
                    array[n++] = b4;
                }
                if (n2 == 0) {
                    continue;
                }
                if (n > 0) {
                    if (q.Esa) {
                        sb.append(new String(array, 0, n, q.Dsa));
                    }
                    else {
                        sb.append(new String(array, 0, n));
                    }
                }
                this.Fsa.addElement(sb.toString());
                sb.setLength(0);
                n = 0;
                b2 = true;
                this.notifyObservers();
            }
        }
        if (!b3 && !b2) {
            if (n > 0) {
                if (q.Esa) {
                    sb.append(new String(array, 0, n, q.Dsa));
                }
                else {
                    sb.append(new String(array, 0, n));
                }
            }
            this.Fsa.addElement(sb.toString());
            this.notifyObservers();
        }
        if (this.Jsa > 0L) {
            this.Hsa = this.Jsa;
        }
        this.notifyObservers();
        return true;
    }
    
    public static void R(final boolean csa) {
        q.Csa = csa;
    }
    
    public String getText() {
        if (this.l() == 0) {
            return null;
        }
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < this.l(); ++i) {
            sb.append(this._(i));
            if (i != this.l() - 1) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }
    
    public String j(final String s) {
        if (this.l() == 0) {
            return null;
        }
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < this.l(); ++i) {
            sb.append(this._(i));
            if (i != this.l() - 1) {
                sb.append(s);
            }
        }
        return sb.toString();
    }
    
    public void setText(final String s) {
        this.clear();
        if (s == null) {
            return;
        }
        StringBuffer sb = new StringBuffer("");
        int n = 1;
        int n2 = 32;
        for (int i = 0; i < s.length(); ++i) {
            final char char1 = s.charAt(i);
            if (char1 == '\r' || char1 == '\n') {
                if (n == 0 || n2 != 13 || char1 != '\n') {
                    this.N(sb.toString());
                    sb = new StringBuffer("");
                    n = 1;
                }
            }
            else {
                sb.append(char1);
                n = 0;
            }
            n2 = char1;
        }
        if (n == 0) {
            this.N(sb.toString());
        }
    }
    
    static {
        q.Csa = false;
        q.Dsa = "ISO8859_1";
        boolean esa = false;
        try {
            esa = (new String(new byte[] { 71, 72, 73 }, q.Dsa).length() > 0);
        }
        catch (UnsupportedEncodingException ex) {
            if (q.Dsa.equals("ISO8859_1")) {
                try {
                    final String s = new String(new byte[] { 71, 72, 73 }, "8859_1");
                    q.Dsa = "8859_1";
                    esa = (s.length() > 0);
                }
                catch (UnsupportedEncodingException ex2) {}
            }
        }
        q.Esa = esa;
        Msa = new byte[] { 80, 75, 3, 4 };
    }
}
