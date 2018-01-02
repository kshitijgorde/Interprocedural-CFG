// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.action.animate;

import java.util.Iterator;
import prefuse.visual.expression.StartVisiblePredicate;
import prefuse.util.ColorLib;
import prefuse.visual.VisualItem;
import prefuse.action.GroupAction;

public class VisibilityAnimator extends GroupAction
{
    public VisibilityAnimator() {
    }
    
    public VisibilityAnimator(final String s) {
        super(s);
    }
    
    public void run(final double n) {
        if (n == 0.0) {
            this.setup();
        }
        else if (n == 1.0) {
            this.finish();
        }
    }
    
    private void setup() {
        final Iterator visibleItems = this.m_vis.visibleItems(this.m_group);
        while (visibleItems.hasNext()) {
            final VisualItem visualItem = visibleItems.next();
            if (!visualItem.isStartVisible()) {
                visualItem.setStartFillColor(ColorLib.setAlpha(visualItem.getEndFillColor(), 0));
                visualItem.setStartStrokeColor(ColorLib.setAlpha(visualItem.getEndStrokeColor(), 0));
                visualItem.setStartTextColor(ColorLib.setAlpha(visualItem.getEndTextColor(), 0));
            }
        }
        final Iterator items = this.m_vis.items(this.m_group, StartVisiblePredicate.TRUE);
        while (items.hasNext()) {
            final VisualItem visualItem2 = items.next();
            if (!visualItem2.isEndVisible()) {
                visualItem2.setVisible(true);
                visualItem2.setEndFillColor(ColorLib.setAlpha(visualItem2.getStartFillColor(), 0));
                visualItem2.setEndStrokeColor(ColorLib.setAlpha(visualItem2.getStartStrokeColor(), 0));
                visualItem2.setEndTextColor(ColorLib.setAlpha(visualItem2.getStartTextColor(), 0));
            }
        }
    }
    
    private void finish() {
        final Iterator items = this.m_vis.items(this.m_group, StartVisiblePredicate.TRUE);
        while (items.hasNext()) {
            final VisualItem visualItem = items.next();
            if (!visualItem.isEndVisible()) {
                visualItem.setVisible(false);
                visualItem.setStartVisible(false);
            }
        }
        final Iterator visibleItems = this.m_vis.visibleItems(this.m_group);
        while (visibleItems.hasNext()) {
            final VisualItem visualItem2 = visibleItems.next();
            if (!visualItem2.isStartVisible()) {
                visualItem2.setStartVisible(true);
                visualItem2.setStartFillColor(visualItem2.getEndFillColor());
                visualItem2.setStartTextColor(visualItem2.getEndTextColor());
                visualItem2.setStartStrokeColor(visualItem2.getEndStrokeColor());
            }
        }
    }
}
