// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.graphics;

import org.eclipse.swt.internal.win32.TCHAR;
import org.eclipse.swt.internal.Callback;
import org.eclipse.swt.internal.win32.LOGFONTA;
import org.eclipse.swt.internal.win32.LOGFONTW;
import org.eclipse.swt.internal.win32.TEXTMETRICA;
import org.eclipse.swt.internal.win32.TEXTMETRICW;
import org.eclipse.swt.internal.gdip.Gdip;
import org.eclipse.swt.internal.gdip.GdiplusStartupInput;
import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.internal.win32.TEXTMETRIC;
import org.eclipse.swt.internal.win32.LOGFONT;

public abstract class Device implements Drawable
{
    public static boolean DEBUG;
    boolean debug;
    boolean tracking;
    Error[] errors;
    Object[] objects;
    Object trackingLock;
    public int hPalette;
    int[] colorRefCount;
    Font systemFont;
    int nFonts;
    LOGFONT[] logFonts;
    TEXTMETRIC metrics;
    int[] pixels;
    int[] scripts;
    int[] gdipToken;
    int fontCollection;
    String[] loadedFonts;
    boolean disposed;
    protected static Device CurrentDevice;
    protected static Runnable DeviceFinder;
    static /* synthetic */ Class class$0;
    
    static {
        try {
            Class.forName("org.eclipse.swt.widgets.Display");
        }
        catch (ClassNotFoundException ex) {}
    }
    
    static synchronized Device getDevice() {
        if (Device.DeviceFinder != null) {
            Device.DeviceFinder.run();
        }
        final Device currentDevice = Device.CurrentDevice;
        Device.CurrentDevice = null;
        return currentDevice;
    }
    
    public Device() {
        this(null);
    }
    
    public Device(final DeviceData deviceData) {
        this.debug = Device.DEBUG;
        this.tracking = Device.DEBUG;
        this.hPalette = 0;
        this.nFonts = 256;
        Class<?> clazz2;
        Class clazz;
        if ((clazz = (clazz2 = (Class<?>)Device.class$0)) == null) {
            try {
                clazz = (Device.class$0 = (clazz2 = Class.forName("org.eclipse.swt.graphics.Device")));
            }
            catch (ClassNotFoundException ex) {
                throw new NoClassDefFoundError(ex.getMessage());
            }
        }
        final Class clazz3 = clazz;
        synchronized (clazz2) {
            if (deviceData != null) {
                this.debug = deviceData.debug;
                this.tracking = deviceData.tracking;
            }
            if (this.tracking) {
                this.errors = new Error[128];
                this.objects = new Object[128];
                this.trackingLock = new Object();
            }
            this.create(deviceData);
            this.init();
        }
        // monitorexit(clazz3)
    }
    
    void addFont(final String s) {
        if (this.loadedFonts == null) {
            this.loadedFonts = new String[4];
        }
        final int length = this.loadedFonts.length;
        for (int i = 0; i < length; ++i) {
            if (s.equals(this.loadedFonts[i])) {
                return;
            }
        }
        int n;
        for (n = 0; n < length && this.loadedFonts[n] != null; ++n) {}
        if (n == length) {
            final String[] loadedFonts = new String[length + 4];
            System.arraycopy(this.loadedFonts, 0, loadedFonts, 0, length);
            this.loadedFonts = loadedFonts;
        }
        this.loadedFonts[n] = s;
    }
    
    protected void checkDevice() {
        if (this.disposed) {
            SWT.error(45);
        }
    }
    
