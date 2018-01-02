// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.widgets;

import org.eclipse.swt.internal.win32.LVITEM;
import org.eclipse.swt.internal.win32.TCHAR;
import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.internal.win32.RECT;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;

public class TableItem extends Item
{
    Table parent;
    String[] strings;
    Image[] images;
    Font font;
    Font[] cellFont;
    boolean checked;
    boolean grayed;
    boolean cached;
    int imageIndent;
    int background;
    int foreground;
    int[] cellBackground;
    int[] cellForeground;
    
    public TableItem(final Table table, final int n) {
        this(table, n, checkNull(table).getItemCount(), true);
    }
    
    public TableItem(final Table table, final int n, final int n2) {
        this(table, n, n2, true);
    }
    
    TableItem(final Table parent, final int n, final int n2, final boolean b) {
        super(parent, n);
        this.background = -1;
        this.foreground = -1;
        this.parent = parent;
        if (b) {
            parent.createItem(this, n2);
        }
    }
    
    static Table checkNull(final Table table) {
        if (table == null) {
            SWT.error(4);
        }
        return table;
    }
    
    protected void checkSubclass() {
        if (!this.isValidSubclass()) {
            this.error(43);
        }
    }
    
    void clear() {
        this.text = "";
        this.image = null;
        this.strings = null;
        this.images = null;
        this.imageIndent = 0;
        final boolean b = false;
        this.grayed = b;
        this.checked = b;
        this.font = null;
        final int n = -1;
        this.foreground = n;
        this.background = n;
        this.cellFont = null;
        final int[] array = null;
        this.cellForeground = array;
        this.cellBackground = array;
        if ((this.parent.style & 0x10000000) != 0x0) {
            this.cached = false;
        }
    }
    
    void destroyWidget() {
        this.parent.destroyItem(this);
        this.releaseHandle();
    }
    
    int fontHandle(final int n) {
        if (this.cellFont != null && this.cellFont[n] != null) {
            return this.cellFont[n].handle;
        }
        if (this.font != null) {
            return this.font.handle;
        }
        return -1;
    }
    
    public Color getBackground() {
        this.checkWidget();
        if (!this.parent.checkData(this, true)) {
            this.error(24);
        }
        if (this.background == -1) {
            return this.parent.getBackground();
        }
        return Color.win32_new(this.display, this.background);
    }
    
    public Color getBackground(final int n) {
        this.checkWidget();
        if (!this.parent.checkData(this, true)) {
            this.error(24);
        }
        final int max = Math.max(1, this.parent.getColumnCount());
        if (n < 0 || n > max - 1) {
            return this.getBackground();
        }
        final int n2 = (this.cellBackground != null) ? this.cellBackground[n] : -1;
        return (n2 == -1) ? this.getBackground() : Color.win32_new(this.display, n2);
    }
    
    public Rectangle getBounds() {
        this.checkWidget();
        if (!this.parent.checkData(this, true)) {
            this.error(24);
        }
        final int index = this.parent.indexOf(this);
        if (index == -1) {
            return new Rectangle(0, 0, 0, 0);
        }
        final RECT bounds = this.getBounds(index, 0, true, false, false);
        return new Rectangle(bounds.left, bounds.top, bounds.right - bounds.left, bounds.bottom - bounds.top);
    }
    
    public Rectangle getBounds(final int n) {
        this.checkWidget();
        if (!this.parent.checkData(this, true)) {
            this.error(24);
        }
        final int index = this.parent.indexOf(this);
        if (index == -1) {
            return new Rectangle(0, 0, 0, 0);
        }
        final RECT bounds = this.getBounds(index, n, true, true, true);
        return new Rectangle(bounds.left, bounds.top, bounds.right - bounds.left, bounds.bottom - bounds.top);
    }
    
    RECT getBounds(final int n, final int n2, final boolean b, final boolean b2, final boolean b3) {
        return this.getBounds(n, n2, b, b2, b3, false, 0);
    }
    
