// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.custom;

import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.TreeEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.events.TreeListener;
import org.eclipse.swt.events.ControlListener;

public class TableTreeEditor extends ControlEditor
{
    TableTree tableTree;
    TableTreeItem item;
    int column;
    ControlListener columnListener;
    TreeListener treeListener;
    
    public TableTreeEditor(final TableTree tableTree) {
        super(tableTree.getTable());
        this.column = -1;
        (this.tableTree = tableTree).addTreeListener(this.treeListener = new TreeListener() {
            final Runnable runnable = new Runnable() {
                final /* synthetic */ TableTreeEditor$1 this$1 = this$1;
                
                public void run() {
                    if (this.this$1.this$0.editor == null || this.this$1.this$0.editor.isDisposed()) {
                        return;
                    }
                    if (this.this$1.this$0.tableTree.isDisposed()) {
                        return;
                    }
                    this.this$1.this$0.layout();
                    this.this$1.this$0.editor.setVisible(true);
                }
            };
            final /* synthetic */ TableTreeEditor this$0;
            
            public void treeCollapsed(final TreeEvent treeEvent) {
                if (TableTreeEditor.this.editor == null || TableTreeEditor.this.editor.isDisposed()) {
                    return;
                }
                TableTreeEditor.this.editor.setVisible(false);
                treeEvent.display.asyncExec(this.runnable);
            }
            
            public void treeExpanded(final TreeEvent treeEvent) {
                if (TableTreeEditor.this.editor == null || TableTreeEditor.this.editor.isDisposed()) {
                    return;
                }
                TableTreeEditor.this.editor.setVisible(false);
                treeEvent.display.asyncExec(this.runnable);
            }
        });
        this.columnListener = new ControlListener() {
            public void controlMoved(final ControlEvent controlEvent) {
                TableTreeEditor.this.layout();
            }
            
            public void controlResized(final ControlEvent controlEvent) {
                TableTreeEditor.this.layout();
            }
        };
        this.grabVertical = true;
    }
    
    Rectangle computeBounds() {
        if (this.item == null || this.column == -1 || this.item.isDisposed() || this.item.tableItem == null) {
            return new Rectangle(0, 0, 0, 0);
        }
        final Rectangle bounds = this.item.getBounds(this.column);
        final Rectangle clientArea = this.tableTree.getClientArea();
        if (bounds.x < clientArea.x + clientArea.width && bounds.x + bounds.width > clientArea.x + clientArea.width) {
            bounds.width = clientArea.x + clientArea.width - bounds.x;
        }
        final Rectangle rectangle = new Rectangle(bounds.x, bounds.y, this.minimumWidth, this.minimumHeight);
        if (this.grabHorizontal) {
            rectangle.width = Math.max(bounds.width, this.minimumWidth);
        }
        if (this.grabVertical) {
            rectangle.height = Math.max(bounds.height, this.minimumHeight);
        }
        if (this.horizontalAlignment == 131072) {
            final Rectangle rectangle2 = rectangle;
            rectangle2.x += bounds.width - rectangle.width;
        }
        else if (this.horizontalAlignment != 16384) {
            final Rectangle rectangle3 = rectangle;
            rectangle3.x += (bounds.width - rectangle.width) / 2;
        }
        if (this.verticalAlignment == 1024) {
            final Rectangle rectangle4 = rectangle;
            rectangle4.y += bounds.height - rectangle.height;
        }
        else if (this.verticalAlignment != 128) {
            final Rectangle rectangle5 = rectangle;
            rectangle5.y += (bounds.height - rectangle.height) / 2;
        }
        return rectangle;
    }
    
    public void dispose() {
        if (this.tableTree != null && !this.tableTree.isDisposed()) {
            final Table table = this.tableTree.getTable();
            if (table != null && !table.isDisposed() && this.column > -1 && this.column < table.getColumnCount()) {
                table.getColumn(this.column).removeControlListener(this.columnListener);
            }
            if (this.treeListener != null) {
                this.tableTree.removeTreeListener(this.treeListener);
            }
        }
        this.treeListener = null;
        this.columnListener = null;
        this.tableTree = null;
        this.item = null;
        this.column = -1;
        super.dispose();
    }
    
    public int getColumn() {
        return this.column;
    }
    
    public TableTreeItem getItem() {
        return this.item;
    }
    
    public void setColumn(final int column) {
        final Table table = this.tableTree.getTable();
        final int columnCount = table.getColumnCount();
        if (columnCount == 0) {
            this.column = ((column == 0) ? 0 : -1);
            this.layout();
            return;
        }
        if (this.column > -1 && this.column < columnCount) {
            table.getColumn(this.column).removeControlListener(this.columnListener);
            this.column = -1;
        }
        if (column < 0 || column >= table.getColumnCount()) {
            return;
        }
        this.column = column;
        table.getColumn(this.column).addControlListener(this.columnListener);
        this.layout();
    }
    
    public void setItem(final TableTreeItem item) {
        this.item = item;
        this.layout();
    }
    
    public void setEditor(final Control editor, final TableTreeItem item, final int column) {
        this.setItem(item);
        this.setColumn(column);
        this.setEditor(editor);
    }
    
    public void layout() {
        if (this.tableTree == null || this.tableTree.isDisposed()) {
            return;
        }
        if (this.item == null || this.item.isDisposed()) {
            return;
        }
        final int columnCount = this.tableTree.getTable().getColumnCount();
        if (columnCount == 0 && this.column != 0) {
            return;
        }
        if (columnCount > 0 && (this.column < 0 || this.column >= columnCount)) {
            return;
        }
        super.layout();
    }
}
