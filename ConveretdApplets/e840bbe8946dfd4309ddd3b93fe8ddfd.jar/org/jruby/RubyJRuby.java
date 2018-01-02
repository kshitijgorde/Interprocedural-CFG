// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.ast.ListNode;
import org.jruby.ast.ArgsNode;
import org.jruby.ast.UnnamedRestArgNode;
import org.jruby.ast.RestArgNode;
import org.jruby.ast.MultipleAsgn19Node;
import org.jruby.internal.runtime.methods.MethodArgs;
import org.jruby.javasupport.util.RuntimeHelpers;
import org.jruby.internal.runtime.methods.MethodArgs2;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import org.jruby.exceptions.RaiseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.util.Collections;
import java.util.Collection;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.ObjectAllocator;
import org.jruby.runtime.ExecutionContext;
import java.util.Map;
import org.jruby.org.objectweb.asm.ClassVisitor;
import org.jruby.org.objectweb.asm.util.TraceClassVisitor;
import java.io.Writer;
import java.io.PrintWriter;
import org.jruby.org.objectweb.asm.ClassReader;
import java.io.StringWriter;
import org.jruby.anno.JRubyClass;
import org.jruby.runtime.Visibility;
import org.jruby.ast.types.INameNode;
import org.jruby.javasupport.JavaObject;
import org.jruby.java.proxies.JavaProxy;
import org.jruby.javasupport.Java;
import org.jruby.compiler.ASTCompiler;
import org.jruby.ast.Node;
import org.jruby.compiler.ScriptCompiler;
import org.jruby.compiler.impl.StandardASMCompiler;
import org.jruby.compiler.ASTInspector;
import org.jruby.ast.RootNode;
import org.jruby.runtime.InterpretedBlock;
import org.jruby.runtime.CompiledBlock;
import org.jruby.runtime.DynamicScope;
import org.jruby.runtime.Arity;
import org.jruby.anno.JRubyMethod;
import org.jruby.javasupport.JavaUtil;
import org.jruby.runtime.Block;
import org.jruby.runtime.ThreadContext;
import org.jruby.ext.jruby.JRubyUtilLibrary;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.anno.JRubyModule;

@JRubyModule(name = { "JRuby" })
public class RubyJRuby
{
    public static RubyModule createJRuby(final Ruby runtime) {
        final ThreadContext context = runtime.getCurrentContext();
        runtime.getKernel().callMethod(context, "require", runtime.newString("java"));
        final RubyModule jrubyModule = runtime.defineModule("JRuby");
        jrubyModule.defineAnnotatedMethods(RubyJRuby.class);
        jrubyModule.defineAnnotatedMethods(JRubyUtilLibrary.class);
        final RubyClass compiledScriptClass = jrubyModule.defineClassUnder("CompiledScript", runtime.getObject(), runtime.getObject().getAllocator());
        for (final String name : new String[] { "name", "class_name", "original_script", "code" }) {
            compiledScriptClass.addReadWriteAttribute(context, name);
        }
        compiledScriptClass.defineAnnotatedMethods(JRubyCompiledScript.class);
        final RubyClass threadLocalClass = jrubyModule.defineClassUnder("ThreadLocal", runtime.getObject(), JRubyThreadLocal.ALLOCATOR);
        threadLocalClass.defineAnnotatedMethods(JRubyExecutionContextLocal.class);
        final RubyClass fiberLocalClass = jrubyModule.defineClassUnder("FiberLocal", runtime.getObject(), JRubyFiberLocal.ALLOCATOR);
        fiberLocalClass.defineAnnotatedMethods(JRubyExecutionContextLocal.class);
        return jrubyModule;
    }
    
    public static RubyModule createJRubyExt(final Ruby runtime) {
        runtime.getKernel().callMethod(runtime.getCurrentContext(), "require", runtime.newString("java"));
        final RubyModule mJRubyExt = runtime.getOrCreateModule("JRuby").defineModuleUnder("Extensions");
        mJRubyExt.defineAnnotatedMethods(JRubyExtensions.class);
        runtime.getObject().includeModule(mJRubyExt);
        return mJRubyExt;
    }
    
