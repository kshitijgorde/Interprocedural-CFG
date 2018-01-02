// 
// Decompiled by Procyon v0.5.30
// 

package update;

import java.io.File;
import javax.swing.filechooser.FileFilter;

public class JarFileFilter extends FileFilter
{
    private final String jarExtension = "jar";
    
    public boolean accept(final File file) {
        if (file.isDirectory()) {
            return true;
        }
        final String extension = this.getExtension(file);
        return extension != null && extension.equals("jar");
    }
    
    public String getDescription() {
        return "Jar File (*.jar)";
    }
    
    private String getExtension(final File file) {
        String lowerCase = null;
        String name;
        try {
            name = file.getName();
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        final int lastIndex = name.lastIndexOf(46);
        if (lastIndex > 0 && lastIndex < name.length() - 1) {
            lowerCase = name.substring(lastIndex + 1).toLowerCase();
        }
        return lowerCase;
    }
}
