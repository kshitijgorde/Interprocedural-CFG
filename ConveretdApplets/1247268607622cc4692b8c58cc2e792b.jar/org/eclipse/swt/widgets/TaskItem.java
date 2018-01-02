// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.widgets;

import org.eclipse.swt.internal.win32.LOGFONT;
import org.eclipse.swt.internal.win32.ICONINFO;
import org.eclipse.swt.internal.win32.NONCLIENTMETRICS;
import org.eclipse.swt.internal.win32.NONCLIENTMETRICSA;
import org.eclipse.swt.internal.win32.NONCLIENTMETRICSW;
import org.eclipse.swt.internal.win32.TCHAR;
import org.eclipse.swt.internal.win32.RECT;
import org.eclipse.swt.internal.win32.BITMAPINFOHEADER;
import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;

public class TaskItem extends Item
{
    TaskBar parent;
    Shell shell;
    int progress;
    int progressState;
    Image overlayImage;
    String overlayText;
    boolean showingText;
    Menu menu;
    static final int PROGRESS_MAX = 100;
    
    TaskItem(final TaskBar parent, final int n) {
        super(parent, n);
        this.progressState = -1;
        this.overlayText = "";
        this.showingText = false;
        (this.parent = parent).createItem(this, -1);
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
    
    public Menu getMenu() {
        this.checkWidget();
        return this.menu;
    }
    
    public Image getOverlayImage() {
        this.checkWidget();
        return this.overlayImage;
    }
    
    public String getOverlayText() {
        this.checkWidget();
        return this.overlayText;
    }
    
    public TaskBar getParent() {
        this.checkWidget();
        return this.parent;
    }
    
    public int getProgress() {
        this.checkWidget();
        return this.progress;
    }
    
    public int getProgressState() {
        this.checkWidget();
        return this.progressState;
    }
    
    void recreate() {
        if (this.showingText) {
            if (this.overlayText.length() != 0) {
                this.updateText();
            }
        }
        else if (this.overlayImage != null) {
            this.updateImage();
        }
        if (this.progress != 0) {
            this.updateProgress();
        }
        if (this.progressState != -1) {
            this.updateProgressState();
        }
    }
    
    void releaseHandle() {
        super.releaseHandle();
        this.parent = null;
    }
    
    void releaseWidget() {
        super.releaseWidget();
        this.overlayImage = null;
        this.overlayText = null;
    }
    
    public void setMenu(final Menu menu) {
        this.checkWidget();
        if (menu != null) {
            if (menu.isDisposed()) {
                SWT.error(5);
            }
            if ((menu.style & 0x8) == 0x0) {
                this.error(37);
            }
        }
        if (this.shell != null) {
            return;
        }
        this.menu = menu;
        this.parent.setMenu(menu);
    }
    
    public void setOverlayImage(final Image overlayImage) {
        this.checkWidget();
        if (overlayImage != null && overlayImage.isDisposed()) {
            this.error(5);
        }
        if (this.shell == null) {
            return;
        }
        if ((this.overlayImage = overlayImage) != null) {
            this.updateImage();
        }
        else if (this.overlayText.length() != 0) {
            this.updateText();
        }
        else {
            OS.VtblCall(18, this.parent.mTaskbarList3, this.shell.handle, 0, 0);
        }
    }
    
    public void setOverlayText(final String overlayText) {
        this.checkWidget();
        if (overlayText == null) {
            this.error(4);
        }
        if (this.shell == null) {
            return;
        }
        this.overlayText = overlayText;
        if (overlayText.length() != 0) {
            this.updateText();
        }
        else if (this.overlayImage != null) {
            this.updateImage();
        }
        else {
            OS.VtblCall(18, this.parent.mTaskbarList3, this.shell.handle, 0, 0);
        }
    }
    
    public void setProgress(int max) {
        this.checkWidget();
        if (this.shell == null) {
            return;
        }
        max = Math.max(0, Math.min(max, 100));
        if (this.progress == max) {
            return;
        }
        this.progress = max;
        this.updateProgress();
    }
    
    public void setProgressState(final int progressState) {
        this.checkWidget();
        if (this.shell == null) {
            return;
        }
        if (this.progressState == progressState) {
            return;
        }
        this.progressState = progressState;
        this.updateProgressState();
    }
    
    void setShell(final Shell shell) {
        (this.shell = shell).addListener(12, new Listener() {
            public void handleEvent(final Event event) {
                if (TaskItem.this.isDisposed()) {
                    return;
                }
                TaskItem.this.dispose();
            }
        });
    }
    
    void updateImage() {
        this.showingText = false;
        Image icon = null;
        int n = 0;
        switch (this.overlayImage.type) {
            case 0: {
                icon = Display.createIcon(this.overlayImage);
                n = icon.handle;
                break;
            }
            case 1: {
                n = this.overlayImage.handle;
                break;
            }
        }
        OS.VtblCall(18, this.parent.mTaskbarList3, this.shell.handle, n, 0);
        if (icon != null) {
            icon.dispose();
        }
    }
    
    void updateProgress() {
        if (this.progressState == 2) {
            return;
        }
        if (this.progressState == -1) {
            return;
        }
        OS.VtblCall(9, this.parent.mTaskbarList3, this.shell.handle, this.progress, 100L);
    }
    
    void updateProgressState() {
        int n = 0;
        switch (this.progressState) {
            case 0: {
                n = 2;
                break;
            }
            case 1: {
                n = 4;
                break;
            }
            case 4: {
                n = 8;
                break;
            }
            case 2: {
                n = 1;
                break;
            }
        }
        final int mTaskbarList3 = this.parent.mTaskbarList3;
        final int handle = this.shell.handle;
        OS.VtblCall(9, mTaskbarList3, handle, this.progress, 100L);
        OS.VtblCall(10, mTaskbarList3, handle, n);
    }
    
    void updateText() {
        this.showingText = true;
        final int biWidth = 16;
        final int n = 16;
        final int getDC = OS.GetDC(0);
        final BITMAPINFOHEADER bitmapinfoheader = new BITMAPINFOHEADER();
        bitmapinfoheader.biSize = BITMAPINFOHEADER.sizeof;
        bitmapinfoheader.biWidth = biWidth;
        bitmapinfoheader.biHeight = -n;
        bitmapinfoheader.biPlanes = 1;
        bitmapinfoheader.biBitCount = 32;
        bitmapinfoheader.biCompression = 0;
        final byte[] array = new byte[BITMAPINFOHEADER.sizeof];
        OS.MoveMemory(array, bitmapinfoheader, BITMAPINFOHEADER.sizeof);
        final int createDIBSection = OS.CreateDIBSection(0, array, 0, new int[1], 0, 0);
        if (createDIBSection == 0) {
            SWT.error(2);
        }
        final int createCompatibleDC = OS.CreateCompatibleDC(getDC);
        final int selectObject = OS.SelectObject(createCompatibleDC, createDIBSection);
        final int createBitmap = OS.CreateBitmap(biWidth, n, 1, 1, null);
        if (createBitmap == 0) {
            SWT.error(2);
        }
        final int createCompatibleDC2 = OS.CreateCompatibleDC(getDC);
        final int selectObject2 = OS.SelectObject(createCompatibleDC2, createBitmap);
        OS.PatBlt(createCompatibleDC2, 0, 0, biWidth, n, 16711778);
        final int selectObject3 = OS.SelectObject(createCompatibleDC2, OS.GetStockObject(4));
        OS.RoundRect(createCompatibleDC2, 0, 0, biWidth, n, 8, 8);
        OS.SelectObject(createCompatibleDC2, selectObject3);
        final int createSolidBrush = OS.CreateSolidBrush(OS.GetSysColor(OS.COLOR_HIGHLIGHT));
        final int selectObject4 = OS.SelectObject(createCompatibleDC, createSolidBrush);
        OS.RoundRect(createCompatibleDC, 0, 0, biWidth, n, 8, 8);
        OS.SelectObject(createCompatibleDC, selectObject4);
        OS.DeleteObject(createSolidBrush);
        final int n2 = 2080;
        final RECT rect = new RECT();
        final TCHAR tchar = new TCHAR(this.shell.getCodePage(), this.overlayText, false);
        final int length = tchar.length();
        int n3 = 0;
        int selectObject5 = 0;
        final NONCLIENTMETRICS nonclientmetrics = OS.IsUnicode ? new NONCLIENTMETRICSW() : new NONCLIENTMETRICSA();
        nonclientmetrics.cbSize = NONCLIENTMETRICS.sizeof;
        if (OS.SystemParametersInfo(41, 0, nonclientmetrics, 0)) {
            final LOGFONT logfont = OS.IsUnicode ? ((NONCLIENTMETRICSW)nonclientmetrics).lfMessageFont : ((NONCLIENTMETRICSA)nonclientmetrics).lfMessageFont;
            logfont.lfHeight = -10;
            n3 = OS.CreateFontIndirect(logfont);
            selectObject5 = OS.SelectObject(createCompatibleDC, n3);
            OS.DrawText(createCompatibleDC, tchar, length, rect, n2 | 0x400);
            if (rect.right > biWidth - 2) {
                OS.SelectObject(createCompatibleDC, selectObject5);
                OS.DeleteObject(n3);
                logfont.lfHeight = -8;
                n3 = OS.CreateFontIndirect(logfont);
                OS.SelectObject(createCompatibleDC, n3);
            }
        }
        OS.DrawText(createCompatibleDC, tchar, length, rect, n2 | 0x400);
        OS.OffsetRect(rect, (biWidth - rect.right) / 2, (n - rect.bottom) / 2);
        final int setBkMode = OS.SetBkMode(createCompatibleDC, 1);
        OS.SetTextColor(createCompatibleDC, OS.GetSysColor(OS.COLOR_HIGHLIGHTTEXT));
        OS.DrawText(createCompatibleDC, tchar, length, rect, n2);
        if (n3 != 0) {
            OS.SelectObject(createCompatibleDC, selectObject5);
            OS.DeleteObject(n3);
        }
        OS.SetBkMode(createCompatibleDC, setBkMode);
        OS.SelectObject(createCompatibleDC, selectObject);
        OS.DeleteDC(createCompatibleDC);
        OS.SelectObject(createCompatibleDC2, selectObject2);
        OS.DeleteDC(createCompatibleDC2);
        OS.ReleaseDC(0, getDC);
        final ICONINFO iconinfo = new ICONINFO();
        iconinfo.fIcon = true;
        iconinfo.hbmColor = createDIBSection;
        iconinfo.hbmMask = createBitmap;
        final int createIconIndirect = OS.CreateIconIndirect(iconinfo);
        if (createIconIndirect == 0) {
            SWT.error(2);
        }
        OS.DeleteObject(createDIBSection);
        OS.DeleteObject(createBitmap);
        OS.VtblCall(18, this.parent.mTaskbarList3, this.shell.handle, createIconIndirect, 0);
        OS.DestroyIcon(createIconIndirect);
    }
}
