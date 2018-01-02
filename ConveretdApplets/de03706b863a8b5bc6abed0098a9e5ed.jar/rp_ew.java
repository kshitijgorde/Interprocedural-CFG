import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;

// 
// Decompiled by Procyon v0.5.30
// 

public final class rp_ew extends rp_eV
{
    Rectangle a;
    
    public rp_ew(final rp_fK rp_fK, final Rectangle a, final Point point, final int n) {
        super(point, n);
        (this = this).a = a;
    }
    
    final boolean a(final int n) {
        int a = n;
        if (n < 500) {
            a = 500;
        }
        if (a > 16459) {
            a = 16459;
        }
        if (a != this.a) {
            this.a = a;
            return true;
        }
        return false;
    }
    
    public final void a(final int n, final int n2, final Color color) {
        this.a.setColor(color);
        final int n3 = (this.a.x / n2 - 1) * n2;
        final int n4 = (this.a.y / n2 - 1) * n2;
        final int n5 = this.a.x + this.a.width * this.a;
        final int n6 = this.a.y + this.a.height * this.a;
        final int n7 = n3 + this.a.x * this.a;
        final int n8 = n4 + this.a.y * this.a;
        final int n9 = n5 + this.a.x * this.a;
        final int n10 = n6 + this.a.y * this.a;
        if (n == 1) {
            for (int i = n7; i < n9; i += n2) {
                this.a(i, n8, i, n10);
            }
            for (int j = n8; j < n10; j += n2) {
                this.a(this.a.x, j, n9, j);
            }
        }
        if (n == 2) {
            for (int k = n7; k < n9; k += n2) {
                for (int l = n8; l < n10; l += n2) {
                    this.a(k, l, k + 1, l);
                }
            }
        }
    }
}
