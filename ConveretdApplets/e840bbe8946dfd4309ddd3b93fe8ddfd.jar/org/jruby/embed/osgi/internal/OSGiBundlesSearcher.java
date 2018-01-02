// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.embed.osgi.internal;

import org.jruby.runtime.load.LoadService;

public class OSGiBundlesSearcher implements LoadService.LoadSearcher
{
    public boolean shouldTrySearch(final LoadService.SearchState state) {
        return false;
    }
    
    public void trySearch(final LoadService.SearchState state) throws LoadService.AlreadyLoaded {
    }
}
