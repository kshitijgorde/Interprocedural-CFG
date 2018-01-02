// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.jaffl.provider.jffi;

import com.kenai.jaffl.MemoryIO;
import java.util.HashMap;
import com.kenai.jffi.Type;
import com.kenai.jffi.InvocationBuffer;
import com.kenai.jffi.Platform;
import com.kenai.jffi.HeapInvocationBuffer;
import com.kenai.jaffl.provider.InvocationSession;
import org.jruby.org.objectweb.asm.Label;
import com.kenai.jffi.Invoker;
import java.lang.annotation.Annotation;
import com.kenai.jaffl.mapper.FromNativeContext;
import com.kenai.jaffl.mapper.MethodResultContext;
import java.lang.reflect.Constructor;
import com.kenai.jffi.CallingConvention;
import com.kenai.jaffl.annotations.StdCall;
import com.kenai.jaffl.mapper.ToNativeContext;
import com.kenai.jaffl.mapper.MethodParameterContext;
import com.kenai.jaffl.mapper.FunctionMapper;
import com.kenai.jaffl.mapper.ToNativeConverter;
import com.kenai.jaffl.mapper.FromNativeConverter;
import com.kenai.jffi.Function;
import java.io.OutputStream;
import org.jruby.org.objectweb.asm.ClassVisitor;
import org.jruby.org.objectweb.asm.ClassWriter;
import com.kenai.jaffl.byref.ByReference;
import java.nio.Buffer;
import com.kenai.jaffl.struct.Struct;
import com.kenai.jaffl.Address;
import com.kenai.jaffl.Pointer;
import com.kenai.jaffl.NativeLong;
import java.lang.reflect.Method;
import com.kenai.jaffl.mapper.TypeMapper;
import com.kenai.jaffl.LibraryOption;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import org.jruby.org.objectweb.asm.Opcodes;

public class AsmLibraryLoader extends LibraryLoader implements Opcodes
{
    public static final boolean DEBUG;
    private static final boolean FAST_NUMERIC_AVAILABLE;
    private static final boolean FAST_LONG_AVAILABLE;
    private final AtomicLong nextClassID;
    private final AtomicLong nextIvarID;
    private final AtomicLong nextMethodID;
    
    public AsmLibraryLoader() {
        this.nextClassID = new AtomicLong(0L);
        this.nextIvarID = new AtomicLong(0L);
        this.nextMethodID = new AtomicLong(0L);
    }
    
    static final LibraryLoader getInstance() {
        return SingletonHolder.INSTANCE;
    }
    
    boolean isInterfaceSupported(final Class interfaceClass, final Map<LibraryOption, ?> options) {
        final TypeMapper typeMapper = (TypeMapper)(options.containsKey(LibraryOption.TypeMapper) ? options.get(LibraryOption.TypeMapper) : NullTypeMapper.INSTANCE);
        for (final Method m : interfaceClass.getDeclaredMethods()) {
            if (!this.isReturnTypeSupported(m.getReturnType()) && this.getResultConverter(m, typeMapper) == null) {
                System.err.println("Unsupported return type: " + m.getReturnType());
                return false;
            }
            for (final Class c : m.getParameterTypes()) {
                if (!this.isParameterTypeSupported(c) && typeMapper.getToNativeConverter(c) == null) {
                    System.err.println("Unsupported parameter type: " + c);
                    return false;
                }
            }
        }
        return true;
    }
    
    private boolean isReturnTypeSupported(final Class type) {
        return type.isPrimitive() || Byte.class == type || Short.class == type || Integer.class == type || Long.class == type || Float.class == type || Double.class == type || NativeLong.class == type || Pointer.class == type || Address.class == type || String.class == type || Struct.class.isAssignableFrom(type);
    }
    
    private boolean isParameterTypeSupported(final Class type) {
        return type.isPrimitive() || Byte.class == type || Short.class == type || Integer.class == type || Long.class == type || Float.class == type || Double.class == type || NativeLong.class == type || Pointer.class.isAssignableFrom(type) || Address.class.isAssignableFrom(type) || Enum.class.isAssignableFrom(type) || Buffer.class.isAssignableFrom(type) || (type.isArray() && type.getComponentType().isPrimitive()) || Struct.class.isAssignableFrom(type) || (type.isArray() && Struct.class.isAssignableFrom(type.getComponentType())) || (type.isArray() && Pointer.class.isAssignableFrom(type.getComponentType())) || (type.isArray() && CharSequence.class.isAssignableFrom(type.getComponentType())) || CharSequence.class.isAssignableFrom(type) || ByReference.class.isAssignableFrom(type) || StringBuilder.class.isAssignableFrom(type) || StringBuffer.class.isAssignableFrom(type);
    }
    
     <T> T loadLibrary(final Library library, final Class<T> interfaceClass, final Map<LibraryOption, ?> libraryOptions) {
        return (T)this.generateInterfaceImpl(library, (Class<Object>)interfaceClass, libraryOptions);
    }
    
