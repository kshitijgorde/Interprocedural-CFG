// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ast;

import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.Ruby;
import java.util.List;
import org.jruby.ast.visitor.NodeVisitor;
import org.jruby.lexer.yacc.ISourcePosition;
import org.jruby.ast.types.ILiteralNode;

public class ZArrayNode extends Node implements ILiteralNode
{
    public ZArrayNode(final ISourcePosition position) {
        super(position);
    }
    
    public NodeType getNodeType() {
        return NodeType.ZARRAYNODE;
    }
    
    public Object accept(final NodeVisitor iVisitor) {
        return iVisitor.visitZArrayNode(this);
    }
    
    public List<Node> childNodes() {
        return ZArrayNode.EMPTY_LIST;
    }
    
    public IRubyObject interpret(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock) {
        return runtime.newArray();
    }
}
