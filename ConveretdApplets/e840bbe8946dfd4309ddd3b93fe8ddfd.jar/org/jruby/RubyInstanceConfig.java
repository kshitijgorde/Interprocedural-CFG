// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.runtime.load.LoadService19;
import org.jruby.runtime.profile.GraphProfilePrinter;
import org.jruby.runtime.profile.FlatProfilePrinter;
import org.jruby.runtime.profile.AbstractProfilePrinter;
import org.jruby.runtime.profile.IProfileData;
import org.jruby.compiler.ASTCompiler19;
import org.jruby.compiler.ASTCompiler;
import java.util.jar.JarEntry;
import java.util.zip.ZipEntry;
import java.util.jar.JarFile;
import java.io.FileReader;
import org.jruby.exceptions.MainExitException;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.net.URL;
import java.io.ByteArrayInputStream;
import org.jruby.util.NormalizedFile;
import org.jruby.embed.util.SystemPropertyCatcher;
import java.io.IOException;
import java.io.File;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.jruby.ext.posix.util.Platform;
import org.jruby.runtime.Constants;
import org.jruby.runtime.load.LoadService;
import java.util.Arrays;
import org.jruby.util.JRubyFile;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.HashSet;
import java.util.ArrayList;
import org.jruby.util.SafePropertyAccessor;
import org.jruby.runtime.backtrace.TraceType;
import org.jruby.util.KCode;
import java.util.Collection;
import java.util.Set;
import java.util.List;
import org.jruby.ast.executable.Script;
import org.jruby.util.ClassCache;
import java.util.Map;
import java.io.PrintStream;
import java.io.InputStream;

public class RubyInstanceConfig
{
    public static final int JIT_MAX_METHODS_LIMIT = 4096;
    public static final int JIT_MAX_SIZE_LIMIT = 30000;
    public static final int JIT_THRESHOLD = 50;
    public static final int JAVA_VERSION;
    public static final int CHAINED_COMPILE_LINE_COUNT_DEFAULT = 500;
    public static final int CHAINED_COMPILE_LINE_COUNT;
    private boolean xFlag;
    private boolean hasShebangLine;
    private InputStream input;
    private PrintStream output;
    private PrintStream error;
    private Profile profile;
    private boolean objectSpaceEnabled;
    private CompileMode compileMode;
    private boolean runRubyInProcess;
    private String currentDirectory;
    private Map environment;
    private String[] argv;
    private final boolean jitLogging;
    private final boolean jitDumping;
    private final boolean jitLoggingVerbose;
    private int jitLogEvery;
    private int jitThreshold;
    private int jitMax;
    private int jitMaxSize;
    private final boolean samplingEnabled;
    private CompatVersion compatVersion;
    private String internalEncoding;
    private String externalEncoding;
    private ProfilingMode profilingMode;
    private ClassLoader contextLoader;
    private ClassLoader loader;
    private ClassCache<Script> classCache;
    private List<String> loadPaths;
    private Set<String> excludedMethods;
    private StringBuffer inlineScript;
    private boolean hasInlineScript;
    private String scriptFileName;
    private Collection<String> requiredLibraries;
    private boolean benchmarking;
    private boolean argvGlobalsOn;
    private boolean assumeLoop;
    private boolean assumePrinting;
    private Map optionGlobals;
    private boolean processLineEnds;
    private boolean split;
    private Boolean verbose;
    private boolean debug;
    private boolean showVersion;
    private boolean showBytecode;
    private boolean showCopyright;
    private boolean endOfArguments;
    private boolean shouldRunInterpreter;
    private boolean shouldPrintUsage;
    private boolean shouldPrintProperties;
    private KCode kcode;
    private String recordSeparator;
    private boolean shouldCheckSyntax;
    private String inputFieldSeparator;
    private boolean managementEnabled;
    private String inPlaceBackupExtension;
    private boolean parserDebug;
    private String threadDumpSignal;
    private boolean hardExit;
    private boolean disableGems;
    private int safeLevel;
    private String jrubyHome;
    private static volatile boolean loadedNativeExtensions;
    public static final boolean PEEPHOLE_OPTZ;
    public static boolean DYNOPT_COMPILE_ENABLED;
    public static boolean NOGUARDS_COMPILE_ENABLED;
    public static boolean FASTEST_COMPILE_ENABLED;
    public static boolean FASTOPS_COMPILE_ENABLED;
    public static boolean THREADLESS_COMPILE_ENABLED;
    public static boolean FASTSEND_COMPILE_ENABLED;
    public static boolean LAZYHANDLES_COMPILE;
    public static boolean INLINE_DYNCALL_ENABLED;
    public static final boolean POOLING_ENABLED;
    public static final int POOL_MAX;
    public static final int POOL_MIN;
    public static final int POOL_TTL;
    public static final boolean NATIVE_NET_PROTOCOL;
    public static boolean FULL_TRACE_ENABLED;
    public static final String COMPILE_EXCLUDE;
    public static boolean nativeEnabled;
    public static final boolean REIFY_RUBY_CLASSES;
    public static final boolean REIFY_LOG_ERRORS;
    public static final boolean USE_GENERATED_HANDLES;
    public static final boolean DEBUG_LOAD_SERVICE;
    public static final boolean DEBUG_LOAD_TIMINGS;
    public static final boolean DEBUG_LAUNCHING;
    public static final boolean DEBUG_SCRIPT_RESOLUTION;
    public static final boolean JUMPS_HAVE_BACKTRACE;
    public static final boolean JIT_CACHE_ENABLED;
    public static final String JIT_CODE_CACHE;
    public static final boolean REFLECTED_HANDLES;
    public static final boolean NO_UNWRAP_PROCESS_STREAMS;
    public static final boolean INTERFACES_USE_PROXY;
    public static final boolean JIT_LOADING_DEBUG;
    public static final boolean CAN_SET_ACCESSIBLE;
    private TraceType traceType;
    public static final boolean ERRNO_BACKTRACE;
    private LoadServiceCreator creator;
    public int characterIndex;
    
    public boolean hasShebangLine() {
        return this.hasShebangLine;
    }
    
    public void setHasShebangLine(final boolean hasShebangLine) {
        this.hasShebangLine = hasShebangLine;
    }
    
    public boolean isxFlag() {
        return this.xFlag;
    }
    
    public RubyInstanceConfig(final RubyInstanceConfig parentConfig) {
        this.input = System.in;
        this.output = System.out;
        this.error = System.err;
        this.profile = Profile.DEFAULT;
        this.objectSpaceEnabled = SafePropertyAccessor.getBoolean("jruby.objectspace.enabled", false);
        this.compileMode = CompileMode.JIT;
        this.runRubyInProcess = true;
        this.argv = new String[0];
        this.internalEncoding = null;
        this.externalEncoding = null;
        this.profilingMode = ProfilingMode.OFF;
        this.contextLoader = Thread.currentThread().getContextClassLoader();
        this.loader = ((this.contextLoader == null) ? RubyInstanceConfig.class.getClassLoader() : this.contextLoader);
        this.loadPaths = new ArrayList<String>();
        this.excludedMethods = new HashSet<String>();
        this.inlineScript = new StringBuffer();
        this.hasInlineScript = false;
        this.scriptFileName = null;
        this.requiredLibraries = new LinkedHashSet<String>();
        this.benchmarking = false;
        this.argvGlobalsOn = false;
        this.assumeLoop = false;
        this.assumePrinting = false;
        this.optionGlobals = new HashMap();
        this.processLineEnds = false;
        this.split = false;
        this.verbose = Boolean.FALSE;
        this.debug = false;
        this.showVersion = false;
        this.showBytecode = false;
        this.showCopyright = false;
        this.endOfArguments = false;
        this.shouldRunInterpreter = true;
        this.shouldPrintUsage = false;
        this.shouldPrintProperties = false;
        this.kcode = KCode.NONE;
        this.recordSeparator = "\n";
        this.shouldCheckSyntax = false;
        this.inputFieldSeparator = null;
        this.managementEnabled = false;
        this.inPlaceBackupExtension = null;
        this.parserDebug = false;
        this.threadDumpSignal = null;
        this.hardExit = false;
        this.disableGems = false;
        this.safeLevel = 0;
        this.traceType = TraceType.traceTypeFor(SafePropertyAccessor.getProperty("jruby.backtrace.style", "ruby_framed"));
        this.creator = LoadServiceCreator.DEFAULT;
        this.characterIndex = 0;
        this.setCurrentDirectory(parentConfig.getCurrentDirectory());
        this.samplingEnabled = parentConfig.samplingEnabled;
        this.compatVersion = parentConfig.compatVersion;
        this.compileMode = parentConfig.getCompileMode();
        this.jitLogging = parentConfig.jitLogging;
        this.jitDumping = parentConfig.jitDumping;
        this.jitLoggingVerbose = parentConfig.jitLoggingVerbose;
        this.jitLogEvery = parentConfig.jitLogEvery;
        this.jitThreshold = parentConfig.jitThreshold;
        this.jitMax = parentConfig.jitMax;
        this.jitMaxSize = parentConfig.jitMaxSize;
        this.managementEnabled = parentConfig.managementEnabled;
        this.runRubyInProcess = parentConfig.runRubyInProcess;
        this.excludedMethods = parentConfig.excludedMethods;
        this.threadDumpSignal = parentConfig.threadDumpSignal;
        this.classCache = new ClassCache<Script>(this.loader, this.jitMax);
        try {
            this.environment = System.getenv();
        }
        catch (SecurityException se) {
            this.environment = new HashMap();
        }
    }
    
