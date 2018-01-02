// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ast;

import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.Ruby;
import org.jruby.ast.visitor.NodeVisitor;
import org.jruby.lexer.yacc.ISourcePosition;

public class BlockNode extends ListNode
{
    public BlockNode(final ISourcePosition position) {
        super(position);
    }
    
    public NodeType getNodeType() {
        return NodeType.BLOCKNODE;
    }
    
    public Object accept(final NodeVisitor iVisitor) {
        return iVisitor.visitBlockNode(this);
    }
    
    public IRubyObject interpret(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock) {
        IRubyObject result = runtime.getNil();
        for (int size = this.size(), i = 0; i < size; ++i) {
            result = this.get(i).interpret(runtime, context, self, aBlock);
        }
        return result;
    }
}
