// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.impl;

import org.jruby.org.objectweb.asm.MethodVisitor;
import org.jruby.util.SafePropertyAccessor;
import org.jruby.compiler.BodyCompiler;
import org.jruby.compiler.ASTInspector;
import org.jruby.compiler.CompilerCallback;
import java.lang.reflect.InvocationTargetException;
import org.jruby.Ruby;
import java.net.URL;
import org.jruby.org.objectweb.asm.Type;
import org.jruby.org.objectweb.asm.Label;
import org.jruby.ast.executable.AbstractScript;
import org.jruby.RubyInstanceConfig;
import org.jruby.runtime.Arity;
import org.jruby.internal.runtime.methods.CallConfiguration;
import java.io.FileOutputStream;
import java.util.Iterator;
import org.jruby.org.objectweb.asm.util.CheckClassAdapter;
import org.jruby.javasupport.util.RuntimeHelpers;
import java.io.IOException;
import java.io.File;
import org.jruby.org.objectweb.asm.ClassVisitor;
import org.jruby.org.objectweb.asm.ClassReader;
import org.jruby.org.objectweb.asm.util.TraceClassVisitor;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.PrintStream;
import org.jruby.util.JRubyClassLoader;
import java.util.ArrayList;
import org.jruby.util.CodegenUtils;
import org.jruby.runtime.Block;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;
import java.util.List;
import java.lang.reflect.Method;
import java.lang.reflect.Constructor;
import org.jruby.compiler.CacheCompiler;
import org.jruby.parser.StaticScope;
import org.jruby.org.objectweb.asm.ClassWriter;
import org.jruby.org.objectweb.asm.Opcodes;
import org.jruby.compiler.ScriptCompiler;

public class StandardASMCompiler implements ScriptCompiler, Opcodes
{
    public static final String THREADCONTEXT;
    public static final String RUBY;
    public static final String IRUBYOBJECT;
    public static final boolean VERIFY_CLASSFILES = true;
    public static final int THIS = 0;
    public static final int THREADCONTEXT_INDEX = 1;
    public static final int SELF_INDEX = 2;
    public static final int ARGS_INDEX = 3;
    public static final int CLOSURE_OFFSET = 0;
    public static final int DYNAMIC_SCOPE_OFFSET = 1;
    public static final int VARS_ARRAY_OFFSET = 2;
    public static final int EXCEPTION_OFFSET = 3;
    public static final int PREVIOUS_EXCEPTION_OFFSET = 4;
    public static final int FIRST_TEMP_OFFSET = 5;
    public static final int STARTING_DSTR_SIZE = 20;
    private String classname;
    private String sourcename;
    private Integer javaVersion;
    private ClassWriter classWriter;
    private SkinnyMethodAdapter initMethod;
    private SkinnyMethodAdapter clinitMethod;
    private int methodIndex;
    private int innerIndex;
    private int rescueNumber;
    private int ensureNumber;
    StaticScope topLevelScope;
    private CacheCompiler cacheCompiler;
    public static final Constructor invDynInvCompilerConstructor;
    public static final Method invDynSupportInstaller;
    private List<InvokerDescriptor> invokerDescriptors;
    private List<BlockCallbackDescriptor> blockCallbackDescriptors;
    private List<BlockCallbackDescriptor> blockCallback19Descriptors;
    private int constants;
    