    public RubyInstanceConfig() {
        this.input = System.in;
        this.output = System.out;
        this.error = System.err;
        this.profile = Profile.DEFAULT;
        this.objectSpaceEnabled = SafePropertyAccessor.getBoolean("jruby.objectspace.enabled", false);
        this.compileMode = CompileMode.JIT;
        this.runRubyInProcess = true;
        this.argv = new String[0];
        this.internalEncoding = null;
        this.externalEncoding = null;
        this.profilingMode = ProfilingMode.OFF;
        this.contextLoader = Thread.currentThread().getContextClassLoader();
        this.loader = ((this.contextLoader == null) ? RubyInstanceConfig.class.getClassLoader() : this.contextLoader);
        this.loadPaths = new ArrayList<String>();
        this.excludedMethods = new HashSet<String>();
        this.inlineScript = new StringBuffer();
        this.hasInlineScript = false;
        this.scriptFileName = null;
        this.requiredLibraries = new LinkedHashSet<String>();
        this.benchmarking = false;
        this.argvGlobalsOn = false;
        this.assumeLoop = false;
        this.assumePrinting = false;
        this.optionGlobals = new HashMap();
        this.processLineEnds = false;
        this.split = false;
        this.verbose = Boolean.FALSE;
        this.debug = false;
        this.showVersion = false;
        this.showBytecode = false;
        this.showCopyright = false;
        this.endOfArguments = false;
        this.shouldRunInterpreter = true;
        this.shouldPrintUsage = false;
        this.shouldPrintProperties = false;
        this.kcode = KCode.NONE;
        this.recordSeparator = "\n";
        this.shouldCheckSyntax = false;
        this.inputFieldSeparator = null;
        this.managementEnabled = false;
        this.inPlaceBackupExtension = null;
        this.parserDebug = false;
        this.threadDumpSignal = null;
        this.hardExit = false;
        this.disableGems = false;
        this.safeLevel = 0;
        this.traceType = TraceType.traceTypeFor(SafePropertyAccessor.getProperty("jruby.backtrace.style", "ruby_framed"));
        this.creator = LoadServiceCreator.DEFAULT;
        this.characterIndex = 0;
        this.setCurrentDirectory(Ruby.isSecurityRestricted() ? "/" : JRubyFile.getFileProperty("user.dir"));
        this.samplingEnabled = SafePropertyAccessor.getBoolean("jruby.sampling.enabled", false);
        final String compatString = SafePropertyAccessor.getProperty("jruby.compat.version", "RUBY1_8");
        if (compatString.equalsIgnoreCase("RUBY1_8")) {
            this.setCompatVersion(CompatVersion.RUBY1_8);
        }
        else if (compatString.equalsIgnoreCase("RUBY1_9")) {
            this.setCompatVersion(CompatVersion.RUBY1_9);
        }
        else {
            this.error.println("Compatibility version `" + compatString + "' invalid; use RUBY1_8 or RUBY1_9. Using RUBY1_8.");
            this.setCompatVersion(CompatVersion.RUBY1_8);
        }
        if (Ruby.isSecurityRestricted()) {
            this.compileMode = CompileMode.OFF;
            this.jitLogging = false;
            this.jitDumping = false;
            this.jitLoggingVerbose = false;
            this.jitLogEvery = 0;
            this.jitThreshold = -1;
            this.jitMax = 0;
            this.jitMaxSize = -1;
            this.managementEnabled = false;
        }
        else {
            final String threshold = SafePropertyAccessor.getProperty("jruby.jit.threshold");
            final String max = SafePropertyAccessor.getProperty("jruby.jit.max");
            final String maxSize = SafePropertyAccessor.getProperty("jruby.jit.maxsize");
            if (RubyInstanceConfig.COMPILE_EXCLUDE != null) {
                final String[] elements = RubyInstanceConfig.COMPILE_EXCLUDE.split(",");
                this.excludedMethods.addAll(Arrays.asList(elements));
            }
            this.managementEnabled = SafePropertyAccessor.getBoolean("jruby.management.enabled", false);
            this.runRubyInProcess = SafePropertyAccessor.getBoolean("jruby.launch.inproc", true);
            final boolean jitProperty = SafePropertyAccessor.getProperty("jruby.jit.enabled") != null;
            if (jitProperty) {
                this.error.print("jruby.jit.enabled property is deprecated; use jruby.compile.mode=(OFF|JIT|FORCE) for -C, default, and +C flags");
                this.compileMode = (SafePropertyAccessor.getBoolean("jruby.jit.enabled") ? CompileMode.JIT : CompileMode.OFF);
            }
            else {
                final String jitModeProperty = SafePropertyAccessor.getProperty("jruby.compile.mode", "JIT");
                if (jitModeProperty.equals("OFF")) {
                    this.compileMode = CompileMode.OFF;
                }
                else if (jitModeProperty.equals("JIT")) {
                    this.compileMode = CompileMode.JIT;
                }
                else if (jitModeProperty.equals("FORCE")) {
                    this.compileMode = CompileMode.FORCE;
                }
                else {
                    this.error.print("jruby.compile.mode property must be OFF, JIT, FORCE, or unset; defaulting to JIT");
                    this.compileMode = CompileMode.JIT;
                }
            }
            this.jitLogging = SafePropertyAccessor.getBoolean("jruby.jit.logging");
            this.jitDumping = SafePropertyAccessor.getBoolean("jruby.jit.dumping");
            this.jitLoggingVerbose = SafePropertyAccessor.getBoolean("jruby.jit.logging.verbose");
            final String logEvery = SafePropertyAccessor.getProperty("jruby.jit.logEvery");
            this.jitLogEvery = ((logEvery == null) ? 0 : Integer.parseInt(logEvery));
            this.jitThreshold = ((threshold == null) ? 50 : Integer.parseInt(threshold));
            this.jitMax = ((max == null) ? 4096 : Integer.parseInt(max));
            this.jitMaxSize = ((maxSize == null) ? 30000 : Integer.parseInt(maxSize));
        }
        this.classCache = new ClassCache<Script>(this.loader, this.jitMax);
        this.threadDumpSignal = SafePropertyAccessor.getProperty("jruby.thread.dump.signal", "USR2");
        try {
            this.environment = System.getenv();
        }
        catch (SecurityException se) {
            this.environment = new HashMap();
        }
    }
    
    public LoadServiceCreator getLoadServiceCreator() {
        return this.creator;
    }
    
    public void setLoadServiceCreator(final LoadServiceCreator creator) {
        this.creator = creator;
    }
    
    public LoadService createLoadService(final Ruby runtime) {
        return this.creator.create(runtime);
    }
    
