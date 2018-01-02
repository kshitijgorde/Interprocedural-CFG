// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.runtime.load;

import org.jruby.util.JRubyFile;
import org.jruby.platform.Platform;
import org.jruby.Ruby;

public class LoadService19 extends LoadService
{
    public LoadService19(final Ruby runtime) {
        super(runtime);
    }
    
    protected String resolveLoadName(final LoadServiceResource foundResource, final String previousPath) {
        String path = foundResource.getAbsolutePath();
        if (Platform.IS_WINDOWS) {
            path = path.replace('\\', '/');
        }
        return path;
    }
    
    protected String getFileName(final JRubyFile file, final String nameWithSuffix) {
        return file.getAbsolutePath();
    }
}
