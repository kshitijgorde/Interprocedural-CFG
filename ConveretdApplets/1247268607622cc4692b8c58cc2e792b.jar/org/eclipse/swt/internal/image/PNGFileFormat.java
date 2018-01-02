// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.image;

import org.eclipse.swt.graphics.ImageLoaderEvent;
import java.io.InputStream;
import org.eclipse.swt.internal.Compatibility;
import org.eclipse.swt.graphics.ImageLoader;
import org.eclipse.swt.graphics.PaletteData;
import org.eclipse.swt.SWT;
import java.io.IOException;
import org.eclipse.swt.graphics.ImageData;

public final class PNGFileFormat extends FileFormat
{
    static final int SIGNATURE_LENGTH = 8;
    static final int PRIME = 65521;
    PngIhdrChunk headerChunk;
    PngPlteChunk paletteChunk;
    ImageData imageData;
    byte[] data;
    byte[] alphaPalette;
    byte headerByte1;
    byte headerByte2;
    int adler;
    
    void readSignature() throws IOException {
        this.inputStream.read(new byte[8]);
    }
    
    ImageData[] loadFromByteStream() {
        try {
            this.readSignature();
            final PngChunkReader pngChunkReader = new PngChunkReader(this.inputStream);
            this.headerChunk = pngChunkReader.getIhdrChunk();
            final int width = this.headerChunk.getWidth();
            final int height = this.headerChunk.getHeight();
            if (width <= 0 || height <= 0) {
                SWT.error(40);
            }
            this.data = new byte[this.getAlignedBytesPerRow() * height];
            this.imageData = ImageData.internal_new(width, height, this.headerChunk.getSwtBitsPerPixel(), new PaletteData(0, 0, 0), 4, this.data, 0, null, null, -1, -1, 5, 0, 0, 0, 0);
            if (this.headerChunk.usesDirectColor()) {
                this.imageData.palette = this.headerChunk.getPaletteData();
            }
            while (pngChunkReader.hasMoreChunks()) {
                this.readNextChunk(pngChunkReader);
            }
            return new ImageData[] { this.imageData };
        }
        catch (IOException ex) {
            SWT.error(40);
            return null;
        }
    }
    
    void readNextChunk(final PngChunkReader pngChunkReader) throws IOException {
        final PngChunk nextChunk = pngChunkReader.readNextChunk();
        switch (nextChunk.getChunkType()) {
            case 3: {
                break;
            }
            case 1: {
                if (!this.headerChunk.usesDirectColor()) {
                    this.paletteChunk = (PngPlteChunk)nextChunk;
                    this.imageData.palette = this.paletteChunk.getPaletteData();
                    break;
                }
                break;
            }
            case 5: {
                final PngTrnsChunk pngTrnsChunk = (PngTrnsChunk)nextChunk;
                if (pngTrnsChunk.getTransparencyType(this.headerChunk) == 0) {
                    this.imageData.transparentPixel = pngTrnsChunk.getSwtTransparentPixel(this.headerChunk);
                    break;
                }
                this.alphaPalette = pngTrnsChunk.getAlphaValues(this.headerChunk, this.paletteChunk);
                int n = 0;
                int transparentPixel = -1;
                for (int i = 0; i < this.alphaPalette.length; ++i) {
                    if ((this.alphaPalette[i] & 0xFF) != 0xFF) {
                        ++n;
                        transparentPixel = i;
                    }
                }
                if (n == 0) {
                    this.alphaPalette = null;
                    break;
                }
                if (n == 1 && this.alphaPalette[transparentPixel] == 0) {
                    this.alphaPalette = null;
                    this.imageData.transparentPixel = transparentPixel;
                    break;
                }
                break;
            }
            case 2: {
                if (pngChunkReader.readPixelData()) {
                    SWT.error(40);
                    break;
                }
                this.readPixelData((PngIdatChunk)nextChunk, pngChunkReader);
                break;
            }
            default: {
                if (nextChunk.isCritical()) {
                    SWT.error(20);
                    break;
                }
                break;
            }
        }
    }
    
    void unloadIntoByteStream(final ImageLoader imageLoader) {
        new PngEncoder(imageLoader).encode(this.outputStream);
    }
    
