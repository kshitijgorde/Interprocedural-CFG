// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.ir.operands;

import org.jruby.RubyString;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.interpreter.InterpreterContext;
import org.jruby.compiler.ir.representations.InlinerInfo;
import java.util.Map;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;

public class BacktickString extends Operand
{
    public final List<Operand> pieces;
    
    public BacktickString(final Operand val) {
        (this.pieces = new ArrayList<Operand>()).add(val);
    }
    
    public BacktickString(final List<Operand> pieces) {
        this.pieces = pieces;
    }
    
    public boolean isConstant() {
        for (final Operand o : this.pieces) {
            if (!o.isConstant()) {
                return false;
            }
        }
        return true;
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
        return new BacktickString(newPieces);
    }
    
    public Object retrieve(final InterpreterContext interp) {
        final RubyString newString = interp.getRuntime().newString();
        for (final Operand p : this.pieces) {
            newString.append((IRubyObject)p.retrieve(interp));
        }
        return ((IRubyObject)interp.getSelf()).callMethod(interp.getContext(), "`", newString);
    }
}
