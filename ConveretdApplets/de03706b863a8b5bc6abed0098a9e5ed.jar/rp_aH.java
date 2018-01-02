import javax.swing.plaf.TreeUI;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.Icon;
import javax.swing.plaf.basic.BasicTreeUI;
import javax.swing.ImageIcon;
import javax.swing.tree.TreeModel;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.JTree;

// 
// Decompiled by Procyon v0.5.30
// 

public class rp_aH extends rp_ef
{
    rp_aJ a;
    public boolean a;
    
    public rp_aH(final JTree tree, final TreeCellRenderer treeCellRenderer, final boolean b, final rp_aJ a) {
        super(tree, treeCellRenderer, b);
        this.a = false;
        this.a = a;
        this.a(tree);
    }
    
    public void a() {
        super.a();
        final TreeModel model;
        if (this.a && (model = (this = this).a.getModel()) instanceof DefaultTreeModel) {
            ((DefaultTreeModel)model).reload();
        }
    }
    
    public final void a(final JTree tree) {
        if (this.a != null) {
            final ImageIcon expandedIcon = new ImageIcon(this.a.a("tr2_exp.png"));
            final ImageIcon collapsedIcon = new ImageIcon(this.a.a("tr2_col.png"));
            final ImageIcon closedIcon = new ImageIcon(this.a.a("tr2_cl.png"));
            final ImageIcon openIcon = new ImageIcon(this.a.a("tr2_op.png"));
            final ImageIcon leafIcon = new ImageIcon(this.a.a("tr2_pl.png"));
            final TreeUI ui;
            if ((ui = tree.getUI()) instanceof BasicTreeUI) {
                ((BasicTreeUI)ui).setExpandedIcon(expandedIcon);
                ((BasicTreeUI)ui).setCollapsedIcon(collapsedIcon);
            }
            tree.putClientProperty("JTree.lineStyle", "None");
            final DefaultTreeCellRenderer cellRenderer;
            (cellRenderer = new DefaultTreeCellRenderer()).setOpenIcon(openIcon);
            cellRenderer.setClosedIcon(closedIcon);
            cellRenderer.setLeafIcon(leafIcon);
            tree.setCellRenderer(cellRenderer);
        }
        super.a(tree);
    }
}
