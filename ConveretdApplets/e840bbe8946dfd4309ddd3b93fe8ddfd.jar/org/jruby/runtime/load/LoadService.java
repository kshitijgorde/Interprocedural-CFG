// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.runtime.load;

import java.util.regex.Matcher;
import org.jruby.ast.executable.Script;
import java.util.concurrent.atomic.AtomicInteger;
import org.jruby.util.URLUtil;
import java.util.zip.ZipException;
import java.net.MalformedURLException;
import java.io.FileNotFoundException;
import java.net.URL;
import org.jruby.util.JRubyFile;
import org.jruby.RubyFile;
import org.jruby.exceptions.MainExitException;
import org.jruby.exceptions.RaiseException;
import org.jruby.platform.Platform;
import java.io.IOException;
import org.jruby.RubyString;
import java.util.Iterator;
import java.io.File;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.RubyHash;
import java.util.Collections;
import org.jruby.RubyInstanceConfig;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.HashMap;
import org.jruby.Ruby;
import java.util.jar.JarFile;
import java.util.Map;
import java.util.List;
import org.jruby.RubyArray;
import java.util.regex.Pattern;

public class LoadService
{
    private final LoadTimer loadTimer;
    protected static final Pattern sourcePattern;
    protected static final Pattern extensionPattern;
    protected RubyArray loadPath;
    protected RubyArray loadedFeatures;
    protected List loadedFeaturesInternal;
    protected final Map<String, Library> builtinLibraries;
    protected final Map<String, JarFile> jarFiles;
    protected final Map<String, IAutoloadMethod> autoloadMap;
    protected final Ruby runtime;
    protected boolean caseInsensitiveFS;
    protected Map requireLocks;
    protected final List<LoadSearcher> searchers;
    
    public LoadService(final Ruby runtime) {
        this.builtinLibraries = new HashMap<String, Library>();
        this.jarFiles = new HashMap<String, JarFile>();
        this.autoloadMap = new HashMap<String, IAutoloadMethod>();
        this.caseInsensitiveFS = false;
        this.requireLocks = new Hashtable();
        (this.searchers = new ArrayList<LoadSearcher>()).add(new SourceBailoutSearcher());
        this.searchers.add(new NormalSearcher());
        this.searchers.add(new ClassLoaderSearcher());
        this.searchers.add(new BailoutSearcher());
        this.searchers.add(new ExtensionSearcher());
        this.searchers.add(new ScriptClassSearcher());
        this.runtime = runtime;
        if (RubyInstanceConfig.DEBUG_LOAD_TIMINGS) {
            this.loadTimer = new TracingLoadTimer();
        }
        else {
            this.loadTimer = new LoadTimer();
        }
    }
    
    public void init(final List additionalDirectories) {
        this.loadPath = RubyArray.newArray(this.runtime);
        this.loadedFeatures = RubyArray.newArray(this.runtime);
        this.loadedFeaturesInternal = Collections.synchronizedList((List<Object>)this.loadedFeatures);
        final Iterator iter = additionalDirectories.iterator();
        while (iter.hasNext()) {
            this.addPath(iter.next());
        }
        final RubyHash env = (RubyHash)this.runtime.getObject().fastGetConstant("ENV");
        final RubyString env_rubylib = this.runtime.newString("RUBYLIB");
        if (env.has_key_p(env_rubylib).isTrue()) {
            final String rubylib = env.op_aref(this.runtime.getCurrentContext(), env_rubylib).toString();
            final String[] paths = rubylib.split(File.pathSeparator);
            for (int i = 0; i < paths.length; ++i) {
                this.addPath(paths[i]);
            }
        }
        try {
            final String jrubyHome = this.runtime.getJRubyHome();
            if (jrubyHome != null) {
                final char sep = '/';
                final String rubyDir = jrubyHome + sep + "lib" + sep + "ruby" + sep;
                if (this.runtime.is1_9()) {
                    this.addPath(rubyDir + "site_ruby" + sep + "1.9");
                    this.addPath(rubyDir + "site_ruby" + sep + "shared");
                    this.addPath(rubyDir + "site_ruby" + sep + "1.8");
                    this.addPath(rubyDir + "1.9");
                }
                else {
                    this.addPath(rubyDir + "site_ruby" + sep + "1.8");
                    this.addPath(rubyDir + "site_ruby" + sep + "shared");
                    this.addPath(rubyDir + "1.8");
                }
                final String lowerCaseJRubyHome = jrubyHome.toLowerCase();
                final String upperCaseJRubyHome = lowerCaseJRubyHome.toUpperCase();
                try {
                    final String canonNormal = new File(jrubyHome).getCanonicalPath();
                    final String canonLower = new File(lowerCaseJRubyHome).getCanonicalPath();
                    final String canonUpper = new File(upperCaseJRubyHome).getCanonicalPath();
                    if (canonNormal.equals(canonLower) && canonLower.equals(canonUpper)) {
                        this.caseInsensitiveFS = true;
                    }
                }
                catch (Exception ex) {}
            }
        }
        catch (SecurityException ex2) {}
        if (!this.runtime.is1_9() && this.runtime.getSafeLevel() == 0) {
            this.addPath(".");
        }
    }
    
    protected void addLoadedFeature(final RubyString loadNameRubyString) {
        this.loadedFeaturesInternal.add(loadNameRubyString);
    }
    