    boolean isFileFormat(final LEDataInputStream leDataInputStream) {
        try {
            final byte[] array = new byte[8];
            leDataInputStream.read(array);
            leDataInputStream.unread(array);
            return (array[0] & 0xFF) == 0x89 && (array[1] & 0xFF) == 0x50 && (array[2] & 0xFF) == 0x4E && (array[3] & 0xFF) == 0x47 && (array[4] & 0xFF) == 0xD && (array[5] & 0xFF) == 0xA && (array[6] & 0xFF) == 0x1A && (array[7] & 0xFF) == 0xA;
        }
        catch (Exception ex) {
            return false;
        }
    }
    
    byte[] validateBitDepth(final byte[] array) {
        if (this.headerChunk.getBitDepth() > 8) {
            final byte[] array2 = new byte[array.length / 2];
            compress16BitDepthTo8BitDepth(array, 0, array2, 0, array2.length);
            return array2;
        }
        return array;
    }
    
    void setPixelData(final byte[] array, final ImageData imageData) {
        switch (this.headerChunk.getColorType()) {
            case 4: {
                final int width = imageData.width;
                final int height = imageData.height;
                final int bytesPerLine = imageData.bytesPerLine;
                int alignedBytesPerRow = this.getAlignedBytesPerRow();
                if (this.headerChunk.getBitDepth() > 8) {
                    alignedBytesPerRow /= 2;
                }
                final byte[] data = new byte[bytesPerLine * height];
                final byte[] alphaData = new byte[width * height];
                for (int i = 0; i < height; ++i) {
                    int n = alignedBytesPerRow * i;
                    int n2 = bytesPerLine * i;
                    int n3 = width * i;
                    for (int j = 0; j < width; ++j) {
                        final byte b = array[n];
                        final byte b2 = array[n + 1];
                        data[n2 + 0] = b;
                        data[n2 + 2] = (data[n2 + 1] = b);
                        alphaData[n3] = b2;
                        n += 2;
                        n2 += 3;
                        ++n3;
                    }
                }
                imageData.data = data;
                imageData.alphaData = alphaData;
                break;
            }
            case 6: {
                final int width2 = imageData.width;
                final int height2 = imageData.height;
                final int bytesPerLine2 = imageData.bytesPerLine;
                int alignedBytesPerRow2 = this.getAlignedBytesPerRow();
                if (this.headerChunk.getBitDepth() > 8) {
                    alignedBytesPerRow2 /= 2;
                }
                final byte[] data2 = new byte[bytesPerLine2 * height2];
                final byte[] alphaData2 = new byte[width2 * height2];
                for (int k = 0; k < height2; ++k) {
                    int n4 = alignedBytesPerRow2 * k;
                    int n5 = bytesPerLine2 * k;
                    int n6 = width2 * k;
                    for (int l = 0; l < width2; ++l) {
                        data2[n5 + 0] = array[n4 + 0];
                        data2[n5 + 1] = array[n4 + 1];
                        data2[n5 + 2] = array[n4 + 2];
                        alphaData2[n6] = array[n4 + 3];
                        n4 += 4;
                        n5 += 3;
                        ++n6;
                    }
                }
                imageData.data = data2;
                imageData.alphaData = alphaData2;
                break;
            }
            case 3: {
                imageData.data = array;
                if (this.alphaPalette != null) {
                    final int n7 = imageData.width * imageData.height;
                    final byte[] alphaData3 = new byte[n7];
                    final byte[] array2 = new byte[n7];
                    imageData.getPixels(0, 0, n7, array2, 0);
                    for (int n8 = 0; n8 < array2.length; ++n8) {
                        alphaData3[n8] = this.alphaPalette[array2[n8] & 0xFF];
                    }
                    imageData.alphaData = alphaData3;
                    break;
                }
                break;
            }
            default: {
                final int height3 = imageData.height;
                final int bytesPerLine3 = imageData.bytesPerLine;
                int alignedBytesPerRow3 = this.getAlignedBytesPerRow();
                if (this.headerChunk.getBitDepth() > 8) {
                    alignedBytesPerRow3 /= 2;
                }
                if (bytesPerLine3 != alignedBytesPerRow3) {
                    for (int n9 = 0; n9 < height3; ++n9) {
                        System.arraycopy(array, n9 * alignedBytesPerRow3, imageData.data, n9 * bytesPerLine3, alignedBytesPerRow3);
                    }
                    break;
                }
                imageData.data = array;
                break;
            }
        }
    }
    
