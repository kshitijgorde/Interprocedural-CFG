// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ast;

import org.jruby.exceptions.JumpException;
import org.jruby.util.ByteList;
import org.jruby.javasupport.util.RuntimeHelpers;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.Ruby;
import org.jruby.lexer.yacc.ISourcePosition;

public class Colon2MethodNode extends Colon2Node
{
    public Colon2MethodNode(final ISourcePosition position, final Node leftNode, final String name) {
        super(position, leftNode, name);
        assert leftNode != null : "class fooBar is not valid";
    }
    
    public IRubyObject interpret(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock) {
        return RuntimeHelpers.invoke(context, this.leftNode.interpret(runtime, context, self, aBlock), this.name, aBlock);
    }
    
    public ByteList definition(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock) {
        try {
            if (this.hasMethod(this.leftNode.interpret(runtime, context, self, aBlock))) {
                return Colon2MethodNode.METHOD_BYTELIST;
            }
        }
        catch (JumpException ex) {}
        return null;
    }
    
    private boolean hasMethod(final IRubyObject left) {
        return left.getMetaClass().isMethodBound(this.name, true);
    }
}
