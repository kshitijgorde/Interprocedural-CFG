// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.util;

import java.io.File;

public class DirectoryBuilder
{
    protected File root;
    
    public DirectoryBuilder() {
    }
    
    public DirectoryBuilder(final File root) {
        this.root = root;
    }
    
    public DirectoryBuilder(final File root, final File child) {
        this(root);
        this.cd(child);
    }
    
    public DirectoryBuilder(final String rootname) {
        this(new File(rootname));
    }
    
    public DirectoryBuilder(final String rootname, final String childname) {
        this(new File(rootname), new File(childname));
    }
    
    public DirectoryBuilder cd(final File child) {
        if (child.isAbsolute()) {
            this.root = child;
        }
        else {
            this.root = new File(this.root, child.getPath());
        }
        return this;
    }
    
    public DirectoryBuilder cd(final String childname) {
        return this.cd(new File(childname));
    }
    
    public File get() {
        return this.root;
    }
    
    public String toString() {
        return this.root.toString();
    }
}
