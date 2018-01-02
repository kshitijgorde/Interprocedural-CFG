// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Iterator;
import java.util.Map;
import org.jruby.RubyHash;
import java.io.File;
import org.jruby.common.IRubyWarnings;
import com.kenai.constantine.platform.Errno;
import org.jruby.Ruby;
import org.jruby.ext.posix.POSIXHandler;

public class JRubyPOSIXHandler implements POSIXHandler
{
    private final Ruby runtime;
    private final boolean isVerbose;
    
    public JRubyPOSIXHandler(final Ruby runtime) {
        this.runtime = runtime;
        boolean verbose = false;
        try {
            verbose = Boolean.getBoolean("jruby.native.verbose");
        }
        catch (SecurityException ex) {}
        this.isVerbose = verbose;
    }
    
    public void error(final Errno error, final String extraData) {
        throw this.runtime.newErrnoFromInt(error.value(), extraData);
    }
    
    public void unimplementedError(final String method) {
        throw this.runtime.newNotImplementedError(method + " unsupported on this platform");
    }
    
    public void warn(final WARNING_ID id, final String message, final Object... data) {
        IRubyWarnings.ID ourID;
        if (id == WARNING_ID.DUMMY_VALUE_USED) {
            ourID = IRubyWarnings.ID.DUMMY_VALUE_USED;
        }
        else {
            ourID = IRubyWarnings.ID.MISCELLANEOUS;
        }
        this.runtime.getWarnings().warn(ourID, message, data);
    }
    
    public boolean isVerbose() {
        return this.isVerbose;
    }
    
    public File getCurrentWorkingDirectory() {
        return new File(this.runtime.getCurrentDirectory());
    }
    
    public String[] getEnv() {
        final RubyHash hash = (RubyHash)this.runtime.getObject().fastGetConstant("ENV");
        int i = 0;
        final String[] env = new String[hash.size()];
        for (final Map.Entry<Object, Object> entry : hash.directEntrySet()) {
            env[i] = entry.getKey().toString() + "=" + entry.getValue().toString();
            ++i;
        }
        return env;
    }
    
    public PrintStream getErrorStream() {
        return this.runtime.getErrorStream();
    }
    
    public InputStream getInputStream() {
        return this.runtime.getInputStream();
    }
    
    public int getPID() {
        return this.runtime.hashCode();
    }
    
    public PrintStream getOutputStream() {
        return this.runtime.getOutputStream();
    }
}
