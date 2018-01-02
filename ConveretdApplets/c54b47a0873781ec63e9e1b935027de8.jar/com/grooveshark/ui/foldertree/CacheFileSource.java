// 
// Decompiled by Procyon v0.5.30
// 

package com.grooveshark.ui.foldertree;

import java.io.File;
import java.util.HashMap;

public class CacheFileSource implements FileSource
{
    private HashMap<String, File[]> filesCached;
    private FileSource source;
    
    public CacheFileSource(final FileSource source) {
        this.source = source;
        this.filesCached = new HashMap<String, File[]>();
    }
    
    public int getTotalChildren(final String parent) {
        final File[] children = this.listChildren(parent);
        return children.length;
    }
    
    public File[] listChildren(final String parent) {
        if (this.filesCached.containsKey(parent)) {
            return this.filesCached.get(parent);
        }
        final File[] children = this.source.listChildren(parent);
        this.filesCached.put(parent, children);
        return children;
    }
}
