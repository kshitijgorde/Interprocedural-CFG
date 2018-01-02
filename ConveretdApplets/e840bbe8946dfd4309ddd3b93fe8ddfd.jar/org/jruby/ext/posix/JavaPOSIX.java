// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.posix;

import org.jruby.ext.posix.util.Platform;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.ByteBuffer;
import com.kenai.constantine.platform.Errno;
import java.io.FileDescriptor;

final class JavaPOSIX implements POSIX
{
    private final POSIXHandler handler;
    private final JavaLibCHelper helper;
    
    JavaPOSIX(final POSIXHandler handler) {
        this.handler = handler;
        this.helper = new JavaLibCHelper(handler);
    }
    
    public FileStat allocateStat() {
        return new JavaFileStat(this, this.handler);
    }
    
    public int chmod(final String filename, final int mode) {
        return this.helper.chmod(filename, mode);
    }
    
    public int chown(final String filename, final int user, final int group) {
        return this.helper.chown(filename, user, group);
    }
    
    public int exec(final String path, final String... argv) {
        this.handler.unimplementedError("No exec in Java (yet)");
        return -1;
    }
    
    public int execv(final String path, final String... argv) {
        this.handler.unimplementedError("No execv in Java (yet)");
        return -1;
    }
    
    public FileStat fstat(final FileDescriptor descriptor) {
        this.handler.unimplementedError("fstat unimplemented");
        return null;
    }
    
    public int getegid() {
        return LoginInfo.GID;
    }
    
    public int geteuid() {
        return LoginInfo.UID;
    }
    
    public int getgid() {
        return LoginInfo.GID;
    }
    
    public String getlogin() {
        return this.helper.getlogin();
    }
    
    public int getpgid() {
        return this.unimplementedInt("getpgid");
    }
    
    public int getpgrp() {
        return this.unimplementedInt("getpgrp");
    }
    
    public int getpid() {
        return this.helper.getpid();
    }
    
    public int getppid() {
        return this.unimplementedInt("getppid");
    }
    
    public Passwd getpwent() {
        return this.helper.getpwent();
    }
    
    public Passwd getpwuid(final int which) {
        return this.helper.getpwuid(which);
    }
    
    public Group getgrgid(final int which) {
        this.handler.unimplementedError("getgrgid unimplemented");
        return null;
    }
    
    public Passwd getpwnam(final String which) {
        this.handler.unimplementedError("getpwnam unimplemented");
        return null;
    }
    
    public Group getgrnam(final String which) {
        this.handler.unimplementedError("getgrnam unimplemented");
        return null;
    }
    
    public Group getgrent() {
        this.handler.unimplementedError("getgrent unimplemented");
        return null;
    }
    
    public int setpwent() {
        return this.helper.setpwent();
    }
    
    public int endpwent() {
        return this.helper.endpwent();
    }
    
    public int setgrent() {
        return this.unimplementedInt("setgrent");
    }
    
    public int endgrent() {
        return this.unimplementedInt("endgrent");
    }
    
    public int getuid() {
        return LoginInfo.UID;
    }
    
    public int fork() {
        return -1;
    }
    
    public boolean isatty(final FileDescriptor fd) {
        return fd == FileDescriptor.in || fd == FileDescriptor.out || fd == FileDescriptor.err;
    }
    
    public int kill(final int pid, final int signal) {
        return this.unimplementedInt("kill");
    }
    
    public int lchmod(final String filename, final int mode) {
        return this.unimplementedInt("lchmod");
    }
    
    public int lchown(final String filename, final int user, final int group) {
        return this.unimplementedInt("lchown");
    }
    
    public int link(final String oldpath, final String newpath) {
        return this.helper.link(oldpath, newpath);
    }
    
    public FileStat lstat(final String path) {
        final FileStat stat = this.allocateStat();
        if (this.helper.lstat(path, stat) < 0) {
            this.handler.error(Errno.ENOENT, path);
        }
        return stat;
    }
    
    public int mkdir(final String path, final int mode) {
        return this.helper.mkdir(path, mode);
    }
    
    public String readlink(final String path) throws IOException {
        final ByteBuffer buffer = ByteBuffer.allocateDirect(256);
        final int result = this.helper.readlink(path, buffer, buffer.capacity());
        if (result == -1) {
            return null;
        }
        buffer.position(0);
        buffer.limit(result);
        return Charset.forName("ASCII").decode(buffer).toString();
    }
    
    public FileStat stat(final String path) {
        final FileStat stat = this.allocateStat();
        if (this.helper.stat(path, stat) < 0) {
            this.handler.error(Errno.ENOENT, path);
        }
        return stat;
    }
    
