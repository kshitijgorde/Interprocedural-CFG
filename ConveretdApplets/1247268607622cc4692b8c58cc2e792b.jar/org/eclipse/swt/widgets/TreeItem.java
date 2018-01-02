// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.widgets;

import org.eclipse.swt.internal.win32.SCROLLINFO;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.internal.win32.TCHAR;
import org.eclipse.swt.internal.win32.RECT;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.internal.win32.TVITEM;
import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;

public class TreeItem extends Item
{
    public int handle;
    Tree parent;
    String[] strings;
    Image[] images;
    Font font;
    Font[] cellFont;
    boolean cached;
    int background;
    int foreground;
    int[] cellBackground;
    int[] cellForeground;
    
    public TreeItem(final Tree tree, final int n) {
        this(tree, n, 0, -65534, 0);
    }
    
    public TreeItem(final Tree tree, final int n, final int n2) {
        this(tree, n, 0, findPrevious(tree, n2), 0);
    }
    
    public TreeItem(final TreeItem treeItem, final int n) {
        this(checkNull(treeItem).parent, n, treeItem.handle, -65534, 0);
    }
    
    public TreeItem(final TreeItem treeItem, final int n, final int n2) {
        this(checkNull(treeItem).parent, n, treeItem.handle, findPrevious(treeItem, n2), 0);
    }
    
    TreeItem(final Tree parent, final int n, final int n2, final int n3, final int n4) {
        super(parent, n);
        this.background = -1;
        this.foreground = -1;
        (this.parent = parent).createItem(this, n2, n3, n4);
    }
    
    static TreeItem checkNull(final TreeItem treeItem) {
        if (treeItem == null) {
            SWT.error(4);
        }
        return treeItem;
    }
    
    static int findPrevious(final Tree tree, final int n) {
        if (tree == null) {
            return 0;
        }
        if (n < 0) {
            SWT.error(6);
        }
        if (n == 0) {
            return -65535;
        }
        final int item = tree.findItem(OS.SendMessage(tree.handle, 4362, 0, 0), n - 1);
        if (item == 0) {
            SWT.error(6);
        }
        return item;
    }
    
    static int findPrevious(final TreeItem treeItem, final int n) {
        if (treeItem == null) {
            return 0;
        }
        if (n < 0) {
            SWT.error(6);
        }
        if (n == 0) {
            return -65535;
        }
        final Tree parent = treeItem.parent;
        final int item = parent.findItem(OS.SendMessage(parent.handle, 4362, 4, treeItem.handle), n - 1);
        if (item == 0) {
            SWT.error(6);
        }
        return item;
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
        if ((this.parent.style & 0x20) != 0x0) {
            final int handle = this.parent.handle;
            final TVITEM tvitem = new TVITEM();
            tvitem.mask = 24;
            tvitem.stateMask = 61440;
            tvitem.state = 4096;
            tvitem.hItem = this.handle;
            OS.SendMessage(handle, OS.TVM_SETITEM, 0, tvitem);
        }
        final int n = -1;
        this.foreground = n;
        this.background = n;
        this.font = null;
        final int[] array = null;
        this.cellForeground = array;
        this.cellBackground = array;
        this.cellFont = null;
        if ((this.parent.style & 0x10000000) != 0x0) {
            this.cached = false;
        }
    }
    
    public void clear(final int n, final boolean b) {
        this.checkWidget();
        final int handle = this.parent.handle;
        final int sendMessage = OS.SendMessage(handle, 4362, 4, this.handle);
        if (sendMessage == 0) {
            this.error(6);
        }
        final int item = this.parent.findItem(sendMessage, n);
        if (item == 0) {
            this.error(6);
        }
        final TVITEM tvitem = new TVITEM();
        tvitem.mask = 20;
        this.parent.clear(item, tvitem);
        if (b) {
            this.parent.clearAll(OS.SendMessage(handle, 4362, 4, item), tvitem, b);
        }
    }
    
    public void clearAll(final boolean b) {
        this.checkWidget();
        final int sendMessage = OS.SendMessage(this.parent.handle, 4362, 4, this.handle);
        if (sendMessage == 0) {
            return;
        }
        final TVITEM tvitem = new TVITEM();
        tvitem.mask = 20;
        this.parent.clearAll(sendMessage, tvitem, b);
    }
    
