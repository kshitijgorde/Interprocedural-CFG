// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ast;

import java.util.Iterator;
import org.jruby.evaluator.ASTInterpreter;
import org.jruby.runtime.RubyEvent;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.Ruby;
import java.util.List;
import org.jruby.ast.visitor.NodeVisitor;
import org.jruby.lexer.yacc.ISourcePosition;

public class CaseNode extends Node
{
    private final Node caseNode;
    private final ListNode cases;
    private Node elseNode;
    
    public CaseNode(final ISourcePosition position, final Node caseNode, final ListNode cases) {
        super(position);
        this.elseNode = null;
        assert cases != null : "caseBody is not null";
        this.caseNode = caseNode;
        this.cases = cases;
    }
    
    public void setElseNode(final Node elseNode) {
        this.elseNode = elseNode;
    }
    
    public NodeType getNodeType() {
        return NodeType.CASENODE;
    }
    
    public Object accept(final NodeVisitor iVisitor) {
        return iVisitor.visitCaseNode(this);
    }
    
    public Node getCaseNode() {
        return this.caseNode;
    }
    
    public ListNode getCases() {
        return this.cases;
    }
    
    public Node getElseNode() {
        return this.elseNode;
    }
    
    @Deprecated
    public Node getFirstWhenNode() {
        return this.cases;
    }
    
    public List<Node> childNodes() {
        return Node.createList(this.caseNode, this.cases);
    }
    
    public IRubyObject interpret(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock) {
        final IRubyObject expression = (this.caseNode == null) ? null : this.caseNode.interpret(runtime, context, self, aBlock);
        context.pollThreadEvents();
        for (final Node child : this.cases.childNodes()) {
            final WhenNode when = (WhenNode)child;
            final ISourcePosition position = child.getPosition();
            context.setFile(position.getFile());
            context.setLine(position.getStartLine());
            if (runtime.hasEventHooks()) {
                ASTInterpreter.callTraceFunction(runtime, context, RubyEvent.LINE);
            }
            final IRubyObject result = when.when(expression, context, runtime, self, aBlock);
            if (result != null) {
                return result;
            }
            context.pollThreadEvents();
        }
        return (this.elseNode != null) ? this.elseNode.interpret(runtime, context, self, aBlock) : runtime.getNil();
    }
}
