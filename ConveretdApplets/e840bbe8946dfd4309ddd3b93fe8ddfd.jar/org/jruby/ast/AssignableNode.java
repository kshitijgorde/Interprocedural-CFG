// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ast;

import org.jruby.util.ByteList;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.Ruby;
import org.jruby.runtime.Arity;
import org.jruby.lexer.yacc.ISourcePosition;
import org.jruby.ast.types.IArityNode;

public abstract class AssignableNode extends Node implements IArityNode
{
    private Node valueNode;
    
    public AssignableNode(final ISourcePosition position) {
        super(position);
    }
    
    public AssignableNode(final ISourcePosition position, final Node valueNode) {
        super(position);
        assert valueNode != null : "valueNode is not null";
        this.valueNode = valueNode;
    }
    
    public Node getValueNode() {
        return this.valueNode;
    }
    
    public void setValueNode(final Node valueNode) {
        this.valueNode = ((valueNode == null) ? NilImplicitNode.NIL : valueNode);
    }
    
    public Arity getArity() {
        return Arity.singleArgument();
    }
    
    public ByteList definition(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock) {
        return AssignableNode.ASSIGNMENT_BYTELIST;
    }
}