    void setImageDataValues(final byte[] array, final ImageData imageData) {
        this.setPixelData(this.validateBitDepth(array), imageData);
    }
    
    void readPixelData(final PngIdatChunk pngIdatChunk, final PngChunkReader pngChunkReader) throws IOException {
        final PngInputStream pngInputStream = new PngInputStream(pngIdatChunk, pngChunkReader);
        final InputStream inputStream = (System.getProperty("org.eclipse.swt.internal.image.PNGFileFormat_3.2") != null) ? null : Compatibility.newInflaterInputStream(pngInputStream);
        InputStream inputStream2;
        if (inputStream != null) {
            inputStream2 = inputStream;
        }
        else {
            inputStream2 = new PngDecodingDataStream(pngInputStream);
        }
        if (this.headerChunk.getInterlaceMethod() == 0) {
            this.readNonInterlacedImage(inputStream2);
        }
        else {
            this.readInterlacedImage(inputStream2);
        }
        while (inputStream2.available() > 0) {
            inputStream2.read();
        }
        inputStream2.close();
    }
    
    int getAlignedBytesPerRow() {
        return (this.getBytesPerRow(this.headerChunk.getWidth()) + 3) / 4 * 4;
    }
    
    int getBytesPerRow() {
        return this.getBytesPerRow(this.headerChunk.getWidth());
    }
    
    int getBytesPerPixel() {
        return (this.headerChunk.getBitsPerPixel() + 7) / 8;
    }
    
    int getBytesPerRow(final int n) {
        final int n2 = this.headerChunk.getBitsPerPixel() * n;
        final int n3 = 8;
        return (n2 + (n3 - 1)) / n3;
    }
    
    void readInterlaceFrame(final InputStream inputStream, final int n, final int n2, final int n3, final int n4, final int n5) throws IOException {
        final int width = this.headerChunk.getWidth();
        final int alignedBytesPerRow = this.getAlignedBytesPerRow();
        final int height = this.headerChunk.getHeight();
        if (n3 >= height || n4 >= width) {
            return;
        }
        final int bytesPerRow = this.getBytesPerRow((width - n4 + n2 - 1) / n2);
        final byte[] array = new byte[bytesPerRow];
        final byte[] array2 = new byte[bytesPerRow];
        byte[] array3 = array;
        byte[] array4 = array2;
        for (int i = n3; i < height; i += n) {
            final byte b = (byte)inputStream.read();
            for (int j = 0; j != bytesPerRow; j += inputStream.read(array3, j, bytesPerRow - j)) {}
            this.filterRow(array3, array4, b);
            if (this.headerChunk.getBitDepth() >= 8) {
                final int bytesPerPixel = this.getBytesPerPixel();
                int n6 = i * alignedBytesPerRow + n4 * bytesPerPixel;
                for (int k = 0; k < array3.length; k += bytesPerPixel) {
                    for (int l = 0; l < bytesPerPixel; ++l) {
                        this.data[n6 + l] = array3[k + l];
                    }
                    n6 += n2 * bytesPerPixel;
                }
            }
            else {
                final byte bitDepth = this.headerChunk.getBitDepth();
                final byte b2 = (byte)(8 / bitDepth);
                int n7 = n4;
                final int n8 = i * alignedBytesPerRow;
                int n9 = 0;
                for (byte b3 = 0; b3 < bitDepth; ++b3) {
                    n9 = (n9 << 1 | 0x1);
                }
                final byte b4 = (byte)(8 - bitDepth);
                for (int n10 = 0; n10 < array3.length; ++n10) {
                    for (byte b5 = b4; b5 >= 0; b5 -= bitDepth) {
                        if (n7 < width) {
                            final byte b6 = (byte)(n8 + n7 * bitDepth / 8);
                            final int n11 = array3[n10] >> b5 & n9;
                            final byte b7 = (byte)(b4 - bitDepth * (n7 % b2));
                            final byte[] data = this.data;
                            final byte b8 = b6;
                            data[b8] |= (byte)(n11 << b7);
                        }
                        n7 += n2;
                    }
                }
            }
            array3 = ((array3 == array) ? array2 : array);
            array4 = ((array4 == array) ? array2 : array);
        }
        this.setImageDataValues(this.data, this.imageData);
        this.fireInterlacedFrameEvent(n5);
    }
    
