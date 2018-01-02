// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.embed;

import org.jruby.runtime.GlobalVariable;
import org.jruby.RubyGlobal;
import org.jruby.RubyIO;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.javasupport.JavaEmbedUtils;
import org.jruby.embed.internal.BiVariableMap;
import org.jruby.Ruby;
import org.jruby.util.KCode;
import org.jruby.Profile;
import org.jruby.util.ClassCache;
import org.jruby.CompatVersion;
import java.io.OutputStream;
import org.jruby.embed.io.WriterOutputStream;
import java.io.PrintStream;
import org.jruby.embed.io.ReaderInputStream;
import java.io.Reader;
import java.io.InputStream;
import java.util.HashMap;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.List;
import org.jruby.RubyInstanceConfig;
import org.jruby.embed.util.SystemPropertyCatcher;
import org.jruby.embed.internal.SingletonLocalContextProvider;
import org.jruby.embed.internal.SingleThreadLocalContextProvider;
import org.jruby.embed.internal.ConcurrentLocalContextProvider;
import org.jruby.embed.internal.ThreadSafeLocalContextProvider;
import java.io.Writer;
import java.io.PrintWriter;
import org.jruby.embed.internal.EmbedRubyInterfaceAdapterImpl;
import org.jruby.embed.internal.EmbedRubyObjectAdapterImpl;
import org.jruby.embed.internal.EmbedRubyRuntimeAdapterImpl;
import org.jruby.embed.internal.LocalContextProvider;
import java.util.Map;

public class ScriptingContainer implements EmbedRubyInstanceConfigAdapter
{
    private Map basicProperties;
    private LocalContextProvider provider;
    private EmbedRubyRuntimeAdapter runtimeAdapter;
    private EmbedRubyObjectAdapter objectAdapter;
    private EmbedRubyInterfaceAdapter interfaceAdapter;
    
    public ScriptingContainer() {
        this(LocalContextScope.SINGLETON, LocalVariableBehavior.TRANSIENT, true);
    }
    
    public ScriptingContainer(final LocalContextScope scope) {
        this(scope, LocalVariableBehavior.TRANSIENT, true);
    }
    
    public ScriptingContainer(final LocalVariableBehavior behavior) {
        this(LocalContextScope.SINGLETON, behavior, true);
    }
    
    public ScriptingContainer(final LocalContextScope scope, final LocalVariableBehavior behavior) {
        this(scope, behavior, true);
    }
    
    public ScriptingContainer(final LocalContextScope scope, final LocalVariableBehavior behavior, final boolean lazy) {
        this.basicProperties = null;
        this.provider = null;
        this.runtimeAdapter = new EmbedRubyRuntimeAdapterImpl(this);
        this.objectAdapter = new EmbedRubyObjectAdapterImpl(this);
        this.interfaceAdapter = new EmbedRubyInterfaceAdapterImpl(this);
        this.provider = this.getProviderInstance(scope, behavior, lazy);
        try {
            this.initConfig();
        }
        catch (Exception ex) {
            final Writer w = this.getErrorWriter();
            ex.printStackTrace((PrintWriter)w);
            throw new RuntimeException(ex);
        }
        this.setBasicProperties();
    }
    
    private LocalContextProvider getProviderInstance(final LocalContextScope scope, final LocalVariableBehavior behavior, final boolean lazy) {
        switch (scope) {
            case THREADSAFE: {
                return new ThreadSafeLocalContextProvider(behavior, lazy);
            }
            case CONCURRENT: {
                return new ConcurrentLocalContextProvider(behavior, lazy);
            }
            case SINGLETHREAD: {
                return new SingleThreadLocalContextProvider(behavior, lazy);
            }
            default: {
                final LocalVariableBehavior b = SingletonLocalContextProvider.getLocalVariableBehaviorOrNull();
                if (b == null) {
                    return new SingletonLocalContextProvider(behavior, lazy);
                }
                return new SingletonLocalContextProvider(b, lazy);
            }
        }
    }
    
    private void initConfig() throws URISyntaxException, UnsupportedEncodingException {
        final List<String> paths = SystemPropertyCatcher.findLoadPaths();
        this.provider.getRubyInstanceConfig().setLoadPaths(paths);
        final String home = SystemPropertyCatcher.findJRubyHome(this);
        if (home != null) {
            this.provider.getRubyInstanceConfig().setJRubyHome(home);
        }
        this.provider.getRubyInstanceConfig().setCompileMode(RubyInstanceConfig.CompileMode.OFF);
        this.provider.getRubyInstanceConfig().setScriptFileName("<script>");
    }
    
