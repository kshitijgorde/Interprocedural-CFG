// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.net.URL;
import java.awt.Image;
import java.awt.FontMetrics;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Event;
import java.awt.Insets;
import java.awt.Component;

public class z extends aC
{
    private aC a;
    private Component a;
    private String b;
    private M a;
    private R a;
    
    public Insets insets() {
        final Insets insets = super.insets();
        return new Insets(insets.top + 3, insets.left + 3, insets.bottom + 3, insets.right + 3);
    }
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 503:
            case 504: {
                if (this.a != null) {
                    Component component;
                    if (bs.g < 65800) {
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
                            final Container container = (Container)component;
                            component = container.getComponentAt(x, y);
                            if (container == component) {
                                break;
                            }
                        }
                    }
                    String b = "";
                    for (Container parent = (Container)component; parent != null; parent = parent.getParent()) {
                        if (parent instanceof aO) {
                            final String a2 = ((aO)parent).a(component);
                            if (a2 != null) {
                                b = a2;
                                break;
                            }
                        }
                    }
                    if (!b.equals(this.b)) {
                        this.b = b;
                        this.a.a(b);
                    }
                }
                return false;
            }
            case 7689: {
                if (this.a != null && this.a == event.target) {
                    if (event.arg == null) {
                        event.arg = "";
                    }
                    if (this.b == null || !this.b.equals(event.arg)) {
                        this.b = (String)event.arg;
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
    
    public void setVisible(final boolean b) {
        this.show(b);
    }
    
    public Component a(final int n) {
        if (this.a == null) {
            this.a = new aC(5, 7, 5, 7, n);
            this.a = new R();
            this.a.l = true;
            ((Container)(this.a = new M())).setLayout(new BorderLayout(10, 3));
            this.a.add("West", this.a);
            this.a.add("Center", this.a);
            this.a.setFont(ay.e);
            this.a.a(false);
            final FontMetrics fontMetrics = this.getFontMetrics(ay.e);
            this.a.resize(50, 2 * fontMetrics.getHeight() + fontMetrics.getDescent());
        }
        return this.a;
    }
    
    public void a(final Image image) {
        this.a.b(image);
    }
    
    public void a(final URL url) {
        this.a.a(url);
    }
    
    public z() {
        this.a = null;
        this.setFont(ay.e);
        this.setBackground(ah.i);
    }
    
    public z(final int n) {
        super(n);
        this.a = null;
        this.setFont(ay.e);
        this.setBackground(ah.i);
    }
}
