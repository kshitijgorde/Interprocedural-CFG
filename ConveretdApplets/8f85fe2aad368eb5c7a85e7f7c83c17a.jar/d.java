import java.io.IOException;
import java.io.DataInputStream;
import java.io.InputStream;

// 
// Decompiled by Procyon v0.5.30
// 

public final class d
{
    public int f;
    public i[] a;
    public int b;
    public int g;
    public float b;
    public float a;
    public int e;
    public int d;
    public int[] c;
    public int[] e;
    public int[] d;
    public int[] f;
    public int[] b;
    public int[] g;
    public int c;
    public int a;
    public float d;
    public float c;
    public int[] a;
    public boolean a;
    private char[] a;
    
    public final int a(final char c) {
        if (c < '\u0080') {
            return this.a[c];
        }
        return this.a(c, 0, this.c.length - 1);
    }
    
    private final int a(final int n, final int n2, final int n3) {
        final int n4 = (n2 + n3) / 2;
        if (n == this.c[n4]) {
            return n4;
        }
        if (n2 >= n3) {
            return -1;
        }
        if (n < this.c[n4]) {
            return this.a(n, n2, n4 - 1);
        }
        return this.a(n, n4 + 1, n3);
    }
    
    public final float a(final char c, final char c2) {
        return 0.0f;
    }
    
    public final void a() {
        this.d = 12.0f;
    }
    
    public final void b() {
        this.c = this.d * (this.d / this.a) * 1.2f;
    }
    
    public final float a(final char c) {
        if (c == ' ') {
            return this.a('i');
        }
        final int a;
        if ((a = this.a(c)) == -1) {
            return 0.0f;
        }
        return this.f[a] / this.b * this.d;
    }
    
    public final float a(final String s) {
        float n = 0.0f;
        float n2 = 0.0f;
        char c = '\0';
        final char[] charArray = s.toCharArray();
        for (int i = 0; i < charArray.length; ++i) {
            if (charArray[i] == '\n') {
                if (n > n2) {
                    n2 = n;
                }
                n = 0.0f;
                c = '\0';
            }
            else {
                n += this.a(charArray[i]);
                if (c != '\0') {
                    n += this.a(c, charArray[i]);
                }
                c = charArray[i];
            }
        }
        if (n2 > n) {
            return n2;
        }
        return n;
    }
    
    public final void a(final char c, final float n, final float n2, final float n3, final j j) {
        final int a;
        if ((a = this.a(c)) == -1) {
            return;
        }
        if (!this.a) {
            this.a = true;
        }
        if (j.L == 3) {
            final float n4 = this.e[a] / this.a;
            final float n5 = this.d[a] / this.b;
            final float n6 = this.g[a] / this.b;
            final float n7 = this.b[a] / this.a;
            final int z = j.z;
            final boolean d = j.d;
            j.z = 1;
            j.c = true;
            j.d = false;
            final float n8 = n + n6 * this.d;
            final float n9 = n2 - n7 * this.d;
            final float n10 = n8 + n5 * this.d;
            final float n11 = n9 + n4 * this.d;
            j.c(128);
            j.a(this.a[a]);
            j.a(n8, n9, n3, 0.0f, 0.0f);
            j.a(n8, n11, n3, 0.0f, (float)this.e[a]);
            j.a(n10, n11, n3, this.d[a], (float)this.e[a]);
            j.a(n10, n9, n3, this.d[a], 0.0f);
            j.f();
            j.z = z;
            j.c = false;
            j.d = d;
            return;
        }
        int n12 = (int)n + this.g[a];
        int n13 = (int)n2 - this.b[a];
        int n14 = 0;
        int n15 = 0;
        int n16 = this.d[a];
        int n17 = this.e[a];
        if (n12 >= j.b || n13 >= j.k || n12 + n16 < 0 || n13 + n17 < 0) {
            return;
        }
        if (n12 < 0) {
            n14 = 0 - n12;
            n16 += n12;
            n12 = 0;
        }
        if (n13 < 0) {
            n15 = 0 - n13;
            n17 += n13;
            n13 = 0;
        }
        if (n12 + n16 > j.b) {
            n16 -= n12 + n16 - j.b;
        }
        if (n13 + n17 > j.k) {
            n17 -= n13 + n17 - j.k;
        }
        final int p5 = j.p;
        final int n18 = j.n;
        final int f = j.F;
        final int a2 = j.a;
        final int[] e = this.a[a].e;
        final int[] e2 = j.e;
        for (int i = n15; i < n15 + n17; ++i) {
            for (int k = n14; k < n14 + n16; ++k) {
                final int n20;
                final int n19 = (n20 = a2 * e[i * this.b + k] >> 8) ^ 0xFF;
                final int n21 = e2[(n13 + i - n15) * j.b + (n12 + k - n14)];
                e2[(n13 + i - n15) * j.b + n12 + k - n14] = (0xFF000000 | (n20 * p5 + n19 * (n21 >> 16 & 0xFF) & 0xFF00) << 8 | (n20 * n18 + n19 * (n21 >> 8 & 0xFF) & 0xFF00) | n20 * f + n19 * (n21 & 0xFF) >> 8);
            }
        }
    }
    
