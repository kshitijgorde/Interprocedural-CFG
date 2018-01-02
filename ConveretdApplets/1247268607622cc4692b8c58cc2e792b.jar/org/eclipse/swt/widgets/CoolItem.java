// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.widgets;

import org.eclipse.swt.internal.win32.REBARBANDINFO;
import org.eclipse.swt.internal.win32.MARGINS;
import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.internal.win32.RECT;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.internal.SWTEventListener;
import org.eclipse.swt.events.SelectionListener;

public class CoolItem extends Item
{
    CoolBar parent;
    Control control;
    int id;
    boolean ideal;
    boolean minimum;
    
    public CoolItem(final CoolBar parent, final int n) {
        super(parent, n);
        (this.parent = parent).createItem(this, parent.getItemCount());
    }
    
    public CoolItem(final CoolBar parent, final int n, final int n2) {
        super(parent, n);
        (this.parent = parent).createItem(this, n2);
    }
    
    public void addSelectionListener(final SelectionListener selectionListener) {
        this.checkWidget();
        if (selectionListener == null) {
            this.error(4);
        }
        final TypedListener typedListener = new TypedListener(selectionListener);
        this.addListener(13, typedListener);
        this.addListener(14, typedListener);
    }
    
    protected void checkSubclass() {
        if (!this.isValidSubclass()) {
            this.error(43);
        }
    }
    
    public Point computeSize(final int n, final int n2) {
        this.checkWidget();
        final int index = this.parent.indexOf(this);
        if (index == -1) {
            return new Point(0, 0);
        }
        int n3 = n;
        int n4 = n2;
        if (n == -1) {
            n3 = 32;
        }
        if (n2 == -1) {
            n4 = 32;
        }
        if ((this.parent.style & 0x200) != 0x0) {
            n4 += this.parent.getMargin(index);
        }
        else {
            n3 += this.parent.getMargin(index);
        }
        return new Point(n3, n4);
    }
    
    void destroyWidget() {
        this.parent.destroyItem(this);
        this.releaseHandle();
    }
    
    public Rectangle getBounds() {
        this.checkWidget();
        final int index = this.parent.indexOf(this);
        if (index == -1) {
            return new Rectangle(0, 0, 0, 0);
        }
        final int handle = this.parent.handle;
        final RECT rect = new RECT();
        OS.SendMessage(handle, 1033, index, rect);
        if (OS.COMCTL32_MAJOR >= 6) {
            final MARGINS margins = new MARGINS();
            OS.SendMessage(handle, 1064, 0, margins);
            final RECT rect2 = rect;
            rect2.left -= margins.cxLeftWidth;
            final RECT rect3 = rect;
            rect3.right += margins.cxRightWidth;
        }
        if (!this.parent.isLastItemOfRow(index)) {
            final RECT rect4 = rect;
            rect4.right += (((this.parent.style & 0x800000) == 0x0) ? 2 : 0);
        }
        final int n = rect.right - rect.left;
        final int n2 = rect.bottom - rect.top;
        if ((this.parent.style & 0x200) != 0x0) {
            return new Rectangle(rect.top, rect.left, n2, n);
        }
        return new Rectangle(rect.left, rect.top, n, n2);
    }
    
    Rectangle getClientArea() {
        this.checkWidget();
        final int index = this.parent.indexOf(this);
        if (index == -1) {
            return new Rectangle(0, 0, 0, 0);
        }
        final int handle = this.parent.handle;
        final RECT rect = new RECT();
        OS.SendMessage(handle, 1058, index, rect);
        final RECT rect2 = new RECT();
        OS.SendMessage(handle, 1033, index, rect2);
        final int n = rect2.left + rect.left;
        int top = rect2.top;
        int n2 = rect2.right - rect2.left - rect.left;
        int n3 = rect2.bottom - rect2.top;
        if ((this.parent.style & 0x800000) == 0x0) {
            top += rect.top;
            n2 -= rect.right;
            n3 -= rect.top + rect.bottom;
        }
        if (index == 0) {
            final REBARBANDINFO rebarbandinfo = new REBARBANDINFO();
            rebarbandinfo.cbSize = REBARBANDINFO.sizeof;
            rebarbandinfo.fMask = 2048;
            OS.SendMessage(handle, OS.RB_GETBANDINFO, index, rebarbandinfo);
            n2 = n2 - rebarbandinfo.cxHeader + 1;
        }
        return new Rectangle(n, top, Math.max(0, n2), Math.max(0, n3));
    }
    
    public Control getControl() {
        this.checkWidget();
        return this.control;
    }
    
    public CoolBar getParent() {
        this.checkWidget();
        return this.parent;
    }
    
