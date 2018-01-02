// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.widgets;

import org.eclipse.swt.internal.win32.TEXTMETRIC;
import org.eclipse.swt.internal.win32.TEXTMETRICA;
import org.eclipse.swt.internal.win32.TEXTMETRICW;
import org.eclipse.swt.internal.win32.LOGFONTA;
import org.eclipse.swt.internal.win32.LOGFONTW;
import org.eclipse.swt.internal.win32.LOGFONT;
import org.eclipse.swt.internal.win32.CHOOSEFONT;
import org.eclipse.swt.internal.win32.CREATESTRUCT;
import org.eclipse.swt.internal.win32.TCHAR;
import org.eclipse.swt.SWT;
import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.FontData;

public class FontDialog extends Dialog
{
    FontData fontData;
    RGB rgb;
    
    public FontDialog(final Shell shell) {
        this(shell, 65536);
    }
    
    public FontDialog(final Shell shell, final int n) {
        super(shell, Dialog.checkStyle(shell, n));
        this.checkSubclass();
    }
    
    public FontData getFontData() {
        return this.fontData;
    }
    
    public FontData[] getFontList() {
        if (this.fontData == null) {
            return null;
        }
        return new FontData[] { this.fontData };
    }
    
    public RGB getRGB() {
        return this.rgb;
    }
    
    public FontData open() {
        if (OS.IsWinCE) {
            SWT.error(20);
        }
        int hwndOwner = this.parent.handle;
        final int handle = this.parent.handle;
        boolean isWindowEnabled = false;
        if (!OS.IsWinCE && OS.WIN32_VERSION >= OS.VERSION(4, 10)) {
            final int n = this.style & 0x6000000;
            if (n != (this.parent.style & 0x6000000)) {
                int n2 = 1048576;
                if (n == 67108864) {
                    n2 |= 0x400000;
                }
                hwndOwner = OS.CreateWindowEx(n2, Shell.DialogClass, null, 0, Integer.MIN_VALUE, 0, Integer.MIN_VALUE, 0, handle, 0, OS.GetModuleHandle(null), null);
                isWindowEnabled = OS.IsWindowEnabled(handle);
                if (isWindowEnabled) {
                    OS.EnableWindow(handle, false);
                }
            }
        }
        final int getProcessHeap = OS.GetProcessHeap();
        final CHOOSEFONT choosefont = new CHOOSEFONT();
        choosefont.lStructSize = CHOOSEFONT.sizeof;
        choosefont.hwndOwner = hwndOwner;
        choosefont.Flags = 257;
        final int heapAlloc = OS.HeapAlloc(getProcessHeap, 8, LOGFONT.sizeof);
        if (this.fontData != null && this.fontData.data != null) {
            final LOGFONT data = this.fontData.data;
            final int lfHeight = data.lfHeight;
            final int getDC = OS.GetDC(0);
            final int lfHeight2 = -(int)(0.5f + this.fontData.height * OS.GetDeviceCaps(getDC, 90) / 72.0f);
            OS.ReleaseDC(0, getDC);
            data.lfHeight = lfHeight2;
            final CHOOSEFONT choosefont2 = choosefont;
            choosefont2.Flags |= 0x40;
            OS.MoveMemory(heapAlloc, data, LOGFONT.sizeof);
            data.lfHeight = lfHeight;
        }
        choosefont.lpLogFont = heapAlloc;
        if (this.rgb != null) {
            choosefont.rgbColors = ((this.rgb.red & 0xFF) | (this.rgb.green << 8 & 0xFF00) | (this.rgb.blue << 16 & 0xFF0000));
        }
        Dialog modalDialog = null;
        Display display = null;
        if ((this.style & 0x30000) != 0x0) {
            display = this.parent.getDisplay();
            modalDialog = display.getModalDialog();
            display.setModalDialog(this);
        }
        final boolean chooseFont = OS.ChooseFont(choosefont);
        if ((this.style & 0x30000) != 0x0) {
            display.setModalDialog(modalDialog);
        }
        if (chooseFont) {
            final LOGFONT logfont = OS.IsUnicode ? new LOGFONTW() : new LOGFONTA();
            OS.MoveMemory(logfont, heapAlloc, LOGFONT.sizeof);
            final int getDC2 = OS.GetDC(0);
            final int getDeviceCaps = OS.GetDeviceCaps(getDC2, 90);
            int n3;
            if (logfont.lfHeight > 0) {
                final int createFontIndirect = OS.CreateFontIndirect(logfont);
                final int selectObject = OS.SelectObject(getDC2, createFontIndirect);
                final TEXTMETRIC textmetric = OS.IsUnicode ? new TEXTMETRICW() : new TEXTMETRICA();
                OS.GetTextMetrics(getDC2, textmetric);
                OS.SelectObject(getDC2, selectObject);
                OS.DeleteObject(createFontIndirect);
                n3 = logfont.lfHeight - textmetric.tmInternalLeading;
            }
            else {
                n3 = -logfont.lfHeight;
            }
            OS.ReleaseDC(0, getDC2);
            this.fontData = FontData.win32_new(logfont, n3 * 72.0f / getDeviceCaps);
            this.rgb = new RGB(choosefont.rgbColors & 0xFF, choosefont.rgbColors >> 8 & 0xFF, choosefont.rgbColors >> 16 & 0xFF);
        }
        if (heapAlloc != 0) {
            OS.HeapFree(getProcessHeap, 0, heapAlloc);
        }
        if (handle != hwndOwner) {
            if (isWindowEnabled) {
                OS.EnableWindow(handle, true);
            }
            OS.SetActiveWindow(handle);
            OS.DestroyWindow(hwndOwner);
        }
        if (!chooseFont) {
            return null;
        }
        return this.fontData;
    }
    
    public void setFontData(final FontData fontData) {
        this.fontData = fontData;
    }
    
    public void setFontList(final FontData[] array) {
        if (array != null && array.length > 0) {
            this.fontData = array[0];
        }
        else {
            this.fontData = null;
        }
    }
    
    public void setRGB(final RGB rgb) {
        this.rgb = rgb;
    }
}