    void destroyWidget() {
        final TVITEM tvitem = new TVITEM();
        tvitem.mask = 20;
        this.parent.releaseItem(this.handle, tvitem, false);
        this.parent.destroyItem(this, this.handle);
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
        final RECT bounds = this.getBounds(0, true, false, false);
        return new Rectangle(bounds.left, bounds.top, bounds.right - bounds.left, bounds.bottom - bounds.top);
    }
    
    public Rectangle getBounds(final int n) {
        this.checkWidget();
        if (!this.parent.checkData(this, true)) {
            this.error(24);
        }
        final RECT bounds = this.getBounds(n, true, true, true);
        return new Rectangle(bounds.left, bounds.top, bounds.right - bounds.left, bounds.bottom - bounds.top);
    }
    
    RECT getBounds(final int n, final boolean b, final boolean b2, final boolean b3) {
        return this.getBounds(n, b, b2, b3, false, true, 0);
    }
    
    RECT getBounds(final int n, final boolean b, final boolean b2, final boolean b3, final boolean b4, final boolean b5, final int n2) {
        if (!b && !b2) {
            return new RECT();
        }
        final int handle = this.parent.handle;
        if ((this.parent.style & 0x10000000) == 0x0 && !this.cached && !this.parent.painted) {
            final TVITEM tvitem = new TVITEM();
            tvitem.mask = 17;
            tvitem.hItem = this.handle;
            tvitem.pszText = -1;
            this.parent.ignoreCustomDraw = true;
            OS.SendMessage(handle, OS.TVM_SETITEM, 0, tvitem);
            this.parent.ignoreCustomDraw = false;
        }
        boolean b6 = n == 0;
        int columnCount = 0;
        final int hwndHeader = this.parent.hwndHeader;
        if (hwndHeader != 0) {
            columnCount = this.parent.columnCount;
            b6 = (n == OS.SendMessage(hwndHeader, 4623, 0, 0));
        }
        final RECT rect = new RECT();
        if (b6) {
            if (!OS.TreeView_GetItemRect(handle, this.handle, rect, columnCount != 0 || !b || !b2 || !b3 || !b4)) {
                return new RECT();
            }
            if (b2 && !b4) {
                if (OS.SendMessage(handle, 4360, 0, 0) != 0) {
                    final Point imageSize = this.parent.getImageSize();
                    final RECT rect2 = rect;
                    rect2.left -= imageSize.x + 3;
                    if (!b) {
                        rect.right = rect.left + imageSize.x;
                    }
                }
                else if (!b) {
                    rect.right = rect.left;
                }
            }
            if ((b3 || b4 || b5) && hwndHeader != 0) {
                RECT rect3 = new RECT();
                if (columnCount != 0) {
                    if (OS.SendMessage(hwndHeader, 4615, n, rect3) == 0) {
                        return new RECT();
                    }
                }
                else {
                    rect3.right = this.parent.scrollWidth;
                    if (rect3.right == 0) {
                        rect3 = rect;
                    }
                }
                if (b3 && b5) {
                    rect.right = rect3.right;
                }
                if (b4) {
                    rect.left = rect3.left;
                }
                if (b5 && rect3.right < rect.right) {
                    rect.right = rect3.right;
                }
            }
        }
        else {
            if (n < 0 || n >= columnCount) {
                return new RECT();
            }
            final RECT rect4 = new RECT();
            if (OS.SendMessage(hwndHeader, 4615, n, rect4) == 0) {
                return new RECT();
            }
            if (!OS.TreeView_GetItemRect(handle, this.handle, rect, false)) {
                return new RECT();
            }
            rect.left = rect4.left;
            if (b3 && b2 && b5) {
                rect.right = rect4.right;
            }
            else {
                rect.right = rect4.left;
                Image image = null;
                if (n == 0) {
                    image = this.image;
                }
                else if (this.images != null) {
                    image = this.images[n];
                }
                if (image != null) {
                    final Point imageSize2 = this.parent.getImageSize();
                    final RECT rect5 = rect;
                    rect5.right += imageSize2.x;
                }
                if (b) {
                    if (b3 && b5) {
                        rect.left = rect.right + 3;
                        rect.right = rect4.right;
                    }
                    else {
                        final String s = (n == 0) ? this.text : ((this.strings != null) ? this.strings[n] : null);
                        if (s != null) {
                            final RECT rect6 = new RECT();
                            final TCHAR tchar = new TCHAR(this.parent.getCodePage(), s, false);
                            final int n3 = 3104;
                            int getDC = n2;
                            int selectObject = 0;
                            if (n2 == 0) {
                                getDC = OS.GetDC(handle);
                                int n4 = this.fontHandle(n);
                                if (n4 == -1) {
                                    n4 = OS.SendMessage(handle, 49, 0, 0);
                                }
                                selectObject = OS.SelectObject(getDC, n4);
                            }
                            OS.DrawText(getDC, tchar, tchar.length(), rect6, n3);
                            if (n2 == 0) {
                                OS.SelectObject(getDC, selectObject);
                                OS.ReleaseDC(handle, getDC);
                            }
                            if (b2) {
                                final RECT rect7 = rect;
                                rect7.right += rect6.right - rect6.left + 9;
                            }
                            else {
                                rect.left = rect.right + 3;
                                rect.right = rect.left + (rect6.right - rect6.left) + 3;
                            }
                        }
                    }
                }
                if (b5 && rect4.right < rect.right) {
                    rect.right = rect4.right;
                }
            }
        }
        final int n5 = (this.parent.linesVisible && columnCount != 0) ? 1 : 0;
        if (b || !b2) {
            rect.right = Math.max(rect.left, rect.right - n5);
        }
        rect.bottom = Math.max(rect.top, rect.bottom - n5);
        return rect;
    }
    
