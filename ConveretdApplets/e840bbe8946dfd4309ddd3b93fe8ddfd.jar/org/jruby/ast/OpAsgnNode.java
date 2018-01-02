// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ast;

import org.jruby.evaluator.ASTInterpreter;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.Ruby;
import java.util.List;
import org.jruby.ast.visitor.NodeVisitor;
import org.jruby.runtime.MethodIndex;
import org.jruby.lexer.yacc.ISourcePosition;
import org.jruby.runtime.CallSite;

public class OpAsgnNode extends Node
{
    private final Node receiverNode;
    private final Node valueNode;
    public final CallSite variableCallAdapter;
    public final CallSite operatorCallAdapter;
    public final CallSite variableAsgnCallAdapter;
    
    public OpAsgnNode(final ISourcePosition position, final Node receiverNode, final Node valueNode, final String variableName, final String operatorName) {
        super(position);
        assert receiverNode != null : "receiverNode is not null";
        assert valueNode != null : "valueNode is not null";
        this.receiverNode = receiverNode;
        this.valueNode = valueNode;
        this.variableCallAdapter = MethodIndex.getCallSite(variableName);
        this.operatorCallAdapter = MethodIndex.getCallSite(operatorName);
        this.variableAsgnCallAdapter = MethodIndex.getCallSite((variableName + "=").intern());
    }
    
    public NodeType getNodeType() {
        return NodeType.OPASGNNODE;
    }
    
    public Object accept(final NodeVisitor iVisitor) {
        return iVisitor.visitOpAsgnNode(this);
    }
    
    public String getOperatorName() {
        return this.operatorCallAdapter.methodName;
    }
    
    public Node getReceiverNode() {
        return this.receiverNode;
    }
    
    public Node getValueNode() {
        return this.valueNode;
    }
    
    public String getVariableName() {
        return this.variableCallAdapter.methodName;
    }
    
    public String getVariableNameAsgn() {
        return this.variableAsgnCallAdapter.methodName;
    }
    
    public List<Node> childNodes() {
        return Node.createList(this.receiverNode, this.valueNode);
    }
    
    public IRubyObject interpret(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock) {
        final IRubyObject receiver = this.receiverNode.interpret(runtime, context, self, aBlock);
        IRubyObject value = this.variableCallAdapter.call(context, self, receiver);
        if (this.getOperatorName() == "||") {
            if (value.isTrue()) {
                return ASTInterpreter.pollAndReturn(context, value);
            }
            value = this.valueNode.interpret(runtime, context, self, aBlock);
        }
        else if (this.getOperatorName() == "&&") {
            if (!value.isTrue()) {
                return ASTInterpreter.pollAndReturn(context, value);
            }
            value = this.valueNode.interpret(runtime, context, self, aBlock);
        }
        else {
            value = this.operatorCallAdapter.call(context, self, value, this.valueNode.interpret(runtime, context, self, aBlock));
        }
        this.variableAsgnCallAdapter.call(context, self, receiver, value);
        return ASTInterpreter.pollAndReturn(context, value);
    }
}
