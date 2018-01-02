// 
// Decompiled by Procyon v0.5.30
// 

package ca.odell.glazedlists.impl.matchers;

import ca.odell.glazedlists.matchers.Matcher;

public final class FalseMatcher implements Matcher
{
    private static final Matcher a;
    
    public static Matcher a() {
        return FalseMatcher.a;
    }
    
    public boolean a(final Object o) {
        return false;
    }
    
    static {
        a = new FalseMatcher();
    }
}
