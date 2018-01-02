// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ast;

import org.jruby.ast.visitor.NodeVisitor;
import org.jruby.lexer.yacc.ISourcePosition;
import org.jruby.ast.types.INameNode;

public class RestArgNode extends ArgumentNode implements INameNode
{
    public RestArgNode(final ISourcePosition position, final String name, final int index) {
        super(position, name, index);
    }
    
    public RestArgNode(final ArgumentNode argNode) {
        this(argNode.getPosition(), argNode.getName(), argNode.getIndex());
    }
    
    public NodeType getNodeType() {
        return NodeType.RESTARG;
    }
    
    public Object accept(final NodeVisitor iVisitor) {
        return iVisitor.visitRestArgNode(this);
    }
}
