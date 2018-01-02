// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.runtime.ReadonlyGlobalVariable;
import org.jruby.util.io.BadDescriptorException;
import org.jruby.util.KCode;
import org.jruby.javasupport.JavaUtil;
import org.jruby.common.IRubyWarnings;
import org.jcodings.Encoding;
import org.jruby.util.ByteList;
import org.jruby.platform.Platform;
import org.jruby.javasupport.util.RuntimeHelpers;
import org.jruby.runtime.Block;
import org.jruby.anno.JRubyMethod;
import java.util.Map;
import java.util.HashMap;
import org.jruby.util.OSEnvironment;
import org.jruby.util.io.STDIO;
import org.jruby.util.RegexpOptions;
import org.jruby.runtime.GlobalVariable;
import org.jruby.runtime.Constants;
import org.jruby.runtime.IAccessor;
import org.jruby.internal.runtime.ValueAccessor;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;

public class RubyGlobal
{
    public static void createGlobals(final ThreadContext context, final Ruby runtime) {
        runtime.defineGlobalConstant("TOPLEVEL_BINDING", runtime.newBinding());
        runtime.defineGlobalConstant("TRUE", runtime.getTrue());
        runtime.defineGlobalConstant("FALSE", runtime.getFalse());
        runtime.defineGlobalConstant("NIL", runtime.getNil());
        final RubyArray argvArray = runtime.newArray();
        final String[] argv = runtime.getInstanceConfig().getArgv();
        for (int i = 0; i < argv.length; ++i) {
            argvArray.append(RubyString.newStringShared(runtime, argv[i].getBytes()));
        }
        runtime.defineGlobalConstant("ARGV", argvArray);
        runtime.getGlobalVariables().defineReadonly("$*", new ValueAccessor(argvArray));
        final IAccessor d = new ValueAccessor(runtime.newString(runtime.getInstanceConfig().displayedFileName()));
        runtime.getGlobalVariables().define("$PROGRAM_NAME", d);
        runtime.getGlobalVariables().define("$0", d);
        IRubyObject version = null;
        IRubyObject patchlevel = null;
        final IRubyObject release = runtime.newString("2011-05-23").freeze(context);
        final IRubyObject platform = runtime.newString("java").freeze(context);
        final IRubyObject engine = runtime.newString("jruby").freeze(context);
        switch (runtime.getInstanceConfig().getCompatVersion()) {
            case RUBY1_8: {
                version = runtime.newString("1.8.7").freeze(context);
                patchlevel = runtime.newFixnum(Constants.RUBY_PATCHLEVEL);
                break;
            }
            case RUBY1_9: {
                version = runtime.newString("1.9.2").freeze(context);
                patchlevel = runtime.newFixnum(Constants.RUBY1_9_PATCHLEVEL);
                break;
            }
        }
        runtime.defineGlobalConstant("RUBY_VERSION", version);
        runtime.defineGlobalConstant("RUBY_PATCHLEVEL", patchlevel);
        runtime.defineGlobalConstant("RUBY_RELEASE_DATE", release);
        runtime.defineGlobalConstant("RUBY_PLATFORM", platform);
        runtime.defineGlobalConstant("RUBY_ENGINE", engine);
        final IRubyObject description = runtime.newString(runtime.getInstanceConfig().getVersionString()).freeze(context);
        runtime.defineGlobalConstant("RUBY_DESCRIPTION", description);
        final IRubyObject copyright = runtime.newString(runtime.getInstanceConfig().getCopyrightString()).freeze(context);
        runtime.defineGlobalConstant("RUBY_COPYRIGHT", copyright);
        runtime.defineGlobalConstant("VERSION", version);
        runtime.defineGlobalConstant("RELEASE_DATE", release);
        runtime.defineGlobalConstant("PLATFORM", platform);
        final IRubyObject jrubyVersion = runtime.newString("1.6.2").freeze(context);
        final IRubyObject jrubyRevision = runtime.newString(Constants.REVISION).freeze(context);
        runtime.defineGlobalConstant("JRUBY_VERSION", jrubyVersion);
        runtime.defineGlobalConstant("JRUBY_REVISION", jrubyRevision);
        if (runtime.is1_9()) {
            runtime.defineGlobalConstant("RUBY_REVISION", runtime.newFixnum(Constants.RUBY1_9_REVISION));
        }
        final GlobalVariable kcodeGV = new KCodeGlobalVariable(runtime, "$KCODE", runtime.newString("NONE"));
        runtime.defineVariable(kcodeGV);
        runtime.defineVariable(new GlobalVariable.Copy(runtime, "$-K", kcodeGV));
        final IRubyObject defaultRS = runtime.newString(runtime.getInstanceConfig().getRecordSeparator()).freeze(context);
        final GlobalVariable rs = new StringGlobalVariable(runtime, "$/", defaultRS);
        runtime.defineVariable(rs);
        runtime.setRecordSeparatorVar(rs);
        runtime.getGlobalVariables().setDefaultSeparator(defaultRS);
        runtime.defineVariable(new StringGlobalVariable(runtime, "$\\", runtime.getNil()));
        runtime.defineVariable(new StringGlobalVariable(runtime, "$,", runtime.getNil()));
        runtime.defineVariable(new LineNumberGlobalVariable(runtime, "$."));
        runtime.defineVariable(new LastlineGlobalVariable(runtime, "$_"));
        runtime.defineVariable(new LastExitStatusVariable(runtime, "$?"));
        runtime.defineVariable(new ErrorInfoGlobalVariable(runtime, "$!", runtime.getNil()));
        runtime.defineVariable(new NonEffectiveGlobalVariable(runtime, "$=", runtime.getFalse()));
        if (runtime.getInstanceConfig().getInputFieldSeparator() == null) {
            runtime.defineVariable(new GlobalVariable(runtime, "$;", runtime.getNil()));
        }
        else {
            runtime.defineVariable(new GlobalVariable(runtime, "$;", RubyRegexp.newRegexp(runtime, runtime.getInstanceConfig().getInputFieldSeparator(), new RegexpOptions())));
        }
        final Boolean verbose = runtime.getInstanceConfig().getVerbose();
        IRubyObject verboseValue = null;
        if (verbose == null) {
            verboseValue = runtime.getNil();
        }
        else if (verbose == Boolean.TRUE) {
            verboseValue = runtime.getTrue();
        }
        else {
            verboseValue = runtime.getFalse();
        }
        runtime.defineVariable(new VerboseGlobalVariable(runtime, "$VERBOSE", verboseValue));
        final IRubyObject debug = runtime.newBoolean(runtime.getInstanceConfig().isDebug());
        runtime.defineVariable(new DebugGlobalVariable(runtime, "$DEBUG", debug));
        runtime.defineVariable(new DebugGlobalVariable(runtime, "$-d", debug));
        runtime.defineVariable(new SafeGlobalVariable(runtime, "$SAFE"));
        runtime.defineVariable(new BacktraceGlobalVariable(runtime, "$@"));
        final IRubyObject stdin = new RubyIO(runtime, STDIO.IN);
        final IRubyObject stdout = new RubyIO(runtime, STDIO.OUT);
        final IRubyObject stderr = new RubyIO(runtime, STDIO.ERR);
        runtime.defineVariable(new InputGlobalVariable(runtime, "$stdin", stdin));
        runtime.defineVariable(new OutputGlobalVariable(runtime, "$stdout", stdout));
        runtime.getGlobalVariables().alias("$>", "$stdout");
        runtime.getGlobalVariables().alias("$defout", "$stdout");
        runtime.defineVariable(new OutputGlobalVariable(runtime, "$stderr", stderr));
        runtime.getGlobalVariables().alias("$deferr", "$stderr");
        runtime.defineGlobalConstant("STDIN", stdin);
        runtime.defineGlobalConstant("STDOUT", stdout);
        runtime.defineGlobalConstant("STDERR", stderr);
        runtime.defineVariable(new LoadedFeatures(runtime, "$\""));
        runtime.defineVariable(new LoadedFeatures(runtime, "$LOADED_FEATURES"));
        runtime.defineVariable(new LoadPath(runtime, "$:"));
        runtime.defineVariable(new LoadPath(runtime, "$-I"));
        runtime.defineVariable(new LoadPath(runtime, "$LOAD_PATH"));
        runtime.defineVariable(new MatchMatchGlobalVariable(runtime, "$&"));
        runtime.defineVariable(new PreMatchGlobalVariable(runtime, "$`"));
        runtime.defineVariable(new PostMatchGlobalVariable(runtime, "$'"));
        runtime.defineVariable(new LastMatchGlobalVariable(runtime, "$+"));
        runtime.defineVariable(new BackRefGlobalVariable(runtime, "$~"));
        runtime.getGlobalVariables().defineReadonly("$$", new PidAccessor(runtime));
        defineGlobalEnvConstants(runtime);
        if (runtime.getGlobalVariables().get("$*").isNil()) {
            runtime.getGlobalVariables().defineReadonly("$*", new ValueAccessor(runtime.newArray()));
        }
        runtime.getGlobalVariables().defineReadonly("$-p", new ValueAccessor(runtime.newBoolean(runtime.getInstanceConfig().isAssumePrinting())));
        runtime.getGlobalVariables().defineReadonly("$-a", new ValueAccessor(runtime.newBoolean(runtime.getInstanceConfig().isSplit())));
        runtime.getGlobalVariables().defineReadonly("$-l", new ValueAccessor(runtime.newBoolean(runtime.getInstanceConfig().isProcessLineEnds())));
        RubyArgsFile.initArgsFile(runtime);
    }
    
