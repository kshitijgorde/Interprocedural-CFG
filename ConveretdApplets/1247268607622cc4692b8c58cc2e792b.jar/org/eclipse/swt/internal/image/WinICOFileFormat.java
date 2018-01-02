// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.image;

import org.eclipse.swt.graphics.ImageLoader;
import org.eclipse.swt.graphics.PaletteData;
import java.io.IOException;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.ImageData;

public final class WinICOFileFormat extends FileFormat
{
    byte[] bitInvertData(final byte[] array, final int n, final int n2) {
        for (int i = n; i < n2; ++i) {
            array[i] = (byte)(255 - array[i - n]);
        }
        return array;
    }
    
    static final byte[] convertPad(final byte[] array, final int n, final int n2, final int n3, final int n4, final int n5) {
        if (n4 == n5) {
            return array;
        }
        final int n6 = (n * n3 + 7) / 8;
        final int n7 = (n6 + (n4 - 1)) / n4 * n4;
        final int n8 = (n6 + (n5 - 1)) / n5 * n5;
        final byte[] array2 = new byte[n2 * n8];
        int n9 = 0;
        int n10 = 0;
        for (int i = 0; i < n2; ++i) {
            System.arraycopy(array, n9, array2, n10, n8);
            n9 += n7;
            n10 += n8;
        }
        return array2;
    }
    
    int iconSize(final ImageData imageData) {
        return 40 + ((imageData.palette.colors != null) ? (imageData.palette.colors.length * 4) : 0) + ((imageData.width * imageData.depth + 31) / 32 * 4 + (imageData.width + 31) / 32 * 4) * imageData.height;
    }
    
    boolean isFileFormat(final LEDataInputStream leDataInputStream) {
        try {
            final byte[] array = new byte[4];
            leDataInputStream.read(array);
            leDataInputStream.unread(array);
            return array[0] == 0 && array[1] == 0 && array[2] == 1 && array[3] == 0;
        }
        catch (Exception ex) {
            return false;
        }
    }
    
    boolean isValidIcon(final ImageData imageData) {
        switch (imageData.depth) {
            case 1:
            case 4:
            case 8: {
                if (imageData.palette.isDirect) {
                    return false;
                }
                final int length = imageData.palette.colors.length;
                return length == 2 || length == 16 || length == 32 || length == 256;
            }
            case 24:
            case 32: {
                return imageData.palette.isDirect;
            }
            default: {
                return false;
            }
        }
    }
    
    int loadFileHeader(final LEDataInputStream leDataInputStream) {
        final int[] array = new int[3];
        try {
            array[0] = leDataInputStream.readShort();
            array[1] = leDataInputStream.readShort();
            array[2] = leDataInputStream.readShort();
        }
        catch (IOException ex) {
            SWT.error(39, ex);
        }
        if (array[0] != 0 || array[1] != 1) {
            SWT.error(40);
        }
        final int n = array[2];
        if (n <= 0) {
            SWT.error(40);
        }
        return n;
    }
    
    int loadFileHeader(final LEDataInputStream leDataInputStream, final boolean b) {
        final int[] array = new int[3];
        try {
            if (b) {
                array[0] = leDataInputStream.readShort();
                array[1] = leDataInputStream.readShort();
            }
            else {
                array[0] = 0;
                array[1] = 1;
            }
            array[2] = leDataInputStream.readShort();
        }
        catch (IOException ex) {
            SWT.error(39, ex);
        }
        if (array[0] != 0 || array[1] != 1) {
            SWT.error(40);
        }
        final int n = array[2];
        if (n <= 0) {
            SWT.error(40);
        }
        return n;
    }
    
    ImageData[] loadFromByteStream() {
        final int[][] loadIconHeaders = this.loadIconHeaders(this.loadFileHeader(this.inputStream));
        final ImageData[] array = new ImageData[loadIconHeaders.length];
        for (int i = 0; i < array.length; ++i) {
            array[i] = this.loadIcon(loadIconHeaders[i]);
        }
        return array;
    }
    
