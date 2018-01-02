// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.ir.operands;

import org.jruby.interpreter.InterpreterContext;
import java.util.ArrayList;
import org.jruby.compiler.ir.representations.InlinerInfo;
import java.util.Map;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class CompoundString extends Operand
{
    public final List<Operand> pieces;
    
    public CompoundString(final List<Operand> pieces) {
        this.pieces = pieces;
    }
    
    public boolean isConstant() {
        if (this.pieces != null) {
            for (final Operand o : this.pieces) {
                if (!o.isConstant()) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public String toString() {
        return "COMPOUND_STRING" + ((this.pieces == null) ? "" : Arrays.toString(this.pieces.toArray()));
    }
    
    public boolean isNonAtomicValue() {
        return true;
    }
    
    public Operand getSimplifiedOperand(final Map<Operand, Operand> valueMap) {
        int i = 0;
        for (final Operand p : this.pieces) {
            this.pieces.set(i, p.getSimplifiedOperand(valueMap));
            ++i;
        }
        return this;
    }
    
    public void addUsedVariables(final List<Variable> l) {
        for (final Operand o : this.pieces) {
            o.addUsedVariables(l);
        }
    }
    
    public Operand cloneForInlining(final InlinerInfo ii) {
        if (this.isConstant()) {
            return this;
        }
        final List<Operand> newPieces = new ArrayList<Operand>();
        for (final Operand p : this.pieces) {
            newPieces.add(p.cloneForInlining(ii));
        }
        return new CompoundString(newPieces);
    }
    
    public Object retrieve(final InterpreterContext interp) {
        final StringBuilder buf = new StringBuilder();
        for (final Operand p : this.pieces) {
            buf.append(p.retrieve(interp));
        }
        return interp.getRuntime().newString(buf.toString());
    }
}
