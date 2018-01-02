// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.action.animate;

import java.util.Iterator;
import prefuse.visual.expression.StartVisiblePredicate;
import prefuse.util.ColorLib;
import prefuse.visual.VisualItem;
import prefuse.action.ItemAction;

public class AxisLabelAnimator extends ItemAction
{
    protected AxisLabelAnimator() {
    }
    
    public AxisLabelAnimator(final String s) {
        super(s);
    }
    
    public void run(final double n) {
        if (n == 0.0) {
            this.setup();
        }
        else if (n == 1.0) {
            this.finish();
        }
        else {
            super.run(n);
        }
        this.m_vis.getGroup(this.m_group).putClientProperty("frac", new Double(n));
    }
    
    private void setup() {
        final Iterator visibleItems = this.m_vis.visibleItems(this.m_group);
        while (visibleItems.hasNext()) {
            final VisualItem visualItem = visibleItems.next();
            if (!visualItem.isStartVisible()) {
                final int endFillColor = visualItem.getEndFillColor();
                final int endStrokeColor = visualItem.getEndStrokeColor();
                final int endTextColor = visualItem.getEndTextColor();
                visualItem.setStartFillColor(ColorLib.setAlpha(endFillColor, 0));
                visualItem.setStartStrokeColor(ColorLib.setAlpha(endStrokeColor, 0));
                visualItem.setStartTextColor(ColorLib.setAlpha(endTextColor, 0));
                visualItem.setStartVisible(true);
            }
            this.process(visualItem, 0.0);
        }
        final Iterator items = this.m_vis.items(this.m_group, StartVisiblePredicate.TRUE);
        while (items.hasNext()) {
            final VisualItem visualItem2 = items.next();
            if (!visualItem2.isEndVisible()) {
                final int startFillColor = visualItem2.getStartFillColor();
                final int startStrokeColor = visualItem2.getStartStrokeColor();
                final int startTextColor = visualItem2.getStartTextColor();
                visualItem2.setEndFillColor(ColorLib.setAlpha(startFillColor, 0));
                visualItem2.setEndStrokeColor(ColorLib.setAlpha(startStrokeColor, 0));
                visualItem2.setEndTextColor(ColorLib.setAlpha(startTextColor, 0));
                visualItem2.setVisible(true);
                this.process(visualItem2, 0.0);
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
            this.process(visualItem2, 1.0);
            visualItem2.setStartFillColor(visualItem2.getEndFillColor());
            visualItem2.setStartTextColor(visualItem2.getEndTextColor());
            visualItem2.setStartStrokeColor(visualItem2.getEndStrokeColor());
        }
    }
    
    public void process(final VisualItem visualItem, final double n) {
        final double startX = visualItem.getStartX();
        visualItem.setX(startX + n * (visualItem.getEndX() - startX));
        final double startY = visualItem.getStartY();
        visualItem.setY(startY + n * (visualItem.getEndY() - startY));
        final double double1 = visualItem.getDouble(VisualItem.STARTX2);
        visualItem.setDouble(VisualItem.X2, double1 + n * (visualItem.getDouble(VisualItem.ENDX2) - double1));
        final double double2 = visualItem.getDouble(VisualItem.STARTY2);
        visualItem.setDouble(VisualItem.Y2, double2 + n * (visualItem.getDouble(VisualItem.ENDY2) - double2));
        visualItem.setStrokeColor(ColorLib.interp(visualItem.getStartStrokeColor(), visualItem.getEndStrokeColor(), n));
        visualItem.setTextColor(ColorLib.interp(visualItem.getStartTextColor(), visualItem.getEndTextColor(), n));
        visualItem.setFillColor(ColorLib.interp(visualItem.getStartFillColor(), visualItem.getEndFillColor(), n));
    }
}
