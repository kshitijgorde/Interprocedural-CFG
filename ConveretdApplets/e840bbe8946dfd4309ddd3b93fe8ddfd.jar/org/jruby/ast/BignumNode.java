// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ast;

import org.jruby.RubyBignum;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.Ruby;
import java.util.List;
import org.jruby.ast.visitor.NodeVisitor;
import org.jruby.lexer.yacc.ISourcePosition;
import java.math.BigInteger;
import org.jruby.ast.types.ILiteralNode;

public class BignumNode extends Node implements ILiteralNode
{
    private BigInteger value;
    
    public BignumNode(final ISourcePosition position, final BigInteger value) {
        super(position);
        this.value = value;
    }
    
    public NodeType getNodeType() {
        return NodeType.BIGNUMNODE;
    }
    
    public Object accept(final NodeVisitor iVisitor) {
        return iVisitor.visitBignumNode(this);
    }
    
    public BigInteger getValue() {
        return this.value;
    }
    
    public List<Node> childNodes() {
        return BignumNode.EMPTY_LIST;
    }
    
    public void setValue(final BigInteger value) {
        this.value = value;
    }
    
    public IRubyObject interpret(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock) {
        return RubyBignum.newBignum(runtime, this.value);
    }
}
