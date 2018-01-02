// 
// Decompiled by Procyon v0.5.30
// 

package chat;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.LayoutManager;
import java.awt.Event;
import java.awt.Label;
import java.awt.Insets;
import java.awt.Checkbox;
import java.awt.Panel;
import java.awt.Color;
import java.awt.Choice;
import java.awt.Component;
import java.awt.TextComponent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

public final class cg extends P
{
    private GridBagConstraints a;
    private GridBagLayout a;
    private cr a;
    private cr b;
    private T a;
    private U a;
    private bF a;
    private bM a;
    private TextComponent a;
    
    public final void setVisible(final boolean visible) {
        super.setVisible(visible);
    }
    
    public final void a(final U a) {
        ((T)(this.a = a)).a(a);
        if (this.a != null) {
            this.a.requestFocus();
        }
    }
    
    public final void a(Component component, final int fill, final float n, final float n2) {
        this.a.weightx = n;
        this.a.weighty = n2;
        this.a.fill = fill;
        this.a.gridwidth = 0;
        if (component instanceof TextComponent) {
            if (this.a == null) {
                this.a = (TextComponent)component;
            }
            component = new i(component);
        }
        else if (component instanceof Choice) {
            component.setForeground(Color.black);
            component.setBackground(Color.white);
        }
        this.a.setConstraints(component, this.a);
        this.a.add(component);
        this.a.weightx = 0.0;
        this.a.weighty = 0.0;
        this.a.fill = 0;
    }
    
    public final void a(final Component component) {
        this.a(component, 2, 0.0f, 0.0f);
    }
    
    public final void a(final String s, Component component, final int n) {
        final Panel panel = (this.a.a() == null) ? this.a : ((Panel)this.a.getComponent(n));
        final bi bi;
        (bi = new bi(s, (byte)0)).setVisible(true);
        bi.setFont(bk.d);
        this.a.fill = 0;
        this.a.gridwidth = 1;
        this.a.setConstraints(bi, this.a);
        panel.add(bi);
        this.a.gridwidth = 0;
        if (ce.d) {
            component.setFont(bk.c);
        }
        if (component instanceof TextComponent) {
            component.setFont(bk.c);
            if (this.a == null) {
                this.a = (TextComponent)component;
            }
            component = new i(component);
        }
        else if (component instanceof Choice) {
            component.setForeground(Color.black);
            component.setBackground(Color.white);
        }
        this.a.setConstraints(component, this.a);
        panel.add(component);
    }
    
    public final void a(final String s, Component component, final Component component2, final int n) {
        final Panel panel = (this.a.a() == null) ? this.a : ((Panel)this.a.getComponent(n));
        final bi bi;
        (bi = new bi(s, (byte)0)).setFont(bk.d);
        this.a.fill = 0;
        this.a.gridwidth = 1;
        this.a.setConstraints(bi, this.a);
        panel.add(bi);
        this.a.gridwidth = -1;
        if (component instanceof TextComponent) {
            component.setFont(bk.c);
            if (this.a == null) {
                this.a = (TextComponent)component;
            }
            component = new i(component);
        }
        else if (component instanceof Choice) {
            component.setForeground(Color.black);
            component.setBackground(Color.white);
        }
        this.a.setConstraints(component, this.a);
        panel.add(component);
        this.a.gridwidth = 0;
        this.a.setConstraints(component2, this.a);
        panel.add(component2);
    }
    
    public final void a(final Component component, Component component2, final int n) {
        final Panel panel = (this.a.a() == null) ? this.a : ((Panel)this.a.getComponent(0));
        component.setFont(bk.d);
        this.a.fill = 0;
        this.a.gridwidth = 1;
        this.a.setConstraints(component, this.a);
        panel.add(component);
        this.a.gridwidth = 0;
        if (component2 instanceof TextComponent) {
            component2.setFont(bk.c);
            if (this.a == null) {
                this.a = (TextComponent)component2;
            }
            component2 = new i(component2);
        }
        else if (component2 instanceof Choice) {
            component2.setForeground(Color.black);
            component2.setBackground(Color.white);
        }
        else if (component2 instanceof Checkbox) {
            component2.setFont(bk.d);
        }
        this.a.setConstraints(component2, this.a);
        panel.add(component2);
    }
    
