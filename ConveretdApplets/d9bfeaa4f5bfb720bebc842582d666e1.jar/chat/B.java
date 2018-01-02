// 
// Decompiled by Procyon v0.5.30
// 

package chat;

import java.awt.Frame;
import java.awt.Container;
import java.awt.Component;
import java.awt.Event;

public class B extends C
{
    aM a;
    l a;
    t a;
    private String a;
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 503:
            case 504: {
                if (this.a != null) {
                    Object o;
                    if (aZ.c < 65800) {
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
                        if (o instanceof ab && (a3 = ((ab)o).a(container2)) != null) {
                            a2 = a3;
                            break;
                        }
                        o = ((Container)o).getParent();
                    }
                    if (!a2.equals(this.a)) {
                        ((t)(this.a = a2)).a(a2);
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
                        ((t)(this.a = (String)event.arg)).a((String)event.arg);
                    }
                }
                return true;
            }
            default: {
                return super.handleEvent(event);
            }
        }
    }
    
    public B(final Frame frame, final String s) {
        super(frame, s, false);
    }
}
