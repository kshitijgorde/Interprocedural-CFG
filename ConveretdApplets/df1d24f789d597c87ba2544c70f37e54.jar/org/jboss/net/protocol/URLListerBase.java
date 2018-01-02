// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.net.protocol;

import java.util.Arrays;
import java.util.HashSet;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Collection;
import java.net.URL;

public abstract class URLListerBase implements URLLister
{
    protected static final URLFilter acceptAllFilter;
    
    public Collection listMembers(final URL baseUrl, final String patterns, final boolean scanNonDottedSubDirs) throws IOException {
        final StringTokenizer tokens = new StringTokenizer(patterns, ",");
        final String[] members = new String[tokens.countTokens()];
        int i = 0;
        while (tokens.hasMoreTokens()) {
            final String token = tokens.nextToken();
            members[i] = token.trim();
            ++i;
        }
        final URLFilter filter = new URLFilterImpl(members);
        return this.listMembers(baseUrl, filter, scanNonDottedSubDirs);
    }
    
    public Collection listMembers(final URL baseUrl, final String patterns) throws IOException {
        return this.listMembers(baseUrl, patterns, false);
    }
    
    static {
        acceptAllFilter = new URLFilter() {
            public boolean accept(final URL baseURL, final String memberName) {
                return true;
            }
        };
    }
    
    public static class URLFilterImpl implements URLFilter
    {
        protected boolean allowAll;
        protected HashSet constants;
        
        public URLFilterImpl(final String[] patterns) {
            this.constants = new HashSet((Collection<? extends E>)Arrays.asList(patterns));
            this.allowAll = this.constants.contains("*");
        }
        
        public boolean accept(final URL baseUrl, final String name) {
            return this.allowAll || this.constants.contains(name);
        }
    }
}
