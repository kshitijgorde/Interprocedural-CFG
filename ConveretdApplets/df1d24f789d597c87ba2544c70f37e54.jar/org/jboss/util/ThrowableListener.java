// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.util;

import java.util.EventListener;

public interface ThrowableListener extends EventListener
{
    void onThrowable(final int p0, final Throwable p1);
}