    public static String getStaticMethodSignature(final String classname, final int args) {
        switch (args) {
            case 0: {
                return CodegenUtils.sig(IRubyObject.class, "L" + classname + ";", ThreadContext.class, IRubyObject.class, Block.class);
            }
            case 1: {
                return CodegenUtils.sig(IRubyObject.class, "L" + classname + ";", ThreadContext.class, IRubyObject.class, IRubyObject.class, Block.class);
            }
            case 2: {
                return CodegenUtils.sig(IRubyObject.class, "L" + classname + ";", ThreadContext.class, IRubyObject.class, IRubyObject.class, IRubyObject.class, Block.class);
            }
            case 3: {
                return CodegenUtils.sig(IRubyObject.class, "L" + classname + ";", ThreadContext.class, IRubyObject.class, IRubyObject.class, IRubyObject.class, IRubyObject.class, Block.class);
            }
            case 4: {
                return CodegenUtils.sig(IRubyObject.class, "L" + classname + ";", ThreadContext.class, IRubyObject.class, IRubyObject[].class, Block.class);
            }
            default: {
                throw new RuntimeException("unsupported arity: " + args);
            }
        }
    }
    
    public static Class[] getStaticMethodParams(final Class target, final int args) {
        switch (args) {
            case 0: {
                return new Class[] { target, ThreadContext.class, IRubyObject.class, Block.class };
            }
            case 1: {
                return new Class[] { target, ThreadContext.class, IRubyObject.class, IRubyObject.class, Block.class };
            }
            case 2: {
                return new Class[] { target, ThreadContext.class, IRubyObject.class, IRubyObject.class, IRubyObject.class, Block.class };
            }
            case 3: {
                return new Class[] { target, ThreadContext.class, IRubyObject.class, IRubyObject.class, IRubyObject.class, IRubyObject.class, Block.class };
            }
            case 4: {
                return new Class[] { target, ThreadContext.class, IRubyObject.class, IRubyObject[].class, Block.class };
            }
            default: {
                throw new RuntimeException("unsupported arity: " + args);
            }
        }
    }
    
    public static String getMethodSignature(final int args) {
        switch (args) {
            case 0: {
                return CodegenUtils.sig(IRubyObject.class, ThreadContext.class, IRubyObject.class, Block.class);
            }
            case 1: {
                return CodegenUtils.sig(IRubyObject.class, ThreadContext.class, IRubyObject.class, IRubyObject.class, Block.class);
            }
            case 2: {
                return CodegenUtils.sig(IRubyObject.class, ThreadContext.class, IRubyObject.class, IRubyObject.class, IRubyObject.class, Block.class);
            }
            case 3: {
                return CodegenUtils.sig(IRubyObject.class, ThreadContext.class, IRubyObject.class, IRubyObject.class, IRubyObject.class, IRubyObject.class, Block.class);
            }
            case 4: {
                return CodegenUtils.sig(IRubyObject.class, ThreadContext.class, IRubyObject.class, IRubyObject[].class, Block.class);
            }
            default: {
                throw new RuntimeException("unsupported arity: " + args);
            }
        }
    }
    
    public static String getStaticClosureSignature(final String classdesc) {
        return CodegenUtils.sig(IRubyObject.class, "L" + classdesc + ";", ThreadContext.class, IRubyObject.class, IRubyObject.class, Block.class);
    }
    
    public static String getStaticClosure19Signature(final String classdesc) {
        return CodegenUtils.sig(IRubyObject.class, "L" + classdesc + ";", ThreadContext.class, IRubyObject.class, IRubyObject[].class, Block.class);
    }
    
    public static String getClosureSignature() {
        return CodegenUtils.sig(IRubyObject.class, ThreadContext.class, IRubyObject.class, IRubyObject.class, Block.class);
    }
    
    public static String getClosure19Signature() {
        return CodegenUtils.sig(IRubyObject.class, ThreadContext.class, IRubyObject.class, IRubyObject[].class, Block.class);
    }
    
    public StandardASMCompiler(final String classname, final String sourcename) {
        this.methodIndex = 0;
        this.innerIndex = 0;
        this.rescueNumber = 1;
        this.ensureNumber = 1;
        this.invokerDescriptors = new ArrayList<InvokerDescriptor>();
        this.blockCallbackDescriptors = new ArrayList<BlockCallbackDescriptor>();
        this.blockCallback19Descriptors = new ArrayList<BlockCallbackDescriptor>();
        this.constants = 0;
        this.classname = classname;
        this.sourcename = sourcename;
    }
    
