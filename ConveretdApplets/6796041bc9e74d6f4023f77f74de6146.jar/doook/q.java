// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.Frame;
import java.awt.Image;
import java.awt.Container;
import java.awt.Event;
import java.awt.FontMetrics;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Component;

public class q extends p
{
    private cA a;
    private B a;
    private c a;
    private String e;
    
    public Component a(final int n) {
        if (this.a == null) {
            this.a = new cA(5, 7, 5, 7, n);
            this.a = new c();
            ((Container)(this.a = new B())).setLayout(new BorderLayout(10, 3));
            this.a.add("West", this.a);
            this.a.add("Center", this.a);
            this.a.setFont(bL.f);
            this.a.a(false);
            final FontMetrics fontMetrics = this.getFontMetrics(bL.f);
            this.a.resize(50, 2 * fontMetrics.getHeight() + fontMetrics.getDescent());
        }
        return this.a;
    }
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 503:
            case 504: {
                if (this.a != null) {
                    Object o;
                    if (f.i < 65800) {
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
                    String e = "";
                    final Container container2 = (Container)o;
                    while (o != null) {
                        if (o instanceof aB) {
                            final String a2 = ((aB)o).a(container2);
                            if (a2 != null) {
                                e = a2;
                                break;
                            }
                        }
                        o = ((Component)o).getParent();
                    }
                    if (!e.equals(this.e)) {
                        this.e = e;
                        this.a.a(e);
                    }
                }
                return false;
            }
            case 7689: {
                if (this.a != null && super.a == event.target) {
                    if (event.arg == null) {
                        event.arg = "";
                    }
                    if (this.e == null || !this.e.equals(event.arg)) {
                        this.e = (String)event.arg;
                        this.a.a((String)event.arg);
                    }
                }
                return true;
            }
            default: {
                return super.handleEvent(event);
            }
        }
    }
    
    public void a(final Image image) {
        this.a.b(image);
    }
    
    public q(final Frame frame, final String s, final boolean b) {
        super(frame, s, b);
    }
    
    public q(final Frame frame, final boolean b) {
        super(frame, b);
    }
}
