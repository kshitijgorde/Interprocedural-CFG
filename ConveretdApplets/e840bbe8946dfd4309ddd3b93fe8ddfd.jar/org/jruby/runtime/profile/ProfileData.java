// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.runtime.profile;

import java.util.Iterator;
import org.jruby.runtime.ThreadContext;

public class ProfileData implements IProfileData
{
    private Invocation currentInvocation;
    private Invocation topInvocation;
    private int[] methodRecursion;
    private ThreadContext threadContext;
    
    public ProfileData(final ThreadContext tc) {
        this.currentInvocation = new Invocation(0);
        this.topInvocation = this.currentInvocation;
        this.methodRecursion = new int[1000];
        this.threadContext = tc;
    }
    
    public int profileEnter(final int calledMethod) {
        final Invocation parentInvocation = this.currentInvocation;
        final Invocation childInvocation = parentInvocation.childInvocationFor(calledMethod);
        childInvocation.incrementCount();
        this.currentInvocation = childInvocation;
        return parentInvocation.getMethodSerialNumber();
    }
    
    public int profileExit(final int callingMethod, final long startTime) {
        final long now = System.nanoTime();
        final long duration = now - startTime;
        final int oldSerial = this.currentInvocation.getMethodSerialNumber();
        this.currentInvocation.addDuration(duration);
        if (this.currentInvocation == this.topInvocation) {
            final Invocation newTopInvocation = new Invocation(0);
            final Invocation newCurrentInvocation = this.currentInvocation.copyWithNewSerialAndParent(callingMethod, newTopInvocation);
            newTopInvocation.addChild(newCurrentInvocation);
            newCurrentInvocation.incrementCount();
            this.topInvocation = newTopInvocation;
            this.currentInvocation = newCurrentInvocation;
            return oldSerial;
        }
        if (this.currentInvocation.getParent() == this.topInvocation && callingMethod != 0) {
            final Invocation newTopInvocation = new Invocation(0);
            final Invocation newCurrentInvocation = newTopInvocation.childInvocationFor(callingMethod);
            final Invocation newChildInvocation = this.currentInvocation.copyWithNewSerialAndParent(this.currentInvocation.getMethodSerialNumber(), newCurrentInvocation);
            newCurrentInvocation.addChild(newChildInvocation);
            newCurrentInvocation.incrementCount();
            this.topInvocation = newTopInvocation;
            this.currentInvocation = newCurrentInvocation;
            return oldSerial;
        }
        this.currentInvocation = this.currentInvocation.getParent();
        return oldSerial;
    }
    
    public void clear() {
        this.methodRecursion = new int[1000];
        this.currentInvocation = new Invocation(0);
        this.topInvocation = this.currentInvocation;
    }
    
    public void decRecursionFor(final int serial) {
        this.ensureRecursionSize(serial);
        final int[] mr = this.methodRecursion;
        --mr[serial];
    }
    
    public int incRecursionFor(final int serial) {
        this.ensureRecursionSize(serial);
        final int[] mr = this.methodRecursion;
        final int inc = mr[serial] + 1;
        return mr[serial] = inc;
    }
    
    private void ensureRecursionSize(final int index) {
        final int[] mr = this.methodRecursion;
        final int length = mr.length;
        if (length <= index) {
            final int[] newRecursion = new int[(int)(index * 1.5 + 1.0)];
            System.arraycopy(mr, 0, newRecursion, 0, length);
            this.methodRecursion = newRecursion;
        }
    }
    
    private void setRecursiveDepths() {
        final int topSerial = this.topInvocation.getMethodSerialNumber();
        final int depth = this.incRecursionFor(topSerial);
        this.topInvocation.setRecursiveDepth(depth);
        this.setRecursiveDepths1(this.topInvocation);
    }
    
    private void setRecursiveDepths1(final Invocation inv) {
        for (final Invocation child : inv.getChildren().values()) {
            final int childSerial = child.getMethodSerialNumber();
            final int depth = this.incRecursionFor(childSerial);
            child.setRecursiveDepth(depth);
            this.setRecursiveDepths1(child);
            this.decRecursionFor(childSerial);
        }
    }
    
    public long totalTime() {
        return this.topInvocation.childTime();
    }
    
    public Invocation getTopInvocation() {
        return this.topInvocation;
    }
    
    public Invocation getResults() {
        this.setRecursiveDepths();
        if (this.topInvocation.getChildren().size() != 1) {
            return this.addDuration(this.topInvocation);
        }
        if (this.topInvocation.getChildren().size() == 1) {
            Invocation singleTopChild = null;
            final Iterator i$ = this.topInvocation.getChildren().values().iterator();
            while (i$.hasNext()) {
                final Invocation inv = singleTopChild = i$.next();
            }
            final String singleTopChildName = AbstractProfilePrinter.getMethodName(singleTopChild.getMethodSerialNumber());
            if (singleTopChildName.equals("JRuby::Profiler.profile")) {
                final Invocation profiledCodeInvocation = null;
                for (final Invocation inv2 : singleTopChild.getChildren().values()) {
                    if (AbstractProfilePrinter.getMethodName(inv2.getMethodSerialNumber()).equals("JRuby::Profiler.profiled_code")) {
                        return this.addDuration(inv2.copyWithNewSerialAndParent(0, null));
                    }
                }
            }
        }
        return this.addDuration(this.topInvocation);
    }
    
    public Invocation addDuration(final Invocation inv) {
        inv.setDuration(inv.childTime());
        return inv;
    }
    
    public Invocation getCurrentInvocation() {
        return this.currentInvocation;
    }
    
    public ThreadContext getThreadContext() {
        return this.threadContext;
    }
}
