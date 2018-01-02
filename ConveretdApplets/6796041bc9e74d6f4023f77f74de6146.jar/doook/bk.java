// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.Event;
import java.awt.Panel;
import java.awt.Label;
import java.awt.Component;
import java.awt.TextComponent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

public class bk extends q
{
    private GridBagConstraints b;
    private GridBagLayout a;
    protected aS c;
    protected aS d;
    private bF a;
    protected cF a;
    private cA c;
    private cr b;
    private TextComponent a;
    
    public void setVisible(final boolean visible) {
        if (visible && this.a instanceof bD) {
            this.a.a(this);
        }
        super.setVisible(visible);
    }
    
    public void a(final cF a) {
        ((bF)(this.a = a)).a(a);
        if (this.a != null) {
            this.a.requestFocus();
        }
    }
    
    public void a() {
        this.c.removeAll();
    }
    
    public void a(Component component, final int fill, final float n, final float n2) {
        this.b.weightx = n;
        this.b.weighty = n2;
        this.b.fill = fill;
        this.b.gridwidth = 0;
        if (component instanceof TextComponent) {
            if (this.a == null) {
                this.a = (TextComponent)component;
            }
            component = new aR(component);
        }
        this.a.setConstraints(component, this.b);
        this.c.add(component);
        this.b.weightx = 0.0;
        this.b.weighty = 0.0;
        this.b.fill = 0;
    }
    
    public void a(final String s, final Component component) {
        this.a(s, component, 0, true);
    }
    
    public void a(final String s, final Component component, final int n) {
        this.a(s, component, n, true);
    }
    
    public void a(final String s, final Component component, final int n, final boolean b) {
        Component component2 = null;
        if (s != null) {
            component2 = new Label(s);
            component2.setFont(bL.g);
        }
        this.a(component2, component, n, b);
    }
    
    public void a(final Component component, Component component2, final int n, final boolean b) {
        final Panel panel = (this.a.a() == null) ? this.c : ((Panel)this.b.b(n));
        if (component != null) {
            this.b.gridwidth = 1;
            this.a.setConstraints(component, this.b);
            panel.add(component);
        }
        this.b.gridwidth = (b ? 0 : -1);
        if (component2 instanceof TextComponent) {
            component2.setFont(bL.f);
            if (this.a == null) {
                this.a = (TextComponent)component2;
            }
            component2 = new aR(component2);
        }
        this.a.setConstraints(component2, this.b);
        panel.add(component2);
    }
    
    public void a(final String s, final String s2) {
        final Label label = new Label(s2);
        label.setFont(bL.f);
        this.a(s, label);
    }
    
    public void a(final String s, Component component, final Component component2, final int n) {
        final Panel panel = (this.a.a() != null) ? ((Panel)this.b.b(n)) : this.c;
        final Label label = new Label(s);
        label.setFont(bL.g);
        this.b.fill = 0;
        this.b.gridwidth = 1;
        this.a.setConstraints(label, this.b);
        panel.add(label);
        this.b.gridwidth = -1;
        if (component instanceof TextComponent) {
            component.setFont(bL.f);
            if (this.a == null) {
                this.a = (TextComponent)component;
            }
            component = new aR(component);
        }
        this.a.setConstraints(component, this.b);
        panel.add(component);
        this.b.gridwidth = 0;
        this.a.setConstraints(component2, this.b);
        panel.add(component2);
    }
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 401: {
                if (event.key == 10 || event.key == f.g) {
                    this.c.s();
                    return true;
                }
                if (event.key == 27 || (event.key == 46 && (event.modifiers & 0x4) != 0x0)) {
                    this.d.s();
                    return true;
                }
                break;
            }
            case 1001: {
                if (event.target == this.c) {
                    if (this.a.a(this.a)) {
                        this.a.aw = true;
                        this.a.b(this.a);
                    }
                    return true;
                }
                if (event.target == this.d) {
                    this.a.f(this.a);
                    return true;
                }
                break;
            }
        }
        return super.handleEvent(event);
    }
    
    public bk(final String s, final bF a, final cF cf, final boolean b, final u u) {
        super(a.b(), true);
        this.b = new GridBagConstraints();
        this.a = new GridBagLayout();
        this.c = new aS(80, 20);
        this.d = new aS(80, 20);
        this.c = new cA();
        this.a = null;
        this.setBackground(u.a.a);
        if (cf.h() < 0) {
            this.setTitle(am.a(ao.e("Add %1"), new String[] { s }));
        }
        else {
            this.setTitle(am.a(ao.e("Edit %1"), new String[] { s }));
        }
        this.a = a;
        if (a.a() != null) {
            this.b = new cr(2, 10, 2, 10, 1, u, false);
            for (int i = 0; i < a.a().length; ++i) {
                final Panel panel = new Panel();
                panel.setLayout(this.a);
                this.b.a(a.a()[i], panel);
            }
        }
        this.c.setBackground(u.a.g);
        this.c.setForeground(u.a.f);
        this.c.setLayout(this.a);
        this.setLayout(this.a);
        this.setResizable(false);
        this.b.insets = new Insets(4, 4, 4, 4);
        this.b.gridwidth = 0;
        this.b.fill = 1;
        this.a.setConstraints((a.a() == null) ? this.c : this.b, this.b);
        this.add((a.a() == null) ? this.c : this.b);
        this.b.gridheight = -1;
        this.b.weightx = 1.0;
        if (b) {
            final Component a2 = this.a(u.a.aE);
            this.a.setConstraints(a2, this.b);
            this.add(a2);
            this.a(u.a.t);
            a2.setBackground(u.a.k);
        }
        this.b.gridheight = 0;
        this.b.gridwidth = -1;
        this.b.anchor = 13;
        this.b.fill = 0;
        this.d.a(ao.e("Cancel"));
        this.d.t();
        this.a.setConstraints(this.d, this.b);
        this.add(this.d);
        this.b.gridwidth = 0;
        this.b.weightx = 0.0;
        this.c.a(ao.e("OK"));
        this.c.t();
        final aQ aq = new aQ(this.c);
        this.a.setConstraints(aq, this.b);
        this.add(aq);
        this.b.gridheight = 1;
        this.b.anchor = 18;
        a.a(this);
        this.pack();
        this.a(cf);
    }
}
