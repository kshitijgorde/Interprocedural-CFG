// 
// Decompiled by Procyon v0.5.30
// 

package com.island.clients.ds.bv;

import java.io.IOException;
import java.io.InputStream;

public class ReadableOrderRecord
{
    public long leaves;
    public long price;
    public long timestamp;
    public String mpid;
    
    ReadableOrderRecord(final InputStream inputstream, final int sharesSize, final int priceSize, final long priceBase) throws IOException {
        this.leaves = Utils.readNum(inputstream, sharesSize);
        this.price = Math.abs(Utils.readNum(inputstream, priceSize) + priceBase);
    }
    
    ReadableOrderRecord(final InputStream inputstream, final int sharesSize, final int priceSize, final long priceBase, final boolean includeTimeAndMPID) throws IOException {
        this.leaves = Utils.readNum(inputstream, sharesSize);
        this.price = Math.abs(Utils.readNum(inputstream, priceSize) + priceBase);
        if (includeTimeAndMPID) {
            this.timestamp = Utils.readNum(inputstream, 4);
            final byte[] attribution = new byte[4];
            inputstream.read(attribution, 0, 4);
            this.mpid = new String(attribution);
        }
    }
}
