// 
// Decompiled by Procyon v0.5.30
// 

package com.grooveshark.ui.foldertree;

import java.util.List;
import java.util.Collection;
import java.util.Arrays;
import java.util.ArrayList;
import java.io.File;
import java.io.FileFilter;

public class FolderFileSource implements FileSource
{
    private static final FileFilter filter;
    
    public int getTotalChildren(final String filepath) {
        final File[] children = this.listChildren(filepath);
        return children.length;
    }
    
    public File[] listChildren(final String filepath) {
        File[] children = null;
        if ("ROOTS".equals(filepath)) {
            final ArrayList<File> roots = new ArrayList<File>();
            roots.addAll(Arrays.asList(File.listRoots()));
            roots.addAll(this.getFavoriteFolders());
            children = roots.toArray(new File[0]);
        }
        else {
            final File parent = new File(filepath);
            children = parent.listFiles(FolderFileSource.filter);
        }
        if (children == null) {
            children = new File[0];
        }
        return children;
    }
    
    private List<File> getFavoriteFolders() {
        final ArrayList<File> favorites = new ArrayList<File>();
        final String home = System.getProperty("user.home");
        favorites.add(new File(home));
        return favorites;
    }
    
    static {
        filter = new FileFilter() {
            public boolean accept(final File file) {
                return file.isDirectory() && !file.isHidden();
            }
        };
    }
}
