// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.rubinius;

import java.io.IOException;
import org.jruby.Ruby;
import org.jruby.runtime.load.Library;

public class RubiniusLibrary implements Library
{
    public void load(final Ruby runtime, final boolean wrap) throws IOException {
        runtime.getOrCreateModule("Rubinius");
        RubyTuple.createTupleClass(runtime);
    }
}
