// 
// Decompiled by Procyon v0.5.30
// 

package org.yecht.ruby;

import org.jruby.runtime.builtin.IRubyObject;
import java.util.List;

public interface PossibleLinkNode
{
    List<StorageLink> getLinks();
    
    void addLink(final StorageLink p0);
    
    void replaceLinks(final IRubyObject p0);
}
