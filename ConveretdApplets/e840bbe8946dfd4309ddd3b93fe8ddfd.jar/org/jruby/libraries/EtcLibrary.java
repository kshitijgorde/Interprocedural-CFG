// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.libraries;

import java.io.IOException;
import org.jruby.RubyEtc;
import org.jruby.Ruby;
import org.jruby.runtime.load.Library;

public class EtcLibrary implements Library
{
    public void load(final Ruby runtime, final boolean wrap) throws IOException {
        RubyEtc.createEtcModule(runtime);
    }
}