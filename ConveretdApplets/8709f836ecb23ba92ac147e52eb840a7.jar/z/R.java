// 
// Decompiled by Procyon v0.5.30
// 

package z;

import java.io.File;
import java.util.Hashtable;
import java.io.FileFilter;

public final class R implements FileFilter
{
    private Hashtable a;
    
    public R() {
        this.a = null;
        this.a = new Hashtable();
    }
    
    public R(final String s) {
        this();
        final String[] split = s.split(",");
        for (int i = 0; i < split.length; ++i) {
            final String s2 = split[i];
            if (this.a == null) {
                this.a = new Hashtable(5);
            }
            this.a.put(s2.toLowerCase(), this);
        }
    }
    
    public final boolean accept(final File file) {
        if (file != null) {
            if (file.getName().startsWith(".")) {
                return false;
            }
            if (file.getName().equals("Macintosh HD")) {
                return false;
            }
            if (file.isDirectory()) {
                return true;
            }
            if (a(file) != null && this.a.get(a(file)) != null) {
                return true;
            }
        }
        return false;
    }
    
    private static String a(final File file) {
        final String name;
        final int lastIndex;
        if (file != null && (lastIndex = (name = file.getName()).lastIndexOf(46)) > 0 && lastIndex < name.length() - 1) {
            return name.substring(lastIndex + 1).toLowerCase();
        }
        return null;
    }
}
