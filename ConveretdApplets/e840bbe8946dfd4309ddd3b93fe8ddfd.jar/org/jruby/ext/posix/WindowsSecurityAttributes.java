// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.posix;

import com.kenai.jaffl.struct.StructUtil;
import com.kenai.jaffl.struct.Struct;

public class WindowsSecurityAttributes extends HeapStruct
{
    public final Unsigned32 length;
    public final Pointer securityDescriptor;
    public final WBOOL inheritHandle;
    
    public WindowsSecurityAttributes() {
        this.length = new Unsigned32(this);
        this.securityDescriptor = new Pointer(this);
        this.inheritHandle = new WBOOL(this);
        this.length.set(StructUtil.getSize(this));
        this.inheritHandle.set(true);
    }
    
    public long getLength() {
        return this.length.get();
    }
    
    public boolean getInheritHandle() {
        return this.inheritHandle.get();
    }
}
