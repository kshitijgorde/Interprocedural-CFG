// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.posix;

import com.kenai.constantine.platform.windows.LastError;
import java.util.HashMap;
import java.io.UnsupportedEncodingException;
import java.io.File;
import com.kenai.jaffl.Type;
import com.kenai.jaffl.FFIProvider;
import org.jruby.ext.posix.util.WindowsHelpers;
import java.io.FileDescriptor;
import com.kenai.jaffl.Pointer;
import com.kenai.constantine.platform.Errno;
import java.util.Map;

final class WindowsPOSIX extends BaseNativePOSIX
{
    private static final int FILE_TYPE_CHAR = 2;
    private static final Map<Integer, Errno> errorToErrnoMapper;
    private static final int INVALID_HANDLE_VALUE = -1;
    private static final int GENERIC_ALL = 268435456;
    private static final int GENERIC_READ = Integer.MIN_VALUE;
    private static final int GENERIC_WRITE = 1073741824;
    private static final int GENERIC_EXECUTE = 33554432;
    private static final int FILE_SHARE_DELETE = 4;
    private static final int FILE_SHARE_READ = 1;
    private static final int FILE_SHARE_WRITE = 2;
    private static final int CREATE_ALWAYS = 2;
    private static final int CREATE_NEW = 1;
    private static final int OPEN_ALWAYS = 4;
    private static final int OPEN_EXISTING = 3;
    private static final int TRUNCATE_EXISTING = 5;
    public static final int FILE_FLAG_BACKUP_SEMANTICS = 33554432;
    private static final int STARTF_USESTDHANDLES = 256;
    
    WindowsPOSIX(final String libraryName, final LibCProvider libc, final POSIXHandler handler) {
        super(libraryName, libc, handler);
    }
    
    public BaseHeapFileStat allocateStat() {
        return new WindowsFileStat(this);
    }
    
    public int kill(final int pid, final int signal) {
        this.handler.unimplementedError("kill");
        return -1;
    }
    
    public int chown(final String filename, final int user, final int group) {
        return 0;
    }
    
    public int exec(final String path, final String[] argv) {
        if (argv.length == 1) {
            return this.spawn(true, argv[0], null, path);
        }
        return this.aspawn(true, null, argv, path);
    }
    
    public int execv(final String path, final String[] argv) {
        this.handler.unimplementedError("egid");
        return -1;
    }
    
    public int getegid() {
        this.handler.unimplementedError("egid");
        return -1;
    }
    
    public int setegid(final int egid) {
        this.handler.unimplementedError("setegid");
        return -1;
    }
    
    public int geteuid() {
        return 0;
    }
    
    public int seteuid(final int euid) {
        this.handler.unimplementedError("seteuid");
        return -1;
    }
    
    public int getuid() {
        return 0;
    }
    
    public int setuid(final int uid) {
        this.handler.unimplementedError("setuid");
        return -1;
    }
    
    public int getgid() {
        this.handler.unimplementedError("getgid");
        return -1;
    }
    
    public int setgid(final int gid) {
        this.handler.unimplementedError("setgid");
        return -1;
    }
    
    public int getpgid(final int pid) {
        this.handler.unimplementedError("getpgid");
        return -1;
    }
    
    public int getpgid() {
        this.handler.unimplementedError("getpgid");
        return -1;
    }
    
    public int setpgid(final int pid, final int pgid) {
        this.handler.unimplementedError("setpgid");
        return -1;
    }
    
    public int getpriority(final int which, final int who) {
        this.handler.unimplementedError("getpriority");
        return -1;
    }
    
    public int setpriority(final int which, final int who, final int prio) {
        this.handler.unimplementedError("setpriority");
        return -1;
    }
    
    public int getppid() {
        return 0;
    }
    
    public int lchmod(final String filename, final int mode) {
        this.handler.unimplementedError("lchmod");
        return -1;
    }
    
    public int lchown(final String filename, final int user, final int group) {
        this.handler.unimplementedError("lchown");
        return -1;
    }
    
    public FileStat lstat(final String path) {
        return this.stat(path);
    }
    
