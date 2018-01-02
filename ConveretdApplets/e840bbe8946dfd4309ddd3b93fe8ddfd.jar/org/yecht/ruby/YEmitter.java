// 
// Decompiled by Procyon v0.5.30
// 

package org.yecht.ruby;

import org.jruby.RubyModule;
import org.yecht.OutputHandler;
import org.yecht.EmitterHandler;
import org.jruby.RubyClass;
import org.jruby.RubyNumeric;
import org.jruby.runtime.Block;
import org.jruby.runtime.ThreadContext;
import org.jruby.Ruby;
import org.jruby.util.TypeConverter;
import org.jruby.RubyHash;
import org.yecht.Emitter;
import org.jruby.anno.JRubyMethod;
import org.jruby.RubyObject;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ObjectAllocator;

public class YEmitter
{
    public static final ObjectAllocator Allocator;
    
    @JRubyMethod
    public static IRubyObject set_resolver(final IRubyObject self, final IRubyObject resolver) {
        ((RubyObject)self).fastSetInstanceVariable("@resolver", resolver);
        return self;
    }
    
    @JRubyMethod
    public static IRubyObject node_export(final IRubyObject self, final IRubyObject node) {
        return node.callMethod(self.getRuntime().getCurrentContext(), "to_yaml", self);
    }
    
    @JRubyMethod(name = { "initialize", "reset" }, optional = 1)
    public static IRubyObject reset(final IRubyObject self, final IRubyObject[] args) {
        final Ruby runtime = self.getRuntime();
        final ThreadContext ctx = runtime.getCurrentContext();
        final Emitter emitter = (Emitter)self.dataGetStructChecked();
        final Extra bonus = (Extra)emitter.bonus;
        bonus.oid = runtime.getNil();
        bonus.port = runtime.newString("");
        bonus.data = RubyHash.newHash(runtime);
        IRubyObject options = null;
        if (args.length == 1) {
            options = args[0];
            final IRubyObject tmp;
            if (!(tmp = options.checkStringType()).isNil()) {
                bonus.port = tmp;
            }
            else if (options.respondsTo("write")) {
                bonus.port = options;
            }
            else {
                options = TypeConverter.convertToTypeWithCheck(options, runtime.getHash(), "to_hash");
                ((RubyObject)self).fastSetInstanceVariable("@options", options);
            }
        }
        else {
            options = RubyHash.newHash(runtime);
            ((RubyObject)self).fastSetInstanceVariable("@options", options);
        }
        emitter.headless = false;
        ((RubyObject)self).fastSetInstanceVariable("@level", runtime.newFixnum(0));
        ((RubyObject)self).fastSetInstanceVariable("@resolver", runtime.getNil());
        return self;
    }
    
    @JRubyMethod(optional = 1, frame = true)
    public static IRubyObject emit(final IRubyObject self, final IRubyObject[] _oid, final Block proc) {
        final Ruby runtime = self.getRuntime();
        int level = RubyNumeric.fix2int(((RubyObject)self).fastGetInstanceVariable("@level")) + 1;
        ((RubyObject)self).fastSetInstanceVariable("@level", runtime.newFixnum(level));
        final ThreadContext ctx = runtime.getCurrentContext();
        final Emitter emitter = (Emitter)self.dataGetStructChecked();
        final Extra bonus = (Extra)emitter.bonus;
        final IRubyObject oid = (_oid.length == 0) ? runtime.getNil() : _oid[0];
        bonus.oid = oid;
        IRubyObject symple;
        if (!oid.isNil() && bonus.data.callMethod(ctx, "has_key?", oid).isTrue()) {
            symple = ((RubyHash)bonus.data).op_aref(ctx, oid);
        }
        else {
            symple = proc.yield(ctx, ((RubyObject)self).fastGetInstanceVariable("@out"));
        }
        emitter.markNode(symple);
        --level;
        ((RubyObject)self).fastSetInstanceVariable("@level", runtime.newFixnum(level));
        if (level == 0) {
            emitter.emit(symple);
            emitter.flush(0);
            return bonus.port;
        }
        return symple;
    }
    
    static {
        Allocator = new ObjectAllocator() {
            public IRubyObject allocate(final Ruby runtime, final RubyClass klass) {
                final Emitter emitter = new Emitter();
                emitter.bonus = new Extra();
                final IRubyObject pobj = runtime.newData(klass, emitter);
                emitter.handler(new RubyEmitterHandler(runtime));
                emitter.outputHandler(new RubyOutputHandler(runtime));
                ((RubyObject)pobj).fastSetInstanceVariable("@out", ((RubyModule)runtime.getModule("YAML").getConstant("Yecht")).getConstant("Out").callMethod(runtime.getCurrentContext(), "new", pobj));
                return pobj;
            }
        };
    }
    
    public static class Extra
    {
        public IRubyObject oid;
        public IRubyObject data;
        public IRubyObject port;
    }
}
