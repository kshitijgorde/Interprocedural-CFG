import java.io.InputStream;
import java.io.InterruptedIOException;
import java.io.IOException;
import java.io.FilterInputStream;

// 
// Decompiled by Procyon v0.5.30
// 

public class f extends FilterInputStream implements b
{
    public volatile int a;
    public volatile int b;
    public volatile int c;
    public volatile long d;
    public byte e;
    public boolean f;
    public Thread g;
    public Thread h;
    public boolean i;
    public boolean j;
    public byte[] k;
    public int l;
    public int m;
    public int n;
    public int o;
    public int p;
    public double q;
    public n r;
    public short[] s;
    public byte[] t;
    public int u;
    public boolean v;
    public boolean w;
    public boolean x;
    public boolean y;
    public int z;
    public int aa;
    public int ab;
    public int ac;
    public int ad;
    public int ae;
    public int af;
    public int ag;
    public c ah;
    public int ai;
    public volatile o[] aj;
    public volatile int ak;
    public volatile int al;
    public int am;
    public int an;
    public int ao;
    public int ap;
    public int aq;
    public int ar;
    public boolean as;
    public int at;
    public short au;
    public int av;
    public int aw;
    
    public f(final c ah) {
        super(ah.a1);
        this.a = 0;
        this.b = 1;
        this.c = 0;
        this.d = Long.MAX_VALUE;
        this.e = 127;
        this.f = false;
        this.g = null;
        this.h = null;
        this.i = false;
        this.j = false;
        this.k = new byte[26];
        this.u = 3;
        this.v = false;
        this.w = false;
        this.x = false;
        this.y = false;
        this.z = 0;
        this.aa = 0;
        this.ab = 0;
        this.ac = 0;
        this.ad = 0;
        this.ae = 0;
        this.af = 0;
        this.ag = 0;
        this.ak = 0;
        this.al = 0;
        this.am = 0;
        this.an = 0;
        this.ao = 0;
        this.as = false;
        this.at = 0;
        this.ah = ah;
    }
    
    public synchronized void close() throws IOException {
        if (!this.i) {
            super.close();
            this.f = true;
            this.notifyAll();
        }
    }
    
    public synchronized int a() throws IOException {
        int n = 200;
        if (this.ah.bc) {
            return this.e;
        }
        while (this.ah.ba < 1) {
            this.g = Thread.currentThread();
            if (this.h != null && !this.h.isAlive() && --n < 0) {
                throw new IOException(zkmToString("1f!}G\u0003}>s\u0002\u000f"));
            }
            if (this.f) {
                return -1;
            }
            this.notifyAll();
            try {
                this.wait(50L);
            }
            catch (InterruptedException ex) {
                throw new InterruptedIOException();
            }
        }
        this.e = this.ah.a5[this.ah.a8];
        if (this.y) {
            this.e = 127;
        }
        ++this.a;
        final c ah = this.ah;
        --ah.ba;
        final c ah2 = this.ah;
        ++ah2.a8;
        final c ah3 = this.ah;
        ah3.a8 %= this.ah.a7;
        return this.e;
    }
    
    public synchronized int read(final byte[] array, int n, int n2) throws IOException {
        if (n2 < 1) {
            return 0;
        }
        if (this.ah.ba < 1 && this.f) {
            return -1;
        }
        if (this.ah.bc) {
            this.b = 1;
            for (int i = 0; i < n2; ++i) {
                array[n + i] = this.e;
            }
            return n2;
        }
        array[n++] = (byte)this.a();
        --n2;
        final int n3 = (this.ah.ba < n2) ? this.ah.ba : n2;
        if (n3 == 0) {
            return 1;
        }
        int j = n3;
        if (n2 > 400) {
            this.ah.c = true;
        }
        while (j > 0) {
            final int n4 = this.ah.a7 - this.ah.a8;
            final int n5 = (j < n4) ? j : n4;
            if (this.y) {
                for (int k = 0; k < n5; ++k) {
                    array[k + n] = 127;
                }
            }
            else {
                System.arraycopy(this.ah.a5, this.ah.a8, array, n, n5);
            }
            j -= n5;
            n += n5;
            final c ah = this.ah;
            ah.a8 += n5;
            final c ah2 = this.ah;
            ah2.a8 %= this.ah.a7;
        }
        if (this.b == 1) {
            this.c = this.a;
            this.d = System.currentTimeMillis();
        }
        this.b = 0;
        final c ah3 = this.ah;
        ah3.ba -= n3;
        this.a += n3;
        this.e = array[n - 1];
        return n3 + 1;
    }
    
