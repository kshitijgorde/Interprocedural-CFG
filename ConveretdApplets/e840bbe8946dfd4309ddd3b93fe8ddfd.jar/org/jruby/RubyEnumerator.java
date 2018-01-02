// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.runtime.Arity;
import org.jruby.runtime.BlockCallback;
import org.jruby.javasupport.util.RuntimeHelpers;
import org.jruby.util.ByteList;
import org.jruby.runtime.Block;
import org.jruby.runtime.Visibility;
import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.ObjectAllocator;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.anno.JRubyModule;

@JRubyModule(name = { "Enumerable::Enumerator" }, include = { "Enumerable" })
public class RubyEnumerator extends RubyObject
{
    private IRubyObject object;
    private String method;
    private IRubyObject[] methodArgs;
    private static ObjectAllocator ENUMERATOR_ALLOCATOR;
    
    public static void defineEnumerator(final Ruby runtime) {
        runtime.getKernel().defineAnnotatedMethods(RubyEnumeratorKernel.class);
        final RubyModule enm = runtime.getClassFromPath("Enumerable");
        enm.defineAnnotatedMethods(RubyEnumeratorEnumerable.class);
        RubyClass enmr;
        if (runtime.is1_9()) {
            enmr = runtime.defineClass("Enumerator", runtime.getObject(), RubyEnumerator.ENUMERATOR_ALLOCATOR);
        }
        else {
            enmr = enm.defineClassUnder("Enumerator", runtime.getObject(), RubyEnumerator.ENUMERATOR_ALLOCATOR);
        }
        enmr.includeModule(enm);
        enmr.defineAnnotatedMethods(RubyEnumerator.class);
        runtime.setEnumerator(enmr);
        if (runtime.is1_9()) {
            runtime.getLoadService().lockAndRequire("generator_internal");
            RubyYielder.createYielderClass(runtime);
        }
    }
    
    private RubyEnumerator(final Ruby runtime, final RubyClass type) {
        super(runtime, type);
        this.object = runtime.getNil();
        this.initialize(runtime.getNil(), RubyString.newEmptyString(runtime), IRubyObject.NULL_ARRAY);
    }
    
    private RubyEnumerator(final Ruby runtime, final IRubyObject object, final IRubyObject method, final IRubyObject[] args) {
        super(runtime, runtime.getEnumerator());
        this.initialize(object, method, args);
    }
    
    static IRubyObject enumeratorize(final Ruby runtime, final IRubyObject object, final String method) {
        return new RubyEnumerator(runtime, object, runtime.fastNewSymbol(method), IRubyObject.NULL_ARRAY);
    }
    
    static IRubyObject enumeratorize(final Ruby runtime, final IRubyObject object, final String method, final IRubyObject arg) {
        return new RubyEnumerator(runtime, object, runtime.fastNewSymbol(method), new IRubyObject[] { arg });
    }
    
    static IRubyObject enumeratorize(final Ruby runtime, final IRubyObject object, final String method, final IRubyObject[] args) {
        return new RubyEnumerator(runtime, object, runtime.fastNewSymbol(method), args);
    }
    
    @JRubyMethod(name = { "initialize" }, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_8)
    public IRubyObject initialize(final ThreadContext context) {
        throw context.getRuntime().newArgumentError(0, 1);
    }
    
    @JRubyMethod(name = { "initialize" }, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_9)
    public IRubyObject initialize19(final ThreadContext context, final Block block) {
        if (!block.isGiven()) {
            throw context.getRuntime().newArgumentError(0, 1);
        }
        final IRubyObject obj = context.getRuntime().getClass("Generator").callMethod(context, "new", new IRubyObject[0], block);
        return this.initialize19(context, obj, block);
    }
    
    @JRubyMethod(name = { "initialize" }, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_8)
    public IRubyObject initialize(final ThreadContext context, final IRubyObject object) {
        return this.initialize(object, context.getRuntime().fastNewSymbol("each"), RubyEnumerator.NULL_ARRAY);
    }
    
    @JRubyMethod(name = { "initialize" }, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_9)
    public IRubyObject initialize19(final ThreadContext context, final IRubyObject object, final Block block) {
        return this.initialize(object, context.getRuntime().fastNewSymbol("each"), RubyEnumerator.NULL_ARRAY);
    }
    
    @JRubyMethod(name = { "initialize" }, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_8)
    public IRubyObject initialize(final ThreadContext context, final IRubyObject object, final IRubyObject method) {
        return this.initialize(object, method, RubyEnumerator.NULL_ARRAY);
    }
    
