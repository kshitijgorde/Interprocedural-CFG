// 
// Decompiled by Procyon v0.5.30
// 

package org.joda.time.convert;

import org.joda.time.ReadablePeriod;
import org.joda.time.DateTimeUtils;
import org.joda.time.Chronology;
import org.joda.time.ReadWritablePeriod;
import org.joda.time.ReadableDuration;

class ReadableDurationConverter extends AbstractConverter implements DurationConverter, PeriodConverter
{
    static final ReadableDurationConverter INSTANCE;
    static /* synthetic */ Class class$org$joda$time$ReadableDuration;
    
    public long getDurationMillis(final Object o) {
        return ((ReadableDuration)o).getMillis();
    }
    
    public void setInto(final ReadWritablePeriod readWritablePeriod, final Object o, Chronology chronology) {
        final ReadableDuration readableDuration = (ReadableDuration)o;
        chronology = DateTimeUtils.getChronology(chronology);
        final int[] value = chronology.get(readWritablePeriod, readableDuration.getMillis());
        for (int i = 0; i < value.length; ++i) {
            readWritablePeriod.setValue(i, value[i]);
        }
    }
    
    public Class getSupportedType() {
        return (ReadableDurationConverter.class$org$joda$time$ReadableDuration == null) ? (ReadableDurationConverter.class$org$joda$time$ReadableDuration = class$("org.joda.time.ReadableDuration")) : ReadableDurationConverter.class$org$joda$time$ReadableDuration;
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
        INSTANCE = new ReadableDurationConverter();
    }
}
