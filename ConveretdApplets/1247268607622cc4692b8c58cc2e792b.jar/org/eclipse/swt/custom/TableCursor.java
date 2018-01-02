// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.custom;

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.internal.SWTEventListener;
import org.eclipse.swt.widgets.TypedListener;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.ScrollBar;
import org.eclipse.swt.accessibility.AccessibleListener;
import org.eclipse.swt.accessibility.AccessibleEvent;
import org.eclipse.swt.accessibility.AccessibleAdapter;
import org.eclipse.swt.accessibility.AccessibleControlListener;
import org.eclipse.swt.accessibility.AccessibleControlEvent;
import org.eclipse.swt.accessibility.AccessibleControlAdapter;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Canvas;

public class TableCursor extends Canvas
{
    Table table;
    TableItem row;
    TableColumn column;
    Listener listener;
    Listener tableListener;
    Listener resizeListener;
    Listener disposeItemListener;
    Listener disposeColumnListener;
    Color background;
    Color foreground;
    static final int BACKGROUND = 27;
    static final int FOREGROUND = 26;
    
    public TableCursor(final Table table, final int n) {
        super(table, n);
        this.row = null;
        this.column = null;
        this.background = null;
        this.foreground = null;
        this.table = table;
        this.setBackground(null);
        this.setForeground(null);
        this.listener = new Listener() {
            public void handleEvent(final Event event) {
                Label_0157: {
                    switch (event.type) {
                        case 12: {
                            TableCursor.this.onDispose(event);
                            break;
                        }
                        case 15:
                        case 16: {
                            TableCursor.this.redraw();
                            break;
                        }
                        case 1: {
                            TableCursor.this.keyDown(event);
                            break;
                        }
                        case 9: {
                            TableCursor.this.paint(event);
                            break;
                        }
                        case 31: {
                            event.doit = true;
                            switch (event.detail) {
                                case 4:
                                case 32:
                                case 64: {
                                    event.doit = false;
                                    break Label_0157;
                                }
                            }
                            break;
                        }
                    }
                }
            }
        };
        final int[] array = { 12, 15, 16, 1, 9, 31 };
        for (int i = 0; i < array.length; ++i) {
            this.addListener(array[i], this.listener);
        }
        this.tableListener = new Listener() {
            public void handleEvent(final Event event) {
                switch (event.type) {
                    case 3: {
                        TableCursor.this.tableMouseDown(event);
                        break;
                    }
                    case 15: {
                        TableCursor.this.tableFocusIn(event);
                        break;
                    }
                }
            }
        };
        this.table.addListener(15, this.tableListener);
        this.table.addListener(3, this.tableListener);
        this.disposeItemListener = new Listener() {
            public void handleEvent(final Event event) {
                TableCursor.this.unhookRowColumnListeners();
                TableCursor.this.row = null;
                TableCursor.this.column = null;
                TableCursor.this._resize();
            }
        };
        this.disposeColumnListener = new Listener() {
            public void handleEvent(final Event event) {
                TableCursor.this.unhookRowColumnListeners();
                TableCursor.this.row = null;
                TableCursor.this.column = null;
                TableCursor.this._resize();
            }
        };
        this.resizeListener = new Listener() {
            public void handleEvent(final Event event) {
                TableCursor.this._resize();
            }
        };
        final ScrollBar horizontalBar = this.table.getHorizontalBar();
        if (horizontalBar != null) {
            horizontalBar.addListener(13, this.resizeListener);
        }
        final ScrollBar verticalBar = this.table.getVerticalBar();
        if (verticalBar != null) {
            verticalBar.addListener(13, this.resizeListener);
        }
        this.getAccessible().addAccessibleControlListener(new AccessibleControlAdapter() {
            public void getRole(final AccessibleControlEvent accessibleControlEvent) {
                accessibleControlEvent.detail = 29;
            }
        });
        this.getAccessible().addAccessibleListener(new AccessibleAdapter() {
            public void getName(final AccessibleEvent accessibleEvent) {
                if (TableCursor.this.row == null) {
                    return;
                }
                accessibleEvent.result = TableCursor.this.row.getText((TableCursor.this.column == null) ? 0 : TableCursor.this.table.indexOf(TableCursor.this.column));
            }
        });
    }
    
