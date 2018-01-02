// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.graphics;

import org.eclipse.swt.SWT;

public final class PaletteData
{
    public boolean isDirect;
    public RGB[] colors;
    public int redMask;
    public int greenMask;
    public int blueMask;
    public int redShift;
    public int greenShift;
    public int blueShift;
    
    public PaletteData(final RGB[] colors) {
        if (colors == null) {
            SWT.error(4);
        }
        this.colors = colors;
        this.isDirect = false;
    }
    
    public PaletteData(final int redMask, final int greenMask, final int blueMask) {
        this.redMask = redMask;
        this.greenMask = greenMask;
        this.blueMask = blueMask;
        this.isDirect = true;
        this.redShift = this.shiftForMask(redMask);
        this.greenShift = this.shiftForMask(greenMask);
        this.blueShift = this.shiftForMask(blueMask);
    }
    
    public int getPixel(final RGB rgb) {
        if (rgb == null) {
            SWT.error(4);
        }
        if (this.isDirect) {
            return 0x0 | (((this.redShift < 0) ? (rgb.red << -this.redShift) : (rgb.red >>> this.redShift)) & this.redMask) | (((this.greenShift < 0) ? (rgb.green << -this.greenShift) : (rgb.green >>> this.greenShift)) & this.greenMask) | (((this.blueShift < 0) ? (rgb.blue << -this.blueShift) : (rgb.blue >>> this.blueShift)) & this.blueMask);
        }
        for (int i = 0; i < this.colors.length; ++i) {
            if (this.colors[i].equals(rgb)) {
                return i;
            }
        }
        SWT.error(5);
        return 0;
    }
    
    public RGB getRGB(final int n) {
        if (this.isDirect) {
            final int n2 = n & this.redMask;
            final int n3 = (this.redShift < 0) ? (n2 >>> -this.redShift) : (n2 << this.redShift);
            final int n4 = n & this.greenMask;
            final int n5 = (this.greenShift < 0) ? (n4 >>> -this.greenShift) : (n4 << this.greenShift);
            final int n6 = n & this.blueMask;
            return new RGB(n3, n5, (this.blueShift < 0) ? (n6 >>> -this.blueShift) : (n6 << this.blueShift));
        }
        if (n < 0 || n >= this.colors.length) {
            SWT.error(5);
        }
        return this.colors[n];
    }
    
    public RGB[] getRGBs() {
        return this.colors;
    }
    
    int shiftForMask(final int n) {
        for (int i = 31; i >= 0; --i) {
            if ((n >> i & 0x1) != 0x0) {
                return 7 - i;
            }
        }
        return 32;
    }
}
