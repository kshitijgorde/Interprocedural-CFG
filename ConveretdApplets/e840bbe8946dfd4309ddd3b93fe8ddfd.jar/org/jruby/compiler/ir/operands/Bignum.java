// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.ir.operands;

import org.jruby.RubyBignum;
import org.jruby.interpreter.InterpreterContext;
import java.math.BigInteger;

public class Bignum extends Constant
{
    public final BigInteger value;
    
    public Bignum(final BigInteger value) {
        this.value = value;
    }
    
    public Object retrieve(final InterpreterContext interp) {
        if (this.cachedValue == null) {
            this.cachedValue = RubyBignum.newBignum(interp.getRuntime(), this.value);
        }
        return this.cachedValue;
    }
}
