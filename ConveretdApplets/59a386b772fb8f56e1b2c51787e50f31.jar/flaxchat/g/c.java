// 
// Decompiled by Procyon v0.5.30
// 

package flaxchat.g;

import java.awt.Rectangle;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Container;

public class c extends b
{
    private int c;
    private int d;
    private int e;
    
    public c() {
        this(5, 5);
    }
    
    public c(final int n, final int n2) {
        this(n, n2, -1);
    }
    
    public c(final int c, final int d, final int e) {
        this.c = c;
        this.d = d;
        this.e = e;
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
            return new Dimension(0, 0);
        }
        int c = this.c;
        final int d = this.d;
        int max = 0;
        int n = 0;
        while (true) {
            Label_0146: {
                if (a == 0) {
                    break Label_0146;
                }
                final Component component = container.getComponent(n);
                if (component.isVisible()) {
                    final Dimension preferredSize = component.getPreferredSize();
                    int n2 = preferredSize.width;
                    max = Math.max(max, preferredSize.height);
                    if (this.e > 0 && n2 >= this.e) {
                        n2 = this.e;
                    }
                    if (b) {
                        component.setBounds(c, d, n2, preferredSize.height);
                    }
                    c += n2 + this.c;
                }
                ++n;
            }
            if (n < componentCount) {
                continue;
            }
            break;
        }
        final Dimension dimension = new Dimension(c, d + max + this.d);
        final int n3 = container.getSize().width - componentCount - this.c;
        if (dimension.width >= n3) {
            final int n4 = n3 / componentCount;
            if (b) {
                int c2 = this.c;
                int n5 = 0;
                while (true) {
                    Label_0314: {
                        if (a == 0) {
                            break Label_0314;
                        }
                        final Component component2 = container.getComponent(n5);
                        final Rectangle bounds = component2.getBounds();
                        Label_0311: {
                            if (component2 instanceof flaxchat.b.a) {
                                final int width = component2.getPreferredSize().width;
                                component2.setBounds(c2, bounds.y, width, max);
                                c2 += width + 1;
                                if (a == 0) {
                                    break Label_0311;
                                }
                            }
                            component2.setBounds(c2, bounds.y, n4, max);
                            c2 += n4 + 1;
                        }
                        ++n5;
                    }
                    if (n5 < componentCount) {
                        continue;
                    }
                    break;
                }
            }
            final Dimension size = container.getSize();
            size.height = dimension.height;
            return size;
        }
        if (b) {
            int n6 = 0;
            while (true) {
                Label_0391: {
                    if (a == 0) {
                        break Label_0391;
                    }
                    final Component component3 = container.getComponent(n6);
                    final Rectangle bounds2 = component3.getBounds();
                    component3.setBounds(bounds2.x, bounds2.y, bounds2.width, max);
                    ++n6;
                }
                if (n6 < componentCount) {
                    continue;
                }
                break;
            }
        }
        return dimension;
    }
}
