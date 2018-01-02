// 
// Decompiled by Procyon v0.5.30
// 

package org.joda.time.tz;

import java.util.SimpleTimeZone;
import java.util.TimeZone;
import org.joda.time.DateTimeZone;

public final class FixedDateTimeZone extends DateTimeZone
{
    private static final long serialVersionUID = -3513011772763289092L;
    private final String iNameKey;
    private final int iWallOffset;
    private final int iStandardOffset;
    
    public FixedDateTimeZone(final String s, final String iNameKey, final int iWallOffset, final int iStandardOffset) {
        super(s);
        this.iNameKey = iNameKey;
        this.iWallOffset = iWallOffset;
        this.iStandardOffset = iStandardOffset;
    }
    
    public String getNameKey(final long n) {
        return this.iNameKey;
    }
    
    public int getOffset(final long n) {
        return this.iWallOffset;
    }
    
    public int getStandardOffset(final long n) {
        return this.iStandardOffset;
    }
    
    public int getOffsetFromLocal(final long n) {
        return this.iWallOffset;
    }
    
    public boolean isFixed() {
        return true;
    }
    
    public long nextTransition(final long n) {
        return n;
    }
    
    public long previousTransition(final long n) {
        return n;
    }
    
    public TimeZone toTimeZone() {
        final String id = this.getID();
        if (id.length() == 6 && (id.startsWith("+") || id.startsWith("-"))) {
            return TimeZone.getTimeZone("GMT" + this.getID());
        }
        return new SimpleTimeZone(this.iWallOffset, this.getID());
    }
    
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o instanceof FixedDateTimeZone) {
            final FixedDateTimeZone fixedDateTimeZone = (FixedDateTimeZone)o;
            return this.getID().equals(fixedDateTimeZone.getID()) && this.iStandardOffset == fixedDateTimeZone.iStandardOffset && this.iWallOffset == fixedDateTimeZone.iWallOffset;
        }
        return false;
    }
    
    public int hashCode() {
        return this.getID().hashCode() + 37 * this.iStandardOffset + 31 * this.iWallOffset;
    }
}
