// 
// Decompiled by Procyon v0.5.30
// 

package primes;

import java.util.Iterator;

public class PrimeNumbers implements Iterator
{
    long current;
    
    public PrimeNumbers() {
        this(0L);
    }
    
    public PrimeNumbers(final long lowerBound) {
        this.current = lowerBound;
    }
    
    public Object next() {
        while (!this.isPrime(this.current)) {
            ++this.current;
        }
        ++this.current;
        return new Long(this.current - 1);
    }
    
    public boolean isPrime(final long n) {
        if (n < 2) {
            return false;
        }
        for (long i = 2L; i <= (long)Math.sqrt(n * 1.0); ++i) {
            if (n == i * (n / i)) {
                return false;
            }
        }
        return true;
    }
    
    public void remove() {
        throw new UnsupportedOperationException("The PrimeNumbers class does not support the remove method.");
    }
    
    public boolean hasNext() {
        return true;
    }
    
    public static void main(final String[] args) {
        final PrimeNumbers primeNumbers1 = new PrimeNumbers(1000000000000L);
        for (int i = 0; i < 100; ++i) {
            System.out.println(primeNumbers1.next());
        }
    }
}
