// 
// Decompiled by Procyon v0.5.30
// 

package jaclib.memory;

public final class Stream
{
    private Buffer a;
    private int b;
    private byte[] c;
    private int d;
    private int e;
    
    public final void a(final float n) {
        try {
            if (this.b + 3 >= this.c.length) {
                this.c();
            }
            final int floatToRawIntBits = floatToRawIntBits(n);
            this.c[this.b++] = (byte)floatToRawIntBits;
            this.c[this.b++] = (byte)(floatToRawIntBits >> -1571973048);
            this.c[this.b++] = (byte)(floatToRawIntBits >> -867554672);
            this.c[this.b++] = (byte)(floatToRawIntBits >> -675913608);
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    public final void a(final int n) {
        try {
            if (this.b + 3 >= this.c.length) {
                this.c();
            }
            this.c[this.b++] = (byte)(n >> -142119792);
            this.c[this.b++] = (byte)(n >> -1412802328);
            this.c[this.b++] = (byte)n;
            this.c[this.b++] = (byte)(n >> -1806138600);
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    public final void b(final int e) {
        try {
            this.c();
            this.e = e;
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    public final void b(final float n) {
        try {
            if (~(this.b + 3) <= ~this.c.length) {
                this.c();
            }
            final int floatToRawIntBits = floatToRawIntBits(n);
            this.c[this.b++] = (byte)(floatToRawIntBits >> -804863784);
            this.c[this.b++] = (byte)(floatToRawIntBits >> 1242398928);
            this.c[this.b++] = (byte)(floatToRawIntBits >> -2034164664);
            this.c[this.b++] = (byte)floatToRawIntBits;
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    public final void a(final int n, final int n2, final int n3, final int n4) {
        try {
            if (this.c.length <= this.b + 3) {
                this.c();
            }
            this.c[this.b++] = (byte)n3;
            this.c[this.b++] = (byte)n2;
            this.c[this.b++] = (byte)n;
            this.c[this.b++] = (byte)n4;
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    public Stream(final Buffer buffer, final int n, final int n2) {
        this((buffer.getSize() >= 4096) ? 4096 : buffer.getSize());
        try {
            this.a(buffer, n, n2);
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    public static final boolean a() {
        try {
            return ~getLSB(-65536) == 0x0;
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    public final int b() {
        try {
            return this.b + this.e;
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    public final void b(final int n, final int n2, final int n3, final int n4) {
        try {
            if (3 + this.b >= this.c.length) {
                this.c();
            }
            this.c[this.b++] = (byte)n;
            this.c[this.b++] = (byte)n2;
            this.c[this.b++] = (byte)n3;
            this.c[this.b++] = (byte)n4;
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    public final void a(final Buffer buffer) {
        try {
            this.a(buffer, 0, buffer.getSize());
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    private static final native byte getLSB(final int p0);
    
    private final void a(final Buffer a, final int e, final int n) {
        try {
            this.c();
            this.e = e;
            this.d = n + e;
            this.a = a;
            if (~this.d < ~a.getSize()) {
                throw new RuntimeException();
            }
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    public final void c(final int n) {
        try {
            if (this.c.length <= 1 + this.b) {
                this.c();
            }
            this.c[this.b++] = (byte)(n >> 446021192);
            this.c[this.b++] = (byte)n;
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    public final void d(final int n) {
        try {
            if (1 + this.b >= this.c.length) {
                this.c();
            }
            this.c[this.b++] = (byte)n;
            this.c[this.b++] = (byte)(n >> 1731009608);
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    public final void e(final int n) {
        try {
            if (this.b >= this.c.length) {
                this.c();
            }
            this.c[this.b++] = (byte)n;
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    public Stream() {
        this(4096);
    }
    
    public final void f(final int n) {
        try {
            if (3 + this.b >= this.c.length) {
                this.c();
            }
            this.c[this.b++] = (byte)n;
            this.c[this.b++] = (byte)(n >> -1554826648);
            this.c[this.b++] = (byte)(n >> 153807472);
            this.c[this.b++] = (byte)(n >> 1864436248);
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    public final void c() {
        try {
            if (~this.b < -1) {
                if (~(this.b + this.e) < ~this.d) {
                    throw new RuntimeException();
                }
                this.a.a(this.c, 0, this.e, this.b);
                this.e += this.b;
                this.b = 0;
            }
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    public Stream(final Buffer buffer) {
        this(buffer, 0, buffer.getSize());
    }
    
    public static final native int floatToRawIntBits(final float p0);
    
    private Stream(final int n) {
        try {
            this.c = new byte[n];
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
}
