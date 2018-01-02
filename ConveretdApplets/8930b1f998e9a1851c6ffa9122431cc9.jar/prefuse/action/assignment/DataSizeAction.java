// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.action.assignment;

import prefuse.util.PrefuseLib;
import prefuse.visual.VisualItem;
import prefuse.data.tuple.TupleSet;
import java.util.logging.Logger;
import prefuse.util.MathLib;
import prefuse.util.DataLib;

public class DataSizeAction extends SizeAction
{
    protected static final double NO_SIZE = Double.NaN;
    protected String m_dataField;
    protected double m_minSize;
    protected double m_sizeRange;
    protected int m_scale;
    protected int m_bins;
    protected boolean m_inferBounds;
    protected boolean m_inferRange;
    protected boolean m_is2DArea;
    protected double[] m_dist;
    protected int m_tempScale;
    
    public DataSizeAction(final String s, final String dataField) {
        super(s, Double.NaN);
        this.m_minSize = 1.0;
        this.m_scale = 0;
        this.m_bins = -1;
        this.m_inferBounds = true;
        this.m_inferRange = true;
        this.m_is2DArea = true;
        this.m_dataField = dataField;
    }
    
    public DataSizeAction(final String s, final String s2, final int n) {
        this(s, s2, n, 0);
    }
    
    public DataSizeAction(final String s, final String dataField, final int binCount, final int scale) {
        super(s, Double.NaN);
        this.m_minSize = 1.0;
        this.m_scale = 0;
        this.m_bins = -1;
        this.m_inferBounds = true;
        this.m_inferRange = true;
        this.m_is2DArea = true;
        this.m_dataField = dataField;
        this.setScale(scale);
        this.setBinCount(binCount);
    }
    
    public String getDataField() {
        return this.m_dataField;
    }
    
    public void setDataField(final String dataField) {
        this.m_dataField = dataField;
    }
    
    public int getScale() {
        return this.m_scale;
    }
    
    public void setScale(final int scale) {
        if (scale < 0 || scale >= 4) {
            throw new IllegalArgumentException("Unrecognized scale value: " + scale);
        }
        this.m_scale = scale;
    }
    
    public int getBinCount() {
        return this.m_bins;
    }
    
    public void setBinCount(final int bins) {
        if (this.m_scale == 3 && bins <= 0) {
            throw new IllegalArgumentException("The quantile scale can not be used without binning. Use a bin value greater than zero.");
        }
        this.m_bins = bins;
    }
    
    public boolean is2DArea() {
        return this.m_is2DArea;
    }
    
    public void setIs2DArea(final boolean is2DArea) {
        this.m_is2DArea = is2DArea;
    }
    
    public double getMinimumSize() {
        return this.m_minSize;
    }
    
    public void setMinimumSize(final double minSize) {
        if (Double.isInfinite(minSize) || Double.isNaN(minSize) || minSize <= 0.0) {
            throw new IllegalArgumentException("Minimum size value must be a finite number greater than zero.");
        }
        if (this.m_inferRange) {
            this.m_sizeRange += this.m_minSize - minSize;
        }
        this.m_minSize = minSize;
    }
    
    public double getMaximumSize() {
        return this.m_minSize + this.m_sizeRange;
    }
    
    public void setMaximumSize(final double n) {
        if (Double.isInfinite(n) || Double.isNaN(n) || n <= 0.0) {
            this.m_inferRange = true;
        }
        else {
            this.m_inferRange = false;
            this.m_sizeRange = n - this.m_minSize;
        }
    }
    
    public void setDefaultSize(final double n) {
        throw new UnsupportedOperationException();
    }
    
    protected void setup() {
        final TupleSet group = this.m_vis.getGroup(this.m_group);
        this.m_tempScale = this.m_scale;
        if (this.m_inferBounds) {
            if (this.m_scale == 3 && this.m_bins > 0) {
                this.m_dist = MathLib.quantiles(this.m_bins, DataLib.toDoubleArray(group.tuples(), this.m_dataField));
            }
            else {
                if (this.m_scale == 3) {
                    Logger.getLogger(this.getClass().getName()).warning("Can't use quantile scale with no binning. Defaulting to linear scale. Set the bin value greater than zero to use a quantile scale.");
                    this.m_scale = 0;
                }
                (this.m_dist = new double[2])[0] = DataLib.min(group, this.m_dataField).getDouble(this.m_dataField);
                this.m_dist[1] = DataLib.max(group, this.m_dataField).getDouble(this.m_dataField);
            }
            if (this.m_inferRange) {
                this.m_sizeRange = this.m_dist[this.m_dist.length - 1] / this.m_dist[0] - this.m_minSize;
            }
        }
    }
    
    protected void finish() {
        this.m_scale = this.m_tempScale;
    }
    
    public double getSize(final VisualItem visualItem) {
        final double size = super.getSize(visualItem);
        if (!Double.isNaN(size)) {
            return size;
        }
        final double interp = MathLib.interp(this.m_scale, visualItem.getDouble(this.m_dataField), this.m_dist);
        double n;
        if (this.m_bins < 1) {
            n = this.m_minSize + interp * this.m_sizeRange;
        }
        else {
            n = this.m_minSize + ((interp < 1.0) ? ((int)(interp * this.m_bins)) : (this.m_bins - 1)) * (this.m_sizeRange / (this.m_bins - 1));
        }
        return this.m_is2DArea ? PrefuseLib.getSize2D(n) : n;
    }
}
