import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

public class LineElement extends Element
{
    PointElement A;
    PointElement B;
    
    LineElement() {
        super.dimension = 1;
    }
    
    LineElement(final PointElement a, final PointElement b) {
        super.dimension = 1;
        this.A = a;
        this.B = b;
    }
    
    public String toString() {
        return "[" + super.name + ": " + this.A + " " + this.B + "]";
    }
    
    protected boolean defined() {
        return this.A.defined() && this.B.defined();
    }
    
    protected void drawName(final Graphics graphics, final Dimension dimension) {
        if (super.nameColor != null && super.name != null && this.defined()) {
            this.drawString((int)Math.round((this.A.x + this.B.x) / 2.0), (int)Math.round((this.A.y + this.B.y) / 2.0), graphics, dimension);
        }
    }
    
    protected void drawVertex(final Graphics graphics) {
        if (super.vertexColor != null && this.defined()) {
            this.A.drawVertex(graphics, super.vertexColor);
            this.B.drawVertex(graphics, super.vertexColor);
        }
    }
    
    public static void drawEdge(final PointElement pointElement, final PointElement pointElement2, final Graphics graphics, final Color color) {
        if (color != null && pointElement.defined() && pointElement2.defined()) {
            graphics.setColor(color);
            graphics.drawLine((int)Math.round(pointElement.x), (int)Math.round(pointElement.y), (int)Math.round(pointElement2.x), (int)Math.round(pointElement2.y));
        }
    }
    
    protected void drawEdge(final Graphics graphics) {
        drawEdge(this.A, this.B, graphics, super.edgeColor);
    }
}
