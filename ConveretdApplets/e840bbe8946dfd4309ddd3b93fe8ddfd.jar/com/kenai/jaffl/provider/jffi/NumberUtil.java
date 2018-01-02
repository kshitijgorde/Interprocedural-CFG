// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.jaffl.provider.jffi;

import com.kenai.jaffl.NativeLong;

public final class NumberUtil
{
    static final Class getBoxedClass(final Class c) {
        if (!c.isPrimitive()) {
            return c;
        }
        if (Void.TYPE == c) {
            return Void.class;
        }
        if (Byte.TYPE == c) {
            return Byte.class;
        }
        if (Character.TYPE == c) {
            return Character.class;
        }
        if (Short.TYPE == c) {
            return Short.class;
        }
        if (Integer.TYPE == c) {
            return Integer.class;
        }
        if (Long.TYPE == c) {
            return Long.class;
        }
        if (Float.TYPE == c) {
            return Float.class;
        }
        if (Double.TYPE == c) {
            return Double.class;
        }
        if (Boolean.TYPE == c) {
            return Boolean.class;
        }
        throw new IllegalArgumentException("unknown primitive class");
    }
    
    static final Class getPrimitiveClass(final Class c) {
        if (Void.class == c) {
            return Void.TYPE;
        }
        if (Boolean.class == c) {
            return Boolean.TYPE;
        }
        if (Byte.class == c) {
            return Byte.TYPE;
        }
        if (Character.class == c) {
            return Character.TYPE;
        }
        if (Short.class == c) {
            return Short.TYPE;
        }
        if (Integer.class == c) {
            return Integer.TYPE;
        }
        if (Long.class == c) {
            return Long.TYPE;
        }
        if (Float.class == c) {
            return Float.TYPE;
        }
        if (Double.class == c) {
            return Double.TYPE;
        }
        if (NativeLong.class == c) {
            return Long.TYPE;
        }
        if (c.isPrimitive()) {
            return c;
        }
        throw new IllegalArgumentException("unsupported number class");
    }
    
    public static boolean isPrimitiveInt(final Class c) {
        return Byte.TYPE == c || Short.TYPE == c || Integer.TYPE == c || Boolean.TYPE == c;
    }
    
    public static final void widen(final SkinnyMethodAdapter mv, final Class from, final Class to) {
        if (Long.TYPE == to && Long.TYPE != from && isPrimitiveInt(from)) {
            mv.i2l();
        }
    }
    
    public static final void narrow(final SkinnyMethodAdapter mv, final Class from, final Class to) {
        if (!from.equals(to) && (Byte.TYPE == to || Short.TYPE == to || Character.TYPE == to || Integer.TYPE == to || Boolean.TYPE == to)) {
            if (Long.TYPE == from) {
                mv.l2i();
            }
            if (Byte.TYPE == to) {
                mv.i2b();
            }
            else if (Short.TYPE == to) {
                mv.i2s();
            }
            else if (Character.TYPE == to) {
                mv.i2c();
            }
            else if (Boolean.TYPE == to) {
                mv.iconst_1();
                mv.iand();
            }
        }
    }
}
