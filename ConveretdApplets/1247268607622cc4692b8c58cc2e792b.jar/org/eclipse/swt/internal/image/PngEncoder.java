// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.image;

import java.io.IOException;
import java.io.OutputStream;
import org.eclipse.swt.internal.Compatibility;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.ImageLoader;
import java.io.ByteArrayOutputStream;

final class PngEncoder
{
    static final byte[] SIGNATURE;
    static final byte[] TAG_IHDR;
    static final byte[] TAG_PLTE;
    static final byte[] TAG_TRNS;
    static final byte[] TAG_IDAT;
    static final byte[] TAG_IEND;
    ByteArrayOutputStream bytes;
    PngChunk chunk;
    ImageLoader loader;
    ImageData data;
    int transparencyType;
    int width;
    int height;
    int bitDepth;
    int colorType;
    int compressionMethod;
    int filterMethod;
    int interlaceMethod;
    
    static {
        SIGNATURE = new byte[] { -119, 80, 78, 71, 13, 10, 26, 10 };
        TAG_IHDR = new byte[] { 73, 72, 68, 82 };
        TAG_PLTE = new byte[] { 80, 76, 84, 69 };
        TAG_TRNS = new byte[] { 116, 82, 78, 83 };
        TAG_IDAT = new byte[] { 73, 68, 65, 84 };
        TAG_IEND = new byte[] { 73, 69, 78, 68 };
    }
    
    public PngEncoder(final ImageLoader loader) {
        this.bytes = new ByteArrayOutputStream(1024);
        this.compressionMethod = 0;
        this.filterMethod = 0;
        this.interlaceMethod = 0;
        this.loader = loader;
        this.data = loader.data[0];
        this.transparencyType = this.data.getTransparencyType();
        this.width = this.data.width;
        this.height = this.data.height;
        this.bitDepth = 8;
        this.colorType = 2;
        if (this.data.palette.isDirect) {
            if (this.transparencyType == 1) {
                this.colorType = 6;
            }
        }
        else {
            this.colorType = 3;
        }
        if (this.colorType != 2 && this.colorType != 3 && this.colorType != 6) {
            SWT.error(40);
        }
    }
    
    void writeShort(final ByteArrayOutputStream byteArrayOutputStream, final int n) {
        byteArrayOutputStream.write(new byte[] { (byte)(n >> 8 & 0xFF), (byte)(n & 0xFF) }, 0, 2);
    }
    
    void writeInt(final ByteArrayOutputStream byteArrayOutputStream, final int n) {
        byteArrayOutputStream.write(new byte[] { (byte)(n >> 24 & 0xFF), (byte)(n >> 16 & 0xFF), (byte)(n >> 8 & 0xFF), (byte)(n & 0xFF) }, 0, 4);
    }
    
    void writeChunk(final byte[] type, final byte[] data) {
        final int n = (data != null) ? data.length : 0;
        this.chunk = new PngChunk(n);
        this.writeInt(this.bytes, n);
        this.bytes.write(type, 0, 4);
        this.chunk.setType(type);
        if (n != 0) {
            this.bytes.write(data, 0, n);
            this.chunk.setData(data);
        }
        else {
            this.chunk.setCRC(this.chunk.computeCRC());
        }
        this.writeInt(this.bytes, this.chunk.getCRC());
    }
    
    void writeSignature() {
        this.bytes.write(PngEncoder.SIGNATURE, 0, 8);
    }
    
    void writeHeader() {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(13);
        this.writeInt(byteArrayOutputStream, this.width);
        this.writeInt(byteArrayOutputStream, this.height);
        byteArrayOutputStream.write(this.bitDepth);
        byteArrayOutputStream.write(this.colorType);
        byteArrayOutputStream.write(this.compressionMethod);
        byteArrayOutputStream.write(this.filterMethod);
        byteArrayOutputStream.write(this.interlaceMethod);
        this.writeChunk(PngEncoder.TAG_IHDR, byteArrayOutputStream.toByteArray());
    }
    
    void writePalette() {
        final RGB[] rgBs = this.data.palette.getRGBs();
        if (rgBs.length > 256) {
            SWT.error(40);
        }
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(rgBs.length);
        for (int i = 0; i < rgBs.length; ++i) {
            byteArrayOutputStream.write((byte)rgBs[i].red);
            byteArrayOutputStream.write((byte)rgBs[i].green);
            byteArrayOutputStream.write((byte)rgBs[i].blue);
        }
        this.writeChunk(PngEncoder.TAG_PLTE, byteArrayOutputStream.toByteArray());
    }
    
