// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.event;

import java.util.EventListener;

public interface ErrorSuppressedListener extends EventListener
{
    void errorSuppressed(final ErrorSuppressedEvent p0);
}