    ImageData loadIcon(final int[] array) {
        final byte[] loadInfoHeader = this.loadInfoHeader(array);
        final WinBMPFileFormat winBMPFileFormat = new WinBMPFileFormat();
        winBMPFileFormat.inputStream = this.inputStream;
        final PaletteData loadPalette = winBMPFileFormat.loadPalette(loadInfoHeader);
        final byte[] loadData = winBMPFileFormat.loadData(loadInfoHeader);
        final int n = (loadInfoHeader[4] & 0xFF) | (loadInfoHeader[5] & 0xFF) << 8 | (loadInfoHeader[6] & 0xFF) << 16 | (loadInfoHeader[7] & 0xFF) << 24;
        int n2 = (loadInfoHeader[8] & 0xFF) | (loadInfoHeader[9] & 0xFF) << 8 | (loadInfoHeader[10] & 0xFF) << 16 | (loadInfoHeader[11] & 0xFF) << 24;
        if (n2 < 0) {
            n2 = -n2;
        }
        final int n3 = (loadInfoHeader[14] & 0xFF) | (loadInfoHeader[15] & 0xFF) << 8;
        loadInfoHeader[14] = 1;
        loadInfoHeader[15] = 0;
        final byte[] convertPad = convertPad(winBMPFileFormat.loadData(loadInfoHeader), n, n2, 1, 4, 2);
        this.bitInvertData(convertPad, 0, convertPad.length);
        return ImageData.internal_new(n, n2, n3, loadPalette, 4, loadData, 2, convertPad, null, -1, -1, 3, 0, 0, 0, 0);
    }
    
    int[][] loadIconHeaders(final int n) {
        final int[][] array = new int[n][7];
        try {
            for (int i = 0; i < n; ++i) {
                array[i][0] = this.inputStream.read();
                array[i][1] = this.inputStream.read();
                array[i][2] = this.inputStream.readShort();
                array[i][3] = this.inputStream.readShort();
                array[i][4] = this.inputStream.readShort();
                array[i][5] = this.inputStream.readInt();
                array[i][6] = this.inputStream.readInt();
            }
        }
        catch (IOException ex) {
            SWT.error(39, ex);
        }
        return array;
    }
    
    byte[] loadInfoHeader(final int[] array) {
        final int n = array[0];
        int n2 = array[1];
        int n3 = array[2];
        if (n3 == 0) {
            n3 = 256;
        }
        if (n3 != 2 && n3 != 8 && n3 != 16 && n3 != 32 && n3 != 256) {
            SWT.error(40);
        }
        if (this.inputStream.getPosition() < array[6]) {
            try {
                this.inputStream.skip(array[6] - this.inputStream.getPosition());
            }
            catch (IOException ex) {
                SWT.error(39, ex);
                return null;
            }
        }
        final byte[] array2 = new byte[40];
        try {
            this.inputStream.read(array2);
        }
        catch (IOException ex2) {
            SWT.error(39, ex2);
        }
        if (((array2[12] & 0xFF) | (array2[13] & 0xFF) << 8) != 0x1) {
            SWT.error(40);
        }
        final int n4 = (array2[4] & 0xFF) | (array2[5] & 0xFF) << 8 | (array2[6] & 0xFF) << 16 | (array2[7] & 0xFF) << 24;
        final int n5 = (array2[8] & 0xFF) | (array2[9] & 0xFF) << 8 | (array2[10] & 0xFF) << 16 | (array2[11] & 0xFF) << 24;
        final int n6 = (array2[14] & 0xFF) | (array2[15] & 0xFF) << 8;
        if (n2 == n5 && n6 == 1) {
            n2 /= 2;
        }
        if (n != n4 || n2 * 2 != n5 || (n6 != 1 && n6 != 4 && n6 != 8 && n6 != 24 && n6 != 32)) {
            SWT.error(40);
        }
        array2[8] = (byte)(n2 & 0xFF);
        array2[9] = (byte)(n2 >> 8 & 0xFF);
        array2[10] = (byte)(n2 >> 16 & 0xFF);
        array2[11] = (byte)(n2 >> 24 & 0xFF);
        return array2;
    }
    
