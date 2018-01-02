// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.runtime.profile;

import java.util.Iterator;
import java.util.ArrayList;

public class InvocationSet
{
    ArrayList<Invocation> invocations;
    
    public InvocationSet() {
    }
    
    public InvocationSet(final ArrayList<Invocation> invs) {
        this.invocations = invs;
    }
    
    public long totalTime() {
        long t = 0L;
        for (final Invocation inv : this.invocations) {
            t += inv.getDuration();
        }
        return t;
    }
    
    public long selfTime() {
        return this.totalTime() - this.childTime();
    }
    
    public long childTime() {
        long t = 0L;
        for (final Invocation inv : this.invocations) {
            t += inv.childTime();
        }
        return t;
    }
    
    public int totalCalls() {
        int t = 0;
        for (final Invocation inv : this.invocations) {
            t += inv.getCount();
        }
        return t;
    }
    
    public long timeSpentInChild(final int serial) {
        long t = 0L;
        for (final Invocation inv : this.invocations) {
            final Invocation childInv = inv.getChildren().get(serial);
            if (childInv != null) {
                t += childInv.getDuration();
            }
        }
        return t;
    }
    
    public int callsOfChild(final int serial) {
        int c = 0;
        for (final Invocation inv : this.invocations) {
            final Invocation childInv = inv.getChildren().get(serial);
            if (childInv != null) {
                c += childInv.getCount();
            }
        }
        return c;
    }
}
