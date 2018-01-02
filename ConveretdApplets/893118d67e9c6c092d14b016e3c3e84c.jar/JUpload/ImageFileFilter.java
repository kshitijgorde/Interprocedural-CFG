// 
// Decompiled by Procyon v0.5.30
// 

package JUpload;

import java.io.File;
import javax.swing.filechooser.FileFilter;

public class ImageFileFilter extends FileFilter
{
    public String getDescription() {
        return "Image files";
    }
    
    public boolean accept(final File f) {
        if (f.isDirectory()) {
            return true;
        }
        final String extension = Utils.getExtension(f);
        return extension != null && (extension.equals("gif") || extension.equals("jpeg") || extension.equals("jpg") || extension.equals("png") || extension.equals("bmp"));
    }
}
