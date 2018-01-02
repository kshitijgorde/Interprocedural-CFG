// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.util;

import java.nio.ByteBuffer;
import java.util.regex.Matcher;
import java.util.Arrays;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import org.jruby.RubyIO;
import java.nio.channels.FileChannel;
import java.io.PrintStream;
import java.util.HashMap;
import org.jruby.Main;
import java.io.PipedOutputStream;
import java.io.PipedInputStream;
import com.kenai.jaffl.NativeLong;
import com.kenai.jaffl.FFIProvider;
import org.jruby.libraries.RbConfigLibrary;
import org.jruby.RubyModule;
import org.jruby.javasupport.util.RuntimeHelpers;
import org.jruby.RubyArray;
import org.jruby.runtime.ThreadContext;
import java.io.BufferedInputStream;
import org.jruby.ext.posix.util.FieldAccess;
import java.io.FilterOutputStream;
import org.jruby.RubyInstanceConfig;
import org.jruby.util.io.ModeFlags;
import java.io.InputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.RubyString;
import org.jruby.ext.posix.POSIX;
import java.io.File;
import org.jruby.ext.posix.util.Platform;
import java.util.Iterator;
import org.jruby.RubyHash;
import java.util.Map;
import org.jruby.Ruby;
import java.lang.reflect.Field;
import java.util.regex.Pattern;

public class ShellLauncher
{
    private static final boolean DEBUG = false;
    private static final String PATH_ENV = "PATH";
    private static final String[] DEFAULT_PATH;
    private static final String[] WINDOWS_EXE_SUFFIXES;
    private static final String[] WINDOWS_INTERNAL_CMDS;
    private static final Pattern SHELL_METACHARACTER_PATTERN;
    private static final Pattern WIN_ENVVAR_PATTERN;
    private static final Class UNIXProcess;
    private static final Field UNIXProcess_pid;
    private static final Class ProcessImpl;
    private static final Field ProcessImpl_handle;
    private static final PidGetter PID_GETTER;
    
    private static String[] getCurrentEnv(final Ruby runtime) {
        return getCurrentEnv(runtime, null);
    }
    
    private static String[] getCurrentEnv(final Ruby runtime, final Map mergeEnv) {
        final RubyHash hash = (RubyHash)runtime.getObject().fastGetConstant("ENV");
        String[] ret;
        if (mergeEnv != null && !mergeEnv.isEmpty()) {
            ret = new String[hash.size() + mergeEnv.size()];
        }
        else {
            ret = new String[hash.size()];
        }
        int i = 0;
        for (final Map.Entry e : hash.directEntrySet()) {
            ret[i] = e.getKey().toString() + "=" + e.getValue().toString();
            ++i;
        }
        if (mergeEnv != null) {
            for (final Map.Entry e : mergeEnv.entrySet()) {
                ret[i] = e.getKey().toString() + "=" + e.getValue().toString();
                ++i;
            }
        }
        return ret;
    }
    
    private static boolean filenameIsPathSearchable(final String fname, final boolean forExec) {
        boolean isSearchable = true;
        if (fname.startsWith("/") || fname.startsWith("./") || fname.startsWith("../") || (forExec && fname.indexOf("/") != -1)) {
            isSearchable = false;
        }
        if (Platform.IS_WINDOWS && (fname.startsWith("\\") || fname.startsWith(".\\") || fname.startsWith("..\\") || (fname.length() > 2 && fname.startsWith(":", 1)) || (forExec && fname.indexOf("\\") != -1))) {
            isSearchable = false;
        }
        return isSearchable;
    }
    
    private static File tryFile(final Ruby runtime, final String fdir, final String fname) {
        File pathFile;
        if (fdir == null) {
            pathFile = new File(fname);
        }
        else {
            pathFile = new File(fdir, fname);
        }
        if (!pathFile.isAbsolute()) {
            pathFile = new File(runtime.getCurrentDirectory(), pathFile.getPath());
        }
        log(runtime, "Trying file " + pathFile);
        if (pathFile.exists()) {
            return pathFile;
        }
        return null;
    }
    
    private static boolean withExeSuffix(final String fname) {
        final String lowerCaseFname = fname.toLowerCase();
        for (final String suffix : ShellLauncher.WINDOWS_EXE_SUFFIXES) {
            if (lowerCaseFname.endsWith(suffix)) {
                return true;
            }
        }
        return false;
    }
    
    private static File isValidFile(final Ruby runtime, final String fdir, final String fname, final boolean isExec) {
        File validFile = null;
        if (isExec && Platform.IS_WINDOWS) {
            if (withExeSuffix(fname)) {
                validFile = tryFile(runtime, fdir, fname);
            }
            else {
                for (final String suffix : ShellLauncher.WINDOWS_EXE_SUFFIXES) {
                    validFile = tryFile(runtime, fdir, fname + suffix);
                    if (validFile != null) {
                        break;
                    }
                }
            }
        }
        else {
            final File pathFile = tryFile(runtime, fdir, fname);
            if (pathFile != null) {
                if (isExec) {
                    if (!pathFile.isDirectory()) {
                        final String pathFileStr = pathFile.getAbsolutePath();
                        final POSIX posix = runtime.getPosix();
                        if (posix.stat(pathFileStr).isExecutable()) {
                            validFile = pathFile;
                        }
                    }
                }
                else {
                    validFile = pathFile;
                }
            }
        }
        return validFile;
    }
    
