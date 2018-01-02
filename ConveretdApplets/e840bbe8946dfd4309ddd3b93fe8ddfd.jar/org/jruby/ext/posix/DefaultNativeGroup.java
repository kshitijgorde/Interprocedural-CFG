// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.posix;

import java.util.List;
import java.util.ArrayList;
import com.kenai.jaffl.Pointer;
import com.kenai.jaffl.struct.Struct;

public final class DefaultNativeGroup extends NativeGroup implements Group
{
    public final UTF8StringRef gr_name;
    public final UTF8StringRef gr_passwd;
    public final Signed32 gr_gid;
    public final Pointer gr_mem;
    
    DefaultNativeGroup(final com.kenai.jaffl.Pointer memory) {
        this.gr_name = new UTF8StringRef(this);
        this.gr_passwd = new UTF8StringRef(this);
        this.gr_gid = new Signed32(this);
        this.gr_mem = new Pointer(this);
        this.useMemory(memory);
    }
    
    public java.lang.String getName() {
        return this.gr_name.get();
    }
    
    public java.lang.String getPassword() {
        return this.gr_passwd.get();
    }
    
    public long getGID() {
        return this.gr_gid.get();
    }
    
    public java.lang.String[] getMembers() {
        final int size = com.kenai.jaffl.Pointer.SIZE / 8;
        int i = 0;
        final List<java.lang.String> lst = new ArrayList<java.lang.String>();
        for (com.kenai.jaffl.Pointer ptr = this.gr_mem.get(); ptr.getPointer(i) != null; i += size) {
            lst.add(ptr.getPointer(i).getString(0L));
        }
        return lst.toArray(new java.lang.String[0]);
    }
}
