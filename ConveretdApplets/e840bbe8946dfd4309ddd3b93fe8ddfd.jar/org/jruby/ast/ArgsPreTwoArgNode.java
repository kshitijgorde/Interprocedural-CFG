// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ast;

import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.Ruby;
import org.jruby.lexer.yacc.ISourcePosition;

public class ArgsPreTwoArgNode extends ArgsNode
{
    public ArgsPreTwoArgNode(final ISourcePosition position, final ListNode pre) {
        super(position, pre, null, null, null, null);
    }
    
    public void checkArgCount(final Ruby runtime, final int length) {
        if (length != 2) {
            throw runtime.newArgumentError(length, 2);
        }
    }
    
    public void prepare(final ThreadContext context, final Ruby runtime, final IRubyObject self, final IRubyObject[] args, final Block block) {
        super.prepare(context, runtime, self, args, block);
    }
    
    public void prepare(final ThreadContext context, final Ruby runtime, final IRubyObject self, final IRubyObject arg0, final Block block) {
        context.getCurrentScope().setArgValues(arg0);
    }
    
    public void prepare(final ThreadContext context, final Ruby runtime, final IRubyObject self, final IRubyObject arg0, final IRubyObject arg1, final Block block) {
        context.getCurrentScope().setArgValues(arg0, arg1);
    }
    
    public void prepare(final ThreadContext context, final Ruby runtime, final IRubyObject self, final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2, final Block block) {
        throw runtime.newArgumentError(3, 2);
    }
    
    public void prepare(final ThreadContext context, final Ruby runtime, final IRubyObject self, final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2, final IRubyObject arg3, final Block block) {
        throw runtime.newArgumentError(4, 2);
    }
}
