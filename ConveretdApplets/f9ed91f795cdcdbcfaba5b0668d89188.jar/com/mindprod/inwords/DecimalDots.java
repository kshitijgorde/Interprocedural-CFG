// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.inwords;

public final class DecimalDots extends DecimalBase implements ToWords
{
    public static final String EMBEDDED_COPYRIGHT = "Copyright: (c) 1999-2011 Roedy Green, Canadian Mind Products, http://mindprod.com";
    
    public String toWords(final long num) {
        return super.toWords(num, '.');
    }
    
    public static void main(final String[] args) {
        Test.test(new DecimalDots(), null);
    }
}