    protected void addPath(final String path) {
        if (path == null || path.length() == 0) {
            return;
        }
        synchronized (this.loadPath) {
            this.loadPath.append(this.runtime.newString(path.replace('\\', '/')));
        }
    }
    
    public void load(final String file, final boolean wrap) {
        if (!this.runtime.getProfile().allowLoad(file)) {
            throw this.runtime.newLoadError("No such file to load -- " + file);
        }
        final SearchState state = new SearchState(file);
        state.prepareLoadSearch(file);
        Library library = this.findBuiltinLibrary(state, state.searchFile, state.suffixType);
        if (library == null) {
            library = this.findLibraryWithoutCWD(state, state.searchFile, state.suffixType);
        }
        if (library == null) {
            library = this.findLibraryWithClassloaders(state, state.searchFile, state.suffixType);
            if (library == null) {
                throw this.runtime.newLoadError("No such file to load -- " + file);
            }
        }
        try {
            library.load(this.runtime, wrap);
        }
        catch (IOException e) {
            if (this.runtime.getDebug().isTrue()) {
                e.printStackTrace(this.runtime.getErr());
            }
            throw newLoadErrorFromThrowable(this.runtime, file, e);
        }
    }
    
    public SearchState findFileForLoad(final String file) throws AlreadyLoaded {
        final SearchState state = new SearchState(file);
        state.prepareRequireSearch(file);
        for (final LoadSearcher searcher : this.searchers) {
            if (searcher.shouldTrySearch(state)) {
                searcher.trySearch(state);
            }
        }
        return state;
    }
    
    public boolean lockAndRequire(final String requireName) {
        try {
            Object requireLock;
            synchronized (this.requireLocks) {
                requireLock = this.requireLocks.get(requireName);
                if (requireLock == null) {
                    requireLock = new Object();
                    this.requireLocks.put(requireName, requireLock);
                }
            }
            synchronized (requireLock) {
                return this.require(requireName);
            }
        }
        finally {
            synchronized (this.requireLocks) {
                this.requireLocks.remove(requireName);
            }
        }
    }
    
    public boolean smartLoad(String file) {
        this.checkEmptyLoad(file);
        if (Platform.IS_WINDOWS) {
            file = file.replace('\\', '/');
        }
        try {
            if (file.endsWith(".so")) {
                file = file.replaceAll(".so$", ".jar");
            }
            final SearchState state = this.findFileForLoad(file);
            return this.tryLoadingLibraryOrScript(this.runtime, state);
        }
        catch (AlreadyLoaded al) {
            return false;
        }
    }
    
    public boolean require(final String file) {
        if (!this.runtime.getProfile().allowRequire(file)) {
            throw this.runtime.newLoadError("No such file to load -- " + file);
        }
        if (this.featureAlreadyLoaded(RubyString.newString(this.runtime, file))) {
            return false;
        }
        final long startTime = this.loadTimer.startLoad(file);
        try {
            return this.smartLoad(file);
        }
        finally {
            this.loadTimer.endLoad(file, startTime);
        }
    }
    
    public static void reflectedLoad(final Ruby runtime, final String libraryName, final String className, ClassLoader classLoader, final boolean wrap) {
        try {
            if (classLoader == null && Ruby.isSecurityRestricted()) {
                classLoader = runtime.getInstanceConfig().getLoader();
            }
            final Object libObject = classLoader.loadClass(className).newInstance();
            if (libObject instanceof Library) {
                final Library library = (Library)libObject;
                library.load(runtime, false);
            }
            else if (libObject instanceof BasicLibraryService) {
                final BasicLibraryService service = (BasicLibraryService)libObject;
                service.basicLoad(runtime);
            }
        }
        catch (RaiseException re) {
            throw re;
        }
        catch (Throwable e) {
            if (runtime.getDebug().isTrue()) {
                e.printStackTrace();
            }
            throw runtime.newLoadError("library `" + libraryName + "' could not be loaded: " + e);
        }
    }
    
    public IRubyObject getLoadPath() {
        return this.loadPath;
    }
    
    public IRubyObject getLoadedFeatures() {
        return this.loadedFeatures;
    }
    
    public IAutoloadMethod autoloadFor(final String name) {
        return this.autoloadMap.get(name);
    }
    
    public void removeAutoLoadFor(final String name) {
        this.autoloadMap.remove(name);
    }
    
    public IRubyObject autoload(final String name) {
        final IAutoloadMethod loadMethod = this.autoloadMap.remove(name);
        if (loadMethod != null) {
            return loadMethod.load(this.runtime, name);
        }
        return null;
    }
    
    public void addAutoload(final String name, final IAutoloadMethod loadMethod) {
        this.autoloadMap.put(name, loadMethod);
    }
    
    public void addBuiltinLibrary(final String name, final Library library) {
        this.builtinLibraries.put(name, library);
    }
    
    public void removeBuiltinLibrary(final String name) {
        this.builtinLibraries.remove(name);
    }
    
    public void removeInternalLoadedFeature(final String name) {
        if (this.caseInsensitiveFS) {
            final Iterator iter = this.loadedFeaturesInternal.iterator();
            while (iter.hasNext()) {
                final Object feature = iter.next();
                if (feature.toString().equalsIgnoreCase(name)) {
                    iter.remove();
                }
            }
        }
        else {
            this.loadedFeaturesInternal.remove(name);
        }
    }
    
