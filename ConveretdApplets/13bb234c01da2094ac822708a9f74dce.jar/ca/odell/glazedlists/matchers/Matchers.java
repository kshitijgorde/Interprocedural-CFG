// 
// Decompiled by Procyon v0.5.30
// 

package ca.odell.glazedlists.matchers;

import ca.odell.glazedlists.impl.matchers.FalseMatcher;
import ca.odell.glazedlists.impl.matchers.TrueMatcher;

public final class Matchers
{
    private Matchers() {
        throw new UnsupportedOperationException();
    }
    
    public static Matcher a() {
        return TrueMatcher.a();
    }
    
    public static Matcher b() {
        return FalseMatcher.a();
    }
}
