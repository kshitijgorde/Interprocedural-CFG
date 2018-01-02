// 
// Decompiled by Procyon v0.5.30
// 

package org.joni;

import org.jcodings.Encoding;

final class OptEnvironment
{
    final MinMaxLen mmd;
    Encoding enc;
    int options;
    int caseFoldFlag;
    ScanEnvironment scanEnv;
    
    OptEnvironment() {
        this.mmd = new MinMaxLen();
    }
    
    void copy(final OptEnvironment other) {
        this.mmd.copy(other.mmd);
        this.enc = other.enc;
        this.options = other.options;
        this.caseFoldFlag = other.caseFoldFlag;
        this.scanEnv = other.scanEnv;
    }
}
