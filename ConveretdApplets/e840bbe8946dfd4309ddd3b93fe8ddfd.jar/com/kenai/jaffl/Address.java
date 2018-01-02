// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.jaffl;

public class Address extends Number implements Comparable<Address>
{
    public static final int SIZE;
    public static final int SHIFT;
    public static final long MASK;
    protected final long address;
    
    public static final Address valueOf(final long address) {
        return new Address(address);
    }
    
    public Address(final long address) {
        this.address = (address & Address.MASK);
    }
    
    public Address(final Address address) {
        this.address = address.address;
    }
    
    public final int intValue() {
        return (int)this.address;
    }
    
    public final long longValue() {
        return this.address;
    }
    
    public final float floatValue() {
        return this.address;
    }
    
    public final double doubleValue() {
        return this.address;
    }
    
    public final long nativeAddress() {
        return this.address;
    }
    
    public final int hashCode() {
        return (int)(this.address ^ this.address >>> 32);
    }
    
    public final boolean equals(final Object obj) {
        return (obj instanceof Address && this.address == ((Address)obj).address) || (obj == null && this.address == 0L);
    }
    
    public String toString() {
        return this.getClass().getName() + String.format("[address=%x]", this.address);
    }
    
    public final int compareTo(final Address other) {
        return (this.address < other.address) ? -1 : ((this.address > other.address) ? 1 : 0);
    }
    
    public final boolean isNull() {
        return this.address == 0L;
    }
    
    static {
        SIZE = Platform.getPlatform().addressSize();
        SHIFT = ((Address.SIZE == 32) ? 2 : 3);
        MASK = ((Address.SIZE == 32) ? 4294967295L : -1L);
    }
}
