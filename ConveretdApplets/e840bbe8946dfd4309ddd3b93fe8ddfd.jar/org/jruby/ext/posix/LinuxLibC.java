// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.posix;

import com.kenai.jaffl.annotations.In;
import com.kenai.jaffl.annotations.NulTerminate;
import java.nio.ByteBuffer;
import com.kenai.jaffl.annotations.Transient;
import com.kenai.jaffl.annotations.Out;

public interface LinuxLibC extends LibC
{
    int __fxstat(final int p0, final int p1, @Out @Transient final FileStat p2);
    
    int __lxstat(final int p0, final CharSequence p1, @Out @Transient final FileStat p2);
    
    int __lxstat(final int p0, @NulTerminate @In final ByteBuffer p1, @Out @Transient final FileStat p2);
    
    int __xstat(final int p0, final CharSequence p1, @Out @Transient final FileStat p2);
    
    int __xstat(final int p0, @NulTerminate @In final ByteBuffer p1, @Out @Transient final FileStat p2);
    
    int __fxstat64(final int p0, final int p1, @Out @Transient final FileStat p2);
    
    int __lxstat64(final int p0, final CharSequence p1, @Out @Transient final FileStat p2);
    
    int __lxstat64(final int p0, @NulTerminate @In final ByteBuffer p1, @Out @Transient final FileStat p2);
    
    int __xstat64(final int p0, final CharSequence p1, @Out @Transient final FileStat p2);
    
    int __xstat64(final int p0, @NulTerminate @In final ByteBuffer p1, @Out @Transient final FileStat p2);
}
