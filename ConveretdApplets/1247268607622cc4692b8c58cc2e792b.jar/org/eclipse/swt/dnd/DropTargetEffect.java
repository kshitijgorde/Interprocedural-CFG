// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.dnd;

import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Control;

public class DropTargetEffect extends DropTargetAdapter
{
    Control control;
    
    public DropTargetEffect(final Control control) {
        if (control == null) {
            SWT.error(4);
        }
        this.control = control;
    }
    
    public Control getControl() {
        return this.control;
    }
    
    public Widget getItem(final int n, final int n2) {
        if (this.control instanceof Table) {
            return this.getItem((Table)this.control, n, n2);
        }
        if (this.control instanceof Tree) {
            return this.getItem((Tree)this.control, n, n2);
        }
        return null;
    }
    
    Widget getItem(final Table table, final int n, final int n2) {
        final Point control = table.toControl(new Point(n, n2));
        final TableItem item = table.getItem(control);
        if (item != null) {
            return item;
        }
        final Rectangle clientArea = table.getClientArea();
        final int n3 = clientArea.y + clientArea.height;
        for (int itemCount = table.getItemCount(), i = table.getTopIndex(); i < itemCount; ++i) {
            final TableItem item2 = table.getItem(i);
            final Rectangle bounds = item2.getBounds();
            bounds.x = clientArea.x;
            bounds.width = clientArea.width;
            if (bounds.contains(control)) {
                return item2;
            }
            if (bounds.y > n3) {
                break;
            }
        }
        return null;
    }
    
    Widget getItem(final Tree tree, final int n, final int n2) {
        final Point control = tree.toControl(new Point(n, n2));
        final TreeItem item = tree.getItem(control);
        if (item == null) {
            final Rectangle clientArea = tree.getClientArea();
            if (clientArea.contains(control)) {
                final int n3 = clientArea.y + clientArea.height;
                for (TreeItem treeItem = tree.getTopItem(); treeItem != null; treeItem = this.nextItem(tree, treeItem)) {
                    final Rectangle bounds = treeItem.getBounds();
                    final int n4 = bounds.y + bounds.height;
                    if (bounds.y <= control.y && control.y < n4) {
                        return treeItem;
                    }
                    if (n4 > n3) {
                        break;
                    }
                }
                return null;
            }
        }
        return item;
    }
    
    TreeItem nextItem(final Tree tree, final TreeItem treeItem) {
        if (treeItem == null) {
            return null;
        }
        if (treeItem.getExpanded() && treeItem.getItemCount() > 0) {
            return treeItem.getItem(0);
        }
        TreeItem treeItem2 = treeItem.getParentItem();
        int n = (treeItem2 == null) ? tree.indexOf(treeItem) : treeItem2.indexOf(treeItem);
        TreeItem treeItem3;
        for (int n2 = (treeItem2 == null) ? tree.getItemCount() : treeItem2.getItemCount(); n + 1 >= n2; n = ((treeItem2 == null) ? tree.indexOf(treeItem3) : treeItem2.indexOf(treeItem3)), n2 = ((treeItem2 == null) ? tree.getItemCount() : treeItem2.getItemCount())) {
            if (treeItem2 == null) {
                return null;
            }
            treeItem3 = treeItem2;
            treeItem2 = treeItem3.getParentItem();
        }
        return (treeItem2 == null) ? tree.getItem(n + 1) : treeItem2.getItem(n + 1);
    }
    
    TreeItem previousItem(final Tree tree, final TreeItem treeItem) {
        if (treeItem == null) {
            return null;
        }
        final TreeItem parentItem = treeItem.getParentItem();
        final int n = (parentItem == null) ? tree.indexOf(treeItem) : parentItem.indexOf(treeItem);
        if (n == 0) {
            return parentItem;
        }
        TreeItem item = (parentItem == null) ? tree.getItem(n - 1) : parentItem.getItem(n - 1);
        for (int n2 = item.getItemCount(); n2 > 0 && item.getExpanded(); item = item.getItem(n2 - 1), n2 = item.getItemCount()) {}
        return item;
    }
}
