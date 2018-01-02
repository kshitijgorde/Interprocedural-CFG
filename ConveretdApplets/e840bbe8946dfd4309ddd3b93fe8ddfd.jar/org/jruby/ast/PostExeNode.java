// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ast;

import org.jruby.runtime.SharedScopeBlock;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.Ruby;
import org.jruby.ast.visitor.NodeVisitor;
import org.jruby.parser.StaticScope;
import org.jruby.lexer.yacc.ISourcePosition;

public class PostExeNode extends IterNode
{
    public PostExeNode(final ISourcePosition position, final Node body) {
        super(position, null, null, body);
    }
    
    public NodeType getNodeType() {
        return NodeType.POSTEXENODE;
    }
    
    public Object accept(final NodeVisitor iVisitor) {
        return iVisitor.visitPostExeNode(this);
    }
    
    public IRubyObject interpret(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock) {
        final Block block = SharedScopeBlock.newInterpretedSharedScopeClosure(context, this, context.getCurrentScope(), self);
        runtime.pushExitBlock(runtime.newProc(Block.Type.LAMBDA, block));
        return runtime.getNil();
    }
}
