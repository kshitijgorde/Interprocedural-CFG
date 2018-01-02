// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

import java.awt.Color;
import java.awt.RenderingHints;
import java.awt.Graphics2D;
import java.awt.Graphics;
import javax.swing.Icon;
import java.awt.Component;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;

public final class ah extends DefaultTreeCellRenderer
{
    private aR a;
    private final aL b;
    
    public ah(final aL b) {
        this.b = b;
    }
    
    public final Component getTreeCellRendererComponent(final JTree tree, final Object o, final boolean b, final boolean b2, final boolean b3, final int n, final boolean b4) {
        super.getTreeCellRendererComponent(tree, o, b, b2, b3, n, b4);
        this.a = (aR)o;
        this.setFont(aL.a(this.b));
        if (!this.b.a.G() && !this.b.a.D() && this.a.b()) {
            this.setIcon(aL.b(this.b));
        }
        else {
            this.setIcon(null);
        }
        this.setBackgroundSelectionColor(aL.c(this.b));
        return this;
    }
    
    public final void paintComponent(final Graphics graphics) {
        ((Graphics2D)graphics).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        super.paintComponent(graphics);
    }
    
    public final void paint(final Graphics graphics) {
        final cQ b = aL.d(this.b).b();
        if (this.a != null && b != null && this.a == b.e && b.h == cQ.b) {
            this.setBackgroundNonSelectionColor(aL.e(this.b));
        }
        else {
            this.setBackgroundNonSelectionColor(Color.white);
        }
        super.paint(graphics);
        if (b == null || this.a != b.e || b.e == null || b.h == cQ.b) {
            return;
        }
        graphics.setColor(aL.e(this.b));
        if (b.h == cQ.a) {
            graphics.fillRect(0, 0, this.getWidth() - 1, 2);
            return;
        }
        if (b.h == cQ.c) {
            graphics.fillRect(0, this.getHeight() - 2, this.getWidth() - 1, 2);
        }
    }
}