    public static void createJRubyCoreExt(final Ruby runtime) {
        runtime.getClassClass().defineAnnotatedMethods(JRubyClassExtensions.class);
        runtime.getThread().defineAnnotatedMethods(JRubyThreadExtensions.class);
        runtime.getString().defineAnnotatedMethods(JRubyStringExtensions.class);
    }
    
    @JRubyMethod(module = true)
    public static IRubyObject runtime(final IRubyObject recv, final Block unusedBlock) {
        return JavaUtil.convertJavaToUsableRubyObject(recv.getRuntime(), recv.getRuntime());
    }
    
    @JRubyMethod(module = true)
    public static IRubyObject with_current_runtime_as_global(final ThreadContext context, final IRubyObject recv, final Block block) {
        final Ruby currentRuntime = context.getRuntime();
        final Ruby globalRuntime = Ruby.getGlobalRuntime();
        try {
            if (globalRuntime != currentRuntime) {
                currentRuntime.useAsGlobalRuntime();
            }
            block.yieldSpecific(context);
        }
        finally {
            if (Ruby.getGlobalRuntime() != globalRuntime) {
                globalRuntime.useAsGlobalRuntime();
            }
        }
        return currentRuntime.getNil();
    }
    
    @JRubyMethod(name = { "parse", "ast_for" }, optional = 3, module = true)
    public static IRubyObject parse(final IRubyObject recv, final IRubyObject[] args, final Block block) {
        if (!block.isGiven()) {
            Arity.checkArgumentCount(recv.getRuntime(), args, 1, 3);
            String filename = "-";
            boolean extraPositionInformation = false;
            final RubyString content = args[0].convertToString();
            if (args.length > 1) {
                filename = args[1].convertToString().toString();
                if (args.length > 2) {
                    extraPositionInformation = args[2].isTrue();
                }
            }
            return JavaUtil.convertJavaToUsableRubyObject(recv.getRuntime(), recv.getRuntime().parse(content.getByteList(), filename, null, 0, extraPositionInformation));
        }
        if (block.getBody() instanceof CompiledBlock) {
            throw new RuntimeException("Cannot compile an already compiled block. Use -J-Djruby.jit.enabled=false to avoid this problem.");
        }
        Arity.checkArgumentCount(recv.getRuntime(), args, 0, 0);
        return JavaUtil.convertJavaToUsableRubyObject(recv.getRuntime(), ((InterpretedBlock)block.getBody()).getBodyNode());
    }
    
    @JRubyMethod(name = { "compile" }, optional = 3, module = true)
    public static IRubyObject compile(final IRubyObject recv, final IRubyObject[] args, final Block block) {
        RubyString content;
        Node node;
        String filename;
        if (block.isGiven()) {
            Arity.checkArgumentCount(recv.getRuntime(), args, 0, 0);
            if (block.getBody() instanceof CompiledBlock) {
                throw new RuntimeException("Cannot compile an already compiled block. Use -J-Djruby.jit.enabled=false to avoid this problem.");
            }
            content = RubyString.newEmptyString(recv.getRuntime());
            final Node bnode = ((InterpretedBlock)block.getBody()).getBodyNode();
            node = new RootNode(bnode.getPosition(), block.getBinding().getDynamicScope(), bnode);
            filename = "__block_" + node.getPosition().getFile();
        }
        else {
            Arity.checkArgumentCount(recv.getRuntime(), args, 1, 3);
            filename = "-";
            boolean extraPositionInformation = false;
            content = args[0].convertToString();
            if (args.length > 1) {
                filename = args[1].convertToString().toString();
                if (args.length > 2) {
                    extraPositionInformation = args[2].isTrue();
                }
            }
            node = recv.getRuntime().parse(content.getByteList(), filename, null, 0, extraPositionInformation);
        }
        String classname;
        if (filename.equals("-e")) {
            classname = "__dash_e__";
        }
        else {
            classname = filename.replace('\\', '/').replaceAll(".rb", "").replaceAll("-", "_dash_");
        }
        final ASTInspector inspector = new ASTInspector();
        inspector.inspect(node);
        final StandardASMCompiler asmCompiler = new StandardASMCompiler(classname, filename);
        final ASTCompiler compiler = recv.getRuntime().getInstanceConfig().newCompiler();
        compiler.compileRoot(node, asmCompiler, inspector);
        final byte[] bts = asmCompiler.getClassByteArray();
        final IRubyObject compiledScript = ((RubyModule)recv).fastGetConstant("CompiledScript").callMethod(recv.getRuntime().getCurrentContext(), "new");
        compiledScript.callMethod(recv.getRuntime().getCurrentContext(), "name=", recv.getRuntime().newString(filename));
        compiledScript.callMethod(recv.getRuntime().getCurrentContext(), "class_name=", recv.getRuntime().newString(classname));
        compiledScript.callMethod(recv.getRuntime().getCurrentContext(), "original_script=", content);
        compiledScript.callMethod(recv.getRuntime().getCurrentContext(), "code=", JavaUtil.convertJavaToUsableRubyObject(recv.getRuntime(), bts));
        return compiledScript;
    }
    
