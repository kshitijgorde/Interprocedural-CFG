// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ast;

import org.jruby.util.ByteList;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.Ruby;
import java.util.List;
import org.jruby.ast.visitor.NodeVisitor;
import org.jruby.lexer.yacc.ISourcePosition;
import org.jruby.ast.types.INameNode;

public class DVarNode extends Node implements INameNode
{
    private String name;
    private int location;
    
    public DVarNode(final ISourcePosition position, final int location, final String name) {
        super(position);
        this.location = location;
        this.name = name;
    }
    
    public NodeType getNodeType() {
        return NodeType.DVARNODE;
    }
    
    public Object accept(final NodeVisitor iVisitor) {
        return iVisitor.visitDVarNode(this);
    }
    
    public int getDepth() {
        return this.location >> 16;
    }
    
    public int getIndex() {
        return this.location & 0xFFFF;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    public List<Node> childNodes() {
        return DVarNode.EMPTY_LIST;
    }
    
    public IRubyObject interpret(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock) {
        final IRubyObject obj = context.getCurrentScope().getValue(this.getIndex(), this.getDepth());
        return (obj == null) ? runtime.getNil() : obj;
    }
    
    public ByteList definition(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock) {
        ByteList definition = DVarNode.LOCAL_VARIABLE_BYTELIST;
        if (!context.getRuntime().is1_9()) {
            definition = DVarNode.LOCAL_VARIABLE_IN_BLOCK_BYTELIST;
        }
        return definition;
    }
}
