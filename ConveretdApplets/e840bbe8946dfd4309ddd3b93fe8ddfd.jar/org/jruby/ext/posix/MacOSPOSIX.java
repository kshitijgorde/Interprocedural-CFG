// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.posix;

import com.kenai.jaffl.Pointer;
import com.kenai.jaffl.mapper.FromNativeContext;

final class MacOSPOSIX extends BaseNativePOSIX
{
    public static final PointerConverter PASSWD;
    
    MacOSPOSIX(final String libraryName, final LibCProvider libcProvider, final POSIXHandler handler) {
        super(libraryName, libcProvider, handler);
    }
    
    public BaseHeapFileStat allocateStat() {
        return new MacOSHeapFileStat(this);
    }
    
    public int lchmod(final String filename, final int mode) {
        try {
            return this.libc().lchmod(filename, mode);
        }
        catch (UnsatisfiedLinkError ex) {
            this.handler.unimplementedError("lchmod");
            return -1;
        }
    }
    
    public int lchown(final String filename, final int user, final int group) {
        try {
            return super.lchown(filename, user, group);
        }
        catch (UnsatisfiedLinkError ex) {
            this.handler.unimplementedError("lchown");
            return -1;
        }
    }
    
    static {
        PASSWD = new PointerConverter() {
            public Object fromNative(final Object arg, final FromNativeContext ctx) {
                return (arg != null) ? new MacOSPasswd((Pointer)arg) : null;
            }
        };
    }
}
