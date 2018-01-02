// 
// Decompiled by Procyon v0.5.30
// 

package org.yecht.ruby;

import org.jruby.RubyModule;
import org.jruby.runtime.MethodIndex;
import org.jruby.Ruby;
import org.jruby.runtime.CallSite;
import org.jruby.RubyClass;
import org.jruby.runtime.builtin.IRubyObject;

public class YAMLExtra
{
    public IRubyObject quote1;
    public IRubyObject quote2;
    public IRubyObject fold;
    public IRubyObject literal;
    public IRubyObject plain;
    public IRubyObject map;
    public IRubyObject seq;
    public IRubyObject scalar;
    public IRubyObject inline;
    public RubyClass Scalar;
    public RubyClass Seq;
    public RubyClass Map;
    public IRubyObject DefaultResolver;
    public RubyClass Node;
    public RubyClass MergeKey;
    public RubyClass DefaultKey;
    public final CallSite type_id_set_ScalarAdapter;
    public final CallSite value_set_ScalarAdapter;
    public final CallSite style_set_ScalarAdapter;
    public final CallSite type_id_set_MapAdapter;
    public final CallSite value_set_MapAdapter;
    public final CallSite style_set_MapAdapter;
    public final CallSite type_id_set_SeqAdapter;
    public final CallSite value_set_SeqAdapter;
    public final CallSite style_set_SeqAdapter;
    public final CallSite node_export_EmitterAdapter;
    public final CallSite keys_HashAdapter;
    public Ruby runtime;
    
    public YAMLExtra(final Ruby runtime) {
        this.type_id_set_ScalarAdapter = MethodIndex.getFunctionalCallSite("type_id=");
        this.value_set_ScalarAdapter = MethodIndex.getFunctionalCallSite("value=");
        this.style_set_ScalarAdapter = MethodIndex.getFunctionalCallSite("style=");
        this.type_id_set_MapAdapter = MethodIndex.getFunctionalCallSite("type_id=");
        this.value_set_MapAdapter = MethodIndex.getFunctionalCallSite("value=");
        this.style_set_MapAdapter = MethodIndex.getFunctionalCallSite("style=");
        this.type_id_set_SeqAdapter = MethodIndex.getFunctionalCallSite("type_id=");
        this.value_set_SeqAdapter = MethodIndex.getFunctionalCallSite("value=");
        this.style_set_SeqAdapter = MethodIndex.getFunctionalCallSite("style=");
        this.node_export_EmitterAdapter = MethodIndex.getFunctionalCallSite("node_export");
        this.keys_HashAdapter = MethodIndex.getFunctionalCallSite("keys");
        this.quote1 = runtime.newSymbol("quote1");
        this.quote2 = runtime.newSymbol("quote2");
        this.fold = runtime.newSymbol("fold");
        this.literal = runtime.newSymbol("literal");
        this.plain = runtime.newSymbol("plain");
        this.map = runtime.newSymbol("map");
        this.seq = runtime.newSymbol("seq");
        this.scalar = runtime.newSymbol("scalar");
        this.inline = runtime.newSymbol("inline");
        this.Scalar = (RubyClass)((RubyModule)runtime.getModule("YAML").getConstant("Yecht")).getConstant("Scalar");
        this.Seq = (RubyClass)((RubyModule)runtime.getModule("YAML").getConstant("Yecht")).getConstant("Seq");
        this.Map = (RubyClass)((RubyModule)runtime.getModule("YAML").getConstant("Yecht")).getConstant("Map");
        this.DefaultResolver = ((RubyModule)runtime.getModule("YAML").getConstant("Yecht")).getConstant("DefaultResolver");
        this.Node = (RubyClass)((RubyModule)runtime.getModule("YAML").getConstant("Yecht")).getConstant("Node");
        this.MergeKey = (RubyClass)((RubyModule)runtime.getModule("YAML").getConstant("Yecht")).getConstant("MergeKey");
        this.DefaultKey = (RubyClass)((RubyModule)runtime.getModule("YAML").getConstant("Yecht")).getConstant("DefaultKey");
        this.runtime = runtime;
    }
}
