import java.net.MalformedURLException;
import java.net.URL;
import java.net.ProtocolException;
import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.DataOutputStream;
import javax.net.ssl.HttpsURLConnection;

// 
// Decompiled by Procyon v0.5.30
// 

public class hb
{
    public static boolean a;
    public static final String[] b;
    private String c;
    private String d;
    private int e;
    private String f;
    private int g;
    private int h;
    private long i;
    public static boolean j;
    private static final String[] z;
    
    public hb(final String s, final String s2, final int n, final String s3, final int n2, final int n3, final String s4) {
        final boolean j = hb.j;
        this.c = hb.z[16];
        System.out.println(hb.z[13] + s);
        System.out.println(hb.z[21] + s2);
        System.out.println(hb.z[19] + n);
        System.out.println(hb.z[17] + s3);
        System.out.println(hb.z[15] + n2);
        System.out.println(hb.z[18] + n3);
        this.b(s);
        this.c(s2);
        this.b(n);
        this.d(s3.substring(0, s3.lastIndexOf(hb.z[14])) + hb.z[20] + s4);
        this.d(n2);
        this.c(n3);
        this.h();
        if (j) {
            int a = q.a;
            q.a = ++a;
        }
    }
    
    protected String a(final int n, final boolean b, final int n2) {
        final boolean j = hb.j;
        System.out.println(hb.z[26] + n);
        final String string = hb.z[23] + this.f() + hb.z[28] + this.g() + hb.z[27] + n + hb.z[25] + (b ? "1" : "0") + hb.z[22] + this.a(n2) + hb.z[24] + this.i();
        if (q.a != 0) {
            hb.j = !j;
        }
        return string;
    }
    
    private int a(final int n) {
        if (n >= 1 && n <= 5) {
            return n;
        }
        System.out.println(hb.z[30] + n + hb.z[29]);
        return 5;
    }
    
