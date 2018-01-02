import java.awt.FontMetrics;
import java.awt.Dimension;
import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

public class CircleElement extends Element
{
    PointElement Center;
    PointElement A;
    PointElement B;
    PlaneElement AP;
    
    CircleElement() {
        super.dimension = 2;
    }
    
    CircleElement(final PointElement center, final PointElement a, final PointElement b, final PlaneElement ap) {
        super.dimension = 2;
        this.Center = center;
        this.A = a;
        this.B = b;
        this.AP = ap;
    }
    
    CircleElement(final PointElement pointElement, final PointElement b, final PlaneElement ap) {
        super.dimension = 2;
        this.Center = pointElement;
        this.A = pointElement;
        this.B = b;
        this.AP = ap;
    }
    
    public String toString() {
        return "[" + super.name + ": Center=" + this.Center + " A=" + this.A + " B=" + this.B + " AP=" + this.AP + "]";
    }
    
    double radius() {
        return this.A.distance(this.B);
    }
    
    double radius2() {
        return this.A.distance2(this.B);
    }
    
    protected void drawName(final Graphics graphics, final Dimension dimension) {
        if (super.nameColor != null && super.name != null) {
            graphics.setColor(super.nameColor);
            final FontMetrics fontMetrics = graphics.getFontMetrics();
            graphics.drawString(super.name, (int)this.Center.x - fontMetrics.stringWidth(super.name) / 2, (int)this.Center.y - fontMetrics.getHeight() / 2 + fontMetrics.getAscent());
        }
    }
    
    protected boolean defined() {
        return true;
    }
    
    protected void drawEdge(final Graphics graphics) {
        if (super.edgeColor != null && this.defined()) {
            graphics.setColor(super.edgeColor);
            final double sqrt = Math.sqrt(this.radius2());
            final double n = this.AP.S.z * this.AP.S.z + this.AP.T.z * this.AP.T.z;
            if (Math.abs(n) < 0.01) {
                graphics.drawOval((int)Math.round(this.Center.x - sqrt), (int)Math.round(this.Center.y - sqrt), (int)Math.round(2.0 * sqrt), (int)Math.round(2.0 * sqrt));
                return;
            }
            final double n2 = sqrt / Math.sqrt(n);
            final double n3 = n2 * this.AP.T.z;
            final double n4 = -n2 * this.AP.S.z;
            final double n5 = n3 * this.AP.S.x + n4 * this.AP.T.x;
            final double n6 = n3 * this.AP.S.y + n4 * this.AP.T.y;
            final double n7 = (n < 1.0) ? Math.sqrt(1.0 - n) : 0.0;
            drawEllipse(graphics, (int)Math.round(this.Center.x), (int)Math.round(this.Center.y), n5, n6, -n7 * n6, n7 * n5);
        }
    }
    
    protected void drawFace(final Graphics graphics) {
        if (super.faceColor != null && this.defined()) {
            graphics.setColor(super.faceColor);
            final double sqrt = Math.sqrt(this.radius2());
            final double n = this.AP.S.z * this.AP.S.z + this.AP.T.z * this.AP.T.z;
            if (Math.abs(n) < 0.01) {
                graphics.fillOval((int)Math.round(this.Center.x - sqrt), (int)Math.round(this.Center.y - sqrt), (int)Math.round(2.0 * sqrt), (int)Math.round(2.0 * sqrt));
                return;
            }
            final double n2 = sqrt / Math.sqrt(n);
            final double n3 = n2 * this.AP.T.z;
            final double n4 = -n2 * this.AP.S.z;
            final double n5 = n3 * this.AP.S.x + n4 * this.AP.T.x;
            final double n6 = n3 * this.AP.S.y + n4 * this.AP.T.y;
            final double n7 = (n < 1.0) ? Math.sqrt(1.0 - n) : 0.0;
            fillEllipse(graphics, (int)Math.round(this.Center.x), (int)Math.round(this.Center.y), n5, n6, -n7 * n6, n7 * n5);
        }
    }
    
