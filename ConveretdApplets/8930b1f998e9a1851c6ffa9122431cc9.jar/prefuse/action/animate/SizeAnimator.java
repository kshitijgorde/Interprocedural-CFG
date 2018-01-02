// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.action.animate;

import prefuse.visual.VisualItem;
import prefuse.action.ItemAction;

public class SizeAnimator extends ItemAction
{
    public SizeAnimator() {
    }
    
    public SizeAnimator(final String s) {
        super(s);
    }
    
    public void process(final VisualItem visualItem, final double n) {
        final double startSize = visualItem.getStartSize();
        visualItem.setSize(startSize + n * (visualItem.getEndSize() - startSize));
    }
}
