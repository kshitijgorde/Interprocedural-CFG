// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ast;

import java.util.List;
import org.jruby.ast.visitor.NodeVisitor;
import org.jruby.lexer.yacc.ISourcePosition;
import org.jruby.ast.types.INameNode;

public class ArgumentNode extends Node implements INameNode
{
    private String identifier;
    private int location;
    
    public ArgumentNode(final ISourcePosition position, final String identifier) {
        super(position);
        this.identifier = identifier;
    }
    
    public ArgumentNode(final ISourcePosition position, final String identifier, final int location) {
        this(position, identifier);
        this.location = location;
    }
    
    public NodeType getNodeType() {
        return NodeType.ARGUMENTNODE;
    }
    
    public Object accept(final NodeVisitor visitor) {
        throw new RuntimeException("ArgumentNode should never be evaluated");
    }
    
    public int getDepth() {
        return this.location >> 16;
    }
    
    public int getIndex() {
        return this.location & 0xFFFF;
    }
    
    public String getName() {
        return this.identifier;
    }
    
    public void setName(final String name) {
        this.identifier = name;
    }
    
    public List<Node> childNodes() {
        return ArgumentNode.EMPTY_LIST;
    }
}
