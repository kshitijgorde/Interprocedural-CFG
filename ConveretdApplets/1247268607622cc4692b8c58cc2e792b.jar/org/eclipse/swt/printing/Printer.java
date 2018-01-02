// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.printing;

import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.internal.win32.DOCINFO;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.GCData;
import org.eclipse.swt.internal.win32.DEVMODE;
import org.eclipse.swt.internal.win32.DEVMODEA;
import org.eclipse.swt.internal.win32.DEVMODEW;
import org.eclipse.swt.internal.win32.PRINTDLG;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.DeviceData;
import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.internal.win32.TCHAR;
import org.eclipse.swt.graphics.Device;

public final class Printer extends Device
{
    public int handle;
    PrinterData data;
    boolean isGCCreated;
    static TCHAR profile;
    static TCHAR appName;
    static TCHAR keyName;
    
    static {
        Printer.profile = new TCHAR(0, "PrinterPorts", true);
        Printer.appName = new TCHAR(0, "windows", true);
        Printer.keyName = new TCHAR(0, "device", true);
    }
    
    public static PrinterData[] getPrinterList() {
        final int n = 1024;
        final TCHAR tchar = new TCHAR(0, n);
        final TCHAR tchar2 = new TCHAR(0, 1);
        final int getProfileString = OS.GetProfileString(Printer.profile, null, tchar2, tchar, n);
        if (getProfileString == 0) {
            return new PrinterData[0];
        }
        String[] array = new String[5];
        int n2 = 0;
        int n3 = 0;
        for (int i = 0; i < getProfileString; ++i) {
            if (tchar.tcharAt(i) == 0) {
                if (n2 == array.length) {
                    final String[] array2 = new String[array.length + 5];
                    System.arraycopy(array, 0, array2, 0, array.length);
                    array = array2;
                }
                array[n2] = tchar.toString(n3, i - n3);
                ++n2;
                n3 = i + 1;
            }
        }
        final PrinterData[] array3 = new PrinterData[n2];
        for (int j = 0; j < n2; ++j) {
            final String s = array[j];
            String string = "";
            if (OS.GetProfileString(Printer.profile, new TCHAR(0, s, true), tchar2, tchar, n) > 0) {
                int n4;
                for (n4 = 0; tchar.tcharAt(n4) != 44 && n4 < n; ++n4) {}
                if (n4 < n) {
                    string = tchar.toString(0, n4);
                }
            }
            array3[j] = new PrinterData(string, s);
        }
        return array3;
    }
    
    public static PrinterData getDefaultPrinterData() {
        String string = null;
        final int n = 1024;
        final TCHAR tchar = new TCHAR(0, n);
        final TCHAR tchar2 = new TCHAR(0, 1);
        if (OS.GetProfileString(Printer.appName, Printer.keyName, tchar2, tchar, n) == 0) {
            return null;
        }
        int n2;
        for (n2 = 0; tchar.tcharAt(n2) != 44 && n2 < n; ++n2) {}
        if (n2 < n) {
            string = tchar.toString(0, n2);
        }
        String string2 = "";
        if (OS.GetProfileString(Printer.profile, new TCHAR(0, string, true), tchar2, tchar, n) > 0) {
            int n3;
            for (n3 = 0; tchar.tcharAt(n3) != 44 && n3 < n; ++n3) {}
            if (n3 < n) {
                string2 = tchar.toString(0, n3);
            }
        }
        return new PrinterData(string2, string);
    }
    
    static DeviceData checkNull(PrinterData printerData) {
        if (printerData == null) {
            printerData = new PrinterData();
        }
        if (printerData.driver == null || printerData.name == null) {
            final PrinterData defaultPrinterData = getDefaultPrinterData();
            if (defaultPrinterData == null) {
                SWT.error(2);
            }
            printerData.driver = defaultPrinterData.driver;
            printerData.name = defaultPrinterData.name;
        }
        return printerData;
    }
    
    public Printer() {
        this(null);
    }
    
    public Printer(final PrinterData printerData) {
        super(checkNull(printerData));
        this.isGCCreated = false;
    }
    
