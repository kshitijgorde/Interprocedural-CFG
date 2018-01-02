// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.widgets;

import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.internal.win32.TCHAR;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.internal.win32.RECT;
import org.eclipse.swt.SWT;

public class ExpandItem extends Item
{
    ExpandBar parent;
    Control control;
    boolean expanded;
    boolean hover;
    int x;
    int y;
    int width;
    int height;
    int imageHeight;
    int imageWidth;
    static final int TEXT_INSET = 6;
    static final int BORDER = 1;
    static final int CHEVRON_SIZE = 24;
    
    public ExpandItem(final ExpandBar expandBar, final int n) {
        this(expandBar, n, checkNull(expandBar).getItemCount());
    }
    
    public ExpandItem(final ExpandBar parent, final int n, final int n2) {
        super(parent, n);
        (this.parent = parent).createItem(this, n, n2);
    }
    
    static ExpandBar checkNull(final ExpandBar expandBar) {
        if (expandBar == null) {
            SWT.error(4);
        }
        return expandBar;
    }
    
    private void drawChevron(final int n, final RECT rect) {
        final int selectObject = OS.SelectObject(n, OS.GetSysColorBrush(OS.COLOR_BTNFACE));
        OS.PatBlt(n, rect.left, rect.top, rect.right - rect.left, rect.bottom - rect.top, 15728673);
        OS.SelectObject(n, selectObject);
        rect.left += 4;
        rect.top += 4;
        rect.right -= 4;
        rect.bottom -= 4;
        final int createPen = OS.CreatePen(0, 1, this.parent.getForegroundPixel());
        final int selectObject2 = OS.SelectObject(n, createPen);
        int[] array;
        int[] array2;
        if (this.expanded) {
            final int n2 = rect.left + 5;
            int n3 = rect.top + 7;
            array = new int[] { n2, n3, n2 + 1, n3, n2 + 1, n3 - 1, n2 + 2, n3 - 1, n2 + 2, n3 - 2, n2 + 3, n3 - 2, n2 + 3, n3 - 3, n2 + 3, n3 - 2, n2 + 4, n3 - 2, n2 + 4, n3 - 1, n2 + 5, n3 - 1, n2 + 5, n3, n2 + 7, n3 };
            n3 += 4;
            array2 = new int[] { n2, n3, n2 + 1, n3, n2 + 1, n3 - 1, n2 + 2, n3 - 1, n2 + 2, n3 - 2, n2 + 3, n3 - 2, n2 + 3, n3 - 3, n2 + 3, n3 - 2, n2 + 4, n3 - 2, n2 + 4, n3 - 1, n2 + 5, n3 - 1, n2 + 5, n3, n2 + 7, n3 };
        }
        else {
            final int n4 = rect.left + 5;
            int n5 = rect.top + 4;
            array = new int[] { n4, n5, n4 + 1, n5, n4 + 1, n5 + 1, n4 + 2, n5 + 1, n4 + 2, n5 + 2, n4 + 3, n5 + 2, n4 + 3, n5 + 3, n4 + 3, n5 + 2, n4 + 4, n5 + 2, n4 + 4, n5 + 1, n4 + 5, n5 + 1, n4 + 5, n5, n4 + 7, n5 };
            n5 += 4;
            array2 = new int[] { n4, n5, n4 + 1, n5, n4 + 1, n5 + 1, n4 + 2, n5 + 1, n4 + 2, n5 + 2, n4 + 3, n5 + 2, n4 + 3, n5 + 3, n4 + 3, n5 + 2, n4 + 4, n5 + 2, n4 + 4, n5 + 1, n4 + 5, n5 + 1, n4 + 5, n5, n4 + 7, n5 };
        }
        OS.Polyline(n, array, array.length / 2);
        OS.Polyline(n, array2, array2.length / 2);
        if (this.hover) {
            final int createPen2 = OS.CreatePen(0, 1, OS.GetSysColor(OS.COLOR_3DHILIGHT));
            final int createPen3 = OS.CreatePen(0, 1, OS.GetSysColor(OS.COLOR_3DSHADOW));
            OS.SelectObject(n, createPen2);
            final int[] array3 = { rect.left, rect.bottom, rect.left, rect.top, rect.right, rect.top };
            OS.Polyline(n, array3, array3.length / 2);
            OS.SelectObject(n, createPen3);
            final int[] array4 = { rect.right, rect.top, rect.right, rect.bottom, rect.left, rect.bottom };
            OS.Polyline(n, array4, array4.length / 2);
            OS.SelectObject(n, selectObject2);
            OS.DeleteObject(createPen2);
            OS.DeleteObject(createPen3);
        }
        else {
            OS.SelectObject(n, selectObject2);
        }
        OS.DeleteObject(createPen);
    }
    
