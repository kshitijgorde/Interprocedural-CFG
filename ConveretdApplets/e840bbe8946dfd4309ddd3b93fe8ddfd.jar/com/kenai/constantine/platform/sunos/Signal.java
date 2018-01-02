// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.constantine.platform.sunos;

import com.kenai.constantine.Constant;

public enum Signal implements Constant
{
    SIGHUP(1), 
    SIGINT(2), 
    SIGQUIT(3), 
    SIGILL(4), 
    SIGTRAP(5), 
    SIGABRT(6), 
    SIGIOT(6), 
    SIGBUS(10), 
    SIGFPE(8), 
    SIGKILL(9), 
    SIGUSR1(16), 
    SIGSEGV(11), 
    SIGUSR2(17), 
    SIGPIPE(13), 
    SIGALRM(14), 
    SIGTERM(15), 
    SIGCLD(18), 
    SIGCHLD(18), 
    SIGCONT(25), 
    SIGSTOP(23), 
    SIGTSTP(24), 
    SIGTTIN(26), 
    SIGTTOU(27), 
    SIGURG(21), 
    SIGXCPU(30), 
    SIGXFSZ(31), 
    SIGVTALRM(28), 
    SIGPROF(29), 
    SIGWINCH(20), 
    SIGPOLL(22), 
    SIGIO(22), 
    SIGPWR(19), 
    SIGSYS(12), 
    SIGRTMIN(41), 
    SIGRTMAX(48), 
    NSIG(49);
    
    private final int value;
    public static final long MIN_VALUE = 1L;
    public static final long MAX_VALUE = 49L;
    
    private Signal(final int value) {
        this.value = value;
    }
    
    public final int value() {
        return this.value;
    }
}
