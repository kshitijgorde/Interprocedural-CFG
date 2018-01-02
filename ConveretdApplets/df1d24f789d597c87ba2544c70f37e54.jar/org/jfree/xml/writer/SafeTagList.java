// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.xml.writer;

import java.util.HashMap;

public class SafeTagList
{
    private HashMap safeTags;
    
    public SafeTagList() {
        this.safeTags = new HashMap();
    }
    
    public void add(final String s) {
        this.safeTags.put(s, new SafeDescription(true, true));
    }
    
    public void add(final String s, final boolean b, final boolean b2) {
        this.safeTags.put(s, new SafeDescription(b, b2));
    }
    
    public boolean isSafeForOpen(final String s) {
        final SafeDescription safeDescription = this.safeTags.get(s);
        return safeDescription != null && safeDescription.isOpen();
    }
    
    public boolean isSafeForClose(final String s) {
        final SafeDescription safeDescription = this.safeTags.get(s);
        return safeDescription != null && safeDescription.isClose();
    }
    
    private static class SafeDescription
    {
        private boolean open;
        private boolean close;
        
        public SafeDescription(final boolean open, final boolean close) {
            this.open = open;
            this.close = close;
        }
        
        public boolean isOpen() {
            return this.open;
        }
        
        public boolean isClose() {
            return this.close;
        }
    }
}
