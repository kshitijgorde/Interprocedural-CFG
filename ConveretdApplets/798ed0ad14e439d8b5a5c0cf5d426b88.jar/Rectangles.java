import java.awt.Point;
import java.awt.Rectangle;
import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class Rectangles extends Vector
{
    public Rectangles() {
    }
    
    public Rectangles(final Rectangle rectangle) {
        this.addElement(rectangle);
    }
    
    public final Rectangle rectangleAt(final int n) {
        return this.elementAt(n);
    }
    
    public final void Afegeix(final Rectangle rectangle) {
        this.addElement(rectangle);
    }
    
    public final void Afegeix(final Rectangles rectangles) {
        for (int i = 0; i < rectangles.size(); ++i) {
            this.addElement(rectangles.elementAt(i));
        }
    }
    
    public final boolean contains(final Point point) {
        boolean b = false;
        for (int i = 0; i < this.size(); ++i) {
            b |= this.rectangleAt(i).contains(point);
        }
        return b;
    }
}
