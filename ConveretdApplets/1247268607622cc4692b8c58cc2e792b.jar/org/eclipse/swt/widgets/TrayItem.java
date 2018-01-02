// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.widgets;

import org.eclipse.swt.internal.win32.TCHAR;
import org.eclipse.swt.internal.win32.NOTIFYICONDATA;
import org.eclipse.swt.internal.win32.NOTIFYICONDATAA;
import org.eclipse.swt.internal.win32.NOTIFYICONDATAW;
import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.events.MenuDetectListener;
import org.eclipse.swt.internal.SWTEventListener;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;

public class TrayItem extends Item
{
    Tray parent;
    int id;
    Image image2;
    ToolTip toolTip;
    String toolTipText;
    boolean visible;
    
    public TrayItem(final Tray parent, final int n) {
        super(parent, n);
        this.visible = true;
        (this.parent = parent).createItem(this, parent.getItemCount());
        this.createWidget();
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
    
    public void addMenuDetectListener(final MenuDetectListener menuDetectListener) {
        this.checkWidget();
        if (menuDetectListener == null) {
            this.error(4);
        }
        this.addListener(35, new TypedListener(menuDetectListener));
    }
    
    protected void checkSubclass() {
        if (!this.isValidSubclass()) {
            this.error(43);
        }
    }
    
    void createWidget() {
        final NOTIFYICONDATA notifyicondata = OS.IsUnicode ? new NOTIFYICONDATAW() : new NOTIFYICONDATAA();
        notifyicondata.cbSize = NOTIFYICONDATA.sizeof;
        final NOTIFYICONDATA notifyicondata2 = notifyicondata;
        final int n = this.display.nextTrayId++;
        this.id = n;
        notifyicondata2.uID = n;
        notifyicondata.hWnd = this.display.hwndMessage;
        notifyicondata.uFlags = 1;
        notifyicondata.uCallbackMessage = 32772;
        OS.Shell_NotifyIcon(0, notifyicondata);
    }
    
    void destroyWidget() {
        this.parent.destroyItem(this);
        this.releaseHandle();
    }
    
    public Tray getParent() {
        this.checkWidget();
        return this.parent;
    }
    
    public ToolTip getToolTip() {
        this.checkWidget();
        return this.toolTip;
    }
    
    public String getToolTipText() {
        this.checkWidget();
        return this.toolTipText;
    }
    
    public boolean getVisible() {
        this.checkWidget();
        return this.visible;
    }
    
    int messageProc(final int n, final int n2, final int n3, final int n4) {
        switch (n4) {
            case 513: {
                if (this.hooks(13)) {
                    OS.SetForegroundWindow(n);
                    this.sendSelectionEvent(13);
                    break;
                }
                break;
            }
            case 515:
            case 518: {
                if (this.hooks(14)) {
                    OS.SetForegroundWindow(n);
                    this.sendSelectionEvent(14);
                    break;
                }
                break;
            }
            case 517: {
                if (!this.hooks(35)) {
                    break;
                }
                OS.SetForegroundWindow(n);
                this.sendEvent(35);
                if (this.isDisposed()) {
                    return 0;
                }
                break;
            }
            case 1026: {
                if (this.toolTip == null || this.toolTip.visible) {
                    break;
                }
                this.toolTip.visible = true;
                if (!this.toolTip.hooks(22)) {
                    break;
                }
                OS.SetForegroundWindow(n);
                this.toolTip.sendEvent(22);
                if (this.isDisposed()) {
                    return 0;
                }
                break;
            }
            case 1027:
            case 1028:
            case 1029: {
                if (this.toolTip == null) {
                    break;
                }
                if (this.toolTip.visible) {
                    this.toolTip.visible = false;
                    if (this.toolTip.hooks(23)) {
                        OS.SetForegroundWindow(n);
                        this.toolTip.sendEvent(23);
                        if (this.isDisposed()) {
                            return 0;
                        }
                    }
                }
                if (n4 != 1029 || !this.toolTip.hooks(13)) {
                    break;
                }
                OS.SetForegroundWindow(n);
                this.toolTip.sendSelectionEvent(13);
                if (this.isDisposed()) {
                    return 0;
                }
                break;
            }
        }
        this.display.wakeThread();
        return 0;
    }
    
    void recreate() {
        this.createWidget();
        if (!this.visible) {
            this.setVisible(false);
        }
        if (this.text.length() != 0) {
            this.setText(this.text);
        }
        if (this.image != null) {
            this.setImage(this.image);
        }
        if (this.toolTipText != null) {
            this.setToolTipText(this.toolTipText);
        }
    }
    
    void releaseHandle() {
        super.releaseHandle();
        this.parent = null;
    }
    
    void releaseWidget() {
        super.releaseWidget();
        if (this.toolTip != null) {
            this.toolTip.item = null;
        }
        this.toolTip = null;
        if (this.image2 != null) {
            this.image2.dispose();
        }
        this.image2 = null;
        this.toolTipText = null;
        final NOTIFYICONDATA notifyicondata = OS.IsUnicode ? new NOTIFYICONDATAW() : new NOTIFYICONDATAA();
        notifyicondata.cbSize = NOTIFYICONDATA.sizeof;
        notifyicondata.uID = this.id;
        notifyicondata.hWnd = this.display.hwndMessage;
        OS.Shell_NotifyIcon(2, notifyicondata);
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
    
    public void removeMenuDetectListener(final MenuDetectListener menuDetectListener) {
        this.checkWidget();
        if (menuDetectListener == null) {
            this.error(4);
        }
        if (this.eventTable == null) {
            return;
        }
        this.eventTable.unhook(35, menuDetectListener);
    }
    
    public void setImage(final Image image) {
        this.checkWidget();
        if (image != null && image.isDisposed()) {
            this.error(5);
        }
        super.setImage(image);
        if (this.image2 != null) {
            this.image2.dispose();
        }
        this.image2 = null;
        int hIcon = 0;
        if (image != null) {
            switch (image.type) {
                case 0: {
                    this.image2 = Display.createIcon(image);
                    hIcon = this.image2.handle;
                    break;
                }
                case 1: {
                    hIcon = image.handle;
                    break;
                }
            }
        }
        final NOTIFYICONDATA notifyicondata = OS.IsUnicode ? new NOTIFYICONDATAW() : new NOTIFYICONDATAA();
        notifyicondata.cbSize = NOTIFYICONDATA.sizeof;
        notifyicondata.uID = this.id;
        notifyicondata.hWnd = this.display.hwndMessage;
        notifyicondata.hIcon = hIcon;
        notifyicondata.uFlags = 2;
        OS.Shell_NotifyIcon(1, notifyicondata);
    }
    
    public void setToolTip(final ToolTip toolTip) {
        this.checkWidget();
        final ToolTip toolTip2 = this.toolTip;
        if (toolTip2 != null) {
            toolTip2.item = null;
        }
        if ((this.toolTip = toolTip) != null) {
            toolTip.item = this;
        }
    }
    
    public void setToolTipText(final String toolTipText) {
        this.checkWidget();
        this.toolTipText = toolTipText;
        final NOTIFYICONDATA notifyicondata = OS.IsUnicode ? new NOTIFYICONDATAW() : new NOTIFYICONDATAA();
        final TCHAR tchar = new TCHAR(0, (this.toolTipText == null) ? "" : this.toolTipText, true);
        final int n = (OS.SHELL32_MAJOR < 5) ? 64 : 128;
        if (OS.IsUnicode) {
            System.arraycopy(tchar.chars, 0, ((NOTIFYICONDATAW)notifyicondata).szTip, 0, Math.min(n - 1, tchar.length()));
        }
        else {
            System.arraycopy(tchar.bytes, 0, ((NOTIFYICONDATAA)notifyicondata).szTip, 0, Math.min(n - 1, tchar.length()));
        }
        notifyicondata.cbSize = NOTIFYICONDATA.sizeof;
        notifyicondata.uID = this.id;
        notifyicondata.hWnd = this.display.hwndMessage;
        notifyicondata.uFlags = 4;
        OS.Shell_NotifyIcon(1, notifyicondata);
    }
    
    public void setVisible(final boolean visible) {
        this.checkWidget();
        if (this.visible == visible) {
            return;
        }
        if (visible) {
            this.sendEvent(22);
            if (this.isDisposed()) {
                return;
            }
        }
        this.visible = visible;
        final NOTIFYICONDATA notifyicondata = OS.IsUnicode ? new NOTIFYICONDATAW() : new NOTIFYICONDATAA();
        notifyicondata.cbSize = NOTIFYICONDATA.sizeof;
        notifyicondata.uID = this.id;
        notifyicondata.hWnd = this.display.hwndMessage;
        if (OS.SHELL32_MAJOR < 5) {
            if (visible) {
                notifyicondata.uFlags = 1;
                notifyicondata.uCallbackMessage = 32772;
                OS.Shell_NotifyIcon(0, notifyicondata);
                this.setImage(this.image);
                this.setToolTipText(this.toolTipText);
            }
            else {
                OS.Shell_NotifyIcon(2, notifyicondata);
            }
        }
        else {
            notifyicondata.uFlags = 8;
            notifyicondata.dwState = (visible ? 0 : 1);
            OS.Shell_NotifyIcon(notifyicondata.dwStateMask = 1, notifyicondata);
        }
        if (!visible) {
            this.sendEvent(23);
        }
    }
}