    RECT getBounds(final int n, final int n2, final boolean b, final boolean b2, final boolean b3, final boolean b4, final int n3) {
        if (!b && !b2) {
            return new RECT();
        }
        final int columnCount = this.parent.getColumnCount();
        if (n2 < 0 || n2 >= Math.max(1, columnCount)) {
            return new RECT();
        }
        if (this.parent.fixScrollWidth) {
            this.parent.setScrollWidth(null, true);
        }
        final RECT rect = new RECT();
        final int handle = this.parent.handle;
        final int sendMessage = OS.SendMessage(handle, 4151, 0, 0);
        if (n2 == 0 && (sendMessage & 0x20) == 0x0) {
            if (this.parent.explorerTheme) {
                rect.left = 1;
                this.parent.ignoreCustomDraw = true;
                final int sendMessage2 = OS.SendMessage(handle, 4110, n, rect);
                this.parent.ignoreCustomDraw = false;
                if (sendMessage2 == 0) {
                    return new RECT();
                }
                if (b) {
                    int n4 = this.fontHandle(n2);
                    int sendMessage3;
                    if (n4 == -1 && n3 == 0) {
                        sendMessage3 = OS.SendMessage(handle, OS.LVM_GETSTRINGWIDTH, 0, new TCHAR(this.parent.getCodePage(), this.text, true));
                    }
                    else {
                        final TCHAR tchar = new TCHAR(this.parent.getCodePage(), this.text, false);
                        final int n5 = (n3 != 0) ? n3 : OS.GetDC(handle);
                        int selectObject = -1;
                        if (n3 == 0) {
                            if (n4 == -1) {
                                n4 = OS.SendMessage(handle, 49, 0, 0);
                            }
                            selectObject = OS.SelectObject(n5, n4);
                        }
                        final RECT rect2 = new RECT();
                        OS.DrawText(n5, tchar, tchar.length(), rect2, 3104);
                        sendMessage3 = rect2.right - rect2.left;
                        if (n3 == 0) {
                            if (selectObject != -1) {
                                OS.SelectObject(n5, selectObject);
                            }
                            OS.ReleaseDC(handle, n5);
                        }
                    }
                    if (!b2) {
                        rect.left = rect.right;
                    }
                    final RECT rect3 = rect;
                    rect3.right += sendMessage3 + 8;
                }
            }
            else if (b) {
                rect.left = 3;
                this.parent.ignoreCustomDraw = true;
                final int sendMessage4 = OS.SendMessage(handle, 4110, n, rect);
                this.parent.ignoreCustomDraw = false;
                if (sendMessage4 == 0) {
                    return new RECT();
                }
                if (!b2) {
                    final RECT rect4 = new RECT();
                    rect4.left = 1;
                    this.parent.ignoreCustomDraw = true;
                    final int sendMessage5 = OS.SendMessage(handle, 4110, n, rect4);
                    this.parent.ignoreCustomDraw = false;
                    if (sendMessage5 != 0) {
                        rect.left = rect4.right;
                    }
                }
            }
            else {
                rect.left = 1;
                this.parent.ignoreCustomDraw = true;
                final int sendMessage6 = OS.SendMessage(handle, 4110, n, rect);
                this.parent.ignoreCustomDraw = false;
                if (sendMessage6 == 0) {
                    return new RECT();
                }
            }
            if (b3 || b4) {
                final RECT rect5 = new RECT();
                final int sendMessage7 = OS.SendMessage(handle, 4127, 0, 0);
                OS.SendMessage(sendMessage7, 4615, 0, rect5);
                OS.MapWindowPoints(sendMessage7, handle, rect5, 2);
                if (b && b3) {
                    rect.right = rect5.right;
                }
                if (b2 && b4) {
                    rect.left = rect5.left;
                }
            }
        }
        else {
            final boolean b5 = (n2 == 0 && this.image != null) || (this.images != null && this.images[n2] != null);
            rect.top = n2;
            if (b3 || b4 || n3 == 0) {
                rect.left = (b ? 2 : 1);
                this.parent.ignoreCustomDraw = true;
                final int sendMessage8 = OS.SendMessage(handle, 4152, n, rect);
                this.parent.ignoreCustomDraw = false;
                if (sendMessage8 == 0) {
                    return new RECT();
                }
                if (n2 == 0 && b && b2) {
                    final RECT rect6 = new RECT();
                    rect6.left = 1;
                    this.parent.ignoreCustomDraw = true;
                    final int sendMessage9 = OS.SendMessage(handle, 4152, n, rect6);
                    this.parent.ignoreCustomDraw = false;
                    if (sendMessage9 != 0) {
                        rect.left = rect6.left;
                    }
                }
                if (b5) {
                    if (n2 != 0 && b && !b2) {
                        final RECT rect7 = new RECT();
                        rect7.top = n2;
                        rect7.left = 1;
                        if (OS.SendMessage(handle, 4152, n, rect7) != 0) {
                            rect.left = rect7.right + 2;
                        }
                    }
                }
                else if (b2 && !b) {
                    rect.right = rect.left;
                }
                if (n2 == 0 && b4) {
                    final RECT rect8 = new RECT();
                    final int sendMessage10 = OS.SendMessage(handle, 4127, 0, 0);
                    OS.SendMessage(sendMessage10, 4615, 0, rect8);
                    OS.MapWindowPoints(sendMessage10, handle, rect8, 2);
                    rect.left = rect8.left;
                }
            }
            else {
                rect.left = 1;
                this.parent.ignoreCustomDraw = true;
                final int sendMessage11 = OS.SendMessage(handle, 4152, n, rect);
                this.parent.ignoreCustomDraw = false;
                if (sendMessage11 == 0) {
                    return new RECT();
                }
                if (!b5) {
                    rect.right = rect.left;
                }
                if (b) {
                    final String s = (n2 == 0) ? this.text : ((this.strings != null) ? this.strings[n2] : null);
                    if (s != null) {
                        final RECT rect9 = new RECT();
                        final TCHAR tchar2 = new TCHAR(this.parent.getCodePage(), s, false);
                        OS.DrawText(n3, tchar2, tchar2.length(), rect9, 3104);
                        final RECT rect10 = rect;
                        rect10.right += rect9.right - rect9.left + 12 + 1;
                    }
                }
            }
        }
        final int linesVisible = this.parent.getLinesVisible() ? 1 : 0;
        if (OS.COMCTL32_VERSION >= OS.VERSION(5, 80)) {
            final RECT rect11 = rect;
            rect11.top -= linesVisible;
        }
        if (n2 != 0) {
            final RECT rect12 = rect;
            rect12.left += linesVisible;
        }
        rect.right = Math.max(rect.right, rect.left);
        final RECT rect13 = rect;
        rect13.top += linesVisible;
        rect.bottom = Math.max(rect.bottom - linesVisible, rect.top);
        return rect;
    }
    
