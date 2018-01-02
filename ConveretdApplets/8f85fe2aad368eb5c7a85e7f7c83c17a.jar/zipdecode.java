import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.zip.GZIPInputStream;
import java.net.URLEncoder;
import java.awt.event.FocusListener;

// 
// Decompiled by Procyon v0.5.30
// 

public class zipdecode extends l implements FocusListener
{
    public static final int[] b;
    public static String[] a;
    public int x;
    public int r;
    public int v;
    public int f;
    public int s;
    public int m;
    public k[] a;
    public g[] a;
    public int k;
    public int[] c;
    public String b;
    public char[] a;
    public int w;
    public d a;
    public float c;
    public float f;
    public boolean h;
    public boolean c;
    public boolean e;
    public boolean i;
    public int n;
    public int b;
    public f b;
    public int e;
    public f a;
    public f d;
    public f c;
    public f e;
    public float[] d;
    public float[] b;
    public float[] a;
    public float[] c;
    public int p;
    public int q;
    public g a;
    public float e;
    public float a;
    
    public void setup() {
        this.a(720, 360);
        this.a(this.a = this.a("ScalaSans-Regular-14.vlw"));
        this.c(2);
        this.m = -6710938;
        this.x = -13421773;
        this.r = -10066356;
        this.v = -3421237;
        this.f = -3421237;
        this.s = -154;
        (this.a = new k[6])[0] = new k(this.r, this.m);
        this.a[0].h = 0.5f;
        this.a[0].a();
        for (int i = 1; i < 6; ++i) {
            this.a[i] = new k(this.r, this.v);
            this.a[i].h = 0.5f;
            this.a[i].a();
        }
        new Runnable(this) {
            public final zipdecode a = a;
            
            public final void run() {
                try {
                    final String string = "?" + URLEncoder.encode(System.getProperty("java.version") + '\t' + System.getProperty("java.vendor") + '\t' + System.getProperty("os.name") + '\t' + System.getProperty("os.version") + '\t' + System.getProperty("os.arch"));
                    GZIPInputStream gzipInputStream;
                    if (this.a.f) {
                        final URL url;
                        gzipInputStream = new GZIPInputStream(url.openStream());
                        url = new URL(this.a.getDocumentBase(), "zips.gz" + string);
                    }
                    else {
                        gzipInputStream = new GZIPInputStream(this.a.a("zips.gz"));
                    }
                    final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(gzipInputStream));
                    this.a.a = new g[41556];
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        final String[] a2;
                        final int a = l.a((a2 = l.a(line, (char)9))[0]);
                        final float a3 = l.a(a2[1]);
                        final float a4 = l.a(a2[2]);
                        final String[] a5 = l.a(a2[4], ' ');
                        for (int i = 0; i < a5.length; ++i) {
                            a5[i] = a5[i].charAt(0) + a5[i].substring(1).toLowerCase();
                        }
                        final String a6 = l.a(a5, " ");
                        final String s;
                        if ((s = zipdecode.a[l.a(a2[5])]) == null) {
                            continue;
                        }
                        final Object object;
                        (object = new Object(this.a, a, a6, s, a4, a3) {
                            public int b;
                            public String b;
                            public String a;
                            public float c = c;
                            public float a;
                            public float b = b2;
                            public float d;
                            public int[] a;
                            public int a;
                            public final zipdecode a = a3;
                            
                            public final void a(final float n, final float n2, final float n3, final float n4) {
                                this.b = (this.c - n) / n3;
                                this.d = 1.0f - (this.a - n2) / n4;
                            }
                            
                            public final void b() {
                                if (this.a[this.a.n] == this.a.b) {
                                    if (this.b < this.a.d[this.a.n]) {
                                        this.a.d[this.a.n] = this.b;
                                    }
                                    if (this.d < this.a.b[this.a.n]) {
                                        this.a.b[this.a.n] = this.d;
                                    }
                                    if (this.b > this.a.a[this.a.n]) {
                                        this.a.a[this.a.n] = this.b;
                                    }
                                    if (this.d > this.a.c[this.a.n]) {
                                        this.a.c[this.a.n] = this.d;
                                    }
                                    final zipdecode a = this.a;
                                    ++a.q;
                                    if (this.a.n == 5) {
                                        this.a.a = this;
                                        return;
                                    }
                                }
                                final float b = zipdecode.b(this.a, this.b);
                                final float a2 = zipdecode.a(this.a, this.d);
                                if (b < 0.0f || a2 < 0.0f || b >= this.a.a || a2 >= this.a.i) {
                                    return;
                                }
                                if (this.a.n < 3 || !this.a.h) {
                                    this.a.a[(int)a2 * this.a.a + (int)b] = this.a.a[this.a].a;
                                    return;
                                }
                                this.a.g();
                                this.a.g(this.a.a[this.a].a);
                                if (this.a == this.a.n) {
                                    if (this.a.n == 4) {
                                        this.a.a(this.b % 10, zipdecode.b(this.a, this.b), zipdecode.a(this.a, this.d));
                                    }
                                    else {
                                        this.a.c(b, a2, this.a.b.a, this.a.b.a);
                                    }
                                    return;
                                }
                                this.a.c(b, a2, this.a.b.a - 1.0f, this.a.b.a - 1.0f);
                            }
                            
                            public final void a() {
                                if (this.a.h) {
                                    this.a.g();
                                    this.a.g(this.a.a[this.a].a);
                                    this.a.a(zipdecode.b(this.a, this.b), zipdecode.a(this.a, this.d), 8.0f, 7.0f);
                                }
                                else {
                                    this.a.g();
                                    this.a.g(this.a.a[this.a].a);
                                    this.a.c(zipdecode.b(this.a, this.b), zipdecode.a(this.a, this.d), 3, 3);
                                }
                                float n = zipdecode.a(this.a, this.d) - 8.0f;
                                final float b = zipdecode.b(this.a, this.b);
                                if (n < 20.0f) {
                                    n = zipdecode.a(this.a, this.d) + 20.0f;
                                }
                                if (n > this.a.i - 5) {
                                    n = zipdecode.a(this.a, this.d) - 20.0f;
                                }
                                final String string = this.b + ", " + this.a + "  " + l.a(this.b, 5);
                                if (this.a.h) {
                                    this.a.d(1);
                                    this.a.a(string, b, n);
                                    return;
                                }
                                final float a = this.a.a.a(string);
                                float n2;
                                if (b > this.a.a / 3) {
                                    n2 = b - (a + 8.0f);
                                }
                                else {
                                    n2 = b - 8.0f;
                                }
                                this.a.d(0);
                                this.a.g(this.a.v);
                                this.a.a(string, n2, n);
                            }
                            
                            {
                                this.a = a;
                                this.b = b;
                                this.a = a2;
                                (this.a = new int[6])[5] = b;
                                this.a[4] = this.a[5] / 10;
                                this.a[3] = this.a[4] / 10;
                                this.a[2] = this.a[3] / 10;
                                this.a[1] = this.a[2] / 10;
                            }
                        }).a(-124.62608f, 24.655691f, 57.58532f, 24.331694f);
                        this.a.a[this.a.k] = object;
                        final zipdecode a7 = this.a;
                        ++a7.k;
                    }
                }
                catch (IOException ex) {
                    ex.printStackTrace();
                }
                this.a.e();
            }
            
