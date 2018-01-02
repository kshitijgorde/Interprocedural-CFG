// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.graphics;

import org.eclipse.swt.internal.win32.LOGFONT;
import org.eclipse.swt.internal.win32.LOGFONTA;
import org.eclipse.swt.internal.win32.LOGFONTW;
import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.SWT;

public final class Font extends Resource
{
    public int handle;
    
    Font(final Device device) {
        super(device);
    }
    
    public Font(final Device device, final FontData fontData) {
        super(device);
        this.init(fontData);
        this.init();
    }
    
    public Font(final Device device, final FontData[] array) {
        super(device);
        if (array == null) {
            SWT.error(4);
        }
        if (array.length == 0) {
            SWT.error(5);
        }
        for (int i = 0; i < array.length; ++i) {
            if (array[i] == null) {
                SWT.error(5);
            }
        }
        this.init(array[0]);
        this.init();
    }
    
    public Font(final Device device, final String s, final int n, final int n2) {
        super(device);
        if (s == null) {
            SWT.error(4);
        }
        this.init(new FontData(s, n, n2));
        this.init();
    }
    
    Font(final Device device, final String s, final float n, final int n2) {
        super(device);
        if (s == null) {
            SWT.error(4);
        }
        this.init(new FontData(s, n, n2));
        this.init();
    }
    
    void destroy() {
        OS.DeleteObject(this.handle);
        this.handle = 0;
    }
    
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Font)) {
            return false;
        }
        final Font font = (Font)o;
        return this.device == font.device && this.handle == font.handle;
    }
    
    public FontData[] getFontData() {
        if (this.isDisposed()) {
            SWT.error(44);
        }
        final LOGFONT logfont = OS.IsUnicode ? new LOGFONTW() : new LOGFONTA();
        OS.GetObject(this.handle, LOGFONT.sizeof, logfont);
        return new FontData[] { FontData.win32_new(logfont, this.device.computePoints(logfont, this.handle)) };
    }
    
    public int hashCode() {
        return this.handle;
    }
    
    void init(final FontData fontData) {
        if (fontData == null) {
            SWT.error(4);
        }
        final LOGFONT data = fontData.data;
        final int lfHeight = data.lfHeight;
        data.lfHeight = this.device.computePixels(fontData.height);
        this.handle = OS.CreateFontIndirect(data);
        data.lfHeight = lfHeight;
        if (this.handle == 0) {
            SWT.error(2);
        }
    }
    
    public boolean isDisposed() {
        return this.handle == 0;
    }
    
    public String toString() {
        if (this.isDisposed()) {
            return "Font {*DISPOSED*}";
        }
        return "Font {" + this.handle + "}";
    }
    
    public static Font win32_new(final Device device, final int handle) {
        final Font font = new Font(device);
        font.handle = handle;
        return font;
    }
}
