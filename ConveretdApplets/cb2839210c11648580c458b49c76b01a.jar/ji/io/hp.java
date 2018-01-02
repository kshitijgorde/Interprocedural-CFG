// 
// Decompiled by Procyon v0.5.30
// 

package ji.io;

public class hp
{
    private static byte[] a;
    private int b;
    private ac c;
    
    public hp(final ac c) throws Exception {
        this.b = 0;
        this.c = null;
        h();
        if (this.c != null) {
            this.a();
        }
        this.c = c;
        this.b = 0;
    }
    
    private static synchronized void h() {
        if (hp.a == null) {
            j();
        }
    }
    
    public hp(final ac c, final int n) throws Exception {
        this.b = 0;
        this.c = null;
        h();
        if (this.c != null) {
            this.a();
        }
        this.c = c;
        this.a(n);
    }
    
    public final void a() {
        this.c = null;
    }
    
    public final void a(final int b) throws Exception {
        this.b = b;
    }
    
    public final int b() {
        return this.b;
    }
    
    public final int c() throws Exception {
        return this.c.j();
    }
    
    public final int d() throws Exception {
        int n;
        if ((this.b & 0x1) > 0) {
            n = this.c.j() + (this.c.j() << 8);
        }
        else {
            n = (this.c.j() << 8) + this.c.j();
        }
        return n;
    }
    
    public final void b(final int n) throws Exception {
        if ((this.b & 0x1) > 0) {
            this.c.c(this.d(n));
        }
        else {
            this.c.c(n);
        }
    }
    
    public final void c(final int n) throws Exception {
        if ((this.b & 0x1) > 0) {
            this.c.b(this.e(n));
        }
        else {
            this.c.b(n);
        }
    }
    
    private final int i() throws Exception {
        return (this.c() << 8) + this.c();
    }
    
    public final int e() throws Exception {
        int e = (this.i() << 16) + this.i();
        if ((this.b & 0x1) > 0) {
            e = this.e(e);
        }
        return e;
    }
    
    public final int a(final byte[] array, final int n, final int n2) throws Exception {
        try {
            final int a = this.c.a(array, n, n2);
            if ((this.b & 0x2) > 0) {
                this.a(array, n);
            }
            return a;
        }
        catch (Exception ex) {
            return 0;
        }
    }
    
    public final int a(final byte[] array) throws Exception {
        try {
            final int a = this.c.a(array);
            if ((this.b & 0x2) > 0) {
                this.a(array, 0);
            }
            return a;
        }
        catch (Exception ex) {
            return 0;
        }
    }
    
    public final void b(final byte[] array) throws Exception {
        if ((this.b & 0x2) > 0) {
            this.c.b(this.c(array));
        }
        else {
            this.c.b(array);
        }
    }
    
    public final void b(final byte[] array, final int n, final int n2) throws Exception {
        if ((this.b & 0x2) > 0) {
            this.c.b(this.c(array, n, n2));
        }
        else {
            this.c.b(array, n, n2);
        }
    }
    
    public final void a(final long n) throws Exception {
        try {
            this.c.a(n);
        }
        catch (Exception ex) {}
    }
    
    public final long f() throws Exception {
        return this.c.r();
    }
    
    public final long g() throws Exception {
        return this.c.w();
    }
    
    public final void finalize() {
        try {
            this.a();
        }
        catch (Exception ex) {}
    }
    
    public final int d(final int n) {
        return ((n & 0xFF) << 8) + (n >> 8);
    }
    
    private final int e(final int n) {
        return (this.d(n & 0xFFFF) << 16) + this.d(n >> 16 & 0xFFFF);
    }
    
    private final void a(final byte[] array, final int n) {
        for (int i = n; i < array.length; ++i) {
            array[i] = hp.a[array[i] & 0xFF];
        }
    }
    
    private final byte[] c(final byte[] array) {
        final byte[] array2 = new byte[array.length];
        for (int i = 0; i < array.length; ++i) {
            array2[i] = hp.a[array[i] & 0xFF];
        }
        return array2;
    }
    
    private final byte[] c(final byte[] array, final int n, final int n2) {
        final byte[] array2 = new byte[n2];
        for (int n3 = n + n2, i = n; i < n3; ++i) {
            array2[i - n] = hp.a[array[i] & 0xFF];
        }
        return array2;
    }
    
    private static final void j() {
        final byte[] a = new byte[256];
        if (a != null) {
            for (int i = 0; i < 256; ++i) {
                a[i] = (byte)(((i & 0x1) << 7) + ((i >> 1 & 0x1) << 6) + ((i >> 2 & 0x1) << 5) + ((i >> 3 & 0x1) << 4) + ((i >> 4 & 0x1) << 3) + ((i >> 5 & 0x1) << 2) + ((i >> 6 & 0x1) << 1) + (i >> 7 & 0x1));
            }
        }
        hp.a = a;
    }
    
    static {
        hp.a = null;
    }
}
