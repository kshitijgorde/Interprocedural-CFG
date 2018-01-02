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

public final class bb extends bC
{
    private cr a;
    private cr b;
    private TextField a;
    private cs a;
    private boolean a;
    private int a;
    private int b;
    
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
                    r.e = this.a;
                    r.a(0, 0, this.a.i);
                    r.a(0, 0, "move=" + String.valueOf(this.b) + "=" + this.a.getText());
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
    
    public bb(final Frame frame, final cx a, final int b, final int a2) {
        super(frame, aS.a(541), true);
        this.a = a2;
        this.b = b;
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
        final A a3 = new A(aS.a(544));
        this.a.a(aS.a(2));
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
        gridBagConstraints.insets = new Insets(6, 6, 6, 6);
        gridBagConstraints.anchor = 10;
        gridBagConstraints.fill = 0;
        this.a.setEchoCharacter('*');
        final i i = new i(this.a);
        layout.setConstraints(i, gridBagConstraints);
        this.add(i);
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