    private final <T> T generateInterfaceImpl(final Library library, final Class<T> interfaceClass, final Map<LibraryOption, ?> libraryOptions) {
        final ClassWriter cw = new ClassWriter(2);
        final ClassVisitor cv = AsmLibraryLoader.DEBUG ? AsmUtil.newCheckClassAdapter(AsmUtil.newTraceClassVisitor(cw, System.err)) : cw;
        final String className = CodegenUtils.p(interfaceClass) + "$jaffl$" + this.nextClassID.getAndIncrement();
        cv.visit(49, 17, className, null, CodegenUtils.p(AbstractNativeInterface.class), new String[] { CodegenUtils.p(interfaceClass) });
        final SkinnyMethodAdapter init = new SkinnyMethodAdapter(cv.visitMethod(1, "<init>", CodegenUtils.sig(Void.TYPE, Library.class, Function[].class, FromNativeConverter[].class, ToNativeConverter[][].class), null, null));
        init.start();
        init.aload(0);
        init.aload(1);
        init.invokespecial(CodegenUtils.p(AbstractNativeInterface.class), "<init>", CodegenUtils.sig(Void.TYPE, Library.class));
        final Method[] methods = interfaceClass.getMethods();
        final Function[] functions = new Function[methods.length];
        final FromNativeConverter[] resultConverters = new FromNativeConverter[methods.length];
        final ToNativeConverter[][] parameterConverters = new ToNativeConverter[methods.length][0];
        final FunctionMapper functionMapper = (FunctionMapper)(libraryOptions.containsKey(LibraryOption.FunctionMapper) ? libraryOptions.get(LibraryOption.FunctionMapper) : IdentityFunctionMapper.getInstance());
        final TypeMapper typeMapper = (TypeMapper)(libraryOptions.containsKey(LibraryOption.TypeMapper) ? libraryOptions.get(LibraryOption.TypeMapper) : NullTypeMapper.INSTANCE);
        final CallingConvention libraryCallingConvention = getCallingConvention(interfaceClass, libraryOptions);
        final StubCompiler compiler = StubCompiler.newCompiler();
        for (int i = 0; i < methods.length; ++i) {
            final Method m = methods[i];
            final Class returnType = m.getReturnType();
            final Class[] parameterTypes = m.getParameterTypes();
            Class nativeReturnType = returnType;
            final Class[] nativeParameterTypes = new Class[parameterTypes.length];
            boolean conversionRequired = false;
            resultConverters[i] = this.getResultConverter(m, typeMapper);
            if (resultConverters[i] != null) {
                cv.visitField(18, this.getResultConverterFieldName(i), CodegenUtils.ci(FromNativeConverter.class), null, null);
                nativeReturnType = resultConverters[i].nativeType();
                conversionRequired = true;
            }
            parameterConverters[i] = new ToNativeConverter[parameterTypes.length];
            for (int pidx = 0; pidx < parameterTypes.length; ++pidx) {
                final ToNativeConverter converter = typeMapper.getToNativeConverter(parameterTypes[pidx]);
                if (converter != null) {
                    cv.visitField(18, this.getParameterConverterFieldName(i, pidx), CodegenUtils.ci(ToNativeConverter.class), null, null);
                    nativeParameterTypes[pidx] = converter.nativeType();
                    parameterConverters[i][pidx] = new ToNativeProxy(converter, new MethodParameterContext(m, pidx));
                    conversionRequired = true;
                }
                else {
                    nativeParameterTypes[pidx] = parameterTypes[pidx];
                }
            }
            final String functionName = functionMapper.mapFunctionName(m.getName(), null);
            cv.visitField(26, "name_" + i, CodegenUtils.ci(String.class), null, functionName);
            final CallingConvention callingConvention = (m.getAnnotation(StdCall.class) != null) ? CallingConvention.STDCALL : libraryCallingConvention;
            try {
                functions[i] = getFunction(library.findSymbolAddress(functionName), nativeReturnType, nativeParameterTypes, InvokerUtil.requiresErrno(m), callingConvention);
            }
            catch (SymbolNotFoundError ex) {
                cv.visitField(26, "error_" + i, CodegenUtils.ci(String.class), null, ex.getMessage());
                this.generateFunctionNotFound(cv, className, i, functionName, returnType, parameterTypes);
                continue;
            }
            final String functionFieldName = "function_" + i;
            cv.visitField(18, functionFieldName, CodegenUtils.ci(Function.class), null, null);
            final boolean ignoreErrno = !InvokerUtil.requiresErrno(m);
            if (this.canCompile(compiler, nativeReturnType, nativeParameterTypes, callingConvention)) {
                this.compile(compiler, functions[i], cv, className, m.getName() + (conversionRequired ? "$raw" : ""), functionFieldName, nativeReturnType, nativeParameterTypes, m.getParameterAnnotations(), callingConvention, ignoreErrno);
            }
            else {
                this.generateMethod(cv, className, m.getName() + (conversionRequired ? "$raw" : ""), functionFieldName, nativeReturnType, nativeParameterTypes, m.getParameterAnnotations(), callingConvention, ignoreErrno);
            }
            if (conversionRequired) {
                this.generateConversionMethod(cv, className, m.getName(), i, returnType, parameterTypes, nativeReturnType, nativeParameterTypes);
            }
            init.aload(0);
            init.aload(2);
            init.pushInt(i);
            init.aaload();
            init.putfield(className, functionFieldName, CodegenUtils.ci(Function.class));
            if (resultConverters[i] != null) {
                init.aload(0);
                init.aload(3);
                init.pushInt(i);
                init.aaload();
                init.putfield(className, this.getResultConverterFieldName(i), CodegenUtils.ci(FromNativeConverter.class));
            }
            for (int pidx2 = 0; pidx2 < parameterTypes.length; ++pidx2) {
                if (parameterConverters[i][pidx2] != null) {
                    init.aload(0);
                    init.aload(4);
                    init.pushInt(i);
                    init.aaload();
                    init.pushInt(pidx2);
                    init.aaload();
                    init.putfield(className, this.getParameterConverterFieldName(i, pidx2), CodegenUtils.ci(ToNativeConverter.class));
                }
            }
        }
        init.voidreturn();
        init.visitMaxs(10, 10);
        init.visitEnd();
        cv.visitEnd();
        try {
            final Class implClass = new AsmClassLoader(interfaceClass.getClassLoader()).defineClass(className.replace("/", "."), cw.toByteArray());
            final Constructor<T> cons = implClass.getDeclaredConstructor(Library.class, Function[].class, FromNativeConverter[].class, ToNativeConverter[][].class);
            final T result = cons.newInstance(library, functions, resultConverters, parameterConverters);
            compiler.attach(implClass);
            return result;
        }
        catch (Throwable ex2) {
            throw new RuntimeException(ex2);
        }
    }
    
    private final FromNativeConverter getResultConverter(final Method m, final TypeMapper typeMapper) {
        final Class returnType = m.getReturnType();
        final FromNativeConverter conv = typeMapper.getFromNativeConverter(returnType);
        if (conv != null) {
            return new FromNativeProxy(conv, new MethodResultContext(m));
        }
        if (Enum.class.isAssignableFrom(returnType)) {
            return new EnumResultConverter(returnType);
        }
        return null;
    }
    
    private static final CallingConvention getCallingConvention(final Class interfaceClass, final Map<LibraryOption, ?> options) {
        if (interfaceClass.getAnnotation(StdCall.class) != null) {
            return CallingConvention.STDCALL;
        }
        return InvokerUtil.getCallingConvention(options);
    }
    
    private final String getFunctionFieldName(final int idx) {
        return "function_" + idx;
    }
    
    private final String getResultConverterFieldName(final int idx) {
        return "resultConverter_" + idx;
    }
    
    private final String getParameterConverterFieldName(final int idx, final int paramIndex) {
        return "parameterConverter_" + idx + "_" + paramIndex;
    }
    
    private final void generateFunctionNotFound(final ClassVisitor cv, final String className, final int idx, final String functionName, final Class returnType, final Class[] parameterTypes) {
        final SkinnyMethodAdapter mv = new SkinnyMethodAdapter(cv.visitMethod(17, functionName, CodegenUtils.sig(returnType, parameterTypes), null, null));
        mv.start();
        mv.getstatic(className, "error_" + idx, CodegenUtils.ci(String.class));
        mv.invokestatic(AsmRuntime.class, "newUnsatisifiedLinkError", UnsatisfiedLinkError.class, String.class);
        mv.athrow();
        mv.visitMaxs(10, 10);
        mv.visitEnd();
    }
    
