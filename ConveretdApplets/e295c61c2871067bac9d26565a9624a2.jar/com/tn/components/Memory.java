// 
// Decompiled by Procyon v0.5.30
// 

package com.tn.components;

import com.tn.util.Array;

public class Memory
{
    private int[] ivBytesRead;
    private int[] ivBytesWrite;
    
    public Memory(final int[] pBytesRead, final int[] pBytesWrite) {
        if (pBytesRead.length != pBytesWrite.length) {
            throw new IllegalArgumentException("Size of read and write memory are different");
        }
        this.ivBytesRead = pBytesRead;
        this.ivBytesWrite = pBytesWrite;
    }
    
    public int getByte(final int pAddress) {
        return this.ivBytesRead[pAddress];
    }
    
    public int[] getBytesRead() {
        return this.ivBytesRead;
    }
    
    public int[] getBytesWrite() {
        return this.ivBytesWrite;
    }
    
    public int getSize() {
        return this.ivBytesRead.length;
    }
    
    public MemoryStatus getStatus() {
        return new MemoryStatus(Array.intsToBytes(this.ivBytesRead));
    }
    
    public void init() {
        if (this.ivBytesRead == this.ivBytesWrite) {
            for (int i = 0; i < this.ivBytesRead.length; ++i) {
                if (Math.random() < 0.999) {
                    this.ivBytesRead[i] = (((i & 0x20) == 0x0) ? 255 : 0);
                }
                else {
                    this.ivBytesRead[i] = ((int)(Math.random() * 256.0) & 0xFF);
                }
            }
        }
    }
    
    public void setByte(final int pAddress, final int pValue) {
        this.ivBytesWrite[pAddress] = pValue;
    }
    
    public void setStatus(final MemoryStatus pStatus) {
        Array.copy(pStatus.getBytes(), this.ivBytesRead);
    }
}
