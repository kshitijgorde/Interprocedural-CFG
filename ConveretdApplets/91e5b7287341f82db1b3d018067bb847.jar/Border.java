import java.awt.Point;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

public class Border extends Panel
{
    protected int thickness;
    protected int gap;
    protected DrawnRectangle border;
    protected static int defaultThickness;
    protected static int defaultGap;
    
    public Border(final Component borderMe) {
        this(borderMe, Border.defaultThickness, Border.defaultGap);
    }
    
    public Border(final Component borderMe, final int thickness) {
        this(borderMe, thickness, Border.defaultGap);
    }
    
    public Border(final Component borderMe, final int thickness, final int gap) {
        this.thickness = thickness;
        this.gap = gap;
        this.setLayout(new BorderLayout());
        this.add("Center", borderMe);
    }
    
    public Insets insets() {
        return new Insets(this.thickness + this.gap, this.thickness + this.gap, this.thickness + this.gap, this.thickness + this.gap);
    }
    
    public Rectangle getInnerBounds() {
        return this.border().getInnerBounds();
    }
    
    public void setLineColor(final Color c) {
        this.border().setLineColor(c);
    }
    
    public Color getLineColor() {
        return this.border().getLineColor();
    }
    
    public void paint(final Graphics g) {
        this.border().paint();
    }
    
    public void resize(final int w, final int h) {
        final Point location = this.location();
        this.reshape(location.x, location.y, w, h);
    }
    
    public void reshape(final int x, final int y, final int w, final int h) {
        super.reshape(x, y, w, h);
        this.border().resize(w, h);
    }
    
    protected DrawnRectangle border() {
        if (this.border == null) {
            this.border = new DrawnRectangle(this, this.thickness);
        }
        return this.border;
    }
}
