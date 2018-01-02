// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.util;

import org.jruby.Ruby;
import java.util.HashSet;
import org.jruby.RubyArray;
import org.jruby.RubyHash;
import java.util.Set;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;

public class RecursiveComparator
{
    public static IRubyObject compare(final ThreadContext context, final String method, final IRubyObject a, final IRubyObject b, Set<Pair> seen) {
        final Ruby runtime = context.getRuntime();
        if (a == b) {
            return runtime.getTrue();
        }
        if ((a instanceof RubyHash && b instanceof RubyHash) || (a instanceof RubyArray && b instanceof RubyArray)) {
            final Pair pair = new Pair(a, b);
            if (seen == null) {
                seen = new HashSet<Pair>();
            }
            else if (seen.contains(pair)) {
                return runtime.getTrue();
            }
            seen.add(pair);
        }
        if (a instanceof RubyHash) {
            final RubyHash hash = (RubyHash)a;
            return hash.compare(context, method, b, seen);
        }
        if (a instanceof RubyArray) {
            final RubyArray array = (RubyArray)a;
            return array.compare(context, method, b, seen);
        }
        return a.callMethod(context, method, b);
    }
    
    public static class Pair
    {
        private int a;
        private int b;
        
        public Pair(final IRubyObject a, final IRubyObject b) {
            this.a = System.identityHashCode(a);
            this.b = System.identityHashCode(b);
        }
        
        public boolean equals(final Object other) {
            if (this == other) {
                return true;
            }
            if (other == null || !(other instanceof Pair)) {
                return false;
            }
            final Pair pair = (Pair)other;
            return this.a == pair.a && this.b == pair.b;
        }
        
        public int hashCode() {
            int result = this.a;
            result = 31 * result + this.b;
            return result;
        }
    }
}
