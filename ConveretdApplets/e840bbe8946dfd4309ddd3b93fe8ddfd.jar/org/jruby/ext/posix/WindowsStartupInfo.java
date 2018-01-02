// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.posix;

import com.kenai.jaffl.Pointer;
import com.kenai.jaffl.struct.Struct;

public class WindowsStartupInfo extends HeapStruct
{
    Unsigned32 cb;
    Pointer lpReserved;
    Pointer lpDesktop;
    Pointer lpTitle;
    Unsigned32 dwX;
    Unsigned32 dwY;
    Unsigned32 dwXSize;
    Unsigned32 dwYSize;
    Unsigned32 dwXCountChars;
    Unsigned32 dwYCountChars;
    Unsigned32 dwFillAttribute;
    Unsigned32 dwFlags;
    Unsigned16 wShowWindow;
    Unsigned16 cbReserved2;
    Pointer lpReserved2;
    Pointer standardInput;
    Pointer standardOutput;
    Pointer standardError;
    
    public WindowsStartupInfo() {
        this.cb = new Unsigned32(this);
        this.lpReserved = new Pointer(this);
        this.lpDesktop = new Pointer(this);
        this.lpTitle = new Pointer(this);
        this.dwX = new Unsigned32(this);
        this.dwY = new Unsigned32(this);
        this.dwXSize = new Unsigned32(this);
        this.dwYSize = new Unsigned32(this);
        this.dwXCountChars = new Unsigned32(this);
        this.dwYCountChars = new Unsigned32(this);
        this.dwFillAttribute = new Unsigned32(this);
        this.dwFlags = new Unsigned32(this);
        this.wShowWindow = new Unsigned16(this);
        this.cbReserved2 = new Unsigned16(this);
        this.lpReserved2 = new Pointer(this);
        this.standardInput = new Pointer(this);
        this.standardOutput = new Pointer(this);
        this.standardError = new Pointer(this);
    }
    
    public void setFlags(final int value) {
        this.dwFlags.set(value);
    }
    
    public void setStandardInput(final com.kenai.jaffl.Pointer standardInput) {
        this.standardInput.set(standardInput);
    }
    
    public void setStandardOutput(final com.kenai.jaffl.Pointer standardOutput) {
        this.standardOutput.set(standardOutput);
    }
    
    public void setStandardError(final com.kenai.jaffl.Pointer standardError) {
        this.standardError.set(standardError);
    }
}
