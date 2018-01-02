// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.awt;

import java.net.URL;
import java.awt.Image;
import java.awt.FontMetrics;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Color;
import com.diginet.digichat.util.p;
import com.diginet.digichat.util.c3;
import java.awt.Event;
import java.awt.Insets;
import java.awt.Panel;
import java.awt.Component;
import java.awt.Container;

public class bi extends a9
{
    private Container a;
    private Component b;
    private String c;
    private aa d;
    private an e;
    private aa f;
    private Panel g;
    
    public final Insets insets() {
        final Insets insets = super.insets();
        return new Insets(insets.top + 3, insets.left + 3, insets.bottom + 3, insets.right + 3);
    }
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 503:
            case 504: {
                if (this.a != null) {
                    Component component;
                    if (c3.c < 65800) {
                        final Component b = (Component)event.target;
                        this.b = b;
                        component = b;
                    }
                    else {
                        int x = event.x;
                        int y = event.y;
                        final Component component2 = this.getComponentAt(x, y);
                        this.b = component2;
                        component = component2;
                        while (component instanceof Container && component != null) {
                            x -= component.getBounds().x;
                            y -= component.getBounds().y;
                            final Container container = (Container)component;
                            component = container.getComponentAt(x, y);
                            if (container == component) {
                                break;
                            }
                        }
                    }
                    String c = "";
                    for (Container parent = (Container)component; parent != null; parent = parent.getParent()) {
                        if (parent instanceof p) {
                            final String a = ((p)parent).a(component);
                            if (a != null) {
                                c = a;
                                break;
                            }
                        }
                    }
                    if (!c.equals(this.c)) {
                        this.c = c;
                        this.e.a(c);
                    }
                }
                return false;
            }
            case 7689: {
                if (this.a != null && this.b == event.target) {
                    if (event.arg == null) {
                        event.arg = "";
                    }
                    if (this.c == null || !this.c.equals(event.arg)) {
                        this.c = (String)event.arg;
                        this.e.a((String)event.arg);
                    }
                }
                return true;
            }
            case 1001: {
                if (this.a == null) {
                    break;
                }
                if (event.target == this.f) {
                    this.d(false);
                    return true;
                }
                if (this.g != null && event.target == this.d) {
                    this.d(true);
                    return true;
                }
                break;
            }
        }
        return super.handleEvent(event);
    }
    
    public void d(final boolean b) {
        if (b && !this.g.isShowing()) {
            this.g.show();
            this.validate();
        }
        else if (!b && this.g.isShowing()) {
            this.g.hide();
            this.validate();
        }
    }
    
    public void setVisible(final boolean b) {
        this.show(b);
    }
    
    public final Component a(final int n) {
        return this.b(n, 2);
    }
    
    public final Component b(final int n, final int n2) {
        return this.a(n, n2, true, null, null);
    }
    
    public final Component a(final int n, final int n2, final boolean b, final Color foreground, final Color background) {
        if (this.a == null) {
            this.e = new an();
            this.d = new aa();
            this.f = new aa();
            final GridBagLayout gridBagLayout = new GridBagLayout();
            final GridBagConstraints gridBagConstraints = new GridBagConstraints();
            if (b) {
                this.a = new a9(5, 7, 5, 7, n);
            }
            else {
                this.a = new Panel();
            }
            this.a.setLayout(gridBagLayout);
            gridBagConstraints.anchor = 17;
            if (!b) {
                gridBagConstraints.gridwidth = 0;
                gridBagConstraints.weightx = 1.0;
            }
            gridBagLayout.setConstraints(this.d, gridBagConstraints);
            this.a.add(this.d);
            gridBagConstraints.fill = 1;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.gridwidth = -1;
            gridBagLayout.setConstraints(this.e, gridBagConstraints);
            if (b) {
                this.a.add(this.e);
            }
            else {
                this.g = new a9(5, 7, 5, 7, n);
                if (foreground != null) {
                    this.g.setForeground(foreground);
                }
                if (background != null) {
                    this.g.setBackground(background);
                }
                this.g.setLayout(gridBagLayout);
                this.g.add(this.e);
            }
            gridBagConstraints.weightx = 0.0;
            gridBagConstraints.fill = 0;
            gridBagConstraints.anchor = 12;
            gridBagConstraints.gridwidth = 0;
            gridBagLayout.setConstraints(this.f, gridBagConstraints);
            if (b) {
                this.a.add(this.f);
            }
            else {
                this.g.add(this.f);
                gridBagConstraints.fill = 1;
                gridBagConstraints.weightx = 1.0;
                gridBagConstraints.gridwidth = 0;
                gridBagConstraints.insets = new Insets(3, 4, 3, 3);
                gridBagLayout.setConstraints(this.g, gridBagConstraints);
                this.a.add(this.g);
            }
            this.a.setFont(m.c);
            this.e.a(false);
            final FontMetrics fontMetrics = this.getFontMetrics(m.c);
            this.e.resize(50, n2 * fontMetrics.getHeight() + fontMetrics.getDescent());
        }
        return this.a;
    }
    
    public final void a(final Image image) {
        this.d.b(image);
    }
    
    public final void a(final URL url) {
        this.d.a(url);
    }
    
    public final void b(final Image image) {
        this.f.b(image);
    }
    
    public bi() {
        this.b = null;
        this.setFont(m.c);
        this.setBackground(ColorChoice.lightGray);
    }
    
    public bi(final int n) {
        super(n);
        this.b = null;
        this.setFont(m.c);
        this.setBackground(ColorChoice.lightGray);
    }
}