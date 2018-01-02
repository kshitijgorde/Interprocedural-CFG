// 
// Decompiled by Procyon v0.5.30
// 

package ji.decode;

class o4 extends o2
{
    long a;
    int b;
    int c;
    int d;
    int e;
    int f;
    int g;
    int h;
    int i;
    char[][] j;
    char[] k;
    int l;
    int m;
    int n;
    
    public o4(final Object o, final String s) {
        super(o, s);
        this.c = 0;
        this.d = 0;
        this.n = 0;
    }
    
    public int a(final int n, final byte[] array, final byte[] array2, final int e, final Object o, final String s) {
        if ((array[this.c] & 0xFF & 0x1) == 0x0) {
            System.arraycopy(array, 1, array2, 0, array.length - 1);
            return array.length - 1;
        }
        final char[] array3 = new char[array2.length];
        for (int i = 0; i < array3.length; ++i) {
            array3[i] = (char)array2[i];
        }
        this.j = new char[4096][];
        this.k = new char[20];
        this.m = (array[this.c++] & 0xFF);
        if (n == 0) {
            for (int j = 0; j < 4096; ++j) {
                (this.j[j] = new char[20])[18] = '\u0001';
                this.j[j][19] = (char)(j * 8);
            }
        }
        else if (n == 1) {
            for (int k = 0; k < 20; ++k) {
                this.k[k] = '\0';
            }
            for (int l = 0; l < 4096; ++l) {
                this.j[l] = new char[20];
                System.arraycopy(this.k, 0, this.j[l], 0, 18);
                this.j[l][18] = '\u0004';
                this.j[l][19] = '\0';
            }
        }
        this.e = e;
        this.f = e - 32;
        this.a = 1L;
        this.a(o, s);
        while (this.c != e) {
            if (this.a == 1) {
                this.a = 65536L;
                this.a |= (array[this.c++] & 0xFF);
                this.a |= (array[this.c++] & 0xFF) << 8;
            }
            this.g = ((this.c < this.f) ? 16 : 1);
            while (this.g-- > 0) {
                if ((this.a & 0x1) > 0) {
                    final int d = this.d;
                    final int n2 = array[this.c++] & 0xFF;
                    final int n3 = (n2 & 0xF0) << 4 | (array[this.c++] & 0xFF);
                    int n4 = n2 & 0xF;
                    int n5 = 0;
                    switch (this.n = this.j[n3][18]) {
                        case 1: {
                            n5 = this.j[n3][19];
                            array3[this.d++] = o2.a[n5++];
                            array3[this.d++] = o2.a[n5++];
                            array3[this.d++] = o2.a[n5++];
                            break;
                        }
                        case 2: {
                            n5 = this.j[n3][19];
                            array3[this.d++] = array3[n5++];
                            array3[this.d++] = array3[n5++];
                            array3[this.d++] = array3[n5++];
                            break;
                        }
                        case 4: {
                            n5 = this.j[n3][19];
                            array3[this.d++] = this.k[n5++];
                            array3[this.d++] = this.k[n5++];
                            array3[this.d++] = this.k[n5++];
                            break;
                        }
                        default: {
                            n5 = 0;
                            array3[this.d++] = this.j[n3][n5++];
                            array3[this.d++] = this.j[n3][n5++];
                            array3[this.d++] = this.j[n3][n5++];
                            break;
                        }
                    }
                    while (n4-- > 0) {
                        switch (this.n) {
                            case 1: {
                                array3[this.d++] = o2.a[n5++];
                                continue;
                            }
                            case 2: {
                                array3[this.d++] = array3[n5++];
                                continue;
                            }
                            case 4: {
                                array3[this.d++] = this.k[n5++];
                                continue;
                            }
                            default: {
                                array3[this.d++] = this.j[n3][n5++];
                                continue;
                            }
                        }
                    }
                    if (this.h > 0) {
                        this.l = d - this.h;
                        this.i = o3.a(array3, this.l);
                        this.j[this.i][18] = '\u0002';
                        this.j[this.i][19] = (char)this.l;
                        if (this.h == 2) {
                            ++this.l;
                            this.i = o3.a(array3, this.l);
                            this.j[this.i][18] = '\u0002';
                            this.j[this.i][19] = (char)this.l;
                        }
                        this.h = 0;
                    }
                    this.j[n3][18] = '\u0002';
                    this.j[n3][19] = (char)d;
                }
                else {
                    array3[this.d++] = (char)(array[this.c++] & 0xFF);
                    if (++this.h == 3) {
                        this.b = this.d - 3;
                        this.i = o3.a(array3, this.b);
                        this.j[this.i][18] = '\u0002';
                        this.j[this.i][19] = (char)this.b;
                        this.h = 2;
                    }
                }
                this.a >>= 1;
            }
        }
        for (int n6 = 0; n6 < array3.length; ++n6) {
            array2[n6] = (byte)array3[n6];
        }
        return this.d;
    }
}
