// 
// Decompiled by Procyon v0.5.30
// 

package b.a.e;

import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.Dimension;
import java.awt.Container;
import java.awt.Component;
import b.a.d.d;
import java.util.Hashtable;
import java.awt.LayoutManager;

public class e implements LayoutManager
{
    protected int a;
    protected Hashtable b;
    protected int[] c;
    protected int[] d;
    protected int e;
    protected int f;
    protected int g;
    protected Hashtable h;
    protected boolean i;
    protected boolean j;
    protected int k;
    protected int l;
    
    public e(final int a, final int e, final int k, final int f, final int l, final String s) {
        this.b = new Hashtable();
        this.e = 4;
        this.f = 5;
        this.g = 0;
        this.h = new Hashtable();
        this.i = false;
        this.j = false;
        this.k = 7;
        this.l = 5;
        this.a = a;
        this.e = e;
        this.k = k;
        this.f = f;
        this.l = l;
        this.c = this.a(this.a(s, "gcol"));
        if (this.c == null) {
            this.c = this.a(this.a(s, "xcol"));
        }
        else {
            this.i = true;
        }
        this.d = this.a(this.a(s, "grow"));
        if (this.d == null) {
            this.d = this.a(this.a(s, "xrow"));
        }
        else {
            this.j = true;
        }
        this.g = b.a.d.d.a((Object)this.a(s, "inset"));
    }
    
    public void addLayoutComponent(final String s, final Component component) {
        if (s != null && component != null) {
            synchronized (component.getTreeLock()) {
                this.b.put(component, s.toLowerCase());
            }
        }
    }
    
