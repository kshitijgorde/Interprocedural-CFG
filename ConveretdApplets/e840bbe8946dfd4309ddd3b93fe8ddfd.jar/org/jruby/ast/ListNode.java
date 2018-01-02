// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ast;

import org.jruby.ast.visitor.NodeVisitor;
import java.util.Collection;
import java.util.ArrayList;
import org.jruby.lexer.yacc.ISourcePosition;
import java.util.List;

public class ListNode extends Node
{
    private List<Node> list;
    
    public ListNode(final ISourcePosition position, final Node firstNode) {
        this(position);
        (this.list = new ArrayList<Node>(4)).add(firstNode);
    }
    
    public ListNode(final ISourcePosition position) {
        super(position);
        this.list = new ArrayList<Node>(0);
    }
    
    public NodeType getNodeType() {
        return NodeType.LISTNODE;
    }
    
    public ListNode add(final Node node) {
        if (node == null || node == NilImplicitNode.NIL) {
            this.list.add(NilImplicitNode.NIL);
            return this;
        }
        this.list.add(node);
        if (this.getPosition() == null) {
            this.setPosition(node.getPosition());
        }
        return this;
    }
    
    public ListNode prepend(final Node node) {
        if (node == null) {
            return this;
        }
        this.list.add(0, node);
        this.setPosition(node.getPosition());
        return this;
    }
    
    public int size() {
        return this.list.size();
    }
    
    public ListNode addAll(final ListNode other) {
        if (other != null && other.size() > 0) {
            this.list.addAll(other.list);
            if (this.getPosition() == null) {
                this.setPosition(other.getPosition());
            }
        }
        return this;
    }
    
    public ListNode addAll(final Node other) {
        return this.add(other);
    }
    
    public Node getLast() {
        return (this.list.size() == 0) ? null : this.list.get(this.list.size() - 1);
    }
    
    public List<Node> childNodes() {
        return this.list;
    }
    
    public Object accept(final NodeVisitor visitor) {
        throw new RuntimeException("Base class ListNode should never be evaluated");
    }
    
    public Node get(final int idx) {
        return this.list.get(idx);
    }
}
