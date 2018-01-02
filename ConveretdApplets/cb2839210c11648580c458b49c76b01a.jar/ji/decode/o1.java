// 
// Decompiled by Procyon v0.5.30
// 

package ji.decode;

public class o1 extends o2
{
    long a;
    long b;
    int c;
    int d;
    int e;
    int f;
    long g;
    int h;
    int i;
    int j;
    int k;
    String l;
    
    public o1(final Object o, final String s) {
        super(o, s);
        this.k = 0;
        this.l = null;
    }
    
    public final void a() {
    }
    
    public int a(final byte[] array, final int n, final byte[] array2, int d, final Object o, final String s) throws Exception {
        this.b = (int)o3.a(array, 0);
        this.d = (int)o3.a(array, 4);
        this.c = (int)o3.a(array, 8);
        this.j = array[12];
        final int c = this.c;
        d = this.d;
        this.a = o3.a(array, 4, this.c + 12 - 4);
        final byte[] array3 = new byte[this.c];
        System.arraycopy(array, 12, array3, 0, this.c - 1);
        this.k = new o4(o, s).a(0, array3, array2, c, o, s);
        if ((this.j & 0x80) > 0) {
            final int a = this.a(array2, this.k, d);
            System.arraycopy(array2, a, array2, 0, Math.min(array2.length - a, array2.length));
            this.k = 0;
        }
        this.g = 0L;
        this.i = 0;
        this.h = 0;
        if ((this.j & 0x40) > 0) {
            final o4 o2 = new o4(o, s);
            this.e = this.c + 12;
            this.f = n - (this.c + 12);
            this.g = (int)o3.a(array, this.e);
            this.i = (int)o3.a(array, this.e + 4);
            this.h = (int)o3.a(array, this.e + 8);
            final byte[] array4 = new byte[this.h];
            final byte[] array5 = new byte[this.i];
            if (this.h + 12 != this.f) {
                throw new Exception("bit vector length error");
            }
            if (this.i != this.d + 7 >> 3) {
                throw new Exception("uncompressed length error");
            }
            this.a = o3.a(array, this.e + 4, this.h + 12 - 4);
            if (this.a != this.g) {
                throw new Exception("BV_FOLLOW_FLAGS===>csum2 error......");
            }
            this.j = array[this.e + 12];
            if ((this.j & 0x20) == 0x0) {
                throw new Exception("CDCD_err_flagerror: BV_FLAG...");
            }
            if ((this.j & 0x40) > 0) {
                throw new Exception("CDCD_err_flagerror:BV_FOLLOW_FLAG...");
            }
            if ((this.j & 0x80) > 0) {
                throw new Exception("CDCD_err_flagerror:BLANK_COMP_FLAG...");
            }
            final int i = this.i;
            System.arraycopy(array, this.e + 12, array4, 0, this.h - 1);
            o2.a(1, array4, array5, this.h, o, s);
            final o5 o3 = new o5(array2, 0, this.d, array5, 0, o, s);
        }
        return this.k = this.d + this.i;
    }
    
    public int a(final byte[] array, int i, int n) {
        while (i > 0) {
            final int n2 = array[--i] & 0xFF;
            if (n2 >= 128) {
                for (int j = n2 - 126; j > 0; --j) {
                    array[--n] = 32;
                }
            }
            else {
                if (n <= 0) {
                    continue;
                }
                array[--n] = (byte)n2;
            }
        }
        return n;
    }
}
