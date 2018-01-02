// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.constantine.platform;

import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.EnumSet;
import java.util.concurrent.ConcurrentHashMap;
import com.kenai.constantine.ConstantSet;
import com.kenai.constantine.Constant;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.Map;

class ConstantResolver<E extends Enum<E>>
{
    public static final String __UNKNOWN_CONSTANT__ = "__UNKNOWN_CONSTANT__";
    private final Object modLock;
    private final Class<E> enumType;
    private final Map<Integer, E> reverseLookupMap;
    private final AtomicInteger nextUnknown;
    private final int lastUnknown;
    private final boolean bitmask;
    private Constant[] cache;
    private volatile E[] valueCache;
    private volatile int cacheGuard;
    private volatile ConstantSet constants;
    
    private ConstantResolver(final Class<E> enumType) {
        this(enumType, Integer.MIN_VALUE, -2147482648, false);
    }
    
    private ConstantResolver(final Class<E> enumType, final int firstUnknown, final int lastUnknown, final boolean bitmask) {
        this.modLock = new Object();
        this.reverseLookupMap = new ConcurrentHashMap<Integer, E>();
        this.cache = null;
        this.valueCache = null;
        this.cacheGuard = 0;
        this.enumType = enumType;
        this.nextUnknown = new AtomicInteger(firstUnknown);
        this.lastUnknown = lastUnknown;
        this.bitmask = bitmask;
    }
    
    static final <T extends Enum<T>> ConstantResolver<T> getResolver(final Class<T> enumType) {
        return new ConstantResolver<T>(enumType);
    }
    
    static final <T extends Enum<T>> ConstantResolver<T> getResolver(final Class<T> enumType, final int first, final int last) {
        return new ConstantResolver<T>(enumType, first, last, false);
    }
    
    static final <T extends Enum<T>> ConstantResolver<T> getBitmaskResolver(final Class<T> enumType) {
        return new ConstantResolver<T>(enumType, 0, Integer.MIN_VALUE, true);
    }
    
    private Constant getConstant(final E e) {
        Constant c;
        if (this.cacheGuard != 0 && (c = this.cache[e.ordinal()]) != null) {
            return c;
        }
        synchronized (this.modLock) {
            if (this.cacheGuard != 0 && (c = this.cache[e.ordinal()]) != null) {
                return c;
            }
            final EnumSet<E> enums = EnumSet.allOf(this.enumType);
            final ConstantSet cset = this.getConstants();
            if (this.cache == null) {
                this.cache = new Constant[enums.size()];
            }
            long known = 0L;
            long unknown = 0L;
            for (final Enum v : enums) {
                c = cset.getConstant(v.name());
                if (c == null) {
                    if (this.bitmask) {
                        unknown |= 1L << v.ordinal();
                        c = new UnknownConstant(0, v.name());
                    }
                    else {
                        c = new UnknownConstant(this.nextUnknown.getAndAdd(1), v.name());
                    }
                }
                else if (this.bitmask) {
                    known |= c.value();
                }
                this.cache[v.ordinal()] = c;
            }
            if (this.bitmask) {
                long mask = 0L;
                while ((mask = Long.lowestOneBit(unknown)) != 0L) {
                    final int index = Long.numberOfTrailingZeros(mask);
                    final int sparebit = Long.numberOfTrailingZeros(Long.lowestOneBit(~known));
                    final int value = 1 << sparebit;
                    this.cache[index] = new UnknownConstant(value, this.cache[index].name());
                    known |= value;
                    unknown &= ~(1L << index);
                }
            }
            this.cacheGuard = 1;
        }
        return this.cache[e.ordinal()];
    }
    
    final int intValue(final E e) {
        return this.getConstant(e).value();
    }
    
    final String description(final E e) {
        return this.getConstant(e).toString();
    }
    
    final E valueOf(final int value) {
        E e;
        if (value >= 0 && value < 256 && this.valueCache != null && (e = (E)this.valueCache[value]) != null) {
            return e;
        }
        e = this.reverseLookupMap.get(value);
        if (e != null) {
            return e;
        }
        final Constant c = this.getConstants().getConstant(value);
        if (c != null) {
            try {
                e = Enum.valueOf(this.enumType, c.name());
                this.reverseLookupMap.put(value, e);
                if (c.value() >= 0 && c.value() < 256) {
                    E[] values = (E[])this.valueCache;
                    if (values == null) {
                        values = (E[])Array.newInstance(this.enumType, 256);
                    }
                    values[c.value()] = e;
                    this.valueCache = values;
                }
                return e;
            }
            catch (IllegalArgumentException ex) {}
        }
        return Enum.valueOf(this.enumType, "__UNKNOWN_CONSTANT__");
    }
    
    private final ConstantSet getConstants() {
        if (this.constants == null) {
            this.constants = ConstantSet.getConstantSet(this.enumType.getSimpleName());
            if (this.constants == null) {
                throw new RuntimeException("Could not load platform constants for " + this.enumType.getSimpleName());
            }
        }
        return this.constants;
    }
    
    private static final class UnknownConstant implements Constant
    {
        private final int value;
        private final String name;
        
        UnknownConstant(final int value, final String name) {
            this.value = value;
            this.name = name;
        }
        
        public int value() {
            return this.value;
        }
        
        public String name() {
            return this.name;
        }
        
        public String toString() {
            return this.name;
        }
    }
}