    private static void defineGlobalEnvConstants(final Ruby runtime) {
        Map environmentVariableMap = null;
        final OSEnvironment environment = new OSEnvironment();
        environmentVariableMap = environment.getEnvironmentVariableMap(runtime);
        if (environmentVariableMap == null) {
            environmentVariableMap = new HashMap();
        }
        final CaseInsensitiveStringOnlyRubyHash h1 = new CaseInsensitiveStringOnlyRubyHash(runtime, environmentVariableMap, runtime.getNil());
        h1.getSingletonClass().defineAnnotatedMethods(CaseInsensitiveStringOnlyRubyHash.class);
        runtime.defineGlobalConstant("ENV", h1);
        final Map systemProps = environment.getSystemPropertiesMap(runtime);
        runtime.defineGlobalConstant("ENV_JAVA", new StringOnlyRubyHash(runtime, systemProps, runtime.getNil()));
    }
    
    public static class CaseInsensitiveStringOnlyRubyHash extends StringOnlyRubyHash
    {
        public CaseInsensitiveStringOnlyRubyHash(final Ruby runtime, final Map valueMap, final IRubyObject defaultValue) {
            super(runtime, valueMap, defaultValue);
        }
        
        public IRubyObject op_aref(final ThreadContext context, final IRubyObject key) {
            return this.case_aware_op_aref(context, key, false);
        }
        
