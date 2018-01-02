// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.internal.runtime.methods;

import org.jruby.runtime.RubyEvent;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.parser.StaticScope;
import org.jruby.runtime.Arity;

public abstract class JavaMethod extends DynamicMethod implements Cloneable
{
    protected int arityValue;
    protected Arity arity;
    private String javaName;
    private boolean isSingleton;
    protected StaticScope staticScope;
    public static final Class[][] METHODS;
    public static final Class[][] REST_METHODS;
    public static final Class[][] BLOCK_METHODS;
    public static final Class[][] BLOCK_REST_METHODS;
    
    public JavaMethod(final RubyModule implementationClass, final Visibility visibility) {
        this(implementationClass, visibility, CallConfiguration.FrameFullScopeNone);
    }
    
    public JavaMethod(final RubyModule implementationClass, final Visibility visibility, final CallConfiguration callConfig) {
        super(implementationClass, visibility, callConfig);
        this.arity = Arity.OPTIONAL;
    }
    
    protected JavaMethod() {
        this.arity = Arity.OPTIONAL;
    }
    
    public void init(final RubyModule implementationClass, final Arity arity, final Visibility visibility, final StaticScope staticScope, final CallConfiguration callConfig) {
        this.staticScope = staticScope;
        this.arity = arity;
        this.arityValue = arity.getValue();
        super.init(implementationClass, visibility, callConfig);
    }
    
    public DynamicMethod dup() {
        try {
            final JavaMethod msm = (JavaMethod)this.clone();
            return msm;
        }
        catch (CloneNotSupportedException cnse) {
            return null;
        }
    }
    
    protected final void preFrameAndScope(final ThreadContext context, final IRubyObject self, final String name, final Block block) {
        context.preMethodFrameAndScope(this.implementationClass, name, self, block, this.staticScope);
    }
    
    protected final void preFrameAndDummyScope(final ThreadContext context, final IRubyObject self, final String name, final Block block) {
        context.preMethodFrameAndDummyScope(this.implementationClass, name, self, block, this.staticScope);
    }
    
    protected final void preFrameOnly(final ThreadContext context, final IRubyObject self, final String name, final Block block) {
        context.preMethodFrameOnly(this.implementationClass, name, self, block);
    }
    
    protected final void preScopeOnly(final ThreadContext context) {
        context.preMethodScopeOnly(this.implementationClass, this.staticScope);
    }
    
    protected final void preNoFrameDummyScope(final ThreadContext context) {
        context.preMethodNoFrameAndDummyScope(this.implementationClass, this.staticScope);
    }
    
    protected final void preBacktraceOnly(final ThreadContext context, final String name) {
        context.preMethodBacktraceOnly(name);
    }
    
    protected final void preBacktraceDummyScope(final ThreadContext context, final String name) {
        context.preMethodBacktraceDummyScope(this.implementationClass, name, this.staticScope);
    }
    
    protected final void preBacktraceAndScope(final ThreadContext context, final String name) {
        context.preMethodBacktraceAndScope(name, this.implementationClass, this.staticScope);
    }
    
    protected final void preNoop() {
    }
    
    protected static final void postFrameAndScope(final ThreadContext context) {
        context.postMethodFrameAndScope();
    }
    
    protected static final void postFrameOnly(final ThreadContext context) {
        context.postMethodFrameOnly();
    }
    
    protected static final void postScopeOnly(final ThreadContext context) {
        context.postMethodScopeOnly();
    }
    
    protected static final void postNoFrameDummyScope(final ThreadContext context) {
        context.postMethodScopeOnly();
    }
    
    protected static final void postBacktraceOnly(final ThreadContext context) {
        context.postMethodBacktraceOnly();
    }
    
    protected static final void postBacktraceDummyScope(final ThreadContext context) {
        context.postMethodBacktraceDummyScope();
    }
    
    protected static final void postBacktraceAndScope(final ThreadContext context) {
        context.postMethodBacktraceAndScope();
    }
    
    protected static final void postNoop(final ThreadContext context) {
    }
    
    protected final void callTrace(final ThreadContext context, final boolean enabled, final String name) {
        if (enabled) {
            context.trace(RubyEvent.C_CALL, name, this.getImplementationClass());
        }
    }
    
    protected final void returnTrace(final ThreadContext context, final boolean enabled, final String name) {
        if (enabled) {
            context.trace(RubyEvent.C_RETURN, name, this.getImplementationClass());
        }
    }
    
    protected final void callTraceCompiled(final ThreadContext context, final boolean enabled, final String name, final String file, final int line) {
        if (enabled) {
            context.trace(RubyEvent.CALL, name, this.getImplementationClass(), file, line);
        }
    }
    
    protected final void returnTraceCompiled(final ThreadContext context, final boolean enabled, final String name) {
        if (enabled) {
            context.trace(RubyEvent.RETURN, name, this.getImplementationClass());
        }
    }
    
    public void setArity(final Arity arity) {
        this.arity = arity;
        this.arityValue = arity.getValue();
    }
    
    public Arity getArity() {
        return this.arity;
    }
    
    public void setJavaName(final String javaName) {
        this.javaName = javaName;
    }
    
    public String getJavaName() {
        return this.javaName;
    }
    
    public void setSingleton(final boolean isSingleton) {
        this.isSingleton = isSingleton;
    }
    
