// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ast;

import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import java.util.List;
import org.jruby.Ruby;
import org.jruby.ast.visitor.NodeVisitor;
import org.jruby.lexer.yacc.ISourcePosition;
import org.jruby.RubyFloat;
import org.jruby.ast.types.ILiteralNode;

public class FloatNode extends Node implements ILiteralNode
{
    private double value;
    private RubyFloat flote;
    
    public FloatNode(final ISourcePosition position, final double value) {
        super(position);
        this.value = value;
    }
    
    public NodeType getNodeType() {
        return NodeType.FLOATNODE;
    }
    
    public Object accept(final NodeVisitor iVisitor) {
        return iVisitor.visitFloatNode(this);
    }
    
    public double getValue() {
        return this.value;
    }
    
    public void setValue(final double value) {
        if (this.flote != null) {
            this.flote = null;
        }
        this.value = value;
    }
    
    public RubyFloat getFloat(final Ruby runtime) {
        if (this.flote == null) {
            return this.flote = RubyFloat.newFloat(runtime, this.value);
        }
        return this.flote;
    }
    
    public List<Node> childNodes() {
        return FloatNode.EMPTY_LIST;
    }
    
    public IRubyObject interpret(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock) {
        return this.getFloat(runtime);
    }
}
