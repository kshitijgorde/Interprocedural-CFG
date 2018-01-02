// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.ir.operands;

public abstract class Reference extends Operand
{
    private final String name;
    
    public Reference(final String name) {
        this.name = name;
    }
    
    public String getName() {
        return this.name;
    }
    
    public String toString() {
        return this.name;
    }
}
