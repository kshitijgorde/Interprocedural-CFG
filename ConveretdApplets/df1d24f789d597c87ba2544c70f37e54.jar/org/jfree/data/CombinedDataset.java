// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.data;

import java.util.ArrayList;
import java.util.List;

public class CombinedDataset extends AbstractIntervalXYDataset implements XYDataset, HighLowDataset, IntervalXYDataset, CombinationDataset
{
    private List datasetInfo;
    
    public CombinedDataset() {
        this.datasetInfo = new ArrayList();
    }
    
    public CombinedDataset(final SeriesDataset[] data) {
        this.datasetInfo = new ArrayList();
        this.add(data);
    }
    
    public void add(final SeriesDataset data) {
        this.fastAdd(data);
        final DatasetChangeEvent event = new DatasetChangeEvent(this, this);
        this.notifyListeners(event);
    }
    
    public void add(final SeriesDataset[] data) {
        for (int i = 0; i < data.length; ++i) {
            this.fastAdd(data[i]);
        }
        final DatasetChangeEvent event = new DatasetChangeEvent(this, this);
        this.notifyListeners(event);
    }
    
    public void add(final SeriesDataset data, final int series) {
        this.add(new SubSeriesDataset(data, series));
    }
    
    private void fastAdd(final SeriesDataset data) {
        for (int i = 0; i < data.getSeriesCount(); ++i) {
            this.datasetInfo.add(new DatasetInfo(data, i));
        }
    }
    
    public int getSeriesCount() {
        return this.datasetInfo.size();
    }
    
    public String getSeriesName(final int series) {
        final DatasetInfo di = this.getDatasetInfo(series);
        return di.data.getSeriesName(di.series);
    }
    
    public Number getXValue(final int series, final int item) {
        final DatasetInfo di = this.getDatasetInfo(series);
        return ((XYDataset)di.data).getXValue(di.series, item);
    }
    
    public Number getYValue(final int series, final int item) {
        final DatasetInfo di = this.getDatasetInfo(series);
        return ((XYDataset)di.data).getYValue(di.series, item);
    }
    
    public int getItemCount(final int series) {
        final DatasetInfo di = this.getDatasetInfo(series);
        return ((XYDataset)di.data).getItemCount(di.series);
    }
    
    public Number getHighValue(final int series, final int item) {
        final DatasetInfo di = this.getDatasetInfo(series);
        return ((HighLowDataset)di.data).getHighValue(di.series, item);
    }
    
    public double getHigh(final int series, final int item) {
        double result = Double.NaN;
        final Number high = this.getHighValue(series, item);
        if (high != null) {
            result = high.doubleValue();
        }
        return result;
    }
    
    public Number getLowValue(final int series, final int item) {
        final DatasetInfo di = this.getDatasetInfo(series);
        return ((HighLowDataset)di.data).getLowValue(di.series, item);
    }
    
    public double getLow(final int series, final int item) {
        double result = Double.NaN;
        final Number low = this.getLowValue(series, item);
        if (low != null) {
            result = low.doubleValue();
        }
        return result;
    }
    
    public Number getOpenValue(final int series, final int item) {
        final DatasetInfo di = this.getDatasetInfo(series);
        return ((HighLowDataset)di.data).getOpenValue(di.series, item);
    }
    
    public double getOpen(final int series, final int item) {
        double result = Double.NaN;
        final Number open = this.getOpenValue(series, item);
        if (open != null) {
            result = open.doubleValue();
        }
        return result;
    }
    
    public Number getCloseValue(final int series, final int item) {
        final DatasetInfo di = this.getDatasetInfo(series);
        return ((HighLowDataset)di.data).getCloseValue(di.series, item);
    }
    
    public double getClose(final int series, final int item) {
        double result = Double.NaN;
        final Number close = this.getCloseValue(series, item);
        if (close != null) {
            result = close.doubleValue();
        }
        return result;
    }
    
    public Number getVolumeValue(final int series, final int item) {
        final DatasetInfo di = this.getDatasetInfo(series);
        return ((HighLowDataset)di.data).getVolumeValue(di.series, item);
    }
    
    public double getVolume(final int series, final int item) {
        double result = Double.NaN;
        final Number volume = this.getVolumeValue(series, item);
        if (volume != null) {
            result = volume.doubleValue();
        }
        return result;
    }
    
    public Number getStartXValue(final int series, final int item) {
        final DatasetInfo di = this.getDatasetInfo(series);
        if (di.data instanceof IntervalXYDataset) {
            return ((IntervalXYDataset)di.data).getStartXValue(di.series, item);
        }
        return this.getXValue(series, item);
    }
    
    public Number getEndXValue(final int series, final int item) {
        final DatasetInfo di = this.getDatasetInfo(series);
        if (di.data instanceof IntervalXYDataset) {
            return ((IntervalXYDataset)di.data).getEndXValue(di.series, item);
        }
        return this.getXValue(series, item);
    }
    
    public Number getStartYValue(final int series, final int item) {
        final DatasetInfo di = this.getDatasetInfo(series);
        if (di.data instanceof IntervalXYDataset) {
            return ((IntervalXYDataset)di.data).getStartYValue(di.series, item);
        }
        return this.getYValue(series, item);
    }
    
    public Number getEndYValue(final int series, final int item) {
        final DatasetInfo di = this.getDatasetInfo(series);
        if (di.data instanceof IntervalXYDataset) {
            return ((IntervalXYDataset)di.data).getEndYValue(di.series, item);
        }
        return this.getYValue(series, item);
    }
    
    public SeriesDataset getParent() {
        SeriesDataset parent = null;
        for (int i = 0; i < this.datasetInfo.size(); ++i) {
            final SeriesDataset child = this.getDatasetInfo(i).data;
            if (!(child instanceof CombinationDataset)) {
                return null;
            }
            final SeriesDataset childParent = ((CombinationDataset)child).getParent();
            if (parent == null) {
                parent = childParent;
            }
            else if (parent != childParent) {
                return null;
            }
        }
        return parent;
    }
    
    public int[] getMap() {
        int[] map = null;
        for (int i = 0; i < this.datasetInfo.size(); ++i) {
            final SeriesDataset child = this.getDatasetInfo(i).data;
            if (!(child instanceof CombinationDataset)) {
                return null;
            }
            final int[] childMap = ((CombinationDataset)child).getMap();
            if (childMap == null) {
                return null;
            }
            map = this.joinMap(map, childMap);
        }
        return map;
    }
    
    public int getChildPosition(final Dataset child) {
        int n = 0;
        for (int i = 0; i < this.datasetInfo.size(); ++i) {
            final SeriesDataset childDataset = this.getDatasetInfo(i).data;
            if (childDataset instanceof CombinedDataset) {
                final int m = ((CombinedDataset)childDataset).getChildPosition(child);
                if (m >= 0) {
                    return n + m;
                }
                ++n;
            }
            else {
                if (child == childDataset) {
                    return n;
                }
                ++n;
            }
        }
        return -1;
    }
    
    private DatasetInfo getDatasetInfo(final int series) {
        return this.datasetInfo.get(series);
    }
    
    private int[] joinMap(final int[] a, final int[] b) {
        if (a == null) {
            return b;
        }
        if (b == null) {
            return a;
        }
        final int[] result = new int[a.length + b.length];
        System.arraycopy(a, 0, result, 0, a.length);
        System.arraycopy(b, 0, result, a.length, b.length);
        return result;
    }
    
    private class DatasetInfo
    {
        private SeriesDataset data;
        private int series;
        
        DatasetInfo(final SeriesDataset data, final int series) {
            this.data = data;
            this.series = series;
        }
    }
}
