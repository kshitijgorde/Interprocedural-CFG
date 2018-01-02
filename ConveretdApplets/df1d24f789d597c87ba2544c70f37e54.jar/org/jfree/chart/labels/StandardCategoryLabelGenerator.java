// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.labels;

import org.jfree.data.CategoryDataset;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.io.Serializable;
import org.jfree.util.PublicCloneable;

public class StandardCategoryLabelGenerator extends AbstractCategoryItemLabelGenerator implements CategoryLabelGenerator, Cloneable, PublicCloneable, Serializable
{
    public static final String DEFAULT_LABEL_FORMAT_STRING = "{2}";
    
    public StandardCategoryLabelGenerator() {
        super("{2}", NumberFormat.getInstance());
    }
    
    public StandardCategoryLabelGenerator(final String labelFormat, final NumberFormat formatter) {
        super(labelFormat, formatter);
    }
    
    public StandardCategoryLabelGenerator(final String labelFormat, final DateFormat formatter) {
        super(labelFormat, formatter);
    }
    
    public String generateLabel(final CategoryDataset dataset, final int row, final int column) {
        return this.generateLabelString(dataset, row, column);
    }
}
