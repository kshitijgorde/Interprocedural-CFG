// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Event;

public class cf extends j
{
    private boolean a;
    private boolean m;
    private aU b;
    
    public void d(final boolean a) {
        if (a != this.a) {
            this.a = a;
            if (this.a) {
                this.b = null;
            }
            else {
                this.b = null;
                for (int i = 0; i < super.c.c(); ++i) {
                    final aU a2 = super.c.a(i);
                    if (this.c(a2)) {
                        if (this.b == null) {
                            this.b = a2;
                        }
                        else {
                            this.a(a2, false);
                        }
                    }
                }
            }
        }
    }
    
    public void a(final aU b, final boolean b2) {
        final String b3 = this.b();
        if (b.a(b3, new Boolean(b2))) {
            if (!this.a && b2) {
                if (this.b != null) {
                    ((cF)this.b).aw = true;
                    this.b.a(b3, new Boolean(false));
                    super.c.a(this.b);
                }
                this.b = b;
            }
            super.c.a(b);
            if (super.c.a() == b) {
                super.c.b(b);
            }
        }
    }
    
    public boolean c(final aU au) {
        final Boolean b = (Boolean)au.a(this.b());
        return b != null && b;
    }
    
    public boolean k() {
        return this.m;
    }
    
    public boolean a(final Event event, final aU au) {
        if (event.id == 6301) {
            if (this.a) {
                this.a(au, !this.c(au));
            }
            else if (this.b != au) {
                this.a(au, true);
            }
        }
        this.m = true;
        return ((cF)au).aw = true;
    }
    
    public void a(final Graphics graphics, final int n, final int n2, final int n3, final boolean b, final boolean b2) {
        if (this.a() == null) {
            final int n4 = n3 / 2 + 3;
            final int n5 = n2 / 2 - 1;
            graphics.setColor(Color.red);
            graphics.drawLine(n + n5, n4, n + n5 - 4, n4 - 4);
            graphics.drawLine(n + n5, n4 - 1, n + n5 - 3, n4 - 4);
            graphics.drawLine(n + n5, n4, n + n5 + 6, n4 - 6);
            graphics.drawLine(n + n5, n4 - 1, n + n5 + 6, n4 - 7);
        }
        else {
            super.a(graphics, n, n2, n3, b, b2);
        }
    }
    
    public cf(final Object o, final String s) {
        super(o, s);
        this.a = false;
        this.m = false;
        this.b(22);
    }
    
    public cf(final String s) {
        this(null, s);
    }
}
