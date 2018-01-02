// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.libraries;

import java.util.HashMap;
import org.jruby.CompatVersion;
import org.jruby.anno.JRubyMethod;
import org.jruby.javasupport.util.RuntimeHelpers;
import org.jruby.runtime.ThreadContext;
import java.util.regex.Matcher;
import java.net.URL;
import org.jruby.RubyModule;
import java.io.IOException;
import java.util.regex.Pattern;
import org.jruby.util.NormalizedFile;
import org.jruby.util.SafePropertyAccessor;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.RubyHash;
import org.jruby.Ruby;
import org.jruby.ext.posix.util.Platform;
import java.util.Map;
import org.jruby.anno.JRubyModule;
import org.jruby.runtime.load.Library;

@JRubyModule(name = { "Config" })
public class RbConfigLibrary implements Library
{
    private static final String RUBY_DARWIN = "darwin";
    private static final String RUBY_LINUX = "linux";
    private static final String RUBY_WIN32 = "mswin32";
    private static final String RUBY_SOLARIS = "solaris";
    private static final String RUBY_FREEBSD = "freebsd";
    private static final String RUBY_AIX = "aix";
    private static String normalizedHome;
    public static final Map<String, String> RUBY_OS_NAMES;
    
    public static String getOSName() {
        if (Platform.IS_WINDOWS) {
            return "mswin32";
        }
        final String OSName = Platform.getOSName();
        final String theOSName = RbConfigLibrary.RUBY_OS_NAMES.get(OSName);
        return (theOSName == null) ? OSName : theOSName;
    }
    
    public static String getArchitecture() {
        String architecture = Platform.ARCH;
        if (architecture == null) {
            architecture = "unknown";
        }
        return architecture;
    }
    