    public void layoutContainer(final Container container) {
        synchronized (container.getTreeLock()) {
            final Insets insets = container.getInsets();
            final Dimension size = container.getSize();
            final Dimension dimension = new Dimension(size);
            final int componentCount = container.getComponentCount();
            final int n = (componentCount + this.a - 1) / this.a;
            final int[] array = new int[this.a];
            final int[] array2 = new int[n];
            final Dimension a = this.a(container, false, array, array2);
            final int[] array3 = new int[this.a];
            final int[] array4 = new int[n];
            final Dimension a2 = this.a(container, true, array3, array4);
            int n2 = 0;
            int n3 = 0;
            final Insets insets2 = insets;
            insets2.top += this.g;
            final Insets insets3 = insets;
            insets3.left += this.g;
            final Insets insets4 = insets;
            insets4.bottom += this.g;
            final Insets insets5 = insets;
            insets5.right += this.g;
            final Dimension dimension2 = dimension;
            dimension2.width -= insets.left + insets.right;
            final Dimension dimension3 = dimension;
            dimension3.height -= insets.top + insets.bottom;
            if (size.width > a.width) {
                n2 = size.width - a.width;
                if (this.c != null) {
                    for (int i = 0; i < this.c.length; ++i) {
                        if (this.c[i] < this.a) {
                            final int[] array5 = array;
                            final int n4 = this.c[i];
                            array5[n4] += n2 * (i + 1) / this.c.length - n2 * i / this.c.length;
                        }
                    }
                    n2 = 0;
                }
                else if (this.e == 4) {
                    for (int j = 0; j < this.a; ++j) {
                        final int[] array6 = array;
                        final int n5 = j;
                        array6[n5] += n2 * (j + 1) / this.a - n2 * j / this.a;
                    }
                    n2 = 0;
                }
            }
            else if (size.width <= a2.width) {
                System.arraycopy(array3, 0, array, 0, this.a);
            }
            else if (size.width < a.width) {
                int n7;
                final int n6 = n7 = a.width - size.width;
                final int n8 = a.width - a2.width;
                int n11;
                for (int n9 = 0; n9 < this.a && n7 > 0; n7 -= n11, ++n9) {
                    final int n10 = array[n9] - array3[n9];
                    n11 = (n10 * n6 + n8 / 2) / n8;
                    if (n11 == 0 && n10 > 0) {
                        n11 = 1;
                    }
                    else if (n11 > n10) {
                        n11 = n10;
                    }
                    if (n11 > n7) {
                        n11 = n7;
                    }
                    final int[] array7 = array;
                    final int n12 = n9;
                    array7[n12] -= n11;
                }
            }
            if (size.height > a.height) {
                n3 = size.height - a.height;
                if (this.d != null) {
                    for (int k = 0; k < this.d.length; ++k) {
                        if (this.d[k] < n) {
                            final int[] array8 = array2;
                            final int n13 = this.d[k];
                            array8[n13] += n3 * (k + 1) / this.d.length - n3 * k / this.d.length;
                        }
                    }
                    n3 = 0;
                }
                else if (this.k == 8) {
                    for (int l = 0; l < n; ++l) {
                        final int[] array9 = array2;
                        final int n14 = l;
                        array9[n14] += n3 * (l + 1) / n - n3 * l / n;
                    }
                    n3 = 0;
                }
            }
            else if (size.height <= a2.height) {
                System.arraycopy(array4, 0, array2, 0, n);
            }
            else if (size.height < a.height) {
                int n16;
                final int n15 = n16 = a.height - size.height;
                final int n17 = a.height - a2.height;
                int n20;
                for (int n18 = 0; n18 < n && n16 > 0; n16 -= n20, ++n18) {
                    final int n19 = array2[n18] - array4[n18];
                    n20 = (n19 * n15 + n17 / 2) / n17;
                    if (n20 == 0 && n19 > 0) {
                        n20 = 1;
                    }
                    else if (n20 > n19) {
                        n20 = n19;
                    }
                    if (n20 > n16) {
                        n20 = n16;
                    }
                    final int[] array10 = array2;
                    final int n21 = n18;
                    array10[n21] -= n20;
                }
            }
            int n22 = 0;
            int left = 0;
            int top = insets.top;
            if (this.k == 7) {
                top += n3 / 2;
            }
            else if (this.e == 6) {
                top += n3;
            }
            for (int n23 = 0; n23 < componentCount; ++n23) {
                final Component component = container.getComponent(n23);
                final int a3 = this.a(component);
                final int b = this.b(component);
                final Dimension a4 = this.a(component, new Dimension(component.getPreferredSize()), false);
                final int n24 = n23 % this.a;
                if (n24 == 0) {
                    left = insets.left;
                    if (this.e == 3) {
                        left += n2 / 2;
                    }
                    else if (this.e == 2) {
                        left += n2;
                    }
                }
                int width;
                int n25;
                if (a3 == 4 || a4.width >= array[n24]) {
                    width = array[n24];
                    n25 = 0;
                }
                else {
                    width = a4.width;
                    if (a3 == 1) {
                        n25 = 0;
                    }
                    else if (a3 == 2) {
                        n25 = array[n24] - width;
                    }
                    else {
                        n25 = (array[n24] - width) / 2;
                    }
                }
                int height;
                int n26;
                if (b == 8 || a4.height >= array2[n22]) {
                    height = array2[n22];
                    n26 = 0;
                }
                else {
                    height = a4.height;
                    if (b == 5) {
                        n26 = 0;
                    }
                    else if (b == 6) {
                        n26 = array2[n22] - height;
                    }
                    else {
                        n26 = (array2[n22] - height) / 2;
                    }
                }
                if (component.isVisible()) {
                    component.setBounds(this.a(component, new Rectangle(left + n25, top + n26, width, height)));
                }
                if (n24 == this.a - 1) {
                    if (array2[n22] != 0) {
                        top += array2[n22] + this.l;
                    }
                    ++n22;
                }
                else if (array[n24] != 0) {
                    left += array[n24] + this.f;
                }
            }
        }
    }
    
    protected int a(final Component component) {
        final String s = this.b.get(component);
        if (b.a.d.d.a(s)) {
            return this.e;
        }
        if (s.indexOf("l") >= 0) {
            return 1;
        }
        if (s.indexOf("ch") >= 0) {
            return 3;
        }
        if (s.indexOf("fh") >= 0) {
            return 4;
        }
        if (s.indexOf("r") >= 0) {
            return 2;
        }
        return this.e;
    }
    
