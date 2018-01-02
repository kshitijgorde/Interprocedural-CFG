// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.awt;

import java.awt.Scrollbar;

public class a5 extends Scrollbar
{
    private static final boolean a;
    
    public final void setValues(final int n, final int n2, final int n3, int n4) {
        if (a5.a) {
            n4 -= n2;
        }
        int pageIncrement = n2 - this.getLineIncrement();
        if (pageIncrement < 1) {
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
        if (a5.a) {
            return maximum;
        }
        final int visible = this.getVisible();
        if (maximum > visible) {
            return maximum - visible;
        }
        return 0;
    }
    
    public a5(final int n) {
        super(n);
    }
    
    static {
        a = (new Scrollbar(1, 100, 50, 0, 100).getValue() == 100);
    }
}
