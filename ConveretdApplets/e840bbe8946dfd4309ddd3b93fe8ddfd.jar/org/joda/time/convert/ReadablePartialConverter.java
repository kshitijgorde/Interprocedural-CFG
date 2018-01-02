// 
// Decompiled by Procyon v0.5.30
// 

package org.joda.time.convert;

import org.joda.time.DateTimeUtils;
import org.joda.time.ReadablePartial;
import org.joda.time.Chronology;
import org.joda.time.DateTimeZone;

class ReadablePartialConverter extends AbstractConverter implements PartialConverter
{
    static final ReadablePartialConverter INSTANCE;
    static /* synthetic */ Class class$org$joda$time$ReadablePartial;
    
    public Chronology getChronology(final Object o, final DateTimeZone dateTimeZone) {
        return this.getChronology(o, (Chronology)null).withZone(dateTimeZone);
    }
    
    public Chronology getChronology(final Object o, Chronology chronology) {
        if (chronology == null) {
            chronology = ((ReadablePartial)o).getChronology();
            chronology = DateTimeUtils.getChronology(chronology);
        }
        return chronology;
    }
    
    public int[] getPartialValues(final ReadablePartial readablePartial, final Object o, final Chronology chronology) {
        final ReadablePartial readablePartial2 = (ReadablePartial)o;
        final int size = readablePartial.size();
        final int[] array = new int[size];
        for (int i = 0; i < size; ++i) {
            array[i] = readablePartial2.get(readablePartial.getFieldType(i));
        }
        chronology.validate(readablePartial, array);
        return array;
    }
    
    public Class getSupportedType() {
        return (ReadablePartialConverter.class$org$joda$time$ReadablePartial == null) ? (ReadablePartialConverter.class$org$joda$time$ReadablePartial = class$("org.joda.time.ReadablePartial")) : ReadablePartialConverter.class$org$joda$time$ReadablePartial;
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
        INSTANCE = new ReadablePartialConverter();
    }
}
