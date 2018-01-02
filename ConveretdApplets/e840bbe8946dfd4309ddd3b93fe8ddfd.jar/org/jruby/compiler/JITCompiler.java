// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler;

import org.jruby.org.objectweb.asm.ClassVisitor;
import org.jruby.org.objectweb.asm.util.TraceClassVisitor;
import java.io.OutputStream;
import java.io.PrintWriter;
import org.jruby.org.objectweb.asm.ClassReader;
import java.io.FileInputStream;
import org.jruby.util.JavaNameMangler;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.parser.StaticScope;
import org.jruby.compiler.impl.StandardASMCompiler;
import java.util.concurrent.atomic.AtomicLong;
import org.jruby.ast.ArgsNode;
import java.io.FileOutputStream;
import java.io.File;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;
import java.security.MessageDigest;
import org.jruby.RubyEncoding;
import java.util.Set;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.ast.executable.Script;
import org.jruby.util.ClassCache;
import org.jruby.ast.Node;
import org.jruby.ast.util.SexpMaker;
import org.jruby.RubyModule;
import org.jruby.MetaClass;
import org.jruby.RubyInstanceConfig;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.ThreadContext;
import org.jruby.internal.runtime.methods.DefaultMethod;
import org.jruby.Ruby;

public class JITCompiler implements JITCompilerMBean
{
    public static final boolean USE_CACHE = true;
    public static final String RUBY_JIT_PREFIX = "rubyjit";
    private final JITCounts counts;
    
    public JITCompiler(final Ruby ruby) {
        this.counts = new JITCounts();
        ruby.getBeanManager().register(this);
    }
    
    public DynamicMethod tryJIT(final DefaultMethod method, final ThreadContext context, final String name) {
        if (context.getRuntime().getInstanceConfig().getCompileMode().shouldJIT()) {
            return this.jitIsEnabled(method, context, name);
        }
        return null;
    }
    
    private DynamicMethod jitIsEnabled(final DefaultMethod method, final ThreadContext context, final String name) {
        final RubyInstanceConfig instanceConfig = context.getRuntime().getInstanceConfig();
        if (method.incrementCallCount() >= instanceConfig.getJitThreshold()) {
            return this.jitThresholdReached(method, instanceConfig, context, name);
        }
        return null;
    }
    
    private DynamicMethod jitThresholdReached(final DefaultMethod method, final RubyInstanceConfig instanceConfig, final ThreadContext context, final String name) {
        try {
            final ClassCache classCache = instanceConfig.getClassCache();
            if (classCache.isFull()) {
                this.counts.abandonCount.incrementAndGet();
                method.setCallCount(-1);
                return null;
            }
            final String moduleName = method.getImplementationClass().getName();
            if (instanceConfig.getExcludedMethods().size() > 0) {
                String excludeModuleName = moduleName;
                if (method.getImplementationClass().isSingleton()) {
                    final IRubyObject possibleRealClass = ((MetaClass)method.getImplementationClass()).getAttached();
                    if (possibleRealClass instanceof RubyModule) {
                        excludeModuleName = "Meta:" + ((RubyModule)possibleRealClass).getName();
                    }
                }
                if (instanceConfig.getExcludedMethods().contains(excludeModuleName) || instanceConfig.getExcludedMethods().contains(excludeModuleName + "#" + name) || instanceConfig.getExcludedMethods().contains(name)) {
                    method.setCallCount(-1);
                    return null;
                }
            }
            final String key = SexpMaker.create(name, method.getArgsNode(), method.getBodyNode());
            final JITClassGenerator generator = new JITClassGenerator(name, key, context.getRuntime(), method, context, this.counts);
            final Class<Script> sourceClass = instanceConfig.getClassCache().cacheClassByKey(key, generator);
            if (sourceClass == null) {
                this.counts.failCount.incrementAndGet();
                method.setCallCount(-1);
                return null;
            }
            this.counts.successCount.incrementAndGet();
            final Script jitCompiledScript = sourceClass.newInstance();
            final Set<Script> jittedMethods = context.getRuntime().getJittedMethods();
            jittedMethods.add(jitCompiledScript);
            if (instanceConfig.getJitLogEvery() > 0) {
                final int methodCount = jittedMethods.size();
                if (methodCount % instanceConfig.getJitLogEvery() == 0) {
                    log(method, name, "live compiled methods: " + methodCount, new String[0]);
                }
            }
            if (instanceConfig.isJitLogging()) {
                log(method, name, "done jitting", new String[0]);
            }
            method.switchToJitted(jitCompiledScript, generator.callConfig());
            return null;
        }
        catch (Throwable t) {
            if (context.getRuntime().getDebug().isTrue()) {
                t.printStackTrace();
            }
            if (instanceConfig.isJitLoggingVerbose()) {
                log(method, name, "could not compile", t.getMessage());
            }
            this.counts.failCount.incrementAndGet();
            method.setCallCount(-1);
            return null;
        }
    }
    
    public static String getHashForString(final String str) {
        return getHashForBytes(RubyEncoding.encodeUTF8(str));
    }
    
