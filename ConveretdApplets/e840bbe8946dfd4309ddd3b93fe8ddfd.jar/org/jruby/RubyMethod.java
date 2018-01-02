// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.runtime.PositionAware;
import org.jruby.runtime.DynamicScope;
import org.jruby.exceptions.JumpException;
import org.jruby.parser.StaticScope;
import org.jruby.runtime.MethodBlock;
import org.jruby.internal.runtime.methods.ProcMethod;
import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.Block;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.ObjectAllocator;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.anno.JRubyClass;
import org.jruby.runtime.marshal.DataType;

@JRubyClass(name = { "Method" })
public class RubyMethod extends RubyObject implements DataType
{
    protected RubyModule implementationModule;
    protected String methodName;
    protected RubyModule originModule;
    protected String originName;
    protected DynamicMethod method;
    protected IRubyObject receiver;
    
    protected RubyMethod(final Ruby runtime, final RubyClass rubyClass) {
        super(runtime, rubyClass);
    }
    
    public static RubyClass createMethodClass(final Ruby runtime) {
        final RubyClass methodClass = runtime.defineClass("Method", runtime.getObject(), ObjectAllocator.NOT_ALLOCATABLE_ALLOCATOR);
        runtime.setMethod(methodClass);
        methodClass.index = 34;
        methodClass.setReifiedClass(RubyMethod.class);
        methodClass.defineAnnotatedMethods(RubyMethod.class);
        return methodClass;
    }
    
    public static RubyMethod newMethod(final RubyModule implementationModule, final String methodName, final RubyModule originModule, final String originName, final DynamicMethod method, final IRubyObject receiver) {
        final Ruby runtime = implementationModule.getRuntime();
        final RubyMethod newMethod = new RubyMethod(runtime, runtime.getMethod());
        newMethod.implementationModule = implementationModule;
        newMethod.methodName = methodName;
        newMethod.originModule = originModule;
        newMethod.originName = originName;
        newMethod.method = method;
        newMethod.receiver = receiver;
        return newMethod;
    }
    
    public DynamicMethod getMethod() {
        return this.method;
    }
    
    @JRubyMethod(name = { "call", "[]" })
    public IRubyObject call(final ThreadContext context, final Block block) {
        return this.method.call(context, this.receiver, this.implementationModule, this.methodName, block);
    }
    
    @JRubyMethod(name = { "call", "[]" })
    public IRubyObject call(final ThreadContext context, final IRubyObject arg, final Block block) {
        return this.method.call(context, this.receiver, this.implementationModule, this.methodName, arg, block);
    }
    
    @JRubyMethod(name = { "call", "[]" })
    public IRubyObject call(final ThreadContext context, final IRubyObject arg0, final IRubyObject arg1, final Block block) {
        return this.method.call(context, this.receiver, this.implementationModule, this.methodName, arg0, arg1, block);
    }
    
    @JRubyMethod(name = { "call", "[]" })
    public IRubyObject call(final ThreadContext context, final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2, final Block block) {
        return this.method.call(context, this.receiver, this.implementationModule, this.methodName, arg0, arg1, arg2, block);
    }
    
    @JRubyMethod(name = { "call", "[]" }, rest = true)
    public IRubyObject call(final ThreadContext context, final IRubyObject[] args, final Block block) {
        return this.method.call(context, this.receiver, this.implementationModule, this.methodName, args, block);
    }
    
    @JRubyMethod(name = { "arity" })
    public RubyFixnum arity() {
        return this.getRuntime().newFixnum(this.method.getArity().getValue());
    }
    
    @JRubyMethod(name = { "==" }, required = 1)
    public RubyBoolean op_equal(final ThreadContext context, final IRubyObject other) {
        if (!(other instanceof RubyMethod)) {
            return context.getRuntime().getFalse();
        }
        if (this.method instanceof ProcMethod) {
            return context.getRuntime().newBoolean(((ProcMethod)this.method).isSame(((RubyMethod)other).getMethod()));
        }
        final RubyMethod otherMethod = (RubyMethod)other;
        return context.getRuntime().newBoolean(this.receiver == otherMethod.receiver && this.originModule == otherMethod.originModule && this.method.getRealMethod().getSerialNumber() == otherMethod.method.getRealMethod().getSerialNumber());
    }
    
    @JRubyMethod(name = { "eql?" }, required = 1, compat = CompatVersion.RUBY1_9)
    public IRubyObject op_eql19(final ThreadContext context, final IRubyObject other) {
        return this.op_equal(context, other);
    }
    
    @JRubyMethod(name = { "clone" })
    public RubyMethod rbClone() {
        return newMethod(this.implementationModule, this.methodName, this.originModule, this.originName, this.method, this.receiver);
    }
    
