// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.net.URL;
import java.awt.Image;
import java.awt.FontMetrics;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Event;
import java.awt.Insets;
import java.awt.Component;

public class K extends s
{
    private s q;
    private Component q;
    private String q;
    private d q;
    private H q;
    
    public Insets insets() {
        final Insets insets = super.insets();
        return new Insets(insets.top + 3, insets.left + 3, insets.bottom + 3, insets.right + 3);
    }
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 503:
            case 504: {
                if (this.q != null) {
                    Component component;
                    if (ef.e < 65800) {
                        final Component q = (Component)event.target;
                        this.q = q;
                        component = q;
                    }
                    else {
                        int x = event.x;
                        int y = event.y;
                        final Component component2 = this.getComponentAt(x, y);
                        this.q = component2;
                        component = component2;
                        while (component instanceof Container && component != null) {
                            x -= component.getBounds().x;
                            y -= component.getBounds().y;
                            final Container container;
                            component = (container = (Container)component).getComponentAt(x, y);
                            if (container != component) {
                                continue;
                            }
                            break;
                        }
                    }
                    String q2 = "";
                    for (Container parent = (Container)component; parent != null; parent = parent.getParent()) {
                        final String q3;
                        if (parent instanceof dT && (q3 = ((dT)parent).q(component)) != null) {
                            q2 = q3;
                            break;
                        }
                    }
                    if (!q2.equals(this.q)) {
                        ((H)(this.q = q2)).q(q2);
                    }
                }
                return false;
            }
            case 7689: {
                if (this.q != null && this.q == event.target) {
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
    
    public void setVisible(final boolean b) {
        this.show(b);
    }
    
    public final Component q(final int n) {
        if (this.q == null) {
            this.q = new s(5, 7, 5, 7, n);
            this.q = new H();
            this.q.q = true;
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
    
    public final void q(final Image image) {
        this.q.w(image);
    }
    
    public final void q(final URL url) {
        this.q.q(url);
    }
    
    public K() {
        this.q = null;
        this.setFont(m.r);
        this.setBackground(k.w);
    }
    
    public K(final int n) {
        super(n);
        this.q = null;
        this.setFont(m.r);
        this.setBackground(k.w);
    }
}
