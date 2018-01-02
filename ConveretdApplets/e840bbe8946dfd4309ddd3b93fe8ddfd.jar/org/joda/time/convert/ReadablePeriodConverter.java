// 
// Decompiled by Procyon v0.5.30
// 

package org.joda.time.convert;

import org.joda.time.PeriodType;
import org.joda.time.ReadablePeriod;
import org.joda.time.Chronology;
import org.joda.time.ReadWritablePeriod;

class ReadablePeriodConverter extends AbstractConverter implements PeriodConverter
{
    static final ReadablePeriodConverter INSTANCE;
    static /* synthetic */ Class class$org$joda$time$ReadablePeriod;
    
    public void setInto(final ReadWritablePeriod readWritablePeriod, final Object o, final Chronology chronology) {
        readWritablePeriod.setPeriod((ReadablePeriod)o);
    }
    
    public PeriodType getPeriodType(final Object o) {
        return ((ReadablePeriod)o).getPeriodType();
    }
    
    public Class getSupportedType() {
        return (ReadablePeriodConverter.class$org$joda$time$ReadablePeriod == null) ? (ReadablePeriodConverter.class$org$joda$time$ReadablePeriod = class$("org.joda.time.ReadablePeriod")) : ReadablePeriodConverter.class$org$joda$time$ReadablePeriod;
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
        INSTANCE = new ReadablePeriodConverter();
    }
}
