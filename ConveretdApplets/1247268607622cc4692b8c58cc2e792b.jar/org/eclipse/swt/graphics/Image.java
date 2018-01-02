// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.graphics;

import org.eclipse.swt.internal.win32.DIBSECTION;
import org.eclipse.swt.internal.win32.ICONINFO;
import org.eclipse.swt.internal.win32.BITMAPINFOHEADER;
import org.eclipse.swt.internal.gdip.ColorPalette;
import org.eclipse.swt.internal.gdip.BitmapData;
import org.eclipse.swt.internal.gdip.Rect;
import org.eclipse.swt.internal.gdip.Gdip;
import org.eclipse.swt.SWTException;
import java.io.InputStream;
import org.eclipse.swt.internal.win32.BITMAP;
import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.SWT;

public final class Image extends Resource implements Drawable
{
    public int type;
    public int handle;
    int transparentPixel;
    int transparentColor;
    GC memGC;
    byte[] alphaData;
    int alpha;
    ImageData data;
    int width;
    int height;
    static final int DEFAULT_SCANLINE_PAD = 4;
    
    Image(final Device device) {
        super(device);
        this.transparentPixel = -1;
        this.transparentColor = -1;
        this.alpha = -1;
        this.width = -1;
        this.height = -1;
    }
    
    public Image(final Device device, final int n, final int n2) {
        super(device);
        this.transparentPixel = -1;
        this.transparentColor = -1;
        this.alpha = -1;
        this.width = -1;
        this.height = -1;
        this.init(n, n2);
        this.init();
    }
    
    public Image(Device device, final Image image, final int n) {
        super(device);
        this.transparentPixel = -1;
        this.transparentColor = -1;
        this.alpha = -1;
        this.width = -1;
        this.height = -1;
        device = this.device;
        if (image == null) {
            SWT.error(4);
        }
        if (image.isDisposed()) {
            SWT.error(5);
        }
        final Rectangle bounds = image.getBounds();
        this.type = image.type;
        Label_1538: {
            switch (n) {
                case 0: {
                    switch (this.type) {
                        case 0: {
                            final int internal_new_GC = device.internal_new_GC(null);
                            final int createCompatibleDC = OS.CreateCompatibleDC(internal_new_GC);
                            final int createCompatibleDC2 = OS.CreateCompatibleDC(internal_new_GC);
                            final int selectObject = OS.SelectObject(createCompatibleDC, image.handle);
                            final BITMAP bitmap = new BITMAP();
                            OS.GetObject(image.handle, BITMAP.sizeof, bitmap);
                            this.handle = OS.CreateCompatibleBitmap(createCompatibleDC, bounds.width, (bitmap.bmBits != 0) ? (-bounds.height) : bounds.height);
                            if (this.handle == 0) {
                                SWT.error(2);
                            }
                            final int selectObject2 = OS.SelectObject(createCompatibleDC2, this.handle);
                            OS.BitBlt(createCompatibleDC2, 0, 0, bounds.width, bounds.height, createCompatibleDC, 0, 0, 13369376);
                            OS.SelectObject(createCompatibleDC, selectObject);
                            OS.SelectObject(createCompatibleDC2, selectObject2);
                            OS.DeleteDC(createCompatibleDC);
                            OS.DeleteDC(createCompatibleDC2);
                            device.internal_dispose_GC(internal_new_GC, null);
                            this.transparentPixel = image.transparentPixel;
                            this.alpha = image.alpha;
                            if (image.alphaData != null) {
                                this.alphaData = new byte[image.alphaData.length];
                                System.arraycopy(image.alphaData, 0, this.alphaData, 0, this.alphaData.length);
                                break Label_1538;
                            }
                            break Label_1538;
                        }
                        case 1: {
                            if (OS.IsWinCE) {
                                this.init(image.data);
                                break Label_1538;
                            }
                            this.handle = OS.CopyImage(image.handle, 1, bounds.width, bounds.height, 0);
                            if (this.handle == 0) {
                                SWT.error(2);
                                break Label_1538;
                            }
                            break Label_1538;
                        }
                        default: {
                            SWT.error(40);
                            break Label_1538;
                        }
                    }
                    break;
                }
                case 1: {
                    final ImageData imageData = image.getImageData();
                    final PaletteData palette = imageData.palette;
                    final ImageData imageData2 = new ImageData(bounds.width, bounds.height, 8, new PaletteData(new RGB[] { device.getSystemColor(2).getRGB(), device.getSystemColor(18).getRGB(), device.getSystemColor(22).getRGB() }));
                    imageData2.alpha = imageData.alpha;
                    imageData2.alphaData = imageData.alphaData;
                    imageData2.maskData = imageData.maskData;
                    imageData2.maskPad = imageData.maskPad;
                    if (imageData.transparentPixel != -1) {
                        imageData2.transparentPixel = 0;
                    }
                    final int[] array = new int[bounds.width];
                    int[] array2 = null;
                    ImageData transparencyMask = null;
                    if (imageData.maskData != null) {
                        transparencyMask = imageData.getTransparencyMask();
                    }
                    if (transparencyMask != null) {
                        array2 = new int[bounds.width];
                    }
                    final int redMask = palette.redMask;
                    final int greenMask = palette.greenMask;
                    final int blueMask = palette.blueMask;
                    final int redShift = palette.redShift;
                    final int greenShift = palette.greenShift;
                    final int blueShift = palette.blueShift;
                    for (int i = 0; i < bounds.height; ++i) {
                        int n2 = i * imageData2.bytesPerLine;
                        imageData.getPixels(0, i, bounds.width, array, 0);
                        if (transparencyMask != null) {
                            transparencyMask.getPixels(0, i, bounds.width, array2, 0);
                        }
                        for (int j = 0; j < bounds.width; ++j) {
                            final int n3 = array[j];
                            if ((imageData.transparentPixel == -1 || n3 != imageData.transparentPixel) && (transparencyMask == null || array2[j] != 0)) {
                                int red;
                                int green;
                                int blue;
                                if (palette.isDirect) {
                                    final int n4 = n3 & redMask;
                                    red = ((redShift < 0) ? (n4 >>> -redShift) : (n4 << redShift));
                                    final int n5 = n3 & greenMask;
                                    green = ((greenShift < 0) ? (n5 >>> -greenShift) : (n5 << greenShift));
                                    final int n6 = n3 & blueMask;
                                    blue = ((blueShift < 0) ? (n6 >>> -blueShift) : (n6 << blueShift));
                                }
                                else {
                                    red = palette.colors[n3].red;
                                    green = palette.colors[n3].green;
                                    blue = palette.colors[n3].blue;
                                }
                                if (red * red + green * green + blue * blue < 98304) {
                                    imageData2.data[n2] = 1;
                                }
                                else {
                                    imageData2.data[n2] = 2;
                                }
                            }
                            ++n2;
                        }
                    }
                    this.init(imageData2);
                    break;
                }
                case 2: {
                    final ImageData imageData3 = image.getImageData();
                    final PaletteData palette2 = imageData3.palette;
                    ImageData imageData4 = imageData3;
                    if (!palette2.isDirect) {
                        final RGB[] rgBs = palette2.getRGBs();
                        for (int k = 0; k < rgBs.length; ++k) {
                            if (imageData3.transparentPixel != k) {
                                final RGB rgb = rgBs[k];
                                final int red2 = rgb.red;
                                final int green2 = rgb.green;
                                final int n7 = red2 + red2 + green2 + green2 + green2 + green2 + green2 + rgb.blue >> 3;
                                final RGB rgb2 = rgb;
                                final RGB rgb3 = rgb;
                                final RGB rgb4 = rgb;
                                final int red3 = n7;
                                rgb4.blue = red3;
                                rgb3.green = red3;
                                rgb2.red = red3;
                            }
                        }
                        imageData4.palette = new PaletteData(rgBs);
                    }
                    else {
                        final RGB[] array3 = new RGB[256];
                        for (int l = 0; l < array3.length; ++l) {
                            array3[l] = new RGB(l, l, l);
                        }
                        imageData4 = new ImageData(bounds.width, bounds.height, 8, new PaletteData(array3));
                        imageData4.alpha = imageData3.alpha;
                        imageData4.alphaData = imageData3.alphaData;
                        imageData4.maskData = imageData3.maskData;
                        imageData4.maskPad = imageData3.maskPad;
                        if (imageData3.transparentPixel != -1) {
                            imageData4.transparentPixel = 254;
                        }
                        final int[] array4 = new int[bounds.width];
                        final int redMask2 = palette2.redMask;
                        final int greenMask2 = palette2.greenMask;
                        final int blueMask2 = palette2.blueMask;
                        final int redShift2 = palette2.redShift;
                        final int greenShift2 = palette2.greenShift;
                        final int blueShift2 = palette2.blueShift;
                        for (int n8 = 0; n8 < bounds.height; ++n8) {
                            int n9 = n8 * imageData4.bytesPerLine;
                            imageData3.getPixels(0, n8, bounds.width, array4, 0);
                            for (int n10 = 0; n10 < bounds.width; ++n10) {
                                final int n11 = array4[n10];
                                if (n11 != imageData3.transparentPixel) {
                                    final int n12 = n11 & redMask2;
                                    final int n13 = (redShift2 < 0) ? (n12 >>> -redShift2) : (n12 << redShift2);
                                    final int n14 = n11 & greenMask2;
                                    final int n15 = (greenShift2 < 0) ? (n14 >>> -greenShift2) : (n14 << greenShift2);
                                    final int n16 = n11 & blueMask2;
                                    int n17 = n13 + n13 + n15 + n15 + n15 + n15 + n15 + ((blueShift2 < 0) ? (n16 >>> -blueShift2) : (n16 << blueShift2)) >> 3;
                                    if (imageData4.transparentPixel == n17) {
                                        n17 = 255;
                                    }
                                    imageData4.data[n9] = (byte)n17;
                                }
                                else {
                                    imageData4.data[n9] = -2;
                                }
                                ++n9;
                            }
                        }
                    }
                    this.init(imageData4);
                    break;
                }
                default: {
                    SWT.error(5);
                    break;
                }
            }
        }
        this.init();
    }
    
