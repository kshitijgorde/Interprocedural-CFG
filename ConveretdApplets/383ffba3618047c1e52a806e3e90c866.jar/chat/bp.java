// 
// Decompiled by Procyon v0.5.30
// 

package chat;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Event;

public final class bp extends w
{
    private boolean b;
    private a a;
    
    public final void a(final boolean b) {
        if (!this.b) {
            this.b = true;
            if (this.b) {
                this.a = null;
                return;
            }
            this.a = null;
            for (int i = 0; i < super.a.a(); ++i) {
                final a a = super.a.a(i);
                if (this.a(a)) {
                    if (this.a == null) {
                        this.a = a;
                    }
                    else {
                        this.a(a, false);
                    }
                }
            }
        }
    }
    
    private void a(final a a, final boolean b) {
        final String a2 = super.a;
        if (a.a(a2, new Boolean(b))) {
            if (!this.b && b) {
                if (this.a != null) {
                    this.a.a(a2, new Boolean(false));
                    super.a.a(this.a);
                }
                this.a = a;
            }
            super.a.a(a);
            if (super.a.a() == a) {
                super.a.b(a);
            }
        }
    }
    
    private boolean a(final a a) {
        final Boolean b;
        return (b = (Boolean)a.a(super.a)) != null && b;
    }
    
    public final boolean a(final Event event, final a a) {
        if (event.id == 6301) {
            if (this.b) {
                this.a(a, !this.a(a));
            }
            else if (this.a != a) {
                this.a(a, true);
            }
        }
        return true;
    }
    
    public final void a(final Graphics graphics, final int n, int n2, final int n3, final boolean b, final boolean b2) {
        if (super.a == null) {
            final int n4 = n3 / 2 + 3;
            n2 = n2 / 2 - 1;
            graphics.setColor(Color.red);
            graphics.drawLine(n + n2, n4, n + n2 - 4, n4 - 4);
            graphics.drawLine(n + n2, n4 - 1, n + n2 - 3, n4 - 4);
            graphics.drawLine(n + n2, n4, n + n2 + 6, n4 - 6);
            graphics.drawLine(n + n2, n4 - 1, n + n2 + 6, n4 - 7);
            return;
        }
        super.a(graphics, n, n2, n3, b, b2);
    }
    
    private bp(final String s, final byte b) {
        super(null, s);
        this.b = false;
        this.a(22);
    }
    
    public bp(final String s) {
        this(s, (byte)0);
    }
}
