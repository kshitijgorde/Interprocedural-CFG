// 
// Decompiled by Procyon v0.5.30
// 

package com.zylom.cipher;

import java.util.Random;

public class CipherInteger extends CipherObject implements Cloneable
{
    private int value;
    
    public CipherInteger() {
        this(0);
    }
    
    public CipherInteger(final int value) {
        this.setValue(value);
    }
    
    private CipherInteger(final int value, final int currentCryptIndex, final long n) {
        this.value = value;
        super.currentCryptIndex = currentCryptIndex;
        super.random = new Random(n);
    }
    
    public static boolean cipherMatch(final int n, final String s) {
        if (s != null) {
            final int lastIndex = s.lastIndexOf(105);
            if (lastIndex > 0) {
                try {
                    final int int1 = Integer.parseInt(s.substring(lastIndex + 1));
                    return n == (Integer.parseInt(s.substring(0, lastIndex)) ^ CipherObject.staticState[int1] + (CipherObject.staticState[(int1 + 1) % 256] << 8) + (CipherObject.staticState[(int1 + 2) % 256] << 16) + (CipherObject.staticState[(int1 + 3) % 256] << 24));
                }
                catch (Exception ex) {}
            }
        }
        return false;
    }
    
    public String getCipherValue() {
        return String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.getValue() ^ CipherObject.staticState[super.currentCryptIndex] + (CipherObject.staticState[(super.currentCryptIndex + 1) % 256] << 8) + (CipherObject.staticState[(super.currentCryptIndex + 2) % 256] << 16) + (CipherObject.staticState[(super.currentCryptIndex + 3) % 256] << 24)))).append("i").append(super.currentCryptIndex)));
    }
    
    public int getValue() {
        return this.value ^ super.state[super.currentCryptIndex] + (super.state[(super.currentCryptIndex + 1) % 256] << 8) + (super.state[(super.currentCryptIndex + 2) % 256] << 16) + (super.state[(super.currentCryptIndex + 3) % 256] << 24);
    }
    
    public void setValue(final int n) {
        super.currentCryptIndex = (int)(super.random.nextFloat() * 256.0f);
        this.value = (n ^ super.state[super.currentCryptIndex] + (super.state[(super.currentCryptIndex + 1) % 256] << 8) + (super.state[(super.currentCryptIndex + 2) % 256] << 16) + (super.state[(super.currentCryptIndex + 3) % 256] << 24));
    }
    
    public Object clone() throws CloneNotSupportedException {
        return new CipherInteger(this.value, super.currentCryptIndex, super.seed);
    }
}
