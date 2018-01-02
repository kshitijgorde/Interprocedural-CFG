// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.ffi;

import org.jruby.runtime.builtin.IRubyObject;

public enum NativeType implements NativeParam
{
    VOID, 
    BOOL, 
    CHAR, 
    UCHAR, 
    SHORT, 
    USHORT, 
    INT, 
    UINT, 
    LONG_LONG, 
    ULONG_LONG, 
    LONG, 
    ULONG, 
    FLOAT, 
    DOUBLE, 
    POINTER, 
    BUFFER_IN, 
    BUFFER_OUT, 
    BUFFER_INOUT, 
    CHAR_ARRAY, 
    STRING, 
    RBXSTRING, 
    VARARGS, 
    ARRAY, 
    STRUCT, 
    MAPPED;
    
    public final int intValue() {
        return this.ordinal();
    }
    
    public final NativeType getNativeType() {
        return this;
    }
    
    public static final NativeType valueOf(final int type) {
        final NativeType[] values = values();
        if (type < 0 || type >= values.length) {
            return NativeType.VOID;
        }
        return values[type];
    }
    
    public static final NativeType valueOf(final IRubyObject type) {
        if (type instanceof Type.Builtin) {
            return ((Type.Builtin)type).getNativeType();
        }
        if (type instanceof NativeParam) {
            return ((NativeParam)type).getNativeType();
        }
        return NativeType.VOID;
    }
}
