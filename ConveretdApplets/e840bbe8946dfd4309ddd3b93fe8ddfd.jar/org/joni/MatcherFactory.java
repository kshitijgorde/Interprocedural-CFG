// 
// Decompiled by Procyon v0.5.30
// 

package org.joni;

public abstract class MatcherFactory
{
    static final MatcherFactory DEFAULT;
    
    public abstract Matcher create(final Regex p0, final byte[] p1, final int p2, final int p3);
    
    static {
        DEFAULT = new MatcherFactory() {
            public Matcher create(final Regex regex, final byte[] bytes, final int p, final int end) {
                return new ByteCodeMachine(regex, bytes, p, end);
            }
        };
    }
}