    private final void generateConversionMethod(final ClassVisitor cv, final String className, final String functionName, final int idx, final Class returnType, final Class[] parameterTypes, final Class nativeReturnType, final Class[] nativeParameterTypes) {
        final SkinnyMethodAdapter mv = new SkinnyMethodAdapter(cv.visitMethod(17, functionName, CodegenUtils.sig(returnType, parameterTypes), null, null));
        mv.start();
        if (!returnType.equals(nativeReturnType)) {
            mv.aload(0);
            mv.getfield(className, this.getResultConverterFieldName(idx), CodegenUtils.ci(FromNativeConverter.class));
        }
        mv.aload(0);
        int lvar = 1;
        for (int pidx = 0; pidx < parameterTypes.length; ++pidx) {
            final boolean convertParameter = !parameterTypes[pidx].equals(nativeParameterTypes[pidx]);
            if (convertParameter) {
                mv.aload(0);
                mv.getfield(className, this.getParameterConverterFieldName(idx, pidx), CodegenUtils.ci(ToNativeConverter.class));
            }
            lvar = this.loadParameter(mv, parameterTypes[pidx], lvar);
            if (convertParameter) {
                if (parameterTypes[pidx].isPrimitive()) {
                    this.boxPrimitive(mv, parameterTypes[pidx]);
                }
                mv.aconst_null();
                mv.invokeinterface(ToNativeConverter.class, "toNative", Object.class, Object.class, ToNativeContext.class);
                mv.checkcast(CodegenUtils.p(nativeParameterTypes[pidx]));
            }
        }
        mv.invokevirtual(className, functionName + "$raw", CodegenUtils.sig(nativeReturnType, nativeParameterTypes));
        if (!returnType.equals(nativeReturnType)) {
            if (nativeReturnType.isPrimitive()) {
                this.boxPrimitive(mv, nativeReturnType);
            }
            mv.aconst_null();
            mv.invokeinterface(FromNativeConverter.class, "fromNative", Object.class, Object.class, FromNativeContext.class);
            mv.checkcast(CodegenUtils.p(returnType));
        }
        AsmUtil.emitReturnOp(mv, returnType);
        mv.visitMaxs(10, 10);
        mv.visitEnd();
    }
    
    private final boolean canCompile(final StubCompiler compiler, final Class returnType, final Class[] parameterTypes, final CallingConvention convention) {
        final Class[] nativeParameterTypes = new Class[parameterTypes.length];
        for (int i = 0; i < nativeParameterTypes.length; ++i) {
            nativeParameterTypes[i] = AsmUtil.unboxedType(parameterTypes[i]);
        }
        return compiler.canCompile(AsmUtil.unboxedReturnType(returnType), nativeParameterTypes, convention);
    }
    
    private final void compile(final StubCompiler compiler, final Function function, final ClassVisitor cv, final String className, final String functionName, final String functionFieldName, final Class returnType, final Class[] parameterTypes, final Annotation[][] annotations, final CallingConvention convention, final boolean ignoreErrno) {
        final Class[] nativeParameterTypes = new Class[parameterTypes.length];
        boolean unboxing = false;
        boolean ptrCheck = false;
        for (int i = 0; i < nativeParameterTypes.length; ++i) {
            nativeParameterTypes[i] = AsmUtil.unboxedType(parameterTypes[i]);
            unboxing |= (nativeParameterTypes[i] != parameterTypes[i]);
            ptrCheck |= (Pointer.class.isAssignableFrom(parameterTypes[i]) || Struct.class.isAssignableFrom(parameterTypes[i]));
        }
        final Class nativeReturnType = AsmUtil.unboxedReturnType(returnType);
        unboxing |= (nativeReturnType != returnType);
        final String stubName = functionName + ((unboxing || ptrCheck) ? ("$jni$" + this.nextMethodID.getAndIncrement()) : "");
        if (unboxing || ptrCheck) {
            final SkinnyMethodAdapter mv = new SkinnyMethodAdapter(cv.visitMethod(17, functionName, CodegenUtils.sig(returnType, parameterTypes), null, null));
            mv.start();
            mv.aload(0);
            final Label bufferInvocationLabel = emitDirectCheck(mv, parameterTypes);
            int j = 0;
            int lvar = 1;
            while (j < parameterTypes.length) {
                lvar = this.loadParameter(mv, parameterTypes[j], lvar);
                if (parameterTypes[j] != nativeParameterTypes[j]) {
                    if (Number.class.isAssignableFrom(parameterTypes[j])) {
                        AsmUtil.unboxNumber(mv, parameterTypes[j], nativeParameterTypes[j]);
                    }
                    else if (Pointer.class.isAssignableFrom(parameterTypes[j])) {
                        AsmUtil.unboxPointer(mv, nativeParameterTypes[j]);
                    }
                    else if (Struct.class.isAssignableFrom(parameterTypes[j])) {
                        AsmUtil.unboxStruct(mv, nativeParameterTypes[j]);
                    }
                }
                ++j;
            }
            mv.invokevirtual(className, stubName, CodegenUtils.sig(nativeReturnType, nativeParameterTypes));
            this.emitReturn(mv, returnType, nativeReturnType);
            String bufInvoke = null;
            if (bufferInvocationLabel != null) {
                mv.label(bufferInvocationLabel);
                bufInvoke = functionName + "$buf$" + this.nextMethodID.getAndIncrement();
                int k = 0;
                int lvar2 = 1;
                while (k < parameterTypes.length) {
                    lvar2 = this.loadParameter(mv, parameterTypes[k], lvar2);
                    ++k;
                }
                mv.invokevirtual(className, bufInvoke, CodegenUtils.sig(returnType, parameterTypes));
                AsmUtil.emitReturnOp(mv, returnType);
            }
            mv.visitMaxs(100, AsmUtil.calculateLocalVariableSpace(parameterTypes) + 10);
            mv.visitEnd();
            if (bufInvoke != null) {
                final SkinnyMethodAdapter bi = new SkinnyMethodAdapter(cv.visitMethod(17, bufInvoke, CodegenUtils.sig(returnType, parameterTypes), null, null));
                bi.start();
                bi.getstatic(CodegenUtils.p(AbstractNativeInterface.class), "ffi", CodegenUtils.ci(Invoker.class));
                bi.aload(0);
                bi.getfield(className, functionFieldName, CodegenUtils.ci(Function.class));
                this.generateBufferInvocation(bi, returnType, parameterTypes, annotations);
                bi.visitMaxs(100, AsmUtil.calculateLocalVariableSpace(parameterTypes) + 10);
                bi.visitEnd();
            }
        }
        cv.visitMethod(273, stubName, CodegenUtils.sig(nativeReturnType, nativeParameterTypes), null, null);
        compiler.compile(function, stubName, nativeReturnType, nativeParameterTypes, convention, !ignoreErrno);
    }
    
