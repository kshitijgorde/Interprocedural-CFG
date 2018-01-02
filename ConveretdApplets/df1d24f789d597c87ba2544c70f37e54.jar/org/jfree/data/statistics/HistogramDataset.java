// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.data.statistics;

import org.jfree.util.ObjectUtils;
import java.util.Map;
import java.util.HashMap;
import org.jfree.data.Dataset;
import org.jfree.data.DatasetChangeEvent;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import org.jfree.data.IntervalXYDataset;
import org.jfree.data.AbstractIntervalXYDataset;

public class HistogramDataset extends AbstractIntervalXYDataset implements IntervalXYDataset, Cloneable, Serializable
{
    private List list;
    private HistogramType type;
    
    public HistogramDataset() {
        this.list = new ArrayList();
        this.type = HistogramType.FREQUENCY;
    }
    
    public HistogramType getType() {
        return this.type;
    }
    
    public void setType(final HistogramType type) {
        if (type == null) {
            throw new IllegalArgumentException("Null 'type' argument");
        }
        this.type = type;
        this.notifyListeners(new DatasetChangeEvent(this, this));
    }
    
    public void addSeries(final String name, final double[] values, final int bins) {
        final double minimum = this.getMinimum(values);
        final double maximum = this.getMaximum(values);
        this.addSeries(name, values, bins, minimum, maximum);
    }
    
    public void addSeries(final String name, final double[] values, final int bins, final double minimum, final double maximum) {
        if (name == null) {
            throw new IllegalArgumentException("Null 'name' argument.");
        }
        if (values == null) {
            throw new IllegalArgumentException("Null 'values' argument.");
        }
        if (bins < 1) {
            throw new IllegalArgumentException("The 'bins' value must be at least 1.");
        }
        final double binWidth = (maximum - minimum) / bins;
        double tmp = minimum;
        final List binList = new ArrayList(bins);
        for (int i = 0; i < bins; ++i) {
            HistogramBin bin;
            if (i == bins - 1) {
                bin = new HistogramBin(tmp, maximum);
            }
            else {
                bin = new HistogramBin(tmp, tmp + binWidth);
            }
            tmp += binWidth;
            binList.add(bin);
        }
        for (int i = 0; i < values.length; ++i) {
            for (int j = 0; j < bins; ++j) {
                final HistogramBin currentBin = binList.get(j);
                if (values[i] >= currentBin.getStartBoundary() && values[i] <= currentBin.getEndBoundary()) {
                    currentBin.incrementCount();
                    break;
                }
            }
        }
        final Map map = new HashMap();
        map.put("name", name);
        map.put("bins", binList);
        map.put("values.length", new Integer(values.length));
        map.put("bin width", new Double(binWidth));
        this.list.add(map);
    }
    
    private double getMinimum(final double[] values) {
        if (values == null || values.length < 1) {
            throw new IllegalArgumentException("Null or zero length 'values' argument.");
        }
        double min = Double.MAX_VALUE;
        for (int i = 0; i < values.length; ++i) {
            if (values[i] < min) {
                min = values[i];
            }
        }
        return min;
    }
    
    private double getMaximum(final double[] values) {
        if (values == null || values.length < 1) {
            throw new IllegalArgumentException("Null or zero length 'values' argument.");
        }
        double max = -1.7976931348623157E308;
        for (int i = 0; i < values.length; ++i) {
            if (values[i] > max) {
                max = values[i];
            }
        }
        return max;
    }
    
    List getBins(final int series) {
        final Map map = this.list.get(series);
        return map.get("bins");
    }
    
    private int getTotal(final int series) {
        final Map map = this.list.get(series);
        return map.get("values.length");
    }
    
    private double getBinWidth(final int series) {
        final Map map = this.list.get(series);
        return map.get("bin width");
    }
    
    public int getSeriesCount() {
        return this.list.size();
    }
    
    public String getSeriesName(final int series) {
        final Map map = this.list.get(series);
        return map.get("name");
    }
    
    public int getItemCount(final int series) {
        return this.getBins(series).size();
    }
    
    public Number getXValue(final int series, final int item) {
        final List bins = this.getBins(series);
        final HistogramBin bin = bins.get(item);
        final double x = (bin.getStartBoundary() + bin.getEndBoundary()) / 2.0;
        return new Double(x);
    }
    
    public Number getYValue(final int series, final int item) {
        final List bins = this.getBins(series);
        final HistogramBin bin = bins.get(item);
        final double total = this.getTotal(series);
        final double binWidth = this.getBinWidth(series);
        if (this.type == HistogramType.FREQUENCY) {
            return new Double(bin.getCount());
        }
        if (this.type == HistogramType.RELATIVE_FREQUENCY) {
            return new Double(bin.getCount() / total);
        }
        if (this.type == HistogramType.SCALE_AREA_TO_1) {
            return new Double(bin.getCount() / (binWidth * total));
        }
        throw new IllegalStateException();
    }
    
    public Number getStartXValue(final int series, final int item) {
        final List bins = this.getBins(series);
        final HistogramBin bin = bins.get(item);
        return new Double(bin.getStartBoundary());
    }
    
    public Number getEndXValue(final int series, final int item) {
        final List bins = this.getBins(series);
        final HistogramBin bin = bins.get(item);
        return new Double(bin.getEndBoundary());
    }
    
    public Number getStartYValue(final int series, final int item) {
        return this.getYValue(series, item);
    }
    
    public Number getEndYValue(final int series, final int item) {
        return this.getYValue(series, item);
    }
    
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj instanceof HistogramDataset) {
            final HistogramDataset dataset = (HistogramDataset)obj;
            final boolean b0 = ObjectUtils.equal(dataset.type, this.type);
            final boolean b2 = ObjectUtils.equal(dataset.list, this.list);
            return b0 && b2;
        }
        return false;
    }
    
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
