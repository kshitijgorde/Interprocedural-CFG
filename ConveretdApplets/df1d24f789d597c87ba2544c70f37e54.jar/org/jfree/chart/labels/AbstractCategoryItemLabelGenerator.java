// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.labels;

import org.jfree.util.ObjectUtils;
import java.text.MessageFormat;
import org.jfree.data.CategoryDataset;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.io.Serializable;
import org.jfree.util.PublicCloneable;

public abstract class AbstractCategoryItemLabelGenerator implements PublicCloneable, Cloneable, Serializable
{
    private String labelFormat;
    private NumberFormat numberFormat;
    private DateFormat dateFormat;
    
    protected AbstractCategoryItemLabelGenerator(final String labelFormat, final NumberFormat formatter) {
        if (labelFormat == null) {
            throw new IllegalArgumentException("Null 'labelFormat' argument.");
        }
        if (formatter == null) {
            throw new IllegalArgumentException("Null 'formatter' argument.");
        }
        this.labelFormat = labelFormat;
        this.numberFormat = formatter;
        this.dateFormat = null;
    }
    
    protected AbstractCategoryItemLabelGenerator(final String labelFormat, final DateFormat formatter) {
        if (labelFormat == null) {
            throw new IllegalArgumentException("Null 'labelFormat' argument.");
        }
        if (formatter == null) {
            throw new IllegalArgumentException("Null 'formatter' argument.");
        }
        this.labelFormat = labelFormat;
        this.numberFormat = null;
        this.dateFormat = formatter;
    }
    
    public String getLabelFormat() {
        return this.labelFormat;
    }
    
    public NumberFormat getNumberFormat() {
        return this.numberFormat;
    }
    
    public DateFormat getDateFormat() {
        return this.dateFormat;
    }
    
    protected String generateLabelString(final CategoryDataset dataset, final int row, final int column) {
        if (dataset == null) {
            throw new IllegalArgumentException("Null 'dataset' argument.");
        }
        String result = null;
        final Object[] items = this.createItemArray(dataset, row, column);
        result = MessageFormat.format(this.labelFormat, items);
        return result;
    }
    
    protected Object[] createItemArray(final CategoryDataset dataset, final int row, final int column) {
        final Object[] result = { dataset.getRowKey(row).toString(), dataset.getColumnKey(column).toString(), null };
        final Number value = dataset.getValue(row, column);
        if (this.numberFormat != null) {
            result[2] = this.numberFormat.format(value);
        }
        else if (this.dateFormat != null) {
            result[2] = this.dateFormat.format(value);
        }
        return result;
    }
    
    public boolean equals(final Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof AbstractCategoryItemLabelGenerator) {
            final AbstractCategoryItemLabelGenerator g = (AbstractCategoryItemLabelGenerator)obj;
            final boolean b0 = this.labelFormat.equals(g.labelFormat);
            final boolean b2 = ObjectUtils.equal(this.dateFormat, g.dateFormat);
            final boolean b3 = ObjectUtils.equal(this.numberFormat, g.numberFormat);
            return b0 && b2 && b3;
        }
        return false;
    }
    
    public Object clone() throws CloneNotSupportedException {
        final AbstractCategoryItemLabelGenerator clone = (AbstractCategoryItemLabelGenerator)super.clone();
        if (this.numberFormat != null) {
            clone.numberFormat = (NumberFormat)this.numberFormat.clone();
        }
        if (this.dateFormat != null) {
            clone.dateFormat = (DateFormat)this.dateFormat.clone();
        }
        return clone;
    }
}
