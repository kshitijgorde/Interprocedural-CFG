// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.util;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

public abstract class WeakReferenceReaper<T> extends WeakReference<T> implements Runnable
{
    public WeakReferenceReaper(final T referent) {
        super(referent, ReferenceReaper.getInstance().referenceQueue);
    }
}
