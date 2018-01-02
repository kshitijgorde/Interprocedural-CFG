import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Cursor;
import java.awt.Polygon;
import java.awt.Button;

// 
// Decompiled by Procyon v0.5.30
// 

public class FlecheButton extends Button
{
    public static final int NEXT = 1;
    public static final int PREVIOUS = -1;
    int type;
    int gap;
    Polygon p;
    
    public FlecheButton(final int type) {
        this.gap = 4;
        this.type = type;
        this.setCursor(new Cursor(12));
    }
    
    public void paint(final Graphics graphics) {
        super.paint(graphics);
        if (this.p == null) {
            final Dimension size = this.getSize();
            (this.p = new Polygon()).addPoint((this.type == 1) ? (size.width - this.gap) : this.gap, size.height / 2);
            this.p.addPoint((this.type == -1) ? (size.width - this.gap) : this.gap, this.gap);
            this.p.addPoint((this.type == -1) ? (size.width - this.gap) : this.gap, size.height - this.gap);
        }
        graphics.setColor(this.getForeground());
        graphics.fillPolygon(this.p);
    }
}
