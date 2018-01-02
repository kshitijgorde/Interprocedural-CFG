// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.javasupport.util.RuntimeHelpers;
import org.jruby.anno.JRubyClass;
import org.jruby.util.ShellLauncher;
import org.jruby.runtime.CallBlock;
import org.jruby.runtime.Arity;
import org.jruby.runtime.Block;
import org.jruby.runtime.BlockCallback;
import com.kenai.constantine.platform.Signal;
import org.jruby.platform.Platform;
import org.jruby.ext.posix.POSIX;
import org.jruby.runtime.Visibility;
import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;
import com.kenai.constantine.platform.RLIMIT;
import com.kenai.constantine.platform.RLIM;
import com.kenai.constantine.platform.PRIO;
import org.jruby.runtime.ObjectAllocator;
import org.jruby.anno.JRubyModule;

@JRubyModule(name = { "Process" })
public class RubyProcess
{
    private static final NonNativeErrno ECHILD;
    private static final NonNativeErrno IGNORE;
    
    public static RubyModule createProcessModule(final Ruby runtime) {
        final RubyModule process = runtime.defineModule("Process");
        runtime.setProcess(process);
        final RubyClass process_status = process.defineClassUnder("Status", runtime.getObject(), ObjectAllocator.NOT_ALLOCATABLE_ALLOCATOR);
        runtime.setProcStatus(process_status);
        final RubyModule process_uid = process.defineModuleUnder("UID");
        runtime.setProcUID(process_uid);
        final RubyModule process_gid = process.defineModuleUnder("GID");
        runtime.setProcGID(process_gid);
        final RubyModule process_sys = process.defineModuleUnder("Sys");
        runtime.setProcSys(process_sys);
        process.defineAnnotatedMethods(RubyProcess.class);
        process_status.defineAnnotatedMethods(RubyStatus.class);
        process_uid.defineAnnotatedMethods(UserID.class);
        process_gid.defineAnnotatedMethods(GroupID.class);
        process_sys.defineAnnotatedMethods(Sys.class);
        runtime.loadConstantSet(process, PRIO.class);
        runtime.loadConstantSet(process, RLIM.class);
        runtime.loadConstantSet(process, RLIMIT.class);
        process.defineConstant("WNOHANG", runtime.newFixnum(1));
        process.defineConstant("WUNTRACED", runtime.newFixnum(2));
        return process;
    }
    
    @JRubyMethod(name = { "abort" }, optional = 1, module = true, visibility = Visibility.PRIVATE)
    public static IRubyObject abort(final ThreadContext context, final IRubyObject recv, final IRubyObject[] args) {
        return RubyKernel.abort(context, recv, args);
    }
    
    @JRubyMethod(name = { "exit!" }, optional = 1, module = true, visibility = Visibility.PRIVATE)
    public static IRubyObject exit_bang(final IRubyObject recv, final IRubyObject[] args) {
        return RubyKernel.exit_bang(recv, args);
    }
    
    @JRubyMethod(name = { "groups" }, module = true, visibility = Visibility.PRIVATE)
    public static IRubyObject groups(final IRubyObject recv) {
        throw recv.getRuntime().newNotImplementedError("Process#groups not yet implemented");
    }
    
    @JRubyMethod(name = { "setrlimit" }, rest = true, module = true, visibility = Visibility.PRIVATE)
    public static IRubyObject setrlimit(final IRubyObject recv, final IRubyObject[] args) {
        throw recv.getRuntime().newNotImplementedError("Process#setrlimit not yet implemented");
    }
    
    @Deprecated
    public static IRubyObject getpgrp(final IRubyObject recv) {
        return getpgrp(recv.getRuntime());
    }
    
    @JRubyMethod(name = { "getpgrp" }, module = true, visibility = Visibility.PRIVATE)
    public static IRubyObject getpgrp(final ThreadContext context, final IRubyObject recv) {
        return getpgrp(context.getRuntime());
    }
    
    public static IRubyObject getpgrp(final Ruby runtime) {
        return runtime.newFixnum(runtime.getPosix().getpgrp());
    }
    
    @JRubyMethod(name = { "groups=" }, required = 1, module = true, visibility = Visibility.PRIVATE)
    public static IRubyObject groups_set(final IRubyObject recv, final IRubyObject arg) {
        throw recv.getRuntime().newNotImplementedError("Process#groups not yet implemented");
    }
    
    @Deprecated
    public static IRubyObject waitpid(final IRubyObject recv, final IRubyObject[] args) {
        return waitpid(recv.getRuntime(), args);
    }
    
    @JRubyMethod(name = { "waitpid" }, rest = true, module = true, visibility = Visibility.PRIVATE)
    public static IRubyObject waitpid(final ThreadContext context, final IRubyObject recv, final IRubyObject[] args) {
        return waitpid(context.getRuntime(), args);
    }
    
    public static IRubyObject waitpid(final Ruby runtime, final IRubyObject[] args) {
        int pid = -1;
        int flags = 0;
        if (args.length > 0) {
            pid = (int)args[0].convertToInteger().getLongValue();
        }
        if (args.length > 1) {
            flags = (int)args[1].convertToInteger().getLongValue();
        }
        final int[] status = { 0 };
        runtime.getPosix().errno(0);
        pid = runtime.getPosix().waitpid(pid, status, flags);
        raiseErrnoIfSet(runtime, RubyProcess.ECHILD);
        runtime.getCurrentContext().setLastExitStatus(RubyStatus.newProcessStatus(runtime, status[0] >> 8 & 0xFF, pid));
        return runtime.newFixnum(pid);
    }
    
