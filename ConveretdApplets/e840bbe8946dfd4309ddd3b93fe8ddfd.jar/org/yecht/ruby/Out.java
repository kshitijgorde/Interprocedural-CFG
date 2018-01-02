// 
// Decompiled by Procyon v0.5.30
// 

package org.yecht.ruby;

import org.jruby.RubyArray;
import org.jruby.runtime.ThreadContext;
import org.jruby.Ruby;
import org.jruby.RubyModule;
import org.jruby.runtime.Block;
import org.jruby.anno.JRubyMethod;
import org.jruby.RubyHash;
import org.jruby.RubyObject;
import org.yecht.Emitter;
import org.jruby.runtime.builtin.IRubyObject;

public class Out
{
    public static void outMark(final IRubyObject emitter, final IRubyObject node) {
        final Emitter emitterPtr = (Emitter)emitter.dataGetStructChecked();
        final YEmitter.Extra bonus = (YEmitter.Extra)emitterPtr.bonus;
        ((RubyObject)node).fastSetInstanceVariable("@emitter", emitter);
        if (!bonus.oid.isNil()) {
            ((RubyHash)bonus.data).fastASet(bonus.oid, node);
        }
    }
    
    @JRubyMethod
    public static IRubyObject initialize(final IRubyObject self, final IRubyObject emitter) {
        ((RubyObject)self).fastSetInstanceVariable("@emitter", emitter);
        return self;
    }
    
    @JRubyMethod(required = 1, optional = 1, frame = true)
    public static IRubyObject map(final IRubyObject self, final IRubyObject[] args, final Block block) {
        final Ruby runtime = self.getRuntime();
        final ThreadContext ctx = runtime.getCurrentContext();
        final IRubyObject type_id = args[0];
        final IRubyObject style = (args.length == 1) ? runtime.getNil() : args[1];
        final IRubyObject map = ((RubyModule)runtime.getModule("YAML").getConstant("Yecht")).getConstant("Map").callMethod(ctx, "new", new IRubyObject[] { type_id, RubyHash.newHash(runtime), style });
        outMark(((RubyObject)self).fastGetInstanceVariable("@emitter"), map);
        block.yield(ctx, map);
        return map;
    }
    
    @JRubyMethod(required = 1, optional = 1, frame = true)
    public static IRubyObject seq(final IRubyObject self, final IRubyObject[] args, final Block block) {
        final Ruby runtime = self.getRuntime();
        final ThreadContext ctx = runtime.getCurrentContext();
        final IRubyObject type_id = args[0];
        final IRubyObject style = (args.length == 1) ? runtime.getNil() : args[1];
        final IRubyObject seq = ((RubyModule)runtime.getModule("YAML").getConstant("Yecht")).getConstant("Seq").callMethod(ctx, "new", new IRubyObject[] { type_id, RubyArray.newArray(runtime), style });
        outMark(((RubyObject)self).fastGetInstanceVariable("@emitter"), seq);
        block.yield(ctx, seq);
        return seq;
    }
    
    @JRubyMethod(required = 2, optional = 1, frame = true)
    public static IRubyObject scalar(final IRubyObject self, final IRubyObject[] args, final Block block) {
        final Ruby runtime = self.getRuntime();
        final ThreadContext ctx = runtime.getCurrentContext();
        final IRubyObject type_id = args[0];
        final IRubyObject str = args[1];
        final IRubyObject style = (args.length == 2) ? runtime.getNil() : args[2];
        final IRubyObject scalar = ((RubyModule)runtime.getModule("YAML").getConstant("Yecht")).getConstant("Scalar").callMethod(ctx, "new", new IRubyObject[] { type_id, str, style });
        outMark(((RubyObject)self).fastGetInstanceVariable("@emitter"), scalar);
        return scalar;
    }
}