    void readInterlacedImage(final InputStream inputStream) throws IOException {
        this.readInterlaceFrame(inputStream, 8, 8, 0, 0, 0);
        this.readInterlaceFrame(inputStream, 8, 8, 0, 4, 1);
        this.readInterlaceFrame(inputStream, 8, 4, 4, 0, 2);
        this.readInterlaceFrame(inputStream, 4, 4, 0, 2, 3);
        this.readInterlaceFrame(inputStream, 4, 2, 2, 0, 4);
        this.readInterlaceFrame(inputStream, 2, 2, 0, 1, 5);
        this.readInterlaceFrame(inputStream, 2, 1, 1, 0, 6);
    }
    
    void fireInterlacedFrameEvent(final int n) {
        if (this.loader.hasListeners()) {
            this.loader.notifyListeners(new ImageLoaderEvent(this.loader, (ImageData)this.imageData.clone(), n, n == 6));
        }
    }
    
    void readNonInterlacedImage(final InputStream inputStream) throws IOException {
        int n = 0;
        final int alignedBytesPerRow = this.getAlignedBytesPerRow();
        final int bytesPerRow = this.getBytesPerRow();
        final byte[] array = new byte[bytesPerRow];
        final byte[] array2 = new byte[bytesPerRow];
        byte[] array3 = array;
        byte[] array4 = array2;
        for (int height = this.headerChunk.getHeight(), i = 0; i < height; ++i) {
            final byte b = (byte)inputStream.read();
            for (int j = 0; j != bytesPerRow; j += inputStream.read(array3, j, bytesPerRow - j)) {}
            this.filterRow(array3, array4, b);
            System.arraycopy(array3, 0, this.data, n, bytesPerRow);
            n += alignedBytesPerRow;
            array3 = ((array3 == array) ? array2 : array);
            array4 = ((array4 == array) ? array2 : array);
        }
        this.setImageDataValues(this.data, this.imageData);
    }
    
    static void compress16BitDepthTo8BitDepth(final byte[] array, final int n, final byte[] array2, final int n2, final int n3) {
        for (int i = 0; i < n3; ++i) {
            array2[n2 + i] = array[n + 2 * i];
        }
    }
    
    static int compress16BitDepthTo8BitDepth(final int n) {
        return n >> 8;
    }
    
    void filterRow(final byte[] array, final byte[] array2, final int n) {
        final int filterByteOffset = this.headerChunk.getFilterByteOffset();
        switch (n) {
            case 1: {
                for (int i = filterByteOffset; i < array.length; ++i) {
                    array[i] = (byte)((array[i] & 0xFF) + (array[i - filterByteOffset] & 0xFF) & 0xFF);
                }
                break;
            }
            case 2: {
                for (int j = 0; j < array.length; ++j) {
                    array[j] = (byte)((array[j] & 0xFF) + (array2[j] & 0xFF) & 0xFF);
                }
                break;
            }
            case 3: {
                for (int k = 0; k < array.length; ++k) {
                    array[k] = (byte)((array[k] & 0xFF) + (((k < filterByteOffset) ? 0 : (array[k - filterByteOffset] & 0xFF)) + (array2[k] & 0xFF)) / 2 & 0xFF);
                }
                break;
            }
            case 4: {
                for (int l = 0; l < array.length; ++l) {
                    final int n2 = (l < filterByteOffset) ? 0 : (array[l - filterByteOffset] & 0xFF);
                    final int n3 = (l < filterByteOffset) ? 0 : (array2[l - filterByteOffset] & 0xFF);
                    final int n4 = array2[l] & 0xFF;
                    final int abs = Math.abs(n4 - n3);
                    final int abs2 = Math.abs(n2 - n3);
                    final int abs3 = Math.abs(n2 - n3 + n4 - n3);
                    int n5;
                    if (abs <= abs2 && abs <= abs3) {
                        n5 = n2;
                    }
                    else if (abs2 <= abs3) {
                        n5 = n4;
                    }
                    else {
                        n5 = n3;
                    }
                    array[l] = (byte)((array[l] & 0xFF) + n5 & 0xFF);
                }
                break;
            }
        }
    }
}
