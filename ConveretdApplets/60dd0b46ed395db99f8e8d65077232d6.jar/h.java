// 
// Decompiled by Procyon v0.5.30
// 

public class h extends b
{
    long k;
    boolean l;
    int m;
    int n;
    int[] o;
    byte[] p;
    
    public h(final int[] array, final int[] array2, final int n, final int n2, final int n3, final int n4) {
        super(array, array2, n, n2, n3, n4);
        this.k = 0L;
        this.l = true;
        this.m = 0;
        this.n = super.c * super.d;
        this.o = new int[this.n];
        this.p = new byte[65536];
    }
    
    public int[] a(final long n) {
        final int c = a.c;
        final int n2 = super.c * super.d;
        final int n3 = 256 * (int)n / super.f;
        final int n4 = 256 - n3;
        this.m = (this.m + 1) % 2;
        int l;
        final int n5 = l = (this.l ? 1 : 0);
        if (c == 0) {
            if (n5 != 0) {
                this.l = false;
                System.arraycopy(super.a, 0, this.o, 0, n2);
            }
            l = 0;
        }
        int n6 = l;
        int n8 = 0;
        while (true) {
            Label_0141: {
                if (c == 0) {
                    break Label_0141;
                }
                int n7 = n8;
                while (true) {
                    Label_0130: {
                        if (c == 0) {
                            break Label_0130;
                        }
                        this.p[(n6 << 8) + n7] = (byte)((n4 * n6 + n3 * n7) / 256);
                        ++n7;
                    }
                    if (n7 < 256) {
                        continue;
                    }
                    break;
                }
                ++n6;
            }
            if (n6 < 256) {
                continue;
            }
            n8 = 0;
            if (c != 0) {
                continue;
            }
            break;
        }
        int n9 = n8;
        while (true) {
            Label_0322: {
                if (c == 0) {
                    break Label_0322;
                }
                int n10 = (n9 + this.m) * super.c;
                int n11 = 0;
                while (true) {
                    Label_0310: {
                        if (c == 0) {
                            break Label_0310;
                        }
                        final int n12 = super.a[n10] & 0xFFFFFF;
                        final int n13 = super.b[n10] & 0xFFFFFF;
                        this.o[n10++] = (this.p[(n12 << 8 & 0xFF00) + (n13 & 0xFF)] & 0xFF) + (this.p[(n12 & 0xFF00) + (n13 >> 8 & 0xFF)] << 8 & 0xFF00) + (this.p[(n12 >> 8 & 0xFF00) + (n13 >> 16)] << 16 & 0xFF0000);
                        ++n11;
                    }
                    if (n11 < super.c) {
                        continue;
                    }
                    break;
                }
                n9 += 2;
            }
            if (n9 >= super.d) {
                return this.o;
            }
            continue;
        }
    }
}