    @JRubyMethod(name = { "initialize" }, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_9)
    public IRubyObject initialize19(final ThreadContext context, final IRubyObject object, final IRubyObject method, final Block block) {
        return this.initialize(object, method, RubyEnumerator.NULL_ARRAY);
    }
    
    @JRubyMethod(name = { "initialize" }, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_8)
    public IRubyObject initialize(final ThreadContext context, final IRubyObject object, final IRubyObject method, final IRubyObject methodArg) {
        return this.initialize(object, method, new IRubyObject[] { methodArg });
    }
    
    @JRubyMethod(name = { "initialize" }, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_9)
    public IRubyObject initialize19(final ThreadContext context, final IRubyObject object, final IRubyObject method, final IRubyObject methodArg, final Block block) {
        return this.initialize(object, method, new IRubyObject[] { methodArg });
    }
    
    @JRubyMethod(name = { "initialize" }, required = 1, rest = true, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_8)
    public IRubyObject initialize(final ThreadContext context, final IRubyObject[] args) {
        final IRubyObject[] methArgs = new IRubyObject[args.length - 2];
        System.arraycopy(args, 2, methArgs, 0, methArgs.length);
        return this.initialize(args[0], args[1], methArgs);
    }
    
    private IRubyObject initialize(final IRubyObject object, final IRubyObject method, final IRubyObject[] methodArgs) {
        this.object = object;
        this.method = method.asJavaString();
        this.methodArgs = methodArgs;
        this.setInstanceVariable("@__object__", object);
        this.setInstanceVariable("@__method__", method);
        this.setInstanceVariable("@__args__", RubyArray.newArrayNoCopyLight(this.getRuntime(), methodArgs));
        return this;
    }
    
    @JRubyMethod(name = { "dup" })
    public IRubyObject dup() {
        final RubyEnumerator copy = (RubyEnumerator)super.dup();
        copy.object = this.object;
        copy.method = this.method;
        copy.methodArgs = this.methodArgs;
        return copy;
    }
    
    @JRubyMethod
    public IRubyObject each(final ThreadContext context, final Block block) {
        return this.object.callMethod(context, this.method, this.methodArgs, block);
    }
    
