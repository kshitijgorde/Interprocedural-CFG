// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.PaletteData;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.SWT;
import org.eclipse.swt.internal.win32.BITMAPINFOHEADER;
import org.eclipse.swt.internal.win32.BITMAP;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.graphics.Image;

public class ImageList
{
    int handle;
    int style;
    int refCount;
    Image[] images;
    
    public ImageList(final int style) {
        this.style = style;
        final boolean b = true;
        int n;
        if (OS.IsWinCE) {
            n = ((b | false) ? 1 : 0);
        }
        else if (OS.COMCTL32_MAJOR >= 6) {
            n = ((b ? 1 : 0) | 0x20);
        }
        else {
            final int getDC = OS.GetDC(0);
            final int getDeviceCaps = OS.GetDeviceCaps(getDC, 12);
            final int getDeviceCaps2 = OS.GetDeviceCaps(getDC, 14);
            OS.ReleaseDC(0, getDC);
            switch (getDeviceCaps * getDeviceCaps2) {
                case 4: {
                    n = ((b ? 1 : 0) | 0x4);
                    break;
                }
                case 8: {
                    n = ((b ? 1 : 0) | 0x8);
                    break;
                }
                case 16: {
                    n = ((b ? 1 : 0) | 0x10);
                    break;
                }
                case 24: {
                    n = ((b ? 1 : 0) | 0x18);
                    break;
                }
                case 32: {
                    n = ((b ? 1 : 0) | 0x20);
                    break;
                }
                default: {
                    n = ((b | false) ? 1 : 0);
                    break;
                }
            }
        }
        this.handle = OS.ImageList_Create(32, 32, n | 0x2000, 16, 16);
        this.images = new Image[4];
    }
    
    public int add(final Image image) {
        int imageList_GetImageCount;
        int i;
        for (imageList_GetImageCount = OS.ImageList_GetImageCount(this.handle), i = 0; i < imageList_GetImageCount; ++i) {
            if (this.images[i] != null && this.images[i].isDisposed()) {
                this.images[i] = null;
            }
            if (this.images[i] == null) {
                break;
            }
        }
        if (imageList_GetImageCount == 0) {
            final Rectangle bounds = image.getBounds();
            OS.ImageList_SetIconSize(this.handle, bounds.width, bounds.height);
        }
        this.set(i, image, imageList_GetImageCount);
        if (i == this.images.length) {
            final Image[] images = new Image[this.images.length + 4];
            System.arraycopy(this.images, 0, images, 0, this.images.length);
            this.images = images;
        }
        this.images[i] = image;
        return i;
    }
    
    public int addRef() {
        return ++this.refCount;
    }
    
    int copyBitmap(final int n, final int biWidth, final int n2) {
        final BITMAP bitmap = new BITMAP();
        OS.GetObject(n, BITMAP.sizeof, bitmap);
        final int getDC = OS.GetDC(0);
        final int createCompatibleDC = OS.CreateCompatibleDC(getDC);
        OS.SelectObject(createCompatibleDC, n);
        final int createCompatibleDC2 = OS.CreateCompatibleDC(getDC);
        int n6;
        if (bitmap.bmBitsPixel == 32 && OS.COMCTL32_MAJOR >= 6) {
            final BITMAPINFOHEADER bitmapinfoheader = new BITMAPINFOHEADER();
            bitmapinfoheader.biSize = BITMAPINFOHEADER.sizeof;
            bitmapinfoheader.biWidth = biWidth;
            bitmapinfoheader.biHeight = -n2;
            bitmapinfoheader.biPlanes = 1;
            bitmapinfoheader.biBitCount = 24;
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
            n6 = OS.CreateDIBSection(0, array, 0, new int[1], 0, 0);
        }
        else {
            n6 = OS.CreateCompatibleBitmap(getDC, biWidth, n2);
        }
        OS.SelectObject(createCompatibleDC2, n6);
        if (biWidth != bitmap.bmWidth || n2 != bitmap.bmHeight) {
            if (!OS.IsWinCE) {
                OS.SetStretchBltMode(createCompatibleDC2, 3);
            }
            OS.StretchBlt(createCompatibleDC2, 0, 0, biWidth, n2, createCompatibleDC, 0, 0, bitmap.bmWidth, bitmap.bmHeight, 13369376);
        }
        else {
            OS.BitBlt(createCompatibleDC2, 0, 0, biWidth, n2, createCompatibleDC, 0, 0, 13369376);
        }
        OS.DeleteDC(createCompatibleDC);
        OS.DeleteDC(createCompatibleDC2);
        OS.ReleaseDC(0, getDC);
        return n6;
    }
    
