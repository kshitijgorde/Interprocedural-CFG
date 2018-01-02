import javax.swing.event.TreeExpansionEvent;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.JTree;
import javax.swing.event.TreeWillExpandListener;

// 
// Decompiled by Procyon v0.5.30
// 

public final class rp_U extends rp_aH implements TreeWillExpandListener
{
    public rp_U(final JTree tree, final TreeCellRenderer treeCellRenderer, final boolean b, final rp_aJ rp_aJ) {
        super(tree, null, true, rp_aJ);
        tree.addTreeWillExpandListener(this);
    }
    
    public final void treeWillExpand(final TreeExpansionEvent treeExpansionEvent) {
        final Object lastPathComponent;
        final rp_v rp_v;
        if ((lastPathComponent = treeExpansionEvent.getPath().getLastPathComponent()) instanceof rp_v && (rp_v = (rp_v)lastPathComponent).a() && !rp_v.a(1)) {
            rp_v.a(this.a.a());
        }
    }
    
    public final void treeWillCollapse(final TreeExpansionEvent treeExpansionEvent) {
    }
    
    public final void a() {
        super.a();
        try {
            final Object root;
            if ((root = super.a.getModel().getRoot()) instanceof rp_v) {
                ((rp_v)root).a(this.a.a());
            }
        }
        catch (Exception ex) {}
    }
}
