// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.libraries;

import java.io.IOException;
import org.jruby.RubyEnumerator;
import org.jruby.Ruby;
import org.jruby.runtime.load.Library;

public class EnumeratorLibrary implements Library
{
    public void load(final Ruby runtime, final boolean wrap) throws IOException {
        RubyEnumerator.defineEnumerator(runtime);
    }
}
