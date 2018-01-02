// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

import javax.swing.tree.TreePath;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

final class u implements MouseListener
{
    private final aL a;
    
    u(final aL a) {
        this.a = a;
    }
    
    public final void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public final void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public final void mouseReleased(final MouseEvent mouseEvent) {
        if (mouseEvent.getID() == 502) {
            aL.a(this.a, null);
            if (this.a.getSelectionCount() >= 2) {
                aL.a(this.a, this.a.getSelectionPaths());
            }
        }
    }
    
    public final void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public final void mouseExited(final MouseEvent mouseEvent) {
    }
}