    public boolean getChecked() {
        this.checkWidget();
        if (!this.parent.checkData(this, true)) {
            this.error(24);
        }
        return (this.parent.style & 0x20) != 0x0 && this.checked;
    }
    
    public Font getFont() {
        this.checkWidget();
        if (!this.parent.checkData(this, true)) {
            this.error(24);
        }
        return (this.font != null) ? this.font : this.parent.getFont();
    }
    
    public Font getFont(final int n) {
        this.checkWidget();
        if (!this.parent.checkData(this, true)) {
            this.error(24);
        }
        final int max = Math.max(1, this.parent.getColumnCount());
        if (n < 0 || n > max - 1) {
            return this.getFont();
        }
        if (this.cellFont == null || this.cellFont[n] == null) {
            return this.getFont();
        }
        return this.cellFont[n];
    }
    
    public Color getForeground() {
        this.checkWidget();
        if (!this.parent.checkData(this, true)) {
            this.error(24);
        }
        if (this.foreground == -1) {
            return this.parent.getForeground();
        }
        return Color.win32_new(this.display, this.foreground);
    }
    
    public Color getForeground(final int n) {
        this.checkWidget();
        if (!this.parent.checkData(this, true)) {
            this.error(24);
        }
        final int max = Math.max(1, this.parent.getColumnCount());
        if (n < 0 || n > max - 1) {
            return this.getForeground();
        }
        final int n2 = (this.cellForeground != null) ? this.cellForeground[n] : -1;
        return (n2 == -1) ? this.getForeground() : Color.win32_new(this.display, n2);
    }
    
    public boolean getGrayed() {
        this.checkWidget();
        if (!this.parent.checkData(this, true)) {
            this.error(24);
        }
        return (this.parent.style & 0x20) != 0x0 && this.grayed;
    }
    
    public Image getImage() {
        this.checkWidget();
        if (!this.parent.checkData(this, true)) {
            this.error(24);
        }
        return super.getImage();
    }
    
    public Image getImage(final int n) {
        this.checkWidget();
        if (!this.parent.checkData(this, true)) {
            this.error(24);
        }
        if (n == 0) {
            return this.getImage();
        }
        if (this.images != null && n >= 0 && n < this.images.length) {
            return this.images[n];
        }
        return null;
    }
    
