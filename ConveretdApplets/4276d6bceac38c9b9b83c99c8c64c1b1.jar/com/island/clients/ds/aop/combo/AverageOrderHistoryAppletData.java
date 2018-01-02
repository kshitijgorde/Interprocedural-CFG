// 
// Decompiled by Procyon v0.5.30
// 

package com.island.clients.ds.aop.combo;

import com.island.clients.ds.aop.AverageOrderAppletData;
import com.island.servers.ds.chart.stockorder.OrderChartData;

public class AverageOrderHistoryAppletData extends OrderChartData
{
    protected int history;
    protected AverageOrderAppletData[] orderHistory;
    
    public AverageOrderHistoryAppletData(final String symbol, final int history, final AverageOrderAppletData[] orderHistory) {
        super(symbol);
        this.history = history;
        this.orderHistory = orderHistory;
        if (orderHistory != null && orderHistory.length > 0) {
            this.timeStamp = orderHistory[orderHistory.length - 1].getTimeStamp();
        }
    }
    
    public String toString() {
        final StringBuffer buffer = new StringBuffer();
        for (int i = 0, size = this.orderHistory.length; i < size; ++i) {
            buffer.append(this.orderHistory[i].toString());
            buffer.append("\n");
        }
        return buffer.toString();
    }
    
    public int getHistory() {
        return this.history;
    }
    
    public AverageOrderAppletData[] getOrderHistory() {
        return this.orderHistory;
    }
    
    public void setTimeStamp(final long timeStamp) {
        this.timeStamp = timeStamp;
    }
}
