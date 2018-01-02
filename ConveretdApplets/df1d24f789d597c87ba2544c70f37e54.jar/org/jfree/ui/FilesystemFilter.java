// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.ui;

import java.io.File;
import java.io.FilenameFilter;
import javax.swing.filechooser.FileFilter;

public class FilesystemFilter extends FileFilter implements FilenameFilter
{
    private String fileext;
    private String descr;
    private boolean accDirs;
    
    public FilesystemFilter(final String s, final String s2) {
        this(s, s2, true);
    }
    
    public FilesystemFilter(final String fileext, final String descr, final boolean accDirs) {
        this.fileext = fileext;
        this.descr = descr;
        this.accDirs = accDirs;
    }
    
    public boolean accept(final File file, final String s) {
        return (new File(file, s).isDirectory() && this.acceptsDirectories()) || s.endsWith(this.fileext);
    }
    
    public boolean accept(final File file) {
        return (file.isDirectory() && this.acceptsDirectories()) || file.getName().endsWith(this.fileext);
    }
    
    public String getDescription() {
        return this.descr;
    }
    
    public void acceptDirectories(final boolean accDirs) {
        this.accDirs = accDirs;
    }
    
    public boolean acceptsDirectories() {
        return this.accDirs;
    }
}
