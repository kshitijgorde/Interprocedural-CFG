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
import org.jruby.ast.types.IEqlNode;
import org.jruby.ast.types.INameNode;

public class FalseNode extends Node implements INameNode, IEqlNode
{
    public FalseNode(final ISourcePosition position) {
        super(position);
    }
    
    public NodeType getNodeType() {
        return NodeType.FALSENODE;
    }
    
    public Object accept(final NodeVisitor iVisitor) {
        return iVisitor.visitFalseNode(this);
    }
    
    public String getName() {
        return "false";
    }
    
    public List<Node> childNodes() {
        return FalseNode.EMPTY_LIST;
    }
    
    public IRubyObject interpret(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock) {
        return ASTInterpreter.pollAndReturn(context, runtime.getFalse());
    }
    
    public ByteList definition(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock) {
        return FalseNode.FALSE_BYTELIST;
    }
    
    public boolean eql(final IRubyObject otherValue, final ThreadContext context, final Ruby runtime, final IRubyObject self, final Block aBlock) {
        return otherValue == this.interpret(runtime, context, self, aBlock);
    }
}
