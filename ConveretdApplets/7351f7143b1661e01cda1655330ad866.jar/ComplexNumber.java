// 
// Decompiled by Procyon v0.5.30
// 

class ComplexNumber
{
    double a;
    double b;
    
    ComplexNumber(final double a, final double b) {
        this.a = a;
        this.b = b;
    }
    
    ComplexNumber(final double a) {
        this.a = a;
        this.b = 0.0;
    }
    
    ComplexNumber() {
        this.a = 0.0;
        this.b = 0.0;
    }
    
    ComplexNumber plus(final ComplexNumber complexNumber) {
        return new ComplexNumber(this.a + complexNumber.a, this.b + complexNumber.b);
    }
    
    ComplexNumber minus(final ComplexNumber complexNumber) {
        return new ComplexNumber(this.a - complexNumber.a, this.b - complexNumber.b);
    }
    
    ComplexNumber ominus() {
        return new ComplexNumber(-this.a, -this.b);
    }
    
    ComplexNumber sqrt() {
        final double sqrt = Math.sqrt(Math.sqrt(this.a * this.a + this.b * this.b));
        double n;
        if (this.a >= 0.0) {
            n = Math.atan(this.b / this.a) / 2.0;
        }
        else if (this.b >= 0.0) {
            n = (Math.atan(this.b / this.a) + 3.141592653589793) / 2.0;
        }
        else {
            n = (Math.atan(this.b / this.a) - 3.141592653589793) / 2.0;
        }
        return new ComplexNumber(sqrt * Math.cos(n), sqrt * Math.sin(n));
    }
}
