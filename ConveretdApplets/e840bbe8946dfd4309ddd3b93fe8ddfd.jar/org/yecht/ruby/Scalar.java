// 
// Decompiled by Procyon v0.5.30
// 

package org.yecht.ruby;

import org.jruby.RubyClass;
import org.jruby.util.ByteList;
import org.yecht.Pointer;
import org.jruby.RubyString;
import org.yecht.ScalarStyle;
import org.yecht.Data;
import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.ThreadContext;
import org.jruby.Ruby;
import org.jruby.RubyObject;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ObjectAllocator;

public class Scalar
{
    public static final ObjectAllocator Allocator;
    
    @JRubyMethod
    public static IRubyObject initialize(final IRubyObject self, final IRubyObject type_id, final IRubyObject val, final IRubyObject style) {
        final Ruby runtime = self.getRuntime();
        final ThreadContext ctx = runtime.getCurrentContext();
        ((RubyObject)self).fastSetInstanceVariable("@kind", ((Node)self).x.scalar);
        self.callMethod(ctx, "type_id=", type_id);
        self.callMethod(ctx, "value=", val);
        self.callMethod(ctx, "style=", style);
        return self;
    }
    
    @JRubyMethod(name = { "style=" })
    public static IRubyObject style_set(final IRubyObject self, final IRubyObject style) {
        final YAMLExtra x = ((Node)self).x;
        final Ruby runtime = self.getRuntime();
        final Data.Str ds = (Data.Str)((org.yecht.Node)self.dataGetStructChecked()).data;
        if (style.isNil()) {
            ds.style = ScalarStyle.None;
        }
        else if (style == x.quote1) {
            ds.style = ScalarStyle.OneQuote;
        }
        else if (style == x.quote2) {
            ds.style = ScalarStyle.TwoQuote;
        }
        else if (style == x.fold) {
            ds.style = ScalarStyle.Fold;
        }
        else if (style == x.literal) {
            ds.style = ScalarStyle.Literal;
        }
        else if (style == x.plain) {
            ds.style = ScalarStyle.Plain;
        }
        ((RubyObject)self).fastSetInstanceVariable("@style", style);
        return self;
    }
    
    @JRubyMethod(name = { "value=" })
    public static IRubyObject value_set(final IRubyObject self, IRubyObject val) {
        final org.yecht.Node node = (org.yecht.Node)self.dataGetStructChecked();
        final Ruby runtime = self.getRuntime();
        final Data.Str ds = (Data.Str)node.data;
        val = val.convertToString();
        final ByteList bl = ((RubyString)val).getByteList();
        final byte[] bss = new byte[bl.realSize];
        System.arraycopy(bl.bytes, bl.begin, bss, 0, bss.length);
        ds.ptr = Pointer.create(bss, 0);
        ds.len = bss.length;
        ds.style = ScalarStyle.None;
        ((RubyObject)self).fastSetInstanceVariable("@value", val);
        return val;
    }
    
    static {
        Allocator = new ObjectAllocator() {
            public IRubyObject allocate(final Ruby runtime, final RubyClass klass) {
                final org.yecht.Node node = org.yecht.Node.allocStr();
                final IRubyObject obj = new Node(runtime, klass, node, (YAMLExtra)runtime.getModule("YAML").dataGetStruct());
                return (IRubyObject)(node.id = obj);
            }
        };
    }
}
