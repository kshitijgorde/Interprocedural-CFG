// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.ir.operands;

import org.jruby.interpreter.InterpreterContext;

public class Symbol extends Reference
{
    public Symbol(final String name) {
        super(name);
    }
    
    public Object retrieve(final InterpreterContext interp) {
        return interp.getRuntime().newSymbol(this.getName());
    }
}
