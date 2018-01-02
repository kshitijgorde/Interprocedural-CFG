// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ast;

import java.util.List;
import org.jruby.ast.visitor.NodeVisitor;
import org.jruby.lexer.yacc.Token;

public class LiteralNode extends Node implements InvisibleNode
{
    private String name;
    
    public LiteralNode(final Token token) {
        super(token.getPosition());
        this.name = (String)token.getValue();
    }
    
    public String getName() {
        return this.name;
    }
    
    public Object accept(final NodeVisitor iVisitor) {
        return iVisitor.visitLiteralNode(this);
    }
    
    public List<Node> childNodes() {
        return LiteralNode.EMPTY_LIST;
    }
    
    public NodeType getNodeType() {
        return NodeType.LITERALNODE;
    }
}
