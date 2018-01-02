// 
// Decompiled by Procyon v0.5.30
// 

public final class Z
{
    int[] a;
    private int c;
    private int d;
    private int e;
    private int f;
    private int g;
    private int h;
    public boolean[] b;
    
    public Z() {
        this.b = new boolean[8];
        this.a = new int[64];
    }
    
    public final void a(final int n, final short n2, final short n3) {
        this.d = n << 3;
        this.e = 0;
        while (this.e < 8) {
            this.a[this.d + this.e] = (n2 >> 7 - this.e & 0x1) + ((n3 >> 7 - this.e & 0x1) << 1);
            if (this.a[this.d + this.e] == 0) {
                this.b[n] = false;
            }
            ++this.e;
        }
    }
    
    public final void a(int n, int n2, int n3, int n4, final int n5, final int n6, final int[] array, final int n7, final int[] array2, final boolean b, final boolean b2, final int n8, final int[] array3) {
        if (n5 < -7 || n5 >= 256 || n6 < -7 || n6 >= 240) {
            return;
        }
        if (n5 < 0) {
            n = 0 - n5;
        }
        if (n5 + 8 >= 256) {
            n3 = 256 - n5;
        }
        if (n6 < 0) {
            n2 -= n6;
        }
        if (n6 + n4 >= 240) {
            n4 = 240 - n6;
        }
        if (!b && !b2) {
            this.c = (n6 << 8) + n5;
            this.d = 0;
            this.f = 0;
            while (this.f < 8) {
                this.e = 0;
                while (this.e < 8) {
                    if (this.e >= n && this.e < n3 && this.f >= n2 && this.f < n4) {
                        this.g = this.a[this.d];
                        this.h = array3[this.c];
                        if (this.g != 0 && n8 <= (this.h & 0xFF)) {
                            array[this.c] = array2[this.g + n7];
                            this.h = ((this.h & 0xF00) | n8);
                            array3[this.c] = this.h;
                        }
                    }
                    ++this.c;
                    ++this.d;
                    ++this.e;
                }
                this.c -= 8;
                this.c += 256;
                ++this.f;
            }
        }
        else if (b && !b2) {
            this.c = (n6 << 8) + n5;
            this.d = 7;
            this.f = 0;
            while (this.f < 8) {
                this.e = 0;
                while (this.e < 8) {
                    if (this.e >= n && this.e < n3 && this.f >= n2 && this.f < n4) {
                        this.g = this.a[this.d];
                        this.h = array3[this.c];
                        if (this.g != 0 && n8 <= (this.h & 0xFF)) {
                            array[this.c] = array2[this.g + n7];
                            this.h = ((this.h & 0xF00) | n8);
                            array3[this.c] = this.h;
                        }
                    }
                    ++this.c;
                    --this.d;
                    ++this.e;
                }
                this.c -= 8;
                this.c += 256;
                this.d += 16;
                ++this.f;
            }
        }
        else if (b2 && !b) {
            this.c = (n6 << 8) + n5;
            this.d = 56;
            this.f = 0;
            while (this.f < 8) {
                this.e = 0;
                while (this.e < 8) {
                    if (this.e >= n && this.e < n3 && this.f >= n2 && this.f < n4) {
                        this.g = this.a[this.d];
                        this.h = array3[this.c];
                        if (this.g != 0 && n8 <= (this.h & 0xFF)) {
                            array[this.c] = array2[this.g + n7];
                            this.h = ((this.h & 0xF00) | n8);
                            array3[this.c] = this.h;
                        }
                    }
                    ++this.c;
                    ++this.d;
                    ++this.e;
                }
                this.c -= 8;
                this.c += 256;
                this.d -= 16;
                ++this.f;
            }
        }
        else {
            this.c = (n6 << 8) + n5;
            this.d = 63;
            this.f = 0;
            while (this.f < 8) {
                this.e = 0;
                while (this.e < 8) {
                    if (this.e >= n && this.e < n3 && this.f >= n2 && this.f < n4) {
                        this.g = this.a[this.d];
                        this.h = array3[this.c];
                        if (this.g != 0 && n8 <= (this.h & 0xFF)) {
                            array[this.c] = array2[this.g + n7];
                            this.h = ((this.h & 0xF00) | n8);
                            array3[this.c] = this.h;
                        }
                    }
                    ++this.c;
                    --this.d;
                    ++this.e;
                }
                this.c -= 8;
                this.c += 256;
                ++this.f;
            }
        }
    }
}
