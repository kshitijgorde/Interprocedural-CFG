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

public class DSymbolNode extends DNode
{
    public DSymbolNode(final ISourcePosition position, final DStrNode node) {
        super(position);
        assert node != null : "node is not null";
        this.addAll(node);
    }
    
    public DSymbolNode(final ISourcePosition position) {
        super(position);
    }
    
    public NodeType getNodeType() {
        return NodeType.DSYMBOLNODE;
    }
    
    public Object accept(final NodeVisitor visitor) {
        return visitor.visitDSymbolNode(this);
    }
    
    public IRubyObject interpret(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock) {
        return runtime.newSymbol(super.interpret(runtime, context, self, aBlock).toString());
    }
}