    @JRubyMethod(name = { "reference" }, required = 1, module = true)
    public static IRubyObject reference(final ThreadContext context, final IRubyObject recv, final IRubyObject obj) {
        final Ruby runtime = context.getRuntime();
        return Java.getInstance(runtime, obj);
    }
    
    @JRubyMethod(name = { "dereference" }, required = 1, module = true)
    public static IRubyObject dereference(final ThreadContext context, final IRubyObject recv, final IRubyObject obj) {
        Object unwrapped;
        if (obj instanceof JavaProxy) {
            unwrapped = ((JavaProxy)obj).getObject();
        }
        else {
            if (!(obj.dataGetStruct() instanceof JavaObject)) {
                throw context.getRuntime().newTypeError("got " + obj + ", expected wrapped Java object");
            }
            unwrapped = JavaUtil.unwrapJavaObject(obj);
        }
        if (!(unwrapped instanceof IRubyObject)) {
            throw context.getRuntime().newTypeError("got " + obj + ", expected Java-wrapped Ruby object");
        }
        return (IRubyObject)unwrapped;
    }
    
    private static IRubyObject getNameFrom(final Ruby runtime, final INameNode node) {
        return (node == null) ? runtime.getNil() : RubySymbol.newSymbol(runtime, node.getName());
    }
    
    public static class JRubySynchronizedMeta
    {
        @JRubyMethod(visibility = Visibility.PRIVATE)
        public static IRubyObject append_features(final IRubyObject self, final IRubyObject target) {
            if (target instanceof RubyClass && self instanceof RubyModule) {
                final RubyClass targetModule = (RubyClass)target;
                targetModule.becomeSynchronized();
                return ((RubyModule)self).append_features(target);
            }
            throw target.getRuntime().newTypeError(self + " can only be included into classes");
        }
        
        @JRubyMethod(visibility = Visibility.PRIVATE)
        public static IRubyObject extend_object(final IRubyObject self, final IRubyObject obj) {
            if (self instanceof RubyModule) {
                final RubyClass singletonClass = obj.getSingletonClass();
                singletonClass.becomeSynchronized();
                return ((RubyModule)self).extend_object(obj);
            }
            throw self.getRuntime().newTypeError("JRuby::Singleton.extend_object called against " + self);
        }
    }
    
    @JRubyClass(name = { "JRuby::CompiledScript" })
    public static class JRubyCompiledScript
    {
        @JRubyMethod(name = { "to_s" })
        public static IRubyObject compiled_script_to_s(final IRubyObject recv) {
            return recv.getInstanceVariables().fastGetInstanceVariable("@original_script");
        }
        
        @JRubyMethod(name = { "inspect" })
        public static IRubyObject compiled_script_inspect(final IRubyObject recv) {
            return recv.getRuntime().newString("#<JRuby::CompiledScript " + recv.getInstanceVariables().fastGetInstanceVariable("@name") + ">");
        }
        
        @JRubyMethod(name = { "inspect_bytecode" })
        public static IRubyObject compiled_script_inspect_bytecode(final IRubyObject recv) {
            final StringWriter sw = new StringWriter();
            final ClassReader cr = new ClassReader((byte[])recv.getInstanceVariables().fastGetInstanceVariable("@code").toJava(byte[].class));
            final TraceClassVisitor cv = new TraceClassVisitor(new PrintWriter(sw));
            cr.accept(cv, 2);
            return recv.getRuntime().newString(sw.toString());
        }
    }
    
