// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ast;

import org.jruby.RubyModule;
import org.jruby.evaluator.ASTInterpreter;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.Ruby;
import java.util.List;
import org.jruby.ast.visitor.NodeVisitor;
import org.jruby.lexer.yacc.ISourcePosition;
import org.jruby.parser.StaticScope;

public class ModuleNode extends Node implements IScopingNode
{
    private final Colon3Node cpath;
    private final StaticScope scope;
    private final Node bodyNode;
    
    public ModuleNode(final ISourcePosition position, final Colon3Node cpath, final StaticScope scope, final Node bodyNode) {
        super(position);
        assert cpath != null : "cpath is not null";
        assert scope != null : "scope is not null";
        assert bodyNode != null : "bodyNode is not null";
        this.cpath = cpath;
        this.scope = scope;
        this.bodyNode = bodyNode;
    }
    
    public NodeType getNodeType() {
        return NodeType.MODULENODE;
    }
    
    public Object accept(final NodeVisitor iVisitor) {
        return iVisitor.visitModuleNode(this);
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
    
    public List<Node> childNodes() {
        return Node.createList(this.cpath, this.bodyNode);
    }
    
    public IRubyObject interpret(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock) {
        final RubyModule enclosingModule = this.cpath.getEnclosingModule(runtime, context, self, aBlock);
        if (enclosingModule == null) {
            throw runtime.newTypeError("no outer class/module");
        }
        final String name = this.cpath.getName();
        final RubyModule module = enclosingModule.defineOrGetModuleUnder(name);
        this.scope.setModule(module);
        return ASTInterpreter.evalClassDefinitionBody(runtime, context, this.scope, this.bodyNode, module, self, aBlock);
    }
}