    private void setBasicProperties() {
        (this.basicProperties = new HashMap()).put("container.ids", new String[] { "ruby", "jruby" });
        this.basicProperties.put("language.extension", new String[] { "rb" });
        this.basicProperties.put("language.name", new String[] { "ruby" });
        this.basicProperties.put("language.mimetypes", new String[] { "application/x-ruby" });
    }
    
    public List<String> getLoadPaths() {
        return this.provider.getRubyInstanceConfig().loadPaths();
    }
    
    public void setLoadPaths(final List<String> paths) {
        this.provider.getRubyInstanceConfig().setLoadPaths(paths);
    }
    
    public InputStream getInput() {
        return this.provider.getRubyInstanceConfig().getInput();
    }
    
    public void setInput(final InputStream istream) {
        this.provider.getRubyInstanceConfig().setInput(istream);
    }
    
    public void setInput(final Reader reader) {
        if (reader == null) {
            this.provider.getRubyInstanceConfig().setInput(null);
        }
        else {
            final ReaderInputStream istream = new ReaderInputStream(reader);
            this.provider.getRubyInstanceConfig().setInput(istream);
        }
    }
    
    public PrintStream getOutput() {
        return this.provider.getRubyInstanceConfig().getOutput();
    }
    
    public void setOutput(final PrintStream pstream) {
        this.provider.getRubyInstanceConfig().setOutput(pstream);
    }
    
    public void setOutput(final Writer writer) {
        if (writer == null) {
            this.provider.getRubyInstanceConfig().setOutput(null);
        }
        else {
            final WriterOutputStream ostream = new WriterOutputStream(writer);
            final PrintStream pstream = new PrintStream(ostream);
            this.provider.getRubyInstanceConfig().setOutput(pstream);
        }
    }
    
    public PrintStream getError() {
        return this.provider.getRubyInstanceConfig().getError();
    }
    
    public void setError(final PrintStream pstream) {
        this.provider.getRubyInstanceConfig().setError(pstream);
    }
    
    public void setError(final Writer writer) {
        if (writer == null) {
            this.provider.getRubyInstanceConfig().setError(null);
        }
        else {
            final WriterOutputStream ostream = new WriterOutputStream(writer);
            final PrintStream pstream = new PrintStream(ostream);
            this.provider.getRubyInstanceConfig().setError(pstream);
        }
    }
    
    public RubyInstanceConfig.CompileMode getCompileMode() {
        return this.provider.getRubyInstanceConfig().getCompileMode();
    }
    
    public void setCompileMode(final RubyInstanceConfig.CompileMode mode) {
        this.provider.getRubyInstanceConfig().setCompileMode(mode);
    }
    
    public boolean isRunRubyInProcess() {
        return this.provider.getRubyInstanceConfig().isRunRubyInProcess();
    }
    
    public void setRunRubyInProcess(final boolean inprocess) {
        this.provider.getRubyInstanceConfig().setRunRubyInProcess(inprocess);
    }
    
    public CompatVersion getCompatVersion() {
        return this.provider.getRubyInstanceConfig().getCompatVersion();
    }
    
    public void setCompatVersion(final CompatVersion version) {
        this.provider.getRubyInstanceConfig().setCompatVersion(version);
    }
    
    public boolean isObjectSpaceEnabled() {
        return this.provider.getRubyInstanceConfig().isObjectSpaceEnabled();
    }
    
    public void setObjectSpaceEnabled(final boolean enable) {
        this.provider.getRubyInstanceConfig().setObjectSpaceEnabled(enable);
    }
    
    public Map getEnvironment() {
        return this.provider.getRubyInstanceConfig().getEnvironment();
    }
    
    public void setEnvironment(final Map environment) {
        this.provider.getRubyInstanceConfig().setEnvironment(environment);
    }
    
    public String getCurrentDirectory() {
        if (this.provider.isRuntimeInitialized()) {
            return this.provider.getRuntime().getCurrentDirectory();
        }
        return this.provider.getRubyInstanceConfig().getCurrentDirectory();
    }
    
    public void setCurrentDirectory(final String directory) {
        if (this.provider.isRuntimeInitialized()) {
            this.provider.getRuntime().setCurrentDirectory(directory);
        }
        else {
            this.provider.getRubyInstanceConfig().setCurrentDirectory(directory);
        }
    }
    
    public String getHomeDirectory() {
        return this.provider.getRubyInstanceConfig().getJRubyHome();
    }
    
    public void setHomeDirectory(final String home) {
        this.provider.getRubyInstanceConfig().setJRubyHome(home);
    }
    
