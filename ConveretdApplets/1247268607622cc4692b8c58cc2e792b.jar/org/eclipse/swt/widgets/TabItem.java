// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.widgets;

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.internal.win32.RECT;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.internal.win32.TCITEM;
import org.eclipse.swt.internal.win32.TCHAR;
import org.eclipse.swt.internal.win32.OS;

public class TabItem extends Item
{
    TabFolder parent;
    Control control;
    String toolTipText;
    
    public TabItem(final TabFolder parent, final int n) {
        super(parent, n);
        (this.parent = parent).createItem(this, parent.getItemCount());
    }
    
    public TabItem(final TabFolder parent, final int n, final int n2) {
        super(parent, n);
        (this.parent = parent).createItem(this, n2);
    }
    
    void _setText(final int n, String s) {
        if (OS.COMCTL32_MAJOR >= 6 && this.image != null && s.indexOf(38) != -1) {
            final int length = s.length();
            final char[] array = new char[length];
            s.getChars(0, length, array, 0);
            int n2 = 0;
            int i;
            for (i = 0; i < length; ++i) {
                if (array[i] != '&') {
                    array[n2++] = array[i];
                }
            }
            if (n2 < i) {
                s = new String(array, 0, n2);
            }
        }
        final int handle = this.parent.handle;
        final int getProcessHeap = OS.GetProcessHeap();
        final TCHAR tchar = new TCHAR(this.parent.getCodePage(), s, true);
        final int n3 = tchar.length() * TCHAR.sizeof;
        final int heapAlloc = OS.HeapAlloc(getProcessHeap, 8, n3);
        OS.MoveMemory(heapAlloc, tchar, n3);
        final TCITEM tcitem = new TCITEM();
        tcitem.mask = 1;
        tcitem.pszText = heapAlloc;
        OS.SendMessage(handle, OS.TCM_SETITEM, n, tcitem);
        OS.HeapFree(getProcessHeap, 0, heapAlloc);
    }
    
    protected void checkSubclass() {
        if (!this.isValidSubclass()) {
            this.error(43);
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
    
    public Rectangle getBounds() {
        this.checkWidget();
        final int index = this.parent.indexOf(this);
        if (index == -1) {
            return new Rectangle(0, 0, 0, 0);
        }
        final RECT rect = new RECT();
        OS.SendMessage(this.parent.handle, 4874, index, rect);
        return new Rectangle(rect.left, rect.top, rect.right - rect.left, rect.bottom - rect.top);
    }
    
    public TabFolder getParent() {
        this.checkWidget();
        return this.parent;
    }
    
    public String getToolTipText() {
        this.checkWidget();
        return this.toolTipText;
    }
    
    void releaseHandle() {
        super.releaseHandle();
        this.parent = null;
    }
    
    void releaseParent() {
        super.releaseParent();
        if (this.parent.indexOf(this) == this.parent.getSelectionIndex() && this.control != null) {
            this.control.setVisible(false);
        }
    }
    
    void releaseWidget() {
        super.releaseWidget();
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
        if (this.control != null && this.control.isDisposed()) {
            this.control = null;
        }
        final Control control2 = this.control;
        this.control = control;
        final int index = this.parent.indexOf(this);
        final int selectionIndex = this.parent.getSelectionIndex();
        if (index == selectionIndex || control == null) {
            if (control != null) {
                control.setBounds(this.parent.getClientArea());
                control.setVisible(true);
            }
            if (control2 != null) {
                control2.setVisible(false);
            }
            return;
        }
        if (selectionIndex != -1 && this.parent.getItem(selectionIndex).getControl() == control) {
            return;
        }
        control.setVisible(false);
    }
    
    public void setImage(final Image image) {
        this.checkWidget();
        final int index = this.parent.indexOf(this);
        if (index == -1) {
            return;
        }
        super.setImage(image);
        if (OS.COMCTL32_MAJOR >= 6 && this.text.indexOf(38) != -1) {
            this._setText(index, this.text);
        }
        final int handle = this.parent.handle;
        final TCITEM tcitem = new TCITEM();
        tcitem.mask = 2;
        tcitem.iImage = this.parent.imageIndex(image);
        OS.SendMessage(handle, OS.TCM_SETITEM, index, tcitem);
    }
    
    public void setText(final String text) {
        this.checkWidget();
        if (text == null) {
            this.error(4);
        }
        if (text.equals(this.text)) {
            return;
        }
        final int index = this.parent.indexOf(this);
        if (index == -1) {
            return;
        }
        super.setText(text);
        this._setText(index, text);
    }
    
    public void setToolTipText(final String toolTipText) {
        this.checkWidget();
        this.toolTipText = toolTipText;
    }
}
