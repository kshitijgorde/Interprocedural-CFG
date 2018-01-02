import java.io.InputStream;
import java.io.InterruptedIOException;
import java.io.IOException;
import java.io.FilterInputStream;

// 
// Decompiled by Procyon v0.5.30
// 

public class i extends FilterInputStream
{
    public int a;
    public byte b;
    public volatile int c;
    public volatile int d;
    public volatile long e;
    public boolean f;
    public boolean g;
    public boolean h;
    public Thread i;
    public Thread j;
    public boolean k;
    public boolean l;
    public byte[] m;
    public int n;
    public int o;
    public int p;
    public int q;
    public int r;
    public int s;
    public int t;
    public n u;
    public short[] v;
    public byte[] w;
    public int x;
    public int y;
    public int z;
    public int aa;
    public e ab;
    
    public void a(final int n) {
        if (this.u != null) {
            this.u.c = n / 100.0;
        }
    }
    
    public i(final e ab) {
        super(ab.t);
        this.a = 0;
        this.b = 127;
        this.c = 1;
        this.d = 0;
        this.e = Long.MAX_VALUE;
        this.f = false;
        this.g = false;
        this.h = false;
        this.i = null;
        this.j = null;
        this.k = false;
        this.l = false;
        this.m = new byte[4];
        this.n = 200;
        this.x = 0;
        this.y = 0;
        this.z = 0;
        this.aa = 0;
        this.ab = ab;
    }
    
    public synchronized void close() throws IOException {
        if (!this.k) {
            super.close();
            this.f = true;
            this.notifyAll();
        }
    }
    
    public synchronized int a() throws IOException {
        int n = 200;
        if (this.ab.al) {
            return 127;
        }
        while (this.ab.aj < 1) {
            this.i = Thread.currentThread();
            if (this.j != null && !this.j.isAlive() && --n < 0) {
                throw new IOException(zkmToString("yl4\u00195Kw+\u0017pG"));
            }
            if (this.f) {
                return -1;
            }
            this.notifyAll();
            try {
                this.wait(50L);
                continue;
            }
            catch (InterruptedException ex) {
                throw new InterruptedIOException();
            }
            break;
        }
        this.b = this.ab.af[this.ab.ah];
        ++this.a;
        final e ab = this.ab;
        --ab.aj;
        final e ab2 = this.ab;
        ++ab2.ah;
        final e ab3 = this.ab;
        ab3.ah %= this.ab.ag;
        return this.b;
    }
    
    public synchronized int read(final byte[] array, int n, int n2) throws IOException {
        if (n2 < 1) {
            return 0;
        }
        if (this.ab.aj < 1 && this.f) {
            return -1;
        }
        if (this.ab.al) {
            this.c = 1;
            for (int i = 0; i < n2; ++i) {
                array[n + i] = 127;
            }
            return n2;
        }
        array[n++] = (byte)this.a();
        --n2;
        final int n3 = (this.ab.aj < n2) ? this.ab.aj : n2;
        if (n3 == 0) {
            return 1;
        }
        int j = n3;
        if (n2 > 400) {
            this.ab.c = true;
        }
        while (j > 0) {
            final int n4 = this.ab.ag - this.ab.ah;
            final int n5 = (j < n4) ? j : n4;
            System.arraycopy(this.ab.af, this.ab.ah, array, n, n5);
            j -= n5;
            n += n5;
            final e ab = this.ab;
            ab.ah += n5;
            final e ab2 = this.ab;
            ab2.ah %= this.ab.ag;
        }
        if (this.c == 1) {
            this.d = this.a;
            this.e = System.currentTimeMillis();
        }
        this.c = 0;
        final e ab3 = this.ab;
        ab3.aj -= n3;
        this.a += n3;
        this.b = array[n - 1];
        return n3 + 1;
    }
    
    public synchronized void a(final byte b) throws IOException {
        this.j = Thread.currentThread();
        while (this.ab.aj >= this.ab.ag) {
            if (this.i != null && !this.i.isAlive()) {
                throw new IOException(zkmToString("yl4\u00195Kw+\u0017pG"));
            }
            this.notifyAll();
            if (this.f) {
                return;
            }
            try {
                this.wait(1000L);
                continue;
            }
            catch (InterruptedException ex) {
                throw new InterruptedIOException();
            }
            break;
        }
        this.ab.af[this.ab.ai] = b;
        final e ab = this.ab;
        ++ab.aj;
        final e ab2 = this.ab;
        ++ab2.ai;
        final e ab3 = this.ab;
        ab3.ai %= this.ab.ag;
    }
    
