import java.awt.Point;

// 
// Decompiled by Procyon v0.5.30
// 

public class bi extends ai
{
    int a(final Point point) {
        point.x <<= 8;
        point.y <<= 8;
        super.ab.a(point);
        int n = (point.x >> 15) + 128;
        if (n > 256) {
            n = 256;
            if (!c.l) {
                return super.ac[n];
            }
        }
        if (n < 0) {
            n = 0;
        }
        return super.ac[n];
    }
    
    void a(final int[] array, int n, final int n2, final Point point) {
        final boolean l = c.l;
        point.x <<= 8;
        point.y <<= 8;
        super.ab.a(point);
        final int n3 = super.ab.a >> 8;
        int x = point.x;
        if (super.r) {
            int n4 = 0;
            while (true) {
                Label_0212: {
                    if (!l) {
                        break Label_0212;
                    }
                    int n5 = (x >> 15) + 128;
                    Label_0109: {
                        if (n5 > 256) {
                            n5 = 256;
                            if (!l) {
                                break Label_0109;
                            }
                        }
                        if (n5 < 0) {
                            n5 = 0;
                        }
                    }
                    final int n6 = super.ac[n5];
                    int n7 = n6 >>> 24;
                    if (n7 > 0) {
                        ++n7;
                    }
                    final int n8 = 256 - n7;
                    final int n9 = array[n];
                    array[n++] = (((n6 & 0xFF00) * n7 + (n9 & 0xFF00) * n8 >> 8 & 0xFF00) | ((n6 & 0xFF00FF) * n7 + (n9 & 0xFF00FF) * n8 >> 8 & 0xFF00FF) | 0xFF000000);
                    x += n3;
                    ++n4;
                }
                if (n4 < n2) {
                    continue;
                }
                break;
            }
        }
        else {
            int n10 = 0;
            while (true) {
                Label_0289: {
                    if (!l) {
                        break Label_0289;
                    }
                    int n11 = (x >> 15) + 128;
                    Label_0266: {
                        if (n11 > 256) {
                            n11 = 256;
                            if (!l) {
                                break Label_0266;
                            }
                        }
                        if (n11 < 0) {
                            n11 = 0;
                        }
                    }
                    array[n++] = super.ac[n11];
                    x += n3;
                    ++n10;
                }
                if (n10 < n2) {
                    continue;
                }
                break;
            }
        }
    }
}