    public abstract static class JRubyExecutionContextLocal extends RubyObject
    {
        private IRubyObject default_value;
        private RubyProc default_proc;
        private static final IRubyObject[] EMPTY_ARGS;
        
        public JRubyExecutionContextLocal(final Ruby runtime, final RubyClass type) {
            super(runtime, type);
            this.default_value = runtime.getNil();
            this.default_proc = null;
        }
        
        @JRubyMethod(name = { "initialize" }, required = 0, optional = 1)
        public IRubyObject rubyInitialize(final ThreadContext context, final IRubyObject[] args, final Block block) {
            if (block.isGiven()) {
                if (args.length != 0) {
                    throw context.getRuntime().newArgumentError("wrong number of arguments");
                }
                this.default_proc = block.getProcObject();
                if (this.default_proc == null) {
                    this.default_proc = RubyProc.newProc(context.getRuntime(), block, block.type);
                }
            }
            else if (args.length == 1) {
                this.default_value = args[0];
            }
            else if (args.length != 0) {
                throw context.getRuntime().newArgumentError("wrong number of arguments");
            }
            return context.getRuntime().getNil();
        }
        
        @JRubyMethod(name = { "default" }, required = 0)
        public IRubyObject getDefault(final ThreadContext context) {
            return this.default_value;
        }
        
        @JRubyMethod(name = { "default_proc" }, required = 0)
        public IRubyObject getDefaultProc(final ThreadContext context) {
            if (this.default_proc != null) {
                return this.default_proc;
            }
            return context.getRuntime().getNil();
        }
        
        @JRubyMethod(name = { "value" }, required = 0)
        public IRubyObject getValue(final ThreadContext context) {
            final Map<Object, IRubyObject> contextVariables = this.getContextVariables(context);
            final IRubyObject value = contextVariables.get(this);
            if (value != null) {
                return value;
            }
            if (this.default_proc != null) {
                contextVariables.put(this, context.getRuntime().getNil());
                final IRubyObject new_value = this.default_proc.call(context, JRubyExecutionContextLocal.EMPTY_ARGS, null, Block.NULL_BLOCK);
                contextVariables.put(this, new_value);
                return new_value;
            }
            return this.default_value;
        }
        
        @JRubyMethod(name = { "value=" }, required = 1)
        public IRubyObject setValue(final ThreadContext context, final IRubyObject value) {
            this.getContextVariables(context).put(this, value);
            return value;
        }
        
        protected final Map<Object, IRubyObject> getContextVariables(final ThreadContext context) {
            return this.getExecutionContext(context).getContextVariables();
        }
        
        protected abstract ExecutionContext getExecutionContext(final ThreadContext p0);
        
        static {
            EMPTY_ARGS = new IRubyObject[0];
        }
    }
    
    @JRubyClass(name = { "JRuby::ThreadLocal" })
    public static final class JRubyThreadLocal extends JRubyExecutionContextLocal
    {
        public static final ObjectAllocator ALLOCATOR;
        
        public JRubyThreadLocal(final Ruby runtime, final RubyClass type) {
            super(runtime, type);
        }
        
        protected final ExecutionContext getExecutionContext(final ThreadContext context) {
            return context.getThread();
        }
        
        static {
            ALLOCATOR = new ObjectAllocator() {
                public IRubyObject allocate(final Ruby runtime, final RubyClass type) {
                    return new JRubyThreadLocal(runtime, type);
                }
            };
        }
    }
    
    @JRubyClass(name = { "JRuby::FiberLocal" })
    public static final class JRubyFiberLocal extends JRubyExecutionContextLocal
    {
        public static final ObjectAllocator ALLOCATOR;
        
        public JRubyFiberLocal(final Ruby runtime, final RubyClass type) {
            super(runtime, type);
        }
        
        @JRubyMethod(name = { "with_value" }, required = 1)
        public IRubyObject withValue(final ThreadContext context, final IRubyObject value, final Block block) {
            final Map<Object, IRubyObject> contextVariables = this.getContextVariables(context);
            final IRubyObject old_value = contextVariables.get(this);
            contextVariables.put(this, value);
            try {
                return block.yieldSpecific(context);
            }
            finally {
                contextVariables.put(this, old_value);
            }
        }
        