    public Image(final Device device, final Rectangle rectangle) {
        super(device);
        this.transparentPixel = -1;
        this.transparentColor = -1;
        this.alpha = -1;
        this.width = -1;
        this.height = -1;
        if (rectangle == null) {
            SWT.error(4);
        }
        this.init(rectangle.width, rectangle.height);
        this.init();
    }
    
    public Image(final Device device, final ImageData imageData) {
        super(device);
        this.transparentPixel = -1;
        this.transparentColor = -1;
        this.alpha = -1;
        this.width = -1;
        this.height = -1;
        this.init(imageData);
        this.init();
    }
    
    public Image(final Device device, final ImageData imageData, ImageData convertMask) {
        super(device);
        this.transparentPixel = -1;
        this.transparentColor = -1;
        this.alpha = -1;
        this.width = -1;
        this.height = -1;
        if (imageData == null) {
            SWT.error(4);
        }
        if (convertMask == null) {
            SWT.error(4);
        }
        if (imageData.width != convertMask.width || imageData.height != convertMask.height) {
            SWT.error(5);
        }
        convertMask = ImageData.convertMask(convertMask);
        init(this.device, this, imageData, convertMask);
        this.init();
    }
    
    public Image(final Device device, final InputStream inputStream) {
        super(device);
        this.transparentPixel = -1;
        this.transparentColor = -1;
        this.alpha = -1;
        this.width = -1;
        this.height = -1;
        this.init(new ImageData(inputStream));
        this.init();
    }
    
    public Image(final Device device, final String s) {
        super(device);
        this.transparentPixel = -1;
        this.transparentColor = -1;
        this.alpha = -1;
        this.width = -1;
        this.height = -1;
        if (s == null) {
            SWT.error(4);
        }
        this.initNative(s);
        if (this.handle == 0) {
            this.init(new ImageData(s));
        }
        this.init();
    }
    
    void initNative(final String s) {
        int n = 1;
        try {
            this.device.checkGDIP();
        }
        catch (SWTException ex) {
            n = 0;
        }
        if (n != 0 && OS.PTR_SIZEOF == 8 && s.toLowerCase().endsWith(".gif")) {
            n = 0;
        }
        if (!OS.IsWinCE && OS.WIN32_VERSION >= OS.VERSION(6, 1) && s.toLowerCase().endsWith(".gif")) {
            n = 0;
        }
        if (n != 0) {
            final int length = s.length();
            final char[] array = new char[length + 1];
            s.getChars(0, length, array, 0);
            final int bitmap_new = Gdip.Bitmap_new(array, false);
            if (bitmap_new != 0) {
                int n2 = 2;
                int n3 = Gdip.Image_GetLastStatus(bitmap_new);
                if (n3 == 0) {
                    if (s.toLowerCase().endsWith(".ico")) {
                        this.type = 1;
                        final int[] array2 = { 0 };
                        n3 = Gdip.Bitmap_GetHICON(bitmap_new, array2);
                        this.handle = array2[0];
                    }
                    else {
                        this.type = 0;
                        final int image_GetWidth = Gdip.Image_GetWidth(bitmap_new);
                        final int image_GetHeight = Gdip.Image_GetHeight(bitmap_new);
                        final int image_GetPixelFormat = Gdip.Image_GetPixelFormat(bitmap_new);
                        switch (image_GetPixelFormat) {
                            case 135173:
                            case 135174: {
                                this.handle = createDIB(image_GetWidth, image_GetHeight, 16);
                                break;
                            }
                            case 137224: {
                                this.handle = createDIB(image_GetWidth, image_GetHeight, 24);
                                break;
                            }
                            case 139273:
                            case 925707:
                            case 1052676:
                            case 1060876:
                            case 1851406:
                            case 3424269: {
                                this.handle = createDIB(image_GetWidth, image_GetHeight, 32);
                                break;
                            }
                        }
                        if (this.handle != 0) {
                            final int internal_new_GC = this.device.internal_new_GC(null);
                            final int createCompatibleDC = OS.CreateCompatibleDC(internal_new_GC);
                            final int selectObject = OS.SelectObject(createCompatibleDC, this.handle);
                            final int graphics_new = Gdip.Graphics_new(createCompatibleDC);
                            if (graphics_new != 0) {
                                final Rect rect = new Rect();
                                rect.Width = image_GetWidth;
                                rect.Height = image_GetHeight;
                                n3 = Gdip.Graphics_DrawImage(graphics_new, bitmap_new, rect, 0, 0, image_GetWidth, image_GetHeight, 2, 0, 0, 0);
                                if (n3 != 0) {
                                    n2 = 40;
                                    OS.DeleteObject(this.handle);
                                    this.handle = 0;
                                }
                                Gdip.Graphics_delete(graphics_new);
                            }
                            OS.SelectObject(createCompatibleDC, selectObject);
                            OS.DeleteDC(createCompatibleDC);
                            this.device.internal_dispose_GC(internal_new_GC, null);
                        }
                        else {
                            final int bitmapData_new = Gdip.BitmapData_new();
                            if (bitmapData_new != 0) {
                                n3 = Gdip.Bitmap_LockBits(bitmap_new, 0, 0, image_GetPixelFormat, bitmapData_new);
                                if (n3 == 0) {
                                    final BitmapData bitmapData = new BitmapData();
                                    Gdip.MoveMemory(bitmapData, bitmapData_new);
                                    final int stride = bitmapData.Stride;
                                    final int scan0 = bitmapData.Scan0;
                                    int n4 = 0;
                                    final int n5 = 4;
                                    int transparentPixel = -1;
                                    switch (bitmapData.PixelFormat) {
                                        case 196865: {
                                            n4 = 1;
                                            break;
                                        }
                                        case 197634: {
                                            n4 = 4;
                                            break;
                                        }
                                        case 198659: {
                                            n4 = 8;
                                            break;
                                        }
                                        case 135173:
                                        case 135174:
                                        case 397319: {
                                            n4 = 16;
                                            break;
                                        }
                                        case 137224: {
                                            n4 = 24;
                                            break;
                                        }
                                        case 139273:
                                        case 2498570: {
                                            n4 = 32;
                                            break;
                                        }
                                    }
                                    if (n4 != 0) {
                                        PaletteData paletteData = null;
                                        switch (bitmapData.PixelFormat) {
                                            case 196865:
                                            case 197634:
                                            case 198659: {
                                                final int image_GetPaletteSize = Gdip.Image_GetPaletteSize(bitmap_new);
                                                final int getProcessHeap = OS.GetProcessHeap();
                                                final int heapAlloc = OS.HeapAlloc(getProcessHeap, 8, image_GetPaletteSize);
                                                if (heapAlloc == 0) {
                                                    SWT.error(2);
                                                }
                                                Gdip.Image_GetPalette(bitmap_new, heapAlloc, image_GetPaletteSize);
                                                final ColorPalette colorPalette = new ColorPalette();
                                                Gdip.MoveMemory(colorPalette, heapAlloc, ColorPalette.sizeof);
                                                final int[] array3 = new int[colorPalette.Count];
                                                OS.MoveMemory(array3, heapAlloc + 8, array3.length * 4);
                                                OS.HeapFree(getProcessHeap, 0, heapAlloc);
                                                final RGB[] array4 = new RGB[colorPalette.Count];
                                                paletteData = new PaletteData(array4);
                                                for (int i = 0; i < array3.length; ++i) {
                                                    if ((array3[i] >> 24 & 0xFF) == 0x0 && (colorPalette.Flags & 0x1) != 0x0) {
                                                        transparentPixel = i;
                                                    }
                                                    array4[i] = new RGB((array3[i] & 0xFF0000) >> 16, (array3[i] & 0xFF00) >> 8, (array3[i] & 0xFF) >> 0);
                                                }
                                                break;
                                            }
                                            case 135173:
                                            case 397319: {
                                                paletteData = new PaletteData(31744, 992, 31);
                                                break;
                                            }
                                            case 135174: {
                                                paletteData = new PaletteData(63488, 2016, 31);
                                                break;
                                            }
                                            case 137224: {
                                                paletteData = new PaletteData(255, 65280, 16711680);
                                                break;
                                            }
                                            case 139273:
                                            case 2498570: {
                                                paletteData = new PaletteData(65280, 16711680, -16777216);
                                                break;
                                            }
                                        }
                                        final byte[] array5 = new byte[stride * image_GetHeight];
                                        byte[] alphaData = null;
                                        OS.MoveMemory(array5, scan0, array5.length);
                                        switch (bitmapData.PixelFormat) {
                                            case 397319: {
                                                alphaData = new byte[image_GetWidth * image_GetHeight];
                                                for (int j = 1, n6 = 0; j < array5.length; j += 2, ++n6) {
                                                    alphaData[n6] = (byte)(((array5[j] & 0x80) != 0x0) ? 255 : 0);
                                                }
                                                break;
                                            }
                                            case 2498570: {
                                                alphaData = new byte[image_GetWidth * image_GetHeight];
                                                for (int k = 3, n7 = 0; k < array5.length; k += 4, ++n7) {
                                                    alphaData[n7] = array5[k];
                                                }
                                                break;
                                            }
                                        }
                                        final ImageData imageData = new ImageData(image_GetWidth, image_GetHeight, n4, paletteData, n5, array5);
                                        imageData.transparentPixel = transparentPixel;
                                        imageData.alphaData = alphaData;
                                        this.init(imageData);
                                    }
                                    Gdip.Bitmap_UnlockBits(bitmap_new, bitmapData_new);
                                }
                                else {
                                    n2 = 40;
                                }
                                Gdip.BitmapData_delete(bitmapData_new);
                            }
                        }
                    }
                }
                Gdip.Bitmap_delete(bitmap_new);
                if (n3 == 0 && this.handle == 0) {
                    SWT.error(n2);
                }
            }
        }
    }
    