    void checkGDIP() {
        if (this.gdipToken != null) {
            return;
        }
        int setErrorMode = 0;
        if (!OS.IsWinCE) {
            setErrorMode = OS.SetErrorMode(1);
        }
        try {
            final int[] gdipToken = { 0 };
            final GdiplusStartupInput gdiplusStartupInput = new GdiplusStartupInput();
            gdiplusStartupInput.GdiplusVersion = 1;
            if (Gdip.GdiplusStartup(gdipToken, gdiplusStartupInput, 0) == 0) {
                this.gdipToken = gdipToken;
                if (this.loadedFonts != null) {
                    this.fontCollection = Gdip.PrivateFontCollection_new();
                    if (this.fontCollection == 0) {
                        SWT.error(2);
                    }
                    for (int i = 0; i < this.loadedFonts.length; ++i) {
                        final String s = this.loadedFonts[i];
                        if (s == null) {
                            break;
                        }
                        final int length = s.length();
                        final char[] array = new char[length + 1];
                        s.getChars(0, length, array, 0);
                        Gdip.PrivateFontCollection_AddFontFile(this.fontCollection, array);
                    }
                    this.loadedFonts = null;
                }
            }
        }
        catch (Throwable t) {
            SWT.error(16, t, " [GDI+ is required]");
        }
        finally {
            if (!OS.IsWinCE) {
                OS.SetErrorMode(setErrorMode);
            }
        }
        if (!OS.IsWinCE) {
            OS.SetErrorMode(setErrorMode);
        }
    }
    
    protected void create(final DeviceData deviceData) {
    }
    
    int computePixels(final float n) {
        final int internal_new_GC = this.internal_new_GC(null);
        final int n2 = -(int)(0.5f + n * OS.GetDeviceCaps(internal_new_GC, 90) / 72.0f);
        this.internal_dispose_GC(internal_new_GC, null);
        return n2;
    }
    
    float computePoints(final LOGFONT logfont, final int n) {
        final int internal_new_GC = this.internal_new_GC(null);
        final int getDeviceCaps = OS.GetDeviceCaps(internal_new_GC, 90);
        int n2;
        if (logfont.lfHeight > 0) {
            final int selectObject = OS.SelectObject(internal_new_GC, n);
            final TEXTMETRIC textmetric = OS.IsUnicode ? new TEXTMETRICW() : new TEXTMETRICA();
            OS.GetTextMetrics(internal_new_GC, textmetric);
            OS.SelectObject(internal_new_GC, selectObject);
            n2 = logfont.lfHeight - textmetric.tmInternalLeading;
        }
        else {
            n2 = -logfont.lfHeight;
        }
        this.internal_dispose_GC(internal_new_GC, null);
        return n2 * 72.0f / getDeviceCaps;
    }
    
    protected void destroy() {
    }
    
    public void dispose() {
        Class<?> clazz2;
        Class clazz;
        if ((clazz = (clazz2 = (Class<?>)Device.class$0)) == null) {
            try {
                clazz = (Device.class$0 = (clazz2 = Class.forName("org.eclipse.swt.graphics.Device")));
            }
            catch (ClassNotFoundException ex) {
                throw new NoClassDefFoundError(ex.getMessage());
            }
        }
        final Class clazz3 = clazz;
        synchronized (clazz2) {
            if (this.isDisposed()) {
                // monitorexit(clazz3)
                return;
            }
            this.checkDevice();
            this.release();
            this.destroy();
            this.disposed = true;
            if (this.tracking) {
                // monitorenter(trackingLock = this.trackingLock)
                try {
                    this.printErrors();
                    this.objects = null;
                    this.errors = null;
                    this.trackingLock = null;
                }
                // monitorexit(trackingLock)
                finally {}
            }
        }
        // monitorexit(clazz3)
    }
    
    void dispose_Object(final Object o) {
        synchronized (this.trackingLock) {
            for (int i = 0; i < this.objects.length; ++i) {
                if (this.objects[i] == o) {
                    this.objects[i] = null;
                    this.errors[i] = null;
                    // monitorexit(this.trackingLock)
                    return;
                }
            }
        }
        // monitorexit(this.trackingLock)
    }
    
