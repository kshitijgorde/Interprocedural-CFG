// 
// Decompiled by Procyon v0.5.30
// 

package flaxchat.g;

import java.awt.Rectangle;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Container;

public class d extends b
{
    private int c;
    private int d;
    private int e;
    private int f;
    
    public d() {
        this(5, 5);
    }
    
    public d(final int n, final int n2) {
        this(n, n2, -1);
    }
    
    public d(final int c, final int d, final int e) {
        this.f = -1;
        this.c = c;
        this.d = d;
        this.e = e;
    }
    
    public d(final int c, final int d, final int e, final int f) {
        this.f = -1;
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = f;
    }
    
    public void layoutContainer(final Container container) {
        this.a(container, true);
    }
    
    public Dimension minimumLayoutSize(final Container container) {
        return this.a(container, false);
    }
    
    public Dimension preferredLayoutSize(final Container container) {
        return this.a(container, false);
    }
    
    public Dimension a(final Container container, final boolean b) {
        final int a = flaxchat.g.a.a;
        final int componentCount = container.getComponentCount();
        if (componentCount == 0) {
            return new Dimension(60, 0);
        }
        final int c = this.c;
        int d = this.d;
        int n = 0;
        int n2 = 0;
        while (true) {
            Label_0166: {
                if (a == 0) {
                    break Label_0166;
                }
                final Component component = container.getComponent(n2);
                if (component.isVisible()) {
                    final Dimension preferredSize = component.getPreferredSize();
                    int n3 = preferredSize.height;
                    n = Math.max(n, preferredSize.width);
                    if (this.f != -1) {
                        n = Math.min(n, this.f);
                    }
                    if (this.e > 0 && n3 >= this.e) {
                        n3 = this.e;
                    }
                    if (b) {
                        component.setBounds(c, d, preferredSize.width, n3);
                    }
                    d += n3 + this.d;
                }
                ++n2;
            }
            if (n2 < componentCount) {
                continue;
            }
            break;
        }
        final Dimension dimension = new Dimension(c + n + this.d, d);
        final int n4 = container.getSize().height - componentCount - this.d;
        if (dimension.height >= n4) {
            final int n5 = n4 / componentCount;
            if (b) {
                int d2 = this.d;
                int n6 = 0;
                while (true) {
                    Label_0286: {
                        if (a == 0) {
                            break Label_0286;
                        }
                        final Component component2 = container.getComponent(n6);
                        component2.setBounds(component2.getBounds().x, d2, n, n5);
                        d2 += n5 + 1;
                        ++n6;
                    }
                    if (n6 < componentCount) {
                        continue;
                    }
                    break;
                }
            }
            final Dimension size = container.getSize();
            size.width = dimension.width;
            return size;
        }
        if (b) {
            int n7 = 0;
            while (true) {
                Label_0363: {
                    if (a == 0) {
                        break Label_0363;
                    }
                    final Component component3 = container.getComponent(n7);
                    final Rectangle bounds = component3.getBounds();
                    component3.setBounds(bounds.x, bounds.y, n, bounds.height);
                    ++n7;
                }
                if (n7 < componentCount) {
                    continue;
                }
                break;
            }
        }
        return dimension;
    }
}
