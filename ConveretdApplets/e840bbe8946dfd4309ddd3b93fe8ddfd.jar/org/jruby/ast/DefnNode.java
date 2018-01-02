// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ast;

import org.jruby.MetaClass;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.RubyModule;
import org.jruby.internal.runtime.methods.WrapperMethod;
import org.jruby.internal.runtime.methods.DynamicMethodFactory;
import org.jruby.runtime.Visibility;
import org.jruby.common.IRubyWarnings;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.Ruby;
import java.util.List;
import org.jruby.ast.visitor.NodeVisitor;
import org.jruby.parser.StaticScope;
import org.jruby.lexer.yacc.ISourcePosition;
import org.jruby.ast.types.INameNode;

public class DefnNode extends MethodDefNode implements INameNode
{
    public DefnNode(final ISourcePosition position, final ArgumentNode nameNode, final ArgsNode argsNode, final StaticScope scope, final Node bodyNode) {
        super(position, nameNode, argsNode, scope, bodyNode);
    }
    
    public NodeType getNodeType() {
        return NodeType.DEFNNODE;
    }
    
    public Object accept(final NodeVisitor iVisitor) {
        return iVisitor.visitDefnNode(this);
    }
    
    public String getName() {
        return this.nameNode.getName();
    }
    
    public List<Node> childNodes() {
        return Node.createList(this.nameNode, this.argsNode, this.bodyNode);
    }
    
    public IRubyObject interpret(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock) {
        final RubyModule containingClass = context.getRubyClass();
        if (containingClass == runtime.getDummy()) {
            throw runtime.newTypeError("no class/module to add method");
        }
        final String name = this.getName();
        if (containingClass == runtime.getObject() && name == "initialize") {
            runtime.getWarnings().warn(IRubyWarnings.ID.REDEFINING_DANGEROUS, "redefining Object#initialize may cause infinite loop", "Object#initialize");
        }
        if (name == "__id__" || name == "__send__") {
            runtime.getWarnings().warn(IRubyWarnings.ID.REDEFINING_DANGEROUS, "redefining `" + name + "' may cause serious problem", name);
        }
        Visibility visibility = context.getCurrentVisibility();
        if (name == "initialize" || name == "initialize_copy" || visibility == Visibility.MODULE_FUNCTION) {
            visibility = Visibility.PRIVATE;
        }
        this.scope.determineModule();
        final Node body = (this.bodyNode == null) ? new NilNode(this.getPosition()) : this.bodyNode;
        final DynamicMethod newMethod = DynamicMethodFactory.newDefaultMethod(runtime, containingClass, name, this.scope, body, this.argsNode, visibility, this.getPosition());
        containingClass.addMethod(name, newMethod);
        if (context.getCurrentVisibility() == Visibility.MODULE_FUNCTION) {
            containingClass.getSingletonClass().addMethod(name, new WrapperMethod(containingClass.getSingletonClass(), newMethod, Visibility.PUBLIC));
            containingClass.callMethod(context, "singleton_method_added", runtime.fastNewSymbol(name));
        }
        if (containingClass.isSingleton()) {
            ((MetaClass)containingClass).getAttached().callMethod(context, "singleton_method_added", runtime.fastNewSymbol(name));
        }
        else {
            containingClass.callMethod(context, "method_added", runtime.fastNewSymbol(name));
        }
        return runtime.getNil();
    }
}
