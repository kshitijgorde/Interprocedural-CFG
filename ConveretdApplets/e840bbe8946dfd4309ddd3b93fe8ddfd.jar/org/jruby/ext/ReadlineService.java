// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext;

import java.io.IOException;
import org.jruby.Ruby;
import org.jruby.runtime.load.Library;

public class ReadlineService implements Library
{
    public void load(final Ruby runtime, final boolean wrap) throws IOException {
        Readline.createReadline(runtime);
    }
}
