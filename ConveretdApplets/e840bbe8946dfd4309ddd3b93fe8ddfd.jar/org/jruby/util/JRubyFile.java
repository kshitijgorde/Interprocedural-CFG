// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.util;

import java.io.FileFilter;
import java.io.FilenameFilter;
import java.io.IOException;
import org.jruby.Ruby;
import java.io.File;
import org.jruby.platform.Platform;
import org.jruby.ext.posix.JavaSecuredFile;

public class JRubyFile extends JavaSecuredFile
{
    private static final long serialVersionUID = 435364547567567L;
    
    public static JRubyFile create(final String cwd, final String pathname) {
        return createNoUnicodeConversion(cwd, pathname);
    }
    
    public static String normalizeSeps(final String path) {
        if (Platform.IS_WINDOWS) {
            return path.replace(File.separatorChar, '/');
        }
        return path;
    }
    
    private static JRubyFile createNoUnicodeConversion(final String cwd, final String pathname) {
        if (pathname == null || pathname.equals("") || Ruby.isSecurityRestricted()) {
            return JRubyNonExistentFile.NOT_EXIST;
        }
        File internal = new JavaSecuredFile(pathname);
        if (!internal.isAbsolute()) {
            internal = new JavaSecuredFile(cwd, pathname);
            if (!internal.isAbsolute()) {
                throw new IllegalArgumentException("Neither current working directory (" + cwd + ") nor pathname (" + pathname + ") led to an absolute path");
            }
        }
        return new JRubyFile(internal);
    }
    
    public static String getFileProperty(final String property) {
        return normalizeSeps(SafePropertyAccessor.getProperty(property, "/"));
    }
    
    private JRubyFile(final File file) {
        this(file.getAbsolutePath());
    }
    
    protected JRubyFile(final String filename) {
        super(filename);
    }
    
    public String getAbsolutePath() {
        return normalizeSeps(new File(super.getPath()).getAbsolutePath());
    }
    
    public String getCanonicalPath() throws IOException {
        try {
            return normalizeSeps(super.getCanonicalPath());
        }
        catch (IOException e) {
            throw (IOException)new IOException("Unable to canonicalize path: " + this.getAbsolutePath()).initCause(e);
        }
    }
    
    public String getPath() {
        return normalizeSeps(super.getPath());
    }
    
    public String toString() {
        return normalizeSeps(super.toString());
    }
    
    public File getAbsoluteFile() {
        return new JRubyFile(this.getAbsolutePath());
    }
    
    public File getCanonicalFile() throws IOException {
        return new JRubyFile(this.getCanonicalPath());
    }
    
    public String getParent() {
        String par = super.getParent();
        if (par != null) {
            par = normalizeSeps(par);
        }
        return par;
    }
    
    public File getParentFile() {
        final String par = this.getParent();
        if (par == null) {
            return this;
        }
        return new JRubyFile(par);
    }
    
    public static File[] listRoots() {
        final File[] roots = File.listRoots();
        final JRubyFile[] smartRoots = new JRubyFile[roots.length];
        for (int i = 0, j = roots.length; i < j; ++i) {
            smartRoots[i] = new JRubyFile(roots[i].getPath());
        }
        return smartRoots;
    }
    
    public static File createTempFile(final String prefix, final String suffix, final File directory) throws IOException {
        return new JRubyFile(File.createTempFile(prefix, suffix, directory));
    }
    
    public static File createTempFile(final String prefix, final String suffix) throws IOException {
        return new JRubyFile(File.createTempFile(prefix, suffix));
    }
    
    public String[] list(final FilenameFilter filter) {
        final String[] files = super.list(filter);
        if (files == null) {
            return null;
        }
        final String[] smartFiles = new String[files.length];
        for (int i = 0; i < files.length; ++i) {
            smartFiles[i] = normalizeSeps(files[i]);
        }
        return smartFiles;
    }
    
    public File[] listFiles() {
        final File[] files = super.listFiles();
        if (files == null) {
            return null;
        }
        final JRubyFile[] smartFiles = new JRubyFile[files.length];
        for (int i = 0, j = files.length; i < j; ++i) {
            smartFiles[i] = createNoUnicodeConversion(super.getAbsolutePath(), files[i].getPath());
        }
        return smartFiles;
    }
    
    public File[] listFiles(final FileFilter filter) {
        final File[] files = super.listFiles(filter);
        if (files == null) {
            return null;
        }
        final JRubyFile[] smartFiles = new JRubyFile[files.length];
        for (int i = 0, j = files.length; i < j; ++i) {
            smartFiles[i] = createNoUnicodeConversion(super.getAbsolutePath(), files[i].getPath());
        }
        return smartFiles;
    }
    
    public File[] listFiles(final FilenameFilter filter) {
        final File[] files = super.listFiles(filter);
        if (files == null) {
            return null;
        }
        final JRubyFile[] smartFiles = new JRubyFile[files.length];
        for (int i = 0, j = files.length; i < j; ++i) {
            smartFiles[i] = createNoUnicodeConversion(super.getAbsolutePath(), files[i].getPath());
        }
        return smartFiles;
    }
}
