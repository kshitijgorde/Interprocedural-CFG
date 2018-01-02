// 
// Decompiled by Procyon v0.5.30
// 

package org.joda.time.chrono;

import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import org.joda.time.field.RemainderDateTimeField;
import org.joda.time.field.DividedDateTimeField;
import org.joda.time.DateTimeFieldType;
import org.joda.time.Chronology;
import org.joda.time.DateTimeZone;
import java.util.Map;

public final class ISOChronology extends AssembledChronology
{
    private static final long serialVersionUID = -6212696554273812441L;
    private static final ISOChronology INSTANCE_UTC;
    private static final int FAST_CACHE_SIZE = 64;
    private static final ISOChronology[] cFastCache;
    private static final Map cCache;
    
    public static ISOChronology getInstanceUTC() {
        return ISOChronology.INSTANCE_UTC;
    }
    
    public static ISOChronology getInstance() {
        return getInstance(DateTimeZone.getDefault());
    }
    
    public static ISOChronology getInstance(DateTimeZone default1) {
        if (default1 == null) {
            default1 = DateTimeZone.getDefault();
        }
        final int n = System.identityHashCode(default1) & 0x3F;
        ISOChronology isoChronology = ISOChronology.cFastCache[n];
        if (isoChronology != null && isoChronology.getZone() == default1) {
            return isoChronology;
        }
        synchronized (ISOChronology.cCache) {
            isoChronology = ISOChronology.cCache.get(default1);
            if (isoChronology == null) {
                isoChronology = new ISOChronology(ZonedChronology.getInstance(ISOChronology.INSTANCE_UTC, default1));
                ISOChronology.cCache.put(default1, isoChronology);
            }
        }
        return ISOChronology.cFastCache[n] = isoChronology;
    }
    
    private ISOChronology(final Chronology chronology) {
        super(chronology, null);
    }
    
    public Chronology withUTC() {
        return ISOChronology.INSTANCE_UTC;
    }
    
    public Chronology withZone(DateTimeZone default1) {
        if (default1 == null) {
            default1 = DateTimeZone.getDefault();
        }
        if (default1 == this.getZone()) {
            return this;
        }
        return getInstance(default1);
    }
    
    public String toString() {
        String string = "ISOChronology";
        final DateTimeZone zone = this.getZone();
        if (zone != null) {
            string = string + '[' + zone.getID() + ']';
        }
        return string;
    }
    
    protected void assemble(final Fields fields) {
        if (this.getBase().getZone() == DateTimeZone.UTC) {
            fields.centuryOfEra = new DividedDateTimeField(ISOYearOfEraDateTimeField.INSTANCE, DateTimeFieldType.centuryOfEra(), 100);
            fields.yearOfCentury = new RemainderDateTimeField((DividedDateTimeField)fields.centuryOfEra, DateTimeFieldType.yearOfCentury());
            fields.weekyearOfCentury = new RemainderDateTimeField((DividedDateTimeField)fields.centuryOfEra, DateTimeFieldType.weekyearOfCentury());
            fields.centuries = fields.centuryOfEra.getDurationField();
        }
    }
    
    public boolean equals(final Object o) {
        return super.equals(o);
    }
    
    public int hashCode() {
        return "ISO".hashCode() * 11 + this.getZone().hashCode();
    }
    
    private Object writeReplace() {
        return new Stub(this.getZone());
    }
    
    static {
        cCache = new HashMap();
        cFastCache = new ISOChronology[64];
        INSTANCE_UTC = new ISOChronology(GregorianChronology.getInstanceUTC());
        ISOChronology.cCache.put(DateTimeZone.UTC, ISOChronology.INSTANCE_UTC);
    }
    
    private static final class Stub implements Serializable
    {
        private static final long serialVersionUID = -6212696554273812441L;
        private transient DateTimeZone iZone;
        
        Stub(final DateTimeZone iZone) {
            this.iZone = iZone;
        }
        
        private Object readResolve() {
            return ISOChronology.getInstance(this.iZone);
        }
        
        private void writeObject(final ObjectOutputStream objectOutputStream) throws IOException {
            objectOutputStream.writeObject(this.iZone);
        }
        
        private void readObject(final ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
            this.iZone = (DateTimeZone)objectInputStream.readObject();
        }
    }
}
