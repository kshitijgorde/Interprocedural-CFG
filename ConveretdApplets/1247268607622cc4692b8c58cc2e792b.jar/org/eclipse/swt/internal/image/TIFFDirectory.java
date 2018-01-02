// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.image;

import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.PaletteData;
import org.eclipse.swt.graphics.ImageLoaderEvent;
import java.io.IOException;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.ImageLoader;

final class TIFFDirectory
{
    TIFFRandomFileAccess file;
    boolean isLittleEndian;
    ImageLoader loader;
    int depth;
    int subfileType;
    int imageWidth;
    int imageLength;
    int[] bitsPerSample;
    int compression;
    int photometricInterpretation;
    int[] stripOffsets;
    int samplesPerPixel;
    int rowsPerStrip;
    int[] stripByteCounts;
    int t4Options;
    int colorMapOffset;
    ImageData image;
    LEDataOutputStream out;
    static final int NO_VALUE = -1;
    static final short TAG_NewSubfileType = 254;
    static final short TAG_SubfileType = 255;
    static final short TAG_ImageWidth = 256;
    static final short TAG_ImageLength = 257;
    static final short TAG_BitsPerSample = 258;
    static final short TAG_Compression = 259;
    static final short TAG_PhotometricInterpretation = 262;
    static final short TAG_FillOrder = 266;
    static final short TAG_ImageDescription = 270;
    static final short TAG_StripOffsets = 273;
    static final short TAG_Orientation = 274;
    static final short TAG_SamplesPerPixel = 277;
    static final short TAG_RowsPerStrip = 278;
    static final short TAG_StripByteCounts = 279;
    static final short TAG_XResolution = 282;
    static final short TAG_YResolution = 283;
    static final short TAG_PlanarConfiguration = 284;
    static final short TAG_T4Options = 292;
    static final short TAG_ResolutionUnit = 296;
    static final short TAG_Software = 305;
    static final short TAG_DateTime = 306;
    static final short TAG_ColorMap = 320;
    static final int TYPE_BYTE = 1;
    static final int TYPE_ASCII = 2;
    static final int TYPE_SHORT = 3;
    static final int TYPE_LONG = 4;
    static final int TYPE_RATIONAL = 5;
    static final int FILETYPE_REDUCEDIMAGE = 1;
    static final int FILETYPE_PAGE = 2;
    static final int FILETYPE_MASK = 4;
    static final int OFILETYPE_IMAGE = 1;
    static final int OFILETYPE_REDUCEDIMAGE = 2;
    static final int OFILETYPE_PAGE = 3;
    static final int COMPRESSION_NONE = 1;
    static final int COMPRESSION_CCITT_3_1 = 2;
    static final int COMPRESSION_PACKBITS = 32773;
    static final int IFD_ENTRY_SIZE = 12;
    
    public TIFFDirectory(final TIFFRandomFileAccess file, final boolean isLittleEndian, final ImageLoader loader) {
        this.file = file;
        this.isLittleEndian = isLittleEndian;
        this.loader = loader;
    }
    
    public TIFFDirectory(final ImageData image) {
        this.image = image;
    }
    
    int decodePackBits(final byte[] array, final byte[] array2, final int n) {
        int n2 = n;
        byte b = 0;
        while (b < array.length) {
            final byte b2 = array[b];
            if (b2 >= 0) {
                System.arraycopy(array, ++b, array2, n2, b2 + 1);
                b += (byte)(b2 + 1);
                n2 += b2 + 1;
            }
            else if (b2 >= -127) {
                final byte b3 = array[++b];
                for (byte b4 = 0; b4 < -b2 + 1; ++b4) {
                    array2[n2++] = b3;
                }
                ++b;
            }
            else {
                ++b;
            }
        }
        return n2 - n;
    }
    
    int getEntryValue(final int n, final byte[] array, final int n2) {
        return this.toInt(array, n2 + 8, n);
    }
    
    void getEntryValue(final int n, byte[] array, final int n2, final int[] array2) throws IOException {
        int n3 = n2 + 8;
        final int int1 = this.toInt(array, n3, 4);
        int n4 = 0;
        switch (n) {
            case 3: {
                n4 = 2;
                break;
            }
            case 4: {
                n4 = 4;
                break;
            }
            case 5: {
                n4 = 8;
                break;
            }
            case 1:
            case 2: {
                n4 = 1;
                break;
            }
            default: {
                SWT.error(42);
                return;
            }
        }
        if (array2.length * n4 > 4) {
            array = new byte[array2.length * n4];
            this.file.seek(int1);
            this.file.read(array);
            n3 = 0;
        }
        for (int i = 0; i < array2.length; ++i) {
            array2[i] = this.toInt(array, n3 + i * n4, n);
        }
    }
    
