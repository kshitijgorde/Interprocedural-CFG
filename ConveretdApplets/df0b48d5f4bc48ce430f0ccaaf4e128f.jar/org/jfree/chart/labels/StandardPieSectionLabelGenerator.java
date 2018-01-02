// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.labels;

import org.jfree.data.general.PieDataset;
import java.text.AttributedString;
import java.text.NumberFormat;
import org.jfree.util.ObjectList;
import java.io.Serializable;

public class StandardPieSectionLabelGenerator extends AbstractPieItemLabelGenerator implements PieSectionLabelGenerator, Cloneable, Serializable
{
    private static final long serialVersionUID = 3064190563760203668L;
    public static final String DEFAULT_SECTION_LABEL_FORMAT = "{0}";
    private ObjectList attributedLabels;
    
    public StandardPieSectionLabelGenerator() {
        this("{0}", NumberFormat.getNumberInstance(), NumberFormat.getPercentInstance());
    }
    
    public StandardPieSectionLabelGenerator(final String labelFormat) {
        this(labelFormat, NumberFormat.getNumberInstance(), NumberFormat.getPercentInstance());
    }
    
    public StandardPieSectionLabelGenerator(final String labelFormat, final NumberFormat numberFormat, final NumberFormat percentFormat) {
        super(labelFormat, numberFormat, percentFormat);
        this.attributedLabels = new ObjectList();
    }
    
    public AttributedString getAttributedLabel(final int section) {
        return (AttributedString)this.attributedLabels.get(section);
    }
    
    public void setAttributedLabel(final int section, final AttributedString label) {
        this.attributedLabels.set(section, label);
    }
    
    public String generateSectionLabel(final PieDataset dataset, final Comparable key) {
        return super.generateSectionLabel(dataset, key);
    }
    
    public AttributedString generateAttributedSectionLabel(final PieDataset dataset, final Comparable key) {
        return this.getAttributedLabel(dataset.getIndex(key));
    }
    
    public boolean equals(final Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof StandardPieSectionLabelGenerator)) {
            return false;
        }
        final StandardPieSectionLabelGenerator that = (StandardPieSectionLabelGenerator)obj;
        return this.attributedLabels.equals(that.attributedLabels) && super.equals(obj);
    }
    
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
