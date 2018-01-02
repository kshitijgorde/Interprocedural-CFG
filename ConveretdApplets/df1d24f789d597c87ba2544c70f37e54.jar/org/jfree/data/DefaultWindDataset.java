// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.data;

import java.util.Collections;
import java.util.Date;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

public class DefaultWindDataset extends AbstractXYDataset implements WindDataset
{
    private List seriesNames;
    private List allSeriesData;
    
    public DefaultWindDataset() {
        this.seriesNames = new ArrayList();
        this.allSeriesData = new ArrayList();
    }
    
    public DefaultWindDataset(final Object[][][] data) {
        this(seriesNameListFromDataArray(data), data);
    }
    
    public DefaultWindDataset(final String[] seriesNames, final Object[][][] data) {
        this(Arrays.asList(seriesNames), data);
    }
    
    public DefaultWindDataset(final List seriesNames, final Object[][][] data) {
        this.seriesNames = seriesNames;
        final int seriesCount = data.length;
        this.allSeriesData = new ArrayList(seriesCount);
        for (int seriesIndex = 0; seriesIndex < seriesCount; ++seriesIndex) {
            final List oneSeriesData = new ArrayList();
            for (int maxItemCount = data[seriesIndex].length, itemIndex = 0; itemIndex < maxItemCount; ++itemIndex) {
                final Object xObject = data[seriesIndex][itemIndex][0];
                if (xObject != null) {
                    Number xNumber;
                    if (xObject instanceof Number) {
                        xNumber = (Number)xObject;
                    }
                    else if (xObject instanceof Date) {
                        final Date xDate = (Date)xObject;
                        xNumber = new Long(xDate.getTime());
                    }
                    else {
                        xNumber = new Integer(0);
                    }
                    final Number windDir = (Number)data[seriesIndex][itemIndex][1];
                    final Number windForce = (Number)data[seriesIndex][itemIndex][2];
                    oneSeriesData.add(new WindDataItem(xNumber, windDir, windForce));
                }
            }
            Collections.sort((List<Comparable>)oneSeriesData);
            this.allSeriesData.add(seriesIndex, oneSeriesData);
        }
    }
    
    public int getSeriesCount() {
        return this.allSeriesData.size();
    }
    
    public int getItemCount(final int series) {
        final List oneSeriesData = this.allSeriesData.get(series);
        return oneSeriesData.size();
    }
    
    public String getSeriesName(final int series) {
        return this.seriesNames.get(series).toString();
    }
    
    public Number getXValue(final int series, final int item) {
        final List oneSeriesData = this.allSeriesData.get(series);
        final WindDataItem windItem = oneSeriesData.get(item);
        return windItem.getX();
    }
    
    public Number getYValue(final int series, final int item) {
        return this.getWindForce(series, item);
    }
    
    public Number getWindDirection(final int series, final int item) {
        final List oneSeriesData = this.allSeriesData.get(series);
        final WindDataItem windItem = oneSeriesData.get(item);
        return windItem.getWindDirection();
    }
    
    public Number getWindForce(final int series, final int item) {
        final List oneSeriesData = this.allSeriesData.get(series);
        final WindDataItem windItem = oneSeriesData.get(item);
        return windItem.getWindForce();
    }
    
    public static List seriesNameListFromDataArray(final Object[][] data) {
        final int seriesCount = data.length;
        final List seriesNameList = new ArrayList(seriesCount);
        for (int i = 0; i < seriesCount; ++i) {
            seriesNameList.add("Series " + (i + 1));
        }
        return seriesNameList;
    }
}
