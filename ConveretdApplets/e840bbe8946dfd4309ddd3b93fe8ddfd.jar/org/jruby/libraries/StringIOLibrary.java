// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.libraries;

import java.io.IOException;
import org.jruby.RubyStringIO;
import org.jruby.Ruby;
import org.jruby.runtime.load.Library;

public class StringIOLibrary implements Library
{
    public void load(final Ruby runtime, final boolean wrap) throws IOException {
        RubyStringIO.createStringIOClass(runtime);
    }
}
