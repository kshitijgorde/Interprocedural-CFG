// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.net.URL;
import java.awt.Image;
import java.awt.FontMetrics;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Event;
import java.awt.Insets;
import java.awt.Component;

public class cs extends cA
{
    private cA a;
    private Component h;
    private String d;
    private B c;
    private c c;
    
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
                    if (f.i < 65800) {
                        final Component h = (Component)event.target;
                        this.h = h;
                        component = h;
                    }
                    else {
                        int x = event.x;
                        int y = event.y;
                        final Component component2 = this.getComponentAt(x, y);
                        this.h = component2;
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
                    String d = "";
                    for (Container parent = (Container)component; parent != null; parent = parent.getParent()) {
                        if (parent instanceof aB) {
                            final String a = ((aB)parent).a(component);
                            if (a != null) {
                                d = a;
                                break;
                            }
                        }
                    }
                    if (!d.equals(this.d)) {
                        this.d = d;
                        this.c.a(d);
                    }
                }
                return false;
            }
            case 7689: {
                if (this.a != null && this.h == event.target) {
                    if (event.arg == null) {
                        event.arg = "";
                    }
                    if (this.d == null || !this.d.equals(event.arg)) {
                        this.d = (String)event.arg;
                        this.c.a((String)event.arg);
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
    
    public Component b(final int n) {
        if (this.a == null) {
            this.a = new cA(5, 7, 5, 7, n);
            this.c = new c();
            this.c.c = true;
            this.c = new B();
            this.a.setLayout(new BorderLayout(10, 3));
            this.a.add("West", this.c);
            this.a.add("Center", this.c);
            this.a.setFont(bL.f);
            this.c.a(false);
            final FontMetrics fontMetrics = this.getFontMetrics(bL.f);
            this.c.resize(50, 2 * fontMetrics.getHeight() + fontMetrics.getDescent());
        }
        return this.a;
    }
    
    public void a(final Image image) {
        this.c.b(image);
    }
    
    public void a(final URL url) {
        this.c.a(url);
    }
    
    public cs() {
        this.h = null;
        this.setFont(bL.f);
        this.setBackground(bR.h);
    }
    
    public cs(final int n) {
        super(n);
        this.h = null;
        this.setFont(bL.f);
        this.setBackground(bR.h);
    }
}
