// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.action.animate;

import prefuse.util.FontLib;
import prefuse.visual.VisualItem;
import prefuse.action.ItemAction;

public class FontAnimator extends ItemAction
{
    public FontAnimator() {
    }
    
    public FontAnimator(final String s) {
        super(s);
    }
    
    public void process(final VisualItem visualItem, final double n) {
        visualItem.setFont(FontLib.getIntermediateFont(visualItem.getStartFont(), visualItem.getEndFont(), n));
    }
}
