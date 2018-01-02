// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.action.layout;

import java.util.Iterator;
import java.awt.geom.Rectangle2D;
import prefuse.visual.VisualItem;
import java.util.Random;

public class RandomLayout extends Layout
{
    private Random r;
    
    public RandomLayout() {
        this.r = new Random(12345678L);
    }
    
    public RandomLayout(final String s) {
        super(s);
        this.r = new Random(12345678L);
    }
    
    public void setRandomSeed(final long seed) {
        this.r.setSeed(seed);
    }
    
    public void run(final double n) {
        final Rectangle2D layoutBounds = this.getLayoutBounds();
        final double width = layoutBounds.getWidth();
        final double height = layoutBounds.getHeight();
        final Iterator visibleItems = this.getVisualization().visibleItems(this.m_group);
        while (visibleItems.hasNext()) {
            final VisualItem visualItem = visibleItems.next();
            final double n2 = (int)(layoutBounds.getX() + this.r.nextDouble() * width);
            final double n3 = (int)(layoutBounds.getY() + this.r.nextDouble() * height);
            this.setX(visualItem, null, n2);
            this.setY(visualItem, null, n3);
        }
    }
}
