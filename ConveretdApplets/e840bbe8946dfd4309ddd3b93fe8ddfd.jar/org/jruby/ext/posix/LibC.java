// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.posix;

import com.kenai.jaffl.annotations.In;
import java.nio.ByteBuffer;
import com.kenai.jaffl.annotations.IgnoreError;
import com.kenai.jaffl.annotations.Transient;
import com.kenai.jaffl.annotations.Out;

public interface LibC
{
    int chmod(final CharSequence p0, final int p1);
    
    int chown(final CharSequence p0, final int p1, final int p2);
    
    int fstat(final int p0, @Out @Transient final FileStat p1);
    
    int fstat64(final int p0, @Out @Transient final FileStat p1);
    
    @IgnoreError
    int getegid();
    
    int setegid(final int p0);
    
    @IgnoreError
    int geteuid();
    
    int seteuid(final int p0);
    
    @IgnoreError
    int getgid();
    
    String getlogin();
    
    int setgid(final int p0);
    
    int getpgid();
    
    int getpgid(final int p0);
    
    int setpgid(final int p0, final int p1);
    
    int getpgrp();
    
    int setpgrp(final int p0, final int p1);
    
    @IgnoreError
    int getppid();
    
    @IgnoreError
    int getpid();
    
    NativePasswd getpwent();
    
    NativePasswd getpwuid(final int p0);
    
    NativePasswd getpwnam(final CharSequence p0);
    
    NativeGroup getgrent();
    
    NativeGroup getgrgid(final int p0);
    
    NativeGroup getgrnam(final CharSequence p0);
    
    int setpwent();
    
    int endpwent();
    
    int setgrent();
    
    int endgrent();
    
    @IgnoreError
    int getuid();
    
    int setsid();
    
    int setuid(final int p0);
    
    int kill(final int p0, final int p1);
    
    int lchmod(final CharSequence p0, final int p1);
    
    int lchown(final CharSequence p0, final int p1, final int p2);
    
    int link(final CharSequence p0, final CharSequence p1);
    
    int lstat(final CharSequence p0, @Out @Transient final FileStat p1);
    
    int lstat64(final CharSequence p0, @Out @Transient final FileStat p1);
    
    int mkdir(final CharSequence p0, final int p1);
    
    int stat(final CharSequence p0, @Out @Transient final FileStat p1);
    
    int stat64(final CharSequence p0, @Out @Transient final FileStat p1);
    
    int symlink(final CharSequence p0, final CharSequence p1);
    
    int readlink(final CharSequence p0, @Out final ByteBuffer p1, final int p2);
    
    @IgnoreError
    int umask(final int p0);
    
    int utimes(final CharSequence p0, @In final Timeval[] p1);
    
    int fork();
    
    int waitpid(final int p0, @Out final int[] p1, final int p2);
    
    int wait(@Out final int[] p0);
    
    int getpriority(final int p0, final int p1);
    
    int setpriority(final int p0, final int p1, final int p2);
    
    @IgnoreError
    int isatty(final int p0);
    
    int read(final int p0, @Out final ByteBuffer p1, final int p2);
    
    int write(final int p0, @In final ByteBuffer p1, final int p2);
    
    int close(final int p0);
    
    int execv(final CharSequence p0, @In final CharSequence... p1);
}
