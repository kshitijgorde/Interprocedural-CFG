// 
// Decompiled by Procyon v0.5.30
// 

package com.sun.jna.win32;

import com.sun.jna.ToNativeConverter;
import com.sun.jna.FromNativeContext;
import com.sun.jna.WString;
import com.sun.jna.StringArray;
import com.sun.jna.ToNativeContext;
import com.sun.jna.TypeConverter;
import com.sun.jna.TypeMapper;
import com.sun.jna.DefaultTypeMapper;

public class W32APITypeMapper extends DefaultTypeMapper
{
    public static final TypeMapper UNICODE;
    public static final TypeMapper ASCII;
    static /* synthetic */ Class class$com$sun$jna$WString;
    static /* synthetic */ Class class$java$lang$String;
    static /* synthetic */ Class array$Ljava$lang$String;
    static /* synthetic */ Class class$java$lang$Integer;
    static /* synthetic */ Class class$java$lang$Boolean;
    
    protected W32APITypeMapper(final boolean unicode) {
        if (unicode) {
            final TypeConverter stringConverter = new TypeConverter() {
                public Object toNative(final Object value, final ToNativeContext context) {
                    if (value == null) {
                        return null;
                    }
                    if (value instanceof String[]) {
                        return new StringArray((String[])value, true);
                    }
                    return new WString(value.toString());
                }
                
                public Object fromNative(final Object value, final FromNativeContext context) {
                    if (value == null) {
                        return null;
                    }
                    return value.toString();
                }
                
                public Class nativeType() {
                    return (W32APITypeMapper.class$com$sun$jna$WString == null) ? (W32APITypeMapper.class$com$sun$jna$WString = W32APITypeMapper.class$("com.sun.jna.WString")) : W32APITypeMapper.class$com$sun$jna$WString;
                }
            };
            this.addTypeConverter((W32APITypeMapper.class$java$lang$String == null) ? (W32APITypeMapper.class$java$lang$String = class$("java.lang.String")) : W32APITypeMapper.class$java$lang$String, stringConverter);
            this.addToNativeConverter((W32APITypeMapper.array$Ljava$lang$String == null) ? (W32APITypeMapper.array$Ljava$lang$String = class$("[Ljava.lang.String;")) : W32APITypeMapper.array$Ljava$lang$String, stringConverter);
        }
        final TypeConverter booleanConverter = new TypeConverter() {
            public Object toNative(final Object value, final ToNativeContext context) {
                return new Integer(Boolean.TRUE.equals(value) ? 1 : 0);
            }
            
            public Object fromNative(final Object value, final FromNativeContext context) {
                return ((int)value != 0) ? Boolean.TRUE : Boolean.FALSE;
            }
            
            public Class nativeType() {
                return (W32APITypeMapper.class$java$lang$Integer == null) ? (W32APITypeMapper.class$java$lang$Integer = W32APITypeMapper.class$("java.lang.Integer")) : W32APITypeMapper.class$java$lang$Integer;
            }
        };
        this.addTypeConverter((W32APITypeMapper.class$java$lang$Boolean == null) ? (W32APITypeMapper.class$java$lang$Boolean = class$("java.lang.Boolean")) : W32APITypeMapper.class$java$lang$Boolean, booleanConverter);
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
        UNICODE = new W32APITypeMapper(true);
        ASCII = new W32APITypeMapper(false);
    }
}
