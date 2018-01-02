// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ast;

import java.util.List;
import org.jruby.ast.visitor.NodeVisitor;
import org.jruby.lexer.yacc.ISourcePosition;

public class ArgAuxillaryNode extends Node
{
    private String name;
    private int offset;
    
    public ArgAuxillaryNode(final ISourcePosition position, final String name, final int offset) {
        super(position);
        this.name = name;
        this.offset = offset;
    }
    
    public NodeType getNodeType() {
        return NodeType.ARGAUXILIARYNODE;
    }
    
    public int getOffset() {
        return this.offset;
    }
    
    public String getName() {
        return this.name;
    }
    
    public Object accept(final NodeVisitor visitor) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public List<Node> childNodes() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