    public boolean isSingleton() {
        return this.isSingleton;
    }
    
    public boolean isNative() {
        return true;
    }
    
    protected static IRubyObject raiseArgumentError(final JavaMethod method, final ThreadContext context, final String name, final int given, final int min, final int max) {
        try {
            method.preBacktraceOnly(context, name);
            Arity.raiseArgumentError(context.getRuntime(), given, min, max);
        }
        finally {
            postBacktraceOnly(context);
        }
        return context.getRuntime().getNil();
    }
    
    protected static void checkArgumentCount(final JavaMethod method, final ThreadContext context, final String name, final IRubyObject[] args, final int num) {
        if (args.length != num) {
            raiseArgumentError(method, context, name, args.length, num, num);
        }
    }
    
    static {
        METHODS = new Class[][] { { JavaMethodZero.class, JavaMethodZeroOrOne.class, JavaMethodZeroOrOneOrTwo.class, JavaMethodZeroOrOneOrTwoOrThree.class }, { null, JavaMethodOne.class, JavaMethodOneOrTwo.class, JavaMethodOneOrTwoOrThree.class }, { null, null, JavaMethodTwo.class, JavaMethodTwoOrThree.class }, { null, null, null, JavaMethodThree.class } };
        REST_METHODS = new Class[][] { { JavaMethodZeroOrN.class, JavaMethodZeroOrOneOrN.class, JavaMethodZeroOrOneOrTwoOrN.class, JavaMethodZeroOrOneOrTwoOrThreeOrN.class }, { null, JavaMethodOneOrN.class, JavaMethodOneOrTwoOrN.class, JavaMethodOneOrTwoOrThreeOrN.class }, { null, null, JavaMethodTwoOrN.class, JavaMethodTwoOrThreeOrN.class }, { null, null, null, JavaMethodThreeOrN.class } };
        BLOCK_METHODS = new Class[][] { { JavaMethodZeroBlock.class, JavaMethodZeroOrOneBlock.class, JavaMethodZeroOrOneOrTwoBlock.class, JavaMethodZeroOrOneOrTwoOrThreeBlock.class }, { null, JavaMethodOneBlock.class, JavaMethodOneOrTwoBlock.class, JavaMethodOneOrTwoOrThreeBlock.class }, { null, null, JavaMethodTwoBlock.class, JavaMethodTwoOrThreeBlock.class }, { null, null, null, JavaMethodThreeBlock.class } };
        BLOCK_REST_METHODS = new Class[][] { { JavaMethodZeroOrNBlock.class, JavaMethodZeroOrOneOrNBlock.class, JavaMethodZeroOrOneOrTwoOrNBlock.class, JavaMethodZeroOrOneOrTwoOrThreeOrNBlock.class }, { null, JavaMethodOneOrNBlock.class, JavaMethodOneOrTwoOrNBlock.class, JavaMethodOneOrTwoOrThreeOrNBlock.class }, { null, null, JavaMethodTwoOrNBlock.class, JavaMethodTwoOrThreeOrNBlock.class }, { null, null, null, JavaMethodThreeOrNBlock.class } };
    }
    
    public abstract static class JavaMethodNBlock extends JavaMethod
    {
        public JavaMethodNBlock(final RubyModule implementationClass, final Visibility visibility) {
            super(implementationClass, visibility);
        }
        
        public JavaMethodNBlock(final RubyModule implementationClass, final Visibility visibility, final CallConfiguration callConfig) {
            super(implementationClass, visibility, callConfig);
        }
    }
    
    public abstract static class JavaMethodZeroOrNBlock extends JavaMethodNBlock
    {
        public JavaMethodZeroOrNBlock(final RubyModule implementationClass, final Visibility visibility) {
            super(implementationClass, visibility);
        }
        
        public JavaMethodZeroOrNBlock(final RubyModule implementationClass, final Visibility visibility, final CallConfiguration callConfig) {
            super(implementationClass, visibility, callConfig);
        }
        
        public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name) {
            return this.call(context, self, clazz, name, Block.NULL_BLOCK);
        }
        