    void drawItem(final GC gc, final int n, final RECT rect, final boolean b) {
        final int handle = gc.handle;
        final int bandHeight = this.parent.getBandHeight();
        final RECT rect2 = new RECT();
        OS.SetRect(rect2, this.x, this.y, this.x + this.width, this.y + bandHeight);
        if (n != 0) {
            OS.DrawThemeBackground(n, handle, 8, 0, rect2, rect);
        }
        else {
            final int selectObject = OS.SelectObject(handle, OS.GetSysColorBrush(OS.COLOR_BTNFACE));
            OS.PatBlt(handle, rect2.left, rect2.top, rect2.right - rect2.left, rect2.bottom - rect2.top, 15728673);
            OS.SelectObject(handle, selectObject);
        }
        if (this.image != null) {
            final RECT rect3 = rect2;
            rect3.left += 6;
            if (this.imageHeight > bandHeight) {
                gc.drawImage(this.image, rect2.left, rect2.top + bandHeight - this.imageHeight);
            }
            else {
                gc.drawImage(this.image, rect2.left, rect2.top + (bandHeight - this.imageHeight) / 2);
            }
            final RECT rect4 = rect2;
            rect4.left += this.imageWidth;
        }
        if (this.text.length() > 0) {
            final RECT rect5 = rect2;
            rect5.left += 6;
            final TCHAR tchar = new TCHAR(this.parent.getCodePage(), this.text, false);
            if (n != 0) {
                OS.DrawThemeText(n, handle, 8, 0, tchar.chars, tchar.length(), 36, 0, rect2);
            }
            else {
                final int setBkMode = OS.SetBkMode(handle, 1);
                OS.DrawText(handle, tchar, tchar.length(), rect2, 36);
                OS.SetBkMode(handle, setBkMode);
            }
        }
        final int n2 = 24;
        rect2.left = rect2.right - n2;
        rect2.top = this.y + (bandHeight - n2) / 2;
        rect2.bottom = rect2.top + n2;
        if (n != 0) {
            OS.DrawThemeBackground(n, handle, this.expanded ? 6 : 7, this.hover ? 2 : 1, rect2, rect);
        }
        else {
            this.drawChevron(handle, rect2);
        }
        if (b) {
            OS.SetRect(rect2, this.x + 1, this.y + 1, this.x + this.width - 2, this.y + bandHeight - 2);
            OS.DrawFocusRect(handle, rect2);
        }
        if (this.expanded && !this.parent.isAppThemed()) {
            final int createPen = OS.CreatePen(0, 1, OS.GetSysColor(OS.COLOR_BTNFACE));
            final int selectObject2 = OS.SelectObject(handle, createPen);
            final int[] array = { this.x, this.y + bandHeight, this.x, this.y + bandHeight + this.height, this.x + this.width - 1, this.y + bandHeight + this.height, this.x + this.width - 1, this.y + bandHeight - 1 };
            OS.Polyline(handle, array, array.length / 2);
            OS.SelectObject(handle, selectObject2);
            OS.DeleteObject(createPen);
        }
    }
    
    void destroyWidget() {
        this.parent.destroyItem(this);
        this.releaseHandle();
    }
    
    public Control getControl() {
        this.checkWidget();
        return this.control;
    }
    
    public boolean getExpanded() {
        this.checkWidget();
        return this.expanded;
    }
    
    public int getHeaderHeight() {
        this.checkWidget();
        return Math.max(this.parent.getBandHeight(), this.imageHeight);
    }
    
    public int getHeight() {
        this.checkWidget();
        return this.height;
    }
    
    public ExpandBar getParent() {
        this.checkWidget();
        return this.parent;
    }
    
