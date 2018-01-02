import java.awt.FontMetrics;
import java.awt.Dimension;
import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

public class SectorElement extends Element
{
    PointElement Center;
    PointElement A;
    PointElement B;
    PlaneElement P;
    
    SectorElement() {
        super.dimension = 2;
    }
    
    SectorElement(final PointElement center, final PointElement a, final PointElement b, final PlaneElement p4) {
        super.dimension = 2;
        this.Center = center;
        this.A = a;
        this.B = b;
        this.P = p4;
    }
    
    public String toString() {
        return "[" + super.name + ": Center=" + this.Center + " A=" + this.A + " B=" + this.B + "]";
    }
    
    double radius() {
        return this.Center.distance(this.A);
    }
    
    double radius2() {
        return this.Center.distance2(this.A);
    }
    
    protected void update() {
        this.P.update();
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
            final double radius = this.radius();
            final int n = (int)Math.round(2.0 * radius);
            final int n2 = -(int)Math.round(Math.atan2(this.A.y - this.Center.y, this.A.x - this.Center.x) * 180.0 / 3.141592653589793);
            int n3 = -(int)Math.round(this.Center.angle(this.A, this.B, this.P) * 180.0 / 3.141592653589793);
            if (n3 < 0) {
                n3 += 360;
            }
            graphics.drawArc((int)Math.round(this.Center.x - radius), (int)Math.round(this.Center.y - radius), n, n, n2, n3);
        }
    }
    
    protected void drawFace(final Graphics graphics) {
        if (super.faceColor != null && this.defined()) {
            graphics.setColor(super.faceColor);
            final double radius = this.radius();
            final int n = (int)Math.round(2.0 * radius);
            final int n2 = -(int)Math.round(Math.atan2(this.A.y - this.Center.y, this.A.x - this.Center.x) * 180.0 / 3.141592653589793);
            int n3 = -(int)Math.round(this.Center.angle(this.A, this.B, this.P) * 180.0 / 3.141592653589793);
            if (n3 < 0) {
                n3 += 360;
            }
            graphics.fillArc((int)Math.round(this.Center.x - radius), (int)Math.round(this.Center.y - radius), n, n, n2, n3);
        }
    }
}
