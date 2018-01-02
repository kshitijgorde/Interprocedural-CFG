import java.io.InputStream;
import java.io.InterruptedIOException;
import java.io.IOException;
import java.io.FilterInputStream;

// 
// Decompiled by Procyon v0.5.30
// 

public class h extends FilterInputStream
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
    public boolean k;
    public byte[] l;
    public int m;
    public int n;
    public int o;
    public int p;
    public int q;
    public double r;
    public m s;
    public short[] t;
    public byte[] u;
    public int v;
    public int w;
    public int x;
    public int y;
    public int z;
    public e aa;
    
    public h(final e aa) {
        super(aa.s);
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
        this.k = false;
        this.l = new byte[4];
        this.v = 0;
        this.w = 0;
        this.x = 0;
        this.y = 0;
        this.z = 0;
        this.aa = aa;
    }
    
    public synchronized void close() throws IOException {
        if (!this.j) {
            super.close();
            this.f = true;
            this.notifyAll();
        }
    }
    
    public synchronized int a() throws IOException {
        int n = 200;
        if (this.aa.al) {
            return 127;
        }
        while (this.aa.aj < 1) {
            this.h = Thread.currentThread();
            if (this.i != null && !this.i.isAlive() && --n < 0) {
                throw new IOException(zkmToString("F\u001d&H\bt\u00069FMx"));
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
        this.b = this.aa.af[this.aa.ah];
        ++this.a;
        final e aa = this.aa;
        --aa.aj;
        final e aa2 = this.aa;
        ++aa2.ah;
        final e aa3 = this.aa;
        aa3.ah %= this.aa.ag;
        return this.b;
    }
    
    public synchronized int read(final byte[] array, int n, int n2) throws IOException {
        if (n2 < 1) {
            return 0;
        }
        if (this.aa.aj < 1 && this.f) {
            return -1;
        }
        if (this.aa.al) {
            this.c = 1;
            for (int i = 0; i < n2; ++i) {
                array[n + i] = 127;
            }
            return n2;
        }
        array[n++] = (byte)this.a();
        --n2;
        final int n3 = (this.aa.aj < n2) ? this.aa.aj : n2;
        if (n3 == 0) {
            return 1;
        }
        int j = n3;
        if (n2 > 400) {
            this.aa.c = true;
        }
        while (j > 0) {
            final int n4 = this.aa.ag - this.aa.ah;
            final int n5 = (j < n4) ? j : n4;
            System.arraycopy(this.aa.af, this.aa.ah, array, n, n5);
            j -= n5;
            n += n5;
            final e aa = this.aa;
            aa.ah += n5;
            final e aa2 = this.aa;
            aa2.ah %= this.aa.ag;
        }
        if (this.c == 1) {
            this.d = this.a;
            this.e = System.currentTimeMillis();
        }
        this.c = 0;
        final e aa3 = this.aa;
        aa3.aj -= n3;
        this.a += n3;
        this.b = array[n - 1];
        return n3 + 1;
    }
    
    public synchronized void a(final byte b) throws IOException {
        this.i = Thread.currentThread();
        while (this.aa.aj >= this.aa.ag) {
            if (this.h != null && !this.h.isAlive()) {
                throw new IOException(zkmToString("F\u001d&H\bt\u00069FMx"));
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
        this.aa.af[this.aa.ai] = b;
        final e aa = this.aa;
        ++aa.aj;
        final e aa2 = this.aa;
        ++aa2.ai;
        final e aa3 = this.aa;
        aa3.ai %= this.aa.ag;
    }
    
    public int b() {
        if (this.aa.o.b == null) {
            return 0;
        }
        final int a = this.aa.o.a();
        int n = 0;
        if (a < 1) {
            return 0;
        }
        if (this.aa.aj < 1 && this.f) {
            return -1;
        }
        if (this.aa.al) {
            return 0;
        }
        final int n2 = (this.aa.aj < a) ? this.aa.aj : a;
        if (n2 == 0) {
            return 1;
        }
        int n4;
        e aa;
        e aa2;
        for (int i = n2; i > 0; i -= n4, n += n4, aa = this.aa, aa.ah += n4, aa2 = this.aa, aa2.ah %= this.aa.ag) {
            final int n3 = this.aa.ag - this.aa.ah;
            n4 = ((i < n3) ? i : n3);
            this.aa.o.a(this.aa.af, this.aa.ah * 2, n4 * 2);
        }
        if (this.c == 1) {
            this.d = this.a;
            this.e = System.currentTimeMillis();
        }
        this.c = 0;
        final e aa3 = this.aa;
        aa3.aj -= n2;
        this.a += n2;
        return n2;
    }
    
    public synchronized void a(final short n) throws IOException {
        while (this.aa.aj >= this.aa.ag) {
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
        this.aa.af[this.aa.ai * 2] = (byte)(n & 0xFF);
        this.aa.af[this.aa.ai * 2 + 1] = (byte)(n >> 8 & 0xFF);
        final e aa = this.aa;
        ++aa.aj;
        final e aa2 = this.aa;
        ++aa2.ai;
        final e aa3 = this.aa;
        aa3.ai %= this.aa.ag;
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
        this.m = ((array[4] << 8 & 0xFF00) | (array[5] & 0xFF));
        this.n = (array[6] & 0xFF);
        this.o = ((array[7] << 8 & 0xFF00) | (array[8] & 0xFF));
        this.p = ((array[9] << 8 & 0xFF00) | (array[10] & 0xFF));
        this.r = 0.01 * (array[11] & 0xFF);
        this.k = (array[3] == 52);
        return this.g = true;
    }
    
    public boolean c() throws IOException {
        Math.log(System.currentTimeMillis());
        final boolean b = this.aa.r[0] != 0 || this.aa.r[1] != 0 || this.aa.r[2] != 0 || this.aa.r[3] != 0;
        if (!this.g) {
            super.in.read(this.l);
            if (this.l[0] != 68) {
                return false;
            }
            if (this.l[1] != 78) {
                return false;
            }
            if (this.l[2] != 89) {
                return false;
            }
            if (this.l[3] != 50 && this.l[3] != 52) {
                return false;
            }
            this.m = this.a(super.in, true);
            this.n = super.in.read();
            final int a = this.a(super.in, true);
            this.p = this.a(super.in, true);
            this.r = 0.01 * super.in.read();
            this.q = this.b(super.in, false);
            this.k = (this.l[3] == 52);
            if (this.k) {
                final int n = this.aa.r[0] ^ this.aa.r[1] ^ this.aa.r[2] ^ this.aa.r[3];
                if (((n >> 16 ^ n) & 0xFFFF) != this.q || !b) {
                    j.a(zkmToString("C\u001a7ODsT\"B\bf\u00187T\bb\u001c3\r[s\u0017#_MrT5AAfZv"));
                    return false;
                }
            }
            else if (b) {
                j.a(zkmToString("C\u001a7ODsT\"B\bc\u00073\r\\~\u0011v^Mu\u0001$HL6\u001f3T\u0006"));
                return false;
            }
            this.o = a;
        }
        this.v = 16;
        this.s = new m(this.m, this.n, this.o, this.r, this.p);
        this.u = new byte[this.m * 2];
        this.t = new short[this.m];
        return true;
    }
    
    public boolean d() throws IOException {
        final int read = super.in.read();
        if (read < 1) {
            return false;
        }
        ++this.w;
        ++this.v;
        this.x += read;
        int n = 0;
        int read2;
        for (int n2 = read - 1; this.aa.q == 0 && n2 > 0; n2 -= read2, n += read2) {
            read2 = super.in.read(this.u, n, n2);
            if (read2 == -1) {
                System.out.println(zkmToString("t\u00189NCe+2CQ+") + this.w + zkmToString(":T4T\\s\u0007\tIFoI") + this.x + zkmToString(":T4AGu\u001f\t^Al\u0011k") + read + zkmToString(":T!EMd\u0011k") + n + zkmToString(":T$HEw\u001d8DFqI") + n2);
                throw new IOException(zkmToString("B\u001b9\rD\u007f\u0000\"AM6\u00107YI6\u00063Y]d\u001a3I\bt\u00110BZsT\u0013b{"));
            }
            this.v += read2;
        }
        if (this.aa.q > 0) {
            return false;
        }
        if (this.k) {
            for (int n3 = n - 16, i = 0; i < n3; i += 32) {
                l.a(this.u, this.aa.r, i + 4, 0, 17);
                l.a(this.u, this.aa.r, i, 0, 17);
            }
        }
        if (this.aa.w) {
            final int n4 = read - 1;
            byte b = 0;
            int n5 = read;
            while (b < n4) {
                this.u[b] = (byte)((this.u[b] ^ read) + n5 + b + 1);
                n5 = this.u[b];
                ++b;
            }
        }
        if (read > 3) {
            this.s.a(this.s.i, this.u, this.t);
        }
        else {
            this.t[0] = (short)(this.u[1] << 8 | this.u[0]);
            for (int j = 1; j < this.m; ++j) {
                this.t[j] = this.t[0];
            }
        }
        if (this.aa.o.b == null) {
            this.z += this.a(this.t);
        }
        else {
            while (this.aa.aj + this.m > this.aa.ag && !this.aa.al) {
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
            for (int k = 0; k < this.m; ++k) {
                this.a(this.t[k]);
            }
            this.z += this.s.i;
        }
        ++this.y;
        return true;
    }
    
    public int a(final short[] array) {
        final double n = array.length / this.p;
        final double n2 = 1.0 / this.p;
        final double n3 = 1.0;
        this.aa.getClass();
        final double n4 = n3 / 8000.0;
        final double n5 = n;
        this.aa.getClass();
        final int n6 = (int)(n5 * 8000.0);
        try {
            while (this.aa.aj + this.m > this.aa.ag && !this.aa.al) {
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
            for (int i = 0; i < n6; ++i) {
                this.a(this.a((int)array[(int)(n4 * i / n2)]));
            }
            return n6;
        }
        catch (IOException ex2) {
            System.out.println(zkmToString("E\u0015;]DsT$HIrT3_Zy\u0006"));
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
        final byte b = this.aa.ae[n >> 7 & 0xFF];
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
                    c2 = '\u0016';
                    break;
                }
                case 1: {
                    c2 = 't';
                    break;
                }
                case 2: {
                    c2 = 'V';
                    break;
                }
                case 3: {
                    c2 = '-';
                    break;
                }
                default: {
                    c2 = '(';
                    break;
                }
            }
            array[n] = (char)(c ^ c2);
        }
        return new String(charArray);
    }
}
