// 
// Decompiled by Procyon v0.5.30
// 

package ca.odell.glazedlists.impl.matchers;

import ca.odell.glazedlists.matchers.Matcher;

public final class TrueMatcher implements Matcher
{
    private static final Matcher a;
    
    public static Matcher a() {
        return TrueMatcher.a;
    }
    
    public boolean a(final Object o) {
        return true;
    }
    
    static {
        a = new TrueMatcher();
    }
}
