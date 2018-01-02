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

public final class aY extends bC
{
    private cr a;
    private cr b;
    private TextField a;
    private aP a;
    private cs a;
    private boolean a;
    
    public final boolean handleEvent(final Event event) {
        switch (event.id) {
            case 401: {
                if (event.key == 10 || event.key == ce.a) {
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
                    final r r;
                    (r = new r(66305, 1)).d = -1;
                    r.e = this.a.i;
                    r.a(0, 0, this.a.i);
                    if (!this.a.a(56)) {
                        r.a(0, 0, "moveme=" + String.valueOf(this.a.i) + "=" + this.a.getText());
                    }
                    else {
                        r.a(0, 0, "moveme=" + String.valueOf(this.a.i) + "=1");
                    }
                    this.a.o(r);
                    this.a = true;
                    this.dispose();
                }
                else if (event.target == this.b) {
                    this.a = true;
                    this.dispose();
                    if (this.a.b == -999 || !this.a.h) {
                        this.a.j();
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
    
    public aY(final Frame frame, final cs a, final aP a2) {
        super(frame, a.a(56) ? aS.a(540) : aS.a(541), true);
        this.a = new cr(80, 20);
        this.b = new cr(80, 20);
        this.a = new TextField(15);
        this.a = false;
        this.setBackground(a.a.h);
        this.setForeground(a.a.g);
        this.setResizable(false);
        this.a = a;
        final k k = new k();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final GridBagLayout layout = new GridBagLayout();
        A a3;
        if (this.a.a(56)) {
            a3 = new A(bm.a(aS.a(543), new String[] { a2.d }));
        }
        else {
            a3 = new A(bm.a(aS.a(542), new String[] { a2.d }));
        }
        ((cr)(this.a = a2)).a(aS.a(2));
        this.a.d();
        this.setLayout(layout);
        gridBagConstraints.insets = new Insets(1, 5, 1, 5);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.fill = 2;
        a3.setFont(bk.a);
        layout.setConstraints(a3, gridBagConstraints);
        this.add(a3);
        gridBagConstraints.weightx = 1.0;
        layout.setConstraints(k, gridBagConstraints);
        this.add(k);
        if (!this.a.a(56)) {
            gridBagConstraints.insets = new Insets(6, 6, 6, 6);
            gridBagConstraints.anchor = 10;
            gridBagConstraints.fill = 0;
            this.a.setEchoCharacter('*');
            final i i = new i(this.a);
            layout.setConstraints(i, gridBagConstraints);
            this.add(i);
        }
        gridBagConstraints.insets = new Insets(2, 5, 4, 5);
        gridBagConstraints.anchor = 13;
        gridBagConstraints.gridwidth = -1;
        gridBagConstraints.fill = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.0;
        this.b.a(aS.a(3));
        this.b.d();
        layout.setConstraints(this.b, gridBagConstraints);
        this.add(this.b);
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.gridwidth = 0;
        final g g = new g(this.a);
        layout.setConstraints(g, gridBagConstraints);
        this.add(g);
        this.pack();
        this.a.requestFocus();
    }
}
