// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ast;

import org.jruby.util.ByteList;
import org.jruby.RubyModule;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.Ruby;
import org.jruby.lexer.yacc.ISourcePosition;

public class Colon2ImplicitNode extends Colon2Node
{
    public Colon2ImplicitNode(final ISourcePosition position, final String name) {
        super(position, null, name);
    }
    
    public RubyModule getEnclosingModule(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock) {
        return context.getCurrentScope().getStaticScope().getModule();
    }
    
    public IRubyObject interpret(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock) {
        assert false : "interpret cannot ever happen for Colon2ImplicitNode";
        return null;
    }
    
    public ByteList definition(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock) {
        assert false : "definition should not ever happen for Colon2ImplicitNode";
        return null;
    }
}
