// 
// Decompiled by Procyon v0.5.30
// 

package org.joda.time.convert;

import org.joda.time.Chronology;

class LongConverter extends AbstractConverter implements InstantConverter, PartialConverter, DurationConverter
{
    static final LongConverter INSTANCE;
    static /* synthetic */ Class class$java$lang$Long;
    
    public long getInstantMillis(final Object o, final Chronology chronology) {
        return (long)o;
    }
    
    public long getDurationMillis(final Object o) {
        return (long)o;
    }
    
    public Class getSupportedType() {
        return (LongConverter.class$java$lang$Long == null) ? (LongConverter.class$java$lang$Long = class$("java.lang.Long")) : LongConverter.class$java$lang$Long;
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
        INSTANCE = new LongConverter();
    }
}
