// 
// Decompiled by Procyon v0.5.30
// 

package blackmagic.swing;

import javax.swing.text.DocumentFilter;
import java.text.NumberFormat;
import java.text.DecimalFormat;
import javax.swing.text.NumberFormatter;

class PercentFormatter extends NumberFormatter
{
    private PercentFilter vFilter;
    
    public PercentFormatter(final DecimalFormat decimalFormat) {
        super(decimalFormat);
        this.vFilter = new PercentFilter();
    }
    
    protected DocumentFilter getDocumentFilter() {
        return this.vFilter;
    }
}