    @Deprecated
    public static IRubyObject wait(final IRubyObject recv, final IRubyObject[] args) {
        return wait(recv.getRuntime(), args);
    }
    
    @JRubyMethod(name = { "wait" }, rest = true, module = true, visibility = Visibility.PRIVATE)
    public static IRubyObject wait(final ThreadContext context, final IRubyObject recv, final IRubyObject[] args) {
        return wait(context.getRuntime(), args);
    }
    
    public static IRubyObject wait(final Ruby runtime, final IRubyObject[] args) {
        if (args.length > 0) {
            return waitpid(runtime, args);
        }
        final int[] status = { 0 };
        runtime.getPosix().errno(0);
        final int pid = runtime.getPosix().wait(status);
        raiseErrnoIfSet(runtime, RubyProcess.ECHILD);
        runtime.getCurrentContext().setLastExitStatus(RubyStatus.newProcessStatus(runtime, status[0] >> 8 & 0xFF, pid));
        return runtime.newFixnum(pid);
    }
    
    @Deprecated
    public static IRubyObject waitall(final IRubyObject recv) {
        return waitall(recv.getRuntime());
    }
    
    @JRubyMethod(name = { "waitall" }, module = true, visibility = Visibility.PRIVATE)
    public static IRubyObject waitall(final ThreadContext context, final IRubyObject recv) {
        return waitall(context.getRuntime());
    }
    
    public static IRubyObject waitall(final Ruby runtime) {
        final POSIX posix = runtime.getPosix();
        final RubyArray results = runtime.newArray();
        final int[] status = { 0 };
        for (int result = posix.wait(status); result != -1; result = posix.wait(status)) {
            results.append(runtime.newArray(runtime.newFixnum(result), RubyStatus.newProcessStatus(runtime, status[0] >> 8 & 0xFF, result)));
        }
        return results;
    }
    
    @Deprecated
    public static IRubyObject setsid(final IRubyObject recv) {
        return setsid(recv.getRuntime());
    }
    
    @JRubyMethod(name = { "setsid" }, module = true, visibility = Visibility.PRIVATE)
    public static IRubyObject setsid(final ThreadContext context, final IRubyObject recv) {
        return setsid(context.getRuntime());
    }
    
    public static IRubyObject setsid(final Ruby runtime) {
        return runtime.newFixnum(checkErrno(runtime, runtime.getPosix().setsid()));
    }
    
    @Deprecated
    public static IRubyObject setpgrp(final IRubyObject recv) {
        return setpgrp(recv.getRuntime());
    }
    
    @JRubyMethod(name = { "setpgrp" }, module = true, visibility = Visibility.PRIVATE)
    public static IRubyObject setpgrp(final ThreadContext context, final IRubyObject recv) {
        return setpgrp(context.getRuntime());
    }
    
    public static IRubyObject setpgrp(final Ruby runtime) {
        return runtime.newFixnum(checkErrno(runtime, runtime.getPosix().setpgid(0, 0)));
    }
    
    @Deprecated
    public static IRubyObject egid_set(final IRubyObject recv, final IRubyObject arg) {
        return egid_set(recv.getRuntime(), arg);
    }
    
    @JRubyMethod(name = { "egid=" }, required = 1, module = true, visibility = Visibility.PRIVATE)
    public static IRubyObject egid_set(final ThreadContext context, final IRubyObject recv, final IRubyObject arg) {
        return egid_set(context.getRuntime(), arg);
    }
    
    public static IRubyObject egid_set(final Ruby runtime, final IRubyObject arg) {
        checkErrno(runtime, runtime.getPosix().setegid((int)arg.convertToInteger().getLongValue()));
        return RubyFixnum.zero(runtime);
    }
    
    @Deprecated
    public static IRubyObject euid(final IRubyObject recv) {
        return euid(recv.getRuntime());
    }
    
    @JRubyMethod(name = { "euid" }, module = true, visibility = Visibility.PRIVATE)
    public static IRubyObject euid(final ThreadContext context, final IRubyObject recv) {
        return euid(context.getRuntime());
    }
    
    public static IRubyObject euid(final Ruby runtime) {
        return runtime.newFixnum(checkErrno(runtime, runtime.getPosix().geteuid()));
    }
    
    @Deprecated
    public static IRubyObject uid_set(final IRubyObject recv, final IRubyObject arg) {
        return uid_set(recv.getRuntime(), arg);
    }
    
    @JRubyMethod(name = { "uid=" }, required = 1, module = true, visibility = Visibility.PRIVATE)
    public static IRubyObject uid_set(final ThreadContext context, final IRubyObject recv, final IRubyObject arg) {
        return uid_set(context.getRuntime(), arg);
    }
    
    public static IRubyObject uid_set(final Ruby runtime, final IRubyObject arg) {
        checkErrno(runtime, runtime.getPosix().setuid((int)arg.convertToInteger().getLongValue()));
        return RubyFixnum.zero(runtime);
    }
    
    @Deprecated
    public static IRubyObject gid(final IRubyObject recv) {
        return gid(recv.getRuntime());
    }
    
