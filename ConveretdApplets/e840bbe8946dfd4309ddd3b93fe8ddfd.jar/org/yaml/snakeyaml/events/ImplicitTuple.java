// 
// Decompiled by Procyon v0.5.30
// 

package org.yaml.snakeyaml.events;

public class ImplicitTuple
{
    private final boolean plain;
    private final boolean nonPlain;
    
    public ImplicitTuple(final boolean plain, final boolean nonplain) {
        this.plain = plain;
        this.nonPlain = nonplain;
    }
    
    public boolean isFirst() {
        return this.plain;
    }
    
    public boolean isSecond() {
        return this.nonPlain;
    }
    
    public boolean bothFalse() {
        return !this.plain && !this.nonPlain;
    }
    
    public String toString() {
        return "implicit=[" + this.plain + ", " + this.nonPlain + "]";
    }
}
