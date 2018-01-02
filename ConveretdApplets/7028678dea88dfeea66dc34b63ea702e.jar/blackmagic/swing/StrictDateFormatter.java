// 
// Decompiled by Procyon v0.5.30
// 

package blackmagic.swing;

import javax.swing.text.DocumentFilter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.text.DateFormatter;

class StrictDateFormatter extends DateFormatter
{
    private static final DateFilter vFilter;
    
    public StrictDateFormatter(final SimpleDateFormat simpleDateFormat) {
        super(simpleDateFormat);
    }
    
    protected DocumentFilter getDocumentFilter() {
        return StrictDateFormatter.vFilter;
    }
    
    static {
        vFilter = new DateFilter();
    }
}