    protected void create(final DeviceData deviceData) {
        this.data = (PrinterData)deviceData;
        final TCHAR tchar = new TCHAR(0, this.data.driver, true);
        final TCHAR tchar2 = new TCHAR(0, this.data.name, true);
        int n = 0;
        final byte[] otherData = this.data.otherData;
        final int getProcessHeap = OS.GetProcessHeap();
        if (otherData != null && otherData.length != 0) {
            n = OS.HeapAlloc(getProcessHeap, 8, otherData.length);
            OS.MoveMemory(n, otherData, otherData.length);
        }
        else {
            final PRINTDLG printdlg = new PRINTDLG();
            printdlg.lStructSize = PRINTDLG.sizeof;
            printdlg.Flags = 1024;
            OS.PrintDlg(printdlg);
            if (printdlg.hDevMode != 0) {
                final int hDevMode = printdlg.hDevMode;
                final int globalLock = OS.GlobalLock(hDevMode);
                final int globalSize = OS.GlobalSize(hDevMode);
                n = OS.HeapAlloc(getProcessHeap, 8, globalSize);
                OS.MoveMemory(n, globalLock, globalSize);
                OS.GlobalUnlock(hDevMode);
                OS.GlobalFree(printdlg.hDevMode);
            }
            if (printdlg.hDevNames != 0) {
                OS.GlobalFree(printdlg.hDevNames);
            }
        }
        final DEVMODE devmode = OS.IsUnicode ? new DEVMODEW() : new DEVMODEA();
        OS.MoveMemory(devmode, n, DEVMODE.sizeof);
        final DEVMODE devmode2 = devmode;
        devmode2.dmFields |= 0x1;
        devmode.dmOrientation = (short)((this.data.orientation == 2) ? 2 : 1);
        if (this.data.copyCount != 1) {
            final DEVMODE devmode3 = devmode;
            devmode3.dmFields |= 0x100;
            devmode.dmCopies = (short)this.data.copyCount;
        }
        if (this.data.collate) {
            final DEVMODE devmode4 = devmode;
            devmode4.dmFields |= 0x8000;
            devmode.dmCollate = 1;
        }
        if (this.data.duplex != -1) {
            final DEVMODE devmode5 = devmode;
            devmode5.dmFields |= 0x1000;
            switch (this.data.duplex) {
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
        OS.MoveMemory(n, devmode, DEVMODE.sizeof);
        this.handle = OS.CreateDC(tchar, tchar2, 0, n);
        if (n != 0) {
            OS.HeapFree(getProcessHeap, 0, n);
        }
        if (this.handle == 0) {
            SWT.error(2);
        }
    }
    
    public int internal_new_GC(final GCData gcData) {
        if (this.handle == 0) {
            SWT.error(2);
        }
        if (gcData != null) {
            if (this.isGCCreated) {
                SWT.error(5);
            }
            if ((gcData.style & 0x6000000) != 0x0) {
                gcData.layout = (((gcData.style & 0x4000000) != 0x0) ? 1 : 0);
            }
            else {
                gcData.style |= 0x2000000;
            }
            gcData.device = this;
            gcData.font = Font.win32_new(this, OS.GetCurrentObject(this.handle, 6));
            this.isGCCreated = true;
        }
        return this.handle;
    }
    
    public void internal_dispose_GC(final int n, final GCData gcData) {
        if (gcData != null) {
            this.isGCCreated = false;
        }
    }
    
    public boolean startJob(final String s) {
        this.checkDevice();
        final DOCINFO docinfo = new DOCINFO();
        docinfo.cbSize = DOCINFO.sizeof;
        final int getProcessHeap = OS.GetProcessHeap();
        int heapAlloc = 0;
        if (s != null && s.length() != 0) {
            final TCHAR tchar = new TCHAR(0, s, true);
            final int n = tchar.length() * TCHAR.sizeof;
            heapAlloc = OS.HeapAlloc(getProcessHeap, 8, n);
            OS.MoveMemory(heapAlloc, tchar, n);
            docinfo.lpszDocName = heapAlloc;
        }
        int heapAlloc2 = 0;
        if (this.data.printToFile && this.data.fileName != null) {
            final TCHAR tchar2 = new TCHAR(0, this.data.fileName, true);
            final int n2 = tchar2.length() * TCHAR.sizeof;
            heapAlloc2 = OS.HeapAlloc(getProcessHeap, 8, n2);
            OS.MoveMemory(heapAlloc2, tchar2, n2);
            docinfo.lpszOutput = heapAlloc2;
        }
        final int startDoc = OS.StartDoc(this.handle, docinfo);
        if (heapAlloc != 0) {
            OS.HeapFree(getProcessHeap, 0, heapAlloc);
        }
        if (heapAlloc2 != 0) {
            OS.HeapFree(getProcessHeap, 0, heapAlloc2);
        }
        return startDoc > 0;
    }
    
    public void endJob() {
        this.checkDevice();
        OS.EndDoc(this.handle);
    }
    
    public void cancelJob() {
        this.checkDevice();
        OS.AbortDoc(this.handle);
    }
    
    public boolean startPage() {
        this.checkDevice();
        final int startPage = OS.StartPage(this.handle);
        if (startPage <= 0) {
            OS.AbortDoc(this.handle);
        }
        return startPage > 0;
    }
    
    public void endPage() {
        this.checkDevice();
        OS.EndPage(this.handle);
    }
    
    public Point getDPI() {
        this.checkDevice();
        return new Point(OS.GetDeviceCaps(this.handle, 88), OS.GetDeviceCaps(this.handle, 90));
    }
    
    public Rectangle getBounds() {
        this.checkDevice();
        return new Rectangle(0, 0, OS.GetDeviceCaps(this.handle, 110), OS.GetDeviceCaps(this.handle, 111));
    }
    
    public Rectangle getClientArea() {
        this.checkDevice();
        return new Rectangle(0, 0, OS.GetDeviceCaps(this.handle, 8), OS.GetDeviceCaps(this.handle, 10));
    }
    
    public Rectangle computeTrim(final int n, final int n2, final int n3, final int n4) {
        this.checkDevice();
        return new Rectangle(n + -OS.GetDeviceCaps(this.handle, 112), n2 + -OS.GetDeviceCaps(this.handle, 113), n3 + (OS.GetDeviceCaps(this.handle, 110) - OS.GetDeviceCaps(this.handle, 8)), n4 + (OS.GetDeviceCaps(this.handle, 111) - OS.GetDeviceCaps(this.handle, 10)));
    }
    
    public PrinterData getPrinterData() {
        return this.data;
    }
    
    protected void checkDevice() {
        if (this.handle == 0) {
            SWT.error(45);
        }
    }
    
    protected void release() {
        super.release();
        this.data = null;
    }
    
    protected void destroy() {
        if (this.handle != 0) {
            OS.DeleteDC(this.handle);
        }
        this.handle = 0;
    }
}
