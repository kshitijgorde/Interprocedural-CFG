// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.tp.graph;

public interface Constraint
{
    boolean match(final BasicWalker p0, final Frame p1);
    
    public enum Direction
    {
        Both("Both", 0), 
        Inbound("Inbound", 1), 
        Outbound("Outbound", 2);
        
        private Direction(final String s, final int n) {
        }
    }
}
