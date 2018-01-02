// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ast;

import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.Ruby;
import org.jruby.ast.visitor.NodeVisitor;
import org.jruby.lexer.yacc.ISourcePosition;
import org.jruby.ast.types.ILiteralNode;

public class ArrayNode extends ListNode implements ILiteralNode
{
    private boolean lightweight;
    
    public ArrayNode(final ISourcePosition position, final Node firstNode) {
        super(position, firstNode);
        this.lightweight = false;
        assert firstNode != null : "ArrayNode.first == null";
    }
    
    public ArrayNode(final ISourcePosition position) {
        super(position);
        this.lightweight = false;
    }
    
    public NodeType getNodeType() {
        return NodeType.ARRAYNODE;
    }
    
    public Object accept(final NodeVisitor iVisitor) {
        return iVisitor.visitArrayNode(this);
    }
    
    public void setLightweight(final boolean lightweight) {
        this.lightweight = lightweight;
    }
    
    public boolean isLightweight() {
        return this.lightweight;
    }
    
    public IRubyObject interpret(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock) {
        final IRubyObject[] array = this.interpretPrimitive(runtime, context, self, aBlock);
        return this.lightweight ? runtime.newArrayNoCopyLight(array) : runtime.newArrayNoCopy(array);
    }
    
    public IRubyObject[] interpretPrimitive(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock) {
        final int size = this.size();
        final IRubyObject[] array = new IRubyObject[size];
        for (int i = 0; i < size; ++i) {
            array[i] = this.get(i).interpret(runtime, context, self, aBlock);
        }
        return array;
    }
}
