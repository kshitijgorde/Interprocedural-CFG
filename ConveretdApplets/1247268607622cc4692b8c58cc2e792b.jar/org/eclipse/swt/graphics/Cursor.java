// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.graphics;

import org.eclipse.swt.internal.win32.ICONINFO;
import org.eclipse.swt.internal.win32.BITMAP;
import org.eclipse.swt.internal.win32.TCHAR;
import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.SWT;

public final class Cursor extends Resource
{
    public int handle;
    boolean isIcon;
    static final byte[] HAND_SOURCE;
    static final byte[] HAND_MASK;
    
    static {
        HAND_SOURCE = new byte[] { -7, -1, -1, -1, -16, -1, -1, -1, -16, -1, -1, -1, -16, -1, -1, -1, -16, 63, -1, -1, -16, 7, -1, -1, -16, 3, -1, -1, -16, 0, -1, -1, 16, 0, 127, -1, 0, 0, 127, -1, -128, 0, 127, -1, -64, 0, 127, -1, -32, 0, 127, -1, -16, 0, 127, -1, -8, 0, -1, -1, -4, 1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 };
        final byte[] hand_MASK = new byte[128];
        hand_MASK[4] = 6;
        hand_MASK[12] = (hand_MASK[8] = 6);
        hand_MASK[20] = (hand_MASK[16] = 6);
        hand_MASK[21] = -64;
        hand_MASK[24] = 6;
        hand_MASK[25] = -40;
        hand_MASK[28] = 6;
        hand_MASK[29] = -40;
        hand_MASK[32] = 7;
        hand_MASK[33] = -37;
        hand_MASK[36] = 103;
        hand_MASK[37] = -5;
        hand_MASK[40] = 63;
        hand_MASK[41] = -1;
        hand_MASK[44] = 31;
        hand_MASK[45] = -1;
        hand_MASK[48] = 15;
        hand_MASK[49] = -1;
        hand_MASK[52] = 7;
        hand_MASK[53] = -1;
        hand_MASK[56] = 3;
        hand_MASK[57] = -2;
        HAND_MASK = hand_MASK;
    }
    
    Cursor(final Device device) {
        super(device);
    }
    
    public Cursor(final Device device, final int n) {
        super(device);
        int n2 = 0;
        switch (n) {
            case 21: {
                n2 = 32649;
                break;
            }
            case 0: {
                n2 = 32512;
                break;
            }
            case 1: {
                n2 = 32514;
                break;
            }
            case 2: {
                n2 = 32515;
                break;
            }
            case 3: {
                n2 = 32650;
                break;
            }
            case 4: {
                n2 = 32651;
                break;
            }
            case 5: {
                n2 = 32646;
                break;
            }
            case 6: {
                n2 = 32643;
                break;
            }
            case 7: {
                n2 = 32645;
                break;
            }
            case 8: {
                n2 = 32642;
                break;
            }
            case 9: {
                n2 = 32644;
                break;
            }
            case 10: {
                n2 = 32645;
                break;
            }
            case 11: {
                n2 = 32645;
                break;
            }
            case 12: {
                n2 = 32644;
                break;
            }
            case 13: {
                n2 = 32644;
                break;
            }
            case 14: {
                n2 = 32643;
                break;
            }
            case 15: {
                n2 = 32642;
                break;
            }
            case 16: {
                n2 = 32643;
                break;
            }
            case 17: {
                n2 = 32642;
                break;
            }
            case 18: {
                n2 = 32516;
                break;
            }
            case 19: {
                n2 = 32513;
                break;
            }
            case 20: {
                n2 = 32648;
                break;
            }
            default: {
                SWT.error(5);
                break;
            }
        }
        this.handle = OS.LoadCursor(0, n2);
        if (this.handle == 0 && n == 21) {
            final int getSystemMetrics = OS.GetSystemMetrics(13);
            final int getSystemMetrics2 = OS.GetSystemMetrics(14);
            if (getSystemMetrics == 32 && getSystemMetrics2 == 32) {
                final int getModuleHandle = OS.GetModuleHandle(null);
                if (OS.IsWinCE) {
                    SWT.error(20);
                }
                this.handle = OS.CreateCursor(getModuleHandle, 5, 0, 32, 32, Cursor.HAND_SOURCE, Cursor.HAND_MASK);
            }
        }
        if (this.handle == 0) {
            SWT.error(2);
        }
        this.init();
    }
    
    public Cursor(final Device device, ImageData convertMask, ImageData imageData, final int n, final int n2) {
        super(device);
        if (convertMask == null) {
            SWT.error(4);
        }
        if (imageData == null) {
            if (convertMask.getTransparencyType() != 2) {
                SWT.error(4);
            }
            imageData = convertMask.getTransparencyMask();
        }
        if (imageData.width != convertMask.width || imageData.height != convertMask.height) {
            SWT.error(5);
        }
        if (n >= convertMask.width || n < 0 || n2 >= convertMask.height || n2 < 0) {
            SWT.error(5);
        }
        imageData = ImageData.convertMask(imageData);
        convertMask = ImageData.convertMask(convertMask);
        final byte[] convertPad = ImageData.convertPad(convertMask.data, convertMask.width, convertMask.height, convertMask.depth, convertMask.scanlinePad, 2);
        final byte[] convertPad2 = ImageData.convertPad(imageData.data, imageData.width, imageData.height, imageData.depth, imageData.scanlinePad, 2);
        final int getModuleHandle = OS.GetModuleHandle(null);
        if (OS.IsWinCE) {
            SWT.error(20);
        }
        this.handle = OS.CreateCursor(getModuleHandle, n, n2, convertMask.width, convertMask.height, convertPad, convertPad2);
        if (this.handle == 0) {
            SWT.error(2);
        }
        this.init();
    }
    
