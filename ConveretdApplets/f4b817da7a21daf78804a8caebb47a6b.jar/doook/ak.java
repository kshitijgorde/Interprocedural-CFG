// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.Component;
import java.awt.Label;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.Panel;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Event;
import java.awt.Frame;
import java.awt.TextField;

public final class ak extends N
{
    private al b;
    private al c;
    private TextField b;
    private aW f;
    private Frame d;
    
    private final at a(final String s) {
        final aq[] array = new aq[this.f.d.a()];
        this.f.d.a(array);
        for (int i = 0; i < array.length; ++i) {
            if (array[i] != null && array[i].g().equals(s)) {
                final at at = new at(array[i].b(), array[i].g());
                at.h = true;
                at.v = array[i].v;
                at.a = array[i].a;
                at.w = array[i].w;
                at.a(array[i].d());
                return at;
            }
        }
        return null;
    }
    
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
                    final String trim = this.b.getText().trim();
                    if (trim.length() == 0) {
                        new E(this.d, aG.a("Note"), aG.a("You must provide a name for your Buddy.  Please re-enter this information."), this.f).setVisible(true);
                        return true;
                    }
                    if (trim.indexOf(44) != -1 || trim.indexOf(34) != -1) {
                        this.b.requestFocus();
                        this.b.selectAll();
                        new E(this.f.f, aG.a("Error"), aG.a("Names can not have commas or quotes.  Please re-enter this information."), this.f).setVisible(true);
                        return true;
                    }
                    at a = this.a(trim);
                    if (a == null) {
                        a = new at(-999, trim);
                        a.h = false;
                    }
                    if (this.f.a != null) {
                        ((aV)this.f.a).a(a, true, false);
                    }
                    this.dispose();
                }
                else if (event.target == this.c) {
                    this.dispose();
                }
                return true;
            }
        }
        return super.handleEvent(event);
    }
    
    public ak(final Frame frame, final aW f) {
        super(frame, aG.a("Add Buddy"), true);
        this.b = new al(80, 20);
        this.c = new al(80, 20);
        this.setBackground(f.c.k);
        this.setForeground(f.c.j);
        this.f = f;
        final GridBagLayout gridBagLayout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final Panel panel = new Panel();
        this.setResizable(false);
        this.setLayout(gridBagLayout);
        panel.setLayout(gridBagLayout);
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        gridBagConstraints.anchor = 13;
        this.b = new TextField(20);
        final Label label = new Label(aG.a("Name"));
        label.setFont(aK.g);
        gridBagConstraints.gridwidth = -1;
        gridBagLayout.setConstraints(label, gridBagConstraints);
        panel.add(label);
        gridBagConstraints.anchor = 17;
        gridBagConstraints.gridwidth = 0;
        final aX ax = new aX(this.b);
        gridBagLayout.setConstraints(ax, gridBagConstraints);
        panel.add(ax);
        gridBagLayout.setConstraints(panel, gridBagConstraints);
        this.add(panel);
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.anchor = 13;
        gridBagConstraints.gridwidth = -1;
        this.c.a(aG.a("Cancel"));
        this.c.f();
        gridBagLayout.setConstraints(this.c, gridBagConstraints);
        this.add(this.c);
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.gridwidth = 0;
        this.b.a(aG.a("OK"));
        this.b.f();
        final P p2 = new P(this.b);
        gridBagLayout.setConstraints(p2, gridBagConstraints);
        this.add(p2);
        this.pack();
        this.b.requestFocus();
    }
}
