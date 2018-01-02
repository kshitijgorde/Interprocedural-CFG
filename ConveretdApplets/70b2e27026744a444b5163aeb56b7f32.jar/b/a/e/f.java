// 
// Decompiled by Procyon v0.5.30
// 

package b.a.e;

import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.Point;
import java.awt.Container;
import b.a.d.d;
import java.util.StringTokenizer;
import java.awt.Dimension;
import java.awt.Component;
import java.util.Hashtable;
import java.awt.LayoutManager;

public class f implements LayoutManager
{
    protected Hashtable a;
    protected int b;
    protected int c;
    protected int d;
    protected int e;
    protected int f;
    protected boolean g;
    protected Component h;
    
    public f() {
        this.a = new Hashtable();
        this.b = 0;
        this.c = -1;
        this.d = 5;
        this.e = 6;
        this.f = 3;
        this.g = false;
    }
    
    public f(final int n, final int n2) {
        this(n, n2, 5, 0, -1);
    }
    
    public f(final int n, final int n2, final int n3) {
        this(n, n2, n3, 0, -1);
    }
    
    public f(final int n, final int n2, final int n3, final int n4) {
        this(n, n2, n3, n4, -1);
    }
    
    public f(final int e, final int f, final int d, final int b, final int c) {
        this.a = new Hashtable();
        this.b = 0;
        this.c = -1;
        this.d = 5;
        this.e = 6;
        this.f = 3;
        this.g = false;
        this.e = e;
        this.f = f;
        this.d = d;
        this.b = b;
        this.c = c;
        this.g = (e < 6);
    }
    
    protected int a(final Component component) {
        final g g = this.a.get(component);
        if (g != null) {
            return g.a;
        }
        return this.f;
    }
    
    protected int b(final Component component) {
        final g g = this.a.get(component);
        if (g != null) {
            return g.f;
        }
        return 0;
    }
    
    protected Dimension c(final Component component) {
        final Dimension a = this.a(component, new Dimension(component.getMinimumSize()), true);
        final int a2 = this.a(component);
        if (this.b(component) > 0) {
            if (this.g) {
                a.width = 0;
            }
            else {
                a.height = 0;
            }
        }
        if (a2 == 4 || a2 == 5) {
            a.width = 0;
        }
        else if (a2 == 9 || a2 == 10) {
            a.height = 0;
        }
        return this.a(a, component);
    }
    
    protected Dimension d(final Component component) {
        final Dimension a = this.a(component, new Dimension(component.getPreferredSize()), false);
        final int a2 = this.a(component);
        if ((this.h != null || this.c >= 0) && a2 > 0) {
            if (this.g) {
                if (this.h != null) {
                    a.width = this.h.getPreferredSize().width;
                }
                else {
                    a.width = this.c;
                }
            }
            else if (this.h != null) {
                a.height = this.h.getPreferredSize().height;
            }
            else {
                a.height = this.c;
            }
        }
        if (a2 == 5) {
            a.width = 1;
        }
        else if (a2 == 10) {
            a.height = 1;
        }
        return this.a(a, component);
    }
    
    protected Dimension a(final Dimension dimension, final Component component) {
        final g g = this.a.get(component);
        if (g != null) {
            dimension.width = Math.min(Math.max(dimension.width, g.b), g.c);
            dimension.height = Math.min(Math.max(dimension.height, g.d), g.e);
        }
        return dimension;
    }
    
    public void addLayoutComponent(final String s, final Component component) {
        if (s != null && component != null) {
            final g g = new g(this);
            final StringTokenizer stringTokenizer = new StringTokenizer(s.toLowerCase(), ";=");
            while (stringTokenizer.hasMoreTokens()) {
                String s2 = stringTokenizer.nextToken();
                final int index = s2.indexOf("*");
                if (index >= 0) {
                    g.f = Math.max(b.a.d.d.a((Object)s2.substring(index + 1)), 1);
                    s2 = s2.substring(0, index);
                }
                if (s2.equals("l")) {
                    g.a = 1;
                }
                else if (s2.equals("r")) {
                    g.a = 2;
                }
                else if (s2.equals("ch")) {
                    g.a = 3;
                }
                else if (s2.equals("fh")) {
                    g.a = 4;
                }
                else if (s2.equals("sh")) {
                    g.a = 5;
                }
                else if (s2.equals("t")) {
                    g.a = 6;
                }
                else if (s2.equals("b")) {
                    g.a = 7;
                }
                else if (s2.equals("cv")) {
                    g.a = 8;
                }
                else if (s2.equals("fv")) {
                    g.a = 9;
                }
                else if (s2.equals("sv")) {
                    g.a = 10;
                }
                else {
                    if (index >= 0 || s2.equals("") || !stringTokenizer.hasMoreTokens()) {
                        continue;
                    }
                    final int a = b.a.d.d.a((Object)stringTokenizer.nextToken());
                    if (s2.equals("hmax")) {
                        g.c = a;
                    }
                    else if (s2.equals("hmin")) {
                        g.b = a;
                    }
                    else if (s2.equals("vmax")) {
                        g.e = a;
                    }
                    else {
                        if (!s2.equals("vmin")) {
                            continue;
                        }
                        g.d = a;
                    }
                }
            }
            synchronized (component.getTreeLock()) {
                this.a.put(component, g);
            }
        }
    }
    
