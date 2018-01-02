// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.runtime.Block;
import org.jruby.runtime.Visibility;
import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ObjectAllocator;
import org.jruby.runtime.Binding;
import org.jruby.anno.JRubyClass;

@JRubyClass(name = { "Binding" })
public class RubyBinding extends RubyObject
{
    private Binding binding;
    private static ObjectAllocator BINDING_ALLOCATOR;
    
    public RubyBinding(final Ruby runtime, final RubyClass rubyClass, final Binding binding) {
        super(runtime, rubyClass);
        this.binding = binding;
    }
    
    private RubyBinding(final Ruby runtime, final RubyClass rubyClass) {
        super(runtime, rubyClass);
    }
    
    public static RubyClass createBindingClass(final Ruby runtime) {
        final RubyClass bindingClass = runtime.defineClass("Binding", runtime.getObject(), RubyBinding.BINDING_ALLOCATOR);
        runtime.setBinding(bindingClass);
        bindingClass.index = 32;
        bindingClass.setReifiedClass(RubyBinding.class);
        bindingClass.defineAnnotatedMethods(RubyBinding.class);
        bindingClass.getSingletonClass().undefineMethod("new");
        return bindingClass;
    }
    
    public Binding getBinding() {
        return this.binding;
    }
    
    public static RubyBinding newBinding(final Ruby runtime, final Binding binding) {
        return new RubyBinding(runtime, runtime.getBinding(), binding);
    }
    
    @Deprecated
    public static RubyBinding newBinding(final Ruby runtime) {
        return newBinding(runtime, runtime.getCurrentContext().currentBinding());
    }
    
    @Deprecated
    public static RubyBinding newBinding(final Ruby runtime, final IRubyObject self) {
        return newBinding(runtime, runtime.getCurrentContext().currentBinding(self));
    }
    
    @JRubyMethod(name = { "initialize" }, visibility = Visibility.PRIVATE)
    public IRubyObject initialize(final ThreadContext context) {
        this.binding = context.currentBinding();
        return this;
    }
    
    @JRubyMethod(name = { "initialize_copy" }, required = 1, visibility = Visibility.PRIVATE)
    public IRubyObject initialize_copy(final IRubyObject other) {
        final RubyBinding otherBinding = (RubyBinding)other;
        this.binding = otherBinding.binding;
        return this;
    }
    
    @JRubyMethod(name = { "eval" }, required = 1, optional = 2)
    public IRubyObject eval(final ThreadContext context, final IRubyObject[] args) {
        final IRubyObject[] newArgs = new IRubyObject[args.length + 1];
        newArgs[0] = args[0];
        newArgs[1] = this;
        if (args.length > 1) {
            newArgs[2] = args[1];
            if (args.length > 2) {
                newArgs[3] = args[2];
            }
        }
        return RubyKernel.eval(context, this, newArgs, Block.NULL_BLOCK);
    }
    
    static {
        RubyBinding.BINDING_ALLOCATOR = new ObjectAllocator() {
            public IRubyObject allocate(final Ruby runtime, final RubyClass klass) {
                final RubyBinding instance = new RubyBinding(runtime, klass, (RubyBinding$1)null);
                return instance;
            }
        };
    }
}