    @JRubyMethod(name = { "gid" }, module = true, visibility = Visibility.PRIVATE)
    public static IRubyObject gid(final ThreadContext context, final IRubyObject recv) {
        return gid(context.getRuntime());
    }
    
    public static IRubyObject gid(final Ruby runtime) {
        if (Platform.IS_WINDOWS) {
            return RubyFixnum.zero(runtime);
        }
        return runtime.newFixnum(checkErrno(runtime, runtime.getPosix().getgid()));
    }
    
    @JRubyMethod(name = { "maxgroups" }, module = true, visibility = Visibility.PRIVATE)
    public static IRubyObject maxgroups(final IRubyObject recv) {
        throw recv.getRuntime().newNotImplementedError("Process#maxgroups not yet implemented");
    }
    
    @Deprecated
    public static IRubyObject getpriority(final IRubyObject recv, final IRubyObject arg1, final IRubyObject arg2) {
        return getpriority(recv.getRuntime(), arg1, arg2);
    }
    
    @JRubyMethod(name = { "getpriority" }, required = 2, module = true, visibility = Visibility.PRIVATE)
    public static IRubyObject getpriority(final ThreadContext context, final IRubyObject recv, final IRubyObject arg1, final IRubyObject arg2) {
        return getpriority(context.getRuntime(), arg1, arg2);
    }
    
    public static IRubyObject getpriority(final Ruby runtime, final IRubyObject arg1, final IRubyObject arg2) {
        final int which = (int)arg1.convertToInteger().getLongValue();
        final int who = (int)arg2.convertToInteger().getLongValue();
        final int result = checkErrno(runtime, runtime.getPosix().getpriority(which, who));
        return runtime.newFixnum(result);
    }
    
    @Deprecated
    public static IRubyObject uid(final IRubyObject recv) {
        return uid(recv.getRuntime());
    }
    
    @JRubyMethod(name = { "uid" }, module = true, visibility = Visibility.PRIVATE)
    public static IRubyObject uid(final ThreadContext context, final IRubyObject recv) {
        return uid(context.getRuntime());
    }
    
    public static IRubyObject uid(final Ruby runtime) {
        return runtime.newFixnum(checkErrno(runtime, runtime.getPosix().getuid()));
    }
    
    @Deprecated
    public static IRubyObject waitpid2(final IRubyObject recv, final IRubyObject[] args) {
        return waitpid2(recv.getRuntime(), args);
    }
    
    @JRubyMethod(name = { "waitpid2" }, rest = true, module = true, visibility = Visibility.PRIVATE)
    public static IRubyObject waitpid2(final ThreadContext context, final IRubyObject recv, final IRubyObject[] args) {
        return waitpid2(context.getRuntime(), args);
    }
    
    public static IRubyObject waitpid2(final Ruby runtime, final IRubyObject[] args) {
        int pid = -1;
        int flags = 0;
        if (args.length > 0) {
            pid = (int)args[0].convertToInteger().getLongValue();
        }
        if (args.length > 1) {
            flags = (int)args[1].convertToInteger().getLongValue();
        }
        final int[] status = { 0 };
        pid = checkErrno(runtime, runtime.getPosix().waitpid(pid, status, flags), RubyProcess.ECHILD);
        return runtime.newArray(runtime.newFixnum(pid), RubyStatus.newProcessStatus(runtime, status[0] >> 8 & 0xFF, pid));
    }
    
    @JRubyMethod(name = { "initgroups" }, required = 2, module = true, visibility = Visibility.PRIVATE)
    public static IRubyObject initgroups(final IRubyObject recv, final IRubyObject arg1, final IRubyObject arg2) {
        throw recv.getRuntime().newNotImplementedError("Process#initgroups not yet implemented");
    }
    
    @JRubyMethod(name = { "maxgroups=" }, required = 1, module = true, visibility = Visibility.PRIVATE)
    public static IRubyObject maxgroups_set(final IRubyObject recv, final IRubyObject arg) {
        throw recv.getRuntime().newNotImplementedError("Process#maxgroups_set not yet implemented");
    }
    
    @Deprecated
    public static IRubyObject ppid(final IRubyObject recv) {
        return ppid(recv.getRuntime());
    }
    
    @JRubyMethod(name = { "ppid" }, module = true, visibility = Visibility.PRIVATE)
    public static IRubyObject ppid(final ThreadContext context, final IRubyObject recv) {
        return ppid(context.getRuntime());
    }
    
    public static IRubyObject ppid(final Ruby runtime) {
        final int result = checkErrno(runtime, runtime.getPosix().getppid());
        return runtime.newFixnum(result);
    }
    
    @Deprecated
    public static IRubyObject gid_set(final IRubyObject recv, final IRubyObject arg) {
        return gid_set(recv.getRuntime(), arg);
    }
    
    @JRubyMethod(name = { "gid=" }, required = 1, module = true, visibility = Visibility.PRIVATE)
    public static IRubyObject gid_set(final ThreadContext context, final IRubyObject recv, final IRubyObject arg) {
        return gid_set(context.getRuntime(), arg);
    }
    
    public static IRubyObject gid_set(final Ruby runtime, final IRubyObject arg) {
        final int result = checkErrno(runtime, runtime.getPosix().setgid((int)arg.convertToInteger().getLongValue()));
        return runtime.newFixnum(result);
    }
    
    @Deprecated
    public static IRubyObject wait2(final IRubyObject recv, final IRubyObject[] args) {
        return waitpid2(recv.getRuntime(), args);
    }
    
