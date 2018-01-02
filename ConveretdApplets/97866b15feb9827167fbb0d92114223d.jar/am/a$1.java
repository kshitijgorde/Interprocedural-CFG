// 
// Decompiled by Procyon v0.5.30
// 

package am;

import java.util.Set;
import java.util.HashSet;
import java.util.HashMap;

class a$1 extends HashMap
{
    final HashSet val$s;
    final hodar this$0;
    
    @Override
    public Set entrySet() {
        return this.val$s;
    }
    
    a$1(final hodar this$0, final HashSet val$s) {
        this.this$0 = this$0;
        this.val$s = val$s;
    }
}
