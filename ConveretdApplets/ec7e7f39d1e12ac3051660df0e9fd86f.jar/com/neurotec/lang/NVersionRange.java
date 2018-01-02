// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.lang;

import com.neurotec.jna.NLibrary;
import com.sun.jna.Native;
import com.sun.jna.win32.W32APIOptions;

public final class NVersionRange
{
    static final NVersionRangeLibrary LIBRARY;
    private int value;
    
    public NVersionRange() {
        this.value = 0;
    }
    
    public NVersionRange(final int value) {
        this.value = value;
    }
    
    public NVersionRange(final NVersion from, final NVersion to) {
        this.value = (from.getValue() << 8 | to.getValue());
    }
    
    public NVersion getFrom() {
        return new NVersion((short)(this.value >> 16));
    }
    
    public NVersion getTo() {
        return new NVersion((short)this.value);
    }
    
    public boolean contains(final NVersion value) {
        return value.getValue() >= this.getFrom().getValue() && value.getValue() <= this.getTo().getValue();
    }
    
    public boolean contains(final NVersionRange value) {
        return value.getFrom().getValue() >= this.getFrom().getValue() && value.getTo().getValue() <= this.getTo().getValue();
    }
    
    public boolean intersectsWith(final NVersionRange value) {
        return value.getFrom().getValue() <= this.getTo().getValue() && this.getFrom().getValue() <= value.getTo().getValue();
    }
    
    public NVersionRange intersect(final NVersionRange value) {
        return this.intersect(this, value);
    }
    
    public NVersionRange intersect(final NVersionRange value1, final NVersionRange value2) {
        return new NVersionRange(NVersionRange.LIBRARY.NVersionRangeIntersect(value1.value, value2.value));
    }
    
    public boolean equals(final Object obj) {
        if (obj instanceof NVersionRange) {
            final NVersionRange value = (NVersionRange)obj;
            return this.value == value.value;
        }
        return false;
    }
    
    public int hashCode() {
        return super.hashCode();
    }
    
    public String toString() {
        final NVersion from = this.getFrom();
        final NVersion to = this.getTo();
        return from.equals(to) ? from.toString() : String.format("%s-%s", from, to);
    }
    
    static {
        LIBRARY = (NVersionRangeLibrary)Native.loadLibrary("NCore", NVersionRangeLibrary.class, W32APIOptions.UNICODE_OPTIONS);
    }
    
    interface NVersionRangeLibrary extends NLibrary
    {
        int NVersionRangeIntersect(final int p0, final int p1);
    }
}
