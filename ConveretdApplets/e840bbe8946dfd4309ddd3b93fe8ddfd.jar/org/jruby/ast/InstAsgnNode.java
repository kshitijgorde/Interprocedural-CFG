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
import org.jruby.lexer.yacc.ISourcePosition;
import org.jruby.RubyClass;
import org.jruby.ast.types.INameNode;

public class InstAsgnNode extends AssignableNode implements INameNode
{
    private String name;
    private RubyClass.VariableAccessor accessor;
    
    public InstAsgnNode(final ISourcePosition position, final String name, final Node valueNode) {
        super(position, valueNode);
        this.accessor = RubyClass.VariableAccessor.DUMMY_ACCESSOR;
        this.name = name;
    }
    
    public NodeType getNodeType() {
        return NodeType.INSTASGNNODE;
    }
    
    public Object accept(final NodeVisitor iVisitor) {
        return iVisitor.visitInstAsgnNode(this);
    }
    
    public String getName() {
        return this.name;
    }
    
    public List<Node> childNodes() {
        return Node.createList(this.getValueNode());
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    public IRubyObject interpret(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock) {
        final RubyClass cls = self.getMetaClass().getRealClass();
        final IRubyObject value = this.getValueNode().interpret(runtime, context, self, aBlock);
        this.verifyAccessor(cls).set(self, value);
        return value;
    }
    
    public IRubyObject assign(final Ruby runtime, final ThreadContext context, final IRubyObject self, final IRubyObject value, final Block block, final boolean checkArity) {
        final RubyClass cls = self.getMetaClass().getRealClass();
        this.verifyAccessor(cls).set(self, value);
        return runtime.getNil();
    }
    
    private RubyClass.VariableAccessor verifyAccessor(final RubyClass cls) {
        RubyClass.VariableAccessor localAccessor = this.accessor;
        if (localAccessor.getClassId() != cls.hashCode()) {
            localAccessor = cls.getVariableAccessorForWrite(this.name);
            this.accessor = localAccessor;
        }
        return localAccessor;
    }
}