    public ClassCache getClassCache() {
        return this.provider.getRubyInstanceConfig().getClassCache();
    }
    
    public void setClassCache(final ClassCache cache) {
        this.provider.getRubyInstanceConfig().setClassCache(cache);
    }
    
    public ClassLoader getClassLoader() {
        return this.provider.getRubyInstanceConfig().getLoader();
    }
    
    public void setClassLoader(final ClassLoader loader) {
        this.provider.getRubyInstanceConfig().setLoader(loader);
    }
    
    public Profile getProfile() {
        return this.provider.getRubyInstanceConfig().getProfile();
    }
    
    public void setProfile(final Profile profile) {
        this.provider.getRubyInstanceConfig().setProfile(profile);
    }
    
    public RubyInstanceConfig.LoadServiceCreator getLoadServiceCreator() {
        return this.provider.getRubyInstanceConfig().getLoadServiceCreator();
    }
    
    public void setLoadServiceCreator(final RubyInstanceConfig.LoadServiceCreator creator) {
        this.provider.getRubyInstanceConfig().setLoadServiceCreator(creator);
    }
    
    public String[] getArgv() {
        return this.provider.getRubyInstanceConfig().getArgv();
    }
    
    public void setArgv(final String[] argv) {
        this.provider.getRubyInstanceConfig().setArgv(argv);
    }
    
    public String getScriptFilename() {
        return this.provider.getRubyInstanceConfig().getScriptFileName();
    }
    
    public void setScriptFilename(final String filename) {
        this.provider.getRubyInstanceConfig().setScriptFileName(filename);
    }
    
    public String getRecordSeparator() {
        return this.provider.getRubyInstanceConfig().getRecordSeparator();
    }
    
    public void setRecordSeparator(final String separator) {
        this.provider.getRubyInstanceConfig().setRecordSeparator(separator);
    }
    
    public KCode getKCode() {
        return this.provider.getRubyInstanceConfig().getKCode();
    }
    
    public void setKCode(final KCode kcode) {
        this.provider.getRubyInstanceConfig().setKCode(kcode);
    }
    
    public int getJitLogEvery() {
        return this.provider.getRubyInstanceConfig().getJitLogEvery();
    }
    
    public void setJitLogEvery(final int logEvery) {
        this.provider.getRubyInstanceConfig().setJitLogEvery(logEvery);
    }
    
    public int getJitThreshold() {
        return this.provider.getRubyInstanceConfig().getJitThreshold();
    }
    
    public void setJitThreshold(final int threshold) {
        this.provider.getRubyInstanceConfig().setJitThreshold(threshold);
    }
    
    public int getJitMax() {
        return this.provider.getRubyInstanceConfig().getJitMax();
    }
    
    public void setJitMax(final int max) {
        this.provider.getRubyInstanceConfig().setJitMax(max);
    }
    
    public int getJitMaxSize() {
        return this.provider.getRubyInstanceConfig().getJitMaxSize();
    }
    
    public void setJitMaxSize(final int maxSize) {
        this.provider.getRubyInstanceConfig().setJitMaxSize(maxSize);
    }
    
    public String getSupportedRubyVersion() {
        return this.provider.getRubyInstanceConfig().getVersionString().trim();
    }
    
    public String[] getProperty(final String key) {
        if (this.basicProperties.containsKey(key)) {
            return this.basicProperties.get(key);
        }
        return null;
    }
    
    public LocalContextProvider getProvider() {
        return this.provider;
    }
    
    @Deprecated
    public Ruby getRuntime() {
        return this.provider.getRuntime();
    }
    
    public BiVariableMap getVarMap() {
        return this.provider.getVarMap();
    }
    
    public Map getAttributeMap() {
        return this.provider.getAttributeMap();
    }
    
    public Object getAttribute(final Object key) {
        return this.provider.getAttributeMap().get(key);
    }
    
    public Object setAttribute(final Object key, final Object value) {
        return this.provider.getAttributeMap().put(key, value);
    }
    
    public Object removeAttribute(final Object key) {
        return this.provider.getAttributeMap().remove(key);
    }
    
    public Object get(final String key) {
        return this.provider.getVarMap().get(this.provider.getRuntime().getTopSelf(), key);
    }
    
    public Object get(final Object receiver, final String key) {
        return this.provider.getVarMap().get(receiver, key);
    }
    
    public Object put(final String key, final Object value) {
        return this.provider.getVarMap().put(this.provider.getRuntime().getTopSelf(), key, value);
    }
    
