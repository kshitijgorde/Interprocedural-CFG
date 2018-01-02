// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.action.animate;

import prefuse.visual.VisualItem;
import prefuse.action.ItemAction;

public class LocationAnimator extends ItemAction
{
    public LocationAnimator() {
    }
    
    public LocationAnimator(final String s) {
        super(s);
    }
    
    public void process(final VisualItem visualItem, final double n) {
        final double startX = visualItem.getStartX();
        final double startY = visualItem.getStartY();
        visualItem.setX(startX + n * (visualItem.getEndX() - startX));
        visualItem.setY(startY + n * (visualItem.getEndY() - startY));
    }
}