    public Cursor(final Device device, final ImageData imageData, final int xHotspot, final int yHotspot) {
        super(device);
        if (imageData == null) {
            SWT.error(4);
        }
        if (xHotspot >= imageData.width || xHotspot < 0 || yHotspot >= imageData.height || yHotspot < 0) {
            SWT.error(5);
        }
        int dib;
        int createBitmap;
        if (imageData.maskData == null && imageData.transparentPixel == -1 && (imageData.alpha != -1 || imageData.alphaData != null)) {
            final PaletteData palette = imageData.palette;
            final PaletteData paletteData = new PaletteData(65280, 16711680, -16777216);
            final ImageData imageData2 = new ImageData(imageData.width, imageData.height, 32, paletteData);
            if (palette.isDirect) {
                ImageData.blit(1, imageData.data, imageData.depth, imageData.bytesPerLine, imageData.getByteOrder(), 0, 0, imageData.width, imageData.height, palette.redMask, palette.greenMask, palette.blueMask, 255, null, 0, 0, 0, imageData2.data, imageData2.depth, imageData2.bytesPerLine, imageData2.getByteOrder(), 0, 0, imageData2.width, imageData2.height, paletteData.redMask, paletteData.greenMask, paletteData.blueMask, false, false);
            }
            else {
                final RGB[] rgBs = palette.getRGBs();
                final int length = rgBs.length;
                final byte[] array = new byte[length];
                final byte[] array2 = new byte[length];
                final byte[] array3 = new byte[length];
                for (int i = 0; i < rgBs.length; ++i) {
                    final RGB rgb = rgBs[i];
                    if (rgb != null) {
                        array[i] = (byte)rgb.red;
                        array2[i] = (byte)rgb.green;
                        array3[i] = (byte)rgb.blue;
                    }
                }
                ImageData.blit(1, imageData.data, imageData.depth, imageData.bytesPerLine, imageData.getByteOrder(), 0, 0, imageData.width, imageData.height, array, array2, array3, 255, null, 0, 0, 0, imageData2.data, imageData2.depth, imageData2.bytesPerLine, imageData2.getByteOrder(), 0, 0, imageData2.width, imageData2.height, paletteData.redMask, paletteData.greenMask, paletteData.blueMask, false, false);
            }
            dib = Image.createDIB(imageData.width, imageData.height, 32);
            if (dib == 0) {
                SWT.error(2);
            }
            final BITMAP bitmap = new BITMAP();
            OS.GetObject(dib, BITMAP.sizeof, bitmap);
            final byte[] data = imageData2.data;
            if (imageData.alpha != -1) {
                for (int j = 3; j < data.length; j += 4) {
                    data[j] = (byte)imageData.alpha;
                }
            }
            else if (imageData.alphaData != null) {
                for (int k = 3, n = 0; k < data.length; k += 4, ++n) {
                    data[k] = imageData.alphaData[n];
                }
            }
            OS.MoveMemory(bitmap.bmBits, data, data.length);
            createBitmap = OS.CreateBitmap(imageData.width, imageData.height, 1, 1, new byte[((imageData.width + 7) / 8 + 3) / 4 * 4 * imageData.height]);
            if (createBitmap == 0) {
                SWT.error(2);
            }
        }
        else {
            final int[] init = Image.init(this.device, null, imageData, imageData.getTransparencyMask());
            dib = init[0];
            createBitmap = init[1];
        }
        final ICONINFO iconinfo = new ICONINFO();
        iconinfo.fIcon = false;
        iconinfo.hbmColor = dib;
        iconinfo.hbmMask = createBitmap;
        iconinfo.xHotspot = xHotspot;
        iconinfo.yHotspot = yHotspot;
        this.handle = OS.CreateIconIndirect(iconinfo);
        OS.DeleteObject(dib);
        OS.DeleteObject(createBitmap);
        if (this.handle == 0) {
            SWT.error(2);
        }
        this.isIcon = true;
        this.init();
    }
    
    void destroy() {
        if (this.isIcon) {
            OS.DestroyIcon(this.handle);
        }
        else if (!OS.IsWinCE) {
            OS.DestroyCursor(this.handle);
        }
        this.handle = 0;
    }
    
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Cursor)) {
            return false;
        }
        final Cursor cursor = (Cursor)o;
        return this.device == cursor.device && this.handle == cursor.handle;
    }
    
    public int hashCode() {
        return this.handle;
    }
    
    public boolean isDisposed() {
        return this.handle == 0;
    }
    
    public String toString() {
        if (this.isDisposed()) {
            return "Cursor {*DISPOSED*}";
        }
        return "Cursor {" + this.handle + "}";
    }
    
    public static Cursor win32_new(final Device device, final int handle) {
        final Cursor cursor = new Cursor(device);
        cursor.handle = handle;
        return cursor;
    }
}
