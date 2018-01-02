// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.image;

import java.io.ByteArrayOutputStream;
import org.eclipse.swt.graphics.ImageLoader;
import java.io.OutputStream;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.PaletteData;
import org.eclipse.swt.graphics.ImageData;
import java.io.IOException;
import org.eclipse.swt.SWT;

public final class OS2BMPFileFormat extends FileFormat
{
    static final int BMPFileHeaderSize = 14;
    static final int BMPHeaderFixedSize = 12;
    int width;
    int height;
    int bitCount;
    
    boolean isFileFormat(final LEDataInputStream leDataInputStream) {
        try {
            final byte[] array = new byte[18];
            leDataInputStream.read(array);
            leDataInputStream.unread(array);
            final int n = (array[14] & 0xFF) | (array[15] & 0xFF) << 8 | (array[16] & 0xFF) << 16 | (array[17] & 0xFF) << 24;
            return array[0] == 66 && array[1] == 77 && n == 12;
        }
        catch (Exception ex) {
            return false;
        }
    }
    
    byte[] loadData(final byte[] array) {
        final int n = ((this.width * this.bitCount + 7) / 8 + 3) / 4 * 4;
        final byte[] loadData = this.loadData(array, n);
        this.flipScanLines(loadData, n, this.height);
        return loadData;
    }
    
    byte[] loadData(final byte[] array, final int n) {
        final int n2 = this.height * n;
        final byte[] array2 = new byte[n2];
        try {
            if (this.inputStream.read(array2) != n2) {
                SWT.error(40);
            }
        }
        catch (IOException ex) {
            SWT.error(39, ex);
        }
        return array2;
    }
    
    int[] loadFileHeader() {
        final int[] array = new int[5];
        try {
            array[0] = this.inputStream.readShort();
            array[1] = this.inputStream.readInt();
            array[2] = this.inputStream.readShort();
            array[3] = this.inputStream.readShort();
            array[4] = this.inputStream.readInt();
        }
        catch (IOException ex) {
            SWT.error(39, ex);
        }
        if (array[0] != 19778) {
            SWT.error(40);
        }
        return array;
    }
    
    ImageData[] loadFromByteStream() {
        final int[] loadFileHeader = this.loadFileHeader();
        final byte[] array = new byte[12];
        try {
            this.inputStream.read(array);
        }
        catch (Exception ex) {
            SWT.error(39, ex);
        }
        this.width = ((array[4] & 0xFF) | (array[5] & 0xFF) << 8);
        this.height = ((array[6] & 0xFF) | (array[7] & 0xFF) << 8);
        this.bitCount = ((array[10] & 0xFF) | (array[11] & 0xFF) << 8);
        final PaletteData loadPalette = this.loadPalette(array);
        if (this.inputStream.getPosition() < loadFileHeader[4]) {
            try {
                this.inputStream.skip(loadFileHeader[4] - this.inputStream.getPosition());
            }
            catch (IOException ex2) {
                SWT.error(39, ex2);
            }
        }
        return new ImageData[] { ImageData.internal_new(this.width, this.height, this.bitCount, loadPalette, 4, this.loadData(array), 0, null, null, -1, -1, 7, 0, 0, 0, 0) };
    }
    
    PaletteData loadPalette(final byte[] array) {
        if (this.bitCount <= 8) {
            final int n = 1 << this.bitCount;
            final byte[] array2 = new byte[n * 3];
            try {
                if (this.inputStream.read(array2) != array2.length) {
                    SWT.error(40);
                }
            }
            catch (IOException ex) {
                SWT.error(39, ex);
            }
            return this.paletteFromBytes(array2, n);
        }
        if (this.bitCount == 16) {
            return new PaletteData(31744, 992, 31);
        }
        if (this.bitCount == 24) {
            return new PaletteData(255, 65280, 16711680);
        }
        return new PaletteData(65280, 16711680, -16777216);
    }
    
    PaletteData paletteFromBytes(final byte[] array, final int n) {
        int n2 = 0;
        final RGB[] array2 = new RGB[n];
        for (int i = 0; i < n; ++i) {
            array2[i] = new RGB(array[n2 + 2] & 0xFF, array[n2 + 1] & 0xFF, array[n2] & 0xFF);
            n2 += 3;
        }
        return new PaletteData(array2);
    }
    