    protected boolean featureAlreadyLoaded(final RubyString loadNameRubyString) {
        if (this.caseInsensitiveFS) {
            final String name = loadNameRubyString.toString();
            for (final Object feature : this.loadedFeaturesInternal) {
                if (feature.toString().equalsIgnoreCase(name)) {
                    return true;
                }
            }
            return false;
        }
        return this.loadedFeaturesInternal.contains(loadNameRubyString);
    }
    
    protected boolean isJarfileLibrary(final SearchState state, final String file) {
        return state.library instanceof JarredScript && file.endsWith(".jar");
    }
    
    protected void removeLoadedFeature(final RubyString loadNameRubyString) {
        this.loadedFeaturesInternal.remove(loadNameRubyString);
    }
    
    protected void reraiseRaiseExceptions(final Throwable e) throws RaiseException {
        if (e instanceof RaiseException) {
            throw (RaiseException)e;
        }
    }
    
    protected boolean tryLoadingLibraryOrScript(final Ruby runtime, final SearchState state) {
        final RubyString loadNameRubyString = RubyString.newString(runtime, state.loadName);
        try {
            synchronized (this.loadedFeaturesInternal) {
                if (this.featureAlreadyLoaded(loadNameRubyString)) {
                    return false;
                }
                this.addLoadedFeature(loadNameRubyString);
            }
            state.library.load(runtime, false);
            return true;
        }
        catch (MainExitException mee) {
            throw mee;
        }
        catch (Throwable e) {
            if (this.isJarfileLibrary(state, state.searchFile)) {
                return true;
            }
            this.removeLoadedFeature(loadNameRubyString);
            this.reraiseRaiseExceptions(e);
            if (runtime.getDebug().isTrue()) {
                e.printStackTrace(runtime.getErr());
            }
            final RaiseException re = newLoadErrorFromThrowable(runtime, state.searchFile, e);
            re.initCause(e);
            throw re;
        }
    }
    
    private static RaiseException newLoadErrorFromThrowable(final Ruby runtime, final String file, final Throwable t) {
        return runtime.newLoadError(String.format("load error: %s -- %s: %s", file, t.getClass().getName(), t.getMessage()));
    }
    
    protected String buildClassName(String className) {
        className = className.replaceFirst("^\\.\\/", "");
        if (className.lastIndexOf(".") != -1) {
            className = className.substring(0, className.lastIndexOf("."));
        }
        className = className.replace("-", "_minus_").replace('.', '_');
        return className;
    }
    
    protected void checkEmptyLoad(final String file) throws RaiseException {
        if (file.equals("")) {
            throw this.runtime.newLoadError("No such file to load -- " + file);
        }
    }
    
    protected void debugLogTry(final String what, final String msg) {
        if (RubyInstanceConfig.DEBUG_LOAD_SERVICE) {
            this.runtime.getErr().println("LoadService: trying " + what + ": " + msg);
        }
    }
    
    protected void debugLogFound(final String what, final String msg) {
        if (RubyInstanceConfig.DEBUG_LOAD_SERVICE) {
            this.runtime.getErr().println("LoadService: found " + what + ": " + msg);
        }
    }
    
    protected void debugLogFound(final LoadServiceResource resource) {
        if (RubyInstanceConfig.DEBUG_LOAD_SERVICE) {
            String resourceUrl;
            try {
                resourceUrl = resource.getURL().toString();
            }
            catch (IOException e) {
                resourceUrl = e.getMessage();
            }
            this.runtime.getErr().println("LoadService: found: " + resourceUrl);
        }
    }
    
    protected Library findBuiltinLibrary(final SearchState state, final String baseName, final SuffixType suffixType) {
        for (final String suffix : suffixType.getSuffixes()) {
            final String namePlusSuffix = baseName + suffix;
            this.debugLogTry("builtinLib", namePlusSuffix);
            if (this.builtinLibraries.containsKey(namePlusSuffix)) {
                state.loadName = namePlusSuffix;
                final Library lib = this.builtinLibraries.get(namePlusSuffix);
                this.debugLogFound("builtinLib", namePlusSuffix);
                return lib;
            }
        }
        return null;
    }
    
    protected Library findLibraryWithoutCWD(final SearchState state, final String baseName, final SuffixType suffixType) {
        Library library = null;
        switch (suffixType) {
            case Both: {
                library = this.findBuiltinLibrary(state, baseName, SuffixType.Source);
                if (library == null) {
                    library = this.createLibrary(state, this.tryResourceFromJarURL(state, baseName, SuffixType.Source));
                }
                if (library == null) {
                    library = this.createLibrary(state, this.tryResourceFromLoadPathOrURL(state, baseName, SuffixType.Source));
                }
                if (library == null) {
                    library = this.findBuiltinLibrary(state, baseName, SuffixType.Extension);
                }
                if (library == null) {
                    library = this.createLibrary(state, this.tryResourceFromJarURL(state, baseName, SuffixType.Extension));
                }
                if (library == null) {
                    library = this.createLibrary(state, this.tryResourceFromLoadPathOrURL(state, baseName, SuffixType.Extension));
                    break;
                }
                break;
            }
            case Source:
            case Extension: {
                library = this.findBuiltinLibrary(state, baseName, suffixType);
                if (library == null) {
                    library = this.createLibrary(state, this.tryResourceFromJarURL(state, baseName, suffixType));
                }
                if (library == null) {
                    library = this.createLibrary(state, this.tryResourceFromLoadPathOrURL(state, baseName, suffixType));
                    break;
                }
                break;
            }
            case Neither: {
                library = this.createLibrary(state, this.tryResourceFromJarURL(state, baseName, SuffixType.Neither));
                if (library == null) {
                    library = this.createLibrary(state, this.tryResourceFromLoadPathOrURL(state, baseName, SuffixType.Neither));
                    break;
                }
                break;
            }
        }
        return library;
    }
    
