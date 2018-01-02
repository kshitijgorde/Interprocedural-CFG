// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.custom;

import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Table;

public class TableEditor extends ControlEditor
{
    Table table;
    TableItem item;
    int column;
    ControlListener columnListener;
    Runnable timer;
    static final int TIMEOUT = 1500;
    
    public TableEditor(final Table table) {
        super(table);
        this.column = -1;
        this.table = table;
        this.columnListener = new ControlListener() {
            public void controlMoved(final ControlEvent controlEvent) {
                TableEditor.this.layout();
            }
            
            public void controlResized(final ControlEvent controlEvent) {
                TableEditor.this.layout();
            }
        };
        this.timer = new Runnable() {
            public void run() {
                TableEditor.this.layout();
            }
        };
        this.grabVertical = true;
    }
    
    Rectangle computeBounds() {
        if (this.item == null || this.column == -1 || this.item.isDisposed()) {
            return new Rectangle(0, 0, 0, 0);
        }
        final Rectangle bounds = this.item.getBounds(this.column);
        final Rectangle imageBounds = this.item.getImageBounds(this.column);
        if (imageBounds.width != 0) {
            final int max = Math.max(imageBounds.x - bounds.x, 0);
            bounds.x = imageBounds.x + imageBounds.width;
            final Rectangle rectangle = bounds;
            rectangle.width -= max + imageBounds.width;
        }
        final Rectangle clientArea = this.table.getClientArea();
        if (bounds.x < clientArea.x + clientArea.width && bounds.x + bounds.width > clientArea.x + clientArea.width) {
            bounds.width = clientArea.x + clientArea.width - bounds.x;
        }
        final Rectangle rectangle2 = new Rectangle(bounds.x, bounds.y, this.minimumWidth, this.minimumHeight);
        if (this.grabHorizontal) {
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
        if (this.table != null && !this.table.isDisposed() && this.column > -1 && this.column < this.table.getColumnCount()) {
            this.table.getColumn(this.column).removeControlListener(this.columnListener);
        }
        this.columnListener = null;
        this.table = null;
        this.item = null;
        this.column = -1;
        this.timer = null;
        super.dispose();
    }
    
    public int getColumn() {
        return this.column;
    }
    
    public TableItem getItem() {
        return this.item;
    }
    
    void resize() {
        this.layout();
        if (this.table != null) {
            final Display display = this.table.getDisplay();
            display.timerExec(-1, this.timer);
            display.timerExec(1500, this.timer);
        }
    }
    
    public void setColumn(final int column) {
        final int columnCount = this.table.getColumnCount();
        if (columnCount == 0) {
            this.column = ((column == 0) ? 0 : -1);
            this.resize();
            return;
        }
        if (this.column > -1 && this.column < columnCount) {
            this.table.getColumn(this.column).removeControlListener(this.columnListener);
            this.column = -1;
        }
        if (column < 0 || column >= this.table.getColumnCount()) {
            return;
        }
        this.column = column;
        this.table.getColumn(this.column).addControlListener(this.columnListener);
        this.resize();
    }
    
    public void setItem(final TableItem item) {
        this.item = item;
        this.resize();
    }
    
    public void setEditor(final Control editor) {
        super.setEditor(editor);
        this.resize();
    }
    
    public void setEditor(final Control editor, final TableItem item, final int column) {
        this.setItem(item);
        this.setColumn(column);
        this.setEditor(editor);
    }
    
    public void layout() {
        if (this.table == null || this.table.isDisposed()) {
            return;
        }
        if (this.item == null || this.item.isDisposed()) {
            return;
        }
        final int columnCount = this.table.getColumnCount();
        if (columnCount == 0 && this.column != 0) {
            return;
        }
        if (columnCount > 0 && (this.column < 0 || this.column >= columnCount)) {
            return;
        }
        super.layout();
    }
}