    public void addSelectionListener(final SelectionListener selectionListener) {
        this.checkWidget();
        if (selectionListener == null) {
            SWT.error(4);
        }
        final TypedListener typedListener = new TypedListener(selectionListener);
        this.addListener(13, typedListener);
        this.addListener(14, typedListener);
    }
    
    void onDispose(final Event event) {
        this.removeListener(12, this.listener);
        this.notifyListeners(12, event);
        event.type = 0;
        this.table.removeListener(15, this.tableListener);
        this.table.removeListener(3, this.tableListener);
        this.unhookRowColumnListeners();
        final ScrollBar horizontalBar = this.table.getHorizontalBar();
        if (horizontalBar != null) {
            horizontalBar.removeListener(13, this.resizeListener);
        }
        final ScrollBar verticalBar = this.table.getVerticalBar();
        if (verticalBar != null) {
            verticalBar.removeListener(13, this.resizeListener);
        }
    }
    
    void keyDown(final Event event) {
        if (this.row == null) {
            return;
        }
        switch (event.character) {
            case '\r': {
                this.notifyListeners(14, new Event());
            }
            default: {
                final int index = this.table.indexOf(this.row);
                final int n = (this.column == null) ? 0 : this.table.indexOf(this.column);
                switch (event.keyCode) {
                    case 16777217: {
                        this.setRowColumn(Math.max(0, index - 1), n, true);
                        break;
                    }
                    case 16777218: {
                        this.setRowColumn(Math.min(index + 1, this.table.getItemCount() - 1), n, true);
                        break;
                    }
                    case 16777219:
                    case 16777220: {
                        final int columnCount = this.table.getColumnCount();
                        if (columnCount == 0) {
                            break;
                        }
                        int[] columnOrder;
                        int n2;
                        for (columnOrder = this.table.getColumnOrder(), n2 = 0; n2 < columnOrder.length && columnOrder[n2] != n; ++n2) {}
                        if (n2 == columnOrder.length) {
                            n2 = 0;
                        }
                        if (event.keyCode == (((this.getStyle() & 0x4000000) != 0x0) ? 16777220 : 16777219)) {
                            this.setRowColumn(index, columnOrder[Math.max(0, n2 - 1)], true);
                            break;
                        }
                        this.setRowColumn(index, columnOrder[Math.min(columnCount - 1, n2 + 1)], true);
                        break;
                    }
                    case 16777223: {
                        this.setRowColumn(0, n, true);
                        break;
                    }
                    case 16777224: {
                        this.setRowColumn(this.table.getItemCount() - 1, n, true);
                        break;
                    }
                    case 16777221: {
                        int n3 = this.table.getTopIndex();
                        if (n3 == index) {
                            final Rectangle clientArea = this.table.getClientArea();
                            final Rectangle bounds = this.table.getItem(n3).getBounds(0);
                            final Rectangle rectangle = clientArea;
                            rectangle.height -= bounds.y;
                            n3 = Math.max(0, n3 - Math.max(1, clientArea.height / this.table.getItemHeight()) + 1);
                        }
                        this.setRowColumn(n3, n, true);
                        break;
                    }
                    case 16777222: {
                        final int topIndex = this.table.getTopIndex();
                        final Rectangle clientArea2 = this.table.getClientArea();
                        final Rectangle bounds2 = this.table.getItem(topIndex).getBounds(0);
                        final Rectangle rectangle2 = clientArea2;
                        rectangle2.height -= bounds2.y;
                        final int max = Math.max(1, clientArea2.height / this.table.getItemHeight());
                        final int n4 = this.table.getItemCount() - 1;
                        int n5 = Math.min(n4, topIndex + max - 1);
                        if (n5 == index) {
                            n5 = Math.min(n4, n5 + max - 1);
                        }
                        this.setRowColumn(n5, n, true);
                        break;
                    }
                }
            }
        }
    }
    
