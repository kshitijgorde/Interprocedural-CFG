// 
// Decompiled by Procyon v0.5.30
// 

package com.tn.zx.peripheral;

import java.io.IOException;
import java.io.File;
import java.io.RandomAccessFile;

public class MicrodriveCartridgeMdrFile implements MicrodriveCartridge
{
    private RandomAccessFile ivFile;
    private boolean ivWriteProtected;
    
    public MicrodriveCartridgeMdrFile(final File pFile) throws IOException {
        this.ivWriteProtected = false;
        if (pFile.exists()) {
            if (pFile.length() != 137923L) {
                throw new IOException("File is not an .mdr file: " + pFile);
            }
            final RandomAccessFile raf = new RandomAccessFile(pFile, "r");
            raf.seek(137922L);
            this.ivWriteProtected = (raf.read() != 0);
            raf.close();
        }
        if (this.ivWriteProtected) {
            this.ivFile = new RandomAccessFile(pFile, "r");
        }
        else {
            (this.ivFile = new RandomAccessFile(pFile, "rw")).setLength(137923L);
        }
    }
    
    @Override
    public byte getByte(final int pIndex) {
        try {
            if (this.ivFile.getFilePointer() != pIndex) {
                this.ivFile.seek(pIndex);
            }
            return (byte)this.ivFile.read();
        }
        catch (IOException e) {
            return 0;
        }
    }
    
    @Override
    public int getLength() {
        return 137922;
    }
    
    @Override
    public boolean isWriteProtected() {
        return this.ivWriteProtected;
    }
    
    @Override
    public void setByte(final int pIndex, final byte pValue) {
        try {
            if (this.ivFile.getFilePointer() != pIndex) {
                this.ivFile.seek(pIndex);
            }
            this.ivFile.write(pValue);
        }
        catch (IOException ex) {}
    }
}
