// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.runtime.load;

import org.jruby.ast.executable.Script;
import java.io.IOException;
import org.jruby.Ruby;

public class JavaCompiledScript implements Library
{
    private final LoadServiceResource resource;
    
    public JavaCompiledScript(final LoadServiceResource resource) {
        this.resource = resource;
    }
    
    public void load(final Ruby runtime, final boolean wrap) {
        try {
            final Script script = CompiledScriptLoader.loadScriptFromFile(runtime, this.resource.getInputStream(), this.resource.getName());
            if (script == null) {
                return;
            }
            script.setFilename(this.resource.getName());
            runtime.loadScript(script);
        }
        catch (IOException e) {
            throw runtime.newIOErrorFromException(e);
        }
    }
}
