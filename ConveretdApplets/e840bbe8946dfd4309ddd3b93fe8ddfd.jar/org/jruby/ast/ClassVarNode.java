// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ast;

import org.jruby.MetaClass;
import org.jruby.util.ByteList;
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

public class ClassVarNode extends Node implements INameNode
{
    private String name;
    
    public ClassVarNode(final ISourcePosition position, final String name) {
        super(position);
        this.name = name;
    }
    
    public NodeType getNodeType() {
        return NodeType.CLASSVARNODE;
    }
    
    public Object accept(final NodeVisitor iVisitor) {
        return iVisitor.visitClassVarNode(this);
    }
    
    public String getName() {
        return this.name;
    }
    
    public List<Node> childNodes() {
        return ClassVarNode.EMPTY_LIST;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    public IRubyObject interpret(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock) {
        RubyModule rubyClass = ASTInterpreter.getClassVariableBase(context, runtime);
        if (rubyClass == null) {
            rubyClass = self.getMetaClass();
        }
        return rubyClass.getClassVar(this.name);
    }
    
    public ByteList definition(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock) {
        final RubyModule module = context.getCurrentScope().getStaticScope().getModule();
        if (module == null && self.getMetaClass().fastIsClassVarDefined(this.name)) {
            return ClassVarNode.CLASS_VARIABLE_BYTELIST;
        }
        if (module.fastIsClassVarDefined(this.name)) {
            return ClassVarNode.CLASS_VARIABLE_BYTELIST;
        }
        final IRubyObject attached = module.isSingleton() ? ((MetaClass)module).getAttached() : null;
        if (attached instanceof RubyModule && ((RubyModule)attached).fastIsClassVarDefined(this.name)) {
            return ClassVarNode.CLASS_VARIABLE_BYTELIST;
        }
        return null;
    }
}
