// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.image;

import org.eclipse.swt.graphics.ImageLoader;
import org.eclipse.swt.graphics.ImageLoaderEvent;
import java.io.IOException;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.PaletteData;

public final class GIFFileFormat extends FileFormat
{
    String signature;
    int screenWidth;
    int screenHeight;
    int backgroundPixel;
    int bitsPerPixel;
    int defaultDepth;
    int disposalMethod;
    int delayTime;
    int transparentPixel;
    int repeatCount;
    static final int GIF_APPLICATION_EXTENSION_BLOCK_ID = 255;
    static final int GIF_GRAPHICS_CONTROL_BLOCK_ID = 249;
    static final int GIF_PLAIN_TEXT_BLOCK_ID = 1;
    static final int GIF_COMMENT_BLOCK_ID = 254;
    static final int GIF_EXTENSION_BLOCK_ID = 33;
    static final int GIF_IMAGE_BLOCK_ID = 44;
    static final int GIF_TRAILER_ID = 59;
    static final byte[] GIF89a;
    static final byte[] NETSCAPE2_0;
    
    static {
        GIF89a = new byte[] { 71, 73, 70, 56, 57, 97 };
        NETSCAPE2_0 = new byte[] { 78, 69, 84, 83, 67, 65, 80, 69, 50, 46, 48 };
    }
    
    public GIFFileFormat() {
        this.disposalMethod = 0;
        this.delayTime = 0;
        this.transparentPixel = -1;
        this.repeatCount = 1;
    }
    
    static PaletteData grayRamp(final int n) {
        final int n2 = n - 1;
        final RGB[] array = new RGB[n];
        for (int i = 0; i < n; ++i) {
            final byte b = (byte)(i * 3 * 256 / n2);
            array[i] = new RGB(b, b, b);
        }
        return new PaletteData(array);
    }
    
    boolean isFileFormat(final LEDataInputStream leDataInputStream) {
        try {
            final byte[] array = new byte[3];
            leDataInputStream.read(array);
            leDataInputStream.unread(array);
            return array[0] == 71 && array[1] == 73 && array[2] == 70;
        }
        catch (Exception ex) {
            return false;
        }
    }
    
    ImageData[] loadFromByteStream() {
        final byte[] array = new byte[3];
        final byte[] array2 = new byte[3];
        final byte[] array3 = new byte[7];
        try {
            this.inputStream.read(array);
            if (array[0] != 71 || array[1] != 73 || array[2] != 70) {
                SWT.error(40);
            }
            this.inputStream.read(array2);
            this.inputStream.read(array3);
        }
        catch (IOException ex) {
            SWT.error(39, ex);
        }
        this.screenWidth = ((array3[0] & 0xFF) | (array3[1] & 0xFF) << 8);
        this.loader.logicalScreenWidth = this.screenWidth;
        this.screenHeight = ((array3[2] & 0xFF) | (array3[3] & 0xFF) << 8);
        this.loader.logicalScreenHeight = this.screenHeight;
        final byte b = array3[4];
        this.backgroundPixel = (array3[5] & 0xFF);
        this.bitsPerPixel = (b >> 4 & 0x7) + 1;
        this.defaultDepth = (b & 0x7) + 1;
        PaletteData palette = null;
        if ((b & 0x80) != 0x0) {
            palette = this.readPalette(1 << this.defaultDepth);
        }
        else {
            this.backgroundPixel = -1;
            this.defaultDepth = this.bitsPerPixel;
        }
        this.loader.backgroundPixel = this.backgroundPixel;
        this.getExtensions();
        int i = this.readID();
        ImageData[] array4 = new ImageData[0];
        while (i == 44) {
            final ImageData imageBlock = this.readImageBlock(palette);
            if (this.loader.hasListeners()) {
                this.loader.notifyListeners(new ImageLoaderEvent(this.loader, imageBlock, 3, true));
            }
            final ImageData[] array5 = array4;
            array4 = new ImageData[array5.length + 1];
            System.arraycopy(array5, 0, array4, 0, array5.length);
            array4[array4.length - 1] = imageBlock;
            try {
                final int read = this.inputStream.read();
                if (read > 0) {
                    this.inputStream.unread(new byte[] { (byte)read });
                }
            }
            catch (IOException ex2) {
                SWT.error(39, ex2);
            }
            this.getExtensions();
            i = this.readID();
        }
        return array4;
    }
    
