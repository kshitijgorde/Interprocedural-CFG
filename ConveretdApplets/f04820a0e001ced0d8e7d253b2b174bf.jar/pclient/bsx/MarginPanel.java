// 
// Decompiled by Procyon v0.5.30
// 

package pclient.bsx;

import java.awt.Insets;
import java.awt.Panel;

public class MarginPanel extends Panel
{
    protected Insets insets;
    
    public MarginPanel() {
        this.insets = new Insets(0, 1, 0, 1);
    }
    
    public MarginPanel(final int n, final int n2, final int n3, final int n4) {
        this.insets = new Insets(0, 1, 0, 1);
        this.insets = new Insets(n, n2, n3, n4);
    }
    
    public Insets insets() {
        return this.insets;
    }
    
    public Insets getInsets() {
        return this.insets;
    }
}