    public boolean getChecked() {
        this.checkWidget();
        if (!this.parent.checkData(this, true)) {
            this.error(24);
        }
        if ((this.parent.style & 0x20) == 0x0) {
            return false;
        }
        final int handle = this.parent.handle;
        final TVITEM tvitem = new TVITEM();
        tvitem.mask = 24;
        tvitem.stateMask = 61440;
        tvitem.hItem = this.handle;
        return OS.SendMessage(handle, OS.TVM_GETITEM, 0, tvitem) != 0 && (tvitem.state >> 12 & 0x1) == 0x0;
    }
    
    public boolean getExpanded() {
        this.checkWidget();
        final int handle = this.parent.handle;
        int n;
        if (OS.IsWinCE) {
            final TVITEM tvitem = new TVITEM();
            tvitem.hItem = this.handle;
            tvitem.mask = 8;
            OS.SendMessage(handle, OS.TVM_GETITEM, 0, tvitem);
            n = tvitem.state;
        }
        else {
            n = OS.SendMessage(handle, 4391, this.handle, 32);
        }
        return (n & 0x20) != 0x0;
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
        if ((this.parent.style & 0x20) == 0x0) {
            return false;
        }
        final int handle = this.parent.handle;
        final TVITEM tvitem = new TVITEM();
        tvitem.mask = 24;
        tvitem.stateMask = 61440;
        tvitem.hItem = this.handle;
        return OS.SendMessage(handle, OS.TVM_GETITEM, 0, tvitem) != 0 && tvitem.state >> 12 > 2;
    }
    
    public TreeItem getItem(final int n) {
        this.checkWidget();
        if (n < 0) {
            this.error(6);
        }
        if (!this.parent.checkData(this, true)) {
            this.error(24);
        }
        final int sendMessage = OS.SendMessage(this.parent.handle, 4362, 4, this.handle);
        if (sendMessage == 0) {
            this.error(6);
        }
        final int item = this.parent.findItem(sendMessage, n);
        if (item == 0) {
            this.error(6);
        }
        return this.parent._getItem(item);
    }
    
    public int getItemCount() {
        this.checkWidget();
        if (!this.parent.checkData(this, true)) {
            this.error(24);
        }
        final int sendMessage = OS.SendMessage(this.parent.handle, 4362, 4, this.handle);
        if (sendMessage == 0) {
            return 0;
        }
        return this.parent.getItemCount(sendMessage);
    }
    
