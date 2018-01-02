// 
// Decompiled by Procyon v0.5.30
// 

package JUpload;

import java.net.URI;
import java.io.File;

public class MyFile extends File
{
    private File currentDirectory;
    private File thisFile;
    
    public MyFile(final String pathname) {
        super(pathname);
    }
    
    public MyFile(final URI uri) {
        super(uri);
    }
    
    public MyFile(final File parent, final String child) {
        super(parent, child);
    }
    
    public MyFile(final String parent, final String child) {
        super(parent, child);
    }
    
    public void setCurrentDirectory(final File file) {
        this.currentDirectory = file;
    }
    
    public File getCurrentDirectory() {
        return this.currentDirectory;
    }
    
    public String getRelativeFilename() {
        final String fullPath = this.getAbsolutePath();
        final String basePath = this.currentDirectory.toString();
        final int basePathLength = basePath.length();
        final String relPath = fullPath.substring(basePathLength + 1);
        return relPath;
    }
}
