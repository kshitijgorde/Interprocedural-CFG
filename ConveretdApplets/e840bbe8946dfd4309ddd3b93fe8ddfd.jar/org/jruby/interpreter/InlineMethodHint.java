// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.interpreter;

import org.jruby.compiler.ir.IRMethod;

public class InlineMethodHint extends RuntimeException
{
    public final IRMethod inlineableMethod;
    
    public InlineMethodHint(final IRMethod m) {
        this.inlineableMethod = m;
    }
}
