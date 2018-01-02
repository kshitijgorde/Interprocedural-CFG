// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.runtime.callback;

import org.jruby.exceptions.RaiseException;
import org.jruby.javasupport.util.RuntimeHelpers;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.RubyModule;
import org.jruby.RubyKernel;
import org.jruby.runtime.Arity;
import org.jruby.runtime.Block;
import org.jruby.runtime.CallType;
import org.jruby.RubyClass;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.CompiledBlockCallback19;
import org.jruby.org.objectweb.asm.ClassVisitor;
import org.jruby.compiler.impl.SkinnyMethodAdapter;
import org.jruby.runtime.CompiledBlockCallback;
import org.jruby.RubyInstanceConfig;
import org.jruby.org.objectweb.asm.MethodVisitor;
import org.jruby.org.objectweb.asm.Label;
import org.jruby.org.objectweb.asm.ClassWriter;
import java.security.AccessController;
import java.security.PrivilegedAction;
import org.jruby.util.CodegenUtils;
import org.jruby.Ruby;
import org.jruby.util.JRubyClassLoader;
import java.security.ProtectionDomain;
import org.jruby.org.objectweb.asm.Opcodes;
import org.jruby.runtime.CallbackFactory;

public class InvocationCallbackFactory extends CallbackFactory implements Opcodes
{
    private final Class type;
    final ProtectionDomain protectionDomain;
    protected final JRubyClassLoader classLoader;
    private final String typePath;
    protected final Ruby runtime;
    @Deprecated
    private static final String SUPER_CLASS;
    @Deprecated
    private static final String FAST_SUPER_CLASS;
    @Deprecated
    private static final String CALL_SIG;
    @Deprecated
    private static final String FAST_CALL_SIG;
    private static final String BLOCK_CALL_SIG;
    private static final String BLOCK_CALL_SIG19;
    private static final String IRUB;
    public static final int DISPATCHER_THREADCONTEXT_INDEX = 1;
    public static final int DISPATCHER_SELF_INDEX = 2;
    public static final int DISPATCHER_RUBYMODULE_INDEX = 3;
    public static final int DISPATCHER_METHOD_INDEX = 4;
    public static final int DISPATCHER_NAME_INDEX = 5;
    public static final int DISPATCHER_ARGS_INDEX = 6;
    public static final int DISPATCHER_CALLTYPE_INDEX = 7;
    public static final int DISPATCHER_BLOCK_INDEX = 8;
    public static final int DISPATCHER_RUNTIME_INDEX = 9;
    private static final int METHOD_ARGS_INDEX = 2;
    
    public InvocationCallbackFactory(final Ruby runtime, final Class type, final ClassLoader classLoader) {
        this.type = type;
        if (classLoader instanceof JRubyClassLoader) {
            this.classLoader = (JRubyClassLoader)classLoader;
        }
        else {
            this.classLoader = new JRubyClassLoader(classLoader);
        }
        this.typePath = CodegenUtils.p(type);
        this.runtime = runtime;
        final SecurityManager sm = System.getSecurityManager();
        if (sm == null) {
            this.protectionDomain = type.getProtectionDomain();
        }
        else {
            this.protectionDomain = AccessController.doPrivileged((PrivilegedAction<ProtectionDomain>)new PrivilegedAction<ProtectionDomain>() {
                public ProtectionDomain run() {
                    return type.getProtectionDomain();
                }
            });
        }
    }
    
    @Deprecated
    private Class getReturnClass(final String method, final Class[] args) throws Exception {
        return this.type.getMethod(method, (Class[])args).getReturnType();
    }
    
    @Deprecated
    private ClassWriter createCtor(final String namePath) throws Exception {
        final ClassWriter cw = new ClassWriter(1);
        cw.visit(48, 33, namePath, null, InvocationCallbackFactory.SUPER_CLASS, null);
        final MethodVisitor mv = cw.visitMethod(1, "<init>", "()V", null, null);
        mv.visitCode();
        mv.visitVarInsn(25, 0);
        mv.visitMethodInsn(183, InvocationCallbackFactory.SUPER_CLASS, "<init>", "()V");
        final Label line = new Label();
        mv.visitLineNumber(0, line);
        mv.visitInsn(177);
        mv.visitMaxs(1, 1);
        mv.visitEnd();
        return cw;
    }
    
    @Deprecated
    private ClassWriter createCtorFast(final String namePath) throws Exception {
        final ClassWriter cw = new ClassWriter(1);
        cw.visit(48, 33, namePath, null, InvocationCallbackFactory.FAST_SUPER_CLASS, null);
        final MethodVisitor mv = cw.visitMethod(1, "<init>", "()V", null, null);
        mv.visitCode();
        mv.visitVarInsn(25, 0);
        mv.visitMethodInsn(183, InvocationCallbackFactory.FAST_SUPER_CLASS, "<init>", "()V");
        final Label line = new Label();
        mv.visitLineNumber(0, line);
        mv.visitInsn(177);
        mv.visitMaxs(1, 1);
        mv.visitEnd();
        return cw;
    }
    
    @Deprecated
    private ClassWriter createBlockCtor(final String namePath, final Class fieldClass) throws Exception {
        final ClassWriter cw = new ClassWriter(3);
        cw.visit(RubyInstanceConfig.JAVA_VERSION, 33, namePath, null, CodegenUtils.p(CompiledBlockCallback.class), null);
        cw.visitField(18, "$scriptObject", CodegenUtils.ci(fieldClass), null, null);
        final SkinnyMethodAdapter mv = new SkinnyMethodAdapter(cw, 1, "<init>", CodegenUtils.sig(Void.TYPE, CodegenUtils.params(Object.class)), null, null);
        mv.start();
        mv.aload(0);
        mv.invokespecial(CodegenUtils.p(CompiledBlockCallback.class), "<init>", CodegenUtils.sig(Void.TYPE, new Class[0]));
        mv.aload(0);
        mv.aload(1);
        mv.checkcast(CodegenUtils.p(fieldClass));
        mv.putfield(namePath, "$scriptObject", CodegenUtils.ci(fieldClass));
        mv.voidreturn();
        mv.end();
        return cw;
    }
    
