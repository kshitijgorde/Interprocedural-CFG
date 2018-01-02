// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.util.io;

import java.util.Iterator;
import java.io.File;
import java.util.ArrayList;
import javax.swing.filechooser.FileFilter;

public class SimpleFileFilter extends FileFilter
{
    private ArrayList exts;
    private String desc;
    private Object data;
    
    public SimpleFileFilter(final String s, final String desc) {
        this.exts = new ArrayList();
        this.addExtension(s);
        this.desc = desc;
    }
    
    public SimpleFileFilter(final String s, final String desc, final Object data) {
        this.exts = new ArrayList();
        this.addExtension(s);
        this.desc = desc;
        this.data = data;
    }
    
    public void addExtension(final String s) {
        this.exts.add(s.toLowerCase());
    }
    
    public boolean accept(final File file) {
        if (file == null) {
            return false;
        }
        if (file.isDirectory()) {
            return true;
        }
        final String extension = IOLib.getExtension(file);
        if (extension == null) {
            return false;
        }
        final Iterator<String> iterator = this.exts.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().equalsIgnoreCase(extension)) {
                return true;
            }
        }
        return false;
    }
    
    public Object getUserData() {
        return this.data;
    }
    
    public String getDescription() {
        return this.desc;
    }
    
    public String getExtension() {
        return this.exts.get(0);
    }
}
