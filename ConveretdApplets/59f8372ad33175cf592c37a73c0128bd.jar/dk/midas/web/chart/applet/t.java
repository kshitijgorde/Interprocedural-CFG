// 
// Decompiled by Procyon v0.5.30
// 

package dk.midas.web.chart.applet;

import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Color;

public class t extends ac
{
    ad p;
    a1 q;
    ac n;
    m[] o;
    
    public t(final Color background, final Color foreground) {
        this.setLayout(new BorderLayout());
        this.setBackground(background);
        this.setForeground(foreground);
    }
    
    public boolean new(final String s) {
        if (this.o == null) {
            return false;
        }
        for (int i = 0; i < this.o.length; ++i) {
            if (s.compareTo(this.o[i].a()) == 0) {
                return this.o[i].if();
            }
        }
        return false;
    }
    
    public void do(final String s, final boolean b) {
        if (this.o == null) {
            return;
        }
        for (int i = 0; i < this.o.length; ++i) {
            if (s.compareTo(this.o[i].a()) == 0) {
                this.o[i].a(b);
                return;
            }
        }
    }
}