    public int b() {
        if (this.ab.p.b == null) {
            return 0;
        }
        final int a = this.ab.p.a();
        int n = 0;
        if (a < 1) {
            return 0;
        }
        if (this.ab.aj < 1 && this.f) {
            return -1;
        }
        if (this.ab.al) {
            return 0;
        }
        final int n2 = (this.ab.aj < a) ? this.ab.aj : a;
        if (n2 == 0) {
            return 1;
        }
        int n4;
        e ab;
        e ab2;
        for (int i = n2; i > 0; i -= n4, n += n4, ab = this.ab, ab.ah += n4, ab2 = this.ab, ab2.ah %= this.ab.ag) {
            final int n3 = this.ab.ag - this.ab.ah;
            n4 = ((i < n3) ? i : n3);
            this.ab.p.a(this.ab.af, this.ab.ah * 2, n4 * 2);
        }
        if (this.c == 1) {
            this.d = this.a;
            this.e = System.currentTimeMillis();
        }
        this.c = 0;
        final e ab3 = this.ab;
        ab3.aj -= n2;
        this.a += n2;
        return n2;
    }
    
    public int c() {
        if (this.ab.p.c == null) {
            return 0;
        }
        final int a = this.ab.p.a();
        int n = 0;
        if (a < 1) {
            return 0;
        }
        if (this.ab.aj < 1 && this.f) {
            return -1;
        }
        if (this.ab.al) {
            return 0;
        }
        final int n2 = (this.ab.aj < a) ? this.ab.aj : a;
        if (n2 == 0) {
            return 1;
        }
        int n4;
        e ab;
        e ab2;
        for (int i = n2; i > 0; i -= n4, n += n4, ab = this.ab, ab.ah += n4, ab2 = this.ab, ab2.ah %= this.ab.ag) {
            final int n3 = this.ab.ag - this.ab.ah;
            n4 = ((i < n3) ? i : n3);
            this.ab.p.b(this.ab.af, this.ab.ah * 2, n4 * 2);
        }
        if (this.c == 1) {
            this.d = this.a;
            this.e = System.currentTimeMillis();
        }
        this.c = 0;
        final e ab3 = this.ab;
        ab3.aj -= n2;
        this.a += n2;
        return n2;
    }
    
    public synchronized void a(final short n) throws IOException {
        while (this.ab.aj >= this.ab.ag) {
            this.notifyAll();
            try {
                this.wait(1000L);
                continue;
            }
            catch (InterruptedException ex) {
                throw new InterruptedIOException();
            }
            break;
        }
        this.ab.af[this.ab.ai * 2] = (byte)(n & 0xFF);
        this.ab.af[this.ab.ai * 2 + 1] = (byte)(n >> 8 & 0xFF);
        final e ab = this.ab;
        ++ab.aj;
        final e ab2 = this.ab;
        ++ab2.ai;
        final e ab3 = this.ab;
        ab3.ai %= this.ab.ag;
    }
    
    public int a(final byte[] array, final int n, final int n2) throws IOException {
        int n3;
        if (this.g && !this.h) {
            System.arraycopy(this.ab.ab, this.ab.ac, array, n, n2);
            final e ab = this.ab;
            ab.ac += n2;
            n3 = n2;
        }
        else {
            int n4 = n;
            int read;
            for (int n5 = n2; this.ab.r == 0 && n5 > 0; n5 -= read, n4 += read) {
                read = this.ab.t.read(array, n4, n5);
                if (read == -1) {
                    read = 0;
                    n5 = 0;
                }
                this.x += read;
            }
            n3 = n4 - n;
        }
        for (int i = 0; i < n3 - 7; i += 8) {
            y.a(array, this.ab.s, n + i, 0, 17);
        }
        return n3;
    }
    
    private int a(final InputStream inputStream, final boolean b) throws IOException {
        int n = 0;
        for (int i = 0; i < 2; ++i) {
            n = (n << (i << 3) | inputStream.read());
        }
        if (!b && (n & 0x8000) == 0x8000) {
            n |= 0xFFFF0000;
        }
        return n;
    }
    
    private int b(final InputStream inputStream, final boolean b) throws IOException {
        int n = 0;
        for (int i = 0; i < 4; ++i) {
            n = (n << 8 | (inputStream.read() & 0xFF));
        }
        return n;
    }
    
    public boolean a(final byte[] array) {
        final boolean b = !this.ab.b && !h.h();
        this.s = (((array[4] << 8 & 0xFF00) | (array[5] & 0xFF)) << 16 | ((array[6] << 8 & 0xFF00) | (array[7] & 0xFF)));
        final int n = (array[8] & 0xFF) * 4;
        this.o = (array[9] & 0xFF);
        this.p = n * 1000;
        this.q = ((array[10] << 8 & 0xFF00) | (array[11] & 0xFF));
        this.t = (((array[16] << 8 & 0xFF00) | (array[17] & 0xFF)) << 16 | ((array[18] << 8 & 0xFF00) | (array[19] & 0xFF)));
        if (b) {
            this.t = (int)(this.t / this.q * 8000.0f);
            this.q = 8000;
            this.o = 1;
        }
        this.r = (((array[12] << 8 & 0xFF00) | (array[13] & 0xFF)) << 16 | ((array[14] << 8 & 0xFF00) | (array[15] & 0xFF)));
        this.l = (this.r != 0);
        this.g = true;
        this.h = false;
        return true;
    }
    