    public void setJavaVersion(final Integer javaVersion) {
        this.javaVersion = javaVersion;
    }
    
    public byte[] getClassByteArray() {
        return this.classWriter.toByteArray();
    }
    
    public Class<?> loadClass(final JRubyClassLoader classLoader) throws ClassNotFoundException {
        classLoader.defineClass(CodegenUtils.c(this.getClassname()), this.classWriter.toByteArray());
        return classLoader.loadClass(CodegenUtils.c(this.getClassname()));
    }
    
    public void dumpClass(final PrintStream out) {
        final PrintWriter pw = new PrintWriter(out);
        try {
            final TraceClassVisitor tcv = new TraceClassVisitor(pw);
            new ClassReader(this.classWriter.toByteArray()).accept(tcv, 0);
        }
        finally {
            pw.close();
        }
    }
    
    public void writeClass(final File destination) throws IOException {
        this.writeClass(this.getClassname(), destination, this.classWriter);
    }
    
    public void writeInvokers(final File destination) throws IOException {
        for (final InvokerDescriptor descriptor : this.invokerDescriptors) {
            final byte[] invokerBytes = RuntimeHelpers.defOffline(descriptor.getName(), descriptor.getClassname(), descriptor.getInvokerName(), descriptor.getArity(), descriptor.getScope(), descriptor.getCallConfig(), descriptor.getFile(), descriptor.getLine());
            CheckClassAdapter.verify(new ClassReader(invokerBytes), false, new PrintWriter(System.err));
            this.writeClassFile(destination, invokerBytes, descriptor.getInvokerName());
        }
        for (final BlockCallbackDescriptor descriptor2 : this.blockCallbackDescriptors) {
            final byte[] callbackBytes = RuntimeHelpers.createBlockCallbackOffline(descriptor2.getClassname(), descriptor2.getMethod(), descriptor2.getFile(), descriptor2.getLine());
            CheckClassAdapter.verify(new ClassReader(callbackBytes), false, new PrintWriter(System.err));
            this.writeClassFile(destination, callbackBytes, descriptor2.getCallbackName());
        }
        for (final BlockCallbackDescriptor descriptor2 : this.blockCallback19Descriptors) {
            final byte[] callbackBytes = RuntimeHelpers.createBlockCallback19Offline(descriptor2.getClassname(), descriptor2.getMethod(), descriptor2.getFile(), descriptor2.getLine());
            CheckClassAdapter.verify(new ClassReader(callbackBytes), false, new PrintWriter(System.err));
            this.writeClassFile(destination, callbackBytes, descriptor2.getCallbackName());
        }
    }
    
    private void writeClass(final String classname, final File destination, final ClassWriter writer) throws IOException {
        final byte[] bytecode = writer.toByteArray();
        CheckClassAdapter.verify(new ClassReader(bytecode), false, new PrintWriter(System.err));
        this.writeClassFile(destination, bytecode, classname);
    }
    
    private void writeClassFile(final File destination, final byte[] bytecode, final String classname) throws IOException {
        final String fullname = classname + ".class";
        String filename = null;
        String path = null;
        if (fullname.lastIndexOf("/") == -1) {
            filename = fullname;
            path = "";
        }
        else {
            filename = fullname.substring(fullname.lastIndexOf("/") + 1);
            path = fullname.substring(0, fullname.lastIndexOf("/"));
        }
        final File pathfile = new File(destination, path);
        pathfile.mkdirs();
        final FileOutputStream out = new FileOutputStream(new File(pathfile, filename));
        try {
            out.write(bytecode);
        }
        finally {
            out.close();
        }
    }
    
