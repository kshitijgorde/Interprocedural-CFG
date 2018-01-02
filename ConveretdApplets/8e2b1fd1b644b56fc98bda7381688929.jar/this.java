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

public class this extends Observable
{
    private static boolean Pwa;
    private static String Qwa;
    private static boolean Rwa;
    private Vector Swa;
    private long Twa;
    private long Uwa;
    private long Vwa;
    private long Wwa;
    private long Xwa;
    private boolean Ywa;
    private boolean iqa;
    
    public static void I(final String qwa) {
        if (qwa == null) {
            return;
        }
        this.Qwa = qwa;
        boolean rwa = false;
        try {
            rwa = (new String(new byte[] { 71, 72, 73 }, this.Qwa).length() > 0);
        }
        catch (UnsupportedEncodingException ex2) {
            if (this.Qwa.equals("ISO8859_1")) {
                try {
                    final String s = new String(new byte[] { 71, 72, 73 }, "8859_1");
                    this.Qwa = "8859_1";
                    rwa = (s.length() > 0);
                }
                catch (UnsupportedEncodingException ex) {
                    ex.printStackTrace();
                }
            }
        }
        this.Rwa = rwa;
    }
    
    public this() {
        this.Swa = new Vector();
        this.Twa = 0L;
        this.Uwa = 0L;
        this.Vwa = 0L;
        this.Wwa = 0L;
        this.Xwa = 0L;
        this.Ywa = false;
        this.iqa = false;
    }
    
    public void _(final boolean iqa) {
        this.iqa = iqa;
    }
    
    public void _(final long vwa) {
        this.Vwa = vwa;
    }
    
    public void notifyObservers(final Object o) {
        this.setChanged();
        super.notifyObservers(o);
    }
    
    public void notifyObservers() {
        if (this.Twa > 0L && (this.Xwa == 0L || this.Wwa > this.Xwa + this.Twa / 50L)) {
            this.Xwa = this.Wwa;
            this.setChanged();
            super.notifyObservers(new Integer((int)(100.0 * this.Wwa / this.Twa)));
        }
    }
    
    public void clear() {
        this.Swa.removeAllElements();
    }
    
    public void J(final String s) {
        this.Swa.addElement(s);
    }
    
    public void b(final String s, final int n) {
        if (n >= 0 && n < this.Swa.size()) {
            this.Swa.setElementAt(s, n);
        }
    }
    
    public String b(final int n) {
        if (n >= 0 && n < this.Swa.size()) {
            return this.Swa.elementAt(n);
        }
        return null;
    }
    
    public void _(final String s, final int n) {
        if (n >= 0 && n <= this.Swa.size()) {
            this.Swa.insertElementAt(s, n);
        }
    }
    
    public int K() {
        return this.Swa.size();
    }
    
    public boolean _(final this this2) {
        this.clear();
        this.Wwa = 0L;
        if (!(this.Ywa = this2.Ywa)) {
            return false;
        }
        this.Twa = this2.Uwa;
        for (int i = 0; i < this2.K(); ++i) {
            final String b = this2.b(i);
            this.Swa.addElement(b);
            this.Wwa += b.length();
        }
        this.Uwa = this.Wwa;
        return this.Ywa;
    }
    
    private byte[] _(final String s) {
        if (this.K() == 0) {
            return null;
        }
        final String text = this.getText();
        final StringBuffer sb = new StringBuffer(text.length() * 2 + 100);
        sb.append(a.g(s));
        sb.append("=");
        sb.append(a.g(text));
        return sb.toString().getBytes("UTF8");
    }
    
