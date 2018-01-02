// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

import javax.swing.tree.TreePath;
import javax.swing.tree.DefaultTreeSelectionModel;

public final class bt extends DefaultTreeSelectionModel
{
    public final String[] a() {
        if (this.a(null) != null) {
            final aR[] a;
            final String[] array = new String[(a = this.a(null)).length];
            for (int i = 0; i < a.length; ++i) {
                array[i] = a[i].a().a();
            }
            return array;
        }
        return null;
    }
    
    public final String b() {
        if (this.c() != null) {
            return this.c().a().a();
        }
        return null;
    }
    
    public final aR c() {
        final TreePath selectionPath;
        if ((selectionPath = this.getSelectionPath()) != null) {
            return (aR)selectionPath.getLastPathComponent();
        }
        return null;
    }
    
    public final aR[] a(final TreePath[] selectionPaths) {
        TreePath[] selectionPaths2;
        if (selectionPaths == null) {
            selectionPaths2 = this.getSelectionPaths();
        }
        else {
            selectionPaths2 = selectionPaths;
            this.setSelectionPaths(selectionPaths);
        }
        if (selectionPaths2 == null) {
            return null;
        }
        final aR[] array = new aR[selectionPaths2.length];
        for (int i = 0; i < selectionPaths2.length; ++i) {
            array[i] = (aR)selectionPaths2[i].getLastPathComponent();
        }
        return array;
    }
}
