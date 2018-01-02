// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.custom;

import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.graphics.Drawable;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.PaletteData;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.events.TreeListener;
import org.eclipse.swt.internal.SWTEventListener;
import org.eclipse.swt.widgets.TypedListener;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Composite;

public class TableTree extends Composite
{
    Table table;
    TableTreeItem[] items;
    Image plusImage;
    Image minusImage;
    Image sizeImage;
    Listener listener;
    boolean inDispose;
    static final TableTreeItem[] EMPTY_ITEMS;
    static final String[] EMPTY_TEXTS;
    static final Image[] EMPTY_IMAGES;
    static final String ITEMID = "TableTreeItemID";
    
    static {
        EMPTY_ITEMS = new TableTreeItem[0];
        EMPTY_TEXTS = new String[0];
        EMPTY_IMAGES = new Image[0];
    }
    
    public TableTree(final Composite composite, final int n) {
        super(composite, checkStyle(n));
        this.items = TableTree.EMPTY_ITEMS;
        this.inDispose = false;
        this.table = new Table(this, n);
        final Listener listener = new Listener() {
            public void handleEvent(final Event event) {
                switch (event.type) {
                    case 3: {
                        TableTree.this.onMouseDown(event);
                        break;
                    }
                    case 13: {
                        TableTree.this.onSelection(event);
                        break;
                    }
                    case 14: {
                        TableTree.this.onSelection(event);
                        break;
                    }
                    case 1: {
                        TableTree.this.onKeyDown(event);
                        break;
                    }
                }
            }
        };
        final int[] array = { 3, 13, 14, 1 };
        for (int i = 0; i < array.length; ++i) {
            this.table.addListener(array[i], listener);
        }
        this.listener = new Listener() {
            public void handleEvent(final Event event) {
                switch (event.type) {
                    case 12: {
                        TableTree.this.onDispose(event);
                        break;
                    }
                    case 11: {
                        TableTree.this.onResize(event);
                        break;
                    }
                    case 15: {
                        TableTree.this.onFocusIn(event);
                        break;
                    }
                }
            }
        };
        final int[] array2 = { 12, 11, 15 };
        for (int j = 0; j < array2.length; ++j) {
            this.addListener(array2[j], this.listener);
        }
    }
    