    int createDIBFromDDB(final int n, final int n2, final int biWidth, final int n3) {
        final int n4 = OS.GetDeviceCaps(n, 12) * OS.GetDeviceCaps(n, 14);
        final boolean b = n4 > 8;
        RGB[] array = null;
        if (!b) {
            final int n5 = 1 << n4;
            final byte[] array2 = new byte[4 * n5];
            OS.GetPaletteEntries(this.device.hPalette, 0, n5, array2);
            array = new RGB[n5];
            for (int i = 0; i < n5; ++i) {
                array[i] = new RGB(array2[i] & 0xFF, array2[i + 1] & 0xFF, array2[i + 2] & 0xFF);
            }
        }
        final boolean b2 = OS.IsWinCE && (n4 == 16 || n4 == 32);
        final BITMAPINFOHEADER bitmapinfoheader = new BITMAPINFOHEADER();
        bitmapinfoheader.biSize = BITMAPINFOHEADER.sizeof;
        bitmapinfoheader.biWidth = biWidth;
        bitmapinfoheader.biHeight = -n3;
        bitmapinfoheader.biPlanes = 1;
        bitmapinfoheader.biBitCount = (short)n4;
        if (b2) {
            bitmapinfoheader.biCompression = 3;
        }
        else {
            bitmapinfoheader.biCompression = 0;
        }
        byte[] array3;
        if (b) {
            array3 = new byte[BITMAPINFOHEADER.sizeof + (b2 ? 12 : 0)];
        }
        else {
            array3 = new byte[BITMAPINFOHEADER.sizeof + array.length * 4];
        }
        OS.MoveMemory(array3, bitmapinfoheader, BITMAPINFOHEADER.sizeof);
        int sizeof = BITMAPINFOHEADER.sizeof;
        if (b) {
            if (b2) {
                switch (n4) {
                    case 16: {
                        final int n6 = 31744;
                        final int n7 = 992;
                        final int n8 = 31;
                        array3[sizeof] = (byte)((n6 & 0xFF) >> 0);
                        array3[sizeof + 1] = (byte)((n6 & 0xFF00) >> 8);
                        array3[sizeof + 2] = (byte)((n6 & 0xFF0000) >> 16);
                        array3[sizeof + 3] = (byte)((n6 & 0xFF000000) >> 24);
                        array3[sizeof + 4] = (byte)((n7 & 0xFF) >> 0);
                        array3[sizeof + 5] = (byte)((n7 & 0xFF00) >> 8);
                        array3[sizeof + 6] = (byte)((n7 & 0xFF0000) >> 16);
                        array3[sizeof + 7] = (byte)((n7 & 0xFF000000) >> 24);
                        array3[sizeof + 8] = (byte)((n8 & 0xFF) >> 0);
                        array3[sizeof + 9] = (byte)((n8 & 0xFF00) >> 8);
                        array3[sizeof + 10] = (byte)((n8 & 0xFF0000) >> 16);
                        array3[sizeof + 11] = (byte)((n8 & 0xFF000000) >> 24);
                        break;
                    }
                    case 32: {
                        final int n9 = 65280;
                        final int n10 = 16711680;
                        final int n11 = -16777216;
                        array3[sizeof] = (byte)((n9 & 0xFF000000) >> 24);
                        array3[sizeof + 1] = (byte)((n9 & 0xFF0000) >> 16);
                        array3[sizeof + 2] = (byte)((n9 & 0xFF00) >> 8);
                        array3[sizeof + 3] = (byte)((n9 & 0xFF) >> 0);
                        array3[sizeof + 4] = (byte)((n10 & 0xFF000000) >> 24);
                        array3[sizeof + 5] = (byte)((n10 & 0xFF0000) >> 16);
                        array3[sizeof + 6] = (byte)((n10 & 0xFF00) >> 8);
                        array3[sizeof + 7] = (byte)((n10 & 0xFF) >> 0);
                        array3[sizeof + 8] = (byte)((n11 & 0xFF000000) >> 24);
                        array3[sizeof + 9] = (byte)((n11 & 0xFF0000) >> 16);
                        array3[sizeof + 10] = (byte)((n11 & 0xFF00) >> 8);
                        array3[sizeof + 11] = (byte)((n11 & 0xFF) >> 0);
                        break;
                    }
                    default: {
                        SWT.error(38);
                        break;
                    }
                }
            }
        }
        else {
            for (int j = 0; j < array.length; ++j) {
                array3[sizeof] = (byte)array[j].blue;
                array3[sizeof + 1] = (byte)array[j].green;
                array3[sizeof + 2] = (byte)array[j].red;
                array3[sizeof + 3] = 0;
                sizeof += 4;
            }
        }
        final int createDIBSection = OS.CreateDIBSection(0, array3, 0, new int[1], 0, 0);
        if (createDIBSection == 0) {
            SWT.error(2);
        }
        final int createCompatibleDC = OS.CreateCompatibleDC(n);
        final int createCompatibleDC2 = OS.CreateCompatibleDC(n);
        final int selectObject = OS.SelectObject(createCompatibleDC, n2);
        final int selectObject2 = OS.SelectObject(createCompatibleDC2, createDIBSection);
        OS.BitBlt(createCompatibleDC2, 0, 0, biWidth, n3, createCompatibleDC, 0, 0, 13369376);
        OS.SelectObject(createCompatibleDC, selectObject);
        OS.SelectObject(createCompatibleDC2, selectObject2);
        OS.DeleteDC(createCompatibleDC);
        OS.DeleteDC(createCompatibleDC2);
        return createDIBSection;
    }
    
