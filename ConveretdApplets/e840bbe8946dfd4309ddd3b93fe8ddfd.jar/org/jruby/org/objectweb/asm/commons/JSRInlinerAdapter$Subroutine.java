// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.org.objectweb.asm.commons;

import java.util.BitSet;

public class JSRInlinerAdapter$Subroutine
{
    public final BitSet instructions;
    
    protected JSRInlinerAdapter$Subroutine() {
        this.instructions = new BitSet();
    }
    
    public void addInstruction(final int n) {
        this.instructions.set(n);
    }
    
    public boolean ownsInstruction(final int n) {
        return this.instructions.get(n);
    }
    
    public String toString() {
        return "Subroutine: " + this.instructions;
    }
}