        public IRubyObject op_aset(final ThreadContext context, final IRubyObject key, final IRubyObject value) {
            return this.case_aware_op_aset(context, key, value, false);
        }
        
        @JRubyMethod
        public IRubyObject to_s() {
            return this.getRuntime().newString("ENV");
        }
    }
    
    public static class StringOnlyRubyHash extends RubyHash
    {
        public StringOnlyRubyHash(final Ruby runtime, final Map valueMap, final IRubyObject defaultValue) {
            super(runtime, valueMap, defaultValue);
        }
        
        public IRubyObject op_aref(final ThreadContext context, final IRubyObject key) {
            return this.case_aware_op_aref(context, key, true);
        }
        
        public RubyHash to_hash() {
            final Ruby runtime = this.getRuntime();
            final RubyHash hash = RubyHash.newHash(runtime);
            hash.replace(runtime.getCurrentContext(), this);
            return hash;
        }
        
        public IRubyObject op_aset(final ThreadContext context, final IRubyObject key, final IRubyObject value) {
            return this.case_aware_op_aset(context, key, value, true);
        }
        
        public IRubyObject op_aset19(final ThreadContext context, final IRubyObject key, final IRubyObject value) {
            return this.op_aset(context, key, value);
        }
        
        protected IRubyObject case_aware_op_aref(final ThreadContext context, IRubyObject key, final boolean caseSensitive) {
            if (!caseSensitive) {
                key = this.getCorrectKey(key, context);
            }
            return super.op_aref(context, key);
        }
        
