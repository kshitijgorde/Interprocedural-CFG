// 
// Decompiled by Procyon v0.5.30
// 

package com.eventim.applet.b;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.Box;

public class ag extends Box
{
    private JPanel a;
    private boolean b;
    private JPanel c;
    private JPanel d;
    
    public ag(final boolean b, final boolean b2, final Color color, final Color color2, final Color color3) {
        super(1);
        this.b = b2;
        (this.d = new z(color2, color)).setPreferredSize(new Dimension(Integer.MAX_VALUE, 20));
        this.d.setBackground(color3);
        this.a = new v(this, color);
        this.add(this.d);
        this.add(this.a);
        if (b) {
            (this.c = new x(this, color)).setPreferredSize(new Dimension(Integer.MAX_VALUE, 3));
            this.c.setBackground(color3);
            this.add(this.c);
        }
    }
    
    static boolean a(final ag ag) {
        return ag.b;
    }
    
    public final JPanel b() {
        return this.a;
    }
    
    public final JPanel c() {
        return this.c;
    }
    
    public final JPanel d() {
        return this.d;
    }
}