    private final void generateMethod(final ClassVisitor cv, final String className, final String functionName, final String functionFieldName, final Class returnType, final Class[] parameterTypes, final Annotation[][] parameterAnnotations, final CallingConvention convention, final boolean ignoreErrno) {
        final SkinnyMethodAdapter mv = new SkinnyMethodAdapter(cv.visitMethod(17, functionName, CodegenUtils.sig(returnType, parameterTypes), null, null));
        mv.start();
        mv.getstatic(CodegenUtils.p(AbstractNativeInterface.class), "ffi", CodegenUtils.ci(Invoker.class));
        mv.aload(0);
        mv.getfield(className, functionFieldName, CodegenUtils.ci(Function.class));
        if (convention == CallingConvention.DEFAULT && isFastIntegerMethod(returnType, parameterTypes)) {
            this.generateFastIntegerInvocation(mv, returnType, parameterTypes, parameterAnnotations, ignoreErrno);
        }
        else if (convention == CallingConvention.DEFAULT && AsmLibraryLoader.FAST_NUMERIC_AVAILABLE && isFastNumericMethod(returnType, parameterTypes)) {
            this.generateFastNumericInvocation(mv, returnType, parameterTypes, parameterAnnotations);
        }
        else {
            this.generateBufferInvocation(mv, returnType, parameterTypes, parameterAnnotations);
        }
        mv.visitMaxs(100, AsmUtil.calculateLocalVariableSpace(parameterTypes) + 10);
        mv.visitEnd();
    }
    
    private final void generateBufferInvocation(final SkinnyMethodAdapter mv, final Class returnType, final Class[] parameterTypes, final Annotation[][] annotations) {
        final boolean sessionRequired = isSessionRequired(parameterTypes);
        final int lvarSession = sessionRequired ? (AsmUtil.calculateLocalVariableSpace(parameterTypes) + 1) : -1;
        if (sessionRequired) {
            mv.newobj(CodegenUtils.p(InvocationSession.class));
            mv.dup();
            mv.invokespecial(InvocationSession.class, "<init>", Void.TYPE, new Class[0]);
            mv.astore(lvarSession);
        }
        mv.dup();
        mv.invokestatic(AsmRuntime.class, "newHeapInvocationBuffer", HeapInvocationBuffer.class, Function.class);
        int lvar = 1;
        for (int i = 0; i < parameterTypes.length; ++i) {
            mv.dup();
            if (isSessionRequired(parameterTypes[i])) {
                mv.aload(lvarSession);
                mv.swap();
            }
            lvar = this.loadParameter(mv, parameterTypes[i], lvar);
            final int parameterFlags = DefaultInvokerFactory.getParameterFlags(annotations[i]);
            final int nativeArrayFlags = DefaultInvokerFactory.getNativeArrayFlags(parameterFlags) | (((parameterFlags & 0x2) != 0x0) ? 4 : 0);
            if (parameterTypes[i].isArray() && parameterTypes[i].getComponentType().isPrimitive()) {
                mv.pushInt(nativeArrayFlags);
                this.marshal(mv, parameterTypes[i], Integer.TYPE);
            }
            else if (Pointer.class.isAssignableFrom(parameterTypes[i])) {
                mv.pushInt(nativeArrayFlags);
                this.marshal(mv, Pointer.class, Integer.TYPE);
            }
            else if (Address.class.isAssignableFrom(parameterTypes[i])) {
                this.marshal(mv, Pointer.class);
            }
            else if (Enum.class.isAssignableFrom(parameterTypes[i])) {
                this.marshal(mv, Enum.class);
            }
            else if (Buffer.class.isAssignableFrom(parameterTypes[i])) {
                mv.pushInt(nativeArrayFlags);
                this.marshal(mv, parameterTypes[i], Integer.TYPE);
            }
            else if (ByReference.class.isAssignableFrom(parameterTypes[i])) {
                mv.pushInt(nativeArrayFlags);
                this.sessionmarshal(mv, ByReference.class, Integer.TYPE);
            }
            else if (StringBuilder.class.isAssignableFrom(parameterTypes[i]) || StringBuffer.class.isAssignableFrom(parameterTypes[i])) {
                mv.pushInt(parameterFlags);
                mv.pushInt(nativeArrayFlags);
                this.sessionmarshal(mv, parameterTypes[i], Integer.TYPE, Integer.TYPE);
            }
            else if (CharSequence.class.isAssignableFrom(parameterTypes[i])) {
                this.marshal(mv, CharSequence.class);
            }
            else if (parameterTypes[i].isArray() && CharSequence.class.isAssignableFrom(parameterTypes[i].getComponentType())) {
                mv.pushInt(parameterFlags);
                mv.pushInt(nativeArrayFlags);
                this.sessionmarshal(mv, CharSequence[].class, Integer.TYPE, Integer.TYPE);
            }
            else if (Struct.class.isAssignableFrom(parameterTypes[i])) {
                mv.pushInt(parameterFlags);
                mv.pushInt(nativeArrayFlags);
                this.marshal(mv, Struct.class, Integer.TYPE, Integer.TYPE);
            }
            else if (parameterTypes[i].isArray() && Struct.class.isAssignableFrom(parameterTypes[i].getComponentType())) {
                mv.pushInt(parameterFlags);
                mv.pushInt(nativeArrayFlags);
                this.marshal(mv, Struct[].class, Integer.TYPE, Integer.TYPE);
            }
            else if (parameterTypes[i].isArray() && Pointer.class.isAssignableFrom(parameterTypes[i].getComponentType())) {
                mv.pushInt(parameterFlags);
                mv.pushInt(nativeArrayFlags);
                this.sessionmarshal(mv, Pointer[].class, Integer.TYPE, Integer.TYPE);
            }
            else {
                if (!parameterTypes[i].isPrimitive() && !Number.class.isAssignableFrom(parameterTypes[i])) {
                    throw new IllegalArgumentException("unsupported parameter type " + parameterTypes[i]);
                }
                this.emitInvocationBufferIntParameter(mv, parameterTypes[i]);
            }
        }
        String invokeMethod = null;
        Class nativeReturnType = null;
        if (NumberUtil.isPrimitiveInt(returnType) || Void.TYPE == returnType || Byte.class == returnType || Short.class == returnType || Integer.class == returnType) {
            invokeMethod = "invokeInt";
            nativeReturnType = Integer.TYPE;
        }
        else if (Long.class == returnType || Long.TYPE == returnType) {
            invokeMethod = "invokeLong";
            nativeReturnType = Long.TYPE;
        }
        else if (NativeLong.class == returnType) {
            invokeMethod = ((NativeLong.SIZE == 32) ? "invokeInt" : "invokeLong");
            nativeReturnType = (Class)((NativeLong.SIZE == 32) ? Integer.TYPE : Long.TYPE);
        }
        else if (Pointer.class == returnType || Address.class == returnType || Struct.class.isAssignableFrom(returnType) || String.class.isAssignableFrom(returnType)) {
            invokeMethod = "invokeAddress";
            nativeReturnType = Long.TYPE;
        }
        else if (Float.class == returnType || Float.TYPE == returnType) {
            invokeMethod = "invokeFloat";
            nativeReturnType = Float.TYPE;
        }
        else {
            if (Double.class != returnType && Double.TYPE != returnType) {
                throw new IllegalArgumentException("unsupported return type " + returnType);
            }
            invokeMethod = "invokeDouble";
            nativeReturnType = Double.TYPE;
        }
        mv.invokevirtual(Invoker.class, invokeMethod, nativeReturnType, Function.class, HeapInvocationBuffer.class);
        if (sessionRequired) {
            mv.aload(lvarSession);
            mv.invokevirtual(CodegenUtils.p(InvocationSession.class), "finish", "()V");
        }
        this.emitReturn(mv, returnType, nativeReturnType);
    }
    
