// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ast;

import org.jruby.lexer.yacc.ISourcePosition;
import org.jruby.parser.StaticScope;
import org.jruby.ast.types.INameNode;

public abstract class MethodDefNode extends Node implements INameNode
{
    protected final ArgumentNode nameNode;
    protected final ArgsNode argsNode;
    protected final StaticScope scope;
    protected final Node bodyNode;
    
    public MethodDefNode(final ISourcePosition position, final ArgumentNode nameNode, final ArgsNode argsNode, final StaticScope scope, final Node bodyNode) {
        super(position);
        this.nameNode = nameNode;
        this.argsNode = argsNode;
        this.scope = scope;
        this.bodyNode = bodyNode;
        scope.setArities(argsNode.getRequiredArgsCount(), argsNode.getOptionalArgsCount(), argsNode.getRestArg());
    }
    
    public ArgsNode getArgsNode() {
        return this.argsNode;
    }
    
    public StaticScope getScope() {
        return this.scope;
    }
    
    public Node getBodyNode() {
        return this.bodyNode;
    }
    
    public ArgumentNode getNameNode() {
        return this.nameNode;
    }
    
    public String getName() {
        return this.nameNode.getName();
    }
}