    @Deprecated
    private ClassWriter createBlockCtor19(final String namePath, final Class fieldClass) throws Exception {
        final ClassWriter cw = new ClassWriter(3);
        cw.visit(RubyInstanceConfig.JAVA_VERSION, 33, namePath, null, CodegenUtils.p(Object.class), new String[] { CodegenUtils.p(CompiledBlockCallback19.class) });
        cw.visitField(18, "$scriptObject", CodegenUtils.ci(fieldClass), null, null);
        final SkinnyMethodAdapter mv = new SkinnyMethodAdapter(cw, 1, "<init>", CodegenUtils.sig(Void.TYPE, CodegenUtils.params(Object.class)), null, null);
        mv.start();
        mv.aload(0);
        mv.invokespecial(CodegenUtils.p(Object.class), "<init>", CodegenUtils.sig(Void.TYPE, new Class[0]));
        mv.aload(0);
        mv.aload(1);
        mv.checkcast(CodegenUtils.p(fieldClass));
        mv.putfield(namePath, "$scriptObject", CodegenUtils.ci(fieldClass));
        mv.voidreturn();
        mv.end();
        return cw;
    }
    
    @Deprecated
    private Class tryClass(final String name) {
        try {
            return this.classLoader.loadClass(name);
        }
        catch (Exception e) {
            return null;
        }
    }
    
    @Deprecated
    private MethodVisitor startCall(final ClassWriter cw) {
        final MethodVisitor mv = cw.visitMethod(1, "call", InvocationCallbackFactory.CALL_SIG, null, null);
        mv.visitCode();
        final Label line = new Label();
        mv.visitLineNumber(0, line);
        mv.visitVarInsn(25, 1);
        mv.visitTypeInsn(192, this.typePath);
        return mv;
    }
    
    @Deprecated
    private MethodVisitor startCallS(final ClassWriter cw) {
        final MethodVisitor mv = cw.visitMethod(1, "call", InvocationCallbackFactory.CALL_SIG, null, null);
        mv.visitCode();
        final Label line = new Label();
        mv.visitLineNumber(0, line);
        mv.visitVarInsn(25, 1);
        this.checkCast(mv, IRubyObject.class);
        return mv;
    }
    
    @Deprecated
    private MethodVisitor startCallFast(final ClassWriter cw) {
        final MethodVisitor mv = cw.visitMethod(1, "call", InvocationCallbackFactory.FAST_CALL_SIG, null, null);
        mv.visitCode();
        final Label line = new Label();
        mv.visitLineNumber(0, line);
        mv.visitVarInsn(25, 1);
        mv.visitTypeInsn(192, this.typePath);
        return mv;
    }
    
    @Deprecated
    private MethodVisitor startDispatcher(final ClassWriter cw) {
        final MethodVisitor mv = cw.visitMethod(1, "callMethod", CodegenUtils.sig(IRubyObject.class, CodegenUtils.params(ThreadContext.class, IRubyObject.class, RubyClass.class, Integer.TYPE, String.class, IRubyObject[].class, CallType.class, Block.class)), null, null);
        mv.visitCode();
        final Label line = new Label();
        mv.visitLineNumber(0, line);
        mv.visitVarInsn(25, 2);
        mv.visitTypeInsn(192, this.typePath);
        return mv;
    }
    
    @Deprecated
    private MethodVisitor startCallSFast(final ClassWriter cw) {
        final MethodVisitor mv = cw.visitMethod(1, "call", InvocationCallbackFactory.FAST_CALL_SIG, null, null);
        mv.visitCode();
        final Label line = new Label();
        mv.visitLineNumber(0, line);
        mv.visitVarInsn(25, 1);
        mv.visitTypeInsn(192, InvocationCallbackFactory.IRUB);
        return mv;
    }
    
    @Deprecated
    private SkinnyMethodAdapter startBlockCall(final ClassWriter cw) {
        final SkinnyMethodAdapter mv = new SkinnyMethodAdapter(cw, 4113, "call", InvocationCallbackFactory.BLOCK_CALL_SIG, null, null);
        mv.visitCode();
        final Label line = new Label();
        mv.visitLineNumber(0, line);
        return mv;
    }
    
    @Deprecated
    private SkinnyMethodAdapter startBlockCall19(final ClassWriter cw) {
        final SkinnyMethodAdapter mv = new SkinnyMethodAdapter(cw, 4113, "call", InvocationCallbackFactory.BLOCK_CALL_SIG19, null, null);
        mv.visitCode();
        final Label line = new Label();
        mv.visitLineNumber(0, line);
        return mv;
    }
    
    @Deprecated
    protected Class endCall(final ClassWriter cw, final MethodVisitor mv, final String name) {
        mv.visitEnd();
        cw.visitEnd();
        final byte[] code = cw.toByteArray();
        return this.classLoader.defineClass(name, code, this.protectionDomain);
    }
    
    @Deprecated
    public Callback getMethod(final String method) {
        final String mname = this.type.getName() + "Callback$" + method + "_0";
        final String mnamePath = this.typePath + "Callback$" + method + "_0";
        synchronized (this.runtime.getJRubyClassLoader()) {
            Class c = this.tryClass(mname);
            try {
                if (c == null) {
                    final Class[] signature = { Block.class };
                    final Class ret = this.getReturnClass(method, signature);
                    final ClassWriter cw = this.createCtor(mnamePath);
                    final MethodVisitor mv = this.startCall(cw);
                    mv.visitVarInsn(25, 3);
                    mv.visitMethodInsn(182, this.typePath, method, CodegenUtils.sig(ret, signature));
                    mv.visitInsn(176);
                    mv.visitMaxs(1, 3);
                    c = this.endCall(cw, mv, mname);
                }
                final InvocationCallback ic = c.newInstance();
                ic.setArity(Arity.noArguments());
                ic.setJavaName(method);
                return ic;
            }
            catch (IllegalArgumentException e) {
                throw e;
            }
            catch (Exception e2) {
                throw new IllegalArgumentException(e2.getMessage());
            }
        }
    }
    