    protected Library findLibraryWithClassloaders(final SearchState state, final String baseName, final SuffixType suffixType) {
        for (final String suffix : suffixType.getSuffixes()) {
            final String file = baseName + suffix;
            final LoadServiceResource resource = this.findFileInClasspath(file);
            if (resource != null) {
                state.loadName = this.resolveLoadName(resource, file);
                return this.createLibrary(state, resource);
            }
        }
        return null;
    }
    
    protected Library createLibrary(final SearchState state, final LoadServiceResource resource) {
        if (resource == null) {
            return null;
        }
        final String file = state.loadName;
        if (file.endsWith(".so") || file.endsWith(".dll") || file.endsWith(".bundle")) {
            return new CExtension(resource);
        }
        if (file.endsWith(".jar")) {
            return new JarredScript(resource);
        }
        if (file.endsWith(".class")) {
            return new JavaCompiledScript(resource);
        }
        return new ExternalScript(resource, file);
    }
    
    protected LoadServiceResource tryResourceFromCWD(final SearchState state, final String baseName, final SuffixType suffixType) throws RaiseException {
        LoadServiceResource foundResource = null;
        for (final String suffix : suffixType.getSuffixes()) {
            final String namePlusSuffix = baseName + suffix;
            try {
                final JRubyFile file = JRubyFile.create(this.runtime.getCurrentDirectory(), RubyFile.expandUserPath(this.runtime.getCurrentContext(), namePlusSuffix));
                this.debugLogTry("resourceFromCWD", file.toString());
                if (file.isFile() && file.isAbsolute() && file.canRead()) {
                    final boolean absolute = true;
                    foundResource = new LoadServiceResource(file, this.getFileName(file, namePlusSuffix), absolute);
                    this.debugLogFound(foundResource);
                    state.loadName = this.resolveLoadName(foundResource, namePlusSuffix);
                    break;
                }
            }
            catch (IllegalArgumentException illArgEx) {}
            catch (SecurityException ex) {}
        }
        return foundResource;
    }
    
    protected LoadServiceResource tryResourceFromHome(final SearchState state, final String baseName, final SuffixType suffixType) throws RaiseException {
        LoadServiceResource foundResource = null;
        final RubyHash env = (RubyHash)this.runtime.getObject().fastGetConstant("ENV");
        final RubyString env_home = this.runtime.newString("HOME");
        if (env.has_key_p(env_home).isFalse()) {
            return null;
        }
        final String home = env.op_aref(this.runtime.getCurrentContext(), env_home).toString();
        final String path = baseName.substring(2);
        for (final String suffix : suffixType.getSuffixes()) {
            final String namePlusSuffix = path + suffix;
            try {
                final JRubyFile file = JRubyFile.create(home, RubyFile.expandUserPath(this.runtime.getCurrentContext(), namePlusSuffix));
                this.debugLogTry("resourceFromHome", file.toString());
                if (file.isFile() && file.isAbsolute() && file.canRead()) {
                    final boolean absolute = true;
                    state.loadName = file.getPath();
                    foundResource = new LoadServiceResource(file, state.loadName, absolute);
                    this.debugLogFound(foundResource);
                    break;
                }
            }
            catch (IllegalArgumentException illArgEx) {}
            catch (SecurityException ex) {}
        }
        return foundResource;
    }
    
    protected LoadServiceResource tryResourceFromJarURL(final SearchState state, final String baseName, final SuffixType suffixType) {
        LoadServiceResource foundResource = null;
        if (baseName.startsWith("jar:")) {
            for (final String suffix : suffixType.getSuffixes()) {
                final String namePlusSuffix = baseName + suffix;
                try {
                    final URL url = new URL(namePlusSuffix);
                    this.debugLogTry("resourceFromJarURL", url.toString());
                    if (url.openStream() != null) {
                        foundResource = new LoadServiceResource(url, namePlusSuffix);
                        this.debugLogFound(foundResource);
                    }
                }
                catch (FileNotFoundException e3) {}
                catch (MalformedURLException e) {
                    throw this.runtime.newIOErrorFromException(e);
                }
                catch (IOException e2) {
                    throw this.runtime.newIOErrorFromException(e2);
                }
                if (foundResource != null) {
                    state.loadName = this.resolveLoadName(foundResource, namePlusSuffix);
                    break;
                }
            }
        }
        else if (baseName.startsWith("file:") && baseName.indexOf("!/") != -1) {
            for (final String suffix : suffixType.getSuffixes()) {
                final String namePlusSuffix = baseName + suffix;
                try {
                    final String jarFile = namePlusSuffix.substring(5, namePlusSuffix.indexOf("!/"));
                    final JarFile file = new JarFile(jarFile);
                    final String expandedFilename = this.expandRelativeJarPath(namePlusSuffix.substring(namePlusSuffix.indexOf("!/") + 2));
                    this.debugLogTry("resourceFromJarURL", expandedFilename.toString());
                    if (file.getJarEntry(expandedFilename) != null) {
                        foundResource = new LoadServiceResource(new URL("jar:file:" + jarFile + "!/" + expandedFilename), namePlusSuffix);
                        this.debugLogFound(foundResource);
                    }
                }
                catch (Exception ex) {}
                if (foundResource != null) {
                    state.loadName = this.resolveLoadName(foundResource, namePlusSuffix);
                    break;
                }
            }
        }
        return foundResource;
    }
    
