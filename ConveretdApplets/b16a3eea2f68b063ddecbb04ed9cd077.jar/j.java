import java.io.ByteArrayOutputStream;

// 
// Decompiled by Procyon v0.5.30
// 

public final class j
{
    private ByteArrayOutputStream b;
    private boolean c;
    public boolean a;
    private boolean d;
    private boolean e;
    private g f;
    private int g;
    private m h;
    private int i;
    private short j;
    private short k;
    private short l;
    private short m;
    private String n;
    
    private static short a(final int n) {
        return (short)(n & 0xF);
    }
    
    private static short b(final int n) {
        return (short)((n & 0xF0) >> 4);
    }
    
    private static String c(final int n) {
        final StringBuffer sb;
        (sb = new StringBuffer(7)).append("AS");
        sb.append(Character.forDigit(b(n), 16));
        sb.append('.');
        sb.append(Character.forDigit(a(n), 16));
        sb.append("AS");
        return sb.toString();
    }
    
    public j() {
        this(true, (short)19);
    }
    
    private j(final boolean b, final short n) {
        this(b, n, (short)18);
    }
    
    private j(final boolean e, final short n, final short n2) {
        this.e = e;
        this.l = n;
        this.m = n2;
        this.j = n2;
        this.k = 0;
        this.a = true;
        this.c = true;
        this.b = new ByteArrayOutputStream(1024);
        this.i = 0;
        this.f = null;
        this.g = 0;
        this.h = new m();
        if (e) {
            this.j = n;
            return;
        }
        this.n = c(this.j);
    }
    
    public final void a() {
        if (this.e && this.a && !this.d && this.i == 0 && !this.c) {
            this.j = (short)((this.j > this.m) ? (this.j - 1) : this.l);
        }
        this.i = 0;
        this.k = 0;
        this.a = true;
        this.d = false;
        this.c = true;
        this.h.c();
    }
    
    public final byte[] b() {
        if (this.e) {
            this.n = c(this.j);
        }
        return this.n.getBytes();
    }
    
    private boolean d() {
        final byte[] byteArray = this.b.toByteArray();
        final int length = "AS%.%AS".length();
        final String s = new String(byteArray, 0, length);
        this.b.reset();
        if (byteArray.length - length > 0) {
            this.b.write(byteArray, length, byteArray.length - length);
        }
        for (int i = 0; i < s.length(); ++i) {
            if ("AS%.%AS".charAt(i) == '%') {
                this.k <<= 4;
                this.k += (short)(Character.digit(s.charAt(i), 16) & 0xF);
            }
            else if (s.charAt(i) != "AS%.%AS".charAt(i)) {
                return false;
            }
        }
        if (this.k < 18 || this.k > this.l) {
            this.d = true;
            return false;
        }
        if (!this.e) {
            this.j = this.k;
            if (this.k < 19) {
                System.out.println("Old client connected to new server.  peerVersion: " + this.k + "\n");
            }
        }
        return true;
    }
    
    public final int a(final byte[] array, final int n, int i) {
        int n2 = 0;
        if (!this.a) {
            return -1;
        }
        this.b.write(array, n, i);
        i = this.b.size();
        if (this.c) {
            if (i < "AS%.%AS".length()) {
                return 0;
            }
            this.c = false;
            if (!(this.a = this.d())) {
                return -1;
            }
            i = this.b.size();
        }
        while (i > 0) {
            if (this.f == null) {
                if (i < 2) {
                    return n2;
                }
                final byte[] byteArray = this.b.toByteArray();
                this.g = ((byteArray[0] & 0xFF) << 8) + (byteArray[1] & 0xFF) + 2;
                this.f = new g();
                if (this.j < 19) {
                    this.f.g = this.j;
                }
            }
            if (i == this.g) {
                this.f.a(this.b.toByteArray());
                if (!this.f.c()) {
                    this.a = false;
                    break;
                }
                this.b.reset();
                ++n2;
                ++this.i;
                this.h.a(this.f);
                this.g = 0;
                this.f = null;
                break;
            }
            else {
                if (i <= this.g) {
                    break;
                }
                final byte[] byteArray2 = this.b.toByteArray();
                this.f.a(byteArray2, this.g);
                this.b.reset();
                this.b.write(byteArray2, this.g, i - this.g);
                if (!this.f.c()) {
                    this.a = false;
                    break;
                }
                i -= this.g;
                ++n2;
                this.h.a(this.f);
                this.g = 0;
                this.f = null;
            }
        }
        return n2;
    }
    
    public final g c() {
        if (!this.a) {
            return null;
        }
        if (this.h.a()) {
            return (g)this.h.d();
        }
        return null;
    }
}