    @Deprecated
    public Callback getMethod(final String method, final Class arg1) {
        final String mname = this.type.getName() + "Callback$" + method + "_1";
        final String mnamePath = this.typePath + "Callback$" + method + "_1";
        synchronized (this.runtime.getJRubyClassLoader()) {
            Class c = this.tryClass(mname);
            try {
                final Class[] descriptor = { arg1 };
                if (c == null) {
                    final Class[] signature = { arg1, Block.class };
                    final Class ret = this.getReturnClass(method, signature);
                    final ClassWriter cw = this.createCtor(mnamePath);
                    final MethodVisitor mv = this.startCall(cw);
                    this.loadArguments(mv, 2, 1, descriptor);
                    mv.visitVarInsn(25, 3);
                    mv.visitMethodInsn(182, this.typePath, method, CodegenUtils.sig(ret, signature));
                    mv.visitInsn(176);
                    mv.visitMaxs(3, 3);
                    c = this.endCall(cw, mv, mname);
                }
                final InvocationCallback ic = c.newInstance();
                ic.setArity(Arity.singleArgument());
                ic.setArgumentTypes(descriptor);
                ic.setJavaName(method);
                return ic;
            }
            catch (IllegalArgumentException e) {
                throw e;
            }
            catch (Exception e2) {
                throw new IllegalArgumentException(e2.getMessage());
            }
        }
    }
    
    @Deprecated
    public Callback getMethod(final String method, final Class arg1, final Class arg2) {
        final String mname = this.type.getName() + "Callback$" + method + "_2";
        final String mnamePath = this.typePath + "Callback$" + method + "_2";
        synchronized (this.runtime.getJRubyClassLoader()) {
            Class c = this.tryClass(mname);
            try {
                final Class[] descriptor = { arg1, arg2 };
                if (c == null) {
                    final Class[] signature = { arg1, arg2, Block.class };
                    final Class ret = this.getReturnClass(method, signature);
                    final ClassWriter cw = this.createCtor(mnamePath);
                    final MethodVisitor mv = this.startCall(cw);
                    this.loadArguments(mv, 2, 2, descriptor);
                    mv.visitVarInsn(25, 3);
                    mv.visitMethodInsn(182, this.typePath, method, CodegenUtils.sig(ret, signature));
                    mv.visitInsn(176);
                    mv.visitMaxs(4, 3);
                    c = this.endCall(cw, mv, mname);
                }
                final InvocationCallback ic = c.newInstance();
                ic.setArity(Arity.twoArguments());
                ic.setArgumentTypes(descriptor);
                ic.setJavaName(method);
                return ic;
            }
            catch (IllegalArgumentException e) {
                throw e;
            }
            catch (Exception e2) {
                throw new IllegalArgumentException(e2.getMessage());
            }
        }
    }
    
    @Deprecated
    public Callback getMethod(final String method, final Class arg1, final Class arg2, final Class arg3) {
        final String mname = this.type.getName() + "Callback$" + method + "_3";
        final String mnamePath = this.typePath + "Callback$" + method + "_3";
        synchronized (this.runtime.getJRubyClassLoader()) {
            Class c = this.tryClass(mname);
            try {
                final Class[] descriptor = { arg1, arg2, arg3 };
                if (c == null) {
                    final Class[] signature = { arg1, arg2, arg3, Block.class };
                    final Class ret = this.getReturnClass(method, descriptor);
                    final ClassWriter cw = this.createCtor(mnamePath);
                    final MethodVisitor mv = this.startCall(cw);
                    this.loadArguments(mv, 2, 3, descriptor);
                    mv.visitVarInsn(25, 3);
                    mv.visitMethodInsn(182, this.typePath, method, CodegenUtils.sig(ret, signature));
                    mv.visitInsn(176);
                    mv.visitMaxs(5, 3);
                    c = this.endCall(cw, mv, mname);
                }
                final InvocationCallback ic = c.newInstance();
                ic.setArity(Arity.fixed(3));
                ic.setArgumentTypes(descriptor);
                ic.setJavaName(method);
                return ic;
            }
            catch (IllegalArgumentException e) {
                throw e;
            }
            catch (Exception e2) {
                throw new IllegalArgumentException(e2.getMessage());
            }
        }
    }
    
    @Deprecated
    public Callback getSingletonMethod(final String method) {
        final String mname = this.type.getName() + "Callback$" + method + "S0";
        final String mnamePath = this.typePath + "Callback$" + method + "S0";
        synchronized (this.runtime.getJRubyClassLoader()) {
            Class c = this.tryClass(mname);
            try {
                if (c == null) {
                    final Class[] signature = { RubyKernel.IRUBY_OBJECT, Block.class };
                    final Class ret = this.getReturnClass(method, signature);
                    final ClassWriter cw = this.createCtor(mnamePath);
                    final MethodVisitor mv = this.startCallS(cw);
                    mv.visitVarInsn(25, 3);
                    mv.visitMethodInsn(184, this.typePath, method, CodegenUtils.sig(ret, signature));
                    mv.visitInsn(176);
                    mv.visitMaxs(1, 3);
                    c = this.endCall(cw, mv, mname);
                }
                final InvocationCallback ic = c.newInstance();
                ic.setArity(Arity.noArguments());
                ic.setJavaName(method);
                ic.setSingleton(true);
                return ic;
            }
            catch (IllegalArgumentException e) {
                throw e;
            }
            catch (Exception e2) {
                throw new IllegalArgumentException(e2.getMessage());
            }
        }
    }
    
