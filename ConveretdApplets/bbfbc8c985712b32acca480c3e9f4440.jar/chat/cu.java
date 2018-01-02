// 
// Decompiled by Procyon v0.5.30
// 

package chat;

import java.awt.Container;
import java.awt.Event;
import java.awt.Component;

public class cu extends aC
{
    public bF a;
    public q a;
    public A a;
    private String a;
    private Component a;
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 503:
            case 504: {
                if (this.a != null) {
                    Object o;
                    if (ce.c < 65800) {
                        final Component a = (Component)event.target;
                        this.a = a;
                        o = a;
                    }
                    else {
                        int x = event.x;
                        int y = event.y;
                        final Component component = this.getComponentAt(x, y);
                        this.a = component;
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
                if (this.a != null && this.a == event.target) {
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
}
