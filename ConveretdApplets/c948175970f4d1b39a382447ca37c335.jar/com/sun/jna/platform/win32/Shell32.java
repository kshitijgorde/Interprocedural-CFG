// 
// Decompiled by Procyon v0.5.30
// 

package com.sun.jna.platform.win32;

import com.sun.jna.Native;
import com.sun.jna.win32.W32APIOptions;
import com.sun.jna.ptr.PointerByReference;
import com.sun.jna.win32.StdCallLibrary;

public interface Shell32 extends StdCallLibrary
{
    public static final Shell32 INSTANCE = (Shell32)Native.loadLibrary("shell32", Shell32.class, W32APIOptions.UNICODE_OPTIONS);
    
    int SHFileOperation(final ShellAPI.SHFILEOPSTRUCT p0);
    
    WinNT.HRESULT SHGetFolderPath(final WinDef.HWND p0, final int p1, final WinNT.HANDLE p2, final WinDef.DWORD p3, final char[] p4);
    
    WinNT.HRESULT SHGetDesktopFolder(final PointerByReference p0);
}
