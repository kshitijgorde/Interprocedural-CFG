// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ast;

import org.jruby.evaluator.ASTInterpreter;
import org.jruby.RubyModule;
import org.jruby.RubyClass;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.Ruby;
import java.util.List;
import org.jruby.ast.visitor.NodeVisitor;
import org.jruby.lexer.yacc.ISourcePosition;
import org.jruby.parser.StaticScope;

public class ClassNode extends Node implements IScopingNode
{
    private final Colon3Node cpath;
    private final StaticScope scope;
    private final Node bodyNode;
    private final Node superNode;
    
    public ClassNode(final ISourcePosition position, final Colon3Node cpath, final StaticScope scope, final Node bodyNode, final Node superNode) {
        super(position);
        assert cpath != null : "cpath is not null";
        assert scope != null : "scope is not null";
        assert bodyNode != null : "bodyNode is not null";
        this.cpath = cpath;
        this.scope = scope;
        this.bodyNode = bodyNode;
        this.superNode = superNode;
    }
    
    public NodeType getNodeType() {
        return NodeType.CLASSNODE;
    }
    
    public Object accept(final NodeVisitor iVisitor) {
        return iVisitor.visitClassNode(this);
    }
    
    public Node getBodyNode() {
        return this.bodyNode;
    }
    
    public StaticScope getScope() {
        return this.scope;
    }
    
    public Colon3Node getCPath() {
        return this.cpath;
    }
    
    public Node getSuperNode() {
        return this.superNode;
    }
    
    public List<Node> childNodes() {
        return Node.createList(this.cpath, this.bodyNode, this.superNode);
    }
    
    public IRubyObject interpret(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock) {
        final RubyModule enclosingClass = this.cpath.getEnclosingModule(runtime, context, self, aBlock);
        if (enclosingClass == null) {
            throw runtime.newTypeError("no outer class/module");
        }
        RubyClass superClass = null;
        if (this.superNode != null) {
            final IRubyObject superObj = this.superNode.interpret(runtime, context, self, aBlock);
            RubyClass.checkInheritable(superObj);
            superClass = (RubyClass)superObj;
        }
        final RubyClass clazz = enclosingClass.defineOrGetClassUnder(this.cpath.getName(), superClass);
        this.scope.setModule(clazz);
        final IRubyObject classBodyResult = ASTInterpreter.evalClassDefinitionBody(runtime, context, this.scope, this.bodyNode, clazz, self, aBlock);
        return classBodyResult;
    }
}
