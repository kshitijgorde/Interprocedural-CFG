// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing.text.html;

import javax.swing.text.View;
import javax.swing.text.Element;
import javax.swing.text.AttributeSet;

class BRView extends InlineView
{
    AttributeSet attr;
    
    public BRView(final Element element) {
        super(element);
        this.attr = this.getStyleSheet().getViewAttributes(this);
    }
    
    public int getBreakWeight(final int n, final float n2, final float n3) {
        if (n == 0) {
            return 3000;
        }
        return super.getBreakWeight(n, n2, n3);
    }
}
