// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.image;

import org.eclipse.swt.SWT;

class PngIendChunk extends PngChunk
{
    PngIendChunk() {
        super(0);
        this.setType(PngIendChunk.TYPE_IEND);
        this.setCRC(this.computeCRC());
    }
    
    PngIendChunk(final byte[] array) {
        super(array);
    }
    
    int getChunkType() {
        return 3;
    }
    
    void validate(final PngFileReadState pngFileReadState, final PngIhdrChunk pngIhdrChunk) {
        if (!pngFileReadState.readIHDR || (pngIhdrChunk.getMustHavePalette() && !pngFileReadState.readPLTE) || !pngFileReadState.readIDAT || pngFileReadState.readIEND) {
            SWT.error(40);
        }
        else {
            pngFileReadState.readIEND = true;
        }
        super.validate(pngFileReadState, pngIhdrChunk);
        if (this.getLength() > 0) {
            SWT.error(40);
        }
    }
}
