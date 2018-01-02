// 
// Decompiled by Procyon v0.5.30
// 

package com.q10.util;

public class Financeira
{
    public static float acumulado(final float[] array) {
        float n = array[0] / 100.0f + 1.0f;
        for (int i = 1; i < array.length; ++i) {
            n *= array[i] / 100.0f + 1.0f;
        }
        return (n - 1.0f) * 100.0f;
    }
    
    public static float variacao(final float n, final float n2) {
        return (n2 - n) / n * 100.0f;
    }
}
