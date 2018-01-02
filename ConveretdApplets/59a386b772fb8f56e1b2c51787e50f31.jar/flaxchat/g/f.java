// 
// Decompiled by Procyon v0.5.30
// 

package flaxchat.g;

import java.util.Enumeration;
import flaxchat.e.e;
import java.awt.Insets;
import java.awt.Dimension;
import java.awt.LayoutManager;
import flaxchat.f.a;
import java.awt.Component;
import java.awt.Container;
import java.awt.LayoutManager2;

public class f implements LayoutManager2
{
    private Container a;
    private Component b;
    private a c;
    private int d;
    private int e;
    private int f;
    private static String[] z;
    
    public f(final Container container) {
        this(container, 0, 0);
    }
    
    public f(final Container a, final int e, final int f) {
        this.c = new a();
        this.a = a;
        this.e = e;
        this.f = f;
        a.setLayout(this);
    }
    
    public void addLayoutComponent(final Component component, final Object o) {
        synchronized (component.getTreeLock()) {
            if (o instanceof String) {
                this.addLayoutComponent((String)o, component);
                if (flaxchat.g.a.a == 0) {
                    // monitorexit(component.getTreeLock())
                    return;
                }
            }
            throw new IllegalArgumentException(flaxchat.g.f.z[1]);
        }
    }
    
    public void addLayoutComponent(final String s, final Component component) {
        final int a = flaxchat.g.a.a;
        synchronized (component.getTreeLock()) {
            Label_0037: {
                if (!this.c.b()) {
                    component.setVisible(false);
                    if (a == 0) {
                        break Label_0037;
                    }
                }
                this.b = component;
            }
            int n = 0;
            while (true) {
                Label_0099: {
                    if (a == 0) {
                        break Label_0099;
                    }
                    if (((h)this.c.b(n)).b.toLowerCase().equals(s.toLowerCase())) {
                        ((h)this.c.b(n)).c = component;
                        // monitorexit(component.getTreeLock())
                        return;
                    }
                    ++n;
                }
                if (n >= this.c.a()) {
                    this.c.b(new h(this, s, component));
                    // monitorexit(component.getTreeLock())
                    return;
                }
                continue;
            }
        }
    }
    
    public void removeLayoutComponent(final Component component) {
        final int a = flaxchat.g.a.a;
        synchronized (component.getTreeLock()) {
            int n = 0;
        Label_0110:
            while (true) {
                Label_0098: {
                    if (a == 0) {
                        break Label_0098;
                    }
                    if (((h)this.c.b(n)).c == component) {
                        if (component.isVisible() && component.getParent() != null) {
                            this.b(component.getParent());
                        }
                        this.c.c(n);
                        if (this.d <= n) {
                            break Label_0110;
                        }
                        --this.d;
                        if (a == 0) {
                            break Label_0110;
                        }
                    }
                    ++n;
                }
                if (n < this.c.a()) {
                    continue;
                }
                break;
            }
        }
        // monitorexit(component.getTreeLock())
    }
    
    public Dimension preferredLayoutSize(final Container container) {
        final int a = flaxchat.g.a.a;
        synchronized (container.getTreeLock()) {
            final Insets insets = container.getInsets();
            final int componentCount = container.getComponentCount();
            int width = 0;
            int height = 0;
            boolean visible = false;
            int n = 0;
            while (true) {
                Label_0100: {
                    if (a == 0) {
                        break Label_0100;
                    }
                    final Component component = container.getComponent(n);
                    visible = component.isVisible();
                    final Dimension preferredSize = component.getPreferredSize();
                    if (preferredSize.width > width) {
                        width = preferredSize.width;
                    }
                    if (preferredSize.height > height) {
                        height = preferredSize.height;
                    }
                    ++n;
                }
                if (n >= componentCount) {
                    // monitorexit(container.getTreeLock())
                    return visible ? new Dimension(insets.left + insets.right + width + this.e * 2, insets.top + insets.bottom + height + this.f * 2) : new Dimension(0, 0);
                }
                continue;
            }
        }
    }
    