    public boolean d() throws IOException {
        final boolean b = !this.ab.b && !h.h();
        Math.log(System.currentTimeMillis());
        final boolean b2 = this.ab.s[0] != 0 || this.ab.s[1] != 0 || this.ab.s[2] != 0 || this.ab.s[3] != 0;
        if (!this.g) {
            super.in.read(this.m);
            if (this.m[0] != 68) {
                return false;
            }
            if (this.m[1] != 78) {
                return false;
            }
            if (this.m[2] != 89) {
                return false;
            }
            if (this.m[3] != 51) {
                return false;
            }
            this.s = this.b(super.in, true);
            final int n = super.in.read() * 4;
            this.o = super.in.read();
            this.q = this.a(super.in, true);
            this.r = this.b(super.in, false);
            this.l = (this.r != 0);
            this.t = this.b(super.in, false);
            if (b) {
                this.t = (int)(this.t / this.q * 8000.0f);
                this.q = 8000;
                this.o = 1;
            }
            System.out.println(zkmToString("zd)\fyLv~\\") + this.t + zkmToString("\tW%\bp\u0013%") + this.q + zkmToString("\tF,\u001d{G`(\u000f/\t") + this.o);
            this.b(super.in, false);
            this.b(super.in, false);
            this.b(super.in, false);
            if (this.l) {
                if ((this.ab.s[0] ^ this.ab.s[1] ^ this.ab.s[2] ^ this.ab.s[3]) != this.r || !b2) {
                    k.a(zkmToString("|k%\u001eyL%0\u00135Yi%\u00055]m!\\fLf1\u000epM%'\u0010|Y+d"));
                    return false;
                }
            }
            else if (b2) {
                k.a(zkmToString("|k%\u001eyL%0\u00135\\v!\\aA`d\u000fpJp6\u0019q\tn!\u0005;"));
                return false;
            }
            this.p = n * 1000;
        }
        this.x = 32;
        this.u = new n(b, this);
        this.w = new byte[this.n * 2];
        this.v = new short[this.n * 100];
        return true;
    }
    
    public boolean e() throws IOException {
        this.n = this.u.a(this.v);
        if (this.n <= 0) {
            return false;
        }
        if (this.ab.p.b == null && this.ab.p.c == null) {
            this.aa += this.a(this.v, this.n);
        }
        else {
            while (this.ab.aj + this.n > this.ab.ag && !this.ab.al) {
                System.out.println(this.ab.aj + this.n + zkmToString("\t;d") + this.ab.ag + zkmToString("\t%") + this.n);
                try {
                    Thread.currentThread();
                    Thread.sleep(200L);
                    continue;
                }
                catch (InterruptedException ex) {
                    throw new InterruptedIOException();
                }
                break;
            }
            for (int i = 0; i < this.n; ++i) {
                this.a(this.v[i]);
            }
            this.aa += this.n;
        }
        ++this.z;
        return true;
    }
    
    public int a(final short[] array, final int n) {
        final double n2 = n / this.q;
        final double n3 = 1.0 / this.q;
        final double n4 = 1.0 / this.ab.ad;
        final int n5 = (int)(n2 * this.ab.ad);
        try {
            while (this.ab.aj + this.n > this.ab.ag && !this.ab.al) {
                try {
                    Thread.currentThread();
                    Thread.sleep(200L);
                    continue;
                }
                catch (InterruptedException ex) {
                    throw new InterruptedIOException();
                }
                break;
            }
            for (int i = 0; i < n5; ++i) {
                this.a(this.b(array[(int)(n4 * i / n3)]));
            }
            return n5;
        }
        catch (IOException ex2) {
            System.out.println(zkmToString("zd)\fyL%6\u0019tM%!\u000egFw"));
            return 0;
        }
    }
    
    private byte b(int n) {
        int n2;
        if (n < 0) {
            n = -n;
            n2 = 128;
        }
        else {
            n2 = 0;
        }
        if (n > 32635) {
            n = 32635;
        }
        n += 132;
        final byte b = this.ab.ae[n >> 7 & 0xFF];
        int n3 = ~(n2 | b << 4 | (n >> b + 3 & 0xF)) & 0xFF;
        if (n3 == 0) {
            n3 = 2;
        }
        return (byte)n3;
    }
    
    private static String zkmToString(final String s) {
        final char[] charArray = s.toCharArray();
        for (int length = charArray.length, i = 0; i < length; ++i) {
            final char[] array = charArray;
            final int n = i;
            final char c = array[n];
            char c2 = '\0';
            switch (i % 5) {
                case 0: {
                    c2 = ')';
                    break;
                }
                case 1: {
                    c2 = '\u0005';
                    break;
                }
                case 2: {
                    c2 = 'D';
                    break;
                }
                case 3: {
                    c2 = '|';
                    break;
                }
                default: {
                    c2 = '\u0015';
                    break;
                }
            }
            array[n] = (char)(c ^ c2);
        }
        return new String(charArray);
    }
}
