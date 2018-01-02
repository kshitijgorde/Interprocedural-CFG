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

public class ab extends N
{
    private n c;
    private bp a;
    private D a;
    private String m;
    
    public Component a(final int n) {
        if (this.c == null) {
            this.c = new n(5, 7, 5, 7, n);
            this.a = new D();
            this.a = new bp();
            this.c.setLayout(new BorderLayout(10, 3));
            this.c.add("West", this.a);
            this.c.add("Center", this.a);
            this.c.setFont(aK.d);
            this.a.a(false);
            final FontMetrics fontMetrics = this.getFontMetrics(aK.d);
            this.a.resize(50, 2 * fontMetrics.getHeight() + fontMetrics.getDescent());
        }
        return this.c;
    }
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 503:
            case 504: {
                if (this.c != null) {
                    Object o;
                    if (F.a < 65800) {
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
                    String m = "";
                    final Container container2 = (Container)o;
                    while (o != null) {
                        if (o instanceof aj) {
                            final String a = ((aj)o).a(container2);
                            if (a != null) {
                                m = a;
                                break;
                            }
                        }
                        o = ((Component)o).getParent();
                    }
                    if (!m.equals(this.m)) {
                        this.m = m;
                        this.a.a(m);
                    }
                }
                return false;
            }
            case 7689: {
                if (this.c != null && super.c == event.target) {
                    if (event.arg == null) {
                        event.arg = "";
                    }
                    if (this.m == null || !this.m.equals(event.arg)) {
                        this.m = (String)event.arg;
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
    
    public ab(final Frame frame, final String s, final boolean b) {
        super(frame, s, b);
    }
}
