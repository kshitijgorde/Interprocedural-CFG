// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.ir.operands;

public class Backref extends Operand
{
    public final char _type;
    
    public Backref(final char t) {
        this._type = t;
    }
    
    public String toString() {
        return "$" + this._type;
    }
}