    int EnumFontFamProc(final int n, final int n2, final int n3, final int n4) {
        if ((n3 & 0x1) == 0x0 == (n4 == 1)) {
            if (this.nFonts == this.logFonts.length) {
                final LOGFONT[] logFonts = new LOGFONT[this.logFonts.length + 128];
                System.arraycopy(this.logFonts, 0, logFonts, 0, this.nFonts);
                this.logFonts = logFonts;
                final int[] pixels = new int[logFonts.length];
                System.arraycopy(this.pixels, 0, pixels, 0, this.nFonts);
                this.pixels = pixels;
            }
            LOGFONT logfont = this.logFonts[this.nFonts];
            if (logfont == null) {
                logfont = (OS.IsUnicode ? new LOGFONTW() : new LOGFONTA());
            }
            OS.MoveMemory(logfont, n, LOGFONT.sizeof);
            this.logFonts[this.nFonts] = logfont;
            if (logfont.lfHeight > 0) {
                OS.MoveMemory(this.metrics, n2, TEXTMETRIC.sizeof);
                this.pixels[this.nFonts] = logfont.lfHeight - this.metrics.tmInternalLeading;
            }
            else {
                this.pixels[this.nFonts] = -logfont.lfHeight;
            }
            ++this.nFonts;
        }
        return 1;
    }
    
    public Rectangle getBounds() {
        this.checkDevice();
        final int internal_new_GC = this.internal_new_GC(null);
        final int getDeviceCaps = OS.GetDeviceCaps(internal_new_GC, 8);
        final int getDeviceCaps2 = OS.GetDeviceCaps(internal_new_GC, 10);
        this.internal_dispose_GC(internal_new_GC, null);
        return new Rectangle(0, 0, getDeviceCaps, getDeviceCaps2);
    }
    
    public DeviceData getDeviceData() {
        this.checkDevice();
        final DeviceData deviceData = new DeviceData();
        deviceData.debug = this.debug;
        deviceData.tracking = this.tracking;
        if (this.tracking) {
            synchronized (this.trackingLock) {
                int n = 0;
                final int length = this.objects.length;
                for (int i = 0; i < length; ++i) {
                    if (this.objects[i] != null) {
                        ++n;
                    }
                }
                int n2 = 0;
                deviceData.objects = new Object[n];
                deviceData.errors = new Error[n];
                for (int j = 0; j < length; ++j) {
                    if (this.objects[j] != null) {
                        deviceData.objects[n2] = this.objects[j];
                        deviceData.errors[n2] = this.errors[j];
                        ++n2;
                    }
                }
                // monitorexit(this.trackingLock)
                return deviceData;
            }
        }
        deviceData.objects = new Object[0];
        deviceData.errors = new Error[0];
        return deviceData;
    }
    
    public Rectangle getClientArea() {
        return this.getBounds();
    }
    
    public int getDepth() {
        this.checkDevice();
        final int internal_new_GC = this.internal_new_GC(null);
        final int getDeviceCaps = OS.GetDeviceCaps(internal_new_GC, 12);
        final int getDeviceCaps2 = OS.GetDeviceCaps(internal_new_GC, 14);
        this.internal_dispose_GC(internal_new_GC, null);
        return getDeviceCaps * getDeviceCaps2;
    }
    
    public Point getDPI() {
        this.checkDevice();
        final int internal_new_GC = this.internal_new_GC(null);
        final int getDeviceCaps = OS.GetDeviceCaps(internal_new_GC, 88);
        final int getDeviceCaps2 = OS.GetDeviceCaps(internal_new_GC, 90);
        this.internal_dispose_GC(internal_new_GC, null);
        return new Point(getDeviceCaps, getDeviceCaps2);
    }
    
