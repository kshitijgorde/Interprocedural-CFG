// 
// Decompiled by Procyon v0.5.30
// 

package com.island.clients.ds.aop;

import com.island.servers.ds.chart.stockorder.averageorderchart.AverageOrderChartData;

public class AverageOrderAppletData extends AverageOrderChartData
{
    public static final int DATA = 0;
    public static final int TIMESTAMP_ONLY = 1;
    public static final int INVALID_SYMBOL = 2;
    public static final int ERROR_DATA = 3;
    protected int dataType;
    protected long updateTime;
    
    public AverageOrderAppletData(final String symbol, final int size) {
        super(symbol, size);
        this.dataType = 0;
    }
    
    public int getBidVolume(final float price) {
        final int volume = 0;
        final long p0 = (long)(price * 1000.0f);
        if (this.bids == null || this.bids.length <= 0) {
            return 0;
        }
        if (this.bids[0].getPrice() / 1000.0f < price) {
            return 0;
        }
        int sumSize = this.bids[0].getSize();
        long sum = this.bids[0].getPrice() * sumSize;
        long subSum = 0L;
        long p2 = 0L;
        int s = 0;
        for (int i = 1, bidSize = this.bids.length; i < bidSize; ++i) {
            p2 = this.bids[i].getPrice();
            s = this.bids[i].getSize();
            subSum = p2 * s;
            if ((sum + subSum) / (sumSize + s) < p0) {
                final int deltaS = (int)((sum - price * 1000.0f * sumSize) / (p0 - p2));
                sumSize += ((deltaS > s) ? s : deltaS);
                break;
            }
            sumSize += s;
            sum += subSum;
        }
        return sumSize;
    }
    
    public int getAskVolume(final float price) {
        final int volume = 0;
        final long p0 = (long)(price * 1000.0f);
        if (this.asks == null || this.asks.length <= 0) {
            return 0;
        }
        if (this.asks[0].getPrice() / 1000.0f > price) {
            return 0;
        }
        int sumSize = this.asks[0].getSize();
        long sum = this.asks[0].getPrice() * sumSize;
        long subSum = 0L;
        long p2 = 0L;
        int s = 0;
        for (int i = 1, askSize = this.asks.length; i < askSize; ++i) {
            p2 = this.asks[i].getPrice();
            s = this.asks[i].getSize();
            subSum = p2 * s;
            if ((sum + subSum) / (sumSize + s) > p0) {
                final int deltaS = (int)((price * 1000.0f * sumSize - sum) / (p2 - p0));
                sumSize += ((deltaS > s) ? s : deltaS);
                break;
            }
            sumSize += s;
            sum += subSum;
        }
        return sumSize;
    }
    
    public int getDataType() {
        return this.dataType;
    }
    
    public long getUpdateTime() {
        return this.updateTime;
    }
    
    public void setDataType(final int dataType) {
        this.dataType = dataType;
    }
    
    public void setUpdateTime(final long updateTime) {
        this.updateTime = updateTime;
    }
}
