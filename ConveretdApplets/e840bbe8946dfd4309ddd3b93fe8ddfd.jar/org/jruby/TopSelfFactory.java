// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.runtime.Arity;
import org.jruby.runtime.Block;
import org.jruby.runtime.callback.Callback;
import org.jruby.runtime.builtin.IRubyObject;

public final class TopSelfFactory
{
    public static IRubyObject createTopSelf(final Ruby runtime) {
        final IRubyObject topSelf = new RubyObject(runtime, runtime.getObject());
        topSelf.getSingletonClass().defineFastMethod("to_s", new Callback() {
            public IRubyObject execute(final IRubyObject recv, final IRubyObject[] args, final Block block) {
                return runtime.newString("main");
            }
            
            public Arity getArity() {
                return Arity.noArguments();
            }
        });
        topSelf.getSingletonClass().defineFastPrivateMethod("include", new Callback() {
            public IRubyObject execute(final IRubyObject recv, final IRubyObject[] args, final Block block) {
                runtime.secure(4);
                return runtime.getObject().include(args);
            }
            
            public Arity getArity() {
                return Arity.optional();
            }
        });
        topSelf.getSingletonClass().defineFastPrivateMethod("public", new Callback() {
            public IRubyObject execute(final IRubyObject recv, final IRubyObject[] args, final Block unusedBlock) {
                return runtime.getObject().rbPublic(recv.getRuntime().getCurrentContext(), args);
            }
            
            public Arity getArity() {
                return Arity.optional();
            }
        });
        topSelf.getSingletonClass().defineFastPrivateMethod("private", new Callback() {
            public IRubyObject execute(final IRubyObject recv, final IRubyObject[] args, final Block unusedBlock) {
                return runtime.getObject().rbPrivate(recv.getRuntime().getCurrentContext(), args);
            }
            
            public Arity getArity() {
                return Arity.optional();
            }
        });
        return topSelf;
    }
}
