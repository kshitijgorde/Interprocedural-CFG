// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.image;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.PaletteData;

class PngPlteChunk extends PngChunk
{
    int paletteSize;
    
    PngPlteChunk(final PaletteData paletteData) {
        super(paletteData.getRGBs().length * 3);
        this.paletteSize = this.length / 3;
        this.setType(PngPlteChunk.TYPE_PLTE);
        this.setPaletteData(paletteData);
        this.setCRC(this.computeCRC());
    }
    
    PngPlteChunk(final byte[] array) {
        super(array);
        this.paletteSize = this.length / 3;
    }
    
    int getChunkType() {
        return 1;
    }
    
    int getPaletteSize() {
        return this.paletteSize;
    }
    
    PaletteData getPaletteData() {
        final RGB[] array = new RGB[this.paletteSize];
        for (int i = 0; i < array.length; ++i) {
            final int n = 8 + i * 3;
            array[i] = new RGB(this.reference[n] & 0xFF, this.reference[n + 1] & 0xFF, this.reference[n + 2] & 0xFF);
        }
        return new PaletteData(array);
    }
    
    void setPaletteData(final PaletteData paletteData) {
        final RGB[] rgBs = paletteData.getRGBs();
        for (int i = 0; i < rgBs.length; ++i) {
            final int n = 8 + i * 3;
            this.reference[n] = (byte)rgBs[i].red;
            this.reference[n + 1] = (byte)rgBs[i].green;
            this.reference[n + 2] = (byte)rgBs[i].blue;
        }
    }
    
    void validate(final PngFileReadState pngFileReadState, final PngIhdrChunk pngIhdrChunk) {
        if (!pngFileReadState.readIHDR || pngFileReadState.readPLTE || pngFileReadState.readTRNS || pngFileReadState.readIDAT || pngFileReadState.readIEND) {
            SWT.error(40);
        }
        else {
            pngFileReadState.readPLTE = true;
        }
        super.validate(pngFileReadState, pngIhdrChunk);
        if (this.getLength() % 3 != 0) {
            SWT.error(40);
        }
        if (1 << pngIhdrChunk.getBitDepth() < this.paletteSize) {
            SWT.error(40);
        }
        if (256 < this.paletteSize) {
            SWT.error(40);
        }
    }
    
    void contributeToString(final StringBuffer sb) {
        sb.append("\n\tPalette size:");
        sb.append(this.paletteSize);
    }
}
