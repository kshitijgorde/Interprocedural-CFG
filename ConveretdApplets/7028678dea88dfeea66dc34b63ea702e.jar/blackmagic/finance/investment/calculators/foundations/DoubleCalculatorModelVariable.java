// 
// Decompiled by Procyon v0.5.30
// 

package blackmagic.finance.investment.calculators.foundations;

public class DoubleCalculatorModelVariable extends CalculatorModelVariable
{
    private int vPrecisionValue;
    
    public DoubleCalculatorModelVariable(final CalculatorModel calculatorModel, final int n) {
        super(calculatorModel, n);
        this.vPrecisionValue = 0;
        this.setDouble(0.0);
    }
    
    public void setPrecisionValue(final int vPrecisionValue) {
        this.vPrecisionValue = vPrecisionValue;
    }
    
    public void setDouble(final double n) {
        this.setValue(new Double(this.round(n)));
    }
    
    public double getDouble() {
        final Double n = (Double)this.getValue();
        return (n != null) ? n : 0.0;
    }
    
    private double round(final double n) {
        if (this.vPrecisionValue == 0) {
            return n;
        }
        return Math.round(n * this.vPrecisionValue) / this.vPrecisionValue;
    }
}
