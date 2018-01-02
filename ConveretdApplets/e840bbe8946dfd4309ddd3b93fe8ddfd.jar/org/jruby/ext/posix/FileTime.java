// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.posix;

import com.kenai.jaffl.struct.Struct;

public class FileTime extends Struct
{
    public final Unsigned32 dwLowDateTime;
    public final Unsigned32 dwHighDateTime;
    
    public FileTime() {
        this.dwLowDateTime = new Unsigned32(this);
        this.dwHighDateTime = new Unsigned32(this);
    }
}
