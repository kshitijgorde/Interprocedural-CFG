// 
// Decompiled by Procyon v0.5.30
// 

package dk.midas.web.chart.applet;

import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

public class a5 extends ac
{
    public boolean d;
    public int i;
    public boolean m;
    public boolean j;
    public boolean e;
    public int c;
    public int f;
    private int l;
    private int k;
    public boolean b;
    public boolean h;
    private ActionListener g;
    private g void;
    
    public a5(final int l, final int k, final ActionListener g) {
        this.g = g;
        this.d = true;
        this.i = 0;
        this.m = false;
        this.j = true;
        this.l = l;
        this.k = k;
        final int n = 50;
        this.f = n;
        this.c = n;
        this.a(true);
        this.setLayout(new FlowLayout(0, this.l, this.k));
    }
    
    public a5(final g void1, final ActionListener actionListener) {
        this(void1.bf, void1.bb, actionListener);
        this.void = void1;
        this.setBackground(void1.bn);
        this.setForeground(void1.a7);
        this.setFont(void1.bi);
        this.a(void1.bo.equalsIgnoreCase("vertical"));
        this.d = void1.bj;
        if (!(this.j = void1.bg)) {
            this.c = void1.bm;
            this.f = void1.bc;
        }
    }
    
    public void a(final boolean h) {
        this.e = h;
        this.b = h;
        this.h = h;
    }
    
    public void a(final ar ar) {
        ar.if = this;
        if (!this.j) {
            ar.setSize(this.c, this.f);
        }
        this.add(ar);
        super.add(ar.a);
    }
    
    public void for(final String s) {
        this.g.actionPerformed(new ActionEvent(this, 0, "1000;" + s));
    }
    
    public void a(final String s, final boolean b) {
        for (int i = 0; i < this.try(); ++i) {
            if (this.a(i).a(s, b)) {
                return;
            }
        }
    }
    
    public void if(final String s, final boolean b) {
        for (int i = 0; i < this.try(); ++i) {
            if (this.a(i).if(s, b)) {
                return;
            }
        }
    }
    
    public void int(final String s) {
        for (int i = 0; i < this.try(); ++i) {
            if (this.a(i).a(s)) {
                return;
            }
        }
    }
    
    public void remove(final int n) {
        this.a(n).if = null;
        this.remove((Component)null);
    }
    
    public int try() {
        return this.getComponentCount();
    }
    
    public ar a(final int n) {
        return (ar)this.getComponent(n);
    }
    
    public MenuItem do(final String s) {
        for (int i = 0; i < this.try(); ++i) {
            final MenuItem if1;
            if ((if1 = this.a(i).if(s)) != null) {
                return if1;
            }
        }
        return null;
    }
}