    int[] createGdipImage() {
        switch (this.type) {
            case 0: {
                if (this.alpha != -1 || this.alphaData != null || this.transparentPixel != -1) {
                    final BITMAP bitmap = new BITMAP();
                    OS.GetObject(this.handle, BITMAP.sizeof, bitmap);
                    final int bmWidth = bitmap.bmWidth;
                    final int bmHeight = bitmap.bmHeight;
                    final int internal_new_GC = this.device.internal_new_GC(null);
                    final int createCompatibleDC = OS.CreateCompatibleDC(internal_new_GC);
                    final int selectObject = OS.SelectObject(createCompatibleDC, this.handle);
                    final int createCompatibleDC2 = OS.CreateCompatibleDC(internal_new_GC);
                    final int dib = createDIB(bmWidth, bmHeight, 32);
                    if (dib == 0) {
                        SWT.error(2);
                    }
                    final int selectObject2 = OS.SelectObject(createCompatibleDC2, dib);
                    final BITMAP bitmap2 = new BITMAP();
                    OS.GetObject(dib, BITMAP.sizeof, bitmap2);
                    final int n = bitmap2.bmWidthBytes * bitmap2.bmHeight;
                    OS.BitBlt(createCompatibleDC2, 0, 0, bmWidth, bmHeight, createCompatibleDC, 0, 0, 13369376);
                    byte b = 0;
                    byte b2 = 0;
                    byte b3 = 0;
                    if (this.transparentPixel != -1) {
                        if (bitmap.bmBitsPixel <= 8) {
                            final byte[] array = new byte[4];
                            OS.GetDIBColorTable(createCompatibleDC, this.transparentPixel, 1, array);
                            b3 = array[0];
                            b2 = array[1];
                            b = array[2];
                        }
                        else {
                            switch (bitmap.bmBitsPixel) {
                                case 16: {
                                    final int n2 = 31;
                                    final int channelShift = ImageData.getChannelShift(n2);
                                    b3 = ImageData.ANY_TO_EIGHT[ImageData.getChannelWidth(n2, channelShift)][(this.transparentPixel & n2) >> channelShift];
                                    final int n3 = 992;
                                    final int channelShift2 = ImageData.getChannelShift(n3);
                                    b2 = ImageData.ANY_TO_EIGHT[ImageData.getChannelWidth(n3, channelShift2)][(this.transparentPixel & n3) >> channelShift2];
                                    final int n4 = 31744;
                                    final int channelShift3 = ImageData.getChannelShift(n4);
                                    b = ImageData.ANY_TO_EIGHT[ImageData.getChannelWidth(n4, channelShift3)][(this.transparentPixel & n4) >> channelShift3];
                                    break;
                                }
                                case 24: {
                                    b3 = (byte)((this.transparentPixel & 0xFF0000) >> 16);
                                    b2 = (byte)((this.transparentPixel & 0xFF00) >> 8);
                                    b = (byte)(this.transparentPixel & 0xFF);
                                    break;
                                }
                                case 32: {
                                    b3 = (byte)((this.transparentPixel & 0xFF000000) >>> 24);
                                    b2 = (byte)((this.transparentPixel & 0xFF0000) >> 16);
                                    b = (byte)((this.transparentPixel & 0xFF00) >> 8);
                                    break;
                                }
                            }
                        }
                    }
                    OS.SelectObject(createCompatibleDC, selectObject);
                    OS.SelectObject(createCompatibleDC2, selectObject2);
                    OS.DeleteObject(createCompatibleDC);
                    OS.DeleteObject(createCompatibleDC2);
                    final byte[] array2 = new byte[n];
                    OS.MoveMemory(array2, bitmap2.bmBits, n);
                    OS.DeleteObject(dib);
                    this.device.internal_dispose_GC(internal_new_GC, null);
                    if (this.alpha != -1) {
                        int i = 0;
                        int n5 = 0;
                        while (i < bmHeight) {
                            for (int j = 0; j < bmWidth; ++j) {
                                array2[n5 + 3] = (byte)this.alpha;
                                n5 += 4;
                            }
                            ++i;
                        }
                    }
                    else if (this.alphaData != null) {
                        int k = 0;
                        int n6 = 0;
                        int n7 = 0;
                        while (k < bmHeight) {
                            for (int l = 0; l < bmWidth; ++l) {
                                array2[n6 + 3] = this.alphaData[n7++];
                                n6 += 4;
                            }
                            ++k;
                        }
                    }
                    else if (this.transparentPixel != -1) {
                        int n8 = 0;
                        int n9 = 0;
                        while (n8 < bmHeight) {
                            for (int n10 = 0; n10 < bmWidth; ++n10) {
                                if (array2[n9] == b3 && array2[n9 + 1] == b2 && array2[n9 + 2] == b) {
                                    array2[n9 + 3] = 0;
                                }
                                else {
                                    array2[n9 + 3] = -1;
                                }
                                n9 += 4;
                            }
                            ++n8;
                        }
                    }
                    final int heapAlloc = OS.HeapAlloc(OS.GetProcessHeap(), 8, array2.length);
                    if (heapAlloc == 0) {
                        SWT.error(2);
                    }
                    OS.MoveMemory(heapAlloc, array2, n);
                    return new int[] { Gdip.Bitmap_new(bmWidth, bmHeight, bitmap2.bmWidthBytes, 2498570, heapAlloc), heapAlloc };
                }
                return new int[] { Gdip.Bitmap_new(this.handle, 0), 0 };
            }
            case 1: {
                final ICONINFO iconinfo = new ICONINFO();
                if (OS.IsWinCE) {
                    GetIconInfo(this, iconinfo);
                }
                else {
                    OS.GetIconInfo(this.handle, iconinfo);
                }
                int n11 = iconinfo.hbmColor;
                if (n11 == 0) {
                    n11 = iconinfo.hbmMask;
                }
                final BITMAP bitmap3 = new BITMAP();
                OS.GetObject(n11, BITMAP.sizeof, bitmap3);
                final int bmWidth2 = bitmap3.bmWidth;
                final int n12 = (n11 == iconinfo.hbmMask) ? (bitmap3.bmHeight / 2) : bitmap3.bmHeight;
                int heapAlloc2 = 0;
                int n16;
                if (bmWidth2 > n12 || bitmap3.bmBitsPixel == 32) {
                    final int internal_new_GC2 = this.device.internal_new_GC(null);
                    final int createCompatibleDC3 = OS.CreateCompatibleDC(internal_new_GC2);
                    final int selectObject3 = OS.SelectObject(createCompatibleDC3, n11);
                    final int createCompatibleDC4 = OS.CreateCompatibleDC(internal_new_GC2);
                    final int dib2 = createDIB(bmWidth2, n12, 32);
                    if (dib2 == 0) {
                        SWT.error(2);
                    }
                    final int selectObject4 = OS.SelectObject(createCompatibleDC4, dib2);
                    final BITMAP bitmap4 = new BITMAP();
                    OS.GetObject(dib2, BITMAP.sizeof, bitmap4);
                    OS.BitBlt(createCompatibleDC4, 0, 0, bmWidth2, n12, createCompatibleDC3, 0, (n11 == iconinfo.hbmMask) ? n12 : 0, 13369376);
                    OS.SelectObject(createCompatibleDC4, selectObject4);
                    OS.DeleteObject(createCompatibleDC4);
                    final byte[] array3 = new byte[bitmap4.bmWidthBytes * bitmap4.bmHeight];
                    OS.MoveMemory(array3, bitmap4.bmBits, array3.length);
                    OS.DeleteObject(dib2);
                    OS.SelectObject(createCompatibleDC3, iconinfo.hbmMask);
                    int n13 = 0;
                    int n14 = 3;
                    while (n13 < n12) {
                        for (int n15 = 0; n15 < bmWidth2; ++n15) {
                            if (array3[n14] == 0) {
                                if (OS.GetPixel(createCompatibleDC3, n15, n13) != 0) {
                                    array3[n14] = 0;
                                }
                                else {
                                    array3[n14] = -1;
                                }
                            }
                            n14 += 4;
                        }
                        ++n13;
                    }
                    OS.SelectObject(createCompatibleDC3, selectObject3);
                    OS.DeleteObject(createCompatibleDC3);
                    this.device.internal_dispose_GC(internal_new_GC2, null);
                    heapAlloc2 = OS.HeapAlloc(OS.GetProcessHeap(), 8, array3.length);
                    if (heapAlloc2 == 0) {
                        SWT.error(2);
                    }
                    OS.MoveMemory(heapAlloc2, array3, array3.length);
                    n16 = Gdip.Bitmap_new(bmWidth2, n12, bitmap4.bmWidthBytes, 2498570, heapAlloc2);
                }
                else {
                    n16 = Gdip.Bitmap_new(this.handle);
                }
                if (iconinfo.hbmColor != 0) {
                    OS.DeleteObject(iconinfo.hbmColor);
                }
                if (iconinfo.hbmMask != 0) {
                    OS.DeleteObject(iconinfo.hbmMask);
                }
                return new int[] { n16, heapAlloc2 };
            }
            default: {
                SWT.error(40);
                return null;
            }
        }
    }
    
