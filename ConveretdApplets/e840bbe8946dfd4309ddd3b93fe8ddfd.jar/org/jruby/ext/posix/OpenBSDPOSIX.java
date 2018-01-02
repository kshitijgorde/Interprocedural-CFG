// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.posix;

import com.kenai.jaffl.Pointer;
import com.kenai.jaffl.mapper.FromNativeContext;

final class OpenBSDPOSIX extends BaseNativePOSIX
{
    public static final PointerConverter PASSWD;
    
    OpenBSDPOSIX(final String libraryName, final LibCProvider libc, final POSIXHandler handler) {
        super(libraryName, libc, handler);
    }
    
    public BaseHeapFileStat allocateStat() {
        return new FreeBSDHeapFileStat(this);
    }
    
    static {
        PASSWD = new PointerConverter() {
            public Object fromNative(final Object arg, final FromNativeContext ctx) {
                return (arg != null) ? new OpenBSDPasswd((Pointer)arg) : null;
            }
        };
    }
}