    public void addInvokerDescriptor(final String newMethodName, final int methodArity, final StaticScope scope, final CallConfiguration callConfig, final String filename, final int line) {
        final String classPath = this.classname.replaceAll("/", "_");
        final Arity arity = Arity.createArity(methodArity);
        final String invokerName = classPath + "Invoker" + newMethodName + arity;
        final InvokerDescriptor descriptor = new InvokerDescriptor(newMethodName, this.classname, invokerName, arity, scope, callConfig, filename, line);
        this.invokerDescriptors.add(descriptor);
    }
    
    public void addBlockCallbackDescriptor(final String method, final String file, final int line) {
        this.blockCallbackDescriptors.add(new BlockCallbackDescriptor(method, this.classname, file, line));
    }
    
    public void addBlockCallback19Descriptor(final String method, final String file, final int line) {
        this.blockCallback19Descriptors.add(new BlockCallbackDescriptor(method, this.classname, file, line));
    }
    
    public String getClassname() {
        return this.classname;
    }
    
    public String getSourcename() {
        return this.sourcename;
    }
    
    public ClassVisitor getClassVisitor() {
        return this.classWriter;
    }
    
    public void startScript(final StaticScope scope) {
        (this.classWriter = new ClassWriter(3)).visit((this.javaVersion == null) ? RubyInstanceConfig.JAVA_VERSION : ((int)this.javaVersion), 33, this.getClassname(), null, CodegenUtils.p(AbstractScript.class), null);
        final SkinnyMethodAdapter method = new SkinnyMethodAdapter(this.getClassVisitor(), 4106, "setPosition", CodegenUtils.sig(Void.TYPE, CodegenUtils.params(ThreadContext.class, Integer.TYPE)), null, null);
        method.start();
        method.aload(0);
        method.ldc(this.sourcename);
        method.iload(1);
        method.invokevirtual(CodegenUtils.p(ThreadContext.class), "setFileAndLine", CodegenUtils.sig(Void.TYPE, String.class, Integer.TYPE));
        method.voidreturn();
        method.end();
        this.topLevelScope = scope;
        this.beginInit();
        this.cacheCompiler = new InheritedCacheCompiler(this);
        final File sourceFile = new File(this.getSourcename());
        this.classWriter.visitSource(this.sourcename, sourceFile.getAbsolutePath());
    }
    