    private static File isValidFile(final Ruby runtime, final String fname, final boolean isExec) {
        final String fdir = null;
        return isValidFile(runtime, fdir, fname, isExec);
    }
    
    private static File findPathFile(final Ruby runtime, final String fname, final String[] path, final boolean isExec) {
        File pathFile = null;
        final boolean doPathSearch = filenameIsPathSearchable(fname, isExec);
        if (doPathSearch) {
            for (final String fdir : path) {
                pathFile = isValidFile(runtime, fdir, fname, isExec);
                if (pathFile != null) {
                    break;
                }
            }
        }
        else {
            pathFile = isValidFile(runtime, fname, isExec);
        }
        return pathFile;
    }
    
    private static File findPathExecutable(final Ruby runtime, final String fname) {
        final RubyHash env = (RubyHash)runtime.getObject().fastGetConstant("ENV");
        final IRubyObject pathObject = env.op_aref(runtime.getCurrentContext(), RubyString.newString(runtime, "PATH"));
        String[] pathNodes = null;
        if (pathObject == null) {
            pathNodes = ShellLauncher.DEFAULT_PATH;
        }
        else {
            final String pathSeparator = System.getProperty("path.separator");
            String path = pathObject.toString();
            if (Platform.IS_WINDOWS) {
                path = "." + pathSeparator + path;
            }
            pathNodes = path.split(pathSeparator);
        }
        return findPathFile(runtime, fname, pathNodes, true);
    }
    
    public static int runAndWait(final Ruby runtime, final IRubyObject[] rawArgs) {
        return runAndWait(runtime, rawArgs, runtime.getOutputStream());
    }
    
    public static long[] runAndWaitPid(final Ruby runtime, final IRubyObject[] rawArgs) {
        return runAndWaitPid(runtime, rawArgs, runtime.getOutputStream(), true);
    }
    
    public static long runWithoutWait(final Ruby runtime, final IRubyObject[] rawArgs) {
        return runWithoutWait(runtime, rawArgs, runtime.getOutputStream());
    }
    
    public static long runExternalWithoutWait(final Ruby runtime, final IRubyObject[] rawArgs) {
        return runWithoutWait(runtime, rawArgs, runtime.getOutputStream());
    }
    
    public static int execAndWait(final Ruby runtime, final IRubyObject[] rawArgs) {
        final File pwd = new File(runtime.getCurrentDirectory());
        final LaunchConfig cfg = new LaunchConfig(runtime, rawArgs, true);
        if (cfg.shouldRunInProcess()) {
            log(runtime, "ExecAndWait in-process");
            try {
                final ScriptThreadProcess ipScript = new ScriptThreadProcess(runtime, cfg.getExecArgs(), getCurrentEnv(runtime), pwd, false);
                ipScript.start();
                return ipScript.waitFor();
            }
            catch (IOException e) {
                throw runtime.newIOErrorFromException(e);
            }
            catch (InterruptedException e2) {
                throw runtime.newThreadError("unexpected interrupt");
            }
        }
        return runAndWait(runtime, rawArgs);
    }
    
    public static int runAndWait(final Ruby runtime, final IRubyObject[] rawArgs, final OutputStream output) {
        return runAndWait(runtime, rawArgs, output, true);
    }
    
    public static int runAndWait(final Ruby runtime, final IRubyObject[] rawArgs, final OutputStream output, final boolean doExecutableSearch) {
        return (int)runAndWaitPid(runtime, rawArgs, output, doExecutableSearch)[0];
    }
    
    public static long[] runAndWaitPid(final Ruby runtime, final IRubyObject[] rawArgs, final OutputStream output, final boolean doExecutableSearch) {
        final OutputStream error = runtime.getErrorStream();
        final InputStream input = runtime.getInputStream();
        try {
            final Process aProcess = run(runtime, rawArgs, doExecutableSearch);
            handleStreams(runtime, aProcess, input, output, error);
            return new long[] { aProcess.waitFor(), getPidFromProcess(aProcess) };
        }
        catch (IOException e) {
            throw runtime.newIOErrorFromException(e);
        }
        catch (InterruptedException e2) {
            throw runtime.newThreadError("unexpected interrupt");
        }
    }
    
    private static long runWithoutWait(final Ruby runtime, final IRubyObject[] rawArgs, final OutputStream output) {
        final OutputStream error = runtime.getErrorStream();
        try {
            final Process aProcess = run(runtime, rawArgs, true);
            handleStreamsNonblocking(runtime, aProcess, output, error);
            return getPidFromProcess(aProcess);
        }
        catch (IOException e) {
            throw runtime.newIOErrorFromException(e);
        }
    }
    
    private static long runExternalWithoutWait(final Ruby runtime, final IRubyObject[] rawArgs, final OutputStream output) {
        final OutputStream error = runtime.getErrorStream();
        try {
            final Process aProcess = run(runtime, rawArgs, true, true);
            handleStreamsNonblocking(runtime, aProcess, output, error);
            return getPidFromProcess(aProcess);
        }
        catch (IOException e) {
            throw runtime.newIOErrorFromException(e);
        }
    }
    
    public static long getPidFromProcess(final Process process) {
        if (process instanceof ScriptThreadProcess) {
            return process.hashCode();
        }
        if (process instanceof POpenProcess) {
            return reflectPidFromProcess(((POpenProcess)process).getChild());
        }
        return reflectPidFromProcess(process);
    }
    
    public static long reflectPidFromProcess(final Process process) {
        return ShellLauncher.PID_GETTER.getPid(process);
    }
    