    void decodePixels(final ImageData imageData) throws IOException {
        final byte[] data = new byte[(this.imageWidth * this.depth + 7) / 8 * this.imageLength];
        imageData.data = data;
        int n = 0;
        for (int length = this.stripOffsets.length, i = 0; i < length; ++i) {
            final byte[] array = new byte[this.stripByteCounts[i]];
            this.file.seek(this.stripOffsets[i]);
            this.file.read(array);
            if (this.compression == 1) {
                System.arraycopy(array, 0, data, n, array.length);
                n += array.length;
            }
            else if (this.compression == 32773) {
                n += this.decodePackBits(array, data, n);
            }
            else if (this.compression == 2 || this.compression == 3) {
                final TIFFModifiedHuffmanCodec tiffModifiedHuffmanCodec = new TIFFModifiedHuffmanCodec();
                int rowsPerStrip = this.rowsPerStrip;
                if (i == length - 1) {
                    final int n2 = this.imageLength % this.rowsPerStrip;
                    if (n2 != 0) {
                        rowsPerStrip = n2;
                    }
                }
                n += tiffModifiedHuffmanCodec.decode(array, data, n, this.imageWidth, rowsPerStrip);
            }
            if (this.loader.hasListeners()) {
                this.loader.notifyListeners(new ImageLoaderEvent(this.loader, imageData, i, i == length - 1));
            }
        }
    }
    
    PaletteData getColorMap() throws IOException {
        final int n = 1 << this.bitsPerSample[0];
        final byte[] array = new byte[6 * n];
        this.file.seek(this.colorMapOffset);
        this.file.read(array);
        final RGB[] array2 = new RGB[n];
        int isLittleEndian = this.isLittleEndian ? 1 : 0;
        final int n2 = 2 * n;
        final int n3 = n2 + 2 * n;
        for (int i = 0; i < n; ++i) {
            array2[i] = new RGB(array[isLittleEndian] & 0xFF, array[n2 + isLittleEndian] & 0xFF, array[n3 + isLittleEndian] & 0xFF);
            isLittleEndian += 2;
        }
        return new PaletteData(array2);
    }
    
    PaletteData getGrayPalette() {
        final int n = 1 << this.bitsPerSample[0];
        final RGB[] array = new RGB[n];
        for (int i = 0; i < n; ++i) {
            int n2 = i * 255 / (n - 1);
            if (this.photometricInterpretation == 0) {
                n2 = 255 - n2;
            }
            array[i] = new RGB(n2, n2, n2);
        }
        return new PaletteData(array);
    }
    
    PaletteData getRGBPalette(final int n, final int n2, final int n3) {
        int n4 = 0;
        for (int i = 0; i < n3; ++i) {
            n4 |= 1 << i;
        }
        int n5 = 0;
        for (int j = n3; j < n3 + n2; ++j) {
            n5 |= 1 << j;
        }
        int n6 = 0;
        for (int k = n3 + n2; k < n3 + n2 + n; ++k) {
            n6 |= 1 << k;
        }
        return new PaletteData(n6, n5, n4);
    }
    
    int formatStrips(final int n, final int n2, final byte[] array, final int n3, final int n4, final int n5, final int[][] array2) {
        int n6;
        int n7;
        if (n > n3) {
            n6 = array.length / n;
            n7 = 1;
        }
        else {
            n7 = n2 / ((array.length + n3 - 1) / n3);
            n6 = (n2 + n7 - 1) / n7;
        }
        final int n8 = n * n7;
        final int[] array3 = new int[n6];
        final int[] array4 = new int[n6];
        int n9 = n4 + n5 + ((n6 == 1) ? 0 : (n6 * 2 * 4));
        for (int i = 0; i < n6; ++i) {
            array3[i] = n9;
            array4[i] = n8;
            n9 += n8;
        }
        final int n10 = array.length % n8;
        if (n10 != 0) {
            array4[array4.length - 1] = n10;
        }
        array2[0] = array3;
        array2[1] = array4;
        return n7;
    }
    
    int[] formatColorMap(final RGB[] array) {
        final int[] array2 = new int[array.length * 3];
        final int length = array.length;
        final int n = array.length * 2;
        for (int i = 0; i < array.length; ++i) {
            array2[i] = (array[i].red << 8 | array[i].red);
            array2[i + length] = (array[i].green << 8 | array[i].green);
            array2[i + n] = (array[i].blue << 8 | array[i].blue);
        }
        return array2;
    }
    
