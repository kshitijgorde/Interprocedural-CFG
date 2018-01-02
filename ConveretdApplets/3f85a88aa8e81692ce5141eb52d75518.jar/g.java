import java.io.InputStream;
import java.io.InterruptedIOException;
import java.io.IOException;
import java.io.FilterInputStream;

// 
// Decompiled by Procyon v0.5.30
// 

public class g extends FilterInputStream
{
    public int a;
    public byte b;
    public volatile int c;
    public volatile int d;
    public volatile long e;
    public boolean f;
    public boolean g;
    public Thread h;
    public Thread i;
    public boolean j;
    public byte[] k;
    public int l;
    public int m;
    public int n;
    public int o;
    public int p;
    public double q;
    public l r;
    public short[] s;
    public byte[] t;
    public int u;
    public int v;
    public int w;
    public int x;
    public int y;
    public d z;
    
    public g(final d z) {
        super(z.o);
        this.a = 0;
        this.b = 127;
        this.c = 1;
        this.d = 0;
        this.e = Long.MAX_VALUE;
        this.f = false;
        this.g = false;
        this.h = null;
        this.i = null;
        this.j = false;
        this.k = new byte[4];
        this.u = 0;
        this.v = 0;
        this.w = 0;
        this.x = 0;
        this.y = 0;
        this.z = z;
    }
    
    public synchronized void close() throws IOException {
        super.close();
        this.f = true;
        this.notifyAll();
    }
    
