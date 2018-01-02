// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.posix;

import java.io.IOException;
import java.io.FileDescriptor;

public interface POSIX
{
    FileStat allocateStat();
    
    int chmod(final String p0, final int p1);
    
    int chown(final String p0, final int p1, final int p2);
    
    int exec(final String p0, final String... p1);
    
    int execv(final String p0, final String... p1);
    
    int fork();
    
    FileStat fstat(final FileDescriptor p0);
    
    int getegid();
    
    int geteuid();
    
    int seteuid(final int p0);
    
    int getgid();
    
    String getlogin();
    
    int getpgid();
    
    int getpgid(final int p0);
    
    int getpgrp();
    
    int getpid();
    
    int getppid();
    
    int getpriority(final int p0, final int p1);
    
    Passwd getpwent();
    
    Passwd getpwuid(final int p0);
    
    Passwd getpwnam(final String p0);
    
    Group getgrgid(final int p0);
    
    Group getgrnam(final String p0);
    
    Group getgrent();
    
    int endgrent();
    
    int setgrent();
    
    int endpwent();
    
    int setpwent();
    
    int getuid();
    
    boolean isatty(final FileDescriptor p0);
    
    int kill(final int p0, final int p1);
    
    int lchmod(final String p0, final int p1);
    
    int lchown(final String p0, final int p1, final int p2);
    
    int link(final String p0, final String p1);
    
    FileStat lstat(final String p0);
    
    int mkdir(final String p0, final int p1);
    
    String readlink(final String p0) throws IOException;
    
    int setsid();
    
    int setgid(final int p0);
    
    int setegid(final int p0);
    
    int setpgid(final int p0, final int p1);
    
    int setpgrp(final int p0, final int p1);
    
    int setpriority(final int p0, final int p1, final int p2);
    
    int setuid(final int p0);
    
    FileStat stat(final String p0);
    
    int symlink(final String p0, final String p1);
    
    int umask(final int p0);
    
    int utimes(final String p0, final long[] p1, final long[] p2);
    
    int waitpid(final int p0, final int[] p1, final int p2);
    
    int wait(final int[] p0);
    
    int errno();
    
    void errno(final int p0);
    
    boolean isNative();
    
    int aspawn(final boolean p0, final String p1, final String[] p2, final String p3);
    
    int spawn(final boolean p0, final String p1, final String p2, final String p3);
    
    LibC libc();
}
