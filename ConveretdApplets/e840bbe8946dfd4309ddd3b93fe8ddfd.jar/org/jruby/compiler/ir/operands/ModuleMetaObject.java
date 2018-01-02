// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.ir.operands;

import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.RubyModule;
import org.jruby.parser.StaticScope;
import org.jruby.interpreter.InterpreterContext;
import org.jruby.compiler.ir.IRScope;
import org.jruby.compiler.ir.IRModule;

public class ModuleMetaObject extends MetaObject
{
    protected ModuleMetaObject(final IRModule scope) {
        super(scope);
    }
    
    public boolean isModule() {
        return true;
    }
    
    public Object retrieve(final InterpreterContext interp) {
        final StaticScope ssc = this.scope.getStaticScope();
        return (ssc == null) ? null : ssc.getModule();
    }
    
    public Object store(final InterpreterContext interp, final Object value) {
        final RubyModule container = (RubyModule)this.scope.getContainer().retrieve(interp);
        container.setConstant(this.scope.getName(), (IRubyObject)value);
        this.scope.getStaticScope().setModule((RubyModule)value);
        return value;
    }
}
