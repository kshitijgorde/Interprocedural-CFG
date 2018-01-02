// 
// Decompiled by Procyon v0.5.30
// 

package org.yecht.ruby;

import org.yecht.KindTag;
import org.jruby.runtime.ThreadContext;
import org.yecht.MapPart;
import org.yecht.Data;
import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.RubyClass;
import org.jruby.Ruby;
import org.jruby.runtime.ObjectAllocator;
import org.jruby.RubyObject;

public class Node extends Data
{
    public static final ObjectAllocator Allocator;
    public YAMLExtra x;
    
    public Node(final Ruby runtime, final RubyClass metaClass, final Object data, final YAMLExtra x) {
        super(runtime, metaClass, data);
        this.x = x;
    }
    
    public Node(final RubyClass metaClass, final Object data, final YAMLExtra x) {
        super(metaClass, data);
        this.x = x;
    }
    
    @JRubyMethod
    public static IRubyObject initialize_copy(final IRubyObject copy, final IRubyObject orig) {
        if (copy == orig) {
            return copy;
        }
        if (orig.getClass() != RubyObject.class) {
            throw copy.getRuntime().newTypeError("wrong argument type");
        }
        final org.yecht.Node orig_n = (org.yecht.Node)orig.dataGetStructChecked();
        final org.yecht.Node copy_n = (org.yecht.Node)copy.dataGetStructChecked();
        copy_n.id = orig_n.id;
        copy_n.kind = orig_n.kind;
        copy_n.type_id = orig_n.type_id;
        copy_n.anchor = orig_n.anchor;
        copy_n.data = orig_n.data.copy();
        return copy;
    }
    
    @JRubyMethod(name = { "type_id=" })
    public static IRubyObject set_type_id(final IRubyObject self, final IRubyObject type_id) {
        final org.yecht.Node node = (org.yecht.Node)self.dataGetStructChecked();
        if (!type_id.isNil()) {
            node.type_id = type_id.convertToString().toString();
        }
        ((RubyObject)self).fastSetInstanceVariable("@type_id", type_id);
        return type_id;
    }
    
    @JRubyMethod
    public static IRubyObject transform(final IRubyObject self) {
        final Ruby runtime = self.getRuntime();
        final ThreadContext ctx = runtime.getCurrentContext();
        final org.yecht.Node orig_n = (org.yecht.Node)self.dataGetStructChecked();
        final YAMLExtra x = ((Node)self).x;
        final IRubyObject t = new Node(runtime, self.getType(), null, x);
        org.yecht.Node n = null;
        switch (orig_n.kind) {
            case Map: {
                n = org.yecht.Node.allocMap();
                t.dataWrapStruct(n);
                final org.yecht.Data.Map dm = (org.yecht.Data.Map)orig_n.data;
                for (int i = 0; i < dm.idx; ++i) {
                    final IRubyObject k = ((IRubyObject)orig_n.mapRead(MapPart.Key, i)).callMethod(ctx, "transform");
                    final IRubyObject v = ((IRubyObject)orig_n.mapRead(MapPart.Value, i)).callMethod(ctx, "transform");
                    n.mapAdd(k, v);
                }
                break;
            }
            case Seq: {
                n = org.yecht.Node.allocSeq();
                t.dataWrapStruct(n);
                final org.yecht.Data.Seq ds = (org.yecht.Data.Seq)orig_n.data;
                for (int j = 0; j < ds.idx; ++j) {
                    final IRubyObject itm = ((IRubyObject)orig_n.seqRead(j)).callMethod(ctx, "transform");
                    n.seqAdd(itm);
                }
                break;
            }
            case Str: {
                final org.yecht.Data.Str dss = (org.yecht.Data.Str)orig_n.data;
                n = org.yecht.Node.newStr(dss.ptr, dss.len, dss.style);
                t.dataWrapStruct(n);
                break;
            }
        }
        if (orig_n.type_id != null) {
            n.type_id = orig_n.type_id;
        }
        if (orig_n.anchor != null) {
            n.anchor = orig_n.anchor;
        }
        n.id = t;
        final IRubyObject result = x.DefaultResolver.callMethod(ctx, "node_import", t);
        return result;
    }
    
    static {
        Allocator = new ObjectAllocator() {
            public IRubyObject allocate(final Ruby runtime, final RubyClass klass) {
                return new Node(runtime, klass, null, (YAMLExtra)runtime.getModule("YAML").dataGetStruct());
            }
        };
    }
}
