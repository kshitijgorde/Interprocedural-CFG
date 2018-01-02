// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.runtime.ThreadContext;
import org.jruby.anno.JRubyMethod;
import org.jruby.ext.posix.POSIX;
import org.jruby.common.IRubyWarnings;
import org.jruby.exceptions.RaiseException;
import org.jruby.ext.posix.util.Platform;
import org.jruby.ext.posix.Group;
import org.jruby.ext.posix.Passwd;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.anno.JRubyModule;

@JRubyModule(name = { "Etc" })
public class RubyEtc
{
    public static RubyModule createEtcModule(final Ruby runtime) {
        final RubyModule etcModule = runtime.defineModule("Etc");
        runtime.setEtc(etcModule);
        etcModule.defineAnnotatedMethods(RubyEtc.class);
        definePasswdStruct(runtime);
        defineGroupStruct(runtime);
        return etcModule;
    }
    
    private static void definePasswdStruct(final Ruby runtime) {
        final IRubyObject[] args = { runtime.newString("Passwd"), runtime.newSymbol("name"), runtime.newSymbol("passwd"), runtime.newSymbol("uid"), runtime.newSymbol("gid"), runtime.newSymbol("gecos"), runtime.newSymbol("dir"), runtime.newSymbol("shell"), runtime.newSymbol("change"), runtime.newSymbol("uclass"), runtime.newSymbol("expire") };
        runtime.setPasswdStruct(RubyStruct.newInstance(runtime.getStructClass(), args, Block.NULL_BLOCK));
    }
    
    private static void defineGroupStruct(final Ruby runtime) {
        final IRubyObject[] args = { runtime.newString("Group"), runtime.newSymbol("name"), runtime.newSymbol("passwd"), runtime.newSymbol("gid"), runtime.newSymbol("mem") };
        runtime.setGroupStruct(RubyStruct.newInstance(runtime.getStructClass(), args, Block.NULL_BLOCK));
    }
    
    private static IRubyObject setupPasswd(final Ruby runtime, final Passwd passwd) {
        final IRubyObject[] args = { runtime.newString(passwd.getLoginName()), runtime.newString(passwd.getPassword()), runtime.newFixnum(passwd.getUID()), runtime.newFixnum(passwd.getGID()), runtime.newString(passwd.getGECOS()), runtime.newString(passwd.getHome()), runtime.newString(passwd.getShell()), runtime.newFixnum(passwd.getPasswdChangeTime()), runtime.newString(passwd.getAccessClass()), runtime.newFixnum(passwd.getExpire()) };
        return RubyStruct.newStruct(runtime.getPasswdStruct(), args, Block.NULL_BLOCK);
    }
    
    private static IRubyObject setupGroup(final Ruby runtime, final Group group) {
        final IRubyObject[] args = { runtime.newString(group.getName()), runtime.newString(group.getPassword()), runtime.newFixnum(group.getGID()), intoStringArray(runtime, group.getMembers()) };
        return RubyStruct.newStruct(runtime.getGroupStruct(), args, Block.NULL_BLOCK);
    }
    
    private static IRubyObject intoStringArray(final Ruby runtime, final String[] members) {
        final IRubyObject[] arr = new IRubyObject[members.length];
        for (int i = 0; i < arr.length; ++i) {
            arr[i] = runtime.newString(members[i]);
        }
        return runtime.newArrayNoCopy(arr);
    }
    
    @JRubyMethod(name = { "getpwuid" }, optional = 1, module = true)
    public static IRubyObject getpwuid(final IRubyObject recv, final IRubyObject[] args) {
        final Ruby runtime = recv.getRuntime();
        final POSIX posix = runtime.getPosix();
        try {
            final int uid = (args.length == 0) ? posix.getuid() : RubyNumeric.fix2int(args[0]);
            final Passwd pwd = posix.getpwuid(uid);
            if (pwd != null) {
                return setupPasswd(runtime, pwd);
            }
            if (Platform.IS_WINDOWS) {
                return recv.getRuntime().getNil();
            }
            throw runtime.newArgumentError("can't find user for " + uid);
        }
        catch (RaiseException re) {
            if (runtime.getNotImplementedError().isInstance(re.getException())) {
                return runtime.getNil();
            }
            throw re;
        }
        catch (Exception e) {
            if (runtime.getDebug().isTrue()) {
                runtime.getWarnings().warn(IRubyWarnings.ID.NOT_IMPLEMENTED, "Etc.getpwuid is not supported by JRuby on this platform", e);
            }
            return runtime.getNil();
        }
    }
    