            {
                new Thread(this).start();
            }
        };
        this.addFocusListener(new a(this));
    }
    
    public void draw() {
        this.f(this.x);
        this.d = 30.0f;
        if (this.h) {
            this.f();
        }
        boolean b = false;
        for (int i = 0; i < 6; ++i) {
            b |= this.a[i].a();
        }
        this.b.a((this.p == 0) ? (this.n - 1) : this.n);
        final boolean b2 = b | this.b.a();
        if (this.n < 1) {
            this.a.a(0.0f);
            this.d.a(0.0f);
            this.c.a(1.0f);
            this.e.a(1.0f);
        }
        else {
            this.a.a(this.d[this.n - 1]);
            this.c.a(this.a[this.n - 1]);
            this.d.a(this.b[this.n - 1]);
            this.e.a(this.c[this.n - 1]);
        }
        if (!this.h) {
            this.a.a = this.a.d;
            this.d.a = this.d.d;
            this.c.a = this.c.d;
            this.e.a = this.e.d;
        }
        final boolean b3 = b2 | this.a.a() | this.d.a() | this.c.a() | this.e.a();
        if (this.k == 41556) {
            if (!b3) {
                ++this.e;
                if (this.e > 20) {
                    this.e();
                    this.e = 0;
                }
            }
            else {
                this.e = 0;
            }
        }
        this.n = this.b.length();
        int a = 0;
        if (this.n > 0) {
            a = l.a(this.b);
        }
        this.b = a;
        this.a = null;
        this.a(3);
        this.b(3);
        this.l();
        for (int j = 0; j < this.k; ++j) {
            this.a[j].b();
        }
        this.b();
        if (this.p == 0) {
            this.g(this.s);
            this.a(this.b, this.c, this.f);
        }
        else if (this.n > 0) {
            if (!this.h && this.n == 4) {
                for (int k = 0; k < this.k; ++k) {
                    if (this.a[k].a == this.n) {
                        this.a[k].b();
                    }
                }
            }
            if (this.a != null) {
                this.a.a();
            }
            this.c = this.e((this.a[this.n] + this.d[this.n]) / 2.0f);
            this.f = this.f(this.c[this.n]) + 20.0f;
            this.c = 40.0f;
            this.f = this.i - 40;
            this.g(this.v);
            this.d(0);
            this.a(this.b, this.c, this.f);
        }
        else {
            this.g(this.f);
            this.d(0);
            String s = "zipdecode by ben fry";
            if (this.k == 41556) {
                if (this.b) {
                    s = "type the digits of a zip code";
                }
                else {
                    s = "click the map image to begin";
                }
            }
            this.a(s, 40.0f, this.i - 40);
        }
        this.d(2);
        this.g(this.h ? this.v : this.r);
        this.a("zoom", this.a - 40, this.i - 40);
        this.d(0);
        if (this.e) {
            this.g(this.v);
            this.a(l.a(this.a(), 0, 1), 40.0f, 30.0f);
        }
    }
    
    private final float e(final float n) {
        if (!this.h) {
            return n * this.a;
        }
        return this.a * (n - this.a.a) / (this.c.a - this.a.a);
    }
    
    private final float f(final float n) {
        if (!this.h) {
            return n * this.i;
        }
        return this.i * (n - this.d.a) / (this.e.a - this.d.a);
    }
    
    public final void l() {
        this.d[this.n] = Float.MAX_VALUE;
        this.b[this.n] = Float.MAX_VALUE;
        this.a[this.n] = -3.4028235E38f;
        this.c[this.n] = -3.4028235E38f;
        this.q = 0;
    }
    
    public final void b() {
        this.p = this.q;
        if (this.n == 0) {
            this.d[0] = 0.0f;
            this.a[0] = 1.0f;
            this.b[0] = 0.0f;
            this.c[0] = 1.0f;
            return;
        }
        if (this.p == 0) {
            this.d[this.n - 1] = this.d[this.n - 2];
            this.b[this.n - 1] = this.b[this.n - 2];
            this.a[this.n - 1] = this.a[this.n - 2];
            this.c[this.n - 1] = this.c[this.n - 2];
            return;
        }
        final float n = this.a[this.n] - this.d[this.n];
        final float n2 = this.c[this.n] - this.b[this.n];
        if (n != 0.0f && n2 != 0.0f) {
            final float n3 = 0.1f;
            final float[] d = this.d;
            final int n4 = this.n;
            d[n4] -= n * n3;
            final float[] a = this.a;
            final int n5 = this.n;
            a[n5] += n * n3;
            final float[] b = this.b;
            final int n6 = this.n;
            b[n6] -= n2 * n3;
            final float[] c = this.c;
            final int n7 = this.n;
            c[n7] += n2 * n3;
            final float n8 = n + n * n3 * 2.0f;
            final float n9 = n2 + n2 * n3 * 2.0f;
            if (n8 / n9 > 1.0f) {
                final float n10 = (n8 - n9) / 2.0f;
                final float[] b2 = this.b;
                final int n11 = this.n;
                b2[n11] -= n10;
                final float[] c2 = this.c;
                final int n12 = this.n;
                c2[n12] += n10;
            }
            else {
                final float n13 = (n9 - n8) / 2.0f;
                final float[] d2 = this.d;
                final int n14 = this.n;
                d2[n14] -= n13;
                final float[] a2 = this.a;
                final int n15 = this.n;
                a2[n15] += n13;
            }
            if (this.n == 1) {
                this.d[0] = (0.0f + this.d[1]) / 2.0f;
                this.b[0] = (0.0f + this.b[1]) / 2.0f;
                this.a[0] = (1.0f + this.a[1]) / 2.0f;
                this.c[0] = (1.0f + this.c[1]) / 2.0f;
            }
            return;
        }
        final float n16 = (this.a[this.n - 1] - this.d[this.n - 1]) / 2.0f;
        final float n17 = (this.c[this.n - 1] - this.b[this.n - 1]) / 2.0f;
        final float n18 = this.d[this.n];
        final float n19 = this.b[this.n];
        this.d[this.n - 1] = n18 - n16;
        this.a[this.n - 1] = n18 + n16;
        this.b[this.n - 1] = n19 - n17;
        this.c[this.n - 1] = n19 + n17;
        this.d[this.n] = this.d[this.n - 1];
        this.a[this.n] = this.a[this.n - 1];
        this.b[this.n] = this.b[this.n - 1];
        this.c[this.n] = this.c[this.n - 1];
    }
    
    public void mousePressed() {
        if (this.t > this.e && this.d < this.a + 5) {
            this.c ^= true;
            this.m();
        }
        if (this.t > this.a - 100 && this.d > this.i - 50) {
            this.h ^= true;
            this.m();
        }
    }
    
    public void keyPressed() {
        boolean b = false;
        if (this.u == 8) {
            --this.w;
            if (this.w < 0) {
                this.w = 0;
            }
            b = true;
        }
        else if (this.u >= 48 && this.u <= 57) {
            if (this.w != 5 && this.p > 0) {
                this.a[this.w++] = (char)this.u;
                b = true;
            }
        }
        else if (this.u == 33) {
            if (this.w > 0) {
                this.a[this.w - 1] = '1';
                b = true;
            }
            else {
                this.a[this.w++] = '1';
                b = true;
            }
        }
        else if (this.u == 64) {
            if (this.w > 0) {
                this.a[this.w - 1] = '2';
                b = true;
            }
            else {
                this.a[this.w++] = '2';
                b = true;
            }
        }
        else if (this.u == 35) {
            if (this.w > 0) {
                this.a[this.w - 1] = '3';
                b = true;
            }
            else {
                this.a[this.w++] = '3';
                b = true;
            }
        }
        else if (this.u == 36) {
            if (this.w > 0) {
                this.a[this.w - 1] = '4';
                b = true;
            }
            else {
                this.a[this.w++] = '4';
                b = true;
            }
        }
        else if (this.u == 37) {
            if (this.w > 0) {
                this.a[this.w - 1] = '5';
                b = true;
            }
            else {
                this.a[this.w++] = '5';
                b = true;
            }
        }
        else if (this.u == 94) {
            if (this.w > 0) {
                this.a[this.w - 1] = '6';
                b = true;
            }
            else {
                this.a[this.w++] = '6';
                b = true;
            }
        }
        else if (this.u == 38) {
            if (this.w > 0) {
                this.a[this.w - 1] = '7';
                b = true;
            }
            else {
                this.a[this.w++] = '7';
                b = true;
            }
        }
        else if (this.u == 42) {
            if (this.w > 0) {
                this.a[this.w - 1] = '8';
                b = true;
            }
            else {
                this.a[this.w++] = '8';
                b = true;
            }
        }
        else if (this.u == 40) {
            if (this.w > 0) {
                this.a[this.w - 1] = '9';
                b = true;
            }
            else {
                this.a[this.w++] = '9';
                b = true;
            }
        }
        else if (this.u == 41) {
            if (this.w > 0) {
                this.a[this.w - 1] = '0';
                b = true;
            }
            else {
                this.a[this.w++] = '0';
                b = true;
            }
        }
        else if (this.u == 103) {
            this.a();
        }
        else if (this.u == 97) {
            this.i ^= true;
        }
        else if (this.u == 102) {
            this.e ^= true;
        }
        else if (this.u == 107) {
            this.c ^= true;
        }
        else if (this.u == 122) {
            this.h ^= true;
            this.m();
        }
        if (b) {
            this.j();
            this.b = new String(this.a, 0, this.w);
            for (int i = 0; i < 6; ++i) {
                this.a[i].b();
            }
            for (int j = this.w; j < 6; ++j) {
                this.a[j].a();
            }
            if (this.w == 0) {
                for (int k = 0; k < this.k; ++k) {
                    this.a[k].a = 0;
                }
                return;
            }
            this.c[this.w] = l.a(this.b);
            for (int l = this.w - 1; l > 0; --l) {
                this.c[l] = this.c[l + 1] / 10;
            }
            for (int n = 0; n < this.k; ++n) {
                this.a[n].a = 0;
                for (int w = this.w; w > 0; --w) {
                    if (this.c[w] == this.a[n].a[w]) {
                        this.a[n].a = w;
                        break;
                    }
                }
            }
        }
    }
    
    public void mouseEntered() {
        l.b("entered");
        this.requestFocus();
    }
    
    public final float a(final float n, final float n2, final float n3, final float n4) {
        final float b = l.b(n2);
        final float b2 = l.b(n);
        final float b3 = l.b(n4);
        final float b4 = l.b(n3);
        return (float)Math.acos(this.a(b) * this.a(b2) * this.a(b3) * this.a(b4) + this.a(b) * this.c(b2) * this.a(b3) * this.c(b4) + this.c(b) * this.c(b3)) * (this.c ? 6378.0f : 3443.9f);
    }
    
    public final void f() {
        final float a;
        final float n = (a = this.a(0.0f, 0.0f, (this.c.a - this.a.a) * 57.58532f, 0.0f)) * (65.0f / this.a);
        int n2 = 0;
        float n3 = Float.MAX_VALUE;
        for (int i = 0; i < zipdecode.b.length; ++i) {
            final float d;
            if ((d = l.d(zipdecode.b[i] - n)) < n3) {
                n2 = i;
                n3 = d;
            }
        }
        final int n4 = zipdecode.b[n2];
        String s;
        if (n4 != 0) {
            s = (this.c ? "km" : "miles");
        }
        else {
            s = (this.c ? "km" : "mile");
        }
        final String string = new StringBuffer().append(n4).append(' ').append(s).toString();
        this.g(120);
        this.e = this.a - 40;
        this.e -= this.a.a(string);
        this.d(0);
        this.a(string, this.e, 30.0f);
        this.e -= 8.0f;
        final float n5 = this.e - this.a * (n4 / a);
        final float n6 = 24.0f;
        this.a = 30.0f;
        final float n7 = (n6 + this.a) / 2.0f;
        this.e(80);
        this.b(n5, n6, n5, this.a);
        this.b(this.e, n6, this.e, this.a);
        this.b(n5, n7, this.e, n7);
    }
    
    public static final float b(final zipdecode zipdecode, final float n) {
        return zipdecode.e(n);
    }
    
    public static final float a(final zipdecode zipdecode, final float n) {
        return zipdecode.f(n);
    }
    
    private final void k() {
        this.c = new int[6];
        this.b = "";
        this.a = new char[5];
        this.h = false;
        this.c = false;
        this.e = false;
        this.i = false;
        this.b = new f();
        this.e = 0;
        this.a = new f(0.0f);
        this.d = new f(0.0f);
        this.c = new f(1.0f);
        this.e = new f(1.0f);
        this.d = new float[6];
        this.b = new float[6];
        this.a = new float[6];
        this.c = new float[6];
    }
    
    public zipdecode() {
        this.k();
    }
    
    static {
        b = new int[] { 2, 5, 10, 25, 50, 100, 250, 500 };
        (zipdecode.a = new String[79])[1] = "AL";
        zipdecode.a[4] = "AZ";
        zipdecode.a[5] = "AR";
        zipdecode.a[6] = "CA";
        zipdecode.a[8] = "CO";
        zipdecode.a[9] = "CT";
        zipdecode.a[10] = "DE";
        zipdecode.a[11] = "DC";
        zipdecode.a[12] = "FL";
        zipdecode.a[13] = "GA";
        zipdecode.a[16] = "ID";
        zipdecode.a[17] = "IL";
        zipdecode.a[18] = "IN";
        zipdecode.a[19] = "IA";
        zipdecode.a[20] = "KS";
        zipdecode.a[21] = "KY";
        zipdecode.a[22] = "LA";
        zipdecode.a[23] = "ME";
        zipdecode.a[24] = "MD";
        zipdecode.a[25] = "MA";
        zipdecode.a[26] = "MI";
        zipdecode.a[27] = "MN";
        zipdecode.a[28] = "MS";
        zipdecode.a[29] = "MO";
        zipdecode.a[30] = "MT";
        zipdecode.a[31] = "NE";
        zipdecode.a[32] = "NV";
        zipdecode.a[33] = "NH";
        zipdecode.a[34] = "NJ";
        zipdecode.a[35] = "NM";
        zipdecode.a[36] = "NY";
        zipdecode.a[37] = "NC";
        zipdecode.a[38] = "ND";
        zipdecode.a[39] = "OH";
        zipdecode.a[40] = "OK";
        zipdecode.a[41] = "OR";
        zipdecode.a[42] = "PA";
        zipdecode.a[44] = "RI";
        zipdecode.a[45] = "SC";
        zipdecode.a[46] = "SD";
        zipdecode.a[47] = "TN";
        zipdecode.a[48] = "TX";
        zipdecode.a[49] = "UT";
        zipdecode.a[50] = "VT";
        zipdecode.a[51] = "VA";
        zipdecode.a[53] = "WA";
        zipdecode.a[54] = "WV";
        zipdecode.a[55] = "WI";
        zipdecode.a[56] = "WY";
    }
}
