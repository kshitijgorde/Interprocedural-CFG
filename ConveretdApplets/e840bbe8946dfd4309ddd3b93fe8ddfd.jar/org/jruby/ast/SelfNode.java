// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ast;

import org.jruby.util.ByteList;
import org.jruby.evaluator.ASTInterpreter;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.Ruby;
import java.util.List;
import org.jruby.ast.visitor.NodeVisitor;
import org.jruby.lexer.yacc.ISourcePosition;
import org.jruby.ast.types.INameNode;

public class SelfNode extends Node implements INameNode
{
    public SelfNode(final ISourcePosition position) {
        super(position);
    }
    
    public NodeType getNodeType() {
        return NodeType.SELFNODE;
    }
    
    public Object accept(final NodeVisitor iVisitor) {
        return iVisitor.visitSelfNode(this);
    }
    
    public String getName() {
        return "self";
    }
    
    public List<Node> childNodes() {
        return SelfNode.EMPTY_LIST;
    }
    
    public IRubyObject interpret(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock) {
        return ASTInterpreter.pollAndReturn(context, self);
    }
    
    public ByteList definition(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock) {
        return SelfNode.SELF_BYTELIST;
    }
}
