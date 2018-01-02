// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.parser.interpreter.builtin;

import com.stonewall.parser.interpreter.Dispatcher;
import com.stonewall.parser.interpreter.Interpreter;
import com.stonewall.parser.Function;

class BuiltinContext extends Function.Context
{
    BuiltinContext(final Builtin builtin, final Interpreter interpreter) {
        super(builtin, interpreter);
    }
    
    @Override
    public Interpreter interpreter() {
        return this.interpreter;
    }
}
