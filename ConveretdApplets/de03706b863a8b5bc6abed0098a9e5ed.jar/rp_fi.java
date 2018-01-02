import javax.swing.tree.TreePath;
import javax.swing.MenuSelectionManager;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.JTree;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

// 
// Decompiled by Procyon v0.5.30
// 

final class rp_fi extends MouseAdapter
{
    private /* synthetic */ rp_du a;
    
    rp_fi(final rp_du a) {
        this.a = a;
    }
    
    public final void mouseReleased(final MouseEvent mouseEvent) {
        final TreePath pathForLocation;
        if ((pathForLocation = ((JTree)mouseEvent.getSource()).getPathForLocation(mouseEvent.getPoint().x, mouseEvent.getPoint().y)) == null) {
            return;
        }
        final DefaultMutableTreeNode defaultMutableTreeNode = (DefaultMutableTreeNode)pathForLocation.getLastPathComponent();
        if (this.a.a.b || defaultMutableTreeNode.isLeaf()) {
            this.a.a.setSelectedItem(pathForLocation);
            this.a.a();
            MenuSelectionManager.defaultManager().clearSelectedPath();
        }
    }
}
