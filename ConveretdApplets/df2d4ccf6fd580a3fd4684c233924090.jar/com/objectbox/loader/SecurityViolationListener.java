// 
// Decompiled by Procyon v0.5.30
// 

package com.objectbox.loader;

import java.util.EventListener;

public interface SecurityViolationListener extends EventListener
{
    void handleSecurityViolation(final SecurityViolationEvent p0);
}
