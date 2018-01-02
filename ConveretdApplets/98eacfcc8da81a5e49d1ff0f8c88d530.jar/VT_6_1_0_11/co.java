// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

import java.util.Enumeration;
import javax.swing.tree.MutableTreeNode;
import java.util.List;
import java.util.Collections;
import com.hw.client.util.a;
import java.util.Vector;
import javax.swing.tree.TreeNode;
import com.wimba.clients.vboard.n;
import java.util.Hashtable;
import javax.swing.tree.DefaultTreeModel;

public final class co extends DefaultTreeModel
{
    private final Hashtable a;
    private n b;
    
    public co(final n b) {
        super(new aR());
        this.a = new Hashtable();
        this.b = b;
    }
    
    public final void a(final String s, final Vector vector) {
        if (com.hw.client.util.a.a()) {
            com.hw.client.util.a.b("VBTreeController.messageUpdate: reference=" + s + ", v.length=" + vector.size());
        }
        if (s != null && !Boolean.valueOf(this.b.y())) {
            if (com.hw.client.util.a.a()) {
                com.hw.client.util.a.b("VBTreeController.messageUpdate: is descending: reversing...");
            }
            Collections.reverse(vector);
        }
        this.c(s);
        if (vector == null || vector.size() == 0) {
            if (s != null && this.a.get(s) != null) {
                this.a.get(s).a().a(false);
            }
            return;
        }
        final Enumeration<Object> elements = vector.elements();
        while (elements.hasMoreElements()) {
            final aH ah = new aH(elements.nextElement());
            final String a = ah.a();
            if (com.hw.client.util.a.a()) {
                com.hw.client.util.a.b("update: mid: " + a + " - header position: " + ah.l());
            }
            if (a != null) {
                if (this.a.get(a) != null) {
                    this.a(a);
                }
                if (this.b.S().size() != 0 && !this.b.S().contains(ah.d())) {
                    continue;
                }
                final String b2;
                final boolean b = (b2 = ah.b()) == null || this.a.get(b2) == null;
                final aR ar = new aR(this.b, b, ah);
                this.a.put(a, ar);
                if (b) {
                    this.insertNodeInto(ar, (MutableTreeNode)this.getRoot(), this.getChildCount(this.getRoot()));
                    this.reload();
                }
                else {
                    final aR ar2;
                    if ((ar2 = this.a.get(b2)) == null) {
                        continue;
                    }
                    this.insertNodeInto(ar, ar2, this.getChildCount(ar2));
                }
            }
        }
        if (s != null && this.a.get(s) != null) {
            this.a.get(s).a().a(true);
        }
    }
    
    private void c(final String s) {
        if (com.hw.client.util.a.a()) {
            com.hw.client.util.a.b("VBTreeController.removeChildren: messageId=" + s);
        }
        aR ar;
        if (s == null) {
            ar = (aR)this.getRoot();
        }
        else {
            ar = this.a.get(s);
        }
        if (ar != null) {
            while (ar.getChildCount() > 0) {
                this.a(((aR)ar.getChildAt(0)).a().a());
            }
        }
    }
    
    public final void a(final String s) {
        if (com.hw.client.util.a.a()) {
            com.hw.client.util.a.b("VBTreeController.remove: messageId=" + s);
        }
        this.c(s);
        if (s == null) {
            this.reload();
            return;
        }
        final aR ar;
        if ((ar = this.a.get(s)) != null) {
            this.removeNodeFromParent(ar);
        }
        this.a.remove(s);
    }
    
    public final aH b(final String s) {
        if (this.a.get(s) != null) {
            return this.a.get(s).a();
        }
        return null;
    }
}