    public Rectangle getImageBounds(final int n) {
        this.checkWidget();
        if (!this.parent.checkData(this, true)) {
            this.error(24);
        }
        final int index = this.parent.indexOf(this);
        if (index == -1) {
            return new Rectangle(0, 0, 0, 0);
        }
        final RECT bounds = this.getBounds(index, n, false, true, false);
        return new Rectangle(bounds.left, bounds.top, bounds.right - bounds.left, bounds.bottom - bounds.top);
    }
    
    public int getImageIndent() {
        this.checkWidget();
        if (!this.parent.checkData(this, true)) {
            this.error(24);
        }
        return this.imageIndent;
    }
    
    String getNameText() {
        if ((this.parent.style & 0x10000000) != 0x0 && !this.cached) {
            return "*virtual*";
        }
        return super.getNameText();
    }
    
    public Table getParent() {
        this.checkWidget();
        return this.parent;
    }
    
    public String getText() {
        this.checkWidget();
        if (!this.parent.checkData(this, true)) {
            this.error(24);
        }
        return super.getText();
    }
    
    public String getText(final int n) {
        this.checkWidget();
        if (!this.parent.checkData(this, true)) {
            this.error(24);
        }
        if (n == 0) {
            return this.getText();
        }
        if (this.strings != null && n >= 0 && n < this.strings.length) {
            final String s = this.strings[n];
            return (s != null) ? s : "";
        }
        return "";
    }
    
    public Rectangle getTextBounds(final int n) {
        this.checkWidget();
        if (!this.parent.checkData(this, true)) {
            this.error(24);
        }
        final int index = this.parent.indexOf(this);
        if (index == -1) {
            return new Rectangle(0, 0, 0, 0);
        }
        final RECT bounds;
        final RECT rect = bounds = this.getBounds(index, n, 1 != 0, 0 != 0, 1 != 0);
        bounds.left += 2;
        if (n != 0) {
            final RECT rect2 = rect;
            rect2.left += 4;
        }
        rect.left = Math.min(rect.left, rect.right);
        rect.right -= 4;
        return new Rectangle(rect.left, rect.top, Math.max(0, rect.right - rect.left), Math.max(0, rect.bottom - rect.top));
    }
    
    void redraw() {
        if (this.parent.currentItem == this || !this.parent.getDrawing()) {
            return;
        }
        final int handle = this.parent.handle;
        if (!OS.IsWindowVisible(handle)) {
            return;
        }
        final int index = this.parent.indexOf(this);
        if (index == -1) {
            return;
        }
        OS.SendMessage(handle, 4117, index, index);
    }
    
    void redraw(final int n, final boolean b, final boolean b2) {
        if (this.parent.currentItem == this || !this.parent.getDrawing()) {
            return;
        }
        final int handle = this.parent.handle;
        if (!OS.IsWindowVisible(handle)) {
            return;
        }
        final int index = this.parent.indexOf(this);
        if (index == -1) {
            return;
        }
        OS.InvalidateRect(handle, this.getBounds(index, n, b, b2, true), true);
    }
    
    void releaseHandle() {
        super.releaseHandle();
        this.parent = null;
    }
    
    void releaseWidget() {
        super.releaseWidget();
        this.strings = null;
        this.images = null;
        this.cellFont = null;
        final int[] array = null;
        this.cellForeground = array;
        this.cellBackground = array;
    }
    
    public void setBackground(final Color color) {
        this.checkWidget();
        if (color != null && color.isDisposed()) {
            SWT.error(5);
        }
        int handle = -1;
        if (color != null) {
            this.parent.setCustomDraw(true);
            handle = color.handle;
        }
        if (this.background == handle) {
            return;
        }
        this.background = handle;
        if ((this.parent.style & 0x10000000) != 0x0) {
            this.cached = true;
        }
        this.redraw();
    }
    
    public void setBackground(final int n, final Color color) {
        this.checkWidget();
        if (color != null && color.isDisposed()) {
            SWT.error(5);
        }
        final int max = Math.max(1, this.parent.getColumnCount());
        if (n < 0 || n > max - 1) {
            return;
        }
        int handle = -1;
        if (color != null) {
            this.parent.setCustomDraw(true);
            handle = color.handle;
        }
        if (this.cellBackground == null) {
            this.cellBackground = new int[max];
            for (int i = 0; i < max; ++i) {
                this.cellBackground[i] = -1;
            }
        }
        if (this.cellBackground[n] == handle) {
            return;
        }
        this.cellBackground[n] = handle;
        if ((this.parent.style & 0x10000000) != 0x0) {
            this.cached = true;
        }
        this.redraw(n, true, true);
    }
    
