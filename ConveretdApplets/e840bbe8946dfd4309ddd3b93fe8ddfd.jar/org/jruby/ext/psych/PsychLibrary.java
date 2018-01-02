// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.psych;

import org.jruby.RubyModule;
import org.jruby.Ruby;
import org.jruby.runtime.load.Library;

public class PsychLibrary implements Library
{
    public void load(final Ruby runtime, final boolean wrap) {
        final RubyModule psych = runtime.defineModule("Psych");
        PsychParser.initPsychParser(runtime, psych);
        PsychEmitter.initPsychEmitter(runtime, psych);
        PsychToRuby.initPsychToRuby(runtime, psych);
        PsychYamlTree.initPsychYamlTree(runtime, psych);
    }
}
