// 
// Decompiled by Procyon v0.5.30
// 

package z;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Container;
import java.awt.GridLayout;

public class as extends GridLayout
{
    private int a;
    private int b;
    private int c;
    private boolean d;
    private static /* synthetic */ boolean e;
    
    public as(final int n, final int n2, final int n3, final int a) {
        super(0, n, n2, n3);
        this.d = false;
        if (!as.e && n <= 0) {
            throw new AssertionError();
        }
        if (!as.e && a <= 0) {
            throw new AssertionError();
        }
        this.a = a;
    }
    
    public final void a(final int b) {
        if (!as.e && b <= 0) {
            throw new AssertionError();
        }
        this.b = b;
        this.d = true;
    }
    
    public final void b(final int c) {
        if (!as.e && c <= 0) {
            throw new AssertionError();
        }
        this.c = c;
    }
    
    public void layoutContainer(final Container container) {
        synchronized (container.getTreeLock()) {
            final Insets insets2;
            final Insets insets = insets2 = container.getInsets();
            final int top = insets2.top + this.getVgap();
            insets2.top = top;
            final Insets insets3 = insets;
            final int bottom = insets3.bottom + this.getVgap();
            insets3.bottom = bottom;
            final Insets insets4 = insets;
            final int left = insets4.left + this.getHgap();
            insets4.left = left;
            final Insets insets5 = insets;
            final int right = insets5.right + this.getHgap();
            insets5.right = right;
            final Insets insets6 = new Insets(top, bottom, left, right);
            final int componentCount;
            if ((componentCount = container.getComponentCount()) == 0) {
                return;
            }
            if (!as.e && this.getColumns() <= 0) {
                throw new AssertionError();
            }
            final int columns = this.getColumns();
            final int n = (componentCount + columns - 1) / columns;
            final int max = Math.max(n * this.a + (insets6.top + insets6.bottom + (n - 1) * this.getVgap()), this.c);
            if (this.d || max > container.getHeight() || max < container.getHeight()) {
                final int n2 = this.b - (container.getInsets().left + container.getInsets().right);
                container.setPreferredSize(new Dimension(n2, max));
                container.setSize(new Dimension(n2, max));
                this.d = false;
            }
            for (int n3 = (this.b - (insets6.left + insets6.right) - (columns - 1) * this.getHgap()) / columns, i = 0, left2 = insets6.left; i < columns; ++i, left2 += n3 + this.getHgap()) {
                for (int j = 0, top2 = insets6.top; j < n; ++j, top2 += this.a + this.getVgap()) {
                    final int n4;
                    if ((n4 = j * columns + i) < componentCount) {
                        container.getComponent(n4).setBounds(left2, top2, n3, this.a);
                        container.getComponent(n4).repaint();
                    }
                }
            }
        }
    }
    
    static {
        as.e = !as.class.desiredAssertionStatus();
    }
}