    int copyIcon(final int n, final int n2, final int n3) {
        if (OS.IsWinCE) {
            SWT.error(20);
        }
        final int copyImage = OS.CopyImage(n, 1, n2, n3, 0);
        return (copyImage != 0) ? copyImage : n;
    }
    
    int copyWithAlpha(final int n, final int n2, final byte[] array, final int biWidth, final int n3) {
        final BITMAP bitmap = new BITMAP();
        OS.GetObject(n, BITMAP.sizeof, bitmap);
        final int bmWidth = bitmap.bmWidth;
        final int bmHeight = bitmap.bmHeight;
        final int getDC = OS.GetDC(0);
        final int createCompatibleDC = OS.CreateCompatibleDC(getDC);
        final int selectObject = OS.SelectObject(createCompatibleDC, n);
        final int createCompatibleDC2 = OS.CreateCompatibleDC(getDC);
        final BITMAPINFOHEADER bitmapinfoheader = new BITMAPINFOHEADER();
        bitmapinfoheader.biSize = BITMAPINFOHEADER.sizeof;
        bitmapinfoheader.biWidth = bmWidth;
        bitmapinfoheader.biHeight = -bmHeight;
        bitmapinfoheader.biPlanes = 1;
        bitmapinfoheader.biBitCount = 32;
        bitmapinfoheader.biCompression = 0;
        final byte[] array2 = new byte[BITMAPINFOHEADER.sizeof];
        OS.MoveMemory(array2, bitmapinfoheader, BITMAPINFOHEADER.sizeof);
        int createDIBSection = OS.CreateDIBSection(0, array2, 0, new int[1], 0, 0);
        if (createDIBSection == 0) {
            SWT.error(2);
        }
        final int selectObject2 = OS.SelectObject(createCompatibleDC2, createDIBSection);
        final BITMAP bitmap2 = new BITMAP();
        OS.GetObject(createDIBSection, BITMAP.sizeof, bitmap2);
        final int n4 = bitmap2.bmWidthBytes * bitmap2.bmHeight;
        OS.BitBlt(createCompatibleDC2, 0, 0, bmWidth, bmHeight, createCompatibleDC, 0, 0, 13369376);
        final byte[] array3 = new byte[n4];
        OS.MoveMemory(array3, bitmap2.bmBits, n4);
        if (array != null) {
            final int n5 = bitmap2.bmWidthBytes - bmWidth * 4;
            int n6 = 0;
            int n7 = 3;
            for (int i = 0; i < bmHeight; ++i) {
                for (int j = 0; j < bmWidth; ++j) {
                    array3[n7] = array[n6++];
                    n7 += 4;
                }
                n7 += n5;
            }
        }
        else {
            final byte b = (byte)(n2 & 0xFF);
            final byte b2 = (byte)(n2 >> 8 & 0xFF);
            final byte b3 = (byte)(n2 >> 16 & 0xFF);
            final int n8 = bitmap2.bmWidthBytes - bmWidth * 4;
            int n9 = 3;
            for (int k = 0; k < bmHeight; ++k) {
                for (int l = 0; l < bmWidth; ++l) {
                    array3[n9] = (byte)((array3[n9 - 1] == b && array3[n9 - 2] == b2 && array3[n9 - 3] == b3) ? 0 : -1);
                    n9 += 4;
                }
                n9 += n8;
            }
        }
        OS.MoveMemory(bitmap2.bmBits, array3, n4);
        if (bmWidth != biWidth || bmHeight != n3) {
            final BITMAPINFOHEADER bitmapinfoheader2 = new BITMAPINFOHEADER();
            bitmapinfoheader2.biSize = BITMAPINFOHEADER.sizeof;
            bitmapinfoheader2.biWidth = biWidth;
            bitmapinfoheader2.biHeight = -n3;
            bitmapinfoheader2.biPlanes = 1;
            bitmapinfoheader2.biBitCount = 32;
            bitmapinfoheader2.biCompression = 0;
            final byte[] array4 = new byte[BITMAPINFOHEADER.sizeof];
            OS.MoveMemory(array4, bitmapinfoheader2, BITMAPINFOHEADER.sizeof);
            final int createDIBSection2 = OS.CreateDIBSection(0, array4, 0, new int[1], 0, 0);
            final int createCompatibleDC3 = OS.CreateCompatibleDC(getDC);
            final int selectObject3 = OS.SelectObject(createCompatibleDC3, createDIBSection2);
            if (!OS.IsWinCE) {
                OS.SetStretchBltMode(createCompatibleDC3, 3);
            }
            OS.StretchBlt(createCompatibleDC3, 0, 0, biWidth, n3, createCompatibleDC2, 0, 0, bmWidth, bmHeight, 13369376);
            OS.SelectObject(createCompatibleDC3, selectObject3);
            OS.DeleteDC(createCompatibleDC3);
            OS.SelectObject(createCompatibleDC2, selectObject2);
            OS.DeleteDC(createCompatibleDC2);
            OS.DeleteObject(createDIBSection);
            createDIBSection = createDIBSection2;
        }
        else {
            OS.SelectObject(createCompatibleDC2, selectObject2);
            OS.DeleteDC(createCompatibleDC2);
        }
        OS.SelectObject(createCompatibleDC, selectObject);
        OS.DeleteDC(createCompatibleDC);
        OS.ReleaseDC(0, getDC);
        return createDIBSection;
    }
    
