// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.dnd;

import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.internal.win32.DIBSECTION;
import org.eclipse.swt.internal.ole.win32.FORMATETC;
import org.eclipse.swt.internal.ole.win32.IDataObject;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.internal.ole.win32.STGMEDIUM;
import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.internal.win32.BITMAPINFOHEADER;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.ImageData;

public class ImageTransfer extends ByteArrayTransfer
{
    private static ImageTransfer _instance;
    private static final String CF_DIB = "CF_DIB";
    private static final int CF_DIBID = 8;
    
    static {
        ImageTransfer._instance = new ImageTransfer();
    }
    
    public static ImageTransfer getInstance() {
        return ImageTransfer._instance;
    }
    
    public void javaToNative(final Object o, final TransferData transferData) {
        if (!this.checkImage(o) || !this.isSupportedType(transferData)) {
            DND.error(2003);
        }
        final ImageData imageData = (ImageData)o;
        if (imageData == null) {
            SWT.error(4);
        }
        final int length = imageData.data.length;
        final int height = imageData.height;
        final int bytesPerLine = imageData.bytesPerLine;
        final BITMAPINFOHEADER bitmapinfoheader = new BITMAPINFOHEADER();
        bitmapinfoheader.biSize = BITMAPINFOHEADER.sizeof;
        bitmapinfoheader.biSizeImage = length;
        bitmapinfoheader.biWidth = imageData.width;
        bitmapinfoheader.biHeight = height;
        bitmapinfoheader.biPlanes = 1;
        bitmapinfoheader.biBitCount = (short)imageData.depth;
        bitmapinfoheader.biCompression = 0;
        int n = 0;
        if (bitmapinfoheader.biBitCount <= 8) {
            n += (1 << bitmapinfoheader.biBitCount) * 4;
        }
        final byte[] array = new byte[BITMAPINFOHEADER.sizeof + n];
        OS.MoveMemory(array, bitmapinfoheader, BITMAPINFOHEADER.sizeof);
        final RGB[] rgBs = imageData.palette.getRGBs();
        if (rgBs != null && n > 0) {
            int sizeof = BITMAPINFOHEADER.sizeof;
            for (int i = 0; i < rgBs.length; ++i) {
                array[sizeof] = (byte)rgBs[i].blue;
                array[sizeof + 1] = (byte)rgBs[i].green;
                array[sizeof + 2] = (byte)rgBs[i].red;
                array[sizeof + 3] = 0;
                sizeof += 4;
            }
        }
        final int globalAlloc = OS.GlobalAlloc(64, BITMAPINFOHEADER.sizeof + n + length);
        OS.MoveMemory(globalAlloc, array, array.length);
        final int n2 = globalAlloc + BITMAPINFOHEADER.sizeof + n;
        if (height <= 0) {
            OS.MoveMemory(n2, imageData.data, length);
        }
        else {
            int n3 = 0;
            int n4 = n2 + bytesPerLine * (height - 1);
            final byte[] array2 = new byte[bytesPerLine];
            for (int j = 0; j < height; ++j) {
                System.arraycopy(imageData.data, n3, array2, 0, bytesPerLine);
                OS.MoveMemory(n4, array2, bytesPerLine);
                n3 += bytesPerLine;
                n4 -= bytesPerLine;
            }
        }
        transferData.stgmedium = new STGMEDIUM();
        transferData.stgmedium.tymed = 1;
        transferData.stgmedium.unionField = globalAlloc;
        transferData.stgmedium.pUnkForRelease = 0;
        transferData.result = 0;
    }
    
    public Object nativeToJava(final TransferData transferData) {
        if (!this.isSupportedType(transferData) || transferData.pIDataObject == 0) {
            return null;
        }
        final IDataObject dataObject = new IDataObject(transferData.pIDataObject);
        dataObject.AddRef();
        final FORMATETC formatetc = new FORMATETC();
        formatetc.cfFormat = 8;
        formatetc.ptd = 0;
        formatetc.dwAspect = 1;
        formatetc.lindex = -1;
        formatetc.tymed = 1;
        final STGMEDIUM stgmedium = new STGMEDIUM();
        stgmedium.tymed = 1;
        transferData.result = this.getData(dataObject, formatetc, stgmedium);
        if (transferData.result != 0) {
            return null;
        }
        final int unionField = stgmedium.unionField;
        dataObject.Release();
        try {
            final int globalLock = OS.GlobalLock(unionField);
            if (globalLock == 0) {
                return null;
            }
            try {
                final BITMAPINFOHEADER bitmapinfoheader = new BITMAPINFOHEADER();
                OS.MoveMemory(bitmapinfoheader, globalLock, BITMAPINFOHEADER.sizeof);
                final int[] array = { 0 };
                final int createDIBSection = OS.CreateDIBSection(0, globalLock, 0, array, 0, 0);
                if (createDIBSection == 0) {
                    SWT.error(2);
                }
                int n = globalLock + bitmapinfoheader.biSize;
                if (bitmapinfoheader.biBitCount <= 8) {
                    n += ((bitmapinfoheader.biClrUsed == 0) ? (1 << bitmapinfoheader.biBitCount) : bitmapinfoheader.biClrUsed) * 4;
                }
                else if (bitmapinfoheader.biCompression == 3) {
                    n += 12;
                }
                if (bitmapinfoheader.biHeight < 0) {
                    OS.MoveMemory(array[0], n, bitmapinfoheader.biSizeImage);
                }
                else {
                    final DIBSECTION dibsection = new DIBSECTION();
                    OS.GetObject(createDIBSection, DIBSECTION.sizeof, dibsection);
                    final int biHeight = dibsection.biHeight;
                    final int n2 = dibsection.biSizeImage / biHeight;
                    int n3 = array[0];
                    int n4 = n + n2 * (biHeight - 1);
                    for (int i = 0; i < biHeight; ++i) {
                        OS.MoveMemory(n3, n4, n2);
                        n3 += n2;
                        n4 -= n2;
                    }
                }
                final Image win32_new = Image.win32_new(null, 0, createDIBSection);
                final ImageData imageData = win32_new.getImageData();
                OS.DeleteObject(createDIBSection);
                win32_new.dispose();
                return imageData;
            }
            finally {
                OS.GlobalUnlock(unionField);
            }
        }
        finally {
            OS.GlobalFree(unionField);
        }
    }
    
    protected int[] getTypeIds() {
        return new int[] { 8 };
    }
    
    protected String[] getTypeNames() {
        return new String[] { "CF_DIB" };
    }
    
    boolean checkImage(final Object o) {
        return o != null && o instanceof ImageData;
    }
    
    protected boolean validate(final Object o) {
        return this.checkImage(o);
    }
}
