// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.widgets;

import org.eclipse.swt.internal.win32.CHOOSECOLOR;
import org.eclipse.swt.SWT;
import org.eclipse.swt.internal.Callback;
import org.eclipse.swt.internal.win32.CREATESTRUCT;
import org.eclipse.swt.internal.win32.TCHAR;
import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.internal.win32.RECT;
import org.eclipse.swt.graphics.RGB;

public class ColorDialog extends Dialog
{
    Display display;
    int width;
    int height;
    RGB rgb;
    
    public ColorDialog(final Shell shell) {
        this(shell, 65536);
    }
    
    public ColorDialog(final Shell shell, final int n) {
        super(shell, Dialog.checkStyle(shell, n));
        this.checkSubclass();
    }
    
    int CCHookProc(final int n, final int n2, final int n3, final int n4) {
        switch (n2) {
            case 272: {
                final RECT rect = new RECT();
                OS.GetWindowRect(n, rect);
                this.width = rect.right - rect.left;
                this.height = rect.bottom - rect.top;
                if (this.title != null && this.title.length() != 0) {
                    OS.SetWindowText(n, new TCHAR(0, this.title, true));
                    break;
                }
                break;
            }
            case 2: {
                final RECT rect2 = new RECT();
                OS.GetWindowRect(n, rect2);
                final int n5 = rect2.right - rect2.left;
                final int n6 = rect2.bottom - rect2.top;
                if (n5 < this.width || n6 < this.height || n5 <= this.width) {}
                break;
            }
        }
        return 0;
    }
    
    public RGB getRGB() {
        return this.rgb;
    }
    
    public RGB open() {
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
        final Callback callback = new Callback(this, "CCHookProc", 4);
        final int address = callback.getAddress();
        if (address == 0) {
            SWT.error(3);
        }
        this.display = this.parent.display;
        if (this.display.lpCustColors == 0) {
            this.display.lpCustColors = OS.HeapAlloc(OS.GetProcessHeap(), 8, 64);
        }
        final CHOOSECOLOR choosecolor = new CHOOSECOLOR();
        choosecolor.lStructSize = CHOOSECOLOR.sizeof;
        choosecolor.Flags = 272;
        choosecolor.lpfnHook = address;
        choosecolor.hwndOwner = hwndOwner;
        choosecolor.lpCustColors = this.display.lpCustColors;
        if (this.rgb != null) {
            final CHOOSECOLOR choosecolor2 = choosecolor;
            choosecolor2.Flags |= 0x1;
            choosecolor.rgbResult = ((this.rgb.red & 0xFF) | (this.rgb.green << 8 & 0xFF00) | (this.rgb.blue << 16 & 0xFF0000));
        }
        Dialog modalDialog = null;
        if ((this.style & 0x30000) != 0x0) {
            modalDialog = this.display.getModalDialog();
            this.display.setModalDialog(this);
        }
        final boolean chooseColor = OS.ChooseColor(choosecolor);
        if ((this.style & 0x30000) != 0x0) {
            this.display.setModalDialog(modalDialog);
        }
        if (chooseColor) {
            this.rgb = new RGB(choosecolor.rgbResult & 0xFF, choosecolor.rgbResult >> 8 & 0xFF, choosecolor.rgbResult >> 16 & 0xFF);
        }
        callback.dispose();
        if (handle != hwndOwner) {
            if (isWindowEnabled) {
                OS.EnableWindow(handle, true);
            }
            OS.SetActiveWindow(handle);
            OS.DestroyWindow(hwndOwner);
        }
        this.display = null;
        if (!chooseColor) {
            return null;
        }
        return this.rgb;
    }
    
    public void setRGB(final RGB rgb) {
        this.rgb = rgb;
    }
}
