// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.awt;

import java.awt.Frame;
import java.awt.Image;
import com.diginet.digichat.util.p;
import java.awt.Container;
import com.diginet.digichat.util.c3;
import java.awt.Event;
import java.awt.FontMetrics;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Component;

public class ak extends ShadedDialog
{
    private a9 a;
    private aa b;
    private an c;
    private String d;
    
    public final Component b(final int n) {
        if (this.a == null) {
            this.a = new a9(5, 7, 5, 7, n);
            this.c = new an();
            this.b = new aa();
            this.a.setLayout(new BorderLayout(10, 3));
            this.a.add("West", this.b);
            this.a.add("Center", this.c);
            this.a.setFont(m.c);
            this.c.a(false);
            final FontMetrics fontMetrics = this.getFontMetrics(m.c);
            this.c.resize(50, 2 * fontMetrics.getHeight() + fontMetrics.getDescent());
        }
        return this.a;
    }
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 503:
            case 504: {
                if (this.a != null) {
                    Object o;
                    if (c3.c < 65800) {
                        final Component a = (Component)event.target;
                        super.a = a;
                        o = a;
                    }
                    else {
                        int x = event.x;
                        int y = event.y;
                        final Component component = this.getComponentAt(x, y);
                        super.a = component;
                        o = component;
                        while (o instanceof Container && o != null) {
                            x -= ((Component)o).getBounds().x;
                            y -= ((Component)o).getBounds().y;
                            final Container container = (Container)o;
                            o = container.getComponentAt(x, y);
                            if (container == o) {
                                break;
                            }
                        }
                    }
                    String d = "";
                    final Container container2 = (Container)o;
                    while (o != null) {
                        if (o instanceof p) {
                            final String a2 = ((p)o).a(container2);
                            if (a2 != null) {
                                d = a2;
                                break;
                            }
                        }
                        o = ((Component)o).getParent();
                    }
                    if (!d.equals(this.d)) {
                        this.d = d;
                        this.c.a(d);
                    }
                }
                return false;
            }
            case 7689: {
                if (this.a != null && super.a == event.target) {
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
    
    public final void a(final Image image) {
        this.b.b(image);
    }
    
    public ak(final Frame frame, final String s, final boolean b) {
        super(frame, s, b);
    }
}
