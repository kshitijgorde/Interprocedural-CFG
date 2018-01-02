// 
// Decompiled by Procyon v0.5.30
// 

package com.island.clients.ds.aop.combo;

import com.island.servers.ds.chart.stockorder.averageorderchart.AverageOrderChartData;

public class DataList
{
    Data first;
    Data last;
    int size;
    int timeLimit;
    
    public DataList(final int timeLimit) {
        this.timeLimit = timeLimit;
    }
    
    public synchronized void add(final AverageOrderChartData data) {
        if (data == null) {
            return;
        }
        this.add(new Data(data));
    }
    
    protected void add(final Data data) {
        if (data == null) {
            return;
        }
        if (this.first == null) {
            this.last = data;
            this.first = data;
        }
        else {
            this.last.next = data;
            this.last = data;
        }
        ++this.size;
        final long limit = data.data.getTimeStamp() - this.timeLimit;
        while (this.first != null) {
            final Data next = this.first.next;
            if (this.first.data.getTimeStamp() >= limit) {
                break;
            }
            if (next.data.getTimeStamp() > limit) {
                this.first.data.setTimeStamp(limit);
                break;
            }
            this.removeFirst();
        }
    }
    
    public synchronized void add(final DataList list) {
        if (list == null || list.size < 1) {
            return;
        }
        Data data = null;
        while ((data = list.removeFirst()) != null) {
            this.add(data);
        }
    }
    
    public synchronized Data removeFirst() {
        Data data = null;
        if (this.first != null) {
            data = this.first;
            this.first = this.first.next;
            --this.size;
        }
        return data;
    }
    
    public synchronized Data getLast() {
        return this.last;
    }
    
    public synchronized void clear() {
        final Data data = null;
        this.last = data;
        this.first = data;
        this.size = 0;
    }
    
    public synchronized AverageOrderChartData[] toArray() {
        return this.toArray(this.size);
    }
    
    public synchronized AverageOrderChartData[] toArray(final int size) {
        AverageOrderChartData[] array = null;
        if (size > this.size) {
            array = new AverageOrderChartData[this.size];
        }
        else {
            array = new AverageOrderChartData[size];
        }
        Data d = this.first;
        for (int i = 0; i < size && i < this.size && d != null; d = d.next, ++i) {
            array[i] = d.data;
        }
        return array;
    }
    
    public void setTimeLimit(final int timeLimit) {
        if (timeLimit > 0) {
            this.timeLimit = timeLimit;
        }
    }
    
    public static class Data
    {
        AverageOrderChartData data;
        Data next;
        
        public Data(final AverageOrderChartData data) {
            this.data = data;
        }
    }
}
