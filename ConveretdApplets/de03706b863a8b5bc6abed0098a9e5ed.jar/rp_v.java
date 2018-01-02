import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.tree.DefaultMutableTreeNode;

// 
// Decompiled by Procyon v0.5.30
// 

public final class rp_v extends DefaultMutableTreeNode
{
    public String a;
    public String b;
    public int a;
    private int b;
    private int c;
    public Vector a;
    
    public rp_v(final String a, final String b) {
        this.a = null;
        this.b = null;
        this.a = -9;
        this.b = 0;
        this.c = 0;
        this.a = null;
        this.a = a;
        this.b = b;
        this.setAllowsChildren(false);
    }
    
    public rp_v(final String a, final int a2, final int c) {
        this.a = null;
        this.b = null;
        this.a = -9;
        this.b = 0;
        this.c = 0;
        this.a = null;
        this.a = a;
        this.c = c;
        this.a = a2;
        this.setAllowsChildren(true);
    }
    
    public final boolean a() {
        return this.a != -9;
    }
    
    public final String a() {
        if (this.a < 0) {
            return "null";
        }
        return "" + this.a;
    }
    
    public final boolean isLeaf() {
        return !this.a();
    }
    
    public final boolean a(final int n) {
        return (this.c & n) != 0x0;
    }
    
    public final void a(final int n, final boolean b) {
        this.c |= n;
    }
    
    public final boolean b(final int n) {
        return (this.b & n) != 0x0;
    }
    
    public final void b(final int n, final boolean b) {
        if (b) {
            this.b |= n;
            return;
        }
        this.b &= ~n;
    }
    
    public final void a(final boolean b) {
        for (int i = 0; i < this.getChildCount(); ++i) {
            ((rp_v)this.getChildAt(i)).b(1, b);
        }
    }
    
    public final List a() {
        final ArrayList<rp_v> list = new ArrayList<rp_v>();
        for (int i = 0; i < this.getChildCount(); ++i) {
            if (((rp_v)this.getChildAt(i)).b(1)) {
                list.add((rp_v)this.getChildAt(i));
            }
        }
        return list;
    }
    
    public final String a(final char c) {
        if (this.isRoot()) {
            return "";
        }
        final TreeNode[] path = this.getPath();
        String s = "" + c;
        for (int i = 1; i < path.length - 1; ++i) {
            final TreeNode treeNode;
            if ((treeNode = path[i]) instanceof rp_v) {
                final rp_v rp_v = (rp_v)treeNode;
                if (s.length() > 1) {
                    s += c;
                }
                s += rp_v.a;
            }
        }
        return s;
    }
    
    public final String b(final char c) {
        if (this.isRoot()) {
            return "";
        }
        final String a;
        if ((a = this.a('/')).length() < 2) {
            return a + (this = this).a;
        }
        return a + '/' + (this = this).a;
    }
    
    public final rp_v a(final String s) {
        for (int i = 0; i < this.getChildCount(); ++i) {
            final rp_v rp_v;
            if ((rp_v = (rp_v)this.getChildAt(i)).a.equals(s)) {
                return rp_v;
            }
        }
        return null;
    }
    
    public final String a(final int n) {
        final StringBuffer sb = new StringBuffer("&parent");
        if (n != -1) {
            sb.append(n);
        }
        sb.append('=');
        if (this.parent == null) {
            sb.append("null");
        }
        else {
            sb.append(((rp_v)this.parent).a());
        }
        if (this.a()) {
            sb.append("&folder");
        }
        else {
            sb.append("&plan");
        }
        if (n != -1) {
            sb.append(n);
        }
        sb.append('=');
        if (this.a()) {
            sb.append(this.a());
        }
        else {
            sb.append(rp_C.e((this = this).a));
        }
        return sb.toString();
    }
    
    public final void a(final rp_v rp_v) {
        int n;
        rp_v rp_v2;
        for (n = 0; n < this.getChildCount() && ((rp_v2 = (rp_v)this.getChildAt(n)).a() || !rp_v.a()) && ((rp_v2.a() && !rp_v.a()) || rp_v2.a.compareTo(rp_v.a) < 0); ++n) {}
        this.insert(rp_v, n);
    }
    
    public final void a(final rp_fK rp_fK) {
        try {
            if (!this.a()) {
                return;
            }
            if (this.a(1)) {
                return;
            }
            if (!rp_fK.a().a().a().a((String)null, true)) {
                return;
            }
            final rp_aS rp_aS;
            if ((rp_aS = (rp_aS)rp_fK.a(rp_aw.e, new rp_v[] { this }, null)).a()) {
                rp_f.a(rp_aS.a(), this, 0);
                rp_fK.a().a.a().reload(this);
                this.a(1, true);
            }
        }
        catch (Exception ex) {
            rp_C.a(10, "Exception in checkPlansLoaded: " + ex.getMessage());
        }
    }
    
    public final rp_v a(final int n) {
        if (this.a == n) {
            return this;
        }
        for (int i = 0; i < this.getChildCount(); ++i) {
            final rp_v rp_v;
            if ((rp_v = (rp_v)this.getChildAt(i)).a == n) {
                return rp_v;
            }
            final rp_v a;
            if (rp_v.a() && (a = rp_v.a(n)) != null) {
                return a;
            }
        }
        return null;
    }
    
    public final String toString() {
        return (this = this).a;
    }
}
