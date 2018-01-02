import javax.swing.JLabel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeNode;
import java.awt.Component;
import javax.swing.JList;
import javax.swing.DefaultListCellRenderer;

// 
// Decompiled by Procyon v0.5.30
// 

final class rp_cL extends DefaultListCellRenderer
{
    private rp_cL(final rp_ef rp_ef, final byte b) {
    }
    
    public final Component getListCellRendererComponent(final JList list, final Object o, final int n, final boolean b, final boolean b2) {
        if (o != null) {
            return new JLabel("  " + ((TreeNode)((TreePath)o).getLastPathComponent()).toString());
        }
        return super.getListCellRendererComponent(list, o, n, b, b2);
    }
}