    @JRubyMethod(name = { "wait2" }, rest = true, module = true, visibility = Visibility.PRIVATE)
    public static IRubyObject wait2(final ThreadContext context, final IRubyObject recv, final IRubyObject[] args) {
        return waitpid2(context.getRuntime(), args);
    }
    
    @Deprecated
    public static IRubyObject euid_set(final IRubyObject recv, final IRubyObject arg) {
        return euid_set(recv.getRuntime(), arg);
    }
    
    @JRubyMethod(name = { "euid=" }, required = 1, module = true, visibility = Visibility.PRIVATE)
    public static IRubyObject euid_set(final ThreadContext context, final IRubyObject recv, final IRubyObject arg) {
        return euid_set(context.getRuntime(), arg);
    }
    
    public static IRubyObject euid_set(final Ruby runtime, final IRubyObject arg) {
        checkErrno(runtime, runtime.getPosix().seteuid((int)arg.convertToInteger().getLongValue()));
        return RubyFixnum.zero(runtime);
    }
    
    @Deprecated
    public static IRubyObject setpriority(final IRubyObject recv, final IRubyObject arg1, final IRubyObject arg2, final IRubyObject arg3) {
        return setpriority(recv.getRuntime(), arg1, arg2, arg3);
    }
    
    @JRubyMethod(name = { "setpriority" }, required = 3, module = true, visibility = Visibility.PRIVATE)
    public static IRubyObject setpriority(final ThreadContext context, final IRubyObject recv, final IRubyObject arg1, final IRubyObject arg2, final IRubyObject arg3) {
        return setpriority(context.getRuntime(), arg1, arg2, arg3);
    }
    
    public static IRubyObject setpriority(final Ruby runtime, final IRubyObject arg1, final IRubyObject arg2, final IRubyObject arg3) {
        final int which = (int)arg1.convertToInteger().getLongValue();
        final int who = (int)arg2.convertToInteger().getLongValue();
        final int prio = (int)arg3.convertToInteger().getLongValue();
        runtime.getPosix().errno(0);
        final int result = checkErrno(runtime, runtime.getPosix().setpriority(which, who, prio));
        return runtime.newFixnum(result);
    }
    
    @Deprecated
    public static IRubyObject setpgid(final IRubyObject recv, final IRubyObject arg1, final IRubyObject arg2) {
        return setpgid(recv.getRuntime(), arg1, arg2);
    }
    
    @JRubyMethod(name = { "setpgid" }, required = 2, module = true, visibility = Visibility.PRIVATE)
    public static IRubyObject setpgid(final ThreadContext context, final IRubyObject recv, final IRubyObject arg1, final IRubyObject arg2) {
        return setpgid(context.getRuntime(), arg1, arg2);
    }
    
    public static IRubyObject setpgid(final Ruby runtime, final IRubyObject arg1, final IRubyObject arg2) {
        final int pid = (int)arg1.convertToInteger().getLongValue();
        final int gid = (int)arg2.convertToInteger().getLongValue();
        return runtime.newFixnum(checkErrno(runtime, runtime.getPosix().setpgid(pid, gid)));
    }
    
    @Deprecated
    public static IRubyObject getpgid(final IRubyObject recv, final IRubyObject arg) {
        return getpgid(recv.getRuntime(), arg);
    }
    
    @JRubyMethod(name = { "getpgid" }, required = 1, module = true, visibility = Visibility.PRIVATE)
    public static IRubyObject getpgid(final ThreadContext context, final IRubyObject recv, final IRubyObject arg) {
        return getpgid(context.getRuntime(), arg);
    }
    
    public static IRubyObject getpgid(final Ruby runtime, final IRubyObject arg) {
        return runtime.newFixnum(checkErrno(runtime, runtime.getPosix().getpgid((int)arg.convertToInteger().getLongValue())));
    }
    
    @Deprecated
    public static IRubyObject getrlimit(final IRubyObject recv, final IRubyObject arg) {
        return getrlimit(recv.getRuntime(), arg);
    }
    
    @JRubyMethod(name = { "getrlimit" }, required = 1, module = true, visibility = Visibility.PRIVATE)
    public static IRubyObject getrlimit(final ThreadContext context, final IRubyObject recv, final IRubyObject arg) {
        return getrlimit(context.getRuntime(), arg);
    }
    
    public static IRubyObject getrlimit(final Ruby runtime, final IRubyObject arg) {
        throw runtime.newNotImplementedError("Process#getrlimit not yet implemented");
    }
    
    @Deprecated
    public static IRubyObject egid(final IRubyObject recv) {
        return egid(recv.getRuntime());
    }
    
    @JRubyMethod(name = { "egid" }, module = true, visibility = Visibility.PRIVATE)
    public static IRubyObject egid(final ThreadContext context, final IRubyObject recv) {
        return egid(context.getRuntime());
    }
    
    public static IRubyObject egid(final Ruby runtime) {
        if (Platform.IS_WINDOWS) {
            return RubyFixnum.zero(runtime);
        }
        return runtime.newFixnum(checkErrno(runtime, runtime.getPosix().getegid()));
    }
    