    public Dimension minimumLayoutSize(final Container container) {
        int a = flaxchat.g.a.a;
        synchronized (container.getTreeLock()) {
            boolean visible = false;
            final Insets insets = container.getInsets();
            final int componentCount = container.getComponentCount();
            int width = 0;
            int height = 0;
            int n = 0;
            while (true) {
                Label_0100: {
                    if (a == 0) {
                        break Label_0100;
                    }
                    final Component component = container.getComponent(n);
                    visible = component.isVisible();
                    final Dimension minimumSize = component.getMinimumSize();
                    if (minimumSize.width > width) {
                        width = minimumSize.width;
                    }
                    if (minimumSize.height > height) {
                        height = minimumSize.height;
                    }
                    ++n;
                }
                if (n >= componentCount) {
                    final Dimension dimension = visible ? new Dimension(insets.left + insets.right + width + this.e * 2, insets.top + insets.bottom + height + this.f * 2) : new Dimension(0, 0);
                    // monitorexit(container.getTreeLock())
                    final Dimension dimension2 = dimension;
                    if (flaxchat.e.e.c) {
                        flaxchat.g.a.a = ++a;
                    }
                    return dimension2;
                }
                continue;
            }
        }
    }
    
    public Dimension maximumLayoutSize(final Container container) {
        return new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE);
    }
    
    public float getLayoutAlignmentX(final Container container) {
        return 0.5f;
    }
    
    public float getLayoutAlignmentY(final Container container) {
        return 0.5f;
    }
    
    public void invalidateLayout(final Container container) {
    }
    
    public void layoutContainer(final Container container) {
        final int a = flaxchat.g.a.a;
        synchronized (container.getTreeLock()) {
            final Insets insets = container.getInsets();
            final int componentCount = container.getComponentCount();
            int n = 0;
            while (true) {
                Label_0118: {
                    if (a == 0) {
                        break Label_0118;
                    }
                    final Dimension size = container.getSize();
                    container.getComponent(n).setBounds(this.e + insets.left, this.f + insets.top, size.width - (this.e * 2 + insets.left + insets.right), size.height - (this.f * 2 + insets.top + insets.bottom));
                    ++n;
                }
                if (n >= componentCount) {
                    // monitorexit(container.getTreeLock())
                    return;
                }
                continue;
            }
        }
    }
    
    void a(final Container container) {
        if (container.getLayout() != this) {
            throw new IllegalArgumentException(flaxchat.g.f.z[0]);
        }
    }
    
    public void b(final Container container) {
        final int a = flaxchat.g.a.a;
        synchronized (container.getTreeLock()) {
            this.a(container);
            final int componentCount = container.getComponentCount();
            int n = 0;
            while (true) {
                Label_0100: {
                    if (a == 0) {
                        break Label_0100;
                    }
                    final Component component = container.getComponent(n);
                    if (component.isVisible()) {
                        component.setVisible(false);
                        this.d = (n + 1) % componentCount;
                        final Component component2 = container.getComponent(this.d);
                        component2.setVisible(true);
                        this.b = component2;
                        container.validate();
                        // monitorexit(container.getTreeLock())
                        return;
                    }
                    ++n;
                }
                if (n >= componentCount) {
                    this.c(container);
                    // monitorexit(container.getTreeLock())
                    return;
                }
                continue;
            }
        }
    }
    
    void c(final Container container) {
        if (container.getComponentCount() > 0) {
            this.d = 0;
            container.getComponent(0).setVisible(true);
            container.validate();
        }
    }
    
    public void a(final String s) {
        this.a(this.a, s);
    }
    
    private void a(final Container container, final String s) {
        final int a = flaxchat.g.a.a;
        synchronized (container.getTreeLock()) {
            this.a(container);
            Component c = null;
            final int a2 = this.c.a();
            int d = 0;
        Label_0095:
            while (true) {
                Label_0088: {
                    if (a == 0) {
                        break Label_0088;
                    }
                    final h h = (h)this.c.b(d);
                    if (h.b.toLowerCase().equals(s.toLowerCase())) {
                        c = h.c;
                        this.d = d;
                        break Label_0095;
                    }
                    ++d;
                }
                if (d < a2) {
                    continue;
                }
                break;
            }
            Label_0251: {
                if (c != null && !c.isVisible()) {
                    final int componentCount = container.getComponentCount();
                    int n = 0;
                Label_0159:
                    while (true) {
                        Label_0152: {
                            if (a == 0) {
                                break Label_0152;
                            }
                            final Component component = container.getComponent(n);
                            if (component.isVisible()) {
                                component.setVisible(false);
                                if (a == 0) {
                                    break Label_0159;
                                }
                            }
                            ++n;
                        }
                        if (n < componentCount) {
                            continue;
                        }
                        break;
                    }
                    c.setVisible(true);
                    this.b = c;
                    container.validate();
                    if (a == 0) {
                        break Label_0251;
                    }
                }
                if (c == null) {
                    final int componentCount2 = container.getComponentCount();
                    int n2 = 0;
                Label_0236:
                    while (true) {
                        Label_0229: {
                            if (a == 0) {
                                break Label_0229;
                            }
                            final Component component2 = container.getComponent(n2);
                            if (component2.isVisible()) {
                                component2.setVisible(false);
                                if (a == 0) {
                                    break Label_0236;
                                }
                            }
                            ++n2;
                        }
                        if (n2 < componentCount2) {
                            continue;
                        }
                        break;
                    }
                    this.b = c;
                    this.d = -1;
                    container.validate();
                }
            }
        }
        // monitorexit(container.getTreeLock())
    }
    
    public Component a() {
        return this.b;
    }
    
    public Component b(final String s) {
        final int a = flaxchat.g.a.a;
        final int a2 = this.c.a();
        int n = 0;
        while (true) {
            Label_0060: {
                if (a == 0) {
                    break Label_0060;
                }
                final h h = (h)this.c.b(n);
                if (h.b.toLowerCase().equals(s.toLowerCase())) {
                    return h.c;
                }
                ++n;
            }
            if (n >= a2) {
                return null;
            }
            continue;
        }
    }
    
    public String b() {
        if (this.d == -1) {
            return null;
        }
        if (this.d >= this.c.a()) {
            return null;
        }
        return ((h)this.c.b(this.d)).b;
    }
    
    public Component a(final String s, final String b) {
        final int a = flaxchat.g.a.a;
        synchronized (this.a.getTreeLock()) {
            int n = 0;
            while (true) {
                Label_0081: {
                    if (a == 0) {
                        break Label_0081;
                    }
                    final h h = (h)this.c.b(n);
                    if (h.b.toLowerCase().equals(s.toLowerCase())) {
                        h.b = b;
                        // monitorexit(this.a.getTreeLock())
                        return h.c;
                    }
                    ++n;
                }
                if (n < this.c.a()) {
                    continue;
                }
                break;
            }
        }
        // monitorexit(this.a.getTreeLock())
        return null;
    }
    
    public Enumeration c() {
        return new g(this);
    }
    
    static a a(final f f) {
        return f.c;
    }
    
    static {
        f.z = new String[] { z(z("Lg+Z\u0003\u001be%F\u0001UadR\u000bI5\u0007U\u0016_Y%M\u000bNa")), z(z("Xt*Z\u000bO5%P\u0000\u001ba+\u0014\bZl+A\u0010\u00015'[\nHa6U\rUadY\u0011HadV\u0001\u001btdG\u0010I|*S")) };
    }
    
    private static char[] z(final String s) {
        final char[] charArray = s.toCharArray();
        if (charArray.length < 2) {
            final int n = 0;
            charArray[n] ^= 'd';
        }
        return charArray;
    }
    
    private static String z(final char[] array) {
        final int i = array.length;
        for (int n = 0; i > n; ++n) {
            final int n2 = n;
            final char c = array[n2];
            char c2 = '\0';
            switch (n % 5) {
                case 0: {
                    c2 = ';';
                    break;
                }
                case 1: {
                    c2 = '\u0015';
                    break;
                }
                case 2: {
                    c2 = 'D';
                    break;
                }
                case 3: {
                    c2 = '4';
                    break;
                }
                default: {
                    c2 = 'd';
                    break;
                }
            }
            array[n2] = (char)(c ^ c2);
        }
        return new String(array).intern();
    }
}