    public String getBasicUsageHelp() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Usage: jruby [switches] [--] [programfile] [arguments]\n").append("  -0[octal]       specify record separator (\\0, if no argument)\n").append("  -a              autosplit mode with -n or -p (splits $_ into $F)\n").append("  -b              benchmark mode, times the script execution\n").append("  -c              check syntax only\n").append("  -Cdirectory     cd to directory, before executing your script\n").append("  -d              set debugging flags (set $DEBUG to true)\n").append("  -e 'command'    one line of script. Several -e's allowed. Omit [programfile]\n").append("  -Eex[:in]       specify the default external and internal character encodings\n").append("  -Fpattern       split() pattern for autosplit (-a)\n").append("  -i[extension]   edit ARGV files in place (make backup if extension supplied)\n").append("  -Idirectory     specify $LOAD_PATH directory (may be used more than once)\n").append("  -J[java option] pass an option on to the JVM (e.g. -J-Xmx512m)\n").append("                    use --properties to list JRuby properties\n").append("                    run 'java -help' for a list of other Java options\n").append("  -Kkcode         specifies code-set (e.g. -Ku for Unicode, -Ke for EUC and -Ks\n").append("                    for SJIS)\n").append("  -l              enable line ending processing\n").append("  -n              assume 'while gets(); ... end' loop around your script\n").append("  -p              assume loop like -n but print line also like sed\n").append("  -rlibrary       require the library, before executing your script\n").append("  -s              enable some switch parsing for switches after script name\n").append("  -S              look for the script in bin or using PATH environment variable\n").append("  -T[level]       turn on tainting checks\n").append("  -U              use UTF-8 as default internal encoding\n").append("  -v              print version number, then turn on verbose mode\n").append("  -w              turn warnings on for your script\n").append("  -W[level]       set warning level; 0=silence, 1=medium, 2=verbose (default)\n").append("  -x[directory]   strip off text before #!ruby line and perhaps cd to directory\n").append("  -X[option]      enable extended option (omit option to list)\n").append("  -y              enable parsing debug output\n").append("  --copyright     print the copyright\n").append("  --debug         sets the execution mode most suitable for debugger\n").append("                    functionality\n").append("  --jdb           runs JRuby process under JDB\n").append("  --properties    List all configuration Java properties\n").append("                    (pass -X<property without \"jruby.\">=value to set them)\n").append("  --sample        run with profiling using the JVM's sampling profiler\n").append("  --profile       run with instrumented (timed) profiling, flat format\n").append("  --profile.api   activate Ruby profiler API\n").append("  --profile.flat  synonym for --profile\n").append("  --profile.graph run with instrumented (timed) profiling, graph format\n").append("  --client        use the non-optimizing \"client\" JVM\n").append("                    (improves startup; default)\n").append("  --server        use the optimizing \"server\" JVM (improves perf)\n").append("  --manage        enable remote JMX management and monitoring of the VM\n").append("                    and JRuby\n").append("  --headless      do not launch a GUI window, no matter what\n").append("  --1.8           specify Ruby 1.8.x compatibility (default)\n").append("  --1.9           specify Ruby 1.9.x compatibility\n").append("  --bytecode      show the JVM bytecode produced by compiling specified code\n").append("  --version       print the version\n");
        return sb.toString();
    }
    
    public String getExtendedHelp() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Extended options:\n").append("  -X-O        run with ObjectSpace disabled (default; improves performance)\n").append("  -X+O        run with ObjectSpace enabled (reduces performance)\n").append("  -X-C        disable all compilation\n").append("  -X+C        force compilation of all scripts before they are run (except eval)\n");
        return sb.toString();
    }
    
    public String getPropertyHelp() {
        final StringBuilder sb = new StringBuilder();
        sb.append("These properties can be used to alter runtime behavior for perf or compatibility.\n").append("Specify them by passing -J-D<property>=<value>\n").append("\nCOMPILER SETTINGS:\n").append("    jruby.compile.mode=JIT|FORCE|OFF\n").append("       Set compilation mode. JIT is default; FORCE compiles all, OFF disables\n").append("    jruby.compile.threadless=true|false\n").append("       (EXPERIMENTAL) Turn on compilation without polling for \"unsafe\" thread events. Default is false\n").append("    jruby.compile.dynopt=true|false\n").append("       (EXPERIMENTAL) Use interpreter to help compiler make direct calls. Default is false\n").append("    jruby.compile.fastops=true|false\n").append("       Turn on fast operators for Fixnum and Float. Default is true\n").append("    jruby.compile.chainsize=<line count>\n").append("       Set the number of lines at which compiled bodies are \"chained\". Default is ").append(500).append("\n").append("    jruby.compile.lazyHandles=true|false\n").append("       Generate method bindings (handles) for compiled methods lazily. Default is false.\n").append("    jruby.compile.peephole=true|false\n").append("       Enable or disable peephole optimizations. Default is true (on).\n").append("\nJIT SETTINGS:\n").append("    jruby.jit.threshold=<invocation count>\n").append("       Set the JIT threshold to the specified method invocation count. Default is ").append(50).append(".\n").append("    jruby.jit.max=<method count>\n").append("       Set the max count of active methods eligible for JIT-compilation.\n").append("       Default is ").append(4096).append(" per runtime. A value of 0 disables JIT, -1 disables max.\n").append("    jruby.jit.maxsize=<jitted method size (full .class)>\n").append("       Set the maximum full-class byte size allowed for jitted methods. Default is ").append(30000).append(".\n").append("    jruby.jit.logging=true|false\n").append("       Enable JIT logging (reports successful compilation). Default is false\n").append("    jruby.jit.logging.verbose=true|false\n").append("       Enable verbose JIT logging (reports failed compilation). Default is false\n").append("    jruby.jit.logEvery=<method count>\n").append("       Log a message every n methods JIT compiled. Default is 0 (off).\n").append("    jruby.jit.exclude=<ClsOrMod,ClsOrMod::method_name,-::method_name>\n").append("       Exclude methods from JIT by class/module short name, c/m::method_name,\n").append("       or -::method_name for anon/singleton classes/modules. Comma-delimited.\n").append("    jruby.jit.cache=true|false\n").append("       Cache jitted method in-memory bodies across runtimes and loads. Default is true.\n").append("    jruby.jit.codeCache=<dir>\n").append("       Save jitted methods to <dir> as they're compiled, for future runs.\n").append("\nNATIVE SUPPORT:\n").append("    jruby.native.enabled=true|false\n").append("       Enable/disable native extensions (like JNA for non-Java APIs; Default is true\n").append("       (This affects all JRuby instances in a given JVM)\n").append("    jruby.native.verbose=true|false\n").append("       Enable verbose logging of native extension loading. Default is false.\n").append("\nTHREAD POOLING:\n").append("    jruby.thread.pool.enabled=true|false\n").append("       Enable reuse of native backing threads via a thread pool. Default is false.\n").append("    jruby.thread.pool.min=<min thread count>\n").append("       The minimum number of threads to keep alive in the pool. Default is 0.\n").append("    jruby.thread.pool.max=<max thread count>\n").append("       The maximum number of threads to allow in the pool. Default is unlimited.\n").append("    jruby.thread.pool.ttl=<time to live, in seconds>\n").append("       The maximum number of seconds to keep alive an idle thread. Default is 60.\n").append("\nMISCELLANY:\n").append("    jruby.compat.version=RUBY1_8|RUBY1_9\n").append("       Specify the major Ruby version to be compatible with; Default is RUBY1_8\n").append("    jruby.objectspace.enabled=true|false\n").append("       Enable or disable ObjectSpace.each_object (default is disabled)\n").append("    jruby.launch.inproc=true|false\n").append("       Set in-process launching of e.g. system('ruby ...'). Default is true\n").append("    jruby.bytecode.version=1.5|1.6\n").append("       Set bytecode version for JRuby to generate. Default is current JVM version.\n").append("    jruby.management.enabled=true|false\n").append("       Set whether JMX management is enabled. Default is false.\n").append("    jruby.jump.backtrace=true|false\n").append("       Make non-local flow jumps generate backtraces. Default is false.\n").append("    jruby.process.noUnwrap=true|false\n").append("       Do not unwrap process streams (IBM Java 6 issue). Default is false.\n").append("    jruby.reify.classes=true|false\n").append("       Before instantiation, stand up a real Java class for ever Ruby class. Default is false. \n").append("    jruby.reify.logErrors=true|false\n").append("       Log errors during reification (reify.classes=true). Default is false. \n").append("\nDEBUGGING/LOGGING:\n").append("    jruby.debug.loadService=true|false\n").append("       LoadService logging\n").append("    jruby.debug.loadService.timing=true|false\n").append("       Print load timings for each require'd library. Default is false.\n").append("    jruby.debug.launch=true|false\n").append("       ShellLauncher logging\n").append("    jruby.debug.fullTrace=true|false\n").append("       Set whether full traces are enabled (c-call/c-return). Default is false.\n").append("    jruby.debug.scriptResolution=true|false\n").append("       Print which script is executed by '-S' flag. Default is false.\n").append("    jruby.reflected.handles=true|false\n").append("       Use reflection for binding methods, not generated bytecode. Default is false.\n").append("    jruby.errno.backtrace=true|false\n").append("       Generate backtraces for heavily-used Errno exceptions (EAGAIN). Default is false.\n").append("\nJAVA INTEGRATION:\n").append("    jruby.ji.setAccessible=true|false\n").append("       Try to set inaccessible Java methods to be accessible. Default is true.\n").append("    jruby.interfaces.useProxy=true|false\n").append("       Use java.lang.reflect.Proxy for interface impl. Default is false.\n");
        return sb.toString();
    }
    
    public String getVersionString() {
        String ver = null;
        final String patchDelimeter = "-p";
        int patchlevel = 0;
        switch (this.getCompatVersion()) {
            case RUBY1_8: {
                ver = "1.8.7";
                patchlevel = Constants.RUBY_PATCHLEVEL;
                break;
            }
            case RUBY1_9: {
                ver = "1.9.2";
                patchlevel = Constants.RUBY1_9_PATCHLEVEL;
                break;
            }
        }
        final String fullVersion = String.format("jruby %s (ruby-%s%s%d) (%s %s) (%s %s) [%s-%s-java]", "1.6.2", ver, patchDelimeter, patchlevel, "2011-05-23", Constants.REVISION, System.getProperty("java.vm.name"), System.getProperty("java.version"), Platform.getOSName(), SafePropertyAccessor.getProperty("os.arch", "unknown"));
        return fullVersion;
    }
    
    public String getCopyrightString() {
        return "JRuby - Copyright (C) 2001-2011 The JRuby Community (and contribs)";
    }
    
    public void processArguments(final String[] arguments) {
        new ArgumentProcessor(arguments).processArguments();
        this.tryProcessArgumentsWithRubyopts();
    }
    
    public void tryProcessArgumentsWithRubyopts() {
        try {
            final Object rubyoptObj = this.environment.get("RUBYOPT");
            final String rubyopt = (rubyoptObj == null) ? null : rubyoptObj.toString();
            if (rubyopt == null || "".equals(rubyopt)) {
                return;
            }
            if (rubyopt.split("\\s").length != 0) {
                final String[] rubyoptArgs = rubyopt.split("\\s+");
                this.endOfArguments = false;
                new ArgumentProcessor(rubyoptArgs, false, true).processArguments();
            }
        }
        catch (SecurityException ex) {}
    }
    
    public String[] parseShebangOptions(final InputStream in) {
        BufferedReader reader = null;
        final String[] result = new String[0];
        if (in == null) {
            return result;
        }
        try {
            in.mark(1024);
            reader = new BufferedReader(new InputStreamReader(in, "iso-8859-1"), 8192);
            String firstLine = reader.readLine();
            if (this.isxFlag()) {
                while (firstLine != null && !isShebangLine(firstLine)) {
                    firstLine = reader.readLine();
                }
            }
            boolean usesEnv = false;
            if (firstLine.length() > 2 && firstLine.charAt(0) == '#' && firstLine.charAt(1) == '!') {
                String[] options;
                int i;
                String basename;
                for (options = firstLine.substring(2).split("\\s+"), i = 0; i < options.length; ++i) {
                    if (i == 0 && options[i].endsWith("/env")) {
                        usesEnv = true;
                    }
                    else if (!usesEnv || options[i].indexOf(61) <= 0) {
                        if (!usesEnv || !options[i].startsWith("-")) {
                            basename = new File(options[i]).getName();
                            if (basename.indexOf("ruby") > 0) {
                                break;
                            }
                        }
                    }
                }
                this.setHasShebangLine(true);
                System.arraycopy(options, i, result, 0, options.length - i);
            }
            else {
                this.setHasShebangLine(false);
            }
        }
        catch (Exception ex) {}
        finally {
            try {
                in.reset();
            }
            catch (IOException ex2) {}
        }
        return result;
    }
    
    protected static boolean isShebangLine(final String line) {
        return line.length() > 2 && line.charAt(0) == '#' && line.charAt(1) == '!';
    }
    
    public CompileMode getCompileMode() {
        return this.compileMode;
    }
    
    public void setCompileMode(final CompileMode compileMode) {
        this.compileMode = compileMode;
    }
    
    public boolean isJitLogging() {
        return this.jitLogging;
    }
    
    public boolean isJitDumping() {
        return this.jitDumping;
    }
    
    public boolean isJitLoggingVerbose() {
        return this.jitLoggingVerbose;
    }
    
    public int getJitLogEvery() {
        return this.jitLogEvery;
    }
    
    public void setJitLogEvery(final int jitLogEvery) {
        this.jitLogEvery = jitLogEvery;
    }
    
    public boolean isSamplingEnabled() {
        return this.samplingEnabled;
    }
    
    public int getJitThreshold() {
        return this.jitThreshold;
    }
    
    public void setJitThreshold(final int jitThreshold) {
        this.jitThreshold = jitThreshold;
    }
    
    public int getJitMax() {
        return this.jitMax;
    }
    
    public void setJitMax(final int jitMax) {
        this.jitMax = jitMax;
    }
    
    public int getJitMaxSize() {
        return this.jitMaxSize;
    }
    
    public void setJitMaxSize(final int jitMaxSize) {
        this.jitMaxSize = jitMaxSize;
    }
    
    public boolean isRunRubyInProcess() {
        return this.runRubyInProcess;
    }
    
    public void setRunRubyInProcess(final boolean flag) {
        this.runRubyInProcess = flag;
    }
    
    public void setInput(final InputStream newInput) {
        this.input = newInput;
    }
    
    public InputStream getInput() {
        return this.input;
    }
    
    public CompatVersion getCompatVersion() {
        return this.compatVersion;
    }
    
    public void setCompatVersion(CompatVersion compatVersion) {
        if (compatVersion == null) {
            compatVersion = CompatVersion.RUBY1_8;
        }
        this.compatVersion = compatVersion;
    }
    
    public void setOutput(final PrintStream newOutput) {
        this.output = newOutput;
    }
    
    public PrintStream getOutput() {
        return this.output;
    }
    
    public void setError(final PrintStream newError) {
        this.error = newError;
    }
    
    public PrintStream getError() {
        return this.error;
    }
    
    public void setCurrentDirectory(final String newCurrentDirectory) {
        this.currentDirectory = newCurrentDirectory;
    }
    
    public String getCurrentDirectory() {
        return this.currentDirectory;
    }
    
    public void setProfile(final Profile newProfile) {
        this.profile = newProfile;
    }
    
    public Profile getProfile() {
        return this.profile;
    }
    
    public void setObjectSpaceEnabled(final boolean newObjectSpaceEnabled) {
        this.objectSpaceEnabled = newObjectSpaceEnabled;
    }
    
    public boolean isObjectSpaceEnabled() {
        return this.objectSpaceEnabled;
    }
    
    public void setEnvironment(Map newEnvironment) {
        if (newEnvironment == null) {
            newEnvironment = new HashMap();
        }
        this.environment = newEnvironment;
    }
    
    public Map getEnvironment() {
        return this.environment;
    }
    
    public ClassLoader getLoader() {
        return this.loader;
    }
    
    public void setLoader(final ClassLoader loader) {
        if (this.loader != loader) {
            this.classCache = new ClassCache<Script>(loader, this.classCache.getMax());
        }
        this.loader = loader;
    }
    
    public String[] getArgv() {
        return this.argv;
    }
    
    public void setArgv(final String[] argv) {
        this.argv = argv;
    }
    
    public String getJRubyHome() {
        if (this.jrubyHome == null) {
            if (!Ruby.isSecurityRestricted()) {
                this.jrubyHome = SafePropertyAccessor.getProperty("jruby.home");
            }
            if (this.jrubyHome != null) {
                this.jrubyHome = this.verifyHome(this.jrubyHome);
            }
            else {
                try {
                    this.jrubyHome = SystemPropertyCatcher.findFromJar(this);
                }
                catch (Exception ex) {}
                if (this.jrubyHome != null) {
                    this.jrubyHome = this.verifyHome(this.jrubyHome);
                }
                else {
                    this.jrubyHome = SafePropertyAccessor.getProperty("java.io.tmpdir");
                }
            }
        }
        return this.jrubyHome;
    }
    
    public void setJRubyHome(final String home) {
        this.jrubyHome = this.verifyHome(home);
    }
    
    private String verifyHome(String home) {
        if (home.equals(".")) {
            home = SafePropertyAccessor.getProperty("user.dir");
        }
        if (home.startsWith("cp:")) {
            home = home.substring(3);
        }
        else if (!home.startsWith("file:") && !home.startsWith("classpath:")) {
            final NormalizedFile f = new NormalizedFile(home);
            if (!f.isAbsolute()) {
                home = f.getAbsolutePath();
            }
            if (!f.exists()) {
                this.error.println("Warning: JRuby home \"" + f + "\" does not exist, using " + SafePropertyAccessor.getProperty("java.io.tmpdir"));
                return System.getProperty("java.io.tmpdir");
            }
        }
        return home;
    }
    
    public byte[] inlineScript() {
        return this.inlineScript.toString().getBytes();
    }
    
    public Collection<String> requiredLibraries() {
        return this.requiredLibraries;
    }
    
    public List<String> loadPaths() {
        return this.loadPaths;
    }
    
    public void setLoadPaths(final List<String> loadPaths) {
        this.loadPaths = loadPaths;
    }
    
    public boolean shouldRunInterpreter() {
        return this.isShouldRunInterpreter();
    }
    
    public boolean shouldPrintUsage() {
        return this.shouldPrintUsage;
    }
    
    public boolean shouldPrintProperties() {
        return this.shouldPrintProperties;
    }
    
    private boolean isSourceFromStdin() {
        return this.getScriptFileName() == null;
    }
    
    public boolean isInlineScript() {
        return this.hasInlineScript;
    }
    
    public InputStream getScriptSource() {
        try {
            if (this.hasInlineScript) {
                return new ByteArrayInputStream(this.inlineScript());
            }
            if (!this.isSourceFromStdin()) {
                final String script = this.getScriptFileName();
                InputStream stream = null;
                if (script.startsWith("file:") && script.indexOf(".jar!/") != -1) {
                    stream = new URL("jar:" + script).openStream();
                }
                else if (script.startsWith("classpath:")) {
                    stream = Ruby.getClassLoader().getResourceAsStream(script.substring("classpath:".length()));
                }
                else {
                    final File file = JRubyFile.create(this.getCurrentDirectory(), this.getScriptFileName());
                    if (this.isxFlag()) {
                        return this.findScript(file);
                    }
                    stream = new FileInputStream(file);
                }
                return new BufferedInputStream(stream, 8192);
            }
            if (this.isShowVersion()) {
                return null;
            }
            return this.getInput();
        }
        catch (IOException e) {
            final InputStream is = this.getJarScriptSource();
            if (is != null) {
                return new BufferedInputStream(is, 8129);
            }
            throw new MainExitException(1, "Error opening script file: " + e.getMessage());
        }
    }
    
    private InputStream findScript(final File file) throws IOException {
        final StringBuffer buf = new StringBuffer();
        BufferedReader br;
        String currentLine;
        for (br = new BufferedReader(new FileReader(file)), currentLine = br.readLine(); currentLine != null && (currentLine.length() <= 2 || currentLine.charAt(0) != '#' || currentLine.charAt(1) != '!'); currentLine = br.readLine()) {}
        buf.append(currentLine);
        buf.append("\n");
        do {
            currentLine = br.readLine();
            if (currentLine != null) {
                buf.append(currentLine);
                buf.append("\n");
            }
        } while (currentLine != null && !currentLine.contains("__END__") && !currentLine.contains("\u0016"));
        return new BufferedInputStream(new ByteArrayInputStream(buf.toString().getBytes()), 8192);
    }
    
    private InputStream getJarScriptSource() {
        final String name = this.getScriptFileName();
        final boolean looksLikeJarURL = name.startsWith("file:") && name.indexOf("!/") != -1;
        if (!looksLikeJarURL) {
            return null;
        }
        final String before = name.substring("file:".length(), name.indexOf("!/"));
        final String after = name.substring(name.indexOf("!/") + 2);
        try {
            final JarFile jFile = new JarFile(before);
            final JarEntry entry = jFile.getJarEntry(after);
            if (entry != null && !entry.isDirectory()) {
                return jFile.getInputStream(entry);
            }
        }
        catch (IOException ex) {}
        return null;
    }
    
    public String displayedFileName() {
        if (this.hasInlineScript) {
            if (this.scriptFileName != null) {
                return this.scriptFileName;
            }
            return "-e";
        }
        else {
            if (this.isSourceFromStdin()) {
                return "-";
            }
            return this.getScriptFileName();
        }
    }
    
    public void setScriptFileName(final String scriptFileName) {
        this.scriptFileName = scriptFileName;
    }
    
    public String getScriptFileName() {
        return this.scriptFileName;
    }
    
    public boolean isBenchmarking() {
        return this.benchmarking;
    }
    
    public boolean isAssumeLoop() {
        return this.assumeLoop;
    }
    
    public boolean isAssumePrinting() {
        return this.assumePrinting;
    }
    
    public boolean isProcessLineEnds() {
        return this.processLineEnds;
    }
    
    public boolean isSplit() {
        return this.split;
    }
    
    public boolean isVerbose() {
        return this.verbose == Boolean.TRUE;
    }
    
    public Boolean getVerbose() {
        return this.verbose;
    }
    
    public boolean isDebug() {
        return this.debug;
    }
    
    public void setDebug(final boolean debug) {
        this.debug = debug;
    }
    
    public boolean isParserDebug() {
        return this.parserDebug;
    }
    
    public boolean isShowVersion() {
        return this.showVersion;
    }
    
    public boolean isShowBytecode() {
        return this.showBytecode;
    }
    
    public boolean isShowCopyright() {
        return this.showCopyright;
    }
    
    protected void setShowVersion(final boolean showVersion) {
        this.showVersion = showVersion;
    }
    
    protected void setShowBytecode(final boolean showBytecode) {
        this.showBytecode = showBytecode;
    }
    
    protected void setShowCopyright(final boolean showCopyright) {
        this.showCopyright = showCopyright;
    }
    
    public boolean isShouldRunInterpreter() {
        return this.shouldRunInterpreter;
    }
    
    public boolean isShouldCheckSyntax() {
        return this.shouldCheckSyntax;
    }
    
    public String getInputFieldSeparator() {
        return this.inputFieldSeparator;
    }
    
    public KCode getKCode() {
        return this.kcode;
    }
    
    public void setKCode(final KCode kcode) {
        this.kcode = kcode;
    }
    
    public String getInternalEncoding() {
        return this.internalEncoding;
    }
    
    public String getExternalEncoding() {
        return this.externalEncoding;
    }
    
    public String getRecordSeparator() {
        return this.recordSeparator;
    }
    
    public int getSafeLevel() {
        return this.safeLevel;
    }
    
    public void setRecordSeparator(final String recordSeparator) {
        this.recordSeparator = recordSeparator;
    }
    
    public ClassCache getClassCache() {
        return this.classCache;
    }
    
    public String getInPlaceBackupExtention() {
        return this.inPlaceBackupExtension;
    }
    
    public void setClassCache(final ClassCache classCache) {
        this.classCache = (ClassCache<Script>)classCache;
    }
    
    public Map getOptionGlobals() {
        return this.optionGlobals;
    }
    
    public boolean isManagementEnabled() {
        return this.managementEnabled;
    }
    
    public Set getExcludedMethods() {
        return this.excludedMethods;
    }
    
    public ASTCompiler newCompiler() {
        if (this.getCompatVersion() == CompatVersion.RUBY1_8) {
            return new ASTCompiler();
        }
        return new ASTCompiler19();
    }
    
    public String getThreadDumpSignal() {
        return this.threadDumpSignal;
    }
    
    public boolean isHardExit() {
        return this.hardExit;
    }
    
    public void setHardExit(final boolean hardExit) {
        this.hardExit = hardExit;
    }
    
    public boolean isProfiling() {
        return this.profilingMode != ProfilingMode.OFF;
    }
    
    public boolean isProfilingEntireRun() {
        return this.profilingMode != ProfilingMode.OFF && this.profilingMode != ProfilingMode.API;
    }
    
    public ProfilingMode getProfilingMode() {
        return this.profilingMode;
    }
    
    public AbstractProfilePrinter makeDefaultProfilePrinter(final IProfileData profileData) {
        if (this.profilingMode == ProfilingMode.FLAT) {
            return new FlatProfilePrinter(profileData.getResults());
        }
        if (this.profilingMode == ProfilingMode.GRAPH) {
            return new GraphProfilePrinter(profileData.getResults());
        }
        return null;
    }
    
    public boolean isDisableGems() {
        return this.disableGems;
    }
    
    public void setDisableGems(final boolean dg) {
        this.disableGems = dg;
    }
    
    public TraceType getTraceType() {
        return this.traceType;
    }
    
    public void setTraceType(final TraceType traceType) {
        this.traceType = traceType;
    }
    
    public static boolean hasLoadedNativeExtensions() {
        return RubyInstanceConfig.loadedNativeExtensions;
    }
    
    public static void setLoadedNativeExtensions(final boolean loadedNativeExtensions) {
        RubyInstanceConfig.loadedNativeExtensions = loadedNativeExtensions;
    }
    
    static {
        CHAINED_COMPILE_LINE_COUNT = SafePropertyAccessor.getInt("jruby.compile.chainsize", 500);
        RubyInstanceConfig.loadedNativeExtensions = false;
        PEEPHOLE_OPTZ = SafePropertyAccessor.getBoolean("jruby.compile.peephole", true);
        RubyInstanceConfig.DYNOPT_COMPILE_ENABLED = SafePropertyAccessor.getBoolean("jruby.compile.dynopt", false);
        RubyInstanceConfig.NOGUARDS_COMPILE_ENABLED = SafePropertyAccessor.getBoolean("jruby.compile.noguards");
        RubyInstanceConfig.FASTEST_COMPILE_ENABLED = SafePropertyAccessor.getBoolean("jruby.compile.fastest");
        RubyInstanceConfig.FASTOPS_COMPILE_ENABLED = (RubyInstanceConfig.FASTEST_COMPILE_ENABLED || SafePropertyAccessor.getBoolean("jruby.compile.fastops", true));
        RubyInstanceConfig.THREADLESS_COMPILE_ENABLED = (RubyInstanceConfig.FASTEST_COMPILE_ENABLED || SafePropertyAccessor.getBoolean("jruby.compile.threadless"));
        RubyInstanceConfig.FASTSEND_COMPILE_ENABLED = (RubyInstanceConfig.FASTEST_COMPILE_ENABLED || SafePropertyAccessor.getBoolean("jruby.compile.fastsend"));
        RubyInstanceConfig.LAZYHANDLES_COMPILE = SafePropertyAccessor.getBoolean("jruby.compile.lazyHandles", false);
        RubyInstanceConfig.INLINE_DYNCALL_ENABLED = (RubyInstanceConfig.FASTEST_COMPILE_ENABLED || SafePropertyAccessor.getBoolean("jruby.compile.inlineDyncalls"));
        POOLING_ENABLED = SafePropertyAccessor.getBoolean("jruby.thread.pool.enabled");
        POOL_MAX = SafePropertyAccessor.getInt("jruby.thread.pool.max", Integer.MAX_VALUE);
        POOL_MIN = SafePropertyAccessor.getInt("jruby.thread.pool.min", 0);
        POOL_TTL = SafePropertyAccessor.getInt("jruby.thread.pool.ttl", 60);
        NATIVE_NET_PROTOCOL = SafePropertyAccessor.getBoolean("jruby.native.net.protocol", false);
        RubyInstanceConfig.FULL_TRACE_ENABLED = SafePropertyAccessor.getBoolean("jruby.debug.fullTrace", false);
        COMPILE_EXCLUDE = SafePropertyAccessor.getProperty("jruby.jit.exclude");
        RubyInstanceConfig.nativeEnabled = true;
        REIFY_RUBY_CLASSES = SafePropertyAccessor.getBoolean("jruby.reify.classes", false);
        REIFY_LOG_ERRORS = SafePropertyAccessor.getBoolean("jruby.reify.logErrors", false);
        USE_GENERATED_HANDLES = SafePropertyAccessor.getBoolean("jruby.java.handles", false);
        DEBUG_LOAD_SERVICE = SafePropertyAccessor.getBoolean("jruby.debug.loadService", false);
        DEBUG_LOAD_TIMINGS = SafePropertyAccessor.getBoolean("jruby.debug.loadService.timing", false);
        DEBUG_LAUNCHING = SafePropertyAccessor.getBoolean("jruby.debug.launch", false);
        DEBUG_SCRIPT_RESOLUTION = SafePropertyAccessor.getBoolean("jruby.debug.scriptResolution", false);
        JUMPS_HAVE_BACKTRACE = SafePropertyAccessor.getBoolean("jruby.jump.backtrace", false);
        JIT_CACHE_ENABLED = SafePropertyAccessor.getBoolean("jruby.jit.cache", true);
        JIT_CODE_CACHE = SafePropertyAccessor.getProperty("jruby.jit.codeCache", null);
        REFLECTED_HANDLES = (SafePropertyAccessor.getBoolean("jruby.reflected.handles", false) || SafePropertyAccessor.getBoolean("jruby.reflection", false));
        NO_UNWRAP_PROCESS_STREAMS = SafePropertyAccessor.getBoolean("jruby.process.noUnwrap", false);
        INTERFACES_USE_PROXY = SafePropertyAccessor.getBoolean("jruby.interfaces.useProxy");
        JIT_LOADING_DEBUG = SafePropertyAccessor.getBoolean("jruby.jit.debug", false);
        CAN_SET_ACCESSIBLE = SafePropertyAccessor.getBoolean("jruby.ji.setAccessible", true);
        ERRNO_BACKTRACE = SafePropertyAccessor.getBoolean("jruby.errno.backtrace", false);
        String specVersion = null;
        try {
            specVersion = System.getProperty("jruby.bytecode.version");
            if (specVersion == null) {
                specVersion = System.getProperty("java.specification.version");
            }
            if (System.getProperty("jruby.native.enabled") != null) {
                RubyInstanceConfig.nativeEnabled = Boolean.getBoolean("jruby.native.enabled");
            }
        }
        catch (SecurityException se) {
            RubyInstanceConfig.nativeEnabled = false;
            specVersion = "1.5";
        }
        if (specVersion.equals("1.5")) {
            JAVA_VERSION = 49;
        }
        else {
            JAVA_VERSION = 50;
        }
    }
    
    public enum CompileMode
    {
        JIT, 
        FORCE, 
        OFF, 
        OFFIR;
        
        public boolean shouldPrecompileCLI() {
            switch (this) {
                case JIT:
                case FORCE: {
                    return !RubyInstanceConfig.DYNOPT_COMPILE_ENABLED;
                }
                default: {
                    return false;
                }
            }
        }
        
        public boolean shouldJIT() {
            switch (this) {
                case JIT:
                case FORCE: {
                    return true;
                }
                default: {
                    return false;
                }
            }
        }
        
        public boolean shouldPrecompileAll() {
            return this == CompileMode.FORCE;
        }
    }
    
    public enum ProfilingMode
    {
        OFF, 
        API, 
        FLAT, 
        GRAPH;
    }
    
    public interface LoadServiceCreator
    {
        public static final LoadServiceCreator DEFAULT = new LoadServiceCreator() {
            public LoadService create(final Ruby runtime) {
                if (runtime.is1_9()) {
                    return new LoadService19(runtime);
                }
                return new LoadService(runtime);
            }
        };
        
        LoadService create(final Ruby p0);
    }
    
    private final class Argument
    {
        public final String originalValue;
        public final String dashedValue;
        
        public Argument(final String value, final boolean dashed) {
            this.originalValue = value;
            this.dashedValue = ((dashed && !value.startsWith("-")) ? ("-" + value) : value);
        }
    }
    
    private class ArgumentProcessor
    {
        private List<Argument> arguments;
        private int argumentIndex;
        private boolean processArgv;
        
        public ArgumentProcessor(final RubyInstanceConfig rubyInstanceConfig, final String[] arguments) {
            this(rubyInstanceConfig, arguments, true, false);
        }
        
        public ArgumentProcessor(final String[] arguments, final boolean processArgv, final boolean dashed) {
            this.argumentIndex = 0;
            this.arguments = new ArrayList<Argument>();
            if (arguments != null && arguments.length > 0) {
                for (final String argument : arguments) {
                    this.arguments.add(new Argument(argument, dashed));
                }
            }
            this.processArgv = processArgv;
        }
        
        public void processArguments() {
            this.processArguments(true);
        }
        
        public void processArguments(final boolean inline) {
            while (this.argumentIndex < this.arguments.size() && this.isInterpreterArgument(this.arguments.get(this.argumentIndex).originalValue)) {
                this.processArgument();
                ++this.argumentIndex;
            }
            if (inline && !RubyInstanceConfig.this.hasInlineScript && RubyInstanceConfig.this.scriptFileName == null && this.argumentIndex < this.arguments.size()) {
                RubyInstanceConfig.this.setScriptFileName(this.arguments.get(this.argumentIndex).originalValue);
                ++this.argumentIndex;
            }
            if (this.processArgv) {
                this.processArgv();
            }
        }
        
        private void processArgv() {
            final List<String> arglist = new ArrayList<String>();
            while (this.argumentIndex < this.arguments.size()) {
                String arg = this.arguments.get(this.argumentIndex).originalValue;
                if (RubyInstanceConfig.this.argvGlobalsOn && arg.startsWith("-")) {
                    arg = arg.substring(1);
                    if (arg.indexOf(61) > 0) {
                        final String[] keyvalue = arg.split("=", 2);
                        RubyInstanceConfig.this.optionGlobals.put(keyvalue[0], keyvalue[1]);
                    }
                    else {
                        RubyInstanceConfig.this.optionGlobals.put(arg, null);
                    }
                }
                else {
                    RubyInstanceConfig.this.argvGlobalsOn = false;
                    arglist.add(arg);
                }
                ++this.argumentIndex;
            }
            arglist.addAll(Arrays.asList(RubyInstanceConfig.this.argv));
            RubyInstanceConfig.this.argv = arglist.toArray(new String[arglist.size()]);
        }
        
        private boolean isInterpreterArgument(final String argument) {
            return argument.length() > 0 && (argument.charAt(0) == '-' || argument.charAt(0) == '+') && !RubyInstanceConfig.this.endOfArguments;
        }
        
        private String getArgumentError(final String additionalError) {
            return "jruby: invalid argument\n" + additionalError + "\n";
        }
        
        private void processArgument() {
            final String argument = this.arguments.get(this.argumentIndex).dashedValue;
            RubyInstanceConfig.this.characterIndex = 1;
        Label_2304:
            while (RubyInstanceConfig.this.characterIndex < argument.length()) {
                switch (argument.charAt(RubyInstanceConfig.this.characterIndex)) {
                    case '0': {
                        final String temp = this.grabOptionalValue();
                        if (null == temp) {
                            RubyInstanceConfig.this.recordSeparator = "\u0000";
                            break Label_2304;
                        }
                        if (temp.equals("0")) {
                            RubyInstanceConfig.this.recordSeparator = "\n\n";
                            break Label_2304;
                        }
                        if (temp.equals("777")) {
                            RubyInstanceConfig.this.recordSeparator = "\uffff";
                            break Label_2304;
                        }
                        try {
                            final int val = Integer.parseInt(temp, 8);
                            RubyInstanceConfig.this.recordSeparator = "" + (char)val;
                            break Label_2304;
                        }
                        catch (Exception e) {
                            final MainExitException mee = new MainExitException(1, this.getArgumentError(" -0 must be followed by either 0, 777, or a valid octal value"));
                            mee.setUsageError(true);
                            throw mee;
                        }
                    }
                    case 'a': {
                        RubyInstanceConfig.this.split = true;
                        break;
                    }
                    case 'b': {
                        RubyInstanceConfig.this.benchmarking = true;
                        break;
                    }
                    case 'c': {
                        RubyInstanceConfig.this.shouldCheckSyntax = true;
                        break;
                    }
                    case 'C': {
                        try {
                            final String saved = this.grabValue(this.getArgumentError(" -C must be followed by a directory expression"));
                            final File base = new File(RubyInstanceConfig.this.currentDirectory);
                            final File newDir = new File(saved);
                            if (newDir.isAbsolute()) {
                                RubyInstanceConfig.this.currentDirectory = newDir.getCanonicalPath();
                            }
                            else {
                                RubyInstanceConfig.this.currentDirectory = new File(base, newDir.getPath()).getCanonicalPath();
                            }
                            if (!new File(RubyInstanceConfig.this.currentDirectory).isDirectory()) {
                                final MainExitException mee2 = new MainExitException(1, "jruby: Can't chdir to " + saved + " (fatal)");
                                throw mee2;
                            }
                            break Label_2304;
                        }
                        catch (IOException e2) {
                            final MainExitException mee3 = new MainExitException(1, this.getArgumentError(" -C must be followed by a valid directory"));
                            throw mee3;
                        }
                    }
                    case 'd': {
                        RubyInstanceConfig.this.debug = true;
                        RubyInstanceConfig.this.verbose = Boolean.TRUE;
                        break;
                    }
                    case 'e': {
                        RubyInstanceConfig.this.inlineScript.append(this.grabValue(this.getArgumentError(" -e must be followed by an expression to evaluate")));
                        RubyInstanceConfig.this.inlineScript.append('\n');
                        RubyInstanceConfig.this.hasInlineScript = true;
                        break Label_2304;
                    }
                    case 'E': {
                        this.processEncodingOption(this.grabValue(this.getArgumentError("unknown encoding name")));
                        break Label_2304;
                    }
                    case 'F': {
                        RubyInstanceConfig.this.inputFieldSeparator = this.grabValue(this.getArgumentError(" -F must be followed by a pattern for input field separation"));
                        break Label_2304;
                    }
                    case 'h': {
                        RubyInstanceConfig.this.shouldPrintUsage = true;
                        RubyInstanceConfig.this.shouldRunInterpreter = false;
                        break;
                    }
                    case 'i': {
                        RubyInstanceConfig.this.inPlaceBackupExtension = this.grabOptionalValue();
                        if (RubyInstanceConfig.this.inPlaceBackupExtension == null) {
                            RubyInstanceConfig.this.inPlaceBackupExtension = "";
                            break Label_2304;
                        }
                        break Label_2304;
                    }
                    case 'I': {
                        final String s = this.grabValue(this.getArgumentError("-I must be followed by a directory name to add to lib path"));
                        final String[] ls = s.split(File.pathSeparator);
                        RubyInstanceConfig.this.loadPaths.addAll(Arrays.asList(ls));
                        break Label_2304;
                    }
                    case 'J': {
                        this.grabOptionalValue();
                        RubyInstanceConfig.this.error.println("warning: " + argument + " argument ignored (launched in same VM?)");
                        break Label_2304;
                    }
                    case 'K': {
                        final String eArg = this.grabValue(this.getArgumentError("provide a value for -K"));
                        RubyInstanceConfig.this.kcode = KCode.create(null, eArg);
                        break;
                    }
                    case 'l': {
                        RubyInstanceConfig.this.processLineEnds = true;
                        break;
                    }
                    case 'n': {
                        RubyInstanceConfig.this.assumeLoop = true;
                        break;
                    }
                    case 'p': {
                        RubyInstanceConfig.this.assumePrinting = true;
                        RubyInstanceConfig.this.assumeLoop = true;
                        break;
                    }
                    case 'r': {
                        RubyInstanceConfig.this.requiredLibraries.add(this.grabValue(this.getArgumentError("-r must be followed by a package to require")));
                        break Label_2304;
                    }
                    case 's': {
                        RubyInstanceConfig.this.argvGlobalsOn = true;
                        break;
                    }
                    case 'S': {
                        this.runBinScript();
                        break Label_2304;
                    }
                    case 'T': {
                        final String temp2 = this.grabOptionalValue();
                        int value = 1;
                        if (temp2 != null) {
                            try {
                                value = Integer.parseInt(temp2, 8);
                            }
                            catch (Exception e3) {
                                value = 1;
                            }
                        }
                        RubyInstanceConfig.this.safeLevel = value;
                        break Label_2304;
                    }
                    case 'U': {
                        RubyInstanceConfig.this.internalEncoding = "UTF-8";
                        break;
                    }
                    case 'v': {
                        RubyInstanceConfig.this.verbose = Boolean.TRUE;
                        RubyInstanceConfig.this.setShowVersion(true);
                        break;
                    }
                    case 'w': {
                        RubyInstanceConfig.this.verbose = Boolean.TRUE;
                        break;
                    }
                    case 'W': {
                        final String temp2 = this.grabOptionalValue();
                        int value = 2;
                        if (null != temp2) {
                            if (temp2.equals("2")) {
                                value = 2;
                            }
                            else if (temp2.equals("1")) {
                                value = 1;
                            }
                            else {
                                if (!temp2.equals("0")) {
                                    final MainExitException mee4 = new MainExitException(1, this.getArgumentError(" -W must be followed by either 0, 1, 2 or nothing"));
                                    mee4.setUsageError(true);
                                    throw mee4;
                                }
                                value = 0;
                            }
                        }
                        switch (value) {
                            case 0: {
                                RubyInstanceConfig.this.verbose = null;
                                break;
                            }
                            case 1: {
                                RubyInstanceConfig.this.verbose = Boolean.FALSE;
                                break;
                            }
                            case 2: {
                                RubyInstanceConfig.this.verbose = Boolean.TRUE;
                                break;
                            }
                        }
                        break Label_2304;
                    }
                    case 'x': {
                        try {
                            final String saved2 = this.grabOptionalValue();
                            if (saved2 != null) {
                                final File base2 = new File(RubyInstanceConfig.this.currentDirectory);
                                final File newDir2 = new File(saved2);
                                if (newDir2.isAbsolute()) {
                                    RubyInstanceConfig.this.currentDirectory = newDir2.getCanonicalPath();
                                }
                                else {
                                    RubyInstanceConfig.this.currentDirectory = new File(base2, newDir2.getPath()).getCanonicalPath();
                                }
                                if (!new File(RubyInstanceConfig.this.currentDirectory).isDirectory()) {
                                    final MainExitException mee5 = new MainExitException(1, "jruby: Can't chdir to " + saved2 + " (fatal)");
                                    throw mee5;
                                }
                            }
                            RubyInstanceConfig.this.xFlag = true;
                            break Label_2304;
                        }
                        catch (IOException e4) {
                            final MainExitException mee6 = new MainExitException(1, this.getArgumentError(" -x must be followed by a valid directory"));
                            throw mee6;
                        }
                    }
                    case 'X': {
                        final String extendedOption = this.grabOptionalValue();
                        if (extendedOption == null) {
                            if (SafePropertyAccessor.getBoolean("jruby.launcher.nopreamble", false)) {
                                throw new MainExitException(0, RubyInstanceConfig.this.getExtendedHelp());
                            }
                            throw new MainExitException(0, "jruby: missing argument\n" + RubyInstanceConfig.this.getExtendedHelp());
                        }
                        else {
                            if (extendedOption.equals("-O")) {
                                RubyInstanceConfig.this.objectSpaceEnabled = false;
                                break Label_2304;
                            }
                            if (extendedOption.equals("+O")) {
                                RubyInstanceConfig.this.objectSpaceEnabled = true;
                                break Label_2304;
                            }
                            if (extendedOption.equals("-C")) {
                                RubyInstanceConfig.this.compileMode = CompileMode.OFF;
                                break Label_2304;
                            }
                            if (extendedOption.equals("-CIR")) {
                                RubyInstanceConfig.this.compileMode = CompileMode.OFFIR;
                                break Label_2304;
                            }
                            if (extendedOption.equals("+C")) {
                                RubyInstanceConfig.this.compileMode = CompileMode.FORCE;
                                break Label_2304;
                            }
                            final MainExitException mee6 = new MainExitException(1, "jruby: invalid extended option " + extendedOption + " (-X will list valid options)\n");
                            mee6.setUsageError(true);
                            throw mee6;
                        }
                        break;
                    }
                    case 'y': {
                        RubyInstanceConfig.this.parserDebug = true;
                        break Label_2304;
                    }
                    case '-': {
                        if (argument.equals("--command") || argument.equals("--bin")) {
                            RubyInstanceConfig.this.characterIndex = argument.length();
                            this.runBinScript();
                            break;
                        }
                        if (argument.equals("--compat")) {
                            RubyInstanceConfig.this.characterIndex = argument.length();
                            RubyInstanceConfig.this.setCompatVersion(CompatVersion.getVersionFromString(this.grabValue(this.getArgumentError("--compat must be RUBY1_8 or RUBY1_9"))));
                            break Label_2304;
                        }
                        if (argument.equals("--copyright")) {
                            RubyInstanceConfig.this.setShowCopyright(true);
                            RubyInstanceConfig.this.shouldRunInterpreter = false;
                            break Label_2304;
                        }
                        if (argument.equals("--debug")) {
                            RubyInstanceConfig.FULL_TRACE_ENABLED = true;
                            RubyInstanceConfig.this.compileMode = CompileMode.OFF;
                            break Label_2304;
                        }
                        if (argument.equals("--jdb")) {
                            RubyInstanceConfig.this.debug = true;
                            RubyInstanceConfig.this.verbose = Boolean.TRUE;
                            break;
                        }
                        if (argument.equals("--help")) {
                            RubyInstanceConfig.this.shouldPrintUsage = true;
                            RubyInstanceConfig.this.shouldRunInterpreter = false;
                            break;
                        }
                        if (argument.equals("--properties")) {
                            RubyInstanceConfig.this.shouldPrintProperties = true;
                            RubyInstanceConfig.this.shouldRunInterpreter = false;
                            break;
                        }
                        if (argument.equals("--version")) {
                            RubyInstanceConfig.this.setShowVersion(true);
                            RubyInstanceConfig.this.shouldRunInterpreter = false;
                            break Label_2304;
                        }
                        if (argument.equals("--bytecode")) {
                            RubyInstanceConfig.this.setShowBytecode(true);
                            break Label_2304;
                        }
                        if (argument.equals("--fast")) {
                            RubyInstanceConfig.this.compileMode = CompileMode.FORCE;
                            RubyInstanceConfig.FASTOPS_COMPILE_ENABLED = true;
                            RubyInstanceConfig.FASTSEND_COMPILE_ENABLED = true;
                            RubyInstanceConfig.INLINE_DYNCALL_ENABLED = true;
                            break Label_2304;
                        }
                        if (argument.equals("--profile.api")) {
                            RubyInstanceConfig.this.profilingMode = ProfilingMode.API;
                            break Label_2304;
                        }
                        if (argument.equals("--profile") || argument.equals("--profile.flat")) {
                            RubyInstanceConfig.this.profilingMode = ProfilingMode.FLAT;
                            break Label_2304;
                        }
                        if (argument.equals("--profile.graph")) {
                            RubyInstanceConfig.this.profilingMode = ProfilingMode.GRAPH;
                            break Label_2304;
                        }
                        if (argument.equals("--1.9")) {
                            RubyInstanceConfig.this.setCompatVersion(CompatVersion.RUBY1_9);
                            break Label_2304;
                        }
                        if (argument.equals("--1.8")) {
                            RubyInstanceConfig.this.setCompatVersion(CompatVersion.RUBY1_8);
                            break Label_2304;
                        }
                        if (argument.equals("--disable-gems")) {
                            RubyInstanceConfig.this.disableGems = true;
                            break Label_2304;
                        }
                        if (argument.equals("--")) {
                            RubyInstanceConfig.this.endOfArguments = true;
                            break;
                        }
                        throw new MainExitException(1, "jruby: unknown option " + argument);
                    }
                }
                final RubyInstanceConfig this$0 = RubyInstanceConfig.this;
                ++this$0.characterIndex;
            }
        }
        
        private void processEncodingOption(final String value) {
            final String[] encodings = value.split(":", 3);
            switch (encodings.length) {
                case 3: {
                    throw new MainExitException(1, "extra argument for -E: " + encodings[2]);
                }
                case 2: {
                    RubyInstanceConfig.this.internalEncoding = encodings[1];
                }
                case 1: {
                    RubyInstanceConfig.this.externalEncoding = encodings[0];
                    break;
                }
            }
        }
        
        private void runBinScript() {
            String scriptName = this.grabValue("jruby: provide a bin script to execute");
            if (scriptName.equals("irb")) {
                scriptName = "jirb";
            }
            RubyInstanceConfig.this.scriptFileName = this.resolveScript(scriptName);
            if (RubyInstanceConfig.this.scriptFileName == null) {
                RubyInstanceConfig.this.scriptFileName = scriptName;
                RubyInstanceConfig.this.requiredLibraries.add("jruby/commands");
                RubyInstanceConfig.this.inlineScript.append("JRuby::Commands.").append(scriptName);
                RubyInstanceConfig.this.inlineScript.append("\n");
                RubyInstanceConfig.this.hasInlineScript = true;
            }
            RubyInstanceConfig.this.endOfArguments = true;
        }
        
        private String resolveScript(final String scriptName) {
            File fullName = null;
            try {
                fullName = JRubyFile.create(RubyInstanceConfig.this.currentDirectory, scriptName);
                if (fullName.exists() && fullName.isFile()) {
                    if (RubyInstanceConfig.DEBUG_SCRIPT_RESOLUTION) {
                        RubyInstanceConfig.this.error.println("Found: " + fullName.getAbsolutePath());
                    }
                    return scriptName;
                }
            }
            catch (Exception ex) {}
            try {
                fullName = JRubyFile.create(RubyInstanceConfig.this.getJRubyHome(), "bin/" + scriptName);
                if (fullName.exists() && fullName.isFile()) {
                    if (RubyInstanceConfig.DEBUG_SCRIPT_RESOLUTION) {
                        RubyInstanceConfig.this.error.println("Found: " + fullName.getAbsolutePath());
                    }
                    return fullName.getAbsolutePath();
                }
            }
            catch (Exception ex2) {}
            try {
                final String path = System.getenv("PATH");
                if (path != null) {
                    final String[] paths = path.split(System.getProperty("path.separator"));
                    for (int i = 0; i < paths.length; ++i) {
                        fullName = JRubyFile.create(paths[i], scriptName);
                        if (fullName.exists() && fullName.isFile()) {
                            if (RubyInstanceConfig.DEBUG_SCRIPT_RESOLUTION) {
                                RubyInstanceConfig.this.error.println("Found: " + fullName.getAbsolutePath());
                            }
                            return fullName.getAbsolutePath();
                        }
                    }
                }
            }
            catch (Exception ex3) {}
            if (RubyInstanceConfig.this.debug) {
                RubyInstanceConfig.this.error.println("warning: could not resolve -S script on filesystem: " + scriptName);
            }
            return null;
        }
        
        private String grabValue(final String errorMessage) {
            final String optValue = this.grabOptionalValue();
            if (optValue != null) {
                return optValue;
            }
            ++this.argumentIndex;
            if (this.argumentIndex < this.arguments.size()) {
                return this.arguments.get(this.argumentIndex).originalValue;
            }
            final MainExitException mee = new MainExitException(1, errorMessage);
            mee.setUsageError(true);
            throw mee;
        }
        
        private String grabOptionalValue() {
            final RubyInstanceConfig this$0 = RubyInstanceConfig.this;
            ++this$0.characterIndex;
            final String argValue = this.arguments.get(this.argumentIndex).originalValue;
            if (RubyInstanceConfig.this.characterIndex < argValue.length()) {
                return argValue.substring(RubyInstanceConfig.this.characterIndex);
            }
            return null;
        }
    }
}
