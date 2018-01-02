// 
// Decompiled by Procyon v0.5.30
// 

package ytdfriends.action;

import java.util.Iterator;
import edu.berkeley.guir.prefuse.Display;
import edu.berkeley.guir.prefuse.VisualItem;
import edu.berkeley.guir.prefuse.ItemRegistry;
import edu.berkeley.guir.prefuse.action.assignment.SizeFunction;

public class FriendsSizeFunction extends SizeFunction
{
    private double scaleThresh;
    private double targetScale;
    private double scale;
    private boolean magnify;
    
    public FriendsSizeFunction() {
        this.scaleThresh = 0.75;
        this.targetScale = 1.05;
        this.scale = 1.0;
        this.magnify = false;
    }
    
    public void run(final ItemRegistry registry, final double frac) {
        final Display display = registry.getDisplay(0);
        final double s = display.getScale();
        this.scale = ((s < this.scaleThresh) ? (this.targetScale / s) : 1.0);
        super.run(registry, frac);
        this.scale = 1.0;
        final Iterator iter = registry.getItems();
        while (iter.hasNext()) {
            final VisualItem item = iter.next();
            item.setSize(1.0);
        }
    }
    
    public double getSize(final VisualItem item) {
        final double rval = (this.magnify && this.shouldScale(item)) ? this.scale : 1.0;
        return rval;
    }
    
    private boolean shouldScale(final VisualItem item) {
        return item.isHighlighted();
    }
    
    public boolean isMagnify() {
        return this.magnify;
    }
    
    public void setMagnify(final boolean magnify) {
        this.magnify = magnify;
    }
    
    public double getScaleThresh() {
        return this.scaleThresh;
    }
    
    public void setScaleThresh(final double scaleThresh) {
        this.scaleThresh = scaleThresh;
    }
}
