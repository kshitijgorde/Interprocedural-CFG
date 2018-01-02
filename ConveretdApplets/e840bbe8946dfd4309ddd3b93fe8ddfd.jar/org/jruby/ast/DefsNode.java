// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ast;

import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.RubyClass;
import org.jruby.RubyModule;
import org.jruby.internal.runtime.methods.DynamicMethodFactory;
import org.jruby.runtime.Visibility;
import org.jruby.RubySymbol;
import org.jruby.RubyFixnum;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.Ruby;
import java.util.List;
import org.jruby.ast.visitor.NodeVisitor;
import org.jruby.parser.StaticScope;
import org.jruby.lexer.yacc.ISourcePosition;
import org.jruby.ast.types.INameNode;

public class DefsNode extends MethodDefNode implements INameNode
{
    private final Node receiverNode;
    
    public DefsNode(final ISourcePosition position, final Node receiverNode, final ArgumentNode nameNode, final ArgsNode argsNode, final StaticScope scope, final Node bodyNode) {
        super(position, nameNode, argsNode, scope, bodyNode);
        assert receiverNode != null : "receiverNode is not null";
        this.receiverNode = receiverNode;
    }
    
    public NodeType getNodeType() {
        return NodeType.DEFSNODE;
    }
    
    public Object accept(final NodeVisitor iVisitor) {
        return iVisitor.visitDefsNode(this);
    }
    
    public Node getReceiverNode() {
        return this.receiverNode;
    }
    
    public String getName() {
        return this.nameNode.getName();
    }
    
    public List<Node> childNodes() {
        return Node.createList(this.receiverNode, this.nameNode, this.argsNode, this.bodyNode);
    }
    
    public IRubyObject interpret(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock) {
        final IRubyObject receiver = this.receiverNode.interpret(runtime, context, self, aBlock);
        final String name = this.getName();
        if (runtime.getSafeLevel() >= 4 && !receiver.isTaint()) {
            throw runtime.newSecurityError("Insecure; can't define singleton method.");
        }
        if (receiver instanceof RubyFixnum || receiver instanceof RubySymbol) {
            throw runtime.newTypeError("can't define singleton method \"" + name + "\" for " + receiver.getMetaClass().getBaseName());
        }
        if (receiver.isFrozen()) {
            throw runtime.newFrozenError("object");
        }
        final RubyClass rubyClass = receiver.getSingletonClass();
        if (runtime.getSafeLevel() >= 4 && rubyClass.getMethods().get(name) != null) {
            throw runtime.newSecurityError("redefining method prohibited.");
        }
        this.scope.determineModule();
        final Node body = (this.bodyNode == null) ? new NilNode(this.getPosition()) : this.bodyNode;
        final DynamicMethod newMethod = DynamicMethodFactory.newDefaultMethod(runtime, rubyClass, name, this.scope, body, this.argsNode, Visibility.PUBLIC, this.getPosition());
        rubyClass.addMethod(name, newMethod);
        receiver.callMethod(context, "singleton_method_added", runtime.fastNewSymbol(name));
        return runtime.getNil();
    }
}