    public static Process run(final Ruby runtime, final IRubyObject string) throws IOException {
        return run(runtime, new IRubyObject[] { string }, false);
    }
    
    public static POpenProcess popen(final Ruby runtime, final IRubyObject string, final ModeFlags modes) throws IOException {
        return new POpenProcess(popenShared(runtime, new IRubyObject[] { string }), runtime, modes);
    }
    
    public static POpenProcess popen(final Ruby runtime, final IRubyObject[] strings, final Map env, final ModeFlags modes) throws IOException {
        return new POpenProcess(popenShared(runtime, strings, env), runtime, modes);
    }
    
    public static POpenProcess popen3(final Ruby runtime, final IRubyObject[] strings) throws IOException {
        return new POpenProcess(popenShared(runtime, strings));
    }
    
    public static POpenProcess popen3(final Ruby runtime, final IRubyObject[] strings, final boolean addShell) throws IOException {
        return new POpenProcess(popenShared(runtime, strings, null, addShell));
    }
    
    private static Process popenShared(final Ruby runtime, final IRubyObject[] strings) throws IOException {
        return popenShared(runtime, strings, null);
    }
    
    private static Process popenShared(final Ruby runtime, final IRubyObject[] strings, final Map env) throws IOException {
        return popenShared(runtime, strings, env, true);
    }
    
    private static Process popenShared(final Ruby runtime, final IRubyObject[] strings, final Map env, final boolean addShell) throws IOException {
        final String shell = getShell(runtime);
        Process childProcess = null;
        final File pwd = new File(runtime.getCurrentDirectory());
        try {
            final String[] args = parseCommandLine(runtime.getCurrentContext(), runtime, strings);
            boolean useShell = false;
            if (addShell) {
                for (final String arg : args) {
                    useShell |= shouldUseShell(arg);
                }
            }
            if (strings.length == 1) {
                if (useShell) {
                    final String[] argArray = { shell, shell.endsWith("sh") ? "-c" : "/c", strings[0].asJavaString() };
                    childProcess = Runtime.getRuntime().exec(argArray, getCurrentEnv(runtime, env), pwd);
                }
                else {
                    childProcess = Runtime.getRuntime().exec(args, getCurrentEnv(runtime, env), pwd);
                }
            }
            else if (useShell) {
                final String[] argArray = new String[args.length + 2];
                argArray[0] = shell;
                argArray[1] = (shell.endsWith("sh") ? "-c" : "/c");
                System.arraycopy(args, 0, argArray, 2, args.length);
                childProcess = Runtime.getRuntime().exec(argArray, getCurrentEnv(runtime, env), pwd);
            }
            else {
                childProcess = Runtime.getRuntime().exec(args, getCurrentEnv(runtime, env), pwd);
            }
        }
        catch (SecurityException se) {
            throw runtime.newSecurityError(se.getLocalizedMessage());
        }
        return childProcess;
    }
    
    public static OutputStream unwrapBufferedStream(OutputStream filteredStream) {
        if (RubyInstanceConfig.NO_UNWRAP_PROCESS_STREAMS) {
            return filteredStream;
        }
        while (filteredStream instanceof FilterOutputStream) {
            try {
                filteredStream = (OutputStream)FieldAccess.getProtectedFieldValue(FilterOutputStream.class, "out", filteredStream);
                continue;
            }
            catch (Exception e) {}
            break;
        }
        return filteredStream;
    }
    
    public static InputStream unwrapBufferedStream(InputStream filteredStream) {
        if (RubyInstanceConfig.NO_UNWRAP_PROCESS_STREAMS) {
            return filteredStream;
        }
        while (filteredStream instanceof BufferedInputStream) {
            try {
                filteredStream = (InputStream)FieldAccess.getProtectedFieldValue(BufferedInputStream.class, "in", filteredStream);
                continue;
            }
            catch (Exception e) {}
            break;
        }
        return filteredStream;
    }
    
    public static Process run(final Ruby runtime, final IRubyObject[] rawArgs, final boolean doExecutableSearch) throws IOException {
        return run(runtime, rawArgs, doExecutableSearch, false);
    }
    
    public static Process run(final Ruby runtime, final IRubyObject[] rawArgs, final boolean doExecutableSearch, final boolean forceExternalProcess) throws IOException {
        Process aProcess = null;
        final File pwd = new File(runtime.getCurrentDirectory());
        final LaunchConfig cfg = new LaunchConfig(runtime, rawArgs, doExecutableSearch);
        try {
            if (!forceExternalProcess && cfg.shouldRunInProcess()) {
                log(runtime, "Launching in-process");
                final ScriptThreadProcess ipScript = new ScriptThreadProcess(runtime, cfg.getExecArgs(), getCurrentEnv(runtime), pwd);
                ipScript.start();
                return ipScript;
            }
            if (cfg.shouldRunInShell()) {
                log(runtime, "Launching with shell");
                cfg.verifyExecutableForShell();
                aProcess = Runtime.getRuntime().exec(cfg.getExecArgs(), getCurrentEnv(runtime), pwd);
            }
            else {
                log(runtime, "Launching directly (no shell)");
                cfg.verifyExecutableForDirect();
                aProcess = Runtime.getRuntime().exec(cfg.getExecArgs(), getCurrentEnv(runtime), pwd);
            }
        }
        catch (SecurityException se) {
            throw runtime.newSecurityError(se.getLocalizedMessage());
        }
        return aProcess;
    }
    
