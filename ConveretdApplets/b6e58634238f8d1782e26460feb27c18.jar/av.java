import java.awt.Component;
import java.util.Vector;
import java.awt.Insets;
import java.awt.Dimension;
import java.util.BitSet;
import java.awt.Container;

// 
// Decompiled by Procyon v0.5.30
// 

public class av extends aw
{
    public av(final d d, final q q) {
        super(d, q);
    }
    
    public void layoutContainer(final Container container) {
        this.a(container);
        final Dimension size = container.getSize();
        final Insets insets = container.getInsets();
        synchronized (container.getTreeLock()) {
            final boolean ab = super.b.f().c().ab;
            final int l = super.b.f().c().l;
            final int a = super.b.f().c().a(super.b.f().q());
            this.b();
            final int c = this.c(container);
            final int n = ab ? this.d() : 0;
            final int n2 = size.width - (insets.left + insets.right) - (ab ? n : false);
            final int n3 = size.height - (insets.top + insets.bottom);
            final int n4 = 1;
            final int n5 = n2 / a;
            final int n6 = n2 % a;
            final int b = this.b(container);
            final int n7 = (n3 - b) / c;
            final int n8 = n3 - c * n7 - b;
            final int n9 = insets.left + n;
            final int top = insets.top;
            final BitSet[] array = new BitSet[super.b.f().f()];
            for (int i = 0; i < super.b.f().f(); ++i) {
                final Vector j = super.b.f().b(i).i();
                array[i] = new BitSet((j == null) ? 0 : j.size());
                if (j != null) {
                    for (int k = 0; k < j.size(); ++k) {
                        array[i].set(k);
                    }
                }
            }
            int n10 = 0;
            int n11 = 0;
            int n12 = 0;
            int n13 = n9;
            int n14 = top;
            int n15 = 0;
            int n16 = 0;
            for (int n17 = 0; n17 < container.getComponentCount(); ++n17) {
                Component component = container.getComponent(n17);
                if (!(component instanceof ag)) {
                    af af;
                    if (component instanceof af) {
                        af = (af)component;
                        if (af.c == 0) {
                            af.setSize(n + n2, 0);
                            af = null;
                        }
                        else if (!af.a()) {
                            ++n10;
                        }
                        component = container.getComponent(++n17);
                    }
                    else {
                        af = null;
                    }
                    ax ax;
                    if (component instanceof ax) {
                        ax = (ax)component;
                        if (ax.f > 0) {
                            n16 = n17;
                        }
                        component = container.getComponent(++n17);
                    }
                    else {
                        ax = null;
                    }
                    int n18 = (n11 < n8) ? 1 : 0;
                    if (component instanceof ae) {
                        final int f = ((ax)component).f;
                        if (n12 == a || ((n13 + n5 * f > n + n2 || af != null) && n12 != 0)) {
                            final Dimension size2 = container.getComponent(n16).getSize();
                            n13 = n9;
                            n14 += n4 * (n7 + n18);
                            n18 = ((++n11 < n8) ? 1 : 0);
                            n12 = 0;
                            container.getComponent(n16).setSize(size2.width, size2.height + n4 * (n7 + n18));
                        }
                        if (af != null) {
                            af.setBounds(insets.left, n14, n2 + n, af.c);
                            n14 += af.c;
                        }
                        if (ax != null) {
                            if (ab && ax.f > 0) {
                                ax.setBounds(insets.left, n14, n, n4 * (n7 + n18));
                            }
                            else {
                                ax.setBounds(insets.left, n14, 0, n4 * (n7 + n18));
                            }
                        }
                        n15 = f * n5 + ((n12 < n6) ? (f - Math.max(0, n12 + f - n6)) : 0);
                        component.setBounds(n13, n14, n15, n4 * (n7 + n18));
                        ((ax)component).a(array[n10]);
                        n13 += n15;
                        n12 += f;
                    }
                    else {
                        final int n19 = 1;
                        if ((n13 + n5 * n19 > n + n2 || af != null) && n12 != 0) {
                            final Dimension size3 = container.getComponent(n16).getSize();
                            n13 = n9;
                            n14 += n4 * (n7 + n18);
                            n15 = 0;
                            n18 = ((++n11 < n8) ? 1 : 0);
                            n12 = 0;
                            container.getComponent(n16).setSize(size3.width, size3.height + n4 * (n7 + n18));
                        }
                        if (af != null) {
                            af.setBounds(insets.left, n14, n2 + n, af.c);
                            n14 += af.c;
                        }
                        if (ax != null) {
                            ax.setBounds(insets.left, n14, (ab && ax.f > 0) ? n : 0, n4 * (n7 + n18));
                        }
                        n13 += n15;
                        n12 += n19;
                    }
                }
            }
            for (int n20 = 0; n20 < container.countComponents(); ++n20) {
                final Component component2 = container.getComponent(n20);
                if (component2 instanceof ae) {
                    ((ae)component2).b(array[((ae)component2).d]);
                }
            }
        }
    }
    
    private int c(final Container container) {
        int n = 1;
        int n2 = 0;
        final int a = super.b.f().c().a(super.b.f().q());
        synchronized (container.getTreeLock()) {
            for (int i = 0; i < container.getComponentCount(); ++i) {
                Component component = container.getComponent(i);
                boolean b;
                if (b = (component instanceof af)) {
                    if (((af)component).c == 0) {
                        b = false;
                    }
                    component = container.getComponent(++i);
                }
                if (component instanceof ax) {
                    if (((ax)component).g() && b) {
                        ++n;
                        n2 = 0;
                    }
                    component = container.getComponent(++i);
                }
                if (component instanceof ae) {
                    final int f = ((ax)component).f;
                    if ((n2 + f > a || b) && n2 != 0) {
                        ++n;
                        n2 = 0;
                    }
                    n2 += f;
                }
            }
        }
        return Math.max(n, super.b.f().f());
    }
}
