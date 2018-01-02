// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.util;

import java.util.Arrays;

public abstract class ArrayUtils
{
    public static float[][] clone(final float[][] array) {
        if (array == null) {
            return null;
        }
        final float[][] array2 = new float[array.length][];
        System.arraycopy(array, 0, array2, 0, array.length);
        for (int i = 0; i < array.length; ++i) {
            final float[] array3 = array[i];
            final float[] array4 = new float[array3.length];
            System.arraycopy(array3, 0, array4, 0, array3.length);
            array2[i] = array4;
        }
        return array2;
    }
    
    public static boolean equal(final float[][] array, final float[][] array2) {
        if (array == null) {
            return array2 == null;
        }
        if (array2 == null) {
            return false;
        }
        if (array.length != array2.length) {
            return false;
        }
        for (int i = 0; i < array.length; ++i) {
            if (!Arrays.equals(array[i], array2[i])) {
                return false;
            }
        }
        return true;
    }
}
