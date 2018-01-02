// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.tp.graph;

public class Metric implements Constraint
{
    private int limit;
    
    public Metric() {
        this.limit = Integer.MAX_VALUE;
    }
    
    public Metric(final int limit) {
        this.limit = Integer.MAX_VALUE;
        this.limit = limit;
    }
    
    @Override
    public boolean match(final BasicWalker w, final Frame frame) {
        boolean result = false;
        switch (frame.direction()) {
            case Outbound: {
                result = (frame.metric() > this.limit);
                break;
            }
        }
        return result;
    }
}