    int createMaskFromAlpha(final ImageData imageData, final int n, final int n2) {
        final int width = imageData.width;
        final int height = imageData.height;
        final ImageData internal_new = ImageData.internal_new(width, height, 1, new PaletteData(new RGB[] { new RGB(0, 0, 0), new RGB(255, 255, 255) }), 2, null, 1, null, null, -1, -1, -1, 0, 0, 0, 0);
        int n3 = 0;
        for (int i = 0; i < internal_new.height; ++i) {
            for (int j = 0; j < internal_new.width; ++j) {
                internal_new.setPixel(j, i, ((imageData.alphaData[n3++] & 0xFF) <= 127) ? 1 : 0);
            }
        }
        int createBitmap = OS.CreateBitmap(width, height, 1, 1, internal_new.data);
        if (width != n || height != n2) {
            final int getDC = OS.GetDC(0);
            final int createCompatibleDC = OS.CreateCompatibleDC(getDC);
            OS.SelectObject(createCompatibleDC, createBitmap);
            final int createCompatibleDC2 = OS.CreateCompatibleDC(getDC);
            final int createBitmap2 = OS.CreateBitmap(n, n2, 1, 1, null);
            OS.SelectObject(createCompatibleDC2, createBitmap2);
            if (!OS.IsWinCE) {
                OS.SetStretchBltMode(createCompatibleDC2, 3);
            }
            OS.StretchBlt(createCompatibleDC2, 0, 0, n, n2, createCompatibleDC, 0, 0, width, height, 13369376);
            OS.DeleteDC(createCompatibleDC);
            OS.DeleteDC(createCompatibleDC2);
            OS.ReleaseDC(0, getDC);
            OS.DeleteObject(createBitmap);
            createBitmap = createBitmap2;
        }
        return createBitmap;
    }
    
    int createMask(final int n, final int n2, final int n3, final int n4, final int n5) {
        final BITMAP bitmap = new BITMAP();
        OS.GetObject(n, BITMAP.sizeof, bitmap);
        final int bmWidth = bitmap.bmWidth;
        final int bmHeight = bitmap.bmHeight;
        final int createBitmap = OS.CreateBitmap(n2, n3, 1, 1, null);
        final int getDC = OS.GetDC(0);
        final int createCompatibleDC = OS.CreateCompatibleDC(getDC);
        if (n4 != -1) {
            OS.SelectObject(createCompatibleDC, n);
            final boolean b = bitmap.bmBits != 0;
            byte[] array = null;
            if (!OS.IsWinCE && n5 != -1 && b && bitmap.bmBitsPixel <= 8) {
                final int n6 = 1 << bitmap.bmBitsPixel;
                final byte[] array2 = new byte[n6 * 4];
                OS.GetDIBColorTable(createCompatibleDC, 0, n6, array2);
                final int n7 = n5 * 4;
                final byte[] array3 = new byte[array2.length];
                array3[n7] = -1;
                array3[n7 + 2] = (array3[n7 + 1] = -1);
                OS.SetDIBColorTable(createCompatibleDC, 0, n6, array3);
                array = array2;
                OS.SetBkColor(createCompatibleDC, 16777215);
            }
            else {
                OS.SetBkColor(createCompatibleDC, n4);
            }
            final int createCompatibleDC2 = OS.CreateCompatibleDC(getDC);
            OS.SelectObject(createCompatibleDC2, createBitmap);
            if (n2 != bmWidth || n3 != bmHeight) {
                if (!OS.IsWinCE) {
                    OS.SetStretchBltMode(createCompatibleDC2, 3);
                }
                OS.StretchBlt(createCompatibleDC2, 0, 0, n2, n3, createCompatibleDC, 0, 0, bmWidth, bmHeight, 13369376);
            }
            else {
                OS.BitBlt(createCompatibleDC2, 0, 0, n2, n3, createCompatibleDC, 0, 0, 13369376);
            }
            OS.DeleteDC(createCompatibleDC2);
            if (array != null) {
                OS.SetDIBColorTable(createCompatibleDC, 0, 1 << bitmap.bmBitsPixel, array);
            }
        }
        else {
            final int selectObject = OS.SelectObject(createCompatibleDC, createBitmap);
            OS.PatBlt(createCompatibleDC, 0, 0, n2, n3, 66);
            OS.SelectObject(createCompatibleDC, selectObject);
        }
        OS.ReleaseDC(0, getDC);
        OS.DeleteDC(createCompatibleDC);
        return createBitmap;
    }
    
