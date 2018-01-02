// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.posix;

import java.io.FileFilter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.URI;
import java.io.File;

public class JavaSecuredFile extends File
{
    public JavaSecuredFile(final File parent, final String child) {
        super(parent, child);
    }
    
    public JavaSecuredFile(final String pathname) {
        super(pathname);
    }
    
    public JavaSecuredFile(final String parent, final String child) {
        super(parent, child);
    }
    
    public JavaSecuredFile(final URI uri) {
        super(uri);
    }
    
    public File getParentFile() {
        final String parent = this.getParent();
        if (parent != null) {
            return new JavaSecuredFile(parent);
        }
        return null;
    }
    
    public File getAbsoluteFile() {
        final String absolute = this.getAbsolutePath();
        if (absolute != null) {
            return new JavaSecuredFile(absolute);
        }
        return null;
    }
    
    public String getCanonicalPath() throws IOException {
        try {
            return super.getCanonicalPath();
        }
        catch (SecurityException ex) {
            throw new IOException(ex.getMessage());
        }
    }
    
    public File getCanonicalFile() throws IOException {
        final String canonical = this.getCanonicalPath();
        if (canonical != null) {
            return new JavaSecuredFile(canonical);
        }
        return null;
    }
    
    public boolean canRead() {
        try {
            return super.canRead();
        }
        catch (SecurityException ex) {
            return false;
        }
    }
    
    public boolean canWrite() {
        try {
            return super.canWrite();
        }
        catch (SecurityException ex) {
            return false;
        }
    }
    
    public boolean exists() {
        try {
            return super.exists();
        }
        catch (SecurityException ex) {
            return false;
        }
    }
    
    public boolean isDirectory() {
        try {
            return super.isDirectory();
        }
        catch (SecurityException ex) {
            return false;
        }
    }
    
    public boolean isFile() {
        try {
            return super.isFile();
        }
        catch (SecurityException ex) {
            return false;
        }
    }
    
    public boolean isHidden() {
        try {
            return super.isHidden();
        }
        catch (SecurityException ex) {
            return false;
        }
    }
    
    public long lastModified() {
        try {
            return super.lastModified();
        }
        catch (SecurityException ex) {
            return 0L;
        }
    }
    
    public long length() {
        try {
            return super.length();
        }
        catch (SecurityException ex) {
            return 0L;
        }
    }
    
    public boolean createNewFile() throws IOException {
        try {
            return super.createNewFile();
        }
        catch (SecurityException ex) {
            throw new IOException(ex.getMessage());
        }
    }
    
    public boolean delete() {
        try {
            return super.delete();
        }
        catch (SecurityException ex) {
            return false;
        }
    }
    
    public String[] list() {
        try {
            return super.list();
        }
        catch (SecurityException ex) {
            return null;
        }
    }
    
    public String[] list(final FilenameFilter filter) {
        try {
            return super.list(filter);
        }
        catch (SecurityException ex) {
            return null;
        }
    }
    
    public File[] listFiles() {
        try {
            return super.listFiles();
        }
        catch (SecurityException ex) {
            return null;
        }
    }
    
    public File[] listFiles(final FilenameFilter filter) {
        try {
            return super.listFiles(filter);
        }
        catch (SecurityException ex) {
            return null;
        }
    }
    
    public File[] listFiles(final FileFilter filter) {
        try {
            return super.listFiles(filter);
        }
        catch (SecurityException ex) {
            return null;
        }
    }
    
    public boolean mkdir() {
        try {
            return super.mkdir();
        }
        catch (SecurityException ex) {
            return false;
        }
    }
    
    public boolean mkdirs() {
        try {
            return super.mkdirs();
        }
        catch (SecurityException ex) {
            return false;
        }
    }
    
    public boolean renameTo(final File dest) {
        try {
            return super.renameTo(dest);
        }
        catch (SecurityException ex) {
            return false;
        }
    }
    
    public boolean setLastModified(final long time) {
        try {
            return super.setLastModified(time);
        }
        catch (SecurityException ex) {
            return false;
        }
    }
    
    public boolean setReadOnly() {
        try {
            return super.setReadOnly();
        }
        catch (SecurityException ex) {
            return false;
        }
    }
}
