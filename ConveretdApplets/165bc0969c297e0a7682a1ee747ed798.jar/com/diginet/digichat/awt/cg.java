// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.awt;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Event;

public class cg extends ap
{
    public static final int DEFAULT = 0;
    public static final int PLURAL = 1;
    public static final int SINGLE = 2;
    private boolean b;
    private int nMode;
    private m c;
    
    public void setMode(final int nMode) {
        if (nMode != this.nMode) {
            this.nMode = nMode;
            if (this.nMode == 1) {
                this.c = null;
            }
            else {
                this.c = null;
                for (int i = 0; i < super.j.e(); ++i) {
                    final m j = super.j.j(i);
                    if (this.a(j)) {
                        if (this.c == null) {
                            this.c = j;
                        }
                        else {
                            this.a(j, false);
                        }
                    }
                }
            }
        }
    }
    
    public void d(final boolean mode) {
        this.setMode(mode ? 1 : 0);
    }
    
    public void a(final m c, final boolean b) {
        final String e = this.e();
        if (c.a(e, new Boolean(b))) {
            if (b) {
                if (this.nMode != 1) {
                    if (this.c != null) {
                        this.c.a(e, new Boolean(false));
                        super.j.a(this.c);
                    }
                    this.c = c;
                }
            }
            else if (this.nMode == 2) {
                this.c = null;
            }
            super.j.a(c);
            if (super.j.g() == c) {
                super.j.c(c);
            }
        }
    }
    
    public boolean a(final m m) {
        final Boolean b = (Boolean)m.e(this.e());
        return b != null && b;
    }
    
    public boolean g() {
        return this.b;
    }
    
    public void clear() {
        this.b = false;
    }
    
    public boolean a(final Event event, final m m) {
        if (event.id == 6301) {
            if (this.nMode != 0) {
                this.a(m, !this.a(m));
            }
            else if (this.c != m) {
                this.a(m, true);
            }
        }
        return this.b = true;
    }
    
    public void a(final Graphics graphics, final int n, final int n2, final int n3, final boolean b, final boolean b2) {
        if (this.f() == null) {
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
    
    public cg(final Object o, final String s) {
        super(o, s);
        this.nMode = 0;
        this.b = false;
        this.b(22);
    }
    
    public cg(final String s) {
        this(null, s);
    }
}
