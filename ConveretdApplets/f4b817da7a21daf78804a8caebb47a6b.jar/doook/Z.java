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

public final class Z extends N
{
    private al b;
    private al c;
    private TextField b;
    private ax a;
    private be f;
    private Frame a;
    private boolean e;
    
    public final boolean handleEvent(final Event event) {
        switch (event.id) {
            case 401: {
                if (event.key == 10 || event.key == F.e) {
                    this.b.e();
                    return true;
                }
                if (event.key == 27) {
                    this.c.e();
                    return true;
                }
                break;
            }
            case 1001: {
                if (event.target == this.b) {
                    final V v = new V(66305, 1);
                    v.u = -1;
                    v.j = this.f.b();
                    v.a(0, 0, this.f.b());
                    v.a(0, 0, "moveme=" + String.valueOf(this.a.b()) + "=" + this.b.getText());
                    this.f.F(v);
                    this.e = true;
                    this.dispose();
                }
                else if (event.target == this.c) {
                    this.e = true;
                    this.dispose();
                    if (this.f.w == -999 || !this.f.c) {
                        this.f.i();
                    }
                }
                return true;
            }
            case 201: {
                return this.e && super.handleEvent(event);
            }
        }
        return super.handleEvent(event);
    }
    
    public Z(final Frame a, final be f, final ax a2) {
        super(a, aG.a("Enter Password"), true);
        this.b = new al(80, 20);
        this.c = new al(80, 20);
        this.b = new TextField(15);
        this.e = false;
        this.setBackground(f.c.k);
        this.setForeground(f.c.j);
        this.setResizable(false);
        this.f = f;
        this.a = a;
        final ad ad = new ad();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final GridBagLayout layout = new GridBagLayout();
        final D d = new D(aC.a(aG.a("You must enter the correct password to enter %1."), new String[] { a2.g() }));
        this.a = a2;
        this.b.a(aG.a("OK"));
        this.b.f();
        this.setLayout(layout);
        gridBagConstraints.insets = new Insets(1, 5, 1, 5);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.fill = 2;
        d.setFont(aK.e);
        layout.setConstraints(d, gridBagConstraints);
        this.add(d);
        gridBagConstraints.weightx = 1.0;
        layout.setConstraints(ad, gridBagConstraints);
        this.add(ad);
        gridBagConstraints.insets = new Insets(6, 6, 6, 6);
        gridBagConstraints.anchor = 10;
        gridBagConstraints.fill = 0;
        this.b.setEchoCharacter('*');
        final aX ax = new aX(this.b);
        layout.setConstraints(ax, gridBagConstraints);
        this.add(ax);
        gridBagConstraints.insets = new Insets(2, 5, 4, 5);
        gridBagConstraints.anchor = 13;
        gridBagConstraints.gridwidth = -1;
        gridBagConstraints.fill = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.0;
        this.c.a(aG.a("Cancel"));
        this.c.f();
        layout.setConstraints(this.c, gridBagConstraints);
        this.add(this.c);
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.gridwidth = 0;
        final P p3 = new P(this.b);
        layout.setConstraints(p3, gridBagConstraints);
        this.add(p3);
        this.pack();
        this.b.requestFocus();
    }
}