    @JRubyMethod(name = { "getpwnam" }, required = 1, module = true)
    public static IRubyObject getpwnam(final IRubyObject recv, IRubyObject name) {
        final Ruby runtime = recv.getRuntime();
        final String nam = name.convertToString().toString();
        try {
            final Passwd pwd = runtime.getPosix().getpwnam(nam);
            if (pwd != null) {
                return setupPasswd(recv.getRuntime(), pwd);
            }
            if (Platform.IS_WINDOWS) {
                return runtime.getNil();
            }
            throw runtime.newArgumentError("can't find user for " + nam);
        }
        catch (Exception e) {
            if (runtime.getDebug().isTrue()) {
                runtime.getWarnings().warn(IRubyWarnings.ID.NOT_IMPLEMENTED, "Etc.getpwnam is not supported by JRuby on this platform", e);
            }
            return runtime.getNil();
        }
    }
    
    @JRubyMethod(module = true)
    public static IRubyObject passwd(final IRubyObject recv, final Block block) {
        final Ruby runtime = recv.getRuntime();
        final POSIX posix = runtime.getPosix();
        try {
            posix.getpwent();
            if (block.isGiven()) {
                final ThreadContext context = runtime.getCurrentContext();
                posix.setpwent();
                Passwd pw;
                while ((pw = posix.getpwent()) != null) {
                    block.yield(context, setupPasswd(runtime, pw));
                }
                posix.endpwent();
            }
            final Passwd pw2 = posix.getpwent();
            if (pw2 != null) {
                return setupPasswd(runtime, pw2);
            }
            return runtime.getNil();
        }
        catch (Exception e) {
            if (runtime.getDebug().isTrue()) {
                runtime.getWarnings().warn(IRubyWarnings.ID.NOT_IMPLEMENTED, "Etc.passwd is not supported by JRuby on this platform", e);
            }
            return runtime.getNil();
        }
    }
    
    @JRubyMethod(name = { "getlogin" }, module = true)
    public static IRubyObject getlogin(final IRubyObject recv) {
        final Ruby runtime = recv.getRuntime();
        try {
            String login = runtime.getPosix().getlogin();
            if (login != null) {
                return runtime.newString(login);
            }
            login = System.getenv("USER");
            if (login != null) {
                return runtime.newString(login);
            }
            return runtime.getNil();
        }
        catch (Exception e) {
            return runtime.newString(System.getProperty("user.name"));
        }
    }
    
    @JRubyMethod(name = { "endpwent" }, module = true)
    public static IRubyObject endpwent(final IRubyObject recv) {
        final Ruby runtime = recv.getRuntime();
        try {
            runtime.getPosix().endpwent();
        }
        catch (Exception e) {
            if (runtime.getDebug().isTrue()) {
                runtime.getWarnings().warn(IRubyWarnings.ID.NOT_IMPLEMENTED, "Etc.endpwent is not supported by JRuby on this platform", e);
            }
        }
        return runtime.getNil();
    }
    
    @JRubyMethod(name = { "setpwent" }, module = true)
    public static IRubyObject setpwent(final IRubyObject recv) {
        final Ruby runtime = recv.getRuntime();
        try {
            runtime.getPosix().setpwent();
        }
        catch (Exception e) {
            if (runtime.getDebug().isTrue()) {
                runtime.getWarnings().warn(IRubyWarnings.ID.NOT_IMPLEMENTED, "Etc.setpwent is not supported by JRuby on this platform", e);
            }
        }
        return runtime.getNil();
    }
    
    @JRubyMethod(name = { "getpwent" }, module = true)
    public static IRubyObject getpwent(final IRubyObject recv) {
        final Ruby runtime = recv.getRuntime();
        try {
            final Passwd passwd = runtime.getPosix().getpwent();
            if (passwd != null) {
                return setupPasswd(runtime, passwd);
            }
            return runtime.getNil();
        }
        catch (Exception e) {
            if (runtime.getDebug().isTrue()) {
                runtime.getWarnings().warn(IRubyWarnings.ID.NOT_IMPLEMENTED, "Etc.getpwent is not supported by JRuby on this platform", e);
            }
            return runtime.getNil();
        }
    }
    
