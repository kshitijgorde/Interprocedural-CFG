// 
// Decompiled by Procyon v0.5.30
// 

package com.controlj.green.applets;

import java.io.Serializable;

public class TrendData implements Serializable
{
    SubGraph[] subgraphs;
    
    public TrendData() {
        this.subgraphs = new SubGraph[0];
    }
    
    public TrendData(final SubGraph[] subg) {
        this.subgraphs = subg;
    }
    
    public SubGraph getSubGraph(final int index) {
        if (this.subgraphs == null || index >= this.subgraphs.length) {
            return null;
        }
        return this.subgraphs[index];
    }
    
    public SubGraph[] getAllSubGraphs() {
        return this.subgraphs;
    }
    
    public int getNumSubGraphs() {
        if (this.subgraphs == null) {
            return 0;
        }
        return this.subgraphs.length;
    }
    
    public static class SubGraph implements Serializable
    {
        int typeOfGraph;
        private DataSeries[] series;
        
        public SubGraph() {
            this.series = null;
            this.series = new DataSeries[0];
            this.typeOfGraph = 10;
        }
        
        public SubGraph(final DataSeries[] s, final int type) {
            this.series = null;
            this.series = s;
            this.typeOfGraph = type;
        }
        
        public DataSeries getDataSeries(final int index) {
            if (this.series == null || index >= this.series.length) {
                return null;
            }
            return this.series[index];
        }
        
        public DataSeries[] getAllDataSeries() {
            return this.series;
        }
        
        public int getNumDataSeries() {
            if (this.series == null) {
                return 0;
            }
            return this.series.length;
        }
        
        public int getType() {
            return this.typeOfGraph;
        }
    }
    
    public static class DataSeries implements Serializable
    {
        long currentTime;
        int typeOfSeries;
        public boolean hasMoreDataToLeft;
        public boolean hasMoreDataToRight;
        private BNLogRecord[] records;
        private String errorString;
        
        public DataSeries() {
            this.currentTime = -1L;
            this.hasMoreDataToLeft = false;
            this.hasMoreDataToRight = false;
            this.records = null;
            this.errorString = null;
            this.records = new BNLogRecord[0];
            this.typeOfSeries = 10;
        }
        
        public DataSeries(final BNLogRecord[] r, final int defaultType) {
            this.currentTime = -1L;
            this.hasMoreDataToLeft = false;
            this.hasMoreDataToRight = false;
            this.records = null;
            this.errorString = null;
            this.records = r;
            if (this.records == null) {
                this.records = new BNLogRecord[0];
            }
            this.typeOfSeries = this.getTypeFromRecords(this.records, defaultType);
        }
        
        public BNLogRecord[] getRecords() {
            return this.records;
        }
        
        public int getType() {
            return this.typeOfSeries;
        }
        
        public void setType(final int type) {
            this.typeOfSeries = type;
        }
        
        public int getNumRecords() {
            if (this.records == null) {
                return 0;
            }
            return this.records.length;
        }
        
        private int getTypeFromRecords(final BNLogRecord[] bnRecords, final int defaultType) {
            if (bnRecords == null || bnRecords.length == 0) {
                return defaultType;
            }
            for (int i = 0; i < bnRecords.length; ++i) {
                if (!bnRecords[i].isSpecial()) {
                    final int type = bnRecords[i].getPointType();
                    return type;
                }
            }
            return defaultType;
        }
        
        public void setHasMoreData(final boolean dataToLeft, final boolean dataToRight) {
            this.hasMoreDataToLeft = dataToLeft;
            this.hasMoreDataToRight = dataToRight;
        }
        
        public boolean hasErrors() {
            return this.errorString != null;
        }
        
        public String getErrors() {
            return this.errorString;
        }
        
        public void setErrors(final String val) {
            this.errorString = val;
        }
        
        public void setCurrentSystemTime(final long time) {
            this.currentTime = time;
        }
        
        public long getCurrentSystemTime() {
            return this.currentTime;
        }
    }
}
