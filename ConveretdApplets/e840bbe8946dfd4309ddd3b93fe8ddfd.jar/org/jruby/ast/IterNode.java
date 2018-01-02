// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ast;

import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.Ruby;
import java.util.List;
import org.jruby.ast.visitor.NodeVisitor;
import org.jruby.runtime.Interpreted19Block;
import org.jruby.runtime.InterpretedBlock;
import org.jruby.runtime.Arity;
import org.jruby.lexer.yacc.ISourcePosition;
import org.jruby.runtime.BlockBody;
import org.jruby.parser.StaticScope;

public class IterNode extends Node
{
    private final Node varNode;
    private final Node bodyNode;
    private final Node blockVarNode;
    private StaticScope scope;
    private BlockBody blockBody;
    
    public IterNode(final ISourcePosition position, final Node args, final StaticScope scope, final Node body) {
        super(position);
        if (args instanceof BlockArg18Node) {
            this.varNode = ((BlockArg18Node)args).getArgs();
            this.blockVarNode = ((BlockArg18Node)args).getBlockArg();
        }
        else {
            this.varNode = args;
            this.blockVarNode = null;
        }
        this.scope = scope;
        this.bodyNode = body;
        this.blockBody = InterpretedBlock.newBlockBody(this, Arity.procArityOf(this.varNode), this.getArgumentType());
    }
    
    public IterNode(final ISourcePosition position, final ArgsNode args, final Node body, final StaticScope scope) {
        super(position);
        this.varNode = args;
        this.blockVarNode = null;
        this.bodyNode = body;
        this.scope = scope;
        this.blockBody = Interpreted19Block.newBlockBody(this);
    }
    
    public final int getArgumentType() {
        return BlockBody.asArgumentType(BlockBody.getArgumentTypeWackyHack(this));
    }
    
    public NodeType getNodeType() {
        return NodeType.ITERNODE;
    }
    
    public Object accept(final NodeVisitor iVisitor) {
        return iVisitor.visitIterNode(this);
    }
    
    public Node getBlockVarNode() {
        return this.blockVarNode;
    }
    
    public StaticScope getScope() {
        return this.scope;
    }
    
    public Node getBodyNode() {
        return this.bodyNode;
    }
    
    public Node getVarNode() {
        return this.varNode;
    }
    
    public BlockBody getBlockBody() {
        return this.blockBody;
    }
    
    public List<Node> childNodes() {
        return Node.createList(this.varNode, this.blockVarNode, this.bodyNode);
    }
    
    public IRubyObject interpret(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock) {
        assert false : "Call nodes deal with these directly";
        return null;
    }
}
