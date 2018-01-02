// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.custom;

import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Drawable;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Item;

public class TableTreeItem extends Item
{
    TableItem tableItem;
    TableTree parent;
    TableTreeItem parentItem;
    TableTreeItem[] items;
    String[] texts;
    Image[] images;
    Color background;
    Color foreground;
    Font font;
    boolean expanded;
    boolean checked;
    boolean grayed;
    
    public TableTreeItem(final TableTree tableTree, final int n) {
        this(tableTree, n, tableTree.getItemCount());
    }
    
    public TableTreeItem(final TableTree tableTree, final int n, final int n2) {
        this(tableTree, null, n, n2);
    }
    
    public TableTreeItem(final TableTreeItem tableTreeItem, final int n) {
        this(tableTreeItem, n, tableTreeItem.getItemCount());
    }
    
    public TableTreeItem(final TableTreeItem tableTreeItem, final int n, final int n2) {
        this(tableTreeItem.getParent(), tableTreeItem, n, n2);
    }
    
    TableTreeItem(final TableTree parent, final TableTreeItem parentItem, final int n, final int n2) {
        super(parent, n);
        this.items = TableTree.EMPTY_ITEMS;
        this.texts = TableTree.EMPTY_TEXTS;
        this.images = TableTree.EMPTY_IMAGES;
        this.parent = parent;
        this.parentItem = parentItem;
        if (parentItem == null) {
            (this.tableItem = new TableItem(parent.getTable(), n, parent.addItem(this, n2))).setData("TableTreeItemID", this);
            this.addCheck();
            if (parent.sizeImage == null) {
                final int itemHeight = parent.getItemHeight();
                parent.sizeImage = new Image(parent.getDisplay(), itemHeight, itemHeight);
                final GC gc = new GC(parent.sizeImage);
                gc.setBackground(parent.getBackground());
                gc.fillRectangle(0, 0, itemHeight, itemHeight);
                gc.dispose();
                this.tableItem.setImage(0, parent.sizeImage);
            }
        }
        else {
            parentItem.addItem(this, n2);
        }
    }
    
    void addCheck() {
        if ((this.parent.getTable().getStyle() & 0x20) == 0x0) {
            return;
        }
        this.tableItem.setChecked(this.checked);
        this.tableItem.setGrayed(this.grayed);
    }
    
    void addItem(final TableTreeItem tableTreeItem, final int n) {
        if (tableTreeItem == null) {
            SWT.error(4);
        }
        if (n < 0 || n > this.items.length) {
            SWT.error(5);
        }
        if (this.items.length == 0 && n == 0 && this.tableItem != null) {
            this.tableItem.setImage(0, this.expanded ? this.parent.getMinusImage() : this.parent.getPlusImage());
        }
        final TableTreeItem[] items = new TableTreeItem[this.items.length + 1];
        System.arraycopy(this.items, 0, items, 0, n);
        items[n] = tableTreeItem;
        System.arraycopy(this.items, n, items, n + 1, this.items.length - n);
        this.items = items;
        if (this.expanded) {
            tableTreeItem.setVisible(true);
        }
    }
    
    public Color getBackground() {
        this.checkWidget();
        return (this.background == null) ? this.parent.getBackground() : this.background;
    }
    
    public Rectangle getBounds(final int n) {
        this.checkWidget();
        if (this.tableItem != null) {
            return this.tableItem.getBounds(n);
        }
        return new Rectangle(0, 0, 0, 0);
    }
    
    public boolean getChecked() {
        this.checkWidget();
        if (this.tableItem == null) {
            return this.checked;
        }
        return this.tableItem.getChecked();
    }
    
    public boolean getGrayed() {
        this.checkWidget();
        if (this.tableItem == null) {
            return this.grayed;
        }
        return this.tableItem.getGrayed();
    }
    
    public boolean getExpanded() {
        return this.expanded;
    }
    
    public Font getFont() {
        this.checkWidget();
        return (this.font == null) ? this.parent.getFont() : this.font;
    }
    
    public Color getForeground() {
        this.checkWidget();
        return (this.foreground == null) ? this.parent.getForeground() : this.foreground;
    }
    
    public Image getImage() {
        this.checkWidget();
        return this.getImage(0);
    }
    
