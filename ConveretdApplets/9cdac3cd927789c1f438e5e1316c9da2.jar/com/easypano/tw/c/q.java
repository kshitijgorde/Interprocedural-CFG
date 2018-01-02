// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw.c;

import java.awt.Graphics;
import java.awt.Color;

public class q
{
    protected Color a;
    protected Color b;
    protected Color c;
    
    public q() {
        this.a = Color.black;
        this.b = new Color(128, 0, 128);
        this.c = new Color(255, 0, 0);
    }
    
    public void a(final Color a) {
        if (a != null) {
            this.a = a;
        }
    }
    
    public void b(final Color b) {
        if (b != null) {
            this.b = b;
        }
    }
    
    public void c(final Color c) {
        if (c != null) {
            this.c = c;
        }
    }
    
    public void a(final Graphics graphics, final Object o, final boolean b, final boolean b2, final boolean b3, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7) {
        final boolean o2 = com.easypano.tw.c.a.O;
        final String string = o.toString();
        boolean b4 = b3;
        boolean b5 = b3;
        Label_0082: {
            if (!o2) {
                if (b3) {
                    graphics.setColor(this.b);
                    if (!o2) {
                        break Label_0082;
                    }
                }
                b4 = b;
                b5 = b;
            }
            if (!o2) {
                if (b5) {
                    graphics.setColor(this.b);
                    if (!o2) {
                        break Label_0082;
                    }
                }
                b4 = b2;
            }
            if (b4) {
                graphics.setColor(this.c);
                if (!o2) {
                    break Label_0082;
                }
            }
            graphics.setColor(this.a);
        }
        graphics.drawString(string, n, n2 + (n4 + n5 - n7) / 2 - n6);
    }
}
