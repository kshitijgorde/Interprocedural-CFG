// 
// Decompiled by Procyon v0.5.30
// 

package org.yecht.ruby;

import org.jruby.RubyClass;
import org.yecht.MapStyle;
import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.ThreadContext;
import org.jruby.Ruby;
import org.jruby.RubyObject;
import org.jruby.RubyHash;
import org.jruby.RubyArray;
import org.jruby.util.TypeConverter;
import org.yecht.Data;
import org.yecht.Node;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ObjectAllocator;

public class Map
{
    public static final ObjectAllocator Allocator;
    
    @JRubyMethod
    public static IRubyObject initialize(final IRubyObject self, final IRubyObject type_id, final IRubyObject val, final IRubyObject style) {
        final Node node = (Node)self.dataGetStructChecked();
        final Ruby runtime = self.getRuntime();
        final ThreadContext ctx = runtime.getCurrentContext();
        final Data.Map ds = (Data.Map)node.data;
        if (!val.isNil()) {
            final IRubyObject hsh = TypeConverter.convertToTypeWithCheck(val, runtime.getHash(), "to_hash");
            if (hsh.isNil()) {
                throw runtime.newTypeError("wrong argument type");
            }
            final IRubyObject keys = hsh.callMethod(ctx, "keys");
            for (int i = 0; i < ((RubyArray)keys).getLength(); ++i) {
                final IRubyObject key = ((RubyArray)keys).entry(i);
                node.mapAdd(key, ((RubyHash)hsh).op_aref(ctx, key));
            }
        }
        ((RubyObject)self).fastSetInstanceVariable("@kind", ((org.yecht.ruby.Node)self).x.seq);
        self.callMethod(ctx, "type_id=", type_id);
        self.callMethod(ctx, "value=", val);
        self.callMethod(ctx, "style=", style);
        return self;
    }
    
    @JRubyMethod(name = { "value=" })
    public static IRubyObject value_set(final IRubyObject self, final IRubyObject val) {
        final Node node = (Node)self.dataGetStructChecked();
        final Ruby runtime = self.getRuntime();
        final ThreadContext ctx = runtime.getCurrentContext();
        if (!val.isNil()) {
            final IRubyObject hsh = TypeConverter.convertToTypeWithCheck(val, runtime.getHash(), "to_hash");
            if (hsh.isNil()) {
                throw runtime.newTypeError("wrong argument type");
            }
            node.mapEmpty();
            final IRubyObject keys = hsh.callMethod(ctx, "keys");
            for (int i = 0; i < ((RubyArray)keys).getLength(); ++i) {
                final IRubyObject key = ((RubyArray)keys).entry(i);
                node.mapAdd(key, ((RubyHash)hsh).op_aref(ctx, key));
            }
        }
        ((RubyObject)self).fastSetInstanceVariable("@value", val);
        return val;
    }
    
    @JRubyMethod
    public static IRubyObject add(final IRubyObject self, IRubyObject key, IRubyObject val) {
        final IRubyObject emitter = ((RubyObject)self).fastGetInstanceVariable("@emitter");
        final Node node = (Node)self.dataGetStructChecked();
        if (emitter.respondsTo("node_export")) {
            key = emitter.callMethod(self.getRuntime().getCurrentContext(), "node_export", key);
            val = emitter.callMethod(self.getRuntime().getCurrentContext(), "node_export", val);
        }
        node.mapAdd(key, val);
        ((RubyHash)((RubyObject)self).fastGetInstanceVariable("@value")).fastASet(key, val);
        return self;
    }
    
    @JRubyMethod(name = { "style=" })
    public static IRubyObject style_set(final IRubyObject self, final IRubyObject style) {
        final Node node = (Node)self.dataGetStructChecked();
        final Ruby runtime = self.getRuntime();
        final Data.Map ds = (Data.Map)node.data;
        if (style == ((org.yecht.ruby.Node)self).x.inline) {
            ds.style = MapStyle.Inline;
        }
        else {
            ds.style = MapStyle.None;
        }
        ((RubyObject)self).fastSetInstanceVariable("@style", style);
        return self;
    }
    
    static {
        Allocator = new ObjectAllocator() {
            public IRubyObject allocate(final Ruby runtime, final RubyClass klass) {
                final Node node = Node.allocMap();
                final IRubyObject obj = new org.yecht.ruby.Node(runtime, klass, node, (YAMLExtra)runtime.getModule("YAML").dataGetStruct());
                return (IRubyObject)(node.id = obj);
            }
        };
    }
}
