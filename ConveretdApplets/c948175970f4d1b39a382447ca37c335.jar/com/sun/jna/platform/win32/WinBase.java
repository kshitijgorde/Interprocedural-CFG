// 
// Decompiled by Procyon v0.5.30
// 

package com.sun.jna.platform.win32;

import com.sun.jna.ptr.ByteByReference;
import com.sun.jna.Union;
import java.util.Date;
import com.sun.jna.Structure;
import com.sun.jna.Pointer;
import com.sun.jna.Platform;

public abstract class WinBase
{
    public static WinNT.HANDLE INVALID_HANDLE_VALUE;
    public static final int WAIT_FAILED = -1;
    public static final int WAIT_OBJECT_0 = 0;
    public static final int WAIT_ABANDONED = 128;
    public static final int WAIT_ABANDONED_0 = 128;
    public static final int LOGON32_LOGON_INTERACTIVE = 2;
    public static final int LOGON32_LOGON_NETWORK = 3;
    public static final int LOGON32_LOGON_BATCH = 4;
    public static final int LOGON32_LOGON_SERVICE = 5;
    public static final int LOGON32_LOGON_UNLOCK = 7;
    public static final int LOGON32_LOGON_NETWORK_CLEARTEXT = 8;
    public static final int LOGON32_LOGON_NEW_CREDENTIALS = 9;
    public static final int LOGON32_PROVIDER_DEFAULT = 0;
    public static final int LOGON32_PROVIDER_WINNT35 = 1;
    public static final int LOGON32_PROVIDER_WINNT40 = 2;
    public static final int LOGON32_PROVIDER_WINNT50 = 3;
    public static final int HANDLE_FLAG_INHERIT = 1;
    public static final int HANDLE_FLAG_PROTECT_FROM_CLOSE = 2;
    public static final int STARTF_USESHOWWINDOW = 1;
    public static final int STARTF_USESIZE = 2;
    public static final int STARTF_USEPOSITION = 4;
    public static final int STARTF_USECOUNTCHARS = 8;
    public static final int STARTF_USEFILLATTRIBUTE = 16;
    public static final int STARTF_RUNFULLSCREEN = 32;
    public static final int STARTF_FORCEONFEEDBACK = 64;
    public static final int STARTF_FORCEOFFFEEDBACK = 128;
    public static final int STARTF_USESTDHANDLES = 256;
    public static final int DEBUG_PROCESS = 1;
    public static final int DEBUG_ONLY_THIS_PROCESS = 2;
    public static final int CREATE_SUSPENDED = 4;
    public static final int DETACHED_PROCESS = 8;
    public static final int CREATE_NEW_CONSOLE = 16;
    public static final int CREATE_NEW_PROCESS_GROUP = 512;
    public static final int CREATE_UNICODE_ENVIRONMENT = 1024;
    public static final int CREATE_SEPARATE_WOW_VDM = 2048;
    public static final int CREATE_SHARED_WOW_VDM = 4096;
    public static final int CREATE_FORCEDOS = 8192;
    public static final int INHERIT_PARENT_AFFINITY = 65536;
    public static final int CREATE_PROTECTED_PROCESS = 262144;
    public static final int EXTENDED_STARTUPINFO_PRESENT = 524288;
    public static final int CREATE_BREAKAWAY_FROM_JOB = 16777216;
    public static final int CREATE_PRESERVE_CODE_AUTHZ_LEVEL = 33554432;
    public static final int CREATE_DEFAULT_ERROR_MODE = 67108864;
    public static final int CREATE_NO_WINDOW = 134217728;
    public static final int INVALID_FILE_SIZE = -1;
    public static final int INVALID_SET_FILE_POINTER = -1;
    public static final int INVALID_FILE_ATTRIBUTES = -1;
    public static final int STILL_ACTIVE = 259;
    public static final int LMEM_FIXED = 0;
    public static final int LMEM_MOVEABLE = 2;
    public static final int LMEM_NOCOMPACT = 16;
    public static final int LMEM_NODISCARD = 32;
    public static final int LMEM_ZEROINIT = 64;
    public static final int LMEM_MODIFY = 128;
    public static final int LMEM_DISCARDABLE = 3840;
    public static final int LMEM_VALID_FLAGS = 3954;
    public static final int LMEM_INVALID_HANDLE = 32768;
    public static final int LHND = 66;
    public static final int LPTR = 64;
    public static final int LMEM_DISCARDED = 16384;
    public static final int LMEM_LOCKCOUNT = 255;
    public static final int FORMAT_MESSAGE_ALLOCATE_BUFFER = 256;
    public static final int FORMAT_MESSAGE_IGNORE_INSERTS = 512;
    public static final int FORMAT_MESSAGE_FROM_STRING = 1024;
    public static final int FORMAT_MESSAGE_FROM_HMODULE = 2048;
    public static final int FORMAT_MESSAGE_FROM_SYSTEM = 4096;
    public static final int FORMAT_MESSAGE_ARGUMENT_ARRAY = 8192;
    public static final int DRIVE_UNKNOWN = 0;
    public static final int DRIVE_NO_ROOT_DIR = 1;
    public static final int DRIVE_REMOVABLE = 2;
    public static final int DRIVE_FIXED = 3;
    public static final int DRIVE_REMOTE = 4;
    public static final int DRIVE_CDROM = 5;
    public static final int DRIVE_RAMDISK = 6;
    public static final int INFINITE = -1;
    