    public final void a(final String s, final Component[] array) {
        final Panel panel = (this.a.a() == null) ? this.a : ((Panel)this.a.getComponent(0));
        this.a.anchor = 18;
        this.a.fill = 0;
        if (s != null) {
            final bi bi;
            (bi = new bi(s, (byte)0)).setFont(bk.d);
            this.a.gridwidth = 1;
            this.a.setConstraints(bi, this.a);
            panel.add(bi);
        }
        for (int i = 0; i < array.length; ++i) {
            boolean b = false;
            if (array[i] instanceof TextComponent) {
                array[i].setFont(bk.c);
                array[i] = new i(array[i]);
            }
            else if (array[i] instanceof Choice) {
                array[i].setForeground(Color.black);
                array[i].setBackground(Color.white);
            }
            else if (array[i] instanceof Checkbox) {
                b = true;
                this.a.insets = new Insets(0, 4, 0, 0);
                this.a.setConstraints(array[i], this.a);
                this.a.add(array[i], this.a);
                this.a.insets = new Insets(0, 0, 0, 0);
                this.a.weightx = 0.0;
                this.a.weighty = 0.0;
                this.a.fill = 0;
            }
            else if (array[i] instanceof bi && i > 0 && array[i - 1] instanceof Checkbox) {
                b = true;
                ((bi)array[i]).repaint();
                this.a.add(array[i], this.a);
                this.a.gridwidth = 0;
                this.a.add(new bi(), this.a);
                this.a.weightx = 0.0;
                this.a.weighty = 0.0;
                this.a.fill = 0;
            }
            array[i].setSize(array[i].preferredSize());
            if (ce.d && array[i] instanceof Label) {
                this.a.fill = 2;
                array[i].setFont(bk.d);
            }
            if (array[i] instanceof bi) {
                array[i].setFont(bk.d);
            }
            this.a.gridwidth = ((i != array.length - 1) ? 1 : 0);
            if (!b) {
                this.a.setConstraints(array[i], this.a);
                panel.add(array[i]);
            }
        }
    }
    
    public final boolean handleEvent(final Event event) {
        if (this.a.a(this, event)) {
            return true;
        }
        switch (event.id) {
            case 401: {
                if (event.key == 10 || event.key == ce.a) {
                    this.a.c();
                    return true;
                }
                if (event.key == 27 || (event.key == 46 && (event.modifiers & 0x4) != 0x0)) {
                    this.b.c();
                    return true;
                }
                break;
            }
            case 1001: {
                if (event.target == this.a) {
                    if (this.a.a(this.a)) {
                        this.a.a = true;
                        this.a.j = 1;
                        this.a.b(this.a);
                    }
                    return true;
                }
                if (event.target == this.b) {
                    this.a.a(this.a, this);
                    return true;
                }
                break;
            }
            case 201: {
                this.a.a(this.a, this);
                return true;
            }
        }
        return super.handleEvent(event);
    }
    
    public final void a(final Checkbox checkbox, final bi bi) {
        this.a.gridwidth = 1;
        this.a.insets = new Insets(0, 4, 0, 0);
        this.a.setConstraints(checkbox, this.a);
        this.a.add(checkbox, this.a);
        this.a.insets = new Insets(0, 0, 0, 0);
        this.a.weightx = 0.0;
        this.a.weighty = 0.0;
        this.a.fill = 0;
        bi.repaint();
        this.a.add(bi, this.a);
        this.a.gridwidth = 0;
        this.a.add(new bi(), this.a);
        this.a.weightx = 0.0;
        this.a.weighty = 0.0;
        this.a.fill = 0;
    }
    
