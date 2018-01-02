// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.jaffl.provider.jffi;

import com.kenai.jaffl.NativeLong;
import com.kenai.jaffl.Platform;
import com.kenai.jaffl.struct.Struct;
import com.kenai.jaffl.Pointer;
import java.io.PrintWriter;
import java.io.OutputStream;
import org.jruby.org.objectweb.asm.ClassVisitor;
import java.lang.reflect.Constructor;
import org.jruby.org.objectweb.asm.MethodVisitor;

class AsmUtil
{
    public static final MethodVisitor newTraceMethodVisitor(final MethodVisitor mv) {
        try {
            final Class tmvClass = Class.forName("org.jruby.org.objectweb.asm.util.TraceMethodVisitor");
            final Constructor c = tmvClass.getDeclaredConstructor(MethodVisitor.class);
            return c.newInstance(mv);
        }
        catch (Throwable t) {
            return mv;
        }
    }
    
    public static final ClassVisitor newTraceClassVisitor(final ClassVisitor cv, final OutputStream out) {
        return newTraceClassVisitor(cv, new PrintWriter(out, true));
    }
    
    public static final ClassVisitor newTraceClassVisitor(final ClassVisitor cv, final PrintWriter out) {
        try {
            final Class tmvClass = Class.forName("org.jruby.org.objectweb.asm.util.TraceClassVisitor");
            final Constructor c = tmvClass.getDeclaredConstructor(ClassVisitor.class, PrintWriter.class);
            return c.newInstance(cv, out);
        }
        catch (Throwable t) {
            return cv;
        }
    }
    
    public static final ClassVisitor newCheckClassAdapter(final ClassVisitor cv) {
        try {
            final Class tmvClass = Class.forName("org.jruby.org.objectweb.asm.util.CheckClassAdapter");
            final Constructor c = tmvClass.getDeclaredConstructor(ClassVisitor.class);
            return c.newInstance(cv);
        }
        catch (Throwable t) {
            return cv;
        }
    }
    
    public static final Class unboxedReturnType(final Class type) {
        if (Pointer.class.isAssignableFrom(type) || Struct.class.isAssignableFrom(type) || String.class.isAssignableFrom(type)) {
            return (Class)((Platform.getPlatform().longSize() == 32) ? Integer.TYPE : Long.TYPE);
        }
        return unboxedType(type);
    }
    
    public static final Class unboxedType(final Class boxedType) {
        if (boxedType == Byte.class) {
            return Byte.TYPE;
        }
        if (boxedType == Short.class) {
            return Short.TYPE;
        }
        if (boxedType == Integer.class) {
            return Integer.TYPE;
        }
        if (boxedType == Long.class) {
            return Long.TYPE;
        }
        if (boxedType == Float.class) {
            return Float.TYPE;
        }
        if (boxedType == Double.class) {
            return Double.TYPE;
        }
        if (boxedType == Boolean.class) {
            return Boolean.TYPE;
        }
        if (boxedType == NativeLong.class) {
            return (Class)((Platform.getPlatform().longSize() == 32) ? Integer.TYPE : Long.TYPE);
        }
        if (Pointer.class.isAssignableFrom(boxedType) || Struct.class.isAssignableFrom(boxedType)) {
            return (Class)((Platform.getPlatform().addressSize() == 32) ? Integer.TYPE : Long.TYPE);
        }
        return boxedType;
    }
    
    public static final Class boxedType(final Class type) {
        if (type == Byte.TYPE) {
            return Byte.class;
        }
        if (type == Short.TYPE) {
            return Short.class;
        }
        if (type == Integer.TYPE) {
            return Integer.class;
        }
        if (type == Long.TYPE) {
            return Long.class;
        }
        if (type == Float.TYPE) {
            return Float.class;
        }
        if (type == Double.TYPE) {
            return Double.class;
        }
        if (type == Boolean.TYPE) {
            return Boolean.class;
        }
        return type;
    }
    
    static final void emitReturnOp(final SkinnyMethodAdapter mv, final Class returnType) {
        if (!returnType.isPrimitive()) {
            mv.areturn();
        }
        else if (Long.TYPE == returnType) {
            mv.lreturn();
        }
        else if (Float.TYPE == returnType) {
            mv.freturn();
        }
        else if (Double.TYPE == returnType) {
            mv.dreturn();
        }
        else if (Void.TYPE == returnType) {
            mv.voidreturn();
        }
        else {
            mv.ireturn();
        }
    }
    
    static final int calculateLocalVariableSpace(final Class type) {
        return (Long.TYPE == type || Double.TYPE == type) ? 2 : 1;
    }
    
    static final int calculateLocalVariableSpace(final Class... types) {
        int size = 0;
        for (int i = 0; i < types.length; ++i) {
            size += calculateLocalVariableSpace(types[i]);
        }
        return size;
    }
    
    private static final void unboxPointerOrStruct(final SkinnyMethodAdapter mv, final Class type, final Class nativeType) {
        mv.invokestatic(CodegenUtils.p(AsmRuntime.class), (Long.TYPE == nativeType) ? "longValue" : "intValue", CodegenUtils.sig(nativeType, type));
    }
    
    static final void unboxPointer(final SkinnyMethodAdapter mv, final Class nativeType) {
        unboxPointerOrStruct(mv, Pointer.class, nativeType);
    }
    
    static final void unboxStruct(final SkinnyMethodAdapter mv, final Class nativeType) {
        unboxPointerOrStruct(mv, Struct.class, nativeType);
    }
    
    static final void unboxNumber(final SkinnyMethodAdapter mv, final Class boxedType, final Class nativeType) {
        final String intValueMethod = (Long.TYPE == nativeType) ? "longValue" : "intValue";
        final String intValueSignature = (Long.TYPE == nativeType) ? "()J" : "()I";
        if (Byte.class == boxedType || Short.class == boxedType || Integer.class == boxedType) {
            mv.invokevirtual(CodegenUtils.p(boxedType), intValueMethod, intValueSignature);
        }
        else if (Long.class == boxedType) {
            mv.invokevirtual(CodegenUtils.p(boxedType), "longValue", "()J");
        }
        else if (Float.class == boxedType) {
            mv.invokevirtual(CodegenUtils.p(boxedType), "floatValue", "()F");
        }
        else if (Double.class == boxedType) {
            mv.invokevirtual(CodegenUtils.p(boxedType), "doubleValue", "()D");
        }
        else if (NativeLong.class.isAssignableFrom(boxedType)) {
            mv.invokevirtual(CodegenUtils.p(boxedType), intValueMethod, intValueSignature);
        }
        else {
            if (!Boolean.class.isAssignableFrom(boxedType)) {
                throw new IllegalArgumentException("unsupported Number subclass: " + boxedType);
            }
            mv.invokevirtual(CodegenUtils.p(boxedType), "booleanValue", "()Z");
            NumberUtil.widen(mv, Boolean.TYPE, nativeType);
        }
    }
}
