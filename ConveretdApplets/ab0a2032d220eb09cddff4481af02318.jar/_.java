import java.util.zip.GZIPInputStream;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.net.URLEncoder;
import java.net.URLConnection;
import java.io.IOException;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Vector;
import java.util.Observable;

// 
// Decompiled by Procyon v0.5.30
// 

public class _ extends Observable
{
    private static boolean GKb;
    private static String HKb;
    private static boolean IKb;
    private String JKb;
    private String KKb;
    private Vector LKb;
    private long MKb;
    private long NKb;
    private long OKb;
    private long PKb;
    private long QKb;
    private boolean RKb;
    private boolean LHb;
    
    public static void I(final String hKb) {
        if (hKb == null) {
            return;
        }
        _.HKb = hKb;
        boolean iKb = false;
        try {
            final String s = new String(new byte[] { 71, 72, 73 }, _.HKb);
            iKb = true;
        }
        catch (UnsupportedEncodingException ex2) {
            if (_.HKb.equals("ISO8859_1")) {
                try {
                    final String s2 = new String(new byte[] { 71, 72, 73 }, "8859_1");
                    _.HKb = "8859_1";
                    iKb = true;
                }
                catch (UnsupportedEncodingException ex) {
                    ex.printStackTrace();
                }
            }
        }
        _.IKb = iKb;
    }
    
    public _() {
        this.LKb = new Vector();
        this.MKb = 0L;
        this.NKb = 0L;
        this.OKb = 0L;
        this.PKb = 0L;
        this.QKb = 0L;
        this.RKb = false;
        this.LHb = false;
    }
    
    public void g(final boolean lHb) {
        this.LHb = lHb;
    }
    
    public void a(final long oKb) {
        this.OKb = oKb;
    }
    
    public void notifyObservers(final Object o) {
        this.setChanged();
        super.notifyObservers(o);
    }
    
    public void notifyObservers() {
        if (this.MKb > 0L && (this.QKb == 0L || this.PKb > this.QKb + this.MKb / 50L)) {
            this.QKb = this.PKb;
            this.setChanged();
            super.notifyObservers(new Integer((int)(100.0 * this.PKb / this.MKb)));
        }
    }
    
    public void clear() {
        this.LKb.removeAllElements();
    }
    
    public void J(final String s) {
        this.LKb.addElement(s);
    }
    
    public void _(final String s, final int n) {
        if (n >= 0 && n < this.LKb.size()) {
            this.LKb.setElementAt(s, n);
        }
    }
    
    public String l(final int n) {
        if (n >= 0 && n < this.LKb.size()) {
            return this.LKb.elementAt(n);
        }
        return null;
    }
    
    public void a(final String s, final int n) {
        if (n >= 0 && n <= this.LKb.size()) {
            this.LKb.insertElementAt(s, n);
        }
    }
    
    public int L() {
        return this.LKb.size();
    }
    
    public boolean b(final _ _) {
        this.clear();
        this.PKb = 0L;
        if (!(this.RKb = _.RKb)) {
            return false;
        }
        this.MKb = _.NKb;
        for (int i = 0; i < _.L(); ++i) {
            final String l = _.l(i);
            this.LKb.addElement(l);
            this.PKb += l.length();
        }
        this.NKb = this.PKb;
        return this.RKb;
    }
    
    private byte[] _(final String s, final String s2, final String s3) {
        if (this.L() == 0) {
            return null;
        }
        final String text = this.getText();
        final StringBuffer sb = new StringBuffer(text.length() * 2 + 100);
        if (s != null && s.length() > 0) {
            sb.append(i.b(s));
            sb.append("=");
            if (s2 != null && s2.length() > 0) {
                sb.append(i.b(s2));
            }
            sb.append("&");
        }
        sb.append(i.b(s3));
        sb.append("=");
        sb.append(i.b(text));
        return sb.toString().getBytes("UTF8");
    }
    
    public boolean a(final String s, final String jKb, final String kKb, final String s2) {
        boolean b = false;
        this.JKb = jKb;
        this.KKb = kKb;
        if (this.L() == 0 || s2 == null || s2.length() == 0) {
            return false;
        }
        BufferedInputStream bufferedInputStream = null;
        try {
            final byte[] _ = this._(jKb, kKb, s2);
            if (_ == null) {
                return false;
            }
            if (_.GKb) {
                System.out.println("Data POST: " + s);
            }
            final URLConnection a = fp.a(s, _.length);
            if (!s.startsWith("file:")) {
                final BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(a.getOutputStream());
                bufferedOutputStream.write(_);
                bufferedOutputStream.flush();
                bufferedOutputStream.close();
            }
            bufferedInputStream = new BufferedInputStream(a.getInputStream());
            final StringBuffer sb = new StringBuffer();
            final StringBuffer sb2 = new StringBuffer();
            int n = 1;
            int read;
            while ((read = bufferedInputStream.read()) != -1) {
                final char c = (char)read;
                if (n != 0) {
                    if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9') || c == '_') {
                        sb.append(c);
                    }
                }
                else if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9') || c == '_') {
                    sb2.append(c);
                }
                if (c == '=') {
                    n = 0;
                }
            }
            if (sb.length() > 0 && sb2.length() > 0) {
                this.JKb = sb.toString();
                this.KKb = sb2.toString();
                b = true;
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        finally {
            if (bufferedInputStream != null) {
                try {
                    bufferedInputStream.close();
                }
                catch (IOException ex2) {
                    ex2.printStackTrace();
                }
            }
        }
        return b;
    }
    
