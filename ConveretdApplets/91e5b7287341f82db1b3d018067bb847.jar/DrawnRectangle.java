import java.awt.Graphics;
import java.awt.Color;
import java.awt.Component;
import java.awt.Rectangle;

// 
// Decompiled by Procyon v0.5.30
// 

public class DrawnRectangle extends Rectangle
{
    protected static int defaultThickness;
    protected Component drawInto;
    private int thick;
    private Color lineColor;
    private Color fillColor;
    
    public DrawnRectangle(final Component drawInto) {
        this(drawInto, DrawnRectangle.defaultThickness, 0, 0, 0, 0);
    }
    
    public DrawnRectangle(final Component drawInto, final int thick) {
        this(drawInto, thick, 0, 0, 0, 0);
    }
    
    public DrawnRectangle(final Component drawInto, final int x, final int y, final int w, final int h) {
        this(drawInto, DrawnRectangle.defaultThickness, x, y, w, h);
    }
    
    public DrawnRectangle(final Component drawInto, final int thick, final int x, final int y, final int w, final int h) {
        this.drawInto = drawInto;
        this.thick = thick;
        this.reshape(x, y, w, h);
    }
    
    public Component component() {
        return this.drawInto;
    }
    
    public int getThickness() {
        return this.thick;
    }
    
    public void setThickness(final int thick) {
        this.thick = thick;
    }
    
    public void setLineColor(final Color lineColor) {
        this.lineColor = lineColor;
    }
    
    public void setFillColor(final Color fillColor) {
        this.fillColor = fillColor;
    }
    
    public void fill() {
        this.fill(this.getFillColor());
    }
    
    public Color getLineColor() {
        if (this.lineColor == null) {
            this.lineColor = this.drawInto.getBackground().darker().darker().darker();
        }
        return this.lineColor;
    }
    
    public Color getFillColor() {
        if (this.fillColor == null) {
            this.fillColor = this.drawInto.getBackground();
        }
        return this.fillColor;
    }
    
    public Rectangle getInnerBounds() {
        return new Rectangle(super.x + this.thick, super.y + this.thick, super.width - this.thick * 2, super.height - this.thick * 2);
    }
    
    public void paint() {
        final Graphics g = this.drawInto.getGraphics();
        this.paintFlat(g, this.getLineColor());
    }
    
    private void paintFlat(final Graphics g, final Color c) {
        if (g != null) {
            g.setColor(c);
            for (int i = 0; i < this.thick; ++i) {
                g.drawRect(super.x + i, super.y + i, super.width - i * 2 - 1, super.height - i * 2 - 1);
            }
        }
    }
    
    public void clearInterior() {
        this.fill(this.drawInto.getBackground());
    }
    
    public void clearExterior() {
        this.paintFlat(this.drawInto.getGraphics(), this.drawInto.getBackground());
    }
    
    public void clear() {
        this.clearExterior();
        this.clearInterior();
    }
    
    public void fill(final Color color) {
        final Graphics g = this.drawInto.getGraphics();
        if (g != null) {
            final Rectangle r = this.getInnerBounds();
            g.setColor(color);
            g.fillRect(r.x, r.y, r.width, r.height);
            this.setFillColor(color);
        }
    }
    
    protected Color brighter() {
        return this.getLineColor().brighter().brighter().brighter().brighter();
    }
    
    static {
        DrawnRectangle.defaultThickness = 2;
    }
}
