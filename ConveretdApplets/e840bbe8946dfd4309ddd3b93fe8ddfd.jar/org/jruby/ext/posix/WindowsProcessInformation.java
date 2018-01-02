// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.posix;

import com.kenai.jaffl.struct.Struct;

public class WindowsProcessInformation extends HeapStruct
{
    Pointer hProcess;
    Pointer hThread;
    Unsigned32 dwProcessId;
    Unsigned32 dwThreadId;
    
    public WindowsProcessInformation() {
        this.hProcess = new Pointer(this);
        this.hThread = new Pointer(this);
        this.dwProcessId = new Unsigned32(this);
        this.dwThreadId = new Unsigned32(this);
    }
    
    public int getThread() {
        return this.hThread.intValue();
    }
    
    public Pointer getProcess() {
        return this.hProcess;
    }
    
    public int getPid() {
        return this.dwProcessId.intValue();
    }
}