    public cg(final String s, final T a, final U u, final boolean b, final cx cx, final byte b2) {
        super(a.a());
        this.a = new GridBagConstraints();
        this.a = new GridBagLayout();
        this.a = new cr(80, 20);
        this.b = new cr(80, 20);
        this.a = new bF();
        this.a = null;
        this.setBackground(cx.a.a);
        if (u.i < 0) {
            this.setTitle(bm.a(aS.a(169), new String[] { s }));
        }
        else {
            this.setTitle(bm.a(aS.a(170), new String[] { s }));
        }
        this.a = a;
        if (a.a() != null) {
            this.a = new bM(2, 10, 2, 10, cx, false);
            for (int i = 0; i < a.a().length; ++i) {
                final Panel panel;
                (panel = new Panel()).setLayout(this.a);
                this.a.a(a.a()[i], panel);
            }
        }
        this.a.setBackground(cx.a.h);
        this.a.setForeground(cx.a.g);
        this.a.setLayout(this.a);
        this.setLayout(this.a);
        this.setResizable(false);
        this.a.insets = new Insets(4, 4, 4, 4);
        this.a.gridwidth = 0;
        this.a.fill = 1;
        this.a.weighty = 1.0;
        this.a.weightx = 1.0;
        this.a.setConstraints((a.a() == null) ? this.a : this.a, this.a);
        this.add((a.a() == null) ? this.a : this.a);
        this.a.weighty = 0.0;
        this.a.gridheight = -1;
        this.a.weightx = 1.0;
        if (b) {
            final Component a2 = this.a(cx.a.c);
            this.a.setConstraints(a2, this.a);
            this.add(a2);
            a2.setBackground(cx.a.d);
        }
        this.a.gridheight = 0;
        this.a.gridwidth = -1;
        this.a.anchor = 13;
        this.a.fill = 0;
        this.b.a(aS.a(3));
        this.b.d();
        this.a.setConstraints(this.b, this.a);
        this.add(this.b);
        this.a.gridwidth = 0;
        this.a.weightx = 0.0;
        this.a.a(aS.a(2));
        this.a.d();
        final g g = new g(this.a);
        this.a.setConstraints(g, this.a);
        this.add(g);
        this.a.gridheight = 1;
        this.a.anchor = 18;
        a.a(this);
        this.pack();
        this.a(u);
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        final Dimension size = this.size();
        if (screenSize.height < size.height + 25) {
            this.resize(size.width, screenSize.height - 25);
        }
    }
    
    public cg(final String title, final T a, final U a2, final boolean b, final cx cx) {
        super(a.a());
        this.a = new GridBagConstraints();
        this.a = new GridBagLayout();
        this.a = new cr(80, 20);
        this.b = new cr(80, 20);
        this.a = new bF();
        this.a = null;
        this.setBackground(cx.a.a);
        this.setTitle(title);
        this.a = a;
        if (a.a() != null) {
            this.a = new bM(2, 10, 2, 10, cx, false);
            for (int i = 0; i < a.a().length; ++i) {
                final Panel panel;
                (panel = new Panel()).setLayout(this.a);
                this.a.a(a.a()[i], panel);
            }
        }
        this.a.setBackground(cx.a.h);
        this.a.setForeground(cx.a.g);
        this.a.setLayout(this.a);
        this.setLayout(this.a);
        this.setResizable(false);
        this.a.insets = new Insets(4, 4, 4, 4);
        this.a.gridwidth = 0;
        this.a.fill = 1;
        this.a.setConstraints((a.a() == null) ? this.a : this.a, this.a);
        this.add((a.a() == null) ? this.a : this.a);
        this.a.gridheight = -1;
        this.a.weightx = 1.0;
        if (b) {
            final Component a3 = this.a(cx.a.c);
            this.a.setConstraints(a3, this.a);
            this.add(a3);
            a3.setBackground(cx.a.d);
        }
        this.a.gridheight = 0;
        this.a.gridwidth = -1;
        this.a.anchor = 13;
        this.a.fill = 0;
        this.b.a(aS.a(3));
        this.b.d();
        this.a.setConstraints(this.b, this.a);
        this.add(this.b);
        this.a.gridwidth = 0;
        this.a.weightx = 0.0;
        this.a.a(aS.a(2));
        this.a.d();
        final g g = new g(this.a);
        this.a.setConstraints(g, this.a);
        this.add(g);
        this.a.gridheight = 1;
        this.a.anchor = 18;
        a.a(this);
        this.pack();
        this.a = a2;
        if (this.a instanceof R) {
            ((R)this.a).d(a2);
        }
        if (this.a != null) {
            this.a.requestFocus();
        }
    }
}