    public final void a(final String s, float n, float n2, final float n3, final j j) {
        final float n4 = n;
        int i = 0;
        char c = '\0';
        final int length;
        if ((length = s.length()) > this.a.length) {
            this.a = new char[length + 10];
        }
        s.getChars(0, length, this.a, 0);
        while (i < length) {
            if (this.a[i] == '\n') {
                n = n4;
                n2 += this.c;
                c = '\0';
            }
            else {
                this.a(this.a[i], n, n2, n3, j);
                n += this.a(this.a[i]);
                if (c != '\0') {
                    n += this.a(c, this.a[i]);
                }
                c = this.a[i];
            }
            ++i;
        }
    }
    
    private final void c() {
        this.a = new char[8192];
    }
    
    public d() {
        this.c();
    }
    
    public d(final InputStream inputStream) throws IOException {
        this.c();
        final DataInputStream dataInputStream = new DataInputStream(inputStream);
        this.f = dataInputStream.readInt();
        dataInputStream.readInt();
        this.d = dataInputStream.readInt();
        this.e = dataInputStream.readInt();
        this.b = this.d;
        this.a = this.d;
        this.e = (int)Math.pow(2, Math.ceil(Math.log(this.e) / Math.log(2)));
        final int e = this.e;
        this.g = e;
        this.b = e;
        this.c = dataInputStream.readInt();
        this.a = dataInputStream.readInt();
        this.c = new int[this.f];
        this.e = new int[this.f];
        this.d = new int[this.f];
        this.f = new int[this.f];
        this.b = new int[this.f];
        this.g = new int[this.f];
        this.a = new int[128];
        for (int i = 0; i < 128; ++i) {
            this.a[i] = -1;
        }
        for (int j = 0; j < this.f; ++j) {
            this.c[j] = dataInputStream.readInt();
            this.e[j] = dataInputStream.readInt();
            this.d[j] = dataInputStream.readInt();
            this.f[j] = dataInputStream.readInt();
            this.b[j] = dataInputStream.readInt();
            this.g[j] = dataInputStream.readInt();
            dataInputStream.readInt();
            if (this.c[j] < 128) {
                this.a[this.c[j]] = j;
            }
            if (this.c[j] == 100) {
                this.c = this.b[j];
            }
            if (this.c[j] == 112) {
                this.a = -this.b[j] + this.e[j];
            }
        }
        if (this.c == 0 && this.a == 0) {
            for (int k = 0; k < this.f; ++k) {
                final char c;
                if (!Character.isWhitespace(c = (char)this.c[k]) && c != ' ' && c != '\u2007' && c != '\u202f') {
                    if (this.b[k] > this.c) {
                        this.c = this.b[k];
                    }
                    final int a;
                    if ((a = -this.b[k] + this.e[k]) > this.a) {
                        this.a = a;
                    }
                }
            }
        }
        this.a = new i[this.f];
        for (int l = 0; l < this.f; ++l) {
            this.a[l] = new i(new int[this.b * this.g], this.b, this.g, 4);
            final byte[] array = new byte[this.e[l] * this.d[l]];
            dataInputStream.readFully(array);
            final int n = this.d[l];
            for (int n2 = this.e[l], n3 = 0; n3 < n2; ++n3) {
                for (int n4 = 0; n4 < n; ++n4) {
                    this.a[l].e[n3 * this.b + n4] = (array[n3 * n + n4] & 0xFF);
                }
            }
        }
        this.a = false;
        this.a();
        this.b();
    }
}