    public TreeItem[] getItems() {
        this.checkWidget();
        if (!this.parent.checkData(this, true)) {
            this.error(24);
        }
        final int sendMessage = OS.SendMessage(this.parent.handle, 4362, 4, this.handle);
        if (sendMessage == 0) {
            return new TreeItem[0];
        }
        return this.parent.getItems(sendMessage);
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
        final RECT bounds = this.getBounds(n, false, true, false);
        return new Rectangle(bounds.left, bounds.top, bounds.right - bounds.left, bounds.bottom - bounds.top);
    }
    
    public Tree getParent() {
        this.checkWidget();
        return this.parent;
    }
    
    public TreeItem getParentItem() {
        this.checkWidget();
        final int sendMessage = OS.SendMessage(this.parent.handle, 4362, 3, this.handle);
        return (sendMessage != 0) ? this.parent._getItem(sendMessage) : null;
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
        final RECT bounds = this.getBounds(n, true, false, true);
        if (n == 0) {
            final RECT rect = bounds;
            rect.left += 2;
        }
        bounds.left = Math.min(bounds.left, bounds.right);
        bounds.right -= 3;
        return new Rectangle(bounds.left, bounds.top, Math.max(0, bounds.right - bounds.left), Math.max(0, bounds.bottom - bounds.top));
    }
    
    public int indexOf(final TreeItem treeItem) {
        this.checkWidget();
        if (treeItem == null) {
            this.error(4);
        }
        if (treeItem.isDisposed()) {
            this.error(5);
        }
        final int sendMessage = OS.SendMessage(this.parent.handle, 4362, 4, this.handle);
        return (sendMessage == 0) ? -1 : this.parent.findIndex(sendMessage, treeItem.handle);
    }
    
    void redraw() {
        if (this.parent.currentItem == this || !this.parent.getDrawing()) {
            return;
        }
        final int handle = this.parent.handle;
        if (!OS.IsWindowVisible(handle)) {
            return;
        }
        int n = ((this.parent.style & 0x10010000) != 0x0) ? 1 : 0;
        if (n == 0) {
            n = ((this.parent.columnCount != 0) ? 1 : 0);
            if (n == 0 && (this.parent.hooks(40) || this.parent.hooks(42))) {
                n = 1;
            }
        }
        final RECT rect = new RECT();
        if (OS.TreeView_GetItemRect(handle, this.handle, rect, n == 0)) {
            OS.InvalidateRect(handle, rect, true);
        }
    }
    
    void redraw(final int n, final boolean b, final boolean b2) {
        if (this.parent.currentItem == this || !this.parent.getDrawing()) {
            return;
        }
        final int handle = this.parent.handle;
        if (!OS.IsWindowVisible(handle)) {
            return;
        }
        OS.InvalidateRect(handle, this.getBounds(n, b, b2, true, n == 0 && b && b2, true, 0), true);
    }
    
    void releaseChildren(final boolean b) {
        if (b) {
            final TVITEM tvitem = new TVITEM();
            tvitem.mask = 20;
            this.parent.releaseItems(this.handle, tvitem);
        }
        super.releaseChildren(b);
    }
    
    void releaseHandle() {
        super.releaseHandle();
        this.handle = 0;
        this.parent = null;
    }
    
    void releaseWidget() {
        super.releaseWidget();
        this.strings = null;
        this.images = null;
        final int[] array = null;
        this.cellForeground = array;
        this.cellBackground = array;
        this.cellFont = null;
    }
    
    public void removeAll() {
        this.checkWidget();
        final int handle = this.parent.handle;
        final TVITEM tvitem = new TVITEM();
        tvitem.mask = 20;
        tvitem.hItem = OS.SendMessage(handle, 4362, 4, this.handle);
        while (tvitem.hItem != 0) {
            OS.SendMessage(handle, OS.TVM_GETITEM, 0, tvitem);
            final TreeItem treeItem = (tvitem.lParam != -1) ? this.parent.items[tvitem.lParam] : null;
            if (treeItem != null && !treeItem.isDisposed()) {
                treeItem.dispose();
            }
            else {
                this.parent.releaseItem(tvitem.hItem, tvitem, false);
                this.parent.destroyItem(null, tvitem.hItem);
            }
            tvitem.hItem = OS.SendMessage(handle, 4362, 4, this.handle);
        }
    }
    