    int readID() {
        try {
            return this.inputStream.read();
        }
        catch (IOException ex) {
            SWT.error(39, ex);
            return -1;
        }
    }
    
    void getExtensions() {
        int n;
        for (n = this.readID(); n != 44 && n != 59 && n > 0; n = this.readID()) {
            if (n == 33) {
                this.readExtension();
            }
            else {
                SWT.error(40);
            }
        }
        if (n != 44) {
            if (n != 59) {
                return;
            }
        }
        try {
            this.inputStream.unread(new byte[] { (byte)n });
        }
        catch (IOException ex) {
            SWT.error(39, ex);
        }
    }
    
    byte[] readExtension() {
        final int id = this.readID();
        if (id == 254) {
            return this.readCommentExtension();
        }
        if (id == 1) {
            return this.readPlainTextExtension();
        }
        if (id == 249) {
            return this.readGraphicsControlExtension();
        }
        if (id == 255) {
            return this.readApplicationExtension();
        }
        try {
            final int read = this.inputStream.read();
            if (read < 0) {
                SWT.error(40);
            }
            final byte[] array = new byte[read];
            this.inputStream.read(array, 0, read);
            return array;
        }
        catch (IOException ex) {
            SWT.error(39, ex);
            return null;
        }
    }
    
    byte[] readCommentExtension() {
        try {
            byte[] array = new byte[0];
            final byte[] array2 = new byte[255];
            for (int n = this.inputStream.read(); n > 0 && this.inputStream.read(array2, 0, n) != -1; n = this.inputStream.read()) {
                final byte[] array3 = array;
                array = new byte[array3.length + n];
                System.arraycopy(array3, 0, array, 0, array3.length);
                System.arraycopy(array2, 0, array, array3.length, n);
            }
            return array;
        }
        catch (Exception ex) {
            SWT.error(39, ex);
            return null;
        }
    }
    
    byte[] readPlainTextExtension() {
        try {
            this.inputStream.read();
            this.inputStream.read(new byte[12]);
            byte[] array = new byte[0];
            final byte[] array2 = new byte[255];
            for (int n = this.inputStream.read(); n > 0 && this.inputStream.read(array2, 0, n) != -1; n = this.inputStream.read()) {
                final byte[] array3 = array;
                array = new byte[array3.length + n];
                System.arraycopy(array3, 0, array, 0, array3.length);
                System.arraycopy(array2, 0, array, array3.length, n);
            }
            return array;
        }
        catch (Exception ex) {
            SWT.error(39, ex);
            return null;
        }
    }
    
    byte[] readGraphicsControlExtension() {
        try {
            this.inputStream.read();
            final byte[] array = new byte[4];
            this.inputStream.read(array);
            final byte b = array[0];
            this.disposalMethod = (b >> 2 & 0x7);
            this.delayTime = ((array[1] & 0xFF) | (array[2] & 0xFF) << 8);
            if ((b & 0x1) != 0x0) {
                this.transparentPixel = (array[3] & 0xFF);
            }
            else {
                this.transparentPixel = -1;
            }
            this.inputStream.read();
            return array;
        }
        catch (Exception ex) {
            SWT.error(39, ex);
            return null;
        }
    }
    
    byte[] readApplicationExtension() {
        try {
            final int read = this.inputStream.read();
            final byte[] array = new byte[read];
            this.inputStream.read(array);
            byte[] array2 = new byte[0];
            final byte[] array3 = new byte[255];
            for (int n = this.inputStream.read(); n > 0 && this.inputStream.read(array3, 0, n) != -1; n = this.inputStream.read()) {
                final byte[] array4 = array2;
                array2 = new byte[array4.length + n];
                System.arraycopy(array4, 0, array2, 0, array4.length);
                System.arraycopy(array3, 0, array2, array4.length, n);
            }
            final boolean b = read > 7 && array[0] == 78 && array[1] == 69 && array[2] == 84 && array[3] == 83 && array[4] == 67 && array[5] == 65 && array[6] == 80 && array[7] == 69;
            final boolean b2 = read > 10 && array[8] == 50 && array[9] == 46 && array[10] == 48;
            if (b && b2 && array2[0] == 1) {
                this.repeatCount = ((array2[1] & 0xFF) | (array2[2] & 0xFF) << 8);
                this.loader.repeatCount = this.repeatCount;
            }
            return array2;
        }
        catch (Exception ex) {
            SWT.error(39, ex);
            return null;
        }
    }
    
