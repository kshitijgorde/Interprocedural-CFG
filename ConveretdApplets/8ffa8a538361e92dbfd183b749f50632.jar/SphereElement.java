import java.awt.FontMetrics;
import java.awt.Dimension;
import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

public class SphereElement extends Element
{
    PointElement Center;
    PointElement A;
    PointElement B;
    
    SphereElement() {
        super.dimension = 2;
    }
    
    SphereElement(final PointElement center, final PointElement a, final PointElement b) {
        super.dimension = 2;
        this.Center = center;
        this.A = a;
        this.B = b;
    }
    
    SphereElement(final PointElement pointElement, final PointElement b) {
        super.dimension = 2;
        this.Center = pointElement;
        this.A = pointElement;
        this.B = b;
    }
    
    public String toString() {
        return "[" + super.name + ": Center=" + this.Center + " A=" + this.A + " B=" + this.B + "]";
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
            graphics.drawOval((int)Math.round(this.Center.x - radius), (int)Math.round(this.Center.y - radius), n, n);
        }
    }
    
    protected void drawFace(final Graphics graphics) {
        if (super.faceColor != null && this.defined()) {
            graphics.setColor(super.faceColor);
            final double radius = this.radius();
            final int n = (int)Math.round(2.0 * radius);
            graphics.fillOval((int)Math.round(this.Center.x - radius), (int)Math.round(this.Center.y - radius), n, n);
        }
    }
    
    double radius() {
        return this.A.distance(this.B);
    }
    
    double radius2() {
        return this.A.distance2(this.B);
    }
}
