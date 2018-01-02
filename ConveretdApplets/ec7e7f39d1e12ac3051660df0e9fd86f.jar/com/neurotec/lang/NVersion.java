// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.lang;

public final class NVersion
{
    private short value;
    
    public NVersion() {
        this.value = 0;
    }
    
    public NVersion(final short value) {
        this.value = value;
    }
    
    public NVersion(final int major, final int minor) {
        if (major < -128 || major > 127) {
            throw new IllegalArgumentException("major");
        }
        if (minor < -128 || minor > 127) {
            throw new IllegalArgumentException("minor");
        }
        this.value = (short)(major << 8 | minor);
    }
    
    public int getMajor() {
        return (byte)(this.value >> 8);
    }
    
    public int getMinor() {
        return (byte)this.value;
    }
    
    public short getValue() {
        return this.value;
    }
    
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = 31 * result + this.value;
        return result;
    }
    
    public boolean equals(final Object obj) {
        if (obj instanceof NVersion) {
            final NVersion value = (NVersion)obj;
            return this.value == value.value;
        }
        return false;
    }
    
    public String toString() {
        return String.format("%s.%s", this.getMajor(), this.getMinor());
    }
}