    private static void handleStreams(final Ruby runtime, final Process p, final InputStream in, final OutputStream out, final OutputStream err) throws IOException {
        final InputStream pOut = p.getInputStream();
        final InputStream pErr = p.getErrorStream();
        final OutputStream pIn = p.getOutputStream();
        final StreamPumper t1 = new StreamPumper(runtime, pOut, out, false, Pumper.Slave.IN, p);
        final StreamPumper t2 = new StreamPumper(runtime, pErr, err, false, Pumper.Slave.IN, p);
        final StreamPumper t3 = new StreamPumper(runtime, in, pIn, true, Pumper.Slave.OUT, p);
        t1.start();
        t2.start();
        t3.start();
        try {
            t1.join();
        }
        catch (InterruptedException ex) {}
        try {
            t2.join();
        }
        catch (InterruptedException ex2) {}
        t3.quit();
        try {
            err.flush();
        }
        catch (IOException ex3) {}
        try {
            out.flush();
        }
        catch (IOException ex4) {}
        try {
            pIn.close();
        }
        catch (IOException ex5) {}
        try {
            pOut.close();
        }
        catch (IOException ex6) {}
        try {
            pErr.close();
        }
        catch (IOException ex7) {}
        try {
            t3.interrupt();
        }
        catch (SecurityException ex8) {}
    }
    
    private static void handleStreamsNonblocking(final Ruby runtime, final Process p, final OutputStream out, final OutputStream err) throws IOException {
        final InputStream pOut = p.getInputStream();
        final InputStream pErr = p.getErrorStream();
        final StreamPumper t1 = new StreamPumper(runtime, pOut, out, false, Pumper.Slave.IN, p);
        final StreamPumper t2 = new StreamPumper(runtime, pErr, err, false, Pumper.Slave.IN, p);
        t1.start();
        t2.start();
    }
    
    private static String[] parseCommandLine(final ThreadContext context, final Ruby runtime, final IRubyObject[] rawArgs) {
        String[] args;
        if (rawArgs.length == 1) {
            synchronized (runtime.getLoadService()) {
                runtime.getLoadService().require("jruby/path_helper");
            }
            final RubyModule pathHelper = runtime.getClassFromPath("JRuby::PathHelper");
            final RubyArray parts = (RubyArray)RuntimeHelpers.invoke(context, pathHelper, "smart_split_command", rawArgs);
            args = new String[parts.getLength()];
            for (int i = 0; i < parts.getLength(); ++i) {
                args[i] = parts.entry(i).toString();
            }
        }
        else {
            args = new String[rawArgs.length];
            for (int j = 0; j < rawArgs.length; ++j) {
                args[j] = rawArgs[j].toString();
            }
        }
        return args;
    }
    
    private static String getShell(final Ruby runtime) {
        return RbConfigLibrary.jrubyShell();
    }
    
    private static boolean shouldUseShell(final String command) {
        boolean useShell = false;
        for (final char c : command.toCharArray()) {
            if (c != ' ' && !Character.isLetter(c) && "*?{}[]<>()~&|\\$;'`\"\n".indexOf(c) != -1) {
                useShell = true;
            }
        }
        if (Platform.IS_WINDOWS && command.charAt(0) == '@') {
            useShell = true;
        }
        return useShell;
    }
    
    static void log(final Ruby runtime, final String msg) {
        if (RubyInstanceConfig.DEBUG_LAUNCHING) {
            runtime.getErr().println("ShellLauncher: " + msg);
        }
    }
    
    static {
        DEFAULT_PATH = new String[] { "/usr/local/bin", "/usr/ucb", "/usr/bin", "/bin" };
        WINDOWS_EXE_SUFFIXES = new String[] { ".exe", ".com", ".bat", ".cmd" };
        WINDOWS_INTERNAL_CMDS = new String[] { "assoc", "break", "call", "cd", "chcp", "chdir", "cls", "color", "copy", "ctty", "date", "del", "dir", "echo", "endlocal", "erase", "exit", "for", "ftype", "goto", "if", "lfnfor", "lh", "lock", "md", "mkdir", "move", "path", "pause", "popd", "prompt", "pushd", "rd", "rem", "ren", "rename", "rmdir", "set", "setlocal", "shift", "start", "time", "title", "truename", "type", "unlock", "ver", "verify", "vol" };
        SHELL_METACHARACTER_PATTERN = Pattern.compile("[*?{}\\[\\]<>()~&|$;'`\\\\\"\\n]");
        WIN_ENVVAR_PATTERN = Pattern.compile("%\\w+%");
        PidGetter pg = new PidGetter() {
            public long getPid(final Process process) {
                return process.hashCode();
            }
        };
        Class up = null;
        Field pid = null;
        try {
            up = Class.forName("java.lang.UNIXProcess");
            pid = up.getDeclaredField("pid");
            pid.setAccessible(true);
        }
        catch (Exception ex) {}
        UNIXProcess = up;
        UNIXProcess_pid = pid;
        Class pi = null;
        Field handle = null;
        try {
            pi = Class.forName("java.lang.ProcessImpl");
            handle = pi.getDeclaredField("handle");
            handle.setAccessible(true);
        }
        catch (Exception ex2) {}
        ProcessImpl = pi;
        ProcessImpl_handle = handle;
        if (ShellLauncher.UNIXProcess_pid != null) {
            if (ShellLauncher.ProcessImpl_handle != null) {
                pg = new PidGetter() {
                    public long getPid(final Process process) {
                        try {
                            if (ShellLauncher.UNIXProcess.isInstance(process)) {
                                return (int)ShellLauncher.UNIXProcess_pid.get(process);
                            }
                            if (ShellLauncher.ProcessImpl.isInstance(process)) {
                                final Long hproc = (Long)ShellLauncher.ProcessImpl_handle.get(process);
                                return WindowsFFI.getKernel32(FFIProvider.getProvider()).GetProcessId(new NativeLong(hproc));
                            }
                        }
                        catch (Exception ex) {}
                        return process.hashCode();
                    }
                };
            }
            else {
                pg = new PidGetter() {
                    public long getPid(final Process process) {
                        try {
                            if (ShellLauncher.UNIXProcess.isInstance(process)) {
                                return (int)ShellLauncher.UNIXProcess_pid.get(process);
                            }
                        }
                        catch (Exception ex) {}
                        return process.hashCode();
                    }
                };
            }
        }
        else if (ShellLauncher.ProcessImpl_handle != null) {
            pg = new PidGetter() {
                public long getPid(final Process process) {
                    try {
                        if (ShellLauncher.ProcessImpl.isInstance(process)) {
                            final Long hproc = (Long)ShellLauncher.ProcessImpl_handle.get(process);
                            return WindowsFFI.getKernel32(FFIProvider.getProvider()).GetProcessId(new NativeLong(hproc));
                        }
                    }
                    catch (Exception ex) {}
                    return process.hashCode();
                }
            };
        }
        else {
            pg = new PidGetter() {
                public long getPid(final Process process) {
                    return process.hashCode();
                }
            };
        }
        PID_GETTER = pg;
    }
    
