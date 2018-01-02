// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ast;

import org.jruby.common.IRubyWarnings;
import org.jruby.RubyModule;
import org.jruby.evaluator.ASTInterpreter;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.Ruby;
import java.util.List;
import org.jruby.ast.visitor.NodeVisitor;
import org.jruby.lexer.yacc.ISourcePosition;
import org.jruby.ast.types.INameNode;

public class ClassVarDeclNode extends AssignableNode implements INameNode
{
    private String name;
    
    public ClassVarDeclNode(final ISourcePosition position, final String name, final Node valueNode) {
        super(position, valueNode);
        this.name = name;
    }
    
    public NodeType getNodeType() {
        return NodeType.CLASSVARDECLNODE;
    }
    
    public Object accept(final NodeVisitor iVisitor) {
        return iVisitor.visitClassVarDeclNode(this);
    }
    
    public String getName() {
        return this.name;
    }
    
    public List<Node> childNodes() {
        return Node.createList(this.getValueNode());
    }
    
    public IRubyObject interpret(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock) {
        final RubyModule rubyClass = ASTInterpreter.getClassVariableBase(context, runtime);
        if (rubyClass == null) {
            throw runtime.newTypeError("no class/module to define class variable");
        }
        return rubyClass.fastSetClassVar(this.name, this.getValueNode().interpret(runtime, context, self, aBlock));
    }
    
    public IRubyObject assign(final Ruby runtime, final ThreadContext context, final IRubyObject self, final IRubyObject value, final Block block, final boolean checkArity) {
        if (runtime.isVerbose() && context.getRubyClass().isSingleton()) {
            runtime.getWarnings().warn(IRubyWarnings.ID.DECLARING_SCLASS_VARIABLE, this.getPosition(), "Declaring singleton class variable.", new Object[0]);
        }
        ASTInterpreter.getClassVariableBase(context, runtime).fastSetClassVar(this.name, value);
        return runtime.getNil();
    }
}