    public boolean _(final String s, final String s2) {
        if (this.K() == 0 || s2 == null || s2.length() == 0) {
            return false;
        }
        boolean equals;
        try {
            final byte[] _ = this._(s2);
            if (_ == null) {
                return false;
            }
            if (this.Pwa) {
                System.out.println("Data POST: " + s);
            }
            final URLConnection b = iea.b(s, _.length);
            if (!s.startsWith("file:")) {
                final BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(b.getOutputStream());
                bufferedOutputStream.write(_);
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
    
    public boolean d(final String s) {
        this.clear();
        this.Wwa = 0L;
        if (this.Pwa) {
            System.out.println("Data request: " + s);
        }
        this.Ywa = false;
        URLConnection _ = null;
        try {
            _ = iea._(s, false, false);
            this.Twa = _.getContentLength();
            if (this.Twa == -1L) {
                if (this.Vwa > 0L) {
                    this.Twa = this.Vwa;
                }
                else {
                    this.Twa = this.Uwa;
                }
            }
            this.Xwa = 0L;
            this.Ywa = this._(_.getInputStream(), "UTF8");
            this.Ywa = true;
        }
        catch (IOException ex) {
            ex.printStackTrace();
            this.clear();
            this.Wwa = 0L;
            this.Ywa = false;
        }
        finally {
            if (_ != null) {
                try {
                    _.getInputStream().close();
                }
                catch (IOException ex2) {
                    ex2.printStackTrace();
                }
            }
        }
        return this.Ywa;
    }
    
    private boolean b(final InputStream inputStream) {
        return this._(inputStream, this.Qwa);
    }
    
    private boolean _(final InputStream inputStream, final String s) {
        this.Wwa = 0L;
        if (inputStream == null) {
            return false;
        }
        BufferedReader bufferedReader;
        if (s != null) {
            if (s.equals(this.Qwa)) {
                if (this.Rwa) {
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
            this.Swa.addElement(line);
            this.Wwa += line.length();
            this.notifyObservers();
        }
        if (this.Wwa > 0L) {
            this.Uwa = this.Wwa;
        }
        this.notifyObservers();
        return true;
    }
    
    private long _(final long n) {
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
    
    private boolean _(final URLConnection urlConnection, final BufferedInputStream bufferedInputStream) {
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
    
    public boolean n(final String s) {
        if (this.Pwa) {
            System.out.println("Data request: " + s);
        }
        this.Ywa = false;
        this.clear();
        this.Wwa = 0L;
        BufferedInputStream bufferedInputStream = null;
        InputStream inputStream = null;
        try {
            final URLConnection _ = iea._(s, this.iqa, true);
            bufferedInputStream = new BufferedInputStream(_.getInputStream());
            final boolean _2 = this._(_, bufferedInputStream);
            this.Twa = _.getContentLength();
            if (this.Twa == -1L) {
                if (this.Vwa > 0L) {
                    this.Twa = this.Vwa;
                }
                else {
                    this.Twa = this.Uwa;
                }
            }
            else if (_2) {
                this.Twa = Math.max(0L, this._(this.Twa));
            }
            this.Xwa = 0L;
            if (_2) {
                inputStream = new GZIPInputStream(bufferedInputStream);
                this.Ywa = this._(inputStream);
            }
            else {
                this.Ywa = this.b(bufferedInputStream);
            }
        }
        catch (IOException ex) {
            ex.printStackTrace();
            this.clear();
            this.Wwa = 0L;
            this.Ywa = false;
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
        return this.Ywa;
    }
    
    private boolean _(final InputStream inputStream) {
        this.Wwa = 0L;
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
            ++this.Wwa;
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
                    if (this.Rwa) {
                        sb.append(new String(array, 0, n, this.Qwa));
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
                    if (this.Rwa) {
                        sb.append(new String(array, 0, n, this.Qwa));
                    }
                    else {
                        sb.append(new String(array, 0, n));
                    }
                }
                this.Swa.addElement(sb.toString());
                sb.setLength(0);
                n = 0;
                b2 = true;
                this.notifyObservers();
            }
        }
        if (!b3 && !b2) {
            if (n > 0) {
                if (this.Rwa) {
                    sb.append(new String(array, 0, n, this.Qwa));
                }
                else {
                    sb.append(new String(array, 0, n));
                }
            }
            this.Swa.addElement(sb.toString());
            this.notifyObservers();
        }
        if (this.Wwa > 0L) {
            this.Uwa = this.Wwa;
        }
        this.notifyObservers();
        return true;
    }
    
    public static void X(final boolean pwa) {
        this.Pwa = pwa;
    }
    
    public String getText() {
        if (this.K() == 0) {
            return null;
        }
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < this.K(); ++i) {
            sb.append(this.b(i));
            if (i != this.K() - 1) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }
    
    public String h(final String s) {
        if (this.K() == 0) {
            return null;
        }
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < this.K(); ++i) {
            sb.append(this.b(i));
            if (i != this.K() - 1) {
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
                    this.J(sb.toString());
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
            this.J(sb.toString());
        }
    }
    
    static {
        this.Pwa = false;
        this.Qwa = "ISO8859_1";
        boolean rwa = false;
        try {
            rwa = (new String(new byte[] { 71, 72, 73 }, this.Qwa).length() > 0);
        }
        catch (UnsupportedEncodingException ex) {
            if (this.Qwa.equals("ISO8859_1")) {
                try {
                    final String s = new String(new byte[] { 71, 72, 73 }, "8859_1");
                    this.Qwa = "8859_1";
                    rwa = (s.length() > 0);
                }
                catch (UnsupportedEncodingException ex2) {}
            }
        }
        this.Rwa = rwa;
    }
}