    private final void generateFastIntegerInvocation(final SkinnyMethodAdapter mv, final Class returnType, final Class[] parameterTypes, final Annotation[][] annotations, final boolean ignoreErrno) {
        final Label bufferInvocationLabel = emitDirectCheck(mv, parameterTypes);
        final Class nativeIntType = getNativeIntType(returnType, parameterTypes);
        int i = 0;
        int lvar = 1;
        while (i < parameterTypes.length) {
            lvar = this.loadParameter(mv, parameterTypes[i], lvar);
            if (parameterTypes[i].isPrimitive()) {
                NumberUtil.widen(mv, parameterTypes[i], nativeIntType);
            }
            else if (Number.class.isAssignableFrom(parameterTypes[i])) {
                AsmUtil.unboxNumber(mv, parameterTypes[i], nativeIntType);
            }
            else if (Pointer.class.isAssignableFrom(parameterTypes[i])) {
                AsmUtil.unboxPointer(mv, nativeIntType);
            }
            else if (Struct.class.isAssignableFrom(parameterTypes[i])) {
                AsmUtil.unboxStruct(mv, nativeIntType);
            }
            ++i;
        }
        mv.invokevirtual(CodegenUtils.p(Invoker.class), getFastIntInvokerMethodName(parameterTypes.length, ignoreErrno, nativeIntType), getFastIntInvokerSignature(parameterTypes.length, nativeIntType));
        this.emitReturn(mv, returnType, nativeIntType);
        if (bufferInvocationLabel != null) {
            mv.label(bufferInvocationLabel);
            this.generateBufferInvocation(mv, returnType, parameterTypes, annotations);
        }
    }
    
    private final void generateFastNumericInvocation(final SkinnyMethodAdapter mv, final Class returnType, final Class[] parameterTypes, final Annotation[][] annotations) {
        final Label bufferInvocationLabel = emitDirectCheck(mv, parameterTypes);
        int i = 0;
        int lvar = 1;
        while (i < parameterTypes.length) {
            lvar = this.loadParameter(mv, parameterTypes[i], lvar);
            if (Pointer.class.isAssignableFrom(parameterTypes[i])) {
                AsmUtil.unboxPointer(mv, Long.TYPE);
            }
            else if (Struct.class.isAssignableFrom(parameterTypes[i])) {
                AsmUtil.unboxStruct(mv, Long.TYPE);
            }
            else {
                if (!parameterTypes[i].isPrimitive() && Number.class.isAssignableFrom(parameterTypes[i])) {
                    AsmUtil.unboxNumber(mv, parameterTypes[i], Long.TYPE);
                }
                if (Float.class == parameterTypes[i] || Float.TYPE == parameterTypes[i]) {
                    mv.invokestatic(Float.class, "floatToRawIntBits", Integer.TYPE, Float.TYPE);
                    mv.i2l();
                }
                else if (Double.class == parameterTypes[i] || Double.TYPE == parameterTypes[i]) {
                    mv.invokestatic(Double.class, "doubleToRawLongBits", Long.TYPE, Double.TYPE);
                }
                else {
                    NumberUtil.widen(mv, parameterTypes[i], Long.TYPE);
                }
            }
            ++i;
        }
        mv.invokevirtual(CodegenUtils.p(Invoker.class), getFastNumericInvokerMethodName(parameterTypes.length, returnType), getFastNumericInvokerSignature(parameterTypes.length));
        if (Float.class == returnType || Float.TYPE == returnType) {
            mv.l2i();
            mv.invokestatic(Float.class, "intBitsToFloat", Float.TYPE, Integer.TYPE);
        }
        else if (Double.class == returnType || Double.TYPE == returnType) {
            mv.invokestatic(Double.class, "longBitsToDouble", Double.TYPE, Long.TYPE);
        }
        this.emitReturn(mv, returnType, Long.TYPE);
        if (bufferInvocationLabel != null) {
            mv.label(bufferInvocationLabel);
            this.generateBufferInvocation(mv, returnType, parameterTypes, annotations);
        }
    }
    
    private static final Label emitDirectCheck(final SkinnyMethodAdapter mv, final Class[] parameterTypes) {
        final Label notFastInt = new Label();
        boolean needBufferInvocation = false;
        int i = 0;
        int lvar = 1;
        while (i < parameterTypes.length) {
            if (Pointer.class.isAssignableFrom(parameterTypes[i])) {
                mv.aload(lvar++);
                mv.invokestatic(AsmRuntime.class, "isDirect", Boolean.TYPE, Pointer.class);
                mv.iffalse(notFastInt);
                needBufferInvocation = true;
            }
            else if (Struct.class.isAssignableFrom(parameterTypes[i])) {
                mv.aload(lvar++);
                mv.invokestatic(AsmRuntime.class, "isDirect", Boolean.TYPE, Struct.class);
                mv.iffalse(notFastInt);
                needBufferInvocation = true;
            }
            else {
                lvar += AsmUtil.calculateLocalVariableSpace(parameterTypes[i]);
            }
            ++i;
        }
        return needBufferInvocation ? notFastInt : null;
    }
    