    protected LoadServiceResource tryResourceFromLoadPathOrURL(final SearchState state, final String baseName, final SuffixType suffixType) {
        LoadServiceResource foundResource = null;
        if (baseName.startsWith("./")) {
            foundResource = this.tryResourceFromCWD(state, baseName, suffixType);
            if (foundResource != null) {
                state.loadName = this.resolveLoadName(foundResource, foundResource.getName());
            }
            return foundResource;
        }
        if (baseName.startsWith("~/")) {
            foundResource = this.tryResourceFromHome(state, baseName, suffixType);
            if (foundResource != null) {
                state.loadName = this.resolveLoadName(foundResource, foundResource.getName());
            }
            return foundResource;
        }
        if (new File(baseName).isAbsolute() || baseName.startsWith("../")) {
            for (final String suffix : suffixType.getSuffixes()) {
                final String namePlusSuffix = baseName + suffix;
                foundResource = this.tryResourceAsIs(namePlusSuffix);
                if (foundResource != null) {
                    state.loadName = this.resolveLoadName(foundResource, namePlusSuffix);
                    return foundResource;
                }
            }
            return null;
        }
    Label_0451:
        for (int i = 0; i < this.loadPath.size(); ++i) {
            final RubyString entryString = this.loadPath.eltInternal(i).convertToString();
            final String loadPathEntry = entryString.asJavaString();
            if (loadPathEntry.equals(".") || loadPathEntry.equals("")) {
                foundResource = this.tryResourceFromCWD(state, baseName, suffixType);
                if (foundResource != null) {
                    String ss = foundResource.getName();
                    if (ss.startsWith("./")) {
                        ss = ss.substring(2);
                    }
                    state.loadName = this.resolveLoadName(foundResource, ss);
                    break;
                }
            }
            else {
                final boolean looksLikeJarURL = this.loadPathLooksLikeJarURL(loadPathEntry);
                for (final String suffix2 : suffixType.getSuffixes()) {
                    final String namePlusSuffix2 = baseName + suffix2;
                    if (looksLikeJarURL) {
                        foundResource = this.tryResourceFromJarURLWithLoadPath(namePlusSuffix2, loadPathEntry);
                    }
                    else {
                        foundResource = this.tryResourceFromLoadPath(namePlusSuffix2, loadPathEntry);
                    }
                    if (foundResource != null) {
                        String ss2 = namePlusSuffix2;
                        if (ss2.startsWith("./")) {
                            ss2 = ss2.substring(2);
                        }
                        state.loadName = this.resolveLoadName(foundResource, ss2);
                        break Label_0451;
                    }
                }
            }
        }
        return foundResource;
    }
    
    protected LoadServiceResource tryResourceFromJarURLWithLoadPath(final String namePlusSuffix, final String loadPathEntry) {
        LoadServiceResource foundResource = null;
        JarFile current = this.jarFiles.get(loadPathEntry);
        final boolean isFileJarUrl = loadPathEntry.startsWith("file:") && loadPathEntry.indexOf("!/") != -1;
        final String after = isFileJarUrl ? (loadPathEntry.substring(loadPathEntry.indexOf("!/") + 2) + "/") : "";
        final String before = isFileJarUrl ? loadPathEntry.substring(0, loadPathEntry.indexOf("!/")) : loadPathEntry;
        if (null == current) {
            try {
                if (loadPathEntry.startsWith("jar:")) {
                    current = new JarFile(loadPathEntry.substring(4));
                }
                else if (loadPathEntry.endsWith(".jar")) {
                    current = new JarFile(loadPathEntry);
                }
                else {
                    current = new JarFile(loadPathEntry.substring(5, loadPathEntry.indexOf("!/")));
                }
                this.jarFiles.put(loadPathEntry, current);
            }
            catch (ZipException ignored) {
                if (this.runtime.getInstanceConfig().isDebug()) {
                    this.runtime.getErr().println("ZipException trying to access " + loadPathEntry + ", stack trace follows:");
                    ignored.printStackTrace(this.runtime.getErr());
                }
            }
            catch (FileNotFoundException ignored2) {}
            catch (IOException e) {
                throw this.runtime.newIOErrorFromException(e);
            }
        }
        final String canonicalEntry = after + namePlusSuffix;
        if (current != null) {
            this.debugLogTry("resourceFromJarURLWithLoadPath", current.getName() + "!/" + canonicalEntry);
            if (current.getJarEntry(canonicalEntry) != null) {
                try {
                    if (loadPathEntry.endsWith(".jar")) {
                        foundResource = new LoadServiceResource(new URL("jar:file:" + loadPathEntry + "!/" + canonicalEntry), "/" + namePlusSuffix);
                    }
                    else if (loadPathEntry.startsWith("file:")) {
                        foundResource = new LoadServiceResource(new URL("jar:" + before + "!/" + canonicalEntry), loadPathEntry + "/" + namePlusSuffix);
                    }
                    else {
                        foundResource = new LoadServiceResource(new URL("jar:file:" + loadPathEntry.substring(4) + "!/" + namePlusSuffix), loadPathEntry + namePlusSuffix);
                    }
                    this.debugLogFound(foundResource);
                }
                catch (MalformedURLException e2) {
                    throw this.runtime.newIOErrorFromException(e2);
                }
            }
        }
        return foundResource;
    }
    
