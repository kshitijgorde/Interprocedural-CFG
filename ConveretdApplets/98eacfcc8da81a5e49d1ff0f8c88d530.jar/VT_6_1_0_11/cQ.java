// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

import javax.swing.JTree;
import java.awt.Rectangle;
import javax.swing.tree.TreePath;
import java.awt.Point;

public final class cQ
{
    public static int a;
    public static int b;
    public static int c;
    Point d;
    aR e;
    TreePath f;
    Rectangle g;
    int h;
    
    cQ(final JTree tree, final Point d) {
        this.d = d;
        this.f = tree.getPathForLocation(this.d.x, this.d.y);
        if (this.f != null) {
            this.e = (aR)this.f.getLastPathComponent();
            this.g = tree.getPathBounds(this.f);
            if (this.d.y - this.g.y < 4) {
                this.h = cQ.a;
                return;
            }
            if (this.g.y + this.g.height - this.d.y < 4) {
                this.h = cQ.c;
                return;
            }
            this.h = cQ.b;
        }
    }
    
    static {
        cQ.a = 1;
        cQ.b = 2;
        cQ.c = 3;
    }
}
