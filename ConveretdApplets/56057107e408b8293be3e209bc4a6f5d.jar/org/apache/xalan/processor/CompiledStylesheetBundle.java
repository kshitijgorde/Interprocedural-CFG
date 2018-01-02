// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.processor;

import java.io.ObjectStreamClass;
import java.util.Hashtable;
import java.util.zip.ZipFile;
import java.io.ObjectInputStream;
import javax.xml.transform.Templates;
import java.io.InputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.util.zip.ZipEntry;
import java.io.OutputStream;
import java.util.zip.ZipOutputStream;
import java.io.FileOutputStream;
import java.io.File;
import java.util.Vector;
import org.apache.xalan.templates.Stylesheet;

public class CompiledStylesheetBundle
{
    static void createBundle(final Stylesheet root, final Vector compiledTemplates) {
        final String outdir = "." + File.separator;
        try {
            String zipname = null;
            final String systemID = root.getSystemId();
            if (systemID != null) {
                final int namestart = systemID.lastIndexOf(47) + 1;
                int nameend = systemID.lastIndexOf(35, namestart);
                if (nameend < 0) {
                    nameend = systemID.length();
                }
                if (namestart < nameend) {
                    zipname = systemID.substring(namestart, nameend);
                }
            }
            if (zipname == null) {
                zipname = "UnidentifiedStylesheet";
            }
            final FileOutputStream f = new FileOutputStream(String.valueOf(outdir) + zipname + ".xsb");
            final ZipOutputStream zf = new ZipOutputStream(f);
            zf.setMethod(8);
            final byte[] buffer = new byte[4096];
            for (int i = compiledTemplates.size() - 1; i >= 0; --i) {
                final Class c = compiledTemplates.elementAt(i).getClass();
                final String fullname = c.getName();
                final int start = fullname.lastIndexOf(".");
                final String packagename = fullname.substring(0, start);
                final String shortname = fullname.substring(start + 1);
                final String sink = String.valueOf(packageNameToDirectory(packagename, "", '/')) + shortname + ".class";
                final ZipEntry ze = new ZipEntry(sink);
                zf.putNextEntry(ze);
                final String source = String.valueOf(packageNameToDirectory(packagename, outdir, File.separatorChar)) + shortname + ".class";
                final InputStream fis = new FileInputStream(source);
                int count = 0;
                int got = 1;
                while (got >= 0) {
                    got = fis.read(buffer);
                    if (got > 0) {
                        zf.write(buffer, 0, got);
                        count += got;
                    }
                }
                fis.close();
                ze.setSize(count);
                zf.closeEntry();
            }
            final ZipEntry ze = new ZipEntry("Stylesheet.ser");
            zf.putNextEntry(ze);
            final ObjectOutputStream of = new ObjectOutputStream(zf);
            of.writeObject(root);
            of.flush();
            zf.closeEntry();
            zf.finish();
            zf.flush();
            zf.close();
            f.flush();
            f.close();
        }
        catch (IOException e) {
            System.err.println("Exception while bundling compiled stylesheet");
            e.printStackTrace(System.err);
        }
    }
    
    public Templates loadBundle(final String filename) throws IOException, ClassNotFoundException {
        InputStream is = null;
        ObjectInputStream os = null;
        Templates ss = null;
        try {
            final ClassLoader cl = new ZipfileClassLoader(filename, false);
            is = cl.getResourceAsStream("Stylesheet.ser");
            os = new ClassLoaderObjectInputStream(cl, is);
            ss = (Templates)os.readObject();
        }
        finally {
            if (os != null) {
                os.close();
            }
            if (is != null) {
                is.close();
            }
        }
        return ss;
    }
    
    static String packageNameToDirectory(final String packagename, final String baseLocation, final char separator) {
        final int fnstart = baseLocation.lastIndexOf(separator);
        final StringBuffer subdir = new StringBuffer((fnstart >= 0) ? baseLocation.substring(0, fnstart + 1) : "");
        subdir.append(packagename.replace('.', separator)).append(separator);
        return subdir.toString();
    }
    
    class ZipfileClassLoader extends ClassLoader
    {
        ZipFile zip;
        Hashtable cache;
        
        public ZipfileClassLoader(final String filename, final boolean cached) throws IOException {
            this.zip = null;
            this.zip = new ZipFile(filename);
            if (cached) {
                this.cache = new Hashtable();
            }
        }
        
        public Class findClass(final String name) {
            Class c = null;
            try {
                this.getClass();
                c = Class.forName(name);
            }
            catch (ClassNotFoundException ex) {
                final byte[] b = this.loadClassData(name);
                if (b != null) {
                    c = this.defineClass(name, b, 0, b.length);
                }
            }
            return c;
        }
        
        public InputStream getResourceAsStream(final String name) {
            InputStream is = null;
            try {
                final ZipEntry entry = this.zip.getEntry(name);
                is = this.zip.getInputStream(entry);
            }
            catch (IOException e) {
                System.err.println("Problem loading compiled stylesheet");
                e.printStackTrace();
            }
            return is;
        }
        
        public synchronized Class loadClass(final String name, final boolean resolve) throws ClassNotFoundException {
            Class c = (this.cache == null) ? null : this.cache.get(name);
            if (c == null) {
                c = this.findClass(name);
                if (c != null && resolve) {
                    this.resolveClass(c);
                }
                if (this.cache != null) {
                    this.cache.put(name, c);
                }
            }
            return c;
        }
        
        private byte[] loadClassData(final String name) {
            final int start = name.lastIndexOf(".");
            final String packagename = name.substring(0, start);
            final String shortname = name.substring(start + 1);
            final String fn = String.valueOf(CompiledStylesheetBundle.packageNameToDirectory(packagename, "", '/')) + shortname + ".class";
            byte[] data = null;
            try {
                final ZipEntry entry = this.zip.getEntry(fn);
                if (entry != null) {
                    final int bufsize = (int)entry.getSize();
                    data = new byte[bufsize];
                    final InputStream is = this.zip.getInputStream(entry);
                    for (int len = 0, off = 0; off < bufsize; off += len) {
                        len = is.read(data, off, bufsize - off);
                    }
                    is.close();
                }
            }
            catch (IOException e) {
                System.err.println("Exception while reloading compiled stylesheet");
                e.printStackTrace(System.err);
            }
            return data;
        }
    }
    
    class ClassLoaderObjectInputStream extends ObjectInputStream
    {
        ClassLoader cl;
        
        public ClassLoaderObjectInputStream(final ClassLoader cl, final InputStream is) throws IOException {
            super(is);
            this.cl = cl;
        }
        
        protected Class resolveClass(final ObjectStreamClass v) throws IOException, ClassNotFoundException {
            return this.cl.loadClass(v.getName());
        }
    }
}