    private static int parseSignalString(final Ruby runtime, final String value) {
        int startIndex = 0;
        final boolean negative = value.startsWith("-");
        if (value.startsWith("-")) {
            ++startIndex;
        }
        final String signalName = value.startsWith("SIG", startIndex) ? value : ("SIG" + value.substring(startIndex));
        try {
            final int signalValue = Signal.valueOf(signalName).value();
            return negative ? (-signalValue) : signalValue;
        }
        catch (IllegalArgumentException ex) {
            throw runtime.newArgumentError("unsupported name `SIG" + signalName + "'");
        }
    }
    
    @Deprecated
    public static IRubyObject kill(final IRubyObject recv, final IRubyObject[] args) {
        return kill(recv.getRuntime(), args);
    }
    
    @JRubyMethod(name = { "kill" }, rest = true, module = true, visibility = Visibility.PRIVATE)
    public static IRubyObject kill(final ThreadContext context, final IRubyObject recv, final IRubyObject[] args) {
        return kill(context.getRuntime(), args);
    }
    
    public static IRubyObject kill(final Ruby runtime, final IRubyObject[] args) {
        if (args.length < 2) {
            throw runtime.newArgumentError("wrong number of arguments -- kill(sig, pid...)");
        }
        if (Platform.IS_WINDOWS) {
            return runtime.getNil();
        }
        int signal;
        if (args[0] instanceof RubyFixnum) {
            signal = (int)((RubyFixnum)args[0]).getLongValue();
        }
        else if (args[0] instanceof RubySymbol) {
            signal = parseSignalString(runtime, args[0].toString());
        }
        else if (args[0] instanceof RubyString) {
            signal = parseSignalString(runtime, args[0].toString());
        }
        else {
            signal = parseSignalString(runtime, args[0].checkStringType().toString());
        }
        final boolean processGroupKill = signal < 0;
        if (processGroupKill) {
            signal = -signal;
        }
        final POSIX posix = runtime.getPosix();
        for (int i = 1; i < args.length; ++i) {
            int pid = RubyNumeric.num2int(args[i]);
            if (pid == 0) {
                pid = runtime.getPosix().getpid();
            }
            checkErrno(runtime, posix.kill(processGroupKill ? (-pid) : pid, signal));
        }
        return runtime.newFixnum(args.length - 1);
    }
    
    @JRubyMethod(name = { "detach" }, required = 1, module = true, visibility = Visibility.PRIVATE)
    public static IRubyObject detach(final ThreadContext context, final IRubyObject recv, final IRubyObject arg) {
        final int pid = (int)arg.convertToInteger().getLongValue();
        final Ruby runtime = context.getRuntime();
        final BlockCallback callback = new BlockCallback() {
            public IRubyObject call(final ThreadContext context, final IRubyObject[] args, final Block block) {
                final int[] status = { 0 };
                final Ruby runtime = context.runtime;
                final int result = checkErrno(runtime, runtime.getPosix().waitpid(pid, status, 0));
                return runtime.newFixnum(result);
            }
        };
        return RubyThread.newInstance(runtime.getThread(), IRubyObject.NULL_ARRAY, CallBlock.newCallClosure(recv, (RubyModule)recv, Arity.NO_ARGUMENTS, callback, context));
    }
    
    @Deprecated
    public static IRubyObject times(final IRubyObject recv, final Block unusedBlock) {
        return times(recv.getRuntime());
    }
    
    @JRubyMethod(module = true, visibility = Visibility.PRIVATE)
    public static IRubyObject times(final ThreadContext context, final IRubyObject recv, final Block unusedBlock) {
        return times(context.getRuntime());
    }
    
    public static IRubyObject times(final Ruby runtime) {
        final double currentTime = System.currentTimeMillis() / 1000.0;
        final double startTime = runtime.getStartTime() / 1000.0;
        final RubyFloat zero = runtime.newFloat(0.0);
        return RubyStruct.newStruct(runtime.getTmsStruct(), new IRubyObject[] { runtime.newFloat(currentTime - startTime), zero, zero, zero }, Block.NULL_BLOCK);
    }
    
    @Deprecated
    public static IRubyObject pid(final IRubyObject recv) {
        return pid(recv.getRuntime());
    }
    
    @JRubyMethod(name = { "pid" }, module = true, visibility = Visibility.PRIVATE)
    public static IRubyObject pid(final ThreadContext context, final IRubyObject recv) {
        return pid(context.getRuntime());
    }
    
    public static IRubyObject pid(final Ruby runtime) {
        return runtime.newFixnum(runtime.getPosix().getpid());
    }
    
    @JRubyMethod(name = { "fork" }, module = true, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_8)
    public static IRubyObject fork(final ThreadContext context, final IRubyObject recv, final Block block) {
        return RubyKernel.fork(context, recv, block);
    }
    
    @JRubyMethod(name = { "fork" }, module = true, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_9, notImplemented = true)
    public static IRubyObject fork19(final ThreadContext context, final IRubyObject recv, final Block block) {
        return RubyKernel.fork(context, recv, block);
    }
    
    @JRubyMethod(name = { "spawn" }, required = 1, rest = true, module = true, compat = CompatVersion.RUBY1_9)
    public static RubyFixnum spawn(final ThreadContext context, final IRubyObject recv, final IRubyObject[] args) {
        final Ruby runtime = context.getRuntime();
        final long pid = ShellLauncher.runExternalWithoutWait(runtime, args);
        return RubyFixnum.newFixnum(runtime, pid);
    }
    
    @JRubyMethod(name = { "exit" }, optional = 1, module = true, visibility = Visibility.PRIVATE)
    public static IRubyObject exit(final IRubyObject recv, final IRubyObject[] args) {
        return RubyKernel.exit(recv, args);
    }
    