    public Object put(final Object receiver, final String key, final Object value) {
        return this.provider.getVarMap().put(receiver, key, value);
    }
    
    public Object remove(final String key) {
        return this.remove(this.provider.getRuntime().getTopSelf(), key);
    }
    
    public Object remove(final Object receiver, final String key) {
        return this.provider.getVarMap().remove(receiver, (Object)key);
    }
    
    public void clear() {
        this.provider.getVarMap().clear();
    }
    
    public EmbedEvalUnit parse(final String script, final int... lines) {
        return this.runtimeAdapter.parse(script, lines);
    }
    
    public EmbedEvalUnit parse(final Reader reader, final String filename, final int... lines) {
        return this.runtimeAdapter.parse(reader, filename, lines);
    }
    
    public EmbedEvalUnit parse(final PathType type, final String filename, final int... lines) {
        return this.runtimeAdapter.parse(type, filename, lines);
    }
    
    public EmbedEvalUnit parse(final InputStream istream, final String filename, final int... lines) {
        return this.runtimeAdapter.parse(istream, filename, lines);
    }
    
    public Object runScriptlet(final String script) {
        final EmbedEvalUnit unit = this.parse(script, new int[0]);
        return this.runUnit(unit);
    }
    
    private Object runUnit(final EmbedEvalUnit unit) {
        if (unit == null) {
            return null;
        }
        final IRubyObject ret = unit.run();
        return JavaEmbedUtils.rubyToJava(ret);
    }
    
    public Object runScriptlet(final Reader reader, final String filename) {
        final EmbedEvalUnit unit = this.parse(reader, filename, new int[0]);
        return this.runUnit(unit);
    }
    
    public Object runScriptlet(final InputStream istream, final String filename) {
        final EmbedEvalUnit unit = this.parse(istream, filename, new int[0]);
        return this.runUnit(unit);
    }
    
    public Object runScriptlet(final PathType type, final String filename) {
        final EmbedEvalUnit unit = this.parse(type, filename, new int[0]);
        return this.runUnit(unit);
    }
    
    public EmbedRubyRuntimeAdapter newRuntimeAdapter() {
        return this.runtimeAdapter;
    }
    
    public EmbedRubyObjectAdapter newObjectAdapter() {
        return this.objectAdapter;
    }
    
    public Object callMethod(final Object receiver, final String methodName, final Object... args) {
        return this.objectAdapter.callMethod(receiver, methodName, args);
    }
    
    public Object callMethod(final Object receiver, final String methodName, final Block block, final Object... args) {
        return this.objectAdapter.callMethod(receiver, methodName, block, args);
    }
    
    public <T> T callMethod(final Object receiver, final String methodName, final Class<T> returnType) {
        return this.objectAdapter.callMethod(receiver, methodName, returnType);
    }
    
    public <T> T callMethod(final Object receiver, final String methodName, final Object singleArg, final Class<T> returnType) {
        return this.objectAdapter.callMethod(receiver, methodName, singleArg, returnType);
    }
    
    public <T> T callMethod(final Object receiver, final String methodName, final Object[] args, final Class<T> returnType) {
        return this.objectAdapter.callMethod(receiver, methodName, args, returnType);
    }
    
    public <T> T callMethod(final Object receiver, final String methodName, final Object[] args, final Block block, final Class<T> returnType) {
        return this.objectAdapter.callMethod(receiver, methodName, args, block, returnType);
    }
    
    public <T> T callMethod(final Object receiver, final String methodName, final Class<T> returnType, final EmbedEvalUnit unit) {
        return this.objectAdapter.callMethod(receiver, methodName, returnType, unit);
    }
    
    public <T> T callMethod(final Object receiver, final String methodName, final Object[] args, final Class<T> returnType, final EmbedEvalUnit unit) {
        return this.objectAdapter.callMethod(receiver, methodName, args, returnType, unit);
    }
    
    public <T> T callMethod(final Object receiver, final String methodName, final Object[] args, final Block block, final Class<T> returnType, final EmbedEvalUnit unit) {
        return this.objectAdapter.callMethod(receiver, methodName, args, block, returnType, unit);
    }
    
    public <T> T callSuper(final Object receiver, final Object[] args, final Class<T> returnType) {
        return this.objectAdapter.callSuper(receiver, args, returnType);
    }
    
    public <T> T callSuper(final Object receiver, final Object[] args, final Block block, final Class<T> returnType) {
        return this.objectAdapter.callSuper(receiver, args, block, returnType);
    }
    
