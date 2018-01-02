// 
// Decompiled by Procyon v0.5.30
// 

package org.yecht.ruby;

import org.jruby.RubyClass;
import org.yecht.SeqStyle;
import org.jruby.RubyArray;
import org.yecht.Data;
import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.ThreadContext;
import org.jruby.Ruby;
import org.jruby.RubyObject;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ObjectAllocator;

public class Seq
{
    public static final ObjectAllocator Allocator;
    
    @JRubyMethod
    public static IRubyObject initialize(final IRubyObject self, final IRubyObject type_id, final IRubyObject val, final IRubyObject style) {
        final Ruby runtime = self.getRuntime();
        final ThreadContext ctx = runtime.getCurrentContext();
        ((RubyObject)self).fastSetInstanceVariable("@kind", ((Node)self).x.seq);
        self.callMethod(ctx, "type_id=", type_id);
        self.callMethod(ctx, "value=", val);
        self.callMethod(ctx, "style=", style);
        return self;
    }
    
    @JRubyMethod(name = { "value=" })
    public static IRubyObject value_set(final IRubyObject self, IRubyObject val) {
        final org.yecht.Node node = (org.yecht.Node)self.dataGetStructChecked();
        final Ruby runtime = self.getRuntime();
        val = val.checkArrayType();
        if (!val.isNil()) {
            node.seqEmpty();
            final Data.Seq ds = (Data.Seq)node.data;
            for (int i = 0; i < ((RubyArray)val).getLength(); ++i) {
                node.seqAdd(((RubyArray)val).entry(i));
            }
        }
        ((RubyObject)self).fastSetInstanceVariable("@value", val);
        return val;
    }
    
    @JRubyMethod(name = { "style=" })
    public static IRubyObject style_set(final IRubyObject self, final IRubyObject style) {
        final org.yecht.Node node = (org.yecht.Node)self.dataGetStructChecked();
        final Ruby runtime = self.getRuntime();
        final Data.Seq ds = (Data.Seq)node.data;
        if (style == runtime.newSymbol("inline")) {
            ds.style = SeqStyle.Inline;
        }
        else {
            ds.style = SeqStyle.None;
        }
        ((RubyObject)self).fastSetInstanceVariable("@style", style);
        return self;
    }
    
    @JRubyMethod
    public static IRubyObject add(final IRubyObject self, IRubyObject val) {
        final IRubyObject emitter = ((RubyObject)self).fastGetInstanceVariable("@emitter");
        final org.yecht.Node node = (org.yecht.Node)self.dataGetStructChecked();
        if (emitter.respondsTo("node_export")) {
            val = emitter.callMethod(self.getRuntime().getCurrentContext(), "node_export", val);
        }
        node.seqAdd(val);
        ((RubyArray)((RubyObject)self).fastGetInstanceVariable("@value")).append(val);
        return self;
    }
    
    static {
        Allocator = new ObjectAllocator() {
            public IRubyObject allocate(final Ruby runtime, final RubyClass klass) {
                final org.yecht.Node node = org.yecht.Node.allocSeq();
                final IRubyObject obj = new Node(runtime, klass, node, (YAMLExtra)runtime.getModule("YAML").dataGetStruct());
                return (IRubyObject)(node.id = obj);
            }
        };
    }
}
