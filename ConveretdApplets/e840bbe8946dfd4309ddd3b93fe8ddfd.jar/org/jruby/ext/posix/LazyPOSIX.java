// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.posix;

import java.io.IOException;
import java.io.FileDescriptor;

final class LazyPOSIX implements POSIX
{
    private final POSIXHandler handler;
    private final boolean useNativePosix;
    private volatile POSIX posix;
    
    LazyPOSIX(final POSIXHandler handler, final boolean useNativePosix) {
        this.handler = handler;
        this.useNativePosix = useNativePosix;
    }
    
    private final POSIX posix() {
        return (this.posix != null) ? this.posix : this.loadPOSIX();
    }
    
    private final synchronized POSIX loadPOSIX() {
        return (this.posix != null) ? this.posix : (this.posix = POSIXFactory.loadPOSIX(this.handler, this.useNativePosix));
    }
    
    public FileStat allocateStat() {
        return this.posix().allocateStat();
    }
    
    public int chmod(final String filename, final int mode) {
        return this.posix().chmod(filename, mode);
    }
    
    public int chown(final String filename, final int user, final int group) {
        return this.posix().chown(filename, user, group);
    }
    
    public int endgrent() {
        return this.posix().endgrent();
    }
    
    public int endpwent() {
        return this.posix().endpwent();
    }
    
    public int errno() {
        return this.posix().errno();
    }
    
    public void errno(final int value) {
        this.posix().errno(value);
    }
    
    public int exec(final String path, final String[] args) {
        return this.posix().exec(path, args);
    }
    
    public int execv(final String path, final String... argv) {
        return this.posix().execv(path, argv);
    }
    
    public int fork() {
        return this.posix().fork();
    }
    
    public FileStat fstat(final FileDescriptor descriptor) {
        return this.posix().fstat(descriptor);
    }
    
    public int getegid() {
        return this.posix().getegid();
    }
    
    public int geteuid() {
        return this.posix().geteuid();
    }
    
    public int getgid() {
        return this.posix().getgid();
    }
    
    public Group getgrent() {
        return this.posix().getgrent();
    }
    
    public Group getgrgid(final int which) {
        return this.posix().getgrgid(which);
    }
    
    public Group getgrnam(final String which) {
        return this.posix().getgrnam(which);
    }
    
    public String getlogin() {
        return this.posix().getlogin();
    }
    
    public int getpgid() {
        return this.posix().getpgid();
    }
    
    public int getpgid(final int pid) {
        return this.posix().getpgid(pid);
    }
    
    public int getpgrp() {
        return this.posix().getpgrp();
    }
    
    public int getpid() {
        return this.posix().getpid();
    }
    
    public int getppid() {
        return this.posix().getppid();
    }
    
    public int getpriority(final int which, final int who) {
        return this.posix().getpriority(which, who);
    }
    
    public Passwd getpwent() {
        return this.posix().getpwent();
    }
    
    public Passwd getpwnam(final String which) {
        return this.posix().getpwnam(which);
    }
    
    public Passwd getpwuid(final int which) {
        return this.posix().getpwuid(which);
    }
    
    public int getuid() {
        return this.posix().getuid();
    }
    
    public boolean isatty(final FileDescriptor descriptor) {
        return this.posix().isatty(descriptor);
    }
    
    public int kill(final int pid, final int signal) {
        return this.posix().kill(pid, signal);
    }
    
    public int lchmod(final String filename, final int mode) {
        return this.posix().lchmod(filename, mode);
    }
    
    public int lchown(final String filename, final int user, final int group) {
        return this.posix().lchown(filename, user, group);
    }
    
    public int link(final String oldpath, final String newpath) {
        return this.posix().link(oldpath, newpath);
    }
    
    public FileStat lstat(final String path) {
        return this.posix().lstat(path);
    }
    
    public int mkdir(final String path, final int mode) {
        return this.posix().mkdir(path, mode);
    }
    
    public String readlink(final String path) throws IOException {
        return this.posix().readlink(path);
    }
    
    public int setegid(final int egid) {
        return this.posix().setegid(egid);
    }
    
    public int seteuid(final int euid) {
        return this.posix().seteuid(euid);
    }
    
    public int setgid(final int gid) {
        return this.posix().setgid(gid);
    }
    
    public int setgrent() {
        return this.posix().setgrent();
    }
    
    public int setpgid(final int pid, final int pgid) {
        return this.posix().setpgid(pid, pgid);
    }
    
    public int setpgrp(final int pid, final int pgrp) {
        return this.posix().setpgrp(pid, pgrp);
    }
    
    public int setpriority(final int which, final int who, final int prio) {
        return this.posix().setpriority(which, who, prio);
    }
    
    public int setpwent() {
        return this.posix().setpwent();
    }
    
    public int setsid() {
        return this.posix().setsid();
    }
    
    public int setuid(final int uid) {
        return this.posix().setuid(uid);
    }
    
    public int aspawn(final boolean overlay, final String program, final String[] argv, final String path) {
        return this.posix().aspawn(overlay, program, argv, path);
    }
    
    public int spawn(final boolean ovelay, final String command, final String program, final String path) {
        return this.posix().spawn(ovelay, command, program, path);
    }
    
    public FileStat stat(final String path) {
        return this.posix().stat(path);
    }
    
    public int symlink(final String oldpath, final String newpath) {
        return this.posix().symlink(oldpath, newpath);
    }
    
    public int umask(final int mask) {
        return this.posix().umask(mask);
    }
    
    public int utimes(final String path, final long[] atimeval, final long[] mtimeval) {
        return this.posix().utimes(path, atimeval, mtimeval);
    }
    
    public int wait(final int[] status) {
        return this.posix().wait(status);
    }
    
    public int waitpid(final int pid, final int[] status, final int flags) {
        return this.posix().waitpid(pid, status, flags);
    }
    
    public boolean isNative() {
        return this.posix().isNative();
    }
    
    public LibC libc() {
        return this.posix().libc();
    }
}
