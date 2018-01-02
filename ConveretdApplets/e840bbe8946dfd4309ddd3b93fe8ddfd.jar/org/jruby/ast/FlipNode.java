// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ast;

import org.jruby.RubyBoolean;
import org.jruby.runtime.DynamicScope;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.Ruby;
import java.util.List;
import org.jruby.ast.visitor.NodeVisitor;
import org.jruby.lexer.yacc.ISourcePosition;

public class FlipNode extends Node
{
    private final Node beginNode;
    private final Node endNode;
    private final boolean exclusive;
    private final int location;
    
    public FlipNode(final ISourcePosition position, final Node beginNode, final Node endNode, final boolean exclusive, final int location) {
        super(position);
        assert beginNode != null : "beginNode is not null";
        assert endNode != null : "endNode is not null";
        this.beginNode = beginNode;
        this.endNode = endNode;
        this.exclusive = exclusive;
        this.location = location;
    }
    
    public NodeType getNodeType() {
        return NodeType.FLIPNODE;
    }
    
    public Object accept(final NodeVisitor iVisitor) {
        return iVisitor.visitFlipNode(this);
    }
    
    public Node getBeginNode() {
        return this.beginNode;
    }
    
    public Node getEndNode() {
        return this.endNode;
    }
    
    public boolean isExclusive() {
        return this.exclusive;
    }
    
    public int getDepth() {
        return this.location >> 16;
    }
    
    public int getIndex() {
        return this.location & 0xFFFF;
    }
    
    public List<Node> childNodes() {
        return Node.createList(this.beginNode, this.endNode);
    }
    
    public IRubyObject interpret(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock) {
        final DynamicScope scope = context.getCurrentScope();
        final IRubyObject nil = runtime.getNil();
        final int index = this.getIndex();
        final DynamicScope flipScope = scope.getFlipScope();
        flipScope.growIfNeeded();
        IRubyObject result = flipScope.getValueDepthZeroOrNil(index, nil);
        if (this.exclusive) {
            if (result == null || !result.isTrue()) {
                result = trueIfTrue(runtime, this.beginNode.interpret(runtime, context, self, aBlock));
                flipScope.setValueDepthZero(result, index);
                return result;
            }
            if (this.endNode.interpret(runtime, context, self, aBlock).isTrue()) {
                flipScope.setValueDepthZero(runtime.getFalse(), index);
            }
            return runtime.getTrue();
        }
        else {
            if (result != null && result.isTrue()) {
                if (this.endNode.interpret(runtime, context, self, aBlock).isTrue()) {
                    flipScope.setValueDepthZero(runtime.getFalse(), index);
                }
                return runtime.getTrue();
            }
            if (this.beginNode.interpret(runtime, context, self, aBlock).isTrue()) {
                flipScope.setValueDepthZero(falseIfTrue(runtime, this.endNode.interpret(runtime, context, self, aBlock)), index);
                return runtime.getTrue();
            }
            return runtime.getFalse();
        }
    }
    
    private static RubyBoolean trueIfTrue(final Ruby runtime, final IRubyObject truish) {
        return truish.isTrue() ? runtime.getTrue() : runtime.getFalse();
    }
    
    private static RubyBoolean falseIfTrue(final Ruby runtime, final IRubyObject truish) {
        return truish.isTrue() ? runtime.getFalse() : runtime.getTrue();
    }
}
