import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

public class SelectionPainter implements PaintListener
{
    private Formula formula;
    
    public SelectionPainter(final Formula formula) {
        if (formula == null) {
            throw new NullPointerException();
        }
        this.formula = formula;
    }
    
    public final void onPaint(final Object o, final Graphics graphics, final boolean b) {
        if (!b && o == this.formula.getComponent()) {
            final AbstractSelection selectedArea = this.formula.getSelectedArea();
            if (selectedArea != null) {
                final AbstractSelection excludeSel = selectedArea.excludeSel;
                this.paintRectList(graphics, selectedArea.getRectangles(this.formula));
                if (excludeSel != null) {
                    this.paintRectList(graphics, excludeSel.getRectangles(this.formula));
                }
            }
        }
    }
    
    private void paintRectList(final Graphics graphics, final Rectangles rectangles) {
        if (rectangles == null) {
            return;
        }
        graphics.setColor(Color.white);
        graphics.setXORMode(this.formula.colors[12]);
        if (rectangles != null) {
            for (int i = 0; i < rectangles.size(); ++i) {
                final Rectangle rectangle = rectangles.rectangleAt(i);
                final Point origin = this.formula.getOrigin();
                rectangle.translate(origin.x, origin.y);
                graphics.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
            }
        }
        graphics.setPaintMode();
    }
    
    public final void update() {
        this.formula.repaint();
    }
}