    @JRubyMethod
    public IRubyObject to_proc(final ThreadContext context, final Block unusedBlock) {
        final Ruby runtime = context.getRuntime();
        final DynamicScope currentScope = context.getCurrentScope();
        final MethodBlock mb = new MethodBlock(this, currentScope.getStaticScope()) {
            public IRubyObject callback(final IRubyObject value, final IRubyObject method, final IRubyObject self, final Block block) {
                return RubyMethod.bmcall(value, method, self, block);
            }
        };
        final Block block = MethodBlock.createMethodBlock(context, runtime.getTopSelf(), context.getCurrentScope(), mb);
        try {
            return this.mproc(context, block);
        }
        catch (JumpException.BreakJump bj) {
            return (IRubyObject)bj.getValue();
        }
        catch (JumpException.ReturnJump rj) {
            return (IRubyObject)rj.getValue();
        }
        catch (JumpException.RetryJump rj2) {
            return this.mproc(context, block);
        }
    }
    
    private IRubyObject mproc(final ThreadContext context, final Block block) {
        try {
            context.preMproc();
            return RubyKernel.proc(context, context.getRuntime().getNil(), block);
        }
        finally {
            context.postMproc();
        }
    }
    
    public static IRubyObject bmcall(final IRubyObject blockArg, final IRubyObject arg1, final IRubyObject self, final Block unusedBlock) {
        final ThreadContext context = arg1.getRuntime().getCurrentContext();
        if (blockArg == null) {
            return ((RubyMethod)arg1).call(context, IRubyObject.NULL_ARRAY, Block.NULL_BLOCK);
        }
        if (blockArg instanceof RubyArray) {
            return ((RubyMethod)arg1).call(context, ((RubyArray)blockArg).toJavaArray(), Block.NULL_BLOCK);
        }
        return ((RubyMethod)arg1).call(context, new IRubyObject[] { blockArg }, Block.NULL_BLOCK);
    }
    
    @JRubyMethod
    public RubyUnboundMethod unbind() {
        final RubyUnboundMethod unboundMethod = RubyUnboundMethod.newUnboundMethod(this.implementationModule, this.methodName, this.originModule, this.originName, this.method);
        unboundMethod.infectBy(this);
        return unboundMethod;
    }
    
    @JRubyMethod(name = { "inspect", "to_s" })
    public IRubyObject inspect() {
        final StringBuilder buf = new StringBuilder("#<");
        char delimeter = '#';
        buf.append(this.getMetaClass().getRealClass().getName()).append(": ");
        if (this.implementationModule.isSingleton()) {
            final IRubyObject attached = ((MetaClass)this.implementationModule).getAttached();
            if (this.receiver == null) {
                buf.append(this.implementationModule.inspect().toString());
            }
            else if (this.receiver == attached) {
                buf.append(attached.inspect().toString());
                delimeter = '.';
            }
            else {
                buf.append(this.receiver.inspect().toString());
                buf.append('(').append(attached.inspect().toString()).append(')');
                delimeter = '.';
            }
        }
        else {
            buf.append(this.originModule.getName());
            if (this.implementationModule != this.originModule) {
                buf.append('(').append(this.implementationModule.getName()).append(')');
            }
        }
        buf.append(delimeter).append(this.methodName).append('>');
        final RubyString str = this.getRuntime().newString(buf.toString());
        str.setTaint(this.isTaint());
        return str;
    }
    
    @JRubyMethod(name = { "name" }, compat = CompatVersion.RUBY1_8)
    public IRubyObject name(final ThreadContext context) {
        return context.getRuntime().newString(this.methodName);
    }
    
    @JRubyMethod(name = { "name" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject name19(final ThreadContext context) {
        return context.getRuntime().newSymbol(this.methodName);
    }
    
    public String getMethodName() {
        return this.methodName;
    }
    
    @JRubyMethod(name = { "receiver" })
    public IRubyObject receiver(final ThreadContext context) {
        return this.receiver;
    }
    
    @JRubyMethod(name = { "owner" })
    public IRubyObject owner(final ThreadContext context) {
        return this.implementationModule;
    }
    
    @JRubyMethod(name = { "source_location" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject source_location(final ThreadContext context) {
        final Ruby runtime = context.getRuntime();
        final String filename = this.getFilename();
        if (filename != null) {
            return runtime.newArray(runtime.newString(filename), runtime.newFixnum(this.getLine()));
        }
        return context.getRuntime().getNil();
    }
    
    public String getFilename() {
        final DynamicMethod realMethod = this.method.getRealMethod();
        if (realMethod instanceof PositionAware) {
            final PositionAware poser = (PositionAware)realMethod;
            return poser.getFile();
        }
        return null;
    }
    
    public int getLine() {
        final DynamicMethod realMethod = this.method.getRealMethod();
        if (realMethod instanceof PositionAware) {
            final PositionAware poser = (PositionAware)realMethod;
            return poser.getLine() + 1;
        }
        return -1;
    }
    
    @JRubyMethod(name = { "parameters" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject parameters(final ThreadContext context) {
        return RubyJRuby.MethodExtensions.methodArgs(this);
    }
}