    private static int checkErrno(final Ruby runtime, final int result) {
        return checkErrno(runtime, result, RubyProcess.IGNORE);
    }
    
    private static int checkErrno(final Ruby runtime, final int result, final NonNativeErrno nonNative) {
        if (result == -1) {
            if (runtime.getPosix().isNative()) {
                raiseErrnoIfSet(runtime, nonNative);
            }
            else {
                nonNative.handle(runtime, result);
            }
        }
        return result;
    }
    
    private static void raiseErrnoIfSet(final Ruby runtime, final NonNativeErrno nonNative) {
        if (runtime.getPosix().errno() != 0) {
            throw runtime.newErrnoFromInt(runtime.getPosix().errno());
        }
    }
    
    static {
        ECHILD = new NonNativeErrno() {
            public int handle(final Ruby runtime, final int result) {
                throw runtime.newErrnoECHILDError();
            }
        };
        IGNORE = new NonNativeErrno() {
            public int handle(final Ruby runtime, final int result) {
                return result;
            }
        };
    }
    
    @JRubyClass(name = { "Process::Status" })
    public static class RubyStatus extends RubyObject
    {
        private final long status;
        private final long pid;
        private static final long EXIT_SUCCESS = 0L;
        
        public RubyStatus(final Ruby runtime, final RubyClass metaClass, final long status, final long pid) {
            super(runtime, metaClass);
            this.status = status;
            this.pid = pid;
        }
        
        public static RubyStatus newProcessStatus(final Ruby runtime, final long status, final long pid) {
            return new RubyStatus(runtime, runtime.getProcStatus(), status, pid);
        }
        
        @JRubyMethod(name = { "to_int", "stopped?", "stopsig", "signaled?", "termsig?", "exited?", "coredump?" }, frame = true)
        public IRubyObject not_implemented() {
            final String error = "Process::Status#" + this.getRuntime().getCurrentContext().getFrameName() + " not implemented";
            throw this.getRuntime().newNotImplementedError(error);
        }
        
        @JRubyMethod(name = { "&" }, frame = true)
        public IRubyObject not_implemented1(final IRubyObject arg) {
            final String error = "Process::Status#" + this.getRuntime().getCurrentContext().getFrameName() + " not implemented";
            throw this.getRuntime().newNotImplementedError(error);
        }
        
        @JRubyMethod
        public IRubyObject exitstatus() {
            return this.getRuntime().newFixnum(this.status);
        }
        
        @Deprecated
        public IRubyObject op_rshift(final IRubyObject other) {
            return this.op_rshift(this.getRuntime(), other);
        }
        
        @JRubyMethod(name = { ">>" })
        public IRubyObject op_rshift(final ThreadContext context, final IRubyObject other) {
            return this.op_rshift(context.getRuntime(), other);
        }
        
        public IRubyObject op_rshift(final Ruby runtime, final IRubyObject other) {
            final long shiftValue = other.convertToInteger().getLongValue();
            return runtime.newFixnum(this.status >> (int)shiftValue);
        }
        
        @JRubyMethod(name = { "==" })
        public IRubyObject op_equal(final ThreadContext context, final IRubyObject other) {
            return RuntimeHelpers.invokedynamic(context, other, 1, this.to_i(context.getRuntime()));
        }
        
        @Deprecated
        public IRubyObject to_i() {
            return this.to_i(this.getRuntime());
        }
        
        @JRubyMethod
        public IRubyObject to_i(final ThreadContext context) {
            return this.to_i(context.getRuntime());
        }
        
        public IRubyObject to_i(final Ruby runtime) {
            return runtime.newFixnum(this.shiftedValue());
        }
        
        public IRubyObject to_s() {
            return this.to_s(this.getRuntime());
        }
        
        @JRubyMethod
        public IRubyObject to_s(final ThreadContext context) {
            return this.to_s(context.getRuntime());
        }
        
        public IRubyObject to_s(final Ruby runtime) {
            return runtime.newString(String.valueOf(this.shiftedValue()));
        }
        
        public IRubyObject inspect() {
            return this.inspect(this.getRuntime());
        }
        
        @JRubyMethod
        public IRubyObject inspect(final ThreadContext context) {
            return this.inspect(context.getRuntime());
        }
        
        public IRubyObject inspect(final Ruby runtime) {
            return runtime.newString("#<Process::Status: pid=" + this.pid + ",exited(" + String.valueOf(this.status) + ")>");
        }
        
        @JRubyMethod(name = { "success?" })
        public IRubyObject success_p(final ThreadContext context) {
            return context.getRuntime().newBoolean(this.status == 0L);
        }
        
        @JRubyMethod
        public IRubyObject pid(final ThreadContext context) {
            return context.getRuntime().newFixnum(this.pid);
        }
        
        private long shiftedValue() {
            return this.status << 8;
        }
    }
    
    @JRubyModule(name = { "Process::UID" })
    public static class UserID
    {
        @JRubyMethod(name = { "change_privilege" }, module = true)
        public static IRubyObject change_privilege(final IRubyObject self, final IRubyObject arg) {
            throw self.getRuntime().newNotImplementedError("Process::UID::change_privilege not implemented yet");
        }
        
        @Deprecated
        public static IRubyObject eid(final IRubyObject self) {
            return RubyProcess.euid(self.getRuntime());
        }
        
