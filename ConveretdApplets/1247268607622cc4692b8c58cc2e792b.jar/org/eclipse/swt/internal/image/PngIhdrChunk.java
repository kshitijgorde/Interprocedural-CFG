// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.image;

import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.PaletteData;
import org.eclipse.swt.SWT;

class PngIhdrChunk extends PngChunk
{
    static final int IHDR_DATA_LENGTH = 13;
    static final int WIDTH_DATA_OFFSET = 8;
    static final int HEIGHT_DATA_OFFSET = 12;
    static final int BIT_DEPTH_OFFSET = 16;
    static final int COLOR_TYPE_OFFSET = 17;
    static final int COMPRESSION_METHOD_OFFSET = 18;
    static final int FILTER_METHOD_OFFSET = 19;
    static final int INTERLACE_METHOD_OFFSET = 20;
    static final byte COLOR_TYPE_GRAYSCALE = 0;
    static final byte COLOR_TYPE_RGB = 2;
    static final byte COLOR_TYPE_PALETTE = 3;
    static final byte COLOR_TYPE_GRAYSCALE_WITH_ALPHA = 4;
    static final byte COLOR_TYPE_RGB_WITH_ALPHA = 6;
    static final int INTERLACE_METHOD_NONE = 0;
    static final int INTERLACE_METHOD_ADAM7 = 1;
    static final int FILTER_NONE = 0;
    static final int FILTER_SUB = 1;
    static final int FILTER_UP = 2;
    static final int FILTER_AVERAGE = 3;
    static final int FILTER_PAETH = 4;
    static final byte[] ValidBitDepths;
    static final byte[] ValidColorTypes;
    int width;
    int height;
    byte bitDepth;
    byte colorType;
    byte compressionMethod;
    byte filterMethod;
    byte interlaceMethod;
    
    static {
        ValidBitDepths = new byte[] { 1, 2, 4, 8, 16 };
        ValidColorTypes = new byte[] { 0, 2, 3, 4, 6 };
    }
    
    PngIhdrChunk(final int width, final int height, final byte bitDepth, final byte colorType, final byte compressionMethod, final byte filterMethod, final byte interlaceMethod) {
        super(13);
        this.setType(PngIhdrChunk.TYPE_IHDR);
        this.setWidth(width);
        this.setHeight(height);
        this.setBitDepth(bitDepth);
        this.setColorType(colorType);
        this.setCompressionMethod(compressionMethod);
        this.setFilterMethod(filterMethod);
        this.setInterlaceMethod(interlaceMethod);
        this.setCRC(this.computeCRC());
    }
    
    PngIhdrChunk(final byte[] array) {
        super(array);
        if (array.length <= 13) {
            SWT.error(40);
        }
        this.width = this.getInt32(8);
        this.height = this.getInt32(12);
        this.bitDepth = array[16];
        this.colorType = array[17];
        this.compressionMethod = array[18];
        this.filterMethod = array[19];
        this.interlaceMethod = array[20];
    }
    
    int getChunkType() {
        return 0;
    }
    
    int getWidth() {
        return this.width;
    }
    
    void setWidth(final int width) {
        this.setInt32(8, width);
        this.width = width;
    }
    
    int getHeight() {
        return this.height;
    }
    
    void setHeight(final int height) {
        this.setInt32(12, height);
        this.height = height;
    }
    
    byte getBitDepth() {
        return this.bitDepth;
    }
    
    void setBitDepth(final byte bitDepth) {
        this.reference[16] = bitDepth;
        this.bitDepth = bitDepth;
    }
    
    byte getColorType() {
        return this.colorType;
    }
    
    void setColorType(final byte colorType) {
        this.reference[17] = colorType;
        this.colorType = colorType;
    }
    
    byte getCompressionMethod() {
        return this.compressionMethod;
    }
    
    void setCompressionMethod(final byte compressionMethod) {
        this.reference[18] = compressionMethod;
        this.compressionMethod = compressionMethod;
    }
    
    byte getFilterMethod() {
        return this.filterMethod;
    }
    
    void setFilterMethod(final byte filterMethod) {
        this.reference[19] = filterMethod;
        this.filterMethod = filterMethod;
    }
    
    byte getInterlaceMethod() {
        return this.interlaceMethod;
    }
    
    void setInterlaceMethod(final byte interlaceMethod) {
        this.reference[20] = interlaceMethod;
        this.interlaceMethod = interlaceMethod;
    }
    
