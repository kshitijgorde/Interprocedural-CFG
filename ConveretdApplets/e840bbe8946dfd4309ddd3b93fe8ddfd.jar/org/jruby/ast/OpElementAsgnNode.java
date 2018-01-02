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

public class OpElementAsgnNode extends Node
{
    private final Node receiverNode;
    private final Node argsNode;
    private final Node valueNode;
    public final CallSite callAdapter;
    public final CallSite elementAdapter;
    public final CallSite elementAsgnAdapter;
    
    public OpElementAsgnNode(final ISourcePosition position, final Node receiverNode, final String operatorName, final Node argsNode, final Node valueNode) {
        super(position);
        assert receiverNode != null : "receiverNode is not null";
        assert valueNode != null : "valueNode is not null";
        this.receiverNode = receiverNode;
        this.argsNode = argsNode;
        if (argsNode instanceof ArrayNode) {
            ((ArrayNode)argsNode).setLightweight(true);
        }
        this.valueNode = valueNode;
        this.callAdapter = MethodIndex.getCallSite(operatorName);
        this.elementAdapter = MethodIndex.getFunctionalCallSite("[]");
        this.elementAsgnAdapter = MethodIndex.getFunctionalCallSite("[]=");
    }
    
    public NodeType getNodeType() {
        return NodeType.OPELEMENTASGNNODE;
    }
    
    public Object accept(final NodeVisitor iVisitor) {
        return iVisitor.visitOpElementAsgnNode(this);
    }
    
    public Node getArgsNode() {
        return this.argsNode;
    }
    
    public String getOperatorName() {
        return this.callAdapter.methodName;
    }
    
    public Node getReceiverNode() {
        return this.receiverNode;
    }
    
    public Node getValueNode() {
        return this.valueNode;
    }
    
    public List<Node> childNodes() {
        return Node.createList(this.receiverNode, this.argsNode, this.valueNode);
    }
    
    public IRubyObject interpret(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock) {
        final IRubyObject receiver = this.receiverNode.interpret(runtime, context, self, aBlock);
        final IRubyObject[] args = ASTInterpreter.setupArgs(runtime, context, this.argsNode, self, aBlock);
        IRubyObject value = this.elementAdapter.call(context, self, receiver, args);
        if (this.getOperatorName() == "||") {
            if (value.isTrue()) {
                return value;
            }
            value = this.valueNode.interpret(runtime, context, self, aBlock);
        }
        else if (this.getOperatorName() == "&&") {
            if (!value.isTrue()) {
                return value;
            }
            value = this.valueNode.interpret(runtime, context, self, aBlock);
        }
        else {
            value = this.callAdapter.call(context, self, value, this.valueNode.interpret(runtime, context, self, aBlock));
        }
        final IRubyObject[] expandedArgs = new IRubyObject[args.length + 1];
        System.arraycopy(args, 0, expandedArgs, 0, args.length);
        expandedArgs[expandedArgs.length - 1] = value;
        this.elementAsgnAdapter.call(context, self, receiver, expandedArgs);
        return value;
    }
}