    void releaseHandle() {
        super.releaseHandle();
        this.parent = null;
        this.id = -1;
        this.control = null;
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
        final int index = this.parent.indexOf(this);
        if (index == -1) {
            return;
        }
        if (this.control != null && this.control.isDisposed()) {
            this.control = null;
        }
        final Control control2 = this.control;
        final int handle = this.parent.handle;
        final int hwndChild = (control != null) ? control.topHandle() : 0;
        final REBARBANDINFO rebarbandinfo = new REBARBANDINFO();
        rebarbandinfo.cbSize = REBARBANDINFO.sizeof;
        rebarbandinfo.fMask = 16;
        rebarbandinfo.hwndChild = hwndChild;
        this.control = control;
        int getWindow = 0;
        if (control != null) {
            getWindow = OS.GetWindow(hwndChild, 3);
        }
        final boolean b = control != null && !control.getVisible();
        final boolean b2 = control2 != null && control2.getVisible();
        OS.SendMessage(handle, OS.RB_SETBANDINFO, index, rebarbandinfo);
        if (b) {
            control.setVisible(false);
        }
        if (b2) {
            control2.setVisible(true);
        }
        if (getWindow != 0 && getWindow != hwndChild) {
            this.SetWindowPos(hwndChild, getWindow, 0, 0, 0, 0, 19);
        }
    }
    
    public Point getPreferredSize() {
        this.checkWidget();
        final int index = this.parent.indexOf(this);
        if (index == -1) {
            return new Point(0, 0);
        }
        final int handle = this.parent.handle;
        final REBARBANDINFO rebarbandinfo = new REBARBANDINFO();
        rebarbandinfo.cbSize = REBARBANDINFO.sizeof;
        rebarbandinfo.fMask = 544;
        OS.SendMessage(handle, OS.RB_GETBANDINFO, index, rebarbandinfo);
        final int n = rebarbandinfo.cxIdeal + this.parent.getMargin(index);
        if ((this.parent.style & 0x200) != 0x0) {
            return new Point(rebarbandinfo.cyMaxChild, n);
        }
        return new Point(n, rebarbandinfo.cyMaxChild);
    }
    
    public void setPreferredSize(int max, int max2) {
        this.checkWidget();
        final int index = this.parent.indexOf(this);
        if (index == -1) {
            return;
        }
        max = Math.max(0, max);
        max2 = Math.max(0, max2);
        this.ideal = true;
        final int handle = this.parent.handle;
        int cxIdeal;
        int n;
        if ((this.parent.style & 0x200) != 0x0) {
            cxIdeal = Math.max(0, max2 - this.parent.getMargin(index));
            n = max;
        }
        else {
            cxIdeal = Math.max(0, max - this.parent.getMargin(index));
            n = max2;
        }
        final REBARBANDINFO rebarbandinfo = new REBARBANDINFO();
        rebarbandinfo.cbSize = REBARBANDINFO.sizeof;
        rebarbandinfo.fMask = 32;
        OS.SendMessage(handle, OS.RB_GETBANDINFO, index, rebarbandinfo);
        rebarbandinfo.fMask = 544;
        rebarbandinfo.cxIdeal = cxIdeal;
        rebarbandinfo.cyMaxChild = n;
        if (!this.minimum) {
            rebarbandinfo.cyMinChild = n;
        }
        OS.SendMessage(handle, OS.RB_SETBANDINFO, index, rebarbandinfo);
    }
    
    public void setPreferredSize(final Point point) {
        this.checkWidget();
        if (point == null) {
            this.error(4);
        }
        this.setPreferredSize(point.x, point.y);
    }
    
    public Point getSize() {
        this.checkWidget();
        final int index = this.parent.indexOf(this);
        if (index == -1) {
            new Point(0, 0);
        }
        final int handle = this.parent.handle;
        final RECT rect = new RECT();
        OS.SendMessage(handle, 1033, index, rect);
        if (OS.COMCTL32_MAJOR >= 6) {
            final MARGINS margins = new MARGINS();
            OS.SendMessage(handle, 1064, 0, margins);
            final RECT rect2 = rect;
            rect2.left -= margins.cxLeftWidth;
            final RECT rect3 = rect;
            rect3.right += margins.cxRightWidth;
        }
        if (!this.parent.isLastItemOfRow(index)) {
            final RECT rect4 = rect;
            rect4.right += (((this.parent.style & 0x800000) == 0x0) ? 2 : 0);
        }
        final int n = rect.right - rect.left;
        final int n2 = rect.bottom - rect.top;
        if ((this.parent.style & 0x200) != 0x0) {
            return new Point(n2, n);
        }
        return new Point(n, n2);
    }
    
