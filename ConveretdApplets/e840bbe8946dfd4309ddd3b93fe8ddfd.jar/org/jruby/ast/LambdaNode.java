// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ast;

import org.jruby.RubyProc;
import org.jruby.runtime.Interpreted19Block;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.Ruby;
import java.util.List;
import org.jruby.ast.visitor.NodeVisitor;
import org.jruby.parser.StaticScope;
import org.jruby.lexer.yacc.ISourcePosition;

public class LambdaNode extends IterNode
{
    public LambdaNode(final ISourcePosition position, final ArgsNode args, final Node body, final StaticScope scope) {
        super(position, args, body, scope);
    }
    
    public NodeType getNodeType() {
        return NodeType.LAMBDANODE;
    }
    
    public ArgsNode getArgs() {
        return (ArgsNode)this.getVarNode();
    }
    
    public Node getBody() {
        return this.getBodyNode();
    }
    
    public Object accept(final NodeVisitor visitor) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public List<Node> childNodes() {
        return Node.createList(this.getArgs(), this.getBody());
    }
    
    public IRubyObject interpret(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock) {
        this.getScope().determineModule();
        return RubyProc.newProc(runtime, Interpreted19Block.newInterpretedClosure(context, this.getBlockBody(), self), Block.Type.LAMBDA, this.getPosition());
    }
}
