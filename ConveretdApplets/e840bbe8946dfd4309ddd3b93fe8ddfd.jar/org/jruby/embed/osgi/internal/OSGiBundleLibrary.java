// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.embed.osgi.internal;

import org.jruby.util.JRubyClassLoader;
import org.jruby.Ruby;
import org.osgi.framework.Bundle;
import org.jruby.runtime.load.Library;

public class OSGiBundleLibrary implements Library
{
    private final Bundle bundle;
    
    public OSGiBundleLibrary(final Bundle bundle) {
        this.bundle = bundle;
    }
    
    public void load(final Ruby runtime, final boolean wrap) {
        final JRubyClassLoader jrubycl = runtime.getJRubyClassLoader();
        final ClassLoader cl = jrubycl.getParent();
        if (cl instanceof JRubyOSGiBundleClassLoader) {
            ((JRubyOSGiBundleClassLoader)cl).addBundle(this.bundle);
            return;
        }
        throw new IllegalArgumentException("osgi libraries are only supported with a JRubyOSGiBundleClassLoader as the  loader of the ScriptingContainer.");
    }
}
