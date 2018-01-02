// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.labels;

import org.jfree.data.XYDataset;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.io.Serializable;
import org.jfree.util.PublicCloneable;

public class StandardXYLabelGenerator extends AbstractXYItemLabelGenerator implements XYLabelGenerator, Cloneable, PublicCloneable, Serializable
{
    public static final String DEFAULT_ITEM_LABEL_FORMAT = "{2}";
    
    public StandardXYLabelGenerator() {
        this("{2}", NumberFormat.getNumberInstance(), NumberFormat.getNumberInstance());
    }
    
    public StandardXYLabelGenerator(final String formatString, final NumberFormat xFormat, final NumberFormat yFormat) {
        super(formatString, xFormat, yFormat);
    }
    
    public StandardXYLabelGenerator(final String formatString, final DateFormat xFormat, final NumberFormat yFormat) {
        super(formatString, xFormat, yFormat);
    }
    
    public StandardXYLabelGenerator(final String formatString, final DateFormat xFormat, final DateFormat yFormat) {
        super(formatString, xFormat, yFormat);
    }
    
    public String generateLabel(final XYDataset dataset, final int series, final int item) {
        return this.generateLabelString(dataset, series, item);
    }
    
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
    
    public boolean equals(final Object obj) {
        return obj == this || (obj instanceof StandardXYLabelGenerator && super.equals(obj));
    }
}
