// 
// Decompiled by Procyon v0.5.30
// 

package com.pluraprocessing.node;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.pluraprocessing.node.exception.PluraParameterException;

public class InputValidator
{
    public static void checkValidPercentage(final double percentage) throws PluraParameterException {
        if (percentage < 0.0 || percentage > 1.0) {
            throw new PluraParameterException();
        }
    }
    
    public static void checkValidAffiliateId(final String affiliateId) throws PluraParameterException {
        if (affiliateId.length() > 36) {
            throw new PluraParameterException();
        }
    }
    
    public static void checkValidClientId(final String clientId) throws PluraParameterException {
        if (clientId.length() > 40) {
            throw new PluraParameterException();
        }
    }
    
    public static void checkValidMaxPluraThreads(final int numThreads) throws PluraParameterException {
        if (numThreads < 1) {
            throw new PluraParameterException();
        }
    }
    
    public static boolean isValidGUID(final String guid) {
        final Pattern p = Pattern.compile("^(\\{{0,1}([0-9a-fA-F]){8}-([0-9a-fA-F]){4}-([0-9a-fA-F]){4}-([0-9a-fA-F]){4}-([0-9a-fA-F]){12}\\}{0,1})$");
        final Matcher m = p.matcher(guid);
        return m.find();
    }
}
