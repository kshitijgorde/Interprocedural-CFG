// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.internal.runtime.methods;

import org.jruby.RubyKernel;
import java.util.Iterator;
import org.jruby.RubyString;
import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.CompiledBlockCallback19;
import org.jruby.runtime.CompiledBlockCallback;
import java.util.Arrays;
import java.lang.reflect.Modifier;
import org.jruby.anno.TypePopulator;
import org.jruby.anno.JavaMethodDescriptor;
import java.util.List;
import org.jruby.org.objectweb.asm.ClassWriter;
import org.jruby.exceptions.RaiseException;
import org.jruby.exceptions.JumpException;
import org.jruby.org.objectweb.asm.Label;
import org.jruby.Ruby;
import org.jruby.runtime.Block;
import org.jruby.runtime.ThreadContext;
import org.jruby.org.objectweb.asm.ClassVisitor;
import org.jruby.compiler.impl.SkinnyMethodAdapter;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.compiler.impl.StandardASMCompiler;
import org.jruby.RubyInstanceConfig;
import org.jruby.util.CodegenUtils;
import org.jruby.lexer.yacc.ISourcePosition;
import org.jruby.parser.StaticScope;
import org.jruby.runtime.Visibility;
import org.jruby.runtime.Arity;
import org.jruby.RubyModule;
import org.jruby.util.JRubyClassLoader;
import org.jruby.org.objectweb.asm.Opcodes;
import org.jruby.runtime.MethodFactory;

public class InvocationMethodFactory extends MethodFactory implements Opcodes
{
    private static final boolean DEBUG = false;
    private static final String COMPILED_SUPER_CLASS;
    private static final String COMPILED_CALL_SIG;
    private static final String COMPILED_CALL_SIG_BLOCK;
    private static final String COMPILED_CALL_SIG_ZERO_BLOCK;
    private static final String COMPILED_CALL_SIG_ZERO;
    private static final String COMPILED_CALL_SIG_ONE_BLOCK;
    private static final String COMPILED_CALL_SIG_ONE;
    private static final String COMPILED_CALL_SIG_TWO_BLOCK;
    private static final String COMPILED_CALL_SIG_TWO;
    private static final String COMPILED_CALL_SIG_THREE_BLOCK;
    private static final String COMPILED_CALL_SIG_THREE;
    private static final String BLOCK_CALL_SIG;
    private static final String BLOCK_CALL_SIG19;
    private static final String JAVA_SUPER_SIG;
    public static final int THIS_INDEX = 0;
    public static final int THREADCONTEXT_INDEX = 1;
    public static final int RECEIVER_INDEX = 2;
    public static final int CLASS_INDEX = 3;
    public static final int NAME_INDEX = 4;
    public static final int ARGS_INDEX = 5;
    public static final int BLOCK_INDEX = 6;
    protected final JRubyClassLoader classLoader;
    private boolean seenUndefinedClasses;
    private boolean haveWarnedUser;
    
    public InvocationMethodFactory(final ClassLoader classLoader) {
        this.seenUndefinedClasses = false;
        this.haveWarnedUser = false;
        if (classLoader instanceof JRubyClassLoader) {
            this.classLoader = (JRubyClassLoader)classLoader;
        }
        else {
            this.classLoader = new JRubyClassLoader(classLoader);
        }
    }
    
    public DynamicMethod getCompiledMethodLazily(final RubyModule implementationClass, final String method, final Arity arity, final Visibility visibility, final StaticScope scope, final Object scriptObject, final CallConfiguration callConfig, final ISourcePosition position, final String parameterDesc) {
        return new CompiledMethod.LazyCompiledMethod(implementationClass, method, arity, visibility, scope, scriptObject, callConfig, position, parameterDesc, new InvocationMethodFactory(this.classLoader));
    }
    
    private static String getCompiledCallbackName(final String typePath, final String method) {
        return (typePath + "$" + method).replaceAll("/", "\\$");
    }
    
    public DynamicMethod getCompiledMethod(final RubyModule implementationClass, final String method, final Arity arity, final Visibility visibility, final StaticScope scope, final Object scriptObject, final CallConfiguration callConfig, final ISourcePosition position, final String parameterDesc) {
        final Class scriptClass = scriptObject.getClass();
        final String typePath = CodegenUtils.p(scriptClass);
        final String invokerPath = getCompiledCallbackName(typePath, method);
        synchronized (this.classLoader) {
            Class generatedClass = this.tryClass(invokerPath, scriptClass);
            try {
                if (generatedClass == null) {
                    if (RubyInstanceConfig.JIT_LOADING_DEBUG) {
                        System.err.println("no generated handle in classloader for: " + invokerPath);
                    }
                    final byte[] invokerBytes = this.getCompiledMethodOffline(method, typePath, invokerPath, arity, scope, callConfig, position.getFile(), position.getStartLine());
                    generatedClass = this.endCallWithBytes(invokerBytes, invokerPath);
                }
                else if (RubyInstanceConfig.JIT_LOADING_DEBUG) {
                    System.err.println("found generated handle in classloader: " + invokerPath);
                }
                final CompiledMethod compiledMethod = generatedClass.newInstance();
                compiledMethod.init(implementationClass, arity, visibility, scope, scriptObject, callConfig, position, parameterDesc);
                if (arity.isFixed() && arity.required() <= 3) {
                    final Class[] params = StandardASMCompiler.getStaticMethodParams(scriptClass, scope.getRequiredArgs());
                    compiledMethod.setNativeCall(scriptClass, method, IRubyObject.class, params, true);
                }
                return compiledMethod;
            }
            catch (Exception e) {
                e.printStackTrace();
                throw implementationClass.getRuntime().newLoadError(e.getMessage());
            }
        }
    }
    