    void paint(final Event event) {
        if (this.row == null) {
            return;
        }
        final int n = (this.column == null) ? 0 : this.table.indexOf(this.column);
        final GC gc = event.gc;
        final Display display = this.getDisplay();
        gc.setBackground(this.getBackground());
        gc.setForeground(this.getForeground());
        gc.fillRectangle(event.x, event.y, event.width, event.height);
        int n2 = 0;
        final Point size = this.getSize();
        final Image image = this.row.getImage(n);
        if (image != null) {
            final Rectangle bounds = image.getBounds();
            gc.drawImage(image, n2, (size.y - bounds.height) / 2);
            n2 += bounds.width;
        }
        final String text = this.row.getText(n);
        if (text.length() > 0) {
            final Rectangle bounds2 = this.row.getBounds(n);
            final Point stringExtent = gc.stringExtent(text);
            if ("win32".equals(SWT.getPlatform())) {
                if (this.table.getColumnCount() == 0 || n == 0) {
                    n2 += 2;
                }
                else {
                    switch (this.column.getAlignment()) {
                        case 16384: {
                            n2 += 6;
                            break;
                        }
                        case 131072: {
                            n2 = bounds2.width - stringExtent.x - 6;
                            break;
                        }
                        case 16777216: {
                            n2 += (bounds2.width - n2 - stringExtent.x) / 2;
                            break;
                        }
                    }
                }
            }
            else if (this.table.getColumnCount() == 0) {
                n2 += 5;
            }
            else {
                switch (this.column.getAlignment()) {
                    case 16384: {
                        n2 += 5;
                        break;
                    }
                    case 131072: {
                        n2 = bounds2.width - stringExtent.x - 2;
                        break;
                    }
                    case 16777216: {
                        n2 += (bounds2.width - n2 - stringExtent.x) / 2 + 2;
                        break;
                    }
                }
            }
            gc.drawString(text, n2, (size.y - stringExtent.y) / 2);
        }
        if (this.isFocusControl()) {
            gc.setBackground(display.getSystemColor(2));
            gc.setForeground(display.getSystemColor(1));
            gc.drawFocus(0, 0, size.x, size.y);
        }
    }
    
    void tableFocusIn(final Event event) {
        if (this.isDisposed()) {
            return;
        }
        if (this.isVisible()) {
            if (this.row == null && this.column == null) {
                return;
            }
            this.setFocus();
        }
    }
    
    void tableMouseDown(final Event event) {
        if (this.isDisposed() || !this.isVisible()) {
            return;
        }
        final Point point = new Point(event.x, event.y);
        final int n = this.table.getLinesVisible() ? this.table.getGridLineWidth() : 0;
        TableItem item = this.table.getItem(point);
        if ((this.table.getStyle() & 0x10000) != 0x0) {
            if (item == null) {
                return;
            }
        }
        else {
            final int n2 = (item != null) ? this.table.indexOf(item) : this.table.getTopIndex();
            final int itemCount = this.table.getItemCount();
            final Rectangle clientArea = this.table.getClientArea();
            for (int i = n2; i < itemCount; ++i) {
                final TableItem item2 = this.table.getItem(i);
                final Rectangle bounds = item2.getBounds(0);
                if (point.y >= bounds.y && point.y < bounds.y + bounds.height + n) {
                    item = item2;
                    break;
                }
                if (bounds.y > clientArea.y + clientArea.height) {
                    return;
                }
            }
            if (item == null) {
                return;
            }
        }
        TableColumn tableColumn = null;
        final int columnCount = this.table.getColumnCount();
        if (columnCount == 0) {
            if ((this.table.getStyle() & 0x10000) == 0x0) {
                final Rectangle bounds2;
                final Rectangle rectangle = bounds2 = item.getBounds(0);
                bounds2.width += n;
                final Rectangle rectangle2 = rectangle;
                rectangle2.height += n;
                if (!rectangle.contains(point)) {
                    return;
                }
            }
        }
        else {
            for (int j = 0; j < columnCount; ++j) {
                final Rectangle bounds3;
                final Rectangle rectangle3 = bounds3 = item.getBounds(j);
                bounds3.width += n;
                final Rectangle rectangle4 = rectangle3;
                rectangle4.height += n;
                if (rectangle3.contains(point)) {
                    tableColumn = this.table.getColumn(j);
                    break;
                }
            }
            if (tableColumn == null) {
                if ((this.table.getStyle() & 0x10000) == 0x0) {
                    return;
                }
                tableColumn = this.table.getColumn(0);
            }
        }
        this.setRowColumn(item, tableColumn, true);
        this.setFocus();
    }
    
