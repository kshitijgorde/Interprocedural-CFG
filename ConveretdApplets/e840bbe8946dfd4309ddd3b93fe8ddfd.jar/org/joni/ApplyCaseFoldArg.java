// 
// Decompiled by Procyon v0.5.30
// 

package org.joni;

import org.joni.ast.ConsAltNode;
import org.joni.ast.CClassNode;

public final class ApplyCaseFoldArg
{
    final ScanEnvironment env;
    final CClassNode cc;
    ConsAltNode altRoot;
    ConsAltNode tail;
    
    public ApplyCaseFoldArg(final ScanEnvironment env, final CClassNode cc) {
        this.env = env;
        this.cc = cc;
    }
}