    protected boolean loadPathLooksLikeJarURL(final String loadPathEntry) {
        return loadPathEntry.startsWith("jar:") || loadPathEntry.endsWith(".jar") || (loadPathEntry.startsWith("file:") && loadPathEntry.indexOf("!/") != -1);
    }
    
    protected LoadServiceResource tryResourceFromLoadPath(final String namePlusSuffix, final String loadPathEntry) throws RaiseException {
        LoadServiceResource foundResource = null;
        try {
            if (!Ruby.isSecurityRestricted()) {
                String reportedPath = loadPathEntry + "/" + namePlusSuffix;
                boolean absolute = false;
                JRubyFile actualPath;
                if (new File(reportedPath).isAbsolute()) {
                    absolute = true;
                    actualPath = JRubyFile.create(loadPathEntry, RubyFile.expandUserPath(this.runtime.getCurrentContext(), namePlusSuffix));
                }
                else {
                    absolute = false;
                    if (reportedPath.charAt(0) != '.') {
                        reportedPath = "./" + reportedPath;
                    }
                    actualPath = JRubyFile.create(JRubyFile.create(this.runtime.getCurrentDirectory(), loadPathEntry).getAbsolutePath(), RubyFile.expandUserPath(this.runtime.getCurrentContext(), namePlusSuffix));
                }
                this.debugLogTry("resourceFromLoadPath", "'" + actualPath.toString() + "' " + actualPath.isFile() + " " + actualPath.canRead());
                if (actualPath.isFile() && actualPath.canRead()) {
                    foundResource = new LoadServiceResource(actualPath, reportedPath, absolute);
                    this.debugLogFound(foundResource);
                }
            }
        }
        catch (SecurityException ex) {}
        return foundResource;
    }
    
    protected LoadServiceResource tryResourceAsIs(final String namePlusSuffix) throws RaiseException {
        LoadServiceResource foundResource = null;
        try {
            if (!Ruby.isSecurityRestricted()) {
                String reportedPath = namePlusSuffix;
                File actualPath;
                if (new File(reportedPath).isAbsolute()) {
                    actualPath = new File(RubyFile.expandUserPath(this.runtime.getCurrentContext(), namePlusSuffix));
                }
                else {
                    if (reportedPath.charAt(0) == '.' && reportedPath.charAt(1) == '/') {
                        reportedPath = reportedPath.replaceFirst("\\./", this.runtime.getCurrentDirectory());
                    }
                    actualPath = JRubyFile.create(this.runtime.getCurrentDirectory(), RubyFile.expandUserPath(this.runtime.getCurrentContext(), namePlusSuffix));
                }
                this.debugLogTry("resourceAsIs", actualPath.toString());
                if (actualPath.isFile() && actualPath.canRead()) {
                    foundResource = new LoadServiceResource(actualPath, reportedPath);
                    this.debugLogFound(foundResource);
                }
            }
        }
        catch (SecurityException ex) {}
        return foundResource;
    }
    
    protected LoadServiceResource findFileInClasspath(String name) {
        ClassLoader classLoader = this.runtime.getJRubyClassLoader();
        if (Ruby.isSecurityRestricted() && classLoader == null) {
            classLoader = this.runtime.getInstanceConfig().getLoader();
        }
        if (name.startsWith("classpath:/")) {
            final LoadServiceResource foundResource = this.getClassPathResource(classLoader, name);
            if (foundResource != null) {
                return foundResource;
            }
        }
        else if (name.startsWith("classpath:")) {
            name = name.substring("classpath:".length());
        }
        for (int i = 0; i < this.loadPath.size(); ++i) {
            final RubyString entryString = this.loadPath.eltInternal(i).convertToString();
            String entry = entryString.asJavaString();
            if (entry.length() != 0) {
                if (entry.charAt(0) != '/') {
                    if (entry.length() <= 1 || entry.charAt(1) != ':') {
                        if (entry.startsWith("classpath:/")) {
                            entry = entry.substring("classpath:/".length());
                        }
                        else if (entry.startsWith("classpath:")) {
                            entry = entry.substring("classpath:".length());
                        }
                        final LoadServiceResource foundResource2 = this.getClassPathResource(classLoader, entry + "/" + name);
                        if (foundResource2 != null) {
                            return foundResource2;
                        }
                    }
                }
            }
        }
        if (name.charAt(0) == '/' || (name.length() > 1 && name.charAt(1) == ':')) {
            return null;
        }
        final LoadServiceResource foundResource = this.getClassPathResource(classLoader, name);
        if (foundResource != null) {
            return foundResource;
        }
        return null;
    }
    
    protected boolean isRequireable(final URL loc) {
        if (loc != null) {
            if (loc.getProtocol().equals("file") && new File(URLUtil.getPath(loc)).isDirectory()) {
                return false;
            }
            try {
                loc.openConnection();
                return true;
            }
            catch (Exception ex) {}
        }
        return false;
    }
    
