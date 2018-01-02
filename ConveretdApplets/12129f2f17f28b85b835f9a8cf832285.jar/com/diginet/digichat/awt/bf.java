// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.awt;

import java.net.URL;
import java.awt.Image;
import java.awt.FontMetrics;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import com.diginet.digichat.util.s;
import java.awt.Container;
import com.diginet.digichat.util.ch;
import java.awt.Event;
import java.awt.Insets;
import java.awt.Component;

public class bf extends a7
{
    private a7 a;
    private Component b;
    private String c;
    private as d;
    private ai e;
    
    public Insets insets() {
        final Insets insets = super.insets();
        return new Insets(insets.top + 3, insets.left + 3, insets.bottom + 3, insets.right + 3);
    }
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 503:
            case 504: {
                if (this.a != null) {
                    Component component;
                    if (ch.c < 65800) {
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
                        if (parent instanceof s) {
                            final String a = ((s)parent).a(component);
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
            default: {
                return super.handleEvent(event);
            }
        }
    }
    
    public void setVisible(final boolean b) {
        this.show(b);
    }
    
    public Component a(final int n) {
        if (this.a == null) {
            this.a = new a7(5, 7, 5, 7, n);
            this.e = new ai();
            this.d = new as();
            this.a.setLayout(new BorderLayout(10, 3));
            this.a.add("West", this.d);
            this.a.add("Center", this.e);
            this.a.setFont(p.c);
            this.e.a(false);
            final FontMetrics fontMetrics = this.getFontMetrics(p.c);
            this.e.resize(50, 2 * fontMetrics.getHeight() + fontMetrics.getDescent());
        }
        return this.a;
    }
    
    public void a(final Image image) {
        this.d.b(image);
    }
    
    public void a(final URL url) {
        this.d.a(url);
    }
    
    public bf() {
        this.b = null;
        this.setFont(p.c);
        this.setBackground(b6.b);
    }
    
    public bf(final int n) {
        super(n);
        this.b = null;
        this.setFont(p.c);
        this.setBackground(b6.b);
    }
}