    public synchronized void a(final byte b) throws IOException {
        this.h = Thread.currentThread();
        while (this.ah.ba >= this.ah.a7) {
            if (this.g != null && !this.g.isAlive()) {
                throw new IOException(zkmToString("1f!}G\u0003}>s\u0002\u000f"));
            }
            this.notifyAll();
            if (this.f) {
                return;
            }
            try {
                this.wait(1000L);
            }
            catch (InterruptedException ex) {
                throw new InterruptedIOException();
            }
        }
        this.ah.a5[this.ah.a9] = b;
        final c ah = this.ah;
        ++ah.ba;
        final c ah2 = this.ah;
        ++ah2.a9;
        final c ah3 = this.ah;
        ah3.a9 %= this.ah.a7;
    }
    
    public void a(final boolean y) {
        this.y = y;
        if (this.ah.f.b != null) {
            this.ah.f.a(this.y);
        }
    }
    
    public int b() {
        if (this.ah.f.b == null) {
            return 0;
        }
        final int a = this.ah.f.a();
        int n = 0;
        if (a < 1) {
            return 0;
        }
        if (this.ah.ba < 1 && this.f) {
            return -1;
        }
        if (this.ah.bc) {
            this.b = 1;
            return 0;
        }
        final int n2 = (this.ah.ba < a) ? this.ah.ba : a;
        if (n2 == 0) {
            return 1;
        }
        int n4;
        c ah;
        c ah2;
        for (int i = n2; i > 0; i -= n4, n += n4, ah = this.ah, ah.a8 += n4, ah2 = this.ah, ah2.a8 %= this.ah.a7) {
            final int n3 = this.ah.a7 - this.ah.a8;
            n4 = ((i < n3) ? i : n3);
            this.ah.f.a(this.ah.a5, this.ah.a8 * 2, n4 * 2);
        }
        if (this.b == 1) {
            this.c = this.a;
            this.d = System.currentTimeMillis();
        }
        this.b = 0;
        final c ah3 = this.ah;
        ah3.ba -= n2;
        this.a += n2;
        return n2;
    }
    
    public synchronized void a(final short n) throws IOException {
        while (this.ah.ba >= this.ah.a7) {
            this.notifyAll();
            try {
                this.wait(1000L);
            }
            catch (InterruptedException ex) {
                throw new InterruptedIOException();
            }
        }
        this.ah.a5[this.ah.a9 * 2] = (byte)(n & 0xFF);
        this.ah.a5[this.ah.a9 * 2 + 1] = (byte)(n >> 8 & 0xFF);
        final c ah = this.ah;
        ++ah.ba;
        final c ah2 = this.ah;
        ++ah2.a9;
        final c ah3 = this.ah;
        ah3.a9 %= this.ah.a7;
    }
    
    private int a(final InputStream inputStream) throws IOException {
        int n = 0;
        for (int i = 0; i < 4; ++i) {
            n |= inputStream.read() << (i << 3);
        }
        return n;
    }
    
    private int a(final InputStream inputStream, final boolean b) throws IOException {
        int n = 0;
        for (int i = 0; i < 2; ++i) {
            n |= inputStream.read() << (i << 3);
        }
        if (!b && (n & 0x8000) == 0x8000) {
            n |= 0xFFFF0000;
        }
        return n;
    }
    
    public int a(final byte[] array, final int n) {
        return (array[n] << 8 & 0xFF00) | (array[n + 1] & 0xFF);
    }
    
    public int b(final byte[] array, final int n) {
        return (array[n] << 24 & 0xFF000000) | (array[n + 1] << 16 & 0xFF0000) | (array[n + 2] << 8 & 0xFF00) | (array[n + 3] & 0xFF);
    }
    
    public int c(final byte[] array, final int n) {
        return (array[n + 1] << 8 & 0xFF00) | (array[n] & 0xFF);
    }
    
    public int d(final byte[] array, final int n) {
        return (array[n + 3] << 24 & 0xFF000000) | (array[n + 2] << 16 & 0xFF0000) | (array[n + 1] << 8 & 0xFF00) | (array[n + 0] & 0xFF);
    }
    
    public void a(final byte[] array) throws IOException {
        System.arraycopy(array, 0, this.k, 0, 26);
        this.v = true;
    }
    
    public byte[] c() throws IOException {
        final byte[] array = new byte[this.k.length];
        System.arraycopy(this.k, 0, array, 0, 26);
        return array;
    }
    
