// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.labels;

import org.jfree.data.CategoryDataset;
import java.text.DateFormat;
import java.text.NumberFormat;

public class StandardCategoryToolTipGenerator extends AbstractCategoryItemLabelGenerator implements CategoryToolTipGenerator
{
    public static final String DEFAULT_TOOL_TIP_FORMAT_STRING = "({0}, {1}) = {2}";
    
    public StandardCategoryToolTipGenerator() {
        super("({0}, {1}) = {2}", NumberFormat.getInstance());
    }
    
    public StandardCategoryToolTipGenerator(final String labelFormat, final NumberFormat formatter) {
        super(labelFormat, formatter);
    }
    
    public StandardCategoryToolTipGenerator(final String labelFormat, final DateFormat formatter) {
        super(labelFormat, formatter);
    }
    
    public String generateToolTip(final CategoryDataset dataset, final int row, final int column) {
        return this.generateLabelString(dataset, row, column);
    }
}
