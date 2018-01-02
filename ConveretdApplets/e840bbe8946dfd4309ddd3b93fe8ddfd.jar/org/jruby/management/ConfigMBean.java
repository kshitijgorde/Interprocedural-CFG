// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.management;

public interface ConfigMBean
{
    String getVersionString();
    
    String getCopyrightString();
    
    String getCompileMode();
    
    boolean isJitLogging();
    
    boolean isJitLoggingVerbose();
    
    int getJitLogEvery();
    
    boolean isSamplingEnabled();
    
    int getJitThreshold();
    
    int getJitMax();
    
    int getJitMaxSize();
    
    boolean isRunRubyInProcess();
    
    String getCompatVersion();
    
    String getCurrentDirectory();
    
    boolean isObjectSpaceEnabled();
    
    String getEnvironment();
    
    String getArgv();
    
    String getJRubyHome();
    
    String getRequiredLibraries();
    
    String getLoadPaths();
    
    String getDisplayedFileName();
    
    String getScriptFileName();
    
    boolean isBenchmarking();
    
    boolean isAssumeLoop();
    
    boolean isAssumePrinting();
    
    boolean isProcessLineEnds();
    
    boolean isSplit();
    
    boolean isVerbose();
    
    boolean isDebug();
    
    String getInputFieldSeparator();
    
    String getKCode();
    
    String getRecordSeparator();
    
    int getSafeLevel();
    
    String getOptionGlobals();
    
    boolean isManagementEnabled();
    
    boolean isFullTraceEnabled();
    
    boolean isLazyHandlesEnabled();
    
    boolean isShowBytecode();
    
    String getExcludedMethods();
}
