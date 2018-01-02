// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Frame;
import java.awt.Image;
import java.awt.Container;
import java.awt.Event;
import java.awt.FontMetrics;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Component;

public class g extends F
{
    private dT q;
    private bl q;
    private u q;
    private String q;
    
    public final Component q(int n) {
        if (this.q == null) {
            this.q = new dT(5, 7, 5, 7, n);
            this.q = new u();
            ((Container)(this.q = new bl())).setLayout(new BorderLayout(10, 3));
            this.q.add("West", this.q);
            this.q.add("Center", this.q);
            this.q.setFont(cb.r);
            final u q = this.q;
            n = 0;
            q.q = false;
            final FontMetrics fontMetrics = this.getFontMetrics(cb.r);
            this.q.resize(50, 2 * fontMetrics.getHeight() + fontMetrics.getDescent());
        }
        return this.q;
    }
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 503:
            case 504: {
                if (this.q != null) {
                    Object o;
                    if (cK.e < 65800) {
                        final Component q = (Component)event.target;
                        super.q = q;
                        o = q;
                    }
                    else {
                        int x = event.x;
                        int y = event.y;
                        final Component component = this.getComponentAt(x, y);
                        super.q = component;
                        o = component;
                        while (o instanceof Container && o != null) {
                            x -= ((Container)o).getBounds().x;
                            y -= ((Container)o).getBounds().y;
                            final Container container;
                            o = (container = (Container)o).getComponentAt(x, y);
                            if (container != o) {
                                continue;
                            }
                            break;
                        }
                    }
                    String q2 = "";
                    final Container container2 = (Container)o;
                    while (o != null) {
                        final String q3;
                        if (o instanceof bf && (q3 = ((bf)o).q(container2)) != null) {
                            q2 = q3;
                            break;
                        }
                        o = ((Container)o).getParent();
                    }
                    if (!q2.equals(this.q)) {
                        ((u)(this.q = q2)).q(q2);
                    }
                }
                return false;
            }
            case 7689: {
                if (this.q != null && super.q == event.target) {
                    if (event.arg == null) {
                        event.arg = "";
                    }
                    if (this.q == null || !this.q.equals(event.arg)) {
                        ((u)(this.q = (String)event.arg)).q((String)event.arg);
                    }
                }
                return true;
            }
            default: {
                return super.handleEvent(event);
            }
        }
    }
    
    public final void q(final Image image) {
        this.q.w(image);
    }
    
    public g(final Frame frame, final String s, final boolean b) {
        super(frame, s, false);
    }
    
    public g(final Frame frame, final boolean b) {
        super(frame, true);
    }
}
