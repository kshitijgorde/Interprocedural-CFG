// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tourweaver.f;

import java.awt.image.ImageObserver;
import com.easypano.tourweaver.a.e;
import java.awt.Image;
import com.easypano.tourweaver.a.d;

public class u extends t
{
    int H;
    int I;
    int J;
    int K;
    int L;
    
    public u() {
        this.H = 0;
        this.I = 0;
        this.J = 0;
        this.K = 0;
        this.L = 0;
        this.H = -1;
        this.I = -1;
    }
    
    public u(final int h, final int i) {
        this.H = 0;
        this.I = 0;
        this.J = 0;
        this.K = 0;
        this.L = 0;
        this.H = h;
        this.I = i;
    }
    
    public boolean a(final h h) {
        return false;
    }
    
    public void a(final int n) {
        final boolean i = com.easypano.tourweaver.f.r.i;
        final int n2 = this.J - n * this.J / 100;
        final int n3 = n2 * n2;
        int[] array2;
        final int[] array = array2 = super.A;
        if (!i) {
            if (array == null) {
                return;
            }
            final int[] b;
            array2 = (b = super.B);
        }
        if (!i) {
            if (array == null) {
                return;
            }
            array2 = super.C;
        }
        if (array2 != null) {
            int n4 = 0;
            do {
                Label_0058: {
                    final int k = this.K;
                }
                int j = 0;
                int n5 = 0;
                Label_0064:
                while (j < n5) {
                    if (!i) {
                        int l = 0;
                        while (l < this.L) {
                            final int n6 = l * this.K + n4;
                            final int n7 = (n4 - this.H) * (n4 - this.H) + (l - this.I) * (l - this.I);
                            Label_0178: {
                                Label_0164: {
                                    if (!i) {
                                        j = n7;
                                        n5 = n3;
                                        if (i) {
                                            continue Label_0064;
                                        }
                                        if (j >= n5) {
                                            break Label_0164;
                                        }
                                        super.A[n6] = super.B[n6];
                                    }
                                    if (!i) {
                                        break Label_0178;
                                    }
                                }
                                super.A[n6] = super.C[n6];
                            }
                            ++l;
                            if (i) {
                                break;
                            }
                        }
                        ++n4;
                        continue Label_0058;
                    }
                    return;
                }
                break;
            } while (!i);
            final d d = super.D;
            if (!i) {
                if (d == null) {
                    return;
                }
                final d d2 = super.D;
            }
            d.c();
        }
    }
    
    public void a(final Image image, final Image image2, final Image image3) {
        final boolean i = com.easypano.tourweaver.f.r.i;
        super.a(image, image2, image3);
        if (!i) {
            if (image2 == null) {
                return;
            }
            this.K = image2.getWidth(com.easypano.tourweaver.a.e.f);
            this.L = image2.getHeight(com.easypano.tourweaver.a.e.f);
        }
        u u = this;
        final int j;
        final int l;
        Label_0137: {
            Label_0119: {
                Label_0110: {
                    if (!i) {
                        if (this.H > 0) {
                            u = this;
                            if (i) {
                                break Label_0110;
                            }
                            if (this.I > 0) {
                                u = this;
                                if (i) {
                                    break Label_0110;
                                }
                                if (this.H < this.K) {
                                    j = this.I;
                                    l = this.L;
                                    if (i) {
                                        break Label_0137;
                                    }
                                    if (j < l) {
                                        break Label_0119;
                                    }
                                }
                            }
                        }
                        this.H = this.K / 2;
                        u = this;
                    }
                }
                u.I = this.L / 2;
            }
            final int n = this.H * this.H;
            final int n2 = this.I * this.I;
        }
        final int n3 = (int)Math.sqrt(j + l);
        final int n4 = (int)Math.sqrt((this.H - this.K) * (this.H - this.K) + this.I * this.I);
        final int n5 = (int)Math.sqrt((this.H - this.K) * (this.H - this.K) + (this.I - this.L) * (this.I - this.L));
        final int n6 = (int)Math.sqrt(this.H * this.H + (this.I - this.L) * (this.I - this.L));
        this.J = Math.max(n3, n4);
        this.J = Math.max(this.J, n5);
        this.J = Math.max(this.J, n6);
    }
}
