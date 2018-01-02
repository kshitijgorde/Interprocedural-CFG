// 
// Decompiled by Procyon v0.5.30
// 

package org.yecht.ruby;

import org.jruby.runtime.builtin.IRubyObject;

class ObjectStorageLink extends StorageLink
{
    final IRubyObject obj;
    final String ivarName;
    final IRubyObject originalObject;
    
    public ObjectStorageLink(final IRubyObject obj, final String ivarName, final IRubyObject originalObject) {
        this.obj = obj;
        this.ivarName = ivarName;
        this.originalObject = originalObject;
    }
    
    public void replaceLinkWith(final IRubyObject newObject) {
        this.obj.getInstanceVariables().setInstanceVariable(this.ivarName, newObject);
    }
}