    private final void emitReturn(final SkinnyMethodAdapter mv, final Class returnType, final Class nativeIntType) {
        if (returnType.isPrimitive()) {
            if (Long.TYPE == returnType) {
                NumberUtil.widen(mv, nativeIntType, returnType);
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
                NumberUtil.narrow(mv, nativeIntType, returnType);
                mv.ireturn();
            }
        }
        else {
            this.boxValue(mv, returnType, nativeIntType);
            mv.areturn();
        }
    }
    
    private final int loadParameter(final SkinnyMethodAdapter mv, final Class parameterType, int lvar) {
        if (!parameterType.isPrimitive()) {
            mv.aload(lvar++);
        }
        else if (Long.TYPE == parameterType) {
            mv.lload(lvar);
            lvar += 2;
        }
        else if (Float.TYPE == parameterType) {
            mv.fload(lvar++);
        }
        else if (Double.TYPE == parameterType) {
            mv.dload(lvar);
            lvar += 2;
        }
        else {
            mv.iload(lvar++);
        }
        return lvar;
    }
    
    private static final Class<? extends Number> getNativeIntType(final Class returnType, final Class[] parameterTypes) {
        for (int i = 0; i < parameterTypes.length; ++i) {
            if (requiresLong(parameterTypes[i])) {
                return Long.TYPE;
            }
        }
        return (Class<? extends Number>)(requiresLong(returnType) ? Long.TYPE : Integer.TYPE);
    }
    
    static final String getFastIntInvokerMethodName(final int parameterCount, final boolean ignoreErrno, final Class nativeParamType) {
        final StringBuilder sb = new StringBuilder("invoke");
        if (ignoreErrno && Integer.TYPE == nativeParamType) {
            sb.append("NoErrno");
        }
        final String t = (Integer.TYPE == nativeParamType) ? "I" : "L";
        if (parameterCount < 1) {
            sb.append("V");
        }
        else {
            for (int i = 0; i < parameterCount; ++i) {
                sb.append(t);
            }
        }
        return sb.append("r").append(t).toString();
    }
    
    static final String getFastIntInvokerSignature(final int parameterCount, final Class nativeIntType) {
        final String t = (Integer.TYPE == nativeIntType) ? "I" : "J";
        final StringBuilder sb = new StringBuilder();
        sb.append("(");
        sb.append(CodegenUtils.ci(Function.class));
        for (int i = 0; i < parameterCount; ++i) {
            sb.append(t);
        }
        sb.append(")").append(t);
        return sb.toString();
    }
    
    static final String getFastNumericInvokerMethodName(final int parameterCount, final Class nativeParamType) {
        final StringBuilder sb = new StringBuilder("invoke");
        if (parameterCount < 1) {
            sb.append("V");
        }
        else {
            for (int i = 0; i < parameterCount; ++i) {
                sb.append("N");
            }
        }
        return sb.append("r").append("N").toString();
    }
    
    static final String getFastNumericInvokerSignature(final int parameterCount) {
        final StringBuilder sb = new StringBuilder();
        sb.append("(");
        sb.append(CodegenUtils.ci(Function.class));
        for (int i = 0; i < parameterCount; ++i) {
            sb.append("J");
        }
        sb.append(")").append("J");
        return sb.toString();
    }
    
    private final void boxStruct(final SkinnyMethodAdapter mv, final Class returnType) {
        mv.dup2();
        final Label nonnull = new Label();
        final Label end = new Label();
        mv.lconst_0();
        mv.lcmp();
        mv.ifne(nonnull);
        mv.pop2();
        mv.aconst_null();
        mv.go_to(end);
        mv.label(nonnull);
        mv.newobj(CodegenUtils.p(returnType));
        mv.dup();
        mv.invokespecial(returnType, "<init>", Void.TYPE, new Class[0]);
        mv.dup_x2();
        mv.invokestatic(AsmRuntime.class, "useMemory", Void.TYPE, Long.TYPE, Struct.class);
        mv.label(end);
    }
    
    private final void boxPrimitive(final SkinnyMethodAdapter mv, final Class primitiveType) {
        final Class objClass = NumberUtil.getBoxedClass(primitiveType);
        mv.invokestatic(objClass, "valueOf", objClass, primitiveType);
    }
    
    private final void boxNumber(final SkinnyMethodAdapter mv, final Class type, final Class nativeType) {
        Class primitiveClass = NumberUtil.getPrimitiveClass(type);
        if (Byte.class.isAssignableFrom(type)) {
            NumberUtil.narrow(mv, nativeType, Byte.TYPE);
        }
        else if (Character.class.isAssignableFrom(type)) {
            NumberUtil.narrow(mv, nativeType, Character.TYPE);
        }
        else if (Short.class.isAssignableFrom(type)) {
            NumberUtil.narrow(mv, nativeType, Short.TYPE);
        }
        else if (Integer.class.isAssignableFrom(type)) {
            NumberUtil.narrow(mv, nativeType, Integer.TYPE);
        }
        else if (Long.class.isAssignableFrom(type)) {
            NumberUtil.widen(mv, nativeType, Long.TYPE);
        }
        else if (NativeLong.class.isAssignableFrom(type)) {
            NumberUtil.widen(mv, nativeType, Long.TYPE);
            primitiveClass = Long.TYPE;
        }
        else if (Boolean.class.isAssignableFrom(type)) {
            NumberUtil.narrow(mv, nativeType, Boolean.TYPE);
        }
        else if (Float.class != type) {
            if (Double.class != type) {
                throw new IllegalArgumentException("invalid Number subclass");
            }
        }
        mv.invokestatic(type, "valueOf", type, primitiveClass);
    }
    
    private final void boxValue(final SkinnyMethodAdapter mv, final Class returnType, final Class nativeReturnType) {
        if (returnType == nativeReturnType) {
            return;
        }
        if (Boolean.class.isAssignableFrom(returnType)) {
            NumberUtil.narrow(mv, nativeReturnType, Boolean.TYPE);
            mv.invokestatic(Boolean.class, "valueOf", Boolean.class, Boolean.TYPE);
        }
        else if (Pointer.class.isAssignableFrom(returnType)) {
            mv.invokestatic(AsmRuntime.class, "pointerValue", Pointer.class, nativeReturnType);
        }
        else if (Address.class == returnType) {
            NumberUtil.widen(mv, nativeReturnType, Long.TYPE);
            mv.invokestatic(returnType, "valueOf", returnType, Long.TYPE);
        }
        else if (Struct.class.isAssignableFrom(returnType)) {
            NumberUtil.widen(mv, nativeReturnType, Long.TYPE);
            this.boxStruct(mv, returnType);
        }
        else if (Number.class.isAssignableFrom(returnType)) {
            this.boxNumber(mv, returnType, nativeReturnType);
        }
        else {
            if (String.class != returnType) {
                throw new IllegalArgumentException("cannot box value of type " + nativeReturnType + " to " + returnType);
            }
            NumberUtil.widen(mv, nativeReturnType, Long.TYPE);
            mv.invokestatic(AsmRuntime.class, "returnString", String.class, Long.TYPE);
        }
    }
    
