// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.ir.operands;

import org.jruby.RubyRegexp;
import org.jruby.RubyString;
import org.jruby.interpreter.InterpreterContext;
import org.jruby.compiler.ir.representations.InlinerInfo;
import java.util.List;
import java.util.Map;
import org.jruby.util.RegexpOptions;

public class Regexp extends Operand
{
    public final RegexpOptions options;
    Operand regexp;
    
    public Regexp(final Operand regexp, final RegexpOptions options) {
        this.regexp = regexp;
        this.options = options;
    }
    
    public boolean isConstant() {
        return this.regexp.isConstant();
    }
    
    public String toString() {
        return "RE:|" + this.regexp + "|" + this.options;
    }
    
    public boolean isNonAtomicValue() {
        return true;
    }
    
    public Operand getSimplifiedOperand(final Map<Operand, Operand> valueMap) {
        this.regexp = this.regexp.getSimplifiedOperand(valueMap);
        return this;
    }
    
    public void addUsedVariables(final List<Variable> l) {
        this.regexp.addUsedVariables(l);
    }
    
    public Operand cloneForInlining(final InlinerInfo ii) {
        return this.isConstant() ? this : new Regexp(this.regexp.cloneForInlining(ii), this.options);
    }
    
    public Object retrieve(final InterpreterContext interp) {
        final RubyRegexp reg = RubyRegexp.newRegexp(interp.getRuntime(), ((RubyString)this.regexp.retrieve(interp)).getByteList(), this.options);
        reg.setLiteral();
        return reg;
    }
}