    public void setBackground(final Color color) {
        this.checkWidget();
        if (color != null && color.isDisposed()) {
            SWT.error(5);
        }
        int handle = -1;
        if (color != null) {
            this.parent.customDraw = true;
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
            this.parent.customDraw = true;
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
        final int handle = this.parent.handle;
        final TVITEM tvitem = new TVITEM();
        tvitem.mask = 24;
        tvitem.stateMask = 61440;
        tvitem.hItem = this.handle;
        OS.SendMessage(handle, OS.TVM_GETITEM, 0, tvitem);
        int n = tvitem.state >> 12;
        if (b) {
            if ((n & 0x1) != 0x0) {
                ++n;
            }
        }
        else if ((n & 0x1) == 0x0) {
            --n;
        }
        final int state = n << 12;
        if (tvitem.state == state) {
            return;
        }
        if ((this.parent.style & 0x10000000) != 0x0) {
            this.cached = true;
        }
        tvitem.state = state;
        OS.SendMessage(handle, OS.TVM_SETITEM, 0, tvitem);
        if ((this.parent.style & 0x10000000) != 0x0 && this.parent.currentItem == this && OS.IsWindowVisible(handle)) {
            final RECT rect = new RECT();
            if (OS.TreeView_GetItemRect(handle, this.handle, rect, false)) {
                OS.InvalidateRect(handle, rect, true);
            }
        }
    }
    
    public void setExpanded(final boolean b) {
        this.checkWidget();
        final int handle = this.parent.handle;
        if (OS.SendMessage(handle, 4362, 4, this.handle) == 0) {
            return;
        }
        int n;
        if (OS.IsWinCE) {
            final TVITEM tvitem = new TVITEM();
            tvitem.hItem = this.handle;
            tvitem.mask = 8;
            OS.SendMessage(handle, OS.TVM_GETITEM, 0, tvitem);
            n = tvitem.state;
        }
        else {
            n = OS.SendMessage(handle, 4391, this.handle, 32);
        }
        if ((n & 0x20) != 0x0 == b) {
            return;
        }
        RECT rect = null;
        RECT[] array = null;
        SCROLLINFO scrollinfo = null;
        int sendMessage = 0;
        int n2 = 0;
        boolean b2 = false;
        final boolean b3 = true;
        int n3 = OS.SendMessage(handle, 4362, 5, 0);
        if (b3 && n3 != 0) {
            scrollinfo = new SCROLLINFO();
            scrollinfo.cbSize = SCROLLINFO.sizeof;
            scrollinfo.fMask = 23;
            if (!OS.GetScrollInfo(handle, 0, scrollinfo)) {
                scrollinfo = null;
            }
            if (this.parent.getDrawing() && OS.IsWindowVisible(handle)) {
                final boolean b4 = true;
                sendMessage = OS.SendMessage(handle, 4368, 0, 0);
                array = new RECT[sendMessage + 1];
                int sendMessage2;
                int n4;
                for (sendMessage2 = n3, n4 = 0; sendMessage2 != 0 && (b4 || sendMessage2 != this.handle) && n4 < sendMessage; sendMessage2 = OS.SendMessage(handle, 4362, 6, sendMessage2)) {
                    final RECT rect2 = new RECT();
                    if (OS.TreeView_GetItemRect(handle, sendMessage2, rect2, true)) {
                        array[n4++] = rect2;
                    }
                }
                if (b4 || sendMessage2 != this.handle) {
                    b2 = true;
                    sendMessage = n4;
                    n2 = sendMessage2;
                    rect = new RECT();
                    OS.GetClientRect(handle, rect);
                    final int topHandle = this.parent.topHandle();
                    OS.UpdateWindow(topHandle);
                    OS.DefWindowProc(topHandle, 11, 0, 0);
                    if (handle != topHandle) {
                        OS.UpdateWindow(handle);
                        OS.DefWindowProc(handle, 11, 0, 0);
                    }
                }
            }
        }
        final int sendMessage3 = OS.SendMessage(handle, 4362, 9, 0);
        this.parent.ignoreExpand = true;
        OS.SendMessage(handle, 4354, b ? 2 : 1, this.handle);
        this.parent.ignoreExpand = false;
        if (b3 && n3 != 0) {
            boolean b5 = false;
            if (!b) {
                for (RECT rect3 = new RECT(); n3 != 0 && !OS.TreeView_GetItemRect(handle, n3, rect3, false); n3 = OS.SendMessage(handle, 4362, 3, n3), b5 = true) {}
            }
            int n5 = 1;
            if (n3 != 0) {
                OS.SendMessage(handle, 4363, 5, n3);
                n5 = ((n3 != OS.SendMessage(handle, 4362, 5, 0)) ? 1 : 0);
            }
            if (!b5 && n5 == 0 && scrollinfo != null) {
                final SCROLLINFO scrollinfo2 = new SCROLLINFO();
                scrollinfo2.cbSize = SCROLLINFO.sizeof;
                scrollinfo2.fMask = 23;
                if (OS.GetScrollInfo(handle, 0, scrollinfo2) && scrollinfo.nPos != scrollinfo2.nPos) {
                    OS.SendMessage(handle, 276, OS.MAKELPARAM(4, scrollinfo.nPos), 0);
                }
            }
            if (b2) {
                int n6 = 0;
                if (!b5 && n5 == 0) {
                    final RECT rect4 = new RECT();
                    OS.GetClientRect(handle, rect4);
                    if (OS.EqualRect(rect, rect4)) {
                        int sendMessage4;
                        int n7;
                        for (sendMessage4 = n3, n7 = 0; sendMessage4 != 0 && n7 < sendMessage; sendMessage4 = OS.SendMessage(handle, 4362, 6, sendMessage4), ++n7) {
                            final RECT rect5 = new RECT();
                            if (OS.TreeView_GetItemRect(handle, sendMessage4, rect5, true) && !OS.EqualRect(rect5, array[n7])) {
                                break;
                            }
                        }
                        n6 = ((n7 == sendMessage && sendMessage4 == n2) ? 1 : 0);
                    }
                }
                final int topHandle2 = this.parent.topHandle();
                OS.DefWindowProc(topHandle2, 11, 1, 0);
                if (handle != topHandle2) {
                    OS.DefWindowProc(handle, 11, 1, 0);
                }
                if (n6 != 0) {
                    this.parent.updateScrollBar();
                    final SCROLLINFO scrollinfo3 = new SCROLLINFO();
                    scrollinfo3.cbSize = SCROLLINFO.sizeof;
                    scrollinfo3.fMask = 23;
                    if (OS.GetScrollInfo(handle, 1, scrollinfo3)) {
                        OS.SetScrollInfo(handle, 1, scrollinfo3, true);
                    }
                    if (this.handle == n2) {
                        final RECT rect6 = new RECT();
                        if (OS.TreeView_GetItemRect(handle, n2, rect6, false)) {
                            OS.InvalidateRect(handle, rect6, true);
                        }
                    }
                }
                else if (OS.IsWinCE) {
                    OS.InvalidateRect(topHandle2, null, true);
                    if (handle != topHandle2) {
                        OS.InvalidateRect(handle, null, true);
                    }
                }
                else {
                    OS.RedrawWindow(topHandle2, null, 0, 1157);
                }
            }
        }
        final int sendMessage5 = OS.SendMessage(handle, 4362, 9, 0);
        if (sendMessage5 != sendMessage3) {
            final Event event = new Event();
            if (sendMessage5 != 0) {
                event.item = this.parent._getItem(sendMessage5);
                this.parent.hAnchor = sendMessage5;
            }
            this.parent.sendSelectionEvent(13, event, true);
        }
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
            this.parent.customDraw = true;
        }
        if ((this.parent.style & 0x10000000) != 0x0) {
            this.cached = true;
        }
        if ((this.parent.style & 0x10000000) == 0x0 && !this.cached && !this.parent.painted) {
            return;
        }
        final int handle = this.parent.handle;
        final TVITEM tvitem = new TVITEM();
        tvitem.mask = 17;
        tvitem.hItem = this.handle;
        tvitem.pszText = -1;
        OS.SendMessage(handle, OS.TVM_SETITEM, 0, tvitem);
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
            this.parent.customDraw = true;
        }
        if ((this.parent.style & 0x10000000) != 0x0) {
            this.cached = true;
        }
        if (n == 0) {
            if ((this.parent.style & 0x10000000) == 0x0 && !this.cached && !this.parent.painted) {
                return;
            }
            final int handle = this.parent.handle;
            final TVITEM tvitem = new TVITEM();
            tvitem.mask = 17;
            tvitem.hItem = this.handle;
            tvitem.pszText = -1;
            OS.SendMessage(handle, OS.TVM_SETITEM, 0, tvitem);
        }
        else {
            this.redraw(n, true, false);
        }
    }
    
    public void setForeground(final Color color) {
        this.checkWidget();
        if (color != null && color.isDisposed()) {
            SWT.error(5);
        }
        int handle = -1;
        if (color != null) {
            this.parent.customDraw = true;
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
            this.parent.customDraw = true;
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
    
    public void setGrayed(final boolean b) {
        this.checkWidget();
        if ((this.parent.style & 0x20) == 0x0) {
            return;
        }
        final int handle = this.parent.handle;
        final TVITEM tvitem = new TVITEM();
        tvitem.mask = 24;
        tvitem.stateMask = 61440;
        tvitem.hItem = this.handle;
        OS.SendMessage(handle, OS.TVM_GETITEM, 0, tvitem);
        int n = tvitem.state >> 12;
        if (b) {
            if (n <= 2) {
                n += 2;
            }
        }
        else if (n > 2) {
            n -= 2;
        }
        final int state = n << 12;
        if (tvitem.state == state) {
            return;
        }
        if ((this.parent.style & 0x10000000) != 0x0) {
            this.cached = true;
        }
        tvitem.state = state;
        OS.SendMessage(handle, OS.TVM_SETITEM, 0, tvitem);
        if ((this.parent.style & 0x10000000) != 0x0 && this.parent.currentItem == this && OS.IsWindowVisible(handle)) {
            final RECT rect = new RECT();
            if (OS.TreeView_GetItemRect(handle, this.handle, rect, false)) {
                OS.InvalidateRect(handle, rect, true);
            }
        }
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
            if ((this.parent.style & 0x10000000) == 0x0 && !this.cached && !this.parent.painted) {
                return;
            }
            final int handle = this.parent.handle;
            final TVITEM tvitem = new TVITEM();
            tvitem.mask = 50;
            tvitem.hItem = this.handle;
            final TVITEM tvitem2 = tvitem;
            final TVITEM tvitem3 = tvitem;
            final int n2 = -1;
            tvitem3.iSelectedImage = n2;
            tvitem2.iImage = n2;
            final TVITEM tvitem4 = tvitem;
            tvitem4.mask |= 0x1;
            tvitem.pszText = -1;
            OS.SendMessage(handle, OS.TVM_SETITEM, 0, tvitem);
        }
        else {
            this.redraw(n, (image == null && image2 != null) || (image != null && image2 == null), true);
        }
    }
    
    public void setImage(final Image image) {
        this.checkWidget();
        this.setImage(0, image);
    }
    
    public void setItemCount(int max) {
        this.checkWidget();
        max = Math.max(0, max);
        this.parent.setItemCount(max, this.handle, OS.SendMessage(this.parent.handle, 4362, 4, this.handle));
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
            if ((this.parent.style & 0x10000000) == 0x0 && !this.cached && !this.parent.painted) {
                return;
            }
            final int handle = this.parent.handle;
            final TVITEM tvitem = new TVITEM();
            tvitem.mask = 17;
            tvitem.hItem = this.handle;
            tvitem.pszText = -1;
            OS.SendMessage(handle, OS.TVM_SETITEM, 0, tvitem);
        }
        else {
            this.redraw(n, true, false);
        }
    }
    
    public void setText(final String s) {
        this.checkWidget();
        this.setText(0, s);
    }
    
    void sort() {
        this.checkWidget();
        if ((this.parent.style & 0x10000000) != 0x0) {
            return;
        }
        this.parent.sort(this.handle, false);
    }
}
