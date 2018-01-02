// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.runtime.load;

import java.io.File;
import java.io.InputStream;
import java.io.IOException;
import org.jruby.RubyFile;
import org.jruby.util.JRubyFile;
import org.jruby.Ruby;

public class ExternalScript implements Library
{
    private final LoadServiceResource resource;
    
    public ExternalScript(final LoadServiceResource resource, final String name) {
        this.resource = resource;
    }
    
    public void load(final Ruby runtime, final boolean wrap) {
        InputStream in = null;
        try {
            in = this.resource.getInputStream();
            String name = JRubyFile.normalizeSeps(this.resource.getName());
            if (runtime.getInstanceConfig().getCompileMode().shouldPrecompileAll()) {
                runtime.compileAndLoadFile(name, in, wrap);
            }
            else {
                final File path = this.resource.getPath();
                if (path != null && !this.resource.isAbsolute()) {
                    name = JRubyFile.normalizeSeps(RubyFile.canonicalize(path.getPath()));
                }
                runtime.loadFile(name, in, wrap);
            }
        }
        catch (IOException e) {
            throw runtime.newIOErrorFromException(e);
        }
        finally {
            try {
                in.close();
            }
            catch (Exception ex) {}
        }
    }
    
    public String toString() {
        return "ExternalScript: " + this.resource.getName();
    }
}
