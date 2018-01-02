// 
// Decompiled by Procyon v0.5.30
// 

package org.yecht.ruby;

import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.RubyArray;

class ArrayStorageLink extends StorageLink
{
    private final RubyArray array;
    private final int index;
    private final IRubyObject originalObject;
    
    public ArrayStorageLink(final IRubyObject arr, final int index, final IRubyObject originalObject) {
        this.array = (RubyArray)arr;
        this.index = index;
        this.originalObject = originalObject;
    }
    
    public void replaceLinkWith(final IRubyObject newObject) {
        this.array.store(this.index, newObject);
    }
}
