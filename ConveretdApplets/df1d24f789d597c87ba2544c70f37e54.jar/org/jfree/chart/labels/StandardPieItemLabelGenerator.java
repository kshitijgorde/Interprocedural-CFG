// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.labels;

import org.jfree.data.DatasetUtilities;
import java.text.MessageFormat;
import org.jfree.data.PieDataset;
import java.text.NumberFormat;
import java.io.Serializable;

public class StandardPieItemLabelGenerator implements PieToolTipGenerator, PieSectionLabelGenerator, Cloneable, Serializable
{
    public static final String DEFAULT_TOOLTIP_FORMAT = "{0}: ({1}, {2})";
    public static final String DEFAULT_SECTION_LABEL_FORMAT = "{0} = {1}";
    private String labelFormat;
    private NumberFormat numberFormat;
    private NumberFormat percentFormat;
    
    public StandardPieItemLabelGenerator() {
        this("{0} = {1}", NumberFormat.getNumberInstance(), NumberFormat.getPercentInstance());
    }
    
    public StandardPieItemLabelGenerator(final String labelFormat) {
        this(labelFormat, NumberFormat.getNumberInstance(), NumberFormat.getPercentInstance());
    }
    
    public StandardPieItemLabelGenerator(final String labelFormat, final NumberFormat numberFormat, final NumberFormat percentFormat) {
        if (labelFormat == null) {
            throw new IllegalArgumentException("Null 'itemLabelFormat' argument.");
        }
        if (numberFormat == null) {
            throw new IllegalArgumentException("Null 'numberFormat' argument.");
        }
        if (percentFormat == null) {
            throw new IllegalArgumentException("Null 'percentFormat' argument.");
        }
        this.labelFormat = labelFormat;
        this.numberFormat = numberFormat;
        this.percentFormat = percentFormat;
    }
    
    public NumberFormat getNumberFormat() {
        return this.numberFormat;
    }
    
    public NumberFormat getPercentFormat() {
        return this.percentFormat;
    }
    
    public String generateSectionLabel(final PieDataset dataset, final Comparable key) {
        String result = null;
        if (dataset != null) {
            final Object[] items = this.createItemArray(dataset, key);
            result = MessageFormat.format(this.labelFormat, items);
        }
        return result;
    }
    
    public String generateToolTip(final PieDataset dataset, final Comparable key) {
        return this.generateSectionLabel(dataset, key);
    }
    
    protected Object[] createItemArray(final PieDataset dataset, final Comparable key) {
        final Object[] result = { key.toString(), null, null };
        final Number value = dataset.getValue(key);
        result[1] = this.numberFormat.format(value);
        double percent = 0.0;
        if (value != null) {
            final double v = value.doubleValue();
            if (v > 0.0) {
                percent = v / DatasetUtilities.calculatePieDatasetTotal(dataset);
            }
        }
        result[2] = this.percentFormat.format(percent);
        return result;
    }
    
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj instanceof StandardPieItemLabelGenerator) {
            final StandardPieItemLabelGenerator generator = (StandardPieItemLabelGenerator)obj;
            return this.labelFormat.equals(generator.labelFormat) && this.numberFormat.equals(generator.numberFormat) && this.percentFormat.equals(generator.percentFormat);
        }
        return false;
    }
    
    public Object clone() throws CloneNotSupportedException {
        final StandardPieItemLabelGenerator clone = (StandardPieItemLabelGenerator)super.clone();
        if (this.numberFormat != null) {
            clone.numberFormat = (NumberFormat)this.numberFormat.clone();
        }
        return clone;
    }
}