    public void layoutContainer(final Container container) {
        synchronized (container.getTreeLock()) {
            final Insets insets = container.getInsets();
            final Dimension size = container.getSize();
            final Dimension dimension = new Dimension(size);
            final Dimension minimumLayoutSize = this.minimumLayoutSize(container);
            final Dimension preferredLayoutSize = this.preferredLayoutSize(container);
            int n = 0;
            int n2 = 0;
            int n3 = 0;
            int n4 = 0;
            boolean b = false;
            boolean b2 = false;
            int n5 = 0;
            final Dimension dimension2 = dimension;
            dimension2.width -= insets.left + insets.right + this.b * 2;
            final Dimension dimension3 = dimension;
            dimension3.height -= insets.top + insets.bottom + this.b * 2;
            for (int i = 0; i < container.getComponentCount(); ++i) {
                final Component component = container.getComponent(i);
                if (component.isVisible()) {
                    ++n;
                    if (this.b(component) > 0) {
                        b2 = true;
                    }
                }
            }
            int n6;
            if (this.g) {
                if (minimumLayoutSize.width >= size.width) {
                    n5 = preferredLayoutSize.width - minimumLayoutSize.width;
                    b = true;
                }
                else if (preferredLayoutSize.width > size.width) {
                    n5 = preferredLayoutSize.width - size.width;
                    n4 = preferredLayoutSize.width - minimumLayoutSize.width;
                }
                n6 = insets.left + this.b;
                switch (this.e) {
                    case 1: {
                        break;
                    }
                    case 2: {
                        if (size.width > preferredLayoutSize.width) {
                            n6 += size.width - preferredLayoutSize.width;
                            break;
                        }
                        break;
                    }
                    case 4:
                    case 5: {
                        if (size.width > preferredLayoutSize.width) {
                            n3 = size.width - preferredLayoutSize.width;
                            break;
                        }
                        break;
                    }
                    default: {
                        if (size.width > preferredLayoutSize.width) {
                            n6 += (size.width - preferredLayoutSize.width) / 2;
                            break;
                        }
                        break;
                    }
                }
            }
            else {
                if (minimumLayoutSize.height >= size.height) {
                    n5 = preferredLayoutSize.height - minimumLayoutSize.height;
                    b = true;
                }
                else if (preferredLayoutSize.height > size.height) {
                    n5 = preferredLayoutSize.height - size.height;
                    n4 = preferredLayoutSize.height - minimumLayoutSize.height;
                }
                n6 = insets.top + this.b;
                switch (this.e) {
                    case 6: {
                        break;
                    }
                    case 7: {
                        if (size.height > preferredLayoutSize.height) {
                            n6 += size.height - preferredLayoutSize.height;
                            break;
                        }
                        break;
                    }
                    case 9:
                    case 10: {
                        if (size.height > preferredLayoutSize.height) {
                            n3 = size.height - preferredLayoutSize.height;
                            break;
                        }
                        break;
                    }
                    default: {
                        if (size.height > preferredLayoutSize.height) {
                            n6 += (size.height - preferredLayoutSize.height) / 2;
                            break;
                        }
                        break;
                    }
                }
            }
            int n7 = n5;
            for (int j = 0; j < container.getComponentCount(); ++j) {
                final Component component2 = container.getComponent(j);
                if (component2.isVisible()) {
                    final Dimension c = this.c(component2);
                    final Dimension d = this.d(component2);
                    final Dimension maximumSize = component2.getMaximumSize();
                    final Dimension dimension4 = new Dimension(d);
                    final Point point = new Point(0, 0);
                    final int a = this.a(component2);
                    if (this.b(component2) > 0) {
                        if (this.g) {
                            if (d.width + n3 > maximumSize.width) {
                                d.width = maximumSize.width;
                            }
                            else {
                                final Dimension dimension5 = d;
                                dimension5.width += n3;
                            }
                            dimension4.width = d.width;
                        }
                        else {
                            if (d.height + n3 > maximumSize.height) {
                                d.height = maximumSize.height;
                            }
                            else {
                                final Dimension dimension6 = d;
                                dimension6.height += n3;
                            }
                            dimension4.height = d.height;
                        }
                    }
                    if (this.g) {
                        if (b) {
                            dimension4.width = c.width;
                        }
                        else if (n7 > 0 && n4 > 0) {
                            final int n8 = d.width - c.width;
                            int n9 = (n8 * n5 + n4 / 2) / n4;
                            if (n9 == 0 && n8 > 0) {
                                n9 = 1;
                            }
                            else if (n9 > n8) {
                                n9 = n8;
                            }
                            if (n9 > n7) {
                                n9 = n7;
                            }
                            final Dimension dimension7 = dimension4;
                            dimension7.width -= n9;
                            n7 -= n9;
                        }
                        if (dimension.height < c.height) {
                            dimension4.height = c.height;
                        }
                        else if (dimension.height < d.height) {
                            dimension4.height = dimension.height;
                        }
                        point.x = n6;
                        n6 += dimension4.width + this.d;
                        switch (a) {
                            case 6: {
                                point.y = insets.top + this.b;
                                break;
                            }
                            case 7: {
                                point.y = size.height - insets.bottom - this.b - dimension4.height;
                                break;
                            }
                            case 9:
                            case 10: {
                                point.y = insets.top + this.b;
                                if (c.height < dimension.height) {
                                    dimension4.height = dimension.height;
                                    break;
                                }
                                break;
                            }
                            default: {
                                point.y = insets.top + this.b + (dimension.height - dimension4.height) / 2;
                                break;
                            }
                        }
                    }
                    else {
                        if (b) {
                            dimension4.height = c.height;
                        }
                        else if (n7 > 0 && n4 > 0) {
                            final int n10 = d.height - c.height;
                            int n11 = (n10 * n5 + n4 / 2) / n4;
                            if (n11 == 0 && n10 > 0) {
                                n11 = 1;
                            }
                            else if (n11 > n10) {
                                n11 = n10;
                            }
                            if (n11 > n7) {
                                n11 = n7;
                            }
                            final Dimension dimension8 = dimension4;
                            dimension8.height -= n11;
                            n7 -= n11;
                        }
                        if (dimension.width < c.width) {
                            dimension4.width = c.width;
                        }
                        else if (dimension.width < d.width) {
                            dimension4.width = dimension.width;
                        }
                        point.y = n6;
                        n6 += dimension4.height + this.d;
                        switch (a) {
                            case 1: {
                                point.x = insets.left + this.b;
                                break;
                            }
                            case 2: {
                                point.x = size.width - insets.right - this.b - dimension4.width;
                                break;
                            }
                            case 4:
                            case 5: {
                                point.x = insets.left + this.b;
                                if (c.width < dimension.width) {
                                    dimension4.width = dimension.width;
                                    break;
                                }
                                break;
                            }
                            default: {
                                point.x = insets.left + this.b + (dimension.width - dimension4.width) / 2;
                                break;
                            }
                        }
                    }
                    component2.setBounds(this.a(component2, new Rectangle(point.x, point.y, dimension4.width, dimension4.height)));
                    if (!b2 && n3 > 0 && n > 1) {
                        n6 += (n2 + 1) * n3 / (n - 1) - n2 * n3 / (n - 1);
                    }
                    ++n2;
                }
            }
        }
    }
    