    public static void fillEllipse(final Graphics graphics, final int n, final int n2, double abs, double abs2, double abs3, double abs4) {
        if (Math.abs(abs) < 0.5 && Math.abs(abs4) < 0.5) {
            abs2 = Math.abs(abs2);
            abs3 = Math.abs(abs3);
            graphics.fillOval(n - (int)Math.round(abs3), n2 - (int)Math.round(abs2), (int)Math.round(2.0 * abs3), (int)Math.round(2.0 * abs2));
            return;
        }
        if (Math.abs(abs2) < 0.5 && Math.abs(abs3) < 0.5) {
            abs = Math.abs(abs);
            abs4 = Math.abs(abs4);
            graphics.fillOval(n - (int)Math.round(abs), n2 - (int)Math.round(abs4), (int)Math.round(2.0 * abs), (int)Math.round(2.0 * abs4));
            return;
        }
        if (Math.abs(abs3) + Math.abs(abs4) < 0.5) {
            graphics.drawLine(n - (int)abs, n2 - (int)abs2, n + (int)abs, n2 + (int)abs2);
            return;
        }
        if (Math.abs(abs) + Math.abs(abs2) < 0.5) {
            graphics.drawLine(n - (int)abs3, n2 - (int)abs4, n + (int)abs3, n2 + (int)abs4);
            return;
        }
        final double n3 = abs2 * abs2 + abs4 * abs4;
        final double n4 = (abs * abs2 + abs3 * abs4) / n3;
        final double n5 = n4 * n4;
        final double n6 = (abs * abs + abs3 * abs3) / n3;
        final double n7 = n5 - n6;
        final double n8 = abs * abs4 - abs2 * abs3;
        final double n9 = n8 * n8 / n3;
        for (int n10 = (int)Math.round(Math.sqrt(n9 / (n6 - n5))), n11 = 0; n11 <= n10 && n2 + n11 >= 0; ++n11) {
            final double sqrt = Math.sqrt(n7 * (n11 * n11) + n9);
            final double n12 = n4 * n11;
            graphics.drawLine((int)(n + n12 + sqrt), n2 + n11, (int)(n + n12 - sqrt), n2 + n11);
            graphics.drawLine((int)(n - n12 + sqrt), n2 - n11, (int)(n - n12 - sqrt), n2 - n11);
        }
    }
    
    public static void drawEllipse(final Graphics graphics, final int n, final int n2, double abs, double abs2, double abs3, double abs4) {
        if (Math.abs(abs) < 0.5 && Math.abs(abs4) < 0.5) {
            abs2 = Math.abs(abs2);
            abs3 = Math.abs(abs3);
            graphics.drawOval((int)Math.round(n - abs3), (int)Math.round(n2 - abs2), (int)Math.round(2.0 * abs3), (int)Math.round(2.0 * abs2));
            return;
        }
        if (Math.abs(abs2) < 0.5 && Math.abs(abs3) < 0.5) {
            abs = Math.abs(abs);
            abs4 = Math.abs(abs4);
            graphics.drawOval((int)Math.round(n - abs), (int)Math.round(n2 - abs4), (int)Math.round(2.0 * abs), (int)Math.round(2.0 * abs4));
            return;
        }
        if (Math.abs(abs3) + Math.abs(abs4) < 0.5) {
            graphics.drawLine(n - (int)abs, n2 - (int)abs2, n + (int)abs, n2 + (int)abs2);
            return;
        }
        if (Math.abs(abs) + Math.abs(abs2) < 0.5) {
            graphics.drawLine(n - (int)abs3, n2 - (int)abs4, n + (int)abs3, n2 + (int)abs4);
            return;
        }
        final double n3 = abs2 * abs2 + abs4 * abs4;
        final double n4 = (abs * abs2 + abs3 * abs4) / n3;
        final double n5 = n4 * n4;
        final double n6 = (abs * abs + abs3 * abs3) / n3;
        final double n7 = n5 - n6;
        final double n8 = abs * abs4 - abs2 * abs3;
        final double n9 = n8 * n8 / n3;
        final int n10 = (int)Math.floor(Math.sqrt(n9 / (n6 - n5)));
        int n11 = 0;
        int n12 = 0;
        int n13 = 0;
        int n14 = 0;
        for (int i = 0; i <= n10; ++i) {
            final int n15 = n2 - i;
            final int n16 = n2 + i;
            if (n16 < 0) {
                break;
            }
            final double sqrt = Math.sqrt(n7 * (i * i) + n9);
            final double n17 = n4 * i;
            final int n18 = (int)(n - n17 - sqrt);
            final int n19 = (int)(n - n17 + sqrt);
            final int n20 = (int)(n + n17 - sqrt);
            final int n21 = (int)(n + n17 + sqrt);
            if (i == 0) {
                graphics.drawLine(n18, n15, n18, n15);
                graphics.drawLine(n19, n15, n19, n15);
            }
            else {
                graphics.drawLine(n18, n15, n11, n15);
                graphics.drawLine(n12, n15, n19, n15);
                graphics.drawLine(n20, n16, n13, n16);
                graphics.drawLine(n14, n16, n21, n16);
            }
            n11 = n18;
            n12 = n19;
            n13 = n20;
            n14 = n21;
        }
        graphics.drawLine(n11, n2 - n10 - 1, n12, n2 - n10 - 1);
        graphics.drawLine(n13, n2 + n10 + 1, n14, n2 + n10 + 1);
    }
}
