// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.util;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.PhantomReference;

public abstract class PhantomReferenceReaper<T> extends PhantomReference<T> implements Runnable
{
    public PhantomReferenceReaper(final T referent) {
        super(referent, ReferenceReaper.getInstance().referenceQueue);
    }
}
