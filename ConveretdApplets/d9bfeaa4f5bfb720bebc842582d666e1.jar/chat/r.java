// 
// Decompiled by Procyon v0.5.30
// 

package chat;

import java.awt.Scrollbar;

public final class r extends Scrollbar
{
    private static final boolean a;
    
    public final void setValues(final int n, final int n2, final int n3, int n4) {
        if (r.a) {
            n4 -= n2;
        }
        int pageIncrement;
        if ((pageIncrement = n2 - this.getLineIncrement()) < 1) {
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
        if (r.a) {
            return maximum;
        }
        final int visible = this.getVisible();
        if (maximum > visible) {
            return maximum - visible;
        }
        return 0;
    }
    
    public r() {
        super(1);
    }
    
    static {
        a = (new Scrollbar(1, 100, 50, 0, 100).getValue() == 100);
    }
}
