// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.inwords;

public final class SpanishForCheques extends SpanishBase implements ToWords
{
    String getLowName(final int group, final int i) {
        if (i == 21) {
            return "veinti\u00fan";
        }
        return SpanishForCheques.lowName[i];
    }
    
    String wordForOne(final int group) {
        switch (group) {
            case 0: {
                return "uno";
            }
            default: {
                return "un";
            }
        }
    }
    
    public static void main(final String[] args) {
        Test.test(new SpanishForCheques(), new DecimalDots());
    }
}
