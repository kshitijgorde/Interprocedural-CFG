// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.graphics;

import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.SWT;

public final class Color extends Resource
{
    public int handle;
    
    Color(final Device device) {
        super(device);
    }
    
    public Color(final Device device, final int n, final int n2, final int n3) {
        super(device);
        this.init(n, n2, n3);
        this.init();
    }
    
    public Color(final Device device, final RGB rgb) {
        super(device);
        if (rgb == null) {
            SWT.error(4);
        }
        this.init(rgb.red, rgb.green, rgb.blue);
        this.init();
    }
    
    void destroy() {
        final int hPalette = this.device.hPalette;
        if (hPalette != 0) {
            final int getNearestPaletteIndex = OS.GetNearestPaletteIndex(hPalette, this.handle);
            final int[] colorRefCount = this.device.colorRefCount;
            if (colorRefCount[getNearestPaletteIndex] > 0) {
                final int[] array = colorRefCount;
                final int n = getNearestPaletteIndex;
                --array[n];
            }
        }
        this.handle = -1;
    }
    
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Color)) {
            return false;
        }
        final Color color = (Color)o;
        return this.device == color.device && (this.handle & 0xFFFFFF) == (color.handle & 0xFFFFFF);
    }
    
    public int getBlue() {
        if (this.isDisposed()) {
            SWT.error(44);
        }
        return (this.handle & 0xFF0000) >> 16;
    }
    
    public int getGreen() {
        if (this.isDisposed()) {
            SWT.error(44);
        }
        return (this.handle & 0xFF00) >> 8;
    }
    
    public int getRed() {
        if (this.isDisposed()) {
            SWT.error(44);
        }
        return this.handle & 0xFF;
    }
    
    public RGB getRGB() {
        if (this.isDisposed()) {
            SWT.error(44);
        }
        return new RGB(this.handle & 0xFF, (this.handle & 0xFF00) >> 8, (this.handle & 0xFF0000) >> 16);
    }
    
    public int hashCode() {
        return this.handle;
    }
    
    void init(final int n, final int n2, final int n3) {
        if (n > 255 || n < 0 || n2 > 255 || n2 < 0 || n3 > 255 || n3 < 0) {
            SWT.error(5);
        }
        this.handle = ((n & 0xFF) | (n2 & 0xFF) << 8 | (n3 & 0xFF) << 16);
        final int hPalette = this.device.hPalette;
        if (hPalette == 0) {
            return;
        }
        final int[] colorRefCount = this.device.colorRefCount;
        int getNearestPaletteIndex = OS.GetNearestPaletteIndex(hPalette, this.handle);
        final byte[] array = new byte[4];
        OS.GetPaletteEntries(hPalette, getNearestPaletteIndex, 1, array);
        if (array[0] == (byte)n && array[1] == (byte)n2 && array[2] == (byte)n3) {
            final int[] array2 = colorRefCount;
            final int n4 = getNearestPaletteIndex;
            ++array2[n4];
            return;
        }
        int i;
        for (i = 0; i < colorRefCount.length; ++i) {
            if (colorRefCount[i] == 0) {
                getNearestPaletteIndex = i;
                break;
            }
        }
        if (i == colorRefCount.length) {
            this.handle = ((array[0] & 0xFF) | (array[1] & 0xFF) << 8 | (array[2] & 0xFF) << 16);
        }
        else {
            OS.SetPaletteEntries(hPalette, getNearestPaletteIndex, 1, new byte[] { (byte)(n & 0xFF), (byte)(n2 & 0xFF), (byte)(n3 & 0xFF), 0 });
        }
        final int[] array3 = colorRefCount;
        final int n5 = getNearestPaletteIndex;
        ++array3[n5];
    }
    
    public boolean isDisposed() {
        return this.handle == -1;
    }
    
    public String toString() {
        if (this.isDisposed()) {
            return "Color {*DISPOSED*}";
        }
        return "Color {" + this.getRed() + ", " + this.getGreen() + ", " + this.getBlue() + "}";
    }
    
    public static Color win32_new(final Device device, final int handle) {
        final Color color = new Color(device);
        color.handle = handle;
        return color;
    }
}
