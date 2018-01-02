// 
// Decompiled by Procyon v0.5.30
// 

package courseware.jfcdep;

import java.awt.Dimension;
import java.awt.Label;

public class ClearLabel extends Label
{
    public ClearLabel(final String s) {
        super(s);
        this.setAlignment(2);
    }
    
    public ClearLabel() {
        this.setAlignment(2);
    }
    
    public Dimension getPreferredSize() {
        final Dimension preferredSize = super.getPreferredSize();
        preferredSize.width = this.getGraphics().getFontMetrics().stringWidth(this.getText()) + 2;
        return preferredSize;
    }
}
