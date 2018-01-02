// 
// Decompiled by Procyon v0.5.30
// 

package com.sun.jna;

import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;

public class DefaultTypeMapper implements TypeMapper
{
    private List toNativeConverters;
    private List fromNativeConverters;
    static /* synthetic */ Class class$java$lang$Boolean;
    static /* synthetic */ Class class$java$lang$Byte;
    static /* synthetic */ Class class$java$lang$Character;
    static /* synthetic */ Class class$java$lang$Short;
    static /* synthetic */ Class class$java$lang$Integer;
    static /* synthetic */ Class class$java$lang$Long;
    static /* synthetic */ Class class$java$lang$Float;
    static /* synthetic */ Class class$java$lang$Double;
    
    public DefaultTypeMapper() {
        this.toNativeConverters = new ArrayList();
        this.fromNativeConverters = new ArrayList();
    }
    
    private Class getAltClass(final Class cls) {
        if (cls == ((DefaultTypeMapper.class$java$lang$Boolean == null) ? (DefaultTypeMapper.class$java$lang$Boolean = class$("java.lang.Boolean")) : DefaultTypeMapper.class$java$lang$Boolean)) {
            return Boolean.TYPE;
        }
        if (cls == Boolean.TYPE) {
            return (DefaultTypeMapper.class$java$lang$Boolean == null) ? (DefaultTypeMapper.class$java$lang$Boolean = class$("java.lang.Boolean")) : DefaultTypeMapper.class$java$lang$Boolean;
        }
        if (cls == ((DefaultTypeMapper.class$java$lang$Byte == null) ? (DefaultTypeMapper.class$java$lang$Byte = class$("java.lang.Byte")) : DefaultTypeMapper.class$java$lang$Byte)) {
            return Byte.TYPE;
        }
        if (cls == Byte.TYPE) {
            return (DefaultTypeMapper.class$java$lang$Byte == null) ? (DefaultTypeMapper.class$java$lang$Byte = class$("java.lang.Byte")) : DefaultTypeMapper.class$java$lang$Byte;
        }
        if (cls == ((DefaultTypeMapper.class$java$lang$Character == null) ? (DefaultTypeMapper.class$java$lang$Character = class$("java.lang.Character")) : DefaultTypeMapper.class$java$lang$Character)) {
            return Character.TYPE;
        }
        if (cls == Character.TYPE) {
            return (DefaultTypeMapper.class$java$lang$Character == null) ? (DefaultTypeMapper.class$java$lang$Character = class$("java.lang.Character")) : DefaultTypeMapper.class$java$lang$Character;
        }
        if (cls == ((DefaultTypeMapper.class$java$lang$Short == null) ? (DefaultTypeMapper.class$java$lang$Short = class$("java.lang.Short")) : DefaultTypeMapper.class$java$lang$Short)) {
            return Short.TYPE;
        }
        if (cls == Short.TYPE) {
            return (DefaultTypeMapper.class$java$lang$Short == null) ? (DefaultTypeMapper.class$java$lang$Short = class$("java.lang.Short")) : DefaultTypeMapper.class$java$lang$Short;
        }
        if (cls == ((DefaultTypeMapper.class$java$lang$Integer == null) ? (DefaultTypeMapper.class$java$lang$Integer = class$("java.lang.Integer")) : DefaultTypeMapper.class$java$lang$Integer)) {
            return Integer.TYPE;
        }
        if (cls == Integer.TYPE) {
            return (DefaultTypeMapper.class$java$lang$Integer == null) ? (DefaultTypeMapper.class$java$lang$Integer = class$("java.lang.Integer")) : DefaultTypeMapper.class$java$lang$Integer;
        }
        if (cls == ((DefaultTypeMapper.class$java$lang$Long == null) ? (DefaultTypeMapper.class$java$lang$Long = class$("java.lang.Long")) : DefaultTypeMapper.class$java$lang$Long)) {
            return Long.TYPE;
        }
        if (cls == Long.TYPE) {
            return (DefaultTypeMapper.class$java$lang$Long == null) ? (DefaultTypeMapper.class$java$lang$Long = class$("java.lang.Long")) : DefaultTypeMapper.class$java$lang$Long;
        }
        if (cls == ((DefaultTypeMapper.class$java$lang$Float == null) ? (DefaultTypeMapper.class$java$lang$Float = class$("java.lang.Float")) : DefaultTypeMapper.class$java$lang$Float)) {
            return Float.TYPE;
        }
        if (cls == Float.TYPE) {
            return (DefaultTypeMapper.class$java$lang$Float == null) ? (DefaultTypeMapper.class$java$lang$Float = class$("java.lang.Float")) : DefaultTypeMapper.class$java$lang$Float;
        }
        if (cls == ((DefaultTypeMapper.class$java$lang$Double == null) ? (DefaultTypeMapper.class$java$lang$Double = class$("java.lang.Double")) : DefaultTypeMapper.class$java$lang$Double)) {
            return Double.TYPE;
        }
        if (cls == Double.TYPE) {
            return (DefaultTypeMapper.class$java$lang$Double == null) ? (DefaultTypeMapper.class$java$lang$Double = class$("java.lang.Double")) : DefaultTypeMapper.class$java$lang$Double;
        }
        return null;
    }
    
    public void addToNativeConverter(final Class cls, final ToNativeConverter converter) {
        this.toNativeConverters.add(new Entry(cls, converter));
        final Class alt = this.getAltClass(cls);
        if (alt != null) {
            this.toNativeConverters.add(new Entry(alt, converter));
        }
    }
    
    public void addFromNativeConverter(final Class cls, final FromNativeConverter converter) {
        this.fromNativeConverters.add(new Entry(cls, converter));
        final Class alt = this.getAltClass(cls);
        if (alt != null) {
            this.fromNativeConverters.add(new Entry(alt, converter));
        }
    }
    
    protected void addTypeConverter(final Class cls, final TypeConverter converter) {
        this.addFromNativeConverter(cls, converter);
        this.addToNativeConverter(cls, converter);
    }
    
    private Object lookupConverter(final Class javaClass, final List converters) {
        for (final Entry entry : converters) {
            if (entry.type.isAssignableFrom(javaClass)) {
                return entry.converter;
            }
        }
        return null;
    }
    
    public FromNativeConverter getFromNativeConverter(final Class javaType) {
        return (FromNativeConverter)this.lookupConverter(javaType, this.fromNativeConverters);
    }
    
    public ToNativeConverter getToNativeConverter(final Class javaType) {
        return (ToNativeConverter)this.lookupConverter(javaType, this.toNativeConverters);
    }
    
    static /* synthetic */ Class class$(final String x0) {
        try {
            return Class.forName(x0);
        }
        catch (ClassNotFoundException x) {
            throw new NoClassDefFoundError().initCause(x);
        }
    }
    
    private static class Entry
    {
        public Class type;
        public Object converter;
        
        public Entry(final Class type, final Object converter) {
            this.type = type;
            this.converter = converter;
        }
    }
}
