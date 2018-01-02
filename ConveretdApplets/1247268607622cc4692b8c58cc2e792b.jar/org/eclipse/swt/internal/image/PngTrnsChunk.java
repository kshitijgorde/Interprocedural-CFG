// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.image;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.RGB;

public class PngTrnsChunk extends PngChunk
{
    static final int TRANSPARENCY_TYPE_PIXEL = 0;
    static final int TRANSPARENCY_TYPE_ALPHAS = 1;
    static final int RGB_DATA_LENGTH = 6;
    
    PngTrnsChunk(final RGB rgb) {
        super(6);
        this.setType(PngTrnsChunk.TYPE_tRNS);
        this.setInt16(8, rgb.red);
        this.setInt16(10, rgb.green);
        this.setInt16(12, rgb.blue);
        this.setCRC(this.computeCRC());
    }
    
    PngTrnsChunk(final byte[] array) {
        super(array);
    }
    
    int getChunkType() {
        return 5;
    }
    
    void validateLength(final PngIhdrChunk pngIhdrChunk, final PngPlteChunk pngPlteChunk) {
        int n = 0;
        switch (pngIhdrChunk.getColorType()) {
            case 2: {
                n = ((this.getLength() == 6) ? 1 : 0);
                break;
            }
            case 3: {
                n = ((this.getLength() <= pngPlteChunk.getLength()) ? 1 : 0);
                break;
            }
            case 0: {
                n = ((this.getLength() == 2) ? 1 : 0);
                break;
            }
            default: {
                n = 0;
                break;
            }
        }
        if (n == 0) {
            SWT.error(40);
        }
    }
    
    void validate(final PngFileReadState pngFileReadState, final PngIhdrChunk pngIhdrChunk, final PngPlteChunk pngPlteChunk) {
        if (!pngFileReadState.readIHDR || (pngIhdrChunk.getMustHavePalette() && !pngFileReadState.readPLTE) || pngFileReadState.readIDAT || pngFileReadState.readIEND) {
            SWT.error(40);
        }
        else {
            pngFileReadState.readTRNS = true;
        }
        this.validateLength(pngIhdrChunk, pngPlteChunk);
        super.validate(pngFileReadState, pngIhdrChunk);
    }
    
    int getTransparencyType(final PngIhdrChunk pngIhdrChunk) {
        if (pngIhdrChunk.getColorType() == 3) {
            return 1;
        }
        return 0;
    }
    
    int getSwtTransparentPixel(final PngIhdrChunk pngIhdrChunk) {
        switch (pngIhdrChunk.getColorType()) {
            case 0: {
                final int n = ((this.reference[8] & 0xFF) << 8) + (this.reference[9] & 0xFF);
                if (pngIhdrChunk.getBitDepth() > 8) {
                    return PNGFileFormat.compress16BitDepthTo8BitDepth(n);
                }
                return n & 0xFF;
            }
            case 2: {
                int compress16BitDepthTo8BitDepth = (this.reference[8] & 0xFF) << 8 | (this.reference[9] & 0xFF);
                int compress16BitDepthTo8BitDepth2 = (this.reference[10] & 0xFF) << 8 | (this.reference[11] & 0xFF);
                int compress16BitDepthTo8BitDepth3 = (this.reference[12] & 0xFF) << 8 | (this.reference[13] & 0xFF);
                if (pngIhdrChunk.getBitDepth() > 8) {
                    compress16BitDepthTo8BitDepth = PNGFileFormat.compress16BitDepthTo8BitDepth(compress16BitDepthTo8BitDepth);
                    compress16BitDepthTo8BitDepth2 = PNGFileFormat.compress16BitDepthTo8BitDepth(compress16BitDepthTo8BitDepth2);
                    compress16BitDepthTo8BitDepth3 = PNGFileFormat.compress16BitDepthTo8BitDepth(compress16BitDepthTo8BitDepth3);
                }
                return compress16BitDepthTo8BitDepth << 16 | compress16BitDepthTo8BitDepth2 << 8 | compress16BitDepthTo8BitDepth3;
            }
            default: {
                SWT.error(40);
                return -1;
            }
        }
    }
    
    byte[] getAlphaValues(final PngIhdrChunk pngIhdrChunk, final PngPlteChunk pngPlteChunk) {
        if (pngIhdrChunk.getColorType() != 3) {
            SWT.error(40);
        }
        final byte[] array = new byte[pngPlteChunk.getPaletteSize()];
        int length;
        int i;
        for (length = this.getLength(), i = 0; i < length; ++i) {
            array[i] = this.reference[8 + i];
        }
        for (int j = i; j < array.length; ++j) {
            array[j] = -1;
        }
        return array;
    }
}