        protected final ExecutionContext getExecutionContext(final ThreadContext context) {
            final ExecutionContext fiber = context.getFiber();
            if (fiber != null) {
                return fiber;
            }
            return context.getThread();
        }
        
        static {
            ALLOCATOR = new ObjectAllocator() {
                public IRubyObject allocate(final Ruby runtime, final RubyClass type) {
                    return new JRubyFiberLocal(runtime, type);
                }
            };
        }
    }
    
    @JRubyModule(name = { "JRubyExtensions" })
    public static class JRubyExtensions
    {
        @JRubyMethod(name = { "steal_method" }, required = 2, module = true)
        public static IRubyObject steal_method(final IRubyObject recv, final IRubyObject type, final IRubyObject methodName) {
            RubyModule to_add = null;
            if (recv instanceof RubyModule) {
                to_add = (RubyModule)recv;
            }
            else {
                to_add = recv.getSingletonClass();
            }
            final String name = methodName.toString();
            if (!(type instanceof RubyModule)) {
                throw recv.getRuntime().newArgumentError("First argument must be a module/class");
            }
            final DynamicMethod method = ((RubyModule)type).searchMethod(name);
            if (method == null || method.isUndefined()) {
                throw recv.getRuntime().newArgumentError("No such method " + name + " on " + type);
            }
            to_add.addMethod(name, method);
            return recv.getRuntime().getNil();
        }
        
        @JRubyMethod(name = { "steal_methods" }, required = 1, rest = true, module = true)
        public static IRubyObject steal_methods(final IRubyObject recv, final IRubyObject[] args) {
            final IRubyObject type = args[0];
            for (int i = 1; i < args.length; ++i) {
                steal_method(recv, type, args[i]);
            }
            return recv.getRuntime().getNil();
        }
    }
    
    public static class JRubyClassExtensions
    {
        @JRubyMethod(name = { "subclasses" }, optional = 1)
        public static IRubyObject subclasses(final ThreadContext context, final IRubyObject maybeClass, final IRubyObject[] args) {
            if (maybeClass instanceof RubyClass) {
                final RubyClass clazz = (RubyClass)maybeClass;
                boolean recursive = false;
                if (args.length == 1) {
                    if (args[0] instanceof RubyBoolean) {
                        recursive = args[0].isTrue();
                    }
                    else {
                        context.getRuntime().newTypeError(args[0], context.getRuntime().fastGetClass("Boolean"));
                    }
                }
                return RubyArray.newArray(context.getRuntime(), clazz.subclasses(recursive)).freeze(context);
            }
            throw context.getRuntime().newTypeError(maybeClass, context.getRuntime().getClassClass());
        }
        
        @JRubyMethod(name = { "become_java!" }, optional = 1)
        public static IRubyObject become_java_bang(final ThreadContext context, final IRubyObject maybeClass, final IRubyObject[] args) {
            if (maybeClass instanceof RubyClass) {
                final RubyClass clazz = (RubyClass)maybeClass;
                if (args.length > 0) {
                    clazz.reifyWithAncestors(args[0].convertToString().asJavaString());
                }
                else {
                    clazz.reifyWithAncestors();
                }
                return JavaUtil.convertJavaToUsableRubyObject(context.getRuntime(), clazz.getReifiedClass());
            }
            throw context.getRuntime().newTypeError(maybeClass, context.getRuntime().getClassClass());
        }
        
        @JRubyMethod
        public static IRubyObject java_class(final ThreadContext context, final IRubyObject maybeClass) {
            if (maybeClass instanceof RubyClass) {
                RubyClass current;
                RubyClass clazz;
                for (clazz = (current = (RubyClass)maybeClass); current != null; current = current.getSuperClass()) {
                    if (current.getReifiedClass() != null) {
                        clazz = current;
                        break;
                    }
                }
                return JavaUtil.convertJavaToUsableRubyObject(context.getRuntime(), clazz.getReifiedClass());
            }
            throw context.getRuntime().newTypeError(maybeClass, context.getRuntime().getClassClass());
        }
        
