// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Frame;
import java.awt.Container;
import java.awt.Component;
import java.awt.Event;

public class d extends w
{
    cv q;
    aH q;
    q q;
    private String q;
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 503:
            case 504: {
                if (this.q != null) {
                    Object o;
                    if (bE.e < 65800) {
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
                        if (o instanceof aB && (q3 = ((aB)o).q(container2)) != null) {
                            q2 = q3;
                            break;
                        }
                        o = ((Container)o).getParent();
                    }
                    if (!q2.equals(this.q)) {
                        ((q)(this.q = q2)).q(q2);
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
                        ((q)(this.q = (String)event.arg)).q((String)event.arg);
                    }
                }
                return true;
            }
            default: {
                return super.handleEvent(event);
            }
        }
    }
    
    public d(final Frame frame, final String s, final boolean b) {
        super(frame, s, false);
    }
}