    public boolean d() throws IOException {
        if (!this.v) {
            super.in.read(this.k, 0, 24);
        }
        if (this.k[0] == 68 && this.k[1] == 78 && this.k[2] == 89 && this.k[3] == 50) {
            this.w = false;
        }
        else {
            if (this.k[0] != 86 || this.k[1] != 67 || this.k[2] != 83) {
                return false;
            }
            this.u = this.k[3] - 48;
            if (this.u < 1 || this.u > 4) {
                return false;
            }
            if (this.u < 2) {
                this.x = false;
            }
            else {
                this.x = true;
            }
            this.w = true;
        }
        if (!this.v && this.w) {
            super.in.read(this.k, 24, 2);
        }
        this.l = this.a(this.k, 4);
        this.m = (this.k[6] & 0xFF);
        this.n = this.a(this.k, 7);
        this.o = this.a(this.k, 9);
        this.q = 0.01 * (this.k[11] & 0xFF);
        this.p = this.b(this.k, 12);
        this.z = 16;
        final boolean b = this.ah.g[0] != 0 || this.ah.g[1] != 0 || this.ah.g[2] != 0 || this.ah.g[3] != 0;
        if (this.u == 4) {
            final int n = this.ah.g[0] ^ this.ah.g[1] ^ this.ah.g[2] ^ this.ah.g[3];
            if (((n >> 16 ^ n) & 0xFFFF) != this.p || !b) {
                k.a(zkmToString("4a0z\u000b\u0004/%wG\u0011c0aG\u0015g48\u0014\u0004l$j\u0002\u0005/2t\u000e\u0011!"));
                return false;
            }
        }
        else if (b) {
            k.a(zkmToString("4a0z\u000b\u0004/%wG\u0014|48\u0013\tjqk\u0002\u0002z#}\u0003Ad4aI"));
            return false;
        }
        this.r = new n(this.l, this.m, this.n, this.q, this.o);
        this.t = new byte[this.l * 2];
        this.s = new short[this.l];
        return this.e();
    }
    
    public boolean e() throws IOException {
        if (this.w) {
            this.ap = this.d(this.k, this.z);
            this.z += 2;
        }
        else {
            this.ap = this.a(this.k, this.z);
        }
        this.z += 2;
        this.aq = this.c(this.k, this.z);
        this.z += 2;
        this.ar = this.d(this.k, this.z);
        this.z += 4;
        if (!this.v && this.u >= 3) {
            this.ah.ao = this.a(super.in, true);
            this.ah.ap = this.a(super.in, true);
            this.ah.aq = new int[this.ah.ao];
            this.ah.ar = new boolean[this.ah.ao];
            for (int i = 0; i < this.ah.ao; ++i) {
                this.ah.aq[i] = this.a(super.in);
                if ((this.ah.aq[i] & Integer.MIN_VALUE) != 0x0) {
                    this.ah.ar[i] = true;
                }
                else {
                    this.ah.ar[i] = false;
                }
                final int[] aq = this.ah.aq;
                final int n = i;
                aq[n] &= Integer.MAX_VALUE;
            }
        }
        if (this.aq > 0) {
            this.ai = this.ah.a7 / 8000 * 1000 / this.aq + this.ah.ac / this.aq + 1;
        }
        if (this.ai < 3) {
            this.ai = 3;
        }
        this.aj = new o[this.ai];
        for (int j = 0; j < this.ai; ++j) {
            this.aj[j] = new o();
        }
        final int ak = this.ak;
        this.f();
        this.aj[ak].c = 0;
        return this.as = true;
    }
    
    private int a(int n, final int n2) {
        n += n2;
        ++this.at;
        if (this.at == 3) {
            this.at = 0;
            if (n2 == 33 || n2 == 83 || n2 == 333) {
                ++n;
            }
        }
        else if (n2 == 41 || n2 == 66 || n2 == 166) {
            ++n;
        }
        return n;
    }
    
    public boolean f() throws IOException {
        int n;
        if (this.w) {
            n = this.a(super.in);
            if (n > 65536) {
                System.out.println(zkmToString("#f68!\u0013n<}GI") + n + zkmToString("H/0lG") + this.z);
            }
            this.z += 2;
        }
        else {
            n = this.a(super.in, true);
        }
        if (n < 0) {
            return false;
        }
        final int a = this.a(super.in, true);
        this.au = (short)(a & 0x3FFF);
        this.z += 4;
        this.ag = 0;
        while (this.aj[this.ak].d == 1) {
            try {
                Thread.currentThread();
                Thread.sleep(50L);
                ++this.ag;
            }
            catch (InterruptedException ex) {
                throw new InterruptedIOException();
            }
        }
        final int n2 = n;
        if (n2 < 0) {
            return false;
        }
        this.aj[this.ak].a(n2);
        this.aj[this.ak].e = a;
        this.au = (short)(this.aj[this.ak].e & 0x3FFF);
        this.ae = this.a(this.ae, this.aq);
        this.aj[this.ak].c = this.ac * this.l / 8 + 0;
        if (n2 == 0) {
            return true;
        }
        final byte[] a2 = this.aj[this.ak].a;
        int n3 = 0;
        int read;
        for (int i = n2; i > 0; i -= read, n3 += read) {
            read = super.in.read(a2, n3, i);
            if (read == -1) {
                System.out.println(zkmToString("\u000ba\u000ep\u0002\u0000kl") + this.am + zkmToString("M/&p\u0002\u0013jl") + n3 + zkmToString("M/#}\n\u0000f?q\t\u00062") + i);
                throw new IOException(zkmToString("5`>8\u000b\b{%t\u0002Ak0l\u0006A}4l\u0012\u0013a4|G\u0003j7w\u0015\u0004/\u0014W4"));
            }
            this.z += read;
        }
        if (this.u == 4) {
            for (int n4 = n3 - 16, j = 0; j < n4; j += 32) {
                l.a(a2, this.ah.g, j + 4, 0, 17);
                l.a(a2, this.ah.g, j, 0, 17);
            }
        }
        if (this.ah.at && (a & 0xC000) != 0x0 && !this.j) {
            return true;
        }
        this.j = true;
        this.aj[this.ak].d = 1;
        ++this.am;
        ++this.ak;
        this.ak %= this.ai;
        return true;
    }
    