        @JRubyMethod
        public static IRubyObject add_method_annotation(final ThreadContext context, final IRubyObject maybeClass, final IRubyObject methodName, final IRubyObject annoMap) {
            final RubyClass clazz = getRubyClass(maybeClass, context);
            final String method = methodName.convertToString().asJavaString();
            final Map<Class, Map<String, Object>> annos = (Map<Class, Map<String, Object>>)annoMap;
            for (final Map.Entry<Class, Map<String, Object>> entry : annos.entrySet()) {
                Map<String, Object> value = entry.getValue();
                if (value == null) {
                    value = (Map<String, Object>)Collections.EMPTY_MAP;
                }
                clazz.addMethodAnnotation(method, getAnnoClass(context, entry.getKey()), value);
            }
            return context.getRuntime().getNil();
        }
        
        @JRubyMethod
        public static IRubyObject add_parameter_annotations(final ThreadContext context, final IRubyObject maybeClass, final IRubyObject methodName, final IRubyObject paramAnnoMaps) {
            final RubyClass clazz = getRubyClass(maybeClass, context);
            final String method = methodName.convertToString().asJavaString();
            final List<Map<Class, Map<String, Object>>> annos = (List<Map<Class, Map<String, Object>>>)paramAnnoMaps;
            for (int i = annos.size() - 1; i >= 0; --i) {
                final Map<Class, Map<String, Object>> paramAnnos = annos.get(i);
                for (final Map.Entry<Class, Map<String, Object>> entry : paramAnnos.entrySet()) {
                    Map<String, Object> value = entry.getValue();
                    if (value == null) {
                        value = (Map<String, Object>)Collections.EMPTY_MAP;
                    }
                    clazz.addParameterAnnotation(method, i, getAnnoClass(context, entry.getKey()), value);
                }
            }
            return context.getRuntime().getNil();
        }
        
        @JRubyMethod
        public static IRubyObject add_class_annotation(final ThreadContext context, final IRubyObject maybeClass, final IRubyObject annoMap) {
            final RubyClass clazz = getRubyClass(maybeClass, context);
            final Map<Class, Map<String, Object>> annos = (Map<Class, Map<String, Object>>)annoMap;
            for (final Map.Entry<Class, Map<String, Object>> entry : annos.entrySet()) {
                Map<String, Object> value = entry.getValue();
                if (value == null) {
                    value = (Map<String, Object>)Collections.EMPTY_MAP;
                }
                clazz.addClassAnnotation(getAnnoClass(context, entry.getKey()), value);
            }
            return context.getRuntime().getNil();
        }
        
        @JRubyMethod
        public static IRubyObject add_method_signature(final ThreadContext context, final IRubyObject maybeClass, final IRubyObject methodName, final IRubyObject clsList) {
            final RubyClass clazz = getRubyClass(maybeClass, context);
            final List<Class> types = new ArrayList<Class>();
            final Iterator i = ((List)clsList).iterator();
            while (i.hasNext()) {
                types.add(getAnnoClass(context, i.next()));
            }
            clazz.addMethodSignature(methodName.convertToString().asJavaString(), types.toArray(new Class[types.size()]));
            return context.getRuntime().getNil();
        }
        
        private static Class getAnnoClass(final ThreadContext context, final Object annoClass) {
            if (annoClass instanceof Class) {
                return (Class)annoClass;
            }
            if (annoClass instanceof IRubyObject) {
                final IRubyObject annoMod = (IRubyObject)annoClass;
                if (annoMod.respondsTo("java_class")) {
                    return (Class)annoMod.callMethod(context, "java_class").toJava(Object.class);
                }
            }
            throw context.getRuntime().newArgumentError("must supply java class argument instead of " + annoClass.toString());
        }
        
        private static RubyClass getRubyClass(final IRubyObject maybeClass, final ThreadContext context) throws RaiseException {
            if (maybeClass instanceof RubyClass) {
                final RubyClass clazz = (RubyClass)maybeClass;
                return clazz;
            }
            throw context.getRuntime().newTypeError(maybeClass, context.getRuntime().getClassClass());
        }
    }
    
    public static class JRubyThreadExtensions
    {
        private static final ThreadMXBean threadBean;
        