    public void load(final Ruby runtime, final boolean wrap) {
        final RubyModule configModule = runtime.defineModule("Config");
        configModule.defineAnnotatedMethods(RbConfigLibrary.class);
        final RubyHash configHash = RubyHash.newHash(runtime);
        configModule.defineConstant("CONFIG", configHash);
        runtime.getObject().defineConstant("RbConfig", configModule);
        String[] versionParts;
        if (runtime.is1_9()) {
            versionParts = "1.9.2".split("\\.");
        }
        else {
            versionParts = "1.8.7".split("\\.");
        }
        setConfig(configHash, "MAJOR", versionParts[0]);
        setConfig(configHash, "MINOR", versionParts[1]);
        setConfig(configHash, "TEENY", versionParts[2]);
        setConfig(configHash, "ruby_version", versionParts[0] + '.' + versionParts[1]);
        setConfig(configHash, "arch", "universal-java" + System.getProperty("java.specification.version"));
        RbConfigLibrary.normalizedHome = runtime.getJRubyHome();
        if (RbConfigLibrary.normalizedHome == null && Ruby.isSecurityRestricted()) {
            RbConfigLibrary.normalizedHome = "SECURITY RESTRICTED";
        }
        String binDir = SafePropertyAccessor.getProperty("jruby.bindir");
        if (binDir == null) {
            binDir = new NormalizedFile(RbConfigLibrary.normalizedHome, "bin").getPath();
        }
        setConfig(configHash, "bindir", binDir);
        setConfig(configHash, "RUBY_INSTALL_NAME", jrubyScript());
        setConfig(configHash, "ruby_install_name", jrubyScript());
        setConfig(configHash, "SHELL", jrubyShell());
        setConfig(configHash, "prefix", RbConfigLibrary.normalizedHome);
        setConfig(configHash, "exec_prefix", RbConfigLibrary.normalizedHome);
        setConfig(configHash, "host_os", getOSName());
        setConfig(configHash, "host_vendor", System.getProperty("java.vendor"));
        setConfig(configHash, "host_cpu", getArchitecture());
        setConfig(configHash, "target_os", getOSName());
        setConfig(configHash, "target_cpu", getArchitecture());
        String jrubyJarFile = "jruby.jar";
        final URL jrubyPropertiesUrl = Ruby.getClassLoader().getResource("/org/jruby/Ruby.class");
        if (jrubyPropertiesUrl != null) {
            final Pattern jarFile = Pattern.compile("jar:file:.*?([a-zA-Z0-9.\\-]+\\.jar)!/org/jruby/Ruby.class");
            final Matcher jarMatcher = jarFile.matcher(jrubyPropertiesUrl.toString());
            jarMatcher.find();
            if (jarMatcher.matches()) {
                jrubyJarFile = jarMatcher.group(1);
            }
        }
        setConfig(configHash, "LIBRUBY", jrubyJarFile);
        setConfig(configHash, "LIBRUBY_SO", jrubyJarFile);
        setConfig(configHash, "LIBRUBY_SO", jrubyJarFile);
        setConfig(configHash, "LIBRUBY_ALIASES", jrubyJarFile);
        setConfig(configHash, "build", "java1.5");
        setConfig(configHash, "target", "java1.5");
        String libdir = SafePropertyAccessor.getProperty("jruby.lib");
        if (libdir == null) {
            libdir = new NormalizedFile(RbConfigLibrary.normalizedHome, "lib").getPath();
        }
        else {
            try {
                libdir = new NormalizedFile(libdir).getCanonicalPath();
            }
            catch (IOException e) {
                libdir = new NormalizedFile(libdir).getAbsolutePath();
            }
        }
        final String rubyLibDir = new NormalizedFile(libdir, "ruby/1.8").getPath();
        final String siteDir = new NormalizedFile(libdir, "ruby/site_ruby").getPath();
        final String siteLibDir = new NormalizedFile(libdir, "ruby/site_ruby/1.8").getPath();
        final String siteArchDir = new NormalizedFile(libdir, "ruby/site_ruby/1.8/java").getPath();
        final String archDir = new NormalizedFile(libdir, "ruby/1.8/java").getPath();
        final String shareDir = new NormalizedFile(RbConfigLibrary.normalizedHome, "share").getPath();
        final String includeDir = new NormalizedFile(RbConfigLibrary.normalizedHome, "lib/native/" + getOSName()).getPath();
        setConfig(configHash, "libdir", libdir);
        if (runtime.is1_9()) {
            setConfig(configHash, "rubylibprefix", libdir + "/ruby");
        }
        setConfig(configHash, "rubylibdir", rubyLibDir);
        setConfig(configHash, "sitedir", siteDir);
        setConfig(configHash, "sitelibdir", siteLibDir);
        setConfig(configHash, "sitearch", "java");
        setConfig(configHash, "sitearchdir", siteArchDir);
        setConfig(configHash, "archdir", archDir);
        setConfig(configHash, "topdir", archDir);
        setConfig(configHash, "includedir", includeDir);
        setConfig(configHash, "configure_args", "");
        setConfig(configHash, "datadir", shareDir);
        setConfig(configHash, "mandir", new NormalizedFile(RbConfigLibrary.normalizedHome, "man").getPath());
        setConfig(configHash, "sysconfdir", new NormalizedFile(RbConfigLibrary.normalizedHome, "etc").getPath());
        setConfig(configHash, "localstatedir", new NormalizedFile(RbConfigLibrary.normalizedHome, "var").getPath());
        setConfig(configHash, "DLEXT", "jar");
        if (Platform.IS_WINDOWS) {
            setConfig(configHash, "EXEEXT", ".exe");
        }
        else {
            setConfig(configHash, "EXEEXT", "");
        }
        if (runtime.is1_9()) {
            setConfig(configHash, "ridir", new NormalizedFile(shareDir, "ri").getPath());
        }
        final String gemhome = SafePropertyAccessor.getProperty("jruby.gem.home");
        final String gempath = SafePropertyAccessor.getProperty("jruby.gem.path");
        if (gemhome != null) {
            setConfig(configHash, "default_gem_home", gemhome);
        }
        if (gempath != null) {
            setConfig(configHash, "default_gem_path", gempath);
        }
        setConfig(configHash, "joda-time.version", "1.6.2");
        setConfig(configHash, "tzdata.version", "2010k");
        final RubyHash mkmfHash = RubyHash.newHash(runtime);
        setConfig(mkmfHash, "libdir", libdir);
        setConfig(mkmfHash, "arch", "java");
        setConfig(mkmfHash, "rubylibdir", rubyLibDir);
        setConfig(mkmfHash, "sitedir", siteDir);
        setConfig(mkmfHash, "sitelibdir", siteLibDir);
        setConfig(mkmfHash, "sitearch", "java");
        setConfig(mkmfHash, "sitearchdir", siteArchDir);
        setConfig(mkmfHash, "archdir", archDir);
        setConfig(mkmfHash, "topdir", archDir);
        setConfig(mkmfHash, "configure_args", "");
        setConfig(mkmfHash, "datadir", new NormalizedFile(RbConfigLibrary.normalizedHome, "share").getPath());
        setConfig(mkmfHash, "mandir", new NormalizedFile(RbConfigLibrary.normalizedHome, "man").getPath());
        setConfig(mkmfHash, "sysconfdir", new NormalizedFile(RbConfigLibrary.normalizedHome, "etc").getPath());
        setConfig(mkmfHash, "localstatedir", new NormalizedFile(RbConfigLibrary.normalizedHome, "var").getPath());
        setupMakefileConfig(configModule, mkmfHash);
    }
    
