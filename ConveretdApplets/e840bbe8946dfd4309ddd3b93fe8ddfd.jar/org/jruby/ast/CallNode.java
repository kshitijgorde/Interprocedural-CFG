// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ast;

import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.RubyClass;
import org.jruby.exceptions.JumpException;
import org.jruby.evaluator.ASTInterpreter;
import org.jruby.runtime.Visibility;
import org.jruby.util.ByteList;
import org.jruby.RubyArray;
import org.jruby.javasupport.util.RuntimeHelpers;
import org.jruby.runtime.CallType;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.Ruby;
import java.util.List;
import org.jruby.ast.visitor.NodeVisitor;
import org.jruby.runtime.MethodIndex;
import org.jruby.lexer.yacc.ISourcePosition;
import org.jruby.runtime.CallSite;
import org.jruby.ast.types.INameNode;

public class CallNode extends Node implements INameNode, IArgumentNode, BlockAcceptingNode
{
    private final Node receiverNode;
    private Node argsNode;
    protected Node iterNode;
    public CallSite callAdapter;
    
    public CallNode(final ISourcePosition position, final Node receiverNode, final String name, final Node argsNode) {
        this(position, receiverNode, name, argsNode, null);
    }
    
    public CallNode(final ISourcePosition position, final Node receiverNode, final String name, final Node argsNode, final Node iterNode) {
        super(position);
        assert receiverNode != null : "receiverNode is not null";
        this.receiverNode = receiverNode;
        this.setArgsNode(argsNode);
        this.iterNode = iterNode;
        this.callAdapter = MethodIndex.getCallSite(name);
    }
    
    public NodeType getNodeType() {
        return NodeType.CALLNODE;
    }
    
    public Object accept(final NodeVisitor iVisitor) {
        return iVisitor.visitCallNode(this);
    }
    
    public Node getIterNode() {
        return this.iterNode;
    }
    
    public Node setIterNode(final Node iterNode) {
        this.iterNode = iterNode;
        this.callAdapter = MethodIndex.getCallSite(this.callAdapter.methodName);
        return this;
    }
    
    public Node getArgsNode() {
        return this.argsNode;
    }
    
    public Node setArgsNode(final Node argsNode) {
        this.argsNode = argsNode;
        if (argsNode instanceof ArrayNode) {
            ((ArrayNode)argsNode).setLightweight(true);
        }
        return argsNode;
    }
    
    public String getName() {
        return this.callAdapter.methodName;
    }
    
    public Node getReceiverNode() {
        return this.receiverNode;
    }
    
    public List<Node> childNodes() {
        return Node.createList(this.receiverNode, this.argsNode, this.iterNode);
    }
    
    public IRubyObject interpret(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock) {
        assert false : "No longer called";
        return null;
    }
    
    public IRubyObject assign(final Ruby runtime, final ThreadContext context, final IRubyObject self, final IRubyObject value, final Block block, final boolean checkArity) {
        final IRubyObject receiver = this.receiverNode.interpret(runtime, context, self, block);
        if (this.argsNode == null) {
            RuntimeHelpers.invoke(context, receiver, this.getName(), new IRubyObject[] { value }, CallType.NORMAL, Block.NULL_BLOCK);
        }
        else {
            final RubyArray args = (RubyArray)this.argsNode.interpret(runtime, context, self, block);
            args.append(value);
            RuntimeHelpers.invoke(context, receiver, this.getName(), args.toJavaArray(), CallType.NORMAL, Block.NULL_BLOCK);
        }
        return runtime.getNil();
    }
    
    public ByteList definition(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock) {
        if (this.receiverNode.definition(runtime, context, self, aBlock) != null) {
            try {
                final IRubyObject receiver = this.receiverNode.interpret(runtime, context, self, aBlock);
                final RubyClass metaClass = receiver.getMetaClass();
                final DynamicMethod method = metaClass.searchMethod(this.getName());
                final Visibility visibility = method.getVisibility();
                if (visibility != Visibility.PRIVATE && (visibility != Visibility.PROTECTED || metaClass.getRealClass().isInstance(self)) && !method.isUndefined()) {
                    return ASTInterpreter.getArgumentDefinition(runtime, context, this.getArgsNode(), CallNode.METHOD_BYTELIST, self, aBlock);
                }
            }
            catch (JumpException ex) {}
        }
        return null;
    }
}
