// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ast;

import java.util.List;
import org.jruby.ast.visitor.NodeVisitor;
import org.jruby.lexer.yacc.ISourcePosition;
import org.jruby.ast.types.INameNode;

public class BlockArgNode extends Node implements INameNode
{
    private final int count;
    private String name;
    
    public BlockArgNode(final ISourcePosition position, final int count, final String name) {
        super(position);
        this.count = count;
        this.name = name;
    }
    
    public BlockArgNode(final ArgumentNode argNode) {
        this(argNode.getPosition(), argNode.getIndex(), argNode.getName());
    }
    
    public NodeType getNodeType() {
        return NodeType.BLOCKARGNODE;
    }
    
    public Object accept(final NodeVisitor iVisitor) {
        return iVisitor.visitBlockArgNode(this);
    }
    
    public int getCount() {
        return this.count;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    public List<Node> childNodes() {
        return BlockArgNode.EMPTY_LIST;
    }
}