    ImageData readImageBlock(final PaletteData paletteData) {
        final byte[] array = new byte[9];
        try {
            this.inputStream.read(array);
        }
        catch (IOException ex) {
            SWT.error(39, ex);
        }
        final int n = (array[0] & 0xFF) | (array[1] & 0xFF) << 8;
        final int n2 = (array[2] & 0xFF) | (array[3] & 0xFF) << 8;
        final int n3 = (array[4] & 0xFF) | (array[5] & 0xFF) << 8;
        final int n4 = (array[6] & 0xFF) | (array[7] & 0xFF) << 8;
        final byte b = array[8];
        final boolean b2 = (b & 0x40) != 0x0;
        int defaultDepth;
        PaletteData paletteData2;
        if ((b & 0x80) != 0x0) {
            defaultDepth = (b & 0x7) + 1;
            paletteData2 = this.readPalette(1 << defaultDepth);
        }
        else {
            defaultDepth = this.defaultDepth;
            paletteData2 = paletteData;
        }
        if (this.transparentPixel > 1 << defaultDepth) {
            this.transparentPixel = -1;
        }
        if (defaultDepth != 1 && defaultDepth != 4 && defaultDepth != 8) {
            if (defaultDepth < 4) {
                defaultDepth = 4;
            }
            else {
                defaultDepth = 8;
            }
        }
        if (paletteData2 == null) {
            paletteData2 = grayRamp(1 << defaultDepth);
        }
        int read = -1;
        try {
            read = this.inputStream.read();
        }
        catch (IOException ex2) {
            SWT.error(39, ex2);
        }
        if (read < 0) {
            SWT.error(40);
        }
        final ImageData internal_new = ImageData.internal_new(n3, n4, defaultDepth, paletteData2, 4, null, 0, null, null, -1, this.transparentPixel, 2, n, n2, this.disposalMethod, this.delayTime);
        new LZWCodec().decode(this.inputStream, this.loader, internal_new, b2, read);
        return internal_new;
    }
    
    PaletteData readPalette(final int n) {
        final byte[] array = new byte[n * 3];
        try {
            if (this.inputStream.read(array) != array.length) {
                SWT.error(40);
            }
        }
        catch (IOException ex) {
            SWT.error(39, ex);
        }
        final RGB[] array2 = new RGB[n];
        for (int i = 0; i < n; ++i) {
            array2[i] = new RGB(array[i * 3] & 0xFF, array[i * 3 + 1] & 0xFF, array[i * 3 + 2] & 0xFF);
        }
        return new PaletteData(array2);
    }
    
