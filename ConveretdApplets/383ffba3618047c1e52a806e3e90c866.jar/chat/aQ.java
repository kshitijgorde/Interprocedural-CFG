// 
// Decompiled by Procyon v0.5.30
// 

package chat;

import java.awt.Container;
import java.awt.Event;
import java.awt.Insets;
import java.awt.Component;

public class aQ extends aM
{
    public aM b;
    private Component a;
    private String a;
    public l a;
    public t a;
    
    public Insets insets() {
        final Insets insets = super.insets();
        return new Insets(insets.top + 3, insets.left + 3, insets.bottom + 3, insets.right + 3);
    }
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 503:
            case 504: {
                if (this.b != null) {
                    Component component;
                    if (aZ.c < 65800) {
                        final Component a = (Component)event.target;
                        this.a = a;
                        component = a;
                    }
                    else {
                        int x = event.x;
                        int y = event.y;
                        final Component component2 = this.getComponentAt(x, y);
                        this.a = component2;
                        component = component2;
                        while (component instanceof Container && component != null) {
                            x -= component.getBounds().x;
                            y -= component.getBounds().y;
                            final Container container;
                            component = (container = (Container)component).getComponentAt(x, y);
                            if (container == component) {
                                break;
                            }
                        }
                    }
                    String a2 = "";
                    for (Container parent = (Container)component; parent != null; parent = parent.getParent()) {
                        final String a3;
                        if (parent instanceof ab && (a3 = ((ab)parent).a(component)) != null) {
                            a2 = a3;
                            break;
                        }
                    }
                    if (!a2.equals(this.a)) {
                        ((t)(this.a = a2)).a(a2);
                    }
                }
                return false;
            }
            case 7689: {
                if (this.b != null && this.a == event.target) {
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
    
    public void setVisible(final boolean b) {
        this.show(b);
    }
    
    public aQ() {
        super(0);
        this.a = null;
        this.setFont(ay.c);
        this.setBackground(j.b);
    }
}
