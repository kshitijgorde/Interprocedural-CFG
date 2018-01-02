// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.management;

import org.jruby.Ruby;
import java.lang.ref.SoftReference;

public class Runtime implements RuntimeMBean
{
    private final SoftReference<Ruby> ruby;
    
    public Runtime(final Ruby ruby) {
        this.ruby = new SoftReference<Ruby>(ruby);
    }
    
    public int getExceptionCount() {
        return this.ruby.get().getExceptionCount();
    }
    
    public int getBacktraceCount() {
        return this.ruby.get().getBacktraceCount();
    }
    
    public int getCallerCount() {
        return this.ruby.get().getCallerCount();
    }
}