    public <T> T getInstance(final Object receiver, final Class<T> clazz) {
        return this.interfaceAdapter.getInstance(receiver, clazz);
    }
    
    public void setReader(final Reader reader) {
        if (reader == null) {
            return;
        }
        final Map map = this.getAttributeMap();
        if (map.containsKey(AttributeName.READER)) {
            final Reader old = map.get(AttributeName.READER);
            if (old == reader) {
                return;
            }
        }
        map.put(AttributeName.READER, reader);
        final InputStream istream = new ReaderInputStream(reader);
        final Ruby runtime = this.provider.getRuntime();
        final RubyIO io = new RubyIO(runtime, istream);
        io.getOpenFile().getMainStream().setSync(true);
        runtime.defineVariable(new RubyGlobal.InputGlobalVariable(runtime, "$stdin", io));
        runtime.getObject().getConstantMapForWrite().put("STDIN", io);
    }
    
    public Reader getReader() {
        final Map map = this.getAttributeMap();
        if (map.containsKey(AttributeName.READER)) {
            return this.getAttributeMap().get(AttributeName.READER);
        }
        return null;
    }
    
    @Deprecated
    public InputStream getIn() {
        return this.getInput();
    }
    
    public void setWriter(final Writer writer) {
        if (writer == null) {
            return;
        }
        final Map map = this.getAttributeMap();
        if (map.containsKey(AttributeName.WRITER)) {
            final Writer old = map.get(AttributeName.WRITER);
            if (old == writer) {
                return;
            }
        }
        map.put(AttributeName.WRITER, writer);
        final PrintStream pstream = new PrintStream(new WriterOutputStream(writer));
        this.setOutputStream(pstream);
    }
    
    private void setOutputStream(final PrintStream pstream) {
        if (pstream == null) {
            return;
        }
        final Ruby runtime = this.provider.getRuntime();
        final RubyIO io = new RubyIO(runtime, pstream);
        io.getOpenFile().getMainStream().setSync(true);
        runtime.defineVariable(new RubyGlobal.OutputGlobalVariable(runtime, "$stdout", io));
        runtime.getObject().getConstantMapForWrite().put("STDOUT", io);
        runtime.getGlobalVariables().alias("$>", "$stdout");
        runtime.getGlobalVariables().alias("$defout", "$stdout");
    }
    
    public void resetWriter() {
        final PrintStream pstream = this.provider.getRubyInstanceConfig().getOutput();
        this.setOutputStream(pstream);
    }
    
    public Writer getWriter() {
        final Map map = this.getAttributeMap();
        if (map.containsKey(AttributeName.WRITER)) {
            return this.getAttributeMap().get(AttributeName.WRITER);
        }
        return null;
    }
    
    @Deprecated
    public PrintStream getOut() {
        return this.getOutput();
    }
    
    public void setErrorWriter(final Writer errorWriter) {
        if (errorWriter == null) {
            return;
        }
        final Map map = this.getAttributeMap();
        if (map.containsKey(AttributeName.ERROR_WRITER)) {
            final Writer old = map.get(AttributeName.ERROR_WRITER);
            if (old == errorWriter) {
                return;
            }
        }
        map.put(AttributeName.ERROR_WRITER, errorWriter);
        final PrintStream pstream = new PrintStream(new WriterOutputStream(errorWriter));
        this.setErrorStream(pstream);
    }
    
    private void setErrorStream(final PrintStream error) {
        if (error == null) {
            return;
        }
        final Ruby runtime = this.provider.getRuntime();
        final RubyIO io = new RubyIO(runtime, error);
        io.getOpenFile().getMainStream().setSync(true);
        runtime.defineVariable(new RubyGlobal.OutputGlobalVariable(runtime, "$stderr", io));
        runtime.getObject().getConstantMapForWrite().put("STDERR", io);
        runtime.getGlobalVariables().alias("$deferr", "$stderr");
    }
    
    public void resetErrorWriter() {
        final PrintStream error = this.provider.getRubyInstanceConfig().getError();
        this.setErrorStream(error);
    }
    
    public Writer getErrorWriter() {
        final Map map = this.getAttributeMap();
        if (map.containsKey(AttributeName.ERROR_WRITER)) {
            return this.getAttributeMap().get(AttributeName.ERROR_WRITER);
        }
        return null;
    }
    
    @Deprecated
    public PrintStream getErr() {
        return this.getError();
    }
    
    public void terminate() {
        this.getProvider().getRuntime().tearDown(false);
        this.getProvider().terminate();
    }
    
    public void finalize() throws Throwable {
        super.finalize();
        this.terminate();
    }
}