    public Image getImage(final int n) {
        if (n > 0 && n < this.images.length) {
            return this.images[n];
        }
        return null;
    }
    
    int getIndent() {
        if (this.parentItem == null) {
            return 0;
        }
        return this.parentItem.getIndent() + 1;
    }
    
    public TableTreeItem getItem(final int n) {
        this.checkWidget();
        final int length = this.items.length;
        if (n < 0 || n >= length) {
            SWT.error(6);
        }
        return this.items[n];
    }
    
    public int getItemCount() {
        return this.items.length;
    }
    
    public TableTreeItem[] getItems() {
        final TableTreeItem[] array = new TableTreeItem[this.items.length];
        System.arraycopy(this.items, 0, array, 0, this.items.length);
        return array;
    }
    
    TableTreeItem getItem(final TableItem tableItem) {
        if (tableItem == null) {
            return null;
        }
        if (this.tableItem == tableItem) {
            return this;
        }
        for (int i = 0; i < this.items.length; ++i) {
            final TableTreeItem item = this.items[i].getItem(tableItem);
            if (item != null) {
                return item;
            }
        }
        return null;
    }
    
    public TableTree getParent() {
        return this.parent;
    }
    
    public TableTreeItem getParentItem() {
        return this.parentItem;
    }
    
    public String getText() {
        this.checkWidget();
        return this.getText(0);
    }
    
    public String getText(final int n) {
        if (n >= 0 && n < this.texts.length) {
            return this.texts[n];
        }
        return null;
    }
    
    boolean getVisible() {
        return this.tableItem != null;
    }
    
    public int indexOf(final TableTreeItem tableTreeItem) {
        for (int i = 0; i < this.items.length; ++i) {
            if (this.items[i] == tableTreeItem) {
                return i;
            }
        }
        return -1;
    }
    
    void expandAll(final boolean b) {
        if (this.items.length == 0) {
            return;
        }
        if (!this.expanded) {
            this.setExpanded(true);
            if (b) {
                final Event event = new Event();
                event.item = this;
                this.parent.notifyListeners(17, event);
            }
        }
        for (int i = 0; i < this.items.length; ++i) {
            this.items[i].expandAll(b);
        }
    }
    
    int expandedIndexOf(final TableTreeItem tableTreeItem) {
        int n = 0;
        for (int i = 0; i < this.items.length; ++i) {
            if (this.items[i] == tableTreeItem) {
                return n;
            }
            if (this.items[i].expanded) {
                n += this.items[i].visibleChildrenCount();
            }
            ++n;
        }
        return -1;
    }
    
    int visibleChildrenCount() {
        int n = 0;
        for (int i = 0; i < this.items.length; ++i) {
            if (this.items[i].getVisible()) {
                n += 1 + this.items[i].visibleChildrenCount();
            }
        }
        return n;
    }
    
    public void dispose() {
        if (this.isDisposed()) {
            return;
        }
        for (int i = this.items.length - 1; i >= 0; --i) {
            this.items[i].dispose();
        }
        super.dispose();
        if (!this.parent.inDispose) {
            if (this.parentItem != null) {
                this.parentItem.removeItem(this);
            }
            else {
                this.parent.removeItem(this);
            }
            if (this.tableItem != null) {
                this.tableItem.dispose();
            }
        }
        this.items = null;
        this.parentItem = null;
        this.parent = null;
        this.images = null;
        this.texts = null;
        this.tableItem = null;
        this.foreground = null;
        this.background = null;
        this.font = null;
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
        if (this.items.length == 0 && this.tableItem != null) {
            this.tableItem.setImage(0, null);
        }
    }
    
    public void setBackground(final Color color) {
        this.checkWidget();
        if (color != null && color.isDisposed()) {
            SWT.error(5);
        }
        if (this.tableItem != null) {
            this.tableItem.setBackground(color);
        }
        this.background = color;
    }
    
    public void setChecked(final boolean b) {
        this.checkWidget();
        if ((this.parent.getTable().getStyle() & 0x20) == 0x0) {
            return;
        }
        if (this.tableItem != null) {
            this.tableItem.setChecked(b);
        }
        this.checked = b;
    }
    
