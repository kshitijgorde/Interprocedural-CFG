// 
// Decompiled by Procyon v0.5.30
// 

package com.zylom.cipher;

import java.util.Random;

public abstract class CipherObject
{
    final byte[] state;
    static final byte[] staticState;
    long seed;
    Random random;
    Random staticRandom;
    int currentCryptIndex;
    static String superSeed;
    static boolean inited;
    
    public CipherObject() {
        this.state = new byte[256];
        this.seed = System.currentTimeMillis();
        this.random = new Random(this.seed);
        this.staticRandom = new Random(5492123L);
        int n = 0;
        int n2 = 0;
        final byte[] array = new byte[256];
        this.random.nextBytes(array);
        final byte[] array2 = new byte[256];
        this.staticRandom.nextBytes(array2);
        for (int i = 0; i < 256; ++i) {
            this.state[i] = (byte)i;
            if (!CipherObject.inited) {
                CipherObject.staticState[i] = (byte)i;
            }
        }
        for (int j = 0; j < 256; ++j) {
            final byte b = this.state[j];
            n2 = (n2 + array[n] + b & 0xFF);
            final byte b2 = this.state[n2];
            this.state[n2] = (byte)(b & 0xFF);
            this.state[j] = (byte)(b2 & 0xFF);
            if (++n >= array.length) {
                n = 0;
            }
        }
        if (!CipherObject.inited) {
            int n3 = 0;
            int n4 = 0;
            for (int k = 0; k < 256; ++k) {
                final byte b3 = CipherObject.staticState[k];
                n3 = (n3 + array2[n4] + b3 & 0xFF);
                final byte b4 = CipherObject.staticState[n3];
                CipherObject.staticState[n3] = (byte)(b3 & 0xFF);
                CipherObject.staticState[k] = (byte)(b4 & 0xFF);
                if (++n4 >= array2.length) {
                    n4 = 0;
                }
            }
            CipherObject.inited = true;
        }
    }
    
    public void init(final byte[] array) {
        if (array != null && array.length > 0) {
            int n = 0;
            int n2 = 0;
            for (int i = 0; i < 256; ++i) {
                this.state[i] = (byte)i;
            }
            for (int j = 0; j < 256; ++j) {
                final byte b = this.state[j];
                n2 = (n2 + array[n] + b & 0xFF);
                final byte b2 = this.state[n2];
                this.state[n2] = (byte)(b & 0xFF);
                this.state[j] = (byte)(b2 & 0xFF);
                if (++n >= array.length) {
                    n = 0;
                }
            }
        }
    }
    
    public void init(final long n, final String superSeed) {
        CipherObject.superSeed = superSeed;
    }
    
    public void init(final String s) {
        final byte[] array = new byte[256];
        long n = 0L;
        if (s != null) {
            for (int i = 0; i < s.length(); ++i) {
                n += s.charAt(i);
            }
        }
        new Random(n).nextBytes(array);
        this.init(array);
    }
    
    static {
        staticState = new byte[256];
    }
}