    public void dispose() {
        if (this.handle != 0) {
            OS.ImageList_Destroy(this.handle);
        }
        this.handle = 0;
        this.images = null;
    }
    
    public Image get(final int n) {
        return this.images[n];
    }
    
    public int getStyle() {
        return this.style;
    }
    
    public int getHandle() {
        return this.handle;
    }
    
    public Point getImageSize() {
        final int[] array = { 0 };
        final int[] array2 = { 0 };
        OS.ImageList_GetIconSize(this.handle, array, array2);
        return new Point(array[0], array2[0]);
    }
    
    public int indexOf(final Image image) {
        for (int imageList_GetImageCount = OS.ImageList_GetImageCount(this.handle), i = 0; i < imageList_GetImageCount; ++i) {
            if (this.images[i] != null) {
                if (this.images[i].isDisposed()) {
                    this.images[i] = null;
                }
                if (this.images[i] != null && this.images[i].equals(image)) {
                    return i;
                }
            }
        }
        return -1;
    }
    
    public void put(final int n, final Image image) {
        final int imageList_GetImageCount = OS.ImageList_GetImageCount(this.handle);
        if (n < 0 || n >= imageList_GetImageCount) {
            return;
        }
        if (image != null) {
            this.set(n, image, imageList_GetImageCount);
        }
        this.images[n] = image;
    }
    
    public void remove(final int n) {
        int imageList_GetImageCount = OS.ImageList_GetImageCount(this.handle);
        if (n < 0 || n >= imageList_GetImageCount) {
            return;
        }
        OS.ImageList_Remove(this.handle, n);
        System.arraycopy(this.images, n + 1, this.images, n, --imageList_GetImageCount - n);
        this.images[n] = null;
    }
    
    public int removeRef() {
        return --this.refCount;
    }
    
    void set(final int n, final Image image, final int n2) {
        final int handle = image.handle;
        final int[] array = { 0 };
        final int[] array2 = { 0 };
        OS.ImageList_GetIconSize(this.handle, array, array2);
        switch (image.type) {
            case 0: {
                int n3 = 0;
                final ImageData imageData = image.getImageData();
                int n4 = 0;
                switch (imageData.getTransparencyType()) {
                    case 1: {
                        if (OS.COMCTL32_MAJOR >= 6) {
                            n4 = this.copyWithAlpha(handle, -1, imageData.alphaData, array[0], array2[0]);
                            break;
                        }
                        n4 = this.copyBitmap(handle, array[0], array2[0]);
                        n3 = this.createMaskFromAlpha(imageData, array[0], array2[0]);
                        break;
                    }
                    case 4: {
                        int handle2 = -1;
                        final Color background = image.getBackground();
                        if (background != null) {
                            handle2 = background.handle;
                        }
                        n4 = this.copyBitmap(handle, array[0], array2[0]);
                        n3 = this.createMask(handle, array[0], array2[0], handle2, imageData.transparentPixel);
                        break;
                    }
                    default: {
                        n4 = this.copyBitmap(handle, array[0], array2[0]);
                        if (n != n2) {
                            n3 = this.createMask(handle, array[0], array2[0], -1, -1);
                            break;
                        }
                        break;
                    }
                }
                if (n == n2) {
                    OS.ImageList_Add(this.handle, n4, n3);
                }
                else {
                    OS.ImageList_Replace(this.handle, n, n4, n3);
                }
                if (n3 != 0) {
                    OS.DeleteObject(n3);
                }
                if (n4 != handle) {
                    OS.DeleteObject(n4);
                    break;
                }
                break;
            }
            case 1: {
                if (OS.IsWinCE) {
                    OS.ImageList_ReplaceIcon(this.handle, (n == n2) ? -1 : n, handle);
                    break;
                }
                final int copyIcon = this.copyIcon(handle, array[0], array2[0]);
                OS.ImageList_ReplaceIcon(this.handle, (n == n2) ? -1 : n, copyIcon);
                OS.DestroyIcon(copyIcon);
                break;
            }
        }
    }
    
    public int size() {
        int n = 0;
        for (int imageList_GetImageCount = OS.ImageList_GetImageCount(this.handle), i = 0; i < imageList_GetImageCount; ++i) {
            if (this.images[i] != null) {
                if (this.images[i].isDisposed()) {
                    this.images[i] = null;
                }
                if (this.images[i] != null) {
                    ++n;
                }
            }
        }
        return n;
    }
}