        @JRubyMethod(name = { "eid" }, module = true)
        public static IRubyObject eid(final ThreadContext context, final IRubyObject self) {
            return RubyProcess.euid(context.getRuntime());
        }
        
        @Deprecated
        public static IRubyObject eid(final IRubyObject self, final IRubyObject arg) {
            return eid(self.getRuntime(), arg);
        }
        
        @JRubyMethod(name = { "eid=" }, module = true)
        public static IRubyObject eid(final ThreadContext context, final IRubyObject self, final IRubyObject arg) {
            return eid(context.getRuntime(), arg);
        }
        
        public static IRubyObject eid(final Ruby runtime, final IRubyObject arg) {
            return RubyProcess.euid_set(runtime, arg);
        }
        
        @JRubyMethod(name = { "grant_privilege" }, module = true)
        public static IRubyObject grant_privilege(final IRubyObject self, final IRubyObject arg) {
            throw self.getRuntime().newNotImplementedError("Process::UID::grant_privilege not implemented yet");
        }
        
        @JRubyMethod(name = { "re_exchange" }, module = true)
        public static IRubyObject re_exchange(final ThreadContext context, final IRubyObject self) {
            return switch_rb(context, self, Block.NULL_BLOCK);
        }
        
        @JRubyMethod(name = { "re_exchangeable?" }, module = true)
        public static IRubyObject re_exchangeable_p(final IRubyObject self) {
            throw self.getRuntime().newNotImplementedError("Process::UID::re_exchangeable? not implemented yet");
        }
        
        @Deprecated
        public static IRubyObject rid(final IRubyObject self) {
            return rid(self.getRuntime());
        }
        
        @JRubyMethod(name = { "rid" }, module = true)
        public static IRubyObject rid(final ThreadContext context, final IRubyObject self) {
            return rid(context.getRuntime());
        }
        
        public static IRubyObject rid(final Ruby runtime) {
            return RubyProcess.uid(runtime);
        }
        
        @JRubyMethod(name = { "sid_available?" }, module = true)
        public static IRubyObject sid_available_p(final IRubyObject self) {
            throw self.getRuntime().newNotImplementedError("Process::UID::sid_available not implemented yet");
        }
        
        @JRubyMethod(name = { "switch" }, module = true, visibility = Visibility.PRIVATE)
        public static IRubyObject switch_rb(final ThreadContext context, final IRubyObject self, final Block block) {
            final Ruby runtime = context.getRuntime();
            final int uid = checkErrno(runtime, runtime.getPosix().getuid());
            final int euid = checkErrno(runtime, runtime.getPosix().geteuid());
            if (block.isGiven()) {
                try {
                    checkErrno(runtime, runtime.getPosix().seteuid(uid));
                    checkErrno(runtime, runtime.getPosix().setuid(euid));
                    return block.yield(context, runtime.getNil());
                }
                finally {
                    checkErrno(runtime, runtime.getPosix().seteuid(euid));
                    checkErrno(runtime, runtime.getPosix().setuid(uid));
                }
            }
            checkErrno(runtime, runtime.getPosix().seteuid(uid));
            checkErrno(runtime, runtime.getPosix().setuid(euid));
            return RubyFixnum.zero(runtime);
        }
    }
    
    @JRubyModule(name = { "Process::GID" })
    public static class GroupID
    {
        @JRubyMethod(name = { "change_privilege" }, module = true)
        public static IRubyObject change_privilege(final IRubyObject self, final IRubyObject arg) {
            throw self.getRuntime().newNotImplementedError("Process::GID::change_privilege not implemented yet");
        }
        
        @Deprecated
        public static IRubyObject eid(final IRubyObject self) {
            return eid(self.getRuntime());
        }
        
        @JRubyMethod(name = { "eid" }, module = true)
        public static IRubyObject eid(final ThreadContext context, final IRubyObject self) {
            return eid(context.getRuntime());
        }
        
        public static IRubyObject eid(final Ruby runtime) {
            return RubyProcess.egid(runtime);
        }
        
        @Deprecated
        public static IRubyObject eid(final IRubyObject self, final IRubyObject arg) {
            return eid(self.getRuntime(), arg);
        }
        
        @JRubyMethod(name = { "eid=" }, module = true)
        public static IRubyObject eid(final ThreadContext context, final IRubyObject self, final IRubyObject arg) {
            return eid(context.getRuntime(), arg);
        }
        
        public static IRubyObject eid(final Ruby runtime, final IRubyObject arg) {
            return RubyProcess.egid_set(runtime, arg);
        }
        
        @JRubyMethod(name = { "grant_privilege" }, module = true)
        public static IRubyObject grant_privilege(final IRubyObject self, final IRubyObject arg) {
            throw self.getRuntime().newNotImplementedError("Process::GID::grant_privilege not implemented yet");
        }
        
        @JRubyMethod(name = { "re_exchange" }, module = true)
        public static IRubyObject re_exchange(final ThreadContext context, final IRubyObject self) {
            return switch_rb(context, self, Block.NULL_BLOCK);
        }
        
        @JRubyMethod(name = { "re_exchangeable?" }, module = true)
        public static IRubyObject re_exchangeable_p(final IRubyObject self) {
            throw self.getRuntime().newNotImplementedError("Process::GID::re_exchangeable? not implemented yet");
        }
        