        protected IRubyObject case_aware_op_aset(final ThreadContext context, IRubyObject key, final IRubyObject value, final boolean caseSensitive) {
            if (!key.respondsTo("to_str")) {
                throw this.getRuntime().newTypeError("can't convert " + key.getMetaClass() + " into String");
            }
            if (!value.respondsTo("to_str") && !value.isNil()) {
                throw this.getRuntime().newTypeError("can't convert " + value.getMetaClass() + " into String");
            }
            if (!caseSensitive) {
                key = this.getCorrectKey(key, context);
            }
            if (value.isNil()) {
                return super.delete(context, key, Block.NULL_BLOCK);
            }
            return super.op_aset(context, this.normalizeEnvString(RuntimeHelpers.invoke(context, key, "to_str")), value.isNil() ? this.getRuntime().getNil() : this.normalizeEnvString(RuntimeHelpers.invoke(context, value, "to_str")));
        }
        
        private RubyString getCorrectKey(final IRubyObject key, final ThreadContext context) {
            RubyString actualKey;
            final RubyString originalKey = actualKey = key.convertToString();
            final Ruby runtime = context.getRuntime();
            if (Platform.IS_WINDOWS) {
                final RubyArray keys = super.keys();
                for (int i = 0; i < keys.size(); ++i) {
                    final RubyString candidateKey = keys.eltInternal(i).convertToString();
                    if (candidateKey.casecmp(context, originalKey).op_equal(context, RubyFixnum.zero(runtime)).isTrue()) {
                        actualKey = candidateKey;
                        break;
                    }
                }
            }
            return actualKey;
        }
        
        private IRubyObject normalizeEnvString(final IRubyObject str) {
            if (str instanceof RubyString) {
                final Encoding enc = this.getRuntime().getEncodingService().getLocaleEncoding();
                final RubyString newStr = this.getRuntime().newString(new ByteList(str.toString().getBytes(), enc));
                newStr.setFrozen(true);
                return newStr;
            }
            return str;
        }
    }
    
    private static class NonEffectiveGlobalVariable extends GlobalVariable
    {
        public NonEffectiveGlobalVariable(final Ruby runtime, final String name, final IRubyObject value) {
            super(runtime, name, value);
        }
        
        public IRubyObject set(final IRubyObject value) {
            this.runtime.getWarnings().warn(IRubyWarnings.ID.INEFFECTIVE_GLOBAL, "warning: variable " + this.name + " is no longer effective; ignored", this.name);
            return value;
        }
        
        public IRubyObject get() {
            this.runtime.getWarnings().warn(IRubyWarnings.ID.INEFFECTIVE_GLOBAL, "warning: variable " + this.name + " is no longer effective", this.name);
            return this.runtime.getFalse();
        }
    }
    
    private static class LastExitStatusVariable extends GlobalVariable
    {
        public LastExitStatusVariable(final Ruby runtime, final String name) {
            super(runtime, name, runtime.getNil());
        }
        
        public IRubyObject get() {
            final IRubyObject lastExitStatus = this.runtime.getCurrentContext().getLastExitStatus();
            return (lastExitStatus == null) ? this.runtime.getNil() : lastExitStatus;
        }
        
        public IRubyObject set(final IRubyObject lastExitStatus) {
            throw this.runtime.newNameError("$? is a read-only variable", "$?");
        }
    }
    
    private static class MatchMatchGlobalVariable extends GlobalVariable
    {
        public MatchMatchGlobalVariable(final Ruby runtime, final String name) {
            super(runtime, name, runtime.getNil());
        }
        
        public IRubyObject get() {
            return RubyRegexp.last_match(this.runtime.getCurrentContext().getCurrentScope().getBackRef(this.runtime));
        }
    }
    
    private static class PreMatchGlobalVariable extends GlobalVariable
    {
        public PreMatchGlobalVariable(final Ruby runtime, final String name) {
            super(runtime, name, runtime.getNil());
        }
        
        public IRubyObject get() {
            return RubyRegexp.match_pre(this.runtime.getCurrentContext().getCurrentScope().getBackRef(this.runtime));
        }
    }
    
    private static class PostMatchGlobalVariable extends GlobalVariable
    {
        public PostMatchGlobalVariable(final Ruby runtime, final String name) {
            super(runtime, name, runtime.getNil());
        }
        
        public IRubyObject get() {
            return RubyRegexp.match_post(this.runtime.getCurrentContext().getCurrentScope().getBackRef(this.runtime));
        }
    }
    
    private static class LastMatchGlobalVariable extends GlobalVariable
    {
        public LastMatchGlobalVariable(final Ruby runtime, final String name) {
            super(runtime, name, runtime.getNil());
        }
        
