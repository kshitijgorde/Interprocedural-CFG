// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ast;

import org.jruby.runtime.CallSite;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.Ruby;
import org.jruby.lexer.yacc.ISourcePosition;

public class AttrAssignTwoArgNode extends AttrAssignNode
{
    private Node arg1;
    private Node arg2;
    
    public AttrAssignTwoArgNode(final ISourcePosition position, final Node receiverNode, final String name, final ArrayNode argsNode) {
        super(position, receiverNode, name, argsNode);
        assert argsNode.size() == 2 : "argsNode.size() is 2";
        this.arg1 = argsNode.get(0);
        this.arg2 = argsNode.get(1);
    }
    
    public IRubyObject interpret(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock) {
        final IRubyObject receiver = this.receiverNode.interpret(runtime, context, self, aBlock);
        final IRubyObject param1 = this.arg1.interpret(runtime, context, self, aBlock);
        final IRubyObject param2 = this.arg2.interpret(runtime, context, self, aBlock);
        assert AttrAssignNode.hasMetaClass(receiver) : AttrAssignNode.receiverClassName(receiver);
        final CallSite callSite = this.selectCallSite(self, receiver);
        callSite.call(context, self, receiver, param1, param2);
        return param2;
    }
    
    public IRubyObject assign(final Ruby runtime, final ThreadContext context, final IRubyObject self, final IRubyObject value, final Block aBlock, final boolean checkArity) {
        final IRubyObject receiver = this.receiverNode.interpret(runtime, context, self, aBlock);
        final IRubyObject param1 = this.arg1.interpret(runtime, context, self, aBlock);
        final IRubyObject param2 = this.arg2.interpret(runtime, context, self, aBlock);
        assert AttrAssignNode.hasMetaClass(receiver) : AttrAssignNode.receiverClassName(receiver);
        final CallSite callSite = this.selectCallSite(self, receiver);
        callSite.call(context, self, receiver, param1, param2, value);
        return runtime.getNil();
    }
}