    public void setChecked(final boolean b) {
        this.checkWidget();
        if ((this.parent.style & 0x20) == 0x0) {
            return;
        }
        if (this.checked == b) {
            return;
        }
        this.setChecked(b, false);
    }
    
    void setChecked(final boolean checked, final boolean b) {
        this.checked = checked;
        if ((this.parent.style & 0x10000000) != 0x0) {
            this.cached = true;
        }
        if (b) {
            final Event event = new Event();
            event.item = this;
            event.detail = 32;
            this.parent.sendSelectionEvent(13, event, false);
        }
        this.redraw();
    }
    
    public void setFont(final Font font) {
        this.checkWidget();
        if (font != null && font.isDisposed()) {
            SWT.error(5);
        }
        final Font font2 = this.font;
        if (font2 == font) {
            return;
        }
        this.font = font;
        if (font2 != null && font2.equals(font)) {
            return;
        }
        if (font != null) {
            this.parent.setCustomDraw(true);
        }
        if ((this.parent.style & 0x10000000) != 0x0) {
            this.cached = true;
        }
        if ((this.parent.style & 0x10000000) == 0x0 && this.cached) {
            final int index = this.parent.indexOf(this);
            if (index != -1) {
                final int handle = this.parent.handle;
                final LVITEM lvitem = new LVITEM();
                lvitem.mask = 1;
                lvitem.iItem = index;
                lvitem.pszText = -1;
                OS.SendMessage(handle, OS.LVM_SETITEM, 0, lvitem);
                this.cached = false;
            }
        }
        this.parent.setScrollWidth(this, false);
        this.redraw();
    }
    
    public void setFont(final int n, final Font font) {
        this.checkWidget();
        if (font != null && font.isDisposed()) {
            SWT.error(5);
        }
        final int max = Math.max(1, this.parent.getColumnCount());
        if (n < 0 || n > max - 1) {
            return;
        }
        if (this.cellFont == null) {
            if (font == null) {
                return;
            }
            this.cellFont = new Font[max];
        }
        final Font font2 = this.cellFont[n];
        if (font2 == font) {
            return;
        }
        this.cellFont[n] = font;
        if (font2 != null && font2.equals(font)) {
            return;
        }
        if (font != null) {
            this.parent.setCustomDraw(true);
        }
        if ((this.parent.style & 0x10000000) != 0x0) {
            this.cached = true;
        }
        if (n == 0) {
            if ((this.parent.style & 0x10000000) == 0x0 && this.cached) {
                final int index = this.parent.indexOf(this);
                if (index != -1) {
                    final int handle = this.parent.handle;
                    final LVITEM lvitem = new LVITEM();
                    lvitem.mask = 1;
                    lvitem.iItem = index;
                    lvitem.pszText = -1;
                    OS.SendMessage(handle, OS.LVM_SETITEM, 0, lvitem);
                    this.cached = false;
                }
            }
            this.parent.setScrollWidth(this, false);
        }
        this.redraw(n, true, false);
    }
    
    public void setForeground(final Color color) {
        this.checkWidget();
        if (color != null && color.isDisposed()) {
            SWT.error(5);
        }
        int handle = -1;
        if (color != null) {
            this.parent.setCustomDraw(true);
            handle = color.handle;
        }
        if (this.foreground == handle) {
            return;
        }
        this.foreground = handle;
        if ((this.parent.style & 0x10000000) != 0x0) {
            this.cached = true;
        }
        this.redraw();
    }
    
    public void setForeground(final int n, final Color color) {
        this.checkWidget();
        if (color != null && color.isDisposed()) {
            SWT.error(5);
        }
        final int max = Math.max(1, this.parent.getColumnCount());
        if (n < 0 || n > max - 1) {
            return;
        }
        int handle = -1;
        if (color != null) {
            this.parent.setCustomDraw(true);
            handle = color.handle;
        }
        if (this.cellForeground == null) {
            this.cellForeground = new int[max];
            for (int i = 0; i < max; ++i) {
                this.cellForeground[i] = -1;
            }
        }
        if (this.cellForeground[n] == handle) {
            return;
        }
        this.cellForeground[n] = handle;
        if ((this.parent.style & 0x10000000) != 0x0) {
            this.cached = true;
        }
        this.redraw(n, true, false);
    }
    
