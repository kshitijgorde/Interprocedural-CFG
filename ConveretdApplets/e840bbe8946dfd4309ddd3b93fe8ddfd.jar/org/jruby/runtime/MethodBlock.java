// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.runtime;

import org.jruby.exceptions.JumpException;
import org.jruby.parser.StaticScope;
import org.jruby.RubyModule;
import org.jruby.runtime.backtrace.BacktraceElement;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.RubyMethod;

public abstract class MethodBlock extends ContextAwareBlockBody
{
    private final RubyMethod method;
    private final String filename;
    private final int line;
    
    public static Block createMethodBlock(final ThreadContext context, final IRubyObject self, final DynamicScope dynamicScope, final MethodBlock body) {
        final RubyMethod method = body.method;
        final RubyModule module = method.getMethod().getImplementationClass();
        final Frame frame = new Frame();
        frame.setKlazz(module);
        frame.setName(method.getMethodName());
        frame.setSelf(method.receiver(context));
        frame.setVisibility(method.getMethod().getVisibility());
        final Binding binding = new Binding(frame, module, dynamicScope, new BacktraceElement(module.getName(), method.getMethodName(), body.getFile(), body.getLine()));
        return new Block(body, binding);
    }
    
    public MethodBlock(final RubyMethod method, final StaticScope staticScope) {
        super(staticScope, Arity.createArity((int)method.arity().getLongValue()), 3);
        this.method = method;
        String filename = method.getFilename();
        if (filename == null) {
            filename = "(method)";
        }
        this.filename = filename;
        this.line = method.getLine();
    }
    
    public abstract IRubyObject callback(final IRubyObject p0, final IRubyObject p1, final IRubyObject p2, final Block p3);
    
    public IRubyObject call(final ThreadContext context, final IRubyObject[] args, final Binding binding, final Block.Type type) {
        return this.yield(context, context.getRuntime().newArrayNoCopy(args), null, null, true, binding, type);
    }
    
    protected Frame pre(final ThreadContext context, final RubyModule klass, final Binding binding) {
        return context.preYieldNoScope(binding, klass);
    }
    
    protected void post(final ThreadContext context, final Binding binding, final Visibility visibility, final Frame lastFrame) {
        context.postYieldNoScope(lastFrame);
    }
    
    public IRubyObject yieldSpecific(final ThreadContext context, final Binding binding, final Block.Type type) {
        return this.yield(context, null, binding, type);
    }
    
    public IRubyObject yieldSpecific(final ThreadContext context, final IRubyObject arg0, final Binding binding, final Block.Type type) {
        return this.yield(context, arg0, binding, type);
    }
    
    public IRubyObject yieldSpecific(final ThreadContext context, final IRubyObject arg0, final IRubyObject arg1, final Binding binding, final Block.Type type) {
        return this.yield(context, context.getRuntime().newArrayNoCopyLight(arg0, arg1), null, null, true, binding, type);
    }
    
    public IRubyObject yieldSpecific(final ThreadContext context, final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2, final Binding binding, final Block.Type type) {
        return this.yield(context, context.getRuntime().newArrayNoCopyLight(arg0, arg1, arg2), null, null, true, binding, type);
    }
    
    public IRubyObject yield(final ThreadContext context, final IRubyObject value, final Binding binding, final Block.Type type) {
        return this.yield(context, value, null, null, false, binding, type);
    }
    
    public IRubyObject yield(final ThreadContext context, final IRubyObject value, IRubyObject self, final RubyModule klass, final boolean aValue, final Binding binding, final Block.Type type) {
        if (klass == null) {
            self = binding.getSelf();
            binding.getFrame().setSelf(self);
        }
        final Frame lastFrame = this.pre(context, klass, binding);
        try {
            try {
                return this.callback(value, this.method, self, Block.NULL_BLOCK);
            }
            catch (JumpException.RedoJump rj) {
                context.pollThreadEvents();
            }
            catch (JumpException.BreakJump bj) {
                throw bj;
            }
        }
        catch (JumpException.NextJump nj) {
            return (IRubyObject)nj.getValue();
        }
        finally {
            this.post(context, binding, null, lastFrame);
        }
    }
    
    public String getFile() {
        return this.filename;
    }
    
    public int getLine() {
        return this.line;
    }
    
    public RubyMethod getMethod() {
        return this.method;
    }
}
