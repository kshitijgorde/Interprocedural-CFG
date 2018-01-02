// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.inwords;

public final class Spanish extends SpanishBase implements ToWords
{
    String getLowName(final int group, final int i) {
        if (i == 21) {
            return (group == 0) ? "vientiuno" : "veinti\u00fan";
        }
        return Spanish.lowName[i];
    }
    
    String wordForOne(final int group) {
        switch (group) {
            case 0: {
                return "uno";
            }
            case 1: {
                return "";
            }
            default: {
                return "un";
            }
        }
    }
    
    public static void main(final String[] args) {
        Test.test(new Spanish(), new DecimalDots());
    }
}
