// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ast;

import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.Ruby;
import org.jruby.runtime.ThreadContext;
import org.jruby.lexer.yacc.ISourcePosition;

public class ArgsNoArgNode extends ArgsNode
{
    public ArgsNoArgNode(final ISourcePosition position) {
        super(position, null, null, null, null, null);
    }
    
    public void prepare(final ThreadContext context, final Ruby runtime, final IRubyObject self, final IRubyObject[] args, final Block block) {
    }
}