    public synchronized int a() throws IOException {
        int n = 200;
        if (this.z.ah) {
            return 127;
        }
        while (this.z.af < 1) {
            this.h = Thread.currentThread();
            if (this.i != null && !this.i.isAlive() && --n < 0) {
                throw new IOException(zkmToString("[Z]+)iAB%le"));
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
        this.b = this.z.ab[this.z.ad];
        ++this.a;
        final d z = this.z;
        --z.af;
        final d z2 = this.z;
        ++z2.ad;
        final d z3 = this.z;
        z3.ad %= this.z.ac;
        return this.b;
    }
    
    public synchronized int read(final byte[] array, int n, int n2) throws IOException {
        if (n2 < 1) {
            return 0;
        }
        if (this.z.af < 1 && this.f) {
            return -1;
        }
        if (this.z.ah) {
            this.c = 1;
            for (int i = 0; i < n2; ++i) {
                array[n + i] = 127;
            }
            return n2;
        }
        array[n++] = (byte)this.a();
        --n2;
        final int n3 = (this.z.af < n2) ? this.z.af : n2;
        if (n3 == 0) {
            return 1;
        }
        int n5;
        d z;
        d z2;
        for (int j = n3; j > 0; j -= n5, n += n5, z = this.z, z.ad += n5, z2 = this.z, z2.ad %= this.z.ac) {
            final int n4 = this.z.ac - this.z.ad;
            n5 = ((j < n4) ? j : n4);
            System.arraycopy(this.z.ab, this.z.ad, array, n, n5);
        }
        if (this.c == 1) {
            this.d = this.a;
            this.e = System.currentTimeMillis();
        }
        this.c = 0;
        final d z3 = this.z;
        z3.af -= n3;
        this.a += n3;
        this.b = array[n - 1];
        return n3 + 1;
    }
    
    public synchronized void a(final byte b) throws IOException {
        this.i = Thread.currentThread();
        while (this.z.af >= this.z.ac) {
            if (this.h != null && !this.h.isAlive()) {
                throw new IOException(zkmToString("[Z]+)iAB%le"));
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
        this.z.ab[this.z.ae] = b;
        final d z = this.z;
        ++z.af;
        final d z2 = this.z;
        ++z2.ae;
        final d z3 = this.z;
        z3.ae %= this.z.ac;
    }
    
    public int b() {
        if (this.z.l.b == null) {
            return 0;
        }
        final int d = this.z.l.d();
        int n = 0;
        if (d < 1) {
            return 0;
        }
        if (this.z.af < 1 && this.f) {
            return -1;
        }
        if (this.z.ah) {
            return 0;
        }
        final int n2 = (this.z.af < d) ? this.z.af : d;
        if (n2 == 0) {
            return 1;
        }
        int n4;
        d z;
        d z2;
        for (int i = n2; i > 0; i -= n4, n += n4, z = this.z, z.ad += n4, z2 = this.z, z2.ad %= this.z.ac) {
            final int n3 = this.z.ac - this.z.ad;
            n4 = ((i < n3) ? i : n3);
            this.z.l.a(this.z.ab, this.z.ad * 2, n4 * 2);
        }
        if (this.c == 1) {
            this.d = this.a;
            this.e = System.currentTimeMillis();
        }
        this.c = 0;
        final d z3 = this.z;
        z3.af -= n2;
        this.a += n2;
        return n2;
    }
    
    public synchronized void a(final short n) throws IOException {
        while (this.z.af >= this.z.ac) {
            this.notifyAll();
            try {
                this.wait(1000L);
            }
            catch (InterruptedException ex) {
                throw new InterruptedIOException();
            }
        }
        this.z.ab[this.z.ae * 2] = (byte)(n & 0xFF);
        this.z.ab[this.z.ae * 2 + 1] = (byte)(n >> 8 & 0xFF);
        final d z = this.z;
        ++z.af;
        final d z2 = this.z;
        ++z2.ae;
        final d z3 = this.z;
        z3.ae %= this.z.ac;
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
        this.l = ((array[4] << 8 & 0xFF00) | (array[5] & 0xFF));
        this.m = (array[6] & 0xFF);
        this.n = ((array[7] << 8 & 0xFF00) | (array[8] & 0xFF));
        this.o = ((array[9] << 8 & 0xFF00) | (array[10] & 0xFF));
        this.q = 0.01 * (array[11] & 0xFF);
        this.j = (array[3] == 52);
        return this.g = true;
    }
    
    public boolean c() throws IOException {
        Math.log(System.currentTimeMillis());
        final boolean b = this.z.n[0] != 0 || this.z.n[1] != 0 || this.z.n[2] != 0 || this.z.n[3] != 0;
        if (!this.g) {
            super.in.read(this.k);
            if (this.k[0] != 68) {
                return false;
            }
            if (this.k[1] != 78) {
                return false;
            }
            if (this.k[2] != 89) {
                return false;
            }
            if (this.k[3] != 50 && this.k[3] != 52) {
                return false;
            }
            this.l = this.a(super.in, true);
            this.m = super.in.read();
            final int a = this.a(super.in, true);
            this.o = this.a(super.in, true);
            this.q = 0.01 * super.in.read();
            this.p = this.b(super.in, false);
            this.j = (this.k[3] == 52);
            if (this.j) {
                final int n = this.z.n[0] ^ this.z.n[1] ^ this.z.n[2] ^ this.z.n[3];
                if (((n >> 16 ^ n) & 0xFFFF) != this.p || !b) {
                    i.a(zkmToString("^]L,en\u0013Y!){_L7)\u007f[HnznPX<lo\u0013N\"`{\u001d\r"));
                    return false;
                }
            }
            else if (b) {
                i.a(zkmToString("^]L,en\u0013Y!)~@Hn}cV\r=lhF_+m+XH7'"));
                return false;
            }
            this.n = a;
        }
        this.u = 16;
        this.r = new l(this.l, this.m, this.n, this.q, this.o);
        this.t = new byte[this.l * 2];
        this.s = new short[this.l];
        return true;
    }
    
    public boolean d() throws IOException {
        final int read = super.in.read();
        if (read < 1) {
            return false;
        }
        ++this.v;
        ++this.u;
        this.w += read;
        int n = 0;
        int read2;
        for (int i = read - 1; i > 0; i -= read2, n += read2) {
            read2 = super.in.read(this.t, n, i);
            if (read2 == -1) {
                System.out.println(zkmToString("i_B-bxlI p6") + this.v + zkmToString("'\u0013O7}n@r*gr\u000e") + this.w + zkmToString("'\u0013O\"fhXr=`qV\u0010") + read + zkmToString("'\u0013Z&lyV\u0010") + n + zkmToString("'\u0013_+djZC'gl\u000e") + i);
                throw new IOException(zkmToString("_\\BnebGY\"l+WL:h+AH:|y]H*)iVK!{n\u0013h\u0001Z"));
            }
            this.u += read2;
        }
        if (this.j) {
            for (int n2 = n - 16, j = 0; j < n2; j += 32) {
                k.a(this.t, this.z.n, j + 4, 0, 17);
                k.a(this.t, this.z.n, j, 0, 17);
            }
        }
        if (read > 3) {
            this.r.a(this.r.i, this.t, this.s);
        }
        else {
            this.s[0] = (short)(this.t[1] << 8 | this.t[0]);
            for (int k = 1; k < this.l; ++k) {
                this.s[k] = this.s[0];
            }
        }
        if (this.z.l.b == null) {
            this.y += this.a(this.s);
        }
        else {
            while (this.z.af + this.l > this.z.ac && !this.z.ah) {
                try {
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
            this.y += this.r.i;
        }
        ++this.x;
        return true;
    }
    
    public int a(final short[] array) {
        final double n = array.length / this.o;
        final double n2 = 1.0 / this.o;
        final double n3 = 1.0 / 8000.0;
        final int n4 = (int)(n * 8000.0);
        try {
            while (this.z.af + this.l > this.z.ac && !this.z.ah) {
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
            System.out.println(zkmToString("XR@>en\u0013_+ho\u0013H<{dA"));
            return 0;
        }
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
        final byte b = this.z.aa[n >> 7 & 0xFF];
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
                    c2 = '\u000b';
                    break;
                }
                case 1: {
                    c2 = '3';
                    break;
                }
                case 2: {
                    c2 = '-';
                    break;
                }
                case 3: {
                    c2 = 'N';
                    break;
                }
                default: {
                    c2 = '\t';
                    break;
                }
            }
            array[n] = (char)(c ^ c2);
        }
        return new String(charArray);
    }
}