    protected void a(final HttpsURLConnection httpsURLConnection, final String s) {
        final boolean j = hb.j;
        try {
            final DataOutputStream dataOutputStream = new DataOutputStream(httpsURLConnection.getOutputStream());
            dataOutputStream.writeBytes(s);
            dataOutputStream.flush();
            dataOutputStream.close();
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpsURLConnection.getInputStream()));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
                if (j) {
                    break;
                }
            }
            bufferedReader.close();
        }
        catch (IOException ex) {
            System.out.println(hb.z[6]);
            ex.printStackTrace();
        }
    }
    
    protected HttpsURLConnection a() {
        final URL a = this.a(false);
        if (a == null) {
            return null;
        }
        System.out.println(hb.z[5] + a.toString());
        HttpsURLConnection httpsURLConnection;
        try {
            httpsURLConnection = (HttpsURLConnection)a.openConnection();
        }
        catch (IOException ex) {
            System.out.println(hb.z[1] + a.toString());
            ex.printStackTrace();
            return null;
        }
        httpsURLConnection.setDoInput(true);
        httpsURLConnection.setDoOutput(true);
        httpsURLConnection.setUseCaches(false);
        try {
            httpsURLConnection.setRequestMethod(hb.z[4]);
        }
        catch (ProtocolException ex2) {
            System.out.println(hb.z[0]);
        }
        httpsURLConnection.setRequestProperty(hb.z[2], hb.z[3]);
        return httpsURLConnection;
    }
    
    protected URL a(final boolean b) {
        URL url;
        try {
            url = (b ? new URL(hb.z[11], this.c(), this.e()) : new URL(this.b(), this.c(), this.d(), this.e()));
        }
        catch (MalformedURLException ex) {
            System.out.println(hb.z[7]);
            System.out.println(hb.z[10] + this.b());
            System.out.println(hb.z[12] + this.c());
            System.out.println(hb.z[9] + this.d());
            System.out.println(hb.z[8] + this.e());
            ex.printStackTrace();
            return null;
        }
        return url;
    }
    
    public static boolean a(final String s) {
        final boolean j = hb.j;
        if (s.trim().equalsIgnoreCase(hb.b[1].trim())) {
            return false;
        }
        boolean b = false;
        int i = 0;
        while (i < hb.b.length) {
            if (s.trim().equalsIgnoreCase(hb.b[i].trim())) {
                b = true;
            }
            ++i;
            if (j) {
                break;
            }
        }
        return b;
    }
    
    public String b() {
        return this.c;
    }
    
    private void b(final String c) {
        this.c = c;
    }
    
    public String c() {
        return this.d;
    }
    
    private void c(final String d) {
        this.d = d;
    }
    
    public int d() {
        return this.e;
    }
    
    private void b(final int e) {
        this.e = e;
    }
    
    public String e() {
        return this.f;
    }
    
    private void d(final String f) {
        this.f = f;
    }
    
    public int f() {
        return this.g;
    }
    
    private void c(final int g) {
        this.g = g;
    }
    
    public int g() {
        return this.h;
    }
    
    private void d(final int h) {
        this.h = h;
    }
    
    public void h() {
        this.i = System.currentTimeMillis();
    }
    
    private int i() {
        return (int)((System.currentTimeMillis() - this.i) / 1000L);
    }
    
    static {
        z = new String[] { z(z("41t\b\u0005\u00010h=\u0012\u0014,i\u001b\u0012\u0016mi\n\u0014\u000b1\u007f.\u0003\u0010&v\u001f\u0003Lj;:\u0019\u0005!w\nW\u0010,;\n\u000f\u0014/r\f\u001e\u0010/bO\u0004\u00017;\u001f\u0018\u00177;\u0002\u0012\u0010+t\u000bY")), z(z("\"\"r\u0003\u0012\u0000co\u0000W\u000b3~\u0001W\u0005cx\u0000\u0019\n&x\u001b\u001e\u000b-;\u001b\u0018D")), z(z("',u\u001b\u0012\n76;\u000e\u0014&")), z(z("\u00053k\u0003\u001e\u0007\"o\u0006\u0018\nlcB\u0000\u001346\t\u0018\u0016.6\u001a\u0005\b&u\f\u0018\u0000&\u007f")), z(z("4\fH;")), z(z("',u\u0001\u0012\u00077r\u0001\u0010D7tO")), z(z(" \u0006MO26\u0011T=MD\u0013i\u0000\u0010\u0016&h\u001c%\u00013t\u001d\u0003\u0001';\t\u0016\r/~\u000bW\u0010,;\u0018\u0005\r7~O\u0004\u00106\u007f\n\u0019\u0010cz\u001b\u0003\u0001.k\u001bW\r-}\u0000\u0005\t\"o\u0006\u0018\nco\u0000W\u0007,u\u0001\u0012\u00077r\u0000\u0019J")), z(z(" \u0006MO26\u0011T=MD\u0013i\u0000\u0010\u0016&h\u001c%\u00013t\u001d\u0003\u00011;\f\u0018\u0011/\u007fO\u0019\u000b7;\t\u0018\u0016.;:%(co\u0000W\u0010+~O\u0004\u00011m\n\u0005J")), z(z("1\u0011ROM")), z(z("4,i\u001bW^")), z(z("41t\u001b\u0018\u0007,wOM")), z(z("\f7o\u001f")), z(z("7&i\u0019\u0012\u0016c!")), z(z("\u00141t\u001b\u0018\u0007,wOJD")), z(z("\u0005 o\u0006\u0001\r7r\n\u0004")), z(z("\u0005 o\u0006\u0001\r7b&3D~;")), z(z("\f7o\u001f\u0004")), z(z("\u0017&u\u000b\u0012\u0016\u0016I&WYc")), z(z("\u00177n\u000b\u0012\n7R+WYc")), z(z("\u0014,i\u001bWYc")), z(z("\u00053k@\u0014\u000b-o\u001d\u0018\blk\u0003\u0002\u0017l")), z(z("\u0017&i\u0019\u0012\u0016c&O")), z(z("B'r\t\u0011\r n\u0003\u0003\u001d\n_R")), z(z("\u00177n\u000b\u0012\n7r\u000bJ")), z(z("B'n\u001d\u0016\u0010*t\u0001J")), z(z("B*h,\u0018\u00161~\f\u0003Y")), z(z("56~\u001c\u0003\r,uO#\u001d3~O\u001e\u0017c")), z(z("B2n\n\u0004\u0010*t\u0001#\u001d3~R")), z(z("B\"x\u001b\u001e\u0012*o\u0016\u001e\u0000~")), z(z("Jc;=\u0012\u00106i\u0001\u001e\n$;!8;\u0007R)1-\u0000N##=cy\u0016W\u0000&}\u000e\u0002\b75")), z(z(" \u0006MO26\u0011T=MD\u0013i\u0000\u0010\u0016&h\u001c%\u00013t\u001d\u0003\u000115\u0019\u0016\b*\u007f\u000e\u0003\u0001\u0007r\t\u0011\r n\u0003\u0003\u001dk2UW*,;\u000b\u001e\u0002%r\f\u0002\b7bO\u001e\u0000c")) };
        hb.a = false;
        b = new String[] { z(z("\u00110~\u001d\u001e\u0000")), z(z("\u0005 o\u0006\u0001\r7b\u0006\u0013")), z(z("\u0017&i\u0019\u0012\u0016")), z(z("\u0014,i\u001b")), z(z("\u00111r")), z(z("\r-o\n\u0005\u0005 o\u0006\u0001\u00057~?\u001b\u00110")), z(z("\u0001-x\u001d\u000e\u00147~\u000b$\u001d.v\n\u0003\u0016*x$\u0012\u001d")) };
    }
    
    private static char[] z(final String s) {
        final char[] charArray = s.toCharArray();
        if (charArray.length < 2) {
            final int n = 0;
            charArray[n] ^= 'w';
        }
        return charArray;
    }
    
    private static String z(final char[] array) {
        final int i = array.length;
        for (int n = 0; i > n; ++n) {
            final int n2 = n;
            final char c = array[n2];
            char c2 = '\0';
            switch (n % 5) {
                case 0: {
                    c2 = 'd';
                    break;
                }
                case 1: {
                    c2 = 'C';
                    break;
                }
                case 2: {
                    c2 = '\u001b';
                    break;
                }
                case 3: {
                    c2 = 'o';
                    break;
                }
                default: {
                    c2 = 'w';
                    break;
                }
            }
            array[n2] = (char)(c ^ c2);
        }
        return new String(array).intern();
    }
}
