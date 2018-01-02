// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.parser;

import java.text.DateFormat;

public class DateTimeParser extends DateParser
{
    public DateTimeParser() {
        this(DateFormat.getDateTimeInstance(3, 3));
    }
    
    public DateTimeParser(final DateFormat dateFormat) {
        super(dateFormat);
    }
}
