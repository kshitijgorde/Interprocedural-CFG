// 
// Decompiled by Procyon v0.5.30
// 

package chat;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Event;

public final class cC extends I
{
    private boolean g;
    public boolean e;
    public boolean f;
    private a a;
    
    public final void a(final boolean b) {
        if (!this.g) {
            this.g = true;
            if (this.g) {
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
    
    public final void a(final a a, final boolean b) {
        final String a2 = super.a;
        if (a.a(a2, new Boolean(b))) {
            if (!this.g && b) {
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
        if (this.f) {
            return false;
        }
        if (event.id == 6301) {
            if (this.g) {
                this.a(a, !this.a(a));
            }
            else if (this.a != a) {
                this.a(a, true);
            }
        }
        return this.e = true;
    }
    
    public final void a(final Graphics graphics, final int n, int n2, int n3, final boolean b, final boolean b2) {
        if (super.a == null) {
            n3 = n3 / 2 + 3;
            n2 = n2 / 2 - 1;
            if (super.a == null) {
                graphics.setColor(Color.red);
            }
            else {
                graphics.setColor(super.a);
            }
            graphics.drawLine(n + n2, n3, n + n2 - 4, n3 - 4);
            graphics.drawLine(n + n2, n3 - 1, n + n2 - 3, n3 - 4);
            graphics.drawLine(n + n2, n3, n + n2 + 6, n3 - 6);
            graphics.drawLine(n + n2, n3 - 1, n + n2 + 6, n3 - 7);
            return;
        }
        super.a(graphics, n, n2, n3, b, b2);
    }
    
    public cC(final Object o, final String s) {
        super(o, s);
        this.g = false;
        this.e = false;
        this.a(22);
    }
    
    public cC(final String s) {
        this(null, s);
    }
}
