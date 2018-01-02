// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.ir.operands;

import org.jruby.interpreter.InterpreterContext;
import org.jruby.compiler.ir.IRModule;
import org.jruby.compiler.ir.IRClass;
import org.jruby.util.ByteList;

public class StringLiteral extends Constant
{
    public final ByteList _bl_value;
    public final String _str_value;
    
    public StringLiteral(final ByteList val) {
        this._bl_value = val;
        this._str_value = this._bl_value.toString();
    }
    
    public StringLiteral(final String s) {
        this._bl_value = ByteList.create(s);
        this._str_value = s;
    }
    
    public String toString() {
        return "\"" + this._str_value + "\"";
    }
    
    public IRClass getTargetClass() {
        return IRModule.getCoreClass("String");
    }
    
    public Object retrieve(final InterpreterContext interp) {
        return interp.getRuntime().newString(this._str_value);
    }
}
