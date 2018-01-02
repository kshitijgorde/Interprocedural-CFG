// 
// Decompiled by Procyon v0.5.30
// 

package ca.odell.glazedlists.impl;

import java.util.Comparator;
import java.util.List;

class Diff$ListDiffMatcher implements Diff$DiffMatcher
{
    private List a;
    private List b;
    private Comparator c;
    
    public Diff$ListDiffMatcher(final List a, final List b, final Comparator c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
    
    public int a() {
        return this.a.size();
    }
    
    public int b() {
        return this.b.size();
    }
    
    public boolean a(final int n, final int n2) {
        return this.c.compare(this.a.get(n), this.b.get(n2)) == 0;
    }
}
