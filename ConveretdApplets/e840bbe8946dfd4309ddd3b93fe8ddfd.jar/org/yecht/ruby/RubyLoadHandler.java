// 
// Decompiled by Procyon v0.5.30
// 

package org.yecht.ruby;

import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.RubyHash;
import org.jruby.RubyObject;
import org.yecht.Node;
import org.yecht.Parser;
import org.jruby.runtime.MethodIndex;
import org.jruby.runtime.CallSite;
import org.jruby.Ruby;
import org.yecht.NodeHandler;

public class RubyLoadHandler implements NodeHandler
{
    private Ruby runtime;
    private YAMLExtra x;
    private final CallSite node_importAdapter;
    
    public RubyLoadHandler(final Ruby runtime, final YAMLExtra x) {
        this.node_importAdapter = MethodIndex.getFunctionalCallSite("node_import");
        this.runtime = runtime;
        this.x = x;
    }
    
    public Object handle(final Parser p, final Node n) {
        final YParser.Extra bonus = (YParser.Extra)p.bonus;
        IRubyObject resolver = bonus.resolver;
        if (resolver.isNil()) {
            resolver = this.x.DefaultResolver;
        }
        final IRubyObject _n = new org.yecht.ruby.Node(this.runtime, this.x.Node, n, this.x);
        final IRubyObject obj = this.node_importAdapter.call(this.runtime.getCurrentContext(), resolver, resolver, _n);
        if (n.id != null && !obj.isNil()) {
            if (n.id instanceof PossibleLinkNode) {
                ((PossibleLinkNode)n.id).replaceLinks(obj);
            }
            n.id = obj;
        }
        if (bonus.taint) {
            ((RubyObject)obj).taint(this.runtime.getCurrentContext());
        }
        if (bonus.proc != null) {
            bonus.proc.callMethod(this.runtime.getCurrentContext(), "call", obj);
        }
        ((RubyHash)bonus.data).fastASet(((RubyHash)bonus.data).rb_size(), obj);
        return obj;
    }
}
