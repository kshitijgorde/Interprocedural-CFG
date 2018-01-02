// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.action.assignment;

import prefuse.visual.VisualItem;
import prefuse.util.ColorLib;
import prefuse.data.tuple.TupleSet;
import prefuse.util.MathLib;
import prefuse.util.DataLib;
import java.util.logging.Logger;
import java.util.HashMap;
import prefuse.util.ColorMap;
import java.util.Map;

public class DataColorAction extends ColorAction
{
    private String m_dataField;
    private int m_type;
    private int m_scale;
    private int m_tempScale;
    private double[] m_dist;
    private int m_bins;
    private Map m_omap;
    private Object[] m_olist;
    private ColorMap m_cmap;
    private int[] m_palette;
    
    public DataColorAction(final String s, final String dataField, final int dataType, final String s2) {
        super(s, s2);
        this.m_scale = 0;
        this.m_bins = -1;
        this.m_cmap = new ColorMap(null, 0.0, 1.0);
        this.setDataType(dataType);
        this.setDataField(dataField);
    }
    
    public DataColorAction(final String s, final String dataField, final int dataType, final String s2, final int[] palette) {
        super(s, s2);
        this.m_scale = 0;
        this.m_bins = -1;
        this.m_cmap = new ColorMap(null, 0.0, 1.0);
        this.setDataType(dataType);
        this.setDataField(dataField);
        this.m_palette = palette;
    }
    
    public String getDataField() {
        return this.m_dataField;
    }
    
    public void setDataField(final String dataField) {
        this.m_dataField = dataField;
    }
    
    public int getDataType() {
        return this.m_type;
    }
    
    public void setDataType(final int type) {
        if (type < 0 || type >= 3) {
            throw new IllegalArgumentException("Unrecognized data type: " + type);
        }
        this.m_type = type;
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
    
    public void setDefaultColor(final int n) {
        throw new UnsupportedOperationException();
    }
    
    public void setOrdinalMap(final Object[] olist) {
        this.m_olist = olist;
        this.m_omap = new HashMap();
        for (int i = 0; i < olist.length; ++i) {
            this.m_omap.put(olist[i], new Integer(i));
        }
    }
    
    protected void setup() {
        final int n = 64;
        final int[] palette = this.m_palette;
        this.m_tempScale = this.m_scale;
        if (this.m_scale == 3 && this.m_bins <= 0) {
            Logger.getLogger(this.getClass().getName()).warning("Can't use quantile scale with no binning. Defaulting to linear scale. Set the bin value greater than zero to use a quantile scale.");
            this.m_scale = 0;
        }
        switch (this.m_type) {
            case 0:
            case 1: {
                this.m_dist = this.getDistribution();
                final int size = this.m_omap.size();
                this.m_cmap.setColorPalette((this.m_palette != null) ? this.m_palette : this.createPalette(size));
                this.m_cmap.setMinValue(this.m_dist[0]);
                this.m_cmap.setMaxValue(this.m_dist[1]);
            }
            case 2: {
                this.m_dist = this.getDistribution();
                final int n2 = (this.m_bins > 0) ? this.m_bins : n;
                this.m_cmap.setColorPalette((this.m_palette != null) ? this.m_palette : this.createPalette(n2));
                this.m_cmap.setMinValue(0.0);
                this.m_cmap.setMaxValue(1.0);
            }
            default: {}
        }
    }
    
    protected void finish() {
        this.m_scale = this.m_tempScale;
    }
    
    protected double[] getDistribution() {
        final TupleSet group = this.m_vis.getGroup(this.m_group);
        if (this.m_type != 2) {
            if (this.m_olist == null) {
                this.m_omap = DataLib.ordinalMap(group, this.m_dataField);
            }
            return new double[] { 0.0, this.m_omap.size() - 1 };
        }
        this.m_omap = null;
        if (this.m_scale == 3 && this.m_bins > 0) {
            return MathLib.quantiles(this.m_bins, DataLib.toDoubleArray(group.tuples(), this.m_dataField));
        }
        return new double[] { DataLib.min(group, this.m_dataField).getDouble(this.m_dataField), DataLib.max(group, this.m_dataField).getDouble(this.m_dataField) };
    }
    
    protected int[] createPalette(final int n) {
        switch (this.m_type) {
            case 0: {
                return ColorLib.getCategoryPalette(n);
            }
            default: {
                return ColorLib.getGrayscalePalette(n);
            }
        }
    }
    
    public int getColor(final VisualItem visualItem) {
        final Object lookup = this.lookup(visualItem);
        if (lookup != null) {
            if (lookup instanceof ColorAction) {
                return ((ColorAction)lookup).getColor(visualItem);
            }
            if (lookup instanceof Integer) {
                return (int)lookup;
            }
            Logger.getLogger(this.getClass().getName()).warning("Unrecognized Object from predicate chain.");
        }
        switch (this.m_type) {
            case 2: {
                return this.m_cmap.getColor(MathLib.interp(this.m_scale, visualItem.getDouble(this.m_dataField), this.m_dist));
            }
            default: {
                return this.m_cmap.getColor(this.m_omap.get(visualItem.get(this.m_dataField)));
            }
        }
    }
}
