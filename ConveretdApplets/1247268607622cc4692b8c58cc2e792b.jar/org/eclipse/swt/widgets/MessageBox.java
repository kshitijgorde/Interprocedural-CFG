// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.widgets;

import org.eclipse.swt.internal.win32.TCHAR;
import org.eclipse.swt.internal.win32.OS;

public class MessageBox extends Dialog
{
    String message;
    
    public MessageBox(final Shell shell) {
        this(shell, 65570);
    }
    
    public MessageBox(final Shell shell, final int n) {
        super(shell, Dialog.checkStyle(shell, checkStyle(n)));
        this.message = "";
        this.checkSubclass();
    }
    
    static int checkStyle(int n) {
        final int n2 = 4064;
        final int n3 = n & n2;
        if (n3 == 32 || n3 == 256 || n3 == 288) {
            return n;
        }
        if (n3 == 64 || n3 == 128 || n3 == 192 || n3 == 448) {
            return n;
        }
        if (n3 == 1280 || n3 == 3584) {
            return n;
        }
        n = ((n & ~n2) | 0x20);
        return n;
    }
    
    public String getMessage() {
        return this.message;
    }
    
    public int open() {
        int n = 0;
        if ((this.style & 0x20) == 0x20) {
            n = 0;
        }
        if ((this.style & 0x120) == 0x120) {
            n = 1;
        }
        if ((this.style & 0xC0) == 0xC0) {
            n = 4;
        }
        if ((this.style & 0x1C0) == 0x1C0) {
            n = 3;
        }
        if ((this.style & 0x500) == 0x500) {
            n = 5;
        }
        if ((this.style & 0xE00) == 0xE00) {
            n = 2;
        }
        if (n == 0) {
            n = 0;
        }
        int n2 = 0;
        if ((this.style & 0x1) != 0x0) {
            n2 = 16;
        }
        if ((this.style & 0x2) != 0x0) {
            n2 = 64;
        }
        if ((this.style & 0x4) != 0x0) {
            n2 = 32;
        }
        if ((this.style & 0x8) != 0x0) {
            n2 = 48;
        }
        if ((this.style & 0x10) != 0x0) {
            n2 = 64;
        }
        int n3 = 0;
        if (OS.IsWinCE) {
            if ((this.style & 0x38000) != 0x0) {
                n3 = 0;
            }
        }
        else {
            if ((this.style & 0x8000) != 0x0) {
                n3 = 0;
            }
            if ((this.style & 0x10000) != 0x0) {
                n3 = 8192;
            }
            if ((this.style & 0x20000) != 0x0) {
                n3 = 4096;
            }
        }
        int n4 = n | n2 | n3;
        if ((this.style & 0x4000000) != 0x0) {
            n4 |= 0x180000;
        }
        if ((this.style & 0x6000000) == 0x0 && this.parent != null && (this.parent.style & 0x8000000) != 0x0) {
            n4 |= 0x180000;
        }
        if ((n4 & 0x1000) != 0x0) {
            n4 = (((n4 | 0x2000) & 0xFFFFEFFF) | 0x40000);
        }
        final int n5 = (this.parent != null) ? this.parent.handle : 0;
        Dialog modalDialog = null;
        Display display = null;
        if ((n4 & 0x2000) != 0x0) {
            display = this.parent.getDisplay();
            modalDialog = display.getModalDialog();
            display.setModalDialog(this);
        }
        final int messageBox = OS.MessageBox(n5, new TCHAR(0, this.message, true), new TCHAR(0, this.title, true), n4);
        if ((n4 & 0x2000) != 0x0) {
            display.setModalDialog(modalDialog);
        }
        if (messageBox != 0) {
            final int n6 = n4 & 0xF;
            if (n6 == 0) {
                return 32;
            }
            if (n6 == 1) {
                return (messageBox == 1) ? 32 : 256;
            }
            if (n6 == 4) {
                return (messageBox == 6) ? 64 : 128;
            }
            if (n6 == 3) {
                if (messageBox == 6) {
                    return 64;
                }
                if (messageBox == 7) {
                    return 128;
                }
                return 256;
            }
            else {
                if (n6 == 5) {
                    return (messageBox == 4) ? 1024 : 256;
                }
                if (n6 == 2) {
                    if (messageBox == 4) {
                        return 1024;
                    }
                    if (messageBox == 3) {
                        return 512;
                    }
                    return 2048;
                }
            }
        }
        return 256;
    }
    
    public void setMessage(final String message) {
        if (message == null) {
            this.error(4);
        }
        this.message = message;
    }
}
