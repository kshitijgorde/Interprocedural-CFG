// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ast;

import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.RubyClass;
import org.jruby.exceptions.JumpException;
import org.jruby.runtime.Visibility;
import org.jruby.util.ByteList;
import org.jruby.runtime.CallType;
import org.jruby.RubyArray;
import org.jruby.javasupport.util.RuntimeHelpers;
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
import org.jruby.ast.types.INameNode;

public class AttrAssignNode extends Node implements INameNode, IArgumentNode
{
    protected final Node receiverNode;
    private String name;
    private Node argsNode;
    public CallSite variableCallAdapter;
    public CallSite normalCallAdapter;
    
    public AttrAssignNode(final ISourcePosition position, final Node receiverNode, final String name, final Node argsNode) {
        super(position);
        assert receiverNode != null : "receiverNode is not null";
        this.receiverNode = receiverNode;
        this.name = name;
        this.setArgsInternal(argsNode);
        this.normalCallAdapter = MethodIndex.getCallSite(name);
        this.variableCallAdapter = MethodIndex.getVariableCallSite(name);
    }
    
    public NodeType getNodeType() {
        return NodeType.ATTRASSIGNNODE;
    }
    
    public Object accept(final NodeVisitor visitor) {
        return visitor.visitAttrAssignNode(this);
    }
    
    public String getName() {
        return this.name;
    }
    
    public Node getReceiverNode() {
        return this.receiverNode;
    }
    
    public Node getArgsNode() {
        return this.argsNode;
    }
    
    protected Node newAttrAssignNode(final ArrayNode argsNode) {
        final int size = argsNode.size();
        switch (size) {
            case 1: {
                return new AttrAssignOneArgNode(this.getPosition(), this.receiverNode, this.name, argsNode);
            }
            case 2: {
                return new AttrAssignTwoArgNode(this.getPosition(), this.receiverNode, this.name, argsNode);
            }
            case 3: {
                return new AttrAssignThreeArgNode(this.getPosition(), this.receiverNode, this.name, argsNode);
            }
            default: {
                return new AttrAssignNode(this.getPosition(), this.receiverNode, this.name, argsNode);
            }
        }
    }
    
    protected Node newMutatedAttrAssignNode(final ArrayNode argsNode) {
        final int size = argsNode.size();
        switch (size) {
            case 1: {
                if (!(this instanceof AttrAssignOneArgNode)) {
                    return new AttrAssignOneArgNode(this.getPosition(), this.receiverNode, this.name, argsNode);
                }
                return this;
            }
            case 2: {
                if (!(this instanceof AttrAssignTwoArgNode)) {
                    return new AttrAssignTwoArgNode(this.getPosition(), this.receiverNode, this.name, argsNode);
                }
                return this;
            }
            case 3: {
                if (!(this instanceof AttrAssignThreeArgNode)) {
                    return new AttrAssignThreeArgNode(this.getPosition(), this.receiverNode, this.name, argsNode);
                }
                return this;
            }
            default: {
                return new AttrAssignNode(this.getPosition(), this.receiverNode, this.name, argsNode);
            }
        }
    }
    
    public Node setArgsNode(final Node argsNode) {
        if (this.argsNode == null && argsNode instanceof ArrayNode) {
            return this.newAttrAssignNode((ArrayNode)argsNode);
        }
        if (this.argsNode == argsNode) {
            return this.newMutatedAttrAssignNode((ArrayNode)argsNode);
        }
        this.setArgsInternal(argsNode);
        return this;
    }
    
    private void setArgsInternal(final Node argsNode) {
        this.argsNode = argsNode;
        if (argsNode instanceof ArrayNode) {
            ((ArrayNode)argsNode).setLightweight(true);
        }
    }
    
    public List<Node> childNodes() {
        return Node.createList(this.receiverNode, this.argsNode);
    }
    
    public IRubyObject interpret(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock) {
        final IRubyObject receiver = this.receiverNode.interpret(runtime, context, self, aBlock);
        final IRubyObject[] args = ASTInterpreter.setupArgs(runtime, context, this.argsNode, self, aBlock);
        assert hasMetaClass(receiver) : receiverClassName(receiver);
        final CallSite callSite = this.selectCallSite(self, receiver);
        callSite.call(context, self, receiver, args);
        return args[args.length - 1];
    }
    
    protected static boolean hasMetaClass(final IRubyObject object) {
        return object.getMetaClass() != null;
    }
    
    protected static String receiverClassName(final IRubyObject object) {
        return object.getClass().getName();
    }
    
    protected CallSite selectCallSite(final IRubyObject self, final IRubyObject receiver) {
        return (receiver == self) ? this.variableCallAdapter : this.normalCallAdapter;
    }
    
    public IRubyObject assign(final Ruby runtime, final ThreadContext context, final IRubyObject self, final IRubyObject value, final Block block, final boolean checkArity) {
        final IRubyObject receiver = this.receiverNode.interpret(runtime, context, self, block);
        if (receiver == self) {
            return this.selfAssign(runtime, context, self, value, block, checkArity);
        }
        return this.otherAssign(runtime, context, self, value, block, checkArity);
    }
    
    private IRubyObject selfAssign(final Ruby runtime, final ThreadContext context, final IRubyObject self, final IRubyObject value, final Block block, final boolean checkArity) {
        final IRubyObject receiver = this.receiverNode.interpret(runtime, context, self, block);
        if (this.argsNode == null) {
            RuntimeHelpers.invoke(context, receiver, this.name, value);
        }
        else {
            final RubyArray args = (RubyArray)this.argsNode.interpret(runtime, context, self, block);
            args.append(value);
            RuntimeHelpers.invoke(context, receiver, this.name, args.toJavaArray());
        }
        return runtime.getNil();
    }
    
    private IRubyObject otherAssign(final Ruby runtime, final ThreadContext context, final IRubyObject self, final IRubyObject value, final Block block, final boolean checkArity) {
        final IRubyObject receiver = this.receiverNode.interpret(runtime, context, self, block);
        if (this.argsNode == null) {
            RuntimeHelpers.invoke(context, receiver, this.name, value, CallType.NORMAL, Block.NULL_BLOCK);
        }
        else {
            final RubyArray args = (RubyArray)this.argsNode.interpret(runtime, context, self, block);
            args.append(value);
            RuntimeHelpers.invoke(context, receiver, this.name, args.toJavaArray(), CallType.NORMAL, Block.NULL_BLOCK);
        }
        return runtime.getNil();
    }
    
    public ByteList definition(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock) {
        if (this.receiverNode.definition(runtime, context, self, aBlock) != null) {
            try {
                final IRubyObject receiver = this.receiverNode.interpret(runtime, context, self, aBlock);
                final RubyClass metaClass = receiver.getMetaClass();
                final DynamicMethod method = metaClass.searchMethod(this.name);
                final Visibility visibility = method.getVisibility();
                if (visibility != Visibility.PRIVATE && (visibility != Visibility.PROTECTED || metaClass.getRealClass().isInstance(self)) && metaClass.isMethodBound(this.name, false)) {
                    return ASTInterpreter.getArgumentDefinition(runtime, context, this.argsNode, AttrAssignNode.ASSIGNMENT_BYTELIST, self, aBlock);
                }
            }
            catch (JumpException ex) {}
        }
        return null;
    }
}
