// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.Component;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Event;
import java.awt.Frame;
import java.awt.TextField;

public final class ae extends p
{
    private aS e;
    private aS f;
    private TextField j;
    private T a;
    private t e;
    private Frame e;
    private boolean Z;
    
    public final boolean handleEvent(final Event event) {
        switch (event.id) {
            case 401: {
                if (event.key == 10 || event.key == doook.f.g) {
                    this.e.s();
                    return true;
                }
                if (event.key == 27) {
                    this.f.s();
                    return true;
                }
                break;
            }
            case 1001: {
                if (event.target == this.e) {
                    final cD cd = new cD(66305, 1);
                    cd.o = -1;
                    cd.j = this.e.h();
                    cd.a(0, 0, this.e.h());
                    if (!this.e.d(56)) {
                        cd.a(0, 0, "moveme=" + String.valueOf(this.a.h()) + "=" + this.j.getText());
                    }
                    else {
                        cd.a(0, 0, "moveme=" + String.valueOf(this.a.h()) + "=1");
                    }
                    this.e.o(cd);
                    this.Z = true;
                    this.dispose();
                }
                else if (event.target == this.f) {
                    this.Z = true;
                    this.dispose();
                    if (this.e.h == -999 || !this.e.e) {
                        this.e.i();
                    }
                }
                return true;
            }
            case 201: {
                return this.Z && super.handleEvent(event);
            }
        }
        return super.handleEvent(event);
    }
    
    public ae(final Frame e, final t e2, final T a) {
        super(e, e2.d(56) ? ao.e("Moving To Locked Room") : ao.e("Enter Password"), true);
        this.e = new aS(80, 20);
        this.f = new aS(80, 20);
        this.j = new TextField(15);
        this.Z = false;
        this.setBackground(e2.a.g);
        this.setForeground(e2.a.f);
        this.setResizable(false);
        this.e = e2;
        this.e = e;
        final cH ch = new cH();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final GridBagLayout layout = new GridBagLayout();
        c c;
        if (!this.e.d(56)) {
            c = new c(am.a(ao.e("You must enter the correct password to enter %1."), new String[] { a.f() }));
        }
        else {
            c = new c(am.a(ao.e("Are you sure you want to enter this looked room %1 ?"), new String[] { a.f() }));
        }
        this.a = a;
        this.e.a(ao.e("OK"));
        this.e.t();
        this.setLayout(layout);
        gridBagConstraints.insets = new Insets(1, 5, 1, 5);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.fill = 2;
        c.setFont(bL.e);
        layout.setConstraints(c, gridBagConstraints);
        this.add(c);
        gridBagConstraints.weightx = 1.0;
        layout.setConstraints(ch, gridBagConstraints);
        this.add(ch);
        if (!this.e.d(56)) {
            gridBagConstraints.insets = new Insets(6, 6, 6, 6);
            gridBagConstraints.anchor = 10;
            gridBagConstraints.fill = 0;
            this.j.setEchoCharacter('*');
            final aR ar = new aR(this.j);
            layout.setConstraints(ar, gridBagConstraints);
            this.add(ar);
        }
        gridBagConstraints.insets = new Insets(2, 5, 4, 5);
        gridBagConstraints.anchor = 13;
        gridBagConstraints.gridwidth = -1;
        gridBagConstraints.fill = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.0;
        this.f.a(ao.e("Cancel"));
        this.f.t();
        layout.setConstraints(this.f, gridBagConstraints);
        this.add(this.f);
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.gridwidth = 0;
        final aQ aq = new aQ(this.e);
        layout.setConstraints(aq, gridBagConstraints);
        this.add(aq);
        this.pack();
        this.j.requestFocus();
    }
}