    public FontData[] getFontList(final String s, final boolean b) {
        this.checkDevice();
        final Callback callback = new Callback(this, "EnumFontFamProc", 4);
        final int address = callback.getAddress();
        if (address == 0) {
            SWT.error(3);
        }
        this.metrics = (OS.IsUnicode ? new TEXTMETRICW() : new TEXTMETRICA());
        this.pixels = new int[this.nFonts];
        this.logFonts = new LOGFONT[this.nFonts];
        for (int i = 0; i < this.logFonts.length; ++i) {
            this.logFonts[i] = (OS.IsUnicode ? new LOGFONTW() : new LOGFONTA());
        }
        this.nFonts = 0;
        int nFonts = 0;
        final int internal_new_GC = this.internal_new_GC(null);
        if (s == null) {
            OS.EnumFontFamilies(internal_new_GC, null, address, b ? 1 : 0);
            nFonts = this.nFonts;
            for (int j = 0; j < nFonts; ++j) {
                final LOGFONT logfont = this.logFonts[j];
                if (OS.IsUnicode) {
                    OS.EnumFontFamiliesW(internal_new_GC, ((LOGFONTW)logfont).lfFaceName, address, b ? 1 : 0);
                }
                else {
                    OS.EnumFontFamiliesA(internal_new_GC, ((LOGFONTA)logfont).lfFaceName, address, b ? 1 : 0);
                }
            }
        }
        else {
            OS.EnumFontFamilies(internal_new_GC, new TCHAR(0, s, true), address, b ? 1 : 0);
        }
        final int getDeviceCaps = OS.GetDeviceCaps(internal_new_GC, 90);
        this.internal_dispose_GC(internal_new_GC, null);
        int n = 0;
        FontData[] array = new FontData[this.nFonts - nFonts];
        for (int k = nFonts; k < this.nFonts; ++k) {
            FontData win32_new;
            int n2;
            for (win32_new = FontData.win32_new(this.logFonts[k], this.pixels[k] * 72.0f / getDeviceCaps), n2 = 0; n2 < n && !win32_new.equals(array[n2]); ++n2) {}
            if (n2 == n) {
                array[n++] = win32_new;
            }
        }
        if (n != array.length) {
            final FontData[] array2 = new FontData[n];
            System.arraycopy(array, 0, array2, 0, n);
            array = array2;
        }
        callback.dispose();
        this.logFonts = null;
        this.pixels = null;
        this.metrics = null;
        return array;
    }
    
    String getLastError() {
        final int getLastError = OS.GetLastError();
        if (getLastError == 0) {
            return "";
        }
        return " [GetLastError=0x" + Integer.toHexString(getLastError) + "]";
    }
    
    String getLastErrorText() {
        final int getLastError = OS.GetLastError();
        if (getLastError == 0) {
            return "";
        }
        final int[] array = { 0 };
        final int formatMessage = OS.FormatMessage(4864, 0, getLastError, 1024, array, 0, 0);
        if (formatMessage == 0) {
            return " [GetLastError=0x" + Integer.toHexString(getLastError) + "]";
        }
        final TCHAR tchar = new TCHAR(0, formatMessage);
        OS.MoveMemory(tchar, array[0], formatMessage * TCHAR.sizeof);
        if (array[0] != 0) {
            OS.LocalFree(array[0]);
        }
        return tchar.toString(0, formatMessage);
    }
    
    public Color getSystemColor(final int n) {
        this.checkDevice();
        int n2 = 0;
        switch (n) {
            case 1: {
                n2 = 16777215;
                break;
            }
            case 2: {
                n2 = 0;
                break;
            }
            case 3: {
                n2 = 255;
                break;
            }
            case 4: {
                n2 = 128;
                break;
            }
            case 5: {
                n2 = 65280;
                break;
            }
            case 6: {
                n2 = 32768;
                break;
            }
            case 7: {
                n2 = 65535;
                break;
            }
            case 8: {
                n2 = 32896;
                break;
            }
            case 9: {
                n2 = 16711680;
                break;
            }
            case 10: {
                n2 = 8388608;
                break;
            }
            case 11: {
                n2 = 16711935;
                break;
            }
            case 12: {
                n2 = 8388736;
                break;
            }
            case 13: {
                n2 = 16776960;
                break;
            }
            case 14: {
                n2 = 8421376;
                break;
            }
            case 15: {
                n2 = 12632256;
                break;
            }
            case 16: {
                n2 = 8421504;
                break;
            }
        }
        return Color.win32_new(this, n2);
    }
    
    public Font getSystemFont() {
        this.checkDevice();
        return Font.win32_new(this, OS.GetStockObject(13));
    }
    
    public boolean getWarnings() {
        this.checkDevice();
        return false;
    }
    
