// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.mathn;

import org.jruby.RubyRational;
import org.jruby.Ruby;
import org.jruby.runtime.load.Library;

public class Rational implements Library
{
    public void load(final Ruby runtime, final boolean wrap) {
        RubyRational.setCanonicalization(true);
    }
}
