// 
// Decompiled by Procyon v0.5.30
// 

package org.jdesktop.j3d.loaders.kmz;

import java.io.Reader;
import com.sun.j3d.loaders.ParsingErrorException;
import com.sun.j3d.loaders.IncorrectFormatException;
import java.io.FileNotFoundException;
import java.util.Enumeration;
import java.net.URL;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import org.jdesktop.j3d.loaders.collada.Collada14Loader;
import com.sun.j3d.loaders.Scene;
import java.io.File;
import com.sun.j3d.loaders.LoaderBase;

public class KmzLoader extends LoaderBase
{
    private File findDaeFile(final File f) {
        System.out.println(f);
        final File[] files = f.listFiles();
        File[] array;
        for (int length = (array = files).length, i = 0; i < length; ++i) {
            final File file = array[i];
            System.out.println(file);
            if (file.isDirectory()) {
                final File df = this.findDaeFile(file);
                if (df != null) {
                    return df;
                }
            }
            else if (file.getName().endsWith(".dae")) {
                return file;
            }
        }
        return null;
    }
    
    public Scene load(final String fileName) throws FileNotFoundException, IncorrectFormatException, ParsingErrorException {
        try {
            final File f = new File(fileName);
            if (f.isDirectory()) {
                final File dae = this.findDaeFile(f);
                if (dae != null) {
                    final Collada14Loader loader = new Collada14Loader();
                    return loader.load(dae.toURI().toURL());
                }
            }
            else {
                final ZipFile zf = new ZipFile(f);
                final Enumeration<? extends ZipEntry> entries = zf.entries();
                while (entries.hasMoreElements()) {
                    final ZipEntry e = (ZipEntry)entries.nextElement();
                    if (e.getName().endsWith("dae")) {
                        final Collada14Loader loader2 = new Collada14Loader();
                        final String str = "jar:file:" + f.toString() + "!/" + e.getName();
                        final URL u = new URL(str);
                        return loader2.load(u);
                    }
                }
            }
        }
        catch (Exception e2) {
            System.out.println(e2);
        }
        return null;
    }
    
    public Scene load(final URL url) throws FileNotFoundException, IncorrectFormatException, ParsingErrorException {
        try {
            final File f = new File(url.toURI());
            return this.load(f.getAbsolutePath());
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public Scene load(final Reader reader) throws FileNotFoundException, IncorrectFormatException, ParsingErrorException {
        return null;
    }
}