    protected void init() {
        if (this.debug && !OS.IsWinCE) {
            OS.GdiSetBatchLimit(1);
        }
        this.systemFont = this.getSystemFont();
        if (!OS.IsWinCE) {
            final int[] array = { 0 };
            final int[] array2 = { 0 };
            OS.ScriptGetProperties(array, array2);
            OS.MoveMemory(this.scripts = new int[array2[0]], array[0], this.scripts.length * OS.PTR_SIZEOF);
        }
        final int internal_new_GC = this.internal_new_GC(null);
        final int getDeviceCaps = OS.GetDeviceCaps(internal_new_GC, 38);
        final int n = OS.GetDeviceCaps(internal_new_GC, 12) * OS.GetDeviceCaps(internal_new_GC, 14);
        if ((getDeviceCaps & 0x100) == 0x0 || n != 8) {
            this.internal_dispose_GC(internal_new_GC, null);
            return;
        }
        int getDeviceCaps2 = OS.GetDeviceCaps(internal_new_GC, 106);
        final int getDeviceCaps3 = OS.GetDeviceCaps(internal_new_GC, 104);
        if (OS.IsWinCE && getDeviceCaps2 == 0 && getDeviceCaps3 >= 20) {
            getDeviceCaps2 = 20;
        }
        this.colorRefCount = new int[getDeviceCaps3];
        final byte[] array3 = new byte[4 + 4 * getDeviceCaps3];
        array3[0] = 0;
        array3[1] = 3;
        array3[2] = 0;
        array3[3] = 1;
        final byte[] array4 = new byte[4 * getDeviceCaps3];
        OS.GetSystemPaletteEntries(internal_new_GC, 0, getDeviceCaps3, array4);
        System.arraycopy(array4, 0, array3, 4, 4 * getDeviceCaps3);
        for (int i = 0; i < getDeviceCaps2 / 2; ++i) {
            this.colorRefCount[i] = 1;
            this.colorRefCount[getDeviceCaps3 - 1 - i] = 1;
        }
        this.internal_dispose_GC(internal_new_GC, null);
        this.hPalette = OS.CreatePalette(array3);
    }
    
    public abstract int internal_new_GC(final GCData p0);
    
    public abstract void internal_dispose_GC(final int p0, final GCData p1);
    
    public boolean isDisposed() {
        Class<?> clazz2;
        Class clazz;
        if ((clazz = (clazz2 = (Class<?>)Device.class$0)) == null) {
            try {
                clazz = (Device.class$0 = (clazz2 = Class.forName("org.eclipse.swt.graphics.Device")));
            }
            catch (ClassNotFoundException ex) {
                throw new NoClassDefFoundError(ex.getMessage());
            }
        }
        final Class clazz3 = clazz;
        synchronized (clazz2) {
            // monitorexit(clazz3)
            return this.disposed;
        }
    }
    
    public boolean loadFont(final String s) {
        this.checkDevice();
        if (s == null) {
            SWT.error(4);
        }
        if (OS.IsWinNT && OS.WIN32_VERSION >= OS.VERSION(4, 10)) {
            final boolean b = OS.AddFontResourceEx(new TCHAR(0, s, true), 16, 0) != 0;
            if (b) {
                if (this.gdipToken != null) {
                    if (this.fontCollection == 0) {
                        this.fontCollection = Gdip.PrivateFontCollection_new();
                        if (this.fontCollection == 0) {
                            SWT.error(2);
                        }
                    }
                    final int length = s.length();
                    final char[] array = new char[length + 1];
                    s.getChars(0, length, array, 0);
                    Gdip.PrivateFontCollection_AddFontFile(this.fontCollection, array);
                }
                else {
                    this.addFont(s);
                }
            }
            return b;
        }
        return false;
    }
    
