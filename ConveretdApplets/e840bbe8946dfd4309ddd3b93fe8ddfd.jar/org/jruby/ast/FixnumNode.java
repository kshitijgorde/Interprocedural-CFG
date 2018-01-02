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
import org.jruby.RubyFixnum;
import org.jruby.ast.types.IEqlNode;
import org.jruby.ast.types.ILiteralNode;

public class FixnumNode extends Node implements ILiteralNode, IEqlNode
{
    private long value;
    private RubyFixnum fixnum;
    
    public FixnumNode(final ISourcePosition position, final long value) {
        super(position);
        this.value = value;
    }
    
    public Object accept(final NodeVisitor iVisitor) {
        return iVisitor.visitFixnumNode(this);
    }
    
    public NodeType getNodeType() {
        return NodeType.FIXNUMNODE;
    }
    
    public long getValue() {
        return this.value;
    }
    
    public void setValue(final long value) {
        if (this.fixnum != null) {
            this.fixnum = null;
        }
        this.value = value;
    }
    
    public RubyFixnum getFixnum(final Ruby runtime) {
        if (this.fixnum == null) {
            return this.fixnum = RubyFixnum.newFixnum(runtime, this.value);
        }
        return this.fixnum;
    }
    
    public List<Node> childNodes() {
        return FixnumNode.EMPTY_LIST;
    }
    
    public IRubyObject interpret(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock) {
        return this.getFixnum(runtime);
    }
    
    public boolean eql(final IRubyObject otherValue, final ThreadContext context, final Ruby runtime, final IRubyObject self, final Block aBlock) {
        if (otherValue instanceof RubyFixnum) {
            return this.value == ((RubyFixnum)otherValue).getLongValue();
        }
        return this.getFixnum(runtime).op_equal(context, otherValue).isTrue();
    }
}