    void parseEntries(final byte[] array) throws IOException {
        for (int i = 0; i < array.length; i += 12) {
            final int int1 = this.toInt(array, i, 3);
            final int int2 = this.toInt(array, i + 2, 3);
            final int int3 = this.toInt(array, i + 4, 4);
            switch (int1) {
                case 254: {
                    this.subfileType = this.getEntryValue(int2, array, i);
                    break;
                }
                case 255: {
                    final int entryValue = this.getEntryValue(int2, array, i);
                    this.subfileType = ((entryValue == 2) ? 1 : ((entryValue == 3) ? 2 : 0));
                    break;
                }
                case 256: {
                    this.imageWidth = this.getEntryValue(int2, array, i);
                    break;
                }
                case 257: {
                    this.imageLength = this.getEntryValue(int2, array, i);
                    break;
                }
                case 258: {
                    if (int2 != 3) {
                        SWT.error(40);
                    }
                    this.getEntryValue(int2, array, i, this.bitsPerSample = new int[int3]);
                    break;
                }
                case 259: {
                    this.compression = this.getEntryValue(int2, array, i);
                }
                case 266: {}
                case 262: {
                    this.photometricInterpretation = this.getEntryValue(int2, array, i);
                    break;
                }
                case 273: {
                    if (int2 != 4 && int2 != 3) {
                        SWT.error(40);
                    }
                    this.getEntryValue(int2, array, i, this.stripOffsets = new int[int3]);
                }
                case 277: {
                    if (int2 != 3) {
                        SWT.error(40);
                    }
                    this.samplesPerPixel = this.getEntryValue(int2, array, i);
                    if (this.samplesPerPixel != 1 && this.samplesPerPixel != 3) {
                        SWT.error(38);
                        break;
                    }
                    break;
                }
                case 278: {
                    this.rowsPerStrip = this.getEntryValue(int2, array, i);
                    break;
                }
                case 279: {
                    this.getEntryValue(int2, array, i, this.stripByteCounts = new int[int3]);
                }
                case 282: {}
                case 283: {}
                case 292: {
                    if (int2 != 4) {
                        SWT.error(40);
                    }
                    this.t4Options = this.getEntryValue(int2, array, i);
                    if ((this.t4Options & 0x1) == 0x1) {
                        SWT.error(42);
                        break;
                    }
                    break;
                }
                case 296: {}
                case 305: {}
                case 320: {
                    if (int2 != 3) {
                        SWT.error(40);
                    }
                    this.colorMapOffset = this.getEntryValue(4, array, i);
                    break;
                }
            }
        }
    }
    
    public ImageData read(final int[] array) throws IOException {
        this.bitsPerSample = new int[] { 1 };
        this.colorMapOffset = -1;
        this.compression = 1;
        this.imageLength = -1;
        this.imageWidth = -1;
        this.photometricInterpretation = -1;
        this.rowsPerStrip = Integer.MAX_VALUE;
        this.samplesPerPixel = 1;
        this.stripByteCounts = null;
        this.stripOffsets = null;
        final byte[] array2 = new byte[2];
        this.file.read(array2);
        final byte[] array3 = new byte[12 * this.toInt(array2, 0, 3)];
        this.file.read(array3);
        final byte[] array4 = new byte[4];
        this.file.read(array4);
        array[0] = this.toInt(array4, 0, 4);
        this.parseEntries(array3);
        PaletteData paletteData = null;
        this.depth = 0;
        switch (this.photometricInterpretation) {
            case 0:
            case 1: {
                paletteData = this.getGrayPalette();
                this.depth = this.bitsPerSample[0];
                break;
            }
            case 2: {
                if (this.colorMapOffset != -1) {
                    SWT.error(40);
                }
                paletteData = this.getRGBPalette(this.bitsPerSample[0], this.bitsPerSample[1], this.bitsPerSample[2]);
                this.depth = this.bitsPerSample[0] + this.bitsPerSample[1] + this.bitsPerSample[2];
                break;
            }
            case 3: {
                if (this.colorMapOffset == -1) {
                    SWT.error(40);
                }
                paletteData = this.getColorMap();
                this.depth = this.bitsPerSample[0];
                break;
            }
            default: {
                SWT.error(40);
                break;
            }
        }
        final ImageData internal_new = ImageData.internal_new(this.imageWidth, this.imageLength, this.depth, paletteData, 1, null, 0, null, null, -1, -1, 6, 0, 0, 0, 0);
        this.decodePixels(internal_new);
        return internal_new;
    }
    
    int toInt(final byte[] array, final int n, final int n2) {
        if (n2 == 4) {
            return this.isLittleEndian ? ((array[n] & 0xFF) | (array[n + 1] & 0xFF) << 8 | (array[n + 2] & 0xFF) << 16 | (array[n + 3] & 0xFF) << 24) : ((array[n + 3] & 0xFF) | (array[n + 2] & 0xFF) << 8 | (array[n + 1] & 0xFF) << 16 | (array[n] & 0xFF) << 24);
        }
        if (n2 == 3) {
            return this.isLittleEndian ? ((array[n] & 0xFF) | (array[n + 1] & 0xFF) << 8) : ((array[n + 1] & 0xFF) | (array[n] & 0xFF) << 8);
        }
        SWT.error(40);
        return -1;
    }
    
