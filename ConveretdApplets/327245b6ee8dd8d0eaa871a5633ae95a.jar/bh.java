import java.awt.Point;

// 
// Decompiled by Procyon v0.5.30
// 

public class bh extends ai
{
    bh() {
        super.r = true;
    }
    
    int a(final Point point) {
        final boolean l = c.l;
        point.x <<= 16;
        point.y <<= 16;
        super.ae.a(point);
        int n = point.x + 16384 >> 16;
        int n2 = point.y + 16384 >> 16;
        Label_0091: {
            if (n < 0) {
                n = 0;
                if (!l) {
                    break Label_0091;
                }
            }
            if (n >= super.af.c) {
                n = super.af.c - 1;
            }
        }
        Label_0123: {
            if (n2 < 0) {
                n2 = 0;
                if (!l) {
                    break Label_0123;
                }
            }
            if (n2 >= super.af.d) {
                n2 = super.af.d - 1;
            }
        }
        final int n3 = super.af.b[n + n2 * super.af.c];
        final int n4 = n3 & 0x7F000000;
        if (n4 == 0) {
            return 0;
        }
        if (n4 == 2130706432) {
            return n3 | 0xFF000000;
        }
        return (n3 & 0xFFFFFF) | (n4 >>> 23) + ((n3 & 0x40000000) >> 30) << 24;
    }
    
    void a(final int[] array, int n, final int n2, final Point point) {
        final boolean l = c.l;
        point.x <<= 16;
        point.y <<= 16;
        super.ae.a(point);
        int n3 = point.x + 16384;
        int n4 = point.y + 16384;
        final int a = super.ae.a;
        final int b = super.ae.b;
        final int c = super.af.c;
        final int d = super.af.d;
        int n5 = (n3 >> 16) + (n4 >> 16) * c;
        if (super.r) {
            int n6 = 0;
            while (true) {
                Label_0213: {
                    if (!l) {
                        break Label_0213;
                    }
                    final int n7 = super.af.b[n5];
                    Label_0190: {
                        if ((n7 & 0x7F000000) == 0x7F000000) {
                            array[n] = n7;
                            if (!l) {
                                break Label_0190;
                            }
                        }
                        if (n7 != 0) {
                            array[n] = (r.a(array[n], n7) | 0xFF000000);
                        }
                    }
                    ++n5;
                    ++n;
                    n3 += a;
                    n4 += b;
                    ++n6;
                }
                if (n6 < n2) {
                    continue;
                }
                break;
            }
        }
    }
}
