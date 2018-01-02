// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.management;

import org.jruby.Ruby;
import java.lang.ref.SoftReference;

public class ClassCache implements ClassCacheMBean
{
    private final SoftReference<Ruby> ruby;
    
    public ClassCache(final Ruby ruby) {
        this.ruby = new SoftReference<Ruby>(ruby);
    }
    
    public boolean isFull() {
        return this.ruby.get().getInstanceConfig().getClassCache().isFull();
    }
    
    public int getClassLoadCount() {
        return this.ruby.get().getInstanceConfig().getClassCache().getClassLoadCount();
    }
    
    public int getLiveClassCount() {
        return this.ruby.get().getInstanceConfig().getClassCache().getLiveClassCount();
    }
    
    public int getClassReuseCount() {
        return this.ruby.get().getInstanceConfig().getClassCache().getClassReuseCount();
    }
    
    public void flush() {
        this.ruby.get().getInstanceConfig().getClassCache().flush();
    }
}