    void setRowColumn(final int n, final int n2, final boolean b) {
        this.setRowColumn((n == -1) ? null : this.table.getItem(n), (n2 == -1 || this.table.getColumnCount() == 0) ? null : this.table.getColumn(n2), b);
    }
    
    void setRowColumn(final TableItem row, final TableColumn column, final boolean b) {
        if (this.row == row && this.column == column) {
            return;
        }
        if (this.row != null && this.row != row) {
            this.row.removeListener(12, this.disposeItemListener);
            this.row = null;
        }
        if (this.column != null && this.column != column) {
            this.column.removeListener(12, this.disposeColumnListener);
            this.column.removeListener(10, this.resizeListener);
            this.column.removeListener(11, this.resizeListener);
            this.column = null;
        }
        if (row != null) {
            if (this.row != row) {
                (this.row = row).addListener(12, this.disposeItemListener);
                this.table.showItem(row);
            }
            if (this.column != column && column != null) {
                (this.column = column).addListener(12, this.disposeColumnListener);
                column.addListener(10, this.resizeListener);
                column.addListener(11, this.resizeListener);
                this.table.showColumn(column);
            }
            this.setBounds(row.getBounds((column == null) ? 0 : this.table.indexOf(column)));
            this.redraw();
            if (b) {
                this.notifyListeners(13, new Event());
            }
        }
        this.getAccessible().setFocus(-1);
    }
    
    public void setVisible(final boolean visible) {
        this.checkWidget();
        if (visible) {
            this._resize();
        }
        super.setVisible(visible);
    }
    
    public void removeSelectionListener(final SelectionListener selectionListener) {
        this.checkWidget();
        if (selectionListener == null) {
            SWT.error(4);
        }
        this.removeListener(13, selectionListener);
        this.removeListener(14, selectionListener);
    }
    
    void _resize() {
        if (this.row == null) {
            this.setBounds(-200, -200, 0, 0);
        }
        else {
            this.setBounds(this.row.getBounds((this.column == null) ? 0 : this.table.indexOf(this.column)));
        }
    }
    
    public int getColumn() {
        this.checkWidget();
        return (this.column == null) ? 0 : this.table.indexOf(this.column);
    }
    
    public Color getBackground() {
        this.checkWidget();
        if (this.background == null) {
            return this.getDisplay().getSystemColor(27);
        }
        return this.background;
    }
    
    public Color getForeground() {
        this.checkWidget();
        if (this.foreground == null) {
            return this.getDisplay().getSystemColor(26);
        }
        return this.foreground;
    }
    
    public TableItem getRow() {
        this.checkWidget();
        return this.row;
    }
    
    public void setBackground(final Color background) {
        this.background = background;
        super.setBackground(this.getBackground());
        this.redraw();
    }
    
    public void setForeground(final Color foreground) {
        this.foreground = foreground;
        super.setForeground(this.getForeground());
        this.redraw();
    }
    
    public void setSelection(final int n, final int n2) {
        this.checkWidget();
        final int columnCount = this.table.getColumnCount();
        final int n3 = (columnCount == 0) ? 0 : (columnCount - 1);
        if (n < 0 || n >= this.table.getItemCount() || n2 < 0 || n2 > n3) {
            SWT.error(5);
        }
        this.setRowColumn(n, n2, false);
    }
    
    public void setSelection(final TableItem tableItem, final int n) {
        this.checkWidget();
        final int columnCount = this.table.getColumnCount();
        final int n2 = (columnCount == 0) ? 0 : (columnCount - 1);
        if (tableItem == null || tableItem.isDisposed() || n < 0 || n > n2) {
            SWT.error(5);
        }
        this.setRowColumn(this.table.indexOf(tableItem), n, false);
    }
    
    void unhookRowColumnListeners() {
        if (this.column != null) {
            this.column.removeListener(12, this.disposeColumnListener);
            this.column.removeListener(10, this.resizeListener);
            this.column.removeListener(11, this.resizeListener);
            this.column = null;
        }
        if (this.row != null) {
            this.row.removeListener(12, this.disposeItemListener);
            this.row = null;
        }
    }
}