    void destroy() {
        if (this.memGC != null) {
            this.memGC.dispose();
        }
        if (this.type == 1) {
            if (OS.IsWinCE) {
                this.data = null;
            }
            OS.DestroyIcon(this.handle);
        }
        else {
            OS.DeleteObject(this.handle);
        }
        this.handle = 0;
        this.memGC = null;
    }
    
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Image)) {
            return false;
        }
        final Image image = (Image)o;
        return this.device == image.device && this.handle == image.handle;
    }
    
    public Color getBackground() {
        if (this.isDisposed()) {
            SWT.error(44);
        }
        if (this.transparentPixel == -1) {
            return null;
        }
        final int internal_new_GC = this.device.internal_new_GC(null);
        final BITMAP bitmap = new BITMAP();
        OS.GetObject(this.handle, BITMAP.sizeof, bitmap);
        final int createCompatibleDC = OS.CreateCompatibleDC(internal_new_GC);
        final int selectObject = OS.SelectObject(createCompatibleDC, this.handle);
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        if (bitmap.bmBitsPixel <= 8) {
            if (OS.IsWinCE) {
                final byte[] array = { 0 };
                OS.MoveMemory(array, bitmap.bmBits, 1);
                final byte b = array[0];
                array[0] = (byte)(this.transparentPixel << 8 - bitmap.bmBitsPixel | (array[0] & ~(255 << 8 - bitmap.bmBitsPixel & 0xFF)));
                OS.MoveMemory(bitmap.bmBits, array, 1);
                final int getPixel = OS.GetPixel(createCompatibleDC, 0, 0);
                array[0] = b;
                OS.MoveMemory(bitmap.bmBits, array, 1);
                n = (getPixel & 0xFF0000) >> 16;
                n2 = (getPixel & 0xFF00) >> 8;
                n3 = (getPixel & 0xFF);
            }
            else {
                final byte[] array2 = new byte[4];
                OS.GetDIBColorTable(createCompatibleDC, this.transparentPixel, 1, array2);
                n = (array2[0] & 0xFF);
                n2 = (array2[1] & 0xFF);
                n3 = (array2[2] & 0xFF);
            }
        }
        else {
            switch (bitmap.bmBitsPixel) {
                case 16: {
                    n = (this.transparentPixel & 0x1F) << 3;
                    n2 = (this.transparentPixel & 0x3E0) >> 2;
                    n3 = (this.transparentPixel & 0x7C00) >> 7;
                    break;
                }
                case 24: {
                    n = (this.transparentPixel & 0xFF0000) >> 16;
                    n2 = (this.transparentPixel & 0xFF00) >> 8;
                    n3 = (this.transparentPixel & 0xFF);
                    break;
                }
                case 32: {
                    n = (this.transparentPixel & 0xFF000000) >>> 24;
                    n2 = (this.transparentPixel & 0xFF0000) >> 16;
                    n3 = (this.transparentPixel & 0xFF00) >> 8;
                    break;
                }
                default: {
                    return null;
                }
            }
        }
        OS.SelectObject(createCompatibleDC, selectObject);
        OS.DeleteDC(createCompatibleDC);
        this.device.internal_dispose_GC(internal_new_GC, null);
        return Color.win32_new(this.device, n << 16 | n2 << 8 | n3);
    }
    
    public Rectangle getBounds() {
        if (this.isDisposed()) {
            SWT.error(44);
        }
        if (this.width != -1 && this.height != -1) {
            return new Rectangle(0, 0, this.width, this.height);
        }
        switch (this.type) {
            case 0: {
                final BITMAP bitmap = new BITMAP();
                OS.GetObject(this.handle, BITMAP.sizeof, bitmap);
                final boolean b = false;
                final boolean b2 = false;
                final int bmWidth = bitmap.bmWidth;
                this.width = bmWidth;
                return new Rectangle(b ? 1 : 0, b2 ? 1 : 0, bmWidth, this.height = bitmap.bmHeight);
            }
            case 1: {
                if (OS.IsWinCE) {
                    final boolean b3 = false;
                    final boolean b4 = false;
                    final int width = this.data.width;
                    this.width = width;
                    return new Rectangle(b3 ? 1 : 0, b4 ? 1 : 0, width, this.height = this.data.height);
                }
                final ICONINFO iconinfo = new ICONINFO();
                OS.GetIconInfo(this.handle, iconinfo);
                int n = iconinfo.hbmColor;
                if (n == 0) {
                    n = iconinfo.hbmMask;
                }
                final BITMAP bitmap2 = new BITMAP();
                OS.GetObject(n, BITMAP.sizeof, bitmap2);
                if (n == iconinfo.hbmMask) {
                    final BITMAP bitmap3 = bitmap2;
                    bitmap3.bmHeight /= 2;
                }
                if (iconinfo.hbmColor != 0) {
                    OS.DeleteObject(iconinfo.hbmColor);
                }
                if (iconinfo.hbmMask != 0) {
                    OS.DeleteObject(iconinfo.hbmMask);
                }
                final boolean b5 = false;
                final boolean b6 = false;
                final int bmWidth2 = bitmap2.bmWidth;
                this.width = bmWidth2;
                return new Rectangle(b5 ? 1 : 0, b6 ? 1 : 0, bmWidth2, this.height = bitmap2.bmHeight);
            }
            default: {
                SWT.error(40);
                return null;
            }
        }
    }
    
    public ImageData getImageData() {
        if (this.isDisposed()) {
            SWT.error(44);
        }
        switch (this.type) {
            case 1: {
                if (OS.IsWinCE) {
                    return this.data;
                }
                final ICONINFO iconinfo = new ICONINFO();
                if (OS.IsWinCE) {
                    SWT.error(20);
                }
                OS.GetIconInfo(this.handle, iconinfo);
                int n = iconinfo.hbmColor;
                if (n == 0) {
                    n = iconinfo.hbmMask;
                }
                final BITMAP bitmap = new BITMAP();
                OS.GetObject(n, BITMAP.sizeof, bitmap);
                final short n2 = (short)(bitmap.bmPlanes * bitmap.bmBitsPixel);
                final int bmWidth = bitmap.bmWidth;
                if (n == iconinfo.hbmMask) {
                    final BITMAP bitmap2 = bitmap;
                    bitmap2.bmHeight /= 2;
                }
                final int bmHeight = bitmap.bmHeight;
                int n3 = 0;
                if (n2 <= 8) {
                    n3 = 1 << n2;
                }
                final BITMAPINFOHEADER bitmapinfoheader = new BITMAPINFOHEADER();
                bitmapinfoheader.biSize = BITMAPINFOHEADER.sizeof;
                bitmapinfoheader.biWidth = bmWidth;
                bitmapinfoheader.biHeight = -bmHeight;
                bitmapinfoheader.biPlanes = 1;
                bitmapinfoheader.biBitCount = n2;
                bitmapinfoheader.biCompression = 0;
                final byte[] array = new byte[BITMAPINFOHEADER.sizeof + n3 * 4];
                OS.MoveMemory(array, bitmapinfoheader, BITMAPINFOHEADER.sizeof);
                final int internal_new_GC = this.device.internal_new_GC(null);
                final int createCompatibleDC = OS.CreateCompatibleDC(internal_new_GC);
                final int selectObject = OS.SelectObject(createCompatibleDC, n);
                int selectPalette = 0;
                if (n2 <= 8) {
                    final int hPalette = this.device.hPalette;
                    if (hPalette != 0) {
                        selectPalette = OS.SelectPalette(createCompatibleDC, hPalette, false);
                        OS.RealizePalette(createCompatibleDC);
                    }
                }
                if (OS.IsWinCE) {
                    SWT.error(20);
                }
                OS.GetDIBits(createCompatibleDC, n, 0, bmHeight, 0, array, 0);
                OS.MoveMemory(bitmapinfoheader, array, BITMAPINFOHEADER.sizeof);
                final int biSizeImage = bitmapinfoheader.biSizeImage;
                final byte[] array2 = new byte[biSizeImage];
                final int getProcessHeap = OS.GetProcessHeap();
                final int heapAlloc = OS.HeapAlloc(getProcessHeap, 8, biSizeImage);
                if (heapAlloc == 0) {
                    SWT.error(2);
                }
                if (OS.IsWinCE) {
                    SWT.error(20);
                }
                OS.GetDIBits(createCompatibleDC, n, 0, bmHeight, heapAlloc, array, 0);
                OS.MoveMemory(array2, heapAlloc, biSizeImage);
                PaletteData paletteData = null;
                if (n2 <= 8) {
                    final RGB[] array3 = new RGB[n3];
                    int n4 = 40;
                    for (int i = 0; i < n3; ++i) {
                        array3[i] = new RGB(array[n4 + 2] & 0xFF, array[n4 + 1] & 0xFF, array[n4] & 0xFF);
                        n4 += 4;
                    }
                    paletteData = new PaletteData(array3);
                }
                else if (n2 == 16) {
                    paletteData = new PaletteData(31744, 992, 31);
                }
                else if (n2 == 24) {
                    paletteData = new PaletteData(255, 65280, 16711680);
                }
                else if (n2 == 32) {
                    paletteData = new PaletteData(65280, 16711680, -16777216);
                }
                else {
                    SWT.error(38);
                }
                final byte[] array4 = null;
                byte[] convertPad;
                if (iconinfo.hbmColor == 0) {
                    convertPad = new byte[biSizeImage];
                    if (OS.IsWinCE) {
                        SWT.error(20);
                    }
                    OS.GetDIBits(createCompatibleDC, n, bmHeight, bmHeight, heapAlloc, array, 0);
                    OS.MoveMemory(convertPad, heapAlloc, biSizeImage);
                }
                else {
                    final BITMAPINFOHEADER bitmapinfoheader2 = new BITMAPINFOHEADER();
                    bitmapinfoheader2.biSize = BITMAPINFOHEADER.sizeof;
                    bitmapinfoheader2.biWidth = bmWidth;
                    bitmapinfoheader2.biHeight = -bmHeight;
                    bitmapinfoheader2.biPlanes = 1;
                    bitmapinfoheader2.biBitCount = 1;
                    bitmapinfoheader2.biCompression = 0;
                    final byte[] array5 = new byte[BITMAPINFOHEADER.sizeof + 8];
                    OS.MoveMemory(array5, bitmapinfoheader2, BITMAPINFOHEADER.sizeof);
                    final int sizeof = BITMAPINFOHEADER.sizeof;
                    final byte[] array6 = array5;
                    final int n5 = sizeof + 4;
                    final byte[] array7 = array5;
                    final int n6 = sizeof + 5;
                    final byte[] array8 = array5;
                    final int n7 = sizeof + 6;
                    final byte b = -1;
                    array8[n7] = b;
                    array6[n5] = (array7[n6] = b);
                    array5[sizeof + 7] = 0;
                    OS.SelectObject(createCompatibleDC, iconinfo.hbmMask);
                    if (OS.IsWinCE) {
                        SWT.error(20);
                    }
                    OS.GetDIBits(createCompatibleDC, iconinfo.hbmMask, 0, bmHeight, 0, array5, 0);
                    OS.MoveMemory(bitmapinfoheader2, array5, BITMAPINFOHEADER.sizeof);
                    final int biSizeImage2 = bitmapinfoheader2.biSizeImage;
                    final byte[] array9 = new byte[biSizeImage2];
                    final int heapAlloc2 = OS.HeapAlloc(getProcessHeap, 8, biSizeImage2);
                    if (heapAlloc2 == 0) {
                        SWT.error(2);
                    }
                    if (OS.IsWinCE) {
                        SWT.error(20);
                    }
                    OS.GetDIBits(createCompatibleDC, iconinfo.hbmMask, 0, bmHeight, heapAlloc2, array5, 0);
                    OS.MoveMemory(array9, heapAlloc2, biSizeImage2);
                    OS.HeapFree(getProcessHeap, 0, heapAlloc2);
                    for (int j = 0; j < array9.length; ++j) {
                        final byte[] array10 = array9;
                        final int n8 = j;
                        array10[n8] ^= -1;
                    }
                    int n9;
                    int n10;
                    for (n9 = biSizeImage2 / bmHeight, n10 = 1; n10 < 128 && ((bmWidth + 7) / 8 + (n10 - 1)) / n10 * n10 != n9; ++n10) {}
                    convertPad = ImageData.convertPad(array9, bmWidth, bmHeight, 1, n10, 2);
                }
                OS.HeapFree(getProcessHeap, 0, heapAlloc);
                OS.SelectObject(createCompatibleDC, selectObject);
                if (selectPalette != 0) {
                    OS.SelectPalette(createCompatibleDC, selectPalette, false);
                    OS.RealizePalette(createCompatibleDC);
                }
                OS.DeleteDC(createCompatibleDC);
                this.device.internal_dispose_GC(internal_new_GC, null);
                if (iconinfo.hbmColor != 0) {
                    OS.DeleteObject(iconinfo.hbmColor);
                }
                if (iconinfo.hbmMask != 0) {
                    OS.DeleteObject(iconinfo.hbmMask);
                }
                final ImageData imageData = new ImageData(bmWidth, bmHeight, n2, paletteData, 4, array2);
                imageData.maskData = convertPad;
                imageData.maskPad = 2;
                return imageData;
            }
            case 0: {
                final BITMAP bitmap3 = new BITMAP();
                OS.GetObject(this.handle, BITMAP.sizeof, bitmap3);
                final short n11 = (short)(bitmap3.bmPlanes * bitmap3.bmBitsPixel);
                final int bmWidth2 = bitmap3.bmWidth;
                final int bmHeight2 = bitmap3.bmHeight;
                int n12 = (bitmap3.bmBits != 0) ? 1 : 0;
                final int internal_new_GC2 = this.device.internal_new_GC(null);
                int n13 = this.handle;
                if (OS.IsWinCE && n12 == 0) {
                    boolean b2 = false;
                    if (this.memGC != null && !this.memGC.isDisposed()) {
                        this.memGC.flush();
                        b2 = true;
                        final GCData data = this.memGC.data;
                        if (data.hNullBitmap != 0) {
                            OS.SelectObject(this.memGC.handle, data.hNullBitmap);
                            data.hNullBitmap = 0;
                        }
                    }
                    n13 = this.createDIBFromDDB(internal_new_GC2, this.handle, bmWidth2, bmHeight2);
                    if (b2) {
                        this.memGC.data.hNullBitmap = OS.SelectObject(this.memGC.handle, this.handle);
                    }
                    n12 = 1;
                }
                DIBSECTION dibsection = null;
                if (n12 != 0) {
                    dibsection = new DIBSECTION();
                    OS.GetObject(n13, DIBSECTION.sizeof, dibsection);
                }
                int biClrUsed = 0;
                if (n11 <= 8) {
                    if (n12 != 0) {
                        biClrUsed = dibsection.biClrUsed;
                    }
                    else {
                        biClrUsed = 1 << n11;
                    }
                }
                byte[] array11 = null;
                BITMAPINFOHEADER bitmapinfoheader3 = null;
                if (n12 == 0) {
                    bitmapinfoheader3 = new BITMAPINFOHEADER();
                    bitmapinfoheader3.biSize = BITMAPINFOHEADER.sizeof;
                    bitmapinfoheader3.biWidth = bmWidth2;
                    bitmapinfoheader3.biHeight = -bmHeight2;
                    bitmapinfoheader3.biPlanes = 1;
                    bitmapinfoheader3.biBitCount = n11;
                    bitmapinfoheader3.biCompression = 0;
                    array11 = new byte[BITMAPINFOHEADER.sizeof + biClrUsed * 4];
                    OS.MoveMemory(array11, bitmapinfoheader3, BITMAPINFOHEADER.sizeof);
                }
                final int createCompatibleDC2 = OS.CreateCompatibleDC(internal_new_GC2);
                final int selectObject2 = OS.SelectObject(createCompatibleDC2, n13);
                int selectPalette2 = 0;
                if (n12 == 0 && n11 <= 8) {
                    final int hPalette2 = this.device.hPalette;
                    if (hPalette2 != 0) {
                        selectPalette2 = OS.SelectPalette(createCompatibleDC2, hPalette2, false);
                        OS.RealizePalette(createCompatibleDC2);
                    }
                }
                int n14;
                if (n12 != 0) {
                    n14 = dibsection.biSizeImage;
                }
                else {
                    if (OS.IsWinCE) {
                        SWT.error(20);
                    }
                    OS.GetDIBits(createCompatibleDC2, n13, 0, bmHeight2, 0, array11, 0);
                    OS.MoveMemory(bitmapinfoheader3, array11, BITMAPINFOHEADER.sizeof);
                    n14 = bitmapinfoheader3.biSizeImage;
                }
                final byte[] array12 = new byte[n14];
                if (n12 != 0) {
                    if (OS.IsWinCE && this.handle != n13) {
                        OS.MoveMemory(array12, dibsection.bmBits, n14);
                    }
                    else {
                        OS.MoveMemory(array12, bitmap3.bmBits, n14);
                    }
                }
                else {
                    final int getProcessHeap2 = OS.GetProcessHeap();
                    final int heapAlloc3 = OS.HeapAlloc(getProcessHeap2, 8, n14);
                    if (heapAlloc3 == 0) {
                        SWT.error(2);
                    }
                    if (OS.IsWinCE) {
                        SWT.error(20);
                    }
                    OS.GetDIBits(createCompatibleDC2, n13, 0, bmHeight2, heapAlloc3, array11, 0);
                    OS.MoveMemory(array12, heapAlloc3, n14);
                    OS.HeapFree(getProcessHeap2, 0, heapAlloc3);
                }
                PaletteData paletteData2 = null;
                if (n11 <= 8) {
                    final RGB[] array13 = new RGB[biClrUsed];
                    if (n12 != 0) {
                        if (OS.IsWinCE) {
                            final byte[] array14 = { 0 };
                            OS.MoveMemory(array14, bitmap3.bmBits, 1);
                            final byte b3 = array14[0];
                            final int n15 = 255 << 8 - bitmap3.bmBitsPixel & 0xFF;
                            for (int k = 0; k < biClrUsed; ++k) {
                                array14[0] = (byte)(k << 8 - bitmap3.bmBitsPixel | (array14[0] & ~n15));
                                OS.MoveMemory(bitmap3.bmBits, array14, 1);
                                final int getPixel = OS.GetPixel(createCompatibleDC2, 0, 0);
                                array13[k] = new RGB(getPixel & 0xFF, (getPixel & 0xFF00) >> 8, (getPixel & 0xFF0000) >> 16);
                            }
                            array14[0] = b3;
                            OS.MoveMemory(bitmap3.bmBits, array14, 1);
                        }
                        else {
                            final byte[] array15 = new byte[biClrUsed * 4];
                            OS.GetDIBColorTable(createCompatibleDC2, 0, biClrUsed, array15);
                            int n16 = 0;
                            for (int l = 0; l < array13.length; ++l) {
                                array13[l] = new RGB(array15[n16 + 2] & 0xFF, array15[n16 + 1] & 0xFF, array15[n16] & 0xFF);
                                n16 += 4;
                            }
                        }
                    }
                    else {
                        int sizeof2 = BITMAPINFOHEADER.sizeof;
                        for (int n17 = 0; n17 < biClrUsed; ++n17) {
                            array13[n17] = new RGB(array11[sizeof2 + 2] & 0xFF, array11[sizeof2 + 1] & 0xFF, array11[sizeof2] & 0xFF);
                            sizeof2 += 4;
                        }
                    }
                    paletteData2 = new PaletteData(array13);
                }
                else if (n11 == 16) {
                    paletteData2 = new PaletteData(31744, 992, 31);
                }
                else if (n11 == 24) {
                    paletteData2 = new PaletteData(255, 65280, 16711680);
                }
                else if (n11 == 32) {
                    paletteData2 = new PaletteData(65280, 16711680, -16777216);
                }
                else {
                    SWT.error(38);
                }
                OS.SelectObject(createCompatibleDC2, selectObject2);
                if (selectPalette2 != 0) {
                    OS.SelectPalette(createCompatibleDC2, selectPalette2, false);
                    OS.RealizePalette(createCompatibleDC2);
                }
                if (OS.IsWinCE && n13 != this.handle) {
                    OS.DeleteObject(n13);
                }
                OS.DeleteDC(createCompatibleDC2);
                this.device.internal_dispose_GC(internal_new_GC2, null);
                final ImageData imageData2 = new ImageData(bmWidth2, bmHeight2, n11, paletteData2, 4, array12);
                imageData2.transparentPixel = this.transparentPixel;
                imageData2.alpha = this.alpha;
                if (this.alpha == -1 && this.alphaData != null) {
                    imageData2.alphaData = new byte[this.alphaData.length];
                    System.arraycopy(this.alphaData, 0, imageData2.alphaData, 0, this.alphaData.length);
                }
                return imageData2;
            }
            default: {
                SWT.error(40);
                return null;
            }
        }
    }
    
    public int hashCode() {
        return this.handle;
    }
    
    void init(final int n, final int n2) {
        if (n <= 0 || n2 <= 0) {
            SWT.error(5);
        }
        this.type = 0;
        final int internal_new_GC = this.device.internal_new_GC(null);
        this.handle = OS.CreateCompatibleBitmap(internal_new_GC, n, n2);
        if (this.handle == 0) {
            int n3 = OS.GetDeviceCaps(internal_new_GC, 12) * OS.GetDeviceCaps(internal_new_GC, 14);
            if (n3 < 16) {
                n3 = 16;
            }
            this.handle = createDIB(n, n2, n3);
        }
        if (this.handle != 0) {
            final int createCompatibleDC = OS.CreateCompatibleDC(internal_new_GC);
            final int selectObject = OS.SelectObject(createCompatibleDC, this.handle);
            OS.PatBlt(createCompatibleDC, 0, 0, n, n2, 15728673);
            OS.SelectObject(createCompatibleDC, selectObject);
            OS.DeleteDC(createCompatibleDC);
        }
        this.device.internal_dispose_GC(internal_new_GC, null);
        if (this.handle == 0) {
            SWT.error(2, null, this.device.getLastError());
        }
    }
    
    static int createDIB(final int biWidth, final int n, final int n2) {
        final BITMAPINFOHEADER bitmapinfoheader = new BITMAPINFOHEADER();
        bitmapinfoheader.biSize = BITMAPINFOHEADER.sizeof;
        bitmapinfoheader.biWidth = biWidth;
        bitmapinfoheader.biHeight = -n;
        bitmapinfoheader.biPlanes = 1;
        bitmapinfoheader.biBitCount = (short)n2;
        if (OS.IsWinCE) {
            bitmapinfoheader.biCompression = 3;
        }
        else {
            bitmapinfoheader.biCompression = 0;
        }
        final byte[] array = new byte[BITMAPINFOHEADER.sizeof + (OS.IsWinCE ? 12 : 0)];
        OS.MoveMemory(array, bitmapinfoheader, BITMAPINFOHEADER.sizeof);
        if (OS.IsWinCE) {
            final int n3 = 65280;
            final int n4 = 16711680;
            final int n5 = -16777216;
            final int sizeof = BITMAPINFOHEADER.sizeof;
            array[sizeof] = (byte)((n3 & 0xFF000000) >> 24);
            array[sizeof + 1] = (byte)((n3 & 0xFF0000) >> 16);
            array[sizeof + 2] = (byte)((n3 & 0xFF00) >> 8);
            array[sizeof + 3] = (byte)((n3 & 0xFF) >> 0);
            array[sizeof + 4] = (byte)((n4 & 0xFF000000) >> 24);
            array[sizeof + 5] = (byte)((n4 & 0xFF0000) >> 16);
            array[sizeof + 6] = (byte)((n4 & 0xFF00) >> 8);
            array[sizeof + 7] = (byte)((n4 & 0xFF) >> 0);
            array[sizeof + 8] = (byte)((n5 & 0xFF000000) >> 24);
            array[sizeof + 9] = (byte)((n5 & 0xFF0000) >> 16);
            array[sizeof + 10] = (byte)((n5 & 0xFF00) >> 8);
            array[sizeof + 11] = (byte)((n5 & 0xFF) >> 0);
        }
        return OS.CreateDIBSection(0, array, 0, new int[1], 0, 0);
    }
    
    static void GetIconInfo(final Image image, final ICONINFO iconinfo) {
        final int[] init = init(image.device, null, image.data);
        iconinfo.hbmColor = init[0];
        iconinfo.hbmMask = init[1];
    }
    
    static int[] init(final Device device, final Image image, ImageData data) {
        if ((OS.IsWin95 && data.depth == 1 && data.getTransparencyType() != 2) || data.depth == 2) {
            final ImageData imageData = new ImageData(data.width, data.height, 4, data.palette);
            ImageData.blit(1, data.data, data.depth, data.bytesPerLine, data.getByteOrder(), 0, 0, data.width, data.height, null, null, null, 255, null, 0, 0, 0, imageData.data, imageData.depth, imageData.bytesPerLine, data.getByteOrder(), 0, 0, imageData.width, imageData.height, null, null, null, false, false);
            imageData.transparentPixel = data.transparentPixel;
            imageData.maskPad = data.maskPad;
            imageData.maskData = data.maskData;
            imageData.alpha = data.alpha;
            imageData.alphaData = data.alphaData;
            data = imageData;
        }
        if (data.palette.isDirect) {
            final PaletteData palette = data.palette;
            final int redMask = palette.redMask;
            final int greenMask = palette.greenMask;
            final int blueMask = palette.blueMask;
            int depth = data.depth;
            int n = 1;
            PaletteData paletteData = null;
            switch (data.depth) {
                case 8: {
                    depth = 16;
                    n = 0;
                    paletteData = new PaletteData(31744, 992, 31);
                    break;
                }
                case 16: {
                    n = 0;
                    if (redMask != 31744 || greenMask != 992 || blueMask != 31) {
                        paletteData = new PaletteData(31744, 992, 31);
                        break;
                    }
                    break;
                }
                case 24: {
                    if (redMask != 255 || greenMask != 65280 || blueMask != 16711680) {
                        paletteData = new PaletteData(255, 65280, 16711680);
                        break;
                    }
                    break;
                }
                case 32: {
                    if (redMask != 65280 || greenMask != 16711680 || blueMask != -16777216) {
                        paletteData = new PaletteData(65280, 16711680, -16777216);
                        break;
                    }
                    break;
                }
                default: {
                    SWT.error(38);
                    break;
                }
            }
            if (paletteData != null) {
                final ImageData imageData2 = new ImageData(data.width, data.height, depth, paletteData);
                ImageData.blit(1, data.data, data.depth, data.bytesPerLine, data.getByteOrder(), 0, 0, data.width, data.height, redMask, greenMask, blueMask, 255, null, 0, 0, 0, imageData2.data, imageData2.depth, imageData2.bytesPerLine, n, 0, 0, imageData2.width, imageData2.height, paletteData.redMask, paletteData.greenMask, paletteData.blueMask, false, false);
                if (data.transparentPixel != -1) {
                    imageData2.transparentPixel = paletteData.getPixel(palette.getRGB(data.transparentPixel));
                }
                imageData2.maskPad = data.maskPad;
                imageData2.maskData = data.maskData;
                imageData2.alpha = data.alpha;
                imageData2.alphaData = data.alphaData;
                data = imageData2;
            }
        }
        final RGB[] rgBs = data.palette.getRGBs();
        final boolean b = OS.IsWinCE && (data.depth == 16 || data.depth == 32);
        final BITMAPINFOHEADER bitmapinfoheader = new BITMAPINFOHEADER();
        bitmapinfoheader.biSize = BITMAPINFOHEADER.sizeof;
        bitmapinfoheader.biWidth = data.width;
        bitmapinfoheader.biHeight = -data.height;
        bitmapinfoheader.biPlanes = 1;
        bitmapinfoheader.biBitCount = (short)data.depth;
        if (b) {
            bitmapinfoheader.biCompression = 3;
        }
        else {
            bitmapinfoheader.biCompression = 0;
        }
        bitmapinfoheader.biClrUsed = ((rgBs == null) ? 0 : rgBs.length);
        byte[] array;
        if (data.palette.isDirect) {
            array = new byte[BITMAPINFOHEADER.sizeof + (b ? 12 : 0)];
        }
        else {
            array = new byte[BITMAPINFOHEADER.sizeof + rgBs.length * 4];
        }
        OS.MoveMemory(array, bitmapinfoheader, BITMAPINFOHEADER.sizeof);
        int sizeof = BITMAPINFOHEADER.sizeof;
        if (data.palette.isDirect) {
            if (b) {
                final PaletteData palette2 = data.palette;
                final int redMask2 = palette2.redMask;
                final int greenMask2 = palette2.greenMask;
                final int blueMask2 = palette2.blueMask;
                if (data.getByteOrder() == 0) {
                    array[sizeof] = (byte)((redMask2 & 0xFF) >> 0);
                    array[sizeof + 1] = (byte)((redMask2 & 0xFF00) >> 8);
                    array[sizeof + 2] = (byte)((redMask2 & 0xFF0000) >> 16);
                    array[sizeof + 3] = (byte)((redMask2 & 0xFF000000) >> 24);
                    array[sizeof + 4] = (byte)((greenMask2 & 0xFF) >> 0);
                    array[sizeof + 5] = (byte)((greenMask2 & 0xFF00) >> 8);
                    array[sizeof + 6] = (byte)((greenMask2 & 0xFF0000) >> 16);
                    array[sizeof + 7] = (byte)((greenMask2 & 0xFF000000) >> 24);
                    array[sizeof + 8] = (byte)((blueMask2 & 0xFF) >> 0);
                    array[sizeof + 9] = (byte)((blueMask2 & 0xFF00) >> 8);
                    array[sizeof + 10] = (byte)((blueMask2 & 0xFF0000) >> 16);
                    array[sizeof + 11] = (byte)((blueMask2 & 0xFF000000) >> 24);
                }
                else {
                    array[sizeof] = (byte)((redMask2 & 0xFF000000) >> 24);
                    array[sizeof + 1] = (byte)((redMask2 & 0xFF0000) >> 16);
                    array[sizeof + 2] = (byte)((redMask2 & 0xFF00) >> 8);
                    array[sizeof + 3] = (byte)((redMask2 & 0xFF) >> 0);
                    array[sizeof + 4] = (byte)((greenMask2 & 0xFF000000) >> 24);
                    array[sizeof + 5] = (byte)((greenMask2 & 0xFF0000) >> 16);
                    array[sizeof + 6] = (byte)((greenMask2 & 0xFF00) >> 8);
                    array[sizeof + 7] = (byte)((greenMask2 & 0xFF) >> 0);
                    array[sizeof + 8] = (byte)((blueMask2 & 0xFF000000) >> 24);
                    array[sizeof + 9] = (byte)((blueMask2 & 0xFF0000) >> 16);
                    array[sizeof + 10] = (byte)((blueMask2 & 0xFF00) >> 8);
                    array[sizeof + 11] = (byte)((blueMask2 & 0xFF) >> 0);
                }
            }
        }
        else {
            for (int i = 0; i < rgBs.length; ++i) {
                array[sizeof] = (byte)rgBs[i].blue;
                array[sizeof + 1] = (byte)rgBs[i].green;
                array[sizeof + 2] = (byte)rgBs[i].red;
                array[sizeof + 3] = 0;
                sizeof += 4;
            }
        }
        final int[] array2 = { 0 };
        final int createDIBSection = OS.CreateDIBSection(0, array, 0, array2, 0, 0);
        if (createDIBSection == 0) {
            SWT.error(2);
        }
        byte[] array3 = data.data;
        if (data.scanlinePad != 4 && data.bytesPerLine % 4 != 0) {
            array3 = ImageData.convertPad(array3, data.width, data.height, data.depth, data.scanlinePad, 4);
        }
        OS.MoveMemory(array2[0], array3, array3.length);
        int[] array4 = null;
        if (data.getTransparencyType() == 2) {
            final int internal_new_GC = device.internal_new_GC(null);
            final int createCompatibleDC = OS.CreateCompatibleDC(internal_new_GC);
            OS.SelectObject(createCompatibleDC, createDIBSection);
            final int createCompatibleBitmap = OS.CreateCompatibleBitmap(internal_new_GC, data.width, data.height);
            if (createCompatibleBitmap == 0) {
                SWT.error(2);
            }
            final int createCompatibleDC2 = OS.CreateCompatibleDC(internal_new_GC);
            OS.SelectObject(createCompatibleDC2, createCompatibleBitmap);
            OS.BitBlt(createCompatibleDC2, 0, 0, data.width, data.height, createCompatibleDC, 0, 0, 13369376);
            device.internal_dispose_GC(internal_new_GC, null);
            final int createBitmap = OS.CreateBitmap(data.width, data.height, 1, 1, ImageData.convertPad(data.maskData, data.width, data.height, 1, data.maskPad, 2));
            if (createBitmap == 0) {
                SWT.error(2);
            }
            OS.SelectObject(createCompatibleDC, createBitmap);
            OS.PatBlt(createCompatibleDC, 0, 0, data.width, data.height, 5570569);
            OS.DeleteDC(createCompatibleDC);
            OS.DeleteDC(createCompatibleDC2);
            OS.DeleteObject(createDIBSection);
            if (image == null) {
                array4 = new int[] { createCompatibleBitmap, createBitmap };
            }
            else {
                final ICONINFO iconinfo = new ICONINFO();
                iconinfo.fIcon = true;
                iconinfo.hbmColor = createCompatibleBitmap;
                iconinfo.hbmMask = createBitmap;
                final int createIconIndirect = OS.CreateIconIndirect(iconinfo);
                if (createIconIndirect == 0) {
                    SWT.error(2);
                }
                OS.DeleteObject(createCompatibleBitmap);
                OS.DeleteObject(createBitmap);
                image.handle = createIconIndirect;
                image.type = 1;
                if (OS.IsWinCE) {
                    image.data = data;
                }
            }
        }
        else if (image == null) {
            array4 = new int[] { createDIBSection };
        }
        else {
            image.handle = createDIBSection;
            image.type = 0;
            image.transparentPixel = data.transparentPixel;
            if (image.transparentPixel == -1) {
                image.alpha = data.alpha;
                if (data.alpha == -1 && data.alphaData != null) {
                    final int length = data.alphaData.length;
                    image.alphaData = new byte[length];
                    System.arraycopy(data.alphaData, 0, image.alphaData, 0, length);
                }
            }
        }
        return array4;
    }
    
    static int[] init(final Device device, final Image image, final ImageData imageData, final ImageData imageData2) {
        int transparentPixel = 0;
        ImageData imageData3;
        if (imageData.palette.isDirect) {
            imageData3 = new ImageData(imageData.width, imageData.height, imageData.depth, imageData.palette);
        }
        else {
            final RGB rgb = new RGB(0, 0, 0);
            RGB[] rgBs = imageData.getRGBs();
            if (imageData.transparentPixel != -1) {
                final RGB[] array = new RGB[rgBs.length];
                System.arraycopy(rgBs, 0, array, 0, rgBs.length);
                RGB[] array2;
                if (imageData.transparentPixel >= array.length) {
                    array2 = new RGB[imageData.transparentPixel + 1];
                    System.arraycopy(array, 0, array2, 0, array.length);
                    for (int i = array.length; i <= imageData.transparentPixel; ++i) {
                        array2[i] = new RGB(0, 0, 0);
                    }
                }
                else {
                    array[imageData.transparentPixel] = rgb;
                    array2 = array;
                }
                transparentPixel = imageData.transparentPixel;
                imageData3 = new ImageData(imageData.width, imageData.height, imageData.depth, new PaletteData(array2));
            }
            else {
                while (transparentPixel < rgBs.length && !rgBs[transparentPixel].equals(rgb)) {
                    ++transparentPixel;
                }
                if (transparentPixel == rgBs.length) {
                    if (1 << imageData.depth > rgBs.length) {
                        final RGB[] array3 = new RGB[rgBs.length + 1];
                        System.arraycopy(rgBs, 0, array3, 0, rgBs.length);
                        array3[rgBs.length] = rgb;
                        rgBs = array3;
                    }
                    else {
                        transparentPixel = -1;
                    }
                }
                imageData3 = new ImageData(imageData.width, imageData.height, imageData.depth, new PaletteData(rgBs));
            }
        }
        if (transparentPixel == -1) {
            System.arraycopy(imageData.data, 0, imageData3.data, 0, imageData3.data.length);
        }
        else {
            final int[] array4 = new int[imageData3.width];
            final int[] array5 = new int[imageData2.width];
            for (int j = 0; j < imageData3.height; ++j) {
                imageData.getPixels(0, j, imageData3.width, array4, 0);
                imageData2.getPixels(0, j, imageData2.width, array5, 0);
                for (int k = 0; k < array4.length; ++k) {
                    if (array5[k] == 0) {
                        array4[k] = transparentPixel;
                    }
                }
                imageData3.setPixels(0, j, imageData.width, array4, 0);
            }
        }
        imageData3.maskPad = imageData2.scanlinePad;
        imageData3.maskData = imageData2.data;
        return init(device, image, imageData3);
    }
    
    void init(final ImageData imageData) {
        if (imageData == null) {
            SWT.error(4);
        }
        init(this.device, this, imageData);
    }
    
    public int internal_new_GC(final GCData gcData) {
        if (this.handle == 0) {
            SWT.error(44);
        }
        if (this.type != 0 || this.memGC != null) {
            SWT.error(5);
        }
        final int internal_new_GC = this.device.internal_new_GC(null);
        final int createCompatibleDC = OS.CreateCompatibleDC(internal_new_GC);
        this.device.internal_dispose_GC(internal_new_GC, null);
        if (createCompatibleDC == 0) {
            SWT.error(2);
        }
        if (gcData != null) {
            if ((gcData.style & 0x6000000) != 0x0) {
                gcData.layout = (((gcData.style & 0x4000000) != 0x0) ? 1 : 0);
            }
            else {
                gcData.style |= 0x2000000;
            }
            gcData.device = this.device;
            gcData.image = this;
            gcData.font = this.device.systemFont;
        }
        return createCompatibleDC;
    }
    
    public void internal_dispose_GC(final int n, final GCData gcData) {
        OS.DeleteDC(n);
    }
    
    public boolean isDisposed() {
        return this.handle == 0;
    }
    
    public void setBackground(final Color color) {
        if (OS.IsWinCE) {
            return;
        }
        if (this.isDisposed()) {
            SWT.error(44);
        }
        if (color == null) {
            SWT.error(4);
        }
        if (color.isDisposed()) {
            SWT.error(5);
        }
        if (this.transparentPixel == -1) {
            return;
        }
        this.transparentColor = -1;
        final int internal_new_GC = this.device.internal_new_GC(null);
        final BITMAP bitmap = new BITMAP();
        OS.GetObject(this.handle, BITMAP.sizeof, bitmap);
        final int createCompatibleDC = OS.CreateCompatibleDC(internal_new_GC);
        OS.SelectObject(createCompatibleDC, this.handle);
        final int n = 1 << bitmap.bmBitsPixel;
        final byte[] array = new byte[n * 4];
        if (OS.IsWinCE) {
            SWT.error(20);
        }
        final int getDIBColorTable = OS.GetDIBColorTable(createCompatibleDC, 0, n, array);
        final int n2 = this.transparentPixel * 4;
        array[n2] = (byte)color.getBlue();
        array[n2 + 1] = (byte)color.getGreen();
        array[n2 + 2] = (byte)color.getRed();
        if (OS.IsWinCE) {
            SWT.error(20);
        }
        OS.SetDIBColorTable(createCompatibleDC, 0, getDIBColorTable, array);
        OS.DeleteDC(createCompatibleDC);
        this.device.internal_dispose_GC(internal_new_GC, null);
    }
    
    public String toString() {
        if (this.isDisposed()) {
            return "Image {*DISPOSED*}";
        }
        return "Image {" + this.handle + "}";
    }
    
    public static Image win32_new(final Device device, final int type, final int handle) {
        final Image image = new Image(device);
        image.type = type;
        image.handle = handle;
        return image;
    }
}
