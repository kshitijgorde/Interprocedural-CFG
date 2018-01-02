// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

import javax.swing.tree.TreePath;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

final class dj implements ActionListener
{
    private final cl a;
    
    dj(final cl a) {
        this.a = a;
    }
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        final aL b = cl.b(this.a);
        final TreePath a = cl.a(this.a);
        final aL al = b;
        if (b.isRootVisible() && al.getRowForPath(a) == 0) {
            return;
        }
        if (cl.b(this.a).isCollapsed(cl.a(this.a))) {
            cl.b(this.a).expandPath(cl.a(this.a));
        }
    }
}