        @JRubyMethod(name = { "times" }, module = true)
        public static IRubyObject times(final IRubyObject recv, final Block unusedBlock) {
            final Ruby runtime = recv.getRuntime();
            long cpu = JRubyThreadExtensions.threadBean.getCurrentThreadCpuTime();
            long user = JRubyThreadExtensions.threadBean.getCurrentThreadUserTime();
            if (cpu == -1L) {
                cpu = 0L;
            }
            if (user == -1L) {
                user = 0L;
            }
            final double system_d = (cpu - user) / 1.0E9;
            final double user_d = user / 1.0E9;
            final RubyFloat zero = runtime.newFloat(0.0);
            return RubyStruct.newStruct(runtime.getTmsStruct(), new IRubyObject[] { RubyFloat.newFloat(runtime, user_d), RubyFloat.newFloat(runtime, system_d), zero, zero }, Block.NULL_BLOCK);
        }
        
        static {
            threadBean = ManagementFactory.getThreadMXBean();
        }
    }
    
    public static class JRubyStringExtensions
    {
        @JRubyMethod(name = { "alloc" }, meta = true)
        public static IRubyObject alloc(final ThreadContext context, final IRubyObject recv, final IRubyObject size) {
            return RubyString.newStringLight(context.getRuntime(), (int)size.convertToInteger().getLongValue());
        }
    }
    
    public static class MethodExtensions
    {
        @JRubyMethod(name = { "args" })
        public static IRubyObject methodArgs(final IRubyObject recv) {
            final Ruby runtime = recv.getRuntime();
            final RubyMethod rubyMethod = (RubyMethod)recv;
            final RubyArray argsArray = RubyArray.newArray(runtime);
            final DynamicMethod method = rubyMethod.method;
            final RubySymbol req = runtime.newSymbol("req");
            final RubySymbol opt = runtime.newSymbol("opt");
            final RubySymbol rest = runtime.newSymbol("rest");
            final RubySymbol block = runtime.newSymbol("block");
            if (method instanceof MethodArgs2) {
                return RuntimeHelpers.parameterListToParameters(runtime, ((MethodArgs2)method).getParameterList(), true);
            }
            if (method instanceof MethodArgs) {
                final MethodArgs interpMethod = (MethodArgs)method;
                final ArgsNode args = interpMethod.getArgsNode();
                final ListNode requiredArgs = args.getPre();
                for (int i = 0; requiredArgs != null && i < requiredArgs.size(); ++i) {
                    final Node argNode = requiredArgs.get(i);
                    if (argNode instanceof MultipleAsgn19Node) {
                        argsArray.append(RubyArray.newArray(runtime, req));
                    }
                    else {
                        argsArray.append(RubyArray.newArray(runtime, req, getNameFrom(runtime, (INameNode)argNode)));
                    }
                }
                final ListNode optArgs = args.getOptArgs();
                for (int j = 0; optArgs != null && j < optArgs.size(); ++j) {
                    argsArray.append(RubyArray.newArray(runtime, opt, getNameFrom(runtime, (INameNode)optArgs.get(j))));
                }
                if (args.getRestArg() >= 0) {
                    final RestArgNode restArg = (RestArgNode)args.getRestArgNode();
                    if (restArg instanceof UnnamedRestArgNode) {
                        if (((UnnamedRestArgNode)restArg).isStar()) {
                            argsArray.append(RubyArray.newArray(runtime, rest));
                        }
                    }
                    else {
                        argsArray.append(RubyArray.newArray(runtime, rest, getNameFrom(runtime, args.getRestArgNode())));
                    }
                }
                final ListNode requiredArgsPost = args.getPost();
                for (int k = 0; requiredArgsPost != null && k < requiredArgsPost.size(); ++k) {
                    final Node argNode2 = requiredArgsPost.get(k);
                    if (argNode2 instanceof MultipleAsgn19Node) {
                        argsArray.append(RubyArray.newArray(runtime, req));
                    }
                    else {
                        argsArray.append(RubyArray.newArray(runtime, req, getNameFrom(runtime, (INameNode)requiredArgsPost.get(k))));
                    }
                }
                if (args.getBlock() != null) {
                    argsArray.append(RubyArray.newArray(runtime, block, getNameFrom(runtime, args.getBlock())));
                }
            }
            else if (method.getArity() == Arity.OPTIONAL) {
                argsArray.append(RubyArray.newArray(runtime, rest));
            }
            return argsArray;
        }
    }
}
