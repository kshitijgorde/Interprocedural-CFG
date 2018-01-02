// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.org.objectweb.asm.commons;

import org.jruby.org.objectweb.asm.Label;

public interface TableSwitchGenerator
{
    void generateCase(final int p0, final Label p1);
    
    void generateDefault();
}