    protected LoadServiceResource getClassPathResource(final ClassLoader classLoader, String name) {
        boolean isClasspathScheme = false;
        if (name.startsWith("classpath:/")) {
            isClasspathScheme = true;
            name = name.substring("classpath:/".length());
        }
        else if (name.startsWith("classpath:")) {
            isClasspathScheme = true;
            name = name.substring("classpath:".length());
        }
        this.debugLogTry("fileInClasspath", name);
        final URL loc = classLoader.getResource(name);
        if (loc != null) {
            String path = "classpath:/" + name;
            if (!isClasspathScheme && (loc.getProtocol().equals("jar") || loc.getProtocol().equals("file")) && this.isRequireable(loc)) {
                path = URLUtil.getPath(loc);
            }
            final LoadServiceResource foundResource = new LoadServiceResource(loc, path);
            this.debugLogFound(foundResource);
            return foundResource;
        }
        return null;
    }
    
    private String expandRelativeJarPath(final String path) {
        try {
            final String cwd = new File(".").getCanonicalPath();
            return new File(path).getCanonicalPath().substring(cwd.length() + 1).replaceAll("\\\\", "/");
        }
        catch (Exception e) {
            return path;
        }
    }
    
    protected String resolveLoadName(final LoadServiceResource foundResource, final String previousPath) {
        return previousPath;
    }
    
    protected String getFileName(final JRubyFile file, final String namePlusSuffix) {
        String s = namePlusSuffix;
        if (!namePlusSuffix.startsWith("./")) {
            s = "./" + s;
        }
        return s;
    }
    
    public boolean isCaseInsensitiveFS() {
        return this.caseInsensitiveFS;
    }
    
    static {
        sourcePattern = Pattern.compile("\\.(?:rb)$");
        extensionPattern = Pattern.compile("\\.(?:so|o|dll|bundle|jar)$");
    }
    
    public enum SuffixType
    {
        Source, 
        Extension, 
        Both, 
        Neither;
        
        public static final String[] sourceSuffixes;
        public static final String[] extensionSuffixes;
        private static final String[] allSuffixes;
        private static final String[] emptySuffixes;
        
        public String[] getSuffixes() {
            switch (this) {
                case Source: {
                    return SuffixType.sourceSuffixes;
                }
                case Extension: {
                    return SuffixType.extensionSuffixes;
                }
                case Both: {
                    return SuffixType.allSuffixes;
                }
                case Neither: {
                    return SuffixType.emptySuffixes;
                }
                default: {
                    throw new RuntimeException("Unknown SuffixType: " + this);
                }
            }
        }
        
        static {
            sourceSuffixes = new String[] { ".class", ".rb" };
            extensionSuffixes = new String[] { ".jar", ".so", ".bundle", ".dll" };
            allSuffixes = new String[] { ".class", ".rb", ".jar", ".so", ".bundle", ".dll" };
            emptySuffixes = new String[] { "" };
        }
    }
    
    private static class LoadTimer
    {
        public long startLoad(final String file) {
            return 0L;
        }
        
        public void endLoad(final String file, final long startTime) {
        }
    }
    
    private static class TracingLoadTimer extends LoadTimer
    {
        private final AtomicInteger indent;
        
        private TracingLoadTimer() {
            this.indent = new AtomicInteger(0);
        }
        
        private String getIndentString() {
            final StringBuilder buf = new StringBuilder();
            for (int i = this.indent.get(), j = 0; j < i; ++j) {
                buf.append("  ");
            }
            return buf.toString();
        }
        
        public long startLoad(final String file) {
            this.indent.incrementAndGet();
            System.err.println(this.getIndentString() + "-> " + file);
            return System.currentTimeMillis();
        }
        
        public void endLoad(final String file, final long startTime) {
            System.err.println(this.getIndentString() + "<- " + file + " - " + (System.currentTimeMillis() - startTime) + "ms");
            this.indent.decrementAndGet();
        }
    }
    
    public static class AlreadyLoaded extends Exception
    {
        private RubyString searchNameString;
        
        public AlreadyLoaded(final RubyString searchNameString) {
            this.searchNameString = searchNameString;
        }
        
        public RubyString getSearchNameString() {
            return this.searchNameString;
        }
    }
    
    public class BailoutSearcher implements LoadSearcher
    {
        public boolean shouldTrySearch(final SearchState state) {
            return state.library == null;
        }
        
        protected void trySearch(final String file, final SuffixType suffixType) throws AlreadyLoaded {
            for (final String suffix : suffixType.getSuffixes()) {
                final String searchName = file + suffix;
                final RubyString searchNameString = RubyString.newString(LoadService.this.runtime, searchName);
                if (LoadService.this.featureAlreadyLoaded(searchNameString)) {
                    throw new AlreadyLoaded(searchNameString);
                }
            }
        }
        
        public void trySearch(final SearchState state) throws AlreadyLoaded {
            this.trySearch(state.searchFile, state.suffixType);
        }
    }
    
    public class SourceBailoutSearcher extends BailoutSearcher
    {
        public boolean shouldTrySearch(final SearchState state) {
            return !LoadService.extensionPattern.matcher(state.loadName).find();
        }
        
        public void trySearch(final SearchState state) throws AlreadyLoaded {
            super.trySearch(state.searchFile, SuffixType.Source);
        }
    }
    
    public class NormalSearcher implements LoadSearcher
    {
        public boolean shouldTrySearch(final SearchState state) {
            return state.library == null;
        }
        
