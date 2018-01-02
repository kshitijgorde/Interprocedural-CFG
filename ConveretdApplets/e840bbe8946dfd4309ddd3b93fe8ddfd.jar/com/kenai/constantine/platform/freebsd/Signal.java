// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.constantine.platform.freebsd;

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
    SIGUSR1(30), 
    SIGSEGV(11), 
    SIGUSR2(31), 
    SIGPIPE(13), 
    SIGALRM(14), 
    SIGTERM(15), 
    SIGCHLD(20), 
    SIGCONT(19), 
    SIGSTOP(17), 
    SIGTSTP(18), 
    SIGTTIN(21), 
    SIGTTOU(22), 
    SIGURG(16), 
    SIGXCPU(24), 
    SIGXFSZ(25), 
    SIGVTALRM(26), 
    SIGPROF(27), 
    SIGWINCH(28), 
    SIGIO(23), 
    SIGSYS(12), 
    NSIG(32);
    
    private final int value;
    public static final long MIN_VALUE = 1L;
    public static final long MAX_VALUE = 32L;
    
    private Signal(final int value) {
        this.value = value;
    }
    
    public final int value() {
        return this.value;
    }
}
