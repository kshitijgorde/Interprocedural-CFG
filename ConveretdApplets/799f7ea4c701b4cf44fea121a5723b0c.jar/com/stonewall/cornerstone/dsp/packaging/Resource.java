// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.dsp.packaging;

import java.util.Iterator;
import java.util.List;
import java.io.File;

public class Resource
{
    private File f;
    private String basePath;
    
    Resource(final File f, final String basePath) {
        this.f = f;
        this.basePath = basePath;
    }
    
    String getName() {
        return this.f.getName();
    }
    
    String getPath() {
        String path = this.f.getPath();
        path = path.substring(this.basePath.length() + 1);
        return path;
    }
    
    String getId(final List<String> rootPaths) {
        String id = this.getPath();
        for (final String s : rootPaths) {
            if (id.contains(s)) {
                id = id.substring(s.length() + 1, id.length());
                break;
            }
        }
        if (id.endsWith(".class")) {
            id = id.replace(".class", "");
        }
        id = id.replace(File.separator, ".");
        return id;
    }
    
    String getText() {
        String path = this.f.getPath();
        final String name = this.getName();
        if (name.endsWith(".class")) {
            path = path.replace(".class", "");
            path = path.replace(File.separator, ".");
        }
        else {
            path = path.replace(File.separator, "/");
        }
        return path;
    }
    
    File getFile() {
        return this.f;
    }
    
    @Override
    public boolean equals(final Object obj) {
        if (obj instanceof Resource) {
            final File objf = ((Resource)obj).getFile();
            return this.getFile().equals(objf);
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        return this.f.hashCode();
    }
}
