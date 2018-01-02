// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.ffi;

import java.io.IOException;
import org.jruby.RubyModule;
import org.jruby.RubyInstanceConfig;
import org.jruby.Ruby;
import org.jruby.runtime.load.Library;

public class FFIService implements Library
{
    public void load(final Ruby runtime, final boolean wrap) throws IOException {
        if (!RubyInstanceConfig.nativeEnabled) {
            throw runtime.newLoadError("Native API access is disabled");
        }
        if (!Platform.getPlatform().isSupported()) {
            throw runtime.newLoadError("Unsupported platform: " + Platform.getPlatform().getName());
        }
        final RubyModule ffi = runtime.defineModule("FFI");
        try {
            Factory.getInstance().init(runtime, ffi);
        }
        catch (Exception e) {
            throw runtime.newLoadError("Could not load FFI Provider: " + e.getLocalizedMessage() + " See http://jira.codehaus.org/browse/JRUBY-4583");
        }
    }
}