    @Deprecated
    public Callback getSingletonMethod(final String method, final Class arg1) {
        final String mname = this.type.getName() + "Callback$" + method + "_S1";
        final String mnamePath = this.typePath + "Callback$" + method + "_S1";
        synchronized (this.runtime.getJRubyClassLoader()) {
            Class c = this.tryClass(mname);
            try {
                final Class[] descriptor = { arg1 };
                if (c == null) {
                    final Class[] signature = { RubyKernel.IRUBY_OBJECT, arg1, Block.class };
                    final Class ret = this.getReturnClass(method, signature);
                    final ClassWriter cw = this.createCtor(mnamePath);
                    final MethodVisitor mv = this.startCallS(cw);
                    this.loadArguments(mv, 2, 1, descriptor);
                    mv.visitVarInsn(25, 3);
                    mv.visitMethodInsn(184, this.typePath, method, CodegenUtils.sig(ret, signature));
                    mv.visitInsn(176);
                    mv.visitMaxs(3, 3);
                    c = this.endCall(cw, mv, mname);
                }
                final InvocationCallback ic = c.newInstance();
                ic.setArity(Arity.singleArgument());
                ic.setArgumentTypes(descriptor);
                ic.setJavaName(method);
                ic.setSingleton(true);
                return ic;
            }
            catch (IllegalArgumentException e) {
                throw e;
            }
            catch (Exception e2) {
                throw new IllegalArgumentException(e2.getMessage());
            }
        }
    }
    
    @Deprecated
    public Callback getSingletonMethod(final String method, final Class arg1, final Class arg2) {
        final String mname = this.type.getName() + "Callback$" + method + "_S2";
        final String mnamePath = this.typePath + "Callback$" + method + "_S2";
        synchronized (this.runtime.getJRubyClassLoader()) {
            Class c = this.tryClass(mname);
            try {
                final Class[] descriptor = { arg1, arg2 };
                if (c == null) {
                    final Class[] signature = { RubyKernel.IRUBY_OBJECT, arg1, arg2, Block.class };
                    final Class ret = this.getReturnClass(method, signature);
                    final ClassWriter cw = this.createCtor(mnamePath);
                    final MethodVisitor mv = this.startCallS(cw);
                    this.loadArguments(mv, 2, 2, descriptor);
                    mv.visitVarInsn(25, 3);
                    mv.visitMethodInsn(184, this.typePath, method, CodegenUtils.sig(ret, signature));
                    mv.visitInsn(176);
                    mv.visitMaxs(4, 4);
                    c = this.endCall(cw, mv, mname);
                }
                final InvocationCallback ic = c.newInstance();
                ic.setArity(Arity.twoArguments());
                ic.setArgumentTypes(descriptor);
                ic.setJavaName(method);
                ic.setSingleton(true);
                return ic;
            }
            catch (IllegalArgumentException e) {
                throw e;
            }
            catch (Exception e2) {
                throw new IllegalArgumentException(e2.getMessage());
            }
        }
    }
    
    @Deprecated
    public Callback getSingletonMethod(final String method, final Class arg1, final Class arg2, final Class arg3) {
        final String mname = this.type.getName() + "Callback$" + method + "_S3";
        final String mnamePath = this.typePath + "Callback$" + method + "_S3";
        synchronized (this.runtime.getJRubyClassLoader()) {
            Class c = this.tryClass(mname);
            try {
                final Class[] descriptor = { arg1, arg2, arg3 };
                if (c == null) {
                    final Class[] signature = { RubyKernel.IRUBY_OBJECT, arg1, arg2, arg3, Block.class };
                    final Class ret = this.getReturnClass(method, signature);
                    final ClassWriter cw = this.createCtor(mnamePath);
                    final MethodVisitor mv = this.startCallS(cw);
                    this.loadArguments(mv, 2, 3, descriptor);
                    mv.visitVarInsn(25, 3);
                    mv.visitMethodInsn(184, this.typePath, method, CodegenUtils.sig(ret, signature));
                    mv.visitInsn(176);
                    mv.visitMaxs(5, 3);
                    c = this.endCall(cw, mv, mname);
                }
                final InvocationCallback ic = c.newInstance();
                ic.setArity(Arity.fixed(3));
                ic.setArgumentTypes(descriptor);
                ic.setJavaName(method);
                ic.setSingleton(true);
                return ic;
            }
            catch (IllegalArgumentException e) {
                throw e;
            }
            catch (Exception e2) {
                throw new IllegalArgumentException(e2.getMessage());
            }
        }
    }
    
    @Deprecated
    public Callback getBlockMethod(final String method) {
        return new ReflectionCallback(this.type, method, new Class[] { RubyKernel.IRUBY_OBJECT, RubyKernel.IRUBY_OBJECT }, false, true, Arity.fixed(2), false);
    }
    
    @Deprecated
    public CompiledBlockCallback getBlockCallback(final String method, final Object scriptObject) {
        final Class typeClass = scriptObject.getClass();
        final String typePathString = CodegenUtils.p(typeClass);
        final String mname = typeClass.getName() + "BlockCallback$" + method + "xx1";
        final String mnamePath = typePathString + "BlockCallback$" + method + "xx1";
        synchronized (this.classLoader) {
            Class c = this.tryClass(mname);
            try {
                if (c == null) {
                    final ClassWriter cw = this.createBlockCtor(mnamePath, typeClass);
                    final SkinnyMethodAdapter mv = this.startBlockCall(cw);
                    mv.aload(0);
                    mv.getfield(mnamePath, "$scriptObject", CodegenUtils.ci(typeClass));
                    mv.aload(1);
                    mv.aload(2);
                    mv.aload(3);
                    mv.invokestatic(typePathString, method, CodegenUtils.sig(RubyKernel.IRUBY_OBJECT, "L" + typePathString + ";", ThreadContext.class, RubyKernel.IRUBY_OBJECT, IRubyObject.class));
                    mv.areturn();
                    mv.visitMaxs(2, 3);
                    c = this.endCall(cw, mv, mname);
                }
                final CompiledBlockCallback ic = c.getConstructor(Object.class).newInstance(scriptObject);
                return ic;
            }
            catch (IllegalArgumentException e) {
                throw e;
            }
            catch (Exception e2) {
                e2.printStackTrace();
                throw new IllegalArgumentException(e2.getMessage());
            }
        }
    }
    
