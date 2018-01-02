// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Scrollbar;

public final class p extends Scrollbar
{
    private static final boolean q;
    
    public final void setValues(final int n, final int n2, final int n3, int n4) {
        if (p.q) {
            n4 -= n2;
        }
        int pageIncrement;
        if ((pageIncrement = n2 - this.getLineIncrement()) <= 0) {
            pageIncrement = 1;
        }
        this.setPageIncrement(pageIncrement);
        super.setValues(n, n2, n3, n4);
    }
    
    public final void setValue(final int n) {
        super.setValues(n, this.getVisible(), this.getMinimum(), super.getMaximum());
    }
    
    public final int getMaximum() {
        final int maximum = super.getMaximum();
        if (p.q) {
            return maximum;
        }
        final int visible = this.getVisible();
        if (maximum > visible) {
            return maximum - visible;
        }
        return 0;
    }
    
    public p(final int n) {
        super(1);
    }
    
    static {
        q = (new Scrollbar(1, 100, 50, 0, 100).getValue() == 100);
    }
}
