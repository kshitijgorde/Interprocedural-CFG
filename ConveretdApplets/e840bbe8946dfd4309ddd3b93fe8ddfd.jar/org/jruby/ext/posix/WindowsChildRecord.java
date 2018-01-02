// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.posix;

import com.kenai.jaffl.struct.Struct;

public class WindowsChildRecord
{
    private Struct.Pointer process;
    int pid;
    
    public WindowsChildRecord(final Struct.Pointer process, final int pid) {
        this.process = process;
        this.pid = pid;
    }
    
    public Struct.Pointer getProcess() {
        return this.process;
    }
    
    public int getPid() {
        return this.pid;
    }
}
