import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Dimension;
import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

public class PolygonElement extends Element
{
    int n;
    PointElement[] V;
    
    PolygonElement() {
        super.dimension = 2;
    }
    
    PolygonElement(final int n) {
        super.dimension = 2;
        this.n = n;
        this.V = new PointElement[this.n];
    }
    
    PolygonElement(final PointElement pointElement, final PointElement pointElement2, final PointElement pointElement3) {
        super.dimension = 2;
        this.n = 3;
        (this.V = new PointElement[3])[0] = pointElement;
        this.V[1] = pointElement2;
        this.V[2] = pointElement3;
    }
    
    PolygonElement(final PointElement pointElement, final PointElement pointElement2, final PointElement pointElement3, final PointElement pointElement4) {
        super.dimension = 2;
        this.n = 4;
        (this.V = new PointElement[4])[0] = pointElement;
        this.V[1] = pointElement2;
        this.V[2] = pointElement3;
        this.V[3] = pointElement4;
    }
    
    PolygonElement(final PointElement pointElement, final PointElement pointElement2, final PointElement pointElement3, final PointElement pointElement4, final PointElement pointElement5) {
        super.dimension = 2;
        this.n = 5;
        (this.V = new PointElement[5])[0] = pointElement;
        this.V[1] = pointElement2;
        this.V[2] = pointElement3;
        this.V[3] = pointElement4;
        this.V[4] = pointElement5;
    }
    
    PolygonElement(final PointElement pointElement, final PointElement pointElement2, final PointElement pointElement3, final PointElement pointElement4, final PointElement pointElement5, final PointElement pointElement6) {
        super.dimension = 2;
        this.n = 6;
        (this.V = new PointElement[6])[0] = pointElement;
        this.V[1] = pointElement2;
        this.V[2] = pointElement3;
        this.V[3] = pointElement4;
        this.V[4] = pointElement5;
        this.V[5] = pointElement6;
    }
    
    PolygonElement(final PointElement pointElement, final PointElement pointElement2, final PointElement pointElement3, final PointElement pointElement4, final PointElement pointElement5, final PointElement pointElement6, final PointElement pointElement7, final PointElement pointElement8) {
        super.dimension = 2;
        this.n = 8;
        (this.V = new PointElement[8])[0] = pointElement;
        this.V[1] = pointElement2;
        this.V[2] = pointElement3;
        this.V[3] = pointElement4;
        this.V[4] = pointElement5;
        this.V[5] = pointElement6;
        this.V[6] = pointElement7;
        this.V[7] = pointElement8;
    }
    
    public String toString() {
        return "[" + super.name + ": n=" + this.n + "]";
    }
    
    protected boolean defined() {
        for (int i = 0; i < this.n; ++i) {
            if (!this.V[i].defined()) {
                return false;
            }
        }
        return true;
    }
    
    protected void drawName(final Graphics graphics, final Dimension dimension) {
        if (super.nameColor != null && super.name != null && this.defined()) {
            graphics.setColor(super.nameColor);
            final FontMetrics fontMetrics = graphics.getFontMetrics();
            final int stringWidth = fontMetrics.stringWidth(super.name);
            final int height = fontMetrics.getHeight();
            double n = 0.0;
            double n2 = 0.0;
            for (int i = 0; i < this.n; ++i) {
                n += this.V[i].x;
                n2 += this.V[i].y;
            }
            graphics.drawString(super.name, (int)(n / this.n) - stringWidth / 2, (int)(n2 / this.n) - height / 2 + fontMetrics.getAscent());
        }
    }
    
    protected void drawVertex(final Graphics graphics) {
        if (super.vertexColor != null && this.defined()) {
            for (int i = 1; i < this.n; ++i) {
                this.V[i].drawVertex(graphics, super.vertexColor);
            }
        }
    }
    
    protected void drawEdge(final Graphics graphics) {
        if (super.edgeColor != null && this.defined()) {
            for (int i = 0; i < this.n; ++i) {
                LineElement.drawEdge(this.V[i], this.V[(i + 1) % this.n], graphics, super.edgeColor);
            }
        }
    }
    
    protected void drawFace(final Graphics graphics) {
        this.drawFace(graphics, super.faceColor);
    }
    
    protected void drawFace(final Graphics graphics, final Color color) {
        if (color != null && this.defined()) {
            graphics.setColor(color);
            final int[] array = new int[this.n];
            final int[] array2 = new int[this.n];
            for (int i = 0; i < this.n; ++i) {
                array[i] = (int)Math.round(this.V[i].x);
                array2[i] = (int)Math.round(this.V[i].y);
            }
            graphics.fillPolygon(array, array2, this.n);
        }
    }
    
    double area() {
        double n = 0.0;
        for (int i = 0; i < this.n - 2; ++i) {
            n += PointElement.area(this.V[0], this.V[i + 1], this.V[i + 2]);
        }
        return n;
    }
}
