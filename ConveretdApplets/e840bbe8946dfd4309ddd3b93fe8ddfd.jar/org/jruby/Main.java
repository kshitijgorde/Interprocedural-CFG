// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.platform.Platform;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.File;
import org.jruby.util.SimpleSampler;
import org.jruby.util.SafePropertyAccessor;
import org.jruby.exceptions.ThreadKill;
import org.jruby.exceptions.MainExitException;
import org.jruby.runtime.ThreadContext;
import org.jruby.exceptions.RaiseException;
import java.io.PrintStream;
import java.io.InputStream;

public class Main
{
    private final RubyInstanceConfig config;
    
    public Main(final RubyInstanceConfig config) {
        this(config, false);
    }
    
    public Main(final InputStream in, final PrintStream out, final PrintStream err) {
        this(new RubyInstanceConfig() {
            {
                this.setInput(in);
                this.setOutput(out);
                this.setError(err);
            }
        });
    }
    
    public Main() {
        this(new RubyInstanceConfig());
    }
    
    private Main(final RubyInstanceConfig config, final boolean hardExit) {
        (this.config = config).setHardExit(hardExit);
    }
    
    private Main(final boolean hardExit) {
        this(new RubyInstanceConfig(), hardExit);
    }
    
    public static void main(final String[] args) {
        doGCJCheck();
        final Main main = new Main(true);
        try {
            final Status status = main.run(args);
            if (status.isExit()) {
                System.exit(status.getStatus());
            }
        }
        catch (RaiseException rj) {
            System.exit(handleRaiseException(rj));
        }
        catch (Throwable t) {
            System.err.println(ThreadContext.createRawBacktraceStringFromThrowable(t));
            while ((t = t.getCause()) != null) {
                System.err.println("Caused by:");
                System.err.println(ThreadContext.createRawBacktraceStringFromThrowable(t));
            }
            System.exit(1);
        }
    }
    
    public Status run(final String[] args) {
        try {
            this.config.processArguments(args);
            return this.run();
        }
        catch (MainExitException mee) {
            return this.handleMainExit(mee);
        }
        catch (OutOfMemoryError oome) {
            return this.handleOutOfMemory(oome);
        }
        catch (StackOverflowError soe) {
            return this.handleStackOverflow(soe);
        }
        catch (UnsupportedClassVersionError ucve) {
            return this.handleUnsupportedClassVersion(ucve);
        }
        catch (ThreadKill kill) {
            return new Status();
        }
    }
    
    @Deprecated
    public Status run() {
        return this.internalRun();
    }
    
    private Status internalRun() {
        this.doShowVersion();
        this.doShowCopyright();
        if (!this.config.shouldRunInterpreter()) {
            this.doPrintUsage(false);
            this.doPrintProperties();
            return new Status();
        }
        final InputStream in = this.config.getScriptSource();
        final String filename = this.config.displayedFileName();
        this.doProcessArguments(in);
        final Ruby runtime = Ruby.newInstance(this.config);
        try {
            this.doSetContextClassLoader(runtime);
            if (in == null) {
                return new Status();
            }
            if (this.config.isxFlag() && !this.config.hasShebangLine()) {
                throw new MainExitException(1, "jruby: no Ruby script found in input (LoadError)");
            }
            if (this.config.isShouldCheckSyntax()) {
                return this.doCheckSyntax(runtime, in, filename);
            }
            return this.doRunFromMain(runtime, in, filename);
        }
        finally {
            runtime.tearDown();
        }
    }
    
    private Status handleUnsupportedClassVersion(final UnsupportedClassVersionError ucve) {
        this.config.getError().println("Error: Some library (perhaps JRuby) was built with a later JVM version.");
        this.config.getError().println("Please use libraries built with the version you intend to use or an earlier one.");
        if (this.config.isVerbose()) {
            this.config.getError().println("Exception trace follows:");
            ucve.printStackTrace();
        }
        else {
            this.config.getError().println("Specify -w for full UnsupportedClassVersionError stack trace");
        }
        return new Status(1);
    }
    
    private Status handleStackOverflow(final StackOverflowError soe) {
        System.gc();
        final String stackMax = SafePropertyAccessor.getProperty("jruby.stack.max");
        String message = "";
        if (stackMax != null) {
            message = " of " + stackMax;
        }
        this.config.getError().println("Error: Your application used more stack memory than the safety cap" + message + ".");
        this.config.getError().println("Specify -J-Xss####k to increase it (#### = cap size in KB).");
        if (this.config.isVerbose()) {
            this.config.getError().println("Exception trace follows:");
            soe.printStackTrace();
        }
        else {
            this.config.getError().println("Specify -w for full StackOverflowError stack trace");
        }
        return new Status(1);
    }
    
    private Status handleOutOfMemory(final OutOfMemoryError oome) {
        System.gc();
        final String memoryMax = SafePropertyAccessor.getProperty("jruby.memory.max");
        String message = "";
        if (memoryMax != null) {
            message = " of " + memoryMax;
        }
        this.config.getError().println("Error: Your application used more memory than the safety cap" + message + ".");
        this.config.getError().println("Specify -J-Xmx####m to increase it (#### = cap size in MB).");
        if (this.config.isVerbose()) {
            this.config.getError().println("Exception trace follows:");
            oome.printStackTrace();
        }
        else {
            this.config.getError().println("Specify -w for full OutOfMemoryError stack trace");
        }
        return new Status(1);
    }
    
