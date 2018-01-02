// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.labels;

import java.text.FieldPosition;
import java.util.Date;
import java.text.SimpleDateFormat;
import org.jfree.data.ContourDataset;
import java.text.DecimalFormat;
import java.io.Serializable;

public class StandardContourToolTipGenerator implements ContourToolTipGenerator, Serializable
{
    private DecimalFormat valueForm;
    
    public StandardContourToolTipGenerator() {
        this.valueForm = new DecimalFormat("##.###");
    }
    
    public String generateToolTip(final ContourDataset data, final int item) {
        final Number x = data.getXValue(0, item);
        final Number y = data.getYValue(0, item);
        final Number z = data.getZValue(0, item);
        String xString = null;
        if (data.isDateAxis(0)) {
            final SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
            StringBuffer strbuf = new StringBuffer();
            strbuf = formatter.format(new Date(x.longValue()), strbuf, new FieldPosition(0));
            xString = strbuf.toString();
        }
        else {
            xString = this.valueForm.format(x.doubleValue());
        }
        if (z != null) {
            return "X: " + xString + ", Y: " + this.valueForm.format(y.doubleValue()) + ", Z: " + this.valueForm.format(z.doubleValue());
        }
        return "X: " + xString + ", Y: " + this.valueForm.format(y.doubleValue()) + ", Z: no data";
    }
    
    public boolean equals(final Object o) {
        if (o == null) {
            return false;
        }
        if (o == this) {
            return true;
        }
        if (o instanceof StandardContourToolTipGenerator) {
            final StandardContourToolTipGenerator generator = (StandardContourToolTipGenerator)o;
            if (this.valueForm != null) {
                return this.valueForm.equals(generator.valueForm);
            }
        }
        return false;
    }
}
