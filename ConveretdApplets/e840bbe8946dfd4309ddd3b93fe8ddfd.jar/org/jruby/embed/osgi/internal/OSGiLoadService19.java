// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.embed.osgi.internal;

import org.jruby.platform.Platform;
import org.jruby.runtime.load.LoadServiceResource;
import org.jruby.Ruby;

class OSGiLoadService19 extends OSGiLoadService
{
    public OSGiLoadService19(final Ruby runtime) {
        super(runtime);
    }
    
    protected String resolveLoadName(final LoadServiceResource foundResource, final String previousPath) {
        String path = foundResource.getAbsolutePath();
        if (Platform.IS_WINDOWS) {
            path = path.replace('\\', '/');
        }
        return path;
    }
}
