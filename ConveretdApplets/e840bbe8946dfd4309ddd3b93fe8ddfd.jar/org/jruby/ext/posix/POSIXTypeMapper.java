// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.posix;

import com.kenai.jaffl.mapper.ToNativeConverter;
import org.jruby.ext.posix.util.Platform;
import com.kenai.jaffl.mapper.FromNativeConverter;
import com.kenai.jaffl.mapper.TypeMapper;

final class POSIXTypeMapper implements TypeMapper
{
    public static final TypeMapper INSTANCE;
    
    public FromNativeConverter getFromNativeConverter(final Class klazz) {
        if (Passwd.class.isAssignableFrom(klazz)) {
            if (Platform.IS_MAC) {
                return MacOSPOSIX.PASSWD;
            }
            if (Platform.IS_LINUX) {
                return LinuxPOSIX.PASSWD;
            }
            if (Platform.IS_SOLARIS) {
                return SolarisPOSIX.PASSWD;
            }
            if (Platform.IS_FREEBSD) {
                return FreeBSDPOSIX.PASSWD;
            }
            if (Platform.IS_OPENBSD) {
                return OpenBSDPOSIX.PASSWD;
            }
            return null;
        }
        else {
            if (Group.class.isAssignableFrom(klazz)) {
                return BaseNativePOSIX.GROUP;
            }
            return null;
        }
    }
    
    public ToNativeConverter getToNativeConverter(final Class klazz) {
        if (FileStat.class.isAssignableFrom(klazz)) {
            return BaseNativePOSIX.FileStatConverter;
        }
        return null;
    }
    
    static {
        INSTANCE = new POSIXTypeMapper();
    }
}
