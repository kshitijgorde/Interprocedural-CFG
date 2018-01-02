// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.ObjectAllocator;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.anno.JRubyClass;

@JRubyClass(name = { "UnboundMethod" }, parent = "Method")
public class RubyUnboundMethod extends RubyMethod
{
    protected RubyUnboundMethod(final Ruby runtime) {
        super(runtime, runtime.getUnboundMethod());
    }
    
    public static RubyUnboundMethod newUnboundMethod(final RubyModule implementationModule, final String methodName, final RubyModule originModule, final String originName, final DynamicMethod method) {
        final RubyUnboundMethod newMethod = new RubyUnboundMethod(implementationModule.getRuntime());
        newMethod.implementationModule = implementationModule;
        newMethod.methodName = methodName;
        newMethod.originModule = originModule;
        newMethod.originName = originName;
        newMethod.method = method;
        return newMethod;
    }
    
    public static RubyClass defineUnboundMethodClass(final Ruby runtime) {
        final RubyClass newClass = runtime.defineClass("UnboundMethod", runtime.getMethod(), ObjectAllocator.NOT_ALLOCATABLE_ALLOCATOR);
        runtime.setUnboundMethod(newClass);
        newClass.index = 36;
        newClass.setReifiedClass(RubyUnboundMethod.class);
        newClass.defineAnnotatedMethods(RubyUnboundMethod.class);
        return newClass;
    }
    
    @JRubyMethod(name = { "call", "[]" }, rest = true)
    public IRubyObject call(final ThreadContext context, final IRubyObject[] args, final Block block) {
        throw context.getRuntime().newTypeError("you cannot call unbound method; bind first");
    }
    
    @JRubyMethod
    public RubyUnboundMethod unbind() {
        return this;
    }
    
    @JRubyMethod
    public RubyMethod bind(final ThreadContext context, final IRubyObject aReceiver) {
        final RubyClass receiverClass = aReceiver.getMetaClass();
        if (!this.originModule.isInstance(aReceiver)) {
            if (this.originModule instanceof MetaClass) {
                throw context.getRuntime().newTypeError("singleton method called for a different object");
            }
            if (receiverClass instanceof MetaClass && receiverClass.getMethods().containsKey(this.originName)) {
                throw context.getRuntime().newTypeError("method `" + this.originName + "' overridden");
            }
            if (this.originModule.isModule()) {
                if (this.originModule.isInstance(aReceiver)) {
                    return RubyMethod.newMethod(this.implementationModule, this.methodName, receiverClass, this.originName, this.method, aReceiver);
                }
            }
            else if (aReceiver.getType() == this.originModule) {
                return RubyMethod.newMethod(this.implementationModule, this.methodName, receiverClass, this.originName, this.method, aReceiver);
            }
            throw context.getRuntime().newTypeError("bind argument must be an instance of " + this.originModule.getName());
        }
        return RubyMethod.newMethod(this.implementationModule, this.methodName, receiverClass, this.originName, this.method, aReceiver);
    }
    
    @JRubyMethod(name = { "clone" })
    public RubyMethod rbClone() {
        return newUnboundMethod(this.implementationModule, this.methodName, this.originModule, this.originName, this.method);
    }
    
    @JRubyMethod
    public IRubyObject to_proc(final ThreadContext context, final Block unusedBlock) {
        return super.to_proc(context, unusedBlock);
    }
    
    @JRubyMethod(compat = CompatVersion.RUBY1_8)
    public IRubyObject name(final ThreadContext context) {
        return context.getRuntime().newString(this.methodName);
    }
    
    @JRubyMethod(name = { "name" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject name19(final ThreadContext context) {
        return context.getRuntime().newSymbol(this.methodName);
    }
    
    @JRubyMethod(name = { "owner" })
    public IRubyObject owner(final ThreadContext context) {
        return this.implementationModule;
    }
}
