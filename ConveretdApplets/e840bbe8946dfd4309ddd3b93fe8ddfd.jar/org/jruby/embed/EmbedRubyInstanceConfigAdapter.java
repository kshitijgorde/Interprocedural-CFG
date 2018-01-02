// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.embed;

import org.jruby.util.KCode;
import org.jruby.Profile;
import org.jruby.util.ClassCache;
import java.util.Map;
import org.jruby.CompatVersion;
import org.jruby.RubyInstanceConfig;
import java.io.Writer;
import java.io.PrintStream;
import java.io.Reader;
import java.io.InputStream;
import java.util.List;

public interface EmbedRubyInstanceConfigAdapter
{
    List<String> getLoadPaths();
    
    void setLoadPaths(final List<String> p0);
    
    InputStream getInput();
    
    void setInput(final InputStream p0);
    
    void setInput(final Reader p0);
    
    PrintStream getOutput();
    
    void setOutput(final PrintStream p0);
    
    void setOutput(final Writer p0);
    
    PrintStream getError();
    
    void setError(final PrintStream p0);
    
    void setError(final Writer p0);
    
    RubyInstanceConfig.CompileMode getCompileMode();
    
    void setCompileMode(final RubyInstanceConfig.CompileMode p0);
    
    boolean isRunRubyInProcess();
    
    void setRunRubyInProcess(final boolean p0);
    
    CompatVersion getCompatVersion();
    
    void setCompatVersion(final CompatVersion p0);
    
    boolean isObjectSpaceEnabled();
    
    void setObjectSpaceEnabled(final boolean p0);
    
    Map getEnvironment();
    
    void setEnvironment(final Map p0);
    
    String getCurrentDirectory();
    
    void setCurrentDirectory(final String p0);
    
    String getHomeDirectory();
    
    void setHomeDirectory(final String p0);
    
    ClassCache getClassCache();
    
    void setClassCache(final ClassCache p0);
    
    ClassLoader getClassLoader();
    
    void setClassLoader(final ClassLoader p0);
    
    Profile getProfile();
    
    void setProfile(final Profile p0);
    
    RubyInstanceConfig.LoadServiceCreator getLoadServiceCreator();
    
    void setLoadServiceCreator(final RubyInstanceConfig.LoadServiceCreator p0);
    
    String[] getArgv();
    
    void setArgv(final String[] p0);
    
    String getScriptFilename();
    
    void setScriptFilename(final String p0);
    
    String getRecordSeparator();
    
    void setRecordSeparator(final String p0);
    
    KCode getKCode();
    
    void setKCode(final KCode p0);
    
    int getJitLogEvery();
    
    void setJitLogEvery(final int p0);
    
    int getJitThreshold();
    
    void setJitThreshold(final int p0);
    
    int getJitMax();
    
    void setJitMax(final int p0);
    
    int getJitMaxSize();
    
    void setJitMaxSize(final int p0);
    
    String getSupportedRubyVersion();
}