        public void trySearch(final SearchState state) {
            state.library = LoadService.this.findLibraryWithoutCWD(state, state.searchFile, state.suffixType);
        }
    }
    
    public class ClassLoaderSearcher implements LoadSearcher
    {
        public boolean shouldTrySearch(final SearchState state) {
            return state.library == null;
        }
        
        public void trySearch(final SearchState state) {
            state.library = LoadService.this.findLibraryWithClassloaders(state, state.searchFile, state.suffixType);
        }
    }
    
    public class ExtensionSearcher implements LoadSearcher
    {
        public boolean shouldTrySearch(final SearchState state) {
            return (state.library == null || state.library instanceof JarredScript) && !state.searchFile.equalsIgnoreCase("");
        }
        
        public void trySearch(final SearchState state) {
            final Library oldLibrary = state.library;
            final String[] all = state.searchFile.split("/");
            final StringBuilder finName = new StringBuilder();
            for (int i = 0, j = all.length - 1; i < j; ++i) {
                finName.append(all[i].toLowerCase()).append(".");
            }
            try {
                final String[] last = all[all.length - 1].split("_");
                for (int k = 0, l = last.length; k < l; ++k) {
                    finName.append(Character.toUpperCase(last[k].charAt(0))).append(last[k].substring(1));
                }
                finName.append("Service");
                final String className = finName.toString().replaceAll("^\\.*", "");
                if (state.library instanceof JarredScript) {
                    LoadService.this.runtime.getJRubyClassLoader().addURL(((JarredScript)state.library).getResource().getURL());
                }
                final Class theClass = LoadService.this.runtime.getJavaSupport().loadJavaClassQuiet(className);
                state.library = new ClassExtensionLibrary(className + ".java", theClass);
            }
            catch (Exception ee) {
                state.library = null;
                LoadService.this.runtime.getGlobalVariables().clear("$!");
            }
            if (state.library == null && oldLibrary != null) {
                state.library = oldLibrary;
            }
        }
    }
    
    public class ScriptClassSearcher implements LoadSearcher
    {
        public boolean shouldTrySearch(final SearchState state) {
            return state.library == null;
        }
        
        public void trySearch(final SearchState state) throws RaiseException {
            String className = LoadService.this.buildClassName(state.searchFile);
            final int lastSlashIndex = className.lastIndexOf(47);
            if (lastSlashIndex > -1 && lastSlashIndex < className.length() - 1 && !Character.isJavaIdentifierStart(className.charAt(lastSlashIndex + 1))) {
                if (lastSlashIndex == -1) {
                    className = "_" + className;
                }
                else {
                    className = className.substring(0, lastSlashIndex + 1) + "_" + className.substring(lastSlashIndex + 1);
                }
            }
            className = className.replace('/', '.');
            Script script;
            try {
                final Class scriptClass = Class.forName(className);
                script = scriptClass.newInstance();
            }
            catch (Exception cnfe) {
                throw LoadService.this.runtime.newLoadError("no such file to load -- " + state.searchFile);
            }
            state.library = new ScriptClassLibrary(script);
        }
        
        public class ScriptClassLibrary implements Library
        {
            private Script script;
            
            public ScriptClassLibrary(final Script script) {
                this.script = script;
            }
            
            public void load(final Ruby runtime, final boolean wrap) {
                runtime.loadScript(this.script);
            }
        }
    }
    
    public static class SearchState
    {
        public Library library;
        public String loadName;
        public SuffixType suffixType;
        public String searchFile;
        
        public SearchState(final String file) {
            this.loadName = file;
        }
        
        public void prepareRequireSearch(final String file) {
            if (file.lastIndexOf(46) > file.lastIndexOf(47)) {
                Matcher matcher = null;
                if ((matcher = LoadService.sourcePattern.matcher(file)).find()) {
                    this.suffixType = SuffixType.Source;
                    this.searchFile = file.substring(0, matcher.start());
                }
                else if ((matcher = LoadService.extensionPattern.matcher(file)).find()) {
                    this.suffixType = SuffixType.Extension;
                    this.searchFile = file.substring(0, matcher.start());
                }
                else {
                    this.suffixType = SuffixType.Both;
                    this.searchFile = file;
                }
            }
            else {
                this.suffixType = SuffixType.Both;
                this.searchFile = file;
            }
        }
        
        public void prepareLoadSearch(final String file) {
            if (file.lastIndexOf(46) > file.lastIndexOf(47)) {
                Matcher matcher = null;
                if ((matcher = LoadService.sourcePattern.matcher(file)).find()) {
                    this.suffixType = SuffixType.Source;
                    this.searchFile = file.substring(0, matcher.start());
                }
                else {
                    this.suffixType = SuffixType.Neither;
                    this.searchFile = file;
                }
            }
            else {
                this.suffixType = SuffixType.Neither;
                this.searchFile = file;
            }
        }
        
        public String toString() {
            final StringBuilder sb = new StringBuilder();
            sb.append(this.getClass().getName()).append(": ");
            sb.append("library=").append(this.library.toString());
            sb.append(", loadName=").append(this.loadName);
            sb.append(", suffixType=").append(this.suffixType.toString());
            sb.append(", searchFile=").append(this.searchFile);
            return sb.toString();
        }
    }
    
    public interface LoadSearcher
    {
        boolean shouldTrySearch(final SearchState p0);
        
        void trySearch(final SearchState p0) throws AlreadyLoaded;
    }
}