    public void endScript(final boolean generateLoad, final boolean generateMain) {
        final String methodName = "__file__";
        if (generateLoad || generateMain) {
            final SkinnyMethodAdapter method = new SkinnyMethodAdapter(this.getClassVisitor(), 1, "load", getMethodSignature(4), null, null);
            method.start();
            final Label tryBegin = new Label();
            final Label tryFinally = new Label();
            method.label(tryBegin);
            method.aload(1);
            final String scopeNames = RuntimeHelpers.encodeScope(this.topLevelScope);
            method.ldc(scopeNames);
            method.invokestatic(CodegenUtils.p(RuntimeHelpers.class), "preLoad", CodegenUtils.sig(Void.TYPE, ThreadContext.class, String.class));
            method.aload(0);
            method.aload(1);
            method.aload(2);
            method.aload(3);
            method.aload(4);
            method.invokestatic(this.getClassname(), methodName, getStaticMethodSignature(this.getClassname(), 4));
            method.aload(1);
            method.invokestatic(CodegenUtils.p(RuntimeHelpers.class), "postLoad", CodegenUtils.sig(Void.TYPE, ThreadContext.class));
            method.areturn();
            method.label(tryFinally);
            method.aload(1);
            method.invokestatic(CodegenUtils.p(RuntimeHelpers.class), "postLoad", CodegenUtils.sig(Void.TYPE, ThreadContext.class));
            method.athrow();
            method.trycatch(tryBegin, tryFinally, tryFinally, null);
            method.end();
        }
        if (generateMain) {
            final SkinnyMethodAdapter method = new SkinnyMethodAdapter(this.getClassVisitor(), 9, "main", CodegenUtils.sig(Void.TYPE, CodegenUtils.params(String[].class)), null, null);
            method.start();
            method.newobj(this.getClassname());
            method.dup();
            method.invokespecial(this.getClassname(), "<init>", CodegenUtils.sig(Void.TYPE, new Class[0]));
            method.dup();
            method.ldc(Type.getType("L" + this.getClassname() + ";"));
            method.invokevirtual(CodegenUtils.p(Class.class), "getClassLoader", CodegenUtils.sig(ClassLoader.class, new Class[0]));
            method.ldc(this.getClassname() + ".class");
            method.invokevirtual(CodegenUtils.p(ClassLoader.class), "getResource", CodegenUtils.sig(URL.class, String.class));
            method.invokevirtual(CodegenUtils.p(Object.class), "toString", CodegenUtils.sig(String.class, new Class[0]));
            method.astore(1);
            method.aload(1);
            method.invokevirtual(CodegenUtils.p(AbstractScript.class), "setFilename", CodegenUtils.sig(Void.TYPE, String.class));
            method.newobj(CodegenUtils.p(RubyInstanceConfig.class));
            method.dup();
            method.invokespecial(CodegenUtils.p(RubyInstanceConfig.class), "<init>", "()V");
            method.dup();
            method.aload(0);
            method.invokevirtual(CodegenUtils.p(RubyInstanceConfig.class), "setArgv", CodegenUtils.sig(Void.TYPE, String[].class));
            method.dup();
            method.aload(1);
            method.invokevirtual(CodegenUtils.p(RubyInstanceConfig.class), "setScriptFileName", CodegenUtils.sig(Void.TYPE, String.class));
            method.invokestatic(CodegenUtils.p(Ruby.class), "newInstance", CodegenUtils.sig(Ruby.class, RubyInstanceConfig.class));
            method.dup();
            method.invokevirtual(StandardASMCompiler.RUBY, "getCurrentContext", CodegenUtils.sig(ThreadContext.class, new Class[0]));
            method.swap();
            method.invokevirtual(StandardASMCompiler.RUBY, "getTopSelf", CodegenUtils.sig(IRubyObject.class, new Class[0]));
            method.getstatic(CodegenUtils.p(IRubyObject.class), "NULL_ARRAY", CodegenUtils.ci(IRubyObject[].class));
            method.getstatic(CodegenUtils.p(Block.class), "NULL_BLOCK", CodegenUtils.ci(Block.class));
            method.invokevirtual(this.getClassname(), "load", getMethodSignature(4));
            method.voidreturn();
            method.end();
        }
        this.getCacheCompiler().finish();
        this.endInit();
        this.endClassInit();
    }
    
    public static String buildStaticScopeNames(final StaticScope scope) {
        return RuntimeHelpers.encodeScope(scope);
    }
    
    private void beginInit() {
        final ClassVisitor cv = this.getClassVisitor();
        (this.initMethod = new SkinnyMethodAdapter(cv, 1, "<init>", CodegenUtils.sig(Void.TYPE, new Class[0]), null, null)).start();
        this.initMethod.aload(0);
        this.initMethod.invokespecial(CodegenUtils.p(AbstractScript.class), "<init>", CodegenUtils.sig(Void.TYPE, new Class[0]));
        this.initMethod.aload(0);
        this.initMethod.ldc(this.getSourcename());
        this.initMethod.putfield(this.getClassname(), "filename", CodegenUtils.ci(String.class));
    }
    
    private void endInit() {
        this.initMethod.voidreturn();
        this.initMethod.end();
    }
    
    private void beginClassInit() {
        final ClassVisitor cv = this.getClassVisitor();
        (this.clinitMethod = new SkinnyMethodAdapter(cv, 9, "<clinit>", CodegenUtils.sig(Void.TYPE, new Class[0]), null, null)).start();
        if (StandardASMCompiler.invDynSupportInstaller != null) {
            try {
                StandardASMCompiler.invDynSupportInstaller.invoke(null, this.clinitMethod, this.getClassname());
            }
            catch (IllegalAccessException ex) {
                ex.printStackTrace();
            }
            catch (IllegalArgumentException ex2) {
                ex2.printStackTrace();
            }
            catch (InvocationTargetException ex3) {
                ex3.printStackTrace();
            }
        }
    }
    
