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
import org.jruby.ast.types.INameNode;

public class DAsgnNode extends AssignableNode implements INameNode
{
    private String name;
    private int location;
    
    public DAsgnNode(final ISourcePosition position, final String name, final int location, final Node valueNode) {
        super(position, valueNode);
        this.name = name;
        this.location = location;
    }
    
    public NodeType getNodeType() {
        return NodeType.DASGNNODE;
    }
    
    public Object accept(final NodeVisitor iVisitor) {
        return iVisitor.visitDAsgnNode(this);
    }
    
    public String getName() {
        return this.name;
    }
    
    public int getDepth() {
        return this.location >> 16;
    }
    
    public int getIndex() {
        return this.location & 0xFFFF;
    }
    
    public List<Node> childNodes() {
        return Node.createList(this.getValueNode());
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    public IRubyObject interpret(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock) {
        return context.getCurrentScope().setValue(this.getIndex(), this.getValueNode().interpret(runtime, context, self, aBlock), this.getDepth());
    }
    
    public IRubyObject assign(final Ruby runtime, final ThreadContext context, final IRubyObject self, final IRubyObject value, final Block block, final boolean checkArity) {
        context.getCurrentScope().setValue(this.getIndex(), value, this.getDepth());
        return runtime.getNil();
    }
}