        public IRubyObject get() {
            return RubyRegexp.match_last(this.runtime.getCurrentContext().getCurrentScope().getBackRef(this.runtime));
        }
    }
    
    private static class BackRefGlobalVariable extends GlobalVariable
    {
        public BackRefGlobalVariable(final Ruby runtime, final String name) {
            super(runtime, name, runtime.getNil());
        }
        
        public IRubyObject get() {
            return RuntimeHelpers.getBackref(this.runtime, this.runtime.getCurrentContext());
        }
        
        public IRubyObject set(final IRubyObject value) {
            RuntimeHelpers.setBackref(this.runtime, this.runtime.getCurrentContext(), value);
            return value;
        }
    }
    
    private static class LineNumberGlobalVariable extends GlobalVariable
    {
        public LineNumberGlobalVariable(final Ruby runtime, final String name) {
            super(runtime, name, null);
        }
        
        public IRubyObject set(final IRubyObject value) {
            final int line = (int)value.convertToInteger().getLongValue();
            this.runtime.setCurrentLine(line);
            RubyArgsFile.setCurrentLineNumber(this.runtime.getArgsFile(), line);
            return value;
        }
        
        public IRubyObject get() {
            return this.runtime.newFixnum(this.runtime.getCurrentLine());
        }
    }
    
    private static class ErrorInfoGlobalVariable extends GlobalVariable
    {
        public ErrorInfoGlobalVariable(final Ruby runtime, final String name, final IRubyObject value) {
            super(runtime, name, null);
            this.set(value);
        }
        
        public IRubyObject set(final IRubyObject value) {
            if (!value.isNil() && !this.runtime.getException().isInstance(value) && (!JavaUtil.isJavaObject(value) || !(JavaUtil.unwrapJavaObject(value) instanceof Throwable))) {
                throw this.runtime.newTypeError("assigning non-exception to $!");
            }
            return this.runtime.getCurrentContext().setErrorInfo(value);
        }
        
        public IRubyObject get() {
            return this.runtime.getCurrentContext().getErrorInfo();
        }
    }
    
    public static class StringGlobalVariable extends GlobalVariable
    {
        public StringGlobalVariable(final Ruby runtime, final String name, final IRubyObject value) {
            super(runtime, name, value);
        }
        
        public IRubyObject set(final IRubyObject value) {
            if (!value.isNil() && !(value instanceof RubyString)) {
                throw this.runtime.newTypeError("value of " + this.name() + " must be a String");
            }
            return super.set(value);
        }
    }
    
    public static class KCodeGlobalVariable extends GlobalVariable
    {
        public KCodeGlobalVariable(final Ruby runtime, final String name, final IRubyObject value) {
            super(runtime, name, value);
        }
        
        public IRubyObject get() {
            return this.runtime.getKCode().kcode(this.runtime);
        }
        
        public IRubyObject set(final IRubyObject value) {
            this.runtime.setKCode(KCode.create(this.runtime, value.convertToString().toString()));
            return value;
        }
    }
    
    private static class SafeGlobalVariable extends GlobalVariable
    {
        public SafeGlobalVariable(final Ruby runtime, final String name) {
            super(runtime, name, null);
        }
        
        public IRubyObject get() {
            return this.runtime.newFixnum(this.runtime.getSafeLevel());
        }
        
        public IRubyObject set(final IRubyObject value) {
            this.runtime.getWarnings().warn(IRubyWarnings.ID.SAFE_NOT_SUPPORTED, "SAFE levels are not supported in JRuby", new Object[0]);
            return RubyFixnum.newFixnum(this.runtime, this.runtime.getSafeLevel());
        }
    }
    
    private static class VerboseGlobalVariable extends GlobalVariable
    {
        public VerboseGlobalVariable(final Ruby runtime, final String name, final IRubyObject initialValue) {
            super(runtime, name, initialValue);
            this.set(initialValue);
        }
        
        public IRubyObject get() {
            return this.runtime.getVerbose();
        }
        
        public IRubyObject set(final IRubyObject newValue) {
            if (newValue.isNil()) {
                this.runtime.setVerbose(newValue);
            }
            else {
                this.runtime.setVerbose(this.runtime.newBoolean(newValue.isTrue()));
            }
            return newValue;
        }
    }
    
