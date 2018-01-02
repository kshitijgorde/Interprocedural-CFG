// 
// Decompiled by Procyon v0.5.30
// 

package com.sun.jna.platform.win32;

import com.sun.jna.Native;
import com.sun.jna.win32.W32APIOptions;
import com.sun.jna.ptr.ByReference;
import java.nio.Buffer;
import com.sun.jna.ptr.PointerByReference;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.Pointer;
import com.sun.jna.win32.StdCallLibrary;

public interface Kernel32 extends StdCallLibrary
{
    public static final Kernel32 INSTANCE = (Kernel32)Native.loadLibrary("kernel32", Kernel32.class, W32APIOptions.UNICODE_OPTIONS);
    
    Pointer LocalFree(final Pointer p0);
    
    Pointer GlobalFree(final Pointer p0);
    
    WinDef.HMODULE GetModuleHandle(final String p0);
    
    void GetSystemTime(final WinBase.SYSTEMTIME p0);
    
    int GetTickCount();
    
    int GetCurrentThreadId();
    
    WinNT.HANDLE GetCurrentThread();
    
    int GetCurrentProcessId();
    
    WinNT.HANDLE GetCurrentProcess();
    
    int GetProcessId(final WinNT.HANDLE p0);
    
    int GetProcessVersion(final int p0);
    
    boolean GetExitCodeProcess(final WinNT.HANDLE p0, final IntByReference p1);
    
    boolean TerminateProcess(final WinNT.HANDLE p0, final int p1);
    
    int GetLastError();
    
    void SetLastError(final int p0);
    
    int GetDriveType(final String p0);
    
    int FormatMessage(final int p0, final Pointer p1, final int p2, final int p3, final PointerByReference p4, final int p5, final Pointer p6);
    
    int FormatMessage(final int p0, final Pointer p1, final int p2, final int p3, final Buffer p4, final int p5, final Pointer p6);
    
    WinNT.HANDLE CreateFile(final String p0, final int p1, final int p2, final WinBase.SECURITY_ATTRIBUTES p3, final int p4, final int p5, final WinNT.HANDLE p6);
    
    boolean CreateDirectory(final String p0, final WinBase.SECURITY_ATTRIBUTES p1);
    
    boolean ReadFile(final WinNT.HANDLE p0, final Buffer p1, final int p2, final IntByReference p3, final WinBase.OVERLAPPED p4);
    
    WinNT.HANDLE CreateIoCompletionPort(final WinNT.HANDLE p0, final WinNT.HANDLE p1, final Pointer p2, final int p3);
    
    boolean GetQueuedCompletionStatus(final WinNT.HANDLE p0, final IntByReference p1, final ByReference p2, final PointerByReference p3, final int p4);
    
    boolean PostQueuedCompletionStatus(final WinNT.HANDLE p0, final int p1, final Pointer p2, final WinBase.OVERLAPPED p3);
    
    int WaitForSingleObject(final WinNT.HANDLE p0, final int p1);
    
    int WaitForMultipleObjects(final int p0, final WinNT.HANDLE[] p1, final boolean p2, final int p3);
    
    boolean DuplicateHandle(final WinNT.HANDLE p0, final WinNT.HANDLE p1, final WinNT.HANDLE p2, final WinNT.HANDLEByReference p3, final int p4, final boolean p5, final int p6);
    
    boolean CloseHandle(final WinNT.HANDLE p0);
    
    boolean ReadDirectoryChangesW(final WinNT.HANDLE p0, final WinNT.FILE_NOTIFY_INFORMATION p1, final int p2, final boolean p3, final int p4, final IntByReference p5, final WinBase.OVERLAPPED p6, final OVERLAPPED_COMPLETION_ROUTINE p7);
    
    int GetShortPathName(final String p0, final char[] p1, final int p2);
    
    Pointer LocalAlloc(final int p0, final int p1);
    
    boolean WriteFile(final WinNT.HANDLE p0, final byte[] p1, final int p2, final IntByReference p3, final WinBase.OVERLAPPED p4);
    
    WinNT.HANDLE CreateEvent(final WinBase.SECURITY_ATTRIBUTES p0, final boolean p1, final boolean p2, final String p3);
    
    boolean SetEvent(final WinNT.HANDLE p0);
    
    boolean PulseEvent(final WinNT.HANDLE p0);
    
    WinNT.HANDLE CreateFileMapping(final WinNT.HANDLE p0, final WinBase.SECURITY_ATTRIBUTES p1, final int p2, final int p3, final int p4, final String p5);
    
    Pointer MapViewOfFile(final WinNT.HANDLE p0, final int p1, final int p2, final int p3, final int p4);
    
    boolean UnmapViewOfFile(final Pointer p0);
    
    boolean GetComputerName(final char[] p0, final IntByReference p1);
    
    WinNT.HANDLE OpenThread(final int p0, final boolean p1, final int p2);
    
    WinNT.HANDLE OpenProcess(final int p0, final boolean p1, final int p2);
    
    WinDef.DWORD GetTempPath(final WinDef.DWORD p0, final char[] p1);
    
    WinDef.DWORD GetVersion();
    
    boolean GetVersionEx(final WinNT.OSVERSIONINFO p0);
    
    boolean GetVersionEx(final WinNT.OSVERSIONINFOEX p0);
    
    void GetSystemInfo(final WinBase.SYSTEM_INFO p0);
    
    void GetNativeSystemInfo(final WinBase.SYSTEM_INFO p0);
    
    boolean IsWow64Process(final WinNT.HANDLE p0, final IntByReference p1);
    
    boolean GlobalMemoryStatusEx(final WinBase.MEMORYSTATUSEX p0);
    
    WinDef.DWORD GetLogicalDriveStrings(final WinDef.DWORD p0, final char[] p1);
    
    boolean GetDiskFreeSpaceEx(final String p0, final WinNT.LARGE_INTEGER.ByReference p1, final WinNT.LARGE_INTEGER.ByReference p2, final WinNT.LARGE_INTEGER.ByReference p3);
    
    boolean DeleteFile(final String p0);
    
    boolean CreatePipe(final WinNT.HANDLEByReference p0, final WinNT.HANDLEByReference p1, final WinBase.SECURITY_ATTRIBUTES p2, final int p3);
    
    boolean SetHandleInformation(final WinNT.HANDLE p0, final int p1, final int p2);
    
    int GetFileAttributes(final String p0);
    
    public interface OVERLAPPED_COMPLETION_ROUTINE extends StdCallCallback
    {
        void callback(final int p0, final int p1, final WinBase.OVERLAPPED p2);
    }
}