    public void setGrayed(final boolean grayed) {
        this.checkWidget();
        if ((this.parent.style & 0x20) == 0x0) {
            return;
        }
        if (this.grayed == grayed) {
            return;
        }
        this.grayed = grayed;
        if ((this.parent.style & 0x10000000) != 0x0) {
            this.cached = true;
        }
        this.redraw();
    }
    
    public void setImage(final Image[] array) {
        this.checkWidget();
        if (array == null) {
            this.error(4);
        }
        for (int i = 0; i < array.length; ++i) {
            this.setImage(i, array[i]);
        }
    }
    
    public void setImage(final int n, final Image image) {
        this.checkWidget();
        if (image != null && image.isDisposed()) {
            this.error(5);
        }
        Image image2 = null;
        if (n == 0) {
            if (image != null && image.type == 1 && image.equals(this.image)) {
                return;
            }
            image2 = this.image;
            super.setImage(image);
        }
        final int max = Math.max(1, this.parent.getColumnCount());
        if (n < 0 || n > max - 1) {
            return;
        }
        if (this.images == null && n != 0) {
            (this.images = new Image[max])[0] = image;
        }
        if (this.images != null) {
            if (image != null && image.type == 1 && image.equals(this.images[n])) {
                return;
            }
            image2 = this.images[n];
            this.images[n] = image;
        }
        if ((this.parent.style & 0x10000000) != 0x0) {
            this.cached = true;
        }
        this.parent.imageIndex(image, n);
        if (n == 0) {
            this.parent.setScrollWidth(this, false);
        }
        this.redraw(n, (image == null && image2 != null) || (image != null && image2 == null), true);
    }
    
    public void setImage(final Image image) {
        this.checkWidget();
        this.setImage(0, image);
    }
    
    public void setImageIndent(final int n) {
        this.checkWidget();
        if (n < 0) {
            return;
        }
        if (this.imageIndent == n) {
            return;
        }
        this.imageIndent = n;
        if ((this.parent.style & 0x10000000) != 0x0) {
            this.cached = true;
        }
        else {
            final int index = this.parent.indexOf(this);
            if (index != -1) {
                final int handle = this.parent.handle;
                final LVITEM lvitem = new LVITEM();
                lvitem.mask = 16;
                lvitem.iItem = index;
                lvitem.iIndent = n;
                OS.SendMessage(handle, OS.LVM_SETITEM, 0, lvitem);
            }
        }
        this.parent.setScrollWidth(this, false);
        this.redraw();
    }
    
    public void setText(final String[] array) {
        this.checkWidget();
        if (array == null) {
            this.error(4);
        }
        for (int i = 0; i < array.length; ++i) {
            final String s = array[i];
            if (s != null) {
                this.setText(i, s);
            }
        }
    }
    
    public void setText(final int n, final String text) {
        this.checkWidget();
        if (text == null) {
            this.error(4);
        }
        if (n == 0) {
            if (text.equals(this.text)) {
                return;
            }
            super.setText(text);
        }
        final int max = Math.max(1, this.parent.getColumnCount());
        if (n < 0 || n > max - 1) {
            return;
        }
        if (this.strings == null && n != 0) {
            (this.strings = new String[max])[0] = this.text;
        }
        if (this.strings != null) {
            if (text.equals(this.strings[n])) {
                return;
            }
            this.strings[n] = text;
        }
        if ((this.parent.style & 0x10000000) != 0x0) {
            this.cached = true;
        }
        if (n == 0) {
            if ((this.parent.style & 0x10000000) == 0x0 && this.cached) {
                final int index = this.parent.indexOf(this);
                if (index != -1) {
                    final int handle = this.parent.handle;
                    final LVITEM lvitem = new LVITEM();
                    lvitem.mask = 1;
                    lvitem.iItem = index;
                    lvitem.pszText = -1;
                    OS.SendMessage(handle, OS.LVM_SETITEM, 0, lvitem);
                    this.cached = false;
                }
            }
            this.parent.setScrollWidth(this, false);
        }
        this.redraw(n, true, false);
    }
    
    public void setText(final String s) {
        this.checkWidget();
        this.setText(0, s);
    }
}