    void unloadIntoByteStream(final ImageLoader imageLoader) {
        final ImageData[] data = imageLoader.data;
        final int length = data.length;
        final boolean b = length > 1;
        final ImageData imageData = data[0];
        final int n = b ? imageLoader.logicalScreenWidth : imageData.width;
        final int n2 = b ? imageLoader.logicalScreenHeight : imageData.height;
        final int backgroundPixel = imageLoader.backgroundPixel;
        final int depth = imageData.depth;
        final PaletteData palette = imageData.palette;
        final RGB[] rgBs = palette.getRGBs();
        int n3 = 1;
        if (depth != 1 && depth != 4 && depth != 8) {
            SWT.error(38);
        }
        for (int i = 0; i < length; ++i) {
            if (data[i].palette.isDirect) {
                SWT.error(40);
            }
            if (b) {
                if (data[i].height > n2 || data[i].width > n || data[i].depth != depth) {
                    SWT.error(40);
                }
                if (n3 == 1) {
                    final RGB[] rgBs2 = data[i].palette.getRGBs();
                    if (rgBs2.length != rgBs.length) {
                        n3 = 0;
                    }
                    else {
                        for (int j = 0; j < rgBs.length; ++j) {
                            if (rgBs2[j].red != rgBs[j].red || rgBs2[j].green != rgBs[j].green || rgBs2[j].blue != rgBs[j].blue) {
                                n3 = 0;
                            }
                        }
                    }
                }
            }
        }
        try {
            this.outputStream.write(GIFFileFormat.GIF89a);
            final int n4 = n3 * 128 + (depth - 1) * 16 + depth - 1;
            this.outputStream.writeShort((short)n);
            this.outputStream.writeShort((short)n2);
            this.outputStream.write(n4);
            this.outputStream.write(backgroundPixel);
            this.outputStream.write(0);
        }
        catch (IOException ex) {
            SWT.error(39, ex);
        }
        if (n3 == 1) {
            this.writePalette(palette, depth);
        }
        if (b) {
            final int repeatCount = imageLoader.repeatCount;
            try {
                this.outputStream.write(33);
                this.outputStream.write(255);
                this.outputStream.write(GIFFileFormat.NETSCAPE2_0.length);
                this.outputStream.write(GIFFileFormat.NETSCAPE2_0);
                this.outputStream.write(3);
                this.outputStream.write(1);
                this.outputStream.writeShort((short)repeatCount);
                this.outputStream.write(0);
            }
            catch (IOException ex2) {
                SWT.error(39, ex2);
            }
        }
        for (int k = 0; k < length; ++k) {
            if (b || data[k].transparentPixel != -1) {
                this.writeGraphicsControlBlock(data[k]);
            }
            final int x = data[k].x;
            final int y = data[k].y;
            final int width = data[k].width;
            final int height = data[k].height;
            try {
                this.outputStream.write(44);
                this.outputStream.write(new byte[] { (byte)(x & 0xFF), (byte)(x >> 8 & 0xFF), (byte)(y & 0xFF), (byte)(y >> 8 & 0xFF), (byte)(width & 0xFF), (byte)(width >> 8 & 0xFF), (byte)(height & 0xFF), (byte)(height >> 8 & 0xFF), (byte)((n3 == 0) ? (depth - 1 | 0x80) : 0) });
            }
            catch (IOException ex3) {
                SWT.error(39, ex3);
            }
            if (n3 == 0) {
                this.writePalette(data[k].palette, depth);
            }
            try {
                this.outputStream.write(depth);
            }
            catch (IOException ex4) {
                SWT.error(39, ex4);
            }
            new LZWCodec().encode(this.outputStream, data[k]);
        }
        try {
            this.outputStream.write(59);
        }
        catch (IOException ex5) {
            SWT.error(39, ex5);
        }
    }
    
    void writeGraphicsControlBlock(final ImageData imageData) {
        try {
            this.outputStream.write(33);
            this.outputStream.write(249);
            final byte[] array = { 0, 0, 0, 0 };
            if (imageData.transparentPixel != -1) {
                array[0] = 1;
                array[3] = (byte)imageData.transparentPixel;
            }
            if (imageData.disposalMethod != 0) {
                final byte[] array2 = array;
                final int n = 0;
                array2[n] |= (byte)((imageData.disposalMethod & 0x7) << 2);
            }
            if (imageData.delayTime != 0) {
                array[1] = (byte)(imageData.delayTime & 0xFF);
                array[2] = (byte)(imageData.delayTime >> 8 & 0xFF);
            }
            this.outputStream.write((byte)array.length);
            this.outputStream.write(array);
            this.outputStream.write(0);
        }
        catch (IOException ex) {
            SWT.error(39, ex);
        }
    }
    
    void writePalette(final PaletteData paletteData, final int n) {
        final byte[] array = new byte[(1 << n) * 3];
        int n2 = 0;
        for (int i = 0; i < paletteData.colors.length; ++i) {
            final RGB rgb = paletteData.colors[i];
            array[n2] = (byte)rgb.red;
            array[n2 + 1] = (byte)rgb.green;
            array[n2 + 2] = (byte)rgb.blue;
            n2 += 3;
        }
        try {
            this.outputStream.write(array);
        }
        catch (IOException ex) {
            SWT.error(39, ex);
        }
    }
}
