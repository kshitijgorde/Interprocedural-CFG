// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.runtime.builtin.IRubyObject;
import java.lang.ref.SoftReference;

public final class MetaClass extends RubyClass
{
    private static final SoftReference<IRubyObject> NULL_SOFT_REF;
    private SoftReference<IRubyObject> attached;
    
    public MetaClass(final Ruby runtime, final RubyClass superClass, final IRubyObject attached) {
        super(runtime, superClass, false);
        this.attached = MetaClass.NULL_SOFT_REF;
        this.attached = new SoftReference<IRubyObject>(attached);
        this.index = superClass.index;
    }
    
    public boolean isSingleton() {
        return true;
    }
    
    public RubyClass getRealClass() {
        return this.superClass.getRealClass();
    }
    
    public final IRubyObject allocate() {
        throw this.runtime.newTypeError("can't create instance of virtual class");
    }
    
    public IRubyObject getAttached() {
        return this.attached.get();
    }
    
    public void setAttached(final IRubyObject attached) {
        this.attached = new SoftReference<IRubyObject>(attached);
    }
    
    static {
        NULL_SOFT_REF = new SoftReference<IRubyObject>(null);
    }
}