    public byte[] getCompiledMethodOffline(final String method, final String className, final String invokerPath, final Arity arity, final StaticScope scope, CallConfiguration callConfig, final String filename, final int line) {
        final String sup = InvocationMethodFactory.COMPILED_SUPER_CLASS;
        final ClassWriter cw = this.createCompiledCtor(invokerPath, invokerPath, sup);
        SkinnyMethodAdapter mv = null;
        String signature = null;
        boolean specificArity = false;
        if (RubyInstanceConfig.FULL_TRACE_ENABLED) {
            switch (callConfig) {
                case FrameNoneScopeDummy: {
                    callConfig = CallConfiguration.FrameBacktraceScopeDummy;
                    break;
                }
                case FrameNoneScopeFull: {
                    callConfig = CallConfiguration.FrameBacktraceScopeFull;
                    break;
                }
                case FrameNoneScopeNone: {
                    callConfig = CallConfiguration.FrameBacktraceScopeNone;
                    break;
                }
            }
        }
        if (scope.getRestArg() >= 0 || scope.getOptionalArgs() > 0 || scope.getRequiredArgs() > 3) {
            signature = InvocationMethodFactory.COMPILED_CALL_SIG_BLOCK;
            mv = new SkinnyMethodAdapter(cw, 1, "call", signature, null, null);
        }
        else {
            specificArity = true;
            mv = new SkinnyMethodAdapter(cw, 1, "call", InvocationMethodFactory.COMPILED_CALL_SIG_BLOCK, null, null);
            mv.start();
            mv.line(-1);
            mv.aloadMany(0, 1, 4, 5);
            mv.pushInt(scope.getRequiredArgs());
            mv.invokestatic(CodegenUtils.p(JavaMethod.class), "checkArgumentCount", CodegenUtils.sig(Void.TYPE, JavaMethod.class, ThreadContext.class, String.class, IRubyObject[].class, Integer.TYPE));
            mv.aloadMany(0, 1, 2, 3, 4);
            for (int i = 0; i < scope.getRequiredArgs(); ++i) {
                mv.aload(5);
                mv.ldc(i);
                mv.arrayload();
            }
            mv.aload(6);
            switch (scope.getRequiredArgs()) {
                case 0: {
                    signature = InvocationMethodFactory.COMPILED_CALL_SIG_ZERO_BLOCK;
                    break;
                }
                case 1: {
                    signature = InvocationMethodFactory.COMPILED_CALL_SIG_ONE_BLOCK;
                    break;
                }
                case 2: {
                    signature = InvocationMethodFactory.COMPILED_CALL_SIG_TWO_BLOCK;
                    break;
                }
                case 3: {
                    signature = InvocationMethodFactory.COMPILED_CALL_SIG_THREE_BLOCK;
                    break;
                }
            }
            mv.invokevirtual(invokerPath, "call", signature);
            mv.areturn();
            mv.end();
            switch (scope.getRequiredArgs()) {
                case 0: {
                    signature = InvocationMethodFactory.COMPILED_CALL_SIG_ZERO;
                    break;
                }
                case 1: {
                    signature = InvocationMethodFactory.COMPILED_CALL_SIG_ONE;
                    break;
                }
                case 2: {
                    signature = InvocationMethodFactory.COMPILED_CALL_SIG_TWO;
                    break;
                }
                case 3: {
                    signature = InvocationMethodFactory.COMPILED_CALL_SIG_THREE;
                    break;
                }
            }
            mv = new SkinnyMethodAdapter(cw, 1, "call", signature, null, null);
            mv.start();
            mv.line(-1);
            mv.aloadMany(0, 1, 2, 3, 4);
            for (int i = 1; i <= scope.getRequiredArgs(); ++i) {
                mv.aload(4 + i);
            }
            mv.getstatic(CodegenUtils.p(Block.class), "NULL_BLOCK", CodegenUtils.ci(Block.class));
            switch (scope.getRequiredArgs()) {
                case 0: {
                    signature = InvocationMethodFactory.COMPILED_CALL_SIG_ZERO_BLOCK;
                    break;
                }
                case 1: {
                    signature = InvocationMethodFactory.COMPILED_CALL_SIG_ONE_BLOCK;
                    break;
                }
                case 2: {
                    signature = InvocationMethodFactory.COMPILED_CALL_SIG_TWO_BLOCK;
                    break;
                }
                case 3: {
                    signature = InvocationMethodFactory.COMPILED_CALL_SIG_THREE_BLOCK;
                    break;
                }
            }
            mv.invokevirtual(invokerPath, "call", signature);
            mv.areturn();
            mv.end();
            mv = new SkinnyMethodAdapter(cw, 1, "call", signature, null, null);
        }
        mv.visitCode();
        mv.line(-1);
        mv.aload(1);
        mv.getfield(CodegenUtils.p(ThreadContext.class), "callNumber", CodegenUtils.ci(Integer.TYPE));
        int callNumberIndex = -1;
        if (specificArity) {
            switch (scope.getRequiredArgs()) {
                case -1: {
                    callNumberIndex = 8;
                    break;
                }
                case 0: {
                    callNumberIndex = 7;
                    break;
                }
                default: {
                    callNumberIndex = 5 + scope.getRequiredArgs() + 1 + 1;
                    break;
                }
            }
        }
        else {
            callNumberIndex = 7;
        }
        mv.istore(callNumberIndex);
        if (!callConfig.isNoop() || RubyInstanceConfig.FULL_TRACE_ENABLED) {
            if (specificArity) {
                this.invokeCallConfigPre(mv, InvocationMethodFactory.COMPILED_SUPER_CLASS, scope.getRequiredArgs(), true, callConfig);
            }
            else {
                this.invokeCallConfigPre(mv, InvocationMethodFactory.COMPILED_SUPER_CLASS, -1, true, callConfig);
            }
        }
        int traceBoolIndex = -1;
        if (RubyInstanceConfig.FULL_TRACE_ENABLED) {
            if (specificArity) {
                switch (scope.getRequiredArgs()) {
                    case -1: {
                        traceBoolIndex = 9;
                        break;
                    }
                    case 0: {
                        traceBoolIndex = 8;
                        break;
                    }
                    default: {
                        traceBoolIndex = 5 + scope.getRequiredArgs() + 1 + 2;
                        break;
                    }
                }
            }
            else {
                traceBoolIndex = 8;
            }
            mv.aload(1);
            mv.invokevirtual(CodegenUtils.p(ThreadContext.class), "getRuntime", CodegenUtils.sig(Ruby.class, new Class[0]));
            mv.invokevirtual(CodegenUtils.p(Ruby.class), "hasEventHooks", CodegenUtils.sig(Boolean.TYPE, new Class[0]));
            mv.istore(traceBoolIndex);
            this.invokeTraceCompiledPre(mv, InvocationMethodFactory.COMPILED_SUPER_CLASS, traceBoolIndex, filename, line);
        }
        final Label tryBegin = new Label();
        final Label tryEnd = new Label();
        final Label doFinally = new Label();
        final Label doReturnFinally = new Label();
        final Label doRedoFinally = new Label();
        final Label catchReturnJump = new Label();
        final Label catchRedoJump = new Label();
        final boolean heapScoped = callConfig.scoping() != Scoping.None;
        final boolean framed = callConfig.framing() != Framing.None;
        if (framed || heapScoped) {
            mv.trycatch(tryBegin, tryEnd, catchReturnJump, CodegenUtils.p(JumpException.ReturnJump.class));
        }
        if (framed) {
            mv.trycatch(tryBegin, tryEnd, catchRedoJump, CodegenUtils.p(JumpException.RedoJump.class));
        }
        if (framed || heapScoped) {
            mv.trycatch(tryBegin, tryEnd, doFinally, null);
        }
        if (framed || heapScoped) {
            mv.trycatch(catchReturnJump, doReturnFinally, doFinally, null);
        }
        if (framed) {
            mv.trycatch(catchRedoJump, doRedoFinally, doFinally, null);
        }
        if (framed || heapScoped) {
            mv.label(tryBegin);
        }
        mv.aload(0);
        mv.getfield(invokerPath, "$scriptObject", CodegenUtils.ci(Object.class));
        mv.checkcast(className);
        mv.aloadMany(1, 2);
        if (specificArity) {
            for (int j = 0; j < scope.getRequiredArgs(); ++j) {
                mv.aload(5 + j);
            }
            mv.aload(5 + scope.getRequiredArgs());
            mv.invokestatic(className, method, StandardASMCompiler.getStaticMethodSignature(className, scope.getRequiredArgs()));
        }
        else {
            mv.aloadMany(5, 6);
            mv.invokestatic(className, method, StandardASMCompiler.getStaticMethodSignature(className, 4));
        }
        if (framed || heapScoped) {
            mv.label(tryEnd);
        }
        if (RubyInstanceConfig.FULL_TRACE_ENABLED) {
            this.invokeTraceCompiledPost(mv, InvocationMethodFactory.COMPILED_SUPER_CLASS, traceBoolIndex);
        }
        if (!callConfig.isNoop()) {
            this.invokeCallConfigPost(mv, InvocationMethodFactory.COMPILED_SUPER_CLASS, callConfig);
        }
        mv.visitInsn(176);
        if (framed || heapScoped) {
            mv.label(catchReturnJump);
            mv.aload(0);
            mv.swap();
            mv.aload(1);
            mv.swap();
            mv.iload(callNumberIndex);
            mv.invokevirtual(InvocationMethodFactory.COMPILED_SUPER_CLASS, "handleReturn", CodegenUtils.sig(IRubyObject.class, ThreadContext.class, JumpException.ReturnJump.class, Integer.TYPE));
            mv.label(doReturnFinally);
            if (RubyInstanceConfig.FULL_TRACE_ENABLED) {
                this.invokeTraceCompiledPost(mv, InvocationMethodFactory.COMPILED_SUPER_CLASS, traceBoolIndex);
            }
            if (!callConfig.isNoop()) {
                this.invokeCallConfigPost(mv, InvocationMethodFactory.COMPILED_SUPER_CLASS, callConfig);
            }
            mv.areturn();
        }
        if (framed) {
            mv.label(catchRedoJump);
            mv.pop();
            mv.aload(1);
            mv.invokevirtual(CodegenUtils.p(ThreadContext.class), "getRuntime", CodegenUtils.sig(Ruby.class, new Class[0]));
            mv.invokevirtual(CodegenUtils.p(Ruby.class), "newRedoLocalJumpError", CodegenUtils.sig(RaiseException.class, new Class[0]));
            mv.label(doRedoFinally);
            if (RubyInstanceConfig.FULL_TRACE_ENABLED) {
                this.invokeTraceCompiledPost(mv, InvocationMethodFactory.COMPILED_SUPER_CLASS, traceBoolIndex);
            }
            if (!callConfig.isNoop()) {
                this.invokeCallConfigPost(mv, InvocationMethodFactory.COMPILED_SUPER_CLASS, callConfig);
            }
            mv.athrow();
        }
        if (framed || heapScoped) {
            mv.label(doFinally);
            if (RubyInstanceConfig.FULL_TRACE_ENABLED) {
                this.invokeTraceCompiledPost(mv, InvocationMethodFactory.COMPILED_SUPER_CLASS, traceBoolIndex);
            }
            if (!callConfig.isNoop()) {
                this.invokeCallConfigPost(mv, InvocationMethodFactory.COMPILED_SUPER_CLASS, callConfig);
            }
            mv.athrow();
        }
        mv.end();
        return this.endCallOffline(cw);
    }
    