    private void endClassInit() {
        if (this.clinitMethod != null) {
            this.clinitMethod.voidreturn();
            this.clinitMethod.end();
        }
    }
    
    public SkinnyMethodAdapter getInitMethod() {
        return this.initMethod;
    }
    
    public SkinnyMethodAdapter getClassInitMethod() {
        if (this.clinitMethod == null) {
            this.beginClassInit();
        }
        return this.clinitMethod;
    }
    
    public CacheCompiler getCacheCompiler() {
        return this.cacheCompiler;
    }
    
    public BodyCompiler startMethod(final String rubyName, final String javaName, final CompilerCallback args, final StaticScope scope, final ASTInspector inspector) {
        final RootScopedBodyCompiler methodCompiler = new MethodBodyCompiler(this, rubyName, javaName, inspector, scope);
        methodCompiler.beginMethod(args, scope);
        return methodCompiler;
    }
    
    public BodyCompiler startFileMethod(final CompilerCallback args, final StaticScope scope, final ASTInspector inspector) {
        final MethodBodyCompiler methodCompiler = new MethodBodyCompiler(this, "__file__", "__file__", inspector, scope);
        methodCompiler.beginMethod(args, scope);
        SkinnyMethodAdapter method = new SkinnyMethodAdapter(this.getClassVisitor(), 1, "__file__", getMethodSignature(4), null, null);
        method.start();
        method.aload(0);
        method.aload(1);
        method.aload(2);
        method.aload(3);
        method.aload(4);
        method.invokestatic(this.getClassname(), "__file__", getStaticMethodSignature(this.getClassname(), 4));
        method.areturn();
        method.end();
        if (methodCompiler.isSpecificArity()) {
            method = new SkinnyMethodAdapter(this.getClassVisitor(), 1, "__file__", getMethodSignature(scope.getRequiredArgs()), null, null);
            method.start();
            method.aload(0);
            method.aload(1);
            method.aload(2);
            for (int i = 0; i < scope.getRequiredArgs(); ++i) {
                method.aload(3 + i);
            }
            method.aload(3 + scope.getRequiredArgs());
            method.invokestatic(this.getClassname(), "__file__", getStaticMethodSignature(this.getClassname(), scope.getRequiredArgs()));
            method.areturn();
            method.end();
        }
        return methodCompiler;
    }
    
    public BodyCompiler startRoot(final String rubyName, final String javaName, final StaticScope scope, final ASTInspector inspector) {
        final RootScopedBodyCompiler methodCompiler = new MethodBodyCompiler(this, rubyName, javaName, inspector, scope);
        methodCompiler.beginMethod(null, scope);
        return methodCompiler;
    }
    
    public int getMethodIndex() {
        return this.methodIndex;
    }
    
    public int getAndIncrementMethodIndex() {
        return this.methodIndex++;
    }
    
    public int getInnerIndex() {
        return this.innerIndex;
    }
    
    public int getAndIncrementInnerIndex() {
        return this.innerIndex++;
    }
    
    public int getRescueNumber() {
        return this.rescueNumber;
    }
    
    public int getAndIncrementRescueNumber() {
        return this.rescueNumber++;
    }
    
    public int getEnsureNumber() {
        return this.ensureNumber;
    }
    
    public int getAndIncrementEnsureNumber() {
        return this.ensureNumber++;
    }
    
    public String getNewConstant(final String type, final String name_prefix) {
        return this.getNewConstant(type, name_prefix, null);
    }
    
    public synchronized String getNewConstantName() {
        return "_" + this.constants++;
    }
    
