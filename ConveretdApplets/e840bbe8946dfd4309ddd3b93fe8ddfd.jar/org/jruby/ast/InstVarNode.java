// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ast;

import org.jruby.util.ByteList;
import org.jruby.common.IRubyWarnings;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.Ruby;
import java.util.List;
import org.jruby.runtime.Arity;
import org.jruby.ast.visitor.NodeVisitor;
import org.jruby.lexer.yacc.ISourcePosition;
import org.jruby.RubyClass;
import org.jruby.ast.types.INameNode;
import org.jruby.ast.types.IArityNode;

public class InstVarNode extends Node implements IArityNode, INameNode
{
    private String name;
    private RubyClass.VariableAccessor accessor;
    
    public InstVarNode(final ISourcePosition position, final String name) {
        super(position);
        this.accessor = RubyClass.VariableAccessor.DUMMY_ACCESSOR;
        this.name = name;
    }
    
    public NodeType getNodeType() {
        return NodeType.INSTVARNODE;
    }
    
    public Object accept(final NodeVisitor iVisitor) {
        return iVisitor.visitInstVarNode(this);
    }
    
    public Arity getArity() {
        return Arity.noArguments();
    }
    
    public String getName() {
        return this.name;
    }
    
    public List<Node> childNodes() {
        return InstVarNode.EMPTY_LIST;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    public IRubyObject interpret(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock) {
        final RubyClass cls = self.getMetaClass().getRealClass();
        RubyClass.VariableAccessor localAccessor = this.accessor;
        IRubyObject value;
        if (localAccessor.getClassId() != cls.hashCode()) {
            localAccessor = cls.getVariableAccessorForRead(this.name);
            if (localAccessor == null) {
                return runtime.getNil();
            }
            value = (IRubyObject)localAccessor.get(self);
            this.accessor = localAccessor;
        }
        else {
            value = (IRubyObject)localAccessor.get(self);
        }
        if (value != null) {
            return value;
        }
        if (runtime.isVerbose()) {
            this.warnAboutUninitializedIvar(runtime);
        }
        return runtime.getNil();
    }
    
    private void warnAboutUninitializedIvar(final Ruby runtime) {
        runtime.getWarnings().warning(IRubyWarnings.ID.IVAR_NOT_INITIALIZED, this.getPosition(), "instance variable " + this.name + " not initialized", this.name);
    }
    
    public ByteList definition(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock) {
        return self.getInstanceVariables().fastHasInstanceVariable(this.name) ? InstVarNode.INSTANCE_VARIABLE_BYTELIST : null;
    }
}
