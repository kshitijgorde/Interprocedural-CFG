// 
// Decompiled by Procyon v0.5.30
// 

package com.wimba.clients.recorder;

import com.hw.client.util.a;
import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import VT_6_1_0_11.cP;
import javax.swing.border.Border;
import VT_6_1_0_11.du;
import javax.swing.JButton;
import VT_6_1_0_11.dv;
import java.io.InputStream;
import VT_6_1_0_11.X;
import VT_6_1_0_11.l;
import java.awt.event.ActionListener;
import VT_6_1_0_11.U;

public final class d extends U implements ActionListener
{
    private l k;
    private X l;
    protected InputStream a;
    protected dv b;
    private JButton m;
    private JButton n;
    
    public d(final l k, final du du) {
        super(k);
        this.setBorder(null);
        this.k = k;
        (this.l = new X(k, du)).g(false);
        this.l.b(true);
        this.l.c(false);
        this.m = new cP("Validate");
        this.n = new cP("Cancel");
        this.m.setEnabled(false);
        this.n.setEnabled(false);
        this.m.addActionListener(this);
        this.n.addActionListener(this);
        this.n();
    }
    
    protected final void n() {
        this.setLayout(new BorderLayout());
        this.add("Center", this.l);
    }
    
    public final void a(final boolean b) {
        this.l.d(b);
        this.m.setVisible(!b);
        this.n.setVisible(!b);
        this.revalidate();
    }
    
    public final void c() {
        this.l.n().c();
        this.setVisible(true);
    }
    
    public final X d() {
        return this.l;
    }
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        final String actionCommand = actionEvent.getActionCommand();
        if (com.hw.client.util.a.a()) {
            com.hw.client.util.a.b("RecorderContentPane.actionPerformed: ac=" + actionCommand);
        }
        if (actionCommand.equals("cancel_button")) {
            if (this.a != null) {
                this.l.a(this.b, this.a);
            }
            else {
                this.l.a(null, (InputStream)null);
            }
            this.n.setEnabled(false);
            this.m.setEnabled(false);
            return;
        }
        if (actionCommand.equals("validate_button")) {
            this.a = this.l.n().j;
            this.b = this.l.n().a();
            this.n.setEnabled(false);
            this.m.setEnabled(false);
            this.k.w();
            return;
        }
        com.hw.client.util.a.d("RecorderContentPane.action: unknown ac=" + actionCommand);
    }
}