    public DynamicMethod getAnnotatedMethod(final RubyModule implementationClass, final List<JavaMethodDescriptor> descs) {
        final JavaMethodDescriptor desc1 = descs.get(0);
        final String javaMethodName = desc1.name;
        synchronized (this.classLoader) {
            try {
                final Class c = this.getAnnotatedMethodClass(descs);
                final DescriptorInfo info = new DescriptorInfo(descs);
                final JavaMethod ic = c.getConstructor(RubyModule.class, Visibility.class).newInstance(implementationClass, desc1.anno.visibility());
                TypePopulator.populateMethod(ic, Arity.optional().getValue(), javaMethodName, desc1.isStatic, CallConfiguration.getCallConfig(info.isFrame(), info.isScope(), info.isBacktrace()), desc1.anno.notImplemented());
                return ic;
            }
            catch (Exception e) {
                e.printStackTrace();
                throw implementationClass.getRuntime().newLoadError(e.getMessage());
            }
        }
    }
    
    public Class getAnnotatedMethodClass(final List<JavaMethodDescriptor> descs) throws Exception {
        final JavaMethodDescriptor desc1 = descs.get(0);
        if (!Modifier.isPublic(desc1.getDeclaringClass().getModifiers())) {
            System.err.println("warning: binding non-public class" + desc1.declaringClassName + "; reflected handles won't work");
        }
        final String javaMethodName = desc1.name;
        String generatedClassName = CodegenUtils.getAnnotatedBindingClassName(javaMethodName, desc1.declaringClassName, desc1.isStatic, desc1.actualRequired, desc1.optional, descs.size() > 1, desc1.anno.frame());
        if (RubyInstanceConfig.FULL_TRACE_ENABLED) {
            generatedClassName += "_DBG";
        }
        final String generatedClassPath = generatedClassName.replace('.', '/');
        synchronized (this.classLoader) {
            Class c = this.tryClass(generatedClassName, desc1.getDeclaringClass());
            final DescriptorInfo info = new DescriptorInfo(descs);
            if (c == null) {
                Class superClass = null;
                if (info.getMin() == -1) {
                    if (info.isBlock()) {
                        superClass = JavaMethod.JavaMethodNBlock.class;
                    }
                    else {
                        superClass = JavaMethod.JavaMethodN.class;
                    }
                }
                else if (info.isRest()) {
                    if (info.isBlock()) {
                        superClass = JavaMethod.BLOCK_REST_METHODS[info.getMin()][info.getMax()];
                    }
                    else {
                        superClass = JavaMethod.REST_METHODS[info.getMin()][info.getMax()];
                    }
                }
                else if (info.isBlock()) {
                    superClass = JavaMethod.BLOCK_METHODS[info.getMin()][info.getMax()];
                }
                else {
                    superClass = JavaMethod.METHODS[info.getMin()][info.getMax()];
                }
                if (superClass == null) {
                    throw new RuntimeException("invalid multi combination");
                }
                final String superClassString = CodegenUtils.p(superClass);
                final int dotIndex = desc1.declaringClassName.lastIndexOf(46);
                final ClassWriter cw = this.createJavaMethodCtor(generatedClassPath, desc1.declaringClassName.substring(dotIndex + 1) + "$" + desc1.name, superClassString);
                this.addAnnotatedMethodInvoker(cw, "call", superClassString, descs);
                c = this.endClass(cw, generatedClassName);
            }
            return c;
        }
    }
    