    public boolean b(String string, final String s, final String s2) {
        this.clear();
        this.PKb = 0L;
        if (s == null || s.length() == 0 || s2 == null || s2.length() == 0) {
            return false;
        }
        if (!string.startsWith("file:")) {
            string += "?" + URLEncoder.encode(s) + "=" + URLEncoder.encode(s2);
        }
        if (_.GKb) {
            System.out.println("Data request: " + string);
        }
        this.RKb = false;
        URLConnection a = null;
        try {
            a = fp.a(string, false, false);
            this.MKb = a.getContentLength();
            if (this.MKb == -1L) {
                if (this.OKb > 0L) {
                    this.MKb = this.OKb;
                }
                else {
                    this.MKb = this.NKb;
                }
            }
            this.QKb = 0L;
            this.RKb = this._(a.getInputStream(), "UTF8");
            this.RKb = true;
        }
        catch (IOException ex) {
            ex.printStackTrace();
            this.clear();
            this.PKb = 0L;
            this.RKb = false;
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
        return this.RKb;
    }
    
    private boolean _(final InputStream inputStream) {
        return this._(inputStream, _.HKb);
    }
    
    private boolean _(final InputStream inputStream, final String s) {
        this.PKb = 0L;
        if (inputStream == null) {
            return false;
        }
        BufferedReader bufferedReader;
        if (_.IKb && s != null) {
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream, s));
        }
        else {
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        }
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            this.LKb.addElement(line);
            this.PKb += line.length();
            this.notifyObservers();
        }
        if (this.PKb > 0L) {
            this.NKb = this.PKb;
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
    
    private boolean b(final URLConnection urlConnection) {
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
    
    public boolean d(final String s) {
        if (_.GKb) {
            System.out.println("Data request: " + s);
        }
        this.RKb = false;
        this.clear();
        this.PKb = 0L;
        InputStream inputStream = null;
        try {
            final URLConnection a = fp.a(s, this.LHb, true);
            final boolean b = this.b(a);
            this.MKb = a.getContentLength();
            if (this.MKb == -1L) {
                if (this.OKb > 0L) {
                    this.MKb = this.OKb;
                }
                else {
                    this.MKb = this.NKb;
                }
            }
            else if (b) {
                this.MKb = Math.max(0L, this._(this.MKb));
            }
            this.QKb = 0L;
            if (b) {
                inputStream = new GZIPInputStream(new BufferedInputStream(a.getInputStream()));
                this.RKb = this.a(inputStream);
            }
            else {
                inputStream = a.getInputStream();
                this.RKb = this._(inputStream);
            }
        }
        catch (IOException ex) {
            ex.printStackTrace();
            this.clear();
            this.PKb = 0L;
            this.RKb = false;
        }
        finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                }
                catch (IOException ex2) {
                    ex2.printStackTrace();
                }
            }
        }
        return this.RKb;
    }
    
    private boolean a(final InputStream inputStream) {
        this.PKb = 0L;
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
            ++this.PKb;
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
                    if (_.IKb) {
                        sb.append(new String(array, 0, n, _.HKb));
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
                    if (_.IKb) {
                        sb.append(new String(array, 0, n, _.HKb));
                    }
                    else {
                        sb.append(new String(array, 0, n));
                    }
                }
                this.LKb.addElement(sb.toString());
                sb.setLength(0);
                n = 0;
                b2 = true;
                this.notifyObservers();
            }
        }
        if (!b3 && !b2) {
            if (n > 0) {
                if (_.IKb) {
                    sb.append(new String(array, 0, n, _.HKb));
                }
                else {
                    sb.append(new String(array, 0, n));
                }
            }
            this.LKb.addElement(sb.toString());
            this.notifyObservers();
        }
        if (this.PKb > 0L) {
            this.NKb = this.PKb;
        }
        this.notifyObservers();
        return true;
    }
    
    public static void H(final boolean gKb) {
        _.GKb = gKb;
    }
    
    public String getText() {
        if (this.L() == 0) {
            return null;
        }
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < this.L(); ++i) {
            sb.append(this.l(i));
            if (i != this.L() - 1) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }
    
    public String i(final String s) {
        if (this.L() == 0) {
            return null;
        }
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < this.L(); ++i) {
            sb.append(this.l(i));
            if (i != this.L() - 1) {
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
    
    public String C() {
        return this.JKb;
    }
    
    public String D() {
        return this.KKb;
    }
    
    static {
        _.GKb = false;
        _.HKb = "ISO8859_1";
        boolean iKb = false;
        try {
            final String s = new String(new byte[] { 71, 72, 73 }, _.HKb);
            iKb = true;
        }
        catch (UnsupportedEncodingException ex) {
            if (_.HKb.equals("ISO8859_1")) {
                try {
                    final String s2 = new String(new byte[] { 71, 72, 73 }, "8859_1");
                    _.HKb = "8859_1";
                    iKb = true;
                }
                catch (UnsupportedEncodingException ex2) {}
            }
        }
        _.IKb = iKb;
    }
}
