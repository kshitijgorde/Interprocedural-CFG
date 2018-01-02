// 
// Decompiled by Procyon v0.5.30
// 

package org.joda.time.convert;

import org.joda.time.DateTimeUtils;
import org.joda.time.chrono.ISOChronology;
import org.joda.time.ReadableInstant;
import org.joda.time.Chronology;
import org.joda.time.DateTimeZone;

class ReadableInstantConverter extends AbstractConverter implements InstantConverter, PartialConverter
{
    static final ReadableInstantConverter INSTANCE;
    static /* synthetic */ Class class$org$joda$time$ReadableInstant;
    
    public Chronology getChronology(final Object o, final DateTimeZone dateTimeZone) {
        Chronology chronology = ((ReadableInstant)o).getChronology();
        if (chronology == null) {
            return ISOChronology.getInstance(dateTimeZone);
        }
        if (chronology.getZone() != dateTimeZone) {
            chronology = chronology.withZone(dateTimeZone);
            if (chronology == null) {
                return ISOChronology.getInstance(dateTimeZone);
            }
        }
        return chronology;
    }
    
    public Chronology getChronology(final Object o, Chronology chronology) {
        if (chronology == null) {
            chronology = ((ReadableInstant)o).getChronology();
            chronology = DateTimeUtils.getChronology(chronology);
        }
        return chronology;
    }
    
    public long getInstantMillis(final Object o, final Chronology chronology) {
        return ((ReadableInstant)o).getMillis();
    }
    
    public Class getSupportedType() {
        return (ReadableInstantConverter.class$org$joda$time$ReadableInstant == null) ? (ReadableInstantConverter.class$org$joda$time$ReadableInstant = class$("org.joda.time.ReadableInstant")) : ReadableInstantConverter.class$org$joda$time$ReadableInstant;
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
        INSTANCE = new ReadableInstantConverter();
    }
}