    private static class ScriptThreadProcess extends Process implements Runnable
    {
        private final String[] argArray;
        private final String[] env;
        private final File pwd;
        private final boolean pipedStreams;
        private final PipedInputStream processOutput;
        private final PipedInputStream processError;
        private final PipedOutputStream processInput;
        private RubyInstanceConfig config;
        private Thread processThread;
        private int result;
        private Ruby parentRuntime;
        
        public ScriptThreadProcess(final Ruby parentRuntime, final String[] argArray, final String[] env, final File dir) {
            this(parentRuntime, argArray, env, dir, true);
        }
        
        public ScriptThreadProcess(final Ruby parentRuntime, final String[] argArray, final String[] env, final File dir, final boolean pipedStreams) {
            this.parentRuntime = parentRuntime;
            this.argArray = argArray;
            this.env = env;
            this.pwd = dir;
            this.pipedStreams = pipedStreams;
            if (pipedStreams) {
                this.processOutput = new PipedInputStream();
                this.processError = new PipedInputStream();
                this.processInput = new PipedOutputStream();
            }
            else {
                final PipedInputStream pipedInputStream = null;
                this.processError = pipedInputStream;
                this.processOutput = pipedInputStream;
                this.processInput = null;
            }
        }
        
        public void run() {
            try {
                this.result = new Main(this.config).run(this.argArray).getStatus();
            }
            catch (Throwable throwable) {
                throwable.printStackTrace(this.config.getError());
                this.result = -1;
            }
            finally {
                this.config.getOutput().close();
                this.config.getError().close();
                try {
                    this.config.getInput().close();
                }
                catch (IOException ex) {}
            }
        }
        
        private Map<String, String> environmentMap(final String[] env) {
            final Map<String, String> m = new HashMap<String, String>();
            for (int i = 0; i < env.length; ++i) {
                final String[] kv = env[i].split("=", 2);
                m.put(kv[0], kv[1]);
            }
            return m;
        }
        
        public void start() throws IOException {
            this.config = new RubyInstanceConfig(this.parentRuntime.getInstanceConfig()) {
                {
                    this.setEnvironment(ScriptThreadProcess.this.environmentMap(ScriptThreadProcess.this.env));
                    this.setCurrentDirectory(ScriptThreadProcess.this.pwd.toString());
                }
            };
            if (this.pipedStreams) {
                this.config.setInput(new PipedInputStream(this.processInput));
                this.config.setOutput(new PrintStream(new PipedOutputStream(this.processOutput)));
                this.config.setError(new PrintStream(new PipedOutputStream(this.processError)));
            }
            String procName = "piped";
            if (this.argArray.length > 0) {
                procName = this.argArray[0];
            }
            (this.processThread = new Thread(this, "ScriptThreadProcess: " + procName)).setDaemon(true);
            this.processThread.start();
        }
        
        public OutputStream getOutputStream() {
            return this.processInput;
        }
        
        public InputStream getInputStream() {
            return this.processOutput;
        }
        
        public InputStream getErrorStream() {
            return this.processError;
        }
        
        public int waitFor() throws InterruptedException {
            this.processThread.join();
            return this.result;
        }
        
        public int exitValue() {
            return this.result;
        }
        
        public void destroy() {
            if (this.pipedStreams) {
                this.closeStreams();
            }
            this.processThread.interrupt();
        }
        
        private void closeStreams() {
            try {
                this.processInput.close();
            }
            catch (IOException ex) {}
            try {
                this.processOutput.close();
            }
            catch (IOException ex2) {}
            try {
                this.processError.close();
            }
            catch (IOException ex3) {}
        }
    }
    
    public static class POpenProcess extends Process
    {
        private final Process child;
        private InputStream realInput;
        private OutputStream realOutput;
        private InputStream realInerr;
        private InputStream input;
        private OutputStream output;
        private InputStream inerr;
        private FileChannel inputChannel;
        private FileChannel outputChannel;
        private FileChannel inerrChannel;
        private Pumper inputPumper;
        private Pumper inerrPumper;
        private Pumper outputPumper;
        
