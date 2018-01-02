// 
// Decompiled by Procyon v0.5.30
// 

package EDU.oswego.cs.dl.util.concurrent.misc;

import java.io.Serializable;

public class Fraction implements Cloneable, Comparable, Serializable
{
    protected final long numerator_;
    protected final long denominator_;
    
    public Fraction(final long n, final long n2) {
        final boolean b = n >= 0L;
        final boolean b2 = n2 >= 0L;
        final long n3 = b ? n : (-n);
        final long n4 = b2 ? n2 : (-n2);
        final long gcd = gcd(n3, n4);
        this.numerator_ = ((b == b2) ? (n3 / gcd) : (-n3 / gcd));
        this.denominator_ = n4 / gcd;
    }
    
    public Fraction(final Fraction fraction) {
        this.numerator_ = fraction.numerator();
        this.denominator_ = fraction.denominator();
    }
    
    public double asDouble() {
        return this.numerator() / this.denominator();
    }
    
    public Object clone() {
        return new Fraction(this);
    }
    
    public int compareTo(final long n) {
        final long numerator = this.numerator();
        final long denominator = this.denominator();
        final long n2 = numerator * 1L;
        final long n3 = n * denominator;
        return (n2 < n3) ? -1 : ((n2 == n3) ? false : true);
    }
    
    public int compareTo(final Object o) {
        final Fraction fraction = (Fraction)o;
        final long numerator = this.numerator();
        final long denominator = this.denominator();
        final long numerator2 = fraction.numerator();
        final long n = numerator * fraction.denominator();
        final long n2 = numerator2 * denominator;
        return (n < n2) ? -1 : ((n == n2) ? false : true);
    }
    
    public final long denominator() {
        return this.denominator_;
    }
    
    public Fraction dividedBy(final long n) {
        return new Fraction(this.numerator() * 1L, this.denominator() * n);
    }
    
    public Fraction dividedBy(final Fraction fraction) {
        return new Fraction(this.numerator() * fraction.denominator(), this.denominator() * fraction.numerator());
    }
    
    public boolean equals(final long n) {
        return this.compareTo(n) == 0;
    }
    
    public boolean equals(final Object o) {
        return this.compareTo(o) == 0;
    }
    
    public static long gcd(long n, long n2) {
        if (n < 0L) {
            n = -n;
        }
        if (n2 < 0L) {
            n2 = -n2;
        }
        long n3;
        long n4;
        if (n >= n2) {
            n3 = n;
            n4 = n2;
        }
        else {
            n3 = n2;
            n4 = n;
        }
        while (n4 != 0L) {
            final long n5 = n3 % n4;
            n3 = n4;
            n4 = n5;
        }
        return n3;
    }
    
    public int hashCode() {
        return (int)(this.numerator_ ^ this.denominator_);
    }
    
    public Fraction inverse() {
        return new Fraction(this.denominator(), this.numerator());
    }
    
    public Fraction minus(final long n) {
        final long numerator = this.numerator();
        final long denominator = this.denominator();
        final long n2 = 1L;
        return new Fraction(numerator * n2 - n * denominator, denominator * n2);
    }
    
    public Fraction minus(final Fraction fraction) {
        final long numerator = this.numerator();
        final long denominator = this.denominator();
        final long numerator2 = fraction.numerator();
        final long denominator2 = fraction.denominator();
        return new Fraction(numerator * denominator2 - numerator2 * denominator, denominator * denominator2);
    }
    
    public Fraction negative() {
        return new Fraction(-this.numerator(), this.denominator());
    }
    
    public final long numerator() {
        return this.numerator_;
    }
    
    public Fraction plus(final long n) {
        final long numerator = this.numerator();
        final long denominator = this.denominator();
        final long n2 = 1L;
        return new Fraction(numerator * n2 + n * denominator, denominator * n2);
    }
    
    public Fraction plus(final Fraction fraction) {
        final long numerator = this.numerator();
        final long denominator = this.denominator();
        final long numerator2 = fraction.numerator();
        final long denominator2 = fraction.denominator();
        return new Fraction(numerator * denominator2 + numerator2 * denominator, denominator * denominator2);
    }
    
    public Fraction times(final long n) {
        return new Fraction(this.numerator() * n, this.denominator() * 1L);
    }
    
    public Fraction times(final Fraction fraction) {
        return new Fraction(this.numerator() * fraction.numerator(), this.denominator() * fraction.denominator());
    }
    
    public String toString() {
        if (this.denominator() == 1L) {
            return String.valueOf(this.numerator());
        }
        return String.valueOf(this.numerator()) + "/" + this.denominator();
    }
}
