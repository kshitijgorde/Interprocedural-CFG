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
import org.jruby.ast.types.ILiteralNode;

public class DXStrNode extends DNode implements ILiteralNode
{
    public DXStrNode(final ISourcePosition position, final DStrNode node) {
        super(position);
        this.addAll(node);
    }
    
    public DXStrNode(final ISourcePosition position) {
        super(position);
    }
    
    public NodeType getNodeType() {
        return NodeType.DXSTRNODE;
    }
    
    public Object accept(final NodeVisitor iVisitor) {
        return iVisitor.visitDXStrNode(this);
    }
    
    public IRubyObject interpret(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock) {
        return self.callMethod(context, "`", super.interpret(runtime, context, self, aBlock));
    }
}