    int addItem(final TableTreeItem tableTreeItem, final int n) {
        if (n < 0 || n > this.items.length) {
            SWT.error(5);
        }
        final TableTreeItem[] items = new TableTreeItem[this.items.length + 1];
        System.arraycopy(this.items, 0, items, 0, n);
        items[n] = tableTreeItem;
        System.arraycopy(this.items, n, items, n + 1, this.items.length - n);
        this.items = items;
        if (n == this.items.length - 1) {
            return this.table.getItemCount();
        }
        return this.table.indexOf(this.items[n + 1].tableItem);
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
    
    public void addTreeListener(final TreeListener treeListener) {
        this.checkWidget();
        if (treeListener == null) {
            SWT.error(4);
        }
        final TypedListener typedListener = new TypedListener(treeListener);
        this.addListener(17, typedListener);
        this.addListener(18, typedListener);
    }
    
    private static int checkStyle(int n) {
        n &= 0x6000000;
        return n;
    }
    
    public Point computeSize(final int n, final int n2, final boolean b) {
        this.checkWidget();
        return this.table.computeSize(n, n2, b);
    }
    
    public Rectangle computeTrim(final int n, final int n2, final int n3, final int n4) {
        this.checkWidget();
        return this.table.computeTrim(n, n2, n3, n4);
    }
    
    public void deselectAll() {
        this.checkWidget();
        this.table.deselectAll();
    }
    
    void expandItem(final TableTreeItem item) {
        if (item == null) {
            return;
        }
        this.expandItem(item.parentItem);
        if (!item.getVisible()) {
            item.setVisible(true);
        }
        if (!item.expanded && item.items.length > 0) {
            item.setExpanded(true);
            final Event event = new Event();
            event.item = item;
            this.notifyListeners(17, event);
        }
    }
    
    public Color getBackground() {
        return this.table.getBackground();
    }
    
    public Rectangle getClientArea() {
        return this.table.getClientArea();
    }
    
    public Color getForeground() {
        return this.table.getForeground();
    }
    
    public Font getFont() {
        return this.table.getFont();
    }
    
    public int getItemCount() {
        return this.items.length;
    }
    
    public int getItemHeight() {
        this.checkWidget();
        return this.table.getItemHeight();
    }
    
    public TableTreeItem[] getItems() {
        final TableTreeItem[] array = new TableTreeItem[this.items.length];
        System.arraycopy(this.items, 0, array, 0, this.items.length);
        return array;
    }
    
    public TableTreeItem[] getSelection() {
        this.checkWidget();
        final TableItem[] selection = this.table.getSelection();
        final TableTreeItem[] array = new TableTreeItem[selection.length];
        for (int i = 0; i < selection.length; ++i) {
            array[i] = (TableTreeItem)selection[i].getData("TableTreeItemID");
        }
        return array;
    }
    
    public int getSelectionCount() {
        this.checkWidget();
        return this.table.getSelectionCount();
    }
    
    public int getStyle() {
        this.checkWidget();
        return this.table.getStyle();
    }
    
    public Table getTable() {
        return this.table;
    }
    
    void createImages() {
        final int height = this.sizeImage.getBounds().height;
        final int max = Math.max(0, Math.min(6, (height - 9) / 2));
        final int n = (Math.max(10, height - 2 * max) + 1) / 2 * 2;
        final int n2 = max + n / 2;
        final Color foreground = this.getForeground();
        final Color systemColor = this.getDisplay().getSystemColor(18);
        final Color background = this.getBackground();
        final ImageData imageData = new ImageData(height, height, 4, new PaletteData(new RGB[] { foreground.getRGB(), background.getRGB(), systemColor.getRGB() }));
        imageData.transparentPixel = 1;
        this.plusImage = new Image(this.getDisplay(), imageData);
        final GC gc = new GC(this.plusImage);
        gc.setBackground(background);
        gc.fillRectangle(0, 0, height, height);
        gc.setForeground(systemColor);
        gc.drawRectangle(max, max, n, n);
        gc.setForeground(foreground);
        gc.drawLine(n2, max + 2, n2, max + n - 2);
        gc.drawLine(max + 2, n2, max + n - 2, n2);
        gc.dispose();
        final ImageData imageData2 = new ImageData(height, height, 4, new PaletteData(new RGB[] { foreground.getRGB(), background.getRGB(), systemColor.getRGB() }));
        imageData2.transparentPixel = 1;
        this.minusImage = new Image(this.getDisplay(), imageData2);
        final GC gc2 = new GC(this.minusImage);
        gc2.setBackground(background);
        gc2.fillRectangle(0, 0, height, height);
        gc2.setForeground(systemColor);
        gc2.drawRectangle(max, max, n, n);
        gc2.setForeground(foreground);
        gc2.drawLine(max + 2, n2, max + n - 2, n2);
        gc2.dispose();
    }
    
    Image getPlusImage() {
        if (this.plusImage == null) {
            this.createImages();
        }
        return this.plusImage;
    }
    
    Image getMinusImage() {
        if (this.minusImage == null) {
            this.createImages();
        }
        return this.minusImage;
    }
    
    public int indexOf(final TableTreeItem tableTreeItem) {
        for (int i = 0; i < this.items.length; ++i) {
            if (tableTreeItem == this.items[i]) {
                return i;
            }
        }
        return -1;
    }
    
    void onDispose(final Event event) {
        this.removeListener(12, this.listener);
        this.notifyListeners(12, event);
        event.type = 0;
        this.inDispose = true;
        for (int i = 0; i < this.items.length; ++i) {
            this.items[i].dispose();
        }
        this.inDispose = false;
        if (this.plusImage != null) {
            this.plusImage.dispose();
        }
        if (this.minusImage != null) {
            this.minusImage.dispose();
        }
        if (this.sizeImage != null) {
            this.sizeImage.dispose();
        }
        final Image plusImage = null;
        this.sizeImage = plusImage;
        this.minusImage = plusImage;
        this.plusImage = plusImage;
    }
    
    void onResize(final Event event) {
        final Point size = this.getSize();
        this.table.setBounds(0, 0, size.x, size.y);
    }
    
    void onSelection(final Event event) {
        final Event event2 = new Event();
        final TableItem tableItem = (TableItem)event.item;
        final TableTreeItem item = this.getItem(tableItem);
        event2.item = item;
        if (event.type == 13 && event.detail == 32 && item != null) {
            event2.detail = 32;
            item.checked = tableItem.getChecked();
        }
        this.notifyListeners(event.type, event2);
    }
    
    public TableTreeItem getItem(final int n) {
        this.checkWidget();
        final int length = this.items.length;
        if (n < 0 || n >= length) {
            SWT.error(6);
        }
        return this.items[n];
    }
    
    public TableTreeItem getItem(final Point point) {
        this.checkWidget();
        final TableItem item = this.table.getItem(point);
        if (item == null) {
            return null;
        }
        return this.getItem(item);
    }
    
    TableTreeItem getItem(final TableItem tableItem) {
        if (tableItem == null) {
            return null;
        }
        for (int i = 0; i < this.items.length; ++i) {
            final TableTreeItem item = this.items[i].getItem(tableItem);
            if (item != null) {
                return item;
            }
        }
        return null;
    }
    
    void onFocusIn(final Event event) {
        this.table.setFocus();
    }
    
    void onKeyDown(final Event event) {
        final TableTreeItem[] selection = this.getSelection();
        if (selection.length == 0) {
            return;
        }
        final TableTreeItem item = selection[0];
        int n = 0;
        if (event.keyCode == 16777220 || event.keyCode == 16777219) {
            if (event.keyCode == (((this.getStyle() & 0x8000000) != 0x0) ? 16777219 : 16777220)) {
                if (item.getItemCount() == 0) {
                    return;
                }
                if (item.getExpanded()) {
                    final TableTreeItem tableTreeItem = item.getItems()[0];
                    this.table.setSelection(new TableItem[] { tableTreeItem.tableItem });
                    this.showItem(tableTreeItem);
                    n = 13;
                }
                else {
                    item.setExpanded(true);
                    n = 17;
                }
            }
            else if (item.getExpanded()) {
                item.setExpanded(false);
                n = 18;
            }
            else {
                final TableTreeItem parentItem = item.getParentItem();
                if (parentItem != null) {
                    if (parentItem.indexOf(item) != 0) {
                        return;
                    }
                    this.table.setSelection(new TableItem[] { parentItem.tableItem });
                    n = 13;
                }
            }
        }
        if (event.character == '*') {
            item.expandAll(true);
        }
        if (event.character == '-' && item.getExpanded()) {
            item.setExpanded(false);
            n = 18;
        }
        if (event.character == '+' && item.getItemCount() > 0 && !item.getExpanded()) {
            item.setExpanded(true);
            n = 17;
        }
        if (n == 0) {
            return;
        }
        final Event event2 = new Event();
        event2.item = item;
        this.notifyListeners(n, event2);
    }
    
    void onMouseDown(Event event) {
        final TableItem[] items = this.table.getItems();
        for (int i = 0; i < items.length; ++i) {
            if (items[i].getImageBounds(0).contains(event.x, event.y)) {
                final TableTreeItem item = (TableTreeItem)items[i].getData("TableTreeItemID");
                event = new Event();
                ((TableTreeItem)(event.item = item)).setExpanded(!item.getExpanded());
                if (item.getExpanded()) {
                    this.notifyListeners(17, event);
                }
                else {
                    this.notifyListeners(18, event);
                }
                return;
            }
        }
    }
    
    public void removeAll() {
        this.checkWidget();
        this.setRedraw(false);
        for (int i = this.items.length - 1; i >= 0; --i) {
            this.items[i].dispose();
        }
        this.items = TableTree.EMPTY_ITEMS;
        this.setRedraw(true);
    }
    
    void removeItem(final TableTreeItem tableTreeItem) {
        int n;
        for (n = 0; n < this.items.length && this.items[n] != tableTreeItem; ++n) {}
        if (n == this.items.length) {
            return;
        }
        final TableTreeItem[] items = new TableTreeItem[this.items.length - 1];
        System.arraycopy(this.items, 0, items, 0, n);
        System.arraycopy(this.items, n + 1, items, n, this.items.length - n - 1);
        this.items = items;
    }
    
    public void removeSelectionListener(final SelectionListener selectionListener) {
        this.checkWidget();
        if (selectionListener == null) {
            SWT.error(4);
        }
        this.removeListener(13, selectionListener);
        this.removeListener(14, selectionListener);
    }
    
    public void removeTreeListener(final TreeListener treeListener) {
        this.checkWidget();
        if (treeListener == null) {
            SWT.error(4);
        }
        this.removeListener(17, treeListener);
        this.removeListener(18, treeListener);
    }
    
    public void selectAll() {
        this.checkWidget();
        this.table.selectAll();
    }
    
    public void setBackground(final Color color) {
        super.setBackground(color);
        this.table.setBackground(color);
        if (this.sizeImage != null) {
            final GC gc = new GC(this.sizeImage);
            gc.setBackground(this.getBackground());
            gc.fillRectangle(this.sizeImage.getBounds());
            gc.dispose();
        }
    }
    
    public void setEnabled(final boolean b) {
        super.setEnabled(b);
        this.table.setEnabled(b);
    }
    
    public void setFont(final Font font) {
        super.setFont(font);
        this.table.setFont(font);
    }
    
    public void setForeground(final Color color) {
        super.setForeground(color);
        this.table.setForeground(color);
    }
    
    public void setMenu(final Menu menu) {
        super.setMenu(menu);
        this.table.setMenu(menu);
    }
    
    public void setSelection(final TableTreeItem[] array) {
        this.checkWidget();
        if (array == null) {
            SWT.error(4);
        }
        final int length = array.length;
        if (length == 0 || ((this.table.getStyle() & 0x4) != 0x0 && length > 1)) {
            this.deselectAll();
            return;
        }
        final TableItem[] selection = new TableItem[length];
        for (int i = 0; i < length; ++i) {
            if (array[i] == null) {
                SWT.error(4);
            }
            if (!array[i].getVisible()) {
                this.expandItem(array[i]);
            }
            selection[i] = array[i].tableItem;
        }
        this.table.setSelection(selection);
    }
    
    public void setToolTipText(final String s) {
        super.setToolTipText(s);
        this.table.setToolTipText(s);
    }
    
    public void showItem(final TableTreeItem tableTreeItem) {
        this.checkWidget();
        if (tableTreeItem == null) {
            SWT.error(4);
        }
        if (!tableTreeItem.getVisible()) {
            this.expandItem(tableTreeItem);
        }
        this.table.showItem(tableTreeItem.tableItem);
    }
    
    public void showSelection() {
        this.checkWidget();
        this.table.showSelection();
    }
}
