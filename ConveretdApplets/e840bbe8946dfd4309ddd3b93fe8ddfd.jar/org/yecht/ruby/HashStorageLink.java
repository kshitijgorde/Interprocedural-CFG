// 
// Decompiled by Procyon v0.5.30
// 

package org.yecht.ruby;

import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.RubyHash;

class HashStorageLink extends StorageLink
{
    final RubyHash hash;
    final IRubyObject key;
    final IRubyObject originalObject;
    
    public HashStorageLink(final IRubyObject h, final IRubyObject key, final IRubyObject originalObject) {
        this.hash = (RubyHash)h;
        this.key = key;
        this.originalObject = originalObject;
    }
    
    public void replaceLinkWith(final IRubyObject newObject) {
        this.hash.fastASet(this.key, newObject);
    }
}
