// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Event;

public final class bV extends aX
{
    private boolean w;
    public boolean q;
    private bJ q;
    
    public final void r(final boolean b) {
        if (!this.w) {
            this.w = true;
            if (this.w) {
                this.q = null;
                return;
            }
            this.q = null;
            for (int i = 0; i < super.q.q(); ++i) {
                final bJ q = super.q.q(i);
                if (this.q(q)) {
                    if (this.q == null) {
                        this.q = q;
                    }
                    else {
                        this.q(q, false);
                    }
                }
            }
        }
    }
    
    public final void q(final bJ q, final boolean b) {
        final String q2 = this.q();
        if (q.q(q2, new Boolean(b))) {
            if (!this.w && b) {
                if (this.q != null) {
                    this.q.q(q2, new Boolean(false));
                    super.q.q(this.q);
                }
                this.q = q;
            }
            super.q.q(q);
            if (super.q.q() == q) {
                super.q.w(q);
            }
        }
    }
    
    private boolean q(final bJ bj) {
        final Boolean b;
        return (b = (Boolean)bj.q(this.q())) != null && b;
    }
    
    public final boolean q(final Event event, final bJ bj) {
        if (event.id == 6301) {
            if (this.w) {
                this.q(bj, !this.q(bj));
            }
            else if (this.q != bj) {
                this.q(bj, true);
            }
        }
        return this.q = true;
    }
    
    public final void q(final Graphics graphics, final int n, int n2, int n3, final boolean b, final boolean b2) {
        if (this.q() == null) {
            n3 = n3 / 2 + 3;
            n2 = n2 / 2 - 1;
            graphics.setColor(Color.red);
            graphics.drawLine(n + n2, n3, n + n2 - 4, n3 - 4);
            graphics.drawLine(n + n2, n3 - 1, n + n2 - 3, n3 - 4);
            graphics.drawLine(n + n2, n3, n + n2 + 6, n3 - 6);
            graphics.drawLine(n + n2, n3 - 1, n + n2 + 6, n3 - 7);
            return;
        }
        super.q(graphics, n, n2, n3, b, b2);
    }
    
    public bV(final Object o, final String s) {
        super(o, s);
        this.w = false;
        this.q = false;
        this.w(22);
    }
    
    public bV(final String s) {
        this(null, s);
    }
}
