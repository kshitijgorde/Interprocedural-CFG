// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xslt4j.regexp;

public class REUtil
{
    private static final String complexPrefix = "complex:";
    
    public static RE createRE(final String expression, final int matchFlags) throws RESyntaxException {
        if (expression.startsWith("complex:")) {
            return new RE(expression.substring("complex:".length()), matchFlags);
        }
        return new RE(RE.simplePatternToFullRegularExpression(expression), matchFlags);
    }
    
    public static RE createRE(final String expression) throws RESyntaxException {
        return createRE(expression, 0);
    }
}
