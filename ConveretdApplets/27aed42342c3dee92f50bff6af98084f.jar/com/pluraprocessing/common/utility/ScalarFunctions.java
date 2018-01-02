// 
// Decompiled by Procyon v0.5.30
// 

package com.pluraprocessing.common.utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.io.IOException;

public class ScalarFunctions
{
    public static float webComputeArraySum(final String[] arrayString) throws IOException {
        float sum = 0.0f;
        for (int i = 0; i < arrayString.length; ++i) {
            sum += Float.valueOf(arrayString[i]);
        }
        return sum;
    }
    
    public static HashMap<String, Float> webComputeArraySumByNodeType(final String[] arrayPercentages, final String[] arrayNodeTypes) throws IOException {
        final HashMap<String, Float> hmPercentages = new HashMap<String, Float>();
        for (int i = 0; i < arrayPercentages.length; ++i) {
            if (!hmPercentages.containsKey(arrayNodeTypes[i])) {
                hmPercentages.put(arrayNodeTypes[i], Float.valueOf(arrayPercentages[i]));
            }
            else {
                hmPercentages.put(arrayNodeTypes[i], Float.valueOf(hmPercentages.get(arrayNodeTypes[i]).toString()) + Float.valueOf(arrayPercentages[i]));
            }
        }
        return hmPercentages;
    }
    
    public static ArrayList<String> webConvertStringArrayToArrayList(final String[] arrayString) {
        final ArrayList<String> result = new ArrayList<String>();
        for (int i = 0; i < arrayString.length; ++i) {
            result.add(arrayString[i]);
        }
        return result;
    }
    
    public static String RemoveNonAlphanumeric(final String source) {
        return source.replaceAll("[^a-zA-Z0-9]", "");
    }
    
    public static String FormatFloatNumber(final float f, int dp, final int percentage) {
        dp = (int)Math.pow(10.0, dp);
        if (percentage == 1) {
            return Float.toString((int)(f * dp) / dp * 100.0f);
        }
        return Float.toString((int)(f * dp) / dp);
    }
}
