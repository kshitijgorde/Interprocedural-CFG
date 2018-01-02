// 
// Decompiled by Procyon v0.5.30
// 

package com.pluraprocessing.node.customer;

public class Result
{
    private byte[] resultBytes;
    private long downloadTotalKB;
    
    public Result() {
        this.downloadTotalKB = 0L;
    }
    
    public byte[] getResultBytes() {
        return this.resultBytes;
    }
    
    public void setResultBytes(final byte[] resultBytes) {
        this.resultBytes = resultBytes;
    }
    
    public long getDownloadTotalKB() {
        return this.downloadTotalKB;
    }
    
    public void setDownloadTotalKB(final long downloadTotalKB) {
        this.downloadTotalKB = downloadTotalKB;
    }
}
