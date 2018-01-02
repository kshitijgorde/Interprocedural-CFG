// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.ir.operands;

import org.jruby.parser.StaticScope;
import org.jruby.interpreter.InterpreterContext;
import org.jruby.compiler.ir.IRModule;
import org.jruby.compiler.ir.IRClass;

public class ClassMetaObject extends ModuleMetaObject
{
    protected ClassMetaObject(final IRClass scope) {
        super(scope);
    }
    
    public boolean isClass() {
        return true;
    }
    
    public Object retrieve(final InterpreterContext interp) {
        final StaticScope ssc = this.scope.getStaticScope();
        return (ssc == null) ? null : ssc.getModule();
    }
}
