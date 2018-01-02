// 
// Decompiled by Procyon v0.5.30
// 

package org.joda.time.convert;

import java.util.Date;
import org.joda.time.Chronology;

final class DateConverter extends AbstractConverter implements InstantConverter, PartialConverter
{
    static final DateConverter INSTANCE;
    static /* synthetic */ Class class$java$util$Date;
    
    public long getInstantMillis(final Object o, final Chronology chronology) {
        return ((Date)o).getTime();
    }
    
    public Class getSupportedType() {
        return (DateConverter.class$java$util$Date == null) ? (DateConverter.class$java$util$Date = class$("java.util.Date")) : DateConverter.class$java$util$Date;
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        INSTANCE = new DateConverter();
    }
}
