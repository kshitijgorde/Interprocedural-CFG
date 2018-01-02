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

public class Y extends aA
{
    private aC a;
    private M b;
    private R b;
    private String g;
    
    public Component b(final int n) {
        if (this.a == null) {
            this.a = new aC(5, 7, 5, 7, n);
            this.b = new R();
            this.b = new M();
            this.a.setLayout(new BorderLayout(10, 3));
            this.a.add("West", this.b);
            this.a.add("Center", this.b);
            this.a.setFont(ay.e);
            this.b.a(false);
            final FontMetrics fontMetrics = this.getFontMetrics(ay.e);
            this.b.resize(50, 2 * fontMetrics.getHeight() + fontMetrics.getDescent());
        }
        return this.a;
    }
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 503:
            case 504: {
                if (this.a != null) {
                    Object o;
                    if (bs.g < 65800) {
                        final Component c = (Component)event.target;
                        super.c = c;
                        o = c;
                    }
                    else {
                        int x = event.x;
                        int y = event.y;
                        final Component component = this.getComponentAt(x, y);
                        super.c = component;
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
                    String g = "";
                    final Container container2 = (Container)o;
                    while (o != null) {
                        if (o instanceof aO) {
                            final String a = ((aO)o).a(container2);
                            if (a != null) {
                                g = a;
                                break;
                            }
                        }
                        o = ((Component)o).getParent();
                    }
                    if (!g.equals(this.g)) {
                        this.g = g;
                        this.b.a(g);
                    }
                }
                return false;
            }
            case 7689: {
                if (this.a != null && super.c == event.target) {
                    if (event.arg == null) {
                        event.arg = "";
                    }
                    if (this.g == null || !this.g.equals(event.arg)) {
                        this.g = (String)event.arg;
                        this.b.a((String)event.arg);
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
        this.b.b(image);
    }
    
    public Y(final Frame frame, final String s, final boolean b) {
        super(frame, s, b);
    }
}