    protected Rectangle a(final Component component, final Rectangle rectangle) {
        return rectangle;
    }
    
    protected Dimension a(final Component component, final Dimension dimension, final boolean b) {
        return dimension;
    }
    
    protected Dimension a(final Container container, final boolean b) {
        synchronized (container.getTreeLock()) {
            final Dimension dimension = new Dimension(0, 0);
            for (int i = 0; i < container.getComponentCount(); ++i) {
                final Component component = container.getComponent(i);
                if (component.isVisible()) {
                    Dimension dimension2;
                    if (b) {
                        dimension2 = this.c(component);
                    }
                    else {
                        dimension2 = this.d(component);
                    }
                    if (this.g) {
                        if (dimension.height < dimension2.height) {
                            dimension.height = dimension2.height;
                        }
                        final Dimension dimension3 = dimension;
                        dimension3.width += dimension2.width;
                        if (i > 0) {
                            final Dimension dimension4 = dimension;
                            dimension4.width += this.d;
                        }
                    }
                    else {
                        if (dimension.width < dimension2.width) {
                            dimension.width = dimension2.width;
                        }
                        final Dimension dimension5 = dimension;
                        dimension5.height += dimension2.height;
                        if (i > 0) {
                            final Dimension dimension6 = dimension;
                            dimension6.height += this.d;
                        }
                    }
                }
            }
            final Insets insets = container.getInsets();
            final Dimension dimension7 = dimension;
            dimension7.width += insets.left + insets.right + this.b * 2;
            final Dimension dimension8 = dimension;
            dimension8.height += insets.top + insets.bottom + this.b * 2;
            return dimension;
        }
    }
    
    public Dimension minimumLayoutSize(final Container container) {
        return this.a(container, true);
    }
    
    public Dimension preferredLayoutSize(final Container container) {
        return this.a(container, false);
    }
    
    public void removeLayoutComponent(final Component component) {
        if (component != null) {
            synchronized (component.getTreeLock()) {
                this.a.remove(component);
            }
        }
    }
}