        public POpenProcess(final Process child, final Ruby runtime, final ModeFlags modes) {
            this.child = child;
            if (modes.isWritable()) {
                this.prepareOutput(child);
            }
            else {
                try {
                    child.getOutputStream().close();
                }
                catch (IOException ex) {}
            }
            if (modes.isReadable()) {
                this.prepareInput(child);
            }
            else {
                this.pumpInput(child, runtime);
            }
            this.pumpInerr(child, runtime);
        }
        
        public POpenProcess(final Process child) {
            this.prepareOutput(this.child = child);
            this.prepareInput(child);
            this.prepareInerr(child);
        }
        
        public OutputStream getOutputStream() {
            return this.output;
        }
        
        public InputStream getInputStream() {
            return this.input;
        }
        
        public InputStream getErrorStream() {
            return this.inerr;
        }
        
        public FileChannel getInput() {
            return this.inputChannel;
        }
        
        public FileChannel getOutput() {
            return this.outputChannel;
        }
        
        public FileChannel getError() {
            return this.inerrChannel;
        }
        
        public boolean hasOutput() {
            return this.output != null || this.outputChannel != null;
        }
        
        public Process getChild() {
            return this.child;
        }
        
        public int waitFor() throws InterruptedException {
            if (this.outputPumper == null) {
                try {
                    if (this.output != null) {
                        this.output.close();
                    }
                }
                catch (IOException ioe) {}
            }
            else {
                this.outputPumper.quit();
            }
            final int result = this.child.waitFor();
            return result;
        }
        
        public int exitValue() {
            return this.child.exitValue();
        }
        
        public void destroy() {
            try {
                if (this.input != null) {
                    this.input.close();
                }
                if (this.inerr != null) {
                    this.inerr.close();
                }
                if (this.output != null) {
                    this.output.close();
                }
                if (this.inputChannel != null) {
                    this.inputChannel.close();
                }
                if (this.inerrChannel != null) {
                    this.inerrChannel.close();
                }
                if (this.outputChannel != null) {
                    this.outputChannel.close();
                }
                synchronized (this) {
                    RubyIO.obliterateProcess(this.child);
                    if (this.inputPumper != null) {
                        synchronized (this.inputPumper) {
                            this.inputPumper.quit();
                        }
                    }
                    if (this.inerrPumper != null) {
                        synchronized (this.inerrPumper) {
                            this.inerrPumper.quit();
                        }
                    }
                    if (this.outputPumper != null) {
                        synchronized (this.outputPumper) {
                            this.outputPumper.quit();
                        }
                    }
                }
            }
            catch (IOException ioe) {
                throw new RuntimeException(ioe);
            }
        }
        
        private void prepareInput(final Process child) {
            this.realInput = child.getInputStream();
            this.input = ShellLauncher.unwrapBufferedStream(this.realInput);
            if (this.input instanceof FileInputStream) {
                this.inputChannel = ((FileInputStream)this.input).getChannel();
            }
            else {
                this.inputChannel = null;
            }
            this.inputPumper = null;
        }
        
        private void prepareInerr(final Process child) {
            this.realInerr = child.getErrorStream();
            this.inerr = ShellLauncher.unwrapBufferedStream(this.realInerr);
            if (this.inerr instanceof FileInputStream) {
                this.inerrChannel = ((FileInputStream)this.inerr).getChannel();
            }
            else {
                this.inerrChannel = null;
            }
            this.inerrPumper = null;
        }
        
        private void prepareOutput(final Process child) {
            this.realOutput = child.getOutputStream();
            this.output = ShellLauncher.unwrapBufferedStream(this.realOutput);
            if (this.output instanceof FileOutputStream) {
                this.outputChannel = ((FileOutputStream)this.output).getChannel();
            }
            else {
                this.outputChannel = null;
            }
            this.outputPumper = null;
        }
        
        private void pumpInput(final Process child, final Ruby runtime) {
            final InputStream childIn = ShellLauncher.unwrapBufferedStream(child.getInputStream());
            FileChannel childInChannel = null;
            if (childIn instanceof FileInputStream) {
                childInChannel = ((FileInputStream)childIn).getChannel();
            }
            final OutputStream parentOut = ShellLauncher.unwrapBufferedStream(runtime.getOut());
            FileChannel parentOutChannel = null;
            if (parentOut instanceof FileOutputStream) {
                parentOutChannel = ((FileOutputStream)parentOut).getChannel();
            }
            if (childInChannel != null && parentOutChannel != null) {
                this.inputPumper = new ChannelPumper(runtime, childInChannel, parentOutChannel, Pumper.Slave.IN, this);
            }
            else {
                this.inputPumper = new StreamPumper(runtime, childIn, parentOut, false, Pumper.Slave.IN, this);
            }
            this.inputPumper.start();
            this.input = null;
            this.inputChannel = null;
        }
        
        private void pumpInerr(final Process child, final Ruby runtime) {
            final InputStream childIn = ShellLauncher.unwrapBufferedStream(child.getErrorStream());
            FileChannel childInChannel = null;
            if (childIn instanceof FileInputStream) {
                childInChannel = ((FileInputStream)childIn).getChannel();
            }
            final OutputStream parentOut = ShellLauncher.unwrapBufferedStream(runtime.getOut());
            FileChannel parentOutChannel = null;
            if (parentOut instanceof FileOutputStream) {
                parentOutChannel = ((FileOutputStream)parentOut).getChannel();
            }
            if (childInChannel != null && parentOutChannel != null) {
                this.inerrPumper = new ChannelPumper(runtime, childInChannel, parentOutChannel, Pumper.Slave.IN, this);
            }
            else {
                this.inerrPumper = new StreamPumper(runtime, childIn, parentOut, false, Pumper.Slave.IN, this);
            }
            this.inerrPumper.start();
            this.inerr = null;
            this.inerrChannel = null;
        }
    }
    
