import java.awt.FontMetrics;
import java.awt.Dimension;
import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

public class PolyhedronElement extends Element
{
    int n;
    PolygonElement[] P;
    
    public String toString() {
        return "[" + super.name + ": n=" + this.n + "]";
    }
    
    protected boolean defined() {
        for (int i = 0; i < this.n; ++i) {
            if (!this.P[i].defined()) {
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
            int n3 = 0;
            for (int i = 0; i < this.n; ++i) {
                for (int j = 0; j < this.P[i].n; ++j) {
                    n += this.P[i].V[j].x;
                    n2 += this.P[i].V[j].y;
                    ++n3;
                }
            }
            graphics.drawString(super.name, (int)(n / n3) - stringWidth / 2, (int)(n2 / n3) - height / 2 + fontMetrics.getAscent());
        }
    }
    
    protected void drawVertex(final Graphics graphics) {
        if (super.vertexColor != null && this.defined()) {
            for (int i = 0; i < this.n; ++i) {
                for (int j = 0; j < this.P[i].n; ++j) {
                    this.P[this.n].V[j].drawVertex(graphics, super.vertexColor);
                }
            }
        }
    }
    
    protected void drawEdge(final Graphics graphics) {
        if (super.edgeColor != null && this.defined()) {
            for (int i = 0; i < this.n; ++i) {
                for (int j = 0; j < this.P[i].n; ++j) {
                    LineElement.drawEdge(this.P[i].V[j], this.P[i].V[(j + 1) % this.P[i].n], graphics, super.edgeColor);
                }
            }
        }
    }
    
    protected void drawFace(final Graphics graphics) {
        if (super.faceColor != null && this.defined()) {
            for (int i = 0; i < this.n; ++i) {
                for (int j = 0; j < this.P[i].n; ++j) {
                    this.P[i].drawFace(graphics, super.faceColor);
                }
            }
        }
    }
}
