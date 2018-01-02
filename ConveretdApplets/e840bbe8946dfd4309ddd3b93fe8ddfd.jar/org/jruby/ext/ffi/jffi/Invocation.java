// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.ffi.jffi;

import java.util.Iterator;
import java.util.ArrayList;
import org.jruby.runtime.ThreadContext;

final class Invocation
{
    private final ThreadContext context;
    private final int postInvokeCount;
    private final int referenceCount;
    private ArrayList<Runnable> postInvokeList;
    private ArrayList<Object> references;
    
    public Invocation(final ThreadContext context) {
        this(context, 0, 0);
    }
    
    Invocation(final ThreadContext context, final int postInvokeCount, final int referenceCount) {
        this.context = context;
        this.postInvokeCount = postInvokeCount;
        this.referenceCount = referenceCount;
    }
    
    void finish() {
        if (this.postInvokeList != null) {
            for (final Runnable r : this.postInvokeList) {
                r.run();
            }
        }
    }
    
    void addPostInvoke(final Runnable postInvoke) {
        if (this.postInvokeList == null) {
            this.postInvokeList = new ArrayList<Runnable>(this.postInvokeCount);
        }
        this.postInvokeList.add(postInvoke);
    }
    
    ThreadContext getThreadContext() {
        return this.context;
    }
    
    void addReference(final Object ref) {
        if (this.references == null) {
            this.references = new ArrayList<Object>(this.referenceCount);
        }
        this.references.add(ref);
    }
}