        public abstract IRubyObject call(final ThreadContext p0, final IRubyObject p1, final RubyModule p2, final String p3, final Block p4);
    }
    
    public abstract static class JavaMethodZeroOrOneOrNBlock extends JavaMethodZeroOrNBlock
    {
        public JavaMethodZeroOrOneOrNBlock(final RubyModule implementationClass, final Visibility visibility) {
            super(implementationClass, visibility);
        }
        
        public JavaMethodZeroOrOneOrNBlock(final RubyModule implementationClass, final Visibility visibility, final CallConfiguration callConfig) {
            super(implementationClass, visibility, callConfig);
        }
        
        public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject arg0) {
            return this.call(context, self, clazz, name, arg0, Block.NULL_BLOCK);
        }
        
        public abstract IRubyObject call(final ThreadContext p0, final IRubyObject p1, final RubyModule p2, final String p3, final IRubyObject p4, final Block p5);
    }
    
    public abstract static class JavaMethodZeroOrOneOrTwoOrNBlock extends JavaMethodZeroOrOneOrNBlock
    {
        public JavaMethodZeroOrOneOrTwoOrNBlock(final RubyModule implementationClass, final Visibility visibility) {
            super(implementationClass, visibility);
        }
        
        public JavaMethodZeroOrOneOrTwoOrNBlock(final RubyModule implementationClass, final Visibility visibility, final CallConfiguration callConfig) {
            super(implementationClass, visibility, callConfig);
        }
        
        public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject arg0, final IRubyObject arg1) {
            return this.call(context, self, clazz, name, arg0, arg1, Block.NULL_BLOCK);
        }
        
        public abstract IRubyObject call(final ThreadContext p0, final IRubyObject p1, final RubyModule p2, final String p3, final IRubyObject p4, final IRubyObject p5, final Block p6);
    }
    
    public abstract static class JavaMethodZeroOrOneOrTwoOrThreeOrNBlock extends JavaMethodZeroOrOneOrTwoOrNBlock
    {
        public JavaMethodZeroOrOneOrTwoOrThreeOrNBlock(final RubyModule implementationClass, final Visibility visibility) {
            super(implementationClass, visibility);
        }
        
        public JavaMethodZeroOrOneOrTwoOrThreeOrNBlock(final RubyModule implementationClass, final Visibility visibility, final CallConfiguration callConfig) {
            super(implementationClass, visibility, callConfig);
        }
        
        public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2) {
            return this.call(context, self, clazz, name, arg0, arg1, arg2, Block.NULL_BLOCK);
        }
        
        public abstract IRubyObject call(final ThreadContext p0, final IRubyObject p1, final RubyModule p2, final String p3, final IRubyObject p4, final IRubyObject p5, final IRubyObject p6, final Block p7);
    }
    
    public abstract static class JavaMethodOneOrNBlock extends JavaMethodNBlock
    {
        public JavaMethodOneOrNBlock(final RubyModule implementationClass, final Visibility visibility) {
            super(implementationClass, visibility);
        }
        
        public JavaMethodOneOrNBlock(final RubyModule implementationClass, final Visibility visibility, final CallConfiguration callConfig) {
            super(implementationClass, visibility, callConfig);
        }
        
        public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject arg0) {
            return this.call(context, self, clazz, name, arg0, Block.NULL_BLOCK);
        }
        
        public abstract IRubyObject call(final ThreadContext p0, final IRubyObject p1, final RubyModule p2, final String p3, final IRubyObject p4, final Block p5);
    }
    
    public abstract static class JavaMethodOneOrTwoOrNBlock extends JavaMethodOneOrNBlock
    {
        public JavaMethodOneOrTwoOrNBlock(final RubyModule implementationClass, final Visibility visibility) {
            super(implementationClass, visibility);
        }
        
        public JavaMethodOneOrTwoOrNBlock(final RubyModule implementationClass, final Visibility visibility, final CallConfiguration callConfig) {
            super(implementationClass, visibility, callConfig);
        }
        
        public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject arg0, final IRubyObject arg1) {
            return this.call(context, self, clazz, name, arg0, arg1, Block.NULL_BLOCK);
        }
        
        public abstract IRubyObject call(final ThreadContext p0, final IRubyObject p1, final RubyModule p2, final String p3, final IRubyObject p4, final IRubyObject p5, final Block p6);
    }
    
    public abstract static class JavaMethodOneOrTwoOrThreeOrNBlock extends JavaMethodOneOrTwoOrNBlock
    {
        public JavaMethodOneOrTwoOrThreeOrNBlock(final RubyModule implementationClass, final Visibility visibility) {
            super(implementationClass, visibility);
        }
        
        public JavaMethodOneOrTwoOrThreeOrNBlock(final RubyModule implementationClass, final Visibility visibility, final CallConfiguration callConfig) {
            super(implementationClass, visibility, callConfig);
        }
        
        public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2) {
            return this.call(context, self, clazz, name, arg0, arg1, arg2, Block.NULL_BLOCK);
        }
        
        public abstract IRubyObject call(final ThreadContext p0, final IRubyObject p1, final RubyModule p2, final String p3, final IRubyObject p4, final IRubyObject p5, final IRubyObject p6, final Block p7);
    }
    
    public abstract static class JavaMethodTwoOrNBlock extends JavaMethodNBlock
    {
        public JavaMethodTwoOrNBlock(final RubyModule implementationClass, final Visibility visibility) {
            super(implementationClass, visibility);
        }
        
        public JavaMethodTwoOrNBlock(final RubyModule implementationClass, final Visibility visibility, final CallConfiguration callConfig) {
            super(implementationClass, visibility, callConfig);
        }
        
        public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject arg0, final IRubyObject arg1) {
            return this.call(context, self, clazz, name, arg0, arg1, Block.NULL_BLOCK);
        }
        
        public abstract IRubyObject call(final ThreadContext p0, final IRubyObject p1, final RubyModule p2, final String p3, final IRubyObject p4, final IRubyObject p5, final Block p6);
    }
    
    public abstract static class JavaMethodTwoOrThreeOrNBlock extends JavaMethodTwoOrNBlock
    {
        public JavaMethodTwoOrThreeOrNBlock(final RubyModule implementationClass, final Visibility visibility) {
            super(implementationClass, visibility);
        }
        
        public JavaMethodTwoOrThreeOrNBlock(final RubyModule implementationClass, final Visibility visibility, final CallConfiguration callConfig) {
            super(implementationClass, visibility, callConfig);
        }
        
        public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2) {
            return this.call(context, self, clazz, name, arg0, arg1, arg2, Block.NULL_BLOCK);
        }
        
        public abstract IRubyObject call(final ThreadContext p0, final IRubyObject p1, final RubyModule p2, final String p3, final IRubyObject p4, final IRubyObject p5, final IRubyObject p6, final Block p7);
    }
    
    public abstract static class JavaMethodThreeOrNBlock extends JavaMethodNBlock
    {
        public JavaMethodThreeOrNBlock(final RubyModule implementationClass, final Visibility visibility) {
            super(implementationClass, visibility);
        }
        
        public JavaMethodThreeOrNBlock(final RubyModule implementationClass, final Visibility visibility, final CallConfiguration callConfig) {
            super(implementationClass, visibility, callConfig);
        }
        
        public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2) {
            return this.call(context, self, clazz, name, arg0, arg1, arg2, Block.NULL_BLOCK);
        }
        
        public abstract IRubyObject call(final ThreadContext p0, final IRubyObject p1, final RubyModule p2, final String p3, final IRubyObject p4, final IRubyObject p5, final IRubyObject p6, final Block p7);
    }
    
    public abstract static class JavaMethodZeroBlock extends JavaMethodZeroOrNBlock
    {
        public JavaMethodZeroBlock(final RubyModule implementationClass, final Visibility visibility) {
            super(implementationClass, visibility);
        }
        
        public JavaMethodZeroBlock(final RubyModule implementationClass, final Visibility visibility, final CallConfiguration callConfig) {
            super(implementationClass, visibility, callConfig);
        }
        
        public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject[] args, final Block block) {
            if (args.length != 0) {
                return JavaMethod.raiseArgumentError(this, context, name, args.length, 0, 0);
            }
            return this.call(context, self, clazz, name, block);
        }
    }
    
    public abstract static class JavaMethodZeroOrOneBlock extends JavaMethodZeroOrOneOrNBlock
    {
        public JavaMethodZeroOrOneBlock(final RubyModule implementationClass, final Visibility visibility) {
            super(implementationClass, visibility);
        }
        
        public JavaMethodZeroOrOneBlock(final RubyModule implementationClass, final Visibility visibility, final CallConfiguration callConfig) {
            super(implementationClass, visibility, callConfig);
        }
        
        public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject[] args, final Block block) {
            switch (args.length) {
                case 0: {
                    return this.call(context, self, clazz, name, block);
                }
                case 1: {
                    return this.call(context, self, clazz, name, args[0], block);
                }
                default: {
                    return JavaMethod.raiseArgumentError(this, context, name, args.length, 0, 1);
                }
            }
        }
    }
    
    public abstract static class JavaMethodZeroOrOneOrTwoBlock extends JavaMethodZeroOrOneOrTwoOrNBlock
    {
        public JavaMethodZeroOrOneOrTwoBlock(final RubyModule implementationClass, final Visibility visibility) {
            super(implementationClass, visibility);
        }
        
        public JavaMethodZeroOrOneOrTwoBlock(final RubyModule implementationClass, final Visibility visibility, final CallConfiguration callConfig) {
            super(implementationClass, visibility, callConfig);
        }
        
        public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject[] args, final Block block) {
            switch (args.length) {
                case 0: {
                    return this.call(context, self, clazz, name, block);
                }
                case 1: {
                    return this.call(context, self, clazz, name, args[0], block);
                }
                case 2: {
                    return this.call(context, self, clazz, name, args[0], args[1], block);
                }
                default: {
                    return JavaMethod.raiseArgumentError(this, context, name, args.length, 0, 2);
                }
            }
        }
    }
    
    public abstract static class JavaMethodZeroOrOneOrTwoOrThreeBlock extends JavaMethodZeroOrOneOrTwoOrThreeOrNBlock
    {
        public JavaMethodZeroOrOneOrTwoOrThreeBlock(final RubyModule implementationClass, final Visibility visibility) {
            super(implementationClass, visibility);
        }
        
        public JavaMethodZeroOrOneOrTwoOrThreeBlock(final RubyModule implementationClass, final Visibility visibility, final CallConfiguration callConfig) {
            super(implementationClass, visibility, callConfig);
        }
        
        public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject[] args, final Block block) {
            switch (args.length) {
                case 0: {
                    return this.call(context, self, clazz, name, block);
                }
                case 1: {
                    return this.call(context, self, clazz, name, args[0], block);
                }
                case 2: {
                    return this.call(context, self, clazz, name, args[0], args[1], block);
                }
                case 3: {
                    return this.call(context, self, clazz, name, args[0], args[1], args[2], block);
                }
                default: {
                    return JavaMethod.raiseArgumentError(this, context, name, args.length, 0, 3);
                }
            }
        }
    }
    
    public abstract static class JavaMethodOneBlock extends JavaMethodOneOrNBlock
    {
        public JavaMethodOneBlock(final RubyModule implementationClass, final Visibility visibility) {
            super(implementationClass, visibility);
        }
        
        public JavaMethodOneBlock(final RubyModule implementationClass, final Visibility visibility, final CallConfiguration callConfig) {
            super(implementationClass, visibility, callConfig);
        }
        
        public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject[] args, final Block block) {
            if (args.length != 1) {
                return JavaMethod.raiseArgumentError(this, context, name, args.length, 1, 1);
            }
            return this.call(context, self, clazz, name, args[0], block);
        }
        
        public Arity getArity() {
            return Arity.ONE_ARGUMENT;
        }
    }
    
    public abstract static class JavaMethodOneOrTwoBlock extends JavaMethodOneOrTwoOrNBlock
    {
        public JavaMethodOneOrTwoBlock(final RubyModule implementationClass, final Visibility visibility) {
            super(implementationClass, visibility);
        }
        
        public JavaMethodOneOrTwoBlock(final RubyModule implementationClass, final Visibility visibility, final CallConfiguration callConfig) {
            super(implementationClass, visibility, callConfig);
        }
        
        public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject[] args, final Block block) {
            switch (args.length) {
                case 1: {
                    return this.call(context, self, clazz, name, args[0], block);
                }
                case 2: {
                    return this.call(context, self, clazz, name, args[0], args[1], block);
                }
                default: {
                    return JavaMethod.raiseArgumentError(this, context, name, args.length, 1, 2);
                }
            }
        }
    }
    
    public abstract static class JavaMethodOneOrTwoOrThreeBlock extends JavaMethodOneOrTwoOrThreeOrNBlock
    {
        public JavaMethodOneOrTwoOrThreeBlock(final RubyModule implementationClass, final Visibility visibility) {
            super(implementationClass, visibility);
        }
        
        public JavaMethodOneOrTwoOrThreeBlock(final RubyModule implementationClass, final Visibility visibility, final CallConfiguration callConfig) {
            super(implementationClass, visibility, callConfig);
        }
        
        public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject[] args, final Block block) {
            switch (args.length) {
                case 0: {
                    throw context.getRuntime().newArgumentError(0, 1);
                }
                case 1: {
                    return this.call(context, self, clazz, name, args[0], block);
                }
                case 2: {
                    return this.call(context, self, clazz, name, args[0], args[1], block);
                }
                case 3: {
                    return this.call(context, self, clazz, name, args[0], args[1], args[2], block);
                }
                default: {
                    return JavaMethod.raiseArgumentError(this, context, name, args.length, 3, 3);
                }
            }
        }
    }
    
    public abstract static class JavaMethodTwoBlock extends JavaMethodTwoOrNBlock
    {
        public JavaMethodTwoBlock(final RubyModule implementationClass, final Visibility visibility) {
            super(implementationClass, visibility);
        }
        
        public JavaMethodTwoBlock(final RubyModule implementationClass, final Visibility visibility, final CallConfiguration callConfig) {
            super(implementationClass, visibility, callConfig);
        }
        
        public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject[] args, final Block block) {
            if (args.length != 2) {
                return JavaMethod.raiseArgumentError(this, context, name, args.length, 2, 2);
            }
            return this.call(context, self, clazz, name, args[0], args[1], block);
        }
    }
    
    public abstract static class JavaMethodTwoOrThreeBlock extends JavaMethodTwoOrThreeOrNBlock
    {
        public JavaMethodTwoOrThreeBlock(final RubyModule implementationClass, final Visibility visibility) {
            super(implementationClass, visibility);
        }
        
        public JavaMethodTwoOrThreeBlock(final RubyModule implementationClass, final Visibility visibility, final CallConfiguration callConfig) {
            super(implementationClass, visibility, callConfig);
        }
        
        public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject[] args, final Block block) {
            switch (args.length) {
                case 2: {
                    return this.call(context, self, clazz, name, args[0], args[1], block);
                }
                case 3: {
                    return this.call(context, self, clazz, name, args[0], args[1], args[2], block);
                }
                default: {
                    return JavaMethod.raiseArgumentError(this, context, name, args.length, 2, 3);
                }
            }
        }
    }
    
    public abstract static class JavaMethodThreeBlock extends JavaMethodThreeOrNBlock
    {
        public JavaMethodThreeBlock(final RubyModule implementationClass, final Visibility visibility) {
            super(implementationClass, visibility);
        }
        
        public JavaMethodThreeBlock(final RubyModule implementationClass, final Visibility visibility, final CallConfiguration callConfig) {
            super(implementationClass, visibility, callConfig);
        }
        
        public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject[] args, final Block block) {
            if (args.length != 3) {
                return JavaMethod.raiseArgumentError(this, context, name, args.length, 3, 3);
            }
            return this.call(context, self, clazz, name, args[0], args[1], args[2], block);
        }
    }
    
    public abstract static class JavaMethodN extends JavaMethodNBlock
    {
        public JavaMethodN(final RubyModule implementationClass, final Visibility visibility) {
            super(implementationClass, visibility);
        }
        
        public JavaMethodN(final RubyModule implementationClass, final Visibility visibility, final CallConfiguration callConfig) {
            super(implementationClass, visibility, callConfig);
        }
        
        public abstract IRubyObject call(final ThreadContext p0, final IRubyObject p1, final RubyModule p2, final String p3, final IRubyObject[] p4);
        
        public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final Block block) {
            return this.call(context, self, clazz, name, IRubyObject.NULL_ARRAY);
        }
        
        public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject arg0, final Block block) {
            return this.call(context, self, clazz, name, new IRubyObject[] { arg0 });
        }
        
        public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject arg0, final IRubyObject arg1, final Block block) {
            return this.call(context, self, clazz, name, new IRubyObject[] { arg0, arg1 });
        }
        
        public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2, final Block block) {
            return this.call(context, self, clazz, name, new IRubyObject[] { arg0, arg1, arg2 });
        }
        
        public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject[] args, final Block block) {
            return this.call(context, self, clazz, name, args);
        }
    }
    
    public abstract static class JavaMethodZeroOrN extends JavaMethodN
    {
        public JavaMethodZeroOrN(final RubyModule implementationClass, final Visibility visibility) {
            super(implementationClass, visibility);
        }
        
        public JavaMethodZeroOrN(final RubyModule implementationClass, final Visibility visibility, final CallConfiguration callConfig) {
            super(implementationClass, visibility, callConfig);
        }
        
        public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final Block block) {
            return this.call(context, self, clazz, name);
        }
        
        public abstract IRubyObject call(final ThreadContext p0, final IRubyObject p1, final RubyModule p2, final String p3);
    }
    
    public abstract static class JavaMethodZeroOrOneOrN extends JavaMethodZeroOrN
    {
        public JavaMethodZeroOrOneOrN(final RubyModule implementationClass, final Visibility visibility) {
            super(implementationClass, visibility);
        }
        
        public JavaMethodZeroOrOneOrN(final RubyModule implementationClass, final Visibility visibility, final CallConfiguration callConfig) {
            super(implementationClass, visibility, callConfig);
        }
        
        public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject arg0, final Block block) {
            return this.call(context, self, clazz, name, arg0);
        }
        
        public abstract IRubyObject call(final ThreadContext p0, final IRubyObject p1, final RubyModule p2, final String p3, final IRubyObject p4);
    }
    
    public abstract static class JavaMethodZeroOrOneOrTwoOrN extends JavaMethodZeroOrOneOrN
    {
        public JavaMethodZeroOrOneOrTwoOrN(final RubyModule implementationClass, final Visibility visibility) {
            super(implementationClass, visibility);
        }
        
        public JavaMethodZeroOrOneOrTwoOrN(final RubyModule implementationClass, final Visibility visibility, final CallConfiguration callConfig) {
            super(implementationClass, visibility, callConfig);
        }
        
        public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject arg0, final IRubyObject arg1, final Block block) {
            return this.call(context, self, clazz, name, arg0, arg1);
        }
        
        public abstract IRubyObject call(final ThreadContext p0, final IRubyObject p1, final RubyModule p2, final String p3, final IRubyObject p4, final IRubyObject p5);
    }
    
    public abstract static class JavaMethodZeroOrOneOrTwoOrThreeOrN extends JavaMethodZeroOrOneOrTwoOrN
    {
        public JavaMethodZeroOrOneOrTwoOrThreeOrN(final RubyModule implementationClass, final Visibility visibility) {
            super(implementationClass, visibility);
        }
        
        public JavaMethodZeroOrOneOrTwoOrThreeOrN(final RubyModule implementationClass, final Visibility visibility, final CallConfiguration callConfig) {
            super(implementationClass, visibility, callConfig);
        }
        
        public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2, final Block block) {
            return this.call(context, self, clazz, name, arg0, arg1, arg2);
        }
        
        public abstract IRubyObject call(final ThreadContext p0, final IRubyObject p1, final RubyModule p2, final String p3, final IRubyObject p4, final IRubyObject p5, final IRubyObject p6);
    }
    
    public abstract static class JavaMethodOneOrN extends JavaMethodN
    {
        public JavaMethodOneOrN(final RubyModule implementationClass, final Visibility visibility) {
            super(implementationClass, visibility);
        }
        
        public JavaMethodOneOrN(final RubyModule implementationClass, final Visibility visibility, final CallConfiguration callConfig) {
            super(implementationClass, visibility, callConfig);
        }
        
        public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject arg0, final Block block) {
            return this.call(context, self, clazz, name, arg0);
        }
        
        public abstract IRubyObject call(final ThreadContext p0, final IRubyObject p1, final RubyModule p2, final String p3, final IRubyObject p4);
    }
    
    public abstract static class JavaMethodOneOrTwoOrN extends JavaMethodOneOrN
    {
        public JavaMethodOneOrTwoOrN(final RubyModule implementationClass, final Visibility visibility) {
            super(implementationClass, visibility);
        }
        
        public JavaMethodOneOrTwoOrN(final RubyModule implementationClass, final Visibility visibility, final CallConfiguration callConfig) {
            super(implementationClass, visibility, callConfig);
        }
        
        public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject arg0, final IRubyObject arg1, final Block block) {
            return this.call(context, self, clazz, name, arg0, arg1);
        }
        
        public abstract IRubyObject call(final ThreadContext p0, final IRubyObject p1, final RubyModule p2, final String p3, final IRubyObject p4, final IRubyObject p5);
    }
    
    public abstract static class JavaMethodOneOrTwoOrThreeOrN extends JavaMethodOneOrTwoOrN
    {
        public JavaMethodOneOrTwoOrThreeOrN(final RubyModule implementationClass, final Visibility visibility) {
            super(implementationClass, visibility);
        }
        
        public JavaMethodOneOrTwoOrThreeOrN(final RubyModule implementationClass, final Visibility visibility, final CallConfiguration callConfig) {
            super(implementationClass, visibility, callConfig);
        }
        
        public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2, final Block block) {
            return this.call(context, self, clazz, name, arg0, arg1, arg2);
        }
        
        public abstract IRubyObject call(final ThreadContext p0, final IRubyObject p1, final RubyModule p2, final String p3, final IRubyObject p4, final IRubyObject p5, final IRubyObject p6);
    }
    
    public abstract static class JavaMethodTwoOrN extends JavaMethodN
    {
        public JavaMethodTwoOrN(final RubyModule implementationClass, final Visibility visibility) {
            super(implementationClass, visibility);
        }
        
        public JavaMethodTwoOrN(final RubyModule implementationClass, final Visibility visibility, final CallConfiguration callConfig) {
            super(implementationClass, visibility, callConfig);
        }
        
        public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject arg0, final IRubyObject arg1, final Block block) {
            return this.call(context, self, clazz, name, arg0, arg1);
        }
        
        public abstract IRubyObject call(final ThreadContext p0, final IRubyObject p1, final RubyModule p2, final String p3, final IRubyObject p4, final IRubyObject p5);
    }
    
    public abstract static class JavaMethodTwoOrThreeOrN extends JavaMethodTwoOrN
    {
        public JavaMethodTwoOrThreeOrN(final RubyModule implementationClass, final Visibility visibility) {
            super(implementationClass, visibility);
        }
        
        public JavaMethodTwoOrThreeOrN(final RubyModule implementationClass, final Visibility visibility, final CallConfiguration callConfig) {
            super(implementationClass, visibility, callConfig);
        }
        
        public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2, final Block block) {
            return this.call(context, self, clazz, name, arg0, arg1, arg2);
        }
        
        public abstract IRubyObject call(final ThreadContext p0, final IRubyObject p1, final RubyModule p2, final String p3, final IRubyObject p4, final IRubyObject p5, final IRubyObject p6);
    }
    
    public abstract static class JavaMethodThreeOrN extends JavaMethodN
    {
        public JavaMethodThreeOrN(final RubyModule implementationClass, final Visibility visibility) {
            super(implementationClass, visibility);
        }
        
        public JavaMethodThreeOrN(final RubyModule implementationClass, final Visibility visibility, final CallConfiguration callConfig) {
            super(implementationClass, visibility, callConfig);
        }
        
        public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2, final Block block) {
            return this.call(context, self, clazz, name, arg0, arg1, arg2);
        }
        
        public abstract IRubyObject call(final ThreadContext p0, final IRubyObject p1, final RubyModule p2, final String p3, final IRubyObject p4, final IRubyObject p5, final IRubyObject p6);
    }
    
    public abstract static class JavaMethodZero extends JavaMethodZeroOrN
    {
        public JavaMethodZero(final RubyModule implementationClass, final Visibility visibility) {
            super(implementationClass, visibility);
        }
        
        public JavaMethodZero(final RubyModule implementationClass, final Visibility visibility, final CallConfiguration callConfig) {
            super(implementationClass, visibility, callConfig);
        }
        
        public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject[] args) {
            if (args.length != 0) {
                return JavaMethod.raiseArgumentError(this, context, name, args.length, 0, 0);
            }
            return this.call(context, self, clazz, name);
        }
        
        public Arity getArity() {
            return Arity.NO_ARGUMENTS;
        }
    }
    
    public abstract static class JavaMethodZeroOrOne extends JavaMethodZeroOrOneOrN
    {
        public JavaMethodZeroOrOne(final RubyModule implementationClass, final Visibility visibility) {
            super(implementationClass, visibility);
        }
        
        public JavaMethodZeroOrOne(final RubyModule implementationClass, final Visibility visibility, final CallConfiguration callConfig) {
            super(implementationClass, visibility, callConfig);
        }
        
        public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject[] args) {
            switch (args.length) {
                case 0: {
                    return this.call(context, self, clazz, name);
                }
                case 1: {
                    return this.call(context, self, clazz, name, args[0]);
                }
                default: {
                    return JavaMethod.raiseArgumentError(this, context, name, args.length, 0, 1);
                }
            }
        }
    }
    
    public abstract static class JavaMethodZeroOrOneOrTwo extends JavaMethodZeroOrOneOrTwoOrN
    {
        public JavaMethodZeroOrOneOrTwo(final RubyModule implementationClass, final Visibility visibility) {
            super(implementationClass, visibility);
        }
        
        public JavaMethodZeroOrOneOrTwo(final RubyModule implementationClass, final Visibility visibility, final CallConfiguration callConfig) {
            super(implementationClass, visibility, callConfig);
        }
        
        public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject[] args) {
            switch (args.length) {
                case 0: {
                    return this.call(context, self, clazz, name);
                }
                case 1: {
                    return this.call(context, self, clazz, name, args[0]);
                }
                case 2: {
                    return this.call(context, self, clazz, name, args[0], args[1]);
                }
                default: {
                    return JavaMethod.raiseArgumentError(this, context, name, args.length, 0, 2);
                }
            }
        }
    }
    
    public abstract static class JavaMethodZeroOrOneOrTwoOrThree extends JavaMethodZeroOrOneOrTwoOrThreeOrN
    {
        public JavaMethodZeroOrOneOrTwoOrThree(final RubyModule implementationClass, final Visibility visibility) {
            super(implementationClass, visibility);
        }
        
        public JavaMethodZeroOrOneOrTwoOrThree(final RubyModule implementationClass, final Visibility visibility, final CallConfiguration callConfig) {
            super(implementationClass, visibility, callConfig);
        }
        
        public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject[] args) {
            switch (args.length) {
                case 0: {
                    return this.call(context, self, clazz, name);
                }
                case 1: {
                    return this.call(context, self, clazz, name, args[0]);
                }
                case 2: {
                    return this.call(context, self, clazz, name, args[0], args[1]);
                }
                case 3: {
                    return this.call(context, self, clazz, name, args[0], args[1], args[2]);
                }
                default: {
                    return JavaMethod.raiseArgumentError(this, context, name, args.length, 0, 3);
                }
            }
        }
    }
    
    public abstract static class JavaMethodOne extends JavaMethodOneOrN
    {
        public JavaMethodOne(final RubyModule implementationClass, final Visibility visibility) {
            super(implementationClass, visibility);
        }
        
        public JavaMethodOne(final RubyModule implementationClass, final Visibility visibility, final CallConfiguration callConfig) {
            super(implementationClass, visibility, callConfig);
        }
        
        public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject[] args) {
            if (args.length != 1) {
                return JavaMethod.raiseArgumentError(this, context, name, args.length, 1, 1);
            }
            return this.call(context, self, clazz, name, args[0]);
        }
        
        public Arity getArity() {
            return Arity.ONE_ARGUMENT;
        }
    }
    
    public abstract static class JavaMethodOneOrTwo extends JavaMethodOneOrTwoOrN
    {
        public JavaMethodOneOrTwo(final RubyModule implementationClass, final Visibility visibility) {
            super(implementationClass, visibility);
        }
        
        public JavaMethodOneOrTwo(final RubyModule implementationClass, final Visibility visibility, final CallConfiguration callConfig) {
            super(implementationClass, visibility, callConfig);
        }
        
        public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject[] args) {
            switch (args.length) {
                case 1: {
                    return this.call(context, self, clazz, name, args[0]);
                }
                case 2: {
                    return this.call(context, self, clazz, name, args[0], args[1]);
                }
                default: {
                    return JavaMethod.raiseArgumentError(this, context, name, args.length, 1, 2);
                }
            }
        }
    }
    
    public abstract static class JavaMethodOneOrTwoOrThree extends JavaMethodOneOrTwoOrThreeOrN
    {
        public JavaMethodOneOrTwoOrThree(final RubyModule implementationClass, final Visibility visibility) {
            super(implementationClass, visibility);
        }
        
        public JavaMethodOneOrTwoOrThree(final RubyModule implementationClass, final Visibility visibility, final CallConfiguration callConfig) {
            super(implementationClass, visibility, callConfig);
        }
        
        public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject[] args) {
            switch (args.length) {
                case 1: {
                    return this.call(context, self, clazz, name, args[0]);
                }
                case 2: {
                    return this.call(context, self, clazz, name, args[0], args[1]);
                }
                case 3: {
                    return this.call(context, self, clazz, name, args[0], args[1], args[2]);
                }
                default: {
                    return JavaMethod.raiseArgumentError(this, context, name, args.length, 1, 3);
                }
            }
        }
    }
    
    public abstract static class JavaMethodTwo extends JavaMethodTwoOrN
    {
        public JavaMethodTwo(final RubyModule implementationClass, final Visibility visibility) {
            super(implementationClass, visibility);
        }
        
        public JavaMethodTwo(final RubyModule implementationClass, final Visibility visibility, final CallConfiguration callConfig) {
            super(implementationClass, visibility, callConfig);
        }
        
        public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject[] args) {
            if (args.length != 2) {
                return JavaMethod.raiseArgumentError(this, context, name, args.length, 2, 2);
            }
            return this.call(context, self, clazz, name, args[0], args[1]);
        }
        
        public Arity getArity() {
            return Arity.TWO_ARGUMENTS;
        }
    }
    
    public abstract static class JavaMethodTwoOrThree extends JavaMethodTwoOrThreeOrN
    {
        public JavaMethodTwoOrThree(final RubyModule implementationClass, final Visibility visibility) {
            super(implementationClass, visibility);
        }
        
        public JavaMethodTwoOrThree(final RubyModule implementationClass, final Visibility visibility, final CallConfiguration callConfig) {
            super(implementationClass, visibility, callConfig);
        }
        
        public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject[] args) {
            switch (args.length) {
                case 2: {
                    return this.call(context, self, clazz, name, args[0], args[1]);
                }
                case 3: {
                    return this.call(context, self, clazz, name, args[0], args[1], args[2]);
                }
                default: {
                    return JavaMethod.raiseArgumentError(this, context, name, args.length, 2, 3);
                }
            }
        }
    }
    
    public abstract static class JavaMethodThree extends JavaMethodThreeOrN
    {
        public JavaMethodThree(final RubyModule implementationClass, final Visibility visibility) {
            super(implementationClass, visibility);
        }
        
        public JavaMethodThree(final RubyModule implementationClass, final Visibility visibility, final CallConfiguration callConfig) {
            super(implementationClass, visibility, callConfig);
        }
        
        public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject[] args) {
            if (args.length != 3) {
                return JavaMethod.raiseArgumentError(this, context, name, args.length, 3, 3);
            }
            return this.call(context, self, clazz, name, args[0], args[1], args[2]);
        }
        
        public Arity getArity() {
            return Arity.THREE_ARGUMENTS;
        }
    }
}
