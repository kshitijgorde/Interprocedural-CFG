// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ast;

import java.util.List;

public class TypedArgumentNode extends ArgumentNode
{
    private Node typeNode;
    
    public TypedArgumentNode(final ArgumentNode argNode, final Node typeNode) {
        super(argNode.getPosition(), argNode.getName(), argNode.getIndex());
        this.typeNode = typeNode;
    }
    
    public Node getTypeNode() {
        return this.typeNode;
    }
    
    public List<Node> childNodes() {
        return Node.createList(this.typeNode);
    }
}
