// 
// Decompiled by Procyon v0.5.30
// 

public final class h
{
    public byte[][] a;
    public int b;
    public int c;
    public int d;
    
    public h(final int n) {
        this(n, 4096);
    }
    
    public h(final int c, final int d) {
        this.b = 0;
        this.d = d;
        this.c = c;
        this.a();
    }
    
    public int a(final byte[] array, final int n, final int n2, final int n3) {
        final int n4 = this.a[n2].length - n3;
        final int n5 = array.length - n;
        final int n6 = (n5 > n4) ? n4 : n5;
        System.arraycopy(this.a[n2], n3, array, n, n6);
        if (n4 > n6) {
            return -(n6 + n3);
        }
        return n6;
    }
    
    public final boolean a(final int n) {
        return n < this.b;
    }
    
    public int a(final int n, final byte b) {
        if (n == -1) {
            this.a[this.b] = new byte[] { b };
        }
        else {
            final int n2 = this.a[n].length + 1;
            final byte[] array = new byte[n2];
            System.arraycopy(this.a[n], 0, array, 0, n2 - 1);
            array[n2 - 1] = b;
            this.a[this.b] = array;
        }
        return this.b++;
    }
    
    public void a() {
        this.a = new byte[this.d][];
        for (int n = (1 << this.c) + 2, i = 0; i < n; ++i) {
            this.a(-1, (byte)i);
        }
    }
    
    public void b() {
        this.b = (1 << this.c) + 2;
    }
}
