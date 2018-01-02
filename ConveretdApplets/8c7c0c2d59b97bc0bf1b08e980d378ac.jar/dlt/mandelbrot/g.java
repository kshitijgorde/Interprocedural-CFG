// 
// Decompiled by Procyon v0.5.30
// 

package dlt.mandelbrot;

import dlt.mandelbrot.a.i;
import dlt.mandelbrot.a.k;
import dlt.mandelbrot.a.m;
import dlt.mandelbrot.a.o;
import dlt.mandelbrot.a.p;
import dlt.mandelbrot.a.l;
import dlt.mandelbrot.a.f;
import dlt.mandelbrot.a.h;
import dlt.mandelbrot.a.n;
import dlt.mandelbrot.a.d;
import dlt.mandelbrot.a.e;
import dlt.mandelbrot.a.a;
import dlt.mandelbrot.a.c;
import javax.swing.event.ListSelectionEvent;
import java.awt.event.AdjustmentEvent;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import javax.swing.JList;
import javax.swing.JScrollPane;
import dlt.mandelbrot.a.j;
import javax.swing.event.ListSelectionListener;
import java.awt.event.AdjustmentListener;
import javax.swing.JPanel;

public class g extends JPanel implements AdjustmentListener, ListSelectionListener
{
    private static j[] a;
    private JScrollPane for;
    private JList if;
    private b do;
    
    public g() {
        this.setLayout(new BorderLayout());
        (this.if = new JList((E[])g.a)).setSelectedIndex(0);
        this.if.setVisibleRowCount(1);
        this.if.addListSelectionListener(this);
        this.for = new JScrollPane();
        this.for.getViewport().setView(this.if);
        this.for.getVerticalScrollBar().addAdjustmentListener(this);
        this.add(this.for, "North");
        (this.do = new b(g.a[0])).setSize(256, 20);
        this.add(this.do, "Center");
    }
    
    public void a(final AdjustmentListener adjustmentListener) {
        this.for.getVerticalScrollBar().addAdjustmentListener(adjustmentListener);
    }
    
    public void adjustmentValueChanged(final AdjustmentEvent adjustmentEvent) {
        this.if.setSelectedIndex(this.if.getFirstVisibleIndex());
        this.if.updateUI();
    }
    
    public j if() {
        return this.if.getSelectedValue();
    }
    
    public static j do() {
        return g.a[0];
    }
    
    public void a() {
        this.do.a();
    }
    
    public void valueChanged(final ListSelectionEvent listSelectionEvent) {
        if (this.if.getSelectedValue() == null) {
            this.if.setSelectedIndex(this.if.getFirstVisibleIndex());
        }
        else {
            this.if.setSelectedIndex(this.if.getSelectedIndex());
        }
        this.do.a(this.if.getSelectedValue());
        this.do.a();
    }
    
    static {
        g.a = new j[] { new c(), new a(), new e(), new d(), new n(), new h(), new f(), new l(), new p(), new o(), new m(), new k(), new i() };
    }
}