    public void setGrayed(final boolean b) {
        this.checkWidget();
        if ((this.parent.getTable().getStyle() & 0x20) == 0x0) {
            return;
        }
        if (this.tableItem != null) {
            this.tableItem.setGrayed(b);
        }
        this.grayed = b;
    }
    
    public void setExpanded(final boolean b) {
        this.checkWidget();
        if (this.items.length == 0) {
            return;
        }
        if (this.expanded == b) {
            return;
        }
        this.expanded = b;
        if (this.tableItem == null) {
            return;
        }
        this.parent.setRedraw(false);
        for (int i = 0; i < this.items.length; ++i) {
            this.items[i].setVisible(b);
        }
        this.tableItem.setImage(0, b ? this.parent.getMinusImage() : this.parent.getPlusImage());
        this.parent.setRedraw(true);
    }
    
    public void setFont(final Font font) {
        this.checkWidget();
        if (font != null && font.isDisposed()) {
            SWT.error(5);
        }
        if (this.tableItem != null) {
            this.tableItem.setFont(font);
        }
        this.font = font;
    }
    
    public void setForeground(final Color color) {
        this.checkWidget();
        if (color != null && color.isDisposed()) {
            SWT.error(5);
        }
        if (this.tableItem != null) {
            this.tableItem.setForeground(color);
        }
        this.foreground = color;
    }
    
    public void setImage(final int n, final Image image) {
        this.checkWidget();
        final int max = Math.max(this.parent.getTable().getColumnCount(), 1);
        if (n <= 0 || n >= max) {
            return;
        }
        if (this.images.length < max) {
            final Image[] images = new Image[max];
            System.arraycopy(this.images, 0, images, 0, this.images.length);
            this.images = images;
        }
        this.images[n] = image;
        if (this.tableItem != null) {
            this.tableItem.setImage(n, image);
        }
    }
    
    public void setImage(final Image image) {
        this.setImage(0, image);
    }
    
    public void setText(final int n, final String s) {
        this.checkWidget();
        if (s == null) {
            SWT.error(4);
        }
        final int max = Math.max(this.parent.getTable().getColumnCount(), 1);
        if (n < 0 || n >= max) {
            return;
        }
        if (this.texts.length < max) {
            final String[] texts = new String[max];
            System.arraycopy(this.texts, 0, texts, 0, this.texts.length);
            this.texts = texts;
        }
        this.texts[n] = s;
        if (this.tableItem != null) {
            this.tableItem.setText(n, s);
        }
    }
    
    public void setText(final String s) {
        this.setText(0, s);
    }
    
    void setVisible(final boolean b) {
        if (this.parentItem == null) {
            return;
        }
        if (this.getVisible() == b) {
            return;
        }
        if (b) {
            if (!this.parentItem.getVisible()) {
                return;
            }
            final Table table = this.parent.getTable();
            final int n = this.parentItem.expandedIndexOf(this) + table.indexOf(this.parentItem.tableItem) + 1;
            if (n < 0) {
                return;
            }
            (this.tableItem = new TableItem(table, this.getStyle(), n)).setData("TableTreeItemID", this);
            this.tableItem.setImageIndent(this.getIndent());
            if (this.background != null) {
                this.tableItem.setBackground(this.background);
            }
            if (this.foreground != null) {
                this.tableItem.setForeground(this.foreground);
            }
            if (this.font != null) {
                this.tableItem.setFont(this.font);
            }
            this.addCheck();
            for (int max = Math.max(table.getColumnCount(), 1), i = 0; i < max; ++i) {
                if (i < this.texts.length && this.texts[i] != null) {
                    this.setText(i, this.texts[i]);
                }
                if (i < this.images.length && this.images[i] != null) {
                    this.setImage(i, this.images[i]);
                }
            }
            if (this.items.length != 0) {
                if (this.expanded) {
                    this.tableItem.setImage(0, this.parent.getMinusImage());
                    for (int j = 0; j < this.items.length; ++j) {
                        this.items[j].setVisible(true);
                    }
                }
                else {
                    this.tableItem.setImage(0, this.parent.getPlusImage());
                }
            }
        }
        else {
            for (int k = 0; k < this.items.length; ++k) {
                this.items[k].setVisible(false);
            }
            this.tableItem.dispose();
            this.tableItem = null;
        }
    }
}