    public int b(final byte[] array) throws IOException {
        return super.in.read(array);
    }
    
    public boolean g() throws IOException {
        if (this.au > 0) {
            final int read = super.in.read();
            ++this.ab;
            ++this.z;
            this.aa += read;
            if (read > 1) {
                int n = 0;
                int read2;
                for (int i = read - 1; i > 0; i -= read2, n += read2) {
                    read2 = super.in.read(this.t, n, i);
                    if (read2 == -1) {
                        System.out.println(zkmToString("\u0003c>{\f\u0012P5v\u001e\\") + this.ab + zkmToString("M/3a\u0013\u0004|\u000e|\t\u00182") + this.aa + zkmToString("M/3t\b\u0002d\u000ek\u000e\u001bjl") + read + zkmToString("M/&p\u0002\u0013jl") + n + zkmToString("M/#}\n\u0000f?q\t\u00062") + i);
                        throw new IOException(zkmToString("5`>8\u000b\b{%t\u0002Ak0l\u0006A}4l\u0012\u0013a4|G\u0003j7w\u0015\u0004/\u0014W4"));
                    }
                    this.z += read2;
                }
                if (this.u == 4) {
                    for (int n2 = n - 16, j = 0; j < n2; j += 32) {
                        l.a(this.t, this.ah.g, j + 4, 0, 17);
                        l.a(this.t, this.ah.g, j, 0, 17);
                    }
                }
                this.r.a(this.r.i, this.t, this.s);
            }
            else {
                for (int k = 0; k < this.r.i; ++k) {
                    this.s[k] = 0;
                }
            }
            if (this.ah.f.b == null) {
                this.ad += this.a(this.s);
            }
            else {
                while (this.ah.ba + this.l > this.ah.a7 && !this.ah.bc) {
                    try {
                        this.b();
                        Thread.currentThread();
                        Thread.sleep(200L);
                    }
                    catch (InterruptedException ex) {
                        throw new InterruptedIOException();
                    }
                }
                for (int l = 0; l < this.l; ++l) {
                    this.a(this.s[l]);
                }
                this.ad += this.r.i;
            }
            ++this.ac;
        }
        final short au = (short)(this.au - 1);
        this.au = au;
        return au > 0 || this.f();
    }
    
    public int a(final short[] array) {
        final double n = array.length / this.o;
        final double n2 = 1.0 / this.o;
        final double n3 = 1.0 / 8000.0;
        final int n4 = (int)(n * 8000.0);
        if (!this.ah.a0) {
            try {
                while (this.ah.ba + this.l > this.ah.a7 && !this.ah.bc) {
                    try {
                        Thread.currentThread();
                        Thread.sleep(200L);
                    }
                    catch (InterruptedException ex) {
                        throw new InterruptedIOException();
                    }
                }
                for (int i = 0; i < n4; ++i) {
                    this.a(this.a((int)array[(int)(n3 * i / n2)]));
                }
                return n4;
            }
            catch (IOException ex2) {
                System.out.println(zkmToString("2n<h\u000b\u0004/#}\u0006\u0005/4j\u0015\u000e}"));
                return 0;
            }
            return n4;
        }
        return n4;
    }
    
    private byte a(int n) {
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
        final byte b = this.ah.a4[n >> 7 & 0xFF];
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
                    c2 = 'a';
                    break;
                }
                case 1: {
                    c2 = '\u000f';
                    break;
                }
                case 2: {
                    c2 = 'Q';
                    break;
                }
                case 3: {
                    c2 = '\u0018';
                    break;
                }
                default: {
                    c2 = 'g';
                    break;
                }
            }
            array[n] = (char)(c ^ c2);
        }
        return new String(charArray);
    }
}