    public static String getHashForBytes(final byte[] bytes) {
        try {
            final MessageDigest sha1 = MessageDigest.getInstance("SHA1");
            sha1.update(bytes);
            final byte[] digest = sha1.digest();
            final StringBuilder builder = new StringBuilder();
            for (int i = 0; i < digest.length; ++i) {
                builder.append(Integer.toString((digest[i] & 0xFF) + 256, 16).substring(1));
            }
            return builder.toString().toUpperCase(Locale.ENGLISH);
        }
        catch (NoSuchAlgorithmException nsae) {
            throw new RuntimeException(nsae);
        }
    }
    
    public static void saveToCodeCache(final Ruby ruby, final byte[] bytecode, final String packageName, final File cachedClassFile) {
        final String codeCache = RubyInstanceConfig.JIT_CODE_CACHE;
        final File codeCacheDir = new File(codeCache);
        if (!codeCacheDir.exists()) {
            ruby.getWarnings().warn("jruby.jit.codeCache directory " + codeCacheDir + " does not exist");
        }
        else if (!codeCacheDir.isDirectory()) {
            ruby.getWarnings().warn("jruby.jit.codeCache directory " + codeCacheDir + " is not a directory");
        }
        else if (!codeCacheDir.canWrite()) {
            ruby.getWarnings().warn("jruby.jit.codeCache directory " + codeCacheDir + " is not writable");
        }
        else {
            if (!new File(codeCache, packageName).isDirectory()) {
                final boolean createdDirs = new File(codeCache, packageName).mkdirs();
                if (!createdDirs) {
                    ruby.getWarnings().warn("could not create JIT cache dir: " + new File(codeCache, packageName));
                }
            }
            FileOutputStream fos = null;
            try {
                if (RubyInstanceConfig.JIT_LOADING_DEBUG) {
                    System.err.println("writing jitted code to to " + cachedClassFile);
                }
                fos = new FileOutputStream(cachedClassFile);
                fos.write(bytecode);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            finally {
                try {
                    fos.close();
                }
                catch (Exception ex) {}
            }
        }
    }
    
    private static String calculateFilename(final ArgsNode argsNode, final Node bodyNode) {
        if (bodyNode != null) {
            return bodyNode.getPosition().getFile();
        }
        if (argsNode != null) {
            return argsNode.getPosition().getFile();
        }
        return "__eval__";
    }
    
    static void log(final DefaultMethod method, final String name, final String message, final String... reason) {
        String className = method.getImplementationClass().getBaseName();
        if (className == null) {
            className = "<anon class>";
        }
        System.err.print(message + ":" + className + "." + name + " at " + method.getPosition());
        if (reason.length > 0) {
            System.err.print(" because of: \"");
            for (int i = 0; i < reason.length; ++i) {
                System.err.print(reason[i]);
            }
            System.err.print('\"');
        }
        System.err.println("");
    }
    
    public long getSuccessCount() {
        return this.counts.successCount.get();
    }
    
    public long getCompileCount() {
        return this.counts.compiledCount.get();
    }
    
    public long getFailCount() {
        return this.counts.failCount.get();
    }
    
    public long getCompileTime() {
        return this.counts.compileTime.get() / 1000L;
    }
    
    public long getAbandonCount() {
        return this.counts.abandonCount.get();
    }
    
    public long getCodeSize() {
        return this.counts.codeSize.get();
    }
    
    public long getAverageCodeSize() {
        return this.counts.averageCodeSize.get();
    }
    
    public long getAverageCompileTime() {
        return this.counts.averageCompileTime.get() / 1000L;
    }
    
    public long getLargestCodeSize() {
        return this.counts.largestCodeSize.get();
    }
    
    public static class JITCounts
    {
        private final AtomicLong compiledCount;
        private final AtomicLong successCount;
        private final AtomicLong failCount;
        private final AtomicLong abandonCount;
        private final AtomicLong compileTime;
        private final AtomicLong averageCompileTime;
        private final AtomicLong codeSize;
        private final AtomicLong averageCodeSize;
        private final AtomicLong largestCodeSize;
        
        public JITCounts() {
            this.compiledCount = new AtomicLong(0L);
            this.successCount = new AtomicLong(0L);
            this.failCount = new AtomicLong(0L);
            this.abandonCount = new AtomicLong(0L);
            this.compileTime = new AtomicLong(0L);
            this.averageCompileTime = new AtomicLong(0L);
            this.codeSize = new AtomicLong(0L);
            this.averageCodeSize = new AtomicLong(0L);
            this.largestCodeSize = new AtomicLong(0L);
        }
    }
    
    public static class JITClassGenerator implements ClassCache.ClassGenerator
    {
        private final StandardASMCompiler asmCompiler;
        private final StaticScope staticScope;
        private final Node bodyNode;
        private final ArgsNode argsNode;
        private final Ruby ruby;
        private final String packageName;
        private final String className;
        private final String filename;
        private final String methodName;
        private final JITCounts counts;
        private final String digestString;
        private CallConfiguration jitCallConfig;
        private byte[] bytecode;
        private String name;
        
        public JITClassGenerator(final String name, final String key, final Ruby ruby, final DefaultMethod method, final ThreadContext context, final JITCounts counts) {
            this.packageName = "rubyjit";
            this.digestString = JITCompiler.getHashForString(key);
            this.className = this.packageName + "/" + JavaNameMangler.mangleMethodName(name) + "_" + this.digestString;
            this.name = this.className.replaceAll("/", ".");
            this.bodyNode = method.getBodyNode();
            this.argsNode = method.getArgsNode();
            this.methodName = name;
            this.filename = calculateFilename(this.argsNode, this.bodyNode);
            this.staticScope = method.getStaticScope();
            this.asmCompiler = new StandardASMCompiler(this.className, this.filename);
            this.ruby = ruby;
            this.counts = counts;
        }
        
        protected void compile() {
            if (this.bytecode != null) {
                return;
            }
            final String codeCache = RubyInstanceConfig.JIT_CODE_CACHE;
            final File cachedClassFile = new File(codeCache + "/" + this.className + ".class");
            if (codeCache != null && cachedClassFile.exists()) {
                FileInputStream fis = null;
                try {
                    if (RubyInstanceConfig.JIT_LOADING_DEBUG) {
                        System.err.println("loading cached code from: " + cachedClassFile);
                    }
                    fis = new FileInputStream(cachedClassFile);
                    fis.read(this.bytecode = new byte[(int)fis.getChannel().size()]);
                    this.name = new ClassReader(this.bytecode).getClassName();
                    return;
                }
                catch (Exception e) {}
                finally {
                    try {
                        fis.close();
                    }
                    catch (Exception ex) {}
                }
            }
            final long start = System.nanoTime();
            this.asmCompiler.startScript(this.staticScope);
            final ASTCompiler compiler = this.ruby.getInstanceConfig().newCompiler();
            final CompilerCallback args = new CompilerCallback() {
                public void call(final BodyCompiler context) {
                    compiler.compileArgs(JITClassGenerator.this.argsNode, context, true);
                }
            };
            ASTInspector inspector = new ASTInspector();
            if (this.ruby.getInstanceConfig().isJitDumping()) {
                inspector = new ASTInspector(this.className, true);
            }
            inspector.inspect(this.argsNode);
            inspector.inspect(this.bodyNode);
            BodyCompiler methodCompiler;
            if (this.bodyNode != null) {
                methodCompiler = this.asmCompiler.startFileMethod(args, this.staticScope, inspector);
                compiler.compileBody(this.bodyNode, methodCompiler, true);
            }
            else if (this.argsNode != null && (this.argsNode.getRequiredArgsCount() > 0 || this.argsNode.getOptionalArgsCount() > 0)) {
                methodCompiler = this.asmCompiler.startFileMethod(args, this.staticScope, inspector);
                methodCompiler.loadNil();
            }
            else {
                methodCompiler = this.asmCompiler.startFileMethod(null, this.staticScope, inspector);
                methodCompiler.loadNil();
                this.jitCallConfig = CallConfiguration.FrameNoneScopeNone;
            }
            methodCompiler.endBody();
            this.asmCompiler.endScript(false, false);
            if (this.jitCallConfig == null) {
                this.jitCallConfig = inspector.getCallConfig();
            }
            this.bytecode = this.asmCompiler.getClassByteArray();
            if (this.ruby.getInstanceConfig().isJitDumping()) {
                final TraceClassVisitor tcv = new TraceClassVisitor(new PrintWriter(System.out));
                new ClassReader(this.bytecode).accept(tcv, 0);
            }
            if (this.bytecode.length > this.ruby.getInstanceConfig().getJitMaxSize()) {
                this.bytecode = null;
                throw new NotCompilableException("JITed method size exceeds configured max of " + this.ruby.getInstanceConfig().getJitMaxSize());
            }
            if (codeCache != null) {
                JITCompiler.saveToCodeCache(this.ruby, this.bytecode, this.packageName, cachedClassFile);
            }
            this.counts.compiledCount.incrementAndGet();
            this.counts.compileTime.addAndGet(System.nanoTime() - start);
            this.counts.codeSize.addAndGet(this.bytecode.length);
            this.counts.averageCompileTime.set(this.counts.compileTime.get() / this.counts.compiledCount.get());
            this.counts.averageCodeSize.set(this.counts.codeSize.get() / this.counts.compiledCount.get());
            synchronized (this.counts) {
                if (this.counts.largestCodeSize.get() < this.bytecode.length) {
                    this.counts.largestCodeSize.set(this.bytecode.length);
                }
            }
        }
        
        public void generate() {
            this.compile();
        }
        
        public byte[] bytecode() {
            return this.bytecode;
        }
        
        public String name() {
            return this.name;
        }
        
        public CallConfiguration callConfig() {
            this.compile();
            return this.jitCallConfig;
        }
        
        public String toString() {
            return this.methodName + "() at " + this.bodyNode.getPosition().getFile() + ":" + this.bodyNode.getPosition().getLine();
        }
    }
}
