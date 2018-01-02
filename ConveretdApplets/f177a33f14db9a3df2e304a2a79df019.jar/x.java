import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

class x
{
    public static final byte a = 0;
    public static final byte b = 1;
    public static final byte c = 2;
    public static final byte d = 3;
    public static final byte e = 4;
    public static final byte f = 5;
    public static final byte g = 6;
    public static final byte h = 7;
    public static final byte i = 8;
    public static final byte j = 9;
    public static final byte k = 10;
    public static final byte l = 13;
    public static final byte m = 14;
    public static final byte n = -1;
    private static final byte[] o;
    private byte p;
    private int q;
    private int r;
    private byte[] s;
    public Vector t;
    
    x() {
        this.t = new Vector(0, 1);
        this.q = 0;
        this.p = 0;
    }
    
    public int a(final InputStream inputStream) {
        int b = this.b(inputStream);
        if (b == 0) {
            return 0;
        }
        if (this.p != -1) {
            if (this.s == null || (this.r > 0 && this.r != this.s.length)) {
                this.s = new byte[this.r];
            }
            try {
                b += inputStream.read(this.s, 0, this.r);
            }
            catch (IOException ex) {}
        }
        else {
            int i = 0;
            while (i < this.r) {
                final x x = new x();
                i += x.a(inputStream);
                this.t.addElement(x);
            }
            b += i;
        }
        return b;
    }
    
    private int b(final InputStream inputStream) {
        int n = 0;
        try {
            final byte[] array = new byte[4];
            inputStream.read(array);
            this.q = ((array[0] << 24 & 0xFF000000) | (array[1] << 16 & 0xFF0000) | (array[2] << 8 & 0xFF00) | (array[3] & 0xFF));
            n += 4;
            this.p = g.a(inputStream);
            ++n;
            if (this.p > 0 && this.p <= 10) {
                this.r = x.o[this.p];
            }
            else {
                this.r = g.c(inputStream);
                n += 4;
            }
        }
        catch (IOException ex) {}
        return n;
    }
    
    public int a() {
        return this.q;
    }
    
    public byte b() {
        return this.p;
    }
    
    public int c() {
        if (this.p != -1) {
            return 0;
        }
        return this.t.size();
    }
    
    public x a(final int n) {
        if (this.t.size() == 0) {
            return null;
        }
        if (this.p != -1) {
            return null;
        }
        if (n >= this.t.size()) {
            return null;
        }
        return this.t.elementAt(n);
    }
    
    public x b(final int n) {
        if (this.p != -1) {
            return null;
        }
        for (int i = 0; i < this.t.size(); ++i) {
            if (i >= this.t.size()) {
                return null;
            }
            final x x = this.t.elementAt(i);
            if (x.a() == n) {
                return x;
            }
        }
        return null;
    }
    
    public int d() {
        if (this.p == -1) {
            return 0;
        }
        return this.s.length;
    }
    
    public int e() {
        if (this.s == null || (this.p != 1 && this.p != 2)) {
            return 0;
        }
        return g.a(this.s);
    }
    
    public int f() {
        if (this.s == null || (this.p != 3 && this.p != 4)) {
            return 0;
        }
        return g.b(this.s);
    }
    
    public int g() {
        if (this.s == null || (this.p != 5 && this.p != 6)) {
            return 0;
        }
        return g.c(this.s);
    }
    
    public long h() {
        if (this.s == null || (this.p != 7 && this.p != 8)) {
            return 0L;
        }
        return g.d(this.s);
    }
    
    public float i() {
        if (this.s == null || this.p != 9) {
            return 0.0f;
        }
        return g.e(this.s);
    }
    
    public double j() {
        if (this.s == null || this.p != 10) {
            return 0.0;
        }
        return g.f(this.s);
    }
    
    public String k() {
        if (this.p != 13) {
            return "";
        }
        return new String(this.s);
    }
    
    public byte[] l() {
        return this.s;
    }
    
    static {
        o = new byte[] { 1, 1, 1, 2, 2, 4, 4, 8, 8, 4, 8, 4, 8 };
    }
}