    private static void setupMakefileConfig(final RubyModule configModule, final RubyHash mkmfHash) {
        final Ruby ruby = configModule.getRuntime();
        final RubyHash envHash = (RubyHash)ruby.getObject().fastFetchConstant("ENV".intern());
        final String cc = getRubyEnv(envHash, "CC", "cc");
        final String cpp = getRubyEnv(envHash, "CPP", "cc -E");
        final String cxx = getRubyEnv(envHash, "CXX", "c++");
        final String jflags = " -fno-omit-frame-pointer -fno-strict-aliasing ";
        String cflags = jflags + " -fexceptions" + " $(cflags)";
        String cppflags = " $(DEFS) $(cppflags)";
        final String cxxflags = cflags + " $(cxxflags)";
        String ldflags = "";
        String dldflags = "";
        String ldsharedflags = " -shared ";
        String archflags = " -m" + (Platform.IS_64_BIT ? "64" : "32");
        final String hdr_dir = new NormalizedFile(RbConfigLibrary.normalizedHome, "lib/native/include/").getPath();
        if (Platform.IS_WINDOWS) {
            ldflags = ldflags + " -L" + new NormalizedFile(RbConfigLibrary.normalizedHome, "lib/native/" + (Platform.IS_64_BIT ? "x86_64" : "i386") + "-Windows").getPath();
            ldflags += " -ljruby-cext";
            ldsharedflags += " $(if $(filter-out -g -g0,$(debugflags)),,-s)";
            dldflags = "-Wl,--enable-auto-image-base,--enable-auto-import $(DEFFILE)";
            archflags += " -march=native -mtune=native";
            setConfig(mkmfHash, "DLEXT", "dll");
        }
        else if (Platform.IS_MAC) {
            ldsharedflags = " -dynamic -bundle -undefined dynamic_lookup ";
            cflags = " -fPIC -DTARGET_RT_MAC_CFM=0 " + cflags;
            ldflags += " -bundle -framework JavaVM -Wl,-syslibroot,$(SDKROOT) -mmacosx-version-min=10.4 ";
            archflags = " -arch " + Platform.ARCH;
            cppflags = " -D_XOPEN_SOURCE -D_DARWIN_C_SOURCE " + cppflags;
            setConfig(mkmfHash, "DLEXT", "bundle");
        }
        else {
            cflags = " -fPIC " + cflags;
            setConfig(mkmfHash, "DLEXT", "so");
        }
        final String libext = "a";
        final String objext = "o";
        setConfig(mkmfHash, "configure_args", "");
        setConfig(mkmfHash, "CFLAGS", cflags);
        setConfig(mkmfHash, "CPPFLAGS", cppflags);
        setConfig(mkmfHash, "CXXFLAGS", cxxflags);
        setConfig(mkmfHash, "ARCH_FLAG", archflags);
        setConfig(mkmfHash, "LDFLAGS", ldflags);
        setConfig(mkmfHash, "DLDFLAGS", dldflags);
        setConfig(mkmfHash, "DEFS", "");
        setConfig(mkmfHash, "LIBEXT", libext);
        setConfig(mkmfHash, "OBJEXT", objext);
        setConfig(mkmfHash, "LIBRUBYARG_STATIC", "");
        setConfig(mkmfHash, "LIBRUBYARG_SHARED", "");
        setConfig(mkmfHash, "LIBS", "");
        setConfig(mkmfHash, "DLDLIBS", "");
        setConfig(mkmfHash, "ENABLED_SHARED", "");
        setConfig(mkmfHash, "LIBRUBY", "");
        setConfig(mkmfHash, "LIBRUBY_A", "");
        setConfig(mkmfHash, "LIBRUBYARG", "");
        setConfig(mkmfHash, "prefix", " ");
        setConfig(mkmfHash, "ruby_install_name", jrubyScript());
        setConfig(mkmfHash, "LDSHARED", cc + ldsharedflags);
        setConfig(mkmfHash, "LDSHAREDXX", cxx + ldsharedflags);
        setConfig(mkmfHash, "RUBY_PLATFORM", getOSName());
        setConfig(mkmfHash, "CC", cc);
        setConfig(mkmfHash, "CPP", cpp);
        setConfig(mkmfHash, "CXX", cxx);
        setConfig(mkmfHash, "OUTFLAG", "-o ");
        setConfig(mkmfHash, "COMMON_HEADERS", "ruby.h");
        setConfig(mkmfHash, "PATH_SEPARATOR", ":");
        setConfig(mkmfHash, "INSTALL", "install -c ");
        setConfig(mkmfHash, "RM", "rm -f");
        setConfig(mkmfHash, "CP", "cp ");
        setConfig(mkmfHash, "MAKEDIRS", "mkdir -p ");
        setConfig(mkmfHash, "includedir", hdr_dir);
        setConfig(mkmfHash, "rubyhdrdir", hdr_dir);
        setConfig(mkmfHash, "archdir", hdr_dir);
        ruby.getObject().defineConstant("CROSS_COMPILING", ruby.getNil());
        configModule.defineConstant("MAKEFILE_CONFIG", mkmfHash);
    }
    
