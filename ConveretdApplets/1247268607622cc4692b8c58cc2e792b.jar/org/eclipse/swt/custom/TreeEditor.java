// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.custom;

import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.events.TreeEvent;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.events.TreeListener;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swt.widgets.Tree;

public class TreeEditor extends ControlEditor
{
    Tree tree;
    TreeItem item;
    int column;
    ControlListener columnListener;
    TreeListener treeListener;
    Runnable timer;
    static final int TIMEOUT = 1500;
    
    public TreeEditor(final Tree tree) {
        super(tree);
        this.column = 0;
        this.tree = tree;
        this.columnListener = new ControlListener() {
            public void controlMoved(final ControlEvent controlEvent) {
                TreeEditor.this.layout();
            }
            
            public void controlResized(final ControlEvent controlEvent) {
                TreeEditor.this.layout();
            }
        };
        this.timer = new Runnable() {
            public void run() {
                TreeEditor.this.layout();
            }
        };
        tree.addTreeListener(this.treeListener = new TreeListener() {
            final Runnable runnable = new Runnable() {
                final /* synthetic */ TreeEditor$3 this$1 = this$1;
                
                public void run() {
                    if (this.this$1.this$0.editor == null || this.this$1.this$0.editor.isDisposed()) {
                        return;
                    }
                    if (this.this$1.this$0.tree.isDisposed()) {
                        return;
                    }
                    this.this$1.this$0.layout();
                    this.this$1.this$0.editor.setVisible(true);
                }
            };
            final /* synthetic */ TreeEditor this$0;
            
            public void treeCollapsed(final TreeEvent treeEvent) {
                if (TreeEditor.this.editor == null || TreeEditor.this.editor.isDisposed()) {
                    return;
                }
                TreeEditor.this.editor.setVisible(false);
                treeEvent.display.asyncExec(this.runnable);
            }
            
            public void treeExpanded(final TreeEvent treeEvent) {
                if (TreeEditor.this.editor == null || TreeEditor.this.editor.isDisposed()) {
                    return;
                }
                TreeEditor.this.editor.setVisible(false);
                treeEvent.display.asyncExec(this.runnable);
            }
        });
        this.grabVertical = true;
    }
    
    Rectangle computeBounds() {
        if (this.item == null || this.column == -1 || this.item.isDisposed()) {
            return new Rectangle(0, 0, 0, 0);
        }
        final Rectangle bounds = this.item.getBounds(this.column);
        final Rectangle imageBounds = this.item.getImageBounds(this.column);
        bounds.x = imageBounds.x + imageBounds.width;
        final Rectangle rectangle = bounds;
        rectangle.width -= imageBounds.width;
        final Rectangle clientArea = this.tree.getClientArea();
        if (bounds.x < clientArea.x + clientArea.width && bounds.x + bounds.width > clientArea.x + clientArea.width) {
            bounds.width = clientArea.x + clientArea.width - bounds.x;
        }
        final Rectangle rectangle2 = new Rectangle(bounds.x, bounds.y, this.minimumWidth, this.minimumHeight);
        if (this.grabHorizontal) {
            if (this.tree.getColumnCount() == 0) {
                bounds.width = clientArea.x + clientArea.width - bounds.x;
            }
            rectangle2.width = Math.max(bounds.width, this.minimumWidth);
        }
        if (this.grabVertical) {
            rectangle2.height = Math.max(bounds.height, this.minimumHeight);
        }
        if (this.horizontalAlignment == 131072) {
            final Rectangle rectangle3 = rectangle2;
            rectangle3.x += bounds.width - rectangle2.width;
        }
        else if (this.horizontalAlignment != 16384) {
            final Rectangle rectangle4 = rectangle2;
            rectangle4.x += (bounds.width - rectangle2.width) / 2;
        }
        rectangle2.x = Math.max(bounds.x, rectangle2.x);
        if (this.verticalAlignment == 1024) {
            final Rectangle rectangle5 = rectangle2;
            rectangle5.y += bounds.height - rectangle2.height;
        }
        else if (this.verticalAlignment != 128) {
            final Rectangle rectangle6 = rectangle2;
            rectangle6.y += (bounds.height - rectangle2.height) / 2;
        }
        return rectangle2;
    }
    
    public void dispose() {
        if (this.tree != null && !this.tree.isDisposed()) {
            if (this.column > -1 && this.column < this.tree.getColumnCount()) {
                this.tree.getColumn(this.column).removeControlListener(this.columnListener);
            }
            if (this.treeListener != null) {
                this.tree.removeTreeListener(this.treeListener);
            }
        }
        this.columnListener = null;
        this.treeListener = null;
        this.tree = null;
        this.item = null;
        this.column = 0;
        this.timer = null;
        super.dispose();
    }
    
    public int getColumn() {
        return this.column;
    }
    
    public TreeItem getItem() {
        return this.item;
    }
    
    void resize() {
        this.layout();
        if (this.tree != null) {
            final Display display = this.tree.getDisplay();
            display.timerExec(-1, this.timer);
            display.timerExec(1500, this.timer);
        }
    }
    
    public void setColumn(final int column) {
        final int columnCount = this.tree.getColumnCount();
        if (columnCount == 0) {
            this.column = ((column == 0) ? 0 : -1);
            this.resize();
            return;
        }
        if (this.column > -1 && this.column < columnCount) {
            this.tree.getColumn(this.column).removeControlListener(this.columnListener);
            this.column = -1;
        }
        if (column < 0 || column >= this.tree.getColumnCount()) {
            return;
        }
        this.column = column;
        this.tree.getColumn(this.column).addControlListener(this.columnListener);
        this.resize();
    }
    
    public void setItem(final TreeItem item) {
        this.item = item;
        this.resize();
    }
    
    public void setEditor(final Control editor, final TreeItem item, final int column) {
        this.setItem(item);
        this.setColumn(column);
        this.setEditor(editor);
    }
    
    public void setEditor(final Control editor) {
        super.setEditor(editor);
        this.resize();
    }
    
    public void setEditor(final Control editor, final TreeItem item) {
        this.setItem(item);
        this.setEditor(editor);
    }
    
    public void layout() {
        if (this.tree == null || this.tree.isDisposed()) {
            return;
        }
        if (this.item == null || this.item.isDisposed()) {
            return;
        }
        final int columnCount = this.tree.getColumnCount();
        if (columnCount == 0 && this.column != 0) {
            return;
        }
        if (columnCount > 0 && (this.column < 0 || this.column >= columnCount)) {
            return;
        }
        super.layout();
    }
}