    private Status handleMainExit(final MainExitException mee) {
        if (!mee.isAborted()) {
            this.config.getOutput().println(mee.getMessage());
            if (mee.isUsageError()) {
                this.doPrintUsage(true);
            }
        }
        return new Status(mee.getStatus());
    }
    
    private Status doRunFromMain(final Ruby runtime, final InputStream in, final String filename) {
        long now = -1L;
        try {
            if (this.config.isBenchmarking()) {
                now = System.currentTimeMillis();
            }
            if (this.config.isSamplingEnabled()) {
                SimpleSampler.startSampleThread();
            }
            this.doCheckSecurityManager();
            try {
                runtime.runFromMain(in, filename);
            }
            finally {
                if (this.config.isBenchmarking()) {
                    this.config.getOutput().println("Runtime: " + (System.currentTimeMillis() - now) + " ms");
                }
                if (this.config.isSamplingEnabled()) {
                    SimpleSampler.report();
                }
            }
        }
        catch (RaiseException rj) {
            return new Status(handleRaiseException(rj));
        }
        return new Status();
    }
    
    private Status doCheckSyntax(final Ruby runtime, final InputStream in, final String filename) throws RaiseException {
        int status = 0;
        try {
            runtime.parseFromMain(in, filename);
            this.config.getOutput().println("Syntax OK for " + filename);
        }
        catch (RaiseException re) {
            status = -1;
            if (!re.getException().getMetaClass().getBaseName().equals("SyntaxError")) {
                throw re;
            }
            this.config.getOutput().println("SyntaxError in " + re.getException().message(runtime.getCurrentContext()));
        }
        if (this.config.getArgv().length > 0) {
            for (final String arg : this.config.getArgv()) {
                final File argFile = new File(arg);
                if (argFile.exists()) {
                    try {
                        runtime.parseFromMain(new FileInputStream(argFile), arg);
                        this.config.getOutput().println("Syntax OK for " + arg);
                    }
                    catch (FileNotFoundException fnfe) {
                        status = -1;
                        this.config.getOutput().println("File not found: " + arg);
                    }
                    catch (RaiseException re2) {
                        status = -1;
                        if (!re2.getException().getMetaClass().getBaseName().equals("SyntaxError")) {
                            throw re2;
                        }
                        this.config.getOutput().println("SyntaxError in " + re2.getException().message(runtime.getCurrentContext()));
                    }
                }
                else {
                    status = -1;
                    this.config.getOutput().println("File not found: " + arg);
                }
            }
        }
        return new Status(status);
    }
    
    private void doSetContextClassLoader(final Ruby runtime) {
        try {
            Thread.currentThread().setContextClassLoader(runtime.getJRubyClassLoader());
        }
        catch (SecurityException se) {
            if (runtime.getInstanceConfig().isVerbose()) {
                this.config.getError().println("WARNING: Security restrictions disallowed setting context classloader for main thread.");
            }
        }
    }
    
    private void doProcessArguments(final InputStream in) {
        final String[] args = this.config.parseShebangOptions(in);
        if (args.length > 0) {
            this.config.processArguments(args);
        }
    }
    
    private void doPrintProperties() {
        if (this.config.shouldPrintProperties()) {
            this.config.getOutput().print(this.config.getPropertyHelp());
        }
    }
    
    private void doPrintUsage(final boolean force) {
        if (this.config.shouldPrintUsage() || force) {
            this.config.getOutput().print(this.config.getBasicUsageHelp());
        }
    }
    
    private void doShowCopyright() {
        if (this.config.isShowCopyright()) {
            this.config.getOutput().println(this.config.getCopyrightString());
        }
    }
    
    private void doShowVersion() {
        if (this.config.isShowVersion()) {
            this.config.getOutput().println(this.config.getVersionString());
        }
    }
    
    private static void doGCJCheck() {
        if (Platform.IS_GCJ) {
            System.err.println("Fatal: GCJ (GNU Compiler for Java) is not supported by JRuby.");
            System.exit(1);
        }
    }
    
    private void doCheckSecurityManager() {
        if (Main.class.getClassLoader() == null && System.getSecurityManager() != null) {
            System.err.println("Warning: security manager and JRuby running from boot classpath.\nRun from jruby.jar or set env VERIFY_JRUBY=true to enable security.");
        }
    }
    
    private static int handleRaiseException(final RaiseException rj) {
        final RubyException raisedException = rj.getException();
        final Ruby runtime = raisedException.getRuntime();
        if (!runtime.getSystemExit().isInstance(raisedException)) {
            System.err.print(runtime.getInstanceConfig().getTraceType().printBacktrace(raisedException));
            return 1;
        }
        final IRubyObject status = raisedException.callMethod(runtime.getCurrentContext(), "status");
        if (status != null && !status.isNil()) {
            return RubyNumeric.fix2int(status);
        }
        return 0;
    }
    
    public static class Status
    {
        private boolean isExit;
        private int status;
        
        Status(final int status) {
            this.isExit = false;
            this.status = 0;
            this.isExit = true;
            this.status = status;
        }
        
        Status() {
            this.isExit = false;
            this.status = 0;
        }
        
        public boolean isExit() {
            return this.isExit;
        }
        
        public int getStatus() {
            return this.status;
        }
    }
}
