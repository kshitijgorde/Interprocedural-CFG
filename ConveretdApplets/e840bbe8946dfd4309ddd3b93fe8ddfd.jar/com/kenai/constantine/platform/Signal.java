// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.constantine.platform;

import com.kenai.constantine.Constant;

public enum Signal implements Constant
{
    SIGHUP, 
    SIGINT, 
    SIGQUIT, 
    SIGILL, 
    SIGTRAP, 
    SIGABRT, 
    SIGIOT, 
    SIGBUS, 
    SIGFPE, 
    SIGKILL, 
    SIGUSR1, 
    SIGSEGV, 
    SIGUSR2, 
    SIGPIPE, 
    SIGALRM, 
    SIGTERM, 
    SIGSTKFLT, 
    SIGCLD, 
    SIGCHLD, 
    SIGCONT, 
    SIGSTOP, 
    SIGTSTP, 
    SIGTTIN, 
    SIGTTOU, 
    SIGURG, 
    SIGXCPU, 
    SIGXFSZ, 
    SIGVTALRM, 
    SIGPROF, 
    SIGWINCH, 
    SIGPOLL, 
    SIGIO, 
    SIGPWR, 
    SIGSYS, 
    SIGUNUSED, 
    SIGRTMIN, 
    SIGRTMAX, 
    NSIG, 
    __UNKNOWN_CONSTANT__;
    
    private static final ConstantResolver<Signal> resolver;
    
    public final int value() {
        return Signal.resolver.intValue(this);
    }
    
    public final String description() {
        return Signal.resolver.description(this);
    }
    
    public final String toString() {
        return this.description();
    }
    
    public static final Signal valueOf(final int value) {
        return Signal.resolver.valueOf(value);
    }
    
    static {
        resolver = ConstantResolver.getResolver(Signal.class, 20000, 29999);
    }
}