    private static void setConfig(final RubyHash hash, final String key, final String value) {
        final Ruby runtime = hash.getRuntime();
        hash.op_aset(runtime.getCurrentContext(), runtime.newString(key), runtime.newString(value));
    }
    
    public static String jrubyScript() {
        return SafePropertyAccessor.getProperty("jruby.script", "jruby").replace('\\', '/');
    }
    
    public static String jrubyShell() {
        return SafePropertyAccessor.getProperty("jruby.shell", Platform.IS_WINDOWS ? "cmd.exe" : "/bin/sh").replace('\\', '/');
    }
    
    @JRubyMethod(name = { "ruby" }, module = true, compat = CompatVersion.RUBY1_9)
    public static IRubyObject ruby(final ThreadContext context, final IRubyObject recv) {
        final Ruby runtime = context.getRuntime();
        final RubyHash configHash = (RubyHash)runtime.getModule("Config").getConstant("CONFIG");
        final IRubyObject bindir = configHash.op_aref(context, runtime.newString("bindir"));
        final IRubyObject ruby_install_name = configHash.op_aref(context, runtime.newString("ruby_install_name"));
        final IRubyObject exeext = configHash.op_aref(context, runtime.newString("EXEEXT"));
        return RuntimeHelpers.invoke(context, runtime.getClass("File"), "join", bindir, ruby_install_name.callMethod(context, "+", exeext));
    }
    
    private static String getRubyEnv(final RubyHash envHash, String var, final String default_value) {
        var = (String)envHash.get(var);
        return (var == null) ? default_value : var;
    }
    
    static {
        (RUBY_OS_NAMES = new HashMap<String, String>()).put("Mac OS X", "darwin");
        RbConfigLibrary.RUBY_OS_NAMES.put("Darwin", "darwin");
        RbConfigLibrary.RUBY_OS_NAMES.put("Linux", "linux");
        RbConfigLibrary.RUBY_OS_NAMES.put("Windows 95", "mswin32");
        RbConfigLibrary.RUBY_OS_NAMES.put("Windows 98", "mswin32");
        RbConfigLibrary.RUBY_OS_NAMES.put("Windows Me", "mswin32");
        RbConfigLibrary.RUBY_OS_NAMES.put("Windows NT", "mswin32");
        RbConfigLibrary.RUBY_OS_NAMES.put("Windows 2000", "mswin32");
        RbConfigLibrary.RUBY_OS_NAMES.put("Windows NT (unknown)", "mswin32");
        RbConfigLibrary.RUBY_OS_NAMES.put("Windows XP", "mswin32");
        RbConfigLibrary.RUBY_OS_NAMES.put("Windows 2003", "mswin32");
        RbConfigLibrary.RUBY_OS_NAMES.put("Windows Vista", "mswin32");
        RbConfigLibrary.RUBY_OS_NAMES.put("Windows 7", "mswin32");
        RbConfigLibrary.RUBY_OS_NAMES.put("Windows Server 2008", "mswin32");
        RbConfigLibrary.RUBY_OS_NAMES.put("Solaris", "solaris");
        RbConfigLibrary.RUBY_OS_NAMES.put("FreeBSD", "freebsd");
        RbConfigLibrary.RUBY_OS_NAMES.put("AIX", "aix");
    }
}
