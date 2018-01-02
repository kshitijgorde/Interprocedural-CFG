// 
// Decompiled by Procyon v0.5.30
// 

package org.yecht.ruby;

import org.yecht.ScalarStyle;
import org.yecht.KindTag;
import org.yecht.MapStyle;
import org.jruby.RubyHash;
import org.jruby.RubyObject;
import org.yecht.SeqStyle;
import org.jruby.RubyArray;
import org.jruby.RubyString;
import org.yecht.Data;
import org.jruby.RubyModule;
import org.jruby.runtime.MethodIndex;
import org.jruby.runtime.CallSite;
import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.ThreadContext;
import org.jruby.Ruby;
import org.yecht.Node;
import org.jruby.runtime.builtin.IRubyObject;

public class GenericResolver
{
    @JRubyMethod
    public static IRubyObject node_import(final IRubyObject self, final IRubyObject node) {
        final Ruby runtime = self.getRuntime();
        final ThreadContext ctx = runtime.getCurrentContext();
        final Node n = (Node)node.dataGetStructChecked();
        IRubyObject t = runtime.getNil();
        final Extra x = (Extra)self.dataGetStruct();
        if (n.type_id != null) {
            t = runtime.newString(n.type_id);
        }
        switch (n.kind) {
            case Str: {
                return x.scalar(t, n, ctx);
            }
            case Seq: {
                return x.sequence(t, n, ctx);
            }
            case Map: {
                return x.mapping(t, n, ctx);
            }
            default: {
                return runtime.getNil();
            }
        }
    }
    
    public static class Extra
    {
        public IRubyObject quote1;
        public IRubyObject quote2;
        public IRubyObject fold;
        public IRubyObject literal;
        public IRubyObject plain;
        public IRubyObject map;
        public IRubyObject seq;
        public IRubyObject inline;
        public IRubyObject Scalar;
        public IRubyObject Seq;
        public IRubyObject Map;
        public Ruby runtime;
        private final CallSite newScalarAdapter;
        private final CallSite newSeqAdapter;
        private final CallSite newMapAdapter;
        
        public Extra(final Ruby runtime) {
            this.newScalarAdapter = MethodIndex.getFunctionalCallSite("new");
            this.newSeqAdapter = MethodIndex.getFunctionalCallSite("new");
            this.newMapAdapter = MethodIndex.getFunctionalCallSite("new");
            this.quote1 = runtime.newSymbol("quote1");
            this.quote2 = runtime.newSymbol("quote2");
            this.fold = runtime.newSymbol("fold");
            this.literal = runtime.newSymbol("literal");
            this.plain = runtime.newSymbol("plain");
            this.map = runtime.newSymbol("map");
            this.seq = runtime.newSymbol("seq");
            this.inline = runtime.newSymbol("inline");
            this.Scalar = ((RubyModule)runtime.getModule("YAML").getConstant("Yecht")).getConstant("Scalar");
            this.Seq = ((RubyModule)runtime.getModule("YAML").getConstant("Yecht")).getConstant("Seq");
            this.Map = ((RubyModule)runtime.getModule("YAML").getConstant("Yecht")).getConstant("Map");
            this.runtime = runtime;
        }
        
        public IRubyObject scalar(final IRubyObject t, final Node n, final ThreadContext ctx) {
            final Data.Str dd = (Data.Str)n.data;
            final IRubyObject v = RubyString.newStringShared(this.runtime, dd.ptr.buffer, dd.ptr.start, dd.len);
            IRubyObject style = this.runtime.getNil();
            switch (dd.style) {
                case OneQuote: {
                    style = this.quote1;
                    break;
                }
                case TwoQuote: {
                    style = this.quote2;
                    break;
                }
                case Fold: {
                    style = this.fold;
                    break;
                }
                case Literal: {
                    style = this.literal;
                    break;
                }
                case Plain: {
                    style = this.plain;
                    break;
                }
            }
            return this.newScalarAdapter.call(ctx, this.Scalar, this.Scalar, t, v, style);
        }
        
        public IRubyObject sequence(final IRubyObject t, final Node n, final ThreadContext ctx) {
            final Data.Seq ds = (Data.Seq)n.data;
            final Object[] items = ds.items;
            final IRubyObject v = RubyArray.newArray(this.runtime, ds.idx);
            for (int i = 0; i < ds.idx; ++i) {
                ((RubyArray)v).store(i, (IRubyObject)items[i]);
            }
            IRubyObject style = this.runtime.getNil();
            if (((Data.Seq)n.data).style == SeqStyle.Inline) {
                style = this.inline;
            }
            final IRubyObject obj = this.newSeqAdapter.call(ctx, this.Seq, this.Seq, t, v, style);
            ((RubyObject)obj).fastSetInstanceVariable("@kind", this.seq);
            return obj;
        }
        
        public IRubyObject mapping(final IRubyObject t, final Node n, final ThreadContext ctx) {
            final Data.Map dm = (Data.Map)n.data;
            final Object[] keys = dm.keys;
            final Object[] vals = dm.values;
            final IRubyObject v = RubyHash.newHash(this.runtime);
            for (int i = 0; i < dm.idx; ++i) {
                final IRubyObject k3 = (IRubyObject)keys[i];
                IRubyObject v2 = (IRubyObject)vals[i];
                if (null == v2) {
                    v2 = this.runtime.getNil();
                }
                ((RubyHash)v).fastASet(k3, v2);
            }
            IRubyObject style = this.runtime.getNil();
            if (((Data.Map)n.data).style == MapStyle.Inline) {
                style = this.inline;
            }
            final IRubyObject obj = this.newMapAdapter.call(ctx, this.Map, this.Map, t, v, style);
            ((RubyObject)obj).fastSetInstanceVariable("@kind", this.map);
            return obj;
        }
    }
}