    public DynamicMethod getAnnotatedMethod(final RubyModule implementationClass, final JavaMethodDescriptor desc) {
        final String javaMethodName = desc.name;
        synchronized (this.classLoader) {
            try {
                final Class c = this.getAnnotatedMethodClass(Arrays.asList(desc));
                final JavaMethod ic = c.getConstructor(RubyModule.class, Visibility.class).newInstance(implementationClass, desc.anno.visibility());
                TypePopulator.populateMethod(ic, Arity.fromAnnotation(desc.anno, desc.actualRequired).getValue(), javaMethodName, desc.isStatic, CallConfiguration.getCallConfigByAnno(desc.anno), desc.anno.notImplemented());
                return ic;
            }
            catch (Exception e) {
                e.printStackTrace();
                throw implementationClass.getRuntime().newLoadError(e.getMessage());
            }
        }
    }
    
    private static String getBlockCallbackName(final String typePathString, final String method) {
        return (typePathString + "$" + method).replaceAll("/", "\\$");
    }
    
    public CompiledBlockCallback getBlockCallback(final String method, final String file, final int line, final Object scriptObject) {
        final Class typeClass = scriptObject.getClass();
        final String typePathString = CodegenUtils.p(typeClass);
        final String mname = getBlockCallbackName(typePathString, method);
        synchronized (this.classLoader) {
            Class c = this.tryClass(mname);
            try {
                if (c == null) {
                    if (RubyInstanceConfig.JIT_LOADING_DEBUG) {
                        System.err.println("no generated handle in classloader for: " + mname);
                    }
                    final byte[] bytes = this.getBlockCallbackOffline(method, file, line, typePathString);
                    c = this.endCallWithBytes(bytes, mname);
                }
                else if (RubyInstanceConfig.JIT_LOADING_DEBUG) {
                    System.err.println("found generated handle in classloader for: " + mname);
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
    
    public byte[] getBlockCallbackOffline(final String method, final String file, final int line, final String classname) {
        final String mname = getBlockCallbackName(classname, method);
        final ClassWriter cw = this.createBlockCtor(mname, classname);
        SkinnyMethodAdapter mv = this.startBlockCall(cw);
        mv.line(-1);
        mv.aload(0);
        mv.getfield(mname, "$scriptObject", "L" + classname + ";");
        mv.aloadMany(1, 2, 3, 4);
        mv.invokestatic(classname, method, CodegenUtils.sig(IRubyObject.class, "L" + classname + ";", ThreadContext.class, IRubyObject.class, IRubyObject.class, Block.class));
        mv.areturn();
        mv.end();
        mv = new SkinnyMethodAdapter(cw, 1, "getFile", CodegenUtils.sig(String.class, new Class[0]), null, null);
        mv.start();
        mv.ldc(file);
        mv.areturn();
        mv.end();
        mv = new SkinnyMethodAdapter(cw, 1, "getLine", CodegenUtils.sig(Integer.TYPE, new Class[0]), null, null);
        mv.start();
        mv.ldc(line);
        mv.ireturn();
        mv.end();
        return this.endCallOffline(cw);
    }
    
    public CompiledBlockCallback19 getBlockCallback19(final String method, final String file, final int line, final Object scriptObject) {
        final Class typeClass = scriptObject.getClass();
        final String typePathString = CodegenUtils.p(typeClass);
        final String mname = getBlockCallbackName(typePathString, method);
        synchronized (this.classLoader) {
            Class c = this.tryClass(mname);
            try {
                if (c == null) {
                    if (RubyInstanceConfig.JIT_LOADING_DEBUG) {
                        System.err.println("no generated handle in classloader for: " + mname);
                    }
                    final byte[] bytes = this.getBlockCallback19Offline(method, file, line, typePathString);
                    c = this.endClassWithBytes(bytes, mname);
                }
                else if (RubyInstanceConfig.JIT_LOADING_DEBUG) {
                    System.err.println("found generated handle in classloader for: " + mname);
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
    
    public byte[] getBlockCallback19Offline(final String method, final String file, final int line, final String classname) {
        final String mnamePath = getBlockCallbackName(classname, method);
        final ClassWriter cw = this.createBlockCtor19(mnamePath, classname);
        SkinnyMethodAdapter mv = this.startBlockCall19(cw);
        mv.line(-1);
        mv.aload(0);
        mv.getfield(mnamePath, "$scriptObject", "L" + classname + ";");
        mv.aloadMany(1, 2, 3, 4);
        mv.invokestatic(classname, method, CodegenUtils.sig(IRubyObject.class, "L" + classname + ";", ThreadContext.class, IRubyObject.class, IRubyObject[].class, Block.class));
        mv.areturn();
        mv.end();
        mv = new SkinnyMethodAdapter(cw, 1, "getFile", CodegenUtils.sig(String.class, new Class[0]), null, null);
        mv.start();
        mv.ldc(file);
        mv.areturn();
        mv.end();
        mv = new SkinnyMethodAdapter(cw, 1, "getLine", CodegenUtils.sig(Integer.TYPE, new Class[0]), null, null);
        mv.start();
        mv.ldc(line);
        mv.ireturn();
        mv.end();
        return this.endCallOffline(cw);
    }
    
    private SkinnyMethodAdapter startBlockCall(final ClassWriter cw) {
        final SkinnyMethodAdapter mv = new SkinnyMethodAdapter(cw, 4113, "call", InvocationMethodFactory.BLOCK_CALL_SIG, null, null);
        mv.visitCode();
        final Label line = new Label();
        mv.visitLineNumber(0, line);
        return mv;
    }
    
    private SkinnyMethodAdapter startBlockCall19(final ClassWriter cw) {
        final SkinnyMethodAdapter mv = new SkinnyMethodAdapter(cw, 4113, "call", InvocationMethodFactory.BLOCK_CALL_SIG19, null, null);
        mv.visitCode();
        final Label line = new Label();
        mv.visitLineNumber(0, line);
        return mv;
    }
    
    private ClassWriter createBlockCtor(final String namePath, final String classname) {
        final String ciClassname = "L" + classname + ";";
        final ClassWriter cw = new ClassWriter(3);
        cw.visit(RubyInstanceConfig.JAVA_VERSION, 33, namePath, null, CodegenUtils.p(CompiledBlockCallback.class), null);
        cw.visitSource(namePath, null);
        cw.visitField(18, "$scriptObject", ciClassname, null, null);
        final SkinnyMethodAdapter mv = new SkinnyMethodAdapter(cw, 1, "<init>", CodegenUtils.sig(Void.TYPE, CodegenUtils.params(Object.class)), null, null);
        mv.start();
        mv.line(-1);
        mv.aload(0);
        mv.invokespecial(CodegenUtils.p(CompiledBlockCallback.class), "<init>", CodegenUtils.sig(Void.TYPE, new Class[0]));
        mv.aloadMany(0, 1);
        mv.checkcast(classname);
        mv.putfield(namePath, "$scriptObject", ciClassname);
        mv.voidreturn();
        mv.end();
        return cw;
    }
    
    private ClassWriter createBlockCtor19(final String namePath, final String classname) {
        final String ciClassname = "L" + classname + ";";
        final ClassWriter cw = new ClassWriter(3);
        cw.visit(RubyInstanceConfig.JAVA_VERSION, 33, namePath, null, CodegenUtils.p(Object.class), new String[] { CodegenUtils.p(CompiledBlockCallback19.class) });
        cw.visitSource(namePath, null);
        cw.visitField(18, "$scriptObject", ciClassname, null, null);
        final SkinnyMethodAdapter mv = new SkinnyMethodAdapter(cw, 1, "<init>", CodegenUtils.sig(Void.TYPE, CodegenUtils.params(Object.class)), null, null);
        mv.start();
        mv.line(-1);
        mv.aload(0);
        mv.invokespecial(CodegenUtils.p(Object.class), "<init>", CodegenUtils.sig(Void.TYPE, new Class[0]));
        mv.aloadMany(0, 1);
        mv.checkcast(classname);
        mv.putfield(namePath, "$scriptObject", ciClassname);
        mv.voidreturn();
        mv.end();
        return cw;
    }
    
    public void prepareAnnotatedMethod(final RubyModule implementationClass, final JavaMethod javaMethod, final JavaMethodDescriptor desc) {
        final String javaMethodName = desc.name;
        javaMethod.setArity(Arity.fromAnnotation(desc.anno, desc.actualRequired));
        javaMethod.setJavaName(javaMethodName);
        javaMethod.setSingleton(desc.isStatic);
        javaMethod.setCallConfig(CallConfiguration.getCallConfigByAnno(desc.anno));
    }
    
    private void checkArity(final JRubyMethod jrubyMethod, final SkinnyMethodAdapter method, final int specificArity) {
        final Label arityError = new Label();
        final Label noArityError = new Label();
        switch (specificArity) {
            case 0:
            case 1:
            case 2:
            case 3: {}
            default: {
                boolean checkArity = false;
                if (jrubyMethod.rest()) {
                    if (jrubyMethod.required() > 0) {
                        method.aload(5);
                        method.arraylength();
                        method.ldc(jrubyMethod.required());
                        method.if_icmplt(arityError);
                        checkArity = true;
                    }
                }
                else if (jrubyMethod.optional() > 0) {
                    if (jrubyMethod.required() > 0) {
                        method.aload(5);
                        method.arraylength();
                        method.ldc(jrubyMethod.required());
                        method.if_icmplt(arityError);
                    }
                    method.aload(5);
                    method.arraylength();
                    method.ldc(jrubyMethod.required() + jrubyMethod.optional());
                    method.if_icmpgt(arityError);
                    checkArity = true;
                }
                else {
                    method.aload(5);
                    method.arraylength();
                    method.ldc(jrubyMethod.required());
                    method.if_icmpne(arityError);
                    checkArity = true;
                }
                if (checkArity) {
                    method.go_to(noArityError);
                    method.label(arityError);
                    method.aload(1);
                    method.invokevirtual(CodegenUtils.p(ThreadContext.class), "getRuntime", CodegenUtils.sig(Ruby.class, new Class[0]));
                    method.aload(5);
                    method.ldc(jrubyMethod.required());
                    method.ldc(jrubyMethod.required() + jrubyMethod.optional());
                    method.invokestatic(CodegenUtils.p(Arity.class), "checkArgumentCount", CodegenUtils.sig(Integer.TYPE, Ruby.class, IRubyObject[].class, Integer.TYPE, Integer.TYPE));
                    method.pop();
                    method.label(noArityError);
                }
            }
        }
    }
    
    private ClassWriter createCompiledCtor(final String namePath, final String shortPath, final String sup) {
        final ClassWriter cw = new ClassWriter(3);
        cw.visit(RubyInstanceConfig.JAVA_VERSION, 33, namePath, null, sup, null);
        cw.visitSource(shortPath, null);
        final SkinnyMethodAdapter mv = new SkinnyMethodAdapter(cw, 1, "<init>", "()V", null, null);
        mv.visitCode();
        mv.line(-1);
        mv.aload(0);
        mv.visitMethodInsn(183, sup, "<init>", "()V");
        mv.visitLineNumber(-1, new Label());
        mv.voidreturn();
        mv.end();
        return cw;
    }
    
    private ClassWriter createJavaMethodCtor(final String namePath, final String shortPath, final String sup) throws Exception {
        final ClassWriter cw = new ClassWriter(3);
        final String sourceFile = namePath.substring(namePath.lastIndexOf(47) + 1) + ".gen";
        cw.visit(RubyInstanceConfig.JAVA_VERSION, 33, namePath, null, sup, null);
        cw.visitSource(sourceFile, null);
        final SkinnyMethodAdapter mv = new SkinnyMethodAdapter(cw, 1, "<init>", InvocationMethodFactory.JAVA_SUPER_SIG, null, null);
        mv.start();
        mv.line(-1);
        mv.aloadMany(0, 1, 2);
        mv.visitMethodInsn(183, sup, "<init>", InvocationMethodFactory.JAVA_SUPER_SIG);
        mv.visitLineNumber(-1, new Label());
        mv.voidreturn();
        mv.end();
        return cw;
    }
    
    private void invokeCallConfigPre(final SkinnyMethodAdapter mv, final String superClass, final int specificArity, final boolean block, final CallConfiguration callConfig) {
        if (callConfig.isNoop()) {
            return;
        }
        this.prepareForPre(mv, specificArity, block, callConfig);
        mv.invokevirtual(superClass, this.getPreMethod(callConfig), this.getPreSignature(callConfig));
    }
    
    private void invokeCallConfigPost(final SkinnyMethodAdapter mv, final String superClass, final CallConfiguration callConfig) {
        if (callConfig.isNoop()) {
            return;
        }
        mv.aload(1);
        mv.invokestatic(superClass, getPostMethod(callConfig), CodegenUtils.sig(Void.TYPE, CodegenUtils.params(ThreadContext.class)));
    }
    
    private void prepareForPre(final SkinnyMethodAdapter mv, final int specificArity, final boolean block, final CallConfiguration callConfig) {
        if (callConfig.isNoop()) {
            return;
        }
        mv.aloadMany(0, 1);
        switch (callConfig.framing()) {
            case Full: {
                mv.aloadMany(2, 4);
                this.loadBlockForPre(mv, specificArity, block);
                break;
            }
            case Backtrace: {
                mv.aload(4);
                break;
            }
            case None: {
                break;
            }
            default: {
                throw new RuntimeException("Unknown call configuration");
            }
        }
    }
    
    private String getPreMethod(final CallConfiguration callConfig) {
        switch (callConfig) {
            case FrameFullScopeFull: {
                return "preFrameAndScope";
            }
            case FrameFullScopeDummy: {
                return "preFrameAndDummyScope";
            }
            case FrameFullScopeNone: {
                return "preFrameOnly";
            }
            case FrameBacktraceScopeFull: {
                return "preBacktraceAndScope";
            }
            case FrameBacktraceScopeDummy: {
                return "preBacktraceDummyScope";
            }
            case FrameBacktraceScopeNone: {
                return "preBacktraceOnly";
            }
            case FrameNoneScopeFull: {
                return "preScopeOnly";
            }
            case FrameNoneScopeDummy: {
                return "preNoFrameDummyScope";
            }
            case FrameNoneScopeNone: {
                return "preNoop";
            }
            default: {
                throw new RuntimeException("Unknown call configuration");
            }
        }
    }
    
    private String getPreSignature(final CallConfiguration callConfig) {
        switch (callConfig) {
            case FrameFullScopeFull: {
                return CodegenUtils.sig(Void.TYPE, CodegenUtils.params(ThreadContext.class, IRubyObject.class, String.class, Block.class));
            }
            case FrameFullScopeDummy: {
                return CodegenUtils.sig(Void.TYPE, CodegenUtils.params(ThreadContext.class, IRubyObject.class, String.class, Block.class));
            }
            case FrameFullScopeNone: {
                return CodegenUtils.sig(Void.TYPE, CodegenUtils.params(ThreadContext.class, IRubyObject.class, String.class, Block.class));
            }
            case FrameBacktraceScopeFull: {
                return CodegenUtils.sig(Void.TYPE, CodegenUtils.params(ThreadContext.class, String.class));
            }
            case FrameBacktraceScopeDummy: {
                return CodegenUtils.sig(Void.TYPE, CodegenUtils.params(ThreadContext.class, String.class));
            }
            case FrameBacktraceScopeNone: {
                return CodegenUtils.sig(Void.TYPE, CodegenUtils.params(ThreadContext.class, String.class));
            }
            case FrameNoneScopeFull: {
                return CodegenUtils.sig(Void.TYPE, CodegenUtils.params(ThreadContext.class));
            }
            case FrameNoneScopeDummy: {
                return CodegenUtils.sig(Void.TYPE, CodegenUtils.params(ThreadContext.class));
            }
            case FrameNoneScopeNone: {
                return CodegenUtils.sig(Void.TYPE, new Class[0]);
            }
            default: {
                throw new RuntimeException("Unknown call configuration");
            }
        }
    }
    
    public static String getPostMethod(final CallConfiguration callConfig) {
        switch (callConfig) {
            case FrameFullScopeFull: {
                return "postFrameAndScope";
            }
            case FrameFullScopeDummy: {
                return "postFrameAndScope";
            }
            case FrameFullScopeNone: {
                return "postFrameOnly";
            }
            case FrameBacktraceScopeFull: {
                return "postBacktraceAndScope";
            }
            case FrameBacktraceScopeDummy: {
                return "postBacktraceDummyScope";
            }
            case FrameBacktraceScopeNone: {
                return "postBacktraceOnly";
            }
            case FrameNoneScopeFull: {
                return "postScopeOnly";
            }
            case FrameNoneScopeDummy: {
                return "postNoFrameDummyScope";
            }
            case FrameNoneScopeNone: {
                return "postNoop";
            }
            default: {
                throw new RuntimeException("Unknown call configuration");
            }
        }
    }
    
    private void loadArguments(final SkinnyMethodAdapter mv, final JavaMethodDescriptor desc, final int specificArity) {
        switch (specificArity) {
            default: {
                mv.aload(5);
                break;
            }
            case 0: {
                break;
            }
            case 1: {
                this.loadArgumentWithCast(mv, 1, desc.argumentTypes[0]);
                break;
            }
            case 2: {
                this.loadArgumentWithCast(mv, 1, desc.argumentTypes[0]);
                this.loadArgumentWithCast(mv, 2, desc.argumentTypes[1]);
                break;
            }
            case 3: {
                this.loadArgumentWithCast(mv, 1, desc.argumentTypes[0]);
                this.loadArgumentWithCast(mv, 2, desc.argumentTypes[1]);
                this.loadArgumentWithCast(mv, 3, desc.argumentTypes[2]);
                break;
            }
        }
    }
    
    private void loadArgumentWithCast(final SkinnyMethodAdapter mv, final int argNumber, final Class coerceType) {
        mv.aload(5 + (argNumber - 1));
        if (coerceType != IRubyObject.class && coerceType != IRubyObject[].class) {
            if (coerceType != RubyString.class) {
                throw new RuntimeException("Unknown coercion target: " + coerceType);
            }
            mv.invokeinterface(CodegenUtils.p(IRubyObject.class), "convertToString", CodegenUtils.sig(RubyString.class, new Class[0]));
        }
    }
    
    private void loadBlockForPre(final SkinnyMethodAdapter mv, final int specificArity, final boolean getsBlock) {
        if (!getsBlock) {
            mv.getstatic(CodegenUtils.p(Block.class), "NULL_BLOCK", CodegenUtils.ci(Block.class));
            return;
        }
        this.loadBlock(mv, specificArity, getsBlock);
    }
    
    private void loadBlock(final SkinnyMethodAdapter mv, final int specificArity, final boolean getsBlock) {
        if (!getsBlock) {
            return;
        }
        switch (specificArity) {
            case 0:
            case 1:
            case 2:
            case 3: {
                mv.aload(5 + specificArity);
                break;
            }
            default: {
                mv.aload(6);
                break;
            }
        }
    }
    
    private void loadReceiver(final String typePath, final JavaMethodDescriptor desc, final SkinnyMethodAdapter mv) {
        if (Modifier.isStatic(desc.modifiers)) {
            if (desc.hasContext) {
                mv.aload(1);
            }
            mv.aload(2);
        }
        else {
            mv.aload(2);
            mv.checkcast(typePath);
            if (desc.hasContext) {
                mv.aload(1);
            }
        }
    }
    
    private Class tryClass(final String name, final Class targetClass) {
        Class c = null;
        try {
            if (this.classLoader == null) {
                c = Class.forName(name, true, this.classLoader);
            }
            else {
                c = this.classLoader.loadClass(name);
            }
        }
        catch (Exception e) {
            this.seenUndefinedClasses = true;
            return null;
        }
        try {
            c.getMethod("call", ThreadContext.class, IRubyObject.class, RubyModule.class, String.class, IRubyObject[].class, Block.class);
            if (c != null && this.seenUndefinedClasses && !this.haveWarnedUser) {
                this.haveWarnedUser = true;
                System.err.println("WARNING: while creating new bindings for " + targetClass + ",\n" + "found an existing binding; you may want to run a clean build.");
            }
            return c;
        }
        catch (Exception e) {
            e.printStackTrace();
            this.seenUndefinedClasses = true;
            return null;
        }
    }
    
    private Class tryClass(final String name) {
        try {
            final Class c = this.classLoader.loadClass(name);
            c.getMethod("call", ThreadContext.class, IRubyObject.class, IRubyObject.class, Block.class);
            return c;
        }
        catch (Exception e) {
            return null;
        }
    }
    
    protected Class endCall(final ClassWriter cw, final String name) {
        return this.endClass(cw, name);
    }
    
    protected Class endCallWithBytes(final byte[] classBytes, final String name) {
        return this.endClassWithBytes(classBytes, name);
    }
    
    protected byte[] endCallOffline(final ClassWriter cw) {
        return this.endClassOffline(cw);
    }
    
    protected Class endClass(final ClassWriter cw, final String name) {
        cw.visitEnd();
        final byte[] code = cw.toByteArray();
        return this.classLoader.defineClass(name, code);
    }
    
    protected Class endClassWithBytes(final byte[] code, final String name) {
        return this.classLoader.defineClass(name, code);
    }
    
    protected byte[] endClassOffline(final ClassWriter cw) {
        cw.visitEnd();
        final byte[] code = cw.toByteArray();
        return code;
    }
    
    private SkinnyMethodAdapter beginMethod(final ClassWriter cw, final String methodName, final int specificArity, final boolean block) {
        switch (specificArity) {
            default: {
                if (block) {
                    return new SkinnyMethodAdapter(cw, 1, methodName, InvocationMethodFactory.COMPILED_CALL_SIG_BLOCK, null, null);
                }
                return new SkinnyMethodAdapter(cw, 1, methodName, InvocationMethodFactory.COMPILED_CALL_SIG, null, null);
            }
            case 0: {
                if (block) {
                    return new SkinnyMethodAdapter(cw, 1, methodName, InvocationMethodFactory.COMPILED_CALL_SIG_ZERO_BLOCK, null, null);
                }
                return new SkinnyMethodAdapter(cw, 1, methodName, InvocationMethodFactory.COMPILED_CALL_SIG_ZERO, null, null);
            }
            case 1: {
                if (block) {
                    return new SkinnyMethodAdapter(cw, 1, methodName, InvocationMethodFactory.COMPILED_CALL_SIG_ONE_BLOCK, null, null);
                }
                return new SkinnyMethodAdapter(cw, 1, methodName, InvocationMethodFactory.COMPILED_CALL_SIG_ONE, null, null);
            }
            case 2: {
                if (block) {
                    return new SkinnyMethodAdapter(cw, 1, methodName, InvocationMethodFactory.COMPILED_CALL_SIG_TWO_BLOCK, null, null);
                }
                return new SkinnyMethodAdapter(cw, 1, methodName, InvocationMethodFactory.COMPILED_CALL_SIG_TWO, null, null);
            }
            case 3: {
                if (block) {
                    return new SkinnyMethodAdapter(cw, 1, methodName, InvocationMethodFactory.COMPILED_CALL_SIG_THREE_BLOCK, null, null);
                }
                return new SkinnyMethodAdapter(cw, 1, methodName, InvocationMethodFactory.COMPILED_CALL_SIG_THREE, null, null);
            }
        }
    }
    
    private void addAnnotatedMethodInvoker(final ClassWriter cw, final String callName, final String superClass, final List<JavaMethodDescriptor> descs) {
        for (final JavaMethodDescriptor desc : descs) {
            int specificArity = -1;
            if (desc.optional == 0 && !desc.rest) {
                if (desc.required == 0) {
                    if (desc.actualRequired <= 3) {
                        specificArity = desc.actualRequired;
                    }
                    else {
                        specificArity = -1;
                    }
                }
                else if (desc.required >= 0 && desc.required <= 3) {
                    specificArity = desc.required;
                }
            }
            final boolean hasBlock = desc.hasBlock;
            SkinnyMethodAdapter mv = null;
            mv = this.beginMethod(cw, callName, specificArity, hasBlock);
            mv.visitCode();
            mv.line(-1);
            this.createAnnotatedMethodInvocation(desc, mv, superClass, specificArity, hasBlock);
            mv.end();
        }
    }
    
    private void createAnnotatedMethodInvocation(final JavaMethodDescriptor desc, final SkinnyMethodAdapter method, final String superClass, final int specificArity, final boolean block) {
        final String typePath = desc.declaringClassPath;
        final String javaMethodName = desc.name;
        this.checkArity(desc.anno, method, specificArity);
        final CallConfiguration callConfig = CallConfiguration.getCallConfigByAnno(desc.anno);
        if (!callConfig.isNoop()) {
            this.invokeCallConfigPre(method, superClass, specificArity, block, callConfig);
        }
        int traceBoolIndex = -1;
        if (RubyInstanceConfig.FULL_TRACE_ENABLED) {
            switch (specificArity) {
                case -1: {
                    traceBoolIndex = 5 + (block ? 1 : 0) + 1;
                    break;
                }
                case 0: {
                    traceBoolIndex = 5 + (block ? 1 : 0);
                    break;
                }
                default: {
                    traceBoolIndex = 5 + specificArity + (block ? 1 : 0) + 1;
                    break;
                }
            }
            method.aload(1);
            method.invokevirtual(CodegenUtils.p(ThreadContext.class), "getRuntime", CodegenUtils.sig(Ruby.class, new Class[0]));
            method.invokevirtual(CodegenUtils.p(Ruby.class), "hasEventHooks", CodegenUtils.sig(Boolean.TYPE, new Class[0]));
            method.istore(traceBoolIndex);
            this.invokeCCallTrace(method, traceBoolIndex);
        }
        final Label tryBegin = new Label();
        final Label tryEnd = new Label();
        final Label doFinally = new Label();
        if (!callConfig.isNoop()) {
            method.trycatch(tryBegin, tryEnd, doFinally, null);
        }
        method.label(tryBegin);
        this.loadReceiver(typePath, desc, method);
        this.loadArguments(method, desc, specificArity);
        this.loadBlock(method, specificArity, block);
        if (Modifier.isStatic(desc.modifiers)) {
            method.invokestatic(typePath, javaMethodName, desc.signature);
        }
        else {
            method.invokevirtual(typePath, javaMethodName, desc.signature);
        }
        if (desc.getReturnClass() == Void.TYPE) {
            method.aload(1);
            method.invokevirtual(CodegenUtils.p(ThreadContext.class), "getRuntime", CodegenUtils.sig(Ruby.class, new Class[0]));
            method.invokevirtual(CodegenUtils.p(Ruby.class), "getNil", CodegenUtils.sig(IRubyObject.class, new Class[0]));
        }
        method.label(tryEnd);
        if (RubyInstanceConfig.FULL_TRACE_ENABLED) {
            this.invokeCReturnTrace(method, traceBoolIndex);
        }
        if (!callConfig.isNoop()) {
            this.invokeCallConfigPost(method, superClass, callConfig);
        }
        method.visitInsn(176);
        if (!callConfig.isNoop()) {
            method.label(doFinally);
            if (RubyInstanceConfig.FULL_TRACE_ENABLED) {
                this.invokeCReturnTrace(method, traceBoolIndex);
            }
            if (!callConfig.isNoop()) {
                this.invokeCallConfigPost(method, superClass, callConfig);
            }
            method.athrow();
        }
    }
    
    private void invokeCCallTrace(final SkinnyMethodAdapter method, final int traceBoolIndex) {
        method.aloadMany(0, 1);
        method.iload(traceBoolIndex);
        method.aload(4);
        method.invokevirtual(CodegenUtils.p(JavaMethod.class), "callTrace", CodegenUtils.sig(Void.TYPE, ThreadContext.class, Boolean.TYPE, String.class));
    }
    
    private void invokeCReturnTrace(final SkinnyMethodAdapter method, final int traceBoolIndex) {
        method.aloadMany(0, 1);
        method.iload(traceBoolIndex);
        method.aload(4);
        method.invokevirtual(CodegenUtils.p(JavaMethod.class), "returnTrace", CodegenUtils.sig(Void.TYPE, ThreadContext.class, Boolean.TYPE, String.class));
    }
    
    private void invokeTraceCompiledPre(final SkinnyMethodAdapter mv, final String superClass, final int traceBoolIndex, final String filename, final int line) {
        mv.aloadMany(0, 1);
        mv.iload(traceBoolIndex);
        mv.aload(4);
        mv.ldc(filename);
        mv.ldc(line);
        mv.invokevirtual(superClass, "callTraceCompiled", CodegenUtils.sig(Void.TYPE, ThreadContext.class, Boolean.TYPE, String.class, String.class, Integer.TYPE));
    }
    
    private void invokeTraceCompiledPost(final SkinnyMethodAdapter mv, final String superClass, final int traceBoolIndex) {
        mv.aloadMany(0, 1);
        mv.iload(traceBoolIndex);
        mv.aload(4);
        mv.invokevirtual(superClass, "returnTraceCompiled", CodegenUtils.sig(Void.TYPE, ThreadContext.class, Boolean.TYPE, String.class));
    }
    
    static {
        COMPILED_SUPER_CLASS = CodegenUtils.p(CompiledMethod.class);
        COMPILED_CALL_SIG = CodegenUtils.sig(IRubyObject.class, CodegenUtils.params(ThreadContext.class, IRubyObject.class, RubyModule.class, String.class, IRubyObject[].class));
        COMPILED_CALL_SIG_BLOCK = CodegenUtils.sig(IRubyObject.class, CodegenUtils.params(ThreadContext.class, IRubyObject.class, RubyModule.class, String.class, IRubyObject[].class, Block.class));
        COMPILED_CALL_SIG_ZERO_BLOCK = CodegenUtils.sig(IRubyObject.class, CodegenUtils.params(ThreadContext.class, IRubyObject.class, RubyModule.class, String.class, Block.class));
        COMPILED_CALL_SIG_ZERO = CodegenUtils.sig(IRubyObject.class, CodegenUtils.params(ThreadContext.class, IRubyObject.class, RubyModule.class, String.class));
        COMPILED_CALL_SIG_ONE_BLOCK = CodegenUtils.sig(IRubyObject.class, CodegenUtils.params(ThreadContext.class, IRubyObject.class, RubyModule.class, String.class, IRubyObject.class, Block.class));
        COMPILED_CALL_SIG_ONE = CodegenUtils.sig(IRubyObject.class, CodegenUtils.params(ThreadContext.class, IRubyObject.class, RubyModule.class, String.class, IRubyObject.class));
        COMPILED_CALL_SIG_TWO_BLOCK = CodegenUtils.sig(IRubyObject.class, CodegenUtils.params(ThreadContext.class, IRubyObject.class, RubyModule.class, String.class, IRubyObject.class, IRubyObject.class, Block.class));
        COMPILED_CALL_SIG_TWO = CodegenUtils.sig(IRubyObject.class, CodegenUtils.params(ThreadContext.class, IRubyObject.class, RubyModule.class, String.class, IRubyObject.class, IRubyObject.class));
        COMPILED_CALL_SIG_THREE_BLOCK = CodegenUtils.sig(IRubyObject.class, CodegenUtils.params(ThreadContext.class, IRubyObject.class, RubyModule.class, String.class, IRubyObject.class, IRubyObject.class, IRubyObject.class, Block.class));
        COMPILED_CALL_SIG_THREE = CodegenUtils.sig(IRubyObject.class, CodegenUtils.params(ThreadContext.class, IRubyObject.class, RubyModule.class, String.class, IRubyObject.class, IRubyObject.class, IRubyObject.class));
        BLOCK_CALL_SIG = CodegenUtils.sig(RubyKernel.IRUBY_OBJECT, CodegenUtils.params(ThreadContext.class, RubyKernel.IRUBY_OBJECT, IRubyObject.class, Block.class));
        BLOCK_CALL_SIG19 = CodegenUtils.sig(RubyKernel.IRUBY_OBJECT, CodegenUtils.params(ThreadContext.class, IRubyObject.class, IRubyObject[].class, Block.class));
        JAVA_SUPER_SIG = CodegenUtils.sig(Void.TYPE, CodegenUtils.params(RubyModule.class, Visibility.class));
    }
    
    private static class DescriptorInfo
    {
        private int min;
        private int max;
        private boolean frame;
        private boolean scope;
        private boolean backtrace;
        private boolean rest;
        private boolean block;
        
        public DescriptorInfo(final List<JavaMethodDescriptor> descs) {
            this.min = Integer.MAX_VALUE;
            this.max = 0;
            this.frame = false;
            this.scope = false;
            this.backtrace = false;
            this.rest = false;
            this.block = false;
            boolean first = true;
            boolean lastBlock = false;
            for (final JavaMethodDescriptor desc : descs) {
                if (first) {
                    first = false;
                }
                else if (lastBlock != desc.hasBlock) {
                    throw new RuntimeException("Mismatched block parameters for method " + desc.declaringClassName + "." + desc.name);
                }
                lastBlock = desc.hasBlock;
                int specificArity = -1;
                if (desc.hasVarArgs) {
                    if (desc.optional == 0 && !desc.rest && desc.required == 0) {
                        throw new RuntimeException("IRubyObject[] args but neither of optional or rest specified for method " + desc.declaringClassName + "." + desc.name);
                    }
                    this.rest = true;
                    if (descs.size() == 1) {
                        this.min = -1;
                    }
                }
                else {
                    if (desc.optional == 0 && !desc.rest) {
                        if (desc.required == 0) {
                            if (desc.actualRequired > 3) {
                                throw new RuntimeException("Invalid specific-arity number of arguments (" + desc.actualRequired + ") on method " + desc.declaringClassName + "." + desc.name);
                            }
                            specificArity = desc.actualRequired;
                        }
                        else if (desc.required >= 0 && desc.required <= 3) {
                            if (desc.actualRequired != desc.required) {
                                throw new RuntimeException("Specified required args does not match actual on method " + desc.declaringClassName + "." + desc.name);
                            }
                            specificArity = desc.required;
                        }
                    }
                    if (specificArity < this.min) {
                        this.min = specificArity;
                    }
                    if (specificArity > this.max) {
                        this.max = specificArity;
                    }
                }
                this.frame |= desc.anno.frame();
                this.scope |= desc.anno.scope();
                this.backtrace |= desc.anno.backtrace();
                this.block |= desc.hasBlock;
            }
        }
        
        public boolean isBacktrace() {
            return this.backtrace;
        }
        
        public boolean isFrame() {
            return this.frame;
        }
        
        public int getMax() {
            return this.max;
        }
        
        public int getMin() {
            return this.min;
        }
        
        public boolean isScope() {
            return this.scope;
        }
        
        public boolean isRest() {
            return this.rest;
        }
        
        public boolean isBlock() {
            return this.block;
        }
    }
}