    void validate(final PngFileReadState pngFileReadState, final PngIhdrChunk pngIhdrChunk) {
        if (pngFileReadState.readIHDR || pngFileReadState.readPLTE || pngFileReadState.readIDAT || pngFileReadState.readIEND) {
            SWT.error(40);
        }
        else {
            pngFileReadState.readIHDR = true;
        }
        super.validate(pngFileReadState, pngIhdrChunk);
        if (this.length != 13) {
            SWT.error(40);
        }
        if (this.compressionMethod != 0) {
            SWT.error(40);
        }
        if (this.interlaceMethod != 0 && this.interlaceMethod != 1) {
            SWT.error(40);
        }
        boolean b = false;
        for (int i = 0; i < PngIhdrChunk.ValidColorTypes.length; ++i) {
            if (PngIhdrChunk.ValidColorTypes[i] == this.colorType) {
                b = true;
                break;
            }
        }
        if (!b) {
            SWT.error(40);
        }
        boolean b2 = false;
        for (int j = 0; j < PngIhdrChunk.ValidBitDepths.length; ++j) {
            if (PngIhdrChunk.ValidBitDepths[j] == this.bitDepth) {
                b2 = true;
                break;
            }
        }
        if (!b2) {
            SWT.error(40);
        }
        if ((this.colorType == 2 || this.colorType == 6 || this.colorType == 4) && this.bitDepth < 8) {
            SWT.error(40);
        }
        if (this.colorType == 3 && this.bitDepth > 8) {
            SWT.error(40);
        }
    }
    
    String getColorTypeString() {
        switch (this.colorType) {
            case 0: {
                return "Grayscale";
            }
            case 2: {
                return "RGB";
            }
            case 3: {
                return "Palette";
            }
            case 4: {
                return "Grayscale with Alpha";
            }
            case 6: {
                return "RGB with Alpha";
            }
            default: {
                return "Unknown - " + this.colorType;
            }
        }
    }
    
    String getFilterMethodString() {
        switch (this.filterMethod) {
            case 0: {
                return "None";
            }
            case 1: {
                return "Sub";
            }
            case 2: {
                return "Up";
            }
            case 3: {
                return "Average";
            }
            case 4: {
                return "Paeth";
            }
            default: {
                return "Unknown";
            }
        }
    }
    
    String getInterlaceMethodString() {
        switch (this.interlaceMethod) {
            case 0: {
                return "Not Interlaced";
            }
            case 1: {
                return "Interlaced - ADAM7";
            }
            default: {
                return "Unknown";
            }
        }
    }
    
    void contributeToString(final StringBuffer sb) {
        sb.append("\n\tWidth: ");
        sb.append(this.width);
        sb.append("\n\tHeight: ");
        sb.append(this.height);
        sb.append("\n\tBit Depth: ");
        sb.append(this.bitDepth);
        sb.append("\n\tColor Type: ");
        sb.append(this.getColorTypeString());
        sb.append("\n\tCompression Method: ");
        sb.append(this.compressionMethod);
        sb.append("\n\tFilter Method: ");
        sb.append(this.getFilterMethodString());
        sb.append("\n\tInterlace Method: ");
        sb.append(this.getInterlaceMethodString());
    }
    
    boolean getMustHavePalette() {
        return this.colorType == 3;
    }
    
    boolean getCanHavePalette() {
        return this.colorType != 0 && this.colorType != 4;
    }
    
    int getBitsPerPixel() {
        switch (this.colorType) {
            case 6: {
                return 4 * this.bitDepth;
            }
            case 2: {
                return 3 * this.bitDepth;
            }
            case 4: {
                return 2 * this.bitDepth;
            }
            case 0:
            case 3: {
                return this.bitDepth;
            }
            default: {
                SWT.error(40);
                return 0;
            }
        }
    }
    
    int getSwtBitsPerPixel() {
        switch (this.colorType) {
            case 2:
            case 4:
            case 6: {
                return 24;
            }
            case 0:
            case 3: {
                return Math.min(this.bitDepth, 8);
            }
            default: {
                SWT.error(40);
                return 0;
            }
        }
    }
    
    int getFilterByteOffset() {
        if (this.bitDepth < 8) {
            return 1;
        }
        return this.getBitsPerPixel() / 8;
    }
    
    boolean usesDirectColor() {
        switch (this.colorType) {
            case 0:
            case 2:
            case 4:
            case 6: {
                return true;
            }
            default: {
                return false;
            }
        }
    }
    
    PaletteData createGrayscalePalette() {
        final int n = (1 << Math.min(this.bitDepth, 8)) - 1;
        final int n2 = 255 / n;
        int n3 = 0;
        final RGB[] array = new RGB[n + 1];
        for (int i = 0; i <= n; ++i) {
            array[i] = new RGB(n3, n3, n3);
            n3 += n2;
        }
        return new PaletteData(array);
    }
    
    PaletteData getPaletteData() {
        switch (this.colorType) {
            case 0: {
                return this.createGrayscalePalette();
            }
            case 2:
            case 4:
            case 6: {
                return new PaletteData(16711680, 65280, 255);
            }
            default: {
                return null;
            }
        }
    }
}
