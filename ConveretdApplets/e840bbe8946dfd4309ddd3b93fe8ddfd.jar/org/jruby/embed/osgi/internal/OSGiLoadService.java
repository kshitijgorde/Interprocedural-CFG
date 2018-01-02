// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.embed.osgi.internal;

import org.jruby.runtime.load.Library;
import java.net.URL;
import org.osgi.framework.Bundle;
import org.jruby.embed.osgi.utils.OSGiFileLocator;
import java.util.StringTokenizer;
import org.jruby.runtime.load.LoadServiceResource;
import org.jruby.Ruby;
import org.jruby.RubyInstanceConfig;
import org.jruby.runtime.load.LoadService;

public class OSGiLoadService extends LoadService
{
    public static final String OSGI_BUNDLE_CLASSPATH_SCHEME = "osgibundle:/";
    public static RubyInstanceConfig.LoadServiceCreator OSGI_DEFAULT;
    
    public OSGiLoadService(final Ruby runtime) {
        super(runtime);
    }
    
    protected LoadServiceResource findFileInClasspath(String name) {
        if (name.startsWith("osgibundle:/")) {
            name = this.cleanupFindName(name);
            final StringTokenizer tokenizer = new StringTokenizer(name, "/", false);
            tokenizer.nextToken();
            final String symname = tokenizer.nextToken();
            final StringBuilder sb = new StringBuilder();
            if (!tokenizer.hasMoreTokens()) {
                sb.append('/');
            }
            else {
                while (tokenizer.hasMoreTokens()) {
                    sb.append('/');
                    sb.append(tokenizer.nextToken());
                }
            }
            final Bundle bundle = OSGiFileLocator.getBundle(symname);
            if (bundle != null) {
                final URL url = bundle.getEntry(sb.toString());
                if (url != null) {
                    return new LoadServiceResource(OSGiFileLocator.getLocalURL(url), name);
                }
            }
        }
        return super.findFileInClasspath(name);
    }
    
    protected Library createLibrary(final SearchState state, final LoadServiceResource resource) {
        if (resource == null) {
            return null;
        }
        String file = state.loadName;
        if (file.startsWith("osgibundle:/")) {
            file = this.cleanupFindName(file);
            final StringTokenizer tokenizer = new StringTokenizer(file, "/", false);
            tokenizer.nextToken();
            final String symname = tokenizer.nextToken();
            final Bundle bundle = OSGiFileLocator.getBundle(symname);
            if (bundle != null) {
                return new OSGiBundleLibrary(bundle);
            }
        }
        return super.createLibrary(state, resource);
    }
    
    private String cleanupFindName(final String name) {
        if (name.endsWith(".jar")) {
            return name.substring(0, name.length() - ".jar".length());
        }
        if (name.endsWith(".class")) {
            return name.substring(0, name.length() - ".class".length());
        }
        return name;
    }
    
    static {
        OSGiLoadService.OSGI_DEFAULT = new RubyInstanceConfig.LoadServiceCreator() {
            public LoadService create(final Ruby runtime) {
                if (runtime.is1_9()) {
                    return new OSGiLoadService19(runtime);
                }
                return new OSGiLoadService(runtime);
            }
        };
    }
}
