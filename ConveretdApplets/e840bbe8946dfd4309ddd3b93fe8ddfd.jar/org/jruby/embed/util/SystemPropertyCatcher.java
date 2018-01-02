// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.embed.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.net.URL;
import org.jruby.util.URLUtil;
import org.jruby.embed.internal.LocalContextProvider;
import org.jruby.CompatVersion;
import org.jruby.RubyInstanceConfig;
import org.jruby.embed.ScriptingContainer;
import org.jruby.embed.LocalVariableBehavior;
import org.jruby.embed.PropertyName;
import org.jruby.embed.LocalContextScope;

public class SystemPropertyCatcher
{
    public static LocalContextScope getScope(final LocalContextScope defaultScope) {
        final String s = System.getProperty(PropertyName.LOCALCONTEXT_SCOPE.toString());
        if (s == null) {
            return defaultScope;
        }
        if ("singlethread".equalsIgnoreCase(s)) {
            return LocalContextScope.SINGLETHREAD;
        }
        if ("singleton".equalsIgnoreCase(s)) {
            return LocalContextScope.SINGLETON;
        }
        if ("threadsafe".equalsIgnoreCase(s)) {
            return LocalContextScope.THREADSAFE;
        }
        return defaultScope;
    }
    
    public static LocalVariableBehavior getBehavior(final LocalVariableBehavior defaultBehavior) {
        final String s = System.getProperty(PropertyName.LOCALVARIABLE_BEHAVIOR.toString());
        if (s == null) {
            return defaultBehavior;
        }
        if ("global".equalsIgnoreCase(s)) {
            return LocalVariableBehavior.GLOBAL;
        }
        if ("persistent".equalsIgnoreCase(s)) {
            return LocalVariableBehavior.PERSISTENT;
        }
        if ("transient".equalsIgnoreCase(s)) {
            return LocalVariableBehavior.TRANSIENT;
        }
        if ("bsf".equalsIgnoreCase(s)) {
            return LocalVariableBehavior.BSF;
        }
        return defaultBehavior;
    }
    
    public static boolean isLazy(final boolean defaultLaziness) {
        final String s = System.getProperty(PropertyName.LAZINESS.toString());
        if (s == null) {
            return defaultLaziness;
        }
        return Boolean.parseBoolean(s);
    }
    
    public static void setClassLoader(final ScriptingContainer container) {
        final String s = System.getProperty(PropertyName.CLASSLOADER.toString());
        if (s == null || "container".equals(s) || "current".equals(s)) {
            container.setClassLoader(container.getClass().getClassLoader());
            return;
        }
        if ("context".equals(s)) {
            container.setClassLoader(Thread.currentThread().getContextClassLoader());
            return;
        }
        if ("none".equals(s)) {
            return;
        }
    }
    
    public static void setConfiguration(final ScriptingContainer container) {
        final LocalContextProvider provider = container.getProvider();
        final RubyInstanceConfig config = provider.getRubyInstanceConfig();
        String s = System.getProperty(PropertyName.COMPILEMODE.toString());
        if (s != null) {
            if ("jit".equalsIgnoreCase(s)) {
                config.setCompileMode(RubyInstanceConfig.CompileMode.JIT);
            }
            else if ("force".equalsIgnoreCase(s)) {
                config.setCompileMode(RubyInstanceConfig.CompileMode.FORCE);
            }
            else {
                config.setCompileMode(RubyInstanceConfig.CompileMode.OFF);
            }
        }
        s = System.getProperty(PropertyName.COMPATVERSION.toString());
        if (s != null && isRuby19(s)) {
            config.setCompatVersion(CompatVersion.RUBY1_9);
        }
    }
    
    @Deprecated
    public static void setJRubyHome(final ScriptingContainer container) {
        final String jrubyhome = findJRubyHome(container);
        if (jrubyhome != null) {
            container.getProvider().getRubyInstanceConfig().setJRubyHome(jrubyhome);
        }
    }
    
    public static String findJRubyHome(final Object instance) {
        String jrubyhome;
        if ((jrubyhome = System.getenv("JRUBY_HOME")) != null) {
            return jrubyhome;
        }
        if ((jrubyhome = System.getProperty("jruby.home")) != null) {
            return jrubyhome;
        }
        if ((jrubyhome = findFromJar(instance)) != null) {
            return jrubyhome;
        }
        return null;
    }
    
    public static String findFromJar(final Object instance) {
        final URL resource = instance.getClass().getResource("/META-INF/jruby.home");
        if (resource == null) {
            return null;
        }
        String location = null;
        if (resource.getProtocol().equals("jar")) {
            location = URLUtil.getPath(resource);
            if (!location.startsWith("file:")) {
                location = "classpath:/META-INF/jruby.home";
            }
        }
        else {
            location = "classpath:/META-INF/jruby.home";
        }
        if (location.endsWith("/")) {
            location = location.substring(0, location.length() - 1);
        }
        return location;
    }
    
    public static List<String> findLoadPaths() {
        String paths = System.getProperty(PropertyName.CLASSPATH.toString());
        final List<String> loadPaths = new ArrayList<String>();
        if (paths == null) {
            paths = System.getProperty("java.class.path");
        }
        if (paths == null) {
            return loadPaths;
        }
        final String[] possiblePaths = paths.split(File.pathSeparator);
        final String[] prefixes = { "file", "url" };
        for (int i = 0; i < possiblePaths.length; ++i) {
            final int startIndex = i;
            for (int j = 0; j < prefixes.length; ++j) {
                if (prefixes[j].equals(possiblePaths[i]) && i < possiblePaths.length - 1) {
                    loadPaths.add(possiblePaths[i] + ":" + possiblePaths[++i]);
                    break;
                }
            }
            if (startIndex == i) {
                loadPaths.add(possiblePaths[i]);
            }
        }
        return loadPaths;
    }
    
    public static boolean isRuby19(final String name) {
        final String n = name.toLowerCase();
        final Pattern p = Pattern.compile("j?ruby1[\\._]?9");
        final Matcher m = p.matcher(n);
        return m.matches();
    }
    
    public static String getBaseDir() {
        String baseDir = System.getenv("PWD");
        if (baseDir == null || "/".equals(baseDir)) {
            baseDir = System.getProperty("user.dir");
        }
        return baseDir;
    }
}
