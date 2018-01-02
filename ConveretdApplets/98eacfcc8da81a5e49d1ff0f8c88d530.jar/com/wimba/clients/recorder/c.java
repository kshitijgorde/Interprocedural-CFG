// 
// Decompiled by Procyon v0.5.30
// 

package com.wimba.clients.recorder;

import javax.swing.border.LineBorder;
import VT_6_1_0_11.ca;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import javax.swing.border.CompoundBorder;
import javax.swing.JPanel;
import java.awt.Component;
import java.awt.Point;
import javax.swing.BoundedRangeModel;
import javax.swing.Timer;
import VT_6_1_0_11.bs;
import java.awt.Color;
import javax.swing.border.Border;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionListener;
import javax.swing.JWindow;

public final class c extends JWindow implements ActionListener, ChangeListener
{
    private static final Border a;
    private static final Color b;
    private static final Color c;
    private static final Border d;
    private bs e;
    private boolean f;
    private boolean g;
    private Timer h;
    private Timer i;
    private BoundedRangeModel j;
    private Point k;
    private Component l;
    
    public c(final Component l, final BoundedRangeModel j) {
        this.f = false;
        this.g = false;
        this.k = new Point(0, 0);
        this.j = j;
        this.l = l;
        (this.h = new Timer(900, this)).setRepeats(false);
        (this.i = new Timer(50, this)).setRepeats(true);
        this.setSize(26, 90);
        final JPanel panel;
        (panel = new JPanel()).setBackground(com.wimba.clients.recorder.c.c);
        panel.setBorder(new CompoundBorder(com.wimba.clients.recorder.c.d, com.wimba.clients.recorder.c.a));
        panel.setLayout(new BorderLayout());
        panel.add(this.a(), "Center");
        this.a().b().addChangeListener(this);
        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(panel, "Center");
        l.addMouseListener(new f(this));
    }
    
    public final void setVisible(final boolean visible) {
        if (visible == this.isVisible()) {
            return;
        }
        if (visible) {
            this.i.restart();
        }
        else {
            this.i.stop();
            this.h.stop();
        }
        super.setVisible(visible);
    }
    
    public final bs a() {
        if (this.e == null) {
            (this.e = new a(this, this.j)).a(com.hw.client.util.c.a("/images/recorder/cursor_volume.png"));
            this.e.a(0);
        }
        return this.e;
    }
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() != this.h) {
            if (actionEvent.getSource() != this.i) {
                return;
            }
            if (this.l.getLocationOnScreen().equals(this.k)) {
                this.toFront();
                return;
            }
        }
        this.setVisible(false);
    }
    
    public final void stateChanged(final ChangeEvent changeEvent) {
        final BoundedRangeModel b = this.a().b();
        if (changeEvent.getSource() == b) {
            if (b.getValueIsAdjusting()) {
                this.f = true;
                this.h.stop();
                return;
            }
            if (this.f) {
                this.f = false;
                if (!this.g) {
                    this.h.restart();
                }
            }
        }
    }
    
    public final void a(final BoundedRangeModel j) {
        this.j = j;
    }
    
    static Point a(final c c, final Point k) {
        return c.k = k;
    }
    
    static Timer a(final c c) {
        return c.h;
    }
    
    static boolean a(final c c, final boolean g) {
        return c.g = g;
    }
    
    static boolean b(final c c) {
        return c.f;
    }
    
    static {
        a = new EmptyBorder(2, 2, 2, 2);
        b = ca.a("#BFBFBF", Color.darkGray);
        c = ca.a("#F0EFF3", Color.lightGray);
        d = new LineBorder(com.wimba.clients.recorder.c.b, 1, false);
    }
}
