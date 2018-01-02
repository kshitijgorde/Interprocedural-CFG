// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.exceptions.Unrescuable;
import org.jruby.runtime.Block;
import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.anno.JRubyClass;

@JRubyClass(name = { "Continuation" })
public class RubyContinuation extends RubyObject
{
    private final Continuation continuation;
    private boolean disabled;
    
    public static void createContinuation(final Ruby runtime) {
        final RubyClass cContinuation = runtime.defineClass("Continuation", runtime.getObject(), runtime.getObject().getAllocator());
        cContinuation.index = 37;
        cContinuation.setReifiedClass(RubyContinuation.class);
        cContinuation.defineAnnotatedMethods(RubyContinuation.class);
        cContinuation.getSingletonClass().undefineMethod("new");
        runtime.setContinuation(cContinuation);
    }
    
    public RubyContinuation(final Ruby runtime) {
        super(runtime, runtime.getContinuation());
        this.continuation = new Continuation();
    }
    
    public RubyContinuation(final Ruby runtime, final IRubyObject tag) {
        super(runtime, runtime.getContinuation());
        this.continuation = new Continuation(tag);
    }
    
    public Continuation getContinuation() {
        return this.continuation;
    }
    
    @JRubyMethod(name = { "call", "[]" }, rest = true)
    public IRubyObject call(final ThreadContext context, final IRubyObject[] args) {
        if (this.disabled) {
            throw context.getRuntime().newLocalJumpError(RubyLocalJumpError.Reason.NOREASON, this, "continuations can not be called from outside their scope");
        }
        this.continuation.args = args;
        throw this.continuation;
    }
    
    public IRubyObject enter(final ThreadContext context, final IRubyObject yielded, final Block block) {
        try {
            return block.yield(context, yielded);
        }
        catch (Continuation c) {
            if (c != this.continuation) {
                throw c;
            }
            if (this.continuation.args.length == 0) {
                return context.getRuntime().getNil();
            }
            if (this.continuation.args.length == 1) {
                return this.continuation.args[0];
            }
            return context.getRuntime().newArrayNoCopy(this.continuation.args);
        }
        finally {
            this.disabled = true;
        }
    }
    
    public static class Continuation extends Error implements Unrescuable
    {
        public IRubyObject[] args;
        public final IRubyObject tag;
        
        public Continuation() {
            this.args = IRubyObject.NULL_ARRAY;
            this.tag = null;
        }
        
        public Continuation(final IRubyObject tag) {
            this.args = IRubyObject.NULL_ARRAY;
            this.tag = tag;
        }
        
        public synchronized Throwable fillInStackTrace() {
            return this;
        }
    }
}
