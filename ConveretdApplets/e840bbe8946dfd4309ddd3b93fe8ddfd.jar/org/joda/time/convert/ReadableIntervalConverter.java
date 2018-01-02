// 
// Decompiled by Procyon v0.5.30
// 

package org.joda.time.convert;

import org.joda.time.ReadWritableInterval;
import org.joda.time.ReadablePeriod;
import org.joda.time.DateTimeUtils;
import org.joda.time.Chronology;
import org.joda.time.ReadWritablePeriod;
import org.joda.time.ReadableInterval;

class ReadableIntervalConverter extends AbstractConverter implements IntervalConverter, DurationConverter, PeriodConverter
{
    static final ReadableIntervalConverter INSTANCE;
    static /* synthetic */ Class class$org$joda$time$ReadableInterval;
    
    public long getDurationMillis(final Object o) {
        return ((ReadableInterval)o).toDurationMillis();
    }
    
    public void setInto(final ReadWritablePeriod readWritablePeriod, final Object o, Chronology chronology) {
        final ReadableInterval readableInterval = (ReadableInterval)o;
        chronology = ((chronology != null) ? chronology : DateTimeUtils.getIntervalChronology(readableInterval));
        final int[] value = chronology.get(readWritablePeriod, readableInterval.getStartMillis(), readableInterval.getEndMillis());
        for (int i = 0; i < value.length; ++i) {
            readWritablePeriod.setValue(i, value[i]);
        }
    }
    
    public boolean isReadableInterval(final Object o, final Chronology chronology) {
        return true;
    }
    
    public void setInto(final ReadWritableInterval readWritableInterval, final Object o, final Chronology chronology) {
        final ReadableInterval interval = (ReadableInterval)o;
        readWritableInterval.setInterval(interval);
        if (chronology != null) {
            readWritableInterval.setChronology(chronology);
        }
        else {
            readWritableInterval.setChronology(interval.getChronology());
        }
    }
    
    public Class getSupportedType() {
        return (ReadableIntervalConverter.class$org$joda$time$ReadableInterval == null) ? (ReadableIntervalConverter.class$org$joda$time$ReadableInterval = class$("org.joda.time.ReadableInterval")) : ReadableIntervalConverter.class$org$joda$time$ReadableInterval;
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
        INSTANCE = new ReadableIntervalConverter();
    }
}