    static byte[] paletteToBytes(final PaletteData paletteData) {
        final int n = (paletteData.colors == null) ? 0 : ((paletteData.colors.length < 256) ? paletteData.colors.length : 256);
        final byte[] array = new byte[n * 3];
        int n2 = 0;
        for (int i = 0; i < n; ++i) {
            final RGB rgb = paletteData.colors[i];
            array[n2] = (byte)rgb.blue;
            array[n2 + 1] = (byte)rgb.green;
            array[n2 + 2] = (byte)rgb.red;
            n2 += 3;
        }
        return array;
    }
    
    int unloadData(final ImageData imageData, final OutputStream outputStream) {
        int n = 0;
        try {
            final int n2 = (imageData.width * imageData.depth + 7) / 8;
            n = (n2 + 3) / 4 * 4;
            final int n3 = 32678 / n;
            final byte[] array = new byte[n3 * n];
            final byte[] data = imageData.data;
            final int bytesPerLine = imageData.bytesPerLine;
            int n4 = bytesPerLine * (imageData.height - 1);
            if (imageData.depth == 16) {
                for (int i = 0; i < imageData.height; i += n3) {
                    int n5 = imageData.height - i;
                    if (n3 < n5) {
                        n5 = n3;
                    }
                    int n6 = 0;
                    for (int j = 0; j < n5; ++j) {
                        for (int k = 0; k < n2; k += 2) {
                            array[n6 + k + 1] = data[n4 + k + 1];
                            array[n6 + k] = data[n4 + k];
                        }
                        n6 += n;
                        n4 -= bytesPerLine;
                    }
                    outputStream.write(array, 0, n6);
                }
            }
            else {
                for (int l = 0; l < imageData.height; l += n3) {
                    final int n7 = imageData.height - l;
                    final int n8 = (n7 < n3) ? n7 : n3;
                    int n9 = 0;
                    for (int n10 = 0; n10 < n8; ++n10) {
                        System.arraycopy(data, n4, array, n9, n2);
                        n9 += n;
                        n4 -= bytesPerLine;
                    }
                    outputStream.write(array, 0, n9);
                }
            }
        }
        catch (IOException ex) {
            SWT.error(39, ex);
        }
        return n * imageData.height;
    }
    
    void unloadIntoByteStream(final ImageLoader imageLoader) {
        final ImageData imageData = imageLoader.data[0];
        if (imageData.depth != 1 && imageData.depth != 4 && imageData.depth != 8 && imageData.depth != 16 && imageData.depth != 24 && imageData.depth != 32) {
            SWT.error(38);
        }
        final PaletteData palette = imageData.palette;
        int length;
        byte[] paletteToBytes;
        if (imageData.depth == 16 || imageData.depth == 24 || imageData.depth == 32) {
            if (!palette.isDirect) {
                SWT.error(40);
            }
            length = 0;
            paletteToBytes = null;
        }
        else {
            if (palette.isDirect) {
                SWT.error(40);
            }
            length = palette.colors.length;
            paletteToBytes = paletteToBytes(palette);
        }
        final int[] array = { 19778, 0, 0, 0, 26 };
        if (paletteToBytes != null) {
            final int[] array2 = array;
            final int n = 4;
            array2[n] += paletteToBytes.length;
        }
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        this.unloadData(imageData, byteArrayOutputStream);
        final byte[] byteArray = byteArrayOutputStream.toByteArray();
        array[1] = array[4] + byteArray.length;
        try {
            this.outputStream.writeShort(array[0]);
            this.outputStream.writeInt(array[1]);
            this.outputStream.writeShort(array[2]);
            this.outputStream.writeShort(array[3]);
            this.outputStream.writeInt(array[4]);
        }
        catch (IOException ex) {
            SWT.error(39, ex);
        }
        try {
            this.outputStream.writeInt(12);
            this.outputStream.writeShort(imageData.width);
            this.outputStream.writeShort(imageData.height);
            this.outputStream.writeShort(1);
            this.outputStream.writeShort((short)imageData.depth);
        }
        catch (IOException ex2) {
            SWT.error(39, ex2);
        }
        if (length > 0) {
            try {
                this.outputStream.write(paletteToBytes);
            }
            catch (IOException ex3) {
                SWT.error(39, ex3);
            }
        }
        try {
            this.outputStream.write(byteArray);
        }
        catch (IOException ex4) {
            SWT.error(39, ex4);
        }
    }
    
    void flipScanLines(final byte[] array, final int n, final int n2) {
        int n3 = 0;
        int n4 = (n2 - 1) * n;
        for (int i = 0; i < n2 / 2; ++i) {
            for (int j = 0; j < n; ++j) {
                final byte b = array[j + n3];
                array[j + n3] = array[j + n4];
                array[j + n4] = b;
            }
            n3 += n;
            n4 -= n;
        }
    }
}
