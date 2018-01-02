// 
// Decompiled by Procyon v0.5.30
// 

package com.tn.zx;

import com.tn.z80.Z80A;
import com.tn.components.Memory;

public interface ZxExpansionPort
{
    public static final int PAGE_0000_3FFF = 0;
    public static final int PAGE_4000_7FFF = 1;
    public static final int PAGE_8000_BFFF = 2;
    public static final int PAGE_C000_FFFF = 3;
    
    void activateExternalMemory(final int p0, final Memory p1);
    
    void addBreakpointHandler(final ZxIoHandler p0, final int p1);
    
    void addIOHandler(final ZxIoHandler p0, final int p1);
    
    void deactivateExternalMemory(final int p0, final Memory p1);
    
    Z80A getZ80();
    
    void removeBreakpointHandler(final ZxIoHandler p0);
    
    void removeIOHandler(final ZxIoHandler p0);
}
