// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ast;

import org.jruby.exceptions.JumpException;
import org.jruby.runtime.SharedScopeBlock;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.Ruby;
import java.util.List;
import org.jruby.ast.visitor.NodeVisitor;
import org.jruby.runtime.MethodIndex;
import org.jruby.parser.StaticScope;
import org.jruby.lexer.yacc.ISourcePosition;
import org.jruby.runtime.CallSite;

public class ForNode extends IterNode
{
    public final CallSite callAdapter;
    private Node iterNode;
    
    public ForNode(final ISourcePosition position, final Node varNode, final Node bodyNode, final Node iterNode, final StaticScope scope) {
        super(position, varNode, scope, bodyNode);
        this.callAdapter = MethodIndex.getCallSite("each");
        assert iterNode != null : "iterNode is not null";
        this.iterNode = iterNode;
    }
    
    public NodeType getNodeType() {
        return NodeType.FORNODE;
    }
    
    public Node getIterNode() {
        return this.iterNode;
    }
    
    public Object accept(final NodeVisitor iVisitor) {
        return iVisitor.visitForNode(this);
    }
    
    public List<Node> childNodes() {
        return Node.createList(this.getVarNode(), this.getBodyNode(), this.iterNode);
    }
    
    public IRubyObject interpret(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock) {
        final Block block = SharedScopeBlock.newInterpretedSharedScopeClosure(context, this, context.getCurrentScope(), self);
        try {
            try {
                final String savedFile = context.getFile();
                final int savedLine = context.getLine();
                IRubyObject recv = null;
                try {
                    recv = this.iterNode.interpret(runtime, context, self, aBlock);
                }
                finally {
                    context.setFileAndLine(savedFile, savedLine);
                }
                return this.callAdapter.call(context, self, recv, block);
            }
            catch (JumpException.RetryJump rj) {}
        }
        catch (JumpException.BreakJump bj) {
            return (IRubyObject)bj.getValue();
        }
    }
}
