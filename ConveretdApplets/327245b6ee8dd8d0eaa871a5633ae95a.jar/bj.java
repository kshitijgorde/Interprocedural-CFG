import java.awt.Point;

// 
// Decompiled by Procyon v0.5.30
// 

public class bj extends ai
{
    int a(final Point point) {
        point.x <<= 8;
        point.y <<= 8;
        super.ab.a(point);
        int n = ac.c(point.x, point.y) >> 14;
        if (n > 256) {
            n = 256;
        }
        return super.ac[n];
    }
    
    void a(final int[] array, int n, final int n2, final Point point) {
        final boolean l = c.l;
        point.x <<= 8;
        point.y <<= 8;
        super.ab.a(point);
        int x = point.x;
        int y = point.y;
        final int n3 = super.ab.a >> 8;
        final int n4 = super.ab.b >> 8;
        int i = ac.c(x, y) >> 14;
        if (i > 256) {
            i = 256;
        }
        if (super.r) {
            int n5 = 0;
            while (true) {
                int n10 = 0;
                int n11 = 0;
                Label_0308: {
                    if (!l) {
                        break Label_0308;
                    }
                    final int n6 = x >> 14;
                    final int n7 = n6 * n6;
                    final int n8 = y >> 14;
                    final int n9 = n7 + n8 * n8;
                    while (true) {
                        Label_0155: {
                            if (!l) {
                                break Label_0155;
                            }
                            --i;
                        }
                        if (n9 < i * i) {
                            continue;
                        }
                        break;
                    }
                    while (i < 256) {
                        n10 = n9;
                        n11 = (i + 1) * (i + 1);
                        if (l) {
                            break Label_0308;
                        }
                        if (n10 <= n11) {
                            break;
                        }
                        ++i;
                    }
                    final int n12 = super.ac[i];
                    int n13 = n12 >>> 24;
                    if (n13 > 0) {
                        ++n13;
                    }
                    final int n14 = 256 - n13;
                    final int n15 = array[n];
                    array[n++] = (((n12 & 0xFF00) * n13 + (n15 & 0xFF00) * n14 >> 8 & 0xFF00) | ((n12 & 0xFF00FF) * n13 + (n15 & 0xFF00FF) * n14 >> 8 & 0xFF00FF) | 0xFF000000);
                    x += n3;
                    y += n4;
                    ++n5;
                }
                if (n10 < n11) {
                    continue;
                }
                break;
            }
        }
        else {
            int n16 = 0;
            while (true) {
                Label_0437: {
                    if (!l) {
                        break Label_0437;
                    }
                    final int n17 = x >> 14;
                    final int n18 = n17 * n17;
                    final int n19 = y >> 14;
                    final int n20 = n18 + n19 * n19;
                    while (true) {
                        Label_0364: {
                            if (!l) {
                                break Label_0364;
                            }
                            --i;
                        }
                        if (n20 >= i * i) {
                            while (true) {
                                while (i < 256) {
                                    final int n21 = n20;
                                    final int n22 = (i + 1) * (i + 1);
                                    if (l) {
                                        y = n21 + n22;
                                        ++n16;
                                        break Label_0437;
                                    }
                                    if (n21 <= n22) {
                                        break;
                                    }
                                    ++i;
                                }
                                array[n++] = super.ac[i];
                                x += n3;
                                continue;
                            }
                        }
                        continue;
                    }
                }
                if (n16 < n2) {
                    continue;
                }
                break;
            }
        }
    }
}
