// 
// Decompiled by Procyon v0.5.30
// 

package org.yecht.ruby;

import org.jruby.anno.JRubyMethod;
import java.util.Iterator;
import org.jruby.runtime.builtin.IRubyObject;
import java.util.LinkedList;
import org.jruby.RubyClass;
import org.jruby.Ruby;
import java.util.List;
import org.jruby.runtime.ObjectAllocator;
import org.jruby.RubyObject;

public class BadAlias extends RubyObject implements PossibleLinkNode
{
    public static final ObjectAllocator Allocator;
    private List<StorageLink> links;
    
    public BadAlias(final Ruby runtime, final RubyClass metaClass) {
        super(runtime, metaClass);
        this.links = new LinkedList<StorageLink>();
    }
    
    public List<StorageLink> getLinks() {
        return this.links;
    }
    
    public void addLink(final StorageLink link) {
        this.links.add(link);
    }
    
    public void replaceLinks(final IRubyObject newObject) {
        for (final StorageLink sl : this.links) {
            sl.replaceLinkWith(newObject);
        }
        this.links.clear();
    }
    
    @JRubyMethod
    public static IRubyObject initialize(final IRubyObject self, final IRubyObject val) {
        ((RubyObject)self).fastSetInstanceVariable("@name", val);
        return self;
    }
    
    @JRubyMethod(name = { "<=>" })
    public static IRubyObject cmp(final IRubyObject alias1, final IRubyObject alias2) {
        final IRubyObject str1 = ((RubyObject)alias1).fastGetInstanceVariable("@name");
        final IRubyObject str2 = ((RubyObject)alias2).fastGetInstanceVariable("@name");
        return str1.callMethod(alias1.getRuntime().getCurrentContext(), "<=>", str2);
    }
    
    static {
        Allocator = new ObjectAllocator() {
            public IRubyObject allocate(final Ruby runtime, final RubyClass klass) {
                return new BadAlias(runtime, klass);
            }
        };
    }
}
