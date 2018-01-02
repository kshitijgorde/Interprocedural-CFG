// 
// Decompiled by Procyon v0.5.30
// 

package ca.odell.glazedlists.matchers;

import java.util.EventObject;

public class MatcherEditor$Event extends EventObject
{
    private MatcherEditor a;
    private final Matcher b;
    private final int c;
    
    public MatcherEditor$Event(final MatcherEditor a, final int c, final Matcher b) {
        super(a);
        this.a = a;
        this.c = c;
        this.b = b;
    }
    
    public MatcherEditor a() {
        return this.a;
    }
    
    public Matcher b() {
        return this.b;
    }
    
    public int c() {
        return this.c;
    }
}