    private static class LaunchConfig
    {
        private Ruby runtime;
        private boolean doExecutableSearch;
        private IRubyObject[] rawArgs;
        private String shell;
        private String[] args;
        private String[] execArgs;
        private boolean cmdBuiltin;
        private String executable;
        private File executableFile;
        
        LaunchConfig(final Ruby runtime, final IRubyObject[] rawArgs, final boolean doExecutableSearch) {
            this.cmdBuiltin = false;
            this.runtime = runtime;
            this.rawArgs = rawArgs;
            this.doExecutableSearch = doExecutableSearch;
            this.shell = getShell(runtime);
            this.args = parseCommandLine(runtime.getCurrentContext(), runtime, rawArgs);
        }
        
        private boolean shouldRunInProcess() {
            if (!this.runtime.getInstanceConfig().isRunRubyInProcess() || RubyInstanceConfig.hasLoadedNativeExtensions()) {
                return false;
            }
            for (int i = 0; i < this.args.length; ++i) {
                final String c = this.args[i];
                if (c.trim().length() != 0) {
                    final char[] firstLast = { c.charAt(0), c.charAt(c.length() - 1) };
                    for (int j = 0; j < firstLast.length; ++j) {
                        switch (firstLast[j]) {
                            case '\n':
                            case '\"':
                            case '$':
                            case '&':
                            case '\'':
                            case '(':
                            case ')':
                            case '*':
                            case ';':
                            case '<':
                            case '>':
                            case '?':
                            case '[':
                            case '\\':
                            case ']':
                            case '`':
                            case '{':
                            case '|':
                            case '}':
                            case '~': {
                                return false;
                            }
                            case '2': {
                                if (c.length() > 1 && c.charAt(1) == '>') {
                                    return false;
                                }
                                break;
                            }
                        }
                    }
                }
            }
            String command = this.args[0];
            if (Platform.IS_WINDOWS) {
                command = command.toLowerCase();
            }
            final String[] slashDelimitedTokens = command.split("[/\\\\]");
            final String finalToken = slashDelimitedTokens[slashDelimitedTokens.length - 1];
            final boolean inProc = finalToken.endsWith("ruby") || (Platform.IS_WINDOWS && finalToken.endsWith("ruby.exe")) || finalToken.endsWith(".rb") || finalToken.endsWith("irb");
            if (!inProc) {
                return false;
            }
            int startIndex = command.endsWith(".rb") ? 0 : 1;
            if (command.trim().endsWith("irb")) {
                startIndex = 0;
                this.args[0] = this.runtime.getJRubyHome() + File.separator + "bin" + File.separator + "jirb";
            }
            this.execArgs = new String[this.args.length - startIndex];
            System.arraycopy(this.args, startIndex, this.execArgs, 0, this.execArgs.length);
            return true;
        }
        
        private boolean shouldRunInShell() {
            if (this.rawArgs.length != 1) {
                return false;
            }
            if (!Platform.IS_WINDOWS) {
                return true;
            }
            if (this.shell == null) {
                return false;
            }
            for (final String arg : this.args) {
                if (!shouldVerifyPathExecutable(arg.trim())) {
                    return true;
                }
            }
            this.executable = this.args[0].trim();
            this.executableFile = findPathExecutable(this.runtime, this.executable);
            if (this.executableFile != null) {
                ShellLauncher.log(this.runtime, "Got it: " + this.executableFile);
                return false;
            }
            ShellLauncher.log(this.runtime, "Didn't find executable: " + this.executable);
            return this.isCmdBuiltin(this.executable) && (this.cmdBuiltin = true);
        }
        
        private void verifyExecutableForShell() {
            final String cmdline = this.rawArgs[0].toString().trim();
            if (this.doExecutableSearch && shouldVerifyPathExecutable(cmdline) && !this.cmdBuiltin) {
                this.verifyExecutable();
            }
            (this.execArgs = new String[3])[0] = this.shell;
            this.execArgs[1] = (this.shell.endsWith("sh") ? "-c" : "/c");
            if (Platform.IS_WINDOWS) {
                this.execArgs[2] = "\"" + cmdline + "\"";
            }
            else {
                this.execArgs[2] = cmdline;
            }
        }
        
        private void verifyExecutableForDirect() {
            this.verifyExecutable();
            this.execArgs = this.args;
            try {
                this.execArgs[0] = this.executableFile.getCanonicalPath();
            }
            catch (IOException ex) {}
        }
        
        private void verifyExecutable() {
            if (this.executableFile == null) {
                if (this.executable == null) {
                    this.executable = this.args[0].trim();
                }
                this.executableFile = findPathExecutable(this.runtime, this.executable);
            }
            if (this.executableFile == null) {
                throw this.runtime.newErrnoENOENTError(this.executable);
            }
        }
        
        private String[] getExecArgs() {
            return this.execArgs;
        }
        
        private static boolean isBatch(final File f) {
            final String path = f.getPath();
            return path.endsWith(".bat") || path.endsWith(".cmd");
        }
        
