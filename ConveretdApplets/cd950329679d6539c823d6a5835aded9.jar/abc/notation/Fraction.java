// 
// Decompiled by Procyon v0.5.30
// 

package abc.notation;

public class Fraction
{
    private float numerator;
    private int denominator;
    
    public Fraction(final int numeratorValue, final int denominatorValue) throws IllegalArgumentException {
        this.numerator = 0.0f;
        this.denominator = 1;
        this.setNumerator(numeratorValue);
        this.setDenominator(denominatorValue);
    }
    
    public void setNumerator(final int numeratorValue) {
        this.numerator = numeratorValue;
    }
    
    public int getNumerator() {
        return (int)this.numerator;
    }
    
    public void setDenominator(final int denominatorValue) throws IllegalArgumentException {
        if (this.denominator != 0) {
            this.denominator = denominatorValue;
            return;
        }
        throw new IllegalArgumentException("Denominator can't be equal to 0 !");
    }
    
    public int getDenominator() {
        return this.denominator;
    }
    
    public float floatValue() {
        return this.numerator / this.denominator;
    }
    
    public float multipliedBy(final Fraction fraction) {
        final float newNumerator = this.numerator * fraction.getNumerator();
        final float newDenominator = this.denominator * fraction.getDenominator();
        return newNumerator / newDenominator;
    }
    
    public String toString() {
        if (this.denominator == 1) {
            return new Integer((int)this.numerator).toString();
        }
        return new Integer((int)this.numerator).toString() + "/" + new Integer(this.denominator).toString();
    }
}
