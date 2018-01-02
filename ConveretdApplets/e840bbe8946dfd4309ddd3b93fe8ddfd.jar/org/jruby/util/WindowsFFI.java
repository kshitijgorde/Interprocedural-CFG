// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.util;

import com.kenai.jaffl.NativeLong;
import java.util.Map;
import com.kenai.jaffl.CallingConvention;
import com.kenai.jaffl.LibraryOption;
import java.util.HashMap;
import com.kenai.jaffl.FFIProvider;

public class WindowsFFI
{
    public static Kernel32 getKernel32(final FFIProvider provider) {
        final Map<LibraryOption, Object> options = new HashMap<LibraryOption, Object>();
        options.put(LibraryOption.CallingConvention, CallingConvention.STDCALL);
        return provider.loadLibrary("Kernel32.dll", Kernel32.class, options);
    }
    
    public interface Kernel32
    {
        int GetProcessId(final NativeLong p0);
    }
}