    void writeTransparency() {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        switch (this.transparencyType) {
            case 1: {
                final byte[] array = new byte[this.data.palette.getRGBs().length];
                for (int i = 0; i < this.height; ++i) {
                    for (int j = 0; j < this.width; ++j) {
                        array[this.data.getPixel(j, i)] = (byte)this.data.getAlpha(j, i);
                    }
                }
                byteArrayOutputStream.write(array, 0, array.length);
                break;
            }
            case 4: {
                final int transparentPixel = this.data.transparentPixel;
                if (this.colorType == 2) {
                    final int redMask = this.data.palette.redMask;
                    final int redShift = this.data.palette.redShift;
                    final int greenMask = this.data.palette.greenMask;
                    final int greenShift = this.data.palette.greenShift;
                    final int blueShift = this.data.palette.blueShift;
                    final int blueMask = this.data.palette.blueMask;
                    final int n = transparentPixel & redMask;
                    final int n2 = (redShift < 0) ? (n >>> -redShift) : (n << redShift);
                    final int n3 = transparentPixel & greenMask;
                    final int n4 = (greenShift < 0) ? (n3 >>> -greenShift) : (n3 << greenShift);
                    final int n5 = transparentPixel & blueMask;
                    final int n6 = (blueShift < 0) ? (n5 >>> -blueShift) : (n5 << blueShift);
                    this.writeShort(byteArrayOutputStream, n2);
                    this.writeShort(byteArrayOutputStream, n4);
                    this.writeShort(byteArrayOutputStream, n6);
                }
                if (this.colorType == 3) {
                    final byte[] array2 = new byte[transparentPixel + 1];
                    for (int k = 0; k < transparentPixel; ++k) {
                        array2[k] = -1;
                    }
                    array2[transparentPixel] = 0;
                    byteArrayOutputStream.write(array2, 0, array2.length);
                    break;
                }
                break;
            }
        }
        this.writeChunk(PngEncoder.TAG_TRNS, byteArrayOutputStream.toByteArray());
    }
    
    void writeImageData() throws IOException {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(1024);
        OutputStream deflaterOutputStream = Compatibility.newDeflaterOutputStream(byteArrayOutputStream);
        if (deflaterOutputStream == null) {
            deflaterOutputStream = byteArrayOutputStream;
        }
        if (this.colorType == 3) {
            final byte[] array = new byte[this.width];
            for (int i = 0; i < this.height; ++i) {
                deflaterOutputStream.write(0);
                this.data.getPixels(0, i, this.width, array, 0);
                deflaterOutputStream.write(array);
            }
        }
        else {
            final int[] array2 = new int[this.width];
            byte[] array3 = null;
            if (this.colorType == 6) {
                array3 = new byte[this.width];
            }
            final int redMask = this.data.palette.redMask;
            final int redShift = this.data.palette.redShift;
            final int greenMask = this.data.palette.greenMask;
            final int greenShift = this.data.palette.greenShift;
            final int blueShift = this.data.palette.blueShift;
            final int blueMask = this.data.palette.blueMask;
            final byte[] array4 = new byte[this.width * ((this.colorType == 6) ? 4 : 3)];
            for (int j = 0; j < this.height; ++j) {
                deflaterOutputStream.write(0);
                this.data.getPixels(0, j, this.width, array2, 0);
                if (this.colorType == 6) {
                    this.data.getAlphas(0, j, this.width, array3, 0);
                }
                int n = 0;
                for (int k = 0; k < array2.length; ++k) {
                    final int n2 = array2[k];
                    final int n3 = n2 & redMask;
                    array4[n++] = (byte)((redShift < 0) ? (n3 >>> -redShift) : (n3 << redShift));
                    final int n4 = n2 & greenMask;
                    array4[n++] = (byte)((greenShift < 0) ? (n4 >>> -greenShift) : (n4 << greenShift));
                    final int n5 = n2 & blueMask;
                    array4[n++] = (byte)((blueShift < 0) ? (n5 >>> -blueShift) : (n5 << blueShift));
                    if (this.colorType == 6) {
                        array4[n++] = array3[k];
                    }
                }
                deflaterOutputStream.write(array4);
            }
        }
        deflaterOutputStream.flush();
        deflaterOutputStream.close();
        byte[] array5 = byteArrayOutputStream.toByteArray();
        if (deflaterOutputStream == byteArrayOutputStream) {
            array5 = new PngDeflater().deflate(array5);
        }
        this.writeChunk(PngEncoder.TAG_IDAT, array5);
    }
    
    void writeEnd() {
        this.writeChunk(PngEncoder.TAG_IEND, null);
    }
    
    public void encode(final LEDataOutputStream leDataOutputStream) {
        try {
            this.writeSignature();
            this.writeHeader();
            if (this.colorType == 3) {
                this.writePalette();
            }
            final boolean b = this.transparencyType == 1;
            final boolean b2 = this.transparencyType == 4;
            final boolean b3 = this.colorType == 2 && b2;
            final boolean b4 = this.colorType == 3 && (b || b2);
            if (b3 || b4) {
                this.writeTransparency();
            }
            this.writeImageData();
            this.writeEnd();
            leDataOutputStream.write(this.bytes.toByteArray());
        }
        catch (IOException ex) {
            SWT.error(39, ex);
        }
    }
}