        private boolean isCmdBuiltin(final String cmd) {
            if (!this.shell.endsWith("sh")) {
                final int idx = Arrays.binarySearch(ShellLauncher.WINDOWS_INTERNAL_CMDS, cmd.toLowerCase());
                if (idx >= 0) {
                    ShellLauncher.log(this.runtime, "Found Windows shell's built-in command: " + cmd);
                    return true;
                }
            }
            return false;
        }
        
        private static boolean hasRedirection(final String cmdline) {
            if (Platform.IS_WINDOWS) {
                char quote = '\0';
                int idx = 0;
                while (idx < cmdline.length()) {
                    final char ptr = cmdline.charAt(idx);
                    switch (ptr) {
                        case '\"':
                        case '\'': {
                            if (quote == '\0') {
                                quote = ptr;
                            }
                            else if (quote == ptr) {
                                quote = '\0';
                            }
                            ++idx;
                            continue;
                        }
                        case '\n':
                        case '<':
                        case '>':
                        case '|': {
                            if (quote == '\0') {
                                return true;
                            }
                            ++idx;
                            continue;
                        }
                        case '%': {
                            final Matcher envVarMatcher = ShellLauncher.WIN_ENVVAR_PATTERN.matcher(cmdline.substring(idx));
                            if (envVarMatcher.find()) {
                                return true;
                            }
                            ++idx;
                            continue;
                        }
                        case '\\': {
                            ++idx;
                            break;
                        }
                    }
                    ++idx;
                }
                return false;
            }
            final Matcher metaMatcher = ShellLauncher.SHELL_METACHARACTER_PATTERN.matcher(cmdline);
            return metaMatcher.find();
        }
        
        private static boolean shouldVerifyPathExecutable(final String cmdline) {
            final boolean verifyPathExecutable = true;
            return !hasRedirection(cmdline) && verifyPathExecutable;
        }
    }
    
    private static class StreamPumper extends Thread implements Pumper
    {
        private final InputStream in;
        private final OutputStream out;
        private final boolean onlyIfAvailable;
        private final Object waitLock;
        private final Object sync;
        private final Slave slave;
        private volatile boolean quit;
        private final Ruby runtime;
        
        StreamPumper(final Ruby runtime, final InputStream in, final OutputStream out, final boolean avail, final Slave slave, final Object sync) {
            this.waitLock = new Object();
            this.in = ShellLauncher.unwrapBufferedStream(in);
            this.out = ShellLauncher.unwrapBufferedStream(out);
            this.onlyIfAvailable = avail;
            this.slave = slave;
            this.sync = sync;
            this.runtime = runtime;
            this.setDaemon(true);
        }
        
        public void run() {
            this.runtime.getCurrentContext().setEventHooksEnabled(false);
            final byte[] buf = new byte[1024];
            boolean hasReadSomething = false;
            try {
                while (!this.quit) {
                    if (this.onlyIfAvailable && !hasReadSomething) {
                        if (this.in.available() == 0) {
                            synchronized (this.waitLock) {
                                this.waitLock.wait(10L);
                            }
                            continue;
                        }
                        hasReadSomething = true;
                    }
                    final int numRead;
                    if ((numRead = this.in.read(buf)) == -1) {
                        break;
                    }
                    this.out.write(buf, 0, numRead);
                }
            }
            catch (Exception e) {}
            finally {
                if (this.onlyIfAvailable) {
                    synchronized (this.sync) {
                        if (this.slave == Slave.OUT) {
                            try {
                                this.out.close();
                            }
                            catch (IOException ex) {}
                        }
                    }
                }
            }
        }
        
        public void quit() {
            this.quit = true;
            synchronized (this.waitLock) {
                this.waitLock.notify();
            }
        }
    }
    
    private static class ChannelPumper extends Thread implements Pumper
    {
        private final FileChannel inChannel;
        private final FileChannel outChannel;
        private final Slave slave;
        private final Object sync;
        private volatile boolean quit;
        private final Ruby runtime;
        
        ChannelPumper(final Ruby runtime, final FileChannel inChannel, final FileChannel outChannel, final Slave slave, final Object sync) {
            this.inChannel = inChannel;
            this.outChannel = outChannel;
            this.slave = slave;
            this.sync = sync;
            this.runtime = runtime;
            this.setDaemon(true);
        }
        
        public void run() {
            this.runtime.getCurrentContext().setEventHooksEnabled(false);
            final ByteBuffer buf = ByteBuffer.allocateDirect(1024);
            buf.clear();
            try {
                while (!this.quit && this.inChannel.isOpen() && this.outChannel.isOpen()) {
                    final int read = this.inChannel.read(buf);
                    if (read == -1) {
                        break;
                    }
                    buf.flip();
                    this.outChannel.write(buf);
                    buf.clear();
                }
            }
            catch (Exception e) {}
            finally {
                synchronized (this.sync) {
                    switch (this.slave) {
                        case OUT: {
                            try {
                                this.outChannel.close();
                            }
                            catch (IOException ioe) {}
                            break;
                        }
                        case IN: {
                            try {
                                this.inChannel.close();
                            }
                            catch (IOException ex) {}
                            break;
                        }
                    }
                }
            }
        }
        
        public void quit() {
            this.interrupt();
            this.quit = true;
        }
    }
    
    private interface Pumper extends Runnable
    {
        void start();
        
        void quit();
        
        public enum Slave
        {
            IN, 
            OUT;
        }
    }
    
    private interface PidGetter
    {
        long getPid(final Process p0);
    }
}