    private final void emitInvocationBufferIntParameter(final SkinnyMethodAdapter mv, final Class parameterType) {
        String paramMethod = null;
        Class paramClass = Integer.TYPE;
        if (!parameterType.isPrimitive()) {
            AsmUtil.unboxNumber(mv, parameterType, null);
        }
        if (Byte.TYPE == parameterType || Byte.class == parameterType) {
            paramMethod = "putByte";
        }
        else if (Short.TYPE == parameterType || Short.class == parameterType) {
            paramMethod = "putShort";
        }
        else if (Integer.TYPE == parameterType || Integer.class == parameterType || Boolean.TYPE == parameterType) {
            paramMethod = "putInt";
        }
        else if (Long.TYPE == parameterType || Long.class == parameterType) {
            paramMethod = "putLong";
            paramClass = Long.TYPE;
        }
        else if (Float.TYPE == parameterType || Float.class == parameterType) {
            paramMethod = "putFloat";
            paramClass = Float.TYPE;
        }
        else if (Double.TYPE == parameterType || Double.class == parameterType) {
            paramMethod = "putDouble";
            paramClass = Double.TYPE;
        }
        else if (NativeLong.class.isAssignableFrom(parameterType) && Platform.getPlatform().longSize() == 32) {
            paramMethod = "putInt";
            paramClass = Integer.TYPE;
        }
        else {
            if (!NativeLong.class.isAssignableFrom(parameterType) || Platform.getPlatform().longSize() != 64) {
                throw new IllegalArgumentException("unsupported parameter type " + parameterType);
            }
            paramMethod = "putLong";
            paramClass = Long.TYPE;
        }
        mv.invokevirtual(HeapInvocationBuffer.class, paramMethod, Void.TYPE, paramClass);
    }
    
    private final void marshal(final SkinnyMethodAdapter mv, final Class... parameterTypes) {
        mv.invokestatic(CodegenUtils.p(AsmRuntime.class), "marshal", CodegenUtils.sig(Void.TYPE, CodegenUtils.ci(InvocationBuffer.class), parameterTypes));
    }
    
    private final void sessionmarshal(final SkinnyMethodAdapter mv, final Class... parameterTypes) {
        mv.invokestatic(CodegenUtils.p(AsmRuntime.class), "marshal", CodegenUtils.sig(Void.TYPE, CodegenUtils.ci(InvocationSession.class) + CodegenUtils.ci(InvocationBuffer.class), parameterTypes));
    }
    
    private static final Function getFunction(final long address, final Class returnType, final Class[] paramTypes, final boolean requiresErrno, final CallingConvention convention) {
        final Type[] nativeParamTypes = new Type[paramTypes.length];
        for (int i = 0; i < nativeParamTypes.length; ++i) {
            nativeParamTypes[i] = InvokerUtil.getNativeParameterType(paramTypes[i]);
        }
        return new Function(address, InvokerUtil.getNativeReturnType(returnType), nativeParamTypes, convention, requiresErrno);
    }
    
    private static boolean isSessionRequired(final Class parameterType) {
        return StringBuilder.class.isAssignableFrom(parameterType) || StringBuffer.class.isAssignableFrom(parameterType) || ByReference.class.isAssignableFrom(parameterType) || (parameterType.isArray() && Pointer.class.isAssignableFrom(parameterType.getComponentType())) || (parameterType.isArray() && CharSequence.class.isAssignableFrom(parameterType.getComponentType()));
    }
    
    private static boolean isSessionRequired(final Class[] parameterTypes) {
        for (int i = 0; i < parameterTypes.length; ++i) {
            if (isSessionRequired(parameterTypes[i])) {
                return true;
            }
        }
        return false;
    }
    
    static final boolean isFastNumericMethod(final Class returnType, final Class[] parameterTypes) {
        if (!AsmLibraryLoader.FAST_NUMERIC_AVAILABLE || parameterTypes.length > 6) {
            return false;
        }
        if (!isFastNumericResult(returnType)) {
            return false;
        }
        for (int i = 0; i < parameterTypes.length; ++i) {
            if (!isFastNumericParam(parameterTypes[i])) {
                return false;
            }
        }
        return Platform.getPlatform().getCPU() == Platform.CPU.I386 || Platform.getPlatform().getCPU() == Platform.CPU.X86_64;
    }
    
    static final boolean isFastIntegerMethod(final Class returnType, final Class[] parameterTypes) {
        if (parameterTypes.length > 3) {
            return false;
        }
        if (!isFastIntegerResult(returnType)) {
            return false;
        }
        for (int i = 0; i < parameterTypes.length; ++i) {
            if (!isFastIntegerParam(parameterTypes[i])) {
                return false;
            }
        }
        return Platform.getPlatform().getCPU() == Platform.CPU.I386 || Platform.getPlatform().getCPU() == Platform.CPU.X86_64;
    }
    
    static final boolean isInt32(final Class type) {
        return Boolean.class.isAssignableFrom(type) || Boolean.TYPE == type || Byte.class.isAssignableFrom(type) || Byte.TYPE == type || Short.class.isAssignableFrom(type) || Short.TYPE == type || Integer.class.isAssignableFrom(type) || Integer.TYPE == type;
    }
    
    static final boolean isInt32Result(final Class type) {
        return isInt32(type) || Void.class.isAssignableFrom(type) || Void.TYPE == type;
    }
    
    static final boolean isPointerResult(final Class type) {
        return Pointer.class.isAssignableFrom(type) || Struct.class.isAssignableFrom(type) || String.class.isAssignableFrom(type);
    }
    
    static final boolean isPointerParam(final Class type) {
        return Pointer.class.isAssignableFrom(type) || Struct.class.isAssignableFrom(type);
    }
    
    private static final boolean isFastIntegerResult(final Class type) {
        if (isInt32Result(type)) {
            return true;
        }
        final boolean isPointer = isPointerResult(type);
        if (isPointer && Platform.getPlatform().addressSize() == 32) {
            return true;
        }
        if (NativeLong.class.isAssignableFrom(type) && Platform.getPlatform().longSize() == 32) {
            return true;
        }
        final boolean isLong = Long.class == type || Long.TYPE == type;
        return Platform.getPlatform().addressSize() == 64 && AsmLibraryLoader.FAST_LONG_AVAILABLE && (isPointer || NativeLong.class.isAssignableFrom(type) || isLong);
    }
    