    public static int MAX_COMPUTERNAME_LENGTH() {
        if (Platform.isMac()) {
            return 15;
        }
        return 31;
    }
    
    static {
        WinBase.INVALID_HANDLE_VALUE = new WinNT.HANDLE(Pointer.createConstant((Pointer.SIZE == 8) ? -1L : 4294967295L));
    }
    
    public static class FILETIME extends Structure
    {
        public int dwLowDateTime;
        public int dwHighDateTime;
        private static final long EPOCH_DIFF = 11644473600000L;
        
        public static Date filetimeToDate(final int high, final int low) {
            final long filetime = high << 32 | (low & 0xFFFFFFFFL);
            final long ms_since_16010101 = filetime / 10000L;
            final long ms_since_16010102 = ms_since_16010101 - 11644473600000L;
            return new Date(ms_since_16010102);
        }
        
        public static long dateToFileTime(final Date date) {
            final long ms_since_19700101 = date.getTime();
            final long ms_since_19700102 = ms_since_19700101 + 11644473600000L;
            return ms_since_19700102 * 1000L * 10L;
        }
        
        public Date toDate() {
            return filetimeToDate(this.dwHighDateTime, this.dwLowDateTime);
        }
        
        public long toLong() {
            return this.toDate().getTime();
        }
        
        public String toString() {
            return super.toString() + ": " + this.toDate().toString();
        }
    }
    
    public static class SYSTEMTIME extends Structure
    {
        public short wYear;
        public short wMonth;
        public short wDayOfWeek;
        public short wDay;
        public short wHour;
        public short wMinute;
        public short wSecond;
        public short wMilliseconds;
    }
    
    public static class OVERLAPPED extends Structure
    {
        public BaseTSD.ULONG_PTR Internal;
        public BaseTSD.ULONG_PTR InternalHigh;
        public int Offset;
        public int OffsetHigh;
        public WinNT.HANDLE hEvent;
    }
    
    public static class SYSTEM_INFO extends Structure
    {
        public UNION processorArchitecture;
        public WinDef.DWORD dwPageSize;
        public Pointer lpMinimumApplicationAddress;
        public Pointer lpMaximumApplicationAddress;
        public BaseTSD.DWORD_PTR dwActiveProcessorMask;
        public WinDef.DWORD dwNumberOfProcessors;
        public WinDef.DWORD dwProcessorType;
        public WinDef.DWORD dwAllocationGranularity;
        public WinDef.WORD wProcessorLevel;
        public WinDef.WORD wProcessorRevision;
        
        public static class PI extends Structure
        {
            public WinDef.WORD wProcessorArchitecture;
            public WinDef.WORD wReserved;
            
            public static class ByReference extends PI implements Structure.ByReference
            {
            }
        }
        
        public static class UNION extends Union
        {
            public WinDef.DWORD dwOemID;
            public PI.ByReference pi;
        }
    }
    
    public static class MEMORYSTATUSEX extends Structure
    {
        public WinDef.DWORD dwLength;
        public WinDef.DWORD dwMemoryLoad;
        public WinDef.DWORDLONG ullTotalPhys;
        public WinDef.DWORDLONG ullAvailPhys;
        public WinDef.DWORDLONG ullTotalPageFile;
        public WinDef.DWORDLONG ullAvailPageFile;
        public WinDef.DWORDLONG ullTotalVirtual;
        public WinDef.DWORDLONG ullAvailVirtual;
        public WinDef.DWORDLONG ullAvailExtendedVirtual;
        
        public MEMORYSTATUSEX() {
            this.dwLength = new WinDef.DWORD((long)this.size());
        }
    }
    
    public static class SECURITY_ATTRIBUTES extends Structure
    {
        public WinDef.DWORD dwLength;
        public Pointer lpSecurityDescriptor;
        public boolean bInheritHandle;
        
        public SECURITY_ATTRIBUTES() {
            this.dwLength = new WinDef.DWORD((long)this.size());
        }
    }
    
    public static class STARTUPINFO extends Structure
    {
        public WinDef.DWORD cb;
        public String lpReserved;
        public String lpDesktop;
        public String lpTitle;
        public WinDef.DWORD dwX;
        public WinDef.DWORD dwY;
        public WinDef.DWORD dwXSize;
        public WinDef.DWORD dwYSize;
        public WinDef.DWORD dwXCountChars;
        public WinDef.DWORD dwYCountChars;
        public WinDef.DWORD dwFillAttribute;
        public int dwFlags;
        public WinDef.WORD wShowWindow;
        public WinDef.WORD cbReserved2;
        public ByteByReference lpReserved2;
        public WinNT.HANDLE hStdInput;
        public WinNT.HANDLE hStdOutput;
        public WinNT.HANDLE hStdError;
        
        public STARTUPINFO() {
            this.cb = new WinDef.DWORD((long)this.size());
        }
    }
    
    public static class PROCESS_INFORMATION extends Structure
    {
        public WinNT.HANDLE hProcess;
        public WinNT.HANDLE hThread;
        public WinDef.DWORD dwProcessId;
        public WinDef.DWORD dwThreadId;
    }
}
