// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.jaffl.provider.jffi;

import com.kenai.jffi.CallingConvention;
import com.kenai.jaffl.LibraryOption;
import java.util.Map;
import java.lang.annotation.Annotation;
import com.kenai.jaffl.annotations.SaveError;
import com.kenai.jaffl.annotations.IgnoreError;
import com.kenai.jaffl.byref.ByReference;
import java.nio.Buffer;
import com.kenai.jaffl.struct.Struct;
import com.kenai.jaffl.Address;
import com.kenai.jaffl.Pointer;
import com.kenai.jaffl.Platform;
import com.kenai.jaffl.NativeLong;
import com.kenai.jffi.Type;
import java.lang.reflect.Method;

final class InvokerUtil
{
    static final Type getNativeReturnType(final Method method) {
        return getNativeReturnType(method.getReturnType());
    }
    
    static final Type getNativeReturnType(final Class type) {
        if (Void.class.isAssignableFrom(type) || Void.TYPE == type) {
            return Type.VOID;
        }
        if (Boolean.class.isAssignableFrom(type) || Boolean.TYPE == type) {
            return Type.SINT32;
        }
        if (Byte.class.isAssignableFrom(type) || Byte.TYPE == type) {
            return Type.SINT8;
        }
        if (Short.class.isAssignableFrom(type) || Short.TYPE == type) {
            return Type.SINT16;
        }
        if (Integer.class.isAssignableFrom(type) || Integer.TYPE == type) {
            return Type.SINT32;
        }
        if (Long.class.isAssignableFrom(type) || Long.TYPE == type) {
            return Type.SINT64;
        }
        if (NativeLong.class.isAssignableFrom(type)) {
            return (Platform.getPlatform().longSize() == 32) ? Type.SINT32 : Type.SINT64;
        }
        if (Float.class.isAssignableFrom(type) || Float.TYPE == type) {
            return Type.FLOAT;
        }
        if (Double.class.isAssignableFrom(type) || Double.TYPE == type) {
            return Type.DOUBLE;
        }
        if (Enum.class.isAssignableFrom(type)) {
            return Type.SINT32;
        }
        if (Pointer.class.isAssignableFrom(type)) {
            return Type.POINTER;
        }
        if (Address.class.isAssignableFrom(type)) {
            return Type.POINTER;
        }
        if (Struct.class.isAssignableFrom(type)) {
            return Type.POINTER;
        }
        if (String.class.isAssignableFrom(type)) {
            return Type.POINTER;
        }
        throw new IllegalArgumentException("Unsupported return type: " + type);
    }
    
    static final Type getNativeParameterType(final Class type) {
        if (Byte.class.isAssignableFrom(type) || Byte.TYPE == type) {
            return Type.SINT8;
        }
        if (Short.class.isAssignableFrom(type) || Short.TYPE == type) {
            return Type.SINT16;
        }
        if (Integer.class.isAssignableFrom(type) || Integer.TYPE == type) {
            return Type.SINT32;
        }
        if (Long.class.isAssignableFrom(type) || Long.TYPE == type) {
            return Type.SINT64;
        }
        if (NativeLong.class.isAssignableFrom(type)) {
            return (Platform.getPlatform().longSize() == 32) ? Type.SINT32 : Type.SINT64;
        }
        if (Float.class.isAssignableFrom(type) || Float.TYPE == type) {
            return Type.FLOAT;
        }
        if (Double.class.isAssignableFrom(type) || Double.TYPE == type) {
            return Type.DOUBLE;
        }
        if (Boolean.class.isAssignableFrom(type) || Boolean.TYPE == type) {
            return Type.SINT32;
        }
        if (Enum.class.isAssignableFrom(type)) {
            return Type.SINT32;
        }
        if (Pointer.class.isAssignableFrom(type)) {
            return Type.POINTER;
        }
        if (Address.class.isAssignableFrom(type)) {
            return Type.POINTER;
        }
        if (Struct.class.isAssignableFrom(type)) {
            return Type.POINTER;
        }
        if (Buffer.class.isAssignableFrom(type)) {
            return Type.POINTER;
        }
        if (CharSequence.class.isAssignableFrom(type)) {
            return Type.POINTER;
        }
        if (ByReference.class.isAssignableFrom(type)) {
            return Type.POINTER;
        }
        if (type.isArray()) {
            return Type.POINTER;
        }
        throw new IllegalArgumentException("Unsupported parameter type: " + type);
    }
    
    public static final boolean requiresErrno(final Method method) {
        boolean saveError = true;
        for (final Annotation a : method.getAnnotations()) {
            if (a instanceof IgnoreError) {
                saveError = false;
            }
            else if (a instanceof SaveError) {
                saveError = true;
            }
        }
        return saveError;
    }
    
    public static final CallingConvention getCallingConvention(final Map<LibraryOption, ?> libraryOptions) {
        final Object convention = (com.kenai.jaffl.CallingConvention)libraryOptions.get(LibraryOption.CallingConvention);
        if (convention instanceof CallingConvention) {
            return (CallingConvention)convention;
        }
        if (convention instanceof com.kenai.jaffl.CallingConvention) {
            switch ((com.kenai.jaffl.CallingConvention)convention) {
                case DEFAULT: {
                    return CallingConvention.DEFAULT;
                }
                case STDCALL: {
                    return CallingConvention.STDCALL;
                }
            }
        }
        else if (convention != null) {
            throw new IllegalArgumentException("unknown calling convention: " + convention);
        }
        return CallingConvention.DEFAULT;
    }
}