    protected int b(final Component component) {
        final String s = this.b.get(component);
        if (s == null || s.equals("")) {
            return this.k;
        }
        if (s.indexOf("t") >= 0) {
            return 5;
        }
        if (s.indexOf("cv") >= 0) {
            return 7;
        }
        if (s.indexOf("fv") >= 0) {
            return 8;
        }
        if (s.indexOf("b") >= 0) {
            return 6;
        }
        return this.k;
    }
    
    protected Rectangle a(final Component component, final Rectangle rectangle) {
        return rectangle;
    }
    
    protected Dimension a(final Component component, final Dimension dimension, final boolean b) {
        return dimension;
    }
    
    protected Dimension a(final Container container, final boolean b) {
        synchronized (container.getTreeLock()) {
            return this.a(container, b, new int[this.a], null);
        }
    }
    
    protected Dimension a(final Container container, final boolean b, final int[] array, final int[] array2) {
        final Dimension dimension = new Dimension();
        int height = 0;
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        for (int i = 0; i < container.getComponentCount(); ++i) {
            final Component component = container.getComponent(i);
            if (component.isVisible()) {
                Dimension dimension2;
                if (b) {
                    dimension2 = this.a(component, new Dimension(component.getMinimumSize()), true);
                }
                else {
                    final Dimension dimension3 = this.h.get(component);
                    if (dimension3 == null) {
                        dimension2 = this.a(component, new Dimension(component.getPreferredSize()), false);
                        this.h.put(component, new Dimension(dimension2));
                    }
                    else {
                        dimension2 = new Dimension(dimension3);
                    }
                }
                if (b.a.d.d.b(this.c, i % this.a) >= 0 && !this.i) {
                    dimension2.width = 1;
                }
                if (b.a.d.d.b(this.d, n) >= 0 && !this.j) {
                    dimension2.height = 1;
                }
                if (height < dimension2.height) {
                    height = dimension2.height;
                }
                if (array[i % this.a] < dimension2.width) {
                    array[i % this.a] = dimension2.width;
                }
            }
            if ((i + 1) % this.a == 0 || i == container.getComponentCount() - 1) {
                if (height == 0) {
                    ++n2;
                }
                if (array2 != null) {
                    array2[n] = height;
                }
                final Dimension dimension4 = dimension;
                dimension4.height += height;
                height = 0;
                ++n;
            }
        }
        for (int j = 0; j < this.a; ++j) {
            if (array[j] == 0) {
                ++n3;
            }
            final Dimension dimension5 = dimension;
            dimension5.width += array[j];
        }
        final Insets insets = container.getInsets();
        final Dimension dimension6 = dimension;
        dimension6.width += insets.left + insets.right + (this.a - n3 - 1) * this.f + this.g * 2;
        final Dimension dimension7 = dimension;
        dimension7.height += insets.top + insets.bottom + (n - n2 - 1) * this.l + this.g * 2;
        return dimension;
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
                this.b.remove(component);
                this.h.remove(component);
            }
        }
    }
    
    protected String a(final String s, String string) {
        if (s == null || string == null) {
            return null;
        }
        string += "=";
        final int index = s.indexOf(string);
        if (index < 0) {
            return null;
        }
        final int index2 = s.indexOf(59, index + 1);
        if (index2 >= 0) {
            return s.substring(index + string.length(), index2);
        }
        return s.substring(index + string.length());
    }
    
    protected int[] a(String string) {
        if (string == null || string.equals("")) {
            return null;
        }
        string += ",";
        int n = 0;
        int n2 = 0;
        int[] a = new int[10];
        int index;
        while ((index = string.indexOf(44, n2)) >= 0) {
            final int a2 = b.a.d.d.a(string.substring(n2, index), -1);
            if (a2 >= 0) {
                if (a.length <= n) {
                    a = b.a.d.d.a(a, a.length * 2);
                }
                a[n++] = a2;
            }
            n2 = index + 1;
        }
        if (n == 0) {
            return null;
        }
        return b.a.d.d.a(a, n);
    }
}