    @Deprecated
    public CompiledBlockCallback19 getBlockCallback19(final String method, final Object scriptObject) {
        final Class typeClass = scriptObject.getClass();
        final String typePathString = CodegenUtils.p(typeClass);
        final String mname = typeClass.getName() + "BlockCallback$" + method + "xx1";
        final String mnamePath = typePathString + "BlockCallback$" + method + "xx1";
        synchronized (this.classLoader) {
            Class c = this.tryClass(mname);
            try {
                if (c == null) {
                    final ClassWriter cw = this.createBlockCtor19(mnamePath, typeClass);
                    final SkinnyMethodAdapter mv = this.startBlockCall19(cw);
                    mv.aload(0);
                    mv.getfield(mnamePath, "$scriptObject", CodegenUtils.ci(typeClass));
                    mv.aload(1);
                    mv.aload(2);
                    mv.aload(3);
                    mv.aload(4);
                    mv.invokestatic(typePathString, method, CodegenUtils.sig(IRubyObject.class, "L" + typePathString + ";", ThreadContext.class, IRubyObject.class, IRubyObject[].class, Block.class));
                    mv.areturn();
                    mv.visitMaxs(2, 3);
                    c = this.endCall(cw, mv, mname);
                }
                final CompiledBlockCallback19 ic = c.getConstructor(Object.class).newInstance(scriptObject);
                return ic;
            }
            catch (IllegalArgumentException e) {
                throw e;
            }
            catch (Exception e2) {
                e2.printStackTrace();
                throw new IllegalArgumentException(e2.getMessage());
            }
        }
    }
    
    @Deprecated
    public Callback getOptSingletonMethod(final String method) {
        final String mname = this.type.getName() + "Callback$" + method + "_Sopt";
        final String mnamePath = this.typePath + "Callback$" + method + "_Sopt";
        synchronized (this.runtime.getJRubyClassLoader()) {
            Class c = this.tryClass(mname);
            try {
                if (c == null) {
                    final Class[] signature = { RubyKernel.IRUBY_OBJECT, IRubyObject[].class, Block.class };
                    final Class ret = this.getReturnClass(method, signature);
                    final ClassWriter cw = this.createCtor(mnamePath);
                    final MethodVisitor mv = this.startCallS(cw);
                    mv.visitVarInsn(25, 2);
                    this.checkCast(mv, IRubyObject[].class);
                    mv.visitVarInsn(25, 3);
                    mv.visitMethodInsn(184, this.typePath, method, CodegenUtils.sig(ret, signature));
                    mv.visitInsn(176);
                    mv.visitMaxs(2, 3);
                    c = this.endCall(cw, mv, mname);
                }
                final InvocationCallback ic = c.newInstance();
                ic.setArity(Arity.optional());
                ic.setArgumentTypes(InvocationCallback.OPTIONAL_ARGS);
                ic.setJavaName(method);
                ic.setSingleton(true);
                return ic;
            }
            catch (IllegalArgumentException e) {
                throw e;
            }
            catch (Exception e2) {
                throw new IllegalArgumentException(e2.getMessage());
            }
        }
    }
    
    @Deprecated
    public Callback getOptMethod(final String method) {
        final String mname = this.type.getName() + "Callback$" + method + "_opt";
        final String mnamePath = this.typePath + "Callback$" + method + "_opt";
        synchronized (this.runtime.getJRubyClassLoader()) {
            Class c = this.tryClass(mname);
            try {
                if (c == null) {
                    final Class[] signature = { IRubyObject[].class, Block.class };
                    final Class ret = this.getReturnClass(method, signature);
                    final ClassWriter cw = this.createCtor(mnamePath);
                    final MethodVisitor mv = this.startCall(cw);
                    mv.visitVarInsn(25, 2);
                    this.checkCast(mv, IRubyObject[].class);
                    mv.visitVarInsn(25, 3);
                    mv.visitMethodInsn(182, this.typePath, method, CodegenUtils.sig(ret, signature));
                    mv.visitInsn(176);
                    mv.visitMaxs(2, 3);
                    c = this.endCall(cw, mv, mname);
                }
                final InvocationCallback ic = c.newInstance();
                ic.setArity(Arity.optional());
                ic.setArgumentTypes(InvocationCallback.OPTIONAL_ARGS);
                ic.setJavaName(method);
                return ic;
            }
            catch (IllegalArgumentException e) {
                throw e;
            }
            catch (Exception e2) {
                throw new IllegalArgumentException(e2.getMessage());
            }
        }
    }
    
    @Deprecated
    public Callback getFastMethod(final String method) {
        final String mname = this.type.getName() + "Callback$" + method + "_F0";
        final String mnamePath = this.typePath + "Callback$" + method + "_F0";
        synchronized (this.runtime.getJRubyClassLoader()) {
            Class c = this.tryClass(mname);
            try {
                if (c == null) {
                    final Class ret = this.getReturnClass(method, new Class[0]);
                    final ClassWriter cw = this.createCtorFast(mnamePath);
                    final MethodVisitor mv = this.startCallFast(cw);
                    mv.visitMethodInsn(182, this.typePath, method, CodegenUtils.sig(ret, new Class[0]));
                    mv.visitInsn(176);
                    mv.visitMaxs(1, 3);
                    c = this.endCall(cw, mv, mname);
                }
                final FastInvocationCallback ic = c.newInstance();
                ic.setArity(Arity.noArguments());
                ic.setJavaName(method);
                return ic;
            }
            catch (IllegalArgumentException e) {
                throw e;
            }
            catch (Exception e2) {
                throw new IllegalArgumentException(e2.getMessage());
            }
        }
    }
    
    @Deprecated
    public Callback getFastMethod(final String method, final Class arg1) {
        final String mname = this.type.getName() + "Callback$" + method + "_F1";
        final String mnamePath = this.typePath + "Callback$" + method + "_F1";
        synchronized (this.runtime.getJRubyClassLoader()) {
            Class c = this.tryClass(mname);
            try {
                final Class[] descriptor = { arg1 };
                if (c == null) {
                    final Class[] signature = descriptor;
                    final Class ret = this.getReturnClass(method, signature);
                    final ClassWriter cw = this.createCtorFast(mnamePath);
                    final MethodVisitor mv = this.startCallFast(cw);
                    this.loadArguments(mv, 2, 1, descriptor);
                    mv.visitMethodInsn(182, this.typePath, method, CodegenUtils.sig(ret, signature));
                    mv.visitInsn(176);
                    mv.visitMaxs(3, 3);
                    c = this.endCall(cw, mv, mname);
                }
                final FastInvocationCallback ic = c.newInstance();
                ic.setArity(Arity.singleArgument());
                ic.setArgumentTypes(descriptor);
                ic.setJavaName(method);
                return ic;
            }
            catch (IllegalArgumentException e) {
                throw e;
            }
            catch (Exception e2) {
                throw new IllegalArgumentException(e2.getMessage());
            }
        }
    }
    