    public void setSize(int max, int max2) {
        this.checkWidget();
        final int index = this.parent.indexOf(this);
        if (index == -1) {
            return;
        }
        max = Math.max(0, max);
        max2 = Math.max(0, max2);
        final int handle = this.parent.handle;
        int n;
        int n2;
        int cxIdeal;
        if ((this.parent.style & 0x200) != 0x0) {
            n = max2;
            n2 = max;
            cxIdeal = Math.max(0, max2 - this.parent.getMargin(index));
        }
        else {
            n = max;
            n2 = max2;
            cxIdeal = Math.max(0, max - this.parent.getMargin(index));
        }
        final REBARBANDINFO rebarbandinfo = new REBARBANDINFO();
        rebarbandinfo.cbSize = REBARBANDINFO.sizeof;
        rebarbandinfo.fMask = 544;
        OS.SendMessage(handle, OS.RB_GETBANDINFO, index, rebarbandinfo);
        if (!this.ideal) {
            rebarbandinfo.cxIdeal = cxIdeal;
        }
        if (!this.minimum) {
            rebarbandinfo.cyMinChild = n2;
        }
        rebarbandinfo.cyChild = n2;
        if (!this.parent.isLastItemOfRow(index)) {
            if (OS.COMCTL32_MAJOR >= 6) {
                final MARGINS margins = new MARGINS();
                OS.SendMessage(handle, 1064, 0, margins);
                n -= margins.cxLeftWidth + margins.cxRightWidth;
            }
            rebarbandinfo.cx = n - (((this.parent.style & 0x800000) == 0x0) ? 2 : 0);
            final REBARBANDINFO rebarbandinfo2 = rebarbandinfo;
            rebarbandinfo2.fMask |= 0x40;
        }
        OS.SendMessage(handle, OS.RB_SETBANDINFO, index, rebarbandinfo);
    }
    
    public void setSize(final Point point) {
        if (point == null) {
            this.error(4);
        }
        this.setSize(point.x, point.y);
    }
    
    public Point getMinimumSize() {
        this.checkWidget();
        final int index = this.parent.indexOf(this);
        if (index == -1) {
            return new Point(0, 0);
        }
        final int handle = this.parent.handle;
        final REBARBANDINFO rebarbandinfo = new REBARBANDINFO();
        rebarbandinfo.cbSize = REBARBANDINFO.sizeof;
        rebarbandinfo.fMask = 32;
        OS.SendMessage(handle, OS.RB_GETBANDINFO, index, rebarbandinfo);
        if ((this.parent.style & 0x200) != 0x0) {
            return new Point(rebarbandinfo.cyMinChild, rebarbandinfo.cxMinChild);
        }
        return new Point(rebarbandinfo.cxMinChild, rebarbandinfo.cyMinChild);
    }
    
    public void setMinimumSize(int max, int max2) {
        this.checkWidget();
        final int index = this.parent.indexOf(this);
        if (index == -1) {
            return;
        }
        max = Math.max(0, max);
        max2 = Math.max(0, max2);
        this.minimum = true;
        final int handle = this.parent.handle;
        int cxMinChild;
        int cyMinChild;
        if ((this.parent.style & 0x200) != 0x0) {
            cxMinChild = max2;
            cyMinChild = max;
        }
        else {
            cxMinChild = max;
            cyMinChild = max2;
        }
        final REBARBANDINFO rebarbandinfo = new REBARBANDINFO();
        rebarbandinfo.cbSize = REBARBANDINFO.sizeof;
        rebarbandinfo.fMask = 32;
        OS.SendMessage(handle, OS.RB_GETBANDINFO, index, rebarbandinfo);
        rebarbandinfo.cxMinChild = cxMinChild;
        rebarbandinfo.cyMinChild = cyMinChild;
        OS.SendMessage(handle, OS.RB_SETBANDINFO, index, rebarbandinfo);
    }
    
    public void setMinimumSize(final Point point) {
        this.checkWidget();
        if (point == null) {
            this.error(4);
        }
        this.setMinimumSize(point.x, point.y);
    }
    
    boolean getWrap() {
        final int index = this.parent.indexOf(this);
        final int handle = this.parent.handle;
        final REBARBANDINFO rebarbandinfo = new REBARBANDINFO();
        rebarbandinfo.cbSize = REBARBANDINFO.sizeof;
        rebarbandinfo.fMask = 1;
        OS.SendMessage(handle, OS.RB_GETBANDINFO, index, rebarbandinfo);
        return (rebarbandinfo.fStyle & 0x1) != 0x0;
    }
    
    void setWrap(final boolean b) {
        final int index = this.parent.indexOf(this);
        final int handle = this.parent.handle;
        final REBARBANDINFO rebarbandinfo = new REBARBANDINFO();
        rebarbandinfo.cbSize = REBARBANDINFO.sizeof;
        rebarbandinfo.fMask = 1;
        OS.SendMessage(handle, OS.RB_GETBANDINFO, index, rebarbandinfo);
        if (b) {
            final REBARBANDINFO rebarbandinfo2 = rebarbandinfo;
            rebarbandinfo2.fStyle |= 0x1;
        }
        else {
            final REBARBANDINFO rebarbandinfo3 = rebarbandinfo;
            rebarbandinfo3.fStyle &= 0xFFFFFFFE;
        }
        OS.SendMessage(handle, OS.RB_SETBANDINFO, index, rebarbandinfo);
    }
    
    public void removeSelectionListener(final SelectionListener selectionListener) {
        this.checkWidget();
        if (selectionListener == null) {
            this.error(4);
        }
        if (this.eventTable == null) {
            return;
        }
        this.eventTable.unhook(13, selectionListener);
        this.eventTable.unhook(14, selectionListener);
    }
}