    @JRubyMethod(name = { "inspect" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject inspect19(final ThreadContext context) {
        final Ruby runtime = context.getRuntime();
        if (runtime.isInspecting(this)) {
            return this.inspect(context, true);
        }
        try {
            runtime.registerInspecting(this);
            return this.inspect(context, false);
        }
        finally {
            runtime.unregisterInspecting(this);
        }
    }
    
    private IRubyObject inspect(final ThreadContext context, final boolean recurse) {
        final Ruby runtime = context.getRuntime();
        final ByteList bytes = new ByteList();
        bytes.append((byte)35).append((byte)60);
        bytes.append(this.getMetaClass().getName().getBytes());
        bytes.append((byte)58).append((byte)32);
        if (recurse) {
            bytes.append("...>".getBytes());
            return RubyString.newStringNoCopy(runtime, bytes).taint(context);
        }
        boolean tainted = this.isTaint();
        bytes.append(RubyObject.inspect(context, this.object).getByteList());
        bytes.append((byte)58);
        bytes.append(this.method.getBytes());
        if (this.methodArgs.length > 0) {
            bytes.append((byte)40);
            for (int i = 0; i < this.methodArgs.length; ++i) {
                bytes.append(RubyObject.inspect(context, this.methodArgs[i]).getByteList());
                if (i < this.methodArgs.length - 1) {
                    bytes.append((byte)44).append((byte)32);
                }
                else {
                    bytes.append((byte)41);
                }
                if (this.methodArgs[i].isTaint()) {
                    tainted = true;
                }
            }
        }
        bytes.append((byte)62);
        final RubyString result = RubyString.newStringNoCopy(runtime, bytes);
        if (tainted) {
            result.setTaint(true);
        }
        return result;
    }
    
    protected static IRubyObject newEnumerator(final ThreadContext context, final IRubyObject arg) {
        return context.getRuntime().getEnumerator().callMethod(context, "new", arg);
    }
    
    protected static IRubyObject newEnumerator(final ThreadContext context, final IRubyObject arg1, final IRubyObject arg2) {
        return RuntimeHelpers.invoke(context, context.getRuntime().getEnumerator(), "new", arg1, arg2);
    }
    
    protected static IRubyObject newEnumerator(final ThreadContext context, final IRubyObject arg1, final IRubyObject arg2, final IRubyObject arg3) {
        return RuntimeHelpers.invoke(context, context.getRuntime().getEnumerator(), "new", arg1, arg2, arg3);
    }
    
    private static IRubyObject with_index_common(final ThreadContext context, final IRubyObject self, final Block block, final String rubyMethodName, final IRubyObject arg) {
        final Ruby runtime = context.getRuntime();
        final int index = arg.isNil() ? 0 : RubyNumeric.num2int(arg);
        if (!block.isGiven()) {
            return arg.isNil() ? enumeratorize(runtime, self, rubyMethodName) : enumeratorize(runtime, self, rubyMethodName, runtime.newFixnum(index));
        }
        return RubyEnumerable.callEach(runtime, context, self, new EachWithIndex(context, block, index));
    }
    
    @JRubyMethod
    public static IRubyObject each_with_index(final ThreadContext context, final IRubyObject self, final Block block) {
        return with_index_common(context, self, block, "each_with_index", context.getRuntime().getNil());
    }
    
    @JRubyMethod(compat = CompatVersion.RUBY1_8)
    public static IRubyObject with_index(final ThreadContext context, final IRubyObject self, final Block block) {
        return with_index_common(context, self, block, "with_index", context.getRuntime().getNil());
    }
    
    @JRubyMethod(name = { "with_index" }, compat = CompatVersion.RUBY1_9)
    public static IRubyObject with_index19(final ThreadContext context, final IRubyObject self, final Block block) {
        return with_index_common(context, self, block, "with_index", context.getRuntime().getNil());
    }
    
    @JRubyMethod(name = { "with_index" }, compat = CompatVersion.RUBY1_9)
    public static IRubyObject with_index19(final ThreadContext context, final IRubyObject self, final IRubyObject arg, final Block block) {
        return with_index_common(context, self, block, "with_index", arg);
    }
    
    @JRubyMethod
    public static IRubyObject next(final ThreadContext context, final IRubyObject self) {
        context.getRuntime().getLoadService().lockAndRequire("generator_internal");
        return self.callMethod(context, "next");
    }
    
    @JRubyMethod
    public static IRubyObject rewind(final ThreadContext context, final IRubyObject self) {
        context.getRuntime().getLoadService().lockAndRequire("generator_internal");
        return self.callMethod(context, "rewind");
    }
    
    @JRubyMethod(compat = CompatVersion.RUBY1_9)
    public static IRubyObject peek(final ThreadContext context, final IRubyObject self) {
        context.getRuntime().getLoadService().lockAndRequire("generator_internal");
        return self.callMethod(context, "peek");
    }
    
    static {
        RubyEnumerator.ENUMERATOR_ALLOCATOR = new ObjectAllocator() {
            public IRubyObject allocate(final Ruby runtime, final RubyClass klass) {
                return new RubyEnumerator(runtime, klass, null);
            }
        };
    }
    
    public static final class RubyEnumeratorKernel
    {
        @JRubyMethod(name = { "to_enum", "enum_for" })
        public static IRubyObject obj_to_enum(final ThreadContext context, final IRubyObject self, final Block block) {
            return RubyEnumerator.newEnumerator(context, self);
        }
        
        @JRubyMethod(name = { "to_enum", "enum_for" })
        public static IRubyObject obj_to_enum(final ThreadContext context, final IRubyObject self, final IRubyObject arg, final Block block) {
            return RubyEnumerator.newEnumerator(context, self, arg);
        }
        
        @JRubyMethod(name = { "to_enum", "enum_for" })
        public static IRubyObject obj_to_enum(final ThreadContext context, final IRubyObject self, final IRubyObject arg0, final IRubyObject arg1, final Block block) {
            return RubyEnumerator.newEnumerator(context, self, arg0, arg1);
        }
        
        @JRubyMethod(name = { "to_enum", "enum_for" }, optional = 1, rest = true)
        public static IRubyObject obj_to_enum(final ThreadContext context, final IRubyObject self, final IRubyObject[] args, final Block block) {
            final IRubyObject[] newArgs = new IRubyObject[args.length + 1];
            newArgs[0] = self;
            System.arraycopy(args, 0, newArgs, 1, args.length);
            return context.getRuntime().getEnumerator().callMethod(context, "new", newArgs);
        }
    }
    
    public static final class RubyEnumeratorEnumerable
    {
        public static IRubyObject each_slice(final ThreadContext context, final IRubyObject self, final IRubyObject arg, final Block block) {
            final int size = RubyNumeric.num2int(arg);
            final Ruby runtime = context.getRuntime();
            if (size <= 0) {
                throw runtime.newArgumentError("invalid slice size");
            }
            final RubyArray[] result = { runtime.newArray(size) };
            RubyEnumerable.callEach(runtime, context, self, Arity.ONE_ARGUMENT, new BlockCallback() {
                public IRubyObject call(final ThreadContext ctx, final IRubyObject[] largs, final Block blk) {
                    result[0].append(largs[0]);
                    if (result[0].size() == size) {
                        block.yield(ctx, result[0]);
                        result[0] = runtime.newArray(size);
                    }
                    return runtime.getNil();
                }
            });
            if (result[0].size() > 0) {
                block.yield(context, result[0]);
            }
            return context.getRuntime().getNil();
        }
        
        @JRubyMethod(name = { "each_slice" })
        public static IRubyObject each_slice19(final ThreadContext context, final IRubyObject self, final IRubyObject arg, final Block block) {
            return block.isGiven() ? each_slice(context, self, arg, block) : RubyEnumerator.enumeratorize(context.getRuntime(), self, "each_slice", arg);
        }
        
        @JRubyMethod(name = { "enum_slice" })
        public static IRubyObject enum_slice19(final ThreadContext context, final IRubyObject self, final IRubyObject arg, final Block block) {
            return block.isGiven() ? each_slice(context, self, arg, block) : RubyEnumerator.enumeratorize(context.getRuntime(), self, "enum_slice", arg);
        }
        
        public static IRubyObject each_cons(final ThreadContext context, final IRubyObject self, final IRubyObject arg, final Block block) {
            final int size = (int)RubyNumeric.num2long(arg);
            final Ruby runtime = context.getRuntime();
            if (size <= 0) {
                throw runtime.newArgumentError("invalid size");
            }
            final RubyArray result = runtime.newArray(size);
            RubyEnumerable.callEach(runtime, context, self, Arity.ONE_ARGUMENT, new BlockCallback() {
                public IRubyObject call(final ThreadContext ctx, final IRubyObject[] largs, final Block blk) {
                    if (result.size() == size) {
                        result.shift(ctx);
                    }
                    result.append(largs[0]);
                    if (result.size() == size) {
                        block.yield(ctx, result.aryDup());
                    }
                    return runtime.getNil();
                }
            });
            return runtime.getNil();
        }
        
        @JRubyMethod(name = { "each_cons" })
        public static IRubyObject each_cons19(final ThreadContext context, final IRubyObject self, final IRubyObject arg, final Block block) {
            return block.isGiven() ? each_cons(context, self, arg, block) : RubyEnumerator.enumeratorize(context.getRuntime(), self, "each_cons", arg);
        }
        
        @JRubyMethod(name = { "enum_cons" })
        public static IRubyObject enum_cons19(final ThreadContext context, final IRubyObject self, final IRubyObject arg, final Block block) {
            return block.isGiven() ? each_cons(context, self, arg, block) : RubyEnumerator.enumeratorize(context.getRuntime(), self, "enum_cons", arg);
        }
        
        @JRubyMethod(name = { "each_with_object" }, compat = CompatVersion.RUBY1_9)
        public static IRubyObject each_with_object(final ThreadContext context, final IRubyObject self, final IRubyObject arg, final Block block) {
            return with_object_common(context, self, arg, block, "each_with_object");
        }
        
        @JRubyMethod(name = { "with_object" }, compat = CompatVersion.RUBY1_9)
        public static IRubyObject with_object(final ThreadContext context, final IRubyObject self, final IRubyObject arg, final Block block) {
            return with_object_common(context, self, arg, block, "with_object");
        }
        
        private static IRubyObject with_object_common(final ThreadContext context, final IRubyObject self, final IRubyObject arg, final Block block, final String rubyMethodName) {
            final Ruby runtime = context.getRuntime();
            if (!block.isGiven()) {
                return RubyEnumerator.enumeratorize(runtime, self, rubyMethodName, arg);
            }
            RubyEnumerable.callEach(runtime, context, self, Arity.ONE_ARGUMENT, new BlockCallback() {
                public IRubyObject call(final ThreadContext ctx, final IRubyObject[] largs, final Block blk) {
                    block.call(ctx, new IRubyObject[] { runtime.newArray(largs[0], arg) });
                    return runtime.getNil();
                }
            });
            return arg;
        }
    }
    
    private static class EachWithIndex implements BlockCallback
    {
        private int index;
        private final Block block;
        private final Ruby runtime;
        
        public EachWithIndex(final ThreadContext ctx, final Block block, final int index) {
            this.index = 0;
            this.block = block;
            this.runtime = ctx.getRuntime();
            this.index = index;
        }
        
        public IRubyObject call(final ThreadContext context, final IRubyObject[] iargs, final Block block) {
            return this.block.call(context, new IRubyObject[] { this.runtime.newArray(RubyEnumerable.checkArgs(this.runtime, iargs), this.runtime.newFixnum(this.index++)) });
        }
    }
}
