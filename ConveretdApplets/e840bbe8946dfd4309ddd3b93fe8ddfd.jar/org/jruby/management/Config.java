// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.management;

import org.jruby.RubyInstanceConfig;
import java.util.Arrays;
import org.jruby.Ruby;
import java.lang.ref.SoftReference;

public class Config implements ConfigMBean
{
    private final SoftReference<Ruby> ruby;
    
    public Config(final Ruby ruby) {
        this.ruby = new SoftReference<Ruby>(ruby);
    }
    
    public String getVersionString() {
        return this.ruby.get().getInstanceConfig().getVersionString();
    }
    
    public String getCopyrightString() {
        return this.ruby.get().getInstanceConfig().getCopyrightString();
    }
    
    public String getCompileMode() {
        return this.ruby.get().getInstanceConfig().getCompileMode().name();
    }
    
    public boolean isJitLogging() {
        return this.ruby.get().getInstanceConfig().isJitLogging();
    }
    
    public boolean isJitLoggingVerbose() {
        return this.ruby.get().getInstanceConfig().isJitLoggingVerbose();
    }
    
    public int getJitLogEvery() {
        return this.ruby.get().getInstanceConfig().getJitLogEvery();
    }
    
    public boolean isSamplingEnabled() {
        return this.ruby.get().getInstanceConfig().isSamplingEnabled();
    }
    
    public int getJitThreshold() {
        return this.ruby.get().getInstanceConfig().getJitThreshold();
    }
    
    public int getJitMax() {
        return this.ruby.get().getInstanceConfig().getJitMax();
    }
    
    public int getJitMaxSize() {
        return this.ruby.get().getInstanceConfig().getJitMaxSize();
    }
    
    public boolean isRunRubyInProcess() {
        return this.ruby.get().getInstanceConfig().isRunRubyInProcess();
    }
    
    public String getCompatVersion() {
        return this.ruby.get().getInstanceConfig().getCompatVersion().name();
    }
    
    public String getCurrentDirectory() {
        return this.ruby.get().getInstanceConfig().getCurrentDirectory();
    }
    
    public boolean isObjectSpaceEnabled() {
        return this.ruby.get().getInstanceConfig().isObjectSpaceEnabled();
    }
    
    public String getEnvironment() {
        return this.ruby.get().getInstanceConfig().getEnvironment().toString();
    }
    
    public String getArgv() {
        return Arrays.deepToString(this.ruby.get().getInstanceConfig().getArgv());
    }
    
    public String getJRubyHome() {
        return this.ruby.get().getInstanceConfig().getJRubyHome();
    }
    
    public String getRequiredLibraries() {
        return this.ruby.get().getInstanceConfig().requiredLibraries().toString();
    }
    
    public String getLoadPaths() {
        return this.ruby.get().getInstanceConfig().loadPaths().toString();
    }
    
    public String getDisplayedFileName() {
        return this.ruby.get().getInstanceConfig().displayedFileName();
    }
    
    public String getScriptFileName() {
        return this.ruby.get().getInstanceConfig().getScriptFileName();
    }
    
    public boolean isBenchmarking() {
        return this.ruby.get().getInstanceConfig().isBenchmarking();
    }
    
    public boolean isAssumeLoop() {
        return this.ruby.get().getInstanceConfig().isAssumeLoop();
    }
    
    public boolean isAssumePrinting() {
        return this.ruby.get().getInstanceConfig().isAssumePrinting();
    }
    
    public boolean isProcessLineEnds() {
        return this.ruby.get().getInstanceConfig().isProcessLineEnds();
    }
    
    public boolean isSplit() {
        return this.ruby.get().getInstanceConfig().isSplit();
    }
    
    public boolean isVerbose() {
        return this.ruby.get().getInstanceConfig().isVerbose();
    }
    
    public boolean isDebug() {
        return this.ruby.get().getInstanceConfig().isDebug();
    }
    
    public String getInputFieldSeparator() {
        return this.ruby.get().getInstanceConfig().getInputFieldSeparator();
    }
    
    public String getKCode() {
        return this.ruby.get().getInstanceConfig().getKCode().name();
    }
    
    public String getRecordSeparator() {
        return this.ruby.get().getInstanceConfig().getRecordSeparator();
    }
    
    public int getSafeLevel() {
        return this.ruby.get().getInstanceConfig().getSafeLevel();
    }
    
    public String getOptionGlobals() {
        return this.ruby.get().getInstanceConfig().getOptionGlobals().toString();
    }
    
    public boolean isManagementEnabled() {
        return this.ruby.get().getInstanceConfig().isManagementEnabled();
    }
    
    public boolean isFullTraceEnabled() {
        return RubyInstanceConfig.FULL_TRACE_ENABLED;
    }
    
    public boolean isLazyHandlesEnabled() {
        return RubyInstanceConfig.LAZYHANDLES_COMPILE;
    }
    
    public boolean isShowBytecode() {
        return this.ruby.get().getInstanceConfig().isShowBytecode();
    }
    
    public String getExcludedMethods() {
        return this.ruby.get().getInstanceConfig().getExcludedMethods().toString();
    }
}