    void write(final int n) throws IOException {
        final boolean b = n == 2;
        final boolean b2 = n == 3;
        final boolean b3 = n == 0 || n == 1;
        final int width = this.image.width;
        final int height = this.image.height;
        final int bytesPerLine = this.image.bytesPerLine;
        final int n2 = b3 ? 9 : 11;
        int n3 = 8 + (2 + 12 * n2 + 4);
        int n4 = 16;
        int[] formatColorMap = null;
        if (b2) {
            formatColorMap = this.formatColorMap(this.image.palette.getRGBs());
            if (formatColorMap.length != 3 << this.image.depth) {
                SWT.error(42);
            }
            n4 += formatColorMap.length * 2;
        }
        if (b) {
            n4 += 6;
        }
        final byte[] data = this.image.data;
        final int[][] array = new int[2][];
        final int formatStrips = this.formatStrips(bytesPerLine, height, data, 8192, n3, n4, array);
        final int[] array2 = array[0];
        final int[] array3 = array[1];
        int n5 = -1;
        if (b) {
            n5 = n3;
            n3 += 6;
        }
        int n6 = -1;
        int n7 = -1;
        int n8 = -1;
        final int length = array2.length;
        if (length > 1) {
            n6 = n3;
            n3 = (n7 = n3 + 4 * length) + 4 * length;
        }
        final int n9 = n3;
        n3 += 8;
        final int n10 = n3;
        n3 += 8;
        if (b2) {
            n8 = n3;
            final int n11 = n3 + formatColorMap.length * 2;
        }
        this.writeHeader();
        this.out.writeShort(n2);
        this.writeEntry((short)256, 4, 1, width);
        this.writeEntry((short)257, 4, 1, height);
        if (b2) {
            this.writeEntry((short)258, 3, 1, this.image.depth);
        }
        if (b) {
            this.writeEntry((short)258, 3, 3, n5);
        }
        this.writeEntry((short)259, 3, 1, 1);
        this.writeEntry((short)262, 3, 1, n);
        this.writeEntry((short)273, 4, length, (length > 1) ? n6 : array2[0]);
        if (b) {
            this.writeEntry((short)277, 3, 1, 3);
        }
        this.writeEntry((short)278, 4, 1, formatStrips);
        this.writeEntry((short)279, 4, length, (length > 1) ? n7 : array3[0]);
        this.writeEntry((short)282, 5, 1, n9);
        this.writeEntry((short)283, 5, 1, n10);
        if (b2) {
            this.writeEntry((short)320, 3, formatColorMap.length, n8);
        }
        this.out.writeInt(0);
        if (b) {
            for (int i = 0; i < 3; ++i) {
                this.out.writeShort(8);
            }
        }
        if (length > 1) {
            for (int j = 0; j < length; ++j) {
                this.out.writeInt(array2[j]);
            }
            for (int k = 0; k < length; ++k) {
                this.out.writeInt(array3[k]);
            }
        }
        for (int l = 0; l < 2; ++l) {
            this.out.writeInt(300);
            this.out.writeInt(1);
        }
        if (b2) {
            for (int n12 = 0; n12 < formatColorMap.length; ++n12) {
                this.out.writeShort(formatColorMap[n12]);
            }
        }
        this.out.write(data);
    }
    
    void writeEntry(final short n, final int n2, final int n3, final int n4) throws IOException {
        this.out.writeShort(n);
        this.out.writeShort(n2);
        this.out.writeInt(n3);
        this.out.writeInt(n4);
    }
    
    void writeHeader() throws IOException {
        this.out.write(73);
        this.out.write(73);
        this.out.writeShort(42);
        this.out.writeInt(8);
    }
    
    void writeToStream(final LEDataOutputStream out) throws IOException {
        this.out = out;
        int n = -1;
        if (this.image.scanlinePad != 1) {
            SWT.error(42);
        }
        switch (this.image.depth) {
            case 1: {
                final PaletteData palette = this.image.palette;
                final RGB[] colors = palette.colors;
                if (palette.isDirect || colors == null || colors.length != 2) {
                    SWT.error(42);
                }
                final RGB rgb = colors[0];
                final RGB rgb2 = colors[1];
                if (rgb.red != rgb.green || rgb.green != rgb.blue || rgb2.red != rgb2.green || rgb2.green != rgb2.blue || ((rgb.red != 0 || rgb2.red != 255) && (rgb.red != 255 || rgb2.red != 0))) {
                    SWT.error(42);
                }
                n = ((this.image.palette.colors[0].red != 255) ? 1 : 0);
                break;
            }
            case 4:
            case 8: {
                n = 3;
                break;
            }
            case 24: {
                n = 2;
                break;
            }
            default: {
                SWT.error(42);
                break;
            }
        }
        this.write(n);
    }
}
