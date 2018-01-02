// 
// Decompiled by Procyon v0.5.30
// 

package anon.crypto;

import java.security.SecureRandom;
import java.util.Random;

public final class MyRandom
{
    Random m_TheRandom;
    
    public MyRandom(final Random theRandom) {
        this.m_TheRandom = theRandom;
    }
    
    public MyRandom() {
        this(new SecureRandom());
    }
    
    public int nextInt(final int n) {
        final int n2 = Integer.MAX_VALUE;
        int i;
        do {
            i = (this.m_TheRandom.nextInt() & Integer.MAX_VALUE);
        } while (i >= n2 - n2 % n);
        return i % n;
    }
    
    public Random getRandSource() {
        return this.m_TheRandom;
    }
}
