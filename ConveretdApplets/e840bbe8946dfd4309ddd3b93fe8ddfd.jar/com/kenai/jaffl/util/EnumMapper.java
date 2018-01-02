// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.jaffl.util;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;

public class EnumMapper
{
    private final Map<Class<? extends Enum>, Entry> enums;
    
    public EnumMapper() {
        this.enums = new ConcurrentHashMap<Class<? extends Enum>, Entry>();
    }
    
    public static EnumMapper getInstance() {
        return SingletonHolder.INSTANCE;
    }
    
    private static final int getIntegerValue(final Enum e) {
        if (e instanceof IntegerEnum) {
            return ((IntegerEnum)e).intValue();
        }
        return e.ordinal();
    }
    
    private Entry createEntry(final Class<? extends Enum> enumClass) {
        final Entry entry = new Entry();
        for (final Enum e : (Enum[])enumClass.getEnumConstants()) {
            final int intValue = getIntegerValue(e);
            entry.enumMap.put(intValue, e);
            entry.valueMap.put(e, intValue);
        }
        return entry;
    }
    
    private Entry getEntry(final Class<? extends Enum> enumClass) {
        Entry entry = this.enums.get(enumClass);
        if (entry == null) {
            synchronized (enumClass) {
                if (!this.enums.containsKey(enumClass)) {
                    this.enums.put(enumClass, entry = this.createEntry(enumClass));
                }
                else {
                    entry = this.enums.get(enumClass);
                }
            }
        }
        return entry;
    }
    
    public int intValue(final Enum value) {
        return getIntegerValue(value);
    }
    
    public <E extends Enum<E>> E valueOf(final int value, final Class<E> enumClass) {
        final Enum e = this.getEntry(enumClass).enumMap.get(value);
        if (e == null) {
            try {
                return Enum.valueOf(enumClass, "__UNKNOWN_NATIVE_VALUE");
            }
            catch (IllegalArgumentException ex) {
                throw new IllegalArgumentException("No known Enum mapping for value " + value + " of type " + enumClass.getName());
            }
        }
        return enumClass.cast(e);
    }
    
    private static final class Entry
    {
        Map<Integer, Enum> enumMap;
        Map<Enum, Integer> valueMap;
        
        private Entry() {
            this.enumMap = new HashMap<Integer, Enum>();
            this.valueMap = new HashMap<Enum, Integer>();
        }
    }
    
    private static final class SingletonHolder
    {
        private static final EnumMapper INSTANCE;
        
        static {
            INSTANCE = new EnumMapper();
        }
    }
    
    public interface IntegerEnum
    {
        int intValue();
    }
}
