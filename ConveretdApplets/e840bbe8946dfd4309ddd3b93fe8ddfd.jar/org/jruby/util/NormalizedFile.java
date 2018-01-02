// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.util;

import java.io.FileFilter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.File;
import java.net.URI;
import org.jruby.ext.posix.JavaSecuredFile;

public class NormalizedFile extends JavaSecuredFile
{
    private static final long serialVersionUID = 7630618150344842227L;
    
    public NormalizedFile(final String pathname) {
        super(pathname);
    }
    
    public NormalizedFile(final URI uri) {
        super(uri);
    }
    
    public NormalizedFile(final File parent, final String child) {
        super(parent, child);
    }
    
    public NormalizedFile(final String parent, final String child) {
        super(parent, child);
    }
    
    public String getAbsolutePath() {
        return new File(super.getPath()).getAbsolutePath().replace(File.separatorChar, '/');
    }
    
    public String getCanonicalPath() throws IOException {
        return super.getCanonicalPath().replace(File.separatorChar, '/');
    }
    
    public String getPath() {
        return super.getPath().replace(File.separatorChar, '/');
    }
    
    public String toString() {
        return super.toString().replace(File.separatorChar, '/');
    }
    
    public File getAbsoluteFile() {
        return new NormalizedFile(this.getAbsolutePath());
    }
    
    public File getCanonicalFile() throws IOException {
        return new NormalizedFile(this.getCanonicalPath());
    }
    
    public String getParent() {
        return super.getParent().replace(File.separatorChar, '/');
    }
    
    public File getParentFile() {
        return new NormalizedFile(this.getParent());
    }
    
    public static File[] listRoots() {
        final File[] roots = File.listRoots();
        final NormalizedFile[] smartRoots = new NormalizedFile[roots.length];
        for (int i = 0; i < roots.length; ++i) {
            smartRoots[i] = new NormalizedFile(roots[i].getPath());
        }
        return smartRoots;
    }
    
    public static File createTempFile(final String prefix, final String suffix, final File directory) throws IOException {
        final File file = File.createTempFile(prefix, suffix, directory);
        return new NormalizedFile(file.getPath());
    }
    
    public static File createTempFile(final String prefix, final String suffix) throws IOException {
        final File file = File.createTempFile(prefix, suffix);
        return new NormalizedFile(file.getPath());
    }
    
    public String[] list() {
        return super.list();
    }
    
    public String[] list(final FilenameFilter filter) {
        final String[] files = super.list(filter);
        if (files == null) {
            return null;
        }
        final String[] smartFiles = new String[files.length];
        for (int i = 0; i < files.length; ++i) {
            smartFiles[i] = files[i].replace(File.separatorChar, '/');
        }
        return smartFiles;
    }
    
    public File[] listFiles() {
        final File[] files = super.listFiles();
        if (files == null) {
            return null;
        }
        final NormalizedFile[] smartFiles = new NormalizedFile[files.length];
        for (int i = 0; i < files.length; ++i) {
            smartFiles[i] = new NormalizedFile(files[i].getPath());
        }
        return smartFiles;
    }
    
    public File[] listFiles(final FileFilter filter) {
        final File[] files = super.listFiles(filter);
        if (files == null) {
            return null;
        }
        final NormalizedFile[] smartFiles = new NormalizedFile[files.length];
        for (int i = 0; i < files.length; ++i) {
            smartFiles[i] = new NormalizedFile(files[i].getPath());
        }
        return smartFiles;
    }
    
    public File[] listFiles(final FilenameFilter filter) {
        final File[] files = super.listFiles(filter);
        if (files == null) {
            return null;
        }
        final NormalizedFile[] smartFiles = new NormalizedFile[files.length];
        for (int i = 0; i < files.length; ++i) {
            smartFiles[i] = new NormalizedFile(files[i].getPath());
        }
        return smartFiles;
    }
    
    public static String getFileProperty(final String property) {
        final String value = System.getProperty(property);
        return value.replace(File.separatorChar, '/');
    }
}
