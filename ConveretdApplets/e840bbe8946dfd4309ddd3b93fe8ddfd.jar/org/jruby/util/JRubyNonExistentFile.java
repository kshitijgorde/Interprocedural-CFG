// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.util;

import java.io.FileFilter;
import java.io.FilenameFilter;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;

public class JRubyNonExistentFile extends JRubyFile
{
    static final JRubyNonExistentFile NOT_EXIST;
    
    private JRubyNonExistentFile() {
        super("");
    }
    
    public String getAbsolutePath() {
        return "";
    }
    
    public boolean isDirectory() {
        return false;
    }
    
    public boolean exists() {
        return false;
    }
    
    public String getCanonicalPath() throws IOException {
        throw new FileNotFoundException("File does not exist");
    }
    
    public String getPath() {
        return "";
    }
    
    public String toString() {
        return "";
    }
    
    public File getAbsoluteFile() {
        return this;
    }
    
    public File getCanonicalFile() throws IOException {
        throw new FileNotFoundException("File does not exist");
    }
    
    public String getParent() {
        return "";
    }
    
    public File getParentFile() {
        return this;
    }
    
    public static File[] listRoots() {
        return new File[0];
    }
    
    public static File createTempFile(final String prefix, final String suffix, final File directory) throws IOException {
        return createTempFile(prefix, suffix);
    }
    
    public static File createTempFile(final String prefix, final String suffix) throws IOException {
        throw new FileNotFoundException("File does not exist");
    }
    
    public String[] list(final FilenameFilter filter) {
        return new String[0];
    }
    
    public File[] listFiles() {
        return new File[0];
    }
    
    public File[] listFiles(final FileFilter filter) {
        return new File[0];
    }
    
    public File[] listFiles(final FilenameFilter filter) {
        return new File[0];
    }
    
    static {
        NOT_EXIST = new JRubyNonExistentFile();
    }
}
