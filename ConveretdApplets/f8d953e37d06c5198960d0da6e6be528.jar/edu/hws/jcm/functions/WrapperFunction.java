// 
// Decompiled by Procyon v0.5.30
// 

package edu.hws.jcm.functions;

import edu.hws.jcm.data.StackOfDouble;
import edu.hws.jcm.data.Cases;
import edu.hws.jcm.data.Variable;
import edu.hws.jcm.data.Function;

public class WrapperFunction extends FunctionParserExtension
{
    private Function func;
    private double[] params;
    private int serialNumber;
    private WrapperFunction derivativeOf;
    private Variable derivativeVar;
    private int derivativeIndex;
    
    public WrapperFunction(final Function function) {
        this.setFunction(function);
        this.serialNumber = 0;
    }
    
    private void check() {
        if (this.derivativeOf == null || this.derivativeOf.serialNumber == this.serialNumber) {
            return;
        }
        this.serialNumber = this.derivativeOf.serialNumber;
        if (this.derivativeVar != null) {
            this.func = this.derivativeOf.derivative(this.derivativeVar);
        }
        else {
            this.func = this.derivativeOf.derivative(this.derivativeIndex);
        }
    }
    
    public void setFunction(final Function func) {
        if (func == null) {
            throw new IllegalArgumentException("Function supplied to WrapperFunction object can't be null.");
        }
        if (this.func != null && func.getArity() != this.func.getArity()) {
            throw new IllegalArgumentException("Attempt to change the arity of a WrapperFunction.");
        }
        if (this.derivativeOf != null) {
            throw new IllegalArgumentException("Can't change the definition of a function that is a derivative of another function.");
        }
        this.func = func;
        this.params = new double[func.getArity()];
        ++this.serialNumber;
    }
    
    public Function getFunction() {
        return this.func;
    }
    
    public int getArity() {
        return this.func.getArity();
    }
    
    public double getVal(final double[] array) {
        this.check();
        return this.func.getValueWithCases(array, null);
    }
    
    public double getValueWithCases(final double[] array, final Cases cases) {
        this.check();
        return (this.func == null) ? 1.0 : this.func.getValueWithCases(array, cases);
    }
    
    public Function derivative(final int derivativeIndex) {
        this.check();
        final WrapperFunction wrapperFunction = new WrapperFunction(this.func.derivative(derivativeIndex));
        wrapperFunction.derivativeOf = this;
        wrapperFunction.derivativeIndex = derivativeIndex;
        wrapperFunction.serialNumber = this.serialNumber;
        return wrapperFunction;
    }
    
    public Function derivative(final Variable derivativeVar) {
        this.check();
        final WrapperFunction wrapperFunction = new WrapperFunction(this.func.derivative(derivativeVar));
        wrapperFunction.derivativeOf = this;
        wrapperFunction.derivativeVar = derivativeVar;
        wrapperFunction.serialNumber = this.serialNumber;
        return wrapperFunction;
    }
    
    public boolean dependsOn(final Variable variable) {
        this.check();
        return this.func.dependsOn(variable);
    }
    
    public void apply(final StackOfDouble stackOfDouble, final Cases cases) {
        this.check();
        for (int i = this.params.length - 1; i >= 0; --i) {
            this.params[i] = stackOfDouble.pop();
        }
        stackOfDouble.push(this.getValueWithCases(this.params, cases));
    }
}
