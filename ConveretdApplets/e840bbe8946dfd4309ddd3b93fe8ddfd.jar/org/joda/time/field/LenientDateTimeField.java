// 
// Decompiled by Procyon v0.5.30
// 

package org.joda.time.field;

import org.joda.time.DateTimeField;
import org.joda.time.Chronology;

public class LenientDateTimeField extends DelegatedDateTimeField
{
    private static final long serialVersionUID = 8714085824173290599L;
    private final Chronology iBase;
    
    public static DateTimeField getInstance(DateTimeField wrappedField, final Chronology chronology) {
        if (wrappedField == null) {
            return null;
        }
        if (wrappedField instanceof StrictDateTimeField) {
            wrappedField = ((StrictDateTimeField)wrappedField).getWrappedField();
        }
        if (wrappedField.isLenient()) {
            return wrappedField;
        }
        return new LenientDateTimeField(wrappedField, chronology);
    }
    
    protected LenientDateTimeField(final DateTimeField dateTimeField, final Chronology iBase) {
        super(dateTimeField);
        this.iBase = iBase;
    }
    
    public final boolean isLenient() {
        return true;
    }
    
    public long set(final long n, final int n2) {
        return this.iBase.getZone().convertLocalToUTC(this.getType().getField(this.iBase.withUTC()).add(this.iBase.getZone().convertUTCToLocal(n), FieldUtils.safeSubtract(n2, this.get(n))), false);
    }
}
