// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.posix;

import com.kenai.jaffl.annotations.StdCall;
import com.kenai.jaffl.Pointer;
import com.kenai.jaffl.annotations.In;

public interface WindowsLibC extends LibC
{
    public static final int STD_INPUT_HANDLE = -10;
    public static final int STD_OUTPUT_HANDLE = -11;
    public static final int STD_ERROR_HANDLE = -12;
    public static final int NORMAL_PRIORITY_CLASS = 32;
    public static final int INFINITE = -1;
    
    int _open_osfhandle(final int p0, final int p1);
    
    int _wmkdir(@In final byte[] p0);
    
    @StdCall
    boolean CreateProcessA(final String p0, final String p1, final WindowsSecurityAttributes p2, final WindowsSecurityAttributes p3, final int p4, final int p5, final Pointer p6, final String p7, final WindowsStartupInfo p8, final WindowsProcessInformation p9);
    
    @StdCall
    boolean GetExitCodeProcess(final int p0, final Pointer p1);
    
    @StdCall
    int GetFileType(final int p0);
    
    @StdCall
    int GetFileSize(final int p0, final Pointer p1);
    
    @StdCall
    Pointer GetStdHandle(final int p0);
    
    @StdCall
    boolean CreateHardLinkW(final byte[] p0, final byte[] p1, @In final byte[] p2);
    
    @StdCall
    int CreateFileW(final byte[] p0, final int p1, final int p2, final Pointer p3, final int p4, final int p5, final int p6);
    
    @StdCall
    boolean SetFileTime(final int p0, final FileTime p1, final FileTime p2, final FileTime p3);
    
    @StdCall
    boolean CloseHandle(final int p0);
    
    @StdCall
    int WaitForSingleObject(final int p0, final int p1);
}