    public String getNewConstant(final String type, final String name_prefix, final Object init) {
        final ClassVisitor cv = this.getClassVisitor();
        final String realName = this.getNewConstantName();
        cv.visitField(2, realName, type, null, null).visitEnd();
        if (init != null) {
            this.initMethod.aload(0);
            this.initMethod.ldc(init);
            this.initMethod.putfield(this.getClassname(), realName, type);
        }
        return realName;
    }
    
    public String getNewField(final String type, final String name, final Object init) {
        final ClassVisitor cv = this.getClassVisitor();
        cv.visitField(2, name, type, null, null).visitEnd();
        if (init != null) {
            this.initMethod.aload(0);
            this.initMethod.ldc(init);
            this.initMethod.putfield(this.getClassname(), name, type);
        }
        return name;
    }
    
    public String getNewStaticConstant(final String type, final String name_prefix) {
        final ClassVisitor cv = this.getClassVisitor();
        final String realName;
        synchronized (this) {
            realName = "__" + this.constants++;
        }
        cv.visitField(26, realName, type, null, null).visitEnd();
        return realName;
    }
    
    static {
        THREADCONTEXT = CodegenUtils.p(ThreadContext.class);
        RUBY = CodegenUtils.p(Ruby.class);
        IRUBYOBJECT = CodegenUtils.p(IRubyObject.class);
        Constructor compilerConstructor = null;
        Method installerMethod = null;
        try {
            if (SafePropertyAccessor.getBoolean("jruby.compile.invokedynamic")) {
                final Class compiler = Class.forName("org.jruby.compiler.impl.InvokeDynamicInvocationCompiler");
                final Class support = Class.forName("org.jruby.runtime.invokedynamic.InvokeDynamicSupport");
                compilerConstructor = compiler.getConstructor(BaseBodyCompiler.class, SkinnyMethodAdapter.class);
                installerMethod = support.getDeclaredMethod("installBytecode", MethodVisitor.class, String.class);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        invDynInvCompilerConstructor = compilerConstructor;
        invDynSupportInstaller = installerMethod;
    }
    
    public static class InvokerDescriptor
    {
        private final String name;
        private final String classname;
        private final String invokerName;
        private final Arity arity;
        private final StaticScope scope;
        private final CallConfiguration callConfig;
        private final String file;
        private final int line;
        
        public InvokerDescriptor(final String name, final String classname, final String invokerName, final Arity arity, final StaticScope scope, final CallConfiguration callConfig, final String file, final int line) {
            this.name = name;
            this.classname = classname;
            this.invokerName = invokerName;
            this.arity = arity;
            this.scope = scope;
            this.callConfig = callConfig;
            this.file = file;
            this.line = line;
        }
        
        public Arity getArity() {
            return this.arity;
        }
        
        public CallConfiguration getCallConfig() {
            return this.callConfig;
        }
        
        public String getClassname() {
            return this.classname;
        }
        
        public String getFile() {
            return this.file;
        }
        
        public String getInvokerName() {
            return this.invokerName;
        }
        
        public int getLine() {
            return this.line;
        }
        
        public String getName() {
            return this.name;
        }
        
        public StaticScope getScope() {
            return this.scope;
        }
    }
    
    private static class BlockCallbackDescriptor
    {
        private final String method;
        private final String classname;
        private final String callbackName;
        private final String file;
        private final int line;
        
        public BlockCallbackDescriptor(final String method, final String classname, final String file, final int line) {
            this.method = method;
            this.classname = classname;
            this.callbackName = classname + "BlockCallback$" + method + "xx1";
            this.file = file;
            this.line = line;
        }
        
        public String getClassname() {
            return this.classname;
        }
        
        public String getMethod() {
            return this.method;
        }
        
        public String getCallbackName() {
            return this.callbackName;
        }
        
        public String getFile() {
            return this.file;
        }
        
        public int getLine() {
            return this.line;
        }
    }
}