    int getPreferredWidth(final int n, final int n2) {
        int n3 = 36;
        if (this.image != null) {
            n3 += 6 + this.imageWidth;
        }
        if (this.text.length() > 0) {
            final RECT rect = new RECT();
            final TCHAR tchar = new TCHAR(this.parent.getCodePage(), this.text, false);
            if (n != 0) {
                OS.GetThemeTextExtent(n, n2, 8, 0, tchar.chars, tchar.length(), 32, null, rect);
            }
            else {
                OS.DrawText(n2, tchar, tchar.length(), rect, 1024);
            }
            n3 += rect.right - rect.left;
        }
        return n3;
    }
    
    boolean isHover(final int n, final int n2) {
        final int bandHeight = this.parent.getBandHeight();
        return this.x < n && n < this.x + this.width && this.y < n2 && n2 < this.y + bandHeight;
    }
    
    void redraw(final boolean b) {
        final int handle = this.parent.handle;
        final int bandHeight = this.parent.getBandHeight();
        final RECT rect = new RECT();
        OS.SetRect(rect, b ? this.x : (this.x + this.width - bandHeight), this.y, this.x + this.width, this.y + bandHeight);
        OS.InvalidateRect(handle, rect, true);
        if (this.imageHeight > bandHeight) {
            OS.SetRect(rect, this.x + 6, this.y + bandHeight - this.imageHeight, this.x + 6 + this.imageWidth, this.y);
            OS.InvalidateRect(handle, rect, true);
        }
        if (!this.parent.isAppThemed()) {
            OS.SetRect(rect, this.x, this.y + bandHeight, this.x + this.width, this.y + bandHeight + this.height + 1);
            OS.InvalidateRect(handle, rect, true);
        }
    }
    
    void releaseHandle() {
        super.releaseHandle();
        this.parent = null;
    }
    
    void releaseWidget() {
        super.releaseWidget();
        this.control = null;
    }
    
    void setBounds(int x, int y, int max, int max2, final boolean b, final boolean b2) {
        this.redraw(true);
        final int bandHeight = this.parent.getBandHeight();
        if (b) {
            if (this.imageHeight > bandHeight) {
                y += this.imageHeight - bandHeight;
            }
            this.x = x;
            this.y = y;
            this.redraw(true);
        }
        if (b2) {
            this.width = max;
            this.height = max2;
            this.redraw(true);
        }
        if (this.control != null && !this.control.isDisposed()) {
            if (!this.parent.isAppThemed()) {
                ++x;
                max = Math.max(0, max - 2);
                max2 = Math.max(0, max2 - 1);
            }
            if (b && b2) {
                this.control.setBounds(x, y + bandHeight, max, max2);
            }
            if (b && !b2) {
                this.control.setLocation(x, y + bandHeight);
            }
            if (!b && b2) {
                this.control.setSize(max, max2);
            }
        }
    }
    
    public void setControl(final Control control) {
        this.checkWidget();
        if (control != null) {
            if (control.isDisposed()) {
                this.error(5);
            }
            if (control.parent != this.parent) {
                this.error(32);
            }
        }
        if ((this.control = control) != null) {
            final int bandHeight = this.parent.getBandHeight();
            control.setVisible(this.expanded);
            if (!this.parent.isAppThemed()) {
                control.setBounds(this.x + 1, this.y + bandHeight, Math.max(0, this.width - 2), Math.max(0, this.height - 1));
            }
            else {
                control.setBounds(this.x, this.y + bandHeight, this.width, this.height);
            }
        }
    }
    
    public void setExpanded(final boolean expanded) {
        this.checkWidget();
        this.expanded = expanded;
        this.parent.showItem(this);
    }
    
    public void setHeight(final int n) {
        this.checkWidget();
        if (n < 0) {
            return;
        }
        this.setBounds(0, 0, this.width, n, false, true);
        if (this.expanded) {
            this.parent.layoutItems(this.parent.indexOf(this) + 1, true);
        }
    }
    
    public void setImage(final Image image) {
        super.setImage(image);
        final int imageHeight = this.imageHeight;
        if (image != null) {
            final Rectangle bounds = image.getBounds();
            this.imageHeight = bounds.height;
            this.imageWidth = bounds.width;
        }
        else {
            final boolean b = false;
            this.imageWidth = (b ? 1 : 0);
            this.imageHeight = (b ? 1 : 0);
        }
        if (imageHeight != this.imageHeight) {
            this.parent.layoutItems(this.parent.indexOf(this), true);
        }
        else {
            this.redraw(true);
        }
    }
    
    public void setText(final String text) {
        super.setText(text);
        this.redraw(true);
    }
}