    public String readlink(final String oldpath) {
        this.handler.unimplementedError("readlink");
        return null;
    }
    
    public int utimes(final String path, final long[] atimeval, final long[] mtimeval) {
        final WindowsLibC libc = (WindowsLibC)this.libc();
        final byte[] wpath = toWPath(path);
        FileTime aTime = (atimeval == null) ? null : this.unixTimeToFileTime(atimeval[0]);
        FileTime mTime = (mtimeval == null) ? null : this.unixTimeToFileTime(mtimeval[0]);
        if (aTime == null || mTime == null) {
            final FileTime nowFile = this.unixTimeToFileTime(System.currentTimeMillis() / 1000L);
            if (aTime == null) {
                aTime = nowFile;
            }
            if (mTime == null) {
                mTime = nowFile;
            }
        }
        final int handle = libc.CreateFileW(wpath, 1073741824, 3, null, 3, 33554432, 0);
        if (handle == -1) {
            return -1;
        }
        final boolean timeSet = libc.SetFileTime(handle, null, aTime, mTime);
        libc.CloseHandle(handle);
        return timeSet ? 0 : -1;
    }
    
    private FileTime unixTimeToFileTime(final long unixTime) {
        final long ft = (unixTime + 11644473600L) * 10000000L;
        final FileTime fileTime = new FileTime();
        fileTime.dwLowDateTime.set(ft & 0xFFFFFFFFL);
        fileTime.dwHighDateTime.set(ft >> 32 & 0xFFFFFFFFL);
        return fileTime;
    }
    
    public int wait(final int[] status) {
        this.handler.unimplementedError("wait");
        return -1;
    }
    
    public int waitpid(final int pid, final int[] status, final int flags) {
        this.handler.unimplementedError("waitpid");
        return -1;
    }
    
    public String getlogin() {
        return this.helper.getlogin();
    }
    
    public int endgrent() {
        return 0;
    }
    
    public int endpwent() {
        return this.helper.endpwent();
    }
    
    public Group getgrent() {
        return null;
    }
    
    public Passwd getpwent() {
        return null;
    }
    
    public Group getgrgid(final int which) {
        return null;
    }
    
    public Passwd getpwnam(final String which) {
        return null;
    }
    
    public Group getgrnam(final String which) {
        return null;
    }
    
    public int setgrent() {
        return 0;
    }
    
    public int setpwent() {
        return this.helper.setpwent();
    }
    
    public Passwd getpwuid(final int which) {
        return null;
    }
    
    public boolean isatty(final FileDescriptor fd) {
        final int handle = (int)this.helper.gethandle(fd);
        final int type = ((WindowsLibC)this.libc()).GetFileType(handle);
        return type == 2;
    }
    
    public int mkdir(final String path, final int mode) {
        final byte[] widePath = toWPath(path);
        final int res = ((WindowsLibC)this.libc())._wmkdir(widePath);
        if (res < 0) {
            final int error = this.errno();
            this.handler.error(mapErrorToErrno(error), path);
        }
        return res;
    }
    
    public int link(final String oldpath, final String newpath) {
        final byte[] oldWPath = toWPath(oldpath);
        final byte[] newWPath = toWPath(newpath);
        final boolean linkCreated = ((WindowsLibC)this.libc()).CreateHardLinkW(newWPath, oldWPath, null);
        if (!linkCreated) {
            final int error = this.errno();
            this.handler.error(mapErrorToErrno(error), oldpath + " or " + newpath);
            return error;
        }
        return 0;
    }
    