    @Deprecated
    public Callback getFastMethod(final String method, final Class arg1, final Class arg2) {
        final String mname = this.type.getName() + "Callback$" + method + "_F2";
        final String mnamePath = this.typePath + "Callback$" + method + "_F2";
        synchronized (this.runtime.getJRubyClassLoader()) {
            Class c = this.tryClass(mname);
            try {
                final Class[] descriptor = { arg1, arg2 };
                if (c == null) {
                    final Class[] signature = descriptor;
                    final Class ret = this.getReturnClass(method, signature);
                    final ClassWriter cw = this.createCtorFast(mnamePath);
                    final MethodVisitor mv = this.startCallFast(cw);
                    this.loadArguments(mv, 2, 2, descriptor);
                    mv.visitMethodInsn(182, this.typePath, method, CodegenUtils.sig(ret, signature));
                    mv.visitInsn(176);
                    mv.visitMaxs(4, 3);
                    c = this.endCall(cw, mv, mname);
                }
                final FastInvocationCallback ic = c.newInstance();
                ic.setArity(Arity.twoArguments());
                ic.setArgumentTypes(descriptor);
                ic.setJavaName(method);
                return ic;
            }
            catch (IllegalArgumentException e) {
                throw e;
            }
            catch (Exception e2) {
                throw new IllegalArgumentException(e2.getMessage());
            }
        }
    }
    
    @Deprecated
    public Callback getFastMethod(final String method, final Class arg1, final Class arg2, final Class arg3) {
        final String mname = this.type.getName() + "Callback$" + method + "_F3";
        final String mnamePath = this.typePath + "Callback$" + method + "_F3";
        synchronized (this.runtime.getJRubyClassLoader()) {
            Class c = this.tryClass(mname);
            try {
                final Class[] descriptor = { arg1, arg2, arg3 };
                if (c == null) {
                    final Class[] signature = descriptor;
                    final Class ret = this.getReturnClass(method, signature);
                    final ClassWriter cw = this.createCtorFast(mnamePath);
                    final MethodVisitor mv = this.startCallFast(cw);
                    this.loadArguments(mv, 2, 3, descriptor);
                    mv.visitMethodInsn(182, this.typePath, method, CodegenUtils.sig(ret, signature));
                    mv.visitInsn(176);
                    mv.visitMaxs(5, 3);
                    c = this.endCall(cw, mv, mname);
                }
                final FastInvocationCallback ic = c.newInstance();
                ic.setArity(Arity.fixed(3));
                ic.setArgumentTypes(descriptor);
                ic.setJavaName(method);
                return ic;
            }
            catch (IllegalArgumentException e) {
                throw e;
            }
            catch (Exception e2) {
                throw new IllegalArgumentException(e2.getMessage());
            }
        }
    }
    
    @Deprecated
    public Callback getFastSingletonMethod(final String method) {
        final String mname = this.type.getName() + "Callback$" + method + "_FS0";
        final String mnamePath = this.typePath + "Callback$" + method + "_FS0";
        synchronized (this.runtime.getJRubyClassLoader()) {
            Class c = this.tryClass(mname);
            try {
                if (c == null) {
                    final Class[] signature = { RubyKernel.IRUBY_OBJECT };
                    final Class ret = this.getReturnClass(method, signature);
                    final ClassWriter cw = this.createCtorFast(mnamePath);
                    final MethodVisitor mv = this.startCallSFast(cw);
                    mv.visitMethodInsn(184, this.typePath, method, CodegenUtils.sig(ret, signature));
                    mv.visitInsn(176);
                    mv.visitMaxs(1, 3);
                    c = this.endCall(cw, mv, mname);
                }
                final FastInvocationCallback ic = c.newInstance();
                ic.setArity(Arity.noArguments());
                ic.setJavaName(method);
                ic.setSingleton(true);
                return ic;
            }
            catch (IllegalArgumentException e) {
                throw e;
            }
            catch (Exception e2) {
                throw new IllegalArgumentException(e2.getMessage());
            }
        }
    }
    
    @Deprecated
    public Callback getFastSingletonMethod(final String method, final Class arg1) {
        final String mname = this.type.getName() + "Callback$" + method + "_FS1";
        final String mnamePath = this.typePath + "Callback$" + method + "_FS1";
        synchronized (this.runtime.getJRubyClassLoader()) {
            Class c = this.tryClass(mname);
            try {
                final Class[] descriptor = { arg1 };
                if (c == null) {
                    final Class[] signature = { RubyKernel.IRUBY_OBJECT, arg1 };
                    final Class ret = this.getReturnClass(method, signature);
                    final ClassWriter cw = this.createCtorFast(mnamePath);
                    final MethodVisitor mv = this.startCallSFast(cw);
                    this.loadArguments(mv, 2, 1, descriptor);
                    mv.visitMethodInsn(184, this.typePath, method, CodegenUtils.sig(ret, signature));
                    mv.visitInsn(176);
                    mv.visitMaxs(3, 3);
                    c = this.endCall(cw, mv, mname);
                }
                final FastInvocationCallback ic = c.newInstance();
                ic.setArity(Arity.singleArgument());
                ic.setArgumentTypes(descriptor);
                ic.setJavaName(method);
                ic.setSingleton(true);
                return ic;
            }
            catch (IllegalArgumentException e) {
                throw e;
            }
            catch (Exception e2) {
                throw new IllegalArgumentException(e2.getMessage());
            }
        }
    }
    
