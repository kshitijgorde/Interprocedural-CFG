// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.util;

import java.io.Serializable;

public final class HashCode implements Serializable, Cloneable, Comparable
{
    static final long serialVersionUID = 2391396931740264021L;
    private static final int NULL_HASHCODE = 0;
    private static final int TRUE_HASHCODE = 1231;
    private static final int FALSE_HASHCODE = 1237;
    private int value;
    
    public HashCode(final int value) {
        this.value = value;
    }
    
    public HashCode() {
        this(0);
    }
    
    public HashCode add(final boolean b) {
        this.value ^= generate(b);
        return this;
    }
    
    public HashCode add(final byte n) {
        this.value ^= n;
        return this;
    }
    
    public HashCode add(final char n) {
        this.value ^= n;
        return this;
    }
    
    public HashCode add(final short n) {
        this.value ^= n;
        return this;
    }
    
    public HashCode add(final int n) {
        this.value ^= n;
        return this;
    }
    
    public HashCode add(final long n) {
        this.value ^= generate(n);
        return this;
    }
    
    public HashCode add(final float f) {
        this.value ^= generate(f);
        return this;
    }
    
    public HashCode add(final double f) {
        this.value ^= generate(f);
        return this;
    }
    
    public HashCode add(final Object obj) {
        this.value ^= generate(obj);
        return this;
    }
    
    public int hashCode() {
        return this.value;
    }
    
    public int compareTo(final int other) {
        return (this.value < other) ? -1 : ((this.value == other) ? 0 : 1);
    }
    
    public int compareTo(final Object obj) throws ClassCastException {
        final HashCode hashCode = (HashCode)obj;
        return this.compareTo(hashCode.value);
    }
    
    public boolean equals(final Object obj) {
        return obj == this || (obj != null && obj.getClass() == this.getClass() && this.value == ((HashCode)obj).value);
    }
    
    public String toString() {
        return String.valueOf(this.value);
    }
    
    public Object clone() {
        try {
            return super.clone();
        }
        catch (CloneNotSupportedException e) {
            throw new InternalError();
        }
    }
    
    public static int generate(final boolean value) {
        return value ? 1231 : 1237;
    }
    
    public static int generate(final long value) {
        return (int)(value ^ value >> 32);
    }
    
    public static int generate(final double value) {
        return generate(Double.doubleToLongBits(value));
    }
    
    public static int generate(final float value) {
        return Float.floatToIntBits(value);
    }
    
    public static int generate(final byte[] bytes) {
        int hashcode = 0;
        for (int i = 0; i < bytes.length; ++i) {
            hashcode <<= 1;
            hashcode ^= bytes[i];
        }
        return hashcode;
    }
    
    public static int generate(final Object[] array, final boolean deep) {
        int hashcode = 0;
        for (int i = 0; i < array.length; ++i) {
            if (deep && array[i] instanceof Object[]) {
                hashcode ^= generate((Object[])array[i], true);
            }
            else {
                hashcode ^= array[i].hashCode();
            }
        }
        return hashcode;
    }
    
    public static int generate(final Object[] array) {
        return generate(array, false);
    }
    
    public static int generate(final Object obj) {
        if (obj != null) {
            return obj.hashCode();
        }
        return 0;
    }
}
