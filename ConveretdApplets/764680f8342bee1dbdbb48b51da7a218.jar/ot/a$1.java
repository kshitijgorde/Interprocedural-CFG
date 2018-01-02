// 
// Decompiled by Procyon v0.5.30
// 

package ot;

import java.util.Set;
import java.util.HashSet;
import java.util.HashMap;

class a$1 extends HashMap
{
    final HashSet val$s;
    final pizdi this$0;
    
    public Set entrySet() {
        return this.val$s;
    }
    
    a$1(final pizdi a1, final HashSet hashset) {
        this.this$0 = a1;
        this.val$s = hashset;
    }
}