    private static final boolean isFastIntegerParam(final Class type) {
        if (isInt32(type)) {
            return true;
        }
        final boolean isPointer = isPointerParam(type);
        if (isPointer && Platform.getPlatform().addressSize() == 32) {
            return true;
        }
        if (NativeLong.class.isAssignableFrom(type) && Platform.getPlatform().longSize() == 32) {
            return true;
        }
        final boolean isLong = Long.class == type || Long.TYPE == type;
        return Platform.getPlatform().addressSize() == 64 && AsmLibraryLoader.FAST_LONG_AVAILABLE && (isPointer || NativeLong.class.isAssignableFrom(type) || isLong);
    }
    
    static final boolean isFastNumericResult(final Class type) {
        return isFastIntegerResult(type) || Long.class.isAssignableFrom(type) || Long.TYPE == type || NativeLong.class.isAssignableFrom(type) || Pointer.class.isAssignableFrom(type) || Struct.class.isAssignableFrom(type) || String.class.isAssignableFrom(type) || Float.TYPE == type || Float.class == type || Double.TYPE == type || Double.class == type;
    }
    
    static final boolean isFastNumericParam(final Class type) {
        return isFastIntegerParam(type) || Long.class.isAssignableFrom(type) || Long.TYPE == type || NativeLong.class.isAssignableFrom(type) || Pointer.class.isAssignableFrom(type) || Struct.class.isAssignableFrom(type) || Float.TYPE == type || Float.class == type || Double.TYPE == type || Double.class == type;
    }
    
    static final boolean isFastNumericAvailable() {
        try {
            Invoker.class.getDeclaredMethod("invokeNNNNNNrN", Function.class, Long.TYPE, Long.TYPE, Long.TYPE, Long.TYPE, Long.TYPE, Long.TYPE);
            return true;
        }
        catch (Throwable t) {
            return false;
        }
    }
    
    static final boolean isFastLongAvailable() {
        try {
            Invoker.class.getDeclaredMethod("invokeLLLLLLrL", Function.class, Long.TYPE, Long.TYPE, Long.TYPE, Long.TYPE, Long.TYPE, Long.TYPE);
            return true;
        }
        catch (Throwable t) {
            return false;
        }
    }
    
    private static final boolean requiresLong(final Class type) {
        return Long.class.isAssignableFrom(type) || Long.TYPE == type || (NativeLong.class.isAssignableFrom(type) && Platform.getPlatform().longSize() == 64) || (Pointer.class.isAssignableFrom(type) && Platform.getPlatform().addressSize() == 64) || (Struct.class.isAssignableFrom(type) && Platform.getPlatform().addressSize() == 64) || (String.class.isAssignableFrom(type) && Platform.getPlatform().addressSize() == 64);
    }
    
    public static void main(final String[] args) {
        System.setProperty("jaffl.compile.dump", "true");
        System.out.println("cpu=" + Platform.getPlatform().getCPU());
        final Map<LibraryOption, Object> options = new HashMap<LibraryOption, Object>();
        final TestLib lib = getInstance().loadLibrary(new Library("test"), TestLib.class, options);
        final Number result = lib.add_int32_t(1, 2);
        System.err.println("result=" + result);
        System.err.println("adding floats=" + lib.add_float(1.0f, 2.0f));
        System.err.println("adding doubles=" + lib.add_double(1.0, 2.0));
        final Pointer p = MemoryIO.allocateDirect(1024);
        lib.ptr_ret_int8_t(p, 0);
        lib.ptr_ret_int8_t(MemoryIO.allocate(1024), 0);
    }
    
    static {
        DEBUG = Boolean.getBoolean("jaffl.compile.dump");
        FAST_NUMERIC_AVAILABLE = isFastNumericAvailable();
        FAST_LONG_AVAILABLE = isFastLongAvailable();
    }
    
    private static final class SingletonHolder
    {
        static final LibraryLoader INSTANCE;
        
        static {
            INSTANCE = new AsmLibraryLoader();
        }
    }
    
    public abstract static class AbstractNativeInterface
    {
        public static final Invoker ffi;
        protected final Library library;
        
        public AbstractNativeInterface(final Library library) {
            this.library = library;
        }
        
        protected static final HeapInvocationBuffer newInvocationBuffer(final Function f) {
            return new HeapInvocationBuffer(f);
        }
        
        static {
            ffi = Invoker.getInstance();
        }
    }
    
    public static final class ToNativeProxy implements ToNativeConverter
    {
        private final ToNativeConverter converter;
        private final ToNativeContext ctx;
        
        public ToNativeProxy(final ToNativeConverter converter, final ToNativeContext ctx) {
            this.converter = converter;
            this.ctx = ctx;
        }
        
        public Object toNative(final Object value, final ToNativeContext unused) {
            return this.converter.toNative(value, this.ctx);
        }
        
        public Class nativeType() {
            return this.converter.nativeType();
        }
    }
    
    public static final class FromNativeProxy implements FromNativeConverter
    {
        private final FromNativeConverter converter;
        private final FromNativeContext ctx;
        
        public FromNativeProxy(final FromNativeConverter converter, final FromNativeContext ctx) {
            this.converter = converter;
            this.ctx = ctx;
        }
        
        public Object fromNative(final Object value, final FromNativeContext unused) {
            return this.converter.fromNative(value, this.ctx);
        }
        
        public Class nativeType() {
            return this.converter.nativeType();
        }
    }
    
    public static final class IntToLong implements FromNativeConverter, ToNativeConverter
    {
        public Object fromNative(final Object nativeValue, final FromNativeContext context) {
            return ((Number)nativeValue).longValue();
        }
        
        public Object toNative(final Object value, final ToNativeContext context) {
            return ((Number)value).intValue();
        }
        
        public Class nativeType() {
            return Integer.class;
        }
    }
    
    public interface TestLib
    {
        Integer add_int32_t(final Integer p0, final int p1);
        
        Float add_float(final float p0, final float p1);
        
        Double add_double(final Double p0, final double p1);
        
        byte ptr_ret_int8_t(final s8[] p0, final int p1);
        
        Byte ptr_ret_int8_t(final Pointer p0, final int p1);
        
        byte ptr_ret_int8_t(final s8 p0, final int p1);
        
        void not_found_function();
        
        public static final class s8 extends Struct
        {
            public final Signed8 s8;
            
            public s8() {
                this.s8 = new Signed8(this);
            }
        }
    }
}
