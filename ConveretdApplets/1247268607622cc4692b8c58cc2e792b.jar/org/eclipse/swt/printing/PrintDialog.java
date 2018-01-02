// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.printing;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.internal.win32.DEVMODE;
import org.eclipse.swt.internal.win32.DEVMODEA;
import org.eclipse.swt.internal.win32.DEVMODEW;
import org.eclipse.swt.internal.win32.PRINTDLG;
import org.eclipse.swt.internal.win32.CREATESTRUCT;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.internal.win32.TCHAR;
import org.eclipse.swt.widgets.Dialog;

public class PrintDialog extends Dialog
{
    static final TCHAR DialogClass;
    PrinterData printerData;
    static /* synthetic */ Class class$0;
    
    static {
        DialogClass = new TCHAR(0, OS.IsWinCE ? "Dialog" : "#32770", true);
    }
    
    public PrintDialog(final Shell shell) {
        this(shell, 32768);
    }
    
    public PrintDialog(final Shell shell, final int n) {
        super(shell, checkStyle(shell, n));
        this.printerData = new PrinterData();
        this.checkSubclass();
    }
    
    static int checkBits(int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7) {
        final int n8 = n2 | n3 | n4 | n5 | n6 | n7;
        if ((n & n8) == 0x0) {
            n |= n2;
        }
        if ((n & n2) != 0x0) {
            n = ((n & ~n8) | n2);
        }
        if ((n & n3) != 0x0) {
            n = ((n & ~n8) | n3);
        }
        if ((n & n4) != 0x0) {
            n = ((n & ~n8) | n4);
        }
        if ((n & n5) != 0x0) {
            n = ((n & ~n8) | n5);
        }
        if ((n & n6) != 0x0) {
            n = ((n & ~n8) | n6);
        }
        if ((n & n7) != 0x0) {
            n = ((n & ~n8) | n7);
        }
        return n;
    }
    
    static int checkStyle(final Shell shell, int n) {
        final int n2 = 229376;
        if ((n & 0x10000000) != 0x0) {
            n &= 0xEFFFFFFF;
            if ((n & n2) == 0x0) {
                n |= ((shell == null) ? 65536 : 32768);
            }
        }
        if ((n & n2) == 0x0) {
            n |= 0x10000;
        }
        n &= 0xF7FFFFFF;
        if ((n & 0x6000000) == 0x0 && shell != null) {
            if ((shell.getStyle() & 0x2000000) != 0x0) {
                n |= 0x2000000;
            }
            if ((shell.getStyle() & 0x4000000) != 0x0) {
                n |= 0x4000000;
            }
        }
        return checkBits(n, 33554432, 67108864, 0, 0, 0, 0);
    }
    
    public void setPrinterData(PrinterData printerData) {
        if (printerData == null) {
            printerData = new PrinterData();
        }
        this.printerData = printerData;
    }
    
    public PrinterData getPrinterData() {
        return this.printerData;
    }
    
    public int getScope() {
        return this.printerData.scope;
    }
    
    public void setScope(final int scope) {
        this.printerData.scope = scope;
    }
    
    public int getStartPage() {
        return this.printerData.startPage;
    }
    
    public void setStartPage(final int startPage) {
        this.printerData.startPage = startPage;
    }
    
    public int getEndPage() {
        return this.printerData.endPage;
    }
    
    public void setEndPage(final int endPage) {
        this.printerData.endPage = endPage;
    }
    
    public boolean getPrintToFile() {
        return this.printerData.printToFile;
    }
    
    public void setPrintToFile(final boolean printToFile) {
        this.printerData.printToFile = printToFile;
    }
    
    protected void checkSubclass() {
        final String name = this.getClass().getName();
        Class class$0;
        if ((class$0 = PrintDialog.class$0) == null) {
            try {
                class$0 = (PrintDialog.class$0 = Class.forName("org.eclipse.swt.printing.PrintDialog"));
            }
            catch (ClassNotFoundException ex) {
                throw new NoClassDefFoundError(ex.getMessage());
            }
        }
        if (!class$0.getName().equals(name)) {
            SWT.error(43);
        }
    }
    
