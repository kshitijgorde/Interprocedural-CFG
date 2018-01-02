// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ast;

import org.jruby.evaluator.ASTInterpreter;
import org.jruby.javasupport.util.RuntimeHelpers;
import org.jruby.exceptions.JumpException;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.Ruby;
import java.util.List;
import org.jruby.ast.visitor.NodeVisitor;
import org.jruby.lexer.yacc.ISourcePosition;

public class UntilNode extends Node
{
    private final Node conditionNode;
    private final Node bodyNode;
    private final boolean evaluateAtStart;
    public boolean containsNonlocalFlow;
    
    public UntilNode(final ISourcePosition position, final Node conditionNode, final Node bodyNode) {
        this(position, conditionNode, bodyNode, true);
    }
    
    public NodeType getNodeType() {
        return NodeType.UNTILNODE;
    }
    
    public UntilNode(final ISourcePosition position, final Node conditionNode, final Node bodyNode, final boolean evaluateAtStart) {
        super(position);
        this.containsNonlocalFlow = false;
        assert conditionNode != null : "conditionNode is not null";
        assert bodyNode != null : "bodyNode is not null";
        this.conditionNode = conditionNode;
        this.bodyNode = bodyNode;
        this.evaluateAtStart = evaluateAtStart;
    }
    
    public Object accept(final NodeVisitor iVisitor) {
        return iVisitor.visitUntilNode(this);
    }
    
    public Node getBodyNode() {
        return this.bodyNode;
    }
    
    public Node getConditionNode() {
        return this.conditionNode;
    }
    
    public List<Node> childNodes() {
        return Node.createList(this.conditionNode, this.bodyNode);
    }
    
    public boolean evaluateAtStart() {
        return this.evaluateAtStart;
    }
    
    public IRubyObject interpret(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock) {
        IRubyObject result = null;
        boolean firstTest = this.evaluateAtStart;
    Label_0037_Outer:
        while (!firstTest || !this.conditionNode.interpret(runtime, context, self, aBlock).isTrue()) {
            firstTest = true;
            while (true) {
                try {
                    this.bodyNode.interpret(runtime, context, self, aBlock);
                    continue Label_0037_Outer;
                }
                catch (JumpException.RedoJump rj) {
                    continue;
                }
                catch (JumpException.NextJump nj) {
                    continue Label_0037_Outer;
                }
                catch (JumpException.BreakJump bj) {
                    result = RuntimeHelpers.breakJumpInWhile(bj, context);
                }
                break;
            }
            break;
        }
        if (result == null) {
            result = runtime.getNil();
        }
        return ASTInterpreter.pollAndReturn(context, result);
    }
}
