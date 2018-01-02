// 
// Decompiled by Procyon v0.5.30
// 

package JUpload;

import java.util.StringTokenizer;
import java.io.File;
import javax.swing.filechooser.FileFilter;

public class CustomFileFilter extends FileFilter
{
    public String getDescription() {
        return Configurator.getCustomFileFilterDescription();
    }
    
    public boolean accept(final File f) {
        if (f.isDirectory()) {
            return true;
        }
        final String extension = Utils.getExtension(f);
        boolean status = false;
        if (extension != null) {
            final StringTokenizer st = new StringTokenizer(Configurator.getCustomFileFilterExtensions(), "/:,; \\");
            while (st.hasMoreTokens()) {
                final String ext = st.nextToken();
                if (extension.equalsIgnoreCase(ext)) {
                    status = true;
                }
            }
        }
        return status;
    }
}
