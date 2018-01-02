// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ast;

import org.jruby.util.ByteList;
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

public class VCallNode extends Node implements INameNode
{
    public final CallSite callAdapter;
    
    public VCallNode(final ISourcePosition position, final String name) {
        super(position);
        this.callAdapter = MethodIndex.getVariableCallSite(name);
    }
    
    public NodeType getNodeType() {
        return NodeType.VCALLNODE;
    }
    
    public Object accept(final NodeVisitor iVisitor) {
        return iVisitor.visitVCallNode(this);
    }
    
    public String getName() {
        return this.callAdapter.methodName;
    }
    
    public List<Node> childNodes() {
        return VCallNode.EMPTY_LIST;
    }
    
    public IRubyObject interpret(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock) {
        return this.callAdapter.call(context, self, self);
    }
    
    public ByteList definition(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock) {
        return self.getMetaClass().isMethodBound(this.getName(), false) ? VCallNode.METHOD_BYTELIST : null;
    }
}