    void unloadIcon(final ImageData imageData) {
        final int n = ((imageData.width * imageData.depth + 31) / 32 * 4 + (imageData.width + 31) / 32 * 4) * imageData.height;
        try {
            this.outputStream.writeInt(40);
            this.outputStream.writeInt(imageData.width);
            this.outputStream.writeInt(imageData.height * 2);
            this.outputStream.writeShort(1);
            this.outputStream.writeShort((short)imageData.depth);
            this.outputStream.writeInt(0);
            this.outputStream.writeInt(n);
            this.outputStream.writeInt(0);
            this.outputStream.writeInt(0);
            this.outputStream.writeInt((imageData.palette.colors != null) ? imageData.palette.colors.length : 0);
            this.outputStream.writeInt(0);
        }
        catch (IOException ex) {
            SWT.error(39, ex);
        }
        final byte[] paletteToBytes = WinBMPFileFormat.paletteToBytes(imageData.palette);
        try {
            this.outputStream.write(paletteToBytes);
        }
        catch (IOException ex2) {
            SWT.error(39, ex2);
        }
        this.unloadShapeData(imageData);
        this.unloadMaskData(imageData);
    }
    
    void unloadIconHeader(final ImageData imageData) {
        final int n = 16 + 6;
        final int iconSize = this.iconSize(imageData);
        try {
            this.outputStream.write(imageData.width);
            this.outputStream.write(imageData.height);
            this.outputStream.writeShort((imageData.palette.colors != null) ? imageData.palette.colors.length : 0);
            this.outputStream.writeShort(0);
            this.outputStream.writeShort(0);
            this.outputStream.writeInt(iconSize);
            this.outputStream.writeInt(n);
        }
        catch (IOException ex) {
            SWT.error(39, ex);
        }
    }
    
    void unloadIntoByteStream(final ImageLoader imageLoader) {
        final ImageData imageData = imageLoader.data[0];
        if (!this.isValidIcon(imageData)) {
            SWT.error(40);
        }
        try {
            this.outputStream.writeShort(0);
            this.outputStream.writeShort(1);
            this.outputStream.writeShort(1);
        }
        catch (IOException ex) {
            SWT.error(39, ex);
        }
        this.unloadIconHeader(imageData);
        this.unloadIcon(imageData);
    }
    
    void unloadMaskData(final ImageData imageData) {
        final ImageData transparencyMask = imageData.getTransparencyMask();
        final int n = (imageData.width + 7) / 8;
        final int scanlinePad = transparencyMask.scanlinePad;
        final int n2 = (n + scanlinePad - 1) / scanlinePad * scanlinePad;
        final int n3 = (n + 3) / 4 * 4;
        final byte[] array = new byte[n3];
        int n4 = (imageData.height - 1) * n2;
        final byte[] data = transparencyMask.data;
        try {
            for (int i = 0; i < imageData.height; ++i) {
                System.arraycopy(data, n4, array, 0, n);
                this.bitInvertData(array, 0, n);
                this.outputStream.write(array, 0, n3);
                n4 -= n2;
            }
        }
        catch (IOException ex) {
            SWT.error(39, ex);
        }
    }
    
    void unloadShapeData(final ImageData imageData) {
        final int n = (imageData.width * imageData.depth + 7) / 8;
        final int scanlinePad = imageData.scanlinePad;
        final int n2 = (n + scanlinePad - 1) / scanlinePad * scanlinePad;
        final int n3 = (n + 3) / 4 * 4;
        final byte[] array = new byte[n3];
        int n4 = (imageData.height - 1) * n2;
        final byte[] data = imageData.data;
        try {
            for (int i = 0; i < imageData.height; ++i) {
                System.arraycopy(data, n4, array, 0, n);
                this.outputStream.write(array, 0, n3);
                n4 -= n2;
            }
        }
        catch (IOException ex) {
            SWT.error(39, ex);
        }
    }
}
