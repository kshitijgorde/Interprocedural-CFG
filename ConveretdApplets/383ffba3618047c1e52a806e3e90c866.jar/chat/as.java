// 
// Decompiled by Procyon v0.5.30
// 

package chat;

import java.awt.Component;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Frame;
import java.awt.Event;
import java.awt.TextField;

public final class as extends C
{
    private i a;
    private i b;
    private TextField a;
    private ai a;
    private bh a;
    private boolean a;
    
    public final boolean handleEvent(final Event event) {
        switch (event.id) {
            case 401: {
                if (event.key == 10 || event.key == aZ.a) {
                    this.a.c();
                    return true;
                }
                if (event.key == 27) {
                    this.b.c();
                    return true;
                }
                break;
            }
            case 1001: {
                if (event.target == this.a) {
                    final m m;
                    (m = new m(66305, 1)).d = -1;
                    m.e = this.a.g;
                    m.a(0, 0, this.a.g);
                    if (!this.a.a(56)) {
                        m.a(0, "moveme=" + String.valueOf(this.a.g) + "=" + this.a.getText());
                    }
                    else {
                        m.a(0, "moveme=" + String.valueOf(this.a.g) + "=1");
                    }
                    this.a.m(m);
                    this.a = true;
                    this.dispose();
                }
                else if (event.target == this.b) {
                    this.a = true;
                    this.dispose();
                    if (this.a.b == -999 || !this.a.f) {
                        this.a.c();
                    }
                }
                return true;
            }
            case 201: {
                return this.a && super.handleEvent(event);
            }
        }
        return super.handleEvent(event);
    }
    
    public as(final Frame frame, final bh a, final ai a2) {
        super(frame, a.a(56) ? ak.a(540) : ak.a(541), true);
        this.a = new i(80, 20);
        this.b = new i(80, 20);
        this.a = new TextField(15);
        this.a = false;
        this.setBackground(a.a.h);
        this.setForeground(a.a.g);
        this.setResizable(false);
        this.a = a;
        final bb bb = new bb();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final GridBagLayout layout = new GridBagLayout();
        t t;
        if (this.a.a(56)) {
            t = new t(ak.a(ak.a(543), new String[] { a2.c }));
        }
        else {
            t = new t(ak.a(ak.a(542), new String[] { a2.c }));
        }
        ((i)(this.a = a2)).a(ak.a(2));
        this.a.d();
        this.setLayout(layout);
        gridBagConstraints.insets = new Insets(1, 5, 1, 5);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.fill = 2;
        t.setFont(ay.a);
        layout.setConstraints(t, gridBagConstraints);
        this.add(t);
        gridBagConstraints.weightx = 1.0;
        layout.setConstraints(bb, gridBagConstraints);
        this.add(bb);
        if (!this.a.a(56)) {
            gridBagConstraints.insets = new Insets(6, 6, 6, 6);
            gridBagConstraints.anchor = 10;
            gridBagConstraints.fill = 0;
            this.a.setEchoCharacter('*');
            final g g = new g(this.a);
            layout.setConstraints(g, gridBagConstraints);
            this.add(g);
        }
        gridBagConstraints.insets = new Insets(2, 5, 4, 5);
        gridBagConstraints.anchor = 13;
        gridBagConstraints.gridwidth = -1;
        gridBagConstraints.fill = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.0;
        this.b.a(ak.a(3));
        this.b.d();
        layout.setConstraints(this.b, gridBagConstraints);
        this.add(this.b);
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.gridwidth = 0;
        final f f = new f(this.a);
        layout.setConstraints(f, gridBagConstraints);
        this.add(f);
        this.pack();
        this.a.requestFocus();
    }
}