        @Deprecated
        public static IRubyObject rid(final IRubyObject self) {
            return rid(self.getRuntime());
        }
        
        @JRubyMethod(name = { "rid" }, module = true)
        public static IRubyObject rid(final ThreadContext context, final IRubyObject self) {
            return rid(context.getRuntime());
        }
        
        public static IRubyObject rid(final Ruby runtime) {
            return RubyProcess.gid(runtime);
        }
        
        @JRubyMethod(name = { "sid_available?" }, module = true)
        public static IRubyObject sid_available_p(final IRubyObject self) {
            throw self.getRuntime().newNotImplementedError("Process::GID::sid_available not implemented yet");
        }
        
        @JRubyMethod(name = { "switch" }, module = true, visibility = Visibility.PRIVATE)
        public static IRubyObject switch_rb(final ThreadContext context, final IRubyObject self, final Block block) {
            final Ruby runtime = context.getRuntime();
            final int gid = checkErrno(runtime, runtime.getPosix().getgid());
            final int egid = checkErrno(runtime, runtime.getPosix().getegid());
            if (block.isGiven()) {
                try {
                    checkErrno(runtime, runtime.getPosix().setegid(gid));
                    checkErrno(runtime, runtime.getPosix().setgid(egid));
                    return block.yield(context, runtime.getNil());
                }
                finally {
                    checkErrno(runtime, runtime.getPosix().setegid(egid));
                    checkErrno(runtime, runtime.getPosix().setgid(gid));
                }
            }
            checkErrno(runtime, runtime.getPosix().setegid(gid));
            checkErrno(runtime, runtime.getPosix().setgid(egid));
            return RubyFixnum.zero(runtime);
        }
    }
    
    @JRubyModule(name = { "Process::Sys" })
    public static class Sys
    {
        @Deprecated
        public static IRubyObject getegid(final IRubyObject self) {
            return RubyProcess.egid(self.getRuntime());
        }
        
        @JRubyMethod(name = { "getegid" }, module = true, visibility = Visibility.PRIVATE)
        public static IRubyObject getegid(final ThreadContext context, final IRubyObject self) {
            return RubyProcess.egid(context.getRuntime());
        }
        
        @Deprecated
        public static IRubyObject geteuid(final IRubyObject self) {
            return RubyProcess.euid(self.getRuntime());
        }
        
        @JRubyMethod(name = { "geteuid" }, module = true, visibility = Visibility.PRIVATE)
        public static IRubyObject geteuid(final ThreadContext context, final IRubyObject self) {
            return RubyProcess.euid(context.getRuntime());
        }
        
        @Deprecated
        public static IRubyObject getgid(final IRubyObject self) {
            return RubyProcess.gid(self.getRuntime());
        }
        
        @JRubyMethod(name = { "getgid" }, module = true, visibility = Visibility.PRIVATE)
        public static IRubyObject getgid(final ThreadContext context, final IRubyObject self) {
            return RubyProcess.gid(context.getRuntime());
        }
        
        @Deprecated
        public static IRubyObject getuid(final IRubyObject self) {
            return RubyProcess.uid(self.getRuntime());
        }
        
        @JRubyMethod(name = { "getuid" }, module = true, visibility = Visibility.PRIVATE)
        public static IRubyObject getuid(final ThreadContext context, final IRubyObject self) {
            return RubyProcess.uid(context.getRuntime());
        }
        
        @Deprecated
        public static IRubyObject setegid(final IRubyObject recv, final IRubyObject arg) {
            return RubyProcess.egid_set(recv.getRuntime(), arg);
        }
        
        @JRubyMethod(name = { "setegid" }, module = true, visibility = Visibility.PRIVATE)
        public static IRubyObject setegid(final ThreadContext context, final IRubyObject recv, final IRubyObject arg) {
            return RubyProcess.egid_set(context.getRuntime(), arg);
        }
        
        @Deprecated
        public static IRubyObject seteuid(final IRubyObject recv, final IRubyObject arg) {
            return RubyProcess.euid_set(recv.getRuntime(), arg);
        }
        
        @JRubyMethod(name = { "seteuid" }, module = true, visibility = Visibility.PRIVATE)
        public static IRubyObject seteuid(final ThreadContext context, final IRubyObject recv, final IRubyObject arg) {
            return RubyProcess.euid_set(context.getRuntime(), arg);
        }
        
        @Deprecated
        public static IRubyObject setgid(final IRubyObject recv, final IRubyObject arg) {
            return RubyProcess.gid_set(recv.getRuntime(), arg);
        }
        
        @JRubyMethod(name = { "setgid" }, module = true, visibility = Visibility.PRIVATE)
        public static IRubyObject setgid(final ThreadContext context, final IRubyObject recv, final IRubyObject arg) {
            return RubyProcess.gid_set(context.getRuntime(), arg);
        }
        
        @Deprecated
        public static IRubyObject setuid(final IRubyObject recv, final IRubyObject arg) {
            return RubyProcess.uid_set(recv.getRuntime(), arg);
        }
        
        @JRubyMethod(name = { "setuid" }, module = true, visibility = Visibility.PRIVATE)
        public static IRubyObject setuid(final ThreadContext context, final IRubyObject recv, final IRubyObject arg) {
            return RubyProcess.uid_set(context.getRuntime(), arg);
        }
    }
    
    private interface NonNativeErrno
    {
        int handle(final Ruby p0, final int p1);
    }
}
