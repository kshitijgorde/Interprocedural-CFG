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

public final class d extends aA
{
    private ax a;
    private ax b;
    private TextField a;
    private bn a;
    private as a;
    private Frame a;
    private boolean b;
    
    public final boolean handleEvent(final Event event) {
        switch (event.id) {
            case 401: {
                if (event.key == 10 || event.key == bs.c) {
                    this.a.l();
                    return true;
                }
                if (event.key == 27) {
                    this.b.l();
                    return true;
                }
                break;
            }
            case 1001: {
                if (event.target == this.a) {
                    final aJ aj = new aJ(66305, 1);
                    aj.C = -1;
                    aj.j = this.a.e();
                    aj.a(0, 0, this.a.e());
                    aj.a(0, 0, "moveme=" + String.valueOf(this.a.e()) + "=" + this.a.getText());
                    this.a.q(aj);
                    this.b = true;
                    this.dispose();
                }
                else if (event.target == this.b) {
                    this.b = true;
                    this.dispose();
                    if (this.a.t == -999 || !this.a.d) {
                        this.a.n();
                    }
                }
                return true;
            }
            case 201: {
                return this.b && super.handleEvent(event);
            }
        }
        return super.handleEvent(event);
    }
    
    public d(final Frame a, final as a2, final bn a3) {
        super(a, ar.b("Enter Password"), true);
        this.a = new ax(80, 20);
        this.b = new ax(80, 20);
        this.a = new TextField(15);
        this.b = false;
        this.setBackground(a2.b.g);
        this.setForeground(a2.b.f);
        this.setResizable(false);
        this.a = a2;
        this.a = a;
        final K k = new K();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final GridBagLayout layout = new GridBagLayout();
        final R r = new R(H.a(ar.b("You must enter the correct password to enter %1."), new String[] { a3.d() }));
        ((ax)(this.a = a3)).a(ar.b("OK"));
        this.a.p();
        this.setLayout(layout);
        gridBagConstraints.insets = new Insets(1, 5, 1, 5);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.fill = 2;
        r.setFont(ay.d);
        layout.setConstraints(r, gridBagConstraints);
        this.add(r);
        gridBagConstraints.weightx = 1.0;
        layout.setConstraints(k, gridBagConstraints);
        this.add(k);
        gridBagConstraints.insets = new Insets(6, 6, 6, 6);
        gridBagConstraints.anchor = 10;
        gridBagConstraints.fill = 0;
        this.a.setEchoCharacter('*');
        final aw aw = new aw(this.a);
        layout.setConstraints(aw, gridBagConstraints);
        this.add(aw);
        gridBagConstraints.insets = new Insets(2, 5, 4, 5);
        gridBagConstraints.anchor = 13;
        gridBagConstraints.gridwidth = -1;
        gridBagConstraints.fill = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.0;
        this.b.a(ar.b("Cancel"));
        this.b.p();
        layout.setConstraints(this.b, gridBagConstraints);
        this.add(this.b);
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.gridwidth = 0;
        final au au = new au(this.a);
        layout.setConstraints(au, gridBagConstraints);
        this.add(au);
        this.pack();
        this.a.requestFocus();
    }
}
