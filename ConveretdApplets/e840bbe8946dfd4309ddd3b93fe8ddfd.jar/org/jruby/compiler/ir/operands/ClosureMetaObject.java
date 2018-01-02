// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.ir.operands;

import org.jruby.runtime.Binding;
import org.jruby.runtime.BlockBody;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.interpreter.InterpreterContext;
import org.jruby.compiler.ir.IRScope;
import org.jruby.compiler.ir.IRClosure;

public class ClosureMetaObject extends MetaObject
{
    protected ClosureMetaObject(final IRClosure scope) {
        super(scope);
    }
    
    public boolean isClosure() {
        return true;
    }
    
    public Object retrieve(final InterpreterContext interp) {
        final BlockBody body = ((IRClosure)this.scope).getBlockBody();
        this.scope.getStaticScope().determineModule();
        final Binding binding = interp.getContext().currentBinding((IRubyObject)interp.getSelf(), interp.getSharedBindingScope());
        return new Block(body, binding);
    }
}
