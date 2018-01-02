// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.image;

import org.eclipse.swt.SWT;

public class PngChunkReader
{
    LEDataInputStream inputStream;
    PngFileReadState readState;
    PngIhdrChunk headerChunk;
    PngPlteChunk paletteChunk;
    
    PngChunkReader(final LEDataInputStream inputStream) {
        this.inputStream = inputStream;
        this.readState = new PngFileReadState();
        this.headerChunk = null;
    }
    
    PngIhdrChunk getIhdrChunk() {
        if (this.headerChunk == null) {
            try {
                final PngChunk nextFromStream = PngChunk.readNextFromStream(this.inputStream);
                if (nextFromStream == null) {
                    SWT.error(40);
                }
                (this.headerChunk = (PngIhdrChunk)nextFromStream).validate(this.readState, null);
            }
            catch (ClassCastException ex) {
                SWT.error(40);
            }
        }
        return this.headerChunk;
    }
    
    PngChunk readNextChunk() {
        if (this.headerChunk == null) {
            return this.getIhdrChunk();
        }
        final PngChunk nextFromStream = PngChunk.readNextFromStream(this.inputStream);
        if (nextFromStream == null) {
            SWT.error(40);
        }
        switch (nextFromStream.getChunkType()) {
            case 5: {
                ((PngTrnsChunk)nextFromStream).validate(this.readState, this.headerChunk, this.paletteChunk);
                break;
            }
            case 1: {
                nextFromStream.validate(this.readState, this.headerChunk);
                this.paletteChunk = (PngPlteChunk)nextFromStream;
                break;
            }
            default: {
                nextFromStream.validate(this.readState, this.headerChunk);
                break;
            }
        }
        if (this.readState.readIDAT && nextFromStream.getChunkType() != 2) {
            this.readState.readPixelData = true;
        }
        return nextFromStream;
    }
    
    boolean readPixelData() {
        return this.readState.readPixelData;
    }
    
    boolean hasMoreChunks() {
        return !this.readState.readIEND;
    }
}