    @JRubyMethod(name = { "getgrnam" }, required = 1, module = true)
    public static IRubyObject getgrnam(final IRubyObject recv, IRubyObject name) {
        final Ruby runtime = recv.getRuntime();
        final String nam = name.convertToString().toString();
        try {
            final Group grp = runtime.getPosix().getgrnam(nam);
            if (grp != null) {
                return setupGroup(runtime, grp);
            }
            if (Platform.IS_WINDOWS) {
                return runtime.getNil();
            }
            throw runtime.newArgumentError("can't find group for " + nam);
        }
        catch (Exception e) {
            if (runtime.getDebug().isTrue()) {
                runtime.getWarnings().warn(IRubyWarnings.ID.NOT_IMPLEMENTED, "Etc.getgrnam is not supported by JRuby on this platform", e);
            }
            return runtime.getNil();
        }
    }
    
    @JRubyMethod(name = { "getgrgid" }, optional = 1, module = true)
    public static IRubyObject getgrgid(final IRubyObject recv, final IRubyObject[] args) {
        final Ruby runtime = recv.getRuntime();
        final POSIX posix = runtime.getPosix();
        try {
            final int gid = (args.length == 0) ? posix.getgid() : RubyNumeric.fix2int(args[0]);
            final Group gr = posix.getgrgid(gid);
            if (gr != null) {
                return setupGroup(runtime, gr);
            }
            if (Platform.IS_WINDOWS) {
                return runtime.getNil();
            }
            throw runtime.newArgumentError("can't find group for " + gid);
        }
        catch (RaiseException re) {
            throw re;
        }
        catch (Exception e) {
            if (runtime.getDebug().isTrue()) {
                runtime.getWarnings().warn(IRubyWarnings.ID.NOT_IMPLEMENTED, "Etc.getgrgid is not supported by JRuby on this platform", e);
            }
            return runtime.getNil();
        }
    }
    
    @JRubyMethod(name = { "endgrent" }, module = true)
    public static IRubyObject endgrent(final IRubyObject recv) {
        final Ruby runtime = recv.getRuntime();
        try {
            runtime.getPosix().endgrent();
        }
        catch (Exception e) {
            if (runtime.getDebug().isTrue()) {
                runtime.getWarnings().warn(IRubyWarnings.ID.NOT_IMPLEMENTED, "Etc.engrent is not supported by JRuby on this platform", e);
            }
        }
        return runtime.getNil();
    }
    
    @JRubyMethod(module = true)
    public static IRubyObject setgrent(final IRubyObject recv) {
        final Ruby runtime = recv.getRuntime();
        try {
            runtime.getPosix().setgrent();
        }
        catch (Exception e) {
            if (runtime.getDebug().isTrue()) {
                runtime.getWarnings().warn(IRubyWarnings.ID.NOT_IMPLEMENTED, "Etc.setgrent is not supported by JRuby on this platform", e);
            }
        }
        return runtime.getNil();
    }
    
    @JRubyMethod(module = true)
    public static IRubyObject group(final IRubyObject recv, final Block block) {
        final Ruby runtime = recv.getRuntime();
        final POSIX posix = runtime.getPosix();
        try {
            posix.getgrent();
            if (block.isGiven()) {
                final ThreadContext context = runtime.getCurrentContext();
                posix.setgrent();
                Group gr;
                while ((gr = posix.getgrent()) != null) {
                    block.yield(context, setupGroup(runtime, gr));
                }
                posix.endgrent();
            }
            final Group gr2 = posix.getgrent();
            if (gr2 != null) {
                return setupGroup(runtime, gr2);
            }
            return runtime.getNil();
        }
        catch (Exception e) {
            if (runtime.getDebug().isTrue()) {
                runtime.getWarnings().warn(IRubyWarnings.ID.NOT_IMPLEMENTED, "Etc.group is not supported by JRuby on this platform", e);
            }
            return runtime.getNil();
        }
    }
    
    @JRubyMethod(name = { "getgrent" }, module = true)
    public static IRubyObject getgrent(final IRubyObject recv) {
        final Ruby runtime = recv.getRuntime();
        try {
            final Group gr = runtime.getPosix().getgrent();
            if (gr != null) {
                return setupGroup(recv.getRuntime(), gr);
            }
            return runtime.getNil();
        }
        catch (Exception e) {
            if (runtime.getDebug().isTrue()) {
                runtime.getWarnings().warn(IRubyWarnings.ID.NOT_IMPLEMENTED, "Etc.getgrent is not supported by JRuby on this platform", e);
            }
            return runtime.getNil();
        }
    }
}