    public PrinterData open() {
        final Shell parent = this.getParent();
        final int style = this.getStyle();
        int hwndOwner = parent.handle;
        final int handle = parent.handle;
        boolean isWindowEnabled = false;
        if (!OS.IsWinCE && OS.WIN32_VERSION >= OS.VERSION(4, 10)) {
            final int n = style & 0x6000000;
            if (n != (parent.getStyle() & 0x6000000)) {
                int n2 = 1048576;
                if (n == 67108864) {
                    n2 |= 0x400000;
                }
                hwndOwner = OS.CreateWindowEx(n2, PrintDialog.DialogClass, null, 0, Integer.MIN_VALUE, 0, Integer.MIN_VALUE, 0, handle, 0, OS.GetModuleHandle(null), null);
                isWindowEnabled = OS.IsWindowEnabled(handle);
                if (isWindowEnabled) {
                    OS.EnableWindow(handle, false);
                }
            }
        }
        PrinterData printerData = null;
        final PRINTDLG printdlg = new PRINTDLG();
        printdlg.lStructSize = PRINTDLG.sizeof;
        printdlg.hwndOwner = hwndOwner;
        printdlg.Flags = 1024;
        if (OS.PrintDlg(printdlg)) {
            if (printdlg.hDevNames != 0) {
                OS.GlobalFree(printdlg.hDevNames);
                printdlg.hDevNames = 0;
            }
            final byte[] otherData = this.printerData.otherData;
            if (otherData != null && otherData.length != 0) {
                final int globalAlloc = OS.GlobalAlloc(64, otherData.length);
                OS.MoveMemory(globalAlloc, otherData, otherData.length);
                if (printdlg.hDevMode != 0) {
                    OS.GlobalFree(printdlg.hDevMode);
                }
                printdlg.hDevMode = globalAlloc;
            }
            final int hDevMode = printdlg.hDevMode;
            final int globalLock = OS.GlobalLock(hDevMode);
            final DEVMODE devmode = OS.IsUnicode ? new DEVMODEW() : new DEVMODEA();
            OS.MoveMemory(devmode, globalLock, OS.IsUnicode ? OS.DEVMODEW_sizeof() : OS.DEVMODEA_sizeof());
            final DEVMODE devmode2 = devmode;
            devmode2.dmFields |= 0x1;
            devmode.dmOrientation = (short)((this.printerData.orientation == 1) ? 1 : 2);
            if (this.printerData.copyCount != 1) {
                final DEVMODE devmode3 = devmode;
                devmode3.dmFields |= 0x100;
                devmode.dmCopies = (short)this.printerData.copyCount;
            }
            if (this.printerData.collate) {
                final DEVMODE devmode4 = devmode;
                devmode4.dmFields |= 0x8000;
                devmode.dmCollate = 1;
            }
            if (this.printerData.duplex != -1) {
                final DEVMODE devmode5 = devmode;
                devmode5.dmFields |= 0x1000;
                switch (this.printerData.duplex) {
                    case 2: {
                        devmode.dmDuplex = 3;
                        break;
                    }
                    case 1: {
                        devmode.dmDuplex = 2;
                        break;
                    }
                    default: {
                        devmode.dmDuplex = 1;
                        break;
                    }
                }
            }
            OS.MoveMemory(globalLock, devmode, OS.IsUnicode ? OS.DEVMODEW_sizeof() : OS.DEVMODEA_sizeof());
            OS.GlobalUnlock(hDevMode);
            printdlg.Flags = 262144;
            if (this.printerData.printToFile) {
                final PRINTDLG printdlg2 = printdlg;
                printdlg2.Flags |= 0x20;
            }
            switch (this.printerData.scope) {
                case 1: {
                    final PRINTDLG printdlg3 = printdlg;
                    printdlg3.Flags |= 0x2;
                    break;
                }
                case 2: {
                    final PRINTDLG printdlg4 = printdlg;
                    printdlg4.Flags |= 0x1;
                    break;
                }
                default: {
                    final PRINTDLG printdlg5 = printdlg;
                    printdlg5.Flags |= 0x0;
                    break;
                }
            }
            printdlg.nMinPage = 1;
            printdlg.nMaxPage = -1;
            printdlg.nFromPage = (short)Math.min(65535, Math.max(1, this.printerData.startPage));
            printdlg.nToPage = (short)Math.min(65535, Math.max(1, this.printerData.endPage));
            final Display display = parent.getDisplay();
            final Shell[] shells = display.getShells();
            if ((this.getStyle() & 0x30000) != 0x0) {
                for (int i = 0; i < shells.length; ++i) {
                    if (shells[i].isEnabled() && shells[i] != parent) {
                        shells[i].setEnabled(false);
                    }
                    else {
                        shells[i] = null;
                    }
                }
            }
            final String s = "org.eclipse.swt.internal.win32.runMessagesInIdle";
            final Object data = display.getData(s);
            display.setData(s, new Boolean(true));
            final boolean printDlg = OS.PrintDlg(printdlg);
            display.setData(s, data);
            if ((this.getStyle() & 0x30000) != 0x0) {
                for (int j = 0; j < shells.length; ++j) {
                    if (shells[j] != null && !shells[j].isDisposed()) {
                        shells[j].setEnabled(true);
                    }
                }
            }
            if (printDlg) {
                final int hDevNames = printdlg.hDevNames;
                final int n3 = OS.GlobalSize(hDevNames) / TCHAR.sizeof * TCHAR.sizeof;
                final int globalLock2 = OS.GlobalLock(hDevNames);
                final short[] array = new short[4];
                OS.MoveMemory(array, globalLock2, 2 * array.length);
                final TCHAR tchar = new TCHAR(0, n3);
                OS.MoveMemory(tchar, globalLock2, n3);
                OS.GlobalUnlock(hDevNames);
                short n4;
                short n5;
                for (n4 = array[0], n5 = 0; n4 + n5 < n3 && tchar.tcharAt(n4 + n5) != 0; ++n5) {}
                final String string = tchar.toString(n4, n5);
                short n6;
                short n7;
                for (n6 = array[1], n7 = 0; n6 + n7 < n3 && tchar.tcharAt(n6 + n7) != 0; ++n7) {}
                final String string2 = tchar.toString(n6, n7);
                short n8;
                short n9;
                for (n8 = array[2], n9 = 0; n8 + n9 < n3 && tchar.tcharAt(n8 + n9) != 0; ++n9) {}
                final String string3 = tchar.toString(n8, n9);
                printerData = new PrinterData(string, string2);
                if ((printdlg.Flags & 0x2) != 0x0) {
                    printerData.scope = 1;
                    printerData.startPage = (printdlg.nFromPage & 0xFFFF);
                    printerData.endPage = (printdlg.nToPage & 0xFFFF);
                }
                else if ((printdlg.Flags & 0x1) != 0x0) {
                    printerData.scope = 2;
                }
                printerData.printToFile = ((printdlg.Flags & 0x20) != 0x0);
                if (printerData.printToFile) {
                    printerData.fileName = string3;
                }
                printerData.copyCount = printdlg.nCopies;
                printerData.collate = ((printdlg.Flags & 0x10) != 0x0);
                final int hDevMode2 = printdlg.hDevMode;
                final int globalSize = OS.GlobalSize(hDevMode2);
                final int globalLock3 = OS.GlobalLock(hDevMode2);
                OS.MoveMemory(printerData.otherData = new byte[globalSize], globalLock3, globalSize);
                final DEVMODE devmode6 = OS.IsUnicode ? new DEVMODEW() : new DEVMODEA();
                OS.MoveMemory(devmode6, globalLock3, OS.IsUnicode ? OS.DEVMODEW_sizeof() : OS.DEVMODEA_sizeof());
                if ((devmode6.dmFields & 0x1) != 0x0) {
                    printerData.orientation = ((devmode6.dmOrientation == 2) ? 2 : 1);
                }
                if ((devmode6.dmFields & 0x1000) != 0x0) {
                    final short dmDuplex = devmode6.dmDuplex;
                    printerData.duplex = ((dmDuplex == 1) ? 0 : ((dmDuplex == 3) ? 2 : 1));
                }
                OS.GlobalUnlock(hDevMode2);
                this.printerData = printerData;
            }
        }
        if (printdlg.hDevNames != 0) {
            OS.GlobalFree(printdlg.hDevNames);
            printdlg.hDevNames = 0;
        }
        if (printdlg.hDevMode != 0) {
            OS.GlobalFree(printdlg.hDevMode);
            printdlg.hDevMode = 0;
        }
        if (handle != hwndOwner) {
            if (isWindowEnabled) {
                OS.EnableWindow(handle, true);
            }
            OS.SetActiveWindow(handle);
            OS.DestroyWindow(hwndOwner);
        }
        return printerData;
    }
}