    void new_Object(final Object o) {
        synchronized (this.trackingLock) {
            for (int i = 0; i < this.objects.length; ++i) {
                if (this.objects[i] == null) {
                    this.objects[i] = o;
                    this.errors[i] = new Error();
                    // monitorexit(this.trackingLock)
                    return;
                }
            }
            final Object[] objects = new Object[this.objects.length + 128];
            System.arraycopy(this.objects, 0, objects, 0, this.objects.length);
            objects[this.objects.length] = o;
            this.objects = objects;
            final Error[] errors = new Error[this.errors.length + 128];
            System.arraycopy(this.errors, 0, errors, 0, this.errors.length);
            errors[this.errors.length] = new Error();
            this.errors = errors;
        }
        // monitorexit(this.trackingLock)
    }
    
    void printErrors() {
        if (!Device.DEBUG) {
            return;
        }
        if (this.tracking) {
            synchronized (this.trackingLock) {
                if (this.objects == null || this.errors == null) {
                    // monitorexit(this.trackingLock)
                    return;
                }
                int n = 0;
                int n2 = 0;
                int n3 = 0;
                int n4 = 0;
                int n5 = 0;
                int n6 = 0;
                int n7 = 0;
                int n8 = 0;
                int n9 = 0;
                int n10 = 0;
                int n11 = 0;
                for (int i = 0; i < this.objects.length; ++i) {
                    final Object o = this.objects[i];
                    if (o != null) {
                        ++n;
                        if (o instanceof Color) {
                            ++n2;
                        }
                        if (o instanceof Cursor) {
                            ++n3;
                        }
                        if (o instanceof Font) {
                            ++n4;
                        }
                        if (o instanceof GC) {
                            ++n5;
                        }
                        if (o instanceof Image) {
                            ++n6;
                        }
                        if (o instanceof Path) {
                            ++n7;
                        }
                        if (o instanceof Pattern) {
                            ++n8;
                        }
                        if (o instanceof Region) {
                            ++n9;
                        }
                        if (o instanceof TextLayout) {
                            ++n10;
                        }
                        if (o instanceof Transform) {
                            ++n11;
                        }
                    }
                }
                if (n != 0) {
                    String s = "Summary: ";
                    if (n2 != 0) {
                        s = String.valueOf(s) + n2 + " Color(s), ";
                    }
                    if (n3 != 0) {
                        s = String.valueOf(s) + n3 + " Cursor(s), ";
                    }
                    if (n4 != 0) {
                        s = String.valueOf(s) + n4 + " Font(s), ";
                    }
                    if (n5 != 0) {
                        s = String.valueOf(s) + n5 + " GC(s), ";
                    }
                    if (n6 != 0) {
                        s = String.valueOf(s) + n6 + " Image(s), ";
                    }
                    if (n7 != 0) {
                        s = String.valueOf(s) + n7 + " Path(s), ";
                    }
                    if (n8 != 0) {
                        s = String.valueOf(s) + n8 + " Pattern(s), ";
                    }
                    if (n9 != 0) {
                        s = String.valueOf(s) + n9 + " Region(s), ";
                    }
                    if (n10 != 0) {
                        s = String.valueOf(s) + n10 + " TextLayout(s), ";
                    }
                    if (n11 != 0) {
                        s = String.valueOf(s) + n11 + " Transforms(s), ";
                    }
                    if (s.length() != 0) {
                        System.err.println(s.substring(0, s.length() - 2));
                    }
                    for (int j = 0; j < this.errors.length; ++j) {
                        if (this.errors[j] != null) {
                            this.errors[j].printStackTrace(System.err);
                        }
                    }
                }
            }
            // monitorexit(this.trackingLock)
        }
    }
    
    protected void release() {
        if (this.gdipToken != null) {
            if (this.fontCollection != 0) {
                Gdip.PrivateFontCollection_delete(this.fontCollection);
            }
            this.fontCollection = 0;
            Gdip.GdiplusShutdown(this.gdipToken[0]);
        }
        this.gdipToken = null;
        this.scripts = null;
        if (this.hPalette != 0) {
            OS.DeleteObject(this.hPalette);
        }
        this.hPalette = 0;
        this.colorRefCount = null;
        this.logFonts = null;
        this.nFonts = 0;
    }
    
    public void setWarnings(final boolean b) {
        this.checkDevice();
    }
}
