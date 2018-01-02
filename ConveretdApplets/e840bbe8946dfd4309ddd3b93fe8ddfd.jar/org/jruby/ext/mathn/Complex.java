// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.mathn;

import org.jruby.RubyComplex;
import org.jruby.Ruby;
import org.jruby.runtime.load.Library;

public class Complex implements Library
{
    public void load(final Ruby runtime, final boolean wrap) {
        RubyComplex.setCanonicalization(true);
    }
}
