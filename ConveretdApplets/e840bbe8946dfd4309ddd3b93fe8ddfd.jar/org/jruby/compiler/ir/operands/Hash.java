// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.ir.operands;

import org.jruby.Ruby;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.RubyHash;
import org.jruby.interpreter.InterpreterContext;
import java.util.ArrayList;
import org.jruby.compiler.ir.representations.InlinerInfo;
import org.jruby.compiler.ir.IRModule;
import org.jruby.compiler.ir.IRClass;
import java.util.Map;
import java.util.Iterator;
import java.util.List;

public class Hash extends Operand
{
    public final List<KeyValuePair> pairs;
    
    public Hash(final List<KeyValuePair> pairs) {
        this.pairs = pairs;
    }
    
    public boolean isBlank() {
        return this.pairs == null || this.pairs.isEmpty();
    }
    
    public boolean isConstant() {
        for (final KeyValuePair pair : this.pairs) {
            if (!pair.getKey().isConstant() || !pair.getValue().isConstant()) {
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
        for (final KeyValuePair pair : this.pairs) {
            pair.setKey(pair.getKey().getSimplifiedOperand(valueMap));
            pair.setValue(pair.getValue().getSimplifiedOperand(valueMap));
            ++i;
        }
        return this;
    }
    
    public IRClass getTargetClass() {
        return IRModule.getCoreClass("Hash");
    }
    
    public void addUsedVariables(final List<Variable> l) {
        for (final KeyValuePair pair : this.pairs) {
            pair.getKey().addUsedVariables(l);
            pair.getValue().addUsedVariables(l);
        }
    }
    
    public Operand cloneForInlining(final InlinerInfo ii) {
        if (this.isConstant()) {
            return this;
        }
        final List<KeyValuePair> newPairs = new ArrayList<KeyValuePair>();
        for (final KeyValuePair pair : this.pairs) {
            newPairs.add(new KeyValuePair(pair.getKey().cloneForInlining(ii), pair.getValue().cloneForInlining(ii)));
        }
        return new Hash(newPairs);
    }
    
    public Object retrieve(final InterpreterContext interp) {
        final Ruby runtime = interp.getRuntime();
        final RubyHash hash = RubyHash.newHash(runtime);
        for (final KeyValuePair pair : this.pairs) {
            hash.fastASetCheckString(runtime, (IRubyObject)pair.getKey().retrieve(interp), (IRubyObject)pair.getValue().retrieve(interp));
        }
        return hash;
    }
}
