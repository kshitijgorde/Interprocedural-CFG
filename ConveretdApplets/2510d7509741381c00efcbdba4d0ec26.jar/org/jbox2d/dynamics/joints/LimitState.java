// 
// Decompiled by Procyon v0.5.30
// 

package org.jbox2d.dynamics.joints;

public enum LimitState
{
    INACTIVE_LIMIT("INACTIVE_LIMIT", 0), 
    AT_LOWER_LIMIT("AT_LOWER_LIMIT", 1), 
    AT_UPPER_LIMIT("AT_UPPER_LIMIT", 2), 
    EQUAL_LIMITS("EQUAL_LIMITS", 3);
    
    private LimitState(final String s, final int n) {
    }
}
