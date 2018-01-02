import java.util.Enumeration;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import javax.swing.tree.DefaultTreeModel;

// 
// Decompiled by Procyon v0.5.30
// 

public final class rp_f extends DefaultTreeModel
{
    private boolean a;
    
    public rp_f() {
        super(new rp_v("Root", -1, 0));
        this.a = false;
    }
    
    public rp_f(final rp_v rp_v) {
        super(rp_v);
        this.a = false;
        this.a = true;
    }
    
    public final rp_v a() {
        return (rp_v)super.getRoot();
    }
    
    public static void a(final rp_eA rp_eA, final DefaultMutableTreeNode defaultMutableTreeNode, final int n) {
        final Enumeration<rp_eA> elements = rp_eA.a.elements();
        while (elements.hasMoreElements()) {
            final rp_eA rp_eA2;
            if ((rp_eA2 = elements.nextElement()).a.equals("plan")) {
                final String a = rp_eA2.a("name", (String)null);
                final String a2 = rp_eA2.a("note", (String)null);
                if (a != null && a.length() > 0) {
                    defaultMutableTreeNode.add(new rp_v(a, a2));
                }
            }
            if (rp_eA2.a.equals("folder")) {
                final rp_v rp_v = new rp_v(rp_eA2.a("name", (String)null), rp_eA2.a("id", 0), n);
                defaultMutableTreeNode.add(rp_v);
                a(rp_eA2, rp_v, n);
            }
        }
    }
    
    public final void a() {
        ((rp_v)(this = this).getRoot()).removeAllChildren();
    }
    
    public final Object getChild(final Object o, final int n) {
        return ((TreeNode)o).getChildAt(n);
    }
    
    public final int getChildCount(final Object o) {
        if (!this.a || !(o instanceof rp_v)) {
            return ((TreeNode)o).getChildCount();
        }
        final rp_v rp_v;
        if (!(rp_v = (rp_v)o).a()) {
            return 0;
        }
        int n = 0;
        for (int i = 0; i < rp_v.getChildCount(); ++i) {
            if (((rp_v)rp_v.getChildAt(i)).a()) {
                ++n;
            }
        }
        return n;
    }
}
