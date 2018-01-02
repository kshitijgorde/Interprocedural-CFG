// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.image;

import org.eclipse.swt.SWT;

class PngIdatChunk extends PngChunk
{
    static final int HEADER_BYTES_LENGTH = 2;
    static final int ADLER_FIELD_LENGTH = 4;
    static final int HEADER_BYTE1_DATA_OFFSET = 8;
    static final int HEADER_BYTE2_DATA_OFFSET = 9;
    static final int ADLER_DATA_OFFSET = 10;
    
    PngIdatChunk(final byte b, final byte b2, final byte[] array, final int n) {
        super(array.length + 2 + 4);
        this.setType(PngIdatChunk.TYPE_IDAT);
        this.reference[8] = b;
        this.reference[9] = b2;
        System.arraycopy(array, 0, this.reference, 8, array.length);
        this.setInt32(10, n);
        this.setCRC(this.computeCRC());
    }
    
    PngIdatChunk(final byte[] array) {
        super(array);
    }
    
    int getChunkType() {
        return 2;
    }
    
    void validate(final PngFileReadState pngFileReadState, final PngIhdrChunk pngIhdrChunk) {
        if (!pngFileReadState.readIHDR || (pngIhdrChunk.getMustHavePalette() && !pngFileReadState.readPLTE) || pngFileReadState.readIEND) {
            SWT.error(40);
        }
        else {
            pngFileReadState.readIDAT = true;
        }
        super.validate(pngFileReadState, pngIhdrChunk);
    }
    
    byte getDataByteAtOffset(final int n) {
        return this.reference[8 + n];
    }
}
