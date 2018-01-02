// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.ir.operands;

public class KeyValuePair
{
    private Operand key;
    private Operand value;
    
    public KeyValuePair(final Operand key, final Operand value) {
        this.key = key;
        this.value = value;
    }
    
    public Operand getKey() {
        return this.key;
    }
    
    public Operand getValue() {
        return this.value;
    }
    
    public void setKey(final Operand key) {
        this.key = key;
    }
    
    public void setValue(final Operand value) {
        this.value = value;
    }
}