    @Deprecated
    public Callback getFastSingletonMethod(final String method, final Class arg1, final Class arg2) {
        final String mname = this.type.getName() + "Callback$" + method + "_FS2";
        final String mnamePath = this.typePath + "Callback$" + method + "_FS2";
        synchronized (this.runtime.getJRubyClassLoader()) {
            Class c = this.tryClass(mname);
            try {
                final Class[] descriptor = { arg1, arg2 };
                if (c == null) {
                    final Class[] signature = { RubyKernel.IRUBY_OBJECT, arg1, arg2 };
                    final Class ret = this.getReturnClass(method, signature);
                    final ClassWriter cw = this.createCtorFast(mnamePath);
                    final MethodVisitor mv = this.startCallSFast(cw);
                    this.loadArguments(mv, 2, 2, descriptor);
                    mv.visitMethodInsn(184, this.typePath, method, CodegenUtils.sig(ret, signature));
                    mv.visitInsn(176);
                    mv.visitMaxs(4, 4);
                    c = this.endCall(cw, mv, mname);
                }
                final FastInvocationCallback ic = c.newInstance();
                ic.setArity(Arity.twoArguments());
                ic.setArgumentTypes(descriptor);
                ic.setJavaName(method);
                ic.setSingleton(true);
                return ic;
            }
            catch (IllegalArgumentException e) {
                throw e;
            }
            catch (Exception e2) {
                throw new IllegalArgumentException(e2.getMessage());
            }
        }
    }
    
    @Deprecated
    public Callback getFastSingletonMethod(final String method, final Class arg1, final Class arg2, final Class arg3) {
        final String mname = this.type.getName() + "Callback$" + method + "_FS3";
        final String mnamePath = this.typePath + "Callback$" + method + "_FS3";
        synchronized (this.runtime.getJRubyClassLoader()) {
            Class c = this.tryClass(mname);
            try {
                final Class[] descriptor = { arg1, arg2, arg3 };
                if (c == null) {
                    final Class[] signature = { RubyKernel.IRUBY_OBJECT, arg1, arg2, arg3 };
                    final Class ret = this.getReturnClass(method, signature);
                    final ClassWriter cw = this.createCtorFast(mnamePath);
                    final MethodVisitor mv = this.startCallSFast(cw);
                    this.loadArguments(mv, 2, 3, descriptor);
                    mv.visitMethodInsn(184, this.typePath, method, CodegenUtils.sig(ret, signature));
                    mv.visitInsn(176);
                    mv.visitMaxs(5, 3);
                    c = this.endCall(cw, mv, mname);
                }
                final FastInvocationCallback ic = c.newInstance();
                ic.setArity(Arity.fixed(3));
                ic.setArgumentTypes(descriptor);
                ic.setJavaName(method);
                ic.setSingleton(true);
                return ic;
            }
            catch (IllegalArgumentException e) {
                throw e;
            }
            catch (Exception e2) {
                throw new IllegalArgumentException(e2.getMessage());
            }
        }
    }
    
    @Deprecated
    public Callback getFastOptMethod(final String method) {
        final String mname = this.type.getName() + "Callback$" + method + "_Fopt";
        final String mnamePath = this.typePath + "Callback$" + method + "_Fopt";
        synchronized (this.runtime.getJRubyClassLoader()) {
            Class c = this.tryClass(mname);
            try {
                if (c == null) {
                    final Class[] signature = { IRubyObject[].class };
                    final Class ret = this.getReturnClass(method, signature);
                    final ClassWriter cw = this.createCtorFast(mnamePath);
                    final MethodVisitor mv = this.startCallFast(cw);
                    mv.visitVarInsn(25, 2);
                    this.checkCast(mv, IRubyObject[].class);
                    mv.visitMethodInsn(182, this.typePath, method, CodegenUtils.sig(ret, signature));
                    mv.visitInsn(176);
                    mv.visitMaxs(2, 3);
                    c = this.endCall(cw, mv, mname);
                }
                final FastInvocationCallback ic = c.newInstance();
                ic.setArity(Arity.optional());
                ic.setArgumentTypes(InvocationCallback.OPTIONAL_ARGS);
                ic.setJavaName(method);
                return ic;
            }
            catch (IllegalArgumentException e) {
                throw e;
            }
            catch (Exception e2) {
                throw new IllegalArgumentException(e2.getMessage());
            }
        }
    }
    
    @Deprecated
    public Callback getFastOptSingletonMethod(final String method) {
        final String mname = this.type.getName() + "Callback$" + method + "_FSopt";
        final String mnamePath = this.typePath + "Callback$" + method + "_FSopt";
        synchronized (this.runtime.getJRubyClassLoader()) {
            Class c = this.tryClass(mname);
            try {
                if (c == null) {
                    final Class[] signature = { RubyKernel.IRUBY_OBJECT, IRubyObject[].class };
                    final Class ret = this.getReturnClass(method, signature);
                    final ClassWriter cw = this.createCtorFast(mnamePath);
                    final MethodVisitor mv = this.startCallSFast(cw);
                    mv.visitVarInsn(25, 2);
                    this.checkCast(mv, IRubyObject[].class);
                    mv.visitMethodInsn(184, this.typePath, method, CodegenUtils.sig(ret, signature));
                    mv.visitInsn(176);
                    mv.visitMaxs(2, 3);
                    c = this.endCall(cw, mv, mname);
                }
                final FastInvocationCallback ic = c.newInstance();
                ic.setArity(Arity.optional());
                ic.setArgumentTypes(InvocationCallback.OPTIONAL_ARGS);
                ic.setJavaName(method);
                ic.setSingleton(true);
                return ic;
            }
            catch (IllegalArgumentException e) {
                throw e;
            }
            catch (Exception e2) {
                throw new IllegalArgumentException(e2.getMessage());
            }
        }
    }
    
