// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.util;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;

public abstract class SoftReferenceReaper<T> extends SoftReference<T> implements Runnable
{
    public SoftReferenceReaper(final T referent) {
        super(referent, ReferenceReaper.getInstance().referenceQueue);
    }
}
