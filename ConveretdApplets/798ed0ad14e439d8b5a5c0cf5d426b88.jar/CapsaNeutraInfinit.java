import java.awt.Font;
import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

public class CapsaNeutraInfinit extends NeuterBox
{
    Poly2 create;
    
    CapsaNeutraInfinit() {
        this.create = Poly2.create("m 0.8 1 0.75 0.15 d 16 20 ox 2 oy 9 inici 0 -9 g 90 t 4 z 8.5 0 -65 7 t 4 z 5.5 0 90 5 g -90 l 2 g -90 t 6 z -9.5 0 115 7 t 4 z -4.5 0 -90 3 inici 0 -9 clock g -90 t 4 z 8.5 0 65 7 t 4 z 5.5 0 -90 5 g 90 l 2 g 90 t 6 z -9.5 0 -115 7 t 4 z -4.5 0 90 3 end", 25.0f);
    }
    
    public final void calculRect(final BoxComponent boxComponent) {
        super.width = this.em(0.1f) + this.create.getWidth(super.D) + this.em(0.07f);
        if (!super.F.equals("\\infty")) {
            super.width += this.em(0.4f);
        }
        super.height = this.em(1.0f);
        super.baseline = this.em(0.75f);
    }
    
    public final void onPaint(final Graphics graphics) {
        final int style = super.B.getStyle();
        final Font b = super.B;
        int n = (int)(((style == 1) ? 1.3f : 1.0f) * super.height / 11.0f);
        if (n == 0) {
            n = 1;
        }
        final int em = this.em(0.1f);
        final int n2 = 45 * super.height / 100;
        final int n3 = 10 * super.height / 100;
        final int n4 = 20 * super.height / 100;
        int em2;
        if (super.F.equals("\\infty")) {
            em2 = 0;
        }
        else {
            em2 = this.em(0.4f);
        }
        final int n5 = em2 / 2 - n;
        if (super.F.equals("\\minfty")) {
            graphics.fillRect(em, n2 - n / 2, n5 + n + n5, n);
        }
        else if (super.F.equals("\\pinfty")) {
            graphics.fillRect(em, n2 - n / 2, n5 + n + n5, n);
            graphics.fillRect(em + n5, n2 - n / 2 - n5, n, n5 + n + n5);
        }
        else if (super.F.equals("\\pminfty")) {
            graphics.fillRect(em, n2 - n / 2 - n3, n5 + n + n5, n);
            graphics.fillRect(em + n5, n2 - n / 2 - n5 - n3, n, n5 + n + n5);
            graphics.fillRect(em, n2 - n / 2 + n4, n5 + n + n5, n);
        }
        graphics.translate(em2, 0);
        this.create.draw(em, super.baseline, super.D, true, true, graphics);
        graphics.translate(-em2, 0);
    }
}
