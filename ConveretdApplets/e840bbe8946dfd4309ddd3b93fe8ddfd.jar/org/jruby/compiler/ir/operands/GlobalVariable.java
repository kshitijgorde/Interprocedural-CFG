// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.ir.operands;

import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.compiler.ir.Interp;
import org.jruby.interpreter.InterpreterContext;
import org.jruby.compiler.ir.representations.InlinerInfo;

public class GlobalVariable extends Variable
{
    public final String name;
    
    public GlobalVariable(final String name) {
        this.name = name;
    }
    
    public Operand cloneForInlining(final InlinerInfo ii) {
        return this;
    }
    
    public String getName() {
        return this.name;
    }
    
    public int compareTo(final Object arg0) {
        if (!(arg0 instanceof GlobalVariable)) {
            return 0;
        }
        return this.name.compareTo(((GlobalVariable)arg0).name);
    }
    
    @Interp
    public Object retrieve(final InterpreterContext interp) {
        return interp.getRuntime().getGlobalVariables().get(this.getName());
    }
    
    @Interp
    public Object store(final InterpreterContext interp, final Object value) {
        return interp.getRuntime().getGlobalVariables().set(this.getName(), (IRubyObject)value);
    }
}
