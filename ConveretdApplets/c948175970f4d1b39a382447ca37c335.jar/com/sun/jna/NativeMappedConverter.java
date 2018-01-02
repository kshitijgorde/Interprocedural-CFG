// 
// Decompiled by Procyon v0.5.30
// 

package com.sun.jna;

import java.util.WeakHashMap;
import java.util.Map;

public class NativeMappedConverter implements TypeConverter
{
    private static Map converters;
    private final Class type;
    private final Class nativeType;
    private final NativeMapped instance;
    static /* synthetic */ Class class$com$sun$jna$NativeMapped;
    static /* synthetic */ Class class$com$sun$jna$Pointer;
    
    public static NativeMappedConverter getInstance(final Class cls) {
        synchronized (NativeMappedConverter.converters) {
            NativeMappedConverter nmc = NativeMappedConverter.converters.get(cls);
            if (nmc == null) {
                nmc = new NativeMappedConverter(cls);
                NativeMappedConverter.converters.put(cls, nmc);
            }
            return nmc;
        }
    }
    
    public NativeMappedConverter(final Class type) {
        if (!((NativeMappedConverter.class$com$sun$jna$NativeMapped == null) ? (NativeMappedConverter.class$com$sun$jna$NativeMapped = class$("com.sun.jna.NativeMapped")) : NativeMappedConverter.class$com$sun$jna$NativeMapped).isAssignableFrom(type)) {
            throw new IllegalArgumentException("Type must derive from " + ((NativeMappedConverter.class$com$sun$jna$NativeMapped == null) ? (NativeMappedConverter.class$com$sun$jna$NativeMapped = class$("com.sun.jna.NativeMapped")) : NativeMappedConverter.class$com$sun$jna$NativeMapped));
        }
        this.type = type;
        this.instance = this.defaultValue();
        this.nativeType = this.instance.nativeType();
    }
    
    public NativeMapped defaultValue() {
        try {
            return this.type.newInstance();
        }
        catch (InstantiationException e) {
            final String msg = "Can't create an instance of " + this.type + ", requires a no-arg constructor: " + e;
            throw new IllegalArgumentException(msg);
        }
        catch (IllegalAccessException e2) {
            final String msg = "Not allowed to create an instance of " + this.type + ", requires a public, no-arg constructor: " + e2;
            throw new IllegalArgumentException(msg);
        }
    }
    
    public Object fromNative(final Object nativeValue, final FromNativeContext context) {
        return this.instance.fromNative(nativeValue, context);
    }
    
    public Class nativeType() {
        return this.nativeType;
    }
    
    public Object toNative(Object value, final ToNativeContext context) {
        if (value == null) {
            if (((NativeMappedConverter.class$com$sun$jna$Pointer == null) ? (NativeMappedConverter.class$com$sun$jna$Pointer = class$("com.sun.jna.Pointer")) : NativeMappedConverter.class$com$sun$jna$Pointer).isAssignableFrom(this.nativeType)) {
                return null;
            }
            value = this.defaultValue();
        }
        return ((NativeMapped)value).toNative();
    }
    
    static /* synthetic */ Class class$(final String x0) {
        try {
            return Class.forName(x0);
        }
        catch (ClassNotFoundException x) {
            throw new NoClassDefFoundError().initCause(x);
        }
    }
    
    static {
        NativeMappedConverter.converters = new WeakHashMap();
    }
}
