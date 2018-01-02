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

public class F extends ah
{
    private s q;
    private d q;
    private H q;
    private String q;
    
    public final Component q(final int n) {
        if (this.q == null) {
            this.q = new s(5, 7, 5, 7, n);
            this.q = new H();
            ((Container)(this.q = new d())).setLayout(new BorderLayout(10, 3));
            this.q.add("West", this.q);
            this.q.add("Center", this.q);
            this.q.setFont(m.r);
            this.q.q(false);
            final FontMetrics fontMetrics = this.getFontMetrics(m.r);
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
                    if (ef.e < 65800) {
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
                        if (o instanceof dT && (q3 = ((dT)o).q(container2)) != null) {
                            q2 = q3;
                            break;
                        }
                        o = ((Container)o).getParent();
                    }
                    if (!q2.equals(this.q)) {
                        ((H)(this.q = q2)).q(q2);
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
                        ((H)(this.q = (String)event.arg)).q((String)event.arg);
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
    
    public F(final Frame frame, final String s, final boolean b) {
        super(frame, s, false);
    }
    
    public F(final Frame frame, final boolean b) {
        super(frame, true);
    }
}
