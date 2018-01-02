// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.regexp;

public class REUtil
{
    private static final String complexPrefix = "complex:";
    
    public static RE createRE(final String s) throws RESyntaxException {
        return createRE(s, 0);
    }
    
    public static RE createRE(final String s, final int n) throws RESyntaxException {
        if (s.startsWith("complex:")) {
            return new RE(s.substring("complex:".length()), n);
        }
        return new RE(RE.simplePatternToFullRegularExpression(s), n);
    }
}
