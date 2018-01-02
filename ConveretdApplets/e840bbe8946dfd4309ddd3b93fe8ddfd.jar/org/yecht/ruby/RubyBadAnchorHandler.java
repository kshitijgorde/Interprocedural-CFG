// 
// Decompiled by Procyon v0.5.30
// 

package org.yecht.ruby;

import org.jruby.runtime.builtin.IRubyObject;
import org.yecht.Node;
import org.yecht.Parser;
import org.jruby.Ruby;
import org.yecht.BadAnchorHandler;

public class RubyBadAnchorHandler implements BadAnchorHandler
{
    private Ruby runtime;
    
    public RubyBadAnchorHandler(final Ruby runtime) {
        this.runtime = runtime;
    }
    
    public Node handle(final Parser p, final String a) {
        final IRubyObject anchor_name = this.runtime.newString(a);
        final IRubyObject nm = this.runtime.newString("name");
        final Node badanc = Node.newMap(nm, anchor_name);
        badanc.type_id = "tag:ruby.yaml.org,2002:object:YAML::Yecht::BadAlias";
        return badanc;
    }
}
