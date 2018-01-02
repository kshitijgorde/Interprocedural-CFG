// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.ir.operands;

import org.jruby.compiler.ir.representations.InlinerInfo;

public class Label extends Operand
{
    public final String _label;
    private int targetPC;
    public static int index;
    
    public Label(final String l) {
        this.targetPC = -1;
        this._label = l;
    }
    
    public String toString() {
        return this._label;
    }
    
    public int hashCode() {
        return this._label.hashCode();
    }
    
    public boolean equals(final Object o) {
        return o instanceof Label && this._label.equals(((Label)o)._label);
    }
    
    public Operand cloneForInlining(final InlinerInfo ii) {
        return ii.getRenamedLabel(this);
    }
    
    public void setTargetPC(final int i) {
        this.targetPC = i;
    }
    
    public int getTargetPC() {
        return this.targetPC;
    }
    
    static {
        Label.index = 0;
    }
}
