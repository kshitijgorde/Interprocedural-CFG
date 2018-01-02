// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.bcel.util;

import java.util.zip.ZipEntry;
import java.io.FileInputStream;
import java.io.DataInputStream;
import java.io.InputStream;
import java.util.Iterator;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.zip.ZipFile;
import java.io.File;
import java.util.StringTokenizer;
import java.util.ArrayList;

public class ClassPath
{
    private PathEntry[] paths;
    
    public ClassPath(final String class_path) {
        final ArrayList vec = new ArrayList();
        final StringTokenizer tok = new StringTokenizer(class_path, System.getProperty("path.separator"));
        while (tok.hasMoreTokens()) {
            final String path = tok.nextToken();
            if (!path.equals("")) {
                final File file = new File(path);
                try {
                    if (!file.exists()) {
                        continue;
                    }
                    if (file.isDirectory()) {
                        vec.add(new Dir(path));
                    }
                    else {
                        vec.add(new Zip(new ZipFile(file)));
                    }
                }
                catch (IOException e) {
                    System.err.println("CLASSPATH component " + file + ": " + e);
                }
            }
        }
        vec.toArray(this.paths = new PathEntry[vec.size()]);
    }
    
    public ClassPath() {
        this(getClassPath());
    }
    
    private static final void getPathComponents(final String path, final ArrayList list) {
        if (path != null) {
            final StringTokenizer tok = new StringTokenizer(path, File.pathSeparator);
            while (tok.hasMoreTokens()) {
                final String name = tok.nextToken();
                final File file = new File(name);
                if (file.exists()) {
                    list.add(name);
                }
            }
        }
    }
    
    public static final String getClassPath() {
        final String class_path = System.getProperty("java.class.path");
        final String boot_path = System.getProperty("sun.boot.class.path");
        final String ext_path = System.getProperty("java.ext.dirs");
        final ArrayList list = new ArrayList();
        getPathComponents(class_path, list);
        getPathComponents(boot_path, list);
        final ArrayList dirs = new ArrayList();
        getPathComponents(ext_path, dirs);
        final Iterator e = dirs.iterator();
        while (e.hasNext()) {
            final File ext_dir = new File(e.next());
            final String[] extensions = ext_dir.list(new FilenameFilter() {
                public boolean accept(final File dir, String name) {
                    name = name.toLowerCase();
                    return name.endsWith(".zip") || name.endsWith(".jar");
                }
            });
            if (extensions != null) {
                for (int i = 0; i < extensions.length; ++i) {
                    list.add(ext_path + File.separatorChar + extensions[i]);
                }
            }
        }
        final StringBuffer buf = new StringBuffer();
        final Iterator e2 = list.iterator();
        while (e2.hasNext()) {
            buf.append(e2.next());
            if (e2.hasNext()) {
                buf.append(File.pathSeparatorChar);
            }
        }
        return buf.toString();
    }
    
    public InputStream getInputStream(final String name) throws IOException {
        return this.getInputStream(name, ".class");
    }
    
    public InputStream getInputStream(final String name, final String suffix) throws IOException {
        InputStream is = null;
        try {
            is = this.getClass().getClassLoader().getResourceAsStream(name + suffix);
        }
        catch (Exception ex) {}
        if (is != null) {
            return is;
        }
        return this.getClassFile(name, suffix).getInputStream();
    }
    
    public ClassFile getClassFile(final String name, final String suffix) throws IOException {
        for (int i = 0; i < this.paths.length; ++i) {
            final ClassFile cf;
            if ((cf = this.paths[i].getClassFile(name, suffix)) != null) {
                return cf;
            }
        }
        throw new IOException("Couldn't find: " + name + suffix);
    }
    
    public ClassFile getClassFile(final String name) throws IOException {
        return this.getClassFile(name, ".class");
    }
    
    public byte[] getBytes(final String name, final String suffix) throws IOException {
        final InputStream is = this.getInputStream(name, suffix);
        if (is == null) {
            throw new IOException("Couldn't find: " + name + suffix);
        }
        final DataInputStream dis = new DataInputStream(is);
        final byte[] bytes = new byte[is.available()];
        dis.readFully(bytes);
        dis.close();
        is.close();
        return bytes;
    }
    
    public byte[] getBytes(final String name) throws IOException {
        return this.getBytes(name, ".class");
    }
    
    public String getPath(String name) throws IOException {
        final int index = name.lastIndexOf(46);
        String suffix = "";
        if (index > 0) {
            suffix = name.substring(index);
            name = name.substring(0, index);
        }
        return this.getPath(name, suffix);
    }
    
    public String getPath(final String name, final String suffix) throws IOException {
        return this.getClassFile(name, suffix).getPath();
    }
    
    private abstract static class PathEntry
    {
        abstract ClassFile getClassFile(final String p0, final String p1) throws IOException;
    }
    
    public abstract static class ClassFile
    {
        public abstract InputStream getInputStream() throws IOException;
        
        public abstract String getPath();
        
        public abstract long getTime();
        
        public abstract long getSize();
    }
    
    private static class Dir extends PathEntry
    {
        private String dir;
        
        Dir(final String d) {
            this.dir = d;
        }
        
        ClassFile getClassFile(final String name, final String suffix) throws IOException {
            final File file = new File(this.dir + File.separatorChar + name.replace('.', File.separatorChar) + suffix);
            return file.exists() ? new ClassFile() {
                public InputStream getInputStream() throws IOException {
                    return new FileInputStream(file);
                }
                
                public String getPath() {
                    try {
                        return file.getCanonicalPath();
                    }
                    catch (IOException e) {
                        return null;
                    }
                }
                
                public long getTime() {
                    return file.lastModified();
                }
                
                public long getSize() {
                    return file.length();
                }
            } : null;
        }
        
        public String toString() {
            return this.dir;
        }
    }
    
    private static class Zip extends PathEntry
    {
        private ZipFile zip;
        
        Zip(final ZipFile z) {
            this.zip = z;
        }
        
        ClassFile getClassFile(final String name, final String suffix) throws IOException {
            final ZipEntry entry = this.zip.getEntry(name.replace('.', '/') + suffix);
            return (entry != null) ? new ClassFile() {
                private final /* synthetic */ Zip this$0 = this$0;
                
                public InputStream getInputStream() throws IOException {
                    return this.this$0.zip.getInputStream(entry);
                }
                
                public String getPath() {
                    return entry.toString();
                }
                
                public long getTime() {
                    return entry.getTime();
                }
                
                public long getSize() {
                    return entry.getSize();
                }
            } : null;
        }
    }
}
