import java.awt.RenderingHints;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Dimension;

// 
// Decompiled by Procyon v0.5.30
// 

public final class rp_az
{
    rp_aJ a;
    rp_J a;
    Dimension a;
    Graphics a;
    rp_ew a;
    Rectangle a;
    
    public rp_az(final rp_aJ a, final rp_J a2, final String s) {
        this.a = new Dimension();
        this.a = a;
        this.a = a2;
    }
    
    Rectangle a(Graphics graphics) {
        Image image = null;
        if (graphics == null) {
            a(graphics = (image = new BufferedImage(10, 10, 2)).getGraphics());
        }
        final rp_an rp_an;
        (rp_an = new rp_an(graphics)).a = true;
        rp_an.b = rp_aJ.k;
        rp_an.c = rp_aJ.g;
        final Rectangle a;
        (a = this.a.a(rp_an, this.a, true)).grow((int)(a.getWidth() / 50.0), (int)(a.getHeight() / 50.0));
        if (image != null) {
            image.flush();
        }
        return a;
    }
    
    static void a(final Graphics graphics) {
        if (graphics != null && graphics instanceof Graphics2D) {
            ((Graphics2D)graphics).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        }
    }
    
    void a() {
        if (rp_aJ.l) {
            final int n = this.a.height - 10;
            final int n2 = this.a.a().a() ? 5 : 1;
            int n3 = 0;
            for (int i = 0; i <= n2; ++i) {
                n3 = (this.a.a().a() ? (i * 12 * 2540) : (i * 100000)) / this.a.a;
                this.a.drawLine(n3, n - 3, n3, n + 3);
            }
            this.a.drawLine(0, n, n3, n);
            this.a.drawString(this.a.a().a() ? "5'" : "1m", n3 + 5, this.a.height - 4);
        }
    }
}
