// 
// Decompiled by Procyon v0.5.30
// 

package com.objectbox.loader;

import java.util.EventListener;
import java.awt.AWTEventMulticaster;

public class SecurityViolationEventMulticaster extends AWTEventMulticaster implements SecurityViolationListener
{
    protected SecurityViolationEventMulticaster(final SecurityViolationListener securityViolationListener, final SecurityViolationListener securityViolationListener2) {
        super(securityViolationListener, securityViolationListener2);
    }
    
    public static SecurityViolationListener add(final SecurityViolationListener securityViolationListener, final SecurityViolationListener securityViolationListener2) {
        if (securityViolationListener == null) {
            return securityViolationListener2;
        }
        if (securityViolationListener2 == null) {
            return securityViolationListener;
        }
        return new SecurityViolationEventMulticaster(securityViolationListener, securityViolationListener2);
    }
    
    public void handleSecurityViolation(final SecurityViolationEvent securityViolationEvent) {
        ((SecurityViolationListener)super.a).handleSecurityViolation(securityViolationEvent);
        ((SecurityViolationListener)super.b).handleSecurityViolation(securityViolationEvent);
    }
    
    public static SecurityViolationListener remove(final SecurityViolationListener securityViolationListener, final SecurityViolationListener securityViolationListener2) {
        return (SecurityViolationListener)AWTEventMulticaster.removeInternal(securityViolationListener, securityViolationListener2);
    }
}
