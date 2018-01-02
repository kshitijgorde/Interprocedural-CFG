// 
// Decompiled by Procyon v0.5.30
// 

package chat;

import java.awt.Frame;
import java.awt.Image;
import java.awt.Container;
import java.awt.Event;
import java.awt.FontMetrics;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Component;

public class P extends bC
{
    private bF a;
    private q a;
    private A a;
    private String a;
    
    public final Component a(final int n) {
        if (this.a == null) {
            this.a = new bF(5, 7, 5, 7, n);
            this.a = new A();
            ((Container)(this.a = new q())).setLayout(new BorderLayout(10, 3));
            this.a.add("West", this.a);
            this.a.add("Center", this.a);
            this.a.setFont(bk.c);
            this.a.a = false;
            final FontMetrics fontMetrics = this.getFontMetrics(bk.c);
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
                    if (ce.c < 65800) {
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
                            x -= ((Container)o).getBounds().x;
                            y -= ((Container)o).getBounds().y;
                            final Container container;
                            o = (container = (Container)o).getComponentAt(x, y);
                            if (container == o) {
                                break;
                            }
                        }
                    }
                    String a2 = "";
                    final Container container2 = (Container)o;
                    while (o != null) {
                        final String a3;
                        if (o instanceof aB && (a3 = ((aB)o).a(container2)) != null) {
                            a2 = a3;
                            break;
                        }
                        o = ((Container)o).getParent();
                    }
                    if (!a2.equals(this.a)) {
                        ((A)(this.a = a2)).a(a2);
                    }
                }
                return false;
            }
            case 7689: {
                if (this.a != null && super.a == event.target) {
                    if (event.arg == null) {
                        event.arg = "";
                    }
                    if (this.a == null || !this.a.equals(event.arg)) {
                        ((A)(this.a = (String)event.arg)).a((String)event.arg);
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
        this.a.b(image);
    }
    
    public P(final Frame frame, final String s) {
        super(frame, s, false);
    }
    
    public P(final Frame frame) {
        super(frame, true);
    }
}
