// 
// Decompiled by Procyon v0.5.30
// 

package com.island.clients.ds.bv;

import java.io.IOException;
import java.io.InputStream;

public class ReadableStockRecord
{
    public long buyCount;
    public long sellCount;
    public long totalOrderCount;
    public long volume;
    public long lastTradePrice;
    public long lastTradeTime;
    public boolean halted;
    public boolean includeTimeAndMPID;
    public ReadableOrderRecord[] buys;
    public ReadableOrderRecord[] sells;
    
    public ReadableStockRecord(final InputStream inputstream) throws IOException {
        this(inputstream, false);
    }
    
    public ReadableStockRecord(final InputStream inputstream, final boolean halted) throws IOException {
        this(inputstream, halted, false);
    }
    
    public ReadableStockRecord(final InputStream inputstream, final boolean halted, final boolean includeTimeAndMPID) throws IOException {
        this.includeTimeAndMPID = false;
        this.halted = halted;
        this.includeTimeAndMPID = includeTimeAndMPID;
        this.buyCount = Utils.readNum(inputstream, 2);
        this.sellCount = Utils.readNum(inputstream, 2);
        this.totalOrderCount = Utils.readNum(inputstream, 3);
        this.volume = Utils.readNum(inputstream, 4);
        this.lastTradePrice = Utils.readNum(inputstream, 3);
        this.lastTradeTime = Utils.readNum(inputstream, 3);
        final int i = inputstream.read();
        final int j = i >> 4;
        final int k = i & 0xF;
        final int r = inputstream.read();
        final int s = r >> 4;
        final int p = r & 0xF;
        final long bp = Utils.readNum(inputstream, 4);
        this.buys = new ReadableOrderRecord[j];
        for (int l = 0; l < j; ++l) {
            this.buys[l] = new ReadableOrderRecord(inputstream, s, p, -bp, includeTimeAndMPID);
        }
        this.sells = new ReadableOrderRecord[k];
        for (int i2 = 0; i2 < k; ++i2) {
            this.sells[i2] = new ReadableOrderRecord(inputstream, s, p, bp, includeTimeAndMPID);
        }
        if (halted) {
            this.buys = new ReadableOrderRecord[0];
            this.sells = new ReadableOrderRecord[0];
            final long n = 0L;
            this.sellCount = n;
            this.buyCount = n;
        }
    }
    
    public boolean isHalted() {
        return this.halted;
    }
}