    public int symlink(final String oldpath, final String newpath) {
        return this.helper.symlink(oldpath, newpath);
    }
    
    public int setegid(final int egid) {
        return this.unimplementedInt("setegid");
    }
    
    public int seteuid(final int euid) {
        return this.unimplementedInt("seteuid");
    }
    
    public int setgid(final int gid) {
        return this.unimplementedInt("setgid");
    }
    
    public int getpgid(final int pid) {
        return this.unimplementedInt("getpgid");
    }
    
    public int setpgid(final int pid, final int pgid) {
        return this.unimplementedInt("setpgid");
    }
    
    public int setpgrp(final int pid, final int pgrp) {
        return this.unimplementedInt("setpgrp");
    }
    
    public int setsid() {
        return this.unimplementedInt("setsid");
    }
    
    public int setuid(final int uid) {
        return this.unimplementedInt("setuid");
    }
    
    public int umask(final int mask) {
        return 0;
    }
    
    public int utimes(final String path, final long[] atimeval, final long[] mtimeval) {
        long mtimeMillis;
        if (mtimeval != null) {
            assert mtimeval.length == 2;
            mtimeMillis = mtimeval[0] * 1000L + mtimeval[1] / 1000L;
        }
        else {
            mtimeMillis = System.currentTimeMillis();
        }
        new File(path).setLastModified(mtimeMillis);
        return 0;
    }
    
    public int wait(final int[] status) {
        return this.unimplementedInt("wait");
    }
    
    public int waitpid(final int pid, final int[] status, final int flags) {
        return this.unimplementedInt("waitpid");
    }
    
    public int getpriority(final int which, final int who) {
        return this.unimplementedInt("getpriority");
    }
    
    public int setpriority(final int which, final int who, final int prio) {
        return this.unimplementedInt("setpriority");
    }
    
    public int errno() {
        return 0;
    }
    
    public void errno(final int value) {
    }
    
    public boolean isNative() {
        return false;
    }
    
    public LibC libc() {
        return null;
    }
    
    private int unimplementedInt(final String message) {
        this.handler.unimplementedError(message);
        return -1;
    }
    
    public int aspawn(final boolean overlay, final String program, final String[] argv, final String path) {
        this.handler.unimplementedError("No spawn in Java");
        return -1;
    }
    
    public int spawn(final boolean ovelay, final String command, final String program, final String path) {
        this.handler.unimplementedError("No spawn in Java");
        return -1;
    }
    
    static final class LoginInfo
    {
        public static final int UID;
        public static final int GID;
        public static final String USERNAME;
        
        static {
            UID = IDHelper.getInt("-u");
            GID = IDHelper.getInt("-g");
            USERNAME = IDHelper.getString("-un");
        }
    }
    
    private static final class IDHelper
    {
        private static final String ID_CMD;
        private static final int NOBODY;
        
        public static int getInt(final String option) {
            try {
                final Process p = Runtime.getRuntime().exec(new String[] { IDHelper.ID_CMD, option });
                final BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
                return Integer.parseInt(r.readLine());
            }
            catch (IOException ex) {
                return IDHelper.NOBODY;
            }
            catch (NumberFormatException ex2) {
                return IDHelper.NOBODY;
            }
            catch (SecurityException ex3) {
                return IDHelper.NOBODY;
            }
        }
        
        public static String getString(final String option) {
            try {
                final Process p = Runtime.getRuntime().exec(new String[] { IDHelper.ID_CMD, option });
                final BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
                return r.readLine();
            }
            catch (IOException ex) {
                return null;
            }
        }
        
        static {
            ID_CMD = (Platform.IS_SOLARIS ? "/usr/xpg4/bin/id" : "/usr/bin/id");
            NOBODY = (Platform.IS_WINDOWS ? 0 : 32767);
        }
    }
    
    private static final class FakePasswd implements Passwd
    {
        public String getLoginName() {
            return LoginInfo.USERNAME;
        }
        
        public String getPassword() {
            return "";
        }
        
        public long getUID() {
            return LoginInfo.UID;
        }
        
        public long getGID() {
            return LoginInfo.GID;
        }
        
        public int getPasswdChangeTime() {
            return 0;
        }
        
        public String getAccessClass() {
            return "";
        }
        
        public String getGECOS() {
            return this.getLoginName();
        }
        
        public String getHome() {
            return "/";
        }
        
        public String getShell() {
            return "/bin/sh";
        }
        
        public int getExpire() {
            return -1;
        }
    }
}