    private static class DebugGlobalVariable extends GlobalVariable
    {
        public DebugGlobalVariable(final Ruby runtime, final String name, final IRubyObject initialValue) {
            super(runtime, name, initialValue);
            this.set(initialValue);
        }
        
        public IRubyObject get() {
            return this.runtime.getDebug();
        }
        
        public IRubyObject set(final IRubyObject newValue) {
            if (newValue.isNil()) {
                this.runtime.setDebug(newValue);
            }
            else {
                this.runtime.setDebug(this.runtime.newBoolean(newValue.isTrue()));
            }
            return newValue;
        }
    }
    
    private static class BacktraceGlobalVariable extends GlobalVariable
    {
        public BacktraceGlobalVariable(final Ruby runtime, final String name) {
            super(runtime, name, null);
        }
        
        public IRubyObject get() {
            final IRubyObject errorInfo = this.runtime.getGlobalVariables().get("$!");
            IRubyObject backtrace = errorInfo.isNil() ? this.runtime.getNil() : errorInfo.callMethod(errorInfo.getRuntime().getCurrentContext(), "backtrace");
            if (!(backtrace instanceof RubyArray)) {
                backtrace = this.runtime.getNil();
            }
            return backtrace;
        }
        
        public IRubyObject set(final IRubyObject value) {
            if (this.runtime.getGlobalVariables().get("$!").isNil()) {
                throw this.runtime.newArgumentError("$! not set.");
            }
            this.runtime.getGlobalVariables().get("$!").callMethod(value.getRuntime().getCurrentContext(), "set_backtrace", value);
            return value;
        }
    }
    
    private static class LastlineGlobalVariable extends GlobalVariable
    {
        public LastlineGlobalVariable(final Ruby runtime, final String name) {
            super(runtime, name, null);
        }
        
        public IRubyObject get() {
            return RuntimeHelpers.getLastLine(this.runtime, this.runtime.getCurrentContext());
        }
        
        public IRubyObject set(final IRubyObject value) {
            RuntimeHelpers.setLastLine(this.runtime, this.runtime.getCurrentContext(), value);
            return value;
        }
    }
    
    public static class InputGlobalVariable extends GlobalVariable
    {
        public InputGlobalVariable(final Ruby runtime, final String name, final IRubyObject value) {
            super(runtime, name, value);
        }
        
        public IRubyObject set(final IRubyObject value) {
            if (value == this.get()) {
                return value;
            }
            return super.set(value);
        }
    }
    
    public static class OutputGlobalVariable extends GlobalVariable
    {
        public OutputGlobalVariable(final Ruby runtime, final String name, final IRubyObject value) {
            super(runtime, name, value);
        }
        
        public IRubyObject set(final IRubyObject value) {
            if (value == this.get()) {
                return value;
            }
            if (value instanceof RubyIO) {
                final RubyIO io = (RubyIO)value;
                try {
                    io.getOpenFile().getWriteStreamSafe().setSync(true);
                }
                catch (BadDescriptorException e) {
                    throw this.runtime.newErrnoEBADFError();
                }
            }
            if (!value.respondsTo("write")) {
                throw this.runtime.newTypeError(this.name() + " must have write method, " + value.getType().getName() + " given");
            }
            return super.set(value);
        }
    }
    
    private static class LoadPath extends ReadonlyGlobalVariable
    {
        public LoadPath(final Ruby runtime, final String name) {
            super(runtime, name, null);
        }
        
        public IRubyObject get() {
            return this.runtime.getLoadService().getLoadPath();
        }
    }
    
    private static class LoadedFeatures extends ReadonlyGlobalVariable
    {
        public LoadedFeatures(final Ruby runtime, final String name) {
            super(runtime, name, null);
        }
        
        public IRubyObject get() {
            return this.runtime.getLoadService().getLoadedFeatures();
        }
    }
    
    private static final class PidAccessor implements IAccessor
    {
        private final Ruby runtime;
        private IRubyObject pid;
        
        public PidAccessor(final Ruby runtime) {
            this.pid = null;
            this.runtime = runtime;
        }
        
        public IRubyObject getValue() {
            return (this.pid != null) ? this.pid : (this.pid = this.runtime.newFixnum(this.runtime.getPosix().getpid()));
        }
        
        public IRubyObject setValue(final IRubyObject newValue) {
            throw this.runtime.newRuntimeError("cannot assign to $$");
        }
    }
}
