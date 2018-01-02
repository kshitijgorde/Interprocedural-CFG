// 
// Decompiled by Procyon v0.5.30
// 

package com.controlj.green.applets.comm;

import java.util.StringTokenizer;

public class TrendDataRequest
{
    public static final String SEPARATOR_SUBGRAPH = "|";
    public static final String SEPARATOR_DATASERIES = ";";
    public static final String SEPARATOR_DATASERIES_DATA = ",";
    public static final String KEY_TREND_REQUEST = "trendrequest";
    public static final String KEY_LIMIT_FROM = "limitfromstart";
    private boolean hasRequests;
    private SeriesRequest[] requests;
    
    public TrendDataRequest() {
    }
    
    private TrendDataRequest(final SeriesRequest[] series, final boolean hasReqs) {
        this.requests = series;
        this.hasRequests = hasReqs;
    }
    
    public static StringBuffer createRequest(final boolean limitFromStart) {
        final StringBuffer reqString = new StringBuffer();
        reqString.append("limitfromstart");
        reqString.append("=");
        reqString.append(limitFromStart);
        reqString.append("&");
        reqString.append("trendrequest");
        reqString.append("=");
        return reqString;
    }
    
    public static void addGraphToRequest(final StringBuffer request, final boolean isFirstGraph) {
        if (!isFirstGraph) {
            request.append("|");
        }
    }
    
    public static void addSeriesToRequest(final StringBuffer request, final String path, final long startTime, final long endTime, final long sequenceNumber, final boolean isFirstSeries) {
        if (!isFirstSeries) {
            request.append(";");
        }
        if (path == null || path.length() == 0) {
            request.append("null");
        }
        else {
            request.append(escapePathString(path));
        }
        request.append(",");
        request.append(startTime);
        request.append(",");
        request.append(endTime);
        request.append(",");
        request.append(sequenceNumber);
    }
    
    private static String escapePathString(final String path) {
        final StringBuffer buf = new StringBuffer(path);
        for (int index = 0; index < buf.length(); ++index) {
            if (buf.charAt(index) == '#') {
                buf.setCharAt(index, '%');
                buf.insert(index + 1, "23");
                index += 2;
            }
        }
        return buf.toString();
    }
    
    public static TrendDataRequest[] getDataRequests(final String trendRequest) {
        if (trendRequest == null || trendRequest.equals("")) {
            return null;
        }
        final StringTokenizer graphTokenizer = new StringTokenizer(trendRequest, "|");
        final TrendDataRequest[] dataRequests = new TrendDataRequest[graphTokenizer.countTokens()];
        int graphIndex = 0;
        while (graphTokenizer.hasMoreTokens()) {
            final int graphType = 10;
            boolean hasReqs = false;
            final String graphRequest = graphTokenizer.nextToken();
            SeriesRequest[] seriesReqs = null;
            if (graphRequest != null || graphRequest.length() != 0 || !graphRequest.equalsIgnoreCase("null")) {
                final StringTokenizer seriesTokenizer = new StringTokenizer(graphRequest, ";");
                seriesReqs = new SeriesRequest[seriesTokenizer.countTokens()];
                int seriesIndex = 0;
                while (seriesTokenizer.hasMoreTokens()) {
                    final StringTokenizer seriesDataTokenizer = new StringTokenizer(seriesTokenizer.nextToken(), ",");
                    final String seriesPath = seriesDataTokenizer.nextToken();
                    final long start = Long.parseLong(seriesDataTokenizer.nextToken());
                    final long end = Long.parseLong(seriesDataTokenizer.nextToken());
                    final long sequenceNumber = Long.parseLong(seriesDataTokenizer.nextToken());
                    boolean needsData = true;
                    if (seriesPath == null || seriesPath.length() == 0 || seriesPath.equalsIgnoreCase("null")) {
                        needsData = false;
                    }
                    else {
                        hasReqs = true;
                    }
                    seriesReqs[seriesIndex] = new SeriesRequest(seriesPath, start, end, sequenceNumber, needsData);
                    ++seriesIndex;
                }
            }
            dataRequests[graphIndex] = new TrendDataRequest(seriesReqs, hasReqs);
            ++graphIndex;
        }
        return dataRequests;
    }
    
    public boolean hasRequests() {
        return this.hasRequests && this.requests != null && this.requests.length != 0;
    }
    
    public SeriesRequest getRequest(final int index) {
        if (this.requests == null || index < 0 || index >= this.requests.length) {
            return null;
        }
        return this.requests[index];
    }
    
    public int getNumRequests() {
        if (this.requests == null) {
            return 0;
        }
        return this.requests.length;
    }
    
    public static class SeriesRequest
    {
        public String path;
        public long startTime;
        public long endTime;
        public long sequenceNumber;
        public boolean needsData;
        
        public SeriesRequest(final String pathToTrend, final long start, final long end, final long sequence, final boolean needs) {
            this.path = pathToTrend;
            this.startTime = start;
            this.endTime = end;
            this.needsData = needs;
            this.sequenceNumber = sequence;
        }
    }
}