    @Deprecated
    private void dispatchWithoutSTI(final SkinnyMethodAdapter mv, final Label afterCall) {
        mv.aload(3);
        mv.aload(5);
        mv.invokevirtual(CodegenUtils.p(RubyModule.class), "searchMethod", CodegenUtils.sig(DynamicMethod.class, CodegenUtils.params(String.class)));
        final Label okCall = new Label();
        this.callMethodMissingIfNecessary(mv, afterCall, okCall);
        mv.label(okCall);
        mv.aload(1);
        mv.aload(2);
        mv.aload(3);
        mv.aload(5);
        mv.aload(6);
        mv.aload(8);
        mv.invokevirtual(CodegenUtils.p(DynamicMethod.class), "call", CodegenUtils.sig(IRubyObject.class, CodegenUtils.params(ThreadContext.class, IRubyObject.class, RubyModule.class, String.class, IRubyObject[].class, Block.class)));
    }
    
    @Deprecated
    public void callMethodMissingIfNecessary(final SkinnyMethodAdapter mv, final Label afterCall, final Label okCall) {
        final Label methodMissing = new Label();
        mv.dup();
        mv.invokevirtual(CodegenUtils.p(DynamicMethod.class), "isUndefined", CodegenUtils.sig(Boolean.TYPE, new Class[0]));
        mv.ifne(methodMissing);
        mv.aload(5);
        mv.ldc("method_missing");
        mv.invokevirtual(CodegenUtils.p(String.class), "equals", CodegenUtils.sig(Boolean.TYPE, CodegenUtils.params(Object.class)));
        mv.ifne(okCall);
        mv.dup();
        mv.aload(1);
        mv.invokevirtual(CodegenUtils.p(ThreadContext.class), "getFrameSelf", CodegenUtils.sig(IRubyObject.class, new Class[0]));
        mv.aload(7);
        mv.invokevirtual(CodegenUtils.p(DynamicMethod.class), "isCallableFrom", CodegenUtils.sig(Boolean.TYPE, CodegenUtils.params(IRubyObject.class, CallType.class)));
        mv.ifne(okCall);
        mv.label(methodMissing);
        mv.aload(1);
        mv.swap();
        mv.aload(2);
        mv.swap();
        mv.aload(5);
        mv.aload(6);
        mv.aload(1);
        mv.invokevirtual(CodegenUtils.p(ThreadContext.class), "getFrameSelf", CodegenUtils.sig(IRubyObject.class, new Class[0]));
        mv.aload(7);
        mv.aload(8);
        mv.invokestatic(CodegenUtils.p(RuntimeHelpers.class), "callMethodMissing", CodegenUtils.sig(IRubyObject.class, CodegenUtils.params(ThreadContext.class, IRubyObject.class, DynamicMethod.class, String.class, IRubyObject[].class, IRubyObject.class, CallType.class, Block.class)));
        mv.go_to(afterCall);
    }
    
    @Deprecated
    private void loadArguments(final MethodVisitor mv, final int argsIndex, final int count, final Class[] types) {
        this.loadArguments(mv, argsIndex, count, types, false);
    }
    
    @Deprecated
    private void loadArguments(final MethodVisitor mv, final int argsIndex, final int count, final Class[] types, final boolean contextProvided) {
        for (int i = 0; i < count; ++i) {
            this.loadArgument(mv, argsIndex, i, types[i + (contextProvided ? 1 : 0)]);
        }
    }
    
    @Deprecated
    private void loadArgument(final MethodVisitor mv, final int argsIndex, final int argIndex, final Class type1) {
        mv.visitVarInsn(25, argsIndex);
        mv.visitLdcInsn(argIndex);
        mv.visitInsn(50);
        this.checkCast(mv, type1);
    }
    
    @Deprecated
    private void checkCast(final MethodVisitor mv, final Class clazz) {
        mv.visitTypeInsn(192, CodegenUtils.p(clazz));
    }
    
    @Deprecated
    private void checkArity(final SkinnyMethodAdapter mv, final Arity arity) {
        if (arity.getValue() >= 0) {
            final Label arityOk = new Label();
            mv.aload(6);
            mv.arraylength();
            switch (arity.getValue()) {
                case 3: {
                    mv.iconst_3();
                    break;
                }
                case 2: {
                    mv.iconst_2();
                    break;
                }
                case 1: {
                    mv.iconst_1();
                    break;
                }
                case 0: {
                    mv.iconst_0();
                    break;
                }
                default: {
                    mv.ldc(arity.getValue());
                    break;
                }
            }
            mv.if_icmpeq(arityOk);
            mv.aload(9);
            mv.aload(6);
            mv.arraylength();
            switch (arity.getValue()) {
                case 3: {
                    mv.iconst_3();
                    break;
                }
                case 2: {
                    mv.iconst_2();
                    break;
                }
                case 1: {
                    mv.iconst_1();
                    break;
                }
                case 0: {
                    mv.iconst_0();
                    break;
                }
                default: {
                    mv.ldc(arity.getValue());
                    break;
                }
            }
            mv.invokevirtual(CodegenUtils.p(Ruby.class), "newArgumentError", CodegenUtils.sig(RaiseException.class, CodegenUtils.params(Integer.TYPE, Integer.TYPE)));
            mv.athrow();
            mv.label(arityOk);
        }
    }
    
    static {
        SUPER_CLASS = CodegenUtils.p(InvocationCallback.class);
        FAST_SUPER_CLASS = CodegenUtils.p(FastInvocationCallback.class);
        CALL_SIG = CodegenUtils.sig(RubyKernel.IRUBY_OBJECT, CodegenUtils.params(Object.class, Object[].class, Block.class));
        FAST_CALL_SIG = CodegenUtils.sig(RubyKernel.IRUBY_OBJECT, CodegenUtils.params(Object.class, Object[].class));
        BLOCK_CALL_SIG = CodegenUtils.sig(RubyKernel.IRUBY_OBJECT, CodegenUtils.params(ThreadContext.class, RubyKernel.IRUBY_OBJECT, IRubyObject.class));
        BLOCK_CALL_SIG19 = CodegenUtils.sig(RubyKernel.IRUBY_OBJECT, CodegenUtils.params(ThreadContext.class, IRubyObject.class, IRubyObject[].class, Block.class));
        IRUB = CodegenUtils.p(RubyKernel.IRUBY_OBJECT);
    }
}
