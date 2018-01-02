// 
// Decompiled by Procyon v0.5.30
// 

package dlt.a.a;

import java.util.Vector;
import java.awt.Color;

public class k extends f
{
    public k(final a a, final l l) {
        super(a, l);
    }
    
    protected void for(final dlt.a.c.a a) {
        final dlt.a.c.f[] new1 = a.new();
        final double n = new1.length;
        for (int i = 0; i < new1.length; ++i) {
            this.a((int)new1[i].if().for() + this.null / 2, (int)new1[i].if().a() + this.else / 2, (int)new1[(i + 1) % new1.length].if().for() + this.null / 2, (int)new1[(i + 1) % new1.length].if().a() + this.else / 2);
        }
    }
    
    public void a(int n, int n2, int n3, int n4) {
        final int rgb = Color.cyan.getRGB();
        if (n2 == n4) {
            if (n2 > 0 && n2 < this.else) {
                final int n5 = (n2 - 1) * this.null;
                if (n3 < n) {
                    final int n6 = n;
                    n = n3;
                    n3 = n6;
                }
                for (int i = n; i < n3; ++i) {
                    if (i > 0 && i < this.null) {
                        this.long.a(n5 + i, rgb);
                    }
                }
            }
        }
        else {
            if (n4 < n2) {
                final int n7 = n;
                final int n8 = n2;
                n = n3;
                n2 = n4;
                n3 = n7;
                n4 = n8;
            }
            final double n9 = (n3 - n) / (n4 - n2);
            double n10 = n;
            for (int j = n2; j < n4; ++j) {
                if (j > 0 && j < this.else) {
                    final int n11 = (j - 1) * this.null;
                    int n12;
                    int n13;
                    if (n9 < 0.0) {
                        n12 = (int)(n10 + n9);
                        n13 = (int)n10;
                    }
                    else {
                        n12 = (int)n10;
                        n13 = (int)(n10 + n9);
                    }
                    for (int k = n12; k <= n13; ++k) {
                        if (k > 0 && k < this.null) {
                            this.long.a(n11 + k, rgb);
                        }
                    }
                }
                n10 += n9;
            }
        }
    }
    
    protected void a(final Vector vector) {
    }
}