    public int aspawn(final boolean overlay, final String program, final String[] argv, final String path) {
        try {
            if (argv.length == 0) {
                return -1;
            }
            final String[] cmds = WindowsHelpers.processCommandArgs(this, program, argv, path);
            return this.childResult(this.createProcess(cmds[0], cmds[1], null, null, null, null), overlay);
        }
        catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
    
    public int spawn(final boolean overlay, final String command, final String program, final String path) {
        if (command == null) {
            return -1;
        }
        final String[] cmds = WindowsHelpers.processCommandLine(this, command, program, path);
        return this.childResult(this.createProcess(cmds[0], cmds[1], null, null, null, null), overlay);
    }
    
    private int childResult(final WindowsChildRecord child, final boolean overlay) {
        if (child == null) {
            return -1;
        }
        if (overlay) {
            final Pointer exitCode = FFIProvider.getProvider().getMemoryManager().allocate(Type.UINT.size());
            final WindowsLibC libc = (WindowsLibC)this.libc();
            final int handle = child.getProcess().intValue();
            libc.WaitForSingleObject(handle, -1);
            libc.GetExitCodeProcess(handle, exitCode);
            libc.CloseHandle(handle);
            System.exit(exitCode.getInt(0L));
        }
        return child.getPid();
    }
    
    private static Errno mapErrorToErrno(final int error) {
        Errno errno = WindowsPOSIX.errorToErrnoMapper.get(error);
        if (errno == null) {
            errno = Errno.__UNKNOWN_CONSTANT__;
        }
        return errno;
    }
    
    private static byte[] toWPath(String path) {
        final boolean absolute = new File(path).isAbsolute();
        if (absolute) {
            path = "//?/" + path;
        }
        return toWString(path);
    }
    
    private static byte[] toWString(String string) {
        string += '\0';
        try {
            return string.getBytes("UTF-16LE");
        }
        catch (UnsupportedEncodingException e) {
            return null;
        }
    }
    
    private WindowsChildRecord createProcess(final String command, final String program, WindowsSecurityAttributes securityAttributes, final Pointer input, final Pointer output, final Pointer error) {
        if (command == null || program == null) {
            this.handler.error(Errno.EFAULT, "no command or program specified");
            return null;
        }
        if (securityAttributes == null) {
            securityAttributes = new WindowsSecurityAttributes();
        }
        final WindowsStartupInfo startupInfo = new WindowsStartupInfo();
        final WindowsLibC libc = (WindowsLibC)this.libc();
        startupInfo.setFlags(256);
        startupInfo.setStandardInput((input != null) ? input : libc.GetStdHandle(-10));
        startupInfo.setStandardOutput((output != null) ? output : libc.GetStdHandle(-11));
        startupInfo.setStandardError((error != null) ? input : libc.GetStdHandle(-12));
        final int creationFlags = 32;
        final WindowsProcessInformation processInformation = new WindowsProcessInformation();
        final boolean returnValue = libc.CreateProcessA(program, command, securityAttributes, securityAttributes, securityAttributes.getInheritHandle() ? 1 : 0, creationFlags, null, null, startupInfo, processInformation);
        if (!returnValue) {
            return null;
        }
        libc.CloseHandle(processInformation.getThread());
        return new WindowsChildRecord(processInformation.getProcess(), processInformation.getPid());
    }
    
    static {
        (errorToErrnoMapper = new HashMap<Integer, Errno>()).put(LastError.ERROR_INVALID_FUNCTION.value(), Errno.EINVAL);
        WindowsPOSIX.errorToErrnoMapper.put(LastError.ERROR_FILE_NOT_FOUND.value(), Errno.ENOENT);
        WindowsPOSIX.errorToErrnoMapper.put(LastError.ERROR_PATH_NOT_FOUND.value(), Errno.ENOENT);
        WindowsPOSIX.errorToErrnoMapper.put(LastError.ERROR_TOO_MANY_OPEN_FILES.value(), Errno.EMFILE);
        WindowsPOSIX.errorToErrnoMapper.put(LastError.ERROR_ACCESS_DENIED.value(), Errno.EACCES);
        WindowsPOSIX.errorToErrnoMapper.put(LastError.ERROR_INVALID_HANDLE.value(), Errno.EBADF);
        WindowsPOSIX.errorToErrnoMapper.put(LastError.ERROR_ARENA_TRASHED.value(), Errno.ENOMEM);
        WindowsPOSIX.errorToErrnoMapper.put(LastError.ERROR_NOT_ENOUGH_MEMORY.value(), Errno.ENOMEM);
        WindowsPOSIX.errorToErrnoMapper.put(LastError.ERROR_INVALID_BLOCK.value(), Errno.ENOMEM);
        WindowsPOSIX.errorToErrnoMapper.put(LastError.ERROR_BAD_ENVIRONMENT.value(), Errno.E2BIG);
        WindowsPOSIX.errorToErrnoMapper.put(LastError.ERROR_BAD_FORMAT.value(), Errno.ENOEXEC);
        WindowsPOSIX.errorToErrnoMapper.put(LastError.ERROR_INVALID_ACCESS.value(), Errno.EINVAL);
        WindowsPOSIX.errorToErrnoMapper.put(LastError.ERROR_INVALID_DATA.value(), Errno.EINVAL);
        WindowsPOSIX.errorToErrnoMapper.put(LastError.ERROR_INVALID_DRIVE.value(), Errno.ENOENT);
        WindowsPOSIX.errorToErrnoMapper.put(LastError.ERROR_CURRENT_DIRECTORY.value(), Errno.EACCES);
        WindowsPOSIX.errorToErrnoMapper.put(LastError.ERROR_NOT_SAME_DEVICE.value(), Errno.EXDEV);
        WindowsPOSIX.errorToErrnoMapper.put(LastError.ERROR_NO_MORE_FILES.value(), Errno.ENOENT);
        WindowsPOSIX.errorToErrnoMapper.put(LastError.ERROR_WRITE_PROTECT.value(), Errno.EROFS);
        WindowsPOSIX.errorToErrnoMapper.put(LastError.ERROR_BAD_UNIT.value(), Errno.ENODEV);
        WindowsPOSIX.errorToErrnoMapper.put(LastError.ERROR_NOT_READY.value(), Errno.ENXIO);
        WindowsPOSIX.errorToErrnoMapper.put(LastError.ERROR_BAD_COMMAND.value(), Errno.EACCES);
        WindowsPOSIX.errorToErrnoMapper.put(LastError.ERROR_CRC.value(), Errno.EACCES);
        WindowsPOSIX.errorToErrnoMapper.put(LastError.ERROR_BAD_LENGTH.value(), Errno.EACCES);
        WindowsPOSIX.errorToErrnoMapper.put(LastError.ERROR_SEEK.value(), Errno.EIO);
        WindowsPOSIX.errorToErrnoMapper.put(LastError.ERROR_NOT_DOS_DISK.value(), Errno.EACCES);
        WindowsPOSIX.errorToErrnoMapper.put(LastError.ERROR_SECTOR_NOT_FOUND.value(), Errno.EACCES);
        WindowsPOSIX.errorToErrnoMapper.put(LastError.ERROR_OUT_OF_PAPER.value(), Errno.EACCES);
        WindowsPOSIX.errorToErrnoMapper.put(LastError.ERROR_WRITE_FAULT.value(), Errno.EIO);
        WindowsPOSIX.errorToErrnoMapper.put(LastError.ERROR_READ_FAULT.value(), Errno.EIO);
        WindowsPOSIX.errorToErrnoMapper.put(LastError.ERROR_GEN_FAILURE.value(), Errno.EACCES);
        WindowsPOSIX.errorToErrnoMapper.put(LastError.ERROR_LOCK_VIOLATION.value(), Errno.EACCES);
        WindowsPOSIX.errorToErrnoMapper.put(LastError.ERROR_SHARING_VIOLATION.value(), Errno.EACCES);
        WindowsPOSIX.errorToErrnoMapper.put(LastError.ERROR_WRONG_DISK.value(), Errno.EACCES);
        WindowsPOSIX.errorToErrnoMapper.put(LastError.ERROR_SHARING_BUFFER_EXCEEDED.value(), Errno.EACCES);
        WindowsPOSIX.errorToErrnoMapper.put(LastError.ERROR_BAD_NETPATH.value(), Errno.ENOENT);
        WindowsPOSIX.errorToErrnoMapper.put(LastError.ERROR_NETWORK_ACCESS_DENIED.value(), Errno.EACCES);
        WindowsPOSIX.errorToErrnoMapper.put(LastError.ERROR_BAD_NET_NAME.value(), Errno.ENOENT);
        WindowsPOSIX.errorToErrnoMapper.put(LastError.ERROR_FILE_EXISTS.value(), Errno.EEXIST);
        WindowsPOSIX.errorToErrnoMapper.put(LastError.ERROR_CANNOT_MAKE.value(), Errno.EACCES);
        WindowsPOSIX.errorToErrnoMapper.put(LastError.ERROR_FAIL_I24.value(), Errno.EACCES);
        WindowsPOSIX.errorToErrnoMapper.put(LastError.ERROR_INVALID_PARAMETER.value(), Errno.EINVAL);
        WindowsPOSIX.errorToErrnoMapper.put(LastError.ERROR_NO_PROC_SLOTS.value(), Errno.EAGAIN);
        WindowsPOSIX.errorToErrnoMapper.put(LastError.ERROR_DRIVE_LOCKED.value(), Errno.EACCES);
        WindowsPOSIX.errorToErrnoMapper.put(LastError.ERROR_BROKEN_PIPE.value(), Errno.EPIPE);
        WindowsPOSIX.errorToErrnoMapper.put(LastError.ERROR_DISK_FULL.value(), Errno.ENOSPC);
        WindowsPOSIX.errorToErrnoMapper.put(LastError.ERROR_INVALID_TARGET_HANDLE.value(), Errno.EBADF);
        WindowsPOSIX.errorToErrnoMapper.put(LastError.ERROR_INVALID_HANDLE.value(), Errno.EINVAL);
        WindowsPOSIX.errorToErrnoMapper.put(LastError.ERROR_WAIT_NO_CHILDREN.value(), Errno.ECHILD);
        WindowsPOSIX.errorToErrnoMapper.put(LastError.ERROR_CHILD_NOT_COMPLETE.value(), Errno.ECHILD);
        WindowsPOSIX.errorToErrnoMapper.put(LastError.ERROR_DIRECT_ACCESS_HANDLE.value(), Errno.EBADF);
        WindowsPOSIX.errorToErrnoMapper.put(LastError.ERROR_NEGATIVE_SEEK.value(), Errno.EINVAL);
        WindowsPOSIX.errorToErrnoMapper.put(LastError.ERROR_SEEK_ON_DEVICE.value(), Errno.EACCES);
        WindowsPOSIX.errorToErrnoMapper.put(LastError.ERROR_DIR_NOT_EMPTY.value(), Errno.ENOTEMPTY);
        WindowsPOSIX.errorToErrnoMapper.put(LastError.ERROR_DIRECTORY.value(), Errno.ENOTDIR);
        WindowsPOSIX.errorToErrnoMapper.put(LastError.ERROR_NOT_LOCKED.value(), Errno.EACCES);
        WindowsPOSIX.errorToErrnoMapper.put(LastError.ERROR_BAD_PATHNAME.value(), Errno.ENOENT);
        WindowsPOSIX.errorToErrnoMapper.put(LastError.ERROR_MAX_THRDS_REACHED.value(), Errno.EAGAIN);
        WindowsPOSIX.errorToErrnoMapper.put(LastError.ERROR_LOCK_FAILED.value(), Errno.EACCES);
        WindowsPOSIX.errorToErrnoMapper.put(LastError.ERROR_ALREADY_EXISTS.value(), Errno.EEXIST);
        WindowsPOSIX.errorToErrnoMapper.put(LastError.ERROR_INVALID_STARTING_CODESEG.value(), Errno.ENOEXEC);
        WindowsPOSIX.errorToErrnoMapper.put(LastError.ERROR_INVALID_STACKSEG.value(), Errno.ENOEXEC);
        WindowsPOSIX.errorToErrnoMapper.put(LastError.ERROR_INVALID_MODULETYPE.value(), Errno.ENOEXEC);
        WindowsPOSIX.errorToErrnoMapper.put(LastError.ERROR_INVALID_EXE_SIGNATURE.value(), Errno.ENOEXEC);
        WindowsPOSIX.errorToErrnoMapper.put(LastError.ERROR_EXE_MARKED_INVALID.value(), Errno.ENOEXEC);
        WindowsPOSIX.errorToErrnoMapper.put(LastError.ERROR_BAD_EXE_FORMAT.value(), Errno.ENOEXEC);
        WindowsPOSIX.errorToErrnoMapper.put(LastError.ERROR_ITERATED_DATA_EXCEEDS_64k.value(), Errno.ENOEXEC);
        WindowsPOSIX.errorToErrnoMapper.put(LastError.ERROR_INVALID_MINALLOCSIZE.value(), Errno.ENOEXEC);
        WindowsPOSIX.errorToErrnoMapper.put(LastError.ERROR_DYNLINK_FROM_INVALID_RING.value(), Errno.ENOEXEC);
        WindowsPOSIX.errorToErrnoMapper.put(LastError.ERROR_IOPL_NOT_ENABLED.value(), Errno.ENOEXEC);
        WindowsPOSIX.errorToErrnoMapper.put(LastError.ERROR_INVALID_SEGDPL.value(), Errno.ENOEXEC);
        WindowsPOSIX.errorToErrnoMapper.put(LastError.ERROR_AUTODATASEG_EXCEEDS_64k.value(), Errno.ENOEXEC);
        WindowsPOSIX.errorToErrnoMapper.put(LastError.ERROR_RING2SEG_MUST_BE_MOVABLE.value(), Errno.ENOEXEC);
        WindowsPOSIX.errorToErrnoMapper.put(LastError.ERROR_RELOC_CHAIN_XEEDS_SEGLIM.value(), Errno.ENOEXEC);
        WindowsPOSIX.errorToErrnoMapper.put(LastError.ERROR_INFLOOP_IN_RELOC_CHAIN.value(), Errno.ENOEXEC);
        WindowsPOSIX.errorToErrnoMapper.put(LastError.ERROR_FILENAME_EXCED_RANGE.value(), Errno.ENOENT);
        WindowsPOSIX.errorToErrnoMapper.put(LastError.ERROR_NESTING_NOT_ALLOWED.value(), Errno.EAGAIN);
        WindowsPOSIX.errorToErrnoMapper.put(229, Errno.EPIPE);
        WindowsPOSIX.errorToErrnoMapper.put(LastError.ERROR_BAD_PIPE.value(), Errno.EPIPE);
        WindowsPOSIX.errorToErrnoMapper.put(LastError.ERROR_PIPE_BUSY.value(), Errno.EAGAIN);
        WindowsPOSIX.errorToErrnoMapper.put(LastError.ERROR_NO_DATA.value(), Errno.EPIPE);
        WindowsPOSIX.errorToErrnoMapper.put(LastError.ERROR_PIPE_NOT_CONNECTED.value(), Errno.EPIPE);
        WindowsPOSIX.errorToErrnoMapper.put(LastError.ERROR_OPERATION_ABORTED.value(), Errno.EINTR);
        WindowsPOSIX.errorToErrnoMapper.put(LastError.ERROR_NOT_ENOUGH_QUOTA.value(), Errno.ENOMEM);
        WindowsPOSIX.errorToErrnoMapper.put(LastError.ERROR_MOD_NOT_FOUND.value(), Errno.ENOENT);
        WindowsPOSIX.errorToErrnoMapper.put(LastError.WSAENAMETOOLONG.value(), Errno.ENAMETOOLONG);
        WindowsPOSIX.errorToErrnoMapper.put(LastError.WSAENOTEMPTY.value(), Errno.ENOTEMPTY);
        WindowsPOSIX.errorToErrnoMapper.put(LastError.WSAEINTR.value(), Errno.EINTR);
        WindowsPOSIX.errorToErrnoMapper.put(LastError.WSAEBADF.value(), Errno.EBADF);
        WindowsPOSIX.errorToErrnoMapper.put(LastError.WSAEACCES.value(), Errno.EACCES);
        WindowsPOSIX.errorToErrnoMapper.put(LastError.WSAEFAULT.value(), Errno.EFAULT);
        WindowsPOSIX.errorToErrnoMapper.put(LastError.WSAEINVAL.value(), Errno.EINVAL);
        WindowsPOSIX.errorToErrnoMapper.put(LastError.WSAEMFILE.value(), Errno.EMFILE);
    }
}
