// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.ir.operands;

import org.jruby.compiler.ir.representations.InlinerInfo;
import java.util.List;
import java.util.Map;

public class CompoundArray extends Operand
{
    Operand _a1;
    Operand _a2;
    
    public CompoundArray(final Operand a1, final Operand a2) {
        this._a1 = a1;
        this._a2 = a2;
    }
    
    public boolean isConstant() {
        return this._a1.isConstant() && this._a2.isConstant();
    }
    
    public String toString() {
        return this._a1 + ", *" + this._a2;
    }
    
    public Operand getSimplifiedOperand(final Map<Operand, Operand> valueMap) {
        this._a1 = this._a1.getSimplifiedOperand(valueMap);
        this._a2 = this._a2.getSimplifiedOperand(valueMap);
        Operand p1 = this._a1;
        if (p1 instanceof Variable) {
            p1 = ((Variable)p1).getValue(valueMap);
        }
        Operand p2 = this._a2;
        if (p2 instanceof Variable) {
            p2 = ((Variable)p2).getValue(valueMap);
        }
        if (p1 instanceof Array && p2 instanceof Array) {
            final Operand[] p1Elts = ((Array)p1).elts;
            final Operand[] p2Elts = ((Array)p2).elts;
            final Operand[] newElts = new Operand[p1Elts.length + p2Elts.length];
            System.arraycopy(p1Elts, 0, newElts, 0, p1Elts.length);
            System.arraycopy(p2Elts, 0, newElts, p1Elts.length, p2Elts.length);
            return new Array(newElts);
        }
        return this;
    }
    
    public Operand fetchCompileTimeArrayElement(final int argIndex, final boolean getSubArray) {
        return null;
    }
    
    public boolean isNonAtomicValue() {
        return true;
    }
    
    public void addUsedVariables(final List<Variable> l) {
        this._a1.addUsedVariables(l);
        this._a2.addUsedVariables(l);
    }
    
    public Operand cloneForInlining(final InlinerInfo ii) {
        return this.isConstant() ? this : new CompoundArray(this._a1.cloneForInlining(ii), this._a2.cloneForInlining(ii));
    }
}
