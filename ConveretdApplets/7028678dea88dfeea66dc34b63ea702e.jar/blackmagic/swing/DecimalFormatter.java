// 
// Decompiled by Procyon v0.5.30
// 

package blackmagic.swing;

import javax.swing.text.DocumentFilter;
import java.text.NumberFormat;
import java.text.DecimalFormat;
import javax.swing.text.NumberFormatter;

class DecimalFormatter extends NumberFormatter
{
    private DecimalFilter vFilter;
    
    public DecimalFormatter(final DecimalFormat decimalFormat) {
        super(decimalFormat);
        this.vFilter = new DecimalFilter();
    }
    
    protected DocumentFilter getDocumentFilter() {
        return this.vFilter;
    }
}
